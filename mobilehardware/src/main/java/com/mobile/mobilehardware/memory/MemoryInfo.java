package com.mobile.mobilehardware.memory;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.usage.StorageStatsManager;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.text.format.Formatter;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

/**
 * @author guxiaonian
 */
class MemoryInfo {
    private static final String TAG = MemoryInfo.class.getSimpleName();

    /**
     * info
     *
     * @param context
     * @return
     */
    static MemoryBean memoryInfo(Context context) {
        MemoryBean memoryBean = new MemoryBean();
        try {
            memoryBean.setRamMemory(getTotalMemory(context));
            memoryBean.setRamAvailMemory(getAvailMemory(context));
            memoryBean.setRomMemoryAvailable(getRomSpace(context));
            memoryBean.setRomMemoryTotal(getRomSpaceTotal(context));
            memoryBean.setSdCardMemoryAvailable(getSdcardSize(context));
            memoryBean.setSdCardMemoryTotal(getSdcardSizeTotal(context));
            memoryBean.setSdCardRealMemoryTotal(getRealStorage(context));
        } catch (Exception e) {
            Log.i(TAG, e.toString());
        }
        return memoryBean;
    }

    /**
     * total
     *
     * @param context
     * @return
     */
    private static String getTotalMemory(Context context) {
        String   str1           = "/proc/meminfo";
        String   str2;
        String[] arrayOfString;
        long     initial_memory = 0;
        try {
            FileReader localFileReader;

            localFileReader = new FileReader(str1);

            BufferedReader localBufferedReader = new BufferedReader(localFileReader, 8192);
            str2 = localBufferedReader.readLine();

            arrayOfString = str2.split("\\s+");
            //noinspection StatementWithEmptyBody
            for (String num : arrayOfString) {
            }

            initial_memory = Long.valueOf(arrayOfString[1]) * 1024;
            localBufferedReader.close();

        } catch (Exception e) {
            Log.i(TAG, e.toString());
        }
        return getUnit(initial_memory);
    }

    /**
     * ??????android????????????????????????
     *
     * @param context
     * @return
     */
    private static String getAvailMemory(Context context) {

        ActivityManager            am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        if (am != null) {
            am.getMemoryInfo(mi);
        }
        //mi.availMem; ???????????????????????????
        return getUnit(mi.availMem);
    }

    /**
     * rom
     *
     * @param context
     * @return
     */
    private static String getRomSpace(Context context) {
        File   path            = Environment.getDataDirectory();
        StatFs stat            = new StatFs(path.getPath());
        long   blockSize       = stat.getBlockSize();
        long   availableBlocks = stat.getAvailableBlocks();
        return getUnit(availableBlocks * blockSize);
    }

    private static String getRomSpaceTotal(Context context) {
        File   path        = Environment.getDataDirectory();
        StatFs stat        = new StatFs(path.getPath());
        long   blockSize   = stat.getBlockSize();
        long   totalBlocks = stat.getBlockCount();
        return getUnit(totalBlocks * blockSize);
    }


    /**
     * sd is null ==rom
     *
     * @param context
     * @return
     */
    private static String getSdcardSize(Context context) {

        File   path            = Environment.getExternalStorageDirectory();
        StatFs stat            = new StatFs(path.getPath());
        long   blockSize       = stat.getBlockSize();
        long   availableBlocks = stat.getAvailableBlocks();
        return getUnit(availableBlocks * blockSize);
    }

    private static String getSdcardSizeTotal(Context context) {

        File   path       = Environment.getExternalStorageDirectory();
        StatFs stat       = new StatFs(path.getPath());
        long   blockCount = stat.getBlockCount();
        long   blockSize  = stat.getBlockSize();
        return getUnit(blockCount * blockSize);
    }

    private static String getRealStorage(Context context) {
        long total = 0L;
        try {
            StorageManager storageManager = (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
            int            version        = Build.VERSION.SDK_INT;
            if (version < Build.VERSION_CODES.M) {
                Method          getVolumeList = StorageManager.class.getDeclaredMethod("getVolumeList");
                StorageVolume[] volumeList    = (StorageVolume[]) getVolumeList.invoke(storageManager);
                if (volumeList != null) {
                    Method getPathFile = null;
                    for (StorageVolume volume : volumeList) {
                        if (getPathFile == null) {
                            getPathFile = volume.getClass().getDeclaredMethod("getPathFile");
                        }
                        File file = (File) getPathFile.invoke(volume);
                        total += file.getTotalSpace();
                    }
                }
            } else {
                @SuppressLint("PrivateApi") Method getVolumes    = StorageManager.class.getDeclaredMethod("getVolumes");
                List<Object>                       getVolumeInfo = (List<Object>) getVolumes.invoke(storageManager);
                for (Object obj : getVolumeInfo) {
                    Field getType = obj.getClass().getField("type");
                    int   type    = getType.getInt(obj);
                    if (type == 1) {
                        long totalSize = 0L;
                        if (version >= Build.VERSION_CODES.O) {
                            Method getFsUuid = obj.getClass().getDeclaredMethod("getFsUuid");
                            String fsUuid    = (String) getFsUuid.invoke(obj);
                            totalSize = getTotalSize(context, fsUuid);
                        } else if (version >= Build.VERSION_CODES.N_MR1) {
                            Method getPrimaryStorageSize = StorageManager.class.getMethod("getPrimaryStorageSize");
                            totalSize = (long) getPrimaryStorageSize.invoke(storageManager);
                        }
                        Method  isMountedReadable = obj.getClass().getDeclaredMethod("isMountedReadable");
                        boolean readable          = (boolean) isMountedReadable.invoke(obj);
                        if (readable) {
                            Method file = obj.getClass().getDeclaredMethod("getPath");
                            File   f    = (File) file.invoke(obj);
                            if (totalSize == 0) {
                                totalSize = f.getTotalSpace();
                            }
                            total += totalSize;
                        }
                    } else if (type == 0) {
                        Method  isMountedReadable = obj.getClass().getDeclaredMethod("isMountedReadable");
                        boolean readable          = (boolean) isMountedReadable.invoke(obj);
                        if (readable) {
                            Method file = obj.getClass().getDeclaredMethod("getPath");
                            File   f    = (File) file.invoke(obj);
                            total += f.getTotalSpace();
                        }
                    }
                }
            }
            return getUnit(total);
        } catch (Exception ignore) {

        }
        return null;
    }

    private static final String[] units = {"B", "KB", "MB", "GB", "TB"};

    /**
     * ????????????
     */
    public static String getUnit(long size) {
        BigDecimal tempSize = new BigDecimal(size);
        BigDecimal divide   = new BigDecimal(1024);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            divide = new BigDecimal(1000);
        }
        int index = 0;
        while (tempSize.compareTo(divide) >= 0 && index < 4) {
            tempSize = tempSize.divide(divide, 2, RoundingMode.HALF_UP);
            index++;
        }
        return String.format(Locale.getDefault(), "%.2f%s ", tempSize.floatValue(), units[index]);
    }

    /**
     * API 26 android O
     * ?????????????????????????????????????????????
     */
    @SuppressLint("NewApi")
    public static long getTotalSize(Context context, String fsUuid) {
        try {
            UUID id;
            if (fsUuid == null) {
                id = StorageManager.UUID_DEFAULT;
            } else {
                id = UUID.fromString(fsUuid);
            }
            StorageStatsManager stats = context.getSystemService(StorageStatsManager.class);
            return stats.getTotalBytes(id);
        } catch (NoSuchFieldError | NoClassDefFoundError | NullPointerException | IOException e) {
            e.printStackTrace();
            return -1;
        }
    }
}

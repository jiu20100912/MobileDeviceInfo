package com.mobile.mobilehardware.bluetooth;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/**
 * @author guxiaonian
 */
class BluetoothInfo {
    private static final String TAG = BluetoothInfo.class.getSimpleName();

    @SuppressLint("MissingPermission")
    static JSONObject getMobBluetooth(Context context) {
        BluetoothBean bluetoothBean = new BluetoothBean();
        try {
            bluetoothBean.setBluetoothAddress(Settings.Secure.getString(context.getContentResolver(), "bluetooth_address"));
            BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (bluetoothAdapter == null) {
                return bluetoothBean.toJSONObject();
            }
            if (TextUtils.isEmpty(bluetoothBean.getBluetoothAddress()) && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                try {
                    Field field = bluetoothAdapter.getClass().getDeclaredField("mService");
                    field.setAccessible(true);
                    Object bluetoothManagerService = field.get(bluetoothAdapter);
                    if (bluetoothManagerService != null) {
                        Method method  = bluetoothManagerService.getClass().getMethod("getAddress");
                        Object address = method.invoke(bluetoothManagerService);
                        if (address instanceof String) {
                            bluetoothBean.setBluetoothAddress((String) address);
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (TextUtils.isEmpty(bluetoothBean.getBluetoothAddress()))
                bluetoothBean.setBluetoothAddress(bluetoothAdapter.getAddress());
            //抛一个总异常省的一堆代码...
            bluetoothBean.setEnabled(bluetoothAdapter.isEnabled());
            bluetoothBean.setPhoneName(bluetoothAdapter.getName());
            Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
            List<JSONObject>     list          = new ArrayList<>();
            if (pairedDevices.size() > 0) {
                for (BluetoothDevice device : pairedDevices) {
                    BluetoothBean.DeviceBean deviceBean = new BluetoothBean.DeviceBean();
                    deviceBean.setAddress(device.getAddress());
                    deviceBean.setName(device.getName());
                    list.add(deviceBean.toJSONObject());
                }
            }
            bluetoothBean.setDevice(list);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return bluetoothBean.toJSONObject();
    }

}

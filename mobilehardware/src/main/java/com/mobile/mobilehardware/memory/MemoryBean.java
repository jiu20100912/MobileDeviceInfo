package com.mobile.mobilehardware.memory;

import android.util.Log;

import com.mobile.mobilehardware.base.BaseBean;
import com.mobile.mobilehardware.base.BaseData;

import org.json.JSONObject;

/**
 * @author guxiaonian
 */
public class MemoryBean extends BaseBean {
    private static final String TAG = MemoryBean.class.getSimpleName();

    /**
     * ram total
     */
    private String ramMemory;

    /**
     * ram  Available
     */
    private String ramAvailMemory;

    /**
     * rom Available
     */
    private String romMemoryAvailable;

    /**
     * rom total
     */
    private String romMemoryTotal;

    /**
     * sdcard Available
     */
    private String sdCardMemoryAvailable;

    /**
     * sdcard total
     */
    private String sdCardMemoryTotal;

    private String sdCardRealMemoryTotal;

    public String getSdCardRealMemoryTotal() {
        return sdCardRealMemoryTotal;
    }

    public void setSdCardRealMemoryTotal(String sdCardRealMemoryTotal) {
        this.sdCardRealMemoryTotal = sdCardRealMemoryTotal;
    }

    public String getRamMemory() {
        return ramMemory;
    }

    public void setRamMemory(String ramMemory) {
        this.ramMemory = ramMemory;
    }

    public String getRamAvailMemory() {
        return ramAvailMemory;
    }

    public void setRamAvailMemory(String ramAvailMemory) {
        this.ramAvailMemory = ramAvailMemory;
    }

    public String getRomMemoryAvailable() {
        return romMemoryAvailable;
    }

    public void setRomMemoryAvailable(String romMemoryAvailable) {
        this.romMemoryAvailable = romMemoryAvailable;
    }

    public String getRomMemoryTotal() {
        return romMemoryTotal;
    }

    public void setRomMemoryTotal(String romMemoryTotal) {
        this.romMemoryTotal = romMemoryTotal;
    }

    public String getSdCardMemoryAvailable() {
        return sdCardMemoryAvailable;
    }

    public void setSdCardMemoryAvailable(String sdCardMemoryAvailable) {
        this.sdCardMemoryAvailable = sdCardMemoryAvailable;
    }

    public String getSdCardMemoryTotal() {
        return sdCardMemoryTotal;
    }

    public void setSdCardMemoryTotal(String sdCardMemoryTotal) {
        this.sdCardMemoryTotal = sdCardMemoryTotal;
    }
}

package com.mobile.mobilehardware.bluetooth;

import android.util.Log;

import com.mobile.mobilehardware.base.BaseBean;
import com.mobile.mobilehardware.base.BaseData;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * @author guxiaonian
 */
public class BluetoothBean extends BaseBean {
    private static final String TAG = BluetoothBean.class.getSimpleName();

    /**
     * 蓝牙地址
     */
    private String bluetoothAddress;

    /**
     * 蓝牙是否打开
     */
    private boolean isEnabled;

    /**
     * 连接的手机的信息
     */
    private List<BluetoothBean.DeviceBean> device;

    /**
     * 手机设置的名字
     */
    private String phoneName;

    public String getBluetoothAddress() {
        return bluetoothAddress;
    }

    public void setBluetoothAddress(String bluetoothAddress) {
        this.bluetoothAddress = bluetoothAddress;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public List<BluetoothBean.DeviceBean> getDevice() {
        return device;
    }

    public void setDevice(List<BluetoothBean.DeviceBean> device) {
        this.device = device;
    }

    public String getPhoneName() {
        return phoneName;
    }

    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
    }

    public static class DeviceBean extends BaseBean {

        /**
         * 连接手机的蓝牙地址
         */
        private String address;

        /**
         * 连接手机的蓝牙名字
         */
        private String name;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
}

package com.mobile.mobilehardware.setting;

import android.util.Log;

import com.mobile.mobilehardware.base.BaseBean;
import com.mobile.mobilehardware.base.BaseData;

import org.json.JSONObject;

/**
 * @author guxiaonian
 */
public class SettingsBean extends BaseBean {
    private static final String TAG = SettingsBean.class.getSimpleName();

    /**
     * android id
     */
    private String androidId;
    private String screenOffTimeout;
    private String soundEffectsEnabled;
    private String screenBrightnessMode;
    private String developmentSettingsEnabled;
    private String accelerometerRotation;
    private String lockPatternVisiblePattern;
    private String lockPatternAutolock;
    private String usbMassStorageEnabled;
    private boolean allowMockLocation;

    public boolean getAllowMockLocation() {
        return allowMockLocation;
    }

    public void setAllowMockLocation(boolean allowMockLocation) {
        this.allowMockLocation = allowMockLocation;
    }

    public String getUsbMassStorageEnabled() {
        return usbMassStorageEnabled;
    }

    public void setUsbMassStorageEnabled(String usbMassStorageEnabled) {
        this.usbMassStorageEnabled = usbMassStorageEnabled;
    }

    public String getScreenOffTimeout() {
        return screenOffTimeout;
    }

    public void setScreenOffTimeout(String screenOffTimeout) {
        this.screenOffTimeout = screenOffTimeout;
    }

    public String getSoundEffectsEnabled() {
        return soundEffectsEnabled;
    }

    public void setSoundEffectsEnabled(String soundEffectsEnabled) {
        this.soundEffectsEnabled = soundEffectsEnabled;
    }

    public String getScreenBrightnessMode() {
        return screenBrightnessMode;
    }

    public void setScreenBrightnessMode(String screenBrightnessMode) {
        this.screenBrightnessMode = screenBrightnessMode;
    }

    public String getDevelopmentSettingsEnabled() {
        return developmentSettingsEnabled;
    }

    public void setDevelopmentSettingsEnabled(String developmentSettingsEnabled) {
        this.developmentSettingsEnabled = developmentSettingsEnabled;
    }

    public String getAccelerometerRotation() {
        return accelerometerRotation;
    }

    public void setAccelerometerRotation(String accelerometerRotation) {
        this.accelerometerRotation = accelerometerRotation;
    }

    public String getLockPatternVisiblePattern() {
        return lockPatternVisiblePattern;
    }

    public void setLockPatternVisiblePattern(String lockPatternVisiblePattern) {
        this.lockPatternVisiblePattern = lockPatternVisiblePattern;
    }

    public String getLockPatternAutolock() {
        return lockPatternAutolock;
    }

    public void setLockPatternAutolock(String lockPatternAutolock) {
        this.lockPatternAutolock = lockPatternAutolock;
    }

    public String getAndroidId() {
        return androidId;
    }

    public void setAndroidId(String androidId) {
        this.androidId = androidId;
    }

}

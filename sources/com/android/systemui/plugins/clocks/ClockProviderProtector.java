package com.android.systemui.plugins.clocks;

import android.util.Log;
import com.android.systemui.plugins.PluginWrapper;
import com.android.systemui.plugins.ProtectedPluginListener;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class ClockProviderProtector implements ClockProvider, PluginWrapper {
    private static final String CLASS = "ClockProvider";
    private boolean mHasError = false;
    private ClockProvider mInstance;
    private ProtectedPluginListener mListener;

    private ClockProviderProtector(ClockProvider clockProvider, ProtectedPluginListener protectedPluginListener) {
        this.mInstance = clockProvider;
        this.mListener = protectedPluginListener;
    }

    public static ClockProviderProtector protect(ClockProvider clockProvider, ProtectedPluginListener protectedPluginListener) {
        return clockProvider instanceof ClockProviderProtector ? (ClockProviderProtector) clockProvider : new ClockProviderProtector(clockProvider, protectedPluginListener);
    }

    @Override // com.android.systemui.plugins.clocks.ClockProvider
    public ClockController createClock(ClockSettings clockSettings) {
        if (this.mHasError) {
            return null;
        }
        try {
            return ClockControllerProtector.protect(this.mInstance.createClock(clockSettings), this.mListener);
        } catch (LinkageError e) {
            Log.wtf(CLASS, "Failed to execute: createClock", e);
            this.mHasError = this.mListener.onFail(CLASS, "createClock", e);
            return null;
        }
    }

    @Override // com.android.systemui.plugins.clocks.ClockProvider
    public ClockPickerConfig getClockPickerConfig(String str) {
        if (this.mHasError) {
            return new ClockPickerConfig("", "", "", null);
        }
        try {
            return this.mInstance.getClockPickerConfig(str);
        } catch (LinkageError e) {
            Log.wtf(CLASS, "Failed to execute: getClockPickerConfig", e);
            this.mHasError = this.mListener.onFail(CLASS, "getClockPickerConfig", e);
            return new ClockPickerConfig("", "", "", null);
        }
    }

    @Override // com.android.systemui.plugins.clocks.ClockProvider
    public List getClocks() {
        if (this.mHasError) {
            return new ArrayList();
        }
        try {
            return this.mInstance.getClocks();
        } catch (LinkageError e) {
            Log.wtf(CLASS, "Failed to execute: getClocks", e);
            this.mHasError = this.mListener.onFail(CLASS, "getClocks", e);
            return new ArrayList();
        }
    }

    @Override // com.android.systemui.plugins.clocks.ClockProvider
    public void initialize(ClockMessageBuffers clockMessageBuffers) {
        if (this.mHasError) {
            return;
        }
        try {
            this.mInstance.initialize(clockMessageBuffers);
        } catch (LinkageError e) {
            Log.wtf(CLASS, "Failed to execute: initialize", e);
            this.mHasError = this.mListener.onFail(CLASS, "initialize", e);
        }
    }

    @Override // com.android.systemui.plugins.PluginWrapper
    public ClockProvider getPlugin() {
        return this.mInstance;
    }
}

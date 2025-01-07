package com.android.systemui.plugins.clocks;

import android.graphics.Rect;
import android.util.Log;
import com.android.systemui.plugins.PluginWrapper;
import com.android.systemui.plugins.ProtectedPluginListener;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class ClockFaceEventsProtector implements ClockFaceEvents, PluginWrapper {
    private static final String CLASS = "ClockFaceEvents";
    private boolean mHasError = false;
    private ClockFaceEvents mInstance;
    private ProtectedPluginListener mListener;

    private ClockFaceEventsProtector(ClockFaceEvents clockFaceEvents, ProtectedPluginListener protectedPluginListener) {
        this.mInstance = clockFaceEvents;
        this.mListener = protectedPluginListener;
    }

    public static ClockFaceEventsProtector protect(ClockFaceEvents clockFaceEvents, ProtectedPluginListener protectedPluginListener) {
        return clockFaceEvents instanceof ClockFaceEventsProtector ? (ClockFaceEventsProtector) clockFaceEvents : new ClockFaceEventsProtector(clockFaceEvents, protectedPluginListener);
    }

    @Override // com.android.systemui.plugins.clocks.ClockFaceEvents
    public void onFontSettingChanged(float f) {
        if (this.mHasError) {
            return;
        }
        try {
            this.mInstance.onFontSettingChanged(f);
        } catch (LinkageError e) {
            Log.wtf(CLASS, "Failed to execute: onFontSettingChanged", e);
            this.mHasError = this.mListener.onFail(CLASS, "onFontSettingChanged", e);
        }
    }

    @Override // com.android.systemui.plugins.clocks.ClockFaceEvents
    public void onRegionDarknessChanged(boolean z) {
        if (this.mHasError) {
            return;
        }
        try {
            this.mInstance.onRegionDarknessChanged(z);
        } catch (LinkageError e) {
            Log.wtf(CLASS, "Failed to execute: onRegionDarknessChanged", e);
            this.mHasError = this.mListener.onFail(CLASS, "onRegionDarknessChanged", e);
        }
    }

    @Override // com.android.systemui.plugins.clocks.ClockFaceEvents
    public void onSecondaryDisplayChanged(boolean z) {
        if (this.mHasError) {
            return;
        }
        try {
            this.mInstance.onSecondaryDisplayChanged(z);
        } catch (LinkageError e) {
            Log.wtf(CLASS, "Failed to execute: onSecondaryDisplayChanged", e);
            this.mHasError = this.mListener.onFail(CLASS, "onSecondaryDisplayChanged", e);
        }
    }

    @Override // com.android.systemui.plugins.clocks.ClockFaceEvents
    public void onTargetRegionChanged(Rect rect) {
        if (this.mHasError) {
            return;
        }
        try {
            this.mInstance.onTargetRegionChanged(rect);
        } catch (LinkageError e) {
            Log.wtf(CLASS, "Failed to execute: onTargetRegionChanged", e);
            this.mHasError = this.mListener.onFail(CLASS, "onTargetRegionChanged", e);
        }
    }

    @Override // com.android.systemui.plugins.clocks.ClockFaceEvents
    public void onTimeTick() {
        if (this.mHasError) {
            return;
        }
        try {
            this.mInstance.onTimeTick();
        } catch (LinkageError e) {
            Log.wtf(CLASS, "Failed to execute: onTimeTick", e);
            this.mHasError = this.mListener.onFail(CLASS, "onTimeTick", e);
        }
    }

    @Override // com.android.systemui.plugins.PluginWrapper
    public ClockFaceEvents getPlugin() {
        return this.mInstance;
    }
}

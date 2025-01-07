package com.android.systemui.plugins.clocks;

import android.util.Log;
import com.android.systemui.plugins.PluginWrapper;
import com.android.systemui.plugins.ProtectedPluginListener;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class ClockAnimationsProtector implements ClockAnimations, PluginWrapper {
    private static final String CLASS = "ClockAnimations";
    private boolean mHasError = false;
    private ClockAnimations mInstance;
    private ProtectedPluginListener mListener;

    private ClockAnimationsProtector(ClockAnimations clockAnimations, ProtectedPluginListener protectedPluginListener) {
        this.mInstance = clockAnimations;
        this.mListener = protectedPluginListener;
    }

    public static ClockAnimationsProtector protect(ClockAnimations clockAnimations, ProtectedPluginListener protectedPluginListener) {
        return clockAnimations instanceof ClockAnimationsProtector ? (ClockAnimationsProtector) clockAnimations : new ClockAnimationsProtector(clockAnimations, protectedPluginListener);
    }

    @Override // com.android.systemui.plugins.clocks.ClockAnimations
    public void charge() {
        if (this.mHasError) {
            return;
        }
        try {
            this.mInstance.charge();
        } catch (LinkageError e) {
            Log.wtf(CLASS, "Failed to execute: charge", e);
            this.mHasError = this.mListener.onFail(CLASS, "charge", e);
        }
    }

    @Override // com.android.systemui.plugins.clocks.ClockAnimations
    public void doze(float f) {
        if (this.mHasError) {
            return;
        }
        try {
            this.mInstance.doze(f);
        } catch (LinkageError e) {
            Log.wtf(CLASS, "Failed to execute: doze", e);
            this.mHasError = this.mListener.onFail(CLASS, "doze", e);
        }
    }

    @Override // com.android.systemui.plugins.clocks.ClockAnimations
    public void enter() {
        if (this.mHasError) {
            return;
        }
        try {
            this.mInstance.enter();
        } catch (LinkageError e) {
            Log.wtf(CLASS, "Failed to execute: enter", e);
            this.mHasError = this.mListener.onFail(CLASS, "enter", e);
        }
    }

    @Override // com.android.systemui.plugins.clocks.ClockAnimations
    public void fold(float f) {
        if (this.mHasError) {
            return;
        }
        try {
            this.mInstance.fold(f);
        } catch (LinkageError e) {
            Log.wtf(CLASS, "Failed to execute: fold", e);
            this.mHasError = this.mListener.onFail(CLASS, "fold", e);
        }
    }

    @Override // com.android.systemui.plugins.clocks.ClockAnimations
    public void onPickerCarouselSwiping(float f) {
        if (this.mHasError) {
            return;
        }
        try {
            this.mInstance.onPickerCarouselSwiping(f);
        } catch (LinkageError e) {
            Log.wtf(CLASS, "Failed to execute: onPickerCarouselSwiping", e);
            this.mHasError = this.mListener.onFail(CLASS, "onPickerCarouselSwiping", e);
        }
    }

    @Override // com.android.systemui.plugins.clocks.ClockAnimations
    public void onPositionUpdated(int i, int i2, float f) {
        if (this.mHasError) {
            return;
        }
        try {
            this.mInstance.onPositionUpdated(i, i2, f);
        } catch (LinkageError e) {
            Log.wtf(CLASS, "Failed to execute: onPositionUpdated", e);
            this.mHasError = this.mListener.onFail(CLASS, "onPositionUpdated", e);
        }
    }

    @Override // com.android.systemui.plugins.PluginWrapper
    public ClockAnimations getPlugin() {
        return this.mInstance;
    }

    @Override // com.android.systemui.plugins.clocks.ClockAnimations
    public void onPositionUpdated(float f, float f2) {
        if (this.mHasError) {
            return;
        }
        try {
            this.mInstance.onPositionUpdated(f, f2);
        } catch (LinkageError e) {
            Log.wtf(CLASS, "Failed to execute: onPositionUpdated", e);
            this.mHasError = this.mListener.onFail(CLASS, "onPositionUpdated", e);
        }
    }
}

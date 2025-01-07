package com.android.systemui.plugins.clocks;

import android.util.Log;
import androidx.constraintlayout.widget.ConstraintSet;
import com.android.systemui.plugins.PluginWrapper;
import com.android.systemui.plugins.ProtectedPluginListener;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class ClockFaceLayoutProtector implements ClockFaceLayout, PluginWrapper {
    private static final String CLASS = "ClockFaceLayout";
    private boolean mHasError = false;
    private ClockFaceLayout mInstance;
    private ProtectedPluginListener mListener;

    private ClockFaceLayoutProtector(ClockFaceLayout clockFaceLayout, ProtectedPluginListener protectedPluginListener) {
        this.mInstance = clockFaceLayout;
        this.mListener = protectedPluginListener;
    }

    public static ClockFaceLayoutProtector protect(ClockFaceLayout clockFaceLayout, ProtectedPluginListener protectedPluginListener) {
        return clockFaceLayout instanceof ClockFaceLayoutProtector ? (ClockFaceLayoutProtector) clockFaceLayout : new ClockFaceLayoutProtector(clockFaceLayout, protectedPluginListener);
    }

    @Override // com.android.systemui.plugins.clocks.ClockFaceLayout
    public void applyAodBurnIn(AodClockBurnInModel aodClockBurnInModel) {
        if (this.mHasError) {
            return;
        }
        try {
            this.mInstance.applyAodBurnIn(aodClockBurnInModel);
        } catch (LinkageError e) {
            Log.wtf(CLASS, "Failed to execute: applyAodBurnIn", e);
            this.mHasError = this.mListener.onFail(CLASS, "applyAodBurnIn", e);
        }
    }

    @Override // com.android.systemui.plugins.clocks.ClockFaceLayout
    public ConstraintSet applyConstraints(ConstraintSet constraintSet) {
        if (this.mHasError) {
            return constraintSet;
        }
        try {
            return this.mInstance.applyConstraints(constraintSet);
        } catch (LinkageError e) {
            Log.wtf(CLASS, "Failed to execute: applyConstraints", e);
            this.mHasError = this.mListener.onFail(CLASS, "applyConstraints", e);
            return constraintSet;
        }
    }

    @Override // com.android.systemui.plugins.clocks.ClockFaceLayout
    public ConstraintSet applyPreviewConstraints(ConstraintSet constraintSet) {
        if (this.mHasError) {
            return constraintSet;
        }
        try {
            return this.mInstance.applyPreviewConstraints(constraintSet);
        } catch (LinkageError e) {
            Log.wtf(CLASS, "Failed to execute: applyPreviewConstraints", e);
            this.mHasError = this.mListener.onFail(CLASS, "applyPreviewConstraints", e);
            return constraintSet;
        }
    }

    @Override // com.android.systemui.plugins.clocks.ClockFaceLayout
    public List getViews() {
        if (this.mHasError) {
            return new ArrayList();
        }
        try {
            return this.mInstance.getViews();
        } catch (LinkageError e) {
            Log.wtf(CLASS, "Failed to execute: getViews", e);
            this.mHasError = this.mListener.onFail(CLASS, "getViews", e);
            return new ArrayList();
        }
    }

    @Override // com.android.systemui.plugins.PluginWrapper
    public ClockFaceLayout getPlugin() {
        return this.mInstance;
    }
}

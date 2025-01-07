package com.android.systemui.plugins.clocks;

import android.content.res.Resources;
import android.util.Log;
import com.android.systemui.plugins.PluginWrapper;
import com.android.systemui.plugins.ProtectedPluginListener;
import java.io.PrintWriter;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class ClockControllerProtector implements ClockController, PluginWrapper {
    private static final String CLASS = "ClockController";
    private boolean mHasError = false;
    private ClockController mInstance;
    private ProtectedPluginListener mListener;

    private ClockControllerProtector(ClockController clockController, ProtectedPluginListener protectedPluginListener) {
        this.mInstance = clockController;
        this.mListener = protectedPluginListener;
    }

    public static ClockControllerProtector protect(ClockController clockController, ProtectedPluginListener protectedPluginListener) {
        return clockController instanceof ClockControllerProtector ? (ClockControllerProtector) clockController : new ClockControllerProtector(clockController, protectedPluginListener);
    }

    @Override // com.android.systemui.plugins.clocks.ClockController
    public void dump(PrintWriter printWriter) {
        if (this.mHasError) {
            return;
        }
        try {
            this.mInstance.dump(printWriter);
        } catch (LinkageError e) {
            Log.wtf(CLASS, "Failed to execute: dump", e);
            this.mHasError = this.mListener.onFail(CLASS, "dump", e);
        }
    }

    @Override // com.android.systemui.plugins.clocks.ClockController
    public ClockConfig getConfig() {
        return this.mInstance.getConfig();
    }

    @Override // com.android.systemui.plugins.clocks.ClockController
    public ClockEvents getEvents() {
        return ClockEventsProtector.protect(this.mInstance.getEvents(), this.mListener);
    }

    @Override // com.android.systemui.plugins.clocks.ClockController
    public ClockFaceController getLargeClock() {
        return ClockFaceControllerProtector.protect(this.mInstance.getLargeClock(), this.mListener);
    }

    @Override // com.android.systemui.plugins.clocks.ClockController
    public ClockFaceController getSmallClock() {
        return ClockFaceControllerProtector.protect(this.mInstance.getSmallClock(), this.mListener);
    }

    @Override // com.android.systemui.plugins.clocks.ClockController
    public void initialize(Resources resources, float f, float f2) {
        if (this.mHasError) {
            return;
        }
        try {
            this.mInstance.initialize(resources, f, f2);
        } catch (LinkageError e) {
            Log.wtf(CLASS, "Failed to execute: initialize", e);
            this.mHasError = this.mListener.onFail(CLASS, "initialize", e);
        }
    }

    @Override // com.android.systemui.plugins.PluginWrapper
    public ClockController getPlugin() {
        return this.mInstance;
    }
}

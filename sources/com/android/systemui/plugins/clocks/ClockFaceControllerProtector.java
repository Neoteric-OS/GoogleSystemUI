package com.android.systemui.plugins.clocks;

import android.view.View;
import com.android.systemui.plugins.PluginWrapper;
import com.android.systemui.plugins.ProtectedPluginListener;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class ClockFaceControllerProtector implements ClockFaceController, PluginWrapper {
    private static final String CLASS = "ClockFaceController";
    private boolean mHasError = false;
    private ClockFaceController mInstance;
    private ProtectedPluginListener mListener;

    private ClockFaceControllerProtector(ClockFaceController clockFaceController, ProtectedPluginListener protectedPluginListener) {
        this.mInstance = clockFaceController;
        this.mListener = protectedPluginListener;
    }

    public static ClockFaceControllerProtector protect(ClockFaceController clockFaceController, ProtectedPluginListener protectedPluginListener) {
        return clockFaceController instanceof ClockFaceControllerProtector ? (ClockFaceControllerProtector) clockFaceController : new ClockFaceControllerProtector(clockFaceController, protectedPluginListener);
    }

    @Override // com.android.systemui.plugins.clocks.ClockFaceController
    public ClockAnimations getAnimations() {
        return ClockAnimationsProtector.protect(this.mInstance.getAnimations(), this.mListener);
    }

    @Override // com.android.systemui.plugins.clocks.ClockFaceController
    public ClockFaceConfig getConfig() {
        return this.mInstance.getConfig();
    }

    @Override // com.android.systemui.plugins.clocks.ClockFaceController
    public ClockFaceEvents getEvents() {
        return ClockFaceEventsProtector.protect(this.mInstance.getEvents(), this.mListener);
    }

    @Override // com.android.systemui.plugins.clocks.ClockFaceController
    public ClockFaceLayout getLayout() {
        return ClockFaceLayoutProtector.protect(this.mInstance.getLayout(), this.mListener);
    }

    @Override // com.android.systemui.plugins.clocks.ClockFaceController
    public View getView() {
        return this.mInstance.getView();
    }

    @Override // com.android.systemui.plugins.PluginWrapper
    public ClockFaceController getPlugin() {
        return this.mInstance;
    }
}

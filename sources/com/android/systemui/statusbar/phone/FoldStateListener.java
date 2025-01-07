package com.android.systemui.statusbar.phone;

import android.R;
import android.content.Context;
import android.hardware.devicestate.DeviceState;
import android.hardware.devicestate.DeviceStateManager;
import android.os.Trace;
import com.android.systemui.shade.ShadeController;
import com.android.systemui.statusbar.StatusBarStateControllerImpl;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FoldStateListener implements DeviceStateManager.DeviceStateCallback {
    public final int[] foldedDeviceStates;
    public final int[] goToSleepDeviceStates;
    public final CentralSurfacesImpl$$ExternalSyntheticLambda0 listener;
    public Boolean wasFolded;

    public FoldStateListener(Context context, CentralSurfacesImpl$$ExternalSyntheticLambda0 centralSurfacesImpl$$ExternalSyntheticLambda0) {
        this.listener = centralSurfacesImpl$$ExternalSyntheticLambda0;
        this.foldedDeviceStates = context.getResources().getIntArray(R.array.config_fontManagerServiceCerts);
        this.goToSleepDeviceStates = context.getResources().getIntArray(R.array.config_deviceStatesOnWhichToWakeUp);
    }

    public final void onDeviceStateChanged(DeviceState deviceState) {
        boolean contains = ArraysKt.contains(this.foldedDeviceStates, deviceState.getIdentifier());
        if (Intrinsics.areEqual(this.wasFolded, Boolean.valueOf(contains))) {
            return;
        }
        this.wasFolded = Boolean.valueOf(contains);
        boolean contains2 = ArraysKt.contains(this.goToSleepDeviceStates, deviceState.getIdentifier());
        CentralSurfacesImpl centralSurfacesImpl = (CentralSurfacesImpl) this.listener.f$0;
        Trace.beginSection("CentralSurfaces#onFoldedStateChanged");
        ShadeController shadeController = centralSurfacesImpl.mShadeController;
        boolean isShadeFullyOpen = shadeController.isShadeFullyOpen();
        boolean isExpandingOrCollapsing = shadeController.isExpandingOrCollapsing();
        if (isShadeFullyOpen && !contains2 && centralSurfacesImpl.mState == 0) {
            ((StatusBarStateControllerImpl) centralSurfacesImpl.mStatusBarStateController).mLeaveOpenOnKeyguardHide = true;
        }
        if (centralSurfacesImpl.mState != 0 && (isShadeFullyOpen || isExpandingOrCollapsing)) {
            centralSurfacesImpl.mCloseQsBeforeScreenOff = true;
        }
        Trace.endSection();
    }
}

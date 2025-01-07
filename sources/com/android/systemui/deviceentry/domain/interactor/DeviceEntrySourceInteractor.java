package com.android.systemui.deviceentry.domain.interactor;

import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import com.android.systemui.util.kotlin.FlowKt;
import kotlinx.coroutines.flow.FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.SharedFlowImpl;
import kotlinx.coroutines.flow.SharedFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DeviceEntrySourceInteractor {
    public final SharedFlowImpl attemptEnterDeviceFromDeviceEntryIcon;
    public final FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1 deviceEntryFromBiometricSource;
    public final DeviceEntrySourceInteractor$special$$inlined$map$1 deviceEntryFromDeviceEntryIcon;

    public DeviceEntrySourceInteractor(KeyguardInteractor keyguardInteractor) {
        this.deviceEntryFromBiometricSource = new FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1(new DeviceEntrySourceInteractor$special$$inlined$map$1(new DeviceEntrySourceInteractor$special$$inlined$map$1(keyguardInteractor.biometricUnlockState, 1), 0));
        SharedFlowImpl MutableSharedFlow$default = SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7);
        this.attemptEnterDeviceFromDeviceEntryIcon = MutableSharedFlow$default;
        this.deviceEntryFromDeviceEntryIcon = new DeviceEntrySourceInteractor$special$$inlined$map$1(new DeviceEntrySourceInteractor$special$$inlined$map$1(FlowKt.sample(MutableSharedFlow$default, keyguardInteractor.isKeyguardDismissible), 2), 3);
    }
}

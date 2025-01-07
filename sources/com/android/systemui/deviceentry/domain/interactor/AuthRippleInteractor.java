package com.android.systemui.deviceentry.domain.interactor;

import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;
import kotlinx.coroutines.flow.internal.ChannelLimitedFlowMerge;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AuthRippleInteractor {
    public final ChannelLimitedFlowMerge showUnlockRipple;
    public final FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1 showUnlockRippleFromBiometricUnlock;
    public final ChannelFlowTransformLatest showUnlockRippleFromDeviceEntryIcon;

    public AuthRippleInteractor(DeviceEntrySourceInteractor deviceEntrySourceInteractor, DeviceEntryUdfpsInteractor deviceEntryUdfpsInteractor) {
        this.showUnlockRipple = FlowKt.merge(FlowKt.transformLatest(deviceEntryUdfpsInteractor.isUdfpsSupported, new AuthRippleInteractor$special$$inlined$flatMapLatest$1(null, deviceEntrySourceInteractor)), deviceEntrySourceInteractor.deviceEntryFromBiometricSource);
    }
}

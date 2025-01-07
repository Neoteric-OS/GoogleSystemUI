package com.android.systemui.deviceentry.domain.interactor;

import kotlinx.coroutines.flow.EmptyFlow;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class NoopDeviceEntryFaceAuthInteractor implements DeviceEntryFaceAuthInteractor {
    public final StateFlowImpl isAuthenticated;
    public final FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2 isBypassEnabled;
    public final StateFlowImpl isLockedOut;

    public NoopDeviceEntryFaceAuthInteractor() {
        Boolean bool = Boolean.FALSE;
        this.isLockedOut = StateFlowKt.MutableStateFlow(bool);
        this.isAuthenticated = StateFlowKt.MutableStateFlow(bool);
        this.isBypassEnabled = new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(bool);
    }

    @Override // com.android.systemui.deviceentry.domain.interactor.DeviceEntryFaceAuthInteractor
    public final boolean canFaceAuthRun() {
        return false;
    }

    @Override // com.android.systemui.deviceentry.domain.interactor.DeviceEntryFaceAuthInteractor
    public final Flow getAuthenticationStatus() {
        return EmptyFlow.INSTANCE;
    }

    @Override // com.android.systemui.deviceentry.domain.interactor.DeviceEntryFaceAuthInteractor
    public final StateFlow isAuthenticated() {
        return this.isAuthenticated;
    }

    @Override // com.android.systemui.deviceentry.domain.interactor.DeviceEntryFaceAuthInteractor
    public final Flow isBypassEnabled() {
        return this.isBypassEnabled;
    }

    @Override // com.android.systemui.deviceentry.domain.interactor.DeviceEntryFaceAuthInteractor
    public final boolean isFaceAuthEnabledAndEnrolled() {
        return false;
    }

    @Override // com.android.systemui.deviceentry.domain.interactor.DeviceEntryFaceAuthInteractor
    public final boolean isFaceAuthStrong() {
        return false;
    }

    @Override // com.android.systemui.deviceentry.domain.interactor.DeviceEntryFaceAuthInteractor
    public final StateFlow isLockedOut() {
        return this.isLockedOut;
    }

    @Override // com.android.systemui.deviceentry.domain.interactor.DeviceEntryFaceAuthInteractor
    public final boolean isRunning() {
        return false;
    }

    @Override // com.android.systemui.deviceentry.domain.interactor.DeviceEntryFaceAuthInteractor
    public final void onAccessibilityAction() {
    }

    @Override // com.android.systemui.deviceentry.domain.interactor.DeviceEntryFaceAuthInteractor
    public final void onDeviceLifted() {
    }

    @Override // com.android.systemui.deviceentry.domain.interactor.DeviceEntryFaceAuthInteractor
    public final void onNotificationPanelClicked() {
    }

    @Override // com.android.systemui.deviceentry.domain.interactor.DeviceEntryFaceAuthInteractor
    public final void onPrimaryBouncerUserInput() {
    }

    @Override // com.android.systemui.deviceentry.domain.interactor.DeviceEntryFaceAuthInteractor
    public final void onShadeExpansionStarted() {
    }

    @Override // com.android.systemui.deviceentry.domain.interactor.DeviceEntryFaceAuthInteractor
    public final void onSwipeUpOnBouncer() {
    }

    @Override // com.android.systemui.deviceentry.domain.interactor.DeviceEntryFaceAuthInteractor
    public final void onUdfpsSensorTouched() {
    }

    @Override // com.android.systemui.deviceentry.domain.interactor.DeviceEntryFaceAuthInteractor
    public final void onWalletLaunched() {
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
    }
}

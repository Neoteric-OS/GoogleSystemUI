package com.google.android.systemui.keyguard.domain.interactor;

import com.android.systemui.biometrics.AuthController;
import com.android.systemui.bouncer.domain.interactor.AlternateBouncerInteractor;
import com.android.systemui.keyguard.data.repository.BiometricSettingsRepositoryImpl;
import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class RefreshRateInteractor {
    public final AlternateBouncerInteractor alternateBouncerInteractor;
    public final AuthController authController;
    public final ChannelFlowTransformLatest requestOverridingMaxRefreshRate;

    public RefreshRateInteractor(BiometricSettingsRepositoryImpl biometricSettingsRepositoryImpl, KeyguardInteractor keyguardInteractor, AlternateBouncerInteractor alternateBouncerInteractor, AuthController authController) {
        this.alternateBouncerInteractor = alternateBouncerInteractor;
        this.authController = authController;
        this.requestOverridingMaxRefreshRate = FlowKt.transformLatest(biometricSettingsRepositoryImpl.isFingerprintEnrolledAndEnabled, new RefreshRateInteractor$special$$inlined$flatMapLatest$1(null, keyguardInteractor, this));
    }
}

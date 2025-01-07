package com.android.systemui.biometrics.domain.interactor;

import android.app.ActivityTaskManager;
import com.android.systemui.biometrics.data.repository.BiometricStatusRepositoryImpl;
import com.android.systemui.biometrics.data.repository.BiometricStatusRepositoryImpl$special$$inlined$map$2;
import com.android.systemui.biometrics.data.repository.FingerprintPropertyRepository;
import com.android.systemui.biometrics.data.repository.FingerprintPropertyRepositoryImpl;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BiometricStatusInteractorImpl {
    public final ActivityTaskManager activityTaskManager;
    public final BiometricStatusRepositoryImpl$special$$inlined$map$2 fingerprintAcquiredStatus;
    public final FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1 sfpsAuthenticationReason;

    public BiometricStatusInteractorImpl(ActivityTaskManager activityTaskManager, BiometricStatusRepositoryImpl biometricStatusRepositoryImpl, FingerprintPropertyRepository fingerprintPropertyRepository) {
        this.activityTaskManager = activityTaskManager;
        this.sfpsAuthenticationReason = new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(FlowKt.distinctUntilChanged(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(biometricStatusRepositoryImpl.fingerprintAuthenticationReason, ((FingerprintPropertyRepositoryImpl) fingerprintPropertyRepository).sensorType, new BiometricStatusInteractorImpl$sfpsAuthenticationReason$1(this, null))), new BiometricStatusInteractorImpl$sfpsAuthenticationReason$2(2, null), 0);
        this.fingerprintAcquiredStatus = biometricStatusRepositoryImpl.fingerprintAcquiredStatus;
    }
}

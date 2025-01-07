package com.android.systemui.biometrics.domain.interactor;

import android.content.Context;
import android.view.WindowManager;
import com.android.systemui.biometrics.data.repository.FingerprintPropertyRepository;
import com.android.systemui.biometrics.data.repository.FingerprintPropertyRepositoryImpl;
import com.android.systemui.biometrics.domain.model.SideFpsSensorLocation;
import com.android.systemui.keyguard.data.repository.BiometricSettingsRepositoryImpl;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor$isFinishedIn$$inlined$map$2;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.log.SideFpsLogger;
import com.android.wm.shell.R;
import java.util.Optional;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;
import kotlinx.coroutines.flow.FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SideFpsSensorInteractor {
    public final FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1 authenticationDuration;
    public final Context context;
    public final SideFpsSensorInteractor$special$$inlined$map$2 isAvailable;
    public final Flow isProlongedTouchRequiredForAuthentication;
    public final FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1 isSettingEnabled;
    public final SideFpsLogger logger;
    public final FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1 sensorLocation;
    public final FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1 sensorLocationForCurrentDisplay;

    public SideFpsSensorInteractor(Context context, FingerprintPropertyRepository fingerprintPropertyRepository, WindowManager windowManager, DisplayStateInteractor displayStateInteractor, Optional optional, BiometricSettingsRepositoryImpl biometricSettingsRepositoryImpl, KeyguardTransitionInteractor keyguardTransitionInteractor, SideFpsLogger sideFpsLogger) {
        int i = 1;
        int i2 = 0;
        this.context = context;
        this.logger = sideFpsLogger;
        boolean z = context.getResources().getBoolean(R.bool.config_restToUnlockSupported);
        DisplayStateInteractorImpl displayStateInteractorImpl = (DisplayStateInteractorImpl) displayStateInteractor;
        FingerprintPropertyRepositoryImpl fingerprintPropertyRepositoryImpl = (FingerprintPropertyRepositoryImpl) fingerprintPropertyRepository;
        FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1 flowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1 = new FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1(new SideFpsSensorInteractor$special$$inlined$map$1(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(displayStateInteractorImpl.displayChanges, fingerprintPropertyRepositoryImpl.sensorLocations, SideFpsSensorInteractor$sensorLocationForCurrentDisplay$2.INSTANCE), this, i2));
        SideFpsSensorInteractor$special$$inlined$map$2 sideFpsSensorInteractor$special$$inlined$map$2 = new SideFpsSensorInteractor$special$$inlined$map$2(fingerprintPropertyRepositoryImpl.sensorType, i2);
        this.isAvailable = sideFpsSensorInteractor$special$$inlined$map$2;
        new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(new SideFpsSensorInteractor$special$$inlined$map$2(new SideFpsSensorInteractor$special$$inlined$map$1(FlowKt.distinctUntilChanged(new KeyguardTransitionInteractor$isFinishedIn$$inlined$map$2(keyguardTransitionInteractor.finishedKeyguardState, new Function1() { // from class: com.android.systemui.biometrics.domain.interactor.SideFpsSensorInteractor$authenticationDuration$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                KeyguardState keyguardState = (KeyguardState) obj;
                return Boolean.valueOf(keyguardState == KeyguardState.OFF || keyguardState == KeyguardState.DOZING);
            }
        }, i)), this, i), i), new SideFpsSensorInteractor$authenticationDuration$4(this, null), i2);
        this.isProlongedTouchRequiredForAuthentication = !z ? new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(Boolean.FALSE) : new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(sideFpsSensorInteractor$special$$inlined$map$2, new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(FlowKt.transformLatest(biometricSettingsRepositoryImpl.isFingerprintEnrolledAndEnabled, new SideFpsSensorInteractor$special$$inlined$flatMapLatest$1(null, optional)), new SideFpsSensorInteractor$isSettingEnabled$2(this, null), i2), new SideFpsSensorInteractor$isProlongedTouchRequiredForAuthentication$1(3, null));
        this.sensorLocation = new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(FlowKt.distinctUntilChanged(new Function2() { // from class: com.android.systemui.biometrics.domain.interactor.SideFpsSensorInteractor$sensorLocation$4
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                SideFpsSensorLocation sideFpsSensorLocation = (SideFpsSensorLocation) obj;
                SideFpsSensorLocation sideFpsSensorLocation2 = (SideFpsSensorLocation) obj2;
                return Boolean.valueOf(sideFpsSensorLocation.left == sideFpsSensorLocation2.left && sideFpsSensorLocation.top == sideFpsSensorLocation2.top && sideFpsSensorLocation.length == sideFpsSensorLocation2.length && sideFpsSensorLocation.isSensorVerticalInDefaultOrientation == sideFpsSensorLocation2.isSensorVerticalInDefaultOrientation);
            }
        }, new SideFpsSensorInteractor$special$$inlined$map$1(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(displayStateInteractorImpl.currentRotation, flowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1, SideFpsSensorInteractor$sensorLocation$2.INSTANCE), windowManager, 2)), new SideFpsSensorInteractor$sensorLocation$5(this, null), i2);
    }
}

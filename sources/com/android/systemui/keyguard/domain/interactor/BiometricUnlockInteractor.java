package com.android.systemui.keyguard.domain.interactor;

import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import com.android.systemui.keyguard.data.repository.KeyguardRepositoryImpl;
import com.android.systemui.keyguard.shared.model.BiometricUnlockMode;
import com.android.systemui.keyguard.shared.model.BiometricUnlockModel;
import com.android.systemui.keyguard.shared.model.BiometricUnlockSource;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BiometricUnlockInteractor {
    public final KeyguardRepositoryImpl keyguardRepository;
    public final ReadonlyStateFlow unlockState;

    public BiometricUnlockInteractor(KeyguardRepositoryImpl keyguardRepositoryImpl) {
        this.keyguardRepository = keyguardRepositoryImpl;
        this.unlockState = keyguardRepositoryImpl.biometricUnlockState;
    }

    public final void setBiometricUnlockState(int i, BiometricUnlockSource biometricUnlockSource) {
        BiometricUnlockMode biometricUnlockMode;
        switch (i) {
            case 0:
                biometricUnlockMode = BiometricUnlockMode.NONE;
                break;
            case 1:
                biometricUnlockMode = BiometricUnlockMode.WAKE_AND_UNLOCK;
                break;
            case 2:
                biometricUnlockMode = BiometricUnlockMode.WAKE_AND_UNLOCK_PULSING;
                break;
            case 3:
                biometricUnlockMode = BiometricUnlockMode.SHOW_BOUNCER;
                break;
            case 4:
                biometricUnlockMode = BiometricUnlockMode.ONLY_WAKE;
                break;
            case 5:
                biometricUnlockMode = BiometricUnlockMode.UNLOCK_COLLAPSING;
                break;
            case 6:
                biometricUnlockMode = BiometricUnlockMode.WAKE_AND_UNLOCK_FROM_DREAM;
                break;
            case 7:
                biometricUnlockMode = BiometricUnlockMode.DISMISS_BOUNCER;
                break;
            default:
                throw new IllegalArgumentException(AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "Invalid BiometricUnlockModel value: "));
        }
        KeyguardRepositoryImpl keyguardRepositoryImpl = this.keyguardRepository;
        keyguardRepositoryImpl.getClass();
        BiometricUnlockModel biometricUnlockModel = new BiometricUnlockModel(biometricUnlockMode, biometricUnlockSource);
        StateFlowImpl stateFlowImpl = keyguardRepositoryImpl._biometricUnlockState;
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, biometricUnlockModel);
    }
}

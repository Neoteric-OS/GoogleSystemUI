package com.android.systemui.statusbar.phone;

import com.android.systemui.keyguard.shared.model.TransitionStep;
import com.android.systemui.statusbar.phone.BiometricUnlockController;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BiometricUnlockController$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ BiometricUnlockController f$0;

    public /* synthetic */ BiometricUnlockController$$ExternalSyntheticLambda0(BiometricUnlockController biometricUnlockController, int i) {
        this.$r8$classId = i;
        this.f$0 = biometricUnlockController;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        int i = this.$r8$classId;
        BiometricUnlockController biometricUnlockController = this.f$0;
        switch (i) {
            case 0:
                BiometricUnlockController.UI_EVENT_LOGGER.log((BiometricUnlockController.BiometricUiEvent) obj, biometricUnlockController.mSessionTracker.getSessionId(1));
                break;
            case 1:
                BiometricUnlockController.UI_EVENT_LOGGER.log((BiometricUnlockController.BiometricUiEvent) obj, biometricUnlockController.mSessionTracker.getSessionId(1));
                break;
            case 2:
                biometricUnlockController.consumeFromGoneTransitions((TransitionStep) obj);
                break;
            default:
                BiometricUnlockController.UI_EVENT_LOGGER.log((BiometricUnlockController.BiometricUiEvent) obj, biometricUnlockController.mSessionTracker.getSessionId(1));
                break;
        }
    }
}

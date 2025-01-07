package com.android.systemui.doze;

import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.doze.DozeMachine;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DozeAuthRemover implements DozeMachine.Part {
    public final KeyguardUpdateMonitor mKeyguardUpdateMonitor;
    public final SelectedUserInteractor mSelectedUserInteractor;

    public DozeAuthRemover(KeyguardUpdateMonitor keyguardUpdateMonitor, SelectedUserInteractor selectedUserInteractor) {
        this.mKeyguardUpdateMonitor = keyguardUpdateMonitor;
        this.mSelectedUserInteractor = selectedUserInteractor;
    }

    @Override // com.android.systemui.doze.DozeMachine.Part
    public final void transitionTo(DozeMachine.State state, DozeMachine.State state2) {
        if (state2 == DozeMachine.State.DOZE || state2 == DozeMachine.State.DOZE_AOD) {
            int selectedUserId = this.mSelectedUserInteractor.getSelectedUserId();
            KeyguardUpdateMonitor keyguardUpdateMonitor = this.mKeyguardUpdateMonitor;
            if (keyguardUpdateMonitor.getUserUnlockedWithBiometric(selectedUserId)) {
                keyguardUpdateMonitor.clearFingerprintRecognized(-10000);
            }
        }
    }
}

package com.android.systemui.doze;

import android.util.Log;
import com.android.systemui.doze.DozeMachine;
import com.android.systemui.keyguard.data.repository.KeyguardRepositoryImpl;
import com.android.systemui.keyguard.data.repository.KeyguardRepositoryImpl$dozeTransitionModel$1$callback$1;
import com.android.systemui.keyguard.shared.model.DozeTransitionModel;
import com.android.systemui.statusbar.policy.CallbackController;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.ProducerCoroutine;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DozeTransitionListener implements DozeMachine.Part, CallbackController {
    public final Set callbacks = new LinkedHashSet();
    public DozeMachine.State newState;
    public DozeMachine.State oldState;

    public DozeTransitionListener() {
        DozeMachine.State state = DozeMachine.State.UNINITIALIZED;
        this.oldState = state;
        this.newState = state;
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void addCallback(Object obj) {
        this.callbacks.add((KeyguardRepositoryImpl$dozeTransitionModel$1$callback$1) obj);
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void removeCallback(Object obj) {
        this.callbacks.remove((KeyguardRepositoryImpl$dozeTransitionModel$1$callback$1) obj);
    }

    @Override // com.android.systemui.doze.DozeMachine.Part
    public final void transitionTo(DozeMachine.State state, DozeMachine.State state2) {
        this.oldState = state;
        this.newState = state2;
        for (KeyguardRepositoryImpl$dozeTransitionModel$1$callback$1 keyguardRepositoryImpl$dozeTransitionModel$1$callback$1 : this.callbacks) {
            KeyguardRepositoryImpl keyguardRepositoryImpl = keyguardRepositoryImpl$dozeTransitionModel$1$callback$1.this$0;
            Object mo1790trySendJP2dKIU = ((ProducerCoroutine) keyguardRepositoryImpl$dozeTransitionModel$1$callback$1.$$this$conflatedCallbackFlow)._channel.mo1790trySendJP2dKIU(new DozeTransitionModel(KeyguardRepositoryImpl.access$dozeMachineStateToModel(keyguardRepositoryImpl, state), KeyguardRepositoryImpl.access$dozeMachineStateToModel(keyguardRepositoryImpl, state2)));
            if (mo1790trySendJP2dKIU instanceof ChannelResult.Failed) {
                Log.e("KeyguardRepositoryImpl", "Failed to send doze transition model - downstream canceled or failed.", ChannelResult.m1791exceptionOrNullimpl(mo1790trySendJP2dKIU));
            }
        }
    }
}

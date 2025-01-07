package com.android.systemui.dreams;

import android.util.Log;
import com.android.systemui.keyguard.data.repository.KeyguardRepositoryImpl$isDreamingWithOverlay$1$callback$1;
import com.android.systemui.statusbar.policy.CallbackController;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.ProducerCoroutine;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DreamOverlayCallbackController implements CallbackController {
    public final Set callbacks = new LinkedHashSet();
    public boolean isDreaming;

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void addCallback(Object obj) {
        this.callbacks.add((KeyguardRepositoryImpl$isDreamingWithOverlay$1$callback$1) obj);
    }

    public final void onWakeUp() {
        if (this.isDreaming) {
            this.isDreaming = false;
            for (KeyguardRepositoryImpl$isDreamingWithOverlay$1$callback$1 keyguardRepositoryImpl$isDreamingWithOverlay$1$callback$1 : this.callbacks) {
                Object mo1790trySendJP2dKIU = ((ProducerCoroutine) keyguardRepositoryImpl$isDreamingWithOverlay$1$callback$1.$$this$conflatedCallbackFlow)._channel.mo1790trySendJP2dKIU(Boolean.FALSE);
                if (mo1790trySendJP2dKIU instanceof ChannelResult.Failed) {
                    Log.e("KeyguardRepositoryImpl", "Failed to send updated isDreamingWithOverlay - downstream canceled or failed.", ChannelResult.m1791exceptionOrNullimpl(mo1790trySendJP2dKIU));
                }
            }
        }
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void removeCallback(Object obj) {
        this.callbacks.remove((KeyguardRepositoryImpl$isDreamingWithOverlay$1$callback$1) obj);
    }
}

package com.android.wm.shell.pip2.phone;

import android.app.PictureInPictureUiState;
import android.os.Bundle;
import com.android.wm.shell.pip2.phone.PipTransitionState;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PipUiStateChangeController implements PipTransitionState.PipTransitionStateChangedListener {
    public Consumer mPictureInPictureUiStateConsumer;
    public final PipTransitionState mPipTransitionState;

    public PipUiStateChangeController(PipTransitionState pipTransitionState) {
        this.mPipTransitionState = pipTransitionState;
        pipTransitionState.addPipTransitionStateChangedListener(this);
        this.mPictureInPictureUiStateConsumer = new PipUiStateChangeController$$ExternalSyntheticLambda0();
    }

    public final void onIsTransitioningToPipUiStateChange(boolean z) {
        Consumer consumer = this.mPictureInPictureUiStateConsumer;
        if (consumer != null) {
            consumer.accept(new PictureInPictureUiState.Builder().setTransitioningToPip(z).build());
        }
    }

    @Override // com.android.wm.shell.pip2.phone.PipTransitionState.PipTransitionStateChangedListener
    public final void onPipTransitionStateChanged(int i, int i2, Bundle bundle) {
        if (i2 == 1) {
            onIsTransitioningToPipUiStateChange(true);
            return;
        }
        if (i2 == 2 && !this.mPipTransitionState.mInSwipePipToHomeTransition) {
            onIsTransitioningToPipUiStateChange(true);
        } else if (i2 == 3) {
            onIsTransitioningToPipUiStateChange(false);
        }
    }

    public void setPictureInPictureUiStateConsumer(Consumer consumer) {
        this.mPictureInPictureUiStateConsumer = consumer;
    }
}

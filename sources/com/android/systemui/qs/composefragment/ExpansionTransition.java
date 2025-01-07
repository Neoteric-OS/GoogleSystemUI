package com.android.systemui.qs.composefragment;

import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.SnapshotMutableFloatStateImpl;
import com.android.compose.animation.scene.SceneKey;
import com.android.compose.animation.scene.content.state.TransitionState;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlinx.coroutines.CompletableDeferredImpl;
import kotlinx.coroutines.CompletableDeferredKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ExpansionTransition extends TransitionState.Transition.ChangeScene {
    public final CompletableDeferredImpl finishCompletable;
    public final MutableFloatState progress$delegate;

    public ExpansionTransition(float f) {
        super(SceneKeys.QuickQuickSettings, SceneKeys.QuickSettings, null);
        this.progress$delegate = PrimitiveSnapshotStateKt.mutableFloatStateOf(f);
        this.finishCompletable = CompletableDeferredKt.CompletableDeferred$default();
    }

    @Override // com.android.compose.animation.scene.content.state.TransitionState.Transition
    public final void freezeAndAnimateToCurrentState() {
        this.finishCompletable.makeCompleting$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(Unit.INSTANCE);
    }

    @Override // com.android.compose.animation.scene.content.state.TransitionState
    public final SceneKey getCurrentScene() {
        SceneKey sceneKey = SceneKeys.QuickQuickSettings;
        return SceneKeys.QuickQuickSettings;
    }

    @Override // com.android.compose.animation.scene.content.state.TransitionState.Transition
    public final float getProgress() {
        return ((SnapshotMutableFloatStateImpl) this.progress$delegate).getFloatValue();
    }

    @Override // com.android.compose.animation.scene.content.state.TransitionState.Transition
    public final float getProgressVelocity() {
        return 0.0f;
    }

    @Override // com.android.compose.animation.scene.content.state.TransitionState.Transition
    public final boolean isInitiatedByUserInput() {
        return true;
    }

    @Override // com.android.compose.animation.scene.content.state.TransitionState.Transition
    public final boolean isUserInputOngoing() {
        return true;
    }

    @Override // com.android.compose.animation.scene.content.state.TransitionState.Transition
    public final Object run(Continuation continuation) {
        Object awaitInternal = this.finishCompletable.awaitInternal(continuation);
        return awaitInternal == CoroutineSingletons.COROUTINE_SUSPENDED ? awaitInternal : Unit.INSTANCE;
    }
}

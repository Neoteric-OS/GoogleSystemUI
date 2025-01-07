package com.android.compose.animation.scene;

import androidx.compose.foundation.gestures.Orientation;
import com.android.compose.animation.scene.content.state.TransitionState;
import java.util.Set;
import kotlin.Unit;
import kotlin.collections.builders.SetBuilder;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ReplaceOverlaySwipeTransition extends TransitionState.Transition.OverlayTransition implements TransitionState.HasOverscrollProperties {
    public final OverlayKey fromOverlay;
    public final boolean isInitiatedByUserInput;
    public final TransitionKey key;
    public final MutableSceneTransitionLayoutStateImpl layoutState;
    public final SwipeAnimation swipeAnimation;
    public final OverlayKey toOverlay;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public ReplaceOverlaySwipeTransition(com.android.compose.animation.scene.MutableSceneTransitionLayoutStateImpl r3, com.android.compose.animation.scene.SwipeAnimation r4, com.android.compose.animation.scene.TransitionKey r5, com.android.compose.animation.scene.ReplaceOverlaySwipeTransition r6) {
        /*
            r2 = this;
            com.android.compose.animation.scene.ContentKey r0 = r4.fromContent
            com.android.compose.animation.scene.OverlayKey r0 = (com.android.compose.animation.scene.OverlayKey) r0
            com.android.compose.animation.scene.ContentKey r1 = r4.toContent
            com.android.compose.animation.scene.OverlayKey r1 = (com.android.compose.animation.scene.OverlayKey) r1
            r2.<init>(r0, r1, r6)
            r2.fromOverlay = r0
            r2.toOverlay = r1
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r6 != 0) goto L21
            r2.layoutState = r3
            r2.swipeAnimation = r4
            r2.key = r5
            r4.contentTransition = r2
            r3 = 1
            r2.isInitiatedByUserInput = r3
            return
        L21:
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
            java.lang.String r3 = "Check failed."
            r2.<init>(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.compose.animation.scene.ReplaceOverlaySwipeTransition.<init>(com.android.compose.animation.scene.MutableSceneTransitionLayoutStateImpl, com.android.compose.animation.scene.SwipeAnimation, com.android.compose.animation.scene.TransitionKey, com.android.compose.animation.scene.ReplaceOverlaySwipeTransition):void");
    }

    @Override // com.android.compose.animation.scene.content.state.TransitionState.Transition.OverlayTransition
    public final Set computeCurrentOverlays() {
        OverlayKey effectivelyShownOverlay = getEffectivelyShownOverlay();
        OverlayKey overlayKey = this.fromOverlay;
        boolean areEqual = Intrinsics.areEqual(effectivelyShownOverlay, overlayKey);
        OverlayKey overlayKey2 = this.toOverlay;
        if (areEqual) {
            SetBuilder setBuilder = new SetBuilder();
            Set set = this.currentOverlaysWhenTransitionStarted;
            setBuilder.addAll(set != null ? set : null);
            setBuilder.remove(overlayKey2);
            setBuilder.add(overlayKey);
            return setBuilder.build();
        }
        if (Intrinsics.areEqual(effectivelyShownOverlay, overlayKey2)) {
            SetBuilder setBuilder2 = new SetBuilder();
            Set set2 = this.currentOverlaysWhenTransitionStarted;
            setBuilder2.addAll(set2 != null ? set2 : null);
            setBuilder2.remove(overlayKey);
            setBuilder2.add(overlayKey2);
            return setBuilder2.build();
        }
        throw new IllegalStateException(("effectivelyShownOverlay=" + getEffectivelyShownOverlay() + ", should be equal to fromOverlay=" + overlayKey + " or toOverlay=" + overlayKey2).toString());
    }

    @Override // com.android.compose.animation.scene.content.state.TransitionState.Transition
    public final void freezeAndAnimateToCurrentState() {
        this.swipeAnimation.freezeAndAnimateToCurrentState();
    }

    @Override // com.android.compose.animation.scene.content.state.TransitionState.HasOverscrollProperties
    public final ContentKey getBouncingContent() {
        return this.swipeAnimation.bouncingContent;
    }

    public final OverlayKey getEffectivelyShownOverlay() {
        return (OverlayKey) this.swipeAnimation.getCurrentContent();
    }

    @Override // com.android.compose.animation.scene.content.state.TransitionState.Transition
    public final TransitionKey getKey() {
        return this.key;
    }

    @Override // com.android.compose.animation.scene.content.state.TransitionState.HasOverscrollProperties
    public final Orientation getOrientation() {
        return this.swipeAnimation.orientation;
    }

    @Override // com.android.compose.animation.scene.content.state.TransitionState.Transition
    public final float getPreviewProgress$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout() {
        return this.swipeAnimation.getPreviewProgress();
    }

    @Override // com.android.compose.animation.scene.content.state.TransitionState.Transition
    public final float getProgress() {
        return this.swipeAnimation.getProgress();
    }

    @Override // com.android.compose.animation.scene.content.state.TransitionState.Transition
    public final float getProgressVelocity() {
        return this.swipeAnimation.getProgressVelocity();
    }

    @Override // com.android.compose.animation.scene.content.state.TransitionState.Transition
    public final boolean isInPreviewStage$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout() {
        return this.swipeAnimation.isInPreviewStage();
    }

    @Override // com.android.compose.animation.scene.content.state.TransitionState.Transition
    public final boolean isInitiatedByUserInput() {
        return this.isInitiatedByUserInput;
    }

    @Override // com.android.compose.animation.scene.content.state.TransitionState.HasOverscrollProperties
    public final boolean isUpOrLeft() {
        return this.swipeAnimation.isUpOrLeft;
    }

    @Override // com.android.compose.animation.scene.content.state.TransitionState.Transition
    public final boolean isUserInputOngoing() {
        return this.swipeAnimation.getOffsetAnimation() == null;
    }

    @Override // com.android.compose.animation.scene.content.state.TransitionState.Transition
    public final Object run(Continuation continuation) {
        Object run = this.swipeAnimation.run(continuation);
        return run == CoroutineSingletons.COROUTINE_SUSPENDED ? run : Unit.INSTANCE;
    }
}

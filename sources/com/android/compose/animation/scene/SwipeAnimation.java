package com.android.compose.animation.scene;

import androidx.compose.animation.core.Animatable;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.SnapshotMutableFloatStateImpl;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.SnapshotStateKt;
import com.android.compose.animation.scene.content.state.TransitionState;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableDeferredImpl;
import kotlinx.coroutines.CompletableDeferredKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SwipeAnimation implements TransitionState.HasOverscrollProperties {
    public final MutableState _currentContent$delegate;
    public ContentKey bouncingContent;
    public TransitionState.Transition contentTransition;
    public final Function1 distance;
    public final MutableFloatState dragOffset$delegate;
    public final ContentKey fromContent;
    public final boolean isUpOrLeft;
    public final MutableSceneTransitionLayoutStateImpl layoutState;
    public final MutableState offsetAnimation$delegate;
    public final CompletableDeferredImpl offsetAnimationRunnable;
    public final Orientation orientation;
    public final boolean requiresFullDistanceSwipe;
    public final ContentKey toContent;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class SnapException extends Exception {
    }

    public SwipeAnimation(MutableSceneTransitionLayoutStateImpl mutableSceneTransitionLayoutStateImpl, ContentKey contentKey, ContentKey contentKey2, Orientation orientation, boolean z, boolean z2, Function1 function1, ContentKey contentKey3, float f) {
        this.layoutState = mutableSceneTransitionLayoutStateImpl;
        this.fromContent = contentKey;
        this.toContent = contentKey2;
        this.orientation = orientation;
        this.isUpOrLeft = z;
        this.requiresFullDistanceSwipe = z2;
        this.distance = function1;
        this._currentContent$delegate = SnapshotStateKt.mutableStateOf$default(contentKey3);
        this.dragOffset$delegate = PrimitiveSnapshotStateKt.mutableFloatStateOf(f);
        this.offsetAnimation$delegate = SnapshotStateKt.mutableStateOf$default(null);
        this.offsetAnimationRunnable = CompletableDeferredKt.CompletableDeferred$default();
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x016f  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x00c3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final float animateOffset(float r17, com.android.compose.animation.scene.ContentKey r18, androidx.compose.animation.core.AnimationSpec r19) {
        /*
            Method dump skipped, instructions count: 391
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.compose.animation.scene.SwipeAnimation.animateOffset(float, com.android.compose.animation.scene.ContentKey, androidx.compose.animation.core.AnimationSpec):float");
    }

    public final float distance() {
        return ((Number) this.distance.invoke(this)).floatValue();
    }

    public final void freezeAndAnimateToCurrentState() {
        if (isAnimatingOffset()) {
            return;
        }
        animateOffset(0.0f, getCurrentContent(), null);
    }

    @Override // com.android.compose.animation.scene.content.state.TransitionState.HasOverscrollProperties
    public final ContentKey getBouncingContent() {
        return this.bouncingContent;
    }

    public final ContentKey getCurrentContent() {
        return (ContentKey) ((SnapshotMutableStateImpl) this._currentContent$delegate).getValue();
    }

    public final float getDragOffset() {
        return ((SnapshotMutableFloatStateImpl) this.dragOffset$delegate).getFloatValue();
    }

    public final Animatable getOffsetAnimation() {
        return (Animatable) ((SnapshotMutableStateImpl) this.offsetAnimation$delegate).getValue();
    }

    @Override // com.android.compose.animation.scene.content.state.TransitionState.HasOverscrollProperties
    public final Orientation getOrientation() {
        return this.orientation;
    }

    public final float getPreviewProgress() {
        float dragOffset;
        if (isInPreviewStage()) {
            Animatable offsetAnimation = getOffsetAnimation();
            dragOffset = offsetAnimation != null ? ((Number) offsetAnimation.internalState.getValue()).floatValue() : getDragOffset();
        } else {
            dragOffset = getDragOffset();
        }
        float distance = distance();
        if (distance == 0.0f) {
            return 0.0f;
        }
        return dragOffset / distance;
    }

    public final float getProgress() {
        Animatable offsetAnimation = getOffsetAnimation();
        float floatValue = isInPreviewStage() ? 0.0f : offsetAnimation != null ? ((Number) ((SnapshotMutableStateImpl) offsetAnimation.internalState.value$delegate).getValue()).floatValue() : getDragOffset();
        float distance = distance();
        if (distance == 0.0f) {
            return 0.0f;
        }
        return floatValue / distance;
    }

    public final float getProgressVelocity() {
        Animatable offsetAnimation = getOffsetAnimation();
        if (offsetAnimation == null) {
            return 0.0f;
        }
        float distance = distance();
        if (distance == 0.0f) {
            return 0.0f;
        }
        return ((Number) offsetAnimation.getVelocity()).floatValue() / Math.abs(distance);
    }

    public final boolean isAnimatingOffset() {
        return getOffsetAnimation() != null;
    }

    public final boolean isInPreviewStage() {
        TransitionState.Transition transition = this.contentTransition;
        if (transition == null) {
            transition = null;
        }
        return transition.previewTransformationSpec != null && Intrinsics.areEqual(getCurrentContent(), this.fromContent);
    }

    @Override // com.android.compose.animation.scene.content.state.TransitionState.HasOverscrollProperties
    public final boolean isUpOrLeft() {
        return this.isUpOrLeft;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x004c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object run(kotlin.coroutines.Continuation r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof com.android.compose.animation.scene.SwipeAnimation$run$1
            if (r0 == 0) goto L13
            r0 = r7
            com.android.compose.animation.scene.SwipeAnimation$run$1 r0 = (com.android.compose.animation.scene.SwipeAnimation$run$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L1a
        L13:
            com.android.compose.animation.scene.SwipeAnimation$run$1 r0 = new com.android.compose.animation.scene.SwipeAnimation$run$1
            kotlin.coroutines.jvm.internal.ContinuationImpl r7 = (kotlin.coroutines.jvm.internal.ContinuationImpl) r7
            r0.<init>(r6, r7)
        L1a:
            java.lang.Object r7 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            kotlin.Unit r3 = kotlin.Unit.INSTANCE
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L3a
            if (r2 == r5) goto L36
            if (r2 != r4) goto L2e
            kotlin.ResultKt.throwOnFailure(r7)
            goto L56
        L2e:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L36:
            kotlin.ResultKt.throwOnFailure(r7)
            goto L48
        L3a:
            kotlin.ResultKt.throwOnFailure(r7)
            r0.label = r5
            kotlinx.coroutines.CompletableDeferredImpl r6 = r6.offsetAnimationRunnable
            java.lang.Object r7 = r6.awaitInternal(r0)
            if (r7 != r1) goto L48
            return r1
        L48:
            kotlin.jvm.functions.Function1 r7 = (kotlin.jvm.functions.Function1) r7
            if (r7 != 0) goto L4d
            return r3
        L4d:
            r0.label = r4
            java.lang.Object r6 = r7.invoke(r0)
            if (r6 != r1) goto L56
            return r1
        L56:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.compose.animation.scene.SwipeAnimation.run(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public SwipeAnimation(com.android.compose.animation.scene.SwipeAnimation r11) {
        /*
            r10 = this;
            com.android.compose.animation.scene.MutableSceneTransitionLayoutStateImpl r1 = r11.layoutState
            com.android.compose.animation.scene.ContentKey r2 = r11.fromContent
            com.android.compose.animation.scene.ContentKey r3 = r11.toContent
            androidx.compose.foundation.gestures.Orientation r4 = r11.orientation
            boolean r5 = r11.isUpOrLeft
            boolean r6 = r11.requiresFullDistanceSwipe
            kotlin.jvm.functions.Function1 r7 = r11.distance
            com.android.compose.animation.scene.ContentKey r8 = r11.getCurrentContent()
            androidx.compose.animation.core.Animatable r0 = r11.getOffsetAnimation()
            if (r0 == 0) goto L26
            androidx.compose.animation.core.AnimationState r11 = r0.internalState
            java.lang.Object r11 = r11.getValue()
            java.lang.Number r11 = (java.lang.Number) r11
            float r11 = r11.floatValue()
        L24:
            r9 = r11
            goto L2b
        L26:
            float r11 = r11.getDragOffset()
            goto L24
        L2b:
            r0 = r10
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.compose.animation.scene.SwipeAnimation.<init>(com.android.compose.animation.scene.SwipeAnimation):void");
    }
}

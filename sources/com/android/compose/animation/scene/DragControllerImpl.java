package com.android.compose.animation.scene;

import com.android.compose.animation.scene.content.state.TransitionState;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DragControllerImpl implements DragController {
    public final DraggableHandlerImpl draggableHandler;
    public final MutableSceneTransitionLayoutStateImpl layoutState;
    public SwipeAnimation swipeAnimation;
    public final Swipes swipes;

    public DragControllerImpl(DraggableHandlerImpl draggableHandlerImpl, Swipes swipes, SwipeAnimation swipeAnimation) {
        this.draggableHandler = draggableHandlerImpl;
        this.swipes = swipes;
        this.swipeAnimation = swipeAnimation;
        this.layoutState = draggableHandlerImpl.layoutImpl.state;
        if (isDrivingTransition()) {
            throw new IllegalStateException("Multiple controllers with the same SwipeTransition");
        }
    }

    public final boolean isDrivingTransition() {
        TransitionState transitionState = this.layoutState.getTransitionState();
        TransitionState.Transition transition = this.swipeAnimation.contentTransition;
        if (transition == null) {
            transition = null;
        }
        return Intrinsics.areEqual(transitionState, transition);
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0051, code lost:
    
        if (kotlin.jvm.internal.Intrinsics.areEqual(r7 != null ? r7.toContent$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout(r13.getCurrentScene()) : null, r8) != false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0070, code lost:
    
        r7 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x010a, code lost:
    
        if (kotlin.jvm.internal.Intrinsics.areEqual(r1, (r3 != null ? r3 : null).getKey()) == false) goto L93;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x00a4, code lost:
    
        if (r6 > r4) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x006e, code lost:
    
        if (kotlin.jvm.internal.Intrinsics.areEqual(r7 != null ? r7.toContent$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout(r13.getCurrentScene()) : null, r8) != false) goto L33;
     */
    @Override // com.android.compose.animation.scene.DragController
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final float onDrag(float r17) {
        /*
            Method dump skipped, instructions count: 306
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.compose.animation.scene.DragControllerImpl.onDrag(float):float");
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0039, code lost:
    
        if ((r12 / r4) >= 1.0f) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x009c, code lost:
    
        r3 = r7;
     */
    @Override // com.android.compose.animation.scene.DragController
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final float onStop(float r11, boolean r12) {
        /*
            r10 = this;
            com.android.compose.animation.scene.SwipeAnimation r0 = r10.swipeAnimation
            boolean r1 = r10.isDrivingTransition()
            r2 = 0
            if (r1 == 0) goto Lba
            boolean r1 = r0.isAnimatingOffset()
            if (r1 == 0) goto L11
            goto Lba
        L11:
            r1 = 0
            com.android.compose.animation.scene.ContentKey r3 = r0.fromContent
            if (r12 == 0) goto La3
            float r12 = r0.getDragOffset()
            float r4 = r0.distance()
            int r5 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r5 != 0) goto L24
            goto L9d
        L24:
            com.android.compose.animation.scene.ContentKey r6 = r0.getCurrentContent()
            com.android.compose.animation.scene.ContentKey r7 = r0.toContent
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual(r6, r7)
            boolean r8 = r0.requiresFullDistanceSwipe
            if (r8 == 0) goto L3c
            if (r6 != 0) goto L3c
            float r12 = r12 / r4
            r10 = 1065353216(0x3f800000, float:1.0)
            int r10 = (r12 > r10 ? 1 : (r12 == r10 ? 0 : -1))
            if (r10 < 0) goto L9d
            goto L9c
        L3c:
            com.android.compose.animation.scene.DraggableHandlerImpl r10 = r10.draggableHandler
            com.android.compose.animation.scene.SceneTransitionLayoutImpl r8 = r10.layoutImpl
            androidx.compose.ui.unit.Density r8 = r8.density
            r9 = 125(0x7d, float:1.75E-43)
            float r9 = (float) r9
            float r8 = r8.mo51toPx0680j_4(r9)
            com.android.compose.animation.scene.SceneTransitionLayoutImpl r10 = r10.layoutImpl
            androidx.compose.ui.unit.Density r10 = r10.density
            r9 = 56
            float r9 = (float) r9
            float r10 = r10.mo51toPx0680j_4(r9)
            if (r5 >= 0) goto L7a
            int r2 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1))
            if (r2 > 0) goto L9d
            int r2 = (r11 > r8 ? 1 : (r11 == r8 ? 0 : -1))
            if (r2 < 0) goto L5f
            goto L9d
        L5f:
            float r2 = -r8
            int r2 = (r11 > r2 ? 1 : (r11 == r2 ? 0 : -1))
            if (r2 <= 0) goto L9c
            float r10 = -r10
            int r10 = (r12 > r10 ? 1 : (r12 == r10 ? 0 : -1))
            if (r10 > 0) goto L6b
            if (r6 == 0) goto L9c
        L6b:
            float r10 = r12 - r4
            float r10 = java.lang.Math.abs(r10)
            float r12 = java.lang.Math.abs(r12)
            int r10 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r10 >= 0) goto L9d
            goto L9c
        L7a:
            int r2 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1))
            if (r2 < 0) goto L9d
            float r2 = -r8
            int r2 = (r11 > r2 ? 1 : (r11 == r2 ? 0 : -1))
            if (r2 > 0) goto L84
            goto L9d
        L84:
            int r2 = (r11 > r8 ? 1 : (r11 == r8 ? 0 : -1))
            if (r2 >= 0) goto L9c
            int r10 = (r12 > r10 ? 1 : (r12 == r10 ? 0 : -1))
            if (r10 < 0) goto L8e
            if (r6 == 0) goto L9c
        L8e:
            float r10 = r12 - r4
            float r10 = java.lang.Math.abs(r10)
            float r12 = java.lang.Math.abs(r12)
            int r10 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r10 >= 0) goto L9d
        L9c:
            r3 = r7
        L9d:
            float r10 = r0.animateOffset(r11, r3, r1)
        La1:
            r2 = r10
            goto Lba
        La3:
            com.android.compose.animation.scene.ContentKey r10 = r0.getCurrentContent()
            boolean r10 = kotlin.jvm.internal.Intrinsics.areEqual(r3, r10)
            if (r10 == 0) goto Lb2
            float r10 = r0.animateOffset(r11, r3, r1)
            goto La1
        Lb2:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "canChangeContent is false but currentContent != fromContent"
            r10.<init>(r11)
            throw r10
        Lba:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.compose.animation.scene.DragControllerImpl.onStop(float, boolean):float");
    }

    public final void updateTransition(SwipeAnimation swipeAnimation, boolean z) {
        if (z || isDrivingTransition()) {
            ContextScope contextScope = this.draggableHandler.layoutImpl.animationScope;
            TransitionState.Transition transition = swipeAnimation.contentTransition;
            if (transition == null) {
                transition = null;
            }
            this.layoutState.startTransitionImmediately(contextScope, transition, true);
        }
        this.swipeAnimation = swipeAnimation;
    }
}

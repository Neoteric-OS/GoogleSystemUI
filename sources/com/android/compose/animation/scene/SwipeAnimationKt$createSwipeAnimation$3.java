package com.android.compose.animation.scene;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.ui.unit.IntSize;
import com.android.compose.animation.scene.content.state.TransitionState;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$FloatRef;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final /* synthetic */ class SwipeAnimationKt$createSwipeAnimation$3 extends FunctionReferenceImpl implements Function1 {
    final /* synthetic */ boolean $isUpOrLeft;
    final /* synthetic */ Ref$FloatRef $lastDistance;
    final /* synthetic */ SceneTransitionLayoutImpl $layoutImpl;
    final /* synthetic */ Orientation $orientation;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SwipeAnimationKt$createSwipeAnimation$3(Ref$FloatRef ref$FloatRef, boolean z, SceneTransitionLayoutImpl sceneTransitionLayoutImpl, Orientation orientation) {
        super(1, Intrinsics.Kotlin.class, "distance", "createSwipeAnimation$distance(Lkotlin/jvm/internal/Ref$FloatRef;ZLcom/android/compose/animation/scene/SceneTransitionLayoutImpl;Landroidx/compose/foundation/gestures/Orientation;Lcom/android/compose/animation/scene/SwipeAnimation;)F", 0);
        this.$lastDistance = ref$FloatRef;
        this.$isUpOrLeft = z;
        this.$layoutImpl = sceneTransitionLayoutImpl;
        this.$orientation = orientation;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        long j;
        SwipeAnimation swipeAnimation = (SwipeAnimation) obj;
        Ref$FloatRef ref$FloatRef = this.$lastDistance;
        boolean z = this.$isUpOrLeft;
        SceneTransitionLayoutImpl sceneTransitionLayoutImpl = this.$layoutImpl;
        Orientation orientation = this.$orientation;
        float f = ref$FloatRef.element;
        if (f == 0.0f) {
            TransitionState.Transition transition = swipeAnimation.contentTransition;
            if (transition == null) {
                transition = null;
            }
            DefaultSwipeDistance defaultSwipeDistance = transition.transformationSpec.distance;
            if (defaultSwipeDistance == null) {
                defaultSwipeDistance = DefaultSwipeDistance.INSTANCE;
            }
            if (sceneTransitionLayoutImpl._userActionDistanceScope == null) {
                sceneTransitionLayoutImpl._userActionDistanceScope = new UserActionDistanceScopeImpl(sceneTransitionLayoutImpl);
            }
            long j2 = ((IntSize) ((SnapshotMutableStateImpl) sceneTransitionLayoutImpl.content$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout(swipeAnimation.fromContent).targetSize$delegate).getValue()).packedValue;
            defaultSwipeDistance.getClass();
            int ordinal = orientation.ordinal();
            if (ordinal == 0) {
                j = 4294967295L & j2;
            } else {
                if (ordinal != 1) {
                    throw new NoWhenBranchMatchedException();
                }
                j = j2 >> 32;
            }
            float f2 = (int) j;
            if (f2 <= 0.0f) {
                f = 0.0f;
            } else {
                if (z) {
                    f2 = -f2;
                }
                f = f2;
                ref$FloatRef.element = f;
            }
        }
        return Float.valueOf(f);
    }
}

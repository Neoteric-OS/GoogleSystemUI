package com.android.compose.animation.scene;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.unit.IntOffsetKt;
import com.android.compose.animation.scene.Swipe;
import com.android.compose.animation.scene.SwipeDirection;
import com.android.compose.animation.scene.SwipeSource;
import com.android.compose.animation.scene.content.Content;
import com.android.compose.animation.scene.content.state.TransitionState;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DraggableHandlerImpl {
    public DragControllerImpl dragController;
    public final SceneTransitionLayoutImpl layoutImpl;
    public final Object nestedScrollKey = new Object();
    public final Orientation orientation;

    public DraggableHandlerImpl(SceneTransitionLayoutImpl sceneTransitionLayoutImpl, Orientation orientation) {
        this.layoutImpl = sceneTransitionLayoutImpl;
        this.orientation = orientation;
    }

    /* renamed from: computeSwipes-KMgbckE, reason: not valid java name */
    public final Swipes m726computeSwipesKMgbckE(Offset offset, int i) {
        SwipeSource.Resolved resolved;
        SwipeDirection.Resolved resolved2;
        SwipeDirection.Resolved resolved3;
        Orientation orientation = this.orientation;
        if (offset != null) {
            SceneTransitionLayoutImpl sceneTransitionLayoutImpl = this.layoutImpl;
            resolved = sceneTransitionLayoutImpl.swipeSourceDetector.mo733sourceNDhlJko(sceneTransitionLayoutImpl.lastSize, IntOffsetKt.m679roundk4lQ0M(offset.packedValue), sceneTransitionLayoutImpl.density, orientation);
        } else {
            resolved = null;
        }
        int ordinal = orientation.ordinal();
        if (ordinal == 0) {
            resolved2 = SwipeDirection.Resolved.Up;
        } else {
            if (ordinal != 1) {
                throw new NoWhenBranchMatchedException();
            }
            resolved2 = SwipeDirection.Resolved.Left;
        }
        Swipe.Resolved resolved4 = new Swipe.Resolved(resolved2, i, resolved);
        int ordinal2 = orientation.ordinal();
        if (ordinal2 == 0) {
            resolved3 = SwipeDirection.Resolved.Down;
        } else {
            if (ordinal2 != 1) {
                throw new NoWhenBranchMatchedException();
            }
            resolved3 = SwipeDirection.Resolved.Right;
        }
        Swipe.Resolved resolved5 = new Swipe.Resolved(resolved3, i, resolved);
        return resolved == null ? new Swipes(null, null, resolved4, resolved5) : new Swipes(resolved4, resolved5, Swipe.Resolved.copy$default(resolved4), Swipe.Resolved.copy$default(resolved5));
    }

    public final SwipeAnimation createSwipeAnimation$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout(Swipes swipes, UserActionResult userActionResult) {
        boolean z;
        UserActionResult userActionResult2 = swipes.upOrLeftResult;
        UserActionResult userActionResult3 = swipes.downOrRightResult;
        if (userActionResult.equals(userActionResult2)) {
            z = true;
        } else {
            if (!userActionResult.equals(userActionResult3)) {
                throw new IllegalStateException(("Unknown result " + userActionResult + " (" + userActionResult2 + " " + userActionResult3 + ")").toString());
            }
            z = false;
        }
        return SwipeAnimationKt.createSwipeAnimation(this.layoutImpl, userActionResult, z, this.orientation, 0.0f);
    }

    /* renamed from: onDragStarted-MjzGXtM, reason: not valid java name */
    public final DragController m727onDragStartedMjzGXtM(Offset offset, float f, int i) {
        SwipeAnimation swipeAnimation;
        UserActionResult userActionResult = null;
        SceneTransitionLayoutImpl sceneTransitionLayoutImpl = this.layoutImpl;
        if (f != 0.0f) {
            Swipes m726computeSwipesKMgbckE = m726computeSwipesKMgbckE(offset, i);
            m726computeSwipesKMgbckE.updateSwipesResults(sceneTransitionLayoutImpl.contentForUserActions$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout());
            UserActionResult userActionResult2 = m726computeSwipesKMgbckE.upOrLeftResult;
            if ((userActionResult2 != null || m726computeSwipesKMgbckE.downOrRightResult != null) && ((f < 0.0f && userActionResult2 != null) || (userActionResult = m726computeSwipesKMgbckE.downOrRightResult) == null)) {
                userActionResult = userActionResult2;
            }
            if (userActionResult == null) {
                return NoOpDragController.INSTANCE;
            }
            SwipeAnimation createSwipeAnimation$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout = createSwipeAnimation$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout(m726computeSwipesKMgbckE, userActionResult);
            DragControllerImpl dragControllerImpl = new DragControllerImpl(this, m726computeSwipesKMgbckE, createSwipeAnimation$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout);
            dragControllerImpl.updateTransition(createSwipeAnimation$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout, true);
            this.dragController = dragControllerImpl;
            return dragControllerImpl;
        }
        DragControllerImpl dragControllerImpl2 = this.dragController;
        if (dragControllerImpl2 == null || !dragControllerImpl2.isDrivingTransition()) {
            throw new IllegalStateException(("onDragStarted(overSlop=0f) requires an active dragController, but was " + (dragControllerImpl2 != null ? Boolean.valueOf(dragControllerImpl2.isDrivingTransition()) : null)).toString());
        }
        SwipeAnimation swipeAnimation2 = dragControllerImpl2.swipeAnimation;
        Content content$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout = sceneTransitionLayoutImpl.content$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout(swipeAnimation2.fromContent);
        Swipes swipes = dragControllerImpl2.swipes;
        swipes.updateSwipesResults(content$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout);
        TransitionState.Transition transition = swipeAnimation2.contentTransition;
        TransitionState.Transition transition2 = transition != null ? transition : null;
        if (transition2 instanceof TransitionState.Transition.ChangeScene) {
            ChangeSceneSwipeTransition changeSceneSwipeTransition = (ChangeSceneSwipeTransition) transition2;
            swipeAnimation = new ChangeSceneSwipeTransition(changeSceneSwipeTransition.layoutState, new SwipeAnimation(changeSceneSwipeTransition.swipeAnimation), changeSceneSwipeTransition.key, changeSceneSwipeTransition).swipeAnimation;
        } else if (transition2 instanceof TransitionState.Transition.ShowOrHideOverlay) {
            ShowOrHideOverlaySwipeTransition showOrHideOverlaySwipeTransition = (ShowOrHideOverlaySwipeTransition) transition2;
            swipeAnimation = new ShowOrHideOverlaySwipeTransition(showOrHideOverlaySwipeTransition.layoutState, new SwipeAnimation(showOrHideOverlaySwipeTransition.swipeAnimation), showOrHideOverlaySwipeTransition.overlay, showOrHideOverlaySwipeTransition.fromOrToScene, showOrHideOverlaySwipeTransition.key, showOrHideOverlaySwipeTransition).swipeAnimation;
        } else {
            if (!(transition2 instanceof ReplaceOverlaySwipeTransition)) {
                throw new NoWhenBranchMatchedException();
            }
            ReplaceOverlaySwipeTransition replaceOverlaySwipeTransition = (ReplaceOverlaySwipeTransition) transition2;
            swipeAnimation = new ReplaceOverlaySwipeTransition(replaceOverlaySwipeTransition.layoutState, new SwipeAnimation(replaceOverlaySwipeTransition.swipeAnimation), replaceOverlaySwipeTransition.key, replaceOverlaySwipeTransition).swipeAnimation;
        }
        DragControllerImpl dragControllerImpl3 = new DragControllerImpl(this, swipes, swipeAnimation);
        dragControllerImpl3.updateTransition(swipeAnimation, true);
        this.dragController = dragControllerImpl3;
        return dragControllerImpl3;
    }

    /* renamed from: shouldImmediatelyIntercept-_kEHs6E$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout, reason: not valid java name */
    public final boolean m728xd2531d7e(Offset offset) {
        DragControllerImpl dragControllerImpl = this.dragController;
        if (dragControllerImpl == null || !dragControllerImpl.isDrivingTransition()) {
            return false;
        }
        SwipeAnimation swipeAnimation = dragControllerImpl.swipeAnimation;
        Swipes m726computeSwipesKMgbckE = m726computeSwipesKMgbckE(offset, 1);
        ContentKey currentContent = swipeAnimation.getCurrentContent();
        SceneTransitionLayoutImpl sceneTransitionLayoutImpl = this.layoutImpl;
        Content content$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout = sceneTransitionLayoutImpl.content$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout(currentContent);
        Pair computeSwipesResults = m726computeSwipesKMgbckE.computeSwipesResults(content$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout);
        UserActionResult userActionResult = (UserActionResult) computeSwipesResults.component1();
        UserActionResult userActionResult2 = (UserActionResult) computeSwipesResults.component2();
        SceneKey currentScene = sceneTransitionLayoutImpl.state.getCurrentScene();
        TransitionState.Transition transition = swipeAnimation.contentTransition;
        if (transition == null) {
            transition = null;
        }
        return (userActionResult != null && transition.isTransitioningBetween(content$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout.getKey(), userActionResult.toContent$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout(currentScene))) || (userActionResult2 != null && transition.isTransitioningBetween(content$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout.getKey(), userActionResult2.toContent$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout(currentScene)));
    }
}

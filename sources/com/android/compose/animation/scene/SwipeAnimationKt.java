package com.android.compose.animation.scene;

import androidx.compose.foundation.gestures.Orientation;
import com.android.compose.animation.scene.UserActionResult;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Ref$FloatRef;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class SwipeAnimationKt {
    public static final SwipeAnimation createSwipeAnimation(final SceneTransitionLayoutImpl sceneTransitionLayoutImpl, UserActionResult userActionResult, boolean z, Orientation orientation, float f) {
        Ref$FloatRef ref$FloatRef = new Ref$FloatRef();
        ref$FloatRef.element = f;
        SwipeAnimationKt$createSwipeAnimation$3 swipeAnimationKt$createSwipeAnimation$3 = new SwipeAnimationKt$createSwipeAnimation$3(ref$FloatRef, z, sceneTransitionLayoutImpl, orientation);
        Function0 function0 = new Function0() { // from class: com.android.compose.animation.scene.SwipeAnimationKt$createSwipeAnimation$4
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SceneTransitionLayoutImpl.this.contentForUserActions$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout().getKey();
            }
        };
        boolean z2 = userActionResult instanceof UserActionResult.ChangeScene;
        MutableSceneTransitionLayoutStateImpl mutableSceneTransitionLayoutStateImpl = sceneTransitionLayoutImpl.state;
        if (z2) {
            SceneKey currentScene = mutableSceneTransitionLayoutStateImpl.getCurrentScene();
            return new ChangeSceneSwipeTransition(mutableSceneTransitionLayoutStateImpl, new SwipeAnimation(mutableSceneTransitionLayoutStateImpl, currentScene, ((UserActionResult.ChangeScene) userActionResult).toScene, orientation, z, userActionResult.getRequiresFullDistanceSwipe(), swipeAnimationKt$createSwipeAnimation$3, currentScene, 0.0f), ((UserActionResult.ChangeScene) userActionResult).transitionKey, null).swipeAnimation;
        }
        if (userActionResult instanceof UserActionResult.ShowOverlay) {
            SceneKey currentScene2 = mutableSceneTransitionLayoutStateImpl.getCurrentScene();
            OverlayKey overlayKey = ((UserActionResult.ShowOverlay) userActionResult).overlay;
            return new ShowOrHideOverlaySwipeTransition(mutableSceneTransitionLayoutStateImpl, new SwipeAnimation(mutableSceneTransitionLayoutStateImpl, currentScene2, overlayKey, orientation, z, userActionResult.getRequiresFullDistanceSwipe(), swipeAnimationKt$createSwipeAnimation$3, currentScene2, 0.0f), overlayKey, currentScene2, ((UserActionResult.ShowOverlay) userActionResult).transitionKey, null).swipeAnimation;
        }
        if (userActionResult instanceof UserActionResult.HideOverlay) {
            SceneKey currentScene3 = mutableSceneTransitionLayoutStateImpl.getCurrentScene();
            OverlayKey overlayKey2 = ((UserActionResult.HideOverlay) userActionResult).overlay;
            return new ShowOrHideOverlaySwipeTransition(mutableSceneTransitionLayoutStateImpl, new SwipeAnimation(mutableSceneTransitionLayoutStateImpl, overlayKey2, currentScene3, orientation, z, userActionResult.getRequiresFullDistanceSwipe(), swipeAnimationKt$createSwipeAnimation$3, overlayKey2, 0.0f), overlayKey2, currentScene3, ((UserActionResult.HideOverlay) userActionResult).transitionKey, null).swipeAnimation;
        }
        if (!(userActionResult instanceof UserActionResult.ReplaceByOverlay)) {
            throw new NoWhenBranchMatchedException();
        }
        ContentKey contentKey = (ContentKey) function0.invoke();
        if (contentKey instanceof SceneKey) {
            throw new IllegalStateException("ReplaceByOverlay can only be called when an overlay is shown");
        }
        if (!(contentKey instanceof OverlayKey)) {
            throw new NoWhenBranchMatchedException();
        }
        OverlayKey overlayKey3 = (OverlayKey) contentKey;
        return new ReplaceOverlaySwipeTransition(mutableSceneTransitionLayoutStateImpl, new SwipeAnimation(mutableSceneTransitionLayoutStateImpl, overlayKey3, ((UserActionResult.ReplaceByOverlay) userActionResult).overlay, orientation, z, userActionResult.getRequiresFullDistanceSwipe(), swipeAnimationKt$createSwipeAnimation$3, overlayKey3, 0.0f), ((UserActionResult.ReplaceByOverlay) userActionResult).transitionKey, null).swipeAnimation;
    }
}

package com.android.compose.animation.scene.content.state;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimatableKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import com.android.compose.animation.scene.ContentKey;
import com.android.compose.animation.scene.OverlayKey;
import com.android.compose.animation.scene.OverscrollSpecImpl;
import com.android.compose.animation.scene.SceneKey;
import com.android.compose.animation.scene.SceneTransitionLayoutImpl;
import com.android.compose.animation.scene.TransformationSpec;
import com.android.compose.animation.scene.TransformationSpecImpl;
import com.android.compose.animation.scene.TransitionKey;
import com.android.compose.animation.scene.content.state.TransitionState;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface TransitionState {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface HasOverscrollProperties {
        ContentKey getBouncingContent();

        Orientation getOrientation();

        boolean isUpOrLeft();
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Idle implements TransitionState {
        public final Set currentOverlays;
        public final SceneKey currentScene;

        public Idle(SceneKey sceneKey, Set set) {
            this.currentScene = sceneKey;
            this.currentOverlays = set;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Idle)) {
                return false;
            }
            Idle idle = (Idle) obj;
            return Intrinsics.areEqual(this.currentScene, idle.currentScene) && Intrinsics.areEqual(this.currentOverlays, idle.currentOverlays);
        }

        @Override // com.android.compose.animation.scene.content.state.TransitionState
        public final Set getCurrentOverlays() {
            return this.currentOverlays;
        }

        @Override // com.android.compose.animation.scene.content.state.TransitionState
        public final SceneKey getCurrentScene() {
            return this.currentScene;
        }

        public final int hashCode() {
            return this.currentOverlays.hashCode() + (this.currentScene.identity.hashCode() * 31);
        }

        public final String toString() {
            return "Idle(currentScene=" + this.currentScene + ", currentOverlays=" + this.currentOverlays + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Transition implements TransitionState {
        public final Map activeTransitionLinks;
        public Set currentOverlaysWhenTransitionStarted;
        public SceneKey currentSceneWhenTransitionStarted;
        public final ContentKey fromContent;
        public OverscrollSpecImpl fromOverscrollSpec;
        public Animatable interruptionDecay;
        public TransformationSpecImpl previewTransformationSpec;
        public final Transition replacedTransition;
        public final ContentKey toContent;
        public OverscrollSpecImpl toOverscrollSpec;
        public TransformationSpecImpl transformationSpec;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public abstract class ChangeScene extends Transition {
            public final SceneKey fromScene;
            public final SceneKey toScene;

            public ChangeScene(SceneKey sceneKey, SceneKey sceneKey2, Transition transition) {
                super(sceneKey, sceneKey2, transition);
                this.fromScene = sceneKey;
                this.toScene = sceneKey2;
            }

            @Override // com.android.compose.animation.scene.content.state.TransitionState
            public final Set getCurrentOverlays() {
                Set set = this.currentOverlaysWhenTransitionStarted;
                if (set != null) {
                    return set;
                }
                return null;
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public abstract class OverlayTransition extends Transition {
            public final State currentOverlays$delegate;

            public OverlayTransition(ContentKey contentKey, ContentKey contentKey2, OverlayTransition overlayTransition) {
                super(contentKey, contentKey2, overlayTransition);
                this.currentOverlays$delegate = SnapshotStateKt.derivedStateOf(new Function0() { // from class: com.android.compose.animation.scene.content.state.TransitionState$Transition$OverlayTransition$currentOverlays$2
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return TransitionState.Transition.OverlayTransition.this.computeCurrentOverlays();
                    }
                });
            }

            public abstract Set computeCurrentOverlays();

            @Override // com.android.compose.animation.scene.content.state.TransitionState
            public final Set getCurrentOverlays() {
                return (Set) this.currentOverlays$delegate.getValue();
            }

            @Override // com.android.compose.animation.scene.content.state.TransitionState
            public final SceneKey getCurrentScene() {
                SceneKey sceneKey = this.currentSceneWhenTransitionStarted;
                if (sceneKey != null) {
                    return sceneKey;
                }
                return null;
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public abstract class ShowOrHideOverlay extends OverlayTransition {
            public final SceneKey fromOrToScene;
            public final OverlayKey overlay;

            public ShowOrHideOverlay(OverlayKey overlayKey, SceneKey sceneKey, ContentKey contentKey, ContentKey contentKey2, ShowOrHideOverlay showOrHideOverlay) {
                super(contentKey, contentKey2, showOrHideOverlay);
                this.overlay = overlayKey;
                this.fromOrToScene = sceneKey;
                if (contentKey.equals(sceneKey) && contentKey2.equals(overlayKey)) {
                    return;
                }
                if (!contentKey.equals(overlayKey) || !contentKey2.equals(sceneKey)) {
                    throw new IllegalStateException("Check failed.");
                }
            }

            @Override // com.android.compose.animation.scene.content.state.TransitionState.Transition.OverlayTransition
            public final Set computeCurrentOverlays() {
                boolean isEffectivelyShown = isEffectivelyShown();
                OverlayKey overlayKey = this.overlay;
                if (isEffectivelyShown) {
                    Set set = this.currentOverlaysWhenTransitionStarted;
                    return SetsKt.plus(set != null ? set : null, overlayKey);
                }
                Set set2 = this.currentOverlaysWhenTransitionStarted;
                return SetsKt.minus(set2 != null ? set2 : null, overlayKey);
            }

            public abstract boolean isEffectivelyShown();
        }

        public Transition(ContentKey contentKey, ContentKey contentKey2, Transition transition) {
            this.fromContent = contentKey;
            this.toContent = contentKey2;
            this.replacedTransition = transition;
            TransformationSpec.Companion.getClass();
            this.transformationSpec = TransformationSpec.Companion.Empty;
            this.activeTransitionLinks = new LinkedHashMap();
            if (Intrinsics.areEqual(contentKey, contentKey2)) {
                throw new IllegalStateException("Check failed.");
            }
            if (transition != null) {
                if (!Intrinsics.areEqual(transition.fromContent, contentKey) || !Intrinsics.areEqual(transition.toContent, contentKey2)) {
                    throw new IllegalStateException("Check failed.");
                }
            }
        }

        public abstract void freezeAndAnimateToCurrentState();

        /* JADX WARN: Multi-variable type inference failed */
        public final OverscrollSpecImpl getCurrentOverscrollSpec$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout() {
            if (!(this instanceof HasOverscrollProperties)) {
                return null;
            }
            float progress = getProgress();
            ContentKey bouncingContent = ((HasOverscrollProperties) this).getBouncingContent();
            if (progress < 0.0f || Intrinsics.areEqual(bouncingContent, this.fromContent)) {
                return this.fromOverscrollSpec;
            }
            if (progress > 1.0f || Intrinsics.areEqual(bouncingContent, this.toContent)) {
                return this.toOverscrollSpec;
            }
            return null;
        }

        public TransitionKey getKey() {
            return null;
        }

        public float getPreviewProgress$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout() {
            return 0.0f;
        }

        public abstract float getProgress();

        public abstract float getProgressVelocity();

        public final float interruptionProgress$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout(SceneTransitionLayoutImpl sceneTransitionLayoutImpl) {
            if (!sceneTransitionLayoutImpl.state.enableInterruptions) {
                return 0.0f;
            }
            Transition transition = this.replacedTransition;
            if (transition != null) {
                return transition.interruptionProgress$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout(sceneTransitionLayoutImpl);
            }
            Animatable animatable = this.interruptionDecay;
            if (animatable == null) {
                animatable = AnimatableKt.Animatable(1.0f, 0.001f);
                BuildersKt.launch$default(sceneTransitionLayoutImpl.animationScope, null, null, new TransitionState$Transition$interruptionProgress$create$1(sceneTransitionLayoutImpl, animatable, null), 3);
                this.interruptionDecay = animatable;
            }
            return ((Number) ((SnapshotMutableStateImpl) animatable.internalState.value$delegate).getValue()).floatValue();
        }

        public boolean isInPreviewStage$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout() {
            return false;
        }

        public abstract boolean isInitiatedByUserInput();

        public final boolean isTransitioning(ContentKey contentKey, ContentKey contentKey2) {
            return (contentKey == null || Intrinsics.areEqual(this.fromContent, contentKey)) && (contentKey2 == null || Intrinsics.areEqual(this.toContent, contentKey2));
        }

        public final boolean isTransitioningBetween(ContentKey contentKey, ContentKey contentKey2) {
            return isTransitioning(contentKey, contentKey2) || isTransitioning(contentKey2, contentKey);
        }

        public abstract boolean isUserInputOngoing();

        public final boolean isWithinProgressRange$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout(float f) {
            if (!(this instanceof HasOverscrollProperties)) {
                return true;
            }
            if ((f <= 0.0f ? this.fromOverscrollSpec : f >= 1.0f ? this.toOverscrollSpec : null) == null) {
                return true;
            }
            return !r2.transformationSpec.transformations.isEmpty();
        }

        public abstract Object run(Continuation continuation);
    }

    Set getCurrentOverlays();

    SceneKey getCurrentScene();
}

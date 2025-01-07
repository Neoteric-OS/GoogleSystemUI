package com.android.compose.animation.scene;

import androidx.compose.runtime.SnapshotStateKt;
import com.android.compose.animation.scene.ObservableTransitionState;
import com.android.compose.animation.scene.content.state.TransitionState;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SafeFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ObservableTransitionStateKt {
    public static final Flow observableTransitionState(final SceneTransitionLayoutState sceneTransitionLayoutState) {
        return FlowKt.distinctUntilChanged(SnapshotStateKt.snapshotFlow(new Function0() { // from class: com.android.compose.animation.scene.ObservableTransitionStateKt$observableTransitionState$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                TransitionState transitionState = ((MutableSceneTransitionLayoutStateImpl) SceneTransitionLayoutState.this).getTransitionState();
                if (transitionState instanceof TransitionState.Idle) {
                    TransitionState.Idle idle = (TransitionState.Idle) transitionState;
                    return new ObservableTransitionState.Idle(idle.currentScene, idle.currentOverlays);
                }
                if (transitionState instanceof TransitionState.Transition.ChangeScene) {
                    TransitionState.Transition.ChangeScene changeScene = (TransitionState.Transition.ChangeScene) transitionState;
                    SceneKey sceneKey = changeScene.fromScene;
                    final TransitionState.Transition.ChangeScene changeScene2 = (TransitionState.Transition.ChangeScene) transitionState;
                    SafeFlow snapshotFlow = SnapshotStateKt.snapshotFlow(new Function0() { // from class: com.android.compose.animation.scene.ObservableTransitionStateKt$observableTransitionState$1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return changeScene2.getCurrentScene();
                        }
                    });
                    changeScene2.getClass();
                    return new ObservableTransitionState.Transition.ChangeScene(sceneKey, changeScene.toScene, snapshotFlow, SnapshotStateKt.snapshotFlow(new Function0() { // from class: com.android.compose.animation.scene.ObservableTransitionStateKt$observableTransitionState$1.2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return Float.valueOf(((TransitionState.Transition.ChangeScene) changeScene2).getProgress());
                        }
                    }), changeScene.isInitiatedByUserInput(), SnapshotStateKt.snapshotFlow(new Function0() { // from class: com.android.compose.animation.scene.ObservableTransitionStateKt$observableTransitionState$1.3
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return Boolean.valueOf(((TransitionState.Transition.ChangeScene) changeScene2).isUserInputOngoing());
                        }
                    }), SnapshotStateKt.snapshotFlow(new Function0() { // from class: com.android.compose.animation.scene.ObservableTransitionStateKt$observableTransitionState$1.4
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return Float.valueOf(((TransitionState.Transition.ChangeScene) changeScene2).getPreviewProgress$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout());
                        }
                    }), SnapshotStateKt.snapshotFlow(new Function0() { // from class: com.android.compose.animation.scene.ObservableTransitionStateKt$observableTransitionState$1.5
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return Boolean.valueOf(((TransitionState.Transition.ChangeScene) changeScene2).isInPreviewStage$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout());
                        }
                    }));
                }
                if (transitionState instanceof TransitionState.Transition.ShowOrHideOverlay) {
                    TransitionState.Transition.ShowOrHideOverlay showOrHideOverlay = (TransitionState.Transition.ShowOrHideOverlay) transitionState;
                    TransitionState.Transition.OverlayTransition overlayTransition = (TransitionState.Transition.OverlayTransition) transitionState;
                    if (!Intrinsics.areEqual(showOrHideOverlay.fromOrToScene, overlayTransition.getCurrentScene())) {
                        throw new IllegalStateException("Check failed.");
                    }
                    SceneKey currentScene = overlayTransition.getCurrentScene();
                    final TransitionState.Transition.ShowOrHideOverlay showOrHideOverlay2 = (TransitionState.Transition.ShowOrHideOverlay) transitionState;
                    SnapshotStateKt.snapshotFlow(new Function0() { // from class: com.android.compose.animation.scene.ObservableTransitionStateKt$observableTransitionState$1.6
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return showOrHideOverlay2.getCurrentOverlays();
                        }
                    });
                    SafeFlow snapshotFlow2 = SnapshotStateKt.snapshotFlow(new Function0() { // from class: com.android.compose.animation.scene.ObservableTransitionStateKt$observableTransitionState$1.7
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return Float.valueOf(((TransitionState.Transition.ShowOrHideOverlay) showOrHideOverlay2).getProgress());
                        }
                    });
                    boolean isInitiatedByUserInput = showOrHideOverlay.isInitiatedByUserInput();
                    SafeFlow snapshotFlow3 = SnapshotStateKt.snapshotFlow(new Function0() { // from class: com.android.compose.animation.scene.ObservableTransitionStateKt$observableTransitionState$1.8
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return Boolean.valueOf(((TransitionState.Transition.ShowOrHideOverlay) showOrHideOverlay2).isUserInputOngoing());
                        }
                    });
                    SnapshotStateKt.snapshotFlow(new Function0() { // from class: com.android.compose.animation.scene.ObservableTransitionStateKt$observableTransitionState$1.9
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return Float.valueOf(((TransitionState.Transition.ShowOrHideOverlay) showOrHideOverlay2).getPreviewProgress$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout());
                        }
                    });
                    return new ObservableTransitionState.Transition.ShowOrHideOverlay(showOrHideOverlay.fromContent, showOrHideOverlay.toContent, currentScene, snapshotFlow2, isInitiatedByUserInput, snapshotFlow3, SnapshotStateKt.snapshotFlow(new Function0() { // from class: com.android.compose.animation.scene.ObservableTransitionStateKt$observableTransitionState$1.10
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return Boolean.valueOf(((TransitionState.Transition.ShowOrHideOverlay) showOrHideOverlay2).isInPreviewStage$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout());
                        }
                    }));
                }
                if (!(transitionState instanceof ReplaceOverlaySwipeTransition)) {
                    throw new NoWhenBranchMatchedException();
                }
                ReplaceOverlaySwipeTransition replaceOverlaySwipeTransition = (ReplaceOverlaySwipeTransition) transitionState;
                OverlayKey overlayKey = replaceOverlaySwipeTransition.fromOverlay;
                SceneKey currentScene2 = ((TransitionState.Transition.OverlayTransition) transitionState).getCurrentScene();
                final ReplaceOverlaySwipeTransition replaceOverlaySwipeTransition2 = (ReplaceOverlaySwipeTransition) transitionState;
                return new ObservableTransitionState.Transition.ReplaceOverlay(overlayKey, replaceOverlaySwipeTransition.toOverlay, currentScene2, SnapshotStateKt.snapshotFlow(new Function0() { // from class: com.android.compose.animation.scene.ObservableTransitionStateKt$observableTransitionState$1.11
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return replaceOverlaySwipeTransition2.getCurrentOverlays();
                    }
                }), SnapshotStateKt.snapshotFlow(new Function0() { // from class: com.android.compose.animation.scene.ObservableTransitionStateKt$observableTransitionState$1.12
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Float.valueOf(((ReplaceOverlaySwipeTransition) replaceOverlaySwipeTransition2).getProgress());
                    }
                }), replaceOverlaySwipeTransition.isInitiatedByUserInput(), SnapshotStateKt.snapshotFlow(new Function0() { // from class: com.android.compose.animation.scene.ObservableTransitionStateKt$observableTransitionState$1.13
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Boolean.valueOf(((ReplaceOverlaySwipeTransition) replaceOverlaySwipeTransition2).isUserInputOngoing());
                    }
                }), SnapshotStateKt.snapshotFlow(new Function0() { // from class: com.android.compose.animation.scene.ObservableTransitionStateKt$observableTransitionState$1.14
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Float.valueOf(((ReplaceOverlaySwipeTransition) replaceOverlaySwipeTransition2).getPreviewProgress$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout());
                    }
                }), SnapshotStateKt.snapshotFlow(new Function0() { // from class: com.android.compose.animation.scene.ObservableTransitionStateKt$observableTransitionState$1.15
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Boolean.valueOf(((ReplaceOverlaySwipeTransition) replaceOverlaySwipeTransition2).isInPreviewStage$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout());
                    }
                }));
            }
        }));
    }
}

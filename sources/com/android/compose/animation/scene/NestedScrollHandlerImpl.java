package com.android.compose.animation.scene;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.ui.input.nestedscroll.NestedScrollSource;
import com.android.compose.animation.scene.Swipe;
import com.android.compose.animation.scene.SwipeDirection;
import com.android.compose.animation.scene.content.Scene;
import com.android.compose.animation.scene.content.state.TransitionState;
import com.android.compose.nestedscroll.PriorityNestedScrollConnection;
import com.android.compose.nestedscroll.PriorityNestedScrollConnectionKt;
import java.util.Map;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$BooleanRef;
import kotlin.jvm.internal.Ref$ObjectRef;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class NestedScrollHandlerImpl {
    public NestedScrollBehavior bottomOrRightBehavior;
    public final PriorityNestedScrollConnection connection;
    public final DraggableHandlerImpl draggableHandler;
    public Function0 isExternalOverscrollGesture;
    public final SceneTransitionLayoutImpl layoutImpl;
    public final MutableSceneTransitionLayoutStateImpl layoutState;
    public final Orientation orientation;
    public final SwipeToSceneNode$nestedScrollHandlerImpl$2 pointersInfoOwner;
    public NestedScrollBehavior topOrLeftBehavior;

    public NestedScrollHandlerImpl(SceneTransitionLayoutImpl sceneTransitionLayoutImpl, Orientation orientation, NestedScrollBehavior nestedScrollBehavior, NestedScrollBehavior nestedScrollBehavior2, Function0 function0, SwipeToSceneNode$nestedScrollHandlerImpl$2 swipeToSceneNode$nestedScrollHandlerImpl$2) {
        DraggableHandlerImpl draggableHandlerImpl;
        this.layoutImpl = sceneTransitionLayoutImpl;
        this.orientation = orientation;
        this.topOrLeftBehavior = nestedScrollBehavior;
        this.bottomOrRightBehavior = nestedScrollBehavior2;
        this.isExternalOverscrollGesture = function0;
        this.pointersInfoOwner = swipeToSceneNode$nestedScrollHandlerImpl$2;
        this.layoutState = sceneTransitionLayoutImpl.state;
        int ordinal = orientation.ordinal();
        if (ordinal == 0) {
            draggableHandlerImpl = sceneTransitionLayoutImpl.verticalDraggableHandler;
        } else {
            if (ordinal != 1) {
                throw new NoWhenBranchMatchedException();
            }
            draggableHandlerImpl = sceneTransitionLayoutImpl.horizontalDraggableHandler;
        }
        this.draggableHandler = draggableHandlerImpl;
        final Ref$BooleanRef ref$BooleanRef = new Ref$BooleanRef();
        final Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
        final Ref$ObjectRef ref$ObjectRef2 = new Ref$ObjectRef();
        final Ref$BooleanRef ref$BooleanRef2 = new Ref$BooleanRef();
        this.connection = PriorityNestedScrollConnectionKt.PriorityNestedScrollConnection(orientation, new Function2(this) { // from class: com.android.compose.animation.scene.NestedScrollHandlerImpl$nestedScrollConnection$1
            final /* synthetic */ NestedScrollHandlerImpl this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                float floatValue = ((Number) obj).floatValue();
                float floatValue2 = ((Number) obj2).floatValue();
                ref$BooleanRef.element = !((Boolean) this.this$0.isExternalOverscrollGesture.invoke()).booleanValue() && floatValue2 == 0.0f;
                if (!ref$BooleanRef.element || floatValue == 0.0f || !this.this$0.draggableHandler.m728xd2531d7e(null)) {
                    return Boolean.FALSE;
                }
                NestedScrollHandlerImpl nestedScrollHandlerImpl = this.this$0;
                float f = nestedScrollHandlerImpl.layoutImpl.transitionInterceptionThreshold;
                MutableSceneTransitionLayoutStateImpl mutableSceneTransitionLayoutStateImpl = nestedScrollHandlerImpl.layoutState;
                TransitionState.Transition currentTransition = mutableSceneTransitionLayoutStateImpl.getCurrentTransition();
                if (currentTransition != null) {
                    float progress = currentTransition.getProgress();
                    if ((Math.abs(progress - 0.0f) <= f && Intrinsics.areEqual(currentTransition.getCurrentScene(), currentTransition.fromContent)) || (Math.abs(progress - 1.0f) <= f && Intrinsics.areEqual(currentTransition.getCurrentScene(), currentTransition.toContent))) {
                        while (!mutableSceneTransitionLayoutStateImpl.getCurrentTransitions().isEmpty()) {
                            mutableSceneTransitionLayoutStateImpl.finishTransition((TransitionState.Transition) mutableSceneTransitionLayoutStateImpl.getTransitionStates$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout().get(0));
                        }
                        return Boolean.FALSE;
                    }
                }
                ref$ObjectRef.element = this.this$0.pointersInfoOwner.pointersInfo();
                ref$BooleanRef2.element = true;
                return Boolean.TRUE;
            }
        }, new Function2() { // from class: com.android.compose.animation.scene.NestedScrollHandlerImpl$nestedScrollConnection$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                NestedScrollBehavior nestedScrollBehavior3;
                float floatValue = ((Number) obj).floatValue();
                float floatValue2 = ((Number) obj2).floatValue();
                if (floatValue > 0.0f) {
                    nestedScrollBehavior3 = NestedScrollHandlerImpl.this.topOrLeftBehavior;
                } else {
                    if (floatValue >= 0.0f) {
                        return Boolean.FALSE;
                    }
                    nestedScrollBehavior3 = NestedScrollHandlerImpl.this.bottomOrRightBehavior;
                }
                boolean z = true;
                boolean z2 = !((Boolean) NestedScrollHandlerImpl.this.isExternalOverscrollGesture.invoke()).booleanValue() && floatValue2 == 0.0f;
                ref$ObjectRef.element = NestedScrollHandlerImpl.this.pointersInfoOwner.pointersInfo();
                int ordinal2 = nestedScrollBehavior3.ordinal();
                if (ordinal2 == 0) {
                    ref$BooleanRef.element = z2;
                    if (!z2 || !NestedScrollHandlerImpl.access$nestedScrollConnection$hasNextScene(NestedScrollHandlerImpl.this, ref$ObjectRef, floatValue)) {
                        z = false;
                    }
                } else if (ordinal2 == 1) {
                    ref$BooleanRef.element = z2;
                    z = NestedScrollHandlerImpl.access$nestedScrollConnection$hasNextScene(NestedScrollHandlerImpl.this, ref$ObjectRef, floatValue);
                } else {
                    if (ordinal2 != 2) {
                        throw new NoWhenBranchMatchedException();
                    }
                    ref$BooleanRef.element = true;
                    z = NestedScrollHandlerImpl.access$nestedScrollConnection$hasNextScene(NestedScrollHandlerImpl.this, ref$ObjectRef, floatValue);
                }
                if (z) {
                    ref$BooleanRef2.element = false;
                }
                return Boolean.valueOf(z);
            }
        }, new Function1() { // from class: com.android.compose.animation.scene.NestedScrollHandlerImpl$nestedScrollConnection$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                NestedScrollBehavior nestedScrollBehavior3;
                float floatValue = ((Number) obj).floatValue();
                if (floatValue > 0.0f) {
                    nestedScrollBehavior3 = NestedScrollHandlerImpl.this.topOrLeftBehavior;
                } else {
                    if (floatValue >= 0.0f) {
                        return Boolean.FALSE;
                    }
                    nestedScrollBehavior3 = NestedScrollHandlerImpl.this.bottomOrRightBehavior;
                }
                ref$BooleanRef.element = false;
                ref$ObjectRef.element = NestedScrollHandlerImpl.this.pointersInfoOwner.pointersInfo();
                boolean z = nestedScrollBehavior3.getCanStartOnPostFling() && NestedScrollHandlerImpl.access$nestedScrollConnection$hasNextScene(NestedScrollHandlerImpl.this, ref$ObjectRef, floatValue);
                if (z) {
                    ref$BooleanRef2.element = false;
                }
                return Boolean.valueOf(z);
            }
        }, new Function1() { // from class: com.android.compose.animation.scene.NestedScrollHandlerImpl$nestedScrollConnection$4
            @Override // kotlin.jvm.functions.Function1
            public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                ((NestedScrollSource) obj).getClass();
                return Boolean.TRUE;
            }
        }, false, new Function1() { // from class: com.android.compose.animation.scene.NestedScrollHandlerImpl$nestedScrollConnection$5
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                float floatValue = ((Number) obj).floatValue();
                Object obj2 = ref$ObjectRef.element;
                if (obj2 == null) {
                    throw new IllegalStateException("PointersInfo should be initialized before the transition begins.");
                }
                PointersInfo pointersInfo = (PointersInfo) obj2;
                Ref$ObjectRef ref$ObjectRef3 = Ref$ObjectRef.this;
                DraggableHandlerImpl draggableHandlerImpl2 = this.draggableHandler;
                if (ref$BooleanRef2.element) {
                    floatValue = 0.0f;
                }
                ref$ObjectRef3.element = draggableHandlerImpl2.m727onDragStartedMjzGXtM(pointersInfo.startedPosition, floatValue, pointersInfo.pointersDown);
                return Unit.INSTANCE;
            }
        }, new Function1() { // from class: com.android.compose.animation.scene.NestedScrollHandlerImpl$nestedScrollConnection$6
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                float floatValue = ((Number) obj).floatValue();
                DragController dragController = (DragController) Ref$ObjectRef.this.element;
                if (dragController != null) {
                    return Float.valueOf(dragController.onDrag(floatValue));
                }
                throw new IllegalStateException("Should be called after onStart");
            }
        }, new Function1() { // from class: com.android.compose.animation.scene.NestedScrollHandlerImpl$nestedScrollConnection$7
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                float floatValue = ((Number) obj).floatValue();
                DragController dragController = (DragController) Ref$ObjectRef.this.element;
                if (dragController == null) {
                    throw new IllegalStateException("Should be called after onStart");
                }
                Float valueOf = Float.valueOf(dragController.onStop(floatValue, ref$BooleanRef.element));
                Ref$ObjectRef.this.element = null;
                return valueOf;
            }
        });
    }

    public static final boolean access$nestedScrollConnection$hasNextScene(NestedScrollHandlerImpl nestedScrollHandlerImpl, Ref$ObjectRef ref$ObjectRef, float f) {
        SwipeDirection.Resolved resolved;
        SwipeDirection.Resolved resolved2;
        TransitionState transitionState = nestedScrollHandlerImpl.layoutState.getTransitionState();
        SceneKey currentScene = transitionState.getCurrentScene();
        SceneTransitionLayoutImpl sceneTransitionLayoutImpl = nestedScrollHandlerImpl.layoutImpl;
        Scene scene$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout = sceneTransitionLayoutImpl.scene$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout(currentScene);
        Orientation orientation = nestedScrollHandlerImpl.orientation;
        MutableState mutableState = scene$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout.userActions$delegate;
        UserActionResult userActionResult = null;
        if (f < 0.0f) {
            int ordinal = orientation.ordinal();
            if (ordinal == 0) {
                resolved2 = SwipeDirection.Resolved.Up;
            } else {
                if (ordinal != 1) {
                    throw new NoWhenBranchMatchedException();
                }
                resolved2 = SwipeDirection.Resolved.Left;
            }
            Object obj = ref$ObjectRef.element;
            if (obj == null) {
                throw new IllegalStateException("PointersInfo should be initialized before the transition begins.");
            }
            userActionResult = (UserActionResult) ((Map) ((SnapshotMutableStateImpl) mutableState).getValue()).get(new Swipe.Resolved(resolved2, ((PointersInfo) obj).pointersDown, null));
        } else if (f > 0.0f) {
            int ordinal2 = orientation.ordinal();
            if (ordinal2 == 0) {
                resolved = SwipeDirection.Resolved.Down;
            } else {
                if (ordinal2 != 1) {
                    throw new NoWhenBranchMatchedException();
                }
                resolved = SwipeDirection.Resolved.Right;
            }
            Object obj2 = ref$ObjectRef.element;
            if (obj2 == null) {
                throw new IllegalStateException("PointersInfo should be initialized before the transition begins.");
            }
            userActionResult = (UserActionResult) ((Map) ((SnapshotMutableStateImpl) mutableState).getValue()).get(new Swipe.Resolved(resolved, ((PointersInfo) obj2).pointersDown, null));
        }
        if (userActionResult != null) {
            return true;
        }
        return (transitionState instanceof TransitionState.Idle) && sceneTransitionLayoutImpl.state.transitions.overscrollSpec$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout(currentScene, orientation) != null;
    }
}

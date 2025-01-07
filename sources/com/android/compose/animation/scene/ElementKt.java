package com.android.compose.animation.scene;

import androidx.compose.runtime.snapshots.SnapshotStateMap;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutNodeDrawScope;
import androidx.compose.ui.platform.TestTagKt;
import com.android.compose.animation.scene.Element;
import com.android.compose.animation.scene.content.Content;
import com.android.compose.animation.scene.content.state.TransitionState;
import com.android.compose.animation.scene.transformation.SharedElementTransformation;
import java.util.List;
import java.util.Set;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ElementKt {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [com.android.compose.animation.scene.content.state.TransitionState] */
    /* JADX WARN: Type inference failed for: r0v10, types: [com.android.compose.animation.scene.content.state.TransitionState] */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v8, types: [com.android.compose.animation.scene.content.state.TransitionState$Transition] */
    public static final TransitionState access$elementState(SceneTransitionLayoutImpl sceneTransitionLayoutImpl, Element element, List list) {
        TransitionState.Transition transition = (TransitionState) CollectionsKt.last(list);
        if (!(transition instanceof TransitionState.Idle)) {
            int size = list.size() - 1;
            if (size >= 0) {
                while (true) {
                    int i = size - 1;
                    transition = (TransitionState.Transition) ((TransitionState) list.get(size));
                    if (element.stateByContent.containsKey(transition.fromContent) || element.stateByContent.containsKey(transition.toContent)) {
                        break;
                    }
                    if (i < 0) {
                        break;
                    }
                    size = i;
                }
            }
            transition = 0;
        } else if (list.size() != 1) {
            throw new IllegalStateException("Check failed.");
        }
        TransitionState.Transition transition2 = transition instanceof TransitionState.Transition ? transition : null;
        TransitionState.Transition transition3 = element.lastTransition;
        element.lastTransition = transition2;
        boolean areEqual = Intrinsics.areEqual(transition2, transition3);
        SnapshotStateMap snapshotStateMap = element.stateByContent;
        if (areEqual || transition2 == null || transition3 == null) {
            if (transition2 == null && transition3 != null) {
                for (Element.State state : snapshotStateMap.values) {
                    state.offsetBeforeInterruption = 9205357640488583168L;
                    state.scaleBeforeInterruption = Scale.Unspecified;
                    int i2 = Element.$r8$clinit;
                    state.alphaBeforeInterruption = Float.MAX_VALUE;
                    state.offsetInterruptionDelta = 0L;
                    state.sizeInterruptionDelta = 0L;
                    state.scaleInterruptionDelta = Scale.Zero;
                    state.alphaInterruptionDelta = 0.0f;
                }
            }
        } else if (!Intrinsics.areEqual(transition2.replacedTransition, transition3)) {
            Element.State prepareInterruption$updateStateInContent = prepareInterruption$updateStateInContent(snapshotStateMap, transition3.fromContent);
            Element.State prepareInterruption$updateStateInContent2 = prepareInterruption$updateStateInContent(snapshotStateMap, transition3.toContent);
            Element.State prepareInterruption$updateStateInContent3 = prepareInterruption$updateStateInContent(snapshotStateMap, transition2.fromContent);
            Element.State prepareInterruption$updateStateInContent4 = prepareInterruption$updateStateInContent(snapshotStateMap, transition2.toContent);
            reconcileStates(element, transition3);
            reconcileStates(element, transition2);
            if (prepareInterruption$updateStateInContent != null) {
                prepareInterruption$cleanInterruptionValues(sceneTransitionLayoutImpl, element, transition2, prepareInterruption$updateStateInContent);
            }
            if (prepareInterruption$updateStateInContent2 != null) {
                prepareInterruption$cleanInterruptionValues(sceneTransitionLayoutImpl, element, transition2, prepareInterruption$updateStateInContent2);
            }
            if (prepareInterruption$updateStateInContent3 != null) {
                prepareInterruption$cleanInterruptionValues(sceneTransitionLayoutImpl, element, transition2, prepareInterruption$updateStateInContent3);
            }
            if (prepareInterruption$updateStateInContent4 != null) {
                prepareInterruption$cleanInterruptionValues(sceneTransitionLayoutImpl, element, transition2, prepareInterruption$updateStateInContent4);
            }
        }
        return transition;
    }

    public static final Modifier element(Modifier modifier, SceneTransitionLayoutImpl sceneTransitionLayoutImpl, Content content, ElementKey elementKey) {
        return TestTagKt.testTag(modifier.then(new ElementModifier(sceneTransitionLayoutImpl, sceneTransitionLayoutImpl.state.getTransitionStates$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout(), content, elementKey)), elementKey.testTag);
    }

    public static final long getDrawScale$specifiedOrCenter(long j, LayoutNodeDrawScope layoutNodeDrawScope) {
        Offset offset = new Offset(j);
        if ((j & 9223372034707292159L) == 9205357640488583168L) {
            offset = null;
        }
        return offset != null ? offset.packedValue : layoutNodeDrawScope.canvasDrawScope.mo431getCenterF1C5BW0();
    }

    public static final float interruptedAlpha(SceneTransitionLayoutImpl sceneTransitionLayoutImpl, Element element, TransitionState.Transition transition, Element.State state, float f) {
        int i = Element.$r8$clinit;
        Float valueOf = Float.valueOf(Float.MAX_VALUE);
        Float valueOf2 = Float.valueOf(0.0f);
        Float valueOf3 = Float.valueOf(state.alphaBeforeInterruption);
        if (!valueOf3.equals(valueOf)) {
            float floatValue = valueOf3.floatValue() - f;
            state.alphaInterruptionDelta = floatValue;
            if (transition != null) {
                ContentKey contentKey = state.content;
                ContentKey contentKey2 = transition.fromContent;
                if (Intrinsics.areEqual(contentKey, contentKey2)) {
                    contentKey2 = transition.toContent;
                }
                Element.State state2 = (Element.State) element.stateByContent.get(contentKey2);
                if (state2 != null && isSharedElementEnabled(element.key, transition)) {
                    state2.alphaInterruptionDelta = floatValue;
                }
            }
            state.alphaBeforeInterruption = valueOf.floatValue();
        }
        Float valueOf4 = Float.valueOf(state.alphaInterruptionDelta);
        if (valueOf4.equals(valueOf2) || transition == null) {
            return f;
        }
        float interruptionProgress$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout = transition.interruptionProgress$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout(sceneTransitionLayoutImpl);
        return interruptionProgress$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout == 0.0f ? f : f + (valueOf4.floatValue() * interruptionProgress$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout);
    }

    public static final boolean isSharedElementEnabled(ElementKey elementKey, TransitionState.Transition transition) {
        sharedElementTransformation(elementKey, transition);
        return true;
    }

    public static final void prepareInterruption$cleanInterruptionValues(SceneTransitionLayoutImpl sceneTransitionLayoutImpl, Element element, TransitionState.Transition transition, Element.State state) {
        state.sizeInterruptionDelta = 0L;
        state.offsetInterruptionDelta = 0L;
        state.alphaInterruptionDelta = 0.0f;
        state.scaleInterruptionDelta = Scale.Zero;
        if (shouldPlaceElement(sceneTransitionLayoutImpl, state.content, element, transition)) {
            return;
        }
        state.offsetBeforeInterruption = 9205357640488583168L;
        int i = Element.$r8$clinit;
        state.alphaBeforeInterruption = Float.MAX_VALUE;
        state.scaleBeforeInterruption = Scale.Unspecified;
    }

    public static final Element.State prepareInterruption$updateStateInContent(SnapshotStateMap snapshotStateMap, ContentKey contentKey) {
        Element.State state = (Element.State) snapshotStateMap.get(contentKey);
        if (state == null) {
            return null;
        }
        state.sizeBeforeInterruption = state.lastSize;
        float f = state.lastAlpha;
        if (f > 0.0f) {
            state.offsetBeforeInterruption = state.lastOffset;
            state.scaleBeforeInterruption = state.lastScale;
            state.alphaBeforeInterruption = f;
            return state;
        }
        state.offsetBeforeInterruption = 9205357640488583168L;
        state.scaleBeforeInterruption = Scale.Unspecified;
        int i = Element.$r8$clinit;
        state.alphaBeforeInterruption = Float.MAX_VALUE;
        return state;
    }

    public static final void reconcileStates(Element element, TransitionState.Transition transition) {
        Element.State state;
        Element.State state2 = (Element.State) element.stateByContent.get(transition.fromContent);
        if (state2 == null || (state = (Element.State) element.stateByContent.get(transition.toContent)) == null || !isSharedElementEnabled(element.key, transition)) {
            return;
        }
        boolean m310equalsimpl0 = Offset.m310equalsimpl0(state2.offsetBeforeInterruption, 9205357640488583168L);
        Scale scale = Scale.Zero;
        if (!m310equalsimpl0 && Offset.m310equalsimpl0(state.offsetBeforeInterruption, 9205357640488583168L)) {
            state.offsetBeforeInterruption = state2.offsetBeforeInterruption;
            state.sizeBeforeInterruption = state2.sizeBeforeInterruption;
            state.scaleBeforeInterruption = state2.scaleBeforeInterruption;
            state.alphaBeforeInterruption = state2.alphaBeforeInterruption;
            state.offsetInterruptionDelta = 0L;
            state.sizeInterruptionDelta = 0L;
            state.scaleInterruptionDelta = scale;
            state.alphaInterruptionDelta = 0.0f;
            return;
        }
        if (Offset.m310equalsimpl0(state.offsetBeforeInterruption, 9205357640488583168L) || !Offset.m310equalsimpl0(state2.offsetBeforeInterruption, 9205357640488583168L)) {
            return;
        }
        state2.offsetBeforeInterruption = state.offsetBeforeInterruption;
        state2.sizeBeforeInterruption = state.sizeBeforeInterruption;
        state2.scaleBeforeInterruption = state.scaleBeforeInterruption;
        state2.alphaBeforeInterruption = state.alphaBeforeInterruption;
        state2.offsetInterruptionDelta = 0L;
        state2.sizeInterruptionDelta = 0L;
        state2.scaleInterruptionDelta = scale;
        state2.alphaInterruptionDelta = 0.0f;
    }

    public static final SharedElementTransformation sharedElementTransformation(ElementKey elementKey, TransitionState.Transition transition) {
        TransformationSpecImpl transformationSpecImpl = transition.transformationSpec;
        ElementTransformations transformations$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout = transformationSpecImpl.transformations$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout(elementKey, transition.fromContent);
        ElementTransformations transformations$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout2 = transformationSpecImpl.transformations$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout(elementKey, transition.toContent);
        SharedElementTransformation sharedElementTransformation = transformations$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout.shared;
        SharedElementTransformation sharedElementTransformation2 = transformations$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout2.shared;
        if (Intrinsics.areEqual(sharedElementTransformation, sharedElementTransformation2)) {
            return sharedElementTransformation;
        }
        throw new IllegalStateException(("Different sharedElement() transformations matched " + elementKey + " (from=" + sharedElementTransformation + " to=" + sharedElementTransformation2 + ")").toString());
    }

    /* JADX WARN: Type inference failed for: r7v0, types: [boolean, int] */
    public static final boolean shouldPlaceElement(SceneTransitionLayoutImpl sceneTransitionLayoutImpl, ContentKey contentKey, Element element, TransitionState transitionState) {
        if (element.key.placeAllCopies) {
            return true;
        }
        boolean z = transitionState instanceof TransitionState.Idle;
        OverlayKey overlayKey = null;
        SnapshotStateMap snapshotStateMap = element.stateByContent;
        if (z) {
            TransitionState.Idle idle = (TransitionState.Idle) transitionState;
            Object obj = idle.currentScene;
            Set<OverlayKey> set = idle.currentOverlays;
            if (!set.isEmpty()) {
                for (OverlayKey overlayKey2 : set) {
                    if (snapshotStateMap.containsKey(overlayKey2) && (overlayKey == null || sceneTransitionLayoutImpl.overlay$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout(overlayKey2).getZIndex() > sceneTransitionLayoutImpl.overlay$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout(overlayKey).getZIndex())) {
                        overlayKey = overlayKey2;
                    }
                }
                if (overlayKey != null) {
                    obj = overlayKey;
                }
            }
            return Intrinsics.areEqual(contentKey, obj);
        }
        if (!(transitionState instanceof TransitionState.Transition)) {
            throw new NoWhenBranchMatchedException();
        }
        TransitionState.Transition transition = (TransitionState.Transition) transitionState;
        boolean z2 = transition instanceof ReplaceOverlaySwipeTransition;
        boolean areEqual = Intrinsics.areEqual(contentKey, transition.fromContent);
        ContentKey contentKey2 = transition.toContent;
        if (!areEqual && !Intrinsics.areEqual(contentKey, contentKey2) && (!z2 || !Intrinsics.areEqual(contentKey, transition.getCurrentScene()))) {
            return false;
        }
        ContentKey contentKey3 = transition.fromContent;
        ?? containsKey = snapshotStateMap.containsKey(contentKey3);
        int i = containsKey;
        if (snapshotStateMap.containsKey(contentKey2)) {
            i = containsKey + 1;
        }
        if (z2 && snapshotStateMap.containsKey(transition.getCurrentScene())) {
            i++;
        }
        if (i <= 1) {
            return true;
        }
        ElementKey elementKey = element.key;
        sharedElementTransformation(elementKey, transition);
        OverscrollSpecImpl currentOverscrollSpec$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout = transition.getCurrentOverscrollSpec$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout();
        ContentKey contentKey4 = currentOverscrollSpec$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout != null ? currentOverscrollSpec$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout.content : null;
        if (contentKey4 == null) {
            return Intrinsics.areEqual(elementKey.contentPicker.contentDuringTransition(transition, sceneTransitionLayoutImpl.content$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout(contentKey3).getZIndex(), sceneTransitionLayoutImpl.content$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout(contentKey2).getZIndex()), contentKey);
        }
        if (transition instanceof TransitionState.Transition.ChangeScene) {
            return Intrinsics.areEqual(contentKey, contentKey4);
        }
        if (!(transition instanceof ReplaceOverlaySwipeTransition ? true : transition instanceof TransitionState.Transition.ShowOrHideOverlay)) {
            throw new NoWhenBranchMatchedException();
        }
        if (Intrinsics.areEqual(contentKey, contentKey4)) {
            return true;
        }
        return Intrinsics.areEqual(contentKey, transition.getCurrentScene()) && !snapshotStateMap.containsKey(contentKey4);
    }

    public static final long size(Placeable placeable) {
        return (placeable.width << 32) | (placeable.height & 4294967295L);
    }
}

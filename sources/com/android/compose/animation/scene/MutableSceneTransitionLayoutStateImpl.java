package com.android.compose.animation.scene;

import android.util.Log;
import androidx.activity.result.ActivityResultRegistry$$ExternalSyntheticOutline0;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.SnapshotStateKt;
import com.android.compose.animation.scene.content.state.TransitionState;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.StringsKt__IndentKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.StandaloneCoroutine;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MutableSceneTransitionLayoutStateImpl implements MutableSceneTransitionLayoutState {
    public final Function1 canChangeScene;
    public final Function1 canHideOverlay;
    public final Function2 canReplaceOverlay;
    public final Function1 canShowOverlay;
    public final boolean enableInterruptions;
    public final List stateLinks;
    public final MutableState transitionStates$delegate;
    public final SceneTransitions transitions;
    public final Thread creationThread = Thread.currentThread();
    public final Set finishedTransitions = new LinkedHashSet();

    public MutableSceneTransitionLayoutStateImpl(SceneKey sceneKey, SceneTransitions sceneTransitions, Set set, Function1 function1, Function1 function12, Function1 function13, Function2 function2, List list, boolean z) {
        this.transitions = sceneTransitions;
        this.canChangeScene = function1;
        this.canShowOverlay = function12;
        this.canHideOverlay = function13;
        this.canReplaceOverlay = function2;
        this.stateLinks = list;
        this.enableInterruptions = z;
        this.transitionStates$delegate = SnapshotStateKt.mutableStateOf$default(Collections.singletonList(new TransitionState.Idle(sceneKey, set)));
    }

    public final void checkThread$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout() {
        Thread currentThread = Thread.currentThread();
        if (currentThread == this.creationThread) {
            return;
        }
        throw new IllegalStateException(StringsKt__IndentKt.trimIndent("\n                    Only the original thread that created a SceneTransitionLayoutState can mutate it\n                      Expected: " + this.creationThread.getName() + "\n                      Current: " + currentThread.getName() + "\n                ").toString());
    }

    public final void finishTransition(TransitionState.Transition transition) {
        checkThread$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout();
        if (this.finishedTransitions.contains(transition)) {
            return;
        }
        transition.freezeAndAnimateToCurrentState();
        List transitionStates$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout = getTransitionStates$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout();
        if (transitionStates$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout.contains(transition)) {
            int size = transitionStates$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout.size();
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                if (!(((TransitionState) transitionStates$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout.get(i2)) instanceof TransitionState.Transition)) {
                    throw new IllegalStateException("Check failed.");
                }
            }
            this.finishedTransitions.add(transition);
            Iterator it = transition.activeTransitionLinks.entrySet().iterator();
            if (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                if (entry.getKey() != null) {
                    throw new ClassCastException();
                }
                entry.getValue().getClass();
                throw new ClassCastException();
            }
            transition.activeTransitionLinks.clear();
            TransitionState transitionState = (TransitionState) CollectionsKt.last(transitionStates$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout);
            int size2 = transitionStates$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout.size();
            while (i < size2) {
                TransitionState transitionState2 = (TransitionState) transitionStates$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout.get(i);
                if (!CollectionsKt.contains(this.finishedTransitions, transitionState2)) {
                    break;
                }
                TypeIntrinsics.asMutableCollection(this.finishedTransitions).remove(transitionState2);
                i++;
            }
            if (i == size2) {
                if (!this.finishedTransitions.isEmpty()) {
                    throw new IllegalStateException("Check failed.");
                }
                setTransitionStates(Collections.singletonList(new TransitionState.Idle(transitionState.getCurrentScene(), transitionState.getCurrentOverlays())));
            } else if (i > 0) {
                setTransitionStates(transitionStates$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout.subList(i, size2));
            }
        }
    }

    public final SceneKey getCurrentScene() {
        return getTransitionState().getCurrentScene();
    }

    public final List getCurrentTransitions() {
        if (!(CollectionsKt.last(getTransitionStates$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout()) instanceof TransitionState.Idle)) {
            return getTransitionStates$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout();
        }
        if (getTransitionStates$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout().size() == 1) {
            return EmptyList.INSTANCE;
        }
        throw new IllegalStateException("Check failed.");
    }

    public final TransitionState getTransitionState() {
        return (TransitionState) getTransitionStates$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout().get(CollectionsKt__CollectionsKt.getLastIndex(getTransitionStates$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout()));
    }

    public final List getTransitionStates$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout() {
        return (List) ((SnapshotMutableStateImpl) this.transitionStates$delegate).getValue();
    }

    public final void hideOverlay(OverlayKey overlayKey, CoroutineScope coroutineScope) {
        checkThread$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout();
        TransitionState transitionState = getTransitionState();
        if (transitionState.getCurrentOverlays().contains(overlayKey)) {
            SceneKey currentScene = transitionState.getCurrentScene();
            if (transitionState instanceof TransitionState.Transition.ShowOrHideOverlay) {
                TransitionState.Transition.ShowOrHideOverlay showOrHideOverlay = (TransitionState.Transition.ShowOrHideOverlay) transitionState;
                if (Intrinsics.areEqual(showOrHideOverlay.overlay, overlayKey) && Intrinsics.areEqual(showOrHideOverlay.fromOrToScene, currentScene)) {
                    AnimateOverlayKt.showOrHideOverlay(coroutineScope, this, overlayKey, currentScene, false, showOrHideOverlay, overlayKey.equals(showOrHideOverlay.toContent));
                    return;
                }
            }
            AnimateOverlayKt.showOrHideOverlay(coroutineScope, this, overlayKey, currentScene, false, null, false);
        }
    }

    public final void setTransitionStates(List list) {
        ((SnapshotMutableStateImpl) this.transitionStates$delegate).setValue(list);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x00b7 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0022  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object startTransition(com.android.compose.animation.scene.content.state.TransitionState.Transition r7, boolean r8, kotlin.coroutines.jvm.internal.ContinuationImpl r9) {
        /*
            r6 = this;
            boolean r0 = r9 instanceof com.android.compose.animation.scene.MutableSceneTransitionLayoutStateImpl$startTransition$1
            if (r0 == 0) goto L13
            r0 = r9
            com.android.compose.animation.scene.MutableSceneTransitionLayoutStateImpl$startTransition$1 r0 = (com.android.compose.animation.scene.MutableSceneTransitionLayoutStateImpl$startTransition$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.compose.animation.scene.MutableSceneTransitionLayoutStateImpl$startTransition$1 r0 = new com.android.compose.animation.scene.MutableSceneTransitionLayoutStateImpl$startTransition$1
            r0.<init>(r6, r9)
        L18:
            java.lang.Object r9 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L4c
            if (r2 == r4) goto L3f
            if (r2 != r3) goto L37
            java.lang.Object r6 = r0.L$1
            r7 = r6
            com.android.compose.animation.scene.content.state.TransitionState$Transition r7 = (com.android.compose.animation.scene.content.state.TransitionState.Transition) r7
            java.lang.Object r6 = r0.L$0
            com.android.compose.animation.scene.MutableSceneTransitionLayoutStateImpl r6 = (com.android.compose.animation.scene.MutableSceneTransitionLayoutStateImpl) r6
            kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Throwable -> L34
            goto Lb8
        L34:
            r8 = move-exception
            goto Lbe
        L37:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L3f:
            java.lang.Object r6 = r0.L$1
            r7 = r6
            com.android.compose.animation.scene.content.state.TransitionState$Transition r7 = (com.android.compose.animation.scene.content.state.TransitionState.Transition) r7
            java.lang.Object r6 = r0.L$0
            com.android.compose.animation.scene.MutableSceneTransitionLayoutStateImpl r6 = (com.android.compose.animation.scene.MutableSceneTransitionLayoutStateImpl) r6
            kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Throwable -> L34
            goto Lab
        L4c:
            kotlin.ResultKt.throwOnFailure(r9)
            r6.checkThread$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout()
            com.android.compose.animation.scene.content.state.TransitionState$Transition r9 = r6.getCurrentTransition()     // Catch: java.lang.Throwable -> L34
            r6.startTransitionInternal(r7, r8)     // Catch: java.lang.Throwable -> L34
            r8 = 0
            if (r9 == 0) goto L91
            java.util.Map r2 = r9.activeTransitionLinks     // Catch: java.lang.Throwable -> L34
            java.util.Set r2 = r2.entrySet()     // Catch: java.lang.Throwable -> L34
            java.util.Iterator r2 = r2.iterator()     // Catch: java.lang.Throwable -> L34
            boolean r5 = r2.hasNext()     // Catch: java.lang.Throwable -> L34
            if (r5 != 0) goto L72
            java.util.Map r9 = r9.activeTransitionLinks     // Catch: java.lang.Throwable -> L34
            r9.clear()     // Catch: java.lang.Throwable -> L34
            goto L91
        L72:
            java.lang.Object r9 = r2.next()     // Catch: java.lang.Throwable -> L34
            java.util.Map$Entry r9 = (java.util.Map.Entry) r9     // Catch: java.lang.Throwable -> L34
            java.lang.Object r0 = r9.getKey()     // Catch: java.lang.Throwable -> L34
            if (r0 != 0) goto L8b
            java.lang.Object r9 = r9.getValue()     // Catch: java.lang.Throwable -> L34
            if (r9 != 0) goto L85
            throw r8     // Catch: java.lang.Throwable -> L34
        L85:
            java.lang.ClassCastException r8 = new java.lang.ClassCastException     // Catch: java.lang.Throwable -> L34
            r8.<init>()     // Catch: java.lang.Throwable -> L34
            throw r8     // Catch: java.lang.Throwable -> L34
        L8b:
            java.lang.ClassCastException r8 = new java.lang.ClassCastException     // Catch: java.lang.Throwable -> L34
            r8.<init>()     // Catch: java.lang.Throwable -> L34
            throw r8     // Catch: java.lang.Throwable -> L34
        L91:
            java.util.List r9 = r6.stateLinks     // Catch: java.lang.Throwable -> L34
            boolean r9 = r9.isEmpty()     // Catch: java.lang.Throwable -> L34
            if (r9 != 0) goto Lab
            com.android.compose.animation.scene.MutableSceneTransitionLayoutStateImpl$startTransition$3 r9 = new com.android.compose.animation.scene.MutableSceneTransitionLayoutStateImpl$startTransition$3     // Catch: java.lang.Throwable -> L34
            r9.<init>(r6, r7, r8)     // Catch: java.lang.Throwable -> L34
            r0.L$0 = r6     // Catch: java.lang.Throwable -> L34
            r0.L$1 = r7     // Catch: java.lang.Throwable -> L34
            r0.label = r4     // Catch: java.lang.Throwable -> L34
            java.lang.Object r8 = kotlinx.coroutines.CoroutineScopeKt.coroutineScope(r0, r9)     // Catch: java.lang.Throwable -> L34
            if (r8 != r1) goto Lab
            return r1
        Lab:
            r0.L$0 = r6     // Catch: java.lang.Throwable -> L34
            r0.L$1 = r7     // Catch: java.lang.Throwable -> L34
            r0.label = r3     // Catch: java.lang.Throwable -> L34
            java.lang.Object r8 = r7.run(r0)     // Catch: java.lang.Throwable -> L34
            if (r8 != r1) goto Lb8
            return r1
        Lb8:
            r6.finishTransition(r7)
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        Lbe:
            r6.finishTransition(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.compose.animation.scene.MutableSceneTransitionLayoutStateImpl.startTransition(com.android.compose.animation.scene.content.state.TransitionState$Transition, boolean, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public final StandaloneCoroutine startTransitionImmediately(CoroutineScope coroutineScope, TransitionState.Transition transition, boolean z) {
        return BuildersKt.launch$default(coroutineScope, null, CoroutineStart.UNDISPATCHED, new MutableSceneTransitionLayoutStateImpl$startTransitionImmediately$1(this, transition, z, null), 1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v2, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
    /* JADX WARN: Type inference failed for: r2v7, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
    public final void startTransitionInternal(TransitionState.Transition transition, boolean z) {
        TransitionState transitionState = getTransitionState();
        transition.currentSceneWhenTransitionStarted = transitionState.getCurrentScene();
        transition.currentOverlaysWhenTransitionStarted = transitionState.getCurrentOverlays();
        TransitionState.HasOverscrollProperties hasOverscrollProperties = transition instanceof TransitionState.HasOverscrollProperties ? (TransitionState.HasOverscrollProperties) transition : null;
        Orientation orientation = hasOverscrollProperties != null ? hasOverscrollProperties.getOrientation() : null;
        TransitionKey key = transition.getKey();
        SceneTransitions sceneTransitions = this.transitions;
        ContentKey contentKey = transition.fromContent;
        ContentKey contentKey2 = transition.toContent;
        transition.transformationSpec = (TransformationSpecImpl) sceneTransitions.transitionSpec$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout(contentKey, contentKey2, key).transformationSpec.invoke();
        ?? r2 = sceneTransitions.transitionSpec$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout(contentKey, contentKey2, transition.getKey()).previewTransformationSpec;
        transition.previewTransformationSpec = r2 != 0 ? (TransformationSpecImpl) r2.invoke() : null;
        if (orientation != null) {
            OverscrollSpecImpl overscrollSpec$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout = sceneTransitions.overscrollSpec$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout(contentKey, orientation);
            OverscrollSpecImpl overscrollSpec$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout2 = sceneTransitions.overscrollSpec$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout(contentKey2, orientation);
            transition.fromOverscrollSpec = overscrollSpec$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout;
            transition.toOverscrollSpec = overscrollSpec$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout2;
        } else {
            transition.fromOverscrollSpec = null;
            transition.toOverscrollSpec = null;
        }
        if (!this.enableInterruptions) {
            if (getTransitionStates$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout().size() != 1) {
                throw new IllegalStateException("Check failed.");
            }
            setTransitionStates(Collections.singletonList(transition));
            return;
        }
        TransitionState transitionState2 = (TransitionState) CollectionsKt.last(getTransitionStates$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout());
        if (transitionState2 instanceof TransitionState.Idle) {
            if (getTransitionStates$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout().size() != 1) {
                throw new IllegalStateException("Check failed.");
            }
            setTransitionStates(Collections.singletonList(transition));
            return;
        }
        if (transitionState2 instanceof TransitionState.Transition) {
            ((TransitionState.Transition) transitionState2).freezeAndAnimateToCurrentState();
            boolean z2 = getTransitionStates$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout().size() >= 100;
            if (z && !z2) {
                if (Intrinsics.areEqual(transitionState2, transition.replacedTransition)) {
                    setTransitionStates(CollectionsKt.plus(getTransitionStates$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout().subList(0, CollectionsKt__CollectionsKt.getLastIndex(getTransitionStates$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout())), transition));
                    return;
                } else {
                    setTransitionStates(CollectionsKt.plus(getTransitionStates$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout(), transition));
                    return;
                }
            }
            if (z2) {
                StringBuilder sb = new StringBuilder("Potential leak detected in SceneTransitionLayoutState!\n  Some transition(s) never called STLState.finishTransition().\n");
                sb.append("  Transitions (size=" + getTransitionStates$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout().size() + "):");
                sb.append('\n');
                List transitionStates$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout = getTransitionStates$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout();
                int size = transitionStates$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout.size();
                for (int i = 0; i < size; i++) {
                    TransitionState.Transition transition2 = (TransitionState.Transition) ((TransitionState) transitionStates$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout.get(i));
                    StringBuilder m = ActivityResultRegistry$$ExternalSyntheticOutline0.m("  [", this.finishedTransitions.contains(transition2) ? "x" : " ", "] ");
                    m.append(transition2.fromContent);
                    m.append(" => ");
                    m.append(transition2.toContent);
                    m.append(" (");
                    m.append(transition2);
                    m.append(")");
                    sb.append(m.toString());
                    sb.append('\n');
                }
                Log.wtf("SceneTransitionLayoutState", sb.toString());
            }
            while (!getCurrentTransitions().isEmpty()) {
                finishTransition((TransitionState.Transition) getTransitionStates$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout().get(0));
            }
            if (getTransitionStates$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout().size() != 1) {
                throw new IllegalStateException("Check failed.");
            }
            if (!(getTransitionStates$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout().get(0) instanceof TransitionState.Idle)) {
                throw new IllegalStateException("Check failed.");
            }
            setTransitionStates(Collections.singletonList(transition));
        }
    }

    public static /* synthetic */ void getFinishedTransitions$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout$annotations() {
    }
}

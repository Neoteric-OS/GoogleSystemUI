package com.android.compose.animation.scene;

import androidx.compose.animation.AnimatedContentKt$$ExternalSyntheticOutline0;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.FillElement;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.PersistentCompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.snapshots.SnapshotStateMap;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.ZIndexModifierKt;
import androidx.compose.ui.layout.LookaheadScope;
import androidx.compose.ui.layout.LookaheadScopeKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import com.android.compose.animation.scene.UserAction;
import com.android.compose.animation.scene.content.Content;
import com.android.compose.animation.scene.content.Overlay;
import com.android.compose.animation.scene.content.Scene;
import com.android.compose.animation.scene.content.state.TransitionState;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.EmptyList;
import kotlin.collections.builders.ListBuilder;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$BooleanRef;
import kotlin.jvm.internal.Ref$FloatRef;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SceneTransitionLayoutImpl {
    public SnapshotStateMap _overlays;
    public UserActionDistanceScopeImpl _userActionDistanceScope;
    public final ContextScope animationScope;
    public Density density;
    public final DraggableHandlerImpl horizontalDraggableHandler;
    public LayoutDirection layoutDirection;
    public LookaheadScope lookaheadScope;
    public final MutableSceneTransitionLayoutStateImpl state;
    public SwipeSourceDetector swipeSourceDetector;
    public float transitionInterceptionThreshold;
    public final DraggableHandlerImpl verticalDraggableHandler;
    public final SnapshotStateMap scenes = new SnapshotStateMap();
    public final Map elements = new LinkedHashMap();
    public final ElementStateScopeImpl elementStateScope = new ElementStateScopeImpl(this);
    public long lastSize = 0;

    public SceneTransitionLayoutImpl(MutableSceneTransitionLayoutStateImpl mutableSceneTransitionLayoutStateImpl, Density density, LayoutDirection layoutDirection, SwipeSourceDetector swipeSourceDetector, float f, Function1 function1, ContextScope contextScope) {
        this.state = mutableSceneTransitionLayoutStateImpl;
        this.density = density;
        this.layoutDirection = layoutDirection;
        this.swipeSourceDetector = swipeSourceDetector;
        this.transitionInterceptionThreshold = f;
        this.animationScope = contextScope;
        updateContents$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout(function1, this.layoutDirection);
        this.horizontalDraggableHandler = new DraggableHandlerImpl(this, Orientation.Horizontal);
        this.verticalDraggableHandler = new DraggableHandlerImpl(this, Orientation.Vertical);
        mutableSceneTransitionLayoutStateImpl.checkThread$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout();
    }

    public static final void access$BackHandler(final SceneTransitionLayoutImpl sceneTransitionLayoutImpl, Composer composer, final int i) {
        int i2;
        sceneTransitionLayoutImpl.getClass();
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-770610215);
        if ((i & 14) == 0) {
            i2 = (composerImpl.changed(sceneTransitionLayoutImpl) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i2 & 11) == 2 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            PredictiveBackHandlerKt.PredictiveBackHandler(sceneTransitionLayoutImpl, (UserActionResult) ((Map) ((SnapshotMutableStateImpl) sceneTransitionLayoutImpl.contentForUserActions$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout().userActions$delegate).getValue()).get(Back$Resolved.INSTANCE), composerImpl, i2 & 14);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.compose.animation.scene.SceneTransitionLayoutImpl$BackHandler$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    SceneTransitionLayoutImpl.access$BackHandler(SceneTransitionLayoutImpl.this, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v3, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r7v5 */
    public static final void access$Overlays(final SceneTransitionLayoutImpl sceneTransitionLayoutImpl, final BoxScope boxScope, Composer composer, final int i) {
        AbstractCollection build;
        List sortedWith;
        ?? r7;
        sceneTransitionLayoutImpl.getClass();
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(118058814);
        int i2 = (i & 14) == 0 ? (composerImpl.changed(boxScope) ? 4 : 2) | i : i;
        if ((i & 112) == 0) {
            i2 |= composerImpl.changed(sceneTransitionLayoutImpl) ? 32 : 16;
        }
        if ((i2 & 91) == 18 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            boolean z = false;
            if (sceneTransitionLayoutImpl._overlays == null) {
                sortedWith = EmptyList.INSTANCE;
            } else {
                MutableSceneTransitionLayoutStateImpl mutableSceneTransitionLayoutStateImpl = sceneTransitionLayoutImpl.state;
                List currentTransitions = mutableSceneTransitionLayoutStateImpl.getCurrentTransitions();
                if (currentTransitions.isEmpty()) {
                    Set currentOverlays = mutableSceneTransitionLayoutStateImpl.getTransitionState().getCurrentOverlays();
                    build = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(currentOverlays, 10));
                    Iterator it = currentOverlays.iterator();
                    while (it.hasNext()) {
                        build.add(sceneTransitionLayoutImpl.overlay$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout((OverlayKey) it.next()));
                    }
                } else {
                    ListBuilder listBuilder = new ListBuilder();
                    LinkedHashSet linkedHashSet = new LinkedHashSet();
                    int size = currentTransitions.size();
                    for (int i3 = 0; i3 < size; i3++) {
                        TransitionState.Transition transition = (TransitionState.Transition) currentTransitions.get(i3);
                        if (!(transition instanceof TransitionState.Transition.ChangeScene)) {
                            if (transition instanceof TransitionState.Transition.ShowOrHideOverlay) {
                                overlaysToComposeOrderedByZIndex$lambda$25$maybeAdd$22(linkedHashSet, listBuilder, sceneTransitionLayoutImpl, ((TransitionState.Transition.ShowOrHideOverlay) transition).overlay);
                            } else if (transition instanceof ReplaceOverlaySwipeTransition) {
                                ReplaceOverlaySwipeTransition replaceOverlaySwipeTransition = (ReplaceOverlaySwipeTransition) transition;
                                overlaysToComposeOrderedByZIndex$lambda$25$maybeAdd$22(linkedHashSet, listBuilder, sceneTransitionLayoutImpl, replaceOverlaySwipeTransition.fromOverlay);
                                overlaysToComposeOrderedByZIndex$lambda$25$maybeAdd$22(linkedHashSet, listBuilder, sceneTransitionLayoutImpl, replaceOverlaySwipeTransition.toOverlay);
                            }
                        }
                    }
                    Iterator it2 = ((TransitionState.Transition) CollectionsKt.last(currentTransitions)).getCurrentOverlays().iterator();
                    while (it2.hasNext()) {
                        overlaysToComposeOrderedByZIndex$lambda$25$maybeAdd$22(linkedHashSet, listBuilder, sceneTransitionLayoutImpl, (OverlayKey) it2.next());
                    }
                    build = listBuilder.build();
                }
                sortedWith = CollectionsKt.sortedWith(build, new SceneTransitionLayoutImpl$overlaysToComposeOrderedByZIndex$$inlined$sortedBy$1());
            }
            if (sortedWith.isEmpty()) {
                OpaqueKey opaqueKey2 = ComposerKt.invocation;
                RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
                if (endRestartGroup != null) {
                    endRestartGroup.block = new Function2() { // from class: com.android.compose.animation.scene.SceneTransitionLayoutImpl$Overlays$2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            ((Number) obj2).intValue();
                            SceneTransitionLayoutImpl.access$Overlays(SceneTransitionLayoutImpl.this, boxScope, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                            return Unit.INSTANCE;
                        }
                    };
                    return;
                }
                return;
            }
            int size2 = sortedWith.size();
            int i4 = 0;
            while (i4 < size2) {
                Overlay overlay = (Overlay) sortedWith.get(i4);
                final OverlayKey overlayKey = overlay.key;
                composerImpl.startMovableGroup(-297898122, overlayKey);
                Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
                Modifier zIndex = ZIndexModifierKt.zIndex(boxScope.matchParentSize(companion), overlay.getZIndex());
                MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.TopStart, z);
                OpaqueKey opaqueKey3 = ComposerKt.invocation;
                int i5 = composerImpl.compoundKeyHash;
                PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl.currentCompositionLocalScope();
                Modifier materializeModifier = ComposedModifierKt.materializeModifier(composerImpl, zIndex);
                ComposeUiNode.Companion.getClass();
                Function0 function0 = ComposeUiNode.Companion.Constructor;
                composerImpl.startReusableNode();
                List list = sortedWith;
                if (composerImpl.inserting) {
                    composerImpl.createNode(function0);
                } else {
                    composerImpl.useNode();
                }
                Updater.m259setimpl(composerImpl, maybeCachedBoxMeasurePolicy, ComposeUiNode.Companion.SetMeasurePolicy);
                Updater.m259setimpl(composerImpl, currentCompositionLocalScope, ComposeUiNode.Companion.SetResolvedCompositionLocals);
                Function2 function2 = ComposeUiNode.Companion.SetCompositeKeyHash;
                if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i5))) {
                    AnimatedContentKt$$ExternalSyntheticOutline0.m(i5, composerImpl, i5, function2);
                }
                Updater.m259setimpl(composerImpl, materializeModifier, ComposeUiNode.Companion.SetModifier);
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                composerImpl.startReplaceGroup(-1965259122);
                if (((Boolean) ((SnapshotMutableStateImpl) overlay.isModal$delegate).getValue()).booleanValue()) {
                    FillElement fillElement = SizeKt.FillWholeMaxSize;
                    composerImpl.startReplaceGroup(-1965258759);
                    Object rememberedValue = composerImpl.rememberedValue();
                    Object obj = Composer.Companion.Empty;
                    if (rememberedValue == obj) {
                        rememberedValue = InteractionSourceKt.MutableInteractionSource();
                        composerImpl.updateRememberedValue(rememberedValue);
                    }
                    MutableInteractionSource mutableInteractionSource = (MutableInteractionSource) rememberedValue;
                    composerImpl.end(false);
                    composerImpl.startReplaceGroup(-1965258637);
                    boolean changed = ((i2 & 112) == 32) | composerImpl.changed(overlayKey);
                    Object rememberedValue2 = composerImpl.rememberedValue();
                    if (changed || rememberedValue2 == obj) {
                        rememberedValue2 = new Function0() { // from class: com.android.compose.animation.scene.SceneTransitionLayoutImpl$Overlays$3$1$2$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                if (((Boolean) SceneTransitionLayoutImpl.this.state.canHideOverlay.invoke(overlayKey)).booleanValue()) {
                                    SceneTransitionLayoutImpl sceneTransitionLayoutImpl2 = SceneTransitionLayoutImpl.this;
                                    sceneTransitionLayoutImpl2.state.hideOverlay(overlayKey, sceneTransitionLayoutImpl2.animationScope);
                                }
                                return Unit.INSTANCE;
                            }
                        };
                        composerImpl.updateRememberedValue(rememberedValue2);
                    }
                    composerImpl.end(false);
                    r7 = 0;
                    BoxKt.Box(ClickableKt.m31clickableO2vRcR0$default(fillElement, mutableInteractionSource, null, false, null, (Function0) rememberedValue2, 28), composerImpl, 0);
                } else {
                    r7 = 0;
                }
                composerImpl.end(r7);
                overlay.Content(boxScopeInstance.align(companion, (Alignment) ((SnapshotMutableStateImpl) overlay.alignment$delegate).getValue()), composerImpl, r7, r7);
                composerImpl.end(true);
                composerImpl.end(r7);
                i4++;
                z = r7;
                sortedWith = list;
            }
            OpaqueKey opaqueKey4 = ComposerKt.invocation;
        }
        RecomposeScopeImpl endRestartGroup2 = composerImpl.endRestartGroup();
        if (endRestartGroup2 != null) {
            endRestartGroup2.block = new Function2() { // from class: com.android.compose.animation.scene.SceneTransitionLayoutImpl$Overlays$4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    ((Number) obj3).intValue();
                    SceneTransitionLayoutImpl.access$Overlays(SceneTransitionLayoutImpl.this, boxScope, (Composer) obj2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public static final void access$Scenes(final SceneTransitionLayoutImpl sceneTransitionLayoutImpl, Composer composer, final int i) {
        List build;
        sceneTransitionLayoutImpl.getClass();
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(407513209);
        if ((((i & 14) == 0 ? (composerImpl.changed(sceneTransitionLayoutImpl) ? 4 : 2) | i : i) & 11) == 2 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            MutableSceneTransitionLayoutStateImpl mutableSceneTransitionLayoutStateImpl = sceneTransitionLayoutImpl.state;
            List currentTransitions = mutableSceneTransitionLayoutStateImpl.getCurrentTransitions();
            if (currentTransitions.isEmpty()) {
                build = Collections.singletonList(sceneTransitionLayoutImpl.scene$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout(mutableSceneTransitionLayoutStateImpl.getTransitionState().getCurrentScene()));
            } else {
                ListBuilder listBuilder = new ListBuilder();
                LinkedHashSet linkedHashSet = new LinkedHashSet();
                int size = currentTransitions.size() - 1;
                if (size >= 0) {
                    while (true) {
                        int i2 = size - 1;
                        TransitionState.Transition transition = (TransitionState.Transition) currentTransitions.get(size);
                        if (transition instanceof TransitionState.Transition.ChangeScene) {
                            TransitionState.Transition.ChangeScene changeScene = (TransitionState.Transition.ChangeScene) transition;
                            scenesToCompose$lambda$16$maybeAdd(linkedHashSet, listBuilder, sceneTransitionLayoutImpl, changeScene.toScene);
                            scenesToCompose$lambda$16$maybeAdd(linkedHashSet, listBuilder, sceneTransitionLayoutImpl, changeScene.fromScene);
                        } else if (transition instanceof TransitionState.Transition.ShowOrHideOverlay) {
                            scenesToCompose$lambda$16$maybeAdd(linkedHashSet, listBuilder, sceneTransitionLayoutImpl, ((TransitionState.Transition.ShowOrHideOverlay) transition).fromOrToScene);
                        } else {
                            boolean z = transition instanceof ReplaceOverlaySwipeTransition;
                        }
                        if (i2 < 0) {
                            break;
                        } else {
                            size = i2;
                        }
                    }
                }
                scenesToCompose$lambda$16$maybeAdd(linkedHashSet, listBuilder, sceneTransitionLayoutImpl, ((TransitionState.Transition) CollectionsKt.last(currentTransitions)).getCurrentScene());
                build = listBuilder.build();
            }
            int size2 = build.size();
            for (int i3 = 0; i3 < size2; i3++) {
                Scene scene = (Scene) build.get(i3);
                composerImpl.startMovableGroup(-325336435, scene.key);
                scene.Content(null, composerImpl, 0, 1);
                composerImpl.end(false);
            }
            OpaqueKey opaqueKey2 = ComposerKt.invocation;
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.compose.animation.scene.SceneTransitionLayoutImpl$Scenes$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    SceneTransitionLayoutImpl.access$Scenes(SceneTransitionLayoutImpl.this, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public static final String checkUserActions$lambda$12$details(ContentKey contentKey, UserAction.Resolved resolved, UserActionResult userActionResult) {
        return "Content " + contentKey + ", action " + resolved + ", result " + userActionResult + ".";
    }

    public static final void overlaysToComposeOrderedByZIndex$lambda$25$maybeAdd$22(Set set, ListBuilder listBuilder, SceneTransitionLayoutImpl sceneTransitionLayoutImpl, OverlayKey overlayKey) {
        if (set.add(overlayKey)) {
            listBuilder.add(sceneTransitionLayoutImpl.overlay$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout(overlayKey));
        }
    }

    public static final void scenesToCompose$lambda$16$maybeAdd(Set set, ListBuilder listBuilder, SceneTransitionLayoutImpl sceneTransitionLayoutImpl, SceneKey sceneKey) {
        if (set.add(sceneKey)) {
            listBuilder.add(sceneTransitionLayoutImpl.scene$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout(sceneKey));
        }
    }

    /* JADX WARN: Type inference failed for: r0v13, types: [com.android.compose.animation.scene.SceneTransitionLayoutImpl$Content$1$1, kotlin.jvm.internal.Lambda] */
    public final void Content$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout(final Modifier modifier, final SwipeDetector swipeDetector, Composer composer, final int i) {
        int i2;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-1635317180);
        if ((i & 14) == 0) {
            i2 = (composerImpl.changed(modifier) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 112) == 0) {
            i2 |= composerImpl.changed(swipeDetector) ? 32 : 16;
        }
        if ((i & 896) == 0) {
            i2 |= composerImpl.changed(this) ? 256 : 128;
        }
        if ((i2 & 731) == 146 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            Modifier then = modifier.then(new SwipeToSceneElement(this.horizontalDraggableHandler, swipeDetector)).then(new SwipeToSceneElement(this.verticalDraggableHandler, swipeDetector)).then(new LayoutElement(this));
            MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.TopStart, false);
            int i3 = composerImpl.compoundKeyHash;
            PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl.currentCompositionLocalScope();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier(composerImpl, then);
            ComposeUiNode.Companion.getClass();
            Function0 function0 = ComposeUiNode.Companion.Constructor;
            composerImpl.startReusableNode();
            if (composerImpl.inserting) {
                composerImpl.createNode(function0);
            } else {
                composerImpl.useNode();
            }
            Updater.m259setimpl(composerImpl, maybeCachedBoxMeasurePolicy, ComposeUiNode.Companion.SetMeasurePolicy);
            Updater.m259setimpl(composerImpl, currentCompositionLocalScope, ComposeUiNode.Companion.SetResolvedCompositionLocals);
            Function2 function2 = ComposeUiNode.Companion.SetCompositeKeyHash;
            if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i3))) {
                AnimatedContentKt$$ExternalSyntheticOutline0.m(i3, composerImpl, i3, function2);
            }
            Updater.m259setimpl(composerImpl, materializeModifier, ComposeUiNode.Companion.SetModifier);
            LookaheadScopeKt.LookaheadScope(ComposableLambdaKt.rememberComposableLambda(-690920887, new Function3(this) { // from class: com.android.compose.animation.scene.SceneTransitionLayoutImpl$Content$1$1
                final /* synthetic */ BoxScope $this_Box;
                final /* synthetic */ SceneTransitionLayoutImpl this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                    this.this$0 = this;
                    this.$this_Box = boxScopeInstance;
                }

                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    Composer composer2 = (Composer) obj2;
                    ((Number) obj3).intValue();
                    OpaqueKey opaqueKey2 = ComposerKt.invocation;
                    SceneTransitionLayoutImpl sceneTransitionLayoutImpl = this.this$0;
                    sceneTransitionLayoutImpl.lookaheadScope = (LookaheadScope) obj;
                    SceneTransitionLayoutImpl.access$BackHandler(sceneTransitionLayoutImpl, composer2, 0);
                    SceneTransitionLayoutImpl.access$Scenes(this.this$0, composer2, 0);
                    SceneTransitionLayoutImpl.access$Overlays(this.this$0, this.$this_Box, composer2, 0);
                    return Unit.INSTANCE;
                }
            }, composerImpl), composerImpl, 6);
            composerImpl.end(true);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.compose.animation.scene.SceneTransitionLayoutImpl$Content$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    SceneTransitionLayoutImpl.this.Content$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout(modifier, swipeDetector, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public final Content content$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout(ContentKey contentKey) {
        if (contentKey instanceof SceneKey) {
            return scene$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout((SceneKey) contentKey);
        }
        if (contentKey instanceof OverlayKey) {
            return overlay$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout((OverlayKey) contentKey);
        }
        throw new NoWhenBranchMatchedException();
    }

    public final Content contentForUserActions$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout() {
        MutableSceneTransitionLayoutStateImpl mutableSceneTransitionLayoutStateImpl = this.state;
        Set currentOverlays = mutableSceneTransitionLayoutStateImpl.getTransitionState().getCurrentOverlays();
        if (!currentOverlays.isEmpty()) {
            Iterator it = currentOverlays.iterator();
            while (it.hasNext()) {
                overlay$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout((OverlayKey) it.next());
            }
        }
        return scene$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout(mutableSceneTransitionLayoutStateImpl.getTransitionState().getCurrentScene());
    }

    public final Map getOverlays() {
        SnapshotStateMap snapshotStateMap = this._overlays;
        if (snapshotStateMap != null) {
            return snapshotStateMap;
        }
        SnapshotStateMap snapshotStateMap2 = new SnapshotStateMap();
        this._overlays = snapshotStateMap2;
        return snapshotStateMap2;
    }

    public final Overlay overlay$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout(OverlayKey overlayKey) {
        throw new IllegalStateException(("Overlay " + overlayKey + " is not configured").toString());
    }

    public final Scene scene$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout(SceneKey sceneKey) {
        Scene scene = (Scene) this.scenes.get(sceneKey);
        if (scene != null) {
            return scene;
        }
        throw new IllegalStateException(("Scene " + sceneKey + " is not configured").toString());
    }

    /* renamed from: setContentsAndLayoutTargetSizeForTest-ozmzZPI$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout, reason: not valid java name */
    public final void m738x98b17969(long j) {
        this.lastSize = j;
        Iterator it = CollectionsKt.plus((Iterable) ((SnapshotStateMap) getOverlays()).values, this.scenes.values).iterator();
        while (it.hasNext()) {
            ((SnapshotMutableStateImpl) ((Content) it.next()).targetSize$delegate).setValue(new IntSize(j));
        }
    }

    public final void updateContents$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout(Function1 function1, LayoutDirection layoutDirection) {
        SnapshotStateMap snapshotStateMap = this.scenes;
        Set mutableSet = CollectionsKt.toMutableSet(snapshotStateMap.keys);
        Set linkedHashSet = this._overlays == null ? new LinkedHashSet() : CollectionsKt.toMutableSet(((SnapshotStateMap) getOverlays()).keys);
        function1.invoke(new SceneTransitionLayoutImpl$updateContents$1(new Ref$BooleanRef(), mutableSet, this, layoutDirection, new Ref$FloatRef(), linkedHashSet));
        Iterator it = mutableSet.iterator();
        while (it.hasNext()) {
            snapshotStateMap.remove((SceneKey) it.next());
        }
        Iterator it2 = linkedHashSet.iterator();
        while (it2.hasNext()) {
            ((SnapshotStateMap) getOverlays()).remove((OverlayKey) it2.next());
        }
    }
}

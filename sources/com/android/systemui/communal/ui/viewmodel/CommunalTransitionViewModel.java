package com.android.systemui.communal.ui.viewmodel;

import com.android.compose.animation.scene.SceneKey;
import com.android.systemui.communal.domain.interactor.CommunalInteractor;
import com.android.systemui.communal.domain.interactor.CommunalSceneInteractor;
import com.android.systemui.communal.util.CommunalColors;
import com.android.systemui.communal.util.CommunalColorsImpl;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor;
import com.android.systemui.keyguard.shared.model.Edge;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.keyguard.ui.viewmodel.DreamingToGlanceableHubTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.GlanceableHubToDreamingTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.GlanceableHubToLockscreenTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.LockscreenToGlanceableHubTransitionViewModel;
import com.android.systemui.scene.shared.model.Scenes;
import com.android.systemui.util.kotlin.BooleanFlowOperators$not$$inlined$map$1;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CommunalTransitionViewModel {
    public final CommunalSceneInteractor communalSceneInteractor;
    public final ReadonlyStateFlow isUmoOnCommunal;
    public final FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 recentsBackgroundColor;
    public final ReadonlyStateFlow showCommunalFromOccluded;
    public final CommunalTransitionViewModel$special$$inlined$map$1 showUmoFromGlanceableHubToOccluded;
    public final CommunalTransitionViewModel$special$$inlined$map$1 showUmoFromOccludedToGlanceableHub;
    public final CommunalTransitionViewModel$special$$inlined$filter$1 transitionFromOccludedEnded;

    public CommunalTransitionViewModel(CoroutineScope coroutineScope, CommunalColors communalColors, GlanceableHubToLockscreenTransitionViewModel glanceableHubToLockscreenTransitionViewModel, LockscreenToGlanceableHubTransitionViewModel lockscreenToGlanceableHubTransitionViewModel, DreamingToGlanceableHubTransitionViewModel dreamingToGlanceableHubTransitionViewModel, GlanceableHubToDreamingTransitionViewModel glanceableHubToDreamingTransitionViewModel, CommunalInteractor communalInteractor, CommunalSceneInteractor communalSceneInteractor, KeyguardTransitionInteractor keyguardTransitionInteractor) {
        final int i = 1;
        int i2 = 2;
        final int i3 = 0;
        this.communalSceneInteractor = communalSceneInteractor;
        Edge.Companion companion = Edge.Companion;
        KeyguardState keyguardState = KeyguardState.OCCLUDED;
        KeyguardState keyguardState2 = KeyguardState.GLANCEABLE_HUB;
        final CommunalTransitionViewModel$special$$inlined$filter$1 communalTransitionViewModel$special$$inlined$filter$1 = new CommunalTransitionViewModel$special$$inlined$filter$1(keyguardTransitionInteractor.transition(new Edge.StateToState(keyguardState, keyguardState2)), i3);
        Flow flow = new Flow() { // from class: com.android.systemui.communal.ui.viewmodel.CommunalTransitionViewModel$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.communal.ui.viewmodel.CommunalTransitionViewModel$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.communal.ui.viewmodel.CommunalTransitionViewModel$special$$inlined$map$1$2$1, reason: invalid class name */
                public final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector) {
                    this.$this_unsafeFlow = flowCollector;
                }

                /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r5, kotlin.coroutines.Continuation r6) {
                    /*
                        r4 = this;
                        boolean r0 = r6 instanceof com.android.systemui.communal.ui.viewmodel.CommunalTransitionViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.communal.ui.viewmodel.CommunalTransitionViewModel$special$$inlined$map$1$2$1 r0 = (com.android.systemui.communal.ui.viewmodel.CommunalTransitionViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.communal.ui.viewmodel.CommunalTransitionViewModel$special$$inlined$map$1$2$1 r0 = new com.android.systemui.communal.ui.viewmodel.CommunalTransitionViewModel$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L4c
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.keyguard.shared.model.TransitionStep r5 = (com.android.systemui.keyguard.shared.model.TransitionStep) r5
                        com.android.systemui.keyguard.shared.model.TransitionState r5 = r5.transitionState
                        com.android.systemui.keyguard.shared.model.TransitionState r6 = com.android.systemui.keyguard.shared.model.TransitionState.STARTED
                        if (r5 != r6) goto L3c
                        r5 = r3
                        goto L3d
                    L3c:
                        r5 = 0
                    L3d:
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L4c
                        return r1
                    L4c:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.communal.ui.viewmodel.CommunalTransitionViewModel$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i3) {
                    case 0:
                        Object collect = ((CommunalTransitionViewModel$special$$inlined$filter$1) communalTransitionViewModel$special$$inlined$filter$1).collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect2 = ((CommunalTransitionViewModel$special$$inlined$filter$1) communalTransitionViewModel$special$$inlined$filter$1).collect(new CommunalTransitionViewModel$special$$inlined$map$2$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        };
        SceneKey sceneKey = Scenes.Bouncer;
        final CommunalTransitionViewModel$special$$inlined$filter$1 communalTransitionViewModel$special$$inlined$filter$12 = new CommunalTransitionViewModel$special$$inlined$filter$1(keyguardTransitionInteractor.transition(Edge.Companion.create$default(keyguardState2, null, 2)), i);
        Flow flow2 = new Flow() { // from class: com.android.systemui.communal.ui.viewmodel.CommunalTransitionViewModel$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.communal.ui.viewmodel.CommunalTransitionViewModel$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.communal.ui.viewmodel.CommunalTransitionViewModel$special$$inlined$map$1$2$1, reason: invalid class name */
                public final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector) {
                    this.$this_unsafeFlow = flowCollector;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    /*
                        this = this;
                        boolean r0 = r6 instanceof com.android.systemui.communal.ui.viewmodel.CommunalTransitionViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.communal.ui.viewmodel.CommunalTransitionViewModel$special$$inlined$map$1$2$1 r0 = (com.android.systemui.communal.ui.viewmodel.CommunalTransitionViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.communal.ui.viewmodel.CommunalTransitionViewModel$special$$inlined$map$1$2$1 r0 = new com.android.systemui.communal.ui.viewmodel.CommunalTransitionViewModel$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L4c
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.keyguard.shared.model.TransitionStep r5 = (com.android.systemui.keyguard.shared.model.TransitionStep) r5
                        com.android.systemui.keyguard.shared.model.TransitionState r5 = r5.transitionState
                        com.android.systemui.keyguard.shared.model.TransitionState r6 = com.android.systemui.keyguard.shared.model.TransitionState.STARTED
                        if (r5 != r6) goto L3c
                        r5 = r3
                        goto L3d
                    L3c:
                        r5 = 0
                    L3d:
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L4c
                        return r1
                    L4c:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.communal.ui.viewmodel.CommunalTransitionViewModel$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i) {
                    case 0:
                        Object collect = ((CommunalTransitionViewModel$special$$inlined$filter$1) communalTransitionViewModel$special$$inlined$filter$12).collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect2 = ((CommunalTransitionViewModel$special$$inlined$filter$1) communalTransitionViewModel$special$$inlined$filter$12).collect(new CommunalTransitionViewModel$special$$inlined$map$2$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        };
        this.isUmoOnCommunal = FlowKt.stateIn(FlowKt.distinctUntilChanged(new BooleanFlowOperators$not$$inlined$map$1(i2, (Flow[]) CollectionsKt.toList(ArraysKt.asIterable(new Flow[]{communalSceneInteractor.isIdleOnCommunal, FlowKt.distinctUntilChanged(new BooleanFlowOperators$not$$inlined$map$1(i, (Flow[]) CollectionsKt.toList(ArraysKt.asIterable(new Flow[]{communalSceneInteractor.isCommunalVisible, new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new CommunalTransitionViewModel$isUmoOnCommunal$1(2, null), FlowKt.merge(lockscreenToGlanceableHubTransitionViewModel.showUmo, glanceableHubToLockscreenTransitionViewModel.showUmo, dreamingToGlanceableHubTransitionViewModel.showUmo, glanceableHubToDreamingTransitionViewModel.showUmo, flow, flow2))})).toArray(new Flow[0])))})).toArray(new Flow[0]))), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), Boolean.FALSE);
        ReadonlyStateFlow readonlyStateFlow = communalInteractor.showCommunalFromOccluded;
        this.showCommunalFromOccluded = readonlyStateFlow;
        this.transitionFromOccludedEnded = new CommunalTransitionViewModel$special$$inlined$filter$1(keyguardTransitionInteractor.transition(Edge.Companion.create$default(keyguardState, null, 2)), i2);
        this.recentsBackgroundColor = new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(readonlyStateFlow, ((CommunalColorsImpl) communalColors).backgroundColor, new CommunalTransitionViewModel$recentsBackgroundColor$1(3, null));
    }
}

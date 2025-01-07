package com.android.systemui.communal.domain.interactor;

import com.android.app.tracing.coroutines.CoroutineTracingKt;
import com.android.compose.animation.scene.SceneKey;
import com.android.compose.animation.scene.TransitionKey;
import com.android.systemui.communal.data.repository.CommunalSceneRepositoryImpl;
import com.android.systemui.communal.shared.log.CommunalSceneLogger;
import com.android.systemui.communal.shared.model.CommunalScenes;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.scene.domain.interactor.SceneInteractor;
import com.android.systemui.util.kotlin.BooleanFlowOperators$not$$inlined$map$1;
import com.android.systemui.util.kotlin.FlowKt;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SafeFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StartedEagerly;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CommunalSceneInteractor {
    public final StateFlowImpl _editModeState;
    public final StateFlowImpl _isLaunchingWidget;
    public final CoroutineScope applicationScope;
    public final ReadonlyStateFlow currentScene;
    public final ReadonlyStateFlow editModeState;
    public final ReadonlyStateFlow isCommunalVisible;
    public final ReadonlyStateFlow isIdleOnCommunal;
    public final Flow isIdleOnCommunalNotEditMode;
    public final ReadonlyStateFlow isLaunchingWidget;
    public final CommunalSceneLogger logger;
    public final Set onSceneAboutToChangeListener;
    public final CommunalSceneRepositoryImpl repository;
    public final ReadonlyStateFlow transitionState;

    public CommunalSceneInteractor(CoroutineScope coroutineScope, CommunalSceneRepositoryImpl communalSceneRepositoryImpl, CommunalSceneLogger communalSceneLogger, SceneInteractor sceneInteractor) {
        this.applicationScope = coroutineScope;
        this.repository = communalSceneRepositoryImpl;
        this.logger = communalSceneLogger;
        Boolean bool = Boolean.FALSE;
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(bool);
        this._isLaunchingWidget = MutableStateFlow;
        this.isLaunchingWidget = new ReadonlyStateFlow(MutableStateFlow);
        this.onSceneAboutToChangeListener = new LinkedHashSet();
        SafeFlow pairwiseBy = FlowKt.pairwiseBy(communalSceneRepositoryImpl.currentScene.getValue(), new CommunalSceneInteractor$currentScene$1(this, null), communalSceneRepositoryImpl.currentScene);
        StartedEagerly startedEagerly = SharingStarted.Companion.Eagerly;
        this.currentScene = kotlinx.coroutines.flow.FlowKt.stateIn(pairwiseBy, coroutineScope, startedEagerly, communalSceneRepositoryImpl.currentScene.getValue());
        StateFlowImpl MutableStateFlow2 = StateFlowKt.MutableStateFlow(null);
        this._editModeState = MutableStateFlow2;
        final ReadonlyStateFlow readonlyStateFlow = new ReadonlyStateFlow(MutableStateFlow2);
        this.editModeState = readonlyStateFlow;
        CommunalSceneInteractor$transitionState$1 communalSceneInteractor$transitionState$1 = new CommunalSceneInteractor$transitionState$1(this, null);
        ReadonlyStateFlow readonlyStateFlow2 = communalSceneRepositoryImpl.transitionState;
        final ReadonlyStateFlow stateIn = kotlinx.coroutines.flow.FlowKt.stateIn(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(readonlyStateFlow2, communalSceneInteractor$transitionState$1, 0), coroutineScope, startedEagerly, readonlyStateFlow2.getValue());
        this.transitionState = stateIn;
        final int i = 0;
        ReadonlyStateFlow stateIn2 = kotlinx.coroutines.flow.FlowKt.stateIn(new Flow() { // from class: com.android.systemui.communal.domain.interactor.CommunalSceneInteractor$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.communal.domain.interactor.CommunalSceneInteractor$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.communal.domain.interactor.CommunalSceneInteractor$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.communal.domain.interactor.CommunalSceneInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.communal.domain.interactor.CommunalSceneInteractor$special$$inlined$map$1$2$1 r0 = (com.android.systemui.communal.domain.interactor.CommunalSceneInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.communal.domain.interactor.CommunalSceneInteractor$special$$inlined$map$1$2$1 r0 = new com.android.systemui.communal.domain.interactor.CommunalSceneInteractor$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L56
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.compose.animation.scene.ObservableTransitionState r5 = (com.android.compose.animation.scene.ObservableTransitionState) r5
                        boolean r6 = r5 instanceof com.android.compose.animation.scene.ObservableTransitionState.Idle
                        if (r6 == 0) goto L46
                        com.android.compose.animation.scene.ObservableTransitionState$Idle r5 = (com.android.compose.animation.scene.ObservableTransitionState.Idle) r5
                        com.android.compose.animation.scene.SceneKey r5 = r5.currentScene
                        com.android.compose.animation.scene.SceneKey r6 = com.android.systemui.communal.shared.model.CommunalScenes.Communal
                        boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r6)
                        if (r5 == 0) goto L46
                        r5 = r3
                        goto L47
                    L46:
                        r5 = 0
                    L47:
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L56
                        return r1
                    L56:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.communal.domain.interactor.CommunalSceneInteractor$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i) {
                    case 0:
                        stateIn.collect(new AnonymousClass2(flowCollector), continuation);
                        break;
                    case 1:
                        stateIn.collect(new CommunalSceneInteractor$special$$inlined$map$2$2(flowCollector), continuation);
                        break;
                    default:
                        stateIn.collect(new CommunalSceneInteractor$special$$inlined$map$3$2(flowCollector), continuation);
                        break;
                }
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }
        }, coroutineScope, startedEagerly, bool);
        this.isIdleOnCommunal = stateIn2;
        final int i2 = 1;
        this.isIdleOnCommunalNotEditMode = kotlinx.coroutines.flow.FlowKt.distinctUntilChanged(new BooleanFlowOperators$not$$inlined$map$1(1, (Flow[]) CollectionsKt.toList(ArraysKt.asIterable(new Flow[]{stateIn2, new Flow() { // from class: com.android.systemui.communal.domain.interactor.CommunalSceneInteractor$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.communal.domain.interactor.CommunalSceneInteractor$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.communal.domain.interactor.CommunalSceneInteractor$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.communal.domain.interactor.CommunalSceneInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.communal.domain.interactor.CommunalSceneInteractor$special$$inlined$map$1$2$1 r0 = (com.android.systemui.communal.domain.interactor.CommunalSceneInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.communal.domain.interactor.CommunalSceneInteractor$special$$inlined$map$1$2$1 r0 = new com.android.systemui.communal.domain.interactor.CommunalSceneInteractor$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L56
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.compose.animation.scene.ObservableTransitionState r5 = (com.android.compose.animation.scene.ObservableTransitionState) r5
                        boolean r6 = r5 instanceof com.android.compose.animation.scene.ObservableTransitionState.Idle
                        if (r6 == 0) goto L46
                        com.android.compose.animation.scene.ObservableTransitionState$Idle r5 = (com.android.compose.animation.scene.ObservableTransitionState.Idle) r5
                        com.android.compose.animation.scene.SceneKey r5 = r5.currentScene
                        com.android.compose.animation.scene.SceneKey r6 = com.android.systemui.communal.shared.model.CommunalScenes.Communal
                        boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r6)
                        if (r5 == 0) goto L46
                        r5 = r3
                        goto L47
                    L46:
                        r5 = 0
                    L47:
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L56
                        return r1
                    L56:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.communal.domain.interactor.CommunalSceneInteractor$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i2) {
                    case 0:
                        readonlyStateFlow.collect(new AnonymousClass2(flowCollector), continuation);
                        break;
                    case 1:
                        readonlyStateFlow.collect(new CommunalSceneInteractor$special$$inlined$map$2$2(flowCollector), continuation);
                        break;
                    default:
                        readonlyStateFlow.collect(new CommunalSceneInteractor$special$$inlined$map$3$2(flowCollector), continuation);
                        break;
                }
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }
        }})).toArray(new Flow[0])));
        final int i3 = 2;
        this.isCommunalVisible = kotlinx.coroutines.flow.FlowKt.stateIn(new Flow() { // from class: com.android.systemui.communal.domain.interactor.CommunalSceneInteractor$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.communal.domain.interactor.CommunalSceneInteractor$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.communal.domain.interactor.CommunalSceneInteractor$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.communal.domain.interactor.CommunalSceneInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.communal.domain.interactor.CommunalSceneInteractor$special$$inlined$map$1$2$1 r0 = (com.android.systemui.communal.domain.interactor.CommunalSceneInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.communal.domain.interactor.CommunalSceneInteractor$special$$inlined$map$1$2$1 r0 = new com.android.systemui.communal.domain.interactor.CommunalSceneInteractor$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L56
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.compose.animation.scene.ObservableTransitionState r5 = (com.android.compose.animation.scene.ObservableTransitionState) r5
                        boolean r6 = r5 instanceof com.android.compose.animation.scene.ObservableTransitionState.Idle
                        if (r6 == 0) goto L46
                        com.android.compose.animation.scene.ObservableTransitionState$Idle r5 = (com.android.compose.animation.scene.ObservableTransitionState.Idle) r5
                        com.android.compose.animation.scene.SceneKey r5 = r5.currentScene
                        com.android.compose.animation.scene.SceneKey r6 = com.android.systemui.communal.shared.model.CommunalScenes.Communal
                        boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r6)
                        if (r5 == 0) goto L46
                        r5 = r3
                        goto L47
                    L46:
                        r5 = 0
                    L47:
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L56
                        return r1
                    L56:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.communal.domain.interactor.CommunalSceneInteractor$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i3) {
                    case 0:
                        stateIn.collect(new AnonymousClass2(flowCollector), continuation);
                        break;
                    case 1:
                        stateIn.collect(new CommunalSceneInteractor$special$$inlined$map$2$2(flowCollector), continuation);
                        break;
                    default:
                        stateIn.collect(new CommunalSceneInteractor$special$$inlined$map$3$2(flowCollector), continuation);
                        break;
                }
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }
        }, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), bool);
    }

    public static final void access$notifyListeners(CommunalSceneInteractor communalSceneInteractor, SceneKey sceneKey, KeyguardState keyguardState) {
        for (CommunalSceneTransitionInteractor communalSceneTransitionInteractor : communalSceneInteractor.onSceneAboutToChangeListener) {
            communalSceneTransitionInteractor.getClass();
            if (sceneKey.equals(CommunalScenes.Blank) && keyguardState != null) {
                StateFlowImpl stateFlowImpl = communalSceneTransitionInteractor.repository.nextLockscreenTargetState;
                stateFlowImpl.getClass();
                stateFlowImpl.updateState(null, keyguardState);
            }
        }
    }

    public static /* synthetic */ void changeScene$default(CommunalSceneInteractor communalSceneInteractor, SceneKey sceneKey, String str, TransitionKey transitionKey, int i) {
        if ((i & 4) != 0) {
            transitionKey = null;
        }
        communalSceneInteractor.changeScene(sceneKey, str, transitionKey, null);
    }

    public static void snapToScene$default(CommunalSceneInteractor communalSceneInteractor, SceneKey sceneKey, String str, int i) {
        KeyguardState keyguardState = KeyguardState.DOZING;
        long j = (i & 4) != 0 ? 0L : 500L;
        KeyguardState keyguardState2 = (i & 8) != 0 ? null : keyguardState;
        communalSceneInteractor.getClass();
        CoroutineTracingKt.launch$default(communalSceneInteractor.applicationScope, null, new CommunalSceneInteractor$snapToScene$1(j, communalSceneInteractor, sceneKey, str, keyguardState2, null), 6);
    }

    public final void changeScene(SceneKey sceneKey, String str, TransitionKey transitionKey, KeyguardState keyguardState) {
        CoroutineTracingKt.launch$default(this.applicationScope, null, new CommunalSceneInteractor$changeScene$1(this, sceneKey, str, keyguardState, transitionKey, null), 6);
    }

    public final void setIsLaunchingWidget(boolean z) {
        Boolean valueOf = Boolean.valueOf(z);
        StateFlowImpl stateFlowImpl = this._isLaunchingWidget;
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, valueOf);
    }
}

package com.android.systemui.communal.log;

import com.android.internal.logging.UiEventLogger;
import com.android.systemui.CoreStartable;
import com.android.systemui.communal.domain.interactor.CommunalSceneInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__LimitKt$drop$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CommunalLoggerStartable implements CoreStartable {
    public final CoroutineScope backgroundScope;
    public final CommunalSceneInteractor communalSceneInteractor;
    public final KeyguardInteractor keyguardInteractor;
    public final UiEventLogger uiEventLogger;

    public CommunalLoggerStartable(CoroutineScope coroutineScope, CommunalSceneInteractor communalSceneInteractor, KeyguardInteractor keyguardInteractor, UiEventLogger uiEventLogger) {
        this.backgroundScope = coroutineScope;
        this.communalSceneInteractor = communalSceneInteractor;
        this.keyguardInteractor = keyguardInteractor;
        this.uiEventLogger = uiEventLogger;
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        CommunalSceneInteractor communalSceneInteractor = this.communalSceneInteractor;
        final ReadonlyStateFlow readonlyStateFlow = communalSceneInteractor.transitionState;
        FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1 flowKt__TransformKt$onEach$$inlined$unsafeTransform$1 = new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(new FlowKt__LimitKt$drop$$inlined$unsafeFlow$1(FlowKt.distinctUntilChanged(new FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1(new Flow() { // from class: com.android.systemui.communal.log.CommunalLoggerStartable$start$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.communal.log.CommunalLoggerStartable$start$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.communal.log.CommunalLoggerStartable$start$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.communal.log.CommunalLoggerStartable$start$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.communal.log.CommunalLoggerStartable$start$$inlined$map$1$2$1 r0 = (com.android.systemui.communal.log.CommunalLoggerStartable$start$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.communal.log.CommunalLoggerStartable$start$$inlined$map$1$2$1 r0 = new com.android.systemui.communal.log.CommunalLoggerStartable$start$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L52
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.compose.animation.scene.ObservableTransitionState r5 = (com.android.compose.animation.scene.ObservableTransitionState) r5
                        boolean r6 = com.android.systemui.communal.log.CommunalLoggerStartableKt.access$isOnCommunal(r5)
                        if (r6 == 0) goto L3d
                        com.android.systemui.communal.shared.log.CommunalUiEvent r5 = com.android.systemui.communal.shared.log.CommunalUiEvent.COMMUNAL_HUB_SHOWN
                        goto L47
                    L3d:
                        boolean r5 = com.android.systemui.communal.log.CommunalLoggerStartableKt.access$isNotOnCommunal(r5)
                        if (r5 == 0) goto L46
                        com.android.systemui.communal.shared.log.CommunalUiEvent r5 = com.android.systemui.communal.shared.log.CommunalUiEvent.COMMUNAL_HUB_GONE
                        goto L47
                    L46:
                        r5 = 0
                    L47:
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L52
                        return r1
                    L52:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.communal.log.CommunalLoggerStartable$start$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                ReadonlyStateFlow.this.collect(new AnonymousClass2(flowCollector), continuation);
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }
        }))), new CommunalLoggerStartable$start$2(this, null), 0);
        CoroutineScope coroutineScope = this.backgroundScope;
        FlowKt.launchIn(flowKt__TransformKt$onEach$$inlined$unsafeTransform$1, coroutineScope);
        FlowKt.launchIn(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(FlowKt.distinctUntilChanged(new FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(com.android.systemui.util.kotlin.FlowKt.pairwise(communalSceneInteractor.transitionState), this.keyguardInteractor.isDreamingWithOverlay, new CommunalLoggerStartable$start$3(3, null)))), new CommunalLoggerStartable$start$4(this, null), 0), coroutineScope);
    }
}

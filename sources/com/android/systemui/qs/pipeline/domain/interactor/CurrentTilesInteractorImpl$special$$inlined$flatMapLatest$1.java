package com.android.systemui.qs.pipeline.domain.interactor;

import com.android.systemui.qs.pipeline.data.repository.InstalledTilesComponentRepositoryImpl;
import com.android.systemui.qs.pipeline.data.repository.TileSpecSettingsRepository;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CurrentTilesInteractorImpl$special$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    int I$0;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ CurrentTilesInteractorImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CurrentTilesInteractorImpl$special$$inlined$flatMapLatest$1(CurrentTilesInteractorImpl currentTilesInteractorImpl, Continuation continuation) {
        super(3, continuation);
        this.this$0 = currentTilesInteractorImpl;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        CurrentTilesInteractorImpl$special$$inlined$flatMapLatest$1 currentTilesInteractorImpl$special$$inlined$flatMapLatest$1 = new CurrentTilesInteractorImpl$special$$inlined$flatMapLatest$1(this.this$0, (Continuation) obj3);
        currentTilesInteractorImpl$special$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        currentTilesInteractorImpl$special$$inlined$flatMapLatest$1.L$1 = obj2;
        return currentTilesInteractorImpl$special$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        int intValue;
        FlowCollector flowCollector;
        final StateFlow forUserLocked;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector2 = (FlowCollector) this.L$0;
            intValue = ((Number) this.L$1).intValue();
            TileSpecSettingsRepository tileSpecSettingsRepository = this.this$0.tileSpecRepository;
            this.L$0 = flowCollector2;
            this.I$0 = intValue;
            this.label = 1;
            Object tilesSpecs = tileSpecSettingsRepository.tilesSpecs(intValue, this);
            if (tilesSpecs == coroutineSingletons) {
                return coroutineSingletons;
            }
            flowCollector = flowCollector2;
            obj = tilesSpecs;
        } else {
            if (i != 1) {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            }
            intValue = this.I$0;
            flowCollector = (FlowCollector) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        Flow flow = (Flow) obj;
        InstalledTilesComponentRepositoryImpl installedTilesComponentRepositoryImpl = (InstalledTilesComponentRepositoryImpl) this.this$0.installedTilesComponentRepository;
        synchronized (installedTilesComponentRepositoryImpl.userMap) {
            forUserLocked = installedTilesComponentRepositoryImpl.getForUserLocked(intValue);
        }
        FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 flowKt__ZipKt$combine$$inlined$unsafeFlow$1 = new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(flow, new Flow() { // from class: com.android.systemui.qs.pipeline.data.repository.InstalledTilesComponentRepositoryImpl$getInstalledTilesComponents$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.qs.pipeline.data.repository.InstalledTilesComponentRepositoryImpl$getInstalledTilesComponents$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.qs.pipeline.data.repository.InstalledTilesComponentRepositoryImpl$getInstalledTilesComponents$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.qs.pipeline.data.repository.InstalledTilesComponentRepositoryImpl$getInstalledTilesComponents$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.qs.pipeline.data.repository.InstalledTilesComponentRepositoryImpl$getInstalledTilesComponents$$inlined$map$1$2$1 r0 = (com.android.systemui.qs.pipeline.data.repository.InstalledTilesComponentRepositoryImpl$getInstalledTilesComponents$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.qs.pipeline.data.repository.InstalledTilesComponentRepositoryImpl$getInstalledTilesComponents$$inlined$map$1$2$1 r0 = new com.android.systemui.qs.pipeline.data.repository.InstalledTilesComponentRepositoryImpl$getInstalledTilesComponents$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L5c
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        java.util.List r5 = (java.util.List) r5
                        java.util.LinkedHashSet r6 = new java.util.LinkedHashSet
                        r6.<init>()
                        java.util.Iterator r5 = r5.iterator()
                    L3d:
                        boolean r2 = r5.hasNext()
                        if (r2 == 0) goto L51
                        java.lang.Object r2 = r5.next()
                        android.content.pm.ServiceInfo r2 = (android.content.pm.ServiceInfo) r2
                        android.content.ComponentName r2 = r2.getComponentName()
                        r6.add(r2)
                        goto L3d
                    L51:
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r6, r0)
                        if (r4 != r1) goto L5c
                        return r1
                    L5c:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.pipeline.data.repository.InstalledTilesComponentRepositoryImpl$getInstalledTilesComponents$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector3, Continuation continuation) {
                Object collect = StateFlow.this.collect(new AnonymousClass2(flowCollector3), continuation);
                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
            }
        }, new CurrentTilesInteractorImpl$userAndTiles$1$1(intValue, null));
        this.L$0 = null;
        this.label = 2;
        if (FlowKt.emitAll(flowCollector, flowKt__ZipKt$combine$$inlined$unsafeFlow$1, this) == coroutineSingletons) {
            return coroutineSingletons;
        }
        return Unit.INSTANCE;
    }
}

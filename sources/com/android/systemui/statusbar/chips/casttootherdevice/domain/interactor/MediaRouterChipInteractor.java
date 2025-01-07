package com.android.systemui.statusbar.chips.casttootherdevice.domain.interactor;

import com.android.systemui.log.LogBuffer;
import com.android.systemui.mediarouter.data.repository.MediaRouterRepositoryImpl;
import com.android.systemui.statusbar.chips.casttootherdevice.domain.model.MediaRouterCastModel;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MediaRouterChipInteractor {
    public final ReadonlyStateFlow activeCastDevice;
    public final LogBuffer logger;
    public final ReadonlyStateFlow mediaRouterCastingState;
    public final MediaRouterRepositoryImpl mediaRouterRepository;

    public MediaRouterChipInteractor(CoroutineScope coroutineScope, MediaRouterRepositoryImpl mediaRouterRepositoryImpl, LogBuffer logBuffer) {
        this.mediaRouterRepository = mediaRouterRepositoryImpl;
        this.logger = logBuffer;
        final ReadonlyStateFlow readonlyStateFlow = mediaRouterRepositoryImpl.castDevices;
        final ReadonlyStateFlow stateIn = FlowKt.stateIn(new Flow() { // from class: com.android.systemui.statusbar.chips.casttootherdevice.domain.interactor.MediaRouterChipInteractor$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.chips.casttootherdevice.domain.interactor.MediaRouterChipInteractor$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.chips.casttootherdevice.domain.interactor.MediaRouterChipInteractor$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.statusbar.chips.casttootherdevice.domain.interactor.MediaRouterChipInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.statusbar.chips.casttootherdevice.domain.interactor.MediaRouterChipInteractor$special$$inlined$map$1$2$1 r0 = (com.android.systemui.statusbar.chips.casttootherdevice.domain.interactor.MediaRouterChipInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.chips.casttootherdevice.domain.interactor.MediaRouterChipInteractor$special$$inlined$map$1$2$1 r0 = new com.android.systemui.statusbar.chips.casttootherdevice.domain.interactor.MediaRouterChipInteractor$special$$inlined$map$1$2$1
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
                        java.util.List r5 = (java.util.List) r5
                        java.util.Iterator r5 = r5.iterator()
                    L38:
                        boolean r6 = r5.hasNext()
                        if (r6 == 0) goto L4a
                        java.lang.Object r6 = r5.next()
                        r2 = r6
                        com.android.systemui.statusbar.policy.CastDevice r2 = (com.android.systemui.statusbar.policy.CastDevice) r2
                        boolean r2 = r2.isCasting
                        if (r2 == 0) goto L38
                        goto L4b
                    L4a:
                        r6 = 0
                    L4b:
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r6, r0)
                        if (r4 != r1) goto L56
                        return r1
                    L56:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.chips.casttootherdevice.domain.interactor.MediaRouterChipInteractor$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object collect = StateFlow.this.collect(new AnonymousClass2(flowCollector), continuation);
                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
            }
        }, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), null);
        this.activeCastDevice = stateIn;
        this.mediaRouterCastingState = FlowKt.stateIn(new Flow() { // from class: com.android.systemui.statusbar.chips.casttootherdevice.domain.interactor.MediaRouterChipInteractor$special$$inlined$map$2

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.chips.casttootherdevice.domain.interactor.MediaRouterChipInteractor$special$$inlined$map$2$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ MediaRouterChipInteractor this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.chips.casttootherdevice.domain.interactor.MediaRouterChipInteractor$special$$inlined$map$2$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, MediaRouterChipInteractor mediaRouterChipInteractor) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = mediaRouterChipInteractor;
                }

                /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r8, kotlin.coroutines.Continuation r9) {
                    /*
                        r7 = this;
                        boolean r0 = r9 instanceof com.android.systemui.statusbar.chips.casttootherdevice.domain.interactor.MediaRouterChipInteractor$special$$inlined$map$2.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r9
                        com.android.systemui.statusbar.chips.casttootherdevice.domain.interactor.MediaRouterChipInteractor$special$$inlined$map$2$2$1 r0 = (com.android.systemui.statusbar.chips.casttootherdevice.domain.interactor.MediaRouterChipInteractor$special$$inlined$map$2.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.chips.casttootherdevice.domain.interactor.MediaRouterChipInteractor$special$$inlined$map$2$2$1 r0 = new com.android.systemui.statusbar.chips.casttootherdevice.domain.interactor.MediaRouterChipInteractor$special$$inlined$map$2$2$1
                        r0.<init>(r9)
                    L18:
                        java.lang.Object r9 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r9)
                        goto L6f
                    L27:
                        java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                        java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
                        r7.<init>(r8)
                        throw r7
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r9)
                        com.android.systemui.statusbar.policy.CastDevice r8 = (com.android.systemui.statusbar.policy.CastDevice) r8
                        r9 = 0
                        java.lang.String r2 = "MediaRouter"
                        com.android.systemui.statusbar.chips.casttootherdevice.domain.interactor.MediaRouterChipInteractor r4 = r7.this$0
                        if (r8 == 0) goto L55
                        com.android.systemui.log.LogBuffer r4 = r4.logger
                        com.android.systemui.log.core.LogLevel r5 = com.android.systemui.log.core.LogLevel.INFO
                        com.android.systemui.statusbar.chips.casttootherdevice.domain.interactor.MediaRouterChipInteractor$mediaRouterCastingState$1$2 r6 = com.android.systemui.statusbar.chips.casttootherdevice.domain.interactor.MediaRouterChipInteractor$mediaRouterCastingState$1$2.INSTANCE
                        com.android.systemui.log.core.LogMessage r9 = r4.obtain(r2, r5, r6, r9)
                        r2 = r9
                        com.android.systemui.log.LogMessageImpl r2 = (com.android.systemui.log.LogMessageImpl) r2
                        java.lang.String r8 = r8.name
                        r2.str1 = r8
                        r4.commit(r9)
                        com.android.systemui.statusbar.chips.casttootherdevice.domain.model.MediaRouterCastModel$Casting r9 = new com.android.systemui.statusbar.chips.casttootherdevice.domain.model.MediaRouterCastModel$Casting
                        r9.<init>(r8)
                        goto L64
                    L55:
                        com.android.systemui.log.LogBuffer r8 = r4.logger
                        com.android.systemui.log.core.LogLevel r4 = com.android.systemui.log.core.LogLevel.INFO
                        com.android.systemui.statusbar.chips.casttootherdevice.domain.interactor.MediaRouterChipInteractor$mediaRouterCastingState$1$4 r5 = com.android.systemui.statusbar.chips.casttootherdevice.domain.interactor.MediaRouterChipInteractor$mediaRouterCastingState$1$4.INSTANCE
                        com.android.systemui.log.core.LogMessage r9 = r8.obtain(r2, r4, r5, r9)
                        r8.commit(r9)
                        com.android.systemui.statusbar.chips.casttootherdevice.domain.model.MediaRouterCastModel$DoingNothing r9 = com.android.systemui.statusbar.chips.casttootherdevice.domain.model.MediaRouterCastModel.DoingNothing.INSTANCE
                    L64:
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r7 = r7.$this_unsafeFlow
                        java.lang.Object r7 = r7.emit(r9, r0)
                        if (r7 != r1) goto L6f
                        return r1
                    L6f:
                        kotlin.Unit r7 = kotlin.Unit.INSTANCE
                        return r7
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.chips.casttootherdevice.domain.interactor.MediaRouterChipInteractor$special$$inlined$map$2.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                ReadonlyStateFlow.this.collect(new AnonymousClass2(flowCollector, this), continuation);
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }
        }, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), MediaRouterCastModel.DoingNothing.INSTANCE);
    }
}

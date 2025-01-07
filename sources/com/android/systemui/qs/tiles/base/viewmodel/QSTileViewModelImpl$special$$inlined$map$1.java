package com.android.systemui.qs.tiles.base.viewmodel;

import android.os.UserHandle;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.flow.ChannelFlowBuilder;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.ReadonlySharedFlow;
import kotlinx.coroutines.flow.internal.FusibleFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QSTileViewModelImpl$special$$inlined$map$1 implements Flow {
    public final /* synthetic */ int $r8$classId = 1;
    public final /* synthetic */ FusibleFlow $this_unsafeTransform$inlined;
    public final /* synthetic */ Object $uiBackgroundDispatcher$inlined;
    public final /* synthetic */ QSTileViewModelImpl this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl$special$$inlined$map$1$2, reason: invalid class name */
    public final class AnonymousClass2 implements FlowCollector {
        public final /* synthetic */ FlowCollector $this_unsafeFlow;
        public final /* synthetic */ CoroutineDispatcher $uiBackgroundDispatcher$inlined;
        public final /* synthetic */ QSTileViewModelImpl this$0;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl$special$$inlined$map$1$2$1, reason: invalid class name */
        public final class AnonymousClass1 extends ContinuationImpl {
            Object L$0;
            Object L$1;
            Object L$2;
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

        public AnonymousClass2(FlowCollector flowCollector, CoroutineDispatcher coroutineDispatcher, QSTileViewModelImpl qSTileViewModelImpl) {
            this.$this_unsafeFlow = flowCollector;
            this.$uiBackgroundDispatcher$inlined = coroutineDispatcher;
            this.this$0 = qSTileViewModelImpl;
        }

        /* JADX WARN: Removed duplicated region for block: B:19:0x007d A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:20:0x0044  */
        /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
        @Override // kotlinx.coroutines.flow.FlowCollector
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object emit(java.lang.Object r9, kotlin.coroutines.Continuation r10) {
            /*
                r8 = this;
                boolean r0 = r10 instanceof com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                if (r0 == 0) goto L13
                r0 = r10
                com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl$special$$inlined$map$1$2$1 r0 = (com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L13
                int r1 = r1 - r2
                r0.label = r1
                goto L18
            L13:
                com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl$special$$inlined$map$1$2$1 r0 = new com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl$special$$inlined$map$1$2$1
                r0.<init>(r10)
            L18:
                java.lang.Object r10 = r0.result
                kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                int r2 = r0.label
                r3 = 2
                r4 = 1
                r5 = 0
                if (r2 == 0) goto L44
                if (r2 == r4) goto L33
                if (r2 != r3) goto L2b
                kotlin.ResultKt.throwOnFailure(r10)
                goto L7e
            L2b:
                java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
                r8.<init>(r9)
                throw r8
            L33:
                java.lang.Object r9 = r0.L$2
                java.lang.Object r8 = r0.L$1
                kotlinx.coroutines.flow.FlowCollector r8 = (kotlinx.coroutines.flow.FlowCollector) r8
                java.lang.Object r2 = r0.L$0
                com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl$special$$inlined$map$1$2 r2 = (com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl$special$$inlined$map$1.AnonymousClass2) r2
                kotlin.ResultKt.throwOnFailure(r10)
                r7 = r2
                r2 = r8
                r8 = r7
                goto L61
            L44:
                kotlin.ResultKt.throwOnFailure(r10)
                com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl$state$1$1 r10 = new com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl$state$1$1
                com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl r2 = r8.this$0
                r10.<init>(r2, r9, r5)
                r0.L$0 = r8
                kotlinx.coroutines.flow.FlowCollector r2 = r8.$this_unsafeFlow
                r0.L$1 = r2
                r0.L$2 = r9
                r0.label = r4
                kotlinx.coroutines.CoroutineDispatcher r4 = r8.$uiBackgroundDispatcher$inlined
                java.lang.Object r10 = kotlinx.coroutines.BuildersKt.withContext(r4, r10, r0)
                if (r10 != r1) goto L61
                return r1
            L61:
                r4 = r10
                com.android.systemui.qs.tiles.viewmodel.QSTileState r4 = (com.android.systemui.qs.tiles.viewmodel.QSTileState) r4
                com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl r8 = r8.this$0
                com.android.systemui.qs.tiles.base.logging.QSTileLogger r6 = r8.qsTileLogger
                com.android.systemui.qs.tiles.viewmodel.QSTileConfig r8 = r8.config
                com.android.systemui.qs.pipeline.shared.TileSpec r8 = r8.tileSpec
                r6.logStateUpdate(r8, r4, r9)
                r0.L$0 = r5
                r0.L$1 = r5
                r0.L$2 = r5
                r0.label = r3
                java.lang.Object r8 = r2.emit(r10, r0)
                if (r8 != r1) goto L7e
                return r1
            L7e:
                kotlin.Unit r8 = kotlin.Unit.INSTANCE
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    public QSTileViewModelImpl$special$$inlined$map$1(ChannelFlowBuilder channelFlowBuilder, QSTileViewModelImpl qSTileViewModelImpl, UserHandle userHandle) {
        this.$this_unsafeTransform$inlined = channelFlowBuilder;
        this.this$0 = qSTileViewModelImpl;
        this.$uiBackgroundDispatcher$inlined = userHandle;
    }

    @Override // kotlinx.coroutines.flow.Flow
    public final Object collect(FlowCollector flowCollector, Continuation continuation) {
        switch (this.$r8$classId) {
            case 0:
                Object collect = ((ReadonlySharedFlow) this.$this_unsafeTransform$inlined).$$delegate_0.collect(new AnonymousClass2(flowCollector, (CoroutineDispatcher) this.$uiBackgroundDispatcher$inlined, this.this$0), continuation);
                if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                    break;
                }
                break;
            default:
                Object collect2 = ((ChannelFlowBuilder) this.$this_unsafeTransform$inlined).collect(new QSTileViewModelImpl$userInputFlow$$inlined$mapNotNull$1$2(flowCollector, this.this$0, (UserHandle) this.$uiBackgroundDispatcher$inlined), continuation);
                if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                    break;
                }
                break;
        }
        return Unit.INSTANCE;
    }

    public QSTileViewModelImpl$special$$inlined$map$1(ReadonlySharedFlow readonlySharedFlow, CoroutineDispatcher coroutineDispatcher, QSTileViewModelImpl qSTileViewModelImpl) {
        this.$this_unsafeTransform$inlined = readonlySharedFlow;
        this.$uiBackgroundDispatcher$inlined = coroutineDispatcher;
        this.this$0 = qSTileViewModelImpl;
    }
}

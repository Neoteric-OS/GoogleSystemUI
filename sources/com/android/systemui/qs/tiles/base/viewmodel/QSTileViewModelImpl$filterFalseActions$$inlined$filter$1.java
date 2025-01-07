package com.android.systemui.qs.tiles.base.viewmodel;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.SharedFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QSTileViewModelImpl$filterFalseActions$$inlined$filter$1 implements Flow {
    public final /* synthetic */ SharedFlowImpl $this_unsafeTransform$inlined;
    public final /* synthetic */ QSTileViewModelImpl this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl$filterFalseActions$$inlined$filter$1$2, reason: invalid class name */
    public final class AnonymousClass2 implements FlowCollector {
        public final /* synthetic */ FlowCollector $this_unsafeFlow;
        public final /* synthetic */ QSTileViewModelImpl this$0;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl$filterFalseActions$$inlined$filter$1$2$1, reason: invalid class name */
        public final class AnonymousClass1 extends ContinuationImpl {
            Object L$0;
            Object L$1;
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

        public AnonymousClass2(FlowCollector flowCollector, QSTileViewModelImpl qSTileViewModelImpl) {
            this.$this_unsafeFlow = flowCollector;
            this.this$0 = qSTileViewModelImpl;
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
        /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
        @Override // kotlinx.coroutines.flow.FlowCollector
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object emit(java.lang.Object r7, kotlin.coroutines.Continuation r8) {
            /*
                r6 = this;
                boolean r0 = r8 instanceof com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl$filterFalseActions$$inlined$filter$1.AnonymousClass2.AnonymousClass1
                if (r0 == 0) goto L13
                r0 = r8
                com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl$filterFalseActions$$inlined$filter$1$2$1 r0 = (com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl$filterFalseActions$$inlined$filter$1.AnonymousClass2.AnonymousClass1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L13
                int r1 = r1 - r2
                r0.label = r1
                goto L18
            L13:
                com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl$filterFalseActions$$inlined$filter$1$2$1 r0 = new com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl$filterFalseActions$$inlined$filter$1$2$1
                r0.<init>(r8)
            L18:
                java.lang.Object r8 = r0.result
                kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                int r2 = r0.label
                r3 = 1
                if (r2 == 0) goto L2f
                if (r2 != r3) goto L27
                kotlin.ResultKt.throwOnFailure(r8)
                goto L6a
            L27:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r7)
                throw r6
            L2f:
                kotlin.ResultKt.throwOnFailure(r8)
                r8 = r7
                com.android.systemui.qs.tiles.viewmodel.QSTileUserAction r8 = (com.android.systemui.qs.tiles.viewmodel.QSTileUserAction) r8
                boolean r2 = r8 instanceof com.android.systemui.qs.tiles.viewmodel.QSTileUserAction.Click
                if (r2 == 0) goto L3b
                r2 = r3
                goto L3d
            L3b:
                boolean r2 = r8 instanceof com.android.systemui.qs.tiles.viewmodel.QSTileUserAction.ToggleClick
            L3d:
                com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl r4 = r6.this$0
                if (r2 == 0) goto L48
                com.android.systemui.plugins.FalsingManager r2 = r4.falsingManager
                boolean r2 = r2.isFalseTap(r3)
                goto L52
            L48:
                boolean r2 = r8 instanceof com.android.systemui.qs.tiles.viewmodel.QSTileUserAction.LongClick
                if (r2 == 0) goto L6d
                com.android.systemui.plugins.FalsingManager r2 = r4.falsingManager
                boolean r2 = r2.isFalseLongTap(r3)
            L52:
                if (r2 == 0) goto L5d
                com.android.systemui.qs.tiles.base.logging.QSTileLogger r5 = r4.qsTileLogger
                com.android.systemui.qs.tiles.viewmodel.QSTileConfig r4 = r4.config
                com.android.systemui.qs.pipeline.shared.TileSpec r4 = r4.tileSpec
                r5.logUserActionRejectedByFalsing(r8, r4)
            L5d:
                if (r2 != 0) goto L6a
                r0.label = r3
                kotlinx.coroutines.flow.FlowCollector r6 = r6.$this_unsafeFlow
                java.lang.Object r6 = r6.emit(r7, r0)
                if (r6 != r1) goto L6a
                return r1
            L6a:
                kotlin.Unit r6 = kotlin.Unit.INSTANCE
                return r6
            L6d:
                kotlin.NoWhenBranchMatchedException r6 = new kotlin.NoWhenBranchMatchedException
                r6.<init>()
                throw r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl$filterFalseActions$$inlined$filter$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    public QSTileViewModelImpl$filterFalseActions$$inlined$filter$1(SharedFlowImpl sharedFlowImpl, QSTileViewModelImpl qSTileViewModelImpl) {
        this.$this_unsafeTransform$inlined = sharedFlowImpl;
        this.this$0 = qSTileViewModelImpl;
    }

    @Override // kotlinx.coroutines.flow.Flow
    public final Object collect(FlowCollector flowCollector, Continuation continuation) {
        this.$this_unsafeTransform$inlined.collect(new AnonymousClass2(flowCollector, this.this$0), continuation);
        return CoroutineSingletons.COROUTINE_SUSPENDED;
    }
}

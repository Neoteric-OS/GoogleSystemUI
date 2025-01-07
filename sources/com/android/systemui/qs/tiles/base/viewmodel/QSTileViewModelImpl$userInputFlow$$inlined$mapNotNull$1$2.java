package com.android.systemui.qs.tiles.base.viewmodel;

import android.os.UserHandle;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QSTileViewModelImpl$userInputFlow$$inlined$mapNotNull$1$2 implements FlowCollector {
    public final /* synthetic */ FlowCollector $this_unsafeFlow;
    public final /* synthetic */ UserHandle $user$inlined;
    public final /* synthetic */ QSTileViewModelImpl this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl$userInputFlow$$inlined$mapNotNull$1$2$1, reason: invalid class name */
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
            return QSTileViewModelImpl$userInputFlow$$inlined$mapNotNull$1$2.this.emit(null, this);
        }
    }

    public QSTileViewModelImpl$userInputFlow$$inlined$mapNotNull$1$2(FlowCollector flowCollector, QSTileViewModelImpl qSTileViewModelImpl, UserHandle userHandle) {
        this.$this_unsafeFlow = flowCollector;
        this.this$0 = qSTileViewModelImpl;
        this.$user$inlined = userHandle;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object emit(java.lang.Object r9, kotlin.coroutines.Continuation r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl$userInputFlow$$inlined$mapNotNull$1$2.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r10
            com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl$userInputFlow$$inlined$mapNotNull$1$2$1 r0 = (com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl$userInputFlow$$inlined$mapNotNull$1$2.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl$userInputFlow$$inlined$mapNotNull$1$2$1 r0 = new com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl$userInputFlow$$inlined$mapNotNull$1$2$1
            r0.<init>(r10)
        L18:
            java.lang.Object r10 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L30
            if (r2 != r3) goto L28
            kotlin.ResultKt.throwOnFailure(r10)
            goto L9c
        L28:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L30:
            kotlin.ResultKt.throwOnFailure(r10)
            com.android.systemui.qs.tiles.viewmodel.QSTileUserAction r9 = (com.android.systemui.qs.tiles.viewmodel.QSTileUserAction) r9
            com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl r10 = r8.this$0
            kotlinx.coroutines.flow.ReadonlyStateFlow r2 = r10.state
            kotlinx.coroutines.flow.MutableStateFlow r2 = r2.$$delegate_0
            kotlinx.coroutines.flow.StateFlowImpl r2 = (kotlinx.coroutines.flow.StateFlowImpl) r2
            java.util.List r2 = r2.getReplayCache()
            java.lang.Object r2 = kotlin.collections.CollectionsKt.lastOrNull(r2)
            com.android.systemui.qs.tiles.viewmodel.QSTileState r2 = (com.android.systemui.qs.tiles.viewmodel.QSTileState) r2
            r4 = 0
            if (r2 != 0) goto L4b
            goto L8f
        L4b:
            kotlinx.coroutines.flow.ReadonlySharedFlow r5 = r10.tileData
            kotlinx.coroutines.flow.MutableSharedFlow r5 = r5.$$delegate_0
            java.util.List r5 = r5.getReplayCache()
            java.lang.Object r5 = kotlin.collections.CollectionsKt.lastOrNull(r5)
            if (r5 != 0) goto L5a
            goto L8f
        L5a:
            com.android.systemui.qs.tiles.viewmodel.QSTileConfig r4 = r10.config
            com.android.systemui.qs.tiles.base.logging.QSTileLogger r6 = r10.qsTileLogger
            com.android.systemui.qs.pipeline.shared.TileSpec r7 = r4.tileSpec
            r6.logUserActionPipeline(r7, r9, r2, r5)
            com.android.systemui.qs.tiles.base.analytics.QSTileAnalytics r10 = r10.qsTileAnalytics
            com.android.internal.logging.UiEventLogger r10 = r10.uiEventLogger
            boolean r2 = r9 instanceof com.android.systemui.qs.tiles.viewmodel.QSTileUserAction.Click
            if (r2 == 0) goto L6e
            com.android.systemui.qs.QSEvent r2 = com.android.systemui.qs.QSEvent.QS_ACTION_CLICK
            goto L7b
        L6e:
            boolean r2 = r9 instanceof com.android.systemui.qs.tiles.viewmodel.QSTileUserAction.ToggleClick
            if (r2 == 0) goto L75
            com.android.systemui.qs.QSEvent r2 = com.android.systemui.qs.QSEvent.QS_ACTION_SECONDARY_CLICK
            goto L7b
        L75:
            boolean r2 = r9 instanceof com.android.systemui.qs.tiles.viewmodel.QSTileUserAction.LongClick
            if (r2 == 0) goto L9f
            com.android.systemui.qs.QSEvent r2 = com.android.systemui.qs.QSEvent.QS_ACTION_LONG_PRESS
        L7b:
            com.android.internal.logging.InstanceId r6 = r4.instanceId
            r7 = 0
            java.lang.String r4 = r4.metricsSpec
            r10.logWithInstanceId(r2, r7, r4, r6)
            com.android.systemui.qs.tiles.base.interactor.DataUpdateTrigger$UserInput r4 = new com.android.systemui.qs.tiles.base.interactor.DataUpdateTrigger$UserInput
            com.android.systemui.qs.tiles.base.interactor.QSTileInput r10 = new com.android.systemui.qs.tiles.base.interactor.QSTileInput
            android.os.UserHandle r2 = r8.$user$inlined
            r10.<init>(r2, r9, r5)
            r4.<init>(r10)
        L8f:
            if (r4 == 0) goto L9c
            r0.label = r3
            kotlinx.coroutines.flow.FlowCollector r8 = r8.$this_unsafeFlow
            java.lang.Object r8 = r8.emit(r4, r0)
            if (r8 != r1) goto L9c
            return r1
        L9c:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L9f:
            kotlin.NoWhenBranchMatchedException r8 = new kotlin.NoWhenBranchMatchedException
            r8.<init>()
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl$userInputFlow$$inlined$mapNotNull$1$2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}

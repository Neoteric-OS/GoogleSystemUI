package com.android.systemui.qs.pipeline.domain.interactor;

import java.util.List;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AutoAddInteractor$markTrackIfNotAddedTilesThatAreCurrent$3 implements FlowCollector {
    public final /* synthetic */ List $trackIfNotAddedSpecs;
    public final /* synthetic */ int $userId;
    public final /* synthetic */ AutoAddInteractor this$0;

    public AutoAddInteractor$markTrackIfNotAddedTilesThatAreCurrent$3(List list, AutoAddInteractor autoAddInteractor, int i) {
        this.$trackIfNotAddedSpecs = list;
        this.this$0 = autoAddInteractor;
        this.$userId = i;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object emit(java.util.List r7, kotlin.coroutines.Continuation r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof com.android.systemui.qs.pipeline.domain.interactor.AutoAddInteractor$markTrackIfNotAddedTilesThatAreCurrent$3$emit$1
            if (r0 == 0) goto L13
            r0 = r8
            com.android.systemui.qs.pipeline.domain.interactor.AutoAddInteractor$markTrackIfNotAddedTilesThatAreCurrent$3$emit$1 r0 = (com.android.systemui.qs.pipeline.domain.interactor.AutoAddInteractor$markTrackIfNotAddedTilesThatAreCurrent$3$emit$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.qs.pipeline.domain.interactor.AutoAddInteractor$markTrackIfNotAddedTilesThatAreCurrent$3$emit$1 r0 = new com.android.systemui.qs.pipeline.domain.interactor.AutoAddInteractor$markTrackIfNotAddedTilesThatAreCurrent$3$emit$1
            r0.<init>(r6, r8)
        L18:
            java.lang.Object r8 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L39
            if (r2 != r3) goto L31
            int r6 = r0.I$0
            java.lang.Object r7 = r0.L$1
            java.util.Iterator r7 = (java.util.Iterator) r7
            java.lang.Object r2 = r0.L$0
            com.android.systemui.qs.pipeline.domain.interactor.AutoAddInteractor r2 = (com.android.systemui.qs.pipeline.domain.interactor.AutoAddInteractor) r2
            kotlin.ResultKt.throwOnFailure(r8)
            goto L67
        L31:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L39:
            kotlin.ResultKt.throwOnFailure(r8)
            java.util.List r8 = r6.$trackIfNotAddedSpecs
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.Iterator r7 = r7.iterator()
        L47:
            boolean r4 = r7.hasNext()
            if (r4 == 0) goto L5e
            java.lang.Object r4 = r7.next()
            r5 = r4
            com.android.systemui.qs.pipeline.shared.TileSpec r5 = (com.android.systemui.qs.pipeline.shared.TileSpec) r5
            boolean r5 = r8.contains(r5)
            if (r5 == 0) goto L47
            r2.add(r4)
            goto L47
        L5e:
            java.util.Iterator r7 = r2.iterator()
            com.android.systemui.qs.pipeline.domain.interactor.AutoAddInteractor r8 = r6.this$0
            int r6 = r6.$userId
            r2 = r8
        L67:
            boolean r8 = r7.hasNext()
            if (r8 == 0) goto L84
            java.lang.Object r8 = r7.next()
            com.android.systemui.qs.pipeline.shared.TileSpec r8 = (com.android.systemui.qs.pipeline.shared.TileSpec) r8
            com.android.systemui.qs.pipeline.data.repository.AutoAddSettingRepository r4 = r2.repository
            r0.L$0 = r2
            r0.L$1 = r7
            r0.I$0 = r6
            r0.label = r3
            java.lang.Object r8 = r4.markTileAdded(r6, r8, r0)
            if (r8 != r1) goto L67
            return r1
        L84:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.pipeline.domain.interactor.AutoAddInteractor$markTrackIfNotAddedTilesThatAreCurrent$3.emit(java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }
}

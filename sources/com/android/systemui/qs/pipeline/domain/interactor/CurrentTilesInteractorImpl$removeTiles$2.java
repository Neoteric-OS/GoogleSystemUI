package com.android.systemui.qs.pipeline.domain.interactor;

import java.util.Collection;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class CurrentTilesInteractorImpl$removeTiles$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ Collection $specs;
    final /* synthetic */ int $user;
    int label;
    final /* synthetic */ CurrentTilesInteractorImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CurrentTilesInteractorImpl$removeTiles$2(CurrentTilesInteractorImpl currentTilesInteractorImpl, int i, Collection collection, Continuation continuation) {
        super(2, continuation);
        this.this$0 = currentTilesInteractorImpl;
        this.$user = i;
        this.$specs = collection;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new CurrentTilesInteractorImpl$removeTiles$2(this.this$0, this.$user, this.$specs, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((CurrentTilesInteractorImpl$removeTiles$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0047, code lost:
    
        if (r5 == r0) goto L18;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r6) {
        /*
            r5 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r5.label
            kotlin.Unit r2 = kotlin.Unit.INSTANCE
            r3 = 1
            if (r1 == 0) goto L17
            if (r1 != r3) goto Lf
            kotlin.ResultKt.throwOnFailure(r6)
            goto L4c
        Lf:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L17:
            kotlin.ResultKt.throwOnFailure(r6)
            com.android.systemui.qs.pipeline.domain.interactor.CurrentTilesInteractorImpl r6 = r5.this$0
            com.android.systemui.qs.pipeline.data.repository.TileSpecSettingsRepository r6 = r6.tileSpecRepository
            int r1 = r5.$user
            java.util.Collection r4 = r5.$specs
            r5.label = r3
            com.android.systemui.retail.data.repository.RetailModeSettingsRepository r3 = r6.retailModeRepository
            boolean r3 = r3.getInRetailMode()
            if (r3 == 0) goto L2e
        L2c:
            r5 = r2
            goto L49
        L2e:
            android.util.SparseArray r6 = r6.userTileRepositories
            java.lang.Object r6 = r6.get(r1)
            com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository r6 = (com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository) r6
            if (r6 == 0) goto L2c
            kotlinx.coroutines.flow.SharedFlowImpl r6 = r6.changeEvents
            com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository$RemoveTiles r1 = new com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository$RemoveTiles
            r1.<init>(r4)
            java.lang.Object r5 = r6.emit(r1, r5)
            if (r5 != r0) goto L46
            goto L47
        L46:
            r5 = r2
        L47:
            if (r5 != r0) goto L2c
        L49:
            if (r5 != r0) goto L4c
            return r0
        L4c:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.pipeline.domain.interactor.CurrentTilesInteractorImpl$removeTiles$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}

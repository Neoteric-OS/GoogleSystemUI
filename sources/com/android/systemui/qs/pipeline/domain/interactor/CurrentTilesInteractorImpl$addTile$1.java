package com.android.systemui.qs.pipeline.domain.interactor;

import com.android.systemui.qs.pipeline.shared.TileSpec;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class CurrentTilesInteractorImpl$addTile$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ int $position;
    final /* synthetic */ TileSpec $spec;
    int label;
    final /* synthetic */ CurrentTilesInteractorImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CurrentTilesInteractorImpl$addTile$1(CurrentTilesInteractorImpl currentTilesInteractorImpl, TileSpec tileSpec, int i, Continuation continuation) {
        super(2, continuation);
        this.this$0 = currentTilesInteractorImpl;
        this.$spec = tileSpec;
        this.$position = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new CurrentTilesInteractorImpl$addTile$1(this.this$0, this.$spec, this.$position, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((CurrentTilesInteractorImpl$addTile$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x006f, code lost:
    
        if (r6 == r0) goto L26;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r7) {
        /*
            r6 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r6.label
            kotlin.Unit r2 = kotlin.Unit.INSTANCE
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L1e
            if (r1 == r4) goto L1a
            if (r1 != r3) goto L12
            kotlin.ResultKt.throwOnFailure(r7)
            goto L74
        L12:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L1a:
            kotlin.ResultKt.throwOnFailure(r7)
            goto L33
        L1e:
            kotlin.ResultKt.throwOnFailure(r7)
            com.android.systemui.qs.pipeline.domain.interactor.CurrentTilesInteractorImpl r7 = r6.this$0
            kotlinx.coroutines.flow.ReadonlyStateFlow r7 = r7.currentTiles
            com.android.systemui.qs.pipeline.domain.interactor.CurrentTilesInteractorImpl$addTile$1$invokeSuspend$$inlined$filter$1 r1 = new com.android.systemui.qs.pipeline.domain.interactor.CurrentTilesInteractorImpl$addTile$1$invokeSuspend$$inlined$filter$1
            r1.<init>()
            r6.label = r4
            java.lang.Object r7 = kotlinx.coroutines.flow.FlowKt.first(r1, r6)
            if (r7 != r0) goto L33
            return r0
        L33:
            com.android.systemui.qs.pipeline.domain.interactor.CurrentTilesInteractorImpl r7 = r6.this$0
            com.android.systemui.qs.pipeline.data.repository.TileSpecSettingsRepository r1 = r7.tileSpecRepository
            com.android.systemui.user.data.repository.UserRepositoryImpl r7 = r7.userRepository
            android.content.pm.UserInfo r7 = r7.getSelectedUserInfo()
            int r7 = r7.id
            com.android.systemui.qs.pipeline.shared.TileSpec r4 = r6.$spec
            int r5 = r6.$position
            r6.label = r3
            com.android.systemui.retail.data.repository.RetailModeSettingsRepository r3 = r1.retailModeRepository
            boolean r3 = r3.getInRetailMode()
            if (r3 == 0) goto L4f
        L4d:
            r6 = r2
            goto L71
        L4f:
            boolean r3 = r4 instanceof com.android.systemui.qs.pipeline.shared.TileSpec.Invalid
            if (r3 == 0) goto L54
            goto L4d
        L54:
            android.util.SparseArray r1 = r1.userTileRepositories
            java.lang.Object r7 = r1.get(r7)
            com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository r7 = (com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository) r7
            if (r7 == 0) goto L4d
            if (r3 == 0) goto L62
        L60:
            r6 = r2
            goto L6f
        L62:
            kotlinx.coroutines.flow.SharedFlowImpl r7 = r7.changeEvents
            com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository$AddTile r1 = new com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository$AddTile
            r1.<init>(r5, r4)
            java.lang.Object r6 = r7.emit(r1, r6)
            if (r6 != r0) goto L60
        L6f:
            if (r6 != r0) goto L4d
        L71:
            if (r6 != r0) goto L74
            return r0
        L74:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.pipeline.domain.interactor.CurrentTilesInteractorImpl$addTile$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}

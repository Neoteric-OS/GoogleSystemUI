package com.android.systemui.communal.data.backup;

import androidx.room.coroutines.FlowUtil;
import com.android.systemui.communal.data.db.CommunalDatabase;
import com.android.systemui.communal.data.db.CommunalWidgetDao_Impl;
import com.android.systemui.communal.data.db.CommunalWidgetDao_Impl$$ExternalSyntheticLambda0;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SafeFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class CommunalBackupUtils$getCommunalHubState$widgetsFromDb$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ CommunalDatabase $database;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CommunalBackupUtils$getCommunalHubState$widgetsFromDb$1(CommunalDatabase communalDatabase, Continuation continuation) {
        super(2, continuation);
        this.$database = communalDatabase;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new CommunalBackupUtils$getCommunalHubState$widgetsFromDb$1(this.$database, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((CommunalBackupUtils$getCommunalHubState$widgetsFromDb$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CommunalWidgetDao_Impl communalWidgetDao = this.$database.communalWidgetDao();
            CommunalWidgetDao_Impl$$ExternalSyntheticLambda0 communalWidgetDao_Impl$$ExternalSyntheticLambda0 = new CommunalWidgetDao_Impl$$ExternalSyntheticLambda0(0);
            SafeFlow createFlow = FlowUtil.createFlow(communalWidgetDao.__db, new String[]{"communal_widget_table", "communal_item_rank_table"}, communalWidgetDao_Impl$$ExternalSyntheticLambda0);
            this.label = 1;
            obj = FlowKt.first(createFlow, this);
            if (obj == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return obj;
    }
}

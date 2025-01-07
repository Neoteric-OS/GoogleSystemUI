package com.android.systemui.communal.data.repository;

import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import androidx.room.util.DBUtil;
import com.android.systemui.communal.data.db.CommunalWidgetDao_Impl;
import com.android.systemui.communal.data.db.CommunalWidgetDao_Impl$$ExternalSyntheticLambda1;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.log.core.Logger;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class CommunalWidgetRepositoryImpl$updateWidgetOrder$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Map $widgetIdToRankMap;
    int label;
    final /* synthetic */ CommunalWidgetRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CommunalWidgetRepositoryImpl$updateWidgetOrder$1(CommunalWidgetRepositoryImpl communalWidgetRepositoryImpl, Map map, Continuation continuation) {
        super(2, continuation);
        this.this$0 = communalWidgetRepositoryImpl;
        this.$widgetIdToRankMap = map;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new CommunalWidgetRepositoryImpl$updateWidgetOrder$1(this.this$0, this.$widgetIdToRankMap, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        CommunalWidgetRepositoryImpl$updateWidgetOrder$1 communalWidgetRepositoryImpl$updateWidgetOrder$1 = (CommunalWidgetRepositoryImpl$updateWidgetOrder$1) create((CoroutineScope) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        communalWidgetRepositoryImpl$updateWidgetOrder$1.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        int i = 0;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CommunalWidgetDao_Impl communalWidgetDao_Impl = this.this$0.communalWidgetDao;
        Map map = this.$widgetIdToRankMap;
        communalWidgetDao_Impl.getClass();
        DBUtil.performBlocking(communalWidgetDao_Impl.__db, false, true, new CommunalWidgetDao_Impl$$ExternalSyntheticLambda1(communalWidgetDao_Impl, map, i));
        Logger logger = this.this$0.logger;
        AnonymousClass1 anonymousClass1 = new Function1() { // from class: com.android.systemui.communal.data.repository.CommunalWidgetRepositoryImpl$updateWidgetOrder$1.1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                return DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("Updated the order of widget list with ids: ", ((LogMessage) obj2).getStr1(), ".");
            }
        };
        Map map2 = this.$widgetIdToRankMap;
        LogMessage obtain = logger.getBuffer().obtain(logger.getTag(), LogLevel.INFO, anonymousClass1, null);
        obtain.setStr1(map2.toString());
        logger.getBuffer().commit(obtain);
        this.this$0.backupManager.dataChanged();
        return Unit.INSTANCE;
    }
}

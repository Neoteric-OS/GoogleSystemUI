package com.android.systemui.communal.ui.viewmodel;

import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import com.android.systemui.communal.domain.model.CommunalContentModel;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.log.core.Logger;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class CommunalViewModel$communalContent$2 extends SuspendLambda implements Function2 {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ CommunalViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CommunalViewModel$communalContent$2(CommunalViewModel communalViewModel, Continuation continuation) {
        super(2, continuation);
        this.this$0 = communalViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        CommunalViewModel$communalContent$2 communalViewModel$communalContent$2 = new CommunalViewModel$communalContent$2(this.this$0, continuation);
        communalViewModel$communalContent$2.L$0 = obj;
        return communalViewModel$communalContent$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        CommunalViewModel$communalContent$2 communalViewModel$communalContent$2 = (CommunalViewModel$communalContent$2) create((List) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        communalViewModel$communalContent$2.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        List list = (List) this.L$0;
        Logger logger = this.this$0.logger;
        AnonymousClass1 anonymousClass1 = new Function1() { // from class: com.android.systemui.communal.ui.viewmodel.CommunalViewModel$communalContent$2.1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("CommunalContent: ", ((LogMessage) obj2).getStr1());
            }
        };
        LogMessage obtain = logger.getBuffer().obtain(logger.getTag(), LogLevel.DEBUG, anonymousClass1, null);
        obtain.setStr1(CollectionsKt.joinToString$default(list, null, null, null, new Function1() { // from class: com.android.systemui.communal.ui.viewmodel.CommunalViewModel$communalContent$2$2$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                return ((CommunalContentModel) obj2).getKey();
            }
        }, 31));
        logger.getBuffer().commit(obtain);
        return Unit.INSTANCE;
    }
}

package com.android.systemui.communal.domain.interactor;

import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.log.core.Logger;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class CommunalInteractor$isCommunalAvailable$1 extends SuspendLambda implements Function2 {
    /* synthetic */ boolean Z$0;
    int label;
    final /* synthetic */ CommunalInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CommunalInteractor$isCommunalAvailable$1(CommunalInteractor communalInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = communalInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        CommunalInteractor$isCommunalAvailable$1 communalInteractor$isCommunalAvailable$1 = new CommunalInteractor$isCommunalAvailable$1(this.this$0, continuation);
        communalInteractor$isCommunalAvailable$1.Z$0 = ((Boolean) obj).booleanValue();
        return communalInteractor$isCommunalAvailable$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        Boolean bool = (Boolean) obj;
        bool.booleanValue();
        CommunalInteractor$isCommunalAvailable$1 communalInteractor$isCommunalAvailable$1 = (CommunalInteractor$isCommunalAvailable$1) create(bool, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        communalInteractor$isCommunalAvailable$1.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        boolean z = this.Z$0;
        Logger logger = this.this$0.logger;
        AnonymousClass1 anonymousClass1 = new Function1() { // from class: com.android.systemui.communal.domain.interactor.CommunalInteractor$isCommunalAvailable$1.1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                return DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("Communal is ", ((LogMessage) obj2).getBool1() ? "" : "un", "available");
            }
        };
        LogMessage obtain = logger.getBuffer().obtain(logger.getTag(), LogLevel.INFO, anonymousClass1, null);
        obtain.setBool1(z);
        logger.getBuffer().commit(obtain);
        return Unit.INSTANCE;
    }
}

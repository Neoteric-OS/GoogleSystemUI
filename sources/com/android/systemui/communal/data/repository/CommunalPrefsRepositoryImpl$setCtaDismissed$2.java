package com.android.systemui.communal.data.repository;

import android.content.pm.UserInfo;
import com.android.systemui.log.core.Logger;
import com.android.systemui.settings.UserFileManagerImpl;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class CommunalPrefsRepositoryImpl$setCtaDismissed$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ UserInfo $user;
    int label;
    final /* synthetic */ CommunalPrefsRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CommunalPrefsRepositoryImpl$setCtaDismissed$2(CommunalPrefsRepositoryImpl communalPrefsRepositoryImpl, UserInfo userInfo, Continuation continuation) {
        super(2, continuation);
        this.this$0 = communalPrefsRepositoryImpl;
        this.$user = userInfo;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new CommunalPrefsRepositoryImpl$setCtaDismissed$2(this.this$0, this.$user, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        CommunalPrefsRepositoryImpl$setCtaDismissed$2 communalPrefsRepositoryImpl$setCtaDismissed$2 = (CommunalPrefsRepositoryImpl$setCtaDismissed$2) create((CoroutineScope) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        communalPrefsRepositoryImpl$setCtaDismissed$2.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CommunalPrefsRepositoryImpl communalPrefsRepositoryImpl = this.this$0;
        UserInfo userInfo = this.$user;
        communalPrefsRepositoryImpl.getClass();
        ((UserFileManagerImpl) communalPrefsRepositoryImpl.userFileManager).getSharedPreferences$1(userInfo.id, "communal_hub_prefs").edit().putBoolean("cta_dismissed", true).apply();
        Logger.i$default((Logger) this.this$0.logger$delegate.getValue(), "Dismissed CTA tile", null, 2, null);
        return Unit.INSTANCE;
    }
}

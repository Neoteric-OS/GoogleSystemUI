package com.android.systemui.qs.pipeline.data.repository;

import com.android.systemui.util.settings.SecureSettingsImpl;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class UserAutoAddRepository$load$2 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ UserAutoAddRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UserAutoAddRepository$load$2(UserAutoAddRepository userAutoAddRepository, Continuation continuation) {
        super(2, continuation);
        this.this$0 = userAutoAddRepository;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new UserAutoAddRepository$load$2(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((UserAutoAddRepository$load$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        UserAutoAddRepository userAutoAddRepository = this.this$0;
        String stringForUser = ((SecureSettingsImpl) userAutoAddRepository.secureSettings).getStringForUser(userAutoAddRepository.userId, "qs_auto_tiles");
        if (stringForUser == null) {
            stringForUser = "";
        }
        return TilesSettingConverter.toTilesSet(stringForUser);
    }
}

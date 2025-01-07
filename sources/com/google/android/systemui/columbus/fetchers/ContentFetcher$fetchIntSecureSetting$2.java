package com.google.android.systemui.columbus.fetchers;

import android.provider.Settings;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class ContentFetcher$fetchIntSecureSetting$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ int $defaultValue;
    final /* synthetic */ String $settingKey;
    final /* synthetic */ int $userId;
    int label;
    final /* synthetic */ ContentFetcher this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ContentFetcher$fetchIntSecureSetting$2(ContentFetcher contentFetcher, String str, int i, int i2, Continuation continuation) {
        super(2, continuation);
        this.this$0 = contentFetcher;
        this.$settingKey = str;
        this.$defaultValue = i;
        this.$userId = i2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new ContentFetcher$fetchIntSecureSetting$2(this.this$0, this.$settingKey, this.$defaultValue, this.$userId, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((ContentFetcher$fetchIntSecureSetting$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        return new Integer(Settings.Secure.getIntForUser(this.this$0.contentResolver, this.$settingKey, this.$defaultValue, this.$userId));
    }
}

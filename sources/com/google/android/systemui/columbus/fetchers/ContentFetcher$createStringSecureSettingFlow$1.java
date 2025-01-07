package com.google.android.systemui.columbus.fetchers;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class ContentFetcher$createStringSecureSettingFlow$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ String $defaultSettingValue;
    final /* synthetic */ String $settingKey;
    /* synthetic */ int I$0;
    int label;
    final /* synthetic */ ContentFetcher this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ContentFetcher$createStringSecureSettingFlow$1(ContentFetcher contentFetcher, String str, String str2, Continuation continuation) {
        super(2, continuation);
        this.this$0 = contentFetcher;
        this.$settingKey = str;
        this.$defaultSettingValue = str2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        ContentFetcher$createStringSecureSettingFlow$1 contentFetcher$createStringSecureSettingFlow$1 = new ContentFetcher$createStringSecureSettingFlow$1(this.this$0, this.$settingKey, this.$defaultSettingValue, continuation);
        contentFetcher$createStringSecureSettingFlow$1.I$0 = ((Number) obj).intValue();
        return contentFetcher$createStringSecureSettingFlow$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((ContentFetcher$createStringSecureSettingFlow$1) create(Integer.valueOf(((Number) obj).intValue()), (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            int i2 = this.I$0;
            ContentFetcher contentFetcher = this.this$0;
            String str = this.$settingKey;
            String str2 = this.$defaultSettingValue;
            this.label = 1;
            contentFetcher.getClass();
            obj = BuildersKt.withContext(contentFetcher.bgDispatcher, new ContentFetcher$fetchStringSecureSetting$2(contentFetcher, str, i2, str2, null), this);
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

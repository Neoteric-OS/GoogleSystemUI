package com.google.android.systemui.columbus.fetchers;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerCoroutine;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class ContentFetcher$getSettingChangesForUser$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Uri $settingUri;
    final /* synthetic */ int $userId;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ ContentFetcher this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ContentFetcher$getSettingChangesForUser$1(ContentFetcher contentFetcher, Uri uri, int i, Continuation continuation) {
        super(2, continuation);
        this.this$0 = contentFetcher;
        this.$settingUri = uri;
        this.$userId = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        ContentFetcher$getSettingChangesForUser$1 contentFetcher$getSettingChangesForUser$1 = new ContentFetcher$getSettingChangesForUser$1(this.this$0, this.$settingUri, this.$userId, continuation);
        contentFetcher$getSettingChangesForUser$1.L$0 = obj;
        return contentFetcher$getSettingChangesForUser$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((ContentFetcher$getSettingChangesForUser$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [android.database.ContentObserver, com.google.android.systemui.columbus.fetchers.ContentFetcher$getSettingChangesForUser$1$contentObserver$1] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ProducerScope producerScope = (ProducerScope) this.L$0;
            final Handler handler = this.this$0.mainHandler;
            final int i2 = this.$userId;
            final ?? r3 = new ContentObserver(handler) { // from class: com.google.android.systemui.columbus.fetchers.ContentFetcher$getSettingChangesForUser$1$contentObserver$1
                @Override // android.database.ContentObserver
                public final void onChange(boolean z) {
                    ((ProducerCoroutine) ProducerScope.this).mo1790trySendJP2dKIU(Integer.valueOf(i2));
                }
            };
            this.this$0.contentResolver.registerContentObserver(this.$settingUri, false, r3, this.$userId);
            final ContentFetcher contentFetcher = this.this$0;
            Function0 function0 = new Function0() { // from class: com.google.android.systemui.columbus.fetchers.ContentFetcher$getSettingChangesForUser$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    ContentFetcher.this.contentResolver.unregisterContentObserver(r3);
                    return Unit.INSTANCE;
                }
            };
            this.label = 1;
            if (ProduceKt.awaitClose(producerScope, function0, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}

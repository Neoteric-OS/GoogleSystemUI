package com.android.systemui.clipboardoverlay;

import android.net.Uri;
import java.util.function.Consumer;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.TimeoutKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class ClipboardImageLoader$loadAsync$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Consumer $callback;
    final /* synthetic */ Uri $uri;
    Object L$0;
    int label;
    final /* synthetic */ ClipboardImageLoader this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ClipboardImageLoader$loadAsync$1(Consumer consumer, ClipboardImageLoader clipboardImageLoader, Uri uri, Continuation continuation) {
        super(2, continuation);
        this.$callback = consumer;
        this.this$0 = clipboardImageLoader;
        this.$uri = uri;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new ClipboardImageLoader$loadAsync$1(this.$callback, this.this$0, this.$uri, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((ClipboardImageLoader$loadAsync$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Consumer consumer;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Consumer consumer2 = this.$callback;
            ClipboardImageLoader clipboardImageLoader = this.this$0;
            Uri uri = this.$uri;
            this.L$0 = consumer2;
            this.label = 1;
            clipboardImageLoader.getClass();
            Object withTimeoutOrNull = TimeoutKt.withTimeoutOrNull(300L, new ClipboardImageLoader$load$2(clipboardImageLoader, uri, null), this);
            if (withTimeoutOrNull == coroutineSingletons) {
                return coroutineSingletons;
            }
            obj = withTimeoutOrNull;
            consumer = consumer2;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            consumer = (Consumer) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        consumer.accept(obj);
        return Unit.INSTANCE;
    }
}

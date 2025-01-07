package com.android.settingslib.media.session;

import android.media.session.MediaSession;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.ProducerCoroutine;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class MediaSessionManagerExtKt$defaultRemoteSessionChanged$1$callback$1$onDefaultRemoteSessionChanged$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ ProducerScope $$this$callbackFlow;
    final /* synthetic */ MediaSession.Token $sessionToken;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaSessionManagerExtKt$defaultRemoteSessionChanged$1$callback$1$onDefaultRemoteSessionChanged$1(ProducerScope producerScope, MediaSession.Token token, Continuation continuation) {
        super(2, continuation);
        this.$$this$callbackFlow = producerScope;
        this.$sessionToken = token;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new MediaSessionManagerExtKt$defaultRemoteSessionChanged$1$callback$1$onDefaultRemoteSessionChanged$1(this.$$this$callbackFlow, this.$sessionToken, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((MediaSessionManagerExtKt$defaultRemoteSessionChanged$1$callback$1$onDefaultRemoteSessionChanged$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ProducerScope producerScope = this.$$this$callbackFlow;
            MediaSession.Token token = this.$sessionToken;
            this.label = 1;
            if (((ProducerCoroutine) producerScope)._channel.send(token, this) == coroutineSingletons) {
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

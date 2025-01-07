package com.android.settingslib.media.session;

import android.media.session.MediaSessionManager;
import android.os.UserHandle;
import androidx.concurrent.futures.DirectExecutor;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerCoroutine;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class MediaSessionManagerExtKt$activeMediaChanges$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ MediaSessionManager $this_activeMediaChanges;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaSessionManagerExtKt$activeMediaChanges$1(MediaSessionManager mediaSessionManager, Continuation continuation) {
        super(2, continuation);
        this.$this_activeMediaChanges = mediaSessionManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        MediaSessionManagerExtKt$activeMediaChanges$1 mediaSessionManagerExtKt$activeMediaChanges$1 = new MediaSessionManagerExtKt$activeMediaChanges$1(this.$this_activeMediaChanges, continuation);
        mediaSessionManagerExtKt$activeMediaChanges$1.L$0 = obj;
        return mediaSessionManagerExtKt$activeMediaChanges$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((MediaSessionManagerExtKt$activeMediaChanges$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [android.media.session.MediaSessionManager$OnActiveSessionsChangedListener, com.android.settingslib.media.session.MediaSessionManagerExtKt$activeMediaChanges$1$listener$1] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ProducerScope producerScope = (ProducerScope) this.L$0;
            final ?? r1 = new MediaSessionManager.OnActiveSessionsChangedListener() { // from class: com.android.settingslib.media.session.MediaSessionManagerExtKt$activeMediaChanges$1$listener$1

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.settingslib.media.session.MediaSessionManagerExtKt$activeMediaChanges$1$listener$1$1, reason: invalid class name */
                final class AnonymousClass1 extends SuspendLambda implements Function2 {
                    final /* synthetic */ ProducerScope $$this$callbackFlow;
                    final /* synthetic */ List $it;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public AnonymousClass1(ProducerScope producerScope, List list, Continuation continuation) {
                        super(2, continuation);
                        this.$$this$callbackFlow = producerScope;
                        this.$it = list;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation create(Object obj, Continuation continuation) {
                        return new AnonymousClass1(this.$$this$callbackFlow, this.$it, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                        int i = this.label;
                        if (i == 0) {
                            ResultKt.throwOnFailure(obj);
                            ProducerScope producerScope = this.$$this$callbackFlow;
                            List list = this.$it;
                            this.label = 1;
                            if (((ProducerCoroutine) producerScope)._channel.send(list, this) == coroutineSingletons) {
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

                @Override // android.media.session.MediaSessionManager.OnActiveSessionsChangedListener
                public final void onActiveSessionsChanged(List list) {
                    ProducerScope producerScope2 = ProducerScope.this;
                    BuildersKt.launch$default(producerScope2, null, null, new AnonymousClass1(producerScope2, list, null), 3);
                }
            };
            this.$this_activeMediaChanges.addOnActiveSessionsChangedListener(null, UserHandle.of(UserHandle.myUserId()), DirectExecutor.INSTANCE, r1);
            final MediaSessionManager mediaSessionManager = this.$this_activeMediaChanges;
            Function0 function0 = new Function0() { // from class: com.android.settingslib.media.session.MediaSessionManagerExtKt$activeMediaChanges$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    mediaSessionManager.removeOnActiveSessionsChangedListener(r1);
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

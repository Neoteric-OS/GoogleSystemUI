package com.android.settingslib.volume.data.repository;

import android.database.ContentObserver;
import android.net.Uri;
import androidx.concurrent.futures.DirectExecutor;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class AudioRepositoryImpl$volumeSettingChanges$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Uri $uri;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ AudioRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AudioRepositoryImpl$volumeSettingChanges$1(AudioRepositoryImpl audioRepositoryImpl, Uri uri, Continuation continuation) {
        super(2, continuation);
        this.this$0 = audioRepositoryImpl;
        this.$uri = uri;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        AudioRepositoryImpl$volumeSettingChanges$1 audioRepositoryImpl$volumeSettingChanges$1 = new AudioRepositoryImpl$volumeSettingChanges$1(this.this$0, this.$uri, continuation);
        audioRepositoryImpl$volumeSettingChanges$1.L$0 = obj;
        return audioRepositoryImpl$volumeSettingChanges$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((AudioRepositoryImpl$volumeSettingChanges$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v2, types: [android.database.ContentObserver, com.android.settingslib.volume.data.repository.AudioRepositoryImpl$volumeSettingChanges$1$observer$1] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ProducerScope producerScope = (ProducerScope) this.L$0;
            DirectExecutor directExecutor = DirectExecutor.INSTANCE;
            final ?? r1 = new ContentObserver() { // from class: com.android.settingslib.volume.data.repository.AudioRepositoryImpl$volumeSettingChanges$1$observer$1
                {
                    super(DirectExecutor.INSTANCE, 0);
                }

                @Override // android.database.ContentObserver
                public final void onChange(boolean z) {
                    ProducerScope producerScope2 = ProducerScope.this;
                    BuildersKt.launch$default(producerScope2, null, null, new AudioRepositoryImpl$volumeSettingChanges$1$observer$1$onChange$1(producerScope2, null), 3);
                }
            };
            this.this$0.contentResolver.registerContentObserver(this.$uri, false, r1);
            final AudioRepositoryImpl audioRepositoryImpl = this.this$0;
            Function0 function0 = new Function0() { // from class: com.android.settingslib.volume.data.repository.AudioRepositoryImpl$volumeSettingChanges$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    AudioRepositoryImpl.this.contentResolver.unregisterContentObserver(r1);
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

package com.android.settingslib.volume.data.repository;

import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import com.android.internal.util.ConcurrentUtils;
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
/* loaded from: classes.dex */
final class AudioRepositoryImpl$communicationDevice$1 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ AudioRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AudioRepositoryImpl$communicationDevice$1(AudioRepositoryImpl audioRepositoryImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = audioRepositoryImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        AudioRepositoryImpl$communicationDevice$1 audioRepositoryImpl$communicationDevice$1 = new AudioRepositoryImpl$communicationDevice$1(this.this$0, continuation);
        audioRepositoryImpl$communicationDevice$1.L$0 = obj;
        return audioRepositoryImpl$communicationDevice$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((AudioRepositoryImpl$communicationDevice$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [android.media.AudioManager$OnCommunicationDeviceChangedListener, com.android.settingslib.volume.data.repository.AudioRepositoryImpl$communicationDevice$1$listener$1] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ProducerScope producerScope = (ProducerScope) this.L$0;
            final ?? r1 = new AudioManager.OnCommunicationDeviceChangedListener() { // from class: com.android.settingslib.volume.data.repository.AudioRepositoryImpl$communicationDevice$1$listener$1
                @Override // android.media.AudioManager.OnCommunicationDeviceChangedListener
                public final void onCommunicationDeviceChanged(AudioDeviceInfo audioDeviceInfo) {
                    ((ProducerCoroutine) ProducerScope.this).mo1790trySendJP2dKIU(Unit.INSTANCE);
                }
            };
            this.this$0.audioManager.addOnCommunicationDeviceChangedListener(ConcurrentUtils.DIRECT_EXECUTOR, r1);
            final AudioRepositoryImpl audioRepositoryImpl = this.this$0;
            Function0 function0 = new Function0() { // from class: com.android.settingslib.volume.data.repository.AudioRepositoryImpl$communicationDevice$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    AudioRepositoryImpl.this.audioManager.removeOnCommunicationDeviceChangedListener(r1);
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

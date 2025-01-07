package com.android.settingslib.volume.data.repository;

import com.android.settingslib.volume.shared.model.AudioStreamModel;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class AudioRepositoryImpl$getAudioStream$5 extends SuspendLambda implements Function2 {
    final /* synthetic */ int $audioStream;
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ AudioRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AudioRepositoryImpl$getAudioStream$5(AudioRepositoryImpl audioRepositoryImpl, int i, Continuation continuation) {
        super(2, continuation);
        this.this$0 = audioRepositoryImpl;
        this.$audioStream = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        AudioRepositoryImpl$getAudioStream$5 audioRepositoryImpl$getAudioStream$5 = new AudioRepositoryImpl$getAudioStream$5(this.this$0, this.$audioStream, continuation);
        audioRepositoryImpl$getAudioStream$5.L$0 = obj;
        return audioRepositoryImpl$getAudioStream$5;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        AudioRepositoryImpl$getAudioStream$5 audioRepositoryImpl$getAudioStream$5 = (AudioRepositoryImpl$getAudioStream$5) create((AudioStreamModel) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        audioRepositoryImpl$getAudioStream$5.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        this.this$0.logger.m896onVolumeUpdateReceivedVrMivd8(this.$audioStream, (AudioStreamModel) this.L$0);
        return Unit.INSTANCE;
    }
}

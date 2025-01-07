package com.google.android.systemui.volume.domain.startable;

import com.google.android.systemui.volume.domain.startable.MediaDeviceLoggerStartable$start$1;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class MediaDeviceLoggerStartable$start$3 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ MediaDeviceLoggerStartable this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaDeviceLoggerStartable$start$3(MediaDeviceLoggerStartable mediaDeviceLoggerStartable, Continuation continuation) {
        super(2, continuation);
        this.this$0 = mediaDeviceLoggerStartable;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new MediaDeviceLoggerStartable$start$3(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((MediaDeviceLoggerStartable$start$3) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Flow distinctUntilChanged = FlowKt.distinctUntilChanged(new MediaDeviceLoggerStartable$start$1$invokeSuspend$$inlined$map$1(this.this$0.audioOutputInteractor.currentAudioDevice, 2));
            MediaDeviceLoggerStartable$start$1.AnonymousClass3 anonymousClass3 = new MediaDeviceLoggerStartable$start$1.AnonymousClass3(this.this$0, 2);
            this.label = 1;
            Object collect = distinctUntilChanged.collect(new MediaDeviceLoggerStartable$start$3$invokeSuspend$$inlined$filter$1$2(anonymousClass3), this);
            if (collect != coroutineSingletons) {
                collect = unit;
            }
            if (collect == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return unit;
    }
}

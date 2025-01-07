package com.android.systemui.mediaprojection.data.repository;

import android.media.projection.MediaProjectionInfo;
import android.view.ContentRecordingSession;
import com.android.systemui.mediaprojection.data.model.MediaProjectionState;
import com.android.systemui.mediaprojection.data.repository.MediaProjectionManagerRepository;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class MediaProjectionManagerRepository$mediaProjectionState$2 extends SuspendLambda implements Function2 {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ MediaProjectionManagerRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaProjectionManagerRepository$mediaProjectionState$2(MediaProjectionManagerRepository mediaProjectionManagerRepository, Continuation continuation) {
        super(2, continuation);
        this.this$0 = mediaProjectionManagerRepository;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        MediaProjectionManagerRepository$mediaProjectionState$2 mediaProjectionManagerRepository$mediaProjectionState$2 = new MediaProjectionManagerRepository$mediaProjectionState$2(this.this$0, continuation);
        mediaProjectionManagerRepository$mediaProjectionState$2.L$0 = obj;
        return mediaProjectionManagerRepository$mediaProjectionState$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((MediaProjectionManagerRepository$mediaProjectionState$2) create((MediaProjectionManagerRepository.CallbackEvent) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            MediaProjectionManagerRepository.CallbackEvent callbackEvent = (MediaProjectionManagerRepository.CallbackEvent) this.L$0;
            if (callbackEvent instanceof MediaProjectionManagerRepository.CallbackEvent.OnStart ? true : callbackEvent instanceof MediaProjectionManagerRepository.CallbackEvent.OnStop) {
                return MediaProjectionState.NotProjecting.INSTANCE;
            }
            if (!(callbackEvent instanceof MediaProjectionManagerRepository.CallbackEvent.OnRecordingSessionSet)) {
                throw new NoWhenBranchMatchedException();
            }
            MediaProjectionManagerRepository mediaProjectionManagerRepository = this.this$0;
            MediaProjectionManagerRepository.CallbackEvent.OnRecordingSessionSet onRecordingSessionSet = (MediaProjectionManagerRepository.CallbackEvent.OnRecordingSessionSet) callbackEvent;
            MediaProjectionInfo mediaProjectionInfo = onRecordingSessionSet.info;
            ContentRecordingSession contentRecordingSession = onRecordingSessionSet.session;
            this.label = 1;
            obj = MediaProjectionManagerRepository.access$stateForSession(mediaProjectionManagerRepository, mediaProjectionInfo, contentRecordingSession, this);
            if (obj == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return (MediaProjectionState) obj;
    }
}

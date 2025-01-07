package com.android.settingslib.volume.data.repository;

import android.media.session.MediaController;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class MediaControllerRepositoryImpl$activeSessions$4 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ MediaControllerRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaControllerRepositoryImpl$activeSessions$4(MediaControllerRepositoryImpl mediaControllerRepositoryImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = mediaControllerRepositoryImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        MediaControllerRepositoryImpl$activeSessions$4 mediaControllerRepositoryImpl$activeSessions$4 = new MediaControllerRepositoryImpl$activeSessions$4(this.this$0, continuation);
        mediaControllerRepositoryImpl$activeSessions$4.L$0 = obj;
        return mediaControllerRepositoryImpl$activeSessions$4;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((MediaControllerRepositoryImpl$activeSessions$4) create((FlowCollector) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            List<MediaController> activeSessions = this.this$0.mediaSessionManager.getActiveSessions(null);
            this.label = 1;
            if (flowCollector.emit(activeSessions, this) == coroutineSingletons) {
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

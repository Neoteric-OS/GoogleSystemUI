package com.android.systemui.volume.panel.component.mediaoutput.domain.interactor;

import android.media.session.MediaController;
import com.android.systemui.volume.panel.component.mediaoutput.shared.model.MediaDeviceSession;
import java.util.Collection;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class MediaDeviceSessionInteractor$setSessionVolume$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ MediaDeviceSession $mediaDeviceSession;
    final /* synthetic */ int $volume;
    int label;
    final /* synthetic */ MediaDeviceSessionInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaDeviceSessionInteractor$setSessionVolume$2(MediaDeviceSessionInteractor mediaDeviceSessionInteractor, MediaDeviceSession mediaDeviceSession, int i, Continuation continuation) {
        super(2, continuation);
        this.this$0 = mediaDeviceSessionInteractor;
        this.$mediaDeviceSession = mediaDeviceSession;
        this.$volume = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new MediaDeviceSessionInteractor$setSessionVolume$2(this.this$0, this.$mediaDeviceSession, this.$volume, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((MediaDeviceSessionInteractor$setSessionVolume$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        MediaDeviceSessionInteractor mediaDeviceSessionInteractor = this.this$0;
        MediaController access$findControllerForSession = MediaDeviceSessionInteractor.access$findControllerForSession(mediaDeviceSessionInteractor, (Collection) mediaDeviceSessionInteractor.mediaControllerRepository.activeSessions.getValue(), this.$mediaDeviceSession);
        boolean z = false;
        if (access$findControllerForSession != null) {
            access$findControllerForSession.setVolumeTo(this.$volume, 0);
            z = true;
        }
        return Boolean.valueOf(z);
    }
}

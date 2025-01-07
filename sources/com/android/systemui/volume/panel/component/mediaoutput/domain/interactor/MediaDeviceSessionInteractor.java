package com.android.systemui.volume.panel.component.mediaoutput.domain.interactor;

import android.media.session.MediaController;
import com.android.settingslib.volume.data.repository.MediaControllerRepositoryImpl;
import com.android.systemui.volume.panel.component.mediaoutput.domain.model.MediaControllerChangeModel;
import com.android.systemui.volume.panel.component.mediaoutput.shared.model.MediaDeviceSession;
import java.util.Collection;
import java.util.Iterator;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MediaDeviceSessionInteractor {
    public final CoroutineContext backgroundCoroutineContext;
    public final MediaControllerInteractorImpl mediaControllerInteractor;
    public final MediaControllerRepositoryImpl mediaControllerRepository;

    public MediaDeviceSessionInteractor(CoroutineContext coroutineContext, MediaControllerInteractorImpl mediaControllerInteractorImpl, MediaControllerRepositoryImpl mediaControllerRepositoryImpl) {
        this.backgroundCoroutineContext = coroutineContext;
        this.mediaControllerInteractor = mediaControllerInteractorImpl;
        this.mediaControllerRepository = mediaControllerRepositoryImpl;
    }

    public static final MediaController access$findControllerForSession(MediaDeviceSessionInteractor mediaDeviceSessionInteractor, Collection collection, MediaDeviceSession mediaDeviceSession) {
        Object obj;
        mediaDeviceSessionInteractor.getClass();
        Iterator it = collection.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (Intrinsics.areEqual(((MediaController) obj).getSessionToken(), mediaDeviceSession.sessionToken)) {
                break;
            }
        }
        return (MediaController) obj;
    }

    public final MediaDeviceSessionInteractor$playbackInfo$$inlined$map$1 playbackInfo(MediaDeviceSession mediaDeviceSession) {
        return new MediaDeviceSessionInteractor$playbackInfo$$inlined$map$1(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(FlowKt.flowOn(FlowKt.transformLatest(this.mediaControllerRepository.activeSessions, new MediaDeviceSessionInteractor$stateChanges$$inlined$flatMapLatest$1(null, this, mediaDeviceSession, new MediaDeviceSessionInteractor$playbackInfo$1(3, null))), this.backgroundCoroutineContext), Reflection.getOrCreateKotlinClass(MediaControllerChangeModel.AudioInfoChanged.class), 1), 0);
    }

    public final MediaDeviceSessionInteractor$playbackInfo$$inlined$map$1 playbackState(MediaDeviceSession mediaDeviceSession) {
        return new MediaDeviceSessionInteractor$playbackInfo$$inlined$map$1(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(FlowKt.flowOn(FlowKt.transformLatest(this.mediaControllerRepository.activeSessions, new MediaDeviceSessionInteractor$stateChanges$$inlined$flatMapLatest$1(null, this, mediaDeviceSession, new MediaDeviceSessionInteractor$playbackState$1(3, null))), this.backgroundCoroutineContext), Reflection.getOrCreateKotlinClass(MediaControllerChangeModel.PlaybackStateChanged.class), 1), 1);
    }

    public final Object setSessionVolume(MediaDeviceSession mediaDeviceSession, int i, Continuation continuation) {
        if (!mediaDeviceSession.canAdjustVolume) {
            return Boolean.FALSE;
        }
        return BuildersKt.withContext(this.backgroundCoroutineContext, new MediaDeviceSessionInteractor$setSessionVolume$2(this, mediaDeviceSession, i, null), continuation);
    }
}

package com.android.systemui.volume.panel.component.mediaoutput.domain.interactor;

import android.media.MediaMetadata;
import android.media.session.MediaController;
import android.media.session.PlaybackState;
import android.os.Bundle;
import com.android.systemui.volume.panel.component.mediaoutput.domain.model.MediaControllerChangeModel;
import java.util.List;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MediaControllerCallbackProducer extends MediaController.Callback {
    public final ProducerScope producingScope;

    public MediaControllerCallbackProducer(ProducerScope producerScope) {
        this.producingScope = producerScope;
    }

    @Override // android.media.session.MediaController.Callback
    public final void onAudioInfoChanged(MediaController.PlaybackInfo playbackInfo) {
        send(new MediaControllerChangeModel.AudioInfoChanged(playbackInfo));
    }

    @Override // android.media.session.MediaController.Callback
    public final void onExtrasChanged(Bundle bundle) {
        send(new MediaControllerChangeModel.ExtrasChanged(bundle));
    }

    @Override // android.media.session.MediaController.Callback
    public final void onMetadataChanged(MediaMetadata mediaMetadata) {
        send(new MediaControllerChangeModel.MetadataChanged(mediaMetadata));
    }

    @Override // android.media.session.MediaController.Callback
    public final void onPlaybackStateChanged(PlaybackState playbackState) {
        send(new MediaControllerChangeModel.PlaybackStateChanged(playbackState));
    }

    @Override // android.media.session.MediaController.Callback
    public final void onQueueChanged(List list) {
        send(new MediaControllerChangeModel.QueueChanged(list));
    }

    @Override // android.media.session.MediaController.Callback
    public final void onQueueTitleChanged(CharSequence charSequence) {
        send(new MediaControllerChangeModel.QueueTitleChanged(charSequence));
    }

    @Override // android.media.session.MediaController.Callback
    public final void onSessionDestroyed() {
        send(MediaControllerChangeModel.SessionDestroyed.INSTANCE);
    }

    @Override // android.media.session.MediaController.Callback
    public final void onSessionEvent(String str, Bundle bundle) {
        send(new MediaControllerChangeModel.SessionEvent(bundle, str));
    }

    public final void send(MediaControllerChangeModel mediaControllerChangeModel) {
        BuildersKt.launch$default(this.producingScope, null, null, new MediaControllerCallbackProducer$send$1(this, mediaControllerChangeModel, null), 3);
    }
}

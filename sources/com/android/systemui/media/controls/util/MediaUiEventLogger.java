package com.android.systemui.media.controls.util;

import com.android.internal.logging.InstanceId;
import com.android.internal.logging.InstanceIdSequence;
import com.android.internal.logging.UiEventLogger;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaUiEventLogger {
    public final InstanceIdSequence instanceIdSequence = new InstanceIdSequence(1048576);
    public final UiEventLogger logger;

    public MediaUiEventLogger(UiEventLogger uiEventLogger) {
        this.logger = uiEventLogger;
    }

    public final void logActiveMediaAdded(int i, String str, InstanceId instanceId, int i2) {
        MediaUiEvent mediaUiEvent;
        if (i2 == 0) {
            mediaUiEvent = MediaUiEvent.LOCAL_MEDIA_ADDED;
        } else if (i2 == 1) {
            mediaUiEvent = MediaUiEvent.CAST_MEDIA_ADDED;
        } else {
            if (i2 != 2) {
                throw new IllegalArgumentException("Unknown playback location");
            }
            mediaUiEvent = MediaUiEvent.REMOTE_MEDIA_ADDED;
        }
        this.logger.logWithInstanceId(mediaUiEvent, i, str, instanceId);
    }

    public final void logMediaRemoved(int i, String str, InstanceId instanceId) {
        this.logger.logWithInstanceId(MediaUiEvent.MEDIA_REMOVED, i, str, instanceId);
    }

    public final void logPlaybackLocationChange(int i, String str, InstanceId instanceId, int i2) {
        MediaUiEvent mediaUiEvent;
        if (i2 == 0) {
            mediaUiEvent = MediaUiEvent.TRANSFER_TO_LOCAL;
        } else if (i2 == 1) {
            mediaUiEvent = MediaUiEvent.TRANSFER_TO_CAST;
        } else {
            if (i2 != 2) {
                throw new IllegalArgumentException("Unknown playback location");
            }
            mediaUiEvent = MediaUiEvent.TRANSFER_TO_REMOTE;
        }
        this.logger.logWithInstanceId(mediaUiEvent, i, str, instanceId);
    }
}

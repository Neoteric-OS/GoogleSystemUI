package com.android.systemui.media.controls.domain.pipeline;

import android.media.session.MediaController;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaTimeoutListener$PlaybackStateListener$destroy$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ MediaTimeoutListener.PlaybackStateListener this$0;

    public /* synthetic */ MediaTimeoutListener$PlaybackStateListener$destroy$1(MediaTimeoutListener.PlaybackStateListener playbackStateListener, int i) {
        this.$r8$classId = i;
        this.this$0 = playbackStateListener;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                MediaTimeoutListener.PlaybackStateListener playbackStateListener = this.this$0;
                MediaController mediaController = playbackStateListener.mediaController;
                if (mediaController != null) {
                    mediaController.unregisterCallback(playbackStateListener);
                    break;
                }
                break;
            case 1:
                MediaTimeoutListener.PlaybackStateListener playbackStateListener2 = this.this$0;
                MediaController mediaController2 = playbackStateListener2.mediaController;
                if (mediaController2 != null) {
                    mediaController2.unregisterCallback(playbackStateListener2);
                    break;
                }
                break;
            default:
                this.this$0.doTimeout();
                break;
        }
    }
}

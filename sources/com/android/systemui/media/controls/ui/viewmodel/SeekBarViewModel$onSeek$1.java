package com.android.systemui.media.controls.ui.viewmodel;

import android.media.session.MediaController;
import com.android.systemui.media.controls.ui.controller.MediaControlPanel$$ExternalSyntheticLambda5;
import com.android.systemui.media.controls.ui.viewmodel.SeekBarViewModel;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SeekBarViewModel$onSeek$1 implements Runnable {
    public final /* synthetic */ long $position;
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ SeekBarViewModel this$0;

    public /* synthetic */ SeekBarViewModel$onSeek$1(SeekBarViewModel seekBarViewModel, long j, int i) {
        this.$r8$classId = i;
        this.this$0 = seekBarViewModel;
        this.$position = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MediaController.TransportControls transportControls;
        switch (this.$r8$classId) {
            case 0:
                SeekBarViewModel seekBarViewModel = this.this$0;
                if (!seekBarViewModel.isFalseSeek) {
                    MediaControlPanel$$ExternalSyntheticLambda5 mediaControlPanel$$ExternalSyntheticLambda5 = seekBarViewModel.logSeek;
                    if (mediaControlPanel$$ExternalSyntheticLambda5 == null) {
                        mediaControlPanel$$ExternalSyntheticLambda5 = null;
                    }
                    mediaControlPanel$$ExternalSyntheticLambda5.invoke();
                    MediaController mediaController = this.this$0.controller;
                    if (mediaController != null && (transportControls = mediaController.getTransportControls()) != null) {
                        transportControls.seekTo(this.$position);
                    }
                    SeekBarViewModel seekBarViewModel2 = this.this$0;
                    seekBarViewModel2.playbackState = null;
                    SeekBarViewModel.access$setScrubbing(seekBarViewModel2, false);
                    break;
                } else {
                    SeekBarViewModel.access$setScrubbing(seekBarViewModel, false);
                    SeekBarViewModel.access$checkPlaybackPosition(this.this$0);
                    break;
                }
            default:
                SeekBarViewModel seekBarViewModel3 = this.this$0;
                if (!seekBarViewModel3.scrubbing) {
                    seekBarViewModel3.bgExecutor.execute(new SeekBarViewModel$onSeek$1(seekBarViewModel3, this.$position, 0));
                    break;
                } else {
                    seekBarViewModel3.set_data(SeekBarViewModel.Progress.copy$default(seekBarViewModel3._data, false, false, Integer.valueOf((int) this.$position), 0, false, 111));
                    break;
                }
        }
    }
}

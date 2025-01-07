package com.android.systemui.media.controls.ui.viewmodel;

import com.android.systemui.media.controls.ui.viewmodel.SeekBarViewModel;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SeekBarViewModel$onDestroy$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ SeekBarViewModel this$0;

    public /* synthetic */ SeekBarViewModel$onDestroy$1(SeekBarViewModel seekBarViewModel, int i) {
        this.$r8$classId = i;
        this.this$0 = seekBarViewModel;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                this.this$0.setController(null);
                SeekBarViewModel seekBarViewModel = this.this$0;
                seekBarViewModel.playbackState = null;
                SeekBarViewModel$checkIfPollingNeeded$1 seekBarViewModel$checkIfPollingNeeded$1 = seekBarViewModel.cancel;
                if (seekBarViewModel$checkIfPollingNeeded$1 != null) {
                    seekBarViewModel$checkIfPollingNeeded$1.run();
                }
                SeekBarViewModel seekBarViewModel2 = this.this$0;
                seekBarViewModel2.cancel = null;
                seekBarViewModel2.scrubbingChangeListener = null;
                seekBarViewModel2.enabledChangeListener = null;
                break;
            case 1:
                SeekBarViewModel.access$checkPlaybackPosition(this.this$0);
                break;
            case 2:
                this.this$0.setController(null);
                SeekBarViewModel seekBarViewModel3 = this.this$0;
                seekBarViewModel3.playbackState = null;
                SeekBarViewModel$checkIfPollingNeeded$1 seekBarViewModel$checkIfPollingNeeded$12 = seekBarViewModel3.cancel;
                if (seekBarViewModel$checkIfPollingNeeded$12 != null) {
                    seekBarViewModel$checkIfPollingNeeded$12.run();
                }
                SeekBarViewModel seekBarViewModel4 = this.this$0;
                seekBarViewModel4.cancel = null;
                seekBarViewModel4.set_data(SeekBarViewModel.Progress.copy$default(seekBarViewModel4._data, false, false, null, 0, false, 126));
                break;
            case 3:
                SeekBarViewModel seekBarViewModel5 = this.this$0;
                if (seekBarViewModel5.scrubbing) {
                    seekBarViewModel5.isFalseSeek = true;
                    break;
                }
                break;
            default:
                SeekBarViewModel.access$setScrubbing(this.this$0, true);
                this.this$0.isFalseSeek = false;
                break;
        }
    }
}

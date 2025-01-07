package com.android.systemui.screenrecord;

import android.content.res.Resources;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class RecordingServiceStrings {
    public final Resources res;

    public RecordingServiceStrings(Resources resources) {
        this.res = resources;
    }

    public String getBackgroundProcessingLabel() {
        return this.res.getString(R.string.screenrecord_background_processing_label);
    }

    public String getOngoingRecording() {
        return this.res.getString(R.string.screenrecord_ongoing_screen_only);
    }

    public String getSaveError() {
        return this.res.getString(R.string.screenrecord_save_error);
    }

    public String getSaveTitle() {
        return this.res.getString(R.string.screenrecord_save_title);
    }

    public String getStartError() {
        return this.res.getString(R.string.screenrecord_start_error);
    }

    public String getTitle() {
        return this.res.getString(R.string.screenrecord_title);
    }
}

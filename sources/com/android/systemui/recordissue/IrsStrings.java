package com.android.systemui.recordissue;

import android.content.res.Resources;
import com.android.systemui.screenrecord.RecordingServiceStrings;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class IrsStrings extends RecordingServiceStrings {
    public final Resources res;

    public IrsStrings(Resources resources) {
        super(resources);
        this.res = resources;
    }

    @Override // com.android.systemui.screenrecord.RecordingServiceStrings
    public final String getBackgroundProcessingLabel() {
        return this.res.getString(R.string.issuerecord_background_processing_label);
    }

    @Override // com.android.systemui.screenrecord.RecordingServiceStrings
    public final String getOngoingRecording() {
        return this.res.getString(R.string.issuerecord_ongoing_screen_only);
    }

    @Override // com.android.systemui.screenrecord.RecordingServiceStrings
    public final String getSaveError() {
        return this.res.getString(R.string.issuerecord_save_error);
    }

    @Override // com.android.systemui.screenrecord.RecordingServiceStrings
    public final String getSaveTitle() {
        return this.res.getString(R.string.issuerecord_save_title);
    }

    @Override // com.android.systemui.screenrecord.RecordingServiceStrings
    public final String getStartError() {
        return this.res.getString(R.string.issuerecord_start_error);
    }

    @Override // com.android.systemui.screenrecord.RecordingServiceStrings
    public final String getTitle() {
        return this.res.getString(R.string.issuerecord_title);
    }
}

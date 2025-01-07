package com.android.systemui.qs.tiles.impl.irecording;

import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class IssueRecordingModel {
    public final boolean isRecording;

    public final boolean equals(Object obj) {
        if (obj instanceof IssueRecordingModel) {
            return this.isRecording == ((IssueRecordingModel) obj).isRecording;
        }
        return false;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.isRecording);
    }

    public final String toString() {
        return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(new StringBuilder("IssueRecordingModel(isRecording="), this.isRecording, ")");
    }
}

package com.android.systemui.media.controls.shared.model;

import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import com.android.wm.shell.R;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaButton {
    public final MediaAction custom0;
    public final MediaAction custom1;
    public final MediaAction nextOrCustom;
    public final MediaAction playOrPause;
    public final MediaAction prevOrCustom;
    public final boolean reserveNext;
    public final boolean reservePrev;

    public /* synthetic */ MediaButton(MediaAction mediaAction) {
        this(mediaAction, null, null, null, null, false, false);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaButton)) {
            return false;
        }
        MediaButton mediaButton = (MediaButton) obj;
        return Intrinsics.areEqual(this.playOrPause, mediaButton.playOrPause) && Intrinsics.areEqual(this.nextOrCustom, mediaButton.nextOrCustom) && Intrinsics.areEqual(this.prevOrCustom, mediaButton.prevOrCustom) && Intrinsics.areEqual(this.custom0, mediaButton.custom0) && Intrinsics.areEqual(this.custom1, mediaButton.custom1) && this.reserveNext == mediaButton.reserveNext && this.reservePrev == mediaButton.reservePrev;
    }

    public final MediaAction getActionById(int i) {
        if (i == R.id.actionPlayPause) {
            return this.playOrPause;
        }
        if (i == R.id.actionNext) {
            return this.nextOrCustom;
        }
        if (i == R.id.actionPrev) {
            return this.prevOrCustom;
        }
        if (i == R.id.action0) {
            return this.custom0;
        }
        if (i == R.id.action1) {
            return this.custom1;
        }
        return null;
    }

    public final int hashCode() {
        MediaAction mediaAction = this.playOrPause;
        int hashCode = (mediaAction == null ? 0 : mediaAction.hashCode()) * 31;
        MediaAction mediaAction2 = this.nextOrCustom;
        int hashCode2 = (hashCode + (mediaAction2 == null ? 0 : mediaAction2.hashCode())) * 31;
        MediaAction mediaAction3 = this.prevOrCustom;
        int hashCode3 = (hashCode2 + (mediaAction3 == null ? 0 : mediaAction3.hashCode())) * 31;
        MediaAction mediaAction4 = this.custom0;
        int hashCode4 = (hashCode3 + (mediaAction4 == null ? 0 : mediaAction4.hashCode())) * 31;
        MediaAction mediaAction5 = this.custom1;
        return Boolean.hashCode(this.reservePrev) + TransitionData$$ExternalSyntheticOutline0.m((hashCode4 + (mediaAction5 != null ? mediaAction5.hashCode() : 0)) * 31, 31, this.reserveNext);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("MediaButton(playOrPause=");
        sb.append(this.playOrPause);
        sb.append(", nextOrCustom=");
        sb.append(this.nextOrCustom);
        sb.append(", prevOrCustom=");
        sb.append(this.prevOrCustom);
        sb.append(", custom0=");
        sb.append(this.custom0);
        sb.append(", custom1=");
        sb.append(this.custom1);
        sb.append(", reserveNext=");
        sb.append(this.reserveNext);
        sb.append(", reservePrev=");
        return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, this.reservePrev, ")");
    }

    public MediaButton(MediaAction mediaAction, MediaAction mediaAction2, MediaAction mediaAction3, MediaAction mediaAction4, MediaAction mediaAction5, boolean z, boolean z2) {
        this.playOrPause = mediaAction;
        this.nextOrCustom = mediaAction2;
        this.prevOrCustom = mediaAction3;
        this.custom0 = mediaAction4;
        this.custom1 = mediaAction5;
        this.reserveNext = z;
        this.reservePrev = z2;
    }
}

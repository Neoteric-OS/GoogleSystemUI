package com.android.systemui.media.controls.ui.viewmodel;

import androidx.compose.animation.FlingCalculator$FlingInfo$$ExternalSyntheticOutline0;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import com.android.systemui.common.shared.model.Icon;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaOutputSwitcherViewModel {
    public final float alpha;
    public final Icon deviceIcon;
    public final CharSequence deviceString;
    public final boolean isCurrentBroadcastApp;
    public final boolean isIntentValid;
    public final boolean isTapEnabled;
    public final Function1 onClicked;

    public MediaOutputSwitcherViewModel(boolean z, CharSequence charSequence, Icon icon, boolean z2, boolean z3, float f, Function1 function1) {
        this.isTapEnabled = z;
        this.deviceString = charSequence;
        this.deviceIcon = icon;
        this.isCurrentBroadcastApp = z2;
        this.isIntentValid = z3;
        this.alpha = f;
        this.onClicked = function1;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaOutputSwitcherViewModel)) {
            return false;
        }
        MediaOutputSwitcherViewModel mediaOutputSwitcherViewModel = (MediaOutputSwitcherViewModel) obj;
        return this.isTapEnabled == mediaOutputSwitcherViewModel.isTapEnabled && this.deviceString.equals(mediaOutputSwitcherViewModel.deviceString) && this.deviceIcon.equals(mediaOutputSwitcherViewModel.deviceIcon) && this.isCurrentBroadcastApp == mediaOutputSwitcherViewModel.isCurrentBroadcastApp && this.isIntentValid == mediaOutputSwitcherViewModel.isIntentValid && Float.compare(this.alpha, mediaOutputSwitcherViewModel.alpha) == 0 && this.onClicked.equals(mediaOutputSwitcherViewModel.onClicked);
    }

    public final int hashCode() {
        return this.onClicked.hashCode() + TransitionData$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m((this.deviceIcon.hashCode() + ((this.deviceString.hashCode() + (Boolean.hashCode(this.isTapEnabled) * 31)) * 31)) * 31, 31, this.isCurrentBroadcastApp), 31, this.isIntentValid), this.alpha, 31), 31, false);
    }

    public final String toString() {
        return "MediaOutputSwitcherViewModel(isTapEnabled=" + this.isTapEnabled + ", deviceString=" + ((Object) this.deviceString) + ", deviceIcon=" + this.deviceIcon + ", isCurrentBroadcastApp=" + this.isCurrentBroadcastApp + ", isIntentValid=" + this.isIntentValid + ", alpha=" + this.alpha + ", isVisible=false, onClicked=" + this.onClicked + ")";
    }
}

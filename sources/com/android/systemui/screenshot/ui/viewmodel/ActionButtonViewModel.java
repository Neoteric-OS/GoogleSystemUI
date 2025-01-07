package com.android.systemui.screenshot.ui.viewmodel;

import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ActionButtonViewModel {
    public static int nextId;
    public final ActionButtonAppearance appearance;
    public final int id;
    public final Function0 onClicked;
    public final boolean visible;

    public ActionButtonViewModel(ActionButtonAppearance actionButtonAppearance, int i, boolean z, Function0 function0) {
        this.appearance = actionButtonAppearance;
        this.id = i;
        this.visible = z;
        this.onClicked = function0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ActionButtonViewModel)) {
            return false;
        }
        ActionButtonViewModel actionButtonViewModel = (ActionButtonViewModel) obj;
        return this.appearance.equals(actionButtonViewModel.appearance) && this.id == actionButtonViewModel.id && this.visible == actionButtonViewModel.visible && this.onClicked.equals(actionButtonViewModel.onClicked);
    }

    public final int hashCode() {
        return this.onClicked.hashCode() + TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.id, this.appearance.hashCode() * 31, 31), 31, this.visible), 31, true);
    }

    public final String toString() {
        return "ActionButtonViewModel(appearance=" + this.appearance + ", id=" + this.id + ", visible=" + this.visible + ", showDuringEntrance=true, onClicked=" + this.onClicked + ")";
    }
}

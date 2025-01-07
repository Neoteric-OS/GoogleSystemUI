package com.android.systemui.user.ui.viewmodel;

import androidx.compose.animation.FlingCalculator$FlingInfo$$ExternalSyntheticOutline0;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import com.android.systemui.common.shared.model.Text;
import com.android.systemui.common.ui.drawable.CircularDrawable;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UserViewModel {
    public final float alpha;
    public final CircularDrawable image;
    public final boolean isSelectionMarkerVisible;
    public final Text name;
    public final Function0 onClicked;
    public final int viewKey;

    public UserViewModel(int i, Text text, CircularDrawable circularDrawable, boolean z, float f, Function0 function0) {
        this.viewKey = i;
        this.name = text;
        this.image = circularDrawable;
        this.isSelectionMarkerVisible = z;
        this.alpha = f;
        this.onClicked = function0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof UserViewModel)) {
            return false;
        }
        UserViewModel userViewModel = (UserViewModel) obj;
        return this.viewKey == userViewModel.viewKey && Intrinsics.areEqual(this.name, userViewModel.name) && this.image.equals(userViewModel.image) && this.isSelectionMarkerVisible == userViewModel.isSelectionMarkerVisible && Float.compare(this.alpha, userViewModel.alpha) == 0 && Intrinsics.areEqual(this.onClicked, userViewModel.onClicked);
    }

    public final int hashCode() {
        int m = FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m((this.image.hashCode() + ((this.name.hashCode() + (Integer.hashCode(this.viewKey) * 31)) * 31)) * 31, 31, this.isSelectionMarkerVisible), this.alpha, 31);
        Function0 function0 = this.onClicked;
        return m + (function0 == null ? 0 : function0.hashCode());
    }

    public final String toString() {
        return "UserViewModel(viewKey=" + this.viewKey + ", name=" + this.name + ", image=" + this.image + ", isSelectionMarkerVisible=" + this.isSelectionMarkerVisible + ", alpha=" + this.alpha + ", onClicked=" + this.onClicked + ")";
    }
}

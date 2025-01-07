package com.android.systemui.user.ui.viewmodel;

import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UserActionViewModel {
    public final int iconResourceId;
    public final Function0 onClicked;
    public final int textResourceId;
    public final long viewKey;

    public UserActionViewModel(long j, int i, int i2, Function0 function0) {
        this.viewKey = j;
        this.iconResourceId = i;
        this.textResourceId = i2;
        this.onClicked = function0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof UserActionViewModel)) {
            return false;
        }
        UserActionViewModel userActionViewModel = (UserActionViewModel) obj;
        return this.viewKey == userActionViewModel.viewKey && this.iconResourceId == userActionViewModel.iconResourceId && this.textResourceId == userActionViewModel.textResourceId && Intrinsics.areEqual(this.onClicked, userActionViewModel.onClicked);
    }

    public final int hashCode() {
        return this.onClicked.hashCode() + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.textResourceId, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.iconResourceId, Long.hashCode(this.viewKey) * 31, 31), 31);
    }

    public final String toString() {
        return "UserActionViewModel(viewKey=" + this.viewKey + ", iconResourceId=" + this.iconResourceId + ", textResourceId=" + this.textResourceId + ", onClicked=" + this.onClicked + ")";
    }
}

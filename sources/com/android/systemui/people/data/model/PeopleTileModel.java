package com.android.systemui.people.data.model;

import android.graphics.drawable.Icon;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;
import com.android.systemui.people.widget.PeopleTileKey;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PeopleTileModel {
    public final boolean hasNewStory;
    public final boolean isDndBlocking;
    public final boolean isImportant;
    public final PeopleTileKey key;
    public final Icon userIcon;
    public final String username;

    public PeopleTileModel(PeopleTileKey peopleTileKey, String str, Icon icon, boolean z, boolean z2, boolean z3) {
        this.key = peopleTileKey;
        this.username = str;
        this.userIcon = icon;
        this.hasNewStory = z;
        this.isImportant = z2;
        this.isDndBlocking = z3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PeopleTileModel)) {
            return false;
        }
        PeopleTileModel peopleTileModel = (PeopleTileModel) obj;
        return Intrinsics.areEqual(this.key, peopleTileModel.key) && Intrinsics.areEqual(this.username, peopleTileModel.username) && Intrinsics.areEqual(this.userIcon, peopleTileModel.userIcon) && this.hasNewStory == peopleTileModel.hasNewStory && this.isImportant == peopleTileModel.isImportant && this.isDndBlocking == peopleTileModel.isDndBlocking;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.isDndBlocking) + TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m((this.userIcon.hashCode() + PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(this.username, this.key.hashCode() * 31, 31)) * 31, 31, this.hasNewStory), 31, this.isImportant);
    }

    public final String toString() {
        return "PeopleTileModel(key=" + this.key + ", username=" + this.username + ", userIcon=" + this.userIcon + ", hasNewStory=" + this.hasNewStory + ", isImportant=" + this.isImportant + ", isDndBlocking=" + this.isDndBlocking + ")";
    }
}

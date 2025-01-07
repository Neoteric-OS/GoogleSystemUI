package com.android.systemui.user.data.model;

import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UserSwitcherSettingsModel {
    public final boolean isAddUsersFromLockscreen;
    public final boolean isSimpleUserSwitcher;
    public final boolean isUserSwitcherEnabled;

    public UserSwitcherSettingsModel(boolean z, boolean z2, boolean z3) {
        this.isSimpleUserSwitcher = z;
        this.isAddUsersFromLockscreen = z2;
        this.isUserSwitcherEnabled = z3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof UserSwitcherSettingsModel)) {
            return false;
        }
        UserSwitcherSettingsModel userSwitcherSettingsModel = (UserSwitcherSettingsModel) obj;
        return this.isSimpleUserSwitcher == userSwitcherSettingsModel.isSimpleUserSwitcher && this.isAddUsersFromLockscreen == userSwitcherSettingsModel.isAddUsersFromLockscreen && this.isUserSwitcherEnabled == userSwitcherSettingsModel.isUserSwitcherEnabled;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.isUserSwitcherEnabled) + TransitionData$$ExternalSyntheticOutline0.m(Boolean.hashCode(this.isSimpleUserSwitcher) * 31, 31, this.isAddUsersFromLockscreen);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("UserSwitcherSettingsModel(isSimpleUserSwitcher=");
        sb.append(this.isSimpleUserSwitcher);
        sb.append(", isAddUsersFromLockscreen=");
        sb.append(this.isAddUsersFromLockscreen);
        sb.append(", isUserSwitcherEnabled=");
        return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, this.isUserSwitcherEnabled, ")");
    }
}

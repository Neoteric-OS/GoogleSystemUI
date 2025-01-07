package com.android.systemui.user.legacyhelper.ui;

import android.content.Context;
import android.content.pm.UserInfo;
import com.android.systemui.user.data.source.UserRecord;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class LegacyUserUiHelper {
    public static final String getUserRecordName(Context context, UserRecord userRecord, boolean z, boolean z2) {
        boolean z3 = userRecord.isGuest;
        Integer valueOf = (z3 && userRecord.isCurrent) ? Integer.valueOf(R.string.guest_exit_quick_settings_button) : (!z3 || userRecord.info == null) ? null : Integer.valueOf(android.R.string.hour_picker_description);
        if (valueOf != null) {
            return context.getString(valueOf.intValue());
        }
        UserInfo userInfo = userRecord.info;
        if (userInfo == null) {
            return context.getString(getUserSwitcherActionTextResourceId(userRecord.isGuest, z, z2, userRecord.isAddUser, userRecord.isAddSupervisedUser, false, userRecord.isManageUsers));
        }
        String str = userInfo.name;
        if (str != null) {
            return str;
        }
        throw new IllegalStateException("Required value was null.");
    }

    public static final int getUserSwitcherActionTextResourceId(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7) {
        if (!z && !z4 && !z5 && !z7) {
            throw new IllegalStateException("Check failed.");
        }
        if (z && z2 && z3) {
            return R.string.guest_resetting;
        }
        if (z && z6) {
            return R.string.guest_new_guest;
        }
        if ((z && z2) || z) {
            return android.R.string.hour_picker_description;
        }
        if (z4) {
            return R.string.user_add_user;
        }
        if (z5) {
            return R.string.add_user_supervised;
        }
        if (z7) {
            return R.string.manage_users;
        }
        throw new IllegalStateException("This should never happen!");
    }
}

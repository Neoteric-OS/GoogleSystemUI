package com.android.launcher3.util;

import android.os.UserHandle;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class UserIconInfo {
    public final int type;

    public UserIconInfo(UserHandle userHandle, int i) {
        if (userHandle != null) {
            userHandle.hashCode();
        }
        this.type = i;
    }
}

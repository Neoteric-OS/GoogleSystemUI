package com.android.settingslib.wifi;

import android.content.Context;
import android.os.UserManager;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class WifiEnterpriseRestrictionUtils {
    public static boolean hasUserRestrictionFromT(Context context, String str) {
        UserManager userManager = (UserManager) context.getSystemService(UserManager.class);
        if (userManager == null) {
            return false;
        }
        return userManager.hasUserRestriction(str);
    }
}

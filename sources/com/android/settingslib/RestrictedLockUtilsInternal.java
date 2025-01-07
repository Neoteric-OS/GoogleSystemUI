package com.android.settingslib;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.Log;
import com.android.settingslib.RestrictedLockUtils;
import java.util.List;
import java.util.Objects;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class RestrictedLockUtilsInternal extends RestrictedLockUtils {
    public static final boolean DEBUG = Log.isLoggable("RestrictedLockUtils", 3);
    static Proxy sProxy = new Proxy();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    class Proxy {
    }

    public static RestrictedLockUtils.EnforcedAdmin checkIfRestrictionEnforced(Context context, String str, int i) {
        DevicePolicyManager devicePolicyManager;
        ComponentName deviceOwnerComponentOnAnyUser;
        RestrictedLockUtils.EnforcedAdmin enforcedAdmin = null;
        if (((DevicePolicyManager) context.getSystemService("device_policy")) == null) {
            return null;
        }
        UserManager userManager = UserManager.get(context);
        UserHandle of = UserHandle.of(i);
        List userRestrictionSources = userManager.getUserRestrictionSources(str, of);
        if (userRestrictionSources.isEmpty()) {
            return null;
        }
        int size = userRestrictionSources.size();
        if (size > 1) {
            RestrictedLockUtils.EnforcedAdmin enforcedAdmin2 = new RestrictedLockUtils.EnforcedAdmin();
            enforcedAdmin2.enforcedRestriction = str;
            enforcedAdmin2.user = of;
            if (DEBUG) {
                Log.d("RestrictedLockUtils", "Multiple (" + size + ") enforcing users for restriction '" + str + "' on user " + of + "; returning default admin (" + enforcedAdmin2 + ")");
            }
            return enforcedAdmin2;
        }
        UserManager.EnforcingUser enforcingUser = (UserManager.EnforcingUser) userRestrictionSources.get(0);
        if (enforcingUser.getUserRestrictionSource() == 1) {
            return null;
        }
        UserHandle userHandle = enforcingUser.getUserHandle();
        if (userHandle != null && (devicePolicyManager = (DevicePolicyManager) context.getSystemService("device_policy")) != null) {
            try {
                ComponentName profileOwner = ((DevicePolicyManager) context.createPackageContextAsUser(context.getPackageName(), 0, userHandle).getSystemService(DevicePolicyManager.class)).getProfileOwner();
                if (profileOwner != null) {
                    enforcedAdmin = new RestrictedLockUtils.EnforcedAdmin(profileOwner, str, userHandle);
                } else if (Objects.equals(devicePolicyManager.getDeviceOwnerUser(), userHandle) && (deviceOwnerComponentOnAnyUser = devicePolicyManager.getDeviceOwnerComponentOnAnyUser()) != null) {
                    enforcedAdmin = new RestrictedLockUtils.EnforcedAdmin(deviceOwnerComponentOnAnyUser, str, userHandle);
                }
            } catch (PackageManager.NameNotFoundException e) {
                throw new IllegalStateException(e);
            }
        }
        if (enforcedAdmin != null) {
            return enforcedAdmin;
        }
        RestrictedLockUtils.EnforcedAdmin enforcedAdmin3 = new RestrictedLockUtils.EnforcedAdmin();
        enforcedAdmin3.enforcedRestriction = str;
        return enforcedAdmin3;
    }

    public static boolean hasBaseUserRestriction(Context context, String str, int i) {
        return ((UserManager) context.getSystemService("user")).hasBaseUserRestriction(str, UserHandle.of(i));
    }
}

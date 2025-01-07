package com.android.systemui;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ActivityIntentHelper {
    public final PackageManager mPm;

    public ActivityIntentHelper(Context context) {
        this.mPm = context.getPackageManager();
    }

    public static boolean wouldLaunchResolverActivity(ResolveInfo resolveInfo, List list) {
        for (int i = 0; i < list.size(); i++) {
            ResolveInfo resolveInfo2 = (ResolveInfo) list.get(i);
            if (resolveInfo2.activityInfo.name.equals(resolveInfo.activityInfo.name) && resolveInfo2.activityInfo.packageName.equals(resolveInfo.activityInfo.packageName)) {
                return false;
            }
        }
        return true;
    }

    public final ActivityInfo getPendingTargetActivityInfo(PendingIntent pendingIntent, int i) {
        List queryIntentComponents = pendingIntent.queryIntentComponents(852096);
        if (queryIntentComponents.size() == 0) {
            return null;
        }
        if (queryIntentComponents.size() == 1) {
            return ((ResolveInfo) queryIntentComponents.get(0)).activityInfo;
        }
        ResolveInfo resolveActivityAsUser = this.mPm.resolveActivityAsUser(pendingIntent.getIntent(), 852096, i);
        if (resolveActivityAsUser == null || wouldLaunchResolverActivity(resolveActivityAsUser, queryIntentComponents)) {
            return null;
        }
        return resolveActivityAsUser.activityInfo;
    }

    public final ActivityInfo getTargetActivityInfo(Intent intent, int i, boolean z) {
        int i2 = !z ? 852096 : 65664;
        List queryIntentActivitiesAsUser = this.mPm.queryIntentActivitiesAsUser(intent, i2, i);
        if (queryIntentActivitiesAsUser.size() == 0) {
            return null;
        }
        if (queryIntentActivitiesAsUser.size() == 1) {
            return ((ResolveInfo) queryIntentActivitiesAsUser.get(0)).activityInfo;
        }
        ResolveInfo resolveActivityAsUser = this.mPm.resolveActivityAsUser(intent, i2, i);
        if (resolveActivityAsUser == null || wouldLaunchResolverActivity(resolveActivityAsUser, queryIntentActivitiesAsUser)) {
            return null;
        }
        return resolveActivityAsUser.activityInfo;
    }

    public final boolean wouldPendingShowOverLockscreen(PendingIntent pendingIntent, int i) {
        ActivityInfo pendingTargetActivityInfo = getPendingTargetActivityInfo(pendingIntent, i);
        return pendingTargetActivityInfo != null && (pendingTargetActivityInfo.flags & 8389632) > 0;
    }
}

package com.android.systemui.emergency;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;
import com.android.wm.shell.R;
import java.util.Iterator;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class EmergencyGestureModule$emergencyGestureIntentFactory$1 {
    public final /* synthetic */ PackageManager $packageManager;
    public final /* synthetic */ Resources $resources;

    public EmergencyGestureModule$emergencyGestureIntentFactory$1(PackageManager packageManager, Resources resources) {
        this.$packageManager = packageManager;
        this.$resources = resources;
    }

    public final Intent invoke() {
        ResolveInfo resolveInfo;
        PackageManager packageManager = this.$packageManager;
        Resources resources = this.$resources;
        Intent intent = new Intent("com.android.systemui.action.LAUNCH_EMERGENCY");
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 1048576);
        if (!queryIntentActivities.isEmpty()) {
            String string = resources.getString(R.string.config_preferredEmergencySosPackage);
            if (!TextUtils.isEmpty(string)) {
                Iterator<ResolveInfo> it = queryIntentActivities.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        resolveInfo = queryIntentActivities.get(0);
                        break;
                    }
                    ResolveInfo next = it.next();
                    if (TextUtils.equals(next.activityInfo.packageName, string)) {
                        resolveInfo = next;
                        break;
                    }
                }
            } else {
                resolveInfo = queryIntentActivities.get(0);
            }
        } else {
            resolveInfo = null;
        }
        if (resolveInfo == null) {
            Log.wtf("EmergencyGestureModule", "Couldn't find an app to process the emergency intent.");
            return null;
        }
        ActivityInfo activityInfo = resolveInfo.activityInfo;
        intent.setComponent(new ComponentName(activityInfo.packageName, activityInfo.name));
        intent.setFlags(268435456);
        return intent;
    }
}

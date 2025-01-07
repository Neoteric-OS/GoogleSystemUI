package com.android.systemui.controls.management;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Parcelable;
import android.os.UserHandle;
import android.service.controls.Control;
import android.util.Log;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ControlsRequestReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        if (context.getPackageManager().hasSystemFeature("android.software.controls")) {
            try {
                ComponentName componentName = (ComponentName) intent.getParcelableExtra("android.intent.extra.COMPONENT_NAME", ComponentName.class);
                if (componentName == null) {
                    Log.e("ControlsRequestReceiver", "Null target component");
                    return;
                }
                try {
                    Parcelable parcelable = (Control) intent.getParcelableExtra("android.service.controls.extra.CONTROL", Control.class);
                    if (parcelable == null) {
                        Log.e("ControlsRequestReceiver", "Null control");
                        return;
                    }
                    String packageName = componentName.getPackageName();
                    boolean z = false;
                    try {
                        int packageUid = context.getPackageManager().getPackageUid(packageName, 0);
                        ActivityManager activityManager = (ActivityManager) context.getSystemService(ActivityManager.class);
                        if ((activityManager != null ? activityManager.getUidImportance(packageUid) : 1000) != 100) {
                            Log.w("ControlsRequestReceiver", "Uid " + packageUid + " not in foreground");
                        } else {
                            z = true;
                        }
                    } catch (PackageManager.NameNotFoundException unused) {
                        Log.w("ControlsRequestReceiver", "Package " + packageName + " not found");
                    }
                    if (z) {
                        Intent intent2 = new Intent(context, (Class<?>) ControlsRequestDialog.class);
                        intent2.putExtra("android.intent.extra.COMPONENT_NAME", componentName);
                        intent2.putExtra("android.service.controls.extra.CONTROL", parcelable);
                        intent2.addFlags(268566528);
                        intent2.putExtra("android.intent.extra.USER_ID", context.getUserId());
                        context.startActivityAsUser(intent2, UserHandle.SYSTEM);
                    }
                } catch (Exception e) {
                    Log.e("ControlsRequestReceiver", "Malformed intent extra Control", e);
                }
            } catch (Exception e2) {
                Log.e("ControlsRequestReceiver", "Malformed intent extra ComponentName", e2);
            }
        }
    }
}

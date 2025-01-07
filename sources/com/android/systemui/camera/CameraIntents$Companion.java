package com.android.systemui.camera;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;
import com.android.wm.shell.R;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class CameraIntents$Companion {
    public static String getOverrideCameraPackage(int i, Context context) {
        String string = context.getResources().getString(R.string.config_cameraGesturePackage);
        Intrinsics.checkNotNull(string);
        try {
            if (TextUtils.isEmpty(string)) {
                return null;
            }
            if (context.getPackageManager().getApplicationInfoAsUser(string, 0, i).enabled) {
                return string;
            }
            return null;
        } catch (PackageManager.NameNotFoundException e) {
            Log.w("CameraIntents", "Missing cameraGesturePackage ".concat(string), e);
            return null;
        }
    }
}

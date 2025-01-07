package com.android.wm.shell.common.split;

import android.app.ActivityManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Rect;
import androidx.appsearch.platformstorage.converter.GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0;
import com.android.internal.util.ArrayUtils;
import com.android.wm.shell.shared.split.SplitScreenConstants;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class SplitScreenUtils {
    public static String getPackageName(Intent intent) {
        if (intent == null || intent.getComponent() == null) {
            return null;
        }
        return intent.getComponent().getPackageName();
    }

    public static boolean isLeftRightSplit(boolean z, Configuration configuration) {
        boolean z2 = configuration.smallestScreenWidthDp >= 600;
        Rect maxBounds = configuration.windowConfiguration.getMaxBounds();
        boolean z3 = maxBounds.width() >= maxBounds.height();
        return (z && z2) ? !z3 : z3;
    }

    public static boolean isValidToSplit(ActivityManager.RunningTaskInfo runningTaskInfo) {
        return runningTaskInfo != null && runningTaskInfo.supportsMultiWindow && ArrayUtils.contains(SplitScreenConstants.CONTROLLED_ACTIVITY_TYPES, runningTaskInfo.getActivityType()) && ArrayUtils.contains(SplitScreenConstants.CONTROLLED_WINDOWING_MODES, runningTaskInfo.getWindowingMode());
    }

    public static int reverseSplitPosition(int i) {
        if (i != 0) {
            return i != 1 ? -1 : 0;
        }
        return 1;
    }

    public static String splitFailureMessage(String str, String str2) {
        return GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("(", str, ") Splitscreen aborted: ", str2);
    }

    public static String getPackageName(PendingIntent pendingIntent) {
        if (pendingIntent == null || pendingIntent.getIntent() == null) {
            return null;
        }
        return getPackageName(pendingIntent.getIntent());
    }
}

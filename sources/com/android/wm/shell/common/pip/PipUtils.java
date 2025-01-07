package com.android.wm.shell.common.pip;

import android.app.ActivityTaskManager;
import android.app.AppGlobals;
import android.app.RemoteAction;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.Rect;
import android.os.RemoteException;
import android.util.Log;
import android.util.Pair;
import android.window.TaskSnapshot;
import com.android.internal.protolog.ProtoLog;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class PipUtils {
    public static Boolean isPip2ExperimentEnabled;

    public static final Rect getEnterPipWithOverlaySrcRectHint(Rect rect, float f) {
        int i;
        int i2;
        float width = rect.width() / rect.height();
        int i3 = rect.left;
        int i4 = rect.top;
        if (width < f) {
            i = rect.width();
            i2 = MathKt.roundToInt(i / f);
            i4 = rect.top + ((rect.height() - i2) / 2);
        } else {
            int height = rect.height();
            int roundToInt = MathKt.roundToInt(height * f);
            i3 = rect.left + ((rect.width() - roundToInt) / 2);
            i = roundToInt;
            i2 = height;
        }
        return new Rect(i3, i4, i + i3, i2 + i4);
    }

    public static final TaskSnapshot getTaskSnapshot(int i) {
        if (i <= 0) {
            return null;
        }
        try {
            return ActivityTaskManager.getService().getTaskSnapshot(i, false);
        } catch (RemoteException e) {
            Log.e("PipUtils", "Failed to get task snapshot, taskId=" + i, e);
            return null;
        }
    }

    public static final Pair getTopPipActivity(Context context) {
        int length;
        try {
            String packageName = context.getPackageName();
            ActivityTaskManager.RootTaskInfo rootTaskInfo = ActivityTaskManager.getService().getRootTaskInfo(2, 0);
            if ((rootTaskInfo != null ? rootTaskInfo.childTaskIds : null) != null) {
                if (!(rootTaskInfo.childTaskIds.length == 0) && rootTaskInfo.childTaskNames.length - 1 >= 0) {
                    while (true) {
                        int i = length - 1;
                        ComponentName unflattenFromString = ComponentName.unflattenFromString(rootTaskInfo.childTaskNames[length]);
                        if (unflattenFromString != null && !Intrinsics.areEqual(unflattenFromString.getPackageName(), packageName)) {
                            return new Pair(unflattenFromString, Integer.valueOf(rootTaskInfo.childTaskUserIds[length]));
                        }
                        if (i < 0) {
                            break;
                        }
                        length = i;
                    }
                }
            }
        } catch (RemoteException unused) {
            ProtoLog.w(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, "%s: Unable to get pinned stack.", new Object[]{"PipUtils"});
        }
        return new Pair(null, 0);
    }

    public static final boolean isPip2ExperimentEnabled() {
        if (isPip2ExperimentEnabled == null) {
            AppGlobals.getPackageManager().hasSystemFeature("org.chromium.arc", 0);
            AppGlobals.getPackageManager().hasSystemFeature("android.software.leanback", 0);
            isPip2ExperimentEnabled = Boolean.FALSE;
        }
        return isPip2ExperimentEnabled.booleanValue();
    }

    public static final boolean remoteActionsMatch(RemoteAction remoteAction, RemoteAction remoteAction2) {
        if (remoteAction == remoteAction2) {
            return true;
        }
        if (remoteAction == null || remoteAction2 == null) {
            return false;
        }
        return remoteAction.isEnabled() == remoteAction2.isEnabled() && remoteAction.shouldShowIcon() == remoteAction2.shouldShowIcon() && Intrinsics.areEqual(remoteAction.getTitle(), remoteAction2.getTitle()) && Intrinsics.areEqual(remoteAction.getContentDescription(), remoteAction2.getContentDescription()) && Intrinsics.areEqual(remoteAction.getActionIntent(), remoteAction2.getActionIntent());
    }
}

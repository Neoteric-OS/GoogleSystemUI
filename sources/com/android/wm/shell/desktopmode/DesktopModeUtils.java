package com.android.wm.shell.desktopmode;

import android.app.ActivityManager;
import android.app.AppCompatTaskInfo;
import android.app.TaskInfo;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.SystemProperties;
import android.util.Size;
import com.android.wm.shell.common.DisplayLayout;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class DesktopModeUtils {
    public static final float DESKTOP_MODE_INITIAL_BOUNDS_SCALE = SystemProperties.getInt("persist.wm.debug.desktop_mode_initial_bounds_scale", 75) / 100.0f;
    public static final int DESKTOP_MODE_LANDSCAPE_APP_PADDING = SystemProperties.getInt("persist.wm.debug.desktop_mode_landscape_app_padding", 25);

    public static final float calculateAspectRatio(ActivityManager.RunningTaskInfo runningTaskInfo) {
        AppCompatTaskInfo appCompatTaskInfo = runningTaskInfo.appCompatTaskInfo;
        int i = appCompatTaskInfo.topActivityLetterboxAppWidth;
        int i2 = appCompatTaskInfo.topActivityLetterboxAppHeight;
        if (appCompatTaskInfo.isTopActivityLetterboxed() || !getCanChangeAspectRatio(runningTaskInfo)) {
            return Math.max(i, i2) / Math.min(i, i2);
        }
        if (runningTaskInfo.configuration.windowConfiguration.getAppBounds() == null) {
            return 1.0f;
        }
        return Math.max(r3.height(), r3.width()) / Math.min(r3.height(), r3.width());
    }

    public static Rect calculateInitialBounds$default(DisplayLayout displayLayout, ActivityManager.RunningTaskInfo runningTaskInfo) {
        Rect rect = new Rect(0, 0, displayLayout.mWidth, displayLayout.mHeight);
        float calculateAspectRatio = calculateAspectRatio(runningTaskInfo);
        float width = rect.width();
        float f = DESKTOP_MODE_INITIAL_BOUNDS_SCALE;
        Size size = new Size((int) (width * f), (int) (rect.height() * f));
        Rect rect2 = new Rect();
        displayLayout.getStableBounds(rect2);
        int i = displayLayout.mNavBarFrameHeight;
        int i2 = displayLayout.mTaskbarFrameHeight;
        if (i != i2) {
            rect2.bottom = displayLayout.mHeight - i2;
        }
        if (runningTaskInfo.appCompatTaskInfo.isUserFullscreenOverrideEnabled() || runningTaskInfo.appCompatTaskInfo.isSystemFullscreenOverrideEnabled()) {
            return positionInScreen(rect2, size);
        }
        ActivityInfo activityInfo = runningTaskInfo.topActivityInfo;
        if (activityInfo == null) {
            return positionInScreen(rect2, size);
        }
        int i3 = runningTaskInfo.configuration.orientation;
        if (i3 == 1) {
            int width2 = rect.width() - (DESKTOP_MODE_LANDSCAPE_APP_PADDING * 2);
            if (!getCanChangeAspectRatio(runningTaskInfo)) {
                size = ActivityInfo.isFixedOrientationLandscape(activityInfo.screenOrientation) ? maximizeSizeGivenAspectRatio(runningTaskInfo, new Size(width2, size.getHeight()), calculateAspectRatio) : maximizeSizeGivenAspectRatio(runningTaskInfo, size, calculateAspectRatio);
            } else if (ActivityInfo.isFixedOrientationLandscape(activityInfo.screenOrientation)) {
                size = new Size(width2, runningTaskInfo.appCompatTaskInfo.topActivityLetterboxAppHeight);
            }
        } else if (i3 == 2) {
            if (!getCanChangeAspectRatio(runningTaskInfo)) {
                size = maximizeSizeGivenAspectRatio(runningTaskInfo, size, calculateAspectRatio);
            } else if (ActivityInfo.isFixedOrientationPortrait(activityInfo.screenOrientation)) {
                size = new Size(runningTaskInfo.appCompatTaskInfo.topActivityLetterboxAppWidth, size.getHeight());
            }
        }
        return positionInScreen(rect2, size);
    }

    public static final boolean getCanChangeAspectRatio(TaskInfo taskInfo) {
        return taskInfo.isResizeable && !taskInfo.appCompatTaskInfo.hasMinAspectRatioOverride();
    }

    public static final Size maximizeSizeGivenAspectRatio(ActivityManager.RunningTaskInfo runningTaskInfo, Size size, float f) {
        int i;
        float f2;
        int height = size.getHeight();
        int width = size.getWidth();
        ActivityInfo activityInfo = ((TaskInfo) runningTaskInfo).topActivityInfo;
        int i2 = activityInfo != null ? activityInfo.screenOrientation : -1;
        Rect appBounds = ((TaskInfo) runningTaskInfo).configuration.windowConfiguration.getAppBounds();
        if (i2 != -1 ? ActivityInfo.isFixedOrientationPortrait(i2) : ((TaskInfo) runningTaskInfo).appCompatTaskInfo.isTopActivityLetterboxed() ? ((TaskInfo) runningTaskInfo).appCompatTaskInfo.isTopActivityPillarboxed() : appBounds != null ? appBounds.height() > appBounds.width() : ActivityInfo.isFixedOrientationPortrait(((TaskInfo) runningTaskInfo).configuration.orientation)) {
            i = (int) (height / f);
            if (i > width) {
                f2 = width * f;
                height = (int) f2;
            }
            width = i;
        } else {
            i = (int) (height * f);
            if (i > width) {
                f2 = width / f;
                height = (int) f2;
            }
            width = i;
        }
        return new Size(width, height);
    }

    public static final Rect positionInScreen(Rect rect, Size size) {
        Rect rect2 = new Rect(0, 0, size.getWidth(), size.getHeight());
        Point point = new Point((rect.width() - rect2.width()) / 2, (int) (((rect.height() - rect2.height()) * 0.375d) + rect.top));
        rect2.offsetTo(point.x, point.y);
        return rect2;
    }
}

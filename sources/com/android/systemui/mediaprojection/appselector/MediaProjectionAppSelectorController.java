package com.android.systemui.mediaprojection.appselector;

import android.content.ComponentName;
import android.os.UserHandle;
import com.android.systemui.mediaprojection.MediaProjectionMetricsLogger;
import com.android.systemui.mediaprojection.appselector.data.ActivityTaskManagerThumbnailLoader;
import com.android.systemui.mediaprojection.appselector.data.ShellRecentTaskListProvider;
import com.android.systemui.mediaprojection.devicepolicy.ScreenCaptureDevicePolicyResolver;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaProjectionAppSelectorController {
    public final ComponentName appSelectorComponentName;
    public final String callerPackageName;
    public final ScreenCaptureDevicePolicyResolver devicePolicyResolver;
    public final int hostUid;
    public final UserHandle hostUserHandle;
    public final boolean isFirstStart;
    public final MediaProjectionMetricsLogger logger;
    public final ShellRecentTaskListProvider recentTaskListProvider;
    public final CoroutineScope scope;
    public final ActivityTaskManagerThumbnailLoader thumbnailLoader;
    public final MediaProjectionAppSelectorActivity view;

    public MediaProjectionAppSelectorController(ShellRecentTaskListProvider shellRecentTaskListProvider, MediaProjectionAppSelectorActivity mediaProjectionAppSelectorActivity, ScreenCaptureDevicePolicyResolver screenCaptureDevicePolicyResolver, UserHandle userHandle, CoroutineScope coroutineScope, ComponentName componentName, String str, ActivityTaskManagerThumbnailLoader activityTaskManagerThumbnailLoader, boolean z, MediaProjectionMetricsLogger mediaProjectionMetricsLogger, int i) {
        this.recentTaskListProvider = shellRecentTaskListProvider;
        this.view = mediaProjectionAppSelectorActivity;
        this.devicePolicyResolver = screenCaptureDevicePolicyResolver;
        this.hostUserHandle = userHandle;
        this.scope = coroutineScope;
        this.appSelectorComponentName = componentName;
        this.callerPackageName = str;
        this.thumbnailLoader = activityTaskManagerThumbnailLoader;
        this.isFirstStart = z;
        this.logger = mediaProjectionMetricsLogger;
        this.hostUid = i;
    }
}

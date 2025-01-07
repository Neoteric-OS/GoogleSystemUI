package com.android.systemui.keyguard;

import android.app.ActivityManager;
import android.app.ActivityOptions;
import android.app.IActivityTaskManager;
import android.app.ProfilerInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.shared.system.TaskStackChangeListener;
import com.android.systemui.shared.system.TaskStackChangeListeners;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class WorkLockActivityController {
    public final Context mContext;
    public final IActivityTaskManager mIatm;
    public final AnonymousClass1 mLockListener;
    public final UserTracker mUserTracker;

    public WorkLockActivityController(Context context, UserTracker userTracker, TaskStackChangeListeners taskStackChangeListeners, IActivityTaskManager iActivityTaskManager) {
        TaskStackChangeListener taskStackChangeListener = new TaskStackChangeListener() { // from class: com.android.systemui.keyguard.WorkLockActivityController.1
            @Override // com.android.systemui.shared.system.TaskStackChangeListener
            public final void onTaskProfileLocked(ActivityManager.RunningTaskInfo runningTaskInfo, int i) {
                WorkLockActivityController workLockActivityController = WorkLockActivityController.this;
                workLockActivityController.getClass();
                ComponentName componentName = runningTaskInfo.baseActivity;
                Intent addFlags = new Intent("android.app.action.CONFIRM_DEVICE_CREDENTIAL_WITH_USER").setComponent(new ComponentName(workLockActivityController.mContext, (Class<?>) WorkLockActivity.class)).putExtra("android.intent.extra.USER_ID", i).putExtra("android.intent.extra.PACKAGE_NAME", componentName != null ? componentName.getPackageName() : "").addFlags(67239936);
                ActivityOptions makeBasic = ActivityOptions.makeBasic();
                makeBasic.setLaunchTaskId(runningTaskInfo.taskId);
                makeBasic.setTaskOverlay(true, false);
                Bundle bundle = makeBasic.toBundle();
                int i2 = -96;
                try {
                    i2 = workLockActivityController.mIatm.startActivityAsUser(workLockActivityController.mContext.getIApplicationThread(), workLockActivityController.mContext.getBasePackageName(), workLockActivityController.mContext.getAttributionTag(), addFlags, addFlags.resolveTypeIfNeeded(workLockActivityController.mContext.getContentResolver()), (IBinder) null, (String) null, 0, 268435456, (ProfilerInfo) null, bundle, ((UserTrackerImpl) workLockActivityController.mUserTracker).getUserId());
                } catch (RemoteException | Exception unused) {
                }
                if (ActivityManager.isStartResultSuccessful(i2)) {
                    return;
                }
                Log.w("WorkLockActivityController", "Failed to start work lock activity, will remove task=" + runningTaskInfo.taskId);
                try {
                    workLockActivityController.mIatm.removeTask(runningTaskInfo.taskId);
                } catch (RemoteException unused2) {
                    Log.e("WorkLockActivityController", "Failed to remove task=" + runningTaskInfo.taskId);
                }
            }
        };
        this.mContext = context;
        this.mUserTracker = userTracker;
        this.mIatm = iActivityTaskManager;
        taskStackChangeListeners.registerTaskStackListener(taskStackChangeListener);
    }
}

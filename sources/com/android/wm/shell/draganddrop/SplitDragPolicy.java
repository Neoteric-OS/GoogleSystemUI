package com.android.wm.shell.draganddrop;

import android.app.ActivityOptions;
import android.app.ActivityTaskManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.ClipDescription;
import android.content.Context;
import android.content.Intent;
import android.content.pm.LauncherApps;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.Log;
import android.util.Slog;
import android.window.WindowContainerToken;
import com.android.internal.logging.InstanceId;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.splitscreen.SplitScreenController;
import java.util.ArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class SplitDragPolicy {
    public final Context mContext;
    public final Starter mFullscreenStarter;
    public InstanceId mLoggerSessionId;
    public DragSession mSession;
    public final SplitScreenController mSplitScreen;
    public final SplitScreenController mSplitscreenStarter;
    public final ArrayList mTargets = new ArrayList();
    public final RectF mDisallowHitRegion = new RectF();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DefaultStarter implements Starter {
        public final Context mContext;

        public DefaultStarter(Context context) {
            this.mContext = context;
        }

        @Override // com.android.wm.shell.draganddrop.SplitDragPolicy.Starter
        public final void startIntent(PendingIntent pendingIntent, int i, Intent intent, int i2, Bundle bundle, WindowContainerToken windowContainerToken) {
            if (windowContainerToken != null && ProtoLogImpl_411527699.Cache.WM_SHELL_DRAG_AND_DROP_enabled[1]) {
                ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_DRAG_AND_DROP, -9147733928334905053L, 0, null);
            }
            try {
                pendingIntent.send(this.mContext, 0, null, null, null, null, bundle);
            } catch (PendingIntent.CanceledException e) {
                Slog.e("SplitDragPolicy", "Failed to launch activity", e);
            }
        }

        @Override // com.android.wm.shell.draganddrop.SplitDragPolicy.Starter
        public final void startShortcut(String str, String str2, int i, Bundle bundle, UserHandle userHandle) {
            try {
                ((LauncherApps) this.mContext.getSystemService(LauncherApps.class)).startShortcut(str, str2, null, bundle, userHandle);
            } catch (ActivityNotFoundException e) {
                Slog.e("SplitDragPolicy", "Failed to launch shortcut", e);
            }
        }

        @Override // com.android.wm.shell.draganddrop.SplitDragPolicy.Starter
        public final void startTask(int i, int i2, Bundle bundle, WindowContainerToken windowContainerToken) {
            if (windowContainerToken != null && ProtoLogImpl_411527699.Cache.WM_SHELL_DRAG_AND_DROP_enabled[1]) {
                ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_DRAG_AND_DROP, -9147733928334905053L, 0, null);
            }
            try {
                ActivityTaskManager.getService().startActivityFromRecents(i, bundle);
            } catch (RemoteException e) {
                Slog.e("SplitDragPolicy", "Failed to launch task", e);
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Starter {
        void startIntent(PendingIntent pendingIntent, int i, Intent intent, int i2, Bundle bundle, WindowContainerToken windowContainerToken);

        void startShortcut(String str, String str2, int i, Bundle bundle, UserHandle userHandle);

        void startTask(int i, int i2, Bundle bundle, WindowContainerToken windowContainerToken);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Target {
        public final Rect drawRegion;
        public final Rect hitRegion;
        public final int type;

        public Target(int i, Rect rect, Rect rect2) {
            this.type = i;
            this.hitRegion = rect;
            this.drawRegion = rect2;
        }

        public final String toString() {
            return "Target {type=" + this.type + " hit=" + this.hitRegion + " draw=" + this.drawRegion + "}";
        }
    }

    public SplitDragPolicy(Context context, SplitScreenController splitScreenController, Starter starter) {
        this.mContext = context;
        this.mSplitScreen = splitScreenController;
        this.mFullscreenStarter = starter;
        this.mSplitscreenStarter = splitScreenController;
    }

    public void onDropped(Target target, WindowContainerToken windowContainerToken) {
        int i;
        SplitScreenController splitScreenController;
        if (target == null || !this.mTargets.contains(target)) {
            return;
        }
        int i2 = target.type;
        int i3 = (i2 == 2 || i2 == 1) ? 1 : 0;
        if (i2 == 0 || (splitScreenController = this.mSplitScreen) == null) {
            i = -1;
        } else {
            int i4 = i3 ^ 1;
            splitScreenController.onDroppedToSplit(i4, this.mLoggerSessionId);
            i = i4;
        }
        Starter starter = i2 == 0 ? this.mFullscreenStarter : this.mSplitscreenStarter;
        DragSession dragSession = this.mSession;
        if (dragSession.appData == null) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_DRAG_AND_DROP_enabled[1]) {
                ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_DRAG_AND_DROP, -4103711109691327801L, 1, Long.valueOf(i));
            }
            ActivityOptions makeBasic = ActivityOptions.makeBasic();
            makeBasic.setDisallowEnterPictureInPictureWhileLaunching(true);
            makeBasic.setPendingIntentBackgroundActivityStartMode(2);
            makeBasic.setPendingIntentLaunchFlags(402653184);
            Bundle bundle = makeBasic.toBundle();
            PendingIntent pendingIntent = dragSession.launchableIntent;
            starter.startIntent(pendingIntent, pendingIntent.getCreatorUserHandle().getIdentifier(), null, i, bundle, windowContainerToken);
            return;
        }
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_DRAG_AND_DROP_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_DRAG_AND_DROP, -884179424239469908L, 1, Long.valueOf(i));
        }
        ClipDescription description = dragSession.mInitialDragData.getDescription();
        boolean hasMimeType = description.hasMimeType("application/vnd.android.task");
        boolean hasMimeType2 = description.hasMimeType("application/vnd.android.shortcut");
        ActivityOptions makeBasic2 = ActivityOptions.makeBasic();
        makeBasic2.setDisallowEnterPictureInPictureWhileLaunching(true);
        makeBasic2.setPendingIntentBackgroundActivityStartMode(3);
        Bundle bundle2 = makeBasic2.toBundle();
        if (dragSession.appData.hasExtra("android.intent.extra.ACTIVITY_OPTIONS")) {
            bundle2.putAll(dragSession.appData.getBundleExtra("android.intent.extra.ACTIVITY_OPTIONS"));
        }
        UserHandle userHandle = (UserHandle) dragSession.appData.getParcelableExtra("android.intent.extra.USER");
        if (hasMimeType) {
            starter.startTask(dragSession.appData.getIntExtra("android.intent.extra.TASK_ID", -1), i, bundle2, windowContainerToken);
            return;
        }
        if (hasMimeType2) {
            if (windowContainerToken != null && ProtoLogImpl_411527699.Cache.WM_SHELL_DRAG_AND_DROP_enabled[1]) {
                ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_DRAG_AND_DROP, 1109347913185901988L, 0, null);
            }
            starter.startShortcut(dragSession.appData.getStringExtra("android.intent.extra.PACKAGE_NAME"), dragSession.appData.getStringExtra("android.intent.extra.shortcut.ID"), i, bundle2, userHandle);
            return;
        }
        PendingIntent pendingIntent2 = (PendingIntent) dragSession.appData.getParcelableExtra("android.intent.extra.PENDING_INTENT");
        if (Build.IS_DEBUGGABLE && !userHandle.equals(pendingIntent2.getCreatorUserHandle())) {
            Log.e("SplitDragPolicy", "Expected app intent's EXTRA_USER to match pending intent user");
        }
        starter.startIntent(pendingIntent2, userHandle.getIdentifier(), null, i, bundle2, windowContainerToken);
    }
}

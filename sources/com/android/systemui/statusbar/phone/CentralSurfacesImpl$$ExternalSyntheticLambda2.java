package com.android.systemui.statusbar.phone;

import android.app.ActivityOptions;
import android.app.Notification;
import android.app.PendingIntent;
import android.os.SystemClock;
import android.service.notification.StatusBarNotification;
import android.util.EventLog;
import com.android.systemui.shade.NotificationShadeWindowControllerImpl;
import com.android.systemui.shade.NotificationShadeWindowState;
import com.android.systemui.statusbar.data.model.StatusBarMode;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.wm.shell.bubbles.BubbleController;
import com.android.wm.shell.bubbles.BubbleController$$ExternalSyntheticLambda0;
import com.android.wm.shell.bubbles.Bubbles;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.startingsurface.StartingWindowController;
import com.android.wm.shell.startingsurface.StartingWindowController$$ExternalSyntheticLambda3;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class CentralSurfacesImpl$$ExternalSyntheticLambda2 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ CentralSurfacesImpl f$0;

    public /* synthetic */ CentralSurfacesImpl$$ExternalSyntheticLambda2(CentralSurfacesImpl centralSurfacesImpl, int i) {
        this.$r8$classId = i;
        this.f$0 = centralSurfacesImpl;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        int i = this.$r8$classId;
        CentralSurfacesImpl centralSurfacesImpl = this.f$0;
        switch (i) {
            case 0:
                Boolean bool = (Boolean) obj;
                centralSurfacesImpl.getClass();
                if (bool.booleanValue() != centralSurfacesImpl.mIsIdleOnCommunal) {
                    centralSurfacesImpl.mIsIdleOnCommunal = bool.booleanValue();
                    centralSurfacesImpl.updateScrimController();
                    break;
                }
                break;
            case 1:
                centralSurfacesImpl.getClass();
                CentralSurfacesImpl$$ExternalSyntheticLambda0 centralSurfacesImpl$$ExternalSyntheticLambda0 = new CentralSurfacesImpl$$ExternalSyntheticLambda0(centralSurfacesImpl);
                BubbleController.BubblesImpl bubblesImpl = (BubbleController.BubblesImpl) ((Bubbles) obj);
                ((HandlerExecutor) BubbleController.this.mMainExecutor).execute(new BubbleController$$ExternalSyntheticLambda0(2, bubblesImpl, centralSurfacesImpl$$ExternalSyntheticLambda0));
                break;
            case 2:
                StartingWindowController.StartingSurfaceImpl startingSurfaceImpl = (StartingWindowController.StartingSurfaceImpl) obj;
                centralSurfacesImpl.getClass();
                CentralSurfacesImpl$$ExternalSyntheticLambda0 centralSurfacesImpl$$ExternalSyntheticLambda02 = new CentralSurfacesImpl$$ExternalSyntheticLambda0(centralSurfacesImpl);
                ((HandlerExecutor) StartingWindowController.this.mSplashScreenExecutor).execute(new StartingWindowController$$ExternalSyntheticLambda3(1, startingSurfaceImpl, centralSurfacesImpl$$ExternalSyntheticLambda02));
                break;
            case 3:
                if (!((Boolean) obj).booleanValue()) {
                    centralSurfacesImpl.getClass();
                    break;
                } else {
                    centralSurfacesImpl.mNoAnimationOnNextBarModeChange = true;
                    break;
                }
            case 4:
                centralSurfacesImpl.checkBarModes$1();
                centralSurfacesImpl.mAutoHideController.touchAutoHide();
                centralSurfacesImpl.mBubblesOptional.ifPresent(new CentralSurfacesImpl$$ExternalSyntheticLambda16(centralSurfacesImpl, (StatusBarMode) centralSurfacesImpl.mStatusBarModeRepository.defaultDisplay.statusBarMode.getValue()));
                break;
            case 5:
                Integer num = (Integer) obj;
                centralSurfacesImpl.getClass();
                int intValue = num.intValue();
                NotificationShadeWindowControllerImpl notificationShadeWindowControllerImpl = (NotificationShadeWindowControllerImpl) centralSurfacesImpl.mNotificationShadeWindowController;
                NotificationShadeWindowState notificationShadeWindowState = notificationShadeWindowControllerImpl.mCurrentState;
                if (intValue != notificationShadeWindowState.scrimsVisibility) {
                    boolean isExpanded = notificationShadeWindowControllerImpl.isExpanded(notificationShadeWindowState);
                    notificationShadeWindowState.scrimsVisibility = intValue;
                    if (isExpanded != notificationShadeWindowControllerImpl.isExpanded(notificationShadeWindowState)) {
                        notificationShadeWindowControllerImpl.apply(notificationShadeWindowState);
                    }
                    notificationShadeWindowControllerImpl.mScrimsVisibilityListener.accept(num);
                    break;
                }
                break;
            case 6:
                centralSurfacesImpl.getClass();
                CentralSurfacesImpl$$ExternalSyntheticLambda1 centralSurfacesImpl$$ExternalSyntheticLambda1 = new CentralSurfacesImpl$$ExternalSyntheticLambda1(centralSurfacesImpl, 3);
                if (!((Boolean) obj).booleanValue()) {
                    centralSurfacesImpl$$ExternalSyntheticLambda1.run();
                    break;
                } else {
                    centralSurfacesImpl.mLightRevealScrim.post(centralSurfacesImpl$$ExternalSyntheticLambda1);
                    break;
                }
            case 7:
                centralSurfacesImpl.mBrightnessMirrorVisible = ((Boolean) obj).booleanValue();
                centralSurfacesImpl.updateScrimController();
                break;
            default:
                NotificationEntry notificationEntry = (NotificationEntry) obj;
                centralSurfacesImpl.getClass();
                StatusBarNotification statusBarNotification = notificationEntry.mSbn;
                Notification notification = statusBarNotification.getNotification();
                if (notification.fullScreenIntent != null) {
                    try {
                        EventLog.writeEvent(36003, statusBarNotification.getKey());
                        centralSurfacesImpl.mPowerInteractor.wakeUpForFullScreenIntent();
                        ActivityOptions makeBasic = ActivityOptions.makeBasic();
                        makeBasic.setPendingIntentBackgroundActivityStartMode(1);
                        notification.fullScreenIntent.send(makeBasic.toBundle());
                        notificationEntry.interruption = true;
                        notificationEntry.lastFullScreenIntentLaunchTime = SystemClock.elapsedRealtime();
                        break;
                    } catch (PendingIntent.CanceledException unused) {
                        return;
                    }
                }
                break;
        }
    }
}

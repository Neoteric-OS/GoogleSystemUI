package com.android.systemui.statusbar.notification.row;

import android.app.Notification;
import android.content.Context;
import android.widget.RemoteViews;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.row.NotificationContentInflater;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$26;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class NotificationContentInflater$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ ExpandableNotificationRow f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ NotificationRowContentBinderLogger f$2;
    public final /* synthetic */ Notification.Builder f$3;
    public final /* synthetic */ boolean f$4;
    public final /* synthetic */ boolean f$5;
    public final /* synthetic */ HeadsUpStyleProviderImpl f$6;
    public final /* synthetic */ boolean f$7;
    public final /* synthetic */ DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$26 f$8;
    public final /* synthetic */ Context f$9;

    public /* synthetic */ NotificationContentInflater$$ExternalSyntheticLambda0(ExpandableNotificationRow expandableNotificationRow, int i, NotificationRowContentBinderLogger notificationRowContentBinderLogger, Notification.Builder builder, boolean z, boolean z2, HeadsUpStyleProviderImpl headsUpStyleProviderImpl, boolean z3, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$26 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$26, Context context) {
        this.f$0 = expandableNotificationRow;
        this.f$1 = i;
        this.f$2 = notificationRowContentBinderLogger;
        this.f$3 = builder;
        this.f$4 = z;
        this.f$5 = z2;
        this.f$6 = headsUpStyleProviderImpl;
        this.f$7 = z3;
        this.f$8 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$26;
        this.f$9 = context;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        Notification.Builder builder = this.f$3;
        Context context = this.f$9;
        NotificationContentInflater.InflationProgress inflationProgress = new NotificationContentInflater.InflationProgress();
        ExpandableNotificationRow expandableNotificationRow = this.f$0;
        NotificationEntry notificationEntry = expandableNotificationRow.mEntry;
        int i = this.f$1;
        int i2 = i & 1;
        NotificationRowContentBinderLogger notificationRowContentBinderLogger = this.f$2;
        boolean z = this.f$4;
        if (i2 != 0) {
            notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "creating contracted remote view");
            inflationProgress.newContentView = z ? builder.makeLowPriorityContentView(false) : builder.createContentView(this.f$5);
        }
        if ((i & 2) != 0) {
            notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "creating expanded remote view");
            RemoteViews createBigContentView = builder.createBigContentView();
            if (createBigContentView == null) {
                if (z) {
                    createBigContentView = builder.createContentView();
                    Notification.Builder.makeHeaderExpanded(createBigContentView);
                } else {
                    createBigContentView = null;
                }
            }
            inflationProgress.newExpandedView = createBigContentView;
        }
        if ((i & 4) != 0) {
            notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "creating heads up remote view");
            if (((Boolean) this.f$6.statusBarModeRepositoryStore.defaultDisplay.isInFullscreenMode.getValue()).booleanValue()) {
                inflationProgress.newHeadsUpView = builder.createCompactHeadsUpContentView();
            } else {
                inflationProgress.newHeadsUpView = builder.createHeadsUpContentView(this.f$7);
            }
        }
        if ((i & 8) != 0) {
            notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "creating public remote view");
            inflationProgress.newPublicView = builder.makePublicContentView(z);
        }
        if ((i & 32) != 0) {
            notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "creating group summary remote view");
            inflationProgress.mNewGroupHeaderView = builder.makeNotificationGroupHeader();
        }
        if ((i & 64) != 0) {
            notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "creating low-priority group summary remote view");
            inflationProgress.mNewMinimizedGroupHeaderView = builder.makeLowPriorityContentView(true);
        }
        RemoteViews remoteViews = inflationProgress.newContentView;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$26 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$26 = this.f$8;
        NotifLayoutInflaterFactory provide = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$26.provide(expandableNotificationRow, 1);
        if (remoteViews != null) {
            remoteViews.setLayoutInflaterFactory(provide);
        }
        RemoteViews remoteViews2 = inflationProgress.newExpandedView;
        NotifLayoutInflaterFactory provide2 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$26.provide(expandableNotificationRow, 2);
        if (remoteViews2 != null) {
            remoteViews2.setLayoutInflaterFactory(provide2);
        }
        RemoteViews remoteViews3 = inflationProgress.newHeadsUpView;
        NotifLayoutInflaterFactory provide3 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$26.provide(expandableNotificationRow, 4);
        if (remoteViews3 != null) {
            remoteViews3.setLayoutInflaterFactory(provide3);
        }
        RemoteViews remoteViews4 = inflationProgress.newPublicView;
        NotifLayoutInflaterFactory provide4 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$26.provide(expandableNotificationRow, 8);
        if (remoteViews4 != null) {
            remoteViews4.setLayoutInflaterFactory(provide4);
        }
        inflationProgress.packageContext = context;
        inflationProgress.headsUpStatusBarText = builder.getHeadsUpStatusBarText(false);
        inflationProgress.headsUpStatusBarTextPublic = builder.getHeadsUpStatusBarText(true);
        return inflationProgress;
    }
}

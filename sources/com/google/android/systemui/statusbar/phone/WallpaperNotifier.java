package com.google.android.systemui.statusbar.phone;

import android.app.WallpaperInfo;
import android.app.WallpaperManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.broadcast.BroadcastSender;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.notifcollection.CommonNotifCollection;
import com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener;
import com.google.android.collect.Sets;
import java.util.HashSet;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class WallpaperNotifier {
    public final Executor mBgExecutor;
    public final BroadcastDispatcher mBroadcastDispatcher;
    public final BroadcastSender mBroadcastSender;
    public final Context mContext;
    public final CommonNotifCollection mNotifCollection;
    public boolean mShouldBroadcastNotifications;
    public final Executor mUiBgExecutor;
    public final UserTracker mUserTracker;
    public String mWallpaperPackage;
    public static final String[] NOTIFYABLE_WALLPAPERS = {"com.breel.wallpapers.imprint", "com.breel.wallpapers18.tactile", "com.breel.wallpapers18.delight", "com.breel.wallpapers18.miniman", "com.google.pixel.livewallpaper.imprint", "com.google.pixel.livewallpaper.tactile", "com.google.pixel.livewallpaper.delight", "com.google.pixel.livewallpaper.miniman"};
    public static final HashSet NOTIFYABLE_PACKAGES = Sets.newHashSet(new String[]{"com.breel.wallpapers", "com.breel.wallpapers18", "com.google.pixel.livewallpaper"});
    public final AnonymousClass1 mNotifListener = new NotifCollectionListener() { // from class: com.google.android.systemui.statusbar.phone.WallpaperNotifier.1
        @Override // com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener
        public final void onEntryAdded(NotificationEntry notificationEntry) {
            WallpaperNotifier wallpaperNotifier = WallpaperNotifier.this;
            boolean z = ((UserTrackerImpl) wallpaperNotifier.mUserTracker).getUserId() == 0;
            if (wallpaperNotifier.mShouldBroadcastNotifications && z) {
                Intent intent = new Intent();
                intent.setPackage(wallpaperNotifier.mWallpaperPackage);
                intent.setAction("com.breel.wallpapers.NOTIFICATION_RECEIVED");
                intent.putExtra("notification_color", notificationEntry.mSbn.getNotification().color);
                wallpaperNotifier.mBroadcastSender.sendBroadcast(intent, "com.breel.wallpapers.notifications");
            }
        }
    };
    public final AnonymousClass2 mWallpaperChangedReceiver = new AnonymousClass2();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.google.android.systemui.statusbar.phone.WallpaperNotifier$2, reason: invalid class name */
    public final class AnonymousClass2 extends BroadcastReceiver {
        public AnonymousClass2() {
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.intent.action.WALLPAPER_CHANGED")) {
                WallpaperNotifier.this.mUiBgExecutor.execute(new WallpaperNotifier$$ExternalSyntheticLambda0(1, this));
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.google.android.systemui.statusbar.phone.WallpaperNotifier$1] */
    public WallpaperNotifier(Context context, CommonNotifCollection commonNotifCollection, BroadcastSender broadcastSender, UserTracker userTracker, BroadcastDispatcher broadcastDispatcher, Executor executor, Executor executor2) {
        this.mContext = context;
        this.mNotifCollection = commonNotifCollection;
        this.mBroadcastSender = broadcastSender;
        this.mUserTracker = userTracker;
        this.mBroadcastDispatcher = broadcastDispatcher;
        this.mUiBgExecutor = executor;
        this.mBgExecutor = executor2;
    }

    public final void checkNotificationBroadcastSupport() {
        WallpaperInfo wallpaperInfo;
        this.mShouldBroadcastNotifications = false;
        WallpaperManager wallpaperManager = (WallpaperManager) this.mContext.getSystemService(WallpaperManager.class);
        if (wallpaperManager == null || (wallpaperInfo = wallpaperManager.getWallpaperInfo()) == null) {
            return;
        }
        ComponentName component = wallpaperInfo.getComponent();
        String packageName = component.getPackageName();
        if (NOTIFYABLE_PACKAGES.contains(packageName)) {
            this.mWallpaperPackage = packageName;
            String className = component.getClassName();
            String[] strArr = NOTIFYABLE_WALLPAPERS;
            for (int i = 0; i < 8; i++) {
                if (className.startsWith(strArr[i])) {
                    this.mShouldBroadcastNotifications = true;
                    return;
                }
            }
        }
    }
}

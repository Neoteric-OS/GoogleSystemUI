package com.android.systemui.people.widget;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.LauncherApps;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.UserHandle;
import android.os.UserManager;
import android.text.TextUtils;
import android.util.Log;
import com.android.internal.app.UnlaunchableAppActivity;
import com.android.internal.logging.UiEventLogger;
import com.android.internal.logging.UiEventLoggerImpl;
import com.android.internal.statusbar.IStatusBarService;
import com.android.internal.statusbar.NotificationVisibility;
import com.android.systemui.people.PeopleSpaceUtils;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.notifcollection.CommonNotifCollection;
import com.android.systemui.statusbar.notification.collection.provider.NotificationVisibilityProviderImpl;
import com.android.systemui.statusbar.notification.collection.render.NotificationVisibilityProvider;
import com.android.systemui.wmshell.BubblesManager;
import com.android.wm.shell.bubbles.Bubble;
import com.android.wm.shell.bubbles.BubbleController;
import com.android.wm.shell.bubbles.BubbleController$$ExternalSyntheticLambda0;
import com.android.wm.shell.bubbles.BubbleController$BubblesImpl$$ExternalSyntheticLambda5;
import com.android.wm.shell.bubbles.BubbleEntry;
import com.android.wm.shell.common.HandlerExecutor;
import java.util.Optional;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class LaunchConversationActivity extends Activity {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final Executor mBgExecutor;
    public Bubble mBubble;
    public final Optional mBubblesManagerOptional;
    public final CommandQueue mCommandQueue;
    public final CommonNotifCollection mCommonNotifCollection;
    public NotificationEntry mEntryToBubble;
    public IStatusBarService mIStatusBarService;
    public boolean mIsForTesting;
    public final UiEventLogger mUiEventLogger = new UiEventLoggerImpl();
    public final UserManager mUserManager;
    public final NotificationVisibilityProvider mVisibilityProvider;

    public LaunchConversationActivity(NotificationVisibilityProvider notificationVisibilityProvider, CommonNotifCollection commonNotifCollection, Optional optional, UserManager userManager, CommandQueue commandQueue, Executor executor) {
        this.mVisibilityProvider = notificationVisibilityProvider;
        this.mCommonNotifCollection = commonNotifCollection;
        this.mBubblesManagerOptional = optional;
        this.mUserManager = userManager;
        this.mCommandQueue = commandQueue;
        commandQueue.addCallback(new CommandQueue.Callbacks() { // from class: com.android.systemui.people.widget.LaunchConversationActivity.1
            @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
            public final void appTransitionFinished(int i) {
                LaunchConversationActivity launchConversationActivity = LaunchConversationActivity.this;
                if (launchConversationActivity.mBubblesManagerOptional.isPresent()) {
                    if (launchConversationActivity.mBubble != null) {
                        BubblesManager bubblesManager = (BubblesManager) launchConversationActivity.mBubblesManagerOptional.get();
                        Bubble bubble = launchConversationActivity.mBubble;
                        BubbleController.BubblesImpl bubblesImpl = (BubbleController.BubblesImpl) bubblesManager.mBubbles;
                        ((HandlerExecutor) BubbleController.this.mMainExecutor).execute(new BubbleController$$ExternalSyntheticLambda0(4, bubblesImpl, bubble));
                    } else if (launchConversationActivity.mEntryToBubble != null) {
                        BubblesManager bubblesManager2 = (BubblesManager) launchConversationActivity.mBubblesManagerOptional.get();
                        BubbleEntry notifToBubbleEntry = bubblesManager2.notifToBubbleEntry(launchConversationActivity.mEntryToBubble);
                        BubbleController.BubblesImpl bubblesImpl2 = (BubbleController.BubblesImpl) bubblesManager2.mBubbles;
                        ((HandlerExecutor) BubbleController.this.mMainExecutor).execute(new BubbleController$BubblesImpl$$ExternalSyntheticLambda5(bubblesImpl2, notifToBubbleEntry, 0));
                    }
                }
                launchConversationActivity.mCommandQueue.removeCallback((CommandQueue.Callbacks) this);
            }
        });
        this.mBgExecutor = executor;
    }

    @Override // android.app.Activity
    public final void onCreate(Bundle bundle) {
        CommonNotifCollection commonNotifCollection;
        NotificationEntry entry;
        Bubble bubble;
        if (!this.mIsForTesting) {
            super.onCreate(bundle);
        }
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra("extra_tile_id");
        final String stringExtra2 = intent.getStringExtra("extra_package_name");
        final UserHandle userHandle = (UserHandle) intent.getParcelableExtra("extra_user_handle");
        final String stringExtra3 = intent.getStringExtra("extra_notification_key");
        if (!TextUtils.isEmpty(stringExtra)) {
            this.mUiEventLogger.log(PeopleSpaceUtils.PeopleSpaceWidgetEvent.PEOPLE_SPACE_WIDGET_CLICKED);
            try {
                if (this.mUserManager.isQuietModeEnabled(userHandle)) {
                    getApplicationContext().startActivity(UnlaunchableAppActivity.createInQuietModeDialogIntent(userHandle.getIdentifier()));
                    finish();
                    return;
                }
                if (this.mBubblesManagerOptional.isPresent()) {
                    BubbleController.BubblesImpl.CachedState cachedState = ((BubbleController.BubblesImpl) ((BubblesManager) this.mBubblesManagerOptional.get()).mBubbles).mCachedState;
                    synchronized (cachedState) {
                        bubble = (Bubble) cachedState.mShortcutIdToBubble.get(stringExtra);
                    }
                    this.mBubble = bubble;
                    NotificationEntry entry2 = ((NotifPipeline) this.mCommonNotifCollection).mNotifCollection.getEntry(stringExtra3);
                    if (this.mBubble != null || (entry2 != null && entry2.mRanking.canBubble())) {
                        this.mEntryToBubble = entry2;
                        finish();
                        return;
                    }
                }
                if (this.mIStatusBarService == null) {
                    this.mIStatusBarService = IStatusBarService.Stub.asInterface(ServiceManager.getService("statusbar"));
                }
                if (!TextUtils.isEmpty(stringExtra3) && this.mIStatusBarService != null && (commonNotifCollection = this.mCommonNotifCollection) != null && (entry = ((NotifPipeline) commonNotifCollection).mNotifCollection.getEntry(stringExtra3)) != null && entry.mRanking != null) {
                    final NotificationVisibility obtain = ((NotificationVisibilityProviderImpl) this.mVisibilityProvider).obtain(entry);
                    this.mBgExecutor.execute(new Runnable() { // from class: com.android.systemui.people.widget.LaunchConversationActivity$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            LaunchConversationActivity launchConversationActivity = LaunchConversationActivity.this;
                            String str = stringExtra2;
                            UserHandle userHandle2 = userHandle;
                            String str2 = stringExtra3;
                            NotificationVisibility notificationVisibility = obtain;
                            int i = LaunchConversationActivity.$r8$clinit;
                            launchConversationActivity.getClass();
                            try {
                                launchConversationActivity.mIStatusBarService.onNotificationClear(str, userHandle2.getIdentifier(), str2, 0, 2, notificationVisibility);
                            } catch (RemoteException e) {
                                Log.e("PeopleSpaceLaunchConv", "Exception cancelling notification:" + e);
                            }
                        }
                    });
                }
                ((LauncherApps) getApplicationContext().getSystemService(LauncherApps.class)).startShortcut(stringExtra2, stringExtra, null, null, userHandle);
            } catch (Exception e) {
                Log.e("PeopleSpaceLaunchConv", "Exception launching shortcut:" + e);
            }
        }
        finish();
    }

    public void setIsForTesting(boolean z, IStatusBarService iStatusBarService) {
        this.mIsForTesting = z;
        this.mIStatusBarService = iStatusBarService;
    }
}

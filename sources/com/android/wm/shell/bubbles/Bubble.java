package com.android.wm.shell.bubbles;

import android.app.ActivityTaskManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Person;
import android.content.Context;
import android.content.Intent;
import android.content.LocusId;
import android.content.pm.PackageManager;
import android.content.pm.ShortcutInfo;
import android.graphics.Bitmap;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.UserHandle;
import android.service.notification.NotificationListenerService;
import android.text.TextUtils;
import android.util.Log;
import androidx.compose.runtime.OpaqueKey$$ExternalSyntheticOutline0;
import com.android.internal.logging.InstanceId;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.keyguard.KeyguardClockSwitchController$$ExternalSyntheticOutline0;
import com.android.launcher3.icons.BubbleIconFactory;
import com.android.wm.shell.bubbles.BubbleController;
import com.android.wm.shell.bubbles.BubbleViewInfoTask;
import com.android.wm.shell.bubbles.Bubbles;
import com.android.wm.shell.bubbles.bar.BubbleBarExpandedView;
import com.android.wm.shell.bubbles.bar.BubbleBarLayerView;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.shared.bubbles.BubbleInfo;
import com.android.wm.shell.taskview.TaskView;
import com.android.wm.shell.taskview.TaskViewTaskController;
import com.android.wm.shell.transition.Transitions;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class Bubble implements BubbleViewProvider {
    public Intent mAppIntent;
    public String mAppName;
    public int mAppUid;
    public Bitmap mBadgeBitmap;
    public final Executor mBgExecutor;
    public BubbleBarExpandedView mBubbleBarExpandedView;
    public Bitmap mBubbleBitmap;
    public final Bubbles.BubbleMetadataFlagListener mBubbleMetadataFlagListener;
    public BubbleTaskView mBubbleTaskView;
    public String mChannelId;
    public PendingIntent mDeleteIntent;
    public int mDesiredHeight;
    public int mDesiredHeightResId;
    public int mDotColor;
    public Path mDotPath;
    public BubbleExpandedView mExpandedView;
    public int mFlags;
    public FlyoutMessage mFlyoutMessage;
    public final String mGroupKey;
    public Icon mIcon;
    public BadgedImageView mIconView;
    public boolean mInflateSynchronously;
    public BubbleViewInfoTask mInflationTask;
    public InstanceId mInstanceId;
    public PendingIntent mIntent;
    public boolean mIntentActive;
    public final Bubble$$ExternalSyntheticLambda0 mIntentCancelListener;
    public final boolean mIsAppBubble;
    public boolean mIsBubble;
    public boolean mIsDismissable;
    public boolean mIsImportantConversation;
    public boolean mIsTextChanged;
    public final String mKey;
    public long mLastAccessed;
    public long mLastUpdated;
    public final LocusId mLocusId;
    public final Executor mMainExecutor;
    public String mMetadataShortcutId;
    public int mNotificationId;
    public String mPackageName;
    public boolean mPendingIntentCanceled;
    public Bitmap mRawBadgeBitmap;
    public ShortcutInfo mShortcutInfo;
    public boolean mShouldSuppressNotificationDot;
    public boolean mShouldSuppressNotificationList;
    public boolean mShouldSuppressPeek;
    public boolean mShowBubbleUpdateDot;
    public boolean mSuppressFlyout;
    public final int mTaskId;
    public String mTitle;
    public UserHandle mUser;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class FlyoutMessage {
        public boolean isGroupChat;
        public CharSequence message;
        public Drawable senderAvatar;
        public Icon senderIcon;
        public CharSequence senderName;
    }

    public Bubble(String str, ShortcutInfo shortcutInfo, int i, int i2, String str2, int i3, String str3, boolean z, Executor executor, Executor executor2, Bubbles.BubbleMetadataFlagListener bubbleMetadataFlagListener) {
        this.mShowBubbleUpdateDot = true;
        this.mAppUid = -1;
        Objects.requireNonNull(str);
        Objects.requireNonNull(shortcutInfo);
        this.mMetadataShortcutId = shortcutInfo.getId();
        this.mShortcutInfo = shortcutInfo;
        this.mKey = str;
        this.mGroupKey = null;
        this.mLocusId = str3 != null ? new LocusId(str3) : null;
        this.mIsDismissable = z;
        this.mFlags = 0;
        this.mUser = shortcutInfo.getUserHandle();
        this.mPackageName = shortcutInfo.getPackage();
        this.mIcon = shortcutInfo.getIcon();
        this.mDesiredHeight = i;
        this.mDesiredHeightResId = i2;
        this.mTitle = str2;
        this.mShowBubbleUpdateDot = false;
        this.mMainExecutor = executor;
        this.mBgExecutor = executor2;
        this.mTaskId = i3;
        this.mBubbleMetadataFlagListener = bubbleMetadataFlagListener;
        this.mIsAppBubble = false;
    }

    public static String getAppBubbleKeyForApp(String str, UserHandle userHandle) {
        Objects.requireNonNull(str);
        Objects.requireNonNull(userHandle);
        return "key_app_bubble:" + userHandle.getIdentifier() + ":" + str;
    }

    public final BubbleInfo asBubbleBarBubble() {
        int i = this.mFlags;
        ShortcutInfo shortcutInfo = this.mShortcutInfo;
        String id = shortcutInfo != null ? shortcutInfo.getId() : this.mMetadataShortcutId;
        Icon icon = this.mIcon;
        int identifier = this.mUser.getIdentifier();
        String str = this.mPackageName;
        String str2 = this.mTitle;
        String str3 = this.mAppName;
        boolean z = this.mIsImportantConversation;
        BubbleInfo bubbleInfo = new BubbleInfo();
        bubbleInfo.mKey = this.mKey;
        bubbleInfo.mFlags = i;
        bubbleInfo.mShortcutId = id;
        bubbleInfo.mIcon = icon;
        bubbleInfo.mUserId = identifier;
        bubbleInfo.mPackageName = str;
        bubbleInfo.mTitle = str2;
        bubbleInfo.mAppName = str3;
        bubbleInfo.mIsImportantConversation = z;
        bubbleInfo.mShowAppBadge = true;
        return bubbleInfo;
    }

    public final void cleanupExpandedView(boolean z) {
        BubbleTaskView bubbleTaskView;
        BubbleExpandedView bubbleExpandedView = this.mExpandedView;
        if (bubbleExpandedView != null) {
            TaskView taskView = bubbleExpandedView.mTaskView;
            if (taskView != null) {
                taskView.setVisibility(8);
            }
            this.mExpandedView = null;
        }
        BubbleBarExpandedView bubbleBarExpandedView = this.mBubbleBarExpandedView;
        if (bubbleBarExpandedView != null) {
            bubbleBarExpandedView.mMenuViewController.hideMenu(false);
            this.mBubbleBarExpandedView = null;
        }
        if (z && (bubbleTaskView = this.mBubbleTaskView) != null) {
            if (bubbleTaskView.taskId != -1) {
                if (Transitions.ENABLE_SHELL_TRANSITIONS) {
                    bubbleTaskView.taskView.removeTask();
                } else {
                    try {
                        ActivityTaskManager.getService().removeTask(bubbleTaskView.taskId);
                    } catch (RemoteException e) {
                        String message = e.getMessage();
                        if (message == null) {
                            message = "";
                        }
                        Log.w("BubbleTaskView", message);
                    }
                }
            }
            this.mBubbleTaskView = null;
        }
        PendingIntent pendingIntent = this.mIntent;
        if (pendingIntent != null) {
            pendingIntent.unregisterCancelListener(this.mIntentCancelListener);
        }
        this.mIntentActive = false;
    }

    public final void cleanupViews() {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, -7367924038303663062L, 0, String.valueOf(this.mKey));
        }
        cleanupExpandedView(true);
        this.mIconView = null;
    }

    public final void dump(PrintWriter printWriter) {
        printWriter.print("key: ");
        printWriter.println(this.mKey);
        printWriter.print("  showInShade:   ");
        printWriter.println(showInShade());
        printWriter.print("  showDot:       ");
        printWriter.println(showDot());
        printWriter.print("  showFlyout:    ");
        printWriter.println(showFlyout());
        printWriter.print("  lastActivity:  ");
        printWriter.println(Math.max(this.mLastUpdated, this.mLastAccessed));
        printWriter.print("  desiredHeight: ");
        int i = this.mDesiredHeightResId;
        printWriter.println(i != 0 ? String.valueOf(i) : String.valueOf(this.mDesiredHeight));
        printWriter.print("  suppressNotif: ");
        printWriter.println(isEnabled(2));
        printWriter.print("  autoExpand:    ");
        printWriter.println(isEnabled(1));
        printWriter.print("  isDismissable: ");
        printWriter.println(this.mIsDismissable);
        KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(new StringBuilder("  bubbleMetadataFlagListener null?: "), this.mBubbleMetadataFlagListener == null, printWriter);
        BubbleExpandedView bubbleExpandedView = this.mExpandedView;
        if (bubbleExpandedView != null) {
            printWriter.print("  ");
            printWriter.println("BubbleExpandedView:");
            printWriter.print("  ");
            printWriter.print("  taskId: ");
            printWriter.println(bubbleExpandedView.mTaskId);
            printWriter.print("  ");
            printWriter.print("  stackView: ");
            printWriter.println(bubbleExpandedView.mStackView);
            printWriter.print("  ");
            printWriter.print("  contentVisibility: ");
            printWriter.println(bubbleExpandedView.mIsContentVisible);
            printWriter.print("  ");
            printWriter.print("  isAnimating: ");
            printWriter.println(bubbleExpandedView.mIsAnimating);
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Bubble) {
            return Objects.equals(this.mKey, ((Bubble) obj).mKey);
        }
        return false;
    }

    @Override // com.android.wm.shell.bubbles.BubbleViewProvider
    public final Bitmap getAppBadge() {
        return this.mBadgeBitmap;
    }

    public Intent getAppBubbleIntent() {
        return this.mAppIntent;
    }

    @Override // com.android.wm.shell.bubbles.BubbleViewProvider
    public final BubbleBarExpandedView getBubbleBarExpandedView() {
        return this.mBubbleBarExpandedView;
    }

    @Override // com.android.wm.shell.bubbles.BubbleViewProvider
    public final Bitmap getBubbleIcon() {
        return this.mBubbleBitmap;
    }

    @Override // com.android.wm.shell.bubbles.BubbleViewProvider
    public final int getDotColor() {
        return this.mDotColor;
    }

    @Override // com.android.wm.shell.bubbles.BubbleViewProvider
    public final Path getDotPath() {
        return this.mDotPath;
    }

    @Override // com.android.wm.shell.bubbles.BubbleViewProvider
    public final BubbleExpandedView getExpandedView() {
        return this.mExpandedView;
    }

    @Override // com.android.wm.shell.bubbles.BubbleViewProvider
    public final BadgedImageView getIconView$1() {
        return this.mIconView;
    }

    @Override // com.android.wm.shell.bubbles.BubbleViewProvider
    public final String getKey() {
        return this.mKey;
    }

    public final BubbleTaskView getOrCreateBubbleTaskView(BubbleTaskViewFactory bubbleTaskViewFactory) {
        if (this.mBubbleTaskView == null) {
            BubbleController.AnonymousClass1 anonymousClass1 = (BubbleController.AnonymousClass1) bubbleTaskViewFactory;
            anonymousClass1.getClass();
            this.mBubbleTaskView = new BubbleTaskView(new TaskView(anonymousClass1.val$context, new TaskViewTaskController(anonymousClass1.val$context, anonymousClass1.val$organizer, anonymousClass1.val$taskViewTransitions, anonymousClass1.val$syncQueue)), anonymousClass1.val$mainExecutor);
        }
        return this.mBubbleTaskView;
    }

    public final Intent getSettingsIntent(Context context) {
        Intent intent = new Intent("android.settings.APP_NOTIFICATION_BUBBLE_SETTINGS");
        intent.putExtra("android.provider.extra.APP_PACKAGE", this.mPackageName);
        int i = this.mAppUid;
        if (i == -1) {
            PackageManager packageManagerForUser = BubbleController.getPackageManagerForUser(this.mUser.getIdentifier(), context);
            if (packageManagerForUser != null) {
                try {
                    i = packageManagerForUser.getApplicationInfo(this.mShortcutInfo.getPackage(), 0).uid;
                } catch (PackageManager.NameNotFoundException e) {
                    Log.e("Bubble", "cannot find uid", e);
                }
            }
            i = -1;
        }
        if (i != -1) {
            intent.putExtra("app_uid", i);
        }
        intent.addFlags(67108864);
        intent.addFlags(268435456);
        return intent;
    }

    @Override // com.android.wm.shell.bubbles.BubbleViewProvider
    public final int getTaskId() {
        BubbleBarExpandedView bubbleBarExpandedView = this.mBubbleBarExpandedView;
        if (bubbleBarExpandedView == null) {
            BubbleExpandedView bubbleExpandedView = this.mExpandedView;
            return bubbleExpandedView != null ? bubbleExpandedView.mTaskId : this.mTaskId;
        }
        BubbleTaskViewHelper bubbleTaskViewHelper = bubbleBarExpandedView.mBubbleTaskViewHelper;
        if (bubbleTaskViewHelper != null) {
            return bubbleTaskViewHelper.mTaskId;
        }
        return -1;
    }

    public final boolean hasMetadataShortcutId() {
        String str = this.mMetadataShortcutId;
        return (str == null || str.isEmpty()) ? false : true;
    }

    public final int hashCode() {
        return Objects.hash(this.mKey);
    }

    public final void inflate(BubbleViewInfoTask.Callback callback, Context context, BubbleExpandedViewManager$Companion$fromBubbleController$1 bubbleExpandedViewManager$Companion$fromBubbleController$1, BubbleController.AnonymousClass1 anonymousClass1, BubblePositioner bubblePositioner, BubbleStackView bubbleStackView, BubbleBarLayerView bubbleBarLayerView, BubbleIconFactory bubbleIconFactory, boolean z) {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_BUBBLES, 5368920303216639299L, 0, String.valueOf(this.mKey));
        }
        BubbleViewInfoTask bubbleViewInfoTask = this.mInflationTask;
        if (bubbleViewInfoTask != null && !bubbleViewInfoTask.mFinished.get()) {
            this.mInflationTask.mCancelled.set(true);
        }
        final BubbleViewInfoTask bubbleViewInfoTask2 = new BubbleViewInfoTask(this, context, bubbleExpandedViewManager$Companion$fromBubbleController$1, anonymousClass1, bubblePositioner, bubbleStackView, bubbleBarLayerView, bubbleIconFactory, z, callback, this.mMainExecutor, this.mBgExecutor);
        this.mInflationTask = bubbleViewInfoTask2;
        if (!this.mInflateSynchronously) {
            if (bubbleViewInfoTask2.mStarted.getAndSet(true)) {
                throw new IllegalStateException("Task already started");
            }
            if (bubbleViewInfoTask2.mCancelled.get()) {
                bubbleViewInfoTask2.mFinished.set(true);
                return;
            } else {
                bubbleViewInfoTask2.mBgExecutor.execute(new Runnable() { // from class: com.android.wm.shell.bubbles.BubbleViewInfoTask$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        final BubbleViewInfoTask bubbleViewInfoTask3 = BubbleViewInfoTask.this;
                        if (bubbleViewInfoTask3.mCancelled.get()) {
                            bubbleViewInfoTask3.mFinished.set(true);
                            return;
                        }
                        final BubbleViewInfoTask.BubbleViewInfo loadViewInfo = bubbleViewInfoTask3.loadViewInfo();
                        if (bubbleViewInfoTask3.mCancelled.get()) {
                            bubbleViewInfoTask3.mFinished.set(true);
                        } else {
                            bubbleViewInfoTask3.mMainExecutor.execute(new Runnable() { // from class: com.android.wm.shell.bubbles.BubbleViewInfoTask$$ExternalSyntheticLambda1
                                @Override // java.lang.Runnable
                                public final void run() {
                                    BubbleViewInfoTask bubbleViewInfoTask4 = BubbleViewInfoTask.this;
                                    BubbleViewInfoTask.BubbleViewInfo bubbleViewInfo = loadViewInfo;
                                    if (!bubbleViewInfoTask4.mCancelled.get()) {
                                        bubbleViewInfoTask4.updateViewInfo(bubbleViewInfo);
                                    }
                                    bubbleViewInfoTask4.mFinished.set(true);
                                }
                            });
                        }
                    }
                });
                return;
            }
        }
        if (bubbleViewInfoTask2.mStarted.getAndSet(true)) {
            throw new IllegalStateException("Task already started");
        }
        if (bubbleViewInfoTask2.mCancelled.get()) {
            bubbleViewInfoTask2.mFinished.set(true);
        } else {
            bubbleViewInfoTask2.updateViewInfo(bubbleViewInfoTask2.loadViewInfo());
            bubbleViewInfoTask2.mFinished.set(true);
        }
    }

    public final boolean isEnabled(int i) {
        return (this.mFlags & i) != 0;
    }

    public final boolean isInflated() {
        return ((this.mIconView == null || this.mExpandedView == null) && this.mBubbleBarExpandedView == null) ? false : true;
    }

    public final boolean isSuppressed() {
        return (this.mFlags & 8) != 0;
    }

    public final void setEntry(BubbleEntry bubbleEntry) {
        Objects.requireNonNull(bubbleEntry);
        boolean showDot = showDot();
        this.mLastUpdated = bubbleEntry.mSbn.getPostTime();
        this.mIsBubble = bubbleEntry.mSbn.getNotification().isBubbleNotification();
        this.mPackageName = bubbleEntry.mSbn.getPackageName();
        this.mUser = bubbleEntry.mSbn.getUser();
        CharSequence charSequence = bubbleEntry.mSbn.getNotification().extras.getCharSequence("android.title");
        this.mTitle = charSequence == null ? null : charSequence.toString();
        this.mChannelId = bubbleEntry.mSbn.getNotification().getChannelId();
        this.mNotificationId = bubbleEntry.mSbn.getId();
        this.mAppUid = bubbleEntry.mSbn.getUid();
        this.mInstanceId = bubbleEntry.mSbn.getInstanceId();
        Notification notification = bubbleEntry.mSbn.getNotification();
        Class notificationStyle = notification.getNotificationStyle();
        FlyoutMessage flyoutMessage = new FlyoutMessage();
        flyoutMessage.isGroupChat = notification.extras.getBoolean("android.isGroupConversation");
        try {
            if (Notification.BigTextStyle.class.equals(notificationStyle)) {
                CharSequence charSequence2 = notification.extras.getCharSequence("android.bigText");
                if (TextUtils.isEmpty(charSequence2)) {
                    charSequence2 = notification.extras.getCharSequence("android.text");
                }
                flyoutMessage.message = charSequence2;
            } else if (Notification.MessagingStyle.class.equals(notificationStyle)) {
                Notification.MessagingStyle.Message findLatestIncomingMessage = Notification.MessagingStyle.findLatestIncomingMessage(Notification.MessagingStyle.Message.getMessagesFromBundleArray((Parcelable[]) notification.extras.get("android.messages")));
                if (findLatestIncomingMessage != null) {
                    flyoutMessage.message = findLatestIncomingMessage.getText();
                    Person senderPerson = findLatestIncomingMessage.getSenderPerson();
                    flyoutMessage.senderName = senderPerson != null ? senderPerson.getName() : null;
                    flyoutMessage.senderAvatar = null;
                    flyoutMessage.senderIcon = senderPerson != null ? senderPerson.getIcon() : null;
                }
            } else if (Notification.InboxStyle.class.equals(notificationStyle)) {
                CharSequence[] charSequenceArray = notification.extras.getCharSequenceArray("android.textLines");
                if (charSequenceArray != null && charSequenceArray.length > 0) {
                    flyoutMessage.message = charSequenceArray[charSequenceArray.length - 1];
                }
            } else if (!Notification.MediaStyle.class.equals(notificationStyle)) {
                flyoutMessage.message = notification.extras.getCharSequence("android.text");
            }
        } catch (ArrayIndexOutOfBoundsException | ClassCastException | NullPointerException e) {
            e.printStackTrace();
        }
        this.mFlyoutMessage = flyoutMessage;
        NotificationListenerService.Ranking ranking = bubbleEntry.mRanking;
        if (ranking != null) {
            this.mShortcutInfo = ranking.getConversationShortcutInfo();
            this.mIsTextChanged = bubbleEntry.mRanking.isTextChanged();
            if (bubbleEntry.mRanking.getChannel() != null) {
                this.mIsImportantConversation = bubbleEntry.mRanking.getChannel().isImportantConversation();
            }
        }
        if (bubbleEntry.getBubbleMetadata() != null) {
            this.mMetadataShortcutId = bubbleEntry.getBubbleMetadata().getShortcutId();
            this.mFlags = bubbleEntry.getBubbleMetadata().getFlags();
            this.mDesiredHeight = bubbleEntry.getBubbleMetadata().getDesiredHeight();
            this.mDesiredHeightResId = bubbleEntry.getBubbleMetadata().getDesiredHeightResId();
            this.mIcon = bubbleEntry.getBubbleMetadata().getIcon();
            if (!this.mIntentActive || this.mIntent == null) {
                PendingIntent pendingIntent = this.mIntent;
                if (pendingIntent != null) {
                    pendingIntent.unregisterCancelListener(this.mIntentCancelListener);
                }
                PendingIntent intent = bubbleEntry.getBubbleMetadata().getIntent();
                this.mIntent = intent;
                if (intent != null) {
                    intent.registerCancelListener(this.mIntentCancelListener);
                }
            } else if (bubbleEntry.getBubbleMetadata().getIntent() == null) {
                this.mIntent.unregisterCancelListener(this.mIntentCancelListener);
                this.mIntentActive = false;
                this.mIntent = null;
            }
            this.mDeleteIntent = bubbleEntry.getBubbleMetadata().getDeleteIntent();
        }
        this.mIsDismissable = bubbleEntry.mIsDismissable;
        this.mShouldSuppressNotificationDot = bubbleEntry.mShouldSuppressNotificationDot;
        this.mShouldSuppressNotificationList = bubbleEntry.mShouldSuppressNotificationList;
        this.mShouldSuppressPeek = bubbleEntry.mShouldSuppressPeek;
        if (showDot != showDot()) {
            setShowDot(showDot());
        }
    }

    public void setInflateSynchronously(boolean z) {
        this.mInflateSynchronously = z;
    }

    public void setShouldAutoExpand(boolean z) {
        Bubbles.BubbleMetadataFlagListener bubbleMetadataFlagListener;
        boolean isEnabled = isEnabled(1);
        if (z) {
            this.mFlags = 1 | this.mFlags;
        } else {
            this.mFlags &= -2;
        }
        if (isEnabled == z || (bubbleMetadataFlagListener = this.mBubbleMetadataFlagListener) == null) {
            return;
        }
        bubbleMetadataFlagListener.onBubbleMetadataFlagChanged(this);
    }

    public final void setShowDot(boolean z) {
        this.mShowBubbleUpdateDot = z;
        BadgedImageView badgedImageView = this.mIconView;
        if (badgedImageView != null) {
            badgedImageView.updateDotVisibility(true);
        }
    }

    public final void setSuppressBubble(boolean z) {
        Bubbles.BubbleMetadataFlagListener bubbleMetadataFlagListener;
        if ((this.mFlags & 4) == 0) {
            Log.e("Bubble", "calling setSuppressBubble on " + this.mKey + " when bubble not suppressable");
            return;
        }
        boolean isSuppressed = isSuppressed();
        if (z) {
            this.mFlags |= 8;
        } else {
            this.mFlags &= -9;
        }
        if (isSuppressed == z || (bubbleMetadataFlagListener = this.mBubbleMetadataFlagListener) == null) {
            return;
        }
        bubbleMetadataFlagListener.onBubbleMetadataFlagChanged(this);
    }

    public void setSuppressNotification(boolean z) {
        Bubbles.BubbleMetadataFlagListener bubbleMetadataFlagListener;
        boolean showInShade = showInShade();
        if (z) {
            this.mFlags |= 2;
        } else {
            this.mFlags &= -3;
        }
        if (showInShade() == showInShade || (bubbleMetadataFlagListener = this.mBubbleMetadataFlagListener) == null) {
            return;
        }
        bubbleMetadataFlagListener.onBubbleMetadataFlagChanged(this);
    }

    @Override // com.android.wm.shell.bubbles.BubbleViewProvider
    public final void setTaskViewVisibility() {
        BubbleExpandedView bubbleExpandedView = this.mExpandedView;
        if (bubbleExpandedView != null) {
            bubbleExpandedView.setContentVisibility(false);
        }
    }

    public void setTextChangedForTest(boolean z) {
        this.mIsTextChanged = z;
    }

    @Override // com.android.wm.shell.bubbles.BubbleViewProvider
    public final boolean showDot() {
        return (!this.mShowBubbleUpdateDot || this.mShouldSuppressNotificationDot || isEnabled(2)) ? false : true;
    }

    public boolean showFlyout() {
        return (this.mSuppressFlyout || this.mShouldSuppressPeek || isEnabled(2) || this.mShouldSuppressNotificationList) ? false : true;
    }

    public final boolean showInShade() {
        return (isEnabled(2) && this.mIsDismissable) ? false : true;
    }

    public final String toString() {
        return OpaqueKey$$ExternalSyntheticOutline0.m(new StringBuilder("Bubble{"), this.mKey, '}');
    }

    public Bubble(Intent intent, UserHandle userHandle, Icon icon, String str, Executor executor, Executor executor2) {
        this.mAppUid = -1;
        this.mGroupKey = null;
        this.mLocusId = null;
        this.mFlags = 0;
        this.mUser = userHandle;
        this.mIcon = icon;
        this.mIsAppBubble = true;
        this.mKey = str;
        this.mShowBubbleUpdateDot = false;
        this.mMainExecutor = executor;
        this.mBgExecutor = executor2;
        this.mTaskId = -1;
        this.mAppIntent = intent;
        this.mDesiredHeight = Integer.MAX_VALUE;
        this.mPackageName = intent.getPackage();
    }

    /* JADX WARN: Type inference failed for: r4v1, types: [com.android.wm.shell.bubbles.Bubble$$ExternalSyntheticLambda0] */
    public Bubble(BubbleEntry bubbleEntry, Bubbles.BubbleMetadataFlagListener bubbleMetadataFlagListener, final Bubbles.PendingIntentCanceledListener pendingIntentCanceledListener, final Executor executor, Executor executor2) {
        this.mShowBubbleUpdateDot = true;
        this.mAppUid = -1;
        this.mIsAppBubble = false;
        this.mKey = bubbleEntry.mSbn.getKey();
        this.mGroupKey = bubbleEntry.mSbn.getGroupKey();
        this.mLocusId = bubbleEntry.mSbn.getNotification().getLocusId();
        this.mBubbleMetadataFlagListener = bubbleMetadataFlagListener;
        this.mIntentCancelListener = new PendingIntent.CancelListener() { // from class: com.android.wm.shell.bubbles.Bubble$$ExternalSyntheticLambda0
            public final void onCanceled(PendingIntent pendingIntent) {
                final Bubble bubble = Bubble.this;
                Executor executor3 = executor;
                final Bubbles.PendingIntentCanceledListener pendingIntentCanceledListener2 = pendingIntentCanceledListener;
                PendingIntent pendingIntent2 = bubble.mIntent;
                if (pendingIntent2 != null) {
                    pendingIntent2.unregisterCancelListener(bubble.mIntentCancelListener);
                }
                executor3.execute(new Runnable() { // from class: com.android.wm.shell.bubbles.Bubble$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        Bubble bubble2 = Bubble.this;
                        BubbleController bubbleController = ((BubbleController$$ExternalSyntheticLambda7) pendingIntentCanceledListener2).f$0;
                        bubbleController.getClass();
                        if (bubble2.mIntent == null) {
                            return;
                        }
                        if (bubble2.mIntentActive || bubbleController.mBubbleData.hasBubbleInStackWithKey(bubble2.mKey)) {
                            bubble2.mPendingIntentCanceled = true;
                        } else {
                            ((HandlerExecutor) bubbleController.mMainExecutor).execute(new BubbleController$$ExternalSyntheticLambda0(1, bubbleController, bubble2));
                        }
                    }
                });
            }
        };
        this.mMainExecutor = executor;
        this.mBgExecutor = executor2;
        this.mTaskId = -1;
        setEntry(bubbleEntry);
    }
}

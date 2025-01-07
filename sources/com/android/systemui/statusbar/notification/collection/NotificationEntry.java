package com.android.systemui.statusbar.notification.collection;

import android.app.Notification;
import android.net.Uri;
import android.os.SystemClock;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.view.ContentInfo;
import com.android.systemui.statusbar.InflationTask;
import com.android.systemui.statusbar.notification.icon.IconPack;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRowController;
import com.android.systemui.statusbar.notification.row.NotificationContentView;
import com.android.systemui.util.ListenerSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationEntry extends ListEntry {
    public EditedSuggestionInfo editedSuggestionInfo;
    public boolean hasSentReply;
    public long initializationTime;
    public boolean interruption;
    public long lastFullScreenIntentLaunchTime;
    public long lastRemoteInputSent;
    public boolean mBlockable;
    public Notification.BubbleMetadata mBubbleMetadata;
    public int mBucket;
    public int mCachedContrastColor;
    public int mCachedContrastColorIsFor;
    public int mCancellationReason;
    public final List mDismissInterceptors;
    public DismissState mDismissState;
    public boolean mExpandAnimationRunning;
    public boolean mHasEverBeenGroupChild;
    public boolean mHasEverBeenGroupSummary;
    public final StateFlowImpl mHeadsUpStatusBarText;
    public final StateFlowImpl mHeadsUpStatusBarTextPublic;
    public IconPack mIcons;
    public boolean mIsDemoted;
    public boolean mIsHeadsUpEntry;
    public boolean mIsMarkedForUserTriggeredMovement;
    public final String mKey;
    public final List mLifetimeExtenders;
    public final ListenerSet mOnSensitivityChangedListeners;
    public NotificationListenerService.Ranking mRanking;
    public boolean mRemoteEditImeAnimatingAway;
    public boolean mRemoteEditImeVisible;
    public final StateFlowImpl mRichOngoingContentModel;
    public ExpandableNotificationRowController mRowController;
    public InflationTask mRunningTask;
    public StatusBarNotification mSbn;
    public final StateFlowImpl mSensitive;
    public ContentInfo remoteInputAttachment;
    public String remoteInputMimeType;
    public CharSequence remoteInputText;
    public CharSequence remoteInputTextWhenReset;
    public Uri remoteInputUri;
    public ExpandableNotificationRow row;
    public int targetSdk;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DismissState {
        public static final /* synthetic */ DismissState[] $VALUES;
        public static final DismissState DISMISSED;
        public static final DismissState NOT_DISMISSED;
        public static final DismissState PARENT_DISMISSED;

        static {
            DismissState dismissState = new DismissState("NOT_DISMISSED", 0);
            NOT_DISMISSED = dismissState;
            DismissState dismissState2 = new DismissState("DISMISSED", 1);
            DISMISSED = dismissState2;
            DismissState dismissState3 = new DismissState("PARENT_DISMISSED", 2);
            PARENT_DISMISSED = dismissState3;
            $VALUES = new DismissState[]{dismissState, dismissState2, dismissState3};
        }

        public static DismissState valueOf(String str) {
            return (DismissState) Enum.valueOf(DismissState.class, str);
        }

        public static DismissState[] values() {
            return (DismissState[]) $VALUES.clone();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class EditedSuggestionInfo {
        public final int index;
        public final CharSequence originalText;

        public EditedSuggestionInfo(int i, CharSequence charSequence) {
            this.originalText = charSequence;
            this.index = i;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface OnSensitivityChangedListener {
        void onSensitivityChanged(NotificationEntry notificationEntry);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public NotificationEntry(android.service.notification.StatusBarNotification r8, android.service.notification.NotificationListenerService.Ranking r9, long r10) {
        /*
            r7 = this;
            java.util.Objects.requireNonNull(r8)
            java.lang.String r0 = r8.getKey()
            java.util.Objects.requireNonNull(r0)
            r7.<init>(r10, r0)
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
            r7.mLifetimeExtenders = r10
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
            r7.mDismissInterceptors = r10
            r10 = -1
            r7.mCancellationReason = r10
            com.android.systemui.statusbar.notification.collection.NotificationEntry$DismissState r10 = com.android.systemui.statusbar.notification.collection.NotificationEntry.DismissState.NOT_DISMISSED
            r7.mDismissState = r10
            com.android.systemui.statusbar.notification.icon.IconPack r10 = new com.android.systemui.statusbar.notification.icon.IconPack
            r2 = 0
            r3 = 0
            r1 = 0
            r4 = 0
            r5 = 0
            r11 = 0
            r0 = r10
            r6 = r11
            r0.<init>(r1, r2, r3, r4, r5, r6)
            r7.mIcons = r10
            r0 = -2000(0xfffffffffffff830, double:NaN)
            r7.lastFullScreenIntentLaunchTime = r0
            r10 = 1
            r7.mCachedContrastColor = r10
            r7.mCachedContrastColorIsFor = r10
            r7.mRunningTask = r11
            r7.lastRemoteInputSent = r0
            kotlinx.coroutines.flow.StateFlowImpl r10 = kotlinx.coroutines.flow.StateFlowKt.MutableStateFlow(r11)
            r7.mHeadsUpStatusBarText = r10
            kotlinx.coroutines.flow.StateFlowImpl r10 = kotlinx.coroutines.flow.StateFlowKt.MutableStateFlow(r11)
            r7.mHeadsUpStatusBarTextPublic = r10
            kotlinx.coroutines.flow.StateFlowImpl r10 = kotlinx.coroutines.flow.StateFlowKt.MutableStateFlow(r11)
            r7.mRichOngoingContentModel = r10
            r10 = -1
            r7.initializationTime = r10
            java.lang.Boolean r10 = java.lang.Boolean.TRUE
            kotlinx.coroutines.flow.StateFlowImpl r10 = kotlinx.coroutines.flow.StateFlowKt.MutableStateFlow(r10)
            r7.mSensitive = r10
            com.android.systemui.util.ListenerSet r10 = new com.android.systemui.util.ListenerSet
            r10.<init>()
            r7.mOnSensitivityChangedListeners = r10
            r10 = 5
            r7.mBucket = r10
            r10 = 0
            r7.mIsDemoted = r10
            java.lang.String r10 = r8.getKey()
            r7.mKey = r10
            r7.setSbn(r8)
            r7.setRanking(r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.collection.NotificationEntry.<init>(android.service.notification.StatusBarNotification, android.service.notification.NotificationListenerService$Ranking, long):void");
    }

    public final boolean abortTask() {
        InflationTask inflationTask = this.mRunningTask;
        if (inflationTask == null) {
            return false;
        }
        inflationTask.abort();
        this.mRunningTask = null;
        return true;
    }

    public final List getAttachedNotifChildren() {
        List attachedChildren;
        ExpandableNotificationRow expandableNotificationRow = this.row;
        if (expandableNotificationRow == null || (attachedChildren = expandableNotificationRow.getAttachedChildren()) == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = attachedChildren.iterator();
        while (it.hasNext()) {
            arrayList.add(((ExpandableNotificationRow) it.next()).mEntry);
        }
        return arrayList;
    }

    @Override // com.android.systemui.statusbar.notification.collection.ListEntry
    public final String getKey() {
        return this.mKey;
    }

    public final String getNotificationStyle() {
        ExpandableNotificationRow expandableNotificationRow = this.row;
        if (expandableNotificationRow != null && expandableNotificationRow.mIsSummaryWithChildren) {
            return "summary";
        }
        Class notificationStyle = this.mSbn.getNotification().getNotificationStyle();
        return notificationStyle == null ? "nostyle" : notificationStyle.getSimpleName();
    }

    public InflationTask getRunningTask() {
        return this.mRunningTask;
    }

    public final boolean hasFinishedInitialization() {
        return this.initializationTime != -1 && SystemClock.elapsedRealtime() > this.initializationTime + 400;
    }

    public final boolean isBubble() {
        return (this.mSbn.getNotification().flags & 4096) != 0;
    }

    public final boolean isCanceled() {
        return this.mCancellationReason != -1;
    }

    public final boolean isClearable() {
        if (!this.mSbn.isClearable()) {
            return false;
        }
        List attachedNotifChildren = getAttachedNotifChildren();
        if (attachedNotifChildren == null) {
            return true;
        }
        ArrayList arrayList = (ArrayList) attachedNotifChildren;
        if (arrayList.size() <= 0) {
            return true;
        }
        for (int i = 0; i < arrayList.size(); i++) {
            if (!((NotificationEntry) arrayList.get(i)).mSbn.isClearable()) {
                return false;
            }
        }
        return true;
    }

    public boolean isDemoted() {
        return this.mIsDemoted;
    }

    public boolean isExemptFromDndVisualSuppression() {
        Notification notification = this.mSbn.getNotification();
        return (Objects.equals(notification.category, "call") || Objects.equals(notification.category, "msg") || Objects.equals(notification.category, "alarm") || Objects.equals(notification.category, "event") || Objects.equals(notification.category, "reminder") || (!this.mSbn.getNotification().isFgsOrUij() && !this.mSbn.getNotification().isMediaNotification() && this.mBlockable)) ? false : true;
    }

    public final boolean isRowPinned() {
        ExpandableNotificationRow expandableNotificationRow = this.row;
        return expandableNotificationRow != null && expandableNotificationRow.mIsPinned;
    }

    public final boolean isStickyAndNotDemoted() {
        boolean z = (this.mSbn.getNotification().flags & 16384) != 0;
        if (!z && !this.mIsDemoted) {
            this.mIsDemoted = true;
        }
        return z && !this.mIsDemoted;
    }

    public final boolean rowExists() {
        return this.row != null;
    }

    public final void setHeadsUp(boolean z) {
        ExpandableNotificationRow expandableNotificationRow = this.row;
        if (expandableNotificationRow != null) {
            boolean isAboveShelf = expandableNotificationRow.isAboveShelf();
            int intrinsicHeight = expandableNotificationRow.getIntrinsicHeight();
            expandableNotificationRow.mIsHeadsUp = z;
            NotificationContentView notificationContentView = expandableNotificationRow.mPrivateLayout;
            notificationContentView.mIsHeadsUp = z;
            notificationContentView.selectLayout(false, true);
            notificationContentView.updateExpandButtonsDuringLayout(notificationContentView.mExpandable, false);
            if (expandableNotificationRow.mIsSummaryWithChildren) {
                expandableNotificationRow.mChildrenContainer.updateGroupOverflow();
            }
            if (intrinsicHeight != expandableNotificationRow.getIntrinsicHeight()) {
                expandableNotificationRow.notifyHeightChanged(false);
            }
            if (z) {
                expandableNotificationRow.mMustStayOnScreen = true;
                expandableNotificationRow.setAboveShelf(true);
            } else if (expandableNotificationRow.isAboveShelf() != isAboveShelf) {
                expandableNotificationRow.mAboveShelfChangedListener.onAboveShelfStateChanged(!isAboveShelf);
            }
        }
    }

    public final void setRanking(NotificationListenerService.Ranking ranking) {
        Objects.requireNonNull(ranking.getKey());
        String key = ranking.getKey();
        String str = this.mKey;
        if (!key.equals(str)) {
            throw new IllegalArgumentException("New key " + ranking.getKey() + " doesn't match existing key " + str);
        }
        NotificationListenerService.Ranking withAudiblyAlertedInfo = ranking.withAudiblyAlertedInfo(this.mRanking);
        this.mRanking = withAudiblyAlertedInfo;
        if (withAudiblyAlertedInfo.getChannel() == null) {
            this.mBlockable = false;
        } else if (!this.mRanking.getChannel().isImportanceLockedByCriticalDeviceFunction() || this.mRanking.getChannel().isBlockable()) {
            this.mBlockable = true;
        } else {
            this.mBlockable = false;
        }
    }

    public final void setSbn(StatusBarNotification statusBarNotification) {
        Objects.requireNonNull(statusBarNotification);
        Objects.requireNonNull(statusBarNotification.getKey());
        String key = statusBarNotification.getKey();
        String str = this.mKey;
        if (key.equals(str)) {
            this.mSbn = statusBarNotification;
            this.mBubbleMetadata = statusBarNotification.getNotification().getBubbleMetadata();
        } else {
            throw new IllegalArgumentException("New key " + statusBarNotification.getKey() + " doesn't match existing key " + str);
        }
    }

    public final boolean shouldSuppressVisualEffect(int i) {
        return (isExemptFromDndVisualSuppression() || (this.mRanking.getSuppressedVisualEffects() & i) == 0) ? false : true;
    }

    @Override // com.android.systemui.statusbar.notification.collection.ListEntry
    public final NotificationEntry getRepresentativeEntry() {
        return this;
    }
}

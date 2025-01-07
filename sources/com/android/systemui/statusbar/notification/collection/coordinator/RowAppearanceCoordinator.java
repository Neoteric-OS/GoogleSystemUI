package com.android.systemui.statusbar.notification.collection.coordinator;

import android.content.Context;
import com.android.systemui.statusbar.notification.AssistantFeedbackController;
import com.android.systemui.statusbar.notification.FeedbackIcon;
import com.android.systemui.statusbar.notification.collection.ListEntry;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.listbuilder.NotifSection;
import com.android.systemui.statusbar.notification.collection.listbuilder.OnAfterRenderEntryListener;
import com.android.systemui.statusbar.notification.collection.listbuilder.OnBeforeRenderListListener;
import com.android.systemui.statusbar.notification.collection.provider.SectionStyleProvider;
import com.android.systemui.statusbar.notification.collection.render.NotifViewController;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRowController;
import com.android.systemui.statusbar.notification.row.NotificationContentView;
import com.android.systemui.statusbar.notification.row.wrapper.NotificationHeaderViewWrapper;
import com.android.systemui.statusbar.notification.stack.NotificationChildrenContainer;
import com.android.wm.shell.R;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class RowAppearanceCoordinator implements Coordinator {
    public NotificationEntry entryToExpand;
    public final boolean mAlwaysExpandNonGroupedNotification;
    public final AssistantFeedbackController mAssistantFeedbackController;
    public final boolean mAutoExpandFirstNotification;
    public final SectionStyleProvider mSectionStyleProvider;

    public RowAppearanceCoordinator(Context context, AssistantFeedbackController assistantFeedbackController, SectionStyleProvider sectionStyleProvider) {
        this.mAssistantFeedbackController = assistantFeedbackController;
        this.mSectionStyleProvider = sectionStyleProvider;
        this.mAlwaysExpandNonGroupedNotification = context.getResources().getBoolean(R.bool.config_alwaysExpandNonGroupedNotifications);
        this.mAutoExpandFirstNotification = context.getResources().getBoolean(R.bool.config_autoExpandFirstNotification);
    }

    @Override // com.android.systemui.statusbar.notification.collection.coordinator.Coordinator
    public final void attach(NotifPipeline notifPipeline) {
        notifPipeline.addOnBeforeRenderListListener(new OnBeforeRenderListListener() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.RowAppearanceCoordinator$attach$1
            @Override // com.android.systemui.statusbar.notification.collection.listbuilder.OnBeforeRenderListListener
            public final void onBeforeRenderList$1(List list) {
                NotificationEntry representativeEntry;
                RowAppearanceCoordinator rowAppearanceCoordinator = RowAppearanceCoordinator.this;
                rowAppearanceCoordinator.getClass();
                ListEntry listEntry = (ListEntry) CollectionsKt.firstOrNull(list);
                NotificationEntry notificationEntry = null;
                if (listEntry != null && (representativeEntry = listEntry.getRepresentativeEntry()) != null) {
                    NotifSection notifSection = representativeEntry.mAttachState.section;
                    Intrinsics.checkNotNull(notifSection);
                    Set set = rowAppearanceCoordinator.mSectionStyleProvider.lowPrioritySections;
                    if (set == null) {
                        set = null;
                    }
                    if (!set.contains(notifSection.sectioner)) {
                        notificationEntry = representativeEntry;
                    }
                }
                rowAppearanceCoordinator.entryToExpand = notificationEntry;
            }
        });
        notifPipeline.mRenderStageManager.onAfterRenderEntryListeners.add(new OnAfterRenderEntryListener() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.RowAppearanceCoordinator$attach$2
            @Override // com.android.systemui.statusbar.notification.collection.listbuilder.OnAfterRenderEntryListener
            public final void onAfterRenderEntry(NotificationEntry notificationEntry, NotifViewController notifViewController) {
                RowAppearanceCoordinator rowAppearanceCoordinator = RowAppearanceCoordinator.this;
                boolean z = rowAppearanceCoordinator.mAlwaysExpandNonGroupedNotification || (rowAppearanceCoordinator.mAutoExpandFirstNotification && notificationEntry.equals(rowAppearanceCoordinator.entryToExpand));
                ExpandableNotificationRow expandableNotificationRow = ((ExpandableNotificationRowController) notifViewController).mView;
                if (z != expandableNotificationRow.mIsSystemExpanded) {
                    boolean isExpanded = expandableNotificationRow.isExpanded(false);
                    expandableNotificationRow.mIsSystemExpanded = z;
                    expandableNotificationRow.notifyHeightChanged(false);
                    expandableNotificationRow.onExpansionChanged(false, isExpanded);
                    if (expandableNotificationRow.mIsSummaryWithChildren) {
                        expandableNotificationRow.mChildrenContainer.updateGroupOverflow();
                        if (expandableNotificationRow.mIsSummaryWithChildren) {
                            expandableNotificationRow.mChildrenContainer.updateExpansionStates();
                        }
                    }
                }
                AssistantFeedbackController assistantFeedbackController = rowAppearanceCoordinator.mAssistantFeedbackController;
                FeedbackIcon feedbackIcon = (FeedbackIcon) assistantFeedbackController.mIcons.get(assistantFeedbackController.getFeedbackStatus(notificationEntry));
                if (expandableNotificationRow.mIsSummaryWithChildren) {
                    NotificationChildrenContainer notificationChildrenContainer = expandableNotificationRow.mChildrenContainer;
                    NotificationHeaderViewWrapper notificationHeaderViewWrapper = notificationChildrenContainer.mGroupHeaderWrapper;
                    if (notificationHeaderViewWrapper != null) {
                        notificationHeaderViewWrapper.setFeedbackIcon(feedbackIcon);
                    }
                    NotificationHeaderViewWrapper notificationHeaderViewWrapper2 = notificationChildrenContainer.mMinimizedGroupHeaderWrapper;
                    if (notificationHeaderViewWrapper2 != null) {
                        notificationHeaderViewWrapper2.setFeedbackIcon(feedbackIcon);
                    }
                }
                NotificationContentView notificationContentView = expandableNotificationRow.mPrivateLayout;
                if (notificationContentView.mContractedChild != null) {
                    notificationContentView.mContractedWrapper.setFeedbackIcon(feedbackIcon);
                }
                if (notificationContentView.mExpandedChild != null) {
                    notificationContentView.mExpandedWrapper.setFeedbackIcon(feedbackIcon);
                }
                if (notificationContentView.mHeadsUpChild != null) {
                    notificationContentView.mHeadsUpWrapper.setFeedbackIcon(feedbackIcon);
                }
                NotificationContentView notificationContentView2 = expandableNotificationRow.mPublicLayout;
                if (notificationContentView2.mContractedChild != null) {
                    notificationContentView2.mContractedWrapper.setFeedbackIcon(feedbackIcon);
                }
                if (notificationContentView2.mExpandedChild != null) {
                    notificationContentView2.mExpandedWrapper.setFeedbackIcon(feedbackIcon);
                }
                if (notificationContentView2.mHeadsUpChild != null) {
                    notificationContentView2.mHeadsUpWrapper.setFeedbackIcon(feedbackIcon);
                }
            }
        });
    }
}

package com.android.systemui.statusbar.notification.collection.coordinator;

import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.notification.collection.ListEntry;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifFilter;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifSectioner;
import com.android.systemui.statusbar.notification.collection.provider.HighPriorityProvider;
import com.android.systemui.statusbar.notification.collection.render.NodeController;
import com.android.systemui.statusbar.notification.collection.render.SectionHeaderNodeControllerImpl;
import com.android.systemui.statusbar.notification.stack.SectionHeaderView;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class RankingCoordinator implements Coordinator {
    public boolean mHasMinimizedEntries;
    public boolean mHasSilentEntries;
    public final HighPriorityProvider mHighPriorityProvider;
    public final SectionHeaderNodeControllerImpl mSilentHeaderController;
    public final NodeController mSilentNodeController;
    public final StatusBarStateController mStatusBarStateController;
    public final AnonymousClass1 mAlertingNotifSectioner = new NotifSectioner(this, 0) { // from class: com.android.systemui.statusbar.notification.collection.coordinator.RankingCoordinator.1
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ RankingCoordinator this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        {
            super("Alerting", 5);
            this.$r8$classId = r2;
            switch (r2) {
                case 1:
                    this.this$0 = this;
                    super("Silent", 6);
                    break;
                case 2:
                    this.this$0 = this;
                    super("Minimized", 6);
                    break;
                default:
                    this.this$0 = this;
                    break;
            }
        }

        @Override // com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifSectioner
        public final NodeController getHeaderNodeController() {
            switch (this.$r8$classId) {
            }
            return this.this$0.mSilentNodeController;
        }

        @Override // com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifSectioner
        public final boolean isInSection(ListEntry listEntry) {
            switch (this.$r8$classId) {
                case 0:
                    return this.this$0.mHighPriorityProvider.isHighPriority(listEntry, true);
                case 1:
                    return (this.this$0.mHighPriorityProvider.isHighPriority(listEntry, true) || listEntry.getRepresentativeEntry().mRanking.isAmbient()) ? false : true;
                default:
                    return !this.this$0.mHighPriorityProvider.isHighPriority(listEntry, true) && listEntry.getRepresentativeEntry().mRanking.isAmbient();
            }
        }

        @Override // com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifSectioner
        public void onEntriesUpdated(List list) {
            switch (this.$r8$classId) {
                case 1:
                    RankingCoordinator rankingCoordinator = this.this$0;
                    rankingCoordinator.mHasSilentEntries = false;
                    int i = 0;
                    while (true) {
                        ArrayList arrayList = (ArrayList) list;
                        if (i < arrayList.size()) {
                            if (((ListEntry) arrayList.get(i)).getRepresentativeEntry().mSbn.isClearable()) {
                                rankingCoordinator.mHasSilentEntries = true;
                            } else {
                                i++;
                            }
                        }
                    }
                    SectionHeaderNodeControllerImpl sectionHeaderNodeControllerImpl = rankingCoordinator.mSilentHeaderController;
                    boolean z = rankingCoordinator.mHasMinimizedEntries | rankingCoordinator.mHasSilentEntries;
                    sectionHeaderNodeControllerImpl.clearAllButtonEnabled = z;
                    SectionHeaderView sectionHeaderView = sectionHeaderNodeControllerImpl._view;
                    if (sectionHeaderView != null) {
                        sectionHeaderView.mClearAllButton.setVisibility(z ? 0 : 8);
                        break;
                    }
                    break;
                case 2:
                    RankingCoordinator rankingCoordinator2 = this.this$0;
                    rankingCoordinator2.mHasMinimizedEntries = false;
                    int i2 = 0;
                    while (true) {
                        ArrayList arrayList2 = (ArrayList) list;
                        if (i2 < arrayList2.size()) {
                            if (((ListEntry) arrayList2.get(i2)).getRepresentativeEntry().mSbn.isClearable()) {
                                rankingCoordinator2.mHasMinimizedEntries = true;
                            } else {
                                i2++;
                            }
                        }
                    }
                    SectionHeaderNodeControllerImpl sectionHeaderNodeControllerImpl2 = rankingCoordinator2.mSilentHeaderController;
                    boolean z2 = rankingCoordinator2.mHasMinimizedEntries | rankingCoordinator2.mHasSilentEntries;
                    sectionHeaderNodeControllerImpl2.clearAllButtonEnabled = z2;
                    SectionHeaderView sectionHeaderView2 = sectionHeaderNodeControllerImpl2._view;
                    if (sectionHeaderView2 != null) {
                        sectionHeaderView2.mClearAllButton.setVisibility(z2 ? 0 : 8);
                        break;
                    }
                    break;
            }
        }
    };
    public final AnonymousClass1 mSilentNotifSectioner = new NotifSectioner(this, 1) { // from class: com.android.systemui.statusbar.notification.collection.coordinator.RankingCoordinator.1
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ RankingCoordinator this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        {
            super("Alerting", 5);
            this.$r8$classId = r2;
            switch (r2) {
                case 1:
                    this.this$0 = this;
                    super("Silent", 6);
                    break;
                case 2:
                    this.this$0 = this;
                    super("Minimized", 6);
                    break;
                default:
                    this.this$0 = this;
                    break;
            }
        }

        @Override // com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifSectioner
        public final NodeController getHeaderNodeController() {
            switch (this.$r8$classId) {
            }
            return this.this$0.mSilentNodeController;
        }

        @Override // com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifSectioner
        public final boolean isInSection(ListEntry listEntry) {
            switch (this.$r8$classId) {
                case 0:
                    return this.this$0.mHighPriorityProvider.isHighPriority(listEntry, true);
                case 1:
                    return (this.this$0.mHighPriorityProvider.isHighPriority(listEntry, true) || listEntry.getRepresentativeEntry().mRanking.isAmbient()) ? false : true;
                default:
                    return !this.this$0.mHighPriorityProvider.isHighPriority(listEntry, true) && listEntry.getRepresentativeEntry().mRanking.isAmbient();
            }
        }

        @Override // com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifSectioner
        public void onEntriesUpdated(List list) {
            switch (this.$r8$classId) {
                case 1:
                    RankingCoordinator rankingCoordinator = this.this$0;
                    rankingCoordinator.mHasSilentEntries = false;
                    int i = 0;
                    while (true) {
                        ArrayList arrayList = (ArrayList) list;
                        if (i < arrayList.size()) {
                            if (((ListEntry) arrayList.get(i)).getRepresentativeEntry().mSbn.isClearable()) {
                                rankingCoordinator.mHasSilentEntries = true;
                            } else {
                                i++;
                            }
                        }
                    }
                    SectionHeaderNodeControllerImpl sectionHeaderNodeControllerImpl = rankingCoordinator.mSilentHeaderController;
                    boolean z = rankingCoordinator.mHasMinimizedEntries | rankingCoordinator.mHasSilentEntries;
                    sectionHeaderNodeControllerImpl.clearAllButtonEnabled = z;
                    SectionHeaderView sectionHeaderView = sectionHeaderNodeControllerImpl._view;
                    if (sectionHeaderView != null) {
                        sectionHeaderView.mClearAllButton.setVisibility(z ? 0 : 8);
                        break;
                    }
                    break;
                case 2:
                    RankingCoordinator rankingCoordinator2 = this.this$0;
                    rankingCoordinator2.mHasMinimizedEntries = false;
                    int i2 = 0;
                    while (true) {
                        ArrayList arrayList2 = (ArrayList) list;
                        if (i2 < arrayList2.size()) {
                            if (((ListEntry) arrayList2.get(i2)).getRepresentativeEntry().mSbn.isClearable()) {
                                rankingCoordinator2.mHasMinimizedEntries = true;
                            } else {
                                i2++;
                            }
                        }
                    }
                    SectionHeaderNodeControllerImpl sectionHeaderNodeControllerImpl2 = rankingCoordinator2.mSilentHeaderController;
                    boolean z2 = rankingCoordinator2.mHasMinimizedEntries | rankingCoordinator2.mHasSilentEntries;
                    sectionHeaderNodeControllerImpl2.clearAllButtonEnabled = z2;
                    SectionHeaderView sectionHeaderView2 = sectionHeaderNodeControllerImpl2._view;
                    if (sectionHeaderView2 != null) {
                        sectionHeaderView2.mClearAllButton.setVisibility(z2 ? 0 : 8);
                        break;
                    }
                    break;
            }
        }
    };
    public final AnonymousClass1 mMinimizedNotifSectioner = new NotifSectioner(this, 2) { // from class: com.android.systemui.statusbar.notification.collection.coordinator.RankingCoordinator.1
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ RankingCoordinator this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        {
            super("Alerting", 5);
            this.$r8$classId = r2;
            switch (r2) {
                case 1:
                    this.this$0 = this;
                    super("Silent", 6);
                    break;
                case 2:
                    this.this$0 = this;
                    super("Minimized", 6);
                    break;
                default:
                    this.this$0 = this;
                    break;
            }
        }

        @Override // com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifSectioner
        public final NodeController getHeaderNodeController() {
            switch (this.$r8$classId) {
            }
            return this.this$0.mSilentNodeController;
        }

        @Override // com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifSectioner
        public final boolean isInSection(ListEntry listEntry) {
            switch (this.$r8$classId) {
                case 0:
                    return this.this$0.mHighPriorityProvider.isHighPriority(listEntry, true);
                case 1:
                    return (this.this$0.mHighPriorityProvider.isHighPriority(listEntry, true) || listEntry.getRepresentativeEntry().mRanking.isAmbient()) ? false : true;
                default:
                    return !this.this$0.mHighPriorityProvider.isHighPriority(listEntry, true) && listEntry.getRepresentativeEntry().mRanking.isAmbient();
            }
        }

        @Override // com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifSectioner
        public void onEntriesUpdated(List list) {
            switch (this.$r8$classId) {
                case 1:
                    RankingCoordinator rankingCoordinator = this.this$0;
                    rankingCoordinator.mHasSilentEntries = false;
                    int i = 0;
                    while (true) {
                        ArrayList arrayList = (ArrayList) list;
                        if (i < arrayList.size()) {
                            if (((ListEntry) arrayList.get(i)).getRepresentativeEntry().mSbn.isClearable()) {
                                rankingCoordinator.mHasSilentEntries = true;
                            } else {
                                i++;
                            }
                        }
                    }
                    SectionHeaderNodeControllerImpl sectionHeaderNodeControllerImpl = rankingCoordinator.mSilentHeaderController;
                    boolean z = rankingCoordinator.mHasMinimizedEntries | rankingCoordinator.mHasSilentEntries;
                    sectionHeaderNodeControllerImpl.clearAllButtonEnabled = z;
                    SectionHeaderView sectionHeaderView = sectionHeaderNodeControllerImpl._view;
                    if (sectionHeaderView != null) {
                        sectionHeaderView.mClearAllButton.setVisibility(z ? 0 : 8);
                        break;
                    }
                    break;
                case 2:
                    RankingCoordinator rankingCoordinator2 = this.this$0;
                    rankingCoordinator2.mHasMinimizedEntries = false;
                    int i2 = 0;
                    while (true) {
                        ArrayList arrayList2 = (ArrayList) list;
                        if (i2 < arrayList2.size()) {
                            if (((ListEntry) arrayList2.get(i2)).getRepresentativeEntry().mSbn.isClearable()) {
                                rankingCoordinator2.mHasMinimizedEntries = true;
                            } else {
                                i2++;
                            }
                        }
                    }
                    SectionHeaderNodeControllerImpl sectionHeaderNodeControllerImpl2 = rankingCoordinator2.mSilentHeaderController;
                    boolean z2 = rankingCoordinator2.mHasMinimizedEntries | rankingCoordinator2.mHasSilentEntries;
                    sectionHeaderNodeControllerImpl2.clearAllButtonEnabled = z2;
                    SectionHeaderView sectionHeaderView2 = sectionHeaderNodeControllerImpl2._view;
                    if (sectionHeaderView2 != null) {
                        sectionHeaderView2.mClearAllButton.setVisibility(z2 ? 0 : 8);
                        break;
                    }
                    break;
            }
        }
    };
    public final AnonymousClass4 mSuspendedFilter = new AnonymousClass4("IsSuspendedFilter");
    public final AnonymousClass5 mDndVisualEffectsFilter = new NotifFilter() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.RankingCoordinator.5
        @Override // com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifFilter
        public final boolean shouldFilterOut(NotificationEntry notificationEntry, long j) {
            RankingCoordinator rankingCoordinator = RankingCoordinator.this;
            if ((rankingCoordinator.mStatusBarStateController.isDozing() || rankingCoordinator.mStatusBarStateController.getDozeAmount() == 1.0f) && notificationEntry.shouldSuppressVisualEffect(128)) {
                return true;
            }
            return !rankingCoordinator.mStatusBarStateController.isDozing() && notificationEntry.shouldSuppressVisualEffect(256);
        }
    };
    public final AnonymousClass6 mStatusBarStateCallback = new StatusBarStateController.StateListener() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.RankingCoordinator.6
        public boolean mPrevDozeAmountIsOne = false;

        @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
        public final void onDozeAmountChanged(float f, float f2) {
            super.onDozeAmountChanged(f, f2);
            boolean z = f == 1.0f;
            if (this.mPrevDozeAmountIsOne != z) {
                invalidateList("dozeAmount changed to ".concat(z ? "one" : "not one"));
                this.mPrevDozeAmountIsOne = z;
            }
        }

        @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
        public final void onDozingChanged(boolean z) {
            invalidateList("onDozingChanged to " + z);
        }
    };

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.collection.coordinator.RankingCoordinator$4, reason: invalid class name */
    public final class AnonymousClass4 extends NotifFilter {
        @Override // com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifFilter
        public final boolean shouldFilterOut(NotificationEntry notificationEntry, long j) {
            return notificationEntry.mRanking.isSuspended();
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.android.systemui.statusbar.notification.collection.coordinator.RankingCoordinator$1] */
    /* JADX WARN: Type inference failed for: r0v1, types: [com.android.systemui.statusbar.notification.collection.coordinator.RankingCoordinator$1] */
    /* JADX WARN: Type inference failed for: r0v2, types: [com.android.systemui.statusbar.notification.collection.coordinator.RankingCoordinator$1] */
    /* JADX WARN: Type inference failed for: r0v4, types: [com.android.systemui.statusbar.notification.collection.coordinator.RankingCoordinator$5] */
    /* JADX WARN: Type inference failed for: r0v5, types: [com.android.systemui.statusbar.notification.collection.coordinator.RankingCoordinator$6] */
    public RankingCoordinator(StatusBarStateController statusBarStateController, HighPriorityProvider highPriorityProvider, SectionHeaderNodeControllerImpl sectionHeaderNodeControllerImpl, NodeController nodeController) {
        this.mStatusBarStateController = statusBarStateController;
        this.mHighPriorityProvider = highPriorityProvider;
        this.mSilentNodeController = nodeController;
        this.mSilentHeaderController = sectionHeaderNodeControllerImpl;
    }

    @Override // com.android.systemui.statusbar.notification.collection.coordinator.Coordinator
    public final void attach(NotifPipeline notifPipeline) {
        this.mStatusBarStateController.addCallback(this.mStatusBarStateCallback);
        notifPipeline.addPreGroupFilter(this.mSuspendedFilter);
        notifPipeline.addPreGroupFilter(this.mDndVisualEffectsFilter);
    }
}

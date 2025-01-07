package com.android.systemui.statusbar.notification.collection.coordinator;

import android.app.NotificationChannel;
import com.android.systemui.statusbar.notification.collection.ListEntry;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifSectioner;
import com.android.systemui.statusbar.notification.collection.render.NodeController;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BundleCoordinator implements Coordinator {
    public final NodeController newsHeaderController;
    public final BundleCoordinator$newsSectioner$1 newsSectioner;
    public final NodeController promoHeaderController;
    public final BundleCoordinator$newsSectioner$1 promoSectioner;
    public final NodeController recsHeaderController;
    public final BundleCoordinator$newsSectioner$1 recsSectioner;
    public final NodeController socialHeaderController;
    public final BundleCoordinator$newsSectioner$1 socialSectioner;

    public BundleCoordinator(NodeController nodeController, NodeController nodeController2, NodeController nodeController3, NodeController nodeController4) {
        this.newsHeaderController = nodeController;
        this.socialHeaderController = nodeController2;
        this.recsHeaderController = nodeController3;
        this.promoHeaderController = nodeController4;
        final int i = 0;
        new NotifSectioner(this, i) { // from class: com.android.systemui.statusbar.notification.collection.coordinator.BundleCoordinator$newsSectioner$1
            public final /* synthetic */ int $r8$classId;
            public final /* synthetic */ BundleCoordinator this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super("News", 10);
                this.$r8$classId = i;
                switch (i) {
                    case 1:
                        this.this$0 = this;
                        super("Promotions", 13);
                        break;
                    case 2:
                        this.this$0 = this;
                        super("Recommendations", 12);
                        break;
                    case 3:
                        this.this$0 = this;
                        super("Social", 11);
                        break;
                    default:
                        this.this$0 = this;
                        break;
                }
            }

            @Override // com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifSectioner
            public final NodeController getHeaderNodeController() {
                switch (this.$r8$classId) {
                    case 0:
                        return this.this$0.newsHeaderController;
                    case 1:
                        return this.this$0.promoHeaderController;
                    case 2:
                        return this.this$0.recsHeaderController;
                    default:
                        return this.this$0.socialHeaderController;
                }
            }

            @Override // com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifSectioner
            public final boolean isInSection(ListEntry listEntry) {
                NotificationChannel channel;
                NotificationChannel channel2;
                NotificationChannel channel3;
                NotificationChannel channel4;
                switch (this.$r8$classId) {
                    case 0:
                        NotificationEntry representativeEntry = listEntry.getRepresentativeEntry();
                        return Intrinsics.areEqual((representativeEntry == null || (channel = representativeEntry.mRanking.getChannel()) == null) ? null : channel.getId(), "android.app.news");
                    case 1:
                        NotificationEntry representativeEntry2 = listEntry.getRepresentativeEntry();
                        return Intrinsics.areEqual((representativeEntry2 == null || (channel2 = representativeEntry2.mRanking.getChannel()) == null) ? null : channel2.getId(), "android.app.promotions");
                    case 2:
                        NotificationEntry representativeEntry3 = listEntry.getRepresentativeEntry();
                        return Intrinsics.areEqual((representativeEntry3 == null || (channel3 = representativeEntry3.mRanking.getChannel()) == null) ? null : channel3.getId(), "android.app.recs");
                    default:
                        NotificationEntry representativeEntry4 = listEntry.getRepresentativeEntry();
                        return Intrinsics.areEqual((representativeEntry4 == null || (channel4 = representativeEntry4.mRanking.getChannel()) == null) ? null : channel4.getId(), "android.app.social");
                }
            }
        };
        final int i2 = 3;
        new NotifSectioner(this, i2) { // from class: com.android.systemui.statusbar.notification.collection.coordinator.BundleCoordinator$newsSectioner$1
            public final /* synthetic */ int $r8$classId;
            public final /* synthetic */ BundleCoordinator this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super("News", 10);
                this.$r8$classId = i2;
                switch (i2) {
                    case 1:
                        this.this$0 = this;
                        super("Promotions", 13);
                        break;
                    case 2:
                        this.this$0 = this;
                        super("Recommendations", 12);
                        break;
                    case 3:
                        this.this$0 = this;
                        super("Social", 11);
                        break;
                    default:
                        this.this$0 = this;
                        break;
                }
            }

            @Override // com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifSectioner
            public final NodeController getHeaderNodeController() {
                switch (this.$r8$classId) {
                    case 0:
                        return this.this$0.newsHeaderController;
                    case 1:
                        return this.this$0.promoHeaderController;
                    case 2:
                        return this.this$0.recsHeaderController;
                    default:
                        return this.this$0.socialHeaderController;
                }
            }

            @Override // com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifSectioner
            public final boolean isInSection(ListEntry listEntry) {
                NotificationChannel channel;
                NotificationChannel channel2;
                NotificationChannel channel3;
                NotificationChannel channel4;
                switch (this.$r8$classId) {
                    case 0:
                        NotificationEntry representativeEntry = listEntry.getRepresentativeEntry();
                        return Intrinsics.areEqual((representativeEntry == null || (channel = representativeEntry.mRanking.getChannel()) == null) ? null : channel.getId(), "android.app.news");
                    case 1:
                        NotificationEntry representativeEntry2 = listEntry.getRepresentativeEntry();
                        return Intrinsics.areEqual((representativeEntry2 == null || (channel2 = representativeEntry2.mRanking.getChannel()) == null) ? null : channel2.getId(), "android.app.promotions");
                    case 2:
                        NotificationEntry representativeEntry3 = listEntry.getRepresentativeEntry();
                        return Intrinsics.areEqual((representativeEntry3 == null || (channel3 = representativeEntry3.mRanking.getChannel()) == null) ? null : channel3.getId(), "android.app.recs");
                    default:
                        NotificationEntry representativeEntry4 = listEntry.getRepresentativeEntry();
                        return Intrinsics.areEqual((representativeEntry4 == null || (channel4 = representativeEntry4.mRanking.getChannel()) == null) ? null : channel4.getId(), "android.app.social");
                }
            }
        };
        final int i3 = 2;
        new NotifSectioner(this, i3) { // from class: com.android.systemui.statusbar.notification.collection.coordinator.BundleCoordinator$newsSectioner$1
            public final /* synthetic */ int $r8$classId;
            public final /* synthetic */ BundleCoordinator this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super("News", 10);
                this.$r8$classId = i3;
                switch (i3) {
                    case 1:
                        this.this$0 = this;
                        super("Promotions", 13);
                        break;
                    case 2:
                        this.this$0 = this;
                        super("Recommendations", 12);
                        break;
                    case 3:
                        this.this$0 = this;
                        super("Social", 11);
                        break;
                    default:
                        this.this$0 = this;
                        break;
                }
            }

            @Override // com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifSectioner
            public final NodeController getHeaderNodeController() {
                switch (this.$r8$classId) {
                    case 0:
                        return this.this$0.newsHeaderController;
                    case 1:
                        return this.this$0.promoHeaderController;
                    case 2:
                        return this.this$0.recsHeaderController;
                    default:
                        return this.this$0.socialHeaderController;
                }
            }

            @Override // com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifSectioner
            public final boolean isInSection(ListEntry listEntry) {
                NotificationChannel channel;
                NotificationChannel channel2;
                NotificationChannel channel3;
                NotificationChannel channel4;
                switch (this.$r8$classId) {
                    case 0:
                        NotificationEntry representativeEntry = listEntry.getRepresentativeEntry();
                        return Intrinsics.areEqual((representativeEntry == null || (channel = representativeEntry.mRanking.getChannel()) == null) ? null : channel.getId(), "android.app.news");
                    case 1:
                        NotificationEntry representativeEntry2 = listEntry.getRepresentativeEntry();
                        return Intrinsics.areEqual((representativeEntry2 == null || (channel2 = representativeEntry2.mRanking.getChannel()) == null) ? null : channel2.getId(), "android.app.promotions");
                    case 2:
                        NotificationEntry representativeEntry3 = listEntry.getRepresentativeEntry();
                        return Intrinsics.areEqual((representativeEntry3 == null || (channel3 = representativeEntry3.mRanking.getChannel()) == null) ? null : channel3.getId(), "android.app.recs");
                    default:
                        NotificationEntry representativeEntry4 = listEntry.getRepresentativeEntry();
                        return Intrinsics.areEqual((representativeEntry4 == null || (channel4 = representativeEntry4.mRanking.getChannel()) == null) ? null : channel4.getId(), "android.app.social");
                }
            }
        };
        final int i4 = 1;
        new NotifSectioner(this, i4) { // from class: com.android.systemui.statusbar.notification.collection.coordinator.BundleCoordinator$newsSectioner$1
            public final /* synthetic */ int $r8$classId;
            public final /* synthetic */ BundleCoordinator this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super("News", 10);
                this.$r8$classId = i4;
                switch (i4) {
                    case 1:
                        this.this$0 = this;
                        super("Promotions", 13);
                        break;
                    case 2:
                        this.this$0 = this;
                        super("Recommendations", 12);
                        break;
                    case 3:
                        this.this$0 = this;
                        super("Social", 11);
                        break;
                    default:
                        this.this$0 = this;
                        break;
                }
            }

            @Override // com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifSectioner
            public final NodeController getHeaderNodeController() {
                switch (this.$r8$classId) {
                    case 0:
                        return this.this$0.newsHeaderController;
                    case 1:
                        return this.this$0.promoHeaderController;
                    case 2:
                        return this.this$0.recsHeaderController;
                    default:
                        return this.this$0.socialHeaderController;
                }
            }

            @Override // com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifSectioner
            public final boolean isInSection(ListEntry listEntry) {
                NotificationChannel channel;
                NotificationChannel channel2;
                NotificationChannel channel3;
                NotificationChannel channel4;
                switch (this.$r8$classId) {
                    case 0:
                        NotificationEntry representativeEntry = listEntry.getRepresentativeEntry();
                        return Intrinsics.areEqual((representativeEntry == null || (channel = representativeEntry.mRanking.getChannel()) == null) ? null : channel.getId(), "android.app.news");
                    case 1:
                        NotificationEntry representativeEntry2 = listEntry.getRepresentativeEntry();
                        return Intrinsics.areEqual((representativeEntry2 == null || (channel2 = representativeEntry2.mRanking.getChannel()) == null) ? null : channel2.getId(), "android.app.promotions");
                    case 2:
                        NotificationEntry representativeEntry3 = listEntry.getRepresentativeEntry();
                        return Intrinsics.areEqual((representativeEntry3 == null || (channel3 = representativeEntry3.mRanking.getChannel()) == null) ? null : channel3.getId(), "android.app.recs");
                    default:
                        NotificationEntry representativeEntry4 = listEntry.getRepresentativeEntry();
                        return Intrinsics.areEqual((representativeEntry4 == null || (channel4 = representativeEntry4.mRanking.getChannel()) == null) ? null : channel4.getId(), "android.app.social");
                }
            }
        };
    }

    @Override // com.android.systemui.statusbar.notification.collection.coordinator.Coordinator
    public final void attach(NotifPipeline notifPipeline) {
    }
}

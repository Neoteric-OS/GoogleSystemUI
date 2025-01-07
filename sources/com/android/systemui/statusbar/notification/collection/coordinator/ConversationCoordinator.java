package com.android.systemui.statusbar.notification.collection.coordinator;

import android.app.NotificationChannel;
import com.android.systemui.statusbar.notification.collection.GroupEntry;
import com.android.systemui.statusbar.notification.collection.ListEntry;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.ShadeListBuilder;
import com.android.systemui.statusbar.notification.collection.ShadeListBuilder$$ExternalSyntheticLambda0;
import com.android.systemui.statusbar.notification.collection.listbuilder.OnBeforeRenderListListener;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifComparator;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifPromoter;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifSectioner;
import com.android.systemui.statusbar.notification.collection.provider.HighPriorityProvider;
import com.android.systemui.statusbar.notification.collection.render.NodeController;
import com.android.systemui.statusbar.notification.icon.IconManager;
import com.android.systemui.statusbar.notification.people.PeopleNotificationIdentifierImpl;
import com.android.systemui.util.Assert;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ConversationCoordinator implements Coordinator {
    public final IconManager conversationIconManager;
    public final HighPriorityProvider highPriorityProvider;
    public final ConversationCoordinator$notifComparator$1 notifComparator;
    public final ConversationCoordinator$peopleAlertingSectioner$1 peopleAlertingSectioner;
    public final PeopleNotificationIdentifierImpl peopleNotificationIdentifier;
    public final ConversationCoordinator$peopleSilentSectioner$1 peopleSilentSectioner;
    public final ConversationCoordinator$peopleAlertingSectioner$1 priorityPeopleSectioner;
    public final Map promotedEntriesToSummaryOfSameChannel = new LinkedHashMap();
    public final ConversationCoordinator$onBeforeRenderListListener$1 onBeforeRenderListListener = new OnBeforeRenderListListener() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.ConversationCoordinator$onBeforeRenderListListener$1
        @Override // com.android.systemui.statusbar.notification.collection.listbuilder.OnBeforeRenderListListener
        public final void onBeforeRenderList$1(List list) {
            ConversationCoordinator conversationCoordinator = ConversationCoordinator.this;
            Map map = conversationCoordinator.promotedEntriesToSummaryOfSameChannel;
            ArrayList arrayList = new ArrayList();
            for (Map.Entry entry : map.entrySet()) {
                NotificationEntry notificationEntry = (NotificationEntry) entry.getKey();
                NotificationEntry notificationEntry2 = (NotificationEntry) entry.getValue();
                GroupEntry groupEntry = notificationEntry2.mAttachState.parent;
                String str = null;
                if (groupEntry != null && !groupEntry.equals(notificationEntry.mAttachState.parent) && groupEntry.mAttachState.parent != null && Intrinsics.areEqual(groupEntry.mSummary, notificationEntry2)) {
                    List list2 = groupEntry.mUnmodifiableChildren;
                    if (!list2.isEmpty()) {
                        Iterator it = list2.iterator();
                        while (it.hasNext()) {
                            if (Intrinsics.areEqual(((NotificationEntry) it.next()).mRanking.getChannel(), notificationEntry2.mRanking.getChannel())) {
                                break;
                            }
                        }
                    }
                    str = notificationEntry2.mKey;
                }
                if (str != null) {
                    arrayList.add(str);
                }
            }
            IconManager iconManager = conversationCoordinator.conversationIconManager;
            iconManager.getClass();
            Set set = CollectionsKt.toSet(arrayList);
            boolean areEqual = Intrinsics.areEqual(iconManager.unimportantConversationKeys, set);
            iconManager.unimportantConversationKeys = set;
            if (!areEqual) {
                iconManager.recalculateForImportantConversationChange();
            }
            conversationCoordinator.promotedEntriesToSummaryOfSameChannel.clear();
        }
    };
    public final ConversationCoordinator$notificationPromoter$1 notificationPromoter = new NotifPromoter() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.ConversationCoordinator$notificationPromoter$1
        {
            super("ConversationCoordinator");
        }

        @Override // com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifPromoter
        public final boolean shouldPromoteToTopLevel(NotificationEntry notificationEntry) {
            NotificationChannel channel = notificationEntry.mRanking.getChannel();
            boolean z = false;
            if (channel != null && channel.isImportantConversation()) {
                z = true;
            }
            if (z) {
                GroupEntry groupEntry = notificationEntry.mAttachState.parent;
                NotificationEntry notificationEntry2 = groupEntry != null ? groupEntry.mSummary : null;
                if (notificationEntry2 != null && Intrinsics.areEqual(notificationEntry.mRanking.getChannel(), notificationEntry2.mRanking.getChannel())) {
                    ConversationCoordinator.this.promotedEntriesToSummaryOfSameChannel.put(notificationEntry, notificationEntry2);
                }
            }
            return z;
        }
    };

    /* JADX WARN: Type inference failed for: r1v2, types: [com.android.systemui.statusbar.notification.collection.coordinator.ConversationCoordinator$onBeforeRenderListListener$1] */
    /* JADX WARN: Type inference failed for: r1v3, types: [com.android.systemui.statusbar.notification.collection.coordinator.ConversationCoordinator$notificationPromoter$1] */
    /* JADX WARN: Type inference failed for: r1v4, types: [com.android.systemui.statusbar.notification.collection.coordinator.ConversationCoordinator$peopleAlertingSectioner$1] */
    /* JADX WARN: Type inference failed for: r1v5, types: [com.android.systemui.statusbar.notification.collection.coordinator.ConversationCoordinator$peopleAlertingSectioner$1] */
    public ConversationCoordinator(PeopleNotificationIdentifierImpl peopleNotificationIdentifierImpl, IconManager iconManager, HighPriorityProvider highPriorityProvider) {
        this.peopleNotificationIdentifier = peopleNotificationIdentifierImpl;
        this.conversationIconManager = iconManager;
        this.highPriorityProvider = highPriorityProvider;
        final int i = 1;
        this.priorityPeopleSectioner = new NotifSectioner(this, i) { // from class: com.android.systemui.statusbar.notification.collection.coordinator.ConversationCoordinator$peopleAlertingSectioner$1
            public final /* synthetic */ int $r8$classId;
            public final /* synthetic */ ConversationCoordinator this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super("People(alerting)", 4);
                this.$r8$classId = i;
                switch (i) {
                    case 1:
                        this.this$0 = this;
                        super("Priority People", 7);
                        break;
                    default:
                        this.this$0 = this;
                        break;
                }
            }

            @Override // com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifSectioner
            public NotifComparator getComparator() {
                switch (this.$r8$classId) {
                    case 0:
                        return null;
                    default:
                        return super.getComparator();
                }
            }

            @Override // com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifSectioner
            public NodeController getHeaderNodeController() {
                switch (this.$r8$classId) {
                    case 0:
                        this.this$0.getClass();
                        return null;
                    default:
                        return super.getHeaderNodeController();
                }
            }

            @Override // com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifSectioner
            public final boolean isInSection(ListEntry listEntry) {
                switch (this.$r8$classId) {
                    case 0:
                        ConversationCoordinator conversationCoordinator = this.this$0;
                        return conversationCoordinator.highPriorityProvider.isHighPriorityConversation(listEntry) || conversationCoordinator.getPeopleType(listEntry) != 0;
                    default:
                        return this.this$0.getPeopleType(listEntry) == 3;
                }
            }
        };
        final int i2 = 0;
        this.peopleAlertingSectioner = new NotifSectioner(this, i2) { // from class: com.android.systemui.statusbar.notification.collection.coordinator.ConversationCoordinator$peopleAlertingSectioner$1
            public final /* synthetic */ int $r8$classId;
            public final /* synthetic */ ConversationCoordinator this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super("People(alerting)", 4);
                this.$r8$classId = i2;
                switch (i2) {
                    case 1:
                        this.this$0 = this;
                        super("Priority People", 7);
                        break;
                    default:
                        this.this$0 = this;
                        break;
                }
            }

            @Override // com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifSectioner
            public NotifComparator getComparator() {
                switch (this.$r8$classId) {
                    case 0:
                        return null;
                    default:
                        return super.getComparator();
                }
            }

            @Override // com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifSectioner
            public NodeController getHeaderNodeController() {
                switch (this.$r8$classId) {
                    case 0:
                        this.this$0.getClass();
                        return null;
                    default:
                        return super.getHeaderNodeController();
                }
            }

            @Override // com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifSectioner
            public final boolean isInSection(ListEntry listEntry) {
                switch (this.$r8$classId) {
                    case 0:
                        ConversationCoordinator conversationCoordinator = this.this$0;
                        return conversationCoordinator.highPriorityProvider.isHighPriorityConversation(listEntry) || conversationCoordinator.getPeopleType(listEntry) != 0;
                    default:
                        return this.this$0.getPeopleType(listEntry) == 3;
                }
            }
        };
        new ConversationCoordinator$peopleSilentSectioner$1("People(silent)", 4);
        new NotifComparator() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.ConversationCoordinator$notifComparator$1
            {
                super("People");
            }

            @Override // com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifComparator
            public final int compare(ListEntry listEntry, ListEntry listEntry2) {
                return Intrinsics.compare(ConversationCoordinator.this.getPeopleType(listEntry2), ConversationCoordinator.this.getPeopleType(listEntry));
            }
        };
    }

    @Override // com.android.systemui.statusbar.notification.collection.coordinator.Coordinator
    public final void attach(NotifPipeline notifPipeline) {
        ShadeListBuilder shadeListBuilder = notifPipeline.mShadeListBuilder;
        shadeListBuilder.getClass();
        Assert.isMainThread();
        shadeListBuilder.mPipelineState.requireState();
        List list = shadeListBuilder.mNotifPromoters;
        ConversationCoordinator$notificationPromoter$1 conversationCoordinator$notificationPromoter$1 = this.notificationPromoter;
        list.add(conversationCoordinator$notificationPromoter$1);
        conversationCoordinator$notificationPromoter$1.mListener = new ShadeListBuilder$$ExternalSyntheticLambda0(shadeListBuilder, 7);
        notifPipeline.addOnBeforeRenderListListener(this.onBeforeRenderListListener);
    }

    public final int getPeopleType(ListEntry listEntry) {
        NotificationEntry representativeEntry = listEntry.getRepresentativeEntry();
        if (representativeEntry != null) {
            return this.peopleNotificationIdentifier.getPeopleNotificationType(representativeEntry);
        }
        return 0;
    }
}

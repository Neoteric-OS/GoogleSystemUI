package com.android.systemui.statusbar.notification;

import android.app.Notification;
import android.content.Context;
import android.os.Handler;
import android.service.notification.NotificationListenerService;
import android.view.View;
import com.android.internal.widget.ConversationLayout;
import com.android.systemui.statusbar.notification.ConversationNotificationManager;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.inflation.BindEventManager$Listener;
import com.android.systemui.statusbar.notification.collection.inflation.BindEventManagerImpl;
import com.android.systemui.statusbar.notification.collection.notifcollection.CommonNotifCollection;
import com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.NotificationContentView;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import kotlin.Function;
import kotlin.Pair;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionAdapter;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.EmptySequence;
import kotlin.sequences.FilteringSequence;
import kotlin.sequences.FilteringSequence$iterator$1;
import kotlin.sequences.SequencesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ConversationNotificationManager {
    public final Context context;
    public final Handler mainHandler;
    public final CommonNotifCollection notifCollection;
    public final ConcurrentHashMap states = new ConcurrentHashMap();
    public boolean notifPanelCollapsed = true;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.ConversationNotificationManager$2, reason: invalid class name */
    public final /* synthetic */ class AnonymousClass2 implements BindEventManager$Listener, FunctionAdapter {
        public AnonymousClass2() {
        }

        public final boolean equals(Object obj) {
            if ((obj instanceof BindEventManager$Listener) && (obj instanceof FunctionAdapter)) {
                return getFunctionDelegate().equals(((FunctionAdapter) obj).getFunctionDelegate());
            }
            return false;
        }

        @Override // kotlin.jvm.internal.FunctionAdapter
        public final Function getFunctionDelegate() {
            return new FunctionReferenceImpl(1, ConversationNotificationManager.this, ConversationNotificationManager.class, "onEntryViewBound", "onEntryViewBound(Lcom/android/systemui/statusbar/notification/collection/NotificationEntry;)V", 0);
        }

        public final int hashCode() {
            return getFunctionDelegate().hashCode();
        }

        @Override // com.android.systemui.statusbar.notification.collection.inflation.BindEventManager$Listener
        public final void onViewBound(NotificationEntry notificationEntry) {
            ConversationNotificationManager conversationNotificationManager = ConversationNotificationManager.this;
            conversationNotificationManager.getClass();
            if (notificationEntry.mRanking.isConversation()) {
                ExpandableNotificationRow expandableNotificationRow = notificationEntry.row;
                if (expandableNotificationRow != null) {
                    expandableNotificationRow.mExpansionChangedListener = new ConversationNotificationManager$onEntryViewBound$1(notificationEntry, conversationNotificationManager);
                }
                boolean z = false;
                if (expandableNotificationRow != null && expandableNotificationRow.isExpanded(false)) {
                    z = true;
                }
                ConversationNotificationManager.onEntryViewBound$updateCount(conversationNotificationManager, notificationEntry, z);
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ConversationState {
        public final Notification notification;
        public final int unreadCount;

        public ConversationState(int i, Notification notification) {
            this.unreadCount = i;
            this.notification = notification;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ConversationState)) {
                return false;
            }
            ConversationState conversationState = (ConversationState) obj;
            return this.unreadCount == conversationState.unreadCount && Intrinsics.areEqual(this.notification, conversationState.notification);
        }

        public final int hashCode() {
            return this.notification.hashCode() + (Integer.hashCode(this.unreadCount) * 31);
        }

        public final String toString() {
            return "ConversationState(unreadCount=" + this.unreadCount + ", notification=" + this.notification + ")";
        }
    }

    public ConversationNotificationManager(BindEventManagerImpl bindEventManagerImpl, Context context, CommonNotifCollection commonNotifCollection, Handler handler) {
        this.context = context;
        this.notifCollection = commonNotifCollection;
        this.mainHandler = handler;
        ((NotifPipeline) commonNotifCollection).addCollectionListener(new NotifCollectionListener() { // from class: com.android.systemui.statusbar.notification.ConversationNotificationManager.1
            @Override // com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener
            public final void onEntryRemoved(NotificationEntry notificationEntry, int i) {
                ConversationNotificationManager.this.states.remove(notificationEntry.mKey);
            }

            @Override // com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener
            public final void onRankingUpdate(NotificationListenerService.RankingMap rankingMap) {
                final ConversationNotificationManager conversationNotificationManager = ConversationNotificationManager.this;
                conversationNotificationManager.getClass();
                NotificationListenerService.Ranking ranking = new NotificationListenerService.Ranking();
                FilteringSequence$iterator$1 filteringSequence$iterator$1 = new FilteringSequence$iterator$1(SequencesKt.mapNotNull(new CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1(conversationNotificationManager.states.keySet()), new Function1() { // from class: com.android.systemui.statusbar.notification.ConversationNotificationManager$updateNotificationRanking$activeConversationEntries$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return ((NotifPipeline) ConversationNotificationManager.this.notifCollection).mNotifCollection.getEntry((String) obj);
                    }
                }));
                while (filteringSequence$iterator$1.hasNext()) {
                    NotificationEntry notificationEntry = (NotificationEntry) filteringSequence$iterator$1.next();
                    if (rankingMap.getRanking(notificationEntry.mSbn.getKey(), ranking) && ranking.isConversation()) {
                        final boolean isImportantConversation = ranking.getChannel().isImportantConversation();
                        ExpandableNotificationRow expandableNotificationRow = notificationEntry.row;
                        if (expandableNotificationRow != null) {
                            NotificationContentView[] notificationContentViewArr = expandableNotificationRow.mLayouts;
                            NotificationContentView[] notificationContentViewArr2 = (NotificationContentView[]) Arrays.copyOf(notificationContentViewArr, notificationContentViewArr.length);
                            if (notificationContentViewArr2 != null) {
                                FilteringSequence$iterator$1 filteringSequence$iterator$12 = new FilteringSequence$iterator$1(new FilteringSequence(SequencesKt.mapNotNull(SequencesKt.flatMap(ArraysKt.asSequence(notificationContentViewArr2), ConversationNotificationManager$updateNotificationRanking$1.INSTANCE), new Function1() { // from class: com.android.systemui.statusbar.notification.ConversationNotificationManager$updateNotificationRanking$2
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Object invoke(Object obj) {
                                        ConversationLayout conversationLayout = (View) obj;
                                        if (conversationLayout instanceof ConversationLayout) {
                                            return conversationLayout;
                                        }
                                        return null;
                                    }
                                }), false, new Function1() { // from class: com.android.systemui.statusbar.notification.ConversationNotificationManager$updateNotificationRanking$3
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public final Object invoke(Object obj) {
                                        return Boolean.valueOf(((ConversationLayout) obj).isImportantConversation() == isImportantConversation);
                                    }
                                }));
                                while (filteringSequence$iterator$12.hasNext()) {
                                    final ConversationLayout conversationLayout = (ConversationLayout) filteringSequence$iterator$12.next();
                                    if (isImportantConversation && notificationEntry.mIsMarkedForUserTriggeredMovement) {
                                        conversationNotificationManager.mainHandler.postDelayed(new Runnable() { // from class: com.android.systemui.statusbar.notification.ConversationNotificationManager$updateNotificationRanking$4$1
                                            @Override // java.lang.Runnable
                                            public final void run() {
                                                conversationLayout.setIsImportantConversation(isImportantConversation, true);
                                            }
                                        }, 960L);
                                    } else {
                                        conversationLayout.setIsImportantConversation(isImportantConversation, false);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
        bindEventManagerImpl.listeners.listeners.addIfAbsent(new AnonymousClass2());
    }

    public static final void onEntryViewBound$updateCount(ConversationNotificationManager conversationNotificationManager, NotificationEntry notificationEntry, boolean z) {
        if (z) {
            if (conversationNotificationManager.notifPanelCollapsed) {
                ExpandableNotificationRow expandableNotificationRow = notificationEntry.row;
                if (expandableNotificationRow == null) {
                    return;
                }
                if (!(!expandableNotificationRow.mIsPinned ? false : expandableNotificationRow.mExpandedWhenPinned)) {
                    return;
                }
            }
            conversationNotificationManager.states.compute(notificationEntry.mKey, ConversationNotificationManager$resetCount$1.INSTANCE);
            ExpandableNotificationRow expandableNotificationRow2 = notificationEntry.row;
            if (expandableNotificationRow2 != null) {
                resetBadgeUi(expandableNotificationRow2);
            }
        }
    }

    public static void resetBadgeUi(ExpandableNotificationRow expandableNotificationRow) {
        NotificationContentView[] notificationContentViewArr = expandableNotificationRow.mLayouts;
        NotificationContentView[] notificationContentViewArr2 = (NotificationContentView[]) Arrays.copyOf(notificationContentViewArr, notificationContentViewArr.length);
        FilteringSequence$iterator$1 filteringSequence$iterator$1 = new FilteringSequence$iterator$1(SequencesKt.mapNotNull(SequencesKt.flatMap(notificationContentViewArr2 != null ? ArraysKt.asSequence(notificationContentViewArr2) : EmptySequence.INSTANCE, new Function1() { // from class: com.android.systemui.statusbar.notification.ConversationNotificationManager$resetBadgeUi$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ArraysKt.asSequence(((NotificationContentView) obj).getAllViews());
            }
        }), new Function1() { // from class: com.android.systemui.statusbar.notification.ConversationNotificationManager$resetBadgeUi$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                ConversationLayout conversationLayout = (View) obj;
                if (conversationLayout instanceof ConversationLayout) {
                    return conversationLayout;
                }
                return null;
            }
        }));
        while (filteringSequence$iterator$1.hasNext()) {
            ((ConversationLayout) filteringSequence$iterator$1.next()).setUnreadCount(0);
        }
    }

    public final void onNotificationPanelExpandStateChanged(boolean z) {
        this.notifPanelCollapsed = z;
        if (z) {
            return;
        }
        FilteringSequence mapNotNull = SequencesKt.mapNotNull(new CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1(this.states.entrySet()), new Function1() { // from class: com.android.systemui.statusbar.notification.ConversationNotificationManager$onNotificationPanelExpandStateChanged$expanded$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                ExpandableNotificationRow expandableNotificationRow;
                String str = (String) ((Map.Entry) obj).getKey();
                NotificationEntry entry = ((NotifPipeline) ConversationNotificationManager.this.notifCollection).mNotifCollection.getEntry(str);
                if (entry == null || (expandableNotificationRow = entry.row) == null || !expandableNotificationRow.isExpanded(false)) {
                    return null;
                }
                return new Pair(str, entry);
            }
        });
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        FilteringSequence$iterator$1 filteringSequence$iterator$1 = new FilteringSequence$iterator$1(mapNotNull);
        while (filteringSequence$iterator$1.hasNext()) {
            Pair pair = (Pair) filteringSequence$iterator$1.next();
            linkedHashMap.put(pair.component1(), pair.component2());
        }
        final Map optimizeReadOnlyMap = MapsKt.optimizeReadOnlyMap(linkedHashMap);
        this.states.replaceAll(new BiFunction() { // from class: com.android.systemui.statusbar.notification.ConversationNotificationManager$onNotificationPanelExpandStateChanged$1
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                ConversationNotificationManager.ConversationState conversationState = (ConversationNotificationManager.ConversationState) obj2;
                return optimizeReadOnlyMap.containsKey((String) obj) ? new ConversationNotificationManager.ConversationState(0, conversationState.notification) : conversationState;
            }
        });
        FilteringSequence$iterator$1 filteringSequence$iterator$12 = new FilteringSequence$iterator$1(SequencesKt.mapNotNull(new CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1(optimizeReadOnlyMap.values()), new Function1() { // from class: com.android.systemui.statusbar.notification.ConversationNotificationManager$onNotificationPanelExpandStateChanged$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ((NotificationEntry) obj).row;
            }
        }));
        while (filteringSequence$iterator$12.hasNext()) {
            resetBadgeUi((ExpandableNotificationRow) filteringSequence$iterator$12.next());
        }
    }
}

package com.android.systemui.people.widget;

import android.appwidget.AppWidgetProviderInfo;
import android.content.ComponentName;
import android.service.notification.NotificationListenerService;
import android.util.Log;
import com.android.systemui.people.NotificationHelper;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.wm.shell.bubbles.BubbleController;
import com.android.wm.shell.bubbles.Bubbles;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class PeopleSpaceWidgetManager$$ExternalSyntheticLambda0 implements Predicate {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ PeopleSpaceWidgetManager$$ExternalSyntheticLambda0(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        NotificationListenerService.Ranking ranking;
        int i = this.$r8$classId;
        Object obj2 = this.f$0;
        switch (i) {
            case 0:
                PeopleSpaceWidgetManager peopleSpaceWidgetManager = (PeopleSpaceWidgetManager) obj2;
                NotificationEntry notificationEntry = (NotificationEntry) obj;
                Map map = PeopleSpaceWidgetManager.mListeners;
                peopleSpaceWidgetManager.getClass();
                NotificationHelper.AnonymousClass1 anonymousClass1 = NotificationHelper.notificationEntryComparator;
                if (notificationEntry != null && (ranking = notificationEntry.mRanking) != null && ranking.getConversationShortcutInfo() != null && notificationEntry.mSbn.getNotification() != null && NotificationHelper.isMissedCallOrHasContent(notificationEntry)) {
                    Optional optional = peopleSpaceWidgetManager.mBubblesOptional;
                    try {
                        if (optional.isPresent()) {
                            if (((BubbleController.BubblesImpl) ((Bubbles) optional.get())).isBubbleNotificationSuppressedFromShade(notificationEntry.mKey, notificationEntry.mSbn.getGroupKey())) {
                            }
                        }
                    } catch (Exception e) {
                        Log.e("PeopleNotifHelper", "Exception checking if notification is suppressed: " + e);
                    }
                    return true;
                }
                return false;
            default:
                Map map2 = PeopleSpaceWidgetManager.mListeners;
                return ((AppWidgetProviderInfo) obj).provider.equals((ComponentName) obj2);
        }
    }
}

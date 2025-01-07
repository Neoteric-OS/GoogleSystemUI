package com.android.systemui.people.widget;

import android.app.people.ConversationChannel;
import android.service.notification.ConversationChannelWrapper;
import android.service.notification.NotificationListenerService;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import java.util.Map;
import java.util.function.Function;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class PeopleSpaceWidgetManager$$ExternalSyntheticLambda1 implements Function {
    public final /* synthetic */ int $r8$classId;

    public /* synthetic */ PeopleSpaceWidgetManager$$ExternalSyntheticLambda1(int i) {
        this.$r8$classId = i;
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                NotificationEntry notificationEntry = (NotificationEntry) obj;
                PeopleTileKey peopleTileKey = new PeopleTileKey();
                NotificationListenerService.Ranking ranking = notificationEntry.mRanking;
                peopleTileKey.mShortcutId = (ranking == null || ranking.getConversationShortcutInfo() == null) ? "" : notificationEntry.mRanking.getConversationShortcutInfo().getId();
                peopleTileKey.mUserId = notificationEntry.mSbn.getUser() != null ? notificationEntry.mSbn.getUser().getIdentifier() : -1;
                peopleTileKey.mPackageName = notificationEntry.mSbn.getPackageName();
                return peopleTileKey;
            case 1:
                Map map = PeopleSpaceWidgetManager.mListeners;
                return ((ConversationChannelWrapper) obj).getShortcutInfo();
            case 2:
                Map map2 = PeopleSpaceWidgetManager.mListeners;
                return ((ConversationChannel) obj).getShortcutInfo();
            case 3:
                return Integer.valueOf(Integer.parseInt((String) obj));
            default:
                Map map3 = PeopleSpaceWidgetManager.mListeners;
                return ((ConversationChannelWrapper) obj).getShortcutInfo();
        }
    }
}

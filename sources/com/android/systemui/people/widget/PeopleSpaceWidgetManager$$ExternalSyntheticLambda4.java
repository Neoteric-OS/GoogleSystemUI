package com.android.systemui.people.widget;

import android.service.notification.ConversationChannelWrapper;
import java.util.Map;
import java.util.function.Predicate;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class PeopleSpaceWidgetManager$$ExternalSyntheticLambda4 implements Predicate {
    public final /* synthetic */ int $r8$classId;

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                Map map = PeopleSpaceWidgetManager.mListeners;
                break;
            case 1:
                ConversationChannelWrapper conversationChannelWrapper = (ConversationChannelWrapper) obj;
                Map map2 = PeopleSpaceWidgetManager.mListeners;
                if (conversationChannelWrapper.getNotificationChannel() != null && conversationChannelWrapper.getNotificationChannel().isImportantConversation()) {
                    break;
                }
                break;
            default:
                ConversationChannelWrapper conversationChannelWrapper2 = (ConversationChannelWrapper) obj;
                Map map3 = PeopleSpaceWidgetManager.mListeners;
                if (conversationChannelWrapper2.getNotificationChannel() == null || !conversationChannelWrapper2.getNotificationChannel().isImportantConversation()) {
                    break;
                }
                break;
        }
        return true;
    }
}

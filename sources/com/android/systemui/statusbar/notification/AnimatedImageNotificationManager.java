package com.android.systemui.statusbar.notification;

import android.graphics.drawable.AnimatedImageDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.android.internal.widget.ConversationLayout;
import com.android.internal.widget.MessagingGroup;
import com.android.internal.widget.MessagingImageMessage;
import com.android.internal.widget.MessagingLayout;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.inflation.BindEventManagerImpl;
import com.android.systemui.statusbar.notification.collection.notifcollection.CommonNotifCollection;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.NotificationContentView;
import com.android.systemui.statusbar.policy.HeadsUpManager;
import com.android.systemui.util.ConvenienceExtensionsKt;
import java.util.ArrayList;
import java.util.Arrays;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1;
import kotlin.jvm.functions.Function1;
import kotlin.sequences.EmptySequence;
import kotlin.sequences.FilteringSequence$iterator$1;
import kotlin.sequences.SequencesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AnimatedImageNotificationManager {
    public final BindEventManagerImpl bindEventManager;
    public final HeadsUpManager headsUpManager;
    public boolean isStatusBarExpanded;
    public final CommonNotifCollection notifCollection;
    public final StatusBarStateController statusBarStateController;

    public AnimatedImageNotificationManager(CommonNotifCollection commonNotifCollection, BindEventManagerImpl bindEventManagerImpl, HeadsUpManager headsUpManager, StatusBarStateController statusBarStateController) {
        this.notifCollection = commonNotifCollection;
        this.bindEventManager = bindEventManagerImpl;
        this.headsUpManager = headsUpManager;
        this.statusBarStateController = statusBarStateController;
    }

    public static final void access$updateAnimatedImageDrawables(AnimatedImageNotificationManager animatedImageNotificationManager, NotificationEntry notificationEntry) {
        animatedImageNotificationManager.getClass();
        ExpandableNotificationRow expandableNotificationRow = notificationEntry.row;
        if (expandableNotificationRow != null) {
            boolean z = expandableNotificationRow.mIsHeadsUp || animatedImageNotificationManager.isStatusBarExpanded;
            NotificationContentView[] notificationContentViewArr = expandableNotificationRow.mLayouts;
            NotificationContentView[] notificationContentViewArr2 = (NotificationContentView[]) Arrays.copyOf(notificationContentViewArr, notificationContentViewArr.length);
            FilteringSequence$iterator$1 filteringSequence$iterator$1 = new FilteringSequence$iterator$1(SequencesKt.mapNotNull(SequencesKt.flatMap(SequencesKt.flatMap(SequencesKt.flatMap(notificationContentViewArr2 != null ? ArraysKt.asSequence(notificationContentViewArr2) : EmptySequence.INSTANCE, new Function1() { // from class: com.android.systemui.statusbar.notification.AnimatedImageNotificationManager$updateAnimatedImageDrawables$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return ArraysKt.asSequence(((NotificationContentView) obj).getAllViews());
                }
            }), new Function1() { // from class: com.android.systemui.statusbar.notification.AnimatedImageNotificationManager$updateAnimatedImageDrawables$3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    ArrayList messagingGroups;
                    ArrayList messagingGroups2;
                    ConversationLayout conversationLayout = (View) obj;
                    ConversationLayout conversationLayout2 = conversationLayout instanceof ConversationLayout ? conversationLayout : null;
                    if (conversationLayout2 != null && (messagingGroups2 = conversationLayout2.getMessagingGroups()) != null) {
                        return new CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1(messagingGroups2);
                    }
                    MessagingLayout messagingLayout = conversationLayout instanceof MessagingLayout ? (MessagingLayout) conversationLayout : null;
                    return (messagingLayout == null || (messagingGroups = messagingLayout.getMessagingGroups()) == null) ? EmptySequence.INSTANCE : new CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1(messagingGroups);
                }
            }), new Function1() { // from class: com.android.systemui.statusbar.notification.AnimatedImageNotificationManager$updateAnimatedImageDrawables$4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return ConvenienceExtensionsKt.getChildren(((MessagingGroup) obj).getMessageContainer());
                }
            }), new Function1() { // from class: com.android.systemui.statusbar.notification.AnimatedImageNotificationManager$updateAnimatedImageDrawables$5
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    MessagingImageMessage messagingImageMessage = (View) obj;
                    MessagingImageMessage messagingImageMessage2 = messagingImageMessage instanceof MessagingImageMessage ? messagingImageMessage : null;
                    if (messagingImageMessage2 == null) {
                        return null;
                    }
                    Drawable drawable = messagingImageMessage2.getDrawable();
                    if (drawable instanceof AnimatedImageDrawable) {
                        return (AnimatedImageDrawable) drawable;
                    }
                    return null;
                }
            }));
            while (filteringSequence$iterator$1.hasNext()) {
                AnimatedImageDrawable animatedImageDrawable = (AnimatedImageDrawable) filteringSequence$iterator$1.next();
                if (z) {
                    animatedImageDrawable.start();
                } else {
                    animatedImageDrawable.stop();
                }
            }
        }
    }
}

package com.android.systemui.statusbar.notification.collection.coordinator;

import com.android.systemui.statusbar.notification.collection.NotifCollection;
import com.android.systemui.statusbar.notification.collection.NotifCollection$$ExternalSyntheticLambda12;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifFilter;
import com.android.systemui.statusbar.notification.collection.notifcollection.DismissedByUserStats;
import com.android.systemui.util.Assert;
import com.android.systemui.wmshell.BubblesManager;
import com.android.wm.shell.bubbles.BubbleController;
import com.android.wm.shell.bubbles.Bubbles;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BubbleCoordinator implements Coordinator {
    public final Optional mBubblesManagerOptional;
    public final Optional mBubblesOptional;
    public final NotifCollection mNotifCollection;
    public NotifPipeline mNotifPipeline;
    public NotifCollection$$ExternalSyntheticLambda12 mOnEndDismissInterception;
    public final Set mInterceptedDismissalEntries = new HashSet();
    public final AnonymousClass1 mNotifFilter = new NotifFilter() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.BubbleCoordinator.1
        @Override // com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifFilter
        public final boolean shouldFilterOut(NotificationEntry notificationEntry, long j) {
            BubbleCoordinator bubbleCoordinator = BubbleCoordinator.this;
            if (bubbleCoordinator.mBubblesOptional.isPresent()) {
                if (((BubbleController.BubblesImpl) ((Bubbles) bubbleCoordinator.mBubblesOptional.get())).isBubbleNotificationSuppressedFromShade(notificationEntry.mKey, notificationEntry.mSbn.getGroupKey())) {
                    return true;
                }
            }
            return false;
        }
    };
    public final AnonymousClass2 mDismissInterceptor = new AnonymousClass2();
    public final AnonymousClass3 mNotifCallback = new AnonymousClass3();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.collection.coordinator.BubbleCoordinator$2, reason: invalid class name */
    public final class AnonymousClass2 {
        public AnonymousClass2() {
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.collection.coordinator.BubbleCoordinator$3, reason: invalid class name */
    public final class AnonymousClass3 {
        public AnonymousClass3() {
        }

        public final void removeNotification(NotificationEntry notificationEntry, DismissedByUserStats dismissedByUserStats) {
            BubbleCoordinator bubbleCoordinator = BubbleCoordinator.this;
            boolean contains = bubbleCoordinator.mInterceptedDismissalEntries.contains(notificationEntry.mKey);
            String str = notificationEntry.mKey;
            if (!contains) {
                if (bubbleCoordinator.mNotifPipeline.mNotifCollection.getEntry(str) != null) {
                    bubbleCoordinator.mNotifCollection.dismissNotification(notificationEntry, dismissedByUserStats);
                    return;
                }
                return;
            }
            bubbleCoordinator.mInterceptedDismissalEntries.remove(str);
            NotifCollection notifCollection = bubbleCoordinator.mOnEndDismissInterception.f$0;
            notifCollection.getClass();
            Assert.isMainThread();
            if (notifCollection.mAttached) {
                notifCollection.checkForReentrantCall();
                List list = notificationEntry.mDismissInterceptors;
                AnonymousClass2 anonymousClass2 = bubbleCoordinator.mDismissInterceptor;
                if (list.remove(anonymousClass2)) {
                    if (((ArrayList) notificationEntry.mDismissInterceptors).size() > 0) {
                        return;
                    }
                    notifCollection.dismissNotification(notificationEntry, dismissedByUserStats);
                } else {
                    anonymousClass2.getClass();
                    IllegalStateException illegalStateException = new IllegalStateException("Cannot end dismiss interceptor for interceptor \"BubbleCoordinator\" (" + anonymousClass2 + ")");
                    notifCollection.mEulogizer.record(illegalStateException);
                    throw illegalStateException;
                }
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.android.systemui.statusbar.notification.collection.coordinator.BubbleCoordinator$1] */
    public BubbleCoordinator(Optional optional, Optional optional2, NotifCollection notifCollection) {
        this.mBubblesManagerOptional = optional;
        this.mBubblesOptional = optional2;
        this.mNotifCollection = notifCollection;
    }

    @Override // com.android.systemui.statusbar.notification.collection.coordinator.Coordinator
    public final void attach(NotifPipeline notifPipeline) {
        this.mNotifPipeline = notifPipeline;
        NotifCollection notifCollection = notifPipeline.mNotifCollection;
        notifCollection.getClass();
        Assert.isMainThread();
        notifCollection.checkForReentrantCall();
        List list = notifCollection.mDismissInterceptors;
        AnonymousClass2 anonymousClass2 = this.mDismissInterceptor;
        if (list.contains(anonymousClass2)) {
            throw new IllegalArgumentException("Interceptor " + anonymousClass2 + " already added.");
        }
        notifCollection.mDismissInterceptors.add(anonymousClass2);
        BubbleCoordinator.this.mOnEndDismissInterception = new NotifCollection$$ExternalSyntheticLambda12(notifCollection);
        this.mNotifPipeline.addPreGroupFilter(this.mNotifFilter);
        this.mBubblesManagerOptional.ifPresent(new Consumer() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.BubbleCoordinator$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                BubbleCoordinator bubbleCoordinator = BubbleCoordinator.this;
                bubbleCoordinator.getClass();
                ((BubblesManager) obj).mCallbacks.add(bubbleCoordinator.mNotifCallback);
            }
        });
    }
}

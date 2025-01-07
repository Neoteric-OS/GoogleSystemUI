package com.android.systemui.statusbar.notification.collection.coalescer;

import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CoalescedEvent {
    public EventBatch batch;
    public final String key;
    public final int position;
    public NotificationListenerService.Ranking ranking;
    public final StatusBarNotification sbn;

    public CoalescedEvent(String str, int i, StatusBarNotification statusBarNotification, NotificationListenerService.Ranking ranking, EventBatch eventBatch) {
        this.key = str;
        this.position = i;
        this.sbn = statusBarNotification;
        this.ranking = ranking;
        this.batch = eventBatch;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CoalescedEvent)) {
            return false;
        }
        CoalescedEvent coalescedEvent = (CoalescedEvent) obj;
        return Intrinsics.areEqual(this.key, coalescedEvent.key) && this.position == coalescedEvent.position && Intrinsics.areEqual(this.sbn, coalescedEvent.sbn) && Intrinsics.areEqual(this.ranking, coalescedEvent.ranking) && Intrinsics.areEqual(this.batch, coalescedEvent.batch);
    }

    public final int hashCode() {
        int hashCode = (this.ranking.hashCode() + ((this.sbn.hashCode() + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.position, this.key.hashCode() * 31, 31)) * 31)) * 31;
        EventBatch eventBatch = this.batch;
        return hashCode + (eventBatch == null ? 0 : eventBatch.hashCode());
    }

    public final String toString() {
        return ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(new StringBuilder("CoalescedEvent(key="), this.key, ")");
    }
}

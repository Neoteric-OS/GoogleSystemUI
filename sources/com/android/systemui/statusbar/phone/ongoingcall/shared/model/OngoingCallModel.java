package com.android.systemui.statusbar.phone.ongoingcall.shared.model;

import android.app.PendingIntent;
import com.android.systemui.statusbar.StatusBarIconView;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface OngoingCallModel {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class InCall implements OngoingCallModel {
        public final PendingIntent intent;
        public final StatusBarIconView notificationIconView;
        public final long startTimeMs;

        public InCall(long j, StatusBarIconView statusBarIconView, PendingIntent pendingIntent) {
            this.startTimeMs = j;
            this.notificationIconView = statusBarIconView;
            this.intent = pendingIntent;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof InCall)) {
                return false;
            }
            InCall inCall = (InCall) obj;
            return this.startTimeMs == inCall.startTimeMs && Intrinsics.areEqual(this.notificationIconView, inCall.notificationIconView) && Intrinsics.areEqual(this.intent, inCall.intent);
        }

        public final int hashCode() {
            int hashCode = Long.hashCode(this.startTimeMs) * 31;
            StatusBarIconView statusBarIconView = this.notificationIconView;
            int hashCode2 = (hashCode + (statusBarIconView == null ? 0 : statusBarIconView.hashCode())) * 31;
            PendingIntent pendingIntent = this.intent;
            return hashCode2 + (pendingIntent != null ? pendingIntent.hashCode() : 0);
        }

        public final String toString() {
            return "InCall(startTimeMs=" + this.startTimeMs + ", notificationIconView=" + this.notificationIconView + ", intent=" + this.intent + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class NoCall implements OngoingCallModel {
        public static final NoCall INSTANCE = new NoCall();

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof NoCall);
        }

        public final int hashCode() {
            return 2137519088;
        }

        public final String toString() {
            return "NoCall";
        }
    }
}

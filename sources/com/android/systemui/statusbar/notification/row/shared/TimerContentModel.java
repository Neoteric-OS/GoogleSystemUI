package com.android.systemui.statusbar.notification.row.shared;

import android.app.Notification;
import android.app.PendingIntent;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;
import java.time.Duration;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TimerContentModel implements RichOngoingContentModel {
    public final IconModel icon;
    public final String name;
    public final TimerState state;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface TimerState {

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class Paused implements TimerState {
            public final Notification.Action addMinuteAction;
            public final Notification.Action resetAction;
            public final PendingIntent resumeIntent;
            public final Duration timeRemaining;

            public Paused(Duration duration, PendingIntent pendingIntent, Notification.Action action, Notification.Action action2) {
                this.timeRemaining = duration;
                this.resumeIntent = pendingIntent;
                this.addMinuteAction = action;
                this.resetAction = action2;
            }

            public final boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Paused)) {
                    return false;
                }
                Paused paused = (Paused) obj;
                return Intrinsics.areEqual(this.timeRemaining, paused.timeRemaining) && Intrinsics.areEqual(this.resumeIntent, paused.resumeIntent) && Intrinsics.areEqual(this.addMinuteAction, paused.addMinuteAction) && Intrinsics.areEqual(this.resetAction, paused.resetAction);
            }

            public final int hashCode() {
                int hashCode = this.timeRemaining.hashCode() * 31;
                PendingIntent pendingIntent = this.resumeIntent;
                int hashCode2 = (hashCode + (pendingIntent == null ? 0 : pendingIntent.hashCode())) * 31;
                Notification.Action action = this.addMinuteAction;
                int hashCode3 = (hashCode2 + (action == null ? 0 : action.hashCode())) * 31;
                Notification.Action action2 = this.resetAction;
                return hashCode3 + (action2 != null ? action2.hashCode() : 0);
            }

            public final String toString() {
                return "Paused(timeRemaining=" + this.timeRemaining + ", resumeIntent=" + this.resumeIntent + ", addMinuteAction=" + this.addMinuteAction + ", resetAction=" + this.resetAction + ")";
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class Running implements TimerState {
            public final Notification.Action addMinuteAction;
            public final long finishTime;
            public final PendingIntent pauseIntent;
            public final Notification.Action resetAction;

            public Running(long j, PendingIntent pendingIntent, Notification.Action action, Notification.Action action2) {
                this.finishTime = j;
                this.pauseIntent = pendingIntent;
                this.addMinuteAction = action;
                this.resetAction = action2;
            }

            public final boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Running)) {
                    return false;
                }
                Running running = (Running) obj;
                return this.finishTime == running.finishTime && Intrinsics.areEqual(this.pauseIntent, running.pauseIntent) && Intrinsics.areEqual(this.addMinuteAction, running.addMinuteAction) && Intrinsics.areEqual(this.resetAction, running.resetAction);
            }

            public final int hashCode() {
                int hashCode = Long.hashCode(this.finishTime) * 31;
                PendingIntent pendingIntent = this.pauseIntent;
                int hashCode2 = (hashCode + (pendingIntent == null ? 0 : pendingIntent.hashCode())) * 31;
                Notification.Action action = this.addMinuteAction;
                int hashCode3 = (hashCode2 + (action == null ? 0 : action.hashCode())) * 31;
                Notification.Action action2 = this.resetAction;
                return hashCode3 + (action2 != null ? action2.hashCode() : 0);
            }

            public final String toString() {
                return "Running(finishTime=" + this.finishTime + ", pauseIntent=" + this.pauseIntent + ", addMinuteAction=" + this.addMinuteAction + ", resetAction=" + this.resetAction + ")";
            }
        }
    }

    public TimerContentModel(IconModel iconModel, String str, TimerState timerState) {
        this.icon = iconModel;
        this.name = str;
        this.state = timerState;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TimerContentModel)) {
            return false;
        }
        TimerContentModel timerContentModel = (TimerContentModel) obj;
        return Intrinsics.areEqual(this.icon, timerContentModel.icon) && Intrinsics.areEqual(this.name, timerContentModel.name) && Intrinsics.areEqual(this.state, timerContentModel.state);
    }

    public final int hashCode() {
        return this.state.hashCode() + PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(this.name, this.icon.hashCode() * 31, 31);
    }

    public final String toString() {
        return "TimerContentModel(icon=" + this.icon + ", name=" + this.name + ", state=" + this.state + ")";
    }
}

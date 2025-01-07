package com.android.systemui.screenrecord.data.model;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface ScreenRecordModel {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DoingNothing implements ScreenRecordModel {
        public static final DoingNothing INSTANCE = new DoingNothing();

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof DoingNothing);
        }

        public final int hashCode() {
            return -2100932977;
        }

        public final String toString() {
            return "DoingNothing";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Recording implements ScreenRecordModel {
        public static final Recording INSTANCE = new Recording();

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof Recording);
        }

        public final int hashCode() {
            return -1632877992;
        }

        public final String toString() {
            return "Recording";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Starting implements ScreenRecordModel {
        public final long countdownSeconds;
        public final long millisUntilStarted;

        public Starting(long j) {
            this.millisUntilStarted = j;
            this.countdownSeconds = Math.floorDiv(j + 500, 1000);
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Starting) && this.millisUntilStarted == ((Starting) obj).millisUntilStarted;
        }

        public final int hashCode() {
            return Long.hashCode(this.millisUntilStarted);
        }

        public final String toString() {
            return "Starting(millisUntilStarted=" + this.millisUntilStarted + ")";
        }
    }
}

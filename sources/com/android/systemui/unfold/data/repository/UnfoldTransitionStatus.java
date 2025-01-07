package com.android.systemui.unfold.data.repository;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class UnfoldTransitionStatus {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TransitionFinished extends UnfoldTransitionStatus {
        public static final TransitionFinished INSTANCE = new TransitionFinished();

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof TransitionFinished);
        }

        public final int hashCode() {
            return 1524641535;
        }

        public final String toString() {
            return "TransitionFinished";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TransitionInProgress extends UnfoldTransitionStatus {
        public final float progress;

        public TransitionInProgress(float f) {
            this.progress = f;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof TransitionInProgress) && Float.compare(this.progress, ((TransitionInProgress) obj).progress) == 0;
        }

        public final int hashCode() {
            return Float.hashCode(this.progress);
        }

        public final String toString() {
            return "TransitionInProgress(progress=" + this.progress + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TransitionStarted extends UnfoldTransitionStatus {
        public static final TransitionStarted INSTANCE = new TransitionStarted();

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof TransitionStarted);
        }

        public final int hashCode() {
            return -1826272172;
        }

        public final String toString() {
            return "TransitionStarted";
        }
    }
}

package com.android.systemui.qs.ui.adapter;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface CustomizerState {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Animating extends CustomizerState {
        long getAnimationDuration();
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class AnimatingIntoCustomizer implements Animating {
        public final long animationDuration;

        public AnimatingIntoCustomizer(long j) {
            this.animationDuration = j;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof AnimatingIntoCustomizer) && this.animationDuration == ((AnimatingIntoCustomizer) obj).animationDuration;
        }

        @Override // com.android.systemui.qs.ui.adapter.CustomizerState.Animating
        public final long getAnimationDuration() {
            return this.animationDuration;
        }

        public final int hashCode() {
            return Long.hashCode(this.animationDuration);
        }

        @Override // com.android.systemui.qs.ui.adapter.CustomizerState
        public final boolean isCustomizing() {
            return true;
        }

        public final String toString() {
            return "AnimatingIntoCustomizer(animationDuration=" + this.animationDuration + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class AnimatingOutOfCustomizer implements Animating {
        public final long animationDuration;

        public AnimatingOutOfCustomizer(long j) {
            this.animationDuration = j;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof AnimatingOutOfCustomizer) && this.animationDuration == ((AnimatingOutOfCustomizer) obj).animationDuration;
        }

        @Override // com.android.systemui.qs.ui.adapter.CustomizerState.Animating
        public final long getAnimationDuration() {
            return this.animationDuration;
        }

        public final int hashCode() {
            return Long.hashCode(this.animationDuration);
        }

        public final String toString() {
            return "AnimatingOutOfCustomizer(animationDuration=" + this.animationDuration + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Hidden implements CustomizerState {
        public static final Hidden INSTANCE = new Hidden();

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof Hidden);
        }

        public final int hashCode() {
            return 567405442;
        }

        @Override // com.android.systemui.qs.ui.adapter.CustomizerState
        public final boolean isShowing() {
            return false;
        }

        public final String toString() {
            return "Hidden";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Showing implements CustomizerState {
        public static final Showing INSTANCE = new Showing();

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof Showing);
        }

        public final int hashCode() {
            return 1564404973;
        }

        @Override // com.android.systemui.qs.ui.adapter.CustomizerState
        public final boolean isCustomizing() {
            return true;
        }

        public final String toString() {
            return "Showing";
        }
    }

    default boolean isCustomizing() {
        return false;
    }

    default boolean isShowing() {
        return true;
    }
}

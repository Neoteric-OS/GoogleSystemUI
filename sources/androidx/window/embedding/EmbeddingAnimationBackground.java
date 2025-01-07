package androidx.window.embedding;

import android.graphics.Color;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class EmbeddingAnimationBackground {
    public static final DefaultBackground DEFAULT = new DefaultBackground();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ColorBackground extends EmbeddingAnimationBackground {
        public final int color;

        public ColorBackground(int i) {
            this.color = i;
            if (Color.alpha(i) != 255) {
                throw new IllegalArgumentException("Background color must be opaque");
            }
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof ColorBackground) {
                return this.color == ((ColorBackground) obj).color;
            }
            return false;
        }

        public final int hashCode() {
            return Integer.hashCode(this.color);
        }

        public final String toString() {
            return "ColorBackground{color:" + Integer.toHexString(this.color) + '}';
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DefaultBackground extends EmbeddingAnimationBackground {
        public final String toString() {
            return "DefaultBackground";
        }
    }
}

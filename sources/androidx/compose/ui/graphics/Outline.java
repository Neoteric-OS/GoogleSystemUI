package androidx.compose.ui.graphics;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RoundRect;
import androidx.compose.ui.geometry.RoundRectKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class Outline {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Generic extends Outline {
        public final AndroidPath path;

        public Generic(AndroidPath androidPath) {
            this.path = androidPath;
        }

        @Override // androidx.compose.ui.graphics.Outline
        public final Rect getBounds() {
            return this.path.getBounds();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Rectangle extends Outline {
        public final Rect rect;

        public Rectangle(Rect rect) {
            this.rect = rect;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Rectangle) {
                return Intrinsics.areEqual(this.rect, ((Rectangle) obj).rect);
            }
            return false;
        }

        @Override // androidx.compose.ui.graphics.Outline
        public final Rect getBounds() {
            return this.rect;
        }

        public final int hashCode() {
            return this.rect.hashCode();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Rounded extends Outline {
        public final RoundRect roundRect;
        public final AndroidPath roundRectPath;

        public Rounded(RoundRect roundRect) {
            AndroidPath androidPath;
            this.roundRect = roundRect;
            if (RoundRectKt.isSimple(roundRect)) {
                androidPath = null;
            } else {
                androidPath = AndroidPath_androidKt.Path();
                Path.addRoundRect$default(androidPath, roundRect);
            }
            this.roundRectPath = androidPath;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Rounded) {
                return Intrinsics.areEqual(this.roundRect, ((Rounded) obj).roundRect);
            }
            return false;
        }

        @Override // androidx.compose.ui.graphics.Outline
        public final Rect getBounds() {
            RoundRect roundRect = this.roundRect;
            return new Rect(roundRect.left, roundRect.top, roundRect.right, roundRect.bottom);
        }

        public final int hashCode() {
            return this.roundRect.hashCode();
        }
    }

    public abstract Rect getBounds();
}

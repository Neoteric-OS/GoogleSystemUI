package com.android.compose.animation.scene;

import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import androidx.compose.ui.unit.LayoutDirection;
import com.android.compose.animation.scene.SwipeDirection;
import com.android.compose.animation.scene.SwipeSource;
import com.android.compose.animation.scene.UserAction;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class Swipe extends UserAction {
    public static final Swipe Down = null;
    public static final Swipe Left = null;
    public static final Swipe Up = null;
    public final SwipeDirection direction;
    public final SwipeSource fromSource;
    public final int pointerCount;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Resolved extends UserAction.Resolved {
        public final SwipeDirection.Resolved direction;
        public final SwipeSource.Resolved fromSource;
        public final int pointerCount;

        public Resolved(SwipeDirection.Resolved resolved, int i, SwipeSource.Resolved resolved2) {
            this.direction = resolved;
            this.pointerCount = i;
            this.fromSource = resolved2;
        }

        public static Resolved copy$default(Resolved resolved) {
            return new Resolved(resolved.direction, resolved.pointerCount, null);
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Resolved)) {
                return false;
            }
            Resolved resolved = (Resolved) obj;
            return this.direction == resolved.direction && this.pointerCount == resolved.pointerCount && Intrinsics.areEqual(this.fromSource, resolved.fromSource);
        }

        public final int hashCode() {
            int m = KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.pointerCount, this.direction.hashCode() * 31, 31);
            SwipeSource.Resolved resolved = this.fromSource;
            return m + (resolved == null ? 0 : resolved.hashCode());
        }

        public final String toString() {
            return "Resolved(direction=" + this.direction + ", pointerCount=" + this.pointerCount + ", fromSource=" + this.fromSource + ")";
        }
    }

    static {
        new Swipe(SwipeDirection.Left, null, 6);
        new Swipe(SwipeDirection.Up, null, 6);
        new Swipe(SwipeDirection.Down, null, 6);
    }

    public Swipe(SwipeDirection swipeDirection, SwipeSource swipeSource, int i) {
        int i2 = (i & 2) != 0 ? 1 : 2;
        swipeSource = (i & 4) != 0 ? null : swipeSource;
        this.direction = swipeDirection;
        this.pointerCount = i2;
        this.fromSource = swipeSource;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Swipe)) {
            return false;
        }
        Swipe swipe = (Swipe) obj;
        return this.direction == swipe.direction && this.pointerCount == swipe.pointerCount && Intrinsics.areEqual(this.fromSource, swipe.fromSource);
    }

    public final int hashCode() {
        int m = KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.pointerCount, this.direction.hashCode() * 31, 31);
        SwipeSource swipeSource = this.fromSource;
        return m + (swipeSource == null ? 0 : swipeSource.hashCode());
    }

    @Override // com.android.compose.animation.scene.UserAction
    public final UserAction.Resolved resolve$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout(LayoutDirection layoutDirection) {
        SwipeDirection.Resolved resolved = (SwipeDirection.Resolved) this.direction.getResolve$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout().invoke(layoutDirection);
        SwipeSource swipeSource = this.fromSource;
        return new Resolved(resolved, this.pointerCount, swipeSource != null ? swipeSource.resolve(layoutDirection) : null);
    }

    public final String toString() {
        return "Swipe(direction=" + this.direction + ", pointerCount=" + this.pointerCount + ", fromSource=" + this.fromSource + ")";
    }
}

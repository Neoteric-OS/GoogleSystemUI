package androidx.core.view;

import android.view.WindowInsets;
import android.view.WindowInsetsAnimation;
import androidx.core.graphics.Insets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class WindowInsetsAnimationCompat {
    public Impl30 mImpl;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Impl30 {
        public final WindowInsetsAnimation mWrapped;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class ProxyCallback extends WindowInsetsAnimation.Callback {
            public final HashMap mAnimations;
            public final Callback mCompat;
            public List mRORunningAnimations;
            public ArrayList mTmpRunningAnimations;

            public ProxyCallback(Callback callback) {
                super(callback.mDispatchMode);
                this.mAnimations = new HashMap();
                this.mCompat = callback;
            }

            public final WindowInsetsAnimationCompat getWindowInsetsAnimationCompat(WindowInsetsAnimation windowInsetsAnimation) {
                WindowInsetsAnimationCompat windowInsetsAnimationCompat = (WindowInsetsAnimationCompat) this.mAnimations.get(windowInsetsAnimation);
                if (windowInsetsAnimationCompat != null) {
                    return windowInsetsAnimationCompat;
                }
                WindowInsetsAnimationCompat windowInsetsAnimationCompat2 = new WindowInsetsAnimationCompat();
                windowInsetsAnimationCompat2.mImpl = new Impl30(new WindowInsetsAnimation(0, null, 0L));
                windowInsetsAnimationCompat2.mImpl = new Impl30(windowInsetsAnimation);
                this.mAnimations.put(windowInsetsAnimation, windowInsetsAnimationCompat2);
                return windowInsetsAnimationCompat2;
            }

            @Override // android.view.WindowInsetsAnimation.Callback
            public final void onEnd(WindowInsetsAnimation windowInsetsAnimation) {
                this.mCompat.onEnd(getWindowInsetsAnimationCompat(windowInsetsAnimation));
                this.mAnimations.remove(windowInsetsAnimation);
            }

            @Override // android.view.WindowInsetsAnimation.Callback
            public final void onPrepare(WindowInsetsAnimation windowInsetsAnimation) {
                Callback callback = this.mCompat;
                getWindowInsetsAnimationCompat(windowInsetsAnimation);
                callback.onPrepare();
            }

            @Override // android.view.WindowInsetsAnimation.Callback
            public final WindowInsets onProgress(WindowInsets windowInsets, List list) {
                ArrayList arrayList = this.mTmpRunningAnimations;
                if (arrayList == null) {
                    ArrayList arrayList2 = new ArrayList(list.size());
                    this.mTmpRunningAnimations = arrayList2;
                    this.mRORunningAnimations = Collections.unmodifiableList(arrayList2);
                } else {
                    arrayList.clear();
                }
                for (int size = list.size() - 1; size >= 0; size--) {
                    WindowInsetsAnimation windowInsetsAnimation = (WindowInsetsAnimation) list.get(size);
                    WindowInsetsAnimationCompat windowInsetsAnimationCompat = getWindowInsetsAnimationCompat(windowInsetsAnimation);
                    windowInsetsAnimationCompat.mImpl.mWrapped.setFraction(windowInsetsAnimation.getFraction());
                    this.mTmpRunningAnimations.add(windowInsetsAnimationCompat);
                }
                return this.mCompat.onProgress(WindowInsetsCompat.toWindowInsetsCompat(null, windowInsets), this.mRORunningAnimations).toWindowInsets();
            }

            @Override // android.view.WindowInsetsAnimation.Callback
            public final WindowInsetsAnimation.Bounds onStart(WindowInsetsAnimation windowInsetsAnimation, WindowInsetsAnimation.Bounds bounds) {
                Callback callback = this.mCompat;
                getWindowInsetsAnimationCompat(windowInsetsAnimation);
                BoundsCompat onStart = callback.onStart(new BoundsCompat(bounds));
                onStart.getClass();
                return new WindowInsetsAnimation.Bounds(onStart.mLowerBound.toPlatformInsets(), onStart.mUpperBound.toPlatformInsets());
            }
        }

        public Impl30(WindowInsetsAnimation windowInsetsAnimation) {
            this.mWrapped = windowInsetsAnimation;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class BoundsCompat {
        public final Insets mLowerBound;
        public final Insets mUpperBound;

        public BoundsCompat(Insets insets, Insets insets2) {
            this.mLowerBound = insets;
            this.mUpperBound = insets2;
        }

        public final String toString() {
            return "Bounds{lower=" + this.mLowerBound + " upper=" + this.mUpperBound + "}";
        }

        public BoundsCompat(WindowInsetsAnimation.Bounds bounds) {
            this.mLowerBound = Insets.toCompatInsets(bounds.getLowerBound());
            this.mUpperBound = Insets.toCompatInsets(bounds.getUpperBound());
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Callback {
        public final int mDispatchMode;

        public Callback(int i) {
            this.mDispatchMode = i;
        }

        public abstract WindowInsetsCompat onProgress(WindowInsetsCompat windowInsetsCompat, List list);

        public abstract BoundsCompat onStart(BoundsCompat boundsCompat);

        public void onPrepare() {
        }

        public void onEnd(WindowInsetsAnimationCompat windowInsetsAnimationCompat) {
        }
    }
}

package androidx.core.animation;

import android.view.Choreographer;
import androidx.core.animation.AnimationHandler;
import java.util.ArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class Animator implements Cloneable {
    public ArrayList mListeners = null;
    public ArrayList mPauseListeners = null;
    public ArrayList mUpdateListeners = null;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface AnimatorListener {
        void onAnimationCancel();

        default void onAnimationEnd(Animator animator) {
            onAnimationEnd$1(animator);
        }

        void onAnimationEnd$1(Animator animator);

        void onAnimationStart$1();
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface AnimatorUpdateListener {
        void onAnimationUpdate(Animator animator);
    }

    public static void addAnimationCallback(AnimationHandler.AnimationFrameCallback animationFrameCallback) {
        AnimationHandler animationHandler = AnimationHandler.getInstance();
        int size = animationHandler.mAnimationCallbacks.size();
        AnimationHandler.FrameCallbackProvider16 frameCallbackProvider16 = animationHandler.mProvider;
        if (size == 0) {
            frameCallbackProvider16.getClass();
            Choreographer.getInstance().postFrameCallback(frameCallbackProvider16);
        }
        if (!animationHandler.mAnimationCallbacks.contains(animationFrameCallback)) {
            animationHandler.mAnimationCallbacks.add(animationFrameCallback);
        }
        frameCallbackProvider16.getClass();
    }

    public final void addListener(AnimatorListener animatorListener) {
        if (this.mListeners == null) {
            this.mListeners = new ArrayList();
        }
        this.mListeners.add(animatorListener);
    }

    public final void addUpdateListener(AnimatorUpdateListener animatorUpdateListener) {
        if (this.mUpdateListeners == null) {
            this.mUpdateListeners = new ArrayList();
        }
        this.mUpdateListeners.add(animatorUpdateListener);
    }

    public abstract void cancel();

    public Animator clone() {
        try {
            Animator animator = (Animator) super.clone();
            if (this.mListeners != null) {
                animator.mListeners = new ArrayList(this.mListeners);
            }
            if (this.mPauseListeners != null) {
                animator.mPauseListeners = new ArrayList(this.mPauseListeners);
            }
            return animator;
        } catch (CloneNotSupportedException unused) {
            throw new AssertionError();
        }
    }

    public abstract void end();

    public abstract long getStartDelay();

    public abstract long getTotalDuration();

    public abstract boolean isInitialized();

    public abstract boolean isStarted();

    public abstract boolean pulseAnimationFrame(long j);

    public final void removeListener(AnimatorListener animatorListener) {
        ArrayList arrayList = this.mListeners;
        if (arrayList == null) {
            return;
        }
        arrayList.remove(animatorListener);
        if (this.mListeners.size() == 0) {
            this.mListeners = null;
        }
    }

    public abstract void reverse();

    public abstract Animator setDuration(long j);

    public abstract void setInterpolator(Interpolator interpolator);

    public abstract void skipToEndValue(boolean z);

    public abstract void start();

    public abstract void startWithoutPulsing(boolean z);
}

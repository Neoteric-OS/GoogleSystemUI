package com.airbnb.lottie.utils;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.view.Choreographer;
import com.airbnb.lottie.LottieComposition;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LottieValueAnimator extends ValueAnimator implements Choreographer.FrameCallback {
    public LottieComposition composition;
    public final Set updateListeners = new CopyOnWriteArraySet();
    public final Set listeners = new CopyOnWriteArraySet();
    public final Set pauseListeners = new CopyOnWriteArraySet();
    public float speed = 1.0f;
    public boolean speedReversedForRepeatMode = false;
    public long lastFrameTimeNs = 0;
    public float frameRaw = 0.0f;
    public float frame = 0.0f;
    public int repeatCount = 0;
    public float minFrame = -2.1474836E9f;
    public float maxFrame = 2.1474836E9f;
    public boolean running = false;
    public boolean useCompositionFrameRate = false;

    @Override // android.animation.Animator
    public final void addListener(Animator.AnimatorListener animatorListener) {
        this.listeners.add(animatorListener);
    }

    @Override // android.animation.Animator
    public final void addPauseListener(Animator.AnimatorPauseListener animatorPauseListener) {
        this.pauseListeners.add(animatorPauseListener);
    }

    @Override // android.animation.ValueAnimator
    public final void addUpdateListener(ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
        this.updateListeners.add(animatorUpdateListener);
    }

    @Override // android.animation.ValueAnimator, android.animation.Animator
    public final void cancel() {
        Iterator it = ((CopyOnWriteArraySet) this.listeners).iterator();
        while (it.hasNext()) {
            ((Animator.AnimatorListener) it.next()).onAnimationCancel(this);
        }
        notifyEnd(isReversed());
        removeFrameCallback(true);
    }

    @Override // android.view.Choreographer.FrameCallback
    public final void doFrame(long j) {
        boolean z = false;
        if (this.running) {
            removeFrameCallback(false);
            Choreographer.getInstance().postFrameCallback(this);
        }
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null || !this.running) {
            return;
        }
        float abs = (this.lastFrameTimeNs != 0 ? j - r2 : 0L) / ((1.0E9f / lottieComposition.frameRate) / Math.abs(this.speed));
        float f = this.frameRaw;
        if (isReversed()) {
            abs = -abs;
        }
        float f2 = f + abs;
        float minFrame = getMinFrame();
        float maxFrame = getMaxFrame();
        PointF pointF = MiscUtils.pathFromDataCurrentPoint;
        if (f2 >= minFrame && f2 <= maxFrame) {
            z = true;
        }
        float f3 = this.frameRaw;
        float clamp = MiscUtils.clamp(f2, getMinFrame(), getMaxFrame());
        this.frameRaw = clamp;
        if (this.useCompositionFrameRate) {
            clamp = (float) Math.floor(clamp);
        }
        this.frame = clamp;
        this.lastFrameTimeNs = j;
        if (!this.useCompositionFrameRate || this.frameRaw != f3) {
            notifyUpdate();
        }
        if (!z) {
            if (getRepeatCount() == -1 || this.repeatCount < getRepeatCount()) {
                Iterator it = ((CopyOnWriteArraySet) this.listeners).iterator();
                while (it.hasNext()) {
                    ((Animator.AnimatorListener) it.next()).onAnimationRepeat(this);
                }
                this.repeatCount++;
                if (getRepeatMode() == 2) {
                    this.speedReversedForRepeatMode = !this.speedReversedForRepeatMode;
                    this.speed = -this.speed;
                } else {
                    float maxFrame2 = isReversed() ? getMaxFrame() : getMinFrame();
                    this.frameRaw = maxFrame2;
                    this.frame = maxFrame2;
                }
                this.lastFrameTimeNs = j;
            } else {
                float minFrame2 = this.speed < 0.0f ? getMinFrame() : getMaxFrame();
                this.frameRaw = minFrame2;
                this.frame = minFrame2;
                removeFrameCallback(true);
                notifyEnd(isReversed());
            }
        }
        if (this.composition == null) {
            return;
        }
        float f4 = this.frame;
        if (f4 < this.minFrame || f4 > this.maxFrame) {
            throw new IllegalStateException(String.format("Frame must be [%f,%f]. It is %f", Float.valueOf(this.minFrame), Float.valueOf(this.maxFrame), Float.valueOf(this.frame)));
        }
    }

    @Override // android.animation.ValueAnimator
    public final float getAnimatedFraction() {
        float minFrame;
        float maxFrame;
        float minFrame2;
        if (this.composition == null) {
            return 0.0f;
        }
        if (isReversed()) {
            minFrame = getMaxFrame() - this.frame;
            maxFrame = getMaxFrame();
            minFrame2 = getMinFrame();
        } else {
            minFrame = this.frame - getMinFrame();
            maxFrame = getMaxFrame();
            minFrame2 = getMinFrame();
        }
        return minFrame / (maxFrame - minFrame2);
    }

    @Override // android.animation.ValueAnimator
    public final Object getAnimatedValue() {
        return Float.valueOf(getAnimatedValueAbsolute());
    }

    public final float getAnimatedValueAbsolute() {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            return 0.0f;
        }
        float f = this.frame;
        float f2 = lottieComposition.startFrame;
        return (f - f2) / (lottieComposition.endFrame - f2);
    }

    @Override // android.animation.ValueAnimator, android.animation.Animator
    public final long getDuration() {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            return 0L;
        }
        return (long) lottieComposition.getDuration();
    }

    public final float getMaxFrame() {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            return 0.0f;
        }
        float f = this.maxFrame;
        return f == 2.1474836E9f ? lottieComposition.endFrame : f;
    }

    public final float getMinFrame() {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            return 0.0f;
        }
        float f = this.minFrame;
        return f == -2.1474836E9f ? lottieComposition.startFrame : f;
    }

    @Override // android.animation.ValueAnimator, android.animation.Animator
    public final long getStartDelay() {
        throw new UnsupportedOperationException("LottieAnimator does not support getStartDelay.");
    }

    public final boolean isReversed() {
        return this.speed < 0.0f;
    }

    @Override // android.animation.ValueAnimator, android.animation.Animator
    public final boolean isRunning() {
        return this.running;
    }

    public final void notifyEnd(boolean z) {
        Iterator it = ((CopyOnWriteArraySet) this.listeners).iterator();
        while (it.hasNext()) {
            ((Animator.AnimatorListener) it.next()).onAnimationEnd(this, z);
        }
    }

    public final void notifyUpdate() {
        Iterator it = ((CopyOnWriteArraySet) this.updateListeners).iterator();
        while (it.hasNext()) {
            ((ValueAnimator.AnimatorUpdateListener) it.next()).onAnimationUpdate(this);
        }
    }

    @Override // android.animation.Animator
    public final void removeAllListeners() {
        this.listeners.clear();
    }

    @Override // android.animation.ValueAnimator
    public final void removeAllUpdateListeners() {
        this.updateListeners.clear();
    }

    public final void removeFrameCallback(boolean z) {
        Choreographer.getInstance().removeFrameCallback(this);
        if (z) {
            this.running = false;
        }
    }

    @Override // android.animation.Animator
    public final void removeListener(Animator.AnimatorListener animatorListener) {
        this.listeners.remove(animatorListener);
    }

    @Override // android.animation.Animator
    public final void removePauseListener(Animator.AnimatorPauseListener animatorPauseListener) {
        this.pauseListeners.remove(animatorPauseListener);
    }

    @Override // android.animation.ValueAnimator
    public final void removeUpdateListener(ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
        this.updateListeners.remove(animatorUpdateListener);
    }

    @Override // android.animation.ValueAnimator, android.animation.Animator
    public final /* bridge */ /* synthetic */ Animator setDuration(long j) {
        setDuration(j);
        throw null;
    }

    public final void setFrame(float f) {
        if (this.frameRaw == f) {
            return;
        }
        float clamp = MiscUtils.clamp(f, getMinFrame(), getMaxFrame());
        this.frameRaw = clamp;
        if (this.useCompositionFrameRate) {
            clamp = (float) Math.floor(clamp);
        }
        this.frame = clamp;
        this.lastFrameTimeNs = 0L;
        notifyUpdate();
    }

    @Override // android.animation.ValueAnimator, android.animation.Animator
    public final void setInterpolator(TimeInterpolator timeInterpolator) {
        throw new UnsupportedOperationException("LottieAnimator does not support setInterpolator.");
    }

    public final void setMinAndMaxFrames(float f, float f2) {
        if (f > f2) {
            throw new IllegalArgumentException("minFrame (" + f + ") must be <= maxFrame (" + f2 + ")");
        }
        LottieComposition lottieComposition = this.composition;
        float f3 = lottieComposition == null ? -3.4028235E38f : lottieComposition.startFrame;
        float f4 = lottieComposition == null ? Float.MAX_VALUE : lottieComposition.endFrame;
        float clamp = MiscUtils.clamp(f, f3, f4);
        float clamp2 = MiscUtils.clamp(f2, f3, f4);
        if (clamp == this.minFrame && clamp2 == this.maxFrame) {
            return;
        }
        this.minFrame = clamp;
        this.maxFrame = clamp2;
        setFrame((int) MiscUtils.clamp(this.frame, clamp, clamp2));
    }

    @Override // android.animation.ValueAnimator
    public final void setRepeatMode(int i) {
        super.setRepeatMode(i);
        if (i == 2 || !this.speedReversedForRepeatMode) {
            return;
        }
        this.speedReversedForRepeatMode = false;
        this.speed = -this.speed;
    }

    @Override // android.animation.ValueAnimator, android.animation.Animator
    public final void setStartDelay(long j) {
        throw new UnsupportedOperationException("LottieAnimator does not support setStartDelay.");
    }

    @Override // android.animation.ValueAnimator, android.animation.Animator
    public final ValueAnimator setDuration(long j) {
        throw new UnsupportedOperationException("LottieAnimator does not support setDuration.");
    }
}

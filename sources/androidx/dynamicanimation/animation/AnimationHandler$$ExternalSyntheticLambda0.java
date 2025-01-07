package androidx.dynamicanimation.animation;

import android.animation.ValueAnimator;
import android.os.SystemClock;
import androidx.collection.SimpleArrayMap;
import androidx.dynamicanimation.animation.AnimationHandler;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class AnimationHandler$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ AnimationHandler f$0;

    @Override // java.lang.Runnable
    public final void run() {
        AnimationHandler.AnimationCallbackDispatcher animationCallbackDispatcher = this.f$0.mCallbackDispatcher;
        animationCallbackDispatcher.getClass();
        long uptimeMillis = SystemClock.uptimeMillis();
        AnimationHandler animationHandler = animationCallbackDispatcher.this$0;
        animationHandler.getClass();
        long uptimeMillis2 = SystemClock.uptimeMillis();
        for (int i = 0; i < animationHandler.mAnimationCallbacks.size(); i++) {
            DynamicAnimation dynamicAnimation = (DynamicAnimation) animationHandler.mAnimationCallbacks.get(i);
            if (dynamicAnimation != null) {
                SimpleArrayMap simpleArrayMap = animationHandler.mDelayedCallbackStartTime;
                Long l = (Long) simpleArrayMap.get(dynamicAnimation);
                if (l != null) {
                    if (l.longValue() < uptimeMillis2) {
                        simpleArrayMap.remove(dynamicAnimation);
                    }
                }
                long j = dynamicAnimation.mLastFrameTime;
                if (j == 0) {
                    dynamicAnimation.mLastFrameTime = uptimeMillis;
                    dynamicAnimation.setPropertyValue(dynamicAnimation.mValue);
                } else {
                    long j2 = uptimeMillis - j;
                    dynamicAnimation.mLastFrameTime = uptimeMillis;
                    float f = dynamicAnimation.getAnimationHandler().mDurationScale;
                    boolean updateValueAndVelocity = dynamicAnimation.updateValueAndVelocity(f == 0.0f ? 2147483647L : (long) (j2 / f));
                    float min = Math.min(dynamicAnimation.mValue, dynamicAnimation.mMaxValue);
                    dynamicAnimation.mValue = min;
                    float max = Math.max(min, dynamicAnimation.mMinValue);
                    dynamicAnimation.mValue = max;
                    dynamicAnimation.setPropertyValue(max);
                    if (updateValueAndVelocity) {
                        dynamicAnimation.endAnimationInternal(false);
                    }
                }
            }
        }
        if (animationHandler.mListDirty) {
            for (int size = animationHandler.mAnimationCallbacks.size() - 1; size >= 0; size--) {
                if (animationHandler.mAnimationCallbacks.get(size) == null) {
                    animationHandler.mAnimationCallbacks.remove(size);
                }
            }
            if (animationHandler.mAnimationCallbacks.size() == 0) {
                AnimationHandler.FrameCallbackScheduler16 frameCallbackScheduler16 = animationHandler.mDurationScaleChangeListener;
                ValueAnimator.unregisterDurationScaleChangeListener((AnimationHandler$DurationScaleChangeListener33$$ExternalSyntheticLambda0) frameCallbackScheduler16.mChoreographer);
                frameCallbackScheduler16.mChoreographer = null;
            }
            animationHandler.mListDirty = false;
        }
        if (animationHandler.mAnimationCallbacks.size() > 0) {
            animationHandler.mScheduler.postFrameCallback(animationHandler.mRunnable);
        }
    }
}

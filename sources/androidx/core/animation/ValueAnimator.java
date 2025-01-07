package androidx.core.animation;

import android.os.Looper;
import android.util.AndroidRuntimeException;
import android.util.Log;
import android.view.animation.AnimationUtils;
import androidx.constraintlayout.core.PriorityGoalRow$GoalVariableAccessor$$ExternalSyntheticOutline0;
import androidx.core.animation.AnimationHandler;
import androidx.core.animation.Animator;
import androidx.core.animation.PropertyValuesHolder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class ValueAnimator extends Animator implements AnimationHandler.AnimationFrameCallback {
    public static final AccelerateDecelerateInterpolator sDefaultInterpolator = new AccelerateDecelerateInterpolator();
    public boolean mReversing;
    public PropertyValuesHolder[] mValues;
    public HashMap mValuesMap;
    public long mStartTime = -1;
    public float mSeekFraction = -1.0f;
    public float mOverallFraction = 0.0f;
    public long mLastFrameTime = -1;
    public boolean mRunning = false;
    public boolean mStarted = false;
    public boolean mStartListenersCalled = false;
    public boolean mInitialized = false;
    public boolean mAnimationEndRequested = false;
    public long mDuration = 300;
    public long mStartDelay = 0;
    public boolean mSelfPulse = true;
    public boolean mSuppressSelfPulseRequested = false;
    public Interpolator mInterpolator = sDefaultInterpolator;

    public static float clampFraction(float f) {
        if (f < 0.0f) {
            return 0.0f;
        }
        return Math.min(f, 1);
    }

    public static float getCurrentIterationFraction(float f, boolean z) {
        float clampFraction = clampFraction(f);
        float clampFraction2 = clampFraction(clampFraction);
        double d = clampFraction2;
        double floor = Math.floor(d);
        if (d == floor && clampFraction2 > 0.0f) {
            floor -= 1.0d;
        }
        float f2 = clampFraction - ((int) floor);
        return z ? 1.0f - f2 : f2;
    }

    public static ValueAnimator ofFloat(float... fArr) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setFloatValues(fArr);
        return valueAnimator;
    }

    public static ValueAnimator ofInt(int... iArr) {
        ValueAnimator valueAnimator = new ValueAnimator();
        if (iArr.length != 0) {
            PropertyValuesHolder[] propertyValuesHolderArr = valueAnimator.mValues;
            if (propertyValuesHolderArr == null || propertyValuesHolderArr.length == 0) {
                Class[] clsArr = PropertyValuesHolder.FLOAT_VARIANTS;
                PropertyValuesHolder.IntPropertyValuesHolder intPropertyValuesHolder = new PropertyValuesHolder.IntPropertyValuesHolder("");
                intPropertyValuesHolder.setIntValues(iArr);
                valueAnimator.setValues(intPropertyValuesHolder);
            } else {
                propertyValuesHolderArr[0].setIntValues(iArr);
            }
            valueAnimator.mInitialized = false;
        }
        return valueAnimator;
    }

    public void animateValue(float f) {
        float interpolation = this.mInterpolator.getInterpolation(f);
        int length = this.mValues.length;
        for (int i = 0; i < length; i++) {
            this.mValues[i].calculateValue(interpolation);
        }
        ArrayList arrayList = this.mUpdateListeners;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                ((Animator.AnimatorUpdateListener) this.mUpdateListeners.get(i2)).onAnimationUpdate(this);
            }
        }
    }

    @Override // androidx.core.animation.Animator
    public final void cancel() {
        if (Looper.myLooper() == null) {
            throw new AndroidRuntimeException("Animators may only be run on Looper threads");
        }
        if (this.mAnimationEndRequested) {
            return;
        }
        if ((this.mStarted || this.mRunning) && this.mListeners != null) {
            if (!this.mRunning) {
                notifyStartListeners();
            }
            Iterator it = ((ArrayList) this.mListeners.clone()).iterator();
            while (it.hasNext()) {
                ((Animator.AnimatorListener) it.next()).onAnimationCancel();
            }
        }
        endAnimation$1();
    }

    /* JADX WARN: Code restructure failed: missing block: B:43:0x009a, code lost:
    
        if (r11 != false) goto L47;
     */
    @Override // androidx.core.animation.AnimationHandler.AnimationFrameCallback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean doAnimationFrame(long r10) {
        /*
            Method dump skipped, instructions count: 180
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.animation.ValueAnimator.doAnimationFrame(long):boolean");
    }

    @Override // androidx.core.animation.Animator
    public final void end() {
        if (Looper.myLooper() == null) {
            throw new AndroidRuntimeException("Animators may only be run on Looper threads");
        }
        if (!this.mRunning) {
            startAnimation$1();
            this.mStarted = true;
        } else if (!this.mInitialized) {
            initAnimation$1();
        }
        animateValue(this.mReversing ? 0.0f : 1.0f);
        endAnimation$1();
    }

    public final void endAnimation$1() {
        ArrayList arrayList;
        if (this.mAnimationEndRequested) {
            return;
        }
        if (this.mSelfPulse) {
            AnimationHandler animationHandler = AnimationHandler.getInstance();
            int indexOf = animationHandler.mAnimationCallbacks.indexOf(this);
            if (indexOf >= 0) {
                animationHandler.mAnimationCallbacks.set(indexOf, null);
                animationHandler.mListDirty = true;
            }
        }
        this.mAnimationEndRequested = true;
        boolean z = (this.mStarted || this.mRunning) && this.mListeners != null;
        if (z && !this.mRunning) {
            notifyStartListeners();
        }
        this.mRunning = false;
        this.mStarted = false;
        this.mStartListenersCalled = false;
        this.mLastFrameTime = -1L;
        this.mStartTime = -1L;
        if (z && (arrayList = this.mListeners) != null) {
            ArrayList arrayList2 = (ArrayList) arrayList.clone();
            int size = arrayList2.size();
            for (int i = 0; i < size; i++) {
                ((Animator.AnimatorListener) arrayList2.get(i)).onAnimationEnd(this);
            }
        }
        this.mReversing = false;
    }

    public final Object getAnimatedValue() {
        PropertyValuesHolder[] propertyValuesHolderArr = this.mValues;
        if (propertyValuesHolderArr == null || propertyValuesHolderArr.length <= 0) {
            return null;
        }
        return propertyValuesHolderArr[0].getAnimatedValue();
    }

    @Override // androidx.core.animation.Animator
    public final long getStartDelay() {
        return this.mStartDelay;
    }

    @Override // androidx.core.animation.Animator
    public final long getTotalDuration() {
        return (this.mDuration * 1) + this.mStartDelay;
    }

    public void initAnimation$1() {
        if (this.mInitialized) {
            return;
        }
        int length = this.mValues.length;
        for (int i = 0; i < length; i++) {
            PropertyValuesHolder propertyValuesHolder = this.mValues[i];
            if (propertyValuesHolder.mEvaluator == null) {
                Class cls = propertyValuesHolder.mValueType;
                propertyValuesHolder.mEvaluator = cls == Integer.class ? IntEvaluator.sInstance : cls == Float.class ? FloatEvaluator.sInstance : null;
            }
            TypeEvaluator typeEvaluator = propertyValuesHolder.mEvaluator;
            if (typeEvaluator != null) {
                propertyValuesHolder.mKeyframes.mEvaluator = typeEvaluator;
            }
        }
        this.mInitialized = true;
    }

    @Override // androidx.core.animation.Animator
    public boolean isInitialized() {
        return this.mInitialized;
    }

    @Override // androidx.core.animation.Animator
    public final boolean isStarted() {
        return this.mStarted;
    }

    public final void notifyStartListeners() {
        ArrayList arrayList = this.mListeners;
        if (arrayList != null && !this.mStartListenersCalled) {
            ArrayList arrayList2 = (ArrayList) arrayList.clone();
            int size = arrayList2.size();
            for (int i = 0; i < size; i++) {
                ((Animator.AnimatorListener) arrayList2.get(i)).onAnimationStart$1();
            }
        }
        this.mStartListenersCalled = true;
    }

    @Override // androidx.core.animation.Animator
    public final boolean pulseAnimationFrame(long j) {
        if (this.mSelfPulse) {
            return false;
        }
        return doAnimationFrame(j);
    }

    @Override // androidx.core.animation.Animator
    public final void reverse() {
        if (this.mLastFrameTime >= 0) {
            long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            this.mStartTime = currentAnimationTimeMillis - (((long) (this.mDuration * 1.0f)) - (currentAnimationTimeMillis - this.mStartTime));
            this.mReversing = !this.mReversing;
        } else if (!this.mStarted) {
            start(true);
        } else {
            this.mReversing = !this.mReversing;
            end();
        }
    }

    public final void setCurrentFraction(float f) {
        initAnimation$1();
        float clampFraction = clampFraction(f);
        if (this.mLastFrameTime >= 0) {
            this.mStartTime = AnimationUtils.currentAnimationTimeMillis() - ((long) (((long) (this.mDuration * 1.0f)) * clampFraction));
        } else {
            this.mSeekFraction = clampFraction;
        }
        this.mOverallFraction = clampFraction;
        animateValue(getCurrentIterationFraction(clampFraction, this.mReversing));
    }

    public void setFloatValues(float... fArr) {
        if (fArr.length == 0) {
            return;
        }
        PropertyValuesHolder[] propertyValuesHolderArr = this.mValues;
        if (propertyValuesHolderArr == null || propertyValuesHolderArr.length == 0) {
            Class[] clsArr = PropertyValuesHolder.FLOAT_VARIANTS;
            PropertyValuesHolder.FloatPropertyValuesHolder floatPropertyValuesHolder = new PropertyValuesHolder.FloatPropertyValuesHolder("");
            floatPropertyValuesHolder.setFloatValues(fArr);
            setValues(floatPropertyValuesHolder);
        } else {
            propertyValuesHolderArr[0].setFloatValues(fArr);
        }
        this.mInitialized = false;
    }

    @Override // androidx.core.animation.Animator
    public final void setInterpolator(Interpolator interpolator) {
        if (interpolator != null) {
            this.mInterpolator = interpolator;
        } else {
            this.mInterpolator = new LinearInterpolator();
        }
    }

    public final void setStartDelay(long j) {
        if (j < 0) {
            Log.w("ValueAnimator", "Start delay should always be non-negative");
            j = 0;
        }
        this.mStartDelay = j;
    }

    public final void setValues(PropertyValuesHolder... propertyValuesHolderArr) {
        int length = propertyValuesHolderArr.length;
        this.mValues = propertyValuesHolderArr;
        this.mValuesMap = new HashMap(length);
        for (PropertyValuesHolder propertyValuesHolder : propertyValuesHolderArr) {
            this.mValuesMap.put(propertyValuesHolder.mPropertyName, propertyValuesHolder);
        }
        this.mInitialized = false;
    }

    @Override // androidx.core.animation.Animator
    public final void skipToEndValue(boolean z) {
        initAnimation$1();
        animateValue(z ? 0.0f : 1.0f);
    }

    public final void start(boolean z) {
        if (Looper.myLooper() == null) {
            throw new AndroidRuntimeException("Animators may only be run on Looper threads");
        }
        this.mReversing = z;
        this.mSelfPulse = !this.mSuppressSelfPulseRequested;
        if (z) {
            float f = this.mSeekFraction;
            if (f != -1.0f && f != 0.0f) {
                this.mSeekFraction = 1 - f;
            }
        }
        this.mStarted = true;
        this.mRunning = false;
        this.mAnimationEndRequested = false;
        this.mLastFrameTime = -1L;
        this.mStartTime = -1L;
        if (this.mStartDelay == 0 || this.mSeekFraction >= 0.0f || z) {
            startAnimation$1();
            float f2 = this.mSeekFraction;
            if (f2 == -1.0f) {
                long j = this.mDuration;
                setCurrentFraction(j > 0 ? 0 / j : 1.0f);
            } else {
                setCurrentFraction(f2);
            }
        }
        if (this.mSelfPulse) {
            Animator.addAnimationCallback(this);
        }
    }

    public final void startAnimation$1() {
        this.mAnimationEndRequested = false;
        initAnimation$1();
        this.mRunning = true;
        float f = this.mSeekFraction;
        if (f >= 0.0f) {
            this.mOverallFraction = f;
        } else {
            this.mOverallFraction = 0.0f;
        }
        if (this.mListeners != null) {
            notifyStartListeners();
        }
    }

    @Override // androidx.core.animation.Animator
    public final void startWithoutPulsing(boolean z) {
        this.mSuppressSelfPulseRequested = true;
        if (z) {
            reverse();
        } else {
            start();
        }
        this.mSuppressSelfPulseRequested = false;
    }

    public String toString() {
        String str = "ValueAnimator@" + Integer.toHexString(hashCode());
        if (this.mValues != null) {
            for (int i = 0; i < this.mValues.length; i++) {
                StringBuilder m = PriorityGoalRow$GoalVariableAccessor$$ExternalSyntheticOutline0.m(str, "\n    ");
                m.append(this.mValues[i].toString());
                str = m.toString();
            }
        }
        return str;
    }

    @Override // androidx.core.animation.Animator
    public ValueAnimator setDuration(long j) {
        if (j < 0) {
            throw new IllegalArgumentException(ValueAnimator$$ExternalSyntheticOutline0.m(j, "Animators cannot have negative duration: "));
        }
        this.mDuration = j;
        return this;
    }

    @Override // 
    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public ValueAnimator mo716clone() {
        ValueAnimator valueAnimator = (ValueAnimator) super.mo716clone();
        if (this.mUpdateListeners != null) {
            valueAnimator.mUpdateListeners = new ArrayList(this.mUpdateListeners);
        }
        valueAnimator.mSeekFraction = -1.0f;
        valueAnimator.mReversing = false;
        valueAnimator.mInitialized = false;
        valueAnimator.mStarted = false;
        valueAnimator.mRunning = false;
        valueAnimator.mStartListenersCalled = false;
        valueAnimator.mStartTime = -1L;
        valueAnimator.mAnimationEndRequested = false;
        valueAnimator.mLastFrameTime = -1L;
        valueAnimator.mOverallFraction = 0.0f;
        valueAnimator.mSelfPulse = true;
        valueAnimator.mSuppressSelfPulseRequested = false;
        PropertyValuesHolder[] propertyValuesHolderArr = this.mValues;
        if (propertyValuesHolderArr != null) {
            int length = propertyValuesHolderArr.length;
            valueAnimator.mValues = new PropertyValuesHolder[length];
            valueAnimator.mValuesMap = new HashMap(length);
            for (int i = 0; i < length; i++) {
                PropertyValuesHolder clone = propertyValuesHolderArr[i].clone();
                valueAnimator.mValues[i] = clone;
                valueAnimator.mValuesMap.put(clone.mPropertyName, clone);
            }
        }
        return valueAnimator;
    }

    @Override // androidx.core.animation.Animator
    public void start() {
        start(false);
    }
}

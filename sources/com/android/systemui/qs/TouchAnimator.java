package com.android.systemui.qs;

import android.util.FloatProperty;
import android.util.MathUtils;
import android.util.Property;
import android.view.View;
import android.view.animation.Interpolator;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TouchAnimator {
    public static final AnonymousClass1 POSITION = new AnonymousClass1("position");
    public final Interpolator mInterpolator;
    public final FloatKeyframeSet[] mKeyframeSets;
    public float mLastT = -1.0f;
    public final Listener mListener;
    public final float mSpan;
    public final float mStartDelay;
    public final Object[] mTargets;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.qs.TouchAnimator$1, reason: invalid class name */
    public final class AnonymousClass1 extends FloatProperty {
        @Override // android.util.Property
        public final Float get(Object obj) {
            return Float.valueOf(((TouchAnimator) obj).mLastT);
        }

        @Override // android.util.FloatProperty
        public final void setValue(Object obj, float f) {
            ((TouchAnimator) obj).setPosition(f);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Builder {
        public float mEndDelay;
        public Interpolator mInterpolator;
        public Listener mListener;
        public float mStartDelay;
        public final List mTargets = new ArrayList();
        public final List mValues = new ArrayList();

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        public final void addFloat(Object obj, String str, float... fArr) {
            Property of;
            char c;
            Class cls = Float.TYPE;
            if (obj instanceof View) {
                switch (str.hashCode()) {
                    case -1225497657:
                        if (str.equals("translationX")) {
                            c = 0;
                            break;
                        }
                        c = 65535;
                        break;
                    case -1225497656:
                        if (str.equals("translationY")) {
                            c = 1;
                            break;
                        }
                        c = 65535;
                        break;
                    case -1225497655:
                        if (str.equals("translationZ")) {
                            c = 2;
                            break;
                        }
                        c = 65535;
                        break;
                    case -908189618:
                        if (str.equals("scaleX")) {
                            c = 7;
                            break;
                        }
                        c = 65535;
                        break;
                    case -908189617:
                        if (str.equals("scaleY")) {
                            c = '\b';
                            break;
                        }
                        c = 65535;
                        break;
                    case -40300674:
                        if (str.equals("rotation")) {
                            c = 4;
                            break;
                        }
                        c = 65535;
                        break;
                    case 120:
                        if (str.equals("x")) {
                            c = 5;
                            break;
                        }
                        c = 65535;
                        break;
                    case 121:
                        if (str.equals("y")) {
                            c = 6;
                            break;
                        }
                        c = 65535;
                        break;
                    case 92909918:
                        if (str.equals("alpha")) {
                            c = 3;
                            break;
                        }
                        c = 65535;
                        break;
                    default:
                        c = 65535;
                        break;
                }
                switch (c) {
                    case 0:
                        of = View.TRANSLATION_X;
                        break;
                    case 1:
                        of = View.TRANSLATION_Y;
                        break;
                    case 2:
                        of = View.TRANSLATION_Z;
                        break;
                    case 3:
                        of = View.ALPHA;
                        break;
                    case 4:
                        of = View.ROTATION;
                        break;
                    case 5:
                        of = View.X;
                        break;
                    case 6:
                        of = View.Y;
                        break;
                    case 7:
                        of = View.SCALE_X;
                        break;
                    case '\b':
                        of = View.SCALE_Y;
                        break;
                }
                FloatKeyframeSet floatKeyframeSet = new FloatKeyframeSet(of, fArr);
                this.mTargets.add(obj);
                this.mValues.add(floatKeyframeSet);
            }
            of = ((obj instanceof TouchAnimator) && "position".equals(str)) ? TouchAnimator.POSITION : Property.of(obj.getClass(), cls, str);
            FloatKeyframeSet floatKeyframeSet2 = new FloatKeyframeSet(of, fArr);
            this.mTargets.add(obj);
            this.mValues.add(floatKeyframeSet2);
        }

        public final TouchAnimator build() {
            List list = this.mTargets;
            Object[] array = list.toArray(new Object[((ArrayList) list).size()]);
            List list2 = this.mValues;
            return new TouchAnimator(array, (FloatKeyframeSet[]) list2.toArray(new FloatKeyframeSet[((ArrayList) list2).size()]), this.mStartDelay, this.mEndDelay, this.mInterpolator, this.mListener);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class FloatKeyframeSet {
        public final float mFrameWidth;
        public final Property mProperty;
        public final int mSize;
        public final float[] mValues;

        public FloatKeyframeSet(Property property, float[] fArr) {
            this.mSize = fArr.length;
            this.mFrameWidth = 1.0f / (r0 - 1);
            this.mProperty = property;
            this.mValues = fArr;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Listener {
        void onAnimationAtEnd();

        void onAnimationAtStart();

        void onAnimationStarted();
    }

    public TouchAnimator(Object[] objArr, FloatKeyframeSet[] floatKeyframeSetArr, float f, float f2, Interpolator interpolator, Listener listener) {
        this.mTargets = objArr;
        this.mKeyframeSets = floatKeyframeSetArr;
        this.mStartDelay = f;
        this.mSpan = (1.0f - f2) - f;
        this.mInterpolator = interpolator;
        this.mListener = listener;
    }

    public final void setPosition(float f) {
        if (Float.isNaN(f)) {
            return;
        }
        float constrain = MathUtils.constrain((f - this.mStartDelay) / this.mSpan, 0.0f, 1.0f);
        Interpolator interpolator = this.mInterpolator;
        if (interpolator != null) {
            constrain = interpolator.getInterpolation(constrain);
        }
        float f2 = this.mLastT;
        if (constrain == f2) {
            return;
        }
        Listener listener = this.mListener;
        if (listener != null) {
            if (constrain == 1.0f) {
                listener.onAnimationAtEnd();
            } else if (constrain == 0.0f) {
                listener.onAnimationAtStart();
            } else if (f2 <= 0.0f || f2 == 1.0f) {
                listener.onAnimationStarted();
            }
            this.mLastT = constrain;
        }
        int i = 0;
        while (true) {
            Object[] objArr = this.mTargets;
            if (i >= objArr.length) {
                return;
            }
            FloatKeyframeSet floatKeyframeSet = this.mKeyframeSets[i];
            Object obj = objArr[i];
            float f3 = floatKeyframeSet.mFrameWidth;
            int constrain2 = MathUtils.constrain((int) Math.ceil(constrain / f3), 1, floatKeyframeSet.mSize - 1);
            int i2 = constrain2 - 1;
            float f4 = (constrain - (i2 * f3)) / f3;
            float[] fArr = floatKeyframeSet.mValues;
            float f5 = fArr[i2];
            floatKeyframeSet.mProperty.set(obj, Float.valueOf(((fArr[constrain2] - f5) * f4) + f5));
            i++;
        }
    }
}

package androidx.constraintlayout.motion.widget;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import androidx.compose.animation.AndroidFlingSpline$$ExternalSyntheticOutline0;
import androidx.constraintlayout.core.motion.utils.ArcCurveFit;
import androidx.constraintlayout.core.motion.utils.CurveFit;
import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.core.motion.utils.KeyCache;
import androidx.constraintlayout.motion.utils.CustomSupport;
import androidx.constraintlayout.motion.utils.ViewOscillator;
import androidx.constraintlayout.motion.utils.ViewSpline;
import androidx.constraintlayout.motion.utils.ViewTimeCycle;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MotionController {
    public ArcCurveFit mArcSpline;
    public int[] mAttributeInterpolatorCount;
    public String[] mAttributeNames;
    public HashMap mAttributesMap;
    public HashMap mCycleMap;
    public final int mId;
    public double[] mInterpolateData;
    public int[] mInterpolateVariables;
    public double[] mInterpolateVelocity;
    public KeyTrigger[] mKeyTriggers;
    public CurveFit[] mSpline;
    public HashMap mTimeCycleAttributesMap;
    public final View mView;
    public final Rect mTempRect = new Rect();
    public boolean mForceMeasure = false;
    public int mCurveFitType = -1;
    public final MotionPaths mStartMotionPath = new MotionPaths();
    public final MotionPaths mEndMotionPath = new MotionPaths();
    public final MotionConstrainedPoint mStartPoint = new MotionConstrainedPoint();
    public final MotionConstrainedPoint mEndPoint = new MotionConstrainedPoint();
    public float mMotionStagger = Float.NaN;
    public float mStaggerOffset = 0.0f;
    public float mStaggerScale = 1.0f;
    public final float[] mValuesBuff = new float[4];
    public final ArrayList mMotionPaths = new ArrayList();
    public final float[] mVelocity = new float[1];
    public final ArrayList mKeyList = new ArrayList();
    public int mPathMotionArc = -1;
    public int mTransformPivotTarget = -1;
    public View mTransformPivotView = null;
    public int mQuantizeMotionSteps = -1;
    public float mQuantizeMotionPhase = Float.NaN;
    public Interpolator mQuantizeMotionInterpolator = null;
    public boolean mNoMovement = false;

    public MotionController(View view) {
        this.mView = view;
        this.mId = view.getId();
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ConstraintLayout.LayoutParams) {
            ((ConstraintLayout.LayoutParams) layoutParams).getClass();
        }
    }

    public static void rotate(int i, int i2, int i3, Rect rect, Rect rect2) {
        if (i == 1) {
            int i4 = rect.left + rect.right;
            rect2.left = ((rect.top + rect.bottom) - rect.width()) / 2;
            rect2.top = i3 - ((rect.height() + i4) / 2);
            rect2.right = rect.width() + rect2.left;
            rect2.bottom = rect.height() + rect2.top;
            return;
        }
        if (i == 2) {
            int i5 = rect.left + rect.right;
            rect2.left = i2 - ((rect.width() + (rect.top + rect.bottom)) / 2);
            rect2.top = (i5 - rect.height()) / 2;
            rect2.right = rect.width() + rect2.left;
            rect2.bottom = rect.height() + rect2.top;
            return;
        }
        if (i == 3) {
            int i6 = rect.left + rect.right;
            rect2.left = ((rect.height() / 2) + rect.top) - (i6 / 2);
            rect2.top = i3 - ((rect.height() + i6) / 2);
            rect2.right = rect.width() + rect2.left;
            rect2.bottom = rect.height() + rect2.top;
            return;
        }
        if (i != 4) {
            return;
        }
        int i7 = rect.left + rect.right;
        rect2.left = i2 - ((rect.width() + (rect.bottom + rect.top)) / 2);
        rect2.top = (i7 - rect.height()) / 2;
        rect2.right = rect.width() + rect2.left;
        rect2.bottom = rect.height() + rect2.top;
    }

    public final float getAdjustedPosition(float f, float[] fArr) {
        float f2 = 0.0f;
        if (fArr != null) {
            fArr[0] = 1.0f;
        } else {
            float f3 = this.mStaggerScale;
            if (f3 != 1.0d) {
                float f4 = this.mStaggerOffset;
                if (f < f4) {
                    f = 0.0f;
                }
                if (f > f4 && f < 1.0d) {
                    f = Math.min((f - f4) * f3, 1.0f);
                }
            }
        }
        Easing easing = this.mStartMotionPath.mKeyFrameEasing;
        Iterator it = this.mMotionPaths.iterator();
        float f5 = Float.NaN;
        while (it.hasNext()) {
            MotionPaths motionPaths = (MotionPaths) it.next();
            Easing easing2 = motionPaths.mKeyFrameEasing;
            if (easing2 != null) {
                float f6 = motionPaths.mTime;
                if (f6 < f) {
                    easing = easing2;
                    f2 = f6;
                } else if (Float.isNaN(f5)) {
                    f5 = motionPaths.mTime;
                }
            }
        }
        if (easing == null) {
            return f;
        }
        float f7 = (Float.isNaN(f5) ? 1.0f : f5) - f2;
        double d = (f - f2) / f7;
        float f8 = f2 + (((float) easing.get(d)) * f7);
        if (fArr != null) {
            fArr[0] = (float) easing.getDiff(d);
        }
        return f8;
    }

    public final void getCenter(double d, float[] fArr, float[] fArr2) {
        double[] dArr = new double[4];
        double[] dArr2 = new double[4];
        this.mSpline[0].getPos(d, dArr);
        this.mSpline[0].getSlope(d, dArr2);
        float f = 0.0f;
        Arrays.fill(fArr2, 0.0f);
        int[] iArr = this.mInterpolateVariables;
        MotionPaths motionPaths = this.mStartMotionPath;
        float f2 = motionPaths.mX;
        float f3 = motionPaths.mY;
        float f4 = motionPaths.mWidth;
        float f5 = motionPaths.mHeight;
        float f6 = 0.0f;
        float f7 = 0.0f;
        float f8 = 0.0f;
        int i = 0;
        while (i < iArr.length) {
            float f9 = f4;
            float f10 = f5;
            f4 = (float) dArr[i];
            double[] dArr3 = dArr;
            float f11 = (float) dArr2[i];
            int i2 = iArr[i];
            if (i2 == 1) {
                f2 = f4;
                f6 = f11;
            } else if (i2 == 2) {
                f3 = f4;
                f = f11;
            } else if (i2 == 3) {
                f7 = f11;
                i++;
                dArr = dArr3;
                f5 = f10;
            } else if (i2 == 4) {
                f10 = f4;
                f8 = f11;
            }
            f4 = f9;
            i++;
            dArr = dArr3;
            f5 = f10;
        }
        float f12 = f4;
        float f13 = f5;
        float f14 = (f7 / 2.0f) + f6;
        float f15 = (f8 / 2.0f) + f;
        MotionController motionController = motionPaths.mRelativeToController;
        if (motionController != null) {
            float[] fArr3 = new float[2];
            float[] fArr4 = new float[2];
            motionController.getCenter(d, fArr3, fArr4);
            float f16 = fArr3[0];
            float f17 = fArr3[1];
            float f18 = fArr4[0];
            float f19 = fArr4[1];
            double d2 = f2;
            double d3 = f3;
            float sin = (float) (((Math.sin(d3) * d2) + f16) - (f12 / 2.0f));
            float cos = (float) ((f17 - (Math.cos(d3) * d2)) - (f13 / 2.0f));
            double d4 = f6;
            double d5 = f;
            float cos2 = (float) ((Math.cos(d3) * d5) + (Math.sin(d3) * d4) + f18);
            f15 = (float) ((Math.sin(d3) * d5) + (f19 - (Math.cos(d3) * d4)));
            f2 = sin;
            f3 = cos;
            f14 = cos2;
        }
        fArr[0] = (f12 / 2.0f) + f2 + 0.0f;
        fArr[1] = (f13 / 2.0f) + f3 + 0.0f;
        fArr2[0] = f14;
        fArr2[1] = f15;
    }

    public final float getPreCycleDistance() {
        char c;
        float[] fArr = new float[2];
        float f = 1.0f / 99;
        double d = 0.0d;
        double d2 = 0.0d;
        int i = 0;
        float f2 = 0.0f;
        while (i < 100) {
            float f3 = i * f;
            double d3 = f3;
            Easing easing = this.mStartMotionPath.mKeyFrameEasing;
            Iterator it = this.mMotionPaths.iterator();
            float f4 = Float.NaN;
            float f5 = 0.0f;
            while (it.hasNext()) {
                MotionPaths motionPaths = (MotionPaths) it.next();
                Easing easing2 = motionPaths.mKeyFrameEasing;
                if (easing2 != null) {
                    float f6 = motionPaths.mTime;
                    if (f6 < f3) {
                        easing = easing2;
                        f5 = f6;
                    } else if (Float.isNaN(f4)) {
                        f4 = motionPaths.mTime;
                    }
                }
            }
            if (easing != null) {
                if (Float.isNaN(f4)) {
                    f4 = 1.0f;
                }
                d3 = (((float) easing.get((f3 - f5) / r7)) * (f4 - f5)) + f5;
            }
            this.mSpline[0].getPos(d3, this.mInterpolateData);
            int i2 = i;
            this.mStartMotionPath.getCenter(d3, this.mInterpolateVariables, this.mInterpolateData, fArr, 0);
            if (i2 > 0) {
                c = 0;
                f2 += (float) Math.hypot(d2 - fArr[1], d - fArr[0]);
            } else {
                c = 0;
            }
            d = fArr[c];
            i = i2 + 1;
            d2 = fArr[1];
        }
        return f2;
    }

    public final boolean interpolate(float f, long j, View view, KeyCache keyCache) {
        ViewTimeCycle.PathRotate pathRotate;
        boolean z;
        float f2;
        int i;
        boolean z2;
        double d;
        float f3;
        MotionPaths motionPaths;
        ViewTimeCycle.PathRotate pathRotate2;
        boolean z3;
        double d2;
        float f4;
        float f5;
        boolean z4;
        float f6;
        double d3;
        MotionController motionController = this;
        View view2 = view;
        float adjustedPosition = motionController.getAdjustedPosition(f, null);
        int i2 = motionController.mQuantizeMotionSteps;
        if (i2 != -1) {
            float f7 = 1.0f / i2;
            float floor = ((float) Math.floor(adjustedPosition / f7)) * f7;
            float f8 = (adjustedPosition % f7) / f7;
            if (!Float.isNaN(motionController.mQuantizeMotionPhase)) {
                f8 = (f8 + motionController.mQuantizeMotionPhase) % 1.0f;
            }
            Interpolator interpolator = motionController.mQuantizeMotionInterpolator;
            adjustedPosition = ((interpolator != null ? interpolator.getInterpolation(f8) : ((double) f8) > 0.5d ? 1.0f : 0.0f) * f7) + floor;
        }
        float f9 = adjustedPosition;
        HashMap hashMap = motionController.mAttributesMap;
        if (hashMap != null) {
            Iterator it = hashMap.values().iterator();
            while (it.hasNext()) {
                ((ViewSpline) it.next()).setProperty(view2, f9);
            }
        }
        HashMap hashMap2 = motionController.mTimeCycleAttributesMap;
        if (hashMap2 != null) {
            pathRotate = null;
            z = false;
            for (ViewTimeCycle viewTimeCycle : hashMap2.values()) {
                if (viewTimeCycle instanceof ViewTimeCycle.PathRotate) {
                    pathRotate = (ViewTimeCycle.PathRotate) viewTimeCycle;
                } else {
                    z |= viewTimeCycle.setProperty(f9, j, view, keyCache);
                }
            }
        } else {
            pathRotate = null;
            z = false;
        }
        CurveFit[] curveFitArr = motionController.mSpline;
        MotionPaths motionPaths2 = motionController.mStartMotionPath;
        if (curveFitArr != null) {
            double d4 = f9;
            curveFitArr[0].getPos(d4, motionController.mInterpolateData);
            motionController.mSpline[0].getSlope(d4, motionController.mInterpolateVelocity);
            ArcCurveFit arcCurveFit = motionController.mArcSpline;
            if (arcCurveFit != null) {
                double[] dArr = motionController.mInterpolateData;
                if (dArr.length > 0) {
                    arcCurveFit.getPos(d4, dArr);
                    motionController.mArcSpline.getSlope(d4, motionController.mInterpolateVelocity);
                }
            }
            if (motionController.mNoMovement) {
                d = d4;
                f3 = f9;
                motionPaths = motionPaths2;
                pathRotate2 = pathRotate;
                z3 = z;
            } else {
                int[] iArr = motionController.mInterpolateVariables;
                double[] dArr2 = motionController.mInterpolateData;
                double[] dArr3 = motionController.mInterpolateVelocity;
                boolean z5 = motionController.mForceMeasure;
                float f10 = motionPaths2.mX;
                float f11 = motionPaths2.mY;
                float f12 = motionPaths2.mWidth;
                float f13 = motionPaths2.mHeight;
                if (iArr.length != 0) {
                    f5 = f10;
                    if (motionPaths2.mTempValue.length <= iArr[iArr.length - 1]) {
                        int i3 = iArr[iArr.length - 1] + 1;
                        motionPaths2.mTempValue = new double[i3];
                        motionPaths2.mTempDelta = new double[i3];
                    }
                } else {
                    f5 = f10;
                }
                Arrays.fill(motionPaths2.mTempValue, Double.NaN);
                for (int i4 = 0; i4 < iArr.length; i4++) {
                    double[] dArr4 = motionPaths2.mTempValue;
                    int i5 = iArr[i4];
                    dArr4[i5] = dArr2[i4];
                    motionPaths2.mTempDelta[i5] = dArr3[i4];
                }
                float f14 = Float.NaN;
                f3 = f9;
                pathRotate2 = pathRotate;
                float f15 = f13;
                float f16 = f5;
                float f17 = f11;
                float f18 = 0.0f;
                int i6 = 0;
                float f19 = 0.0f;
                float f20 = 0.0f;
                z3 = z;
                float f21 = 0.0f;
                while (true) {
                    double[] dArr5 = motionPaths2.mTempValue;
                    z4 = z5;
                    if (i6 >= dArr5.length) {
                        break;
                    }
                    if (Double.isNaN(dArr5[i6])) {
                        d3 = d4;
                    } else {
                        d3 = d4;
                        float f22 = (float) (Double.isNaN(motionPaths2.mTempValue[i6]) ? 0.0d : motionPaths2.mTempValue[i6] + 0.0d);
                        float f23 = (float) motionPaths2.mTempDelta[i6];
                        if (i6 == 1) {
                            f16 = f22;
                            f18 = f23;
                        } else if (i6 == 2) {
                            f17 = f22;
                            f21 = f23;
                        } else if (i6 == 3) {
                            f12 = f22;
                            f19 = f23;
                        } else if (i6 == 4) {
                            f15 = f22;
                            f20 = f23;
                        } else if (i6 == 5) {
                            f14 = f22;
                        }
                    }
                    i6++;
                    z5 = z4;
                    d4 = d3;
                }
                d = d4;
                MotionController motionController2 = motionPaths2.mRelativeToController;
                if (motionController2 != null) {
                    float[] fArr = new float[2];
                    float[] fArr2 = new float[2];
                    float f24 = f14;
                    motionController2.getCenter(d, fArr, fArr2);
                    float f25 = fArr[0];
                    float f26 = fArr[1];
                    float f27 = fArr2[0];
                    float f28 = fArr2[1];
                    motionPaths = motionPaths2;
                    double d5 = f16;
                    double d6 = f17;
                    float sin = (float) (((Math.sin(d6) * d5) + f25) - (f12 / 2.0f));
                    float cos = (float) ((f26 - (Math.cos(d6) * d5)) - (f15 / 2.0f));
                    double d7 = f18;
                    f6 = f12;
                    double d8 = f21;
                    float cos2 = (float) ((Math.cos(d6) * d5 * d8) + (Math.sin(d6) * d7) + f27);
                    float sin2 = (float) ((Math.sin(d6) * d5 * d8) + (f28 - (Math.cos(d6) * d7)));
                    if (dArr3.length >= 2) {
                        dArr3[0] = cos2;
                        dArr3[1] = sin2;
                    }
                    if (Float.isNaN(f24)) {
                        view2 = view;
                    } else {
                        view2 = view;
                        view2.setRotation((float) (Math.toDegrees(Math.atan2(sin2, cos2)) + f24));
                    }
                    f17 = cos;
                    f16 = sin;
                } else {
                    float f29 = f14;
                    f6 = f12;
                    motionPaths = motionPaths2;
                    if (!Float.isNaN(f29)) {
                        view2.setRotation(f29 + ((float) Math.toDegrees(Math.atan2((f20 / 2.0f) + f21, (f19 / 2.0f) + f18))) + 0.0f);
                    }
                }
                float f30 = f16 + 0.5f;
                int i7 = (int) f30;
                float f31 = f17 + 0.5f;
                int i8 = (int) f31;
                int i9 = (int) (f30 + f6);
                int i10 = (int) (f31 + f15);
                int i11 = i9 - i7;
                int i12 = i10 - i8;
                if (i11 != view.getMeasuredWidth() || i12 != view.getMeasuredHeight() || z4) {
                    view2.measure(View.MeasureSpec.makeMeasureSpec(i11, 1073741824), View.MeasureSpec.makeMeasureSpec(i12, 1073741824));
                }
                view2.layout(i7, i8, i9, i10);
                motionController = this;
                motionController.mForceMeasure = false;
            }
            if (motionController.mTransformPivotTarget != -1) {
                if (motionController.mTransformPivotView == null) {
                    motionController.mTransformPivotView = ((View) view.getParent()).findViewById(motionController.mTransformPivotTarget);
                }
                if (motionController.mTransformPivotView != null) {
                    float bottom = (motionController.mTransformPivotView.getBottom() + r1.getTop()) / 2.0f;
                    float right = (motionController.mTransformPivotView.getRight() + motionController.mTransformPivotView.getLeft()) / 2.0f;
                    if (view.getRight() - view.getLeft() > 0 && view.getBottom() - view.getTop() > 0) {
                        view2.setPivotX(right - view.getLeft());
                        view2.setPivotY(bottom - view.getTop());
                    }
                }
            }
            HashMap hashMap3 = motionController.mAttributesMap;
            if (hashMap3 != null) {
                for (ViewSpline viewSpline : hashMap3.values()) {
                    if (viewSpline instanceof ViewSpline.PathRotate) {
                        double[] dArr6 = motionController.mInterpolateVelocity;
                        if (dArr6.length > 1) {
                            f4 = f3;
                            view2.setRotation(((ViewSpline.PathRotate) viewSpline).get(f4) + ((float) Math.toDegrees(Math.atan2(dArr6[1], dArr6[0]))));
                            f3 = f4;
                        }
                    }
                    f4 = f3;
                    f3 = f4;
                }
            }
            f2 = f3;
            if (pathRotate2 != null) {
                double[] dArr7 = motionController.mInterpolateVelocity;
                d2 = d;
                i = 1;
                view2.setRotation(pathRotate2.get(f2, j, view, keyCache) + ((float) Math.toDegrees(Math.atan2(dArr7[1], dArr7[0]))));
                z2 = z3 | pathRotate2.mContinue;
            } else {
                d2 = d;
                i = 1;
                z2 = z3;
            }
            int i13 = i;
            while (true) {
                CurveFit[] curveFitArr2 = motionController.mSpline;
                if (i13 >= curveFitArr2.length) {
                    break;
                }
                CurveFit curveFit = curveFitArr2[i13];
                float[] fArr3 = motionController.mValuesBuff;
                curveFit.getPos(d2, fArr3);
                CustomSupport.setInterpolatedValue((ConstraintAttribute) motionPaths.mAttributes.get(motionController.mAttributeNames[i13 - 1]), view2, fArr3);
                i13++;
            }
            MotionConstrainedPoint motionConstrainedPoint = motionController.mStartPoint;
            if (motionConstrainedPoint.mVisibilityMode == 0) {
                if (f2 <= 0.0f) {
                    view2.setVisibility(motionConstrainedPoint.mVisibility);
                } else {
                    MotionConstrainedPoint motionConstrainedPoint2 = motionController.mEndPoint;
                    if (f2 >= 1.0f) {
                        view2.setVisibility(motionConstrainedPoint2.mVisibility);
                    } else if (motionConstrainedPoint2.mVisibility != motionConstrainedPoint.mVisibility) {
                        view2.setVisibility(0);
                    }
                }
            }
            if (motionController.mKeyTriggers != null) {
                int i14 = 0;
                while (true) {
                    KeyTrigger[] keyTriggerArr = motionController.mKeyTriggers;
                    if (i14 >= keyTriggerArr.length) {
                        break;
                    }
                    keyTriggerArr[i14].conditionallyFire(view2, f2);
                    i14++;
                }
            }
        } else {
            f2 = f9;
            boolean z6 = z;
            i = 1;
            float f32 = motionPaths2.mX;
            MotionPaths motionPaths3 = motionController.mEndMotionPath;
            float m = AndroidFlingSpline$$ExternalSyntheticOutline0.m(motionPaths3.mX, f32, f2, f32);
            float f33 = motionPaths2.mY;
            float m2 = AndroidFlingSpline$$ExternalSyntheticOutline0.m(motionPaths3.mY, f33, f2, f33);
            float f34 = motionPaths2.mWidth;
            float f35 = motionPaths3.mWidth;
            float m3 = AndroidFlingSpline$$ExternalSyntheticOutline0.m(f35, f34, f2, f34);
            float f36 = motionPaths2.mHeight;
            float f37 = motionPaths3.mHeight;
            float f38 = m + 0.5f;
            int i15 = (int) f38;
            float f39 = m2 + 0.5f;
            int i16 = (int) f39;
            int i17 = (int) (f38 + m3);
            int m4 = (int) (f39 + AndroidFlingSpline$$ExternalSyntheticOutline0.m(f37, f36, f2, f36));
            int i18 = i17 - i15;
            int i19 = m4 - i16;
            if (f35 != f34 || f37 != f36 || motionController.mForceMeasure) {
                view2.measure(View.MeasureSpec.makeMeasureSpec(i18, 1073741824), View.MeasureSpec.makeMeasureSpec(i19, 1073741824));
                motionController.mForceMeasure = false;
            }
            view2.layout(i15, i16, i17, m4);
            z2 = z6;
        }
        HashMap hashMap4 = motionController.mCycleMap;
        if (hashMap4 != null) {
            for (ViewOscillator viewOscillator : hashMap4.values()) {
                if (viewOscillator instanceof ViewOscillator.PathRotateSet) {
                    double[] dArr8 = motionController.mInterpolateVelocity;
                    view2.setRotation(((ViewOscillator.PathRotateSet) viewOscillator).get(f2) + ((float) Math.toDegrees(Math.atan2(dArr8[i], dArr8[0]))));
                } else {
                    viewOscillator.setProperty(view2, f2);
                }
            }
        }
        return z2;
    }

    public final void readView(MotionPaths motionPaths) {
        motionPaths.setBounds((int) this.mView.getX(), (int) this.mView.getY(), this.mView.getWidth(), this.mView.getHeight());
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:391:0x0d43. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:386:0x0dd6  */
    /* JADX WARN: Type inference failed for: r11v43, types: [androidx.constraintlayout.motion.utils.ViewSpline, androidx.constraintlayout.motion.utils.ViewSpline$CustomSet] */
    /* JADX WARN: Type inference failed for: r14v25, types: [androidx.constraintlayout.motion.utils.ViewTimeCycle, androidx.constraintlayout.motion.utils.ViewTimeCycle$ProgressSet] */
    /* JADX WARN: Type inference failed for: r14v30, types: [androidx.constraintlayout.motion.utils.ViewTimeCycle] */
    /* JADX WARN: Type inference failed for: r2v57, types: [androidx.constraintlayout.motion.utils.ViewTimeCycle, androidx.constraintlayout.motion.utils.ViewTimeCycle$CustomSet] */
    /* JADX WARN: Type inference failed for: r6v78, types: [androidx.constraintlayout.motion.utils.ViewSpline, androidx.constraintlayout.motion.utils.ViewSpline$ProgressSet] */
    /* JADX WARN: Type inference failed for: r6v86, types: [androidx.constraintlayout.motion.utils.ViewSpline] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void setup(int r46, int r47, long r48) {
        /*
            Method dump skipped, instructions count: 4642
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionController.setup(int, int, long):void");
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(" start: x: ");
        MotionPaths motionPaths = this.mStartMotionPath;
        sb.append(motionPaths.mX);
        sb.append(" y: ");
        sb.append(motionPaths.mY);
        sb.append(" end: x: ");
        MotionPaths motionPaths2 = this.mEndMotionPath;
        sb.append(motionPaths2.mX);
        sb.append(" y: ");
        sb.append(motionPaths2.mY);
        return sb.toString();
    }
}

package androidx.constraintlayout.motion.utils;

import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import androidx.constraintlayout.core.PriorityGoalRow$GoalVariableAccessor$$ExternalSyntheticOutline0;
import androidx.constraintlayout.core.motion.utils.CurveFit;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintAttribute;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.Arrays;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ViewSpline {
    public int mCount;
    public CurveFit mCurveFit;
    public String mType;
    public int[] mTimePoints = new int[10];
    public float[] mValues = new float[10];

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class AlphaSet extends ViewSpline {
        public final /* synthetic */ int $r8$classId;

        @Override // androidx.constraintlayout.motion.utils.ViewSpline
        public final void setProperty(View view, float f) {
            switch (this.$r8$classId) {
                case 0:
                    view.setAlpha(get(f));
                    break;
                case 1:
                    view.setElevation(get(f));
                    break;
                case 2:
                    view.setPivotX(get(f));
                    break;
                case 3:
                    view.setPivotY(get(f));
                    break;
                case 4:
                    view.setRotation(get(f));
                    break;
                case 5:
                    view.setRotationX(get(f));
                    break;
                case 6:
                    view.setRotationY(get(f));
                    break;
                case 7:
                    view.setScaleX(get(f));
                    break;
                case 8:
                    view.setScaleY(get(f));
                    break;
                case 9:
                    view.setTranslationX(get(f));
                    break;
                case 10:
                    view.setTranslationY(get(f));
                    break;
                default:
                    view.setTranslationZ(get(f));
                    break;
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class CustomSet extends ViewSpline {
        public SparseArray mConstraintAttributeList;
        public float[] mTempValues;

        @Override // androidx.constraintlayout.motion.utils.ViewSpline
        public final void setPoint(int i, float f) {
            throw new RuntimeException("call of custom attribute setPoint");
        }

        @Override // androidx.constraintlayout.motion.utils.ViewSpline
        public final void setProperty(View view, float f) {
            this.mCurveFit.getPos(f, this.mTempValues);
            CustomSupport.setInterpolatedValue((ConstraintAttribute) this.mConstraintAttributeList.valueAt(0), view, this.mTempValues);
        }

        @Override // androidx.constraintlayout.motion.utils.ViewSpline
        public final void setup(int i) {
            int size = this.mConstraintAttributeList.size();
            int numberOfInterpolatedValues = ((ConstraintAttribute) this.mConstraintAttributeList.valueAt(0)).numberOfInterpolatedValues();
            double[] dArr = new double[size];
            this.mTempValues = new float[numberOfInterpolatedValues];
            double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, size, numberOfInterpolatedValues);
            for (int i2 = 0; i2 < size; i2++) {
                int keyAt = this.mConstraintAttributeList.keyAt(i2);
                ConstraintAttribute constraintAttribute = (ConstraintAttribute) this.mConstraintAttributeList.valueAt(i2);
                dArr[i2] = keyAt * 0.01d;
                constraintAttribute.getValuesToInterpolate(this.mTempValues);
                int i3 = 0;
                while (true) {
                    if (i3 < this.mTempValues.length) {
                        dArr2[i2][i3] = r6[i3];
                        i3++;
                    }
                }
            }
            this.mCurveFit = CurveFit.get(i, dArr, dArr2);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ProgressSet extends ViewSpline {
        public boolean mNoMethod;

        @Override // androidx.constraintlayout.motion.utils.ViewSpline
        public final void setProperty(View view, float f) {
            Method method;
            if (view instanceof MotionLayout) {
                ((MotionLayout) view).setProgress(get(f));
                return;
            }
            if (this.mNoMethod) {
                return;
            }
            try {
                method = view.getClass().getMethod("setProgress", Float.TYPE);
            } catch (NoSuchMethodException unused) {
                this.mNoMethod = true;
                method = null;
            }
            if (method != null) {
                try {
                    method.invoke(view, Float.valueOf(get(f)));
                } catch (IllegalAccessException e) {
                    Log.e("ViewSpline", "unable to setProgress", e);
                } catch (InvocationTargetException e2) {
                    Log.e("ViewSpline", "unable to setProgress", e2);
                }
            }
        }
    }

    public final float get(float f) {
        return (float) this.mCurveFit.getPos(f);
    }

    public void setPoint(int i, float f) {
        int[] iArr = this.mTimePoints;
        if (iArr.length < this.mCount + 1) {
            this.mTimePoints = Arrays.copyOf(iArr, iArr.length * 2);
            float[] fArr = this.mValues;
            this.mValues = Arrays.copyOf(fArr, fArr.length * 2);
        }
        int[] iArr2 = this.mTimePoints;
        int i2 = this.mCount;
        iArr2[i2] = i;
        this.mValues[i2] = f;
        this.mCount = i2 + 1;
    }

    public abstract void setProperty(View view, float f);

    public void setup(int i) {
        int i2;
        int i3 = this.mCount;
        if (i3 == 0) {
            return;
        }
        int[] iArr = this.mTimePoints;
        float[] fArr = this.mValues;
        int[] iArr2 = new int[iArr.length + 10];
        iArr2[0] = i3 - 1;
        iArr2[1] = 0;
        int i4 = 2;
        while (i4 > 0) {
            int i5 = i4 - 1;
            int i6 = iArr2[i5];
            int i7 = i4 - 2;
            int i8 = iArr2[i7];
            if (i6 < i8) {
                int i9 = iArr[i8];
                int i10 = i6;
                int i11 = i10;
                while (i10 < i8) {
                    int i12 = iArr[i10];
                    if (i12 <= i9) {
                        int i13 = iArr[i11];
                        iArr[i11] = i12;
                        iArr[i10] = i13;
                        float f = fArr[i11];
                        fArr[i11] = fArr[i10];
                        fArr[i10] = f;
                        i11++;
                    }
                    i10++;
                }
                int i14 = iArr[i11];
                iArr[i11] = iArr[i8];
                iArr[i8] = i14;
                float f2 = fArr[i11];
                fArr[i11] = fArr[i8];
                fArr[i8] = f2;
                iArr2[i7] = i11 - 1;
                iArr2[i5] = i6;
                int i15 = i4 + 1;
                iArr2[i4] = i8;
                i4 += 2;
                iArr2[i15] = i11 + 1;
            } else {
                i4 = i7;
            }
        }
        int i16 = 1;
        for (int i17 = 1; i17 < this.mCount; i17++) {
            int[] iArr3 = this.mTimePoints;
            if (iArr3[i17 - 1] != iArr3[i17]) {
                i16++;
            }
        }
        double[] dArr = new double[i16];
        double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, i16, 1);
        int i18 = 0;
        for (0; i2 < this.mCount; i2 + 1) {
            if (i2 > 0) {
                int[] iArr4 = this.mTimePoints;
                i2 = iArr4[i2] == iArr4[i2 - 1] ? i2 + 1 : 0;
            }
            dArr[i18] = this.mTimePoints[i2] * 0.01d;
            dArr2[i18][0] = this.mValues[i2];
            i18++;
        }
        this.mCurveFit = CurveFit.get(i, dArr, dArr2);
    }

    public final String toString() {
        String str = this.mType;
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        for (int i = 0; i < this.mCount; i++) {
            StringBuilder m = PriorityGoalRow$GoalVariableAccessor$$ExternalSyntheticOutline0.m(str, "[");
            m.append(this.mTimePoints[i]);
            m.append(" , ");
            m.append(decimalFormat.format(this.mValues[i]));
            m.append("] ");
            str = m.toString();
        }
        return str;
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class PathRotate extends ViewSpline {
        @Override // androidx.constraintlayout.motion.utils.ViewSpline
        public final void setProperty(View view, float f) {
        }
    }
}

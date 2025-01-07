package androidx.constraintlayout.motion.utils;

import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import androidx.constraintlayout.core.PriorityGoalRow$GoalVariableAccessor$$ExternalSyntheticOutline0;
import androidx.constraintlayout.core.motion.utils.CurveFit;
import androidx.constraintlayout.core.motion.utils.KeyCache;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintAttribute;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ViewTimeCycle {
    public int mCount;
    public CurveFit mCurveFit;
    public long mLastTime;
    public String mType;
    public int mWaveShape = 0;
    public int[] mTimePoints = new int[10];
    public float[][] mValues = (float[][]) Array.newInstance((Class<?>) Float.TYPE, 10, 3);
    public float[] mCache = new float[3];
    public boolean mContinue = false;
    public float mLastCycle = Float.NaN;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class AlphaSet extends ViewTimeCycle {
        public final /* synthetic */ int $r8$classId;

        public /* synthetic */ AlphaSet(int i) {
            this.$r8$classId = i;
        }

        @Override // androidx.constraintlayout.motion.utils.ViewTimeCycle
        public final boolean setProperty(float f, long j, View view, KeyCache keyCache) {
            switch (this.$r8$classId) {
                case 0:
                    view.setAlpha(get(f, j, view, keyCache));
                    break;
                case 1:
                    view.setElevation(get(f, j, view, keyCache));
                    break;
                case 2:
                    view.setRotation(get(f, j, view, keyCache));
                    break;
                case 3:
                    view.setRotationX(get(f, j, view, keyCache));
                    break;
                case 4:
                    view.setRotationY(get(f, j, view, keyCache));
                    break;
                case 5:
                    view.setScaleX(get(f, j, view, keyCache));
                    break;
                case 6:
                    view.setScaleY(get(f, j, view, keyCache));
                    break;
                case 7:
                    view.setTranslationX(get(f, j, view, keyCache));
                    break;
                case 8:
                    view.setTranslationY(get(f, j, view, keyCache));
                    break;
                default:
                    view.setTranslationZ(get(f, j, view, keyCache));
                    break;
            }
            return this.mContinue;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class CustomSet extends ViewTimeCycle {
        public String mAttributeName;
        public SparseArray mConstraintAttributeList;
        public float[] mTempValues;
        public SparseArray mWaveProperties;

        @Override // androidx.constraintlayout.motion.utils.ViewTimeCycle
        public final void setPoint(float f, float f2, float f3, int i, int i2) {
            throw new RuntimeException("Wrong call for custom attribute");
        }

        @Override // androidx.constraintlayout.motion.utils.ViewTimeCycle
        public final boolean setProperty(float f, long j, View view, KeyCache keyCache) {
            this.mCurveFit.getPos(f, this.mTempValues);
            float[] fArr = this.mTempValues;
            float f2 = fArr[fArr.length - 2];
            float f3 = fArr[fArr.length - 1];
            long j2 = j - this.mLastTime;
            if (Float.isNaN(this.mLastCycle)) {
                float floatValue = keyCache.getFloatValue(view, this.mAttributeName);
                this.mLastCycle = floatValue;
                if (Float.isNaN(floatValue)) {
                    this.mLastCycle = 0.0f;
                }
            }
            float f4 = (float) ((((j2 * 1.0E-9d) * f2) + this.mLastCycle) % 1.0d);
            this.mLastCycle = f4;
            this.mLastTime = j;
            float calcWave = calcWave(f4);
            this.mContinue = false;
            int i = 0;
            while (true) {
                float[] fArr2 = this.mCache;
                if (i >= fArr2.length) {
                    break;
                }
                boolean z = this.mContinue;
                float f5 = this.mTempValues[i];
                this.mContinue = z | (((double) f5) != 0.0d);
                fArr2[i] = (f5 * calcWave) + f3;
                i++;
            }
            CustomSupport.setInterpolatedValue((ConstraintAttribute) this.mConstraintAttributeList.valueAt(0), view, this.mCache);
            if (f2 != 0.0f) {
                this.mContinue = true;
            }
            return this.mContinue;
        }

        @Override // androidx.constraintlayout.motion.utils.ViewTimeCycle
        public final void setup(int i) {
            int size = this.mConstraintAttributeList.size();
            int numberOfInterpolatedValues = ((ConstraintAttribute) this.mConstraintAttributeList.valueAt(0)).numberOfInterpolatedValues();
            double[] dArr = new double[size];
            int i2 = numberOfInterpolatedValues + 2;
            this.mTempValues = new float[i2];
            this.mCache = new float[numberOfInterpolatedValues];
            double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, size, i2);
            for (int i3 = 0; i3 < size; i3++) {
                int keyAt = this.mConstraintAttributeList.keyAt(i3);
                ConstraintAttribute constraintAttribute = (ConstraintAttribute) this.mConstraintAttributeList.valueAt(i3);
                float[] fArr = (float[]) this.mWaveProperties.valueAt(i3);
                dArr[i3] = keyAt * 0.01d;
                constraintAttribute.getValuesToInterpolate(this.mTempValues);
                int i4 = 0;
                while (true) {
                    if (i4 < this.mTempValues.length) {
                        dArr2[i3][i4] = r8[i4];
                        i4++;
                    }
                }
                double[] dArr3 = dArr2[i3];
                dArr3[numberOfInterpolatedValues] = fArr[0];
                dArr3[numberOfInterpolatedValues + 1] = fArr[1];
            }
            this.mCurveFit = CurveFit.get(i, dArr, dArr2);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class PathRotate extends ViewTimeCycle {
        @Override // androidx.constraintlayout.motion.utils.ViewTimeCycle
        public final boolean setProperty(float f, long j, View view, KeyCache keyCache) {
            return this.mContinue;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ProgressSet extends ViewTimeCycle {
        public boolean mNoMethod;

        @Override // androidx.constraintlayout.motion.utils.ViewTimeCycle
        public final boolean setProperty(float f, long j, View view, KeyCache keyCache) {
            Method method;
            if (view instanceof MotionLayout) {
                ((MotionLayout) view).setProgress(get(f, j, view, keyCache));
            } else {
                if (this.mNoMethod) {
                    return false;
                }
                try {
                    method = view.getClass().getMethod("setProgress", Float.TYPE);
                } catch (NoSuchMethodException unused) {
                    this.mNoMethod = true;
                    method = null;
                }
                if (method != null) {
                    try {
                        method.invoke(view, Float.valueOf(get(f, j, view, keyCache)));
                    } catch (IllegalAccessException e) {
                        Log.e("ViewTimeCycle", "unable to setProgress", e);
                    } catch (InvocationTargetException e2) {
                        Log.e("ViewTimeCycle", "unable to setProgress", e2);
                    }
                }
            }
            return this.mContinue;
        }
    }

    public final float calcWave(float f) {
        switch (this.mWaveShape) {
            case 1:
                return Math.signum(f * 6.2831855f);
            case 2:
                return 1.0f - Math.abs(f);
            case 3:
                return (((f * 2.0f) + 1.0f) % 2.0f) - 1.0f;
            case 4:
                return 1.0f - (((f * 2.0f) + 1.0f) % 2.0f);
            case 5:
                return (float) Math.cos(f * 6.2831855f);
            case 6:
                float abs = 1.0f - Math.abs(((f * 4.0f) % 4.0f) - 2.0f);
                return 1.0f - (abs * abs);
            default:
                return (float) Math.sin(f * 6.2831855f);
        }
    }

    public final float get(float f, long j, View view, KeyCache keyCache) {
        this.mCurveFit.getPos(f, this.mCache);
        float[] fArr = this.mCache;
        boolean z = true;
        float f2 = fArr[1];
        if (f2 == 0.0f) {
            this.mContinue = false;
            return fArr[2];
        }
        if (Float.isNaN(this.mLastCycle)) {
            float floatValue = keyCache.getFloatValue(view, this.mType);
            this.mLastCycle = floatValue;
            if (Float.isNaN(floatValue)) {
                this.mLastCycle = 0.0f;
            }
        }
        float f3 = (float) (((((j - this.mLastTime) * 1.0E-9d) * f2) + this.mLastCycle) % 1.0d);
        this.mLastCycle = f3;
        String str = this.mType;
        if (keyCache.mMap.containsKey(view)) {
            HashMap hashMap = (HashMap) keyCache.mMap.get(view);
            if (hashMap == null) {
                hashMap = new HashMap();
            }
            if (hashMap.containsKey(str)) {
                float[] fArr2 = (float[]) hashMap.get(str);
                if (fArr2 == null) {
                    fArr2 = new float[0];
                }
                if (fArr2.length <= 0) {
                    fArr2 = Arrays.copyOf(fArr2, 1);
                }
                fArr2[0] = f3;
                hashMap.put(str, fArr2);
            } else {
                hashMap.put(str, new float[]{f3});
                keyCache.mMap.put(view, hashMap);
            }
        } else {
            HashMap hashMap2 = new HashMap();
            hashMap2.put(str, new float[]{f3});
            keyCache.mMap.put(view, hashMap2);
        }
        this.mLastTime = j;
        float f4 = this.mCache[0];
        float calcWave = (calcWave(this.mLastCycle) * f4) + this.mCache[2];
        if (f4 == 0.0f && f2 == 0.0f) {
            z = false;
        }
        this.mContinue = z;
        return calcWave;
    }

    public void setPoint(float f, float f2, float f3, int i, int i2) {
        int i3 = this.mCount;
        this.mTimePoints[i3] = i;
        float[] fArr = this.mValues[i3];
        fArr[0] = f;
        fArr[1] = f2;
        fArr[2] = f3;
        this.mWaveShape = Math.max(this.mWaveShape, i2);
        this.mCount++;
    }

    public abstract boolean setProperty(float f, long j, View view, KeyCache keyCache);

    public void setup(int i) {
        float[][] fArr;
        int i2 = this.mCount;
        if (i2 == 0) {
            System.err.println("Error no points added to " + this.mType);
            return;
        }
        int[] iArr = this.mTimePoints;
        int[] iArr2 = new int[iArr.length + 10];
        iArr2[0] = i2 - 1;
        iArr2[1] = 0;
        int i3 = 2;
        while (true) {
            fArr = this.mValues;
            if (i3 <= 0) {
                break;
            }
            int i4 = i3 - 1;
            int i5 = iArr2[i4];
            int i6 = i3 - 2;
            int i7 = iArr2[i6];
            if (i5 < i7) {
                int i8 = iArr[i7];
                int i9 = i5;
                int i10 = i9;
                while (i9 < i7) {
                    int i11 = iArr[i9];
                    if (i11 <= i8) {
                        int i12 = iArr[i10];
                        iArr[i10] = i11;
                        iArr[i9] = i12;
                        float[] fArr2 = fArr[i10];
                        fArr[i10] = fArr[i9];
                        fArr[i9] = fArr2;
                        i10++;
                    }
                    i9++;
                }
                int i13 = iArr[i10];
                iArr[i10] = iArr[i7];
                iArr[i7] = i13;
                float[] fArr3 = fArr[i10];
                fArr[i10] = fArr[i7];
                fArr[i7] = fArr3;
                iArr2[i6] = i10 - 1;
                iArr2[i4] = i5;
                int i14 = i3 + 1;
                iArr2[i3] = i7;
                i3 += 2;
                iArr2[i14] = i10 + 1;
            } else {
                i3 = i6;
            }
        }
        int i15 = 0;
        for (int i16 = 1; i16 < iArr.length; i16++) {
            if (iArr[i16] != iArr[i16 - 1]) {
                i15++;
            }
        }
        if (i15 == 0) {
            i15 = 1;
        }
        double[] dArr = new double[i15];
        double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, i15, 3);
        int i17 = 0;
        for (int i18 = 0; i18 < this.mCount; i18++) {
            if (i18 <= 0 || iArr[i18] != iArr[i18 - 1]) {
                dArr[i17] = iArr[i18] * 0.01d;
                double[] dArr3 = dArr2[i17];
                float[] fArr4 = fArr[i18];
                dArr3[0] = fArr4[0];
                dArr3[1] = fArr4[1];
                dArr3[2] = fArr4[2];
                i17++;
            }
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
}

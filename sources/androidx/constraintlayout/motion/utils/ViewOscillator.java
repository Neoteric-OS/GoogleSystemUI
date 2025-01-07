package androidx.constraintlayout.motion.utils;

import android.util.Log;
import android.view.View;
import androidx.constraintlayout.core.PriorityGoalRow$GoalVariableAccessor$$ExternalSyntheticOutline0;
import androidx.constraintlayout.core.motion.utils.CurveFit;
import androidx.constraintlayout.core.motion.utils.KeyCycleOscillator$1;
import androidx.constraintlayout.core.motion.utils.KeyCycleOscillator$CycleOscillator;
import androidx.constraintlayout.core.motion.utils.KeyCycleOscillator$WavePoint;
import androidx.constraintlayout.core.motion.utils.MonotonicCurveFit;
import androidx.constraintlayout.core.motion.utils.Oscillator;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintAttribute;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ViewOscillator {
    public KeyCycleOscillator$CycleOscillator mCycleOscillator;
    public String mType;
    public int mWaveShape = 0;
    public String mWaveString = null;
    public int mVariesBy = 0;
    public final ArrayList mWavePoints = new ArrayList();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class AlphaSet extends ViewOscillator {
        public final /* synthetic */ int $r8$classId;

        public /* synthetic */ AlphaSet(int i) {
            this.$r8$classId = i;
        }

        @Override // androidx.constraintlayout.motion.utils.ViewOscillator
        public final void setProperty(View view, float f) {
            switch (this.$r8$classId) {
                case 0:
                    view.setAlpha(get(f));
                    break;
                case 1:
                    view.setElevation(get(f));
                    break;
                case 2:
                    view.setRotation(get(f));
                    break;
                case 3:
                    view.setRotationX(get(f));
                    break;
                case 4:
                    view.setRotationY(get(f));
                    break;
                case 5:
                    view.setScaleX(get(f));
                    break;
                case 6:
                    view.setScaleY(get(f));
                    break;
                case 7:
                    view.setTranslationX(get(f));
                    break;
                case 8:
                    view.setTranslationY(get(f));
                    break;
                default:
                    view.setTranslationZ(get(f));
                    break;
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class CustomSet extends ViewOscillator {
        public ConstraintAttribute mCustom;
        public float[] mValue;

        @Override // androidx.constraintlayout.motion.utils.ViewOscillator
        public final void setCustom(ConstraintAttribute constraintAttribute) {
            this.mCustom = constraintAttribute;
        }

        @Override // androidx.constraintlayout.motion.utils.ViewOscillator
        public final void setProperty(View view, float f) {
            float f2 = get(f);
            float[] fArr = this.mValue;
            fArr[0] = f2;
            CustomSupport.setInterpolatedValue(this.mCustom, view, fArr);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ProgressSet extends ViewOscillator {
        public boolean mNoMethod;

        @Override // androidx.constraintlayout.motion.utils.ViewOscillator
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
                    Log.e("ViewOscillator", "unable to setProgress", e);
                } catch (InvocationTargetException e2) {
                    Log.e("ViewOscillator", "unable to setProgress", e2);
                }
            }
        }
    }

    public static ViewOscillator makeSpline(String str) {
        int i = 9;
        int i2 = 8;
        int i3 = 7;
        int i4 = 6;
        int i5 = 5;
        int i6 = 4;
        int i7 = 3;
        int i8 = 2;
        int i9 = 1;
        int i10 = 0;
        if (str.startsWith("CUSTOM")) {
            CustomSet customSet = new CustomSet();
            customSet.mValue = new float[1];
            return customSet;
        }
        switch (str) {
            case "rotationX":
                return new AlphaSet(i7);
            case "rotationY":
                return new AlphaSet(i6);
            case "translationX":
                return new AlphaSet(i3);
            case "translationY":
                return new AlphaSet(i2);
            case "translationZ":
                return new AlphaSet(i);
            case "progress":
                ProgressSet progressSet = new ProgressSet();
                progressSet.mNoMethod = false;
                return progressSet;
            case "scaleX":
                return new AlphaSet(i5);
            case "scaleY":
                return new AlphaSet(i4);
            case "waveVariesBy":
                return new AlphaSet(i10);
            case "rotation":
                return new AlphaSet(i8);
            case "elevation":
                return new AlphaSet(i9);
            case "transitionPathRotate":
                return new PathRotateSet();
            case "alpha":
                return new AlphaSet(i10);
            case "waveOffset":
                return new AlphaSet(i10);
            default:
                return null;
        }
    }

    public final float get(float f) {
        double d;
        double signum;
        double abs;
        KeyCycleOscillator$CycleOscillator keyCycleOscillator$CycleOscillator = this.mCycleOscillator;
        CurveFit curveFit = keyCycleOscillator$CycleOscillator.mCurveFit;
        if (curveFit != null) {
            curveFit.getPos(f, keyCycleOscillator$CycleOscillator.mSplineValueCache);
        } else {
            double[] dArr = keyCycleOscillator$CycleOscillator.mSplineValueCache;
            dArr[0] = keyCycleOscillator$CycleOscillator.mOffsetArr[0];
            dArr[1] = keyCycleOscillator$CycleOscillator.mPhaseArr[0];
            dArr[2] = keyCycleOscillator$CycleOscillator.mValues[0];
        }
        double[] dArr2 = keyCycleOscillator$CycleOscillator.mSplineValueCache;
        double d2 = dArr2[0];
        double d3 = dArr2[1];
        double d4 = f;
        Oscillator oscillator = keyCycleOscillator$CycleOscillator.mOscillator;
        oscillator.getClass();
        double d5 = 0.0d;
        if (d4 <= 0.0d) {
            d = d2;
        } else if (d4 >= 1.0d) {
            d = d2;
            d5 = 1.0d;
        } else {
            int binarySearch = Arrays.binarySearch(oscillator.mPosition, d4);
            if (binarySearch < 0) {
                binarySearch = (-binarySearch) - 1;
            }
            float[] fArr = oscillator.mPeriod;
            float f2 = fArr[binarySearch];
            int i = binarySearch - 1;
            float f3 = fArr[i];
            d = d2;
            double[] dArr3 = oscillator.mPosition;
            double d6 = dArr3[binarySearch];
            double d7 = dArr3[i];
            double d8 = (f2 - f3) / (d6 - d7);
            d5 = ((((d4 * d4) - (d7 * d7)) * d8) / 2.0d) + ((d4 - d7) * (f3 - (d8 * d7))) + oscillator.mArea[i];
        }
        double d9 = d5 + d3;
        switch (oscillator.mType) {
            case 1:
                signum = Math.signum(0.5d - (d9 % 1.0d));
                break;
            case 2:
                abs = Math.abs((((d9 * 4.0d) + 1.0d) % 4.0d) - 2.0d);
                signum = 1.0d - abs;
                break;
            case 3:
                signum = (((d9 * 2.0d) + 1.0d) % 2.0d) - 1.0d;
                break;
            case 4:
                signum = 1.0d - (((d9 * 2.0d) + 1.0d) % 2.0d);
                break;
            case 5:
                signum = Math.cos((d3 + d9) * 6.283185307179586d);
                break;
            case 6:
                double abs2 = 1.0d - Math.abs(((d9 * 4.0d) % 4.0d) - 2.0d);
                abs = abs2 * abs2;
                signum = 1.0d - abs;
                break;
            case 7:
                signum = oscillator.mCustomCurve.getPos(d9 % 1.0d);
                break;
            default:
                signum = Math.sin(6.283185307179586d * d9);
                break;
        }
        return (float) ((signum * keyCycleOscillator$CycleOscillator.mSplineValueCache[2]) + d);
    }

    public abstract void setProperty(View view, float f);

    public final void setup() {
        double[][] dArr;
        int i;
        int size = this.mWavePoints.size();
        if (size == 0) {
            return;
        }
        Collections.sort(this.mWavePoints, new KeyCycleOscillator$1());
        double[] dArr2 = new double[size];
        double[][] dArr3 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, size, 3);
        int i2 = this.mWaveShape;
        String str = this.mWaveString;
        KeyCycleOscillator$CycleOscillator keyCycleOscillator$CycleOscillator = new KeyCycleOscillator$CycleOscillator();
        Oscillator oscillator = new Oscillator();
        oscillator.mPeriod = new float[0];
        oscillator.mPosition = new double[0];
        keyCycleOscillator$CycleOscillator.mOscillator = oscillator;
        oscillator.mType = i2;
        if (str != null) {
            double[] dArr4 = new double[str.length() / 2];
            int indexOf = str.indexOf(40) + 1;
            int i3 = 0;
            int indexOf2 = str.indexOf(44, indexOf);
            while (indexOf2 != -1) {
                dArr4[i3] = Double.parseDouble(str.substring(indexOf, indexOf2).trim());
                indexOf = indexOf2 + 1;
                indexOf2 = str.indexOf(44, indexOf);
                i3++;
            }
            dArr4[i3] = Double.parseDouble(str.substring(indexOf, str.indexOf(41, indexOf)).trim());
            double[] copyOf = Arrays.copyOf(dArr4, i3 + 1);
            int length = (copyOf.length * 3) - 2;
            int length2 = copyOf.length - 1;
            double d = 1.0d / length2;
            char c = 0;
            double[][] dArr5 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, length, 1);
            double[] dArr6 = new double[length];
            int i4 = 0;
            while (i4 < copyOf.length) {
                double d2 = copyOf[i4];
                int i5 = i4 + length2;
                dArr5[i5][c] = d2;
                double[][] dArr7 = dArr3;
                double d3 = i4 * d;
                dArr6[i5] = d3;
                if (i4 > 0) {
                    int i6 = (length2 * 2) + i4;
                    dArr5[i6][c] = d2 + 1.0d;
                    dArr6[i6] = d3 + 1.0d;
                    int i7 = i4 - 1;
                    dArr5[i7][c] = (d2 - 1.0d) - d;
                    dArr6[i7] = (d3 - 1.0d) - d;
                }
                i4++;
                dArr3 = dArr7;
                c = 0;
            }
            dArr = dArr3;
            oscillator.mCustomCurve = new MonotonicCurveFit(dArr6, dArr5);
        } else {
            dArr = dArr3;
        }
        keyCycleOscillator$CycleOscillator.mValues = new float[size];
        keyCycleOscillator$CycleOscillator.mPosition = new double[size];
        keyCycleOscillator$CycleOscillator.mPeriod = new float[size];
        keyCycleOscillator$CycleOscillator.mOffsetArr = new float[size];
        keyCycleOscillator$CycleOscillator.mPhaseArr = new float[size];
        float[] fArr = new float[size];
        this.mCycleOscillator = keyCycleOscillator$CycleOscillator;
        Iterator it = this.mWavePoints.iterator();
        int i8 = 0;
        while (it.hasNext()) {
            KeyCycleOscillator$WavePoint keyCycleOscillator$WavePoint = (KeyCycleOscillator$WavePoint) it.next();
            float f = keyCycleOscillator$WavePoint.mPeriod;
            dArr2[i8] = f * 0.01d;
            double[] dArr8 = dArr[i8];
            float f2 = keyCycleOscillator$WavePoint.mValue;
            dArr8[0] = f2;
            float f3 = keyCycleOscillator$WavePoint.mOffset;
            dArr8[1] = f3;
            float f4 = keyCycleOscillator$WavePoint.mPhase;
            dArr8[2] = f4;
            KeyCycleOscillator$CycleOscillator keyCycleOscillator$CycleOscillator2 = this.mCycleOscillator;
            keyCycleOscillator$CycleOscillator2.mPosition[i8] = keyCycleOscillator$WavePoint.mPosition / 100.0d;
            keyCycleOscillator$CycleOscillator2.mPeriod[i8] = f;
            keyCycleOscillator$CycleOscillator2.mOffsetArr[i8] = f3;
            keyCycleOscillator$CycleOscillator2.mPhaseArr[i8] = f4;
            keyCycleOscillator$CycleOscillator2.mValues[i8] = f2;
            i8++;
        }
        KeyCycleOscillator$CycleOscillator keyCycleOscillator$CycleOscillator3 = this.mCycleOscillator;
        double[] dArr9 = keyCycleOscillator$CycleOscillator3.mPosition;
        double[][] dArr10 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, dArr9.length, 3);
        float[] fArr2 = keyCycleOscillator$CycleOscillator3.mValues;
        keyCycleOscillator$CycleOscillator3.mSplineValueCache = new double[fArr2.length + 2];
        double[] dArr11 = new double[fArr2.length + 2];
        double d4 = dArr9[0];
        float[] fArr3 = keyCycleOscillator$CycleOscillator3.mPeriod;
        Oscillator oscillator2 = keyCycleOscillator$CycleOscillator3.mOscillator;
        if (d4 > 0.0d) {
            oscillator2.addPoint(0.0d, fArr3[0]);
        }
        int length3 = dArr9.length - 1;
        if (dArr9[length3] < 1.0d) {
            oscillator2.addPoint(1.0d, fArr3[length3]);
        }
        for (int i9 = 0; i9 < dArr10.length; i9++) {
            double[] dArr12 = dArr10[i9];
            dArr12[0] = keyCycleOscillator$CycleOscillator3.mOffsetArr[i9];
            dArr12[1] = keyCycleOscillator$CycleOscillator3.mPhaseArr[i9];
            dArr12[2] = fArr2[i9];
            oscillator2.addPoint(dArr9[i9], fArr3[i9]);
        }
        double d5 = 0.0d;
        int i10 = 0;
        while (true) {
            if (i10 >= oscillator2.mPeriod.length) {
                break;
            }
            d5 += r8[i10];
            i10++;
        }
        int i11 = 1;
        double d6 = 0.0d;
        while (true) {
            float[] fArr4 = oscillator2.mPeriod;
            if (i11 >= fArr4.length) {
                break;
            }
            int i12 = i11 - 1;
            float f5 = (fArr4[i12] + fArr4[i11]) / 2.0f;
            double[] dArr13 = oscillator2.mPosition;
            d6 = ((dArr13[i11] - dArr13[i12]) * f5) + d6;
            i11++;
        }
        int i13 = 0;
        while (true) {
            float[] fArr5 = oscillator2.mPeriod;
            if (i13 >= fArr5.length) {
                break;
            }
            fArr5[i13] = fArr5[i13] * ((float) (d5 / d6));
            i13++;
        }
        oscillator2.mArea[0] = 0.0d;
        int i14 = 1;
        while (true) {
            float[] fArr6 = oscillator2.mPeriod;
            if (i14 >= fArr6.length) {
                break;
            }
            int i15 = i14 - 1;
            float f6 = (fArr6[i15] + fArr6[i14]) / 2.0f;
            double[] dArr14 = oscillator2.mPosition;
            double d7 = dArr14[i14] - dArr14[i15];
            double[] dArr15 = oscillator2.mArea;
            dArr15[i14] = (d7 * f6) + dArr15[i15];
            i14++;
        }
        if (dArr9.length > 1) {
            i = 0;
            keyCycleOscillator$CycleOscillator3.mCurveFit = CurveFit.get(0, dArr9, dArr10);
        } else {
            i = 0;
            keyCycleOscillator$CycleOscillator3.mCurveFit = null;
        }
        CurveFit.get(i, dArr2, dArr);
    }

    public final String toString() {
        String str = this.mType;
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        Iterator it = this.mWavePoints.iterator();
        while (it.hasNext()) {
            KeyCycleOscillator$WavePoint keyCycleOscillator$WavePoint = (KeyCycleOscillator$WavePoint) it.next();
            StringBuilder m = PriorityGoalRow$GoalVariableAccessor$$ExternalSyntheticOutline0.m(str, "[");
            m.append(keyCycleOscillator$WavePoint.mPosition);
            m.append(" , ");
            m.append(decimalFormat.format(keyCycleOscillator$WavePoint.mValue));
            m.append("] ");
            str = m.toString();
        }
        return str;
    }

    public void setCustom(ConstraintAttribute constraintAttribute) {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class PathRotateSet extends ViewOscillator {
        @Override // androidx.constraintlayout.motion.utils.ViewOscillator
        public final void setProperty(View view, float f) {
        }
    }
}

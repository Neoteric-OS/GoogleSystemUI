package androidx.core.animation;

import android.util.Log;
import android.util.Property;
import androidx.compose.animation.AndroidFlingSpline$$ExternalSyntheticOutline0;
import androidx.core.animation.Keyframe;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class PropertyValuesHolder implements Cloneable {
    public static final Class[] DOUBLE_VARIANTS;
    public static final Class[] FLOAT_VARIANTS;
    public static final Class[] INTEGER_VARIANTS;
    public static final HashMap sGetterPropertyMap;
    public static final HashMap sSetterPropertyMap;
    public TypeEvaluator mEvaluator;
    public Method mGetter;
    public KeyframeSet mKeyframes;
    public Property mProperty;
    public String mPropertyName;
    public Method mSetter;
    public final Object[] mTmpValueArray;
    public Class mValueType;

    static {
        Class cls = Float.TYPE;
        Class cls2 = Double.TYPE;
        Class cls3 = Integer.TYPE;
        FLOAT_VARIANTS = new Class[]{cls, Float.class, cls2, cls3, Double.class, Integer.class};
        INTEGER_VARIANTS = new Class[]{cls3, Integer.class, cls, cls2, Float.class, Double.class};
        DOUBLE_VARIANTS = new Class[]{cls2, Double.class, cls, cls3, Float.class, Integer.class};
        sSetterPropertyMap = new HashMap();
        sGetterPropertyMap = new HashMap();
    }

    public PropertyValuesHolder(String str) {
        this.mSetter = null;
        this.mGetter = null;
        this.mKeyframes = null;
        this.mTmpValueArray = new Object[1];
        this.mPropertyName = str;
    }

    public static String getMethodName(String str, String str2) {
        if (str2 == null || str2.length() == 0) {
            return str;
        }
        return str + Character.toUpperCase(str2.charAt(0)) + str2.substring(1);
    }

    public abstract void calculateValue(float f);

    public PropertyValuesHolder clone() {
        try {
            PropertyValuesHolder propertyValuesHolder = (PropertyValuesHolder) super.clone();
            propertyValuesHolder.mPropertyName = this.mPropertyName;
            propertyValuesHolder.mProperty = this.mProperty;
            propertyValuesHolder.mKeyframes = this.mKeyframes.m713clone();
            propertyValuesHolder.mEvaluator = this.mEvaluator;
            return propertyValuesHolder;
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    public abstract Object getAnimatedValue();

    /* JADX WARN: Multi-variable type inference failed */
    public final Method getPropertyFunction(Class cls, String str, Class cls2) {
        String methodName = getMethodName(str, this.mPropertyName);
        Method method = null;
        if (cls2 == null) {
            try {
                method = cls.getMethod(methodName, null);
            } catch (NoSuchMethodException unused) {
            }
        } else {
            Class[] clsArr = new Class[1];
            for (Class cls3 : cls2.equals(Float.class) ? FLOAT_VARIANTS : cls2.equals(Integer.class) ? INTEGER_VARIANTS : cls2.equals(Double.class) ? DOUBLE_VARIANTS : new Class[]{cls2}) {
                clsArr[0] = cls3;
                try {
                    try {
                        Method method2 = cls.getMethod(methodName, clsArr);
                        this.mValueType = cls3;
                        return method2;
                    } catch (NoSuchMethodException unused2) {
                    }
                } catch (NoSuchMethodException unused3) {
                    method = cls.getDeclaredMethod(methodName, clsArr);
                    method.setAccessible(true);
                    this.mValueType = cls3;
                    return method;
                }
            }
        }
        if (method == null) {
            Log.w("PropertyValuesHolder", "Method " + getMethodName(str, this.mPropertyName) + "() with type " + cls2 + " not found on target class " + cls);
        }
        return method;
    }

    public abstract void setAnimatedValue(Object obj);

    /* JADX WARN: Code restructure failed: missing block: B:4:0x0027, code lost:
    
        if (java.lang.Float.isNaN(r9) != false) goto L14;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void setFloatValues(float... r9) {
        /*
            r8 = this;
            java.lang.Class r0 = java.lang.Float.TYPE
            r8.mValueType = r0
            int r0 = r9.length
            r1 = 2
            int r1 = java.lang.Math.max(r0, r1)
            androidx.core.animation.Keyframe$FloatKeyframe[] r1 = new androidx.core.animation.Keyframe.FloatKeyframe[r1]
            r2 = 1
            r3 = 0
            r4 = 0
            if (r0 != r2) goto L2c
            androidx.core.animation.Keyframe$FloatKeyframe r0 = new androidx.core.animation.Keyframe$FloatKeyframe
            r0.<init>(r3)
            r1[r4] = r0
            r9 = r9[r4]
            androidx.core.animation.Keyframe$FloatKeyframe r0 = new androidx.core.animation.Keyframe$FloatKeyframe
            r3 = 1065353216(0x3f800000, float:1.0)
            r0.<init>(r3, r9)
            r1[r2] = r0
            boolean r9 = java.lang.Float.isNaN(r9)
            if (r9 == 0) goto L2a
            goto L50
        L2a:
            r2 = r4
            goto L50
        L2c:
            r5 = r9[r4]
            androidx.core.animation.Keyframe$FloatKeyframe r6 = new androidx.core.animation.Keyframe$FloatKeyframe
            r6.<init>(r3, r5)
            r1[r4] = r6
            r3 = r2
        L36:
            if (r3 >= r0) goto L2a
            float r5 = (float) r3
            int r6 = r0 + (-1)
            float r6 = (float) r6
            float r5 = r5 / r6
            r6 = r9[r3]
            androidx.core.animation.Keyframe$FloatKeyframe r7 = new androidx.core.animation.Keyframe$FloatKeyframe
            r7.<init>(r5, r6)
            r1[r3] = r7
            boolean r5 = java.lang.Float.isNaN(r6)
            if (r5 == 0) goto L4d
            r4 = r2
        L4d:
            int r3 = r3 + 1
            goto L36
        L50:
            if (r2 == 0) goto L59
            java.lang.String r9 = "Animator"
            java.lang.String r0 = "Bad value (NaN) in float animator"
            android.util.Log.w(r9, r0)
        L59:
            androidx.core.animation.FloatKeyframeSet r9 = new androidx.core.animation.FloatKeyframeSet
            r9.<init>(r1)
            r8.mKeyframes = r9
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.animation.PropertyValuesHolder.setFloatValues(float[]):void");
    }

    public void setIntValues(int... iArr) {
        this.mValueType = Integer.TYPE;
        int length = iArr.length;
        Keyframe.IntKeyframe[] intKeyframeArr = new Keyframe.IntKeyframe[Math.max(length, 2)];
        if (length == 1) {
            intKeyframeArr[0] = new Keyframe.IntKeyframe(0.0f);
            intKeyframeArr[1] = new Keyframe.IntKeyframe(iArr[0], 1.0f);
        } else {
            intKeyframeArr[0] = new Keyframe.IntKeyframe(iArr[0], 0.0f);
            for (int i = 1; i < length; i++) {
                intKeyframeArr[i] = new Keyframe.IntKeyframe(iArr[i], i / (length - 1));
            }
        }
        this.mKeyframes = new IntKeyframeSet(intKeyframeArr);
    }

    public abstract void setProperty(Property property);

    public final Method setupSetterOrGetter(Class cls, HashMap hashMap, String str, Class cls2) {
        Method method;
        boolean z;
        synchronized (hashMap) {
            try {
                HashMap hashMap2 = (HashMap) hashMap.get(cls);
                method = null;
                if (hashMap2 != null) {
                    z = hashMap2.containsKey(this.mPropertyName);
                    if (z) {
                        method = (Method) hashMap2.get(this.mPropertyName);
                    }
                } else {
                    z = false;
                }
                if (!z) {
                    method = getPropertyFunction(cls, str, cls2);
                    if (hashMap2 == null) {
                        hashMap2 = new HashMap();
                        hashMap.put(cls, hashMap2);
                    }
                    hashMap2.put(this.mPropertyName, method);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return method;
    }

    public final String toString() {
        return this.mPropertyName + ": " + this.mKeyframes.toString();
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class FloatPropertyValuesHolder extends PropertyValuesHolder {
        public float mFloatAnimatedValue;
        public FloatKeyframeSet mFloatKeyframes;

        @Override // androidx.core.animation.PropertyValuesHolder
        public final void calculateValue(float f) {
            float floatValue;
            FloatKeyframeSet floatKeyframeSet = this.mFloatKeyframes;
            if (f > 0.0f) {
                if (f < 1.0f) {
                    Keyframe.FloatKeyframe floatKeyframe = (Keyframe.FloatKeyframe) floatKeyframeSet.mKeyframes.get(0);
                    int i = 1;
                    while (true) {
                        int i2 = floatKeyframeSet.mNumKeyframes;
                        if (i >= i2) {
                            floatValue = ((Float) ((Keyframe) floatKeyframeSet.mKeyframes.get(i2 - 1)).getValue()).floatValue();
                            break;
                        }
                        Keyframe.FloatKeyframe floatKeyframe2 = (Keyframe.FloatKeyframe) floatKeyframeSet.mKeyframes.get(i);
                        float f2 = floatKeyframe2.mFraction;
                        if (f < f2) {
                            float f3 = floatKeyframe.mFraction;
                            float f4 = (f - f3) / (f2 - f3);
                            float f5 = floatKeyframe.mValue;
                            float f6 = floatKeyframe2.mValue;
                            TypeEvaluator typeEvaluator = floatKeyframeSet.mEvaluator;
                            floatValue = typeEvaluator == null ? AndroidFlingSpline$$ExternalSyntheticOutline0.m(f6, f5, f4, f5) : ((Float) typeEvaluator.evaluate(f4, Float.valueOf(f5), Float.valueOf(f6))).floatValue();
                        } else {
                            i++;
                            floatKeyframe = floatKeyframe2;
                        }
                    }
                } else {
                    Keyframe.FloatKeyframe floatKeyframe3 = (Keyframe.FloatKeyframe) floatKeyframeSet.mKeyframes.get(floatKeyframeSet.mNumKeyframes - 2);
                    Keyframe.FloatKeyframe floatKeyframe4 = (Keyframe.FloatKeyframe) floatKeyframeSet.mKeyframes.get(floatKeyframeSet.mNumKeyframes - 1);
                    float f7 = floatKeyframe3.mValue;
                    float f8 = floatKeyframe4.mValue;
                    float f9 = floatKeyframe3.mFraction;
                    float f10 = (f - f9) / (floatKeyframe4.mFraction - f9);
                    TypeEvaluator typeEvaluator2 = floatKeyframeSet.mEvaluator;
                    floatValue = typeEvaluator2 == null ? AndroidFlingSpline$$ExternalSyntheticOutline0.m(f8, f7, f10, f7) : ((Float) typeEvaluator2.evaluate(f10, Float.valueOf(f7), Float.valueOf(f8))).floatValue();
                }
            } else {
                Keyframe.FloatKeyframe floatKeyframe5 = (Keyframe.FloatKeyframe) floatKeyframeSet.mKeyframes.get(0);
                Keyframe.FloatKeyframe floatKeyframe6 = (Keyframe.FloatKeyframe) floatKeyframeSet.mKeyframes.get(1);
                float f11 = floatKeyframe5.mValue;
                float f12 = floatKeyframe6.mValue;
                float f13 = floatKeyframe5.mFraction;
                float f14 = (f - f13) / (floatKeyframe6.mFraction - f13);
                TypeEvaluator typeEvaluator3 = floatKeyframeSet.mEvaluator;
                floatValue = typeEvaluator3 == null ? AndroidFlingSpline$$ExternalSyntheticOutline0.m(f12, f11, f14, f11) : ((Float) typeEvaluator3.evaluate(f14, Float.valueOf(f11), Float.valueOf(f12))).floatValue();
            }
            this.mFloatAnimatedValue = floatValue;
        }

        @Override // androidx.core.animation.PropertyValuesHolder
        public final PropertyValuesHolder clone() {
            FloatPropertyValuesHolder floatPropertyValuesHolder = (FloatPropertyValuesHolder) super.clone();
            floatPropertyValuesHolder.mFloatKeyframes = (FloatKeyframeSet) floatPropertyValuesHolder.mKeyframes;
            return floatPropertyValuesHolder;
        }

        @Override // androidx.core.animation.PropertyValuesHolder
        public final Object getAnimatedValue() {
            return Float.valueOf(this.mFloatAnimatedValue);
        }

        @Override // androidx.core.animation.PropertyValuesHolder
        public final void setAnimatedValue(Object obj) {
            Property property = this.mProperty;
            if (property != null) {
                property.set(obj, Float.valueOf(this.mFloatAnimatedValue));
                return;
            }
            if (this.mSetter != null) {
                try {
                    this.mTmpValueArray[0] = Float.valueOf(this.mFloatAnimatedValue);
                    this.mSetter.invoke(obj, this.mTmpValueArray);
                } catch (IllegalAccessException e) {
                    Log.e("PropertyValuesHolder", e.toString());
                } catch (InvocationTargetException e2) {
                    Log.e("PropertyValuesHolder", e2.toString());
                }
            }
        }

        @Override // androidx.core.animation.PropertyValuesHolder
        public final void setFloatValues(float... fArr) {
            super.setFloatValues(fArr);
            this.mFloatKeyframes = (FloatKeyframeSet) this.mKeyframes;
        }

        @Override // androidx.core.animation.PropertyValuesHolder
        public final void setProperty(Property property) {
            this.mProperty = property;
        }

        /* renamed from: clone, reason: collision with other method in class */
        public final Object m718clone() {
            FloatPropertyValuesHolder floatPropertyValuesHolder = (FloatPropertyValuesHolder) super.clone();
            floatPropertyValuesHolder.mFloatKeyframes = (FloatKeyframeSet) floatPropertyValuesHolder.mKeyframes;
            return floatPropertyValuesHolder;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class IntPropertyValuesHolder extends PropertyValuesHolder {
        public int mIntAnimatedValue;
        public IntKeyframeSet mIntKeyframes;

        @Override // androidx.core.animation.PropertyValuesHolder
        public final void calculateValue(float f) {
            int intValue;
            int round;
            int i;
            int i2;
            float f2;
            IntKeyframeSet intKeyframeSet = this.mIntKeyframes;
            if (f <= 0.0f) {
                Keyframe.IntKeyframe intKeyframe = (Keyframe.IntKeyframe) intKeyframeSet.mKeyframes.get(0);
                Keyframe.IntKeyframe intKeyframe2 = (Keyframe.IntKeyframe) intKeyframeSet.mKeyframes.get(1);
                i = intKeyframe.mValue;
                i2 = intKeyframe2.mValue;
                float f3 = intKeyframe.mFraction;
                f2 = (f - f3) / (intKeyframe2.mFraction - f3);
                TypeEvaluator typeEvaluator = intKeyframeSet.mEvaluator;
                if (typeEvaluator != null) {
                    intValue = ((Integer) typeEvaluator.evaluate(f2, Integer.valueOf(i), Integer.valueOf(i2))).intValue();
                }
                intValue = i + ((int) (f2 * (i2 - i)));
            } else {
                if (f < 1.0f) {
                    Keyframe.IntKeyframe intKeyframe3 = (Keyframe.IntKeyframe) intKeyframeSet.mKeyframes.get(0);
                    int i3 = 1;
                    while (true) {
                        int i4 = intKeyframeSet.mNumKeyframes;
                        if (i3 >= i4) {
                            intValue = ((Integer) ((Keyframe) intKeyframeSet.mKeyframes.get(i4 - 1)).getValue()).intValue();
                            break;
                        }
                        Keyframe.IntKeyframe intKeyframe4 = (Keyframe.IntKeyframe) intKeyframeSet.mKeyframes.get(i3);
                        float f4 = intKeyframe4.mFraction;
                        if (f < f4) {
                            float f5 = intKeyframe3.mFraction;
                            float f6 = (f - f5) / (f4 - f5);
                            int i5 = intKeyframe3.mValue;
                            int i6 = intKeyframe4.mValue;
                            TypeEvaluator typeEvaluator2 = intKeyframeSet.mEvaluator;
                            round = typeEvaluator2 == null ? Math.round(f6 * (i6 - i5)) + i5 : ((Integer) typeEvaluator2.evaluate(f6, Integer.valueOf(i5), Integer.valueOf(i6))).intValue();
                        } else {
                            i3++;
                            intKeyframe3 = intKeyframe4;
                        }
                    }
                } else {
                    Keyframe.IntKeyframe intKeyframe5 = (Keyframe.IntKeyframe) intKeyframeSet.mKeyframes.get(intKeyframeSet.mNumKeyframes - 2);
                    Keyframe.IntKeyframe intKeyframe6 = (Keyframe.IntKeyframe) intKeyframeSet.mKeyframes.get(intKeyframeSet.mNumKeyframes - 1);
                    i = intKeyframe5.mValue;
                    i2 = intKeyframe6.mValue;
                    float f7 = intKeyframe5.mFraction;
                    f2 = (f - f7) / (intKeyframe6.mFraction - f7);
                    TypeEvaluator typeEvaluator3 = intKeyframeSet.mEvaluator;
                    if (typeEvaluator3 != null) {
                        round = ((Integer) typeEvaluator3.evaluate(f2, Integer.valueOf(i), Integer.valueOf(i2))).intValue();
                    }
                    intValue = i + ((int) (f2 * (i2 - i)));
                }
                intValue = round;
            }
            this.mIntAnimatedValue = intValue;
        }

        @Override // androidx.core.animation.PropertyValuesHolder
        public final PropertyValuesHolder clone() {
            IntPropertyValuesHolder intPropertyValuesHolder = (IntPropertyValuesHolder) super.clone();
            intPropertyValuesHolder.mIntKeyframes = (IntKeyframeSet) intPropertyValuesHolder.mKeyframes;
            return intPropertyValuesHolder;
        }

        @Override // androidx.core.animation.PropertyValuesHolder
        public final Object getAnimatedValue() {
            return Integer.valueOf(this.mIntAnimatedValue);
        }

        @Override // androidx.core.animation.PropertyValuesHolder
        public final void setAnimatedValue(Object obj) {
            Property property = this.mProperty;
            if (property != null) {
                property.set(obj, Integer.valueOf(this.mIntAnimatedValue));
                return;
            }
            try {
                this.mTmpValueArray[0] = Integer.valueOf(this.mIntAnimatedValue);
                this.mSetter.invoke(obj, this.mTmpValueArray);
            } catch (IllegalAccessException e) {
                Log.e("PropertyValuesHolder", e.toString());
            } catch (InvocationTargetException e2) {
                Log.e("PropertyValuesHolder", e2.toString());
            }
        }

        @Override // androidx.core.animation.PropertyValuesHolder
        public final void setIntValues(int... iArr) {
            super.setIntValues(iArr);
            this.mIntKeyframes = (IntKeyframeSet) this.mKeyframes;
        }

        @Override // androidx.core.animation.PropertyValuesHolder
        public final void setProperty(Property property) {
            this.mProperty = property;
        }

        /* renamed from: clone, reason: collision with other method in class */
        public final Object m719clone() {
            IntPropertyValuesHolder intPropertyValuesHolder = (IntPropertyValuesHolder) super.clone();
            intPropertyValuesHolder.mIntKeyframes = (IntKeyframeSet) intPropertyValuesHolder.mKeyframes;
            return intPropertyValuesHolder;
        }
    }

    public PropertyValuesHolder(Property property) {
        this.mSetter = null;
        this.mGetter = null;
        this.mKeyframes = null;
        this.mTmpValueArray = new Object[1];
        this.mProperty = property;
        if (property != null) {
            this.mPropertyName = property.getName();
        }
    }
}

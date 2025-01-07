package androidx.core.animation;

import android.util.Log;
import android.util.Property;
import androidx.constraintlayout.core.PriorityGoalRow$GoalVariableAccessor$$ExternalSyntheticOutline0;
import androidx.core.animation.AnimationHandler;
import androidx.core.animation.PropertyValuesHolder;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ObjectAnimator extends ValueAnimator {
    public Property mProperty;
    public String mPropertyName;
    public WeakReference mTarget;

    public static ObjectAnimator ofFloat(Object obj, Property property, float... fArr) {
        ObjectAnimator objectAnimator = new ObjectAnimator();
        objectAnimator.setTarget(obj);
        PropertyValuesHolder[] propertyValuesHolderArr = objectAnimator.mValues;
        if (propertyValuesHolderArr != null) {
            PropertyValuesHolder propertyValuesHolder = propertyValuesHolderArr[0];
            String str = propertyValuesHolder.mPropertyName;
            propertyValuesHolder.setProperty(property);
            objectAnimator.mValuesMap.remove(str);
            objectAnimator.mValuesMap.put(objectAnimator.mPropertyName, propertyValuesHolder);
        }
        if (objectAnimator.mProperty != null) {
            objectAnimator.mPropertyName = property.getName();
        }
        objectAnimator.mProperty = property;
        objectAnimator.mInitialized = false;
        objectAnimator.setFloatValues(fArr);
        return objectAnimator;
    }

    @Override // androidx.core.animation.ValueAnimator
    public final void animateValue(float f) {
        WeakReference weakReference = this.mTarget;
        Object obj = weakReference == null ? null : weakReference.get();
        if (this.mTarget != null && obj == null) {
            cancel();
            return;
        }
        super.animateValue(f);
        int length = this.mValues.length;
        for (int i = 0; i < length; i++) {
            this.mValues[i].setAnimatedValue(obj);
        }
    }

    @Override // androidx.core.animation.ValueAnimator, androidx.core.animation.Animator
    /* renamed from: clone */
    public final Animator m710clone() {
        return (ObjectAnimator) super.m710clone();
    }

    @Override // androidx.core.animation.ValueAnimator
    public final void initAnimation$1() {
        if (this.mInitialized) {
            return;
        }
        WeakReference weakReference = this.mTarget;
        Object obj = weakReference == null ? null : weakReference.get();
        if (obj != null) {
            int length = this.mValues.length;
            for (int i = 0; i < length; i++) {
                PropertyValuesHolder propertyValuesHolder = this.mValues[i];
                if (propertyValuesHolder.mProperty != null) {
                    try {
                        List list = propertyValuesHolder.mKeyframes.mKeyframes;
                        int size = list == null ? 0 : list.size();
                        Object obj2 = null;
                        for (int i2 = 0; i2 < size; i2++) {
                            Keyframe keyframe = (Keyframe) list.get(i2);
                            if (!keyframe.mHasValue || keyframe.mValueWasSetOnStart) {
                                if (obj2 == null) {
                                    obj2 = propertyValuesHolder.mProperty.get(obj);
                                }
                                keyframe.setValue(obj2);
                                keyframe.mValueWasSetOnStart = true;
                            }
                        }
                    } catch (ClassCastException unused) {
                        Log.w("PropertyValuesHolder", "No such property (" + propertyValuesHolder.mProperty.getName() + ") on target object " + obj + ". Trying reflection instead");
                        propertyValuesHolder.mProperty = null;
                    }
                }
                if (propertyValuesHolder.mProperty == null) {
                    Class<?> cls = obj.getClass();
                    if (propertyValuesHolder.mSetter == null) {
                        propertyValuesHolder.mSetter = propertyValuesHolder.setupSetterOrGetter(cls, PropertyValuesHolder.sSetterPropertyMap, "set", propertyValuesHolder.mValueType);
                    }
                    List list2 = propertyValuesHolder.mKeyframes.mKeyframes;
                    int size2 = list2 == null ? 0 : list2.size();
                    for (int i3 = 0; i3 < size2; i3++) {
                        Keyframe keyframe2 = (Keyframe) list2.get(i3);
                        if (!keyframe2.mHasValue || keyframe2.mValueWasSetOnStart) {
                            if (propertyValuesHolder.mGetter == null) {
                                Method method = propertyValuesHolder.setupSetterOrGetter(cls, PropertyValuesHolder.sGetterPropertyMap, "get", null);
                                propertyValuesHolder.mGetter = method;
                                if (method == null) {
                                    break;
                                }
                            }
                            try {
                                keyframe2.setValue(propertyValuesHolder.mGetter.invoke(obj, null));
                                keyframe2.mValueWasSetOnStart = true;
                            } catch (IllegalAccessException e) {
                                Log.e("PropertyValuesHolder", e.toString());
                            } catch (InvocationTargetException e2) {
                                Log.e("PropertyValuesHolder", e2.toString());
                            }
                        }
                    }
                }
            }
        }
        super.initAnimation$1();
    }

    @Override // androidx.core.animation.ValueAnimator, androidx.core.animation.Animator
    public final boolean isInitialized() {
        return this.mInitialized;
    }

    @Override // androidx.core.animation.ValueAnimator, androidx.core.animation.Animator
    public final Animator setDuration(long j) {
        super.setDuration(j);
        return this;
    }

    @Override // androidx.core.animation.ValueAnimator
    public final void setFloatValues(float... fArr) {
        PropertyValuesHolder[] propertyValuesHolderArr = this.mValues;
        if (propertyValuesHolderArr != null && propertyValuesHolderArr.length != 0) {
            super.setFloatValues(fArr);
            return;
        }
        Property property = this.mProperty;
        if (property != null) {
            Class[] clsArr = PropertyValuesHolder.FLOAT_VARIANTS;
            PropertyValuesHolder.FloatPropertyValuesHolder floatPropertyValuesHolder = new PropertyValuesHolder.FloatPropertyValuesHolder(property);
            floatPropertyValuesHolder.setFloatValues(fArr);
            setValues(floatPropertyValuesHolder);
            return;
        }
        String str = this.mPropertyName;
        Class[] clsArr2 = PropertyValuesHolder.FLOAT_VARIANTS;
        PropertyValuesHolder.FloatPropertyValuesHolder floatPropertyValuesHolder2 = new PropertyValuesHolder.FloatPropertyValuesHolder(str);
        floatPropertyValuesHolder2.setFloatValues(fArr);
        setValues(floatPropertyValuesHolder2);
    }

    public final void setTarget(Object obj) {
        WeakReference weakReference = this.mTarget;
        if ((weakReference == null ? null : weakReference.get()) != obj) {
            if (this.mStarted) {
                cancel();
            }
            this.mTarget = obj != null ? new WeakReference(obj) : null;
            this.mInitialized = false;
        }
    }

    @Override // androidx.core.animation.ValueAnimator, androidx.core.animation.Animator
    public final void start() {
        AnimationHandler animationHandler = AnimationHandler.getInstance();
        for (int size = animationHandler.mAnimationCallbacks.size() - 1; size >= 0; size--) {
            AnimationHandler.AnimationFrameCallback animationFrameCallback = (AnimationHandler.AnimationFrameCallback) animationHandler.mAnimationCallbacks.get(size);
            if (animationFrameCallback != null && (animationFrameCallback instanceof ObjectAnimator)) {
            }
        }
        start(false);
    }

    @Override // androidx.core.animation.ValueAnimator
    public final String toString() {
        StringBuilder sb = new StringBuilder("ObjectAnimator@");
        sb.append(Integer.toHexString(hashCode()));
        sb.append(", target ");
        WeakReference weakReference = this.mTarget;
        sb.append(weakReference == null ? null : weakReference.get());
        String sb2 = sb.toString();
        if (this.mValues != null) {
            for (int i = 0; i < this.mValues.length; i++) {
                StringBuilder m = PriorityGoalRow$GoalVariableAccessor$$ExternalSyntheticOutline0.m(sb2, "\n    ");
                m.append(this.mValues[i].toString());
                sb2 = m.toString();
            }
        }
        return sb2;
    }

    @Override // androidx.core.animation.ValueAnimator, androidx.core.animation.Animator
    /* renamed from: clone */
    public final ValueAnimator m710clone() {
        return (ObjectAnimator) super.m710clone();
    }

    @Override // androidx.core.animation.ValueAnimator, androidx.core.animation.Animator
    public final ValueAnimator setDuration(long j) {
        super.setDuration(j);
        return this;
    }

    @Override // androidx.core.animation.ValueAnimator
    /* renamed from: clone, reason: collision with other method in class */
    public final Object mo716clone() {
        return (ObjectAnimator) super.m710clone();
    }

    /* renamed from: setDuration, reason: collision with other method in class */
    public final void m717setDuration(long j) {
        super.setDuration(j);
    }
}

package androidx.constraintlayout.motion.widget;

import android.graphics.Rect;
import android.util.Log;
import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.motion.utils.ViewSpline;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintSet;
import java.util.HashMap;
import java.util.LinkedHashMap;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MotionConstrainedPoint implements Comparable {
    public int mVisibility;
    public float rotationY = 0.0f;
    public int mVisibilityMode = 0;
    public final LinkedHashMap mAttributes = new LinkedHashMap();
    public float mAlpha = 1.0f;
    public float mElevation = 0.0f;
    public float mRotation = 0.0f;
    public float mRotationX = 0.0f;
    public float mScaleX = 1.0f;
    public float mScaleY = 1.0f;
    public float mPivotX = Float.NaN;
    public float mPivotY = Float.NaN;
    public float mTranslationX = 0.0f;
    public float mTranslationY = 0.0f;
    public float mTranslationZ = 0.0f;
    public float mPathRotate = Float.NaN;
    public float mProgress = Float.NaN;

    public static boolean diff(float f, float f2) {
        return (Float.isNaN(f) || Float.isNaN(f2)) ? Float.isNaN(f) != Float.isNaN(f2) : Math.abs(f - f2) > 1.0E-6f;
    }

    public final void addValues(HashMap hashMap, int i) {
        for (String str : hashMap.keySet()) {
            ViewSpline viewSpline = (ViewSpline) hashMap.get(str);
            if (viewSpline != null) {
                str.getClass();
                switch (str) {
                    case "rotationX":
                        viewSpline.setPoint(i, Float.isNaN(this.mRotationX) ? 0.0f : this.mRotationX);
                        break;
                    case "rotationY":
                        viewSpline.setPoint(i, Float.isNaN(this.rotationY) ? 0.0f : this.rotationY);
                        break;
                    case "translationX":
                        viewSpline.setPoint(i, Float.isNaN(this.mTranslationX) ? 0.0f : this.mTranslationX);
                        break;
                    case "translationY":
                        viewSpline.setPoint(i, Float.isNaN(this.mTranslationY) ? 0.0f : this.mTranslationY);
                        break;
                    case "translationZ":
                        viewSpline.setPoint(i, Float.isNaN(this.mTranslationZ) ? 0.0f : this.mTranslationZ);
                        break;
                    case "progress":
                        viewSpline.setPoint(i, Float.isNaN(this.mProgress) ? 0.0f : this.mProgress);
                        break;
                    case "scaleX":
                        viewSpline.setPoint(i, Float.isNaN(this.mScaleX) ? 1.0f : this.mScaleX);
                        break;
                    case "scaleY":
                        viewSpline.setPoint(i, Float.isNaN(this.mScaleY) ? 1.0f : this.mScaleY);
                        break;
                    case "transformPivotX":
                        viewSpline.setPoint(i, Float.isNaN(this.mPivotX) ? 0.0f : this.mPivotX);
                        break;
                    case "transformPivotY":
                        viewSpline.setPoint(i, Float.isNaN(this.mPivotY) ? 0.0f : this.mPivotY);
                        break;
                    case "rotation":
                        viewSpline.setPoint(i, Float.isNaN(this.mRotation) ? 0.0f : this.mRotation);
                        break;
                    case "elevation":
                        viewSpline.setPoint(i, Float.isNaN(this.mElevation) ? 0.0f : this.mElevation);
                        break;
                    case "transitionPathRotate":
                        viewSpline.setPoint(i, Float.isNaN(this.mPathRotate) ? 0.0f : this.mPathRotate);
                        break;
                    case "alpha":
                        viewSpline.setPoint(i, Float.isNaN(this.mAlpha) ? 1.0f : this.mAlpha);
                        break;
                    default:
                        if (str.startsWith("CUSTOM")) {
                            String str2 = str.split(",")[1];
                            if (this.mAttributes.containsKey(str2)) {
                                ConstraintAttribute constraintAttribute = (ConstraintAttribute) this.mAttributes.get(str2);
                                if (viewSpline instanceof ViewSpline.CustomSet) {
                                    ((ViewSpline.CustomSet) viewSpline).mConstraintAttributeList.append(i, constraintAttribute);
                                    break;
                                } else {
                                    Log.e("MotionPaths", str + " ViewSpline not a CustomSet frame = " + i + ", value" + constraintAttribute.getValueToInterpolate() + viewSpline);
                                    break;
                                }
                            } else {
                                break;
                            }
                        } else {
                            Log.e("MotionPaths", "UNKNOWN spline ".concat(str));
                            break;
                        }
                }
            }
        }
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        ((MotionConstrainedPoint) obj).getClass();
        return Float.compare(0.0f, 0.0f);
    }

    public final void setState(Rect rect, ConstraintSet constraintSet, int i, int i2) {
        rect.width();
        rect.height();
        ConstraintSet.Constraint constraint = constraintSet.get(i2);
        ConstraintSet.PropertySet propertySet = constraint.propertySet;
        int i3 = propertySet.mVisibilityMode;
        this.mVisibilityMode = i3;
        int i4 = propertySet.visibility;
        this.mVisibility = i4;
        this.mAlpha = (i4 == 0 || i3 != 0) ? propertySet.alpha : 0.0f;
        ConstraintSet.Transform transform = constraint.transform;
        boolean z = transform.applyElevation;
        this.mElevation = transform.elevation;
        this.mRotation = transform.rotation;
        this.mRotationX = transform.rotationX;
        this.rotationY = transform.rotationY;
        this.mScaleX = transform.scaleX;
        this.mScaleY = transform.scaleY;
        this.mPivotX = transform.transformPivotX;
        this.mPivotY = transform.transformPivotY;
        this.mTranslationX = transform.translationX;
        this.mTranslationY = transform.translationY;
        this.mTranslationZ = transform.translationZ;
        ConstraintSet.Motion motion = constraint.motion;
        Easing.getInterpolator(motion.mTransitionEasing);
        this.mPathRotate = motion.mPathRotate;
        this.mProgress = constraint.propertySet.mProgress;
        for (String str : constraint.mCustomConstraints.keySet()) {
            ConstraintAttribute constraintAttribute = (ConstraintAttribute) constraint.mCustomConstraints.get(str);
            int ordinal = constraintAttribute.mType.ordinal();
            if (ordinal != 4 && ordinal != 5 && ordinal != 7) {
                this.mAttributes.put(str, constraintAttribute);
            }
        }
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i != 4) {
                        return;
                    }
                }
            }
            float f = this.mRotation + 90.0f;
            this.mRotation = f;
            if (f > 180.0f) {
                this.mRotation = f - 360.0f;
                return;
            }
            return;
        }
        this.mRotation -= 90.0f;
    }
}

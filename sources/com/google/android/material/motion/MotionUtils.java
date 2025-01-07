package com.google.android.material.motion;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.util.TypedValue;
import android.view.animation.AnimationUtils;
import android.view.animation.PathInterpolator;
import androidx.core.graphics.PathParser;
import com.google.android.material.resources.MaterialAttributes;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class MotionUtils {
    public static float getLegacyControlPoint(int i, String[] strArr) {
        float parseFloat = Float.parseFloat(strArr[i]);
        if (parseFloat >= 0.0f && parseFloat <= 1.0f) {
            return parseFloat;
        }
        throw new IllegalArgumentException("Motion easing control point value must be between 0 and 1; instead got: " + parseFloat);
    }

    public static boolean isLegacyEasingType(String str, String str2) {
        return str.startsWith(str2.concat("(")) && str.endsWith(")");
    }

    public static int resolveThemeDuration(int i, int i2, Context context) {
        TypedValue resolve = MaterialAttributes.resolve(i, context);
        return (resolve == null || resolve.type != 16) ? i2 : resolve.data;
    }

    public static TimeInterpolator resolveThemeInterpolator(Context context, int i, TimeInterpolator timeInterpolator) {
        TypedValue typedValue = new TypedValue();
        if (!context.getTheme().resolveAttribute(i, typedValue, true)) {
            return timeInterpolator;
        }
        if (typedValue.type != 3) {
            throw new IllegalArgumentException("Motion easing theme attribute must be an @interpolator resource for ?attr/motionEasing*Interpolator attributes or a string for ?attr/motionEasing* attributes.");
        }
        String valueOf = String.valueOf(typedValue.string);
        if (!isLegacyEasingType(valueOf, "cubic-bezier") && !isLegacyEasingType(valueOf, "path")) {
            return AnimationUtils.loadInterpolator(context, typedValue.resourceId);
        }
        if (!isLegacyEasingType(valueOf, "cubic-bezier")) {
            if (isLegacyEasingType(valueOf, "path")) {
                return new PathInterpolator(PathParser.createPathFromPathData(valueOf.substring(5, valueOf.length() - 1)));
            }
            throw new IllegalArgumentException("Invalid motion easing type: ".concat(valueOf));
        }
        String[] split = valueOf.substring(13, valueOf.length() - 1).split(",");
        if (split.length == 4) {
            return new PathInterpolator(getLegacyControlPoint(0, split), getLegacyControlPoint(1, split), getLegacyControlPoint(2, split), getLegacyControlPoint(3, split));
        }
        throw new IllegalArgumentException("Motion easing theme attribute must have 4 control points if using bezier curve format; instead got: " + split.length);
    }
}

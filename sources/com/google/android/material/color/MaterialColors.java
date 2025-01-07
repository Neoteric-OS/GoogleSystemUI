package com.google.android.material.color;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import androidx.core.graphics.ColorUtils;
import com.google.android.material.resources.MaterialAttributes;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class MaterialColors {
    public static int getColor(View view, int i) {
        Context context = view.getContext();
        Context context2 = view.getContext();
        String canonicalName = view.getClass().getCanonicalName();
        TypedValue resolve = MaterialAttributes.resolve(i, context2);
        if (resolve == null) {
            throw new IllegalArgumentException(String.format("%1$s requires a value for the %2$s attribute to be set in your app theme. You can either set the attribute in your theme or update your theme to inherit from Theme.MaterialComponents (or a descendant).", canonicalName, context2.getResources().getResourceName(i)));
        }
        int i2 = resolve.resourceId;
        return i2 != 0 ? context.getColor(i2) : resolve.data;
    }

    public static int layer(int i, float f, int i2) {
        return ColorUtils.compositeColors(ColorUtils.setAlphaComponent(i2, Math.round(Color.alpha(i2) * f)), i);
    }

    public static int getColor(int i, Context context) {
        Integer num;
        int i2;
        TypedValue resolve = MaterialAttributes.resolve(i, context);
        if (resolve != null) {
            int i3 = resolve.resourceId;
            if (i3 != 0) {
                i2 = context.getColor(i3);
            } else {
                i2 = resolve.data;
            }
            num = Integer.valueOf(i2);
        } else {
            num = null;
        }
        if (num != null) {
            return num.intValue();
        }
        return 0;
    }
}

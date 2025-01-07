package com.android.systemui.util.leak;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class RotationUtils {
    public static int getExactRotation(Context context) {
        int rotation = context.getDisplay().getRotation();
        if (rotation == 1) {
            return 1;
        }
        if (rotation == 3) {
            return 3;
        }
        return rotation == 2 ? 2 : 0;
    }

    public static Resources getResourcesForRotation(int i, Context context) {
        int i2 = 1;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        throw new IllegalArgumentException(AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "Unknown rotation: "));
                    }
                }
            }
            i2 = 2;
        }
        Configuration configuration = new Configuration(context.getResources().getConfiguration());
        configuration.orientation = i2;
        return context.createConfigurationContext(configuration).getResources();
    }

    public static int getRotation(Context context) {
        int rotation = context.getDisplay().getRotation();
        if (rotation == 1) {
            return 1;
        }
        return rotation == 3 ? 3 : 0;
    }
}

package com.android.systemui.shared.rotation;

import android.content.Context;
import android.util.Log;
import com.android.internal.view.RotationPolicy;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class RotationPolicyUtil {
    public static final Boolean isRotationLocked(Context context) {
        try {
            return Boolean.valueOf(RotationPolicy.isRotationLocked(context));
        } catch (SecurityException e) {
            Log.e("RotationPolicy", "Failed to get isRotationLocked", e);
            return null;
        }
    }
}

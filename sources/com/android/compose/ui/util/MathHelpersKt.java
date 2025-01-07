package com.android.compose.ui.util;

import androidx.compose.ui.geometry.OffsetKt;
import com.android.compose.animation.scene.Scale;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class MathHelpersKt {
    public static final Scale lerp(Scale scale, Scale scale2, float f) {
        long j = scale.pivot;
        long j2 = j & 9223372034707292159L;
        long j3 = scale2.pivot;
        if (j2 != 9205357640488583168L && (9223372034707292159L & j3) != 9205357640488583168L) {
            j = OffsetKt.m318lerpWko1d7g(f, j, j3);
        } else if (j2 == 9205357640488583168L) {
            j = j3;
        }
        return new Scale(androidx.compose.ui.util.MathHelpersKt.lerp(scale.scaleX, scale2.scaleX, f), androidx.compose.ui.util.MathHelpersKt.lerp(scale.scaleY, scale2.scaleY, f), j);
    }

    /* renamed from: lerp-e0twbBA, reason: not valid java name */
    public static final long m746lerpe0twbBA(float f, long j, long j2) {
        return (androidx.compose.ui.util.MathHelpersKt.lerp((int) (j >> 32), f, (int) (j2 >> 32)) << 32) | (androidx.compose.ui.util.MathHelpersKt.lerp((int) (j & 4294967295L), f, (int) (j2 & 4294967295L)) & 4294967295L);
    }
}

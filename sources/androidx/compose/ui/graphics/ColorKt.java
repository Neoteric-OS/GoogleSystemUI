package androidx.compose.ui.graphics;

import androidx.compose.ui.graphics.colorspace.ColorSpaces;
import androidx.compose.ui.graphics.colorspace.Oklab;
import androidx.compose.ui.util.MathHelpersKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ColorKt {
    /* JADX WARN: Removed duplicated region for block: B:101:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x015e  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x01a9  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x01b0  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0170  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final long Color(float r20, float r21, float r22, float r23, androidx.compose.ui.graphics.colorspace.ColorSpace r24) {
        /*
            Method dump skipped, instructions count: 472
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.graphics.ColorKt.Color(float, float, float, float, androidx.compose.ui.graphics.colorspace.ColorSpace):long");
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0098  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final long UncheckedColor(float r18, float r19, float r20, float r21, androidx.compose.ui.graphics.colorspace.ColorSpace r22) {
        /*
            Method dump skipped, instructions count: 338
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.graphics.ColorKt.UncheckedColor(float, float, float, float, androidx.compose.ui.graphics.colorspace.ColorSpace):long");
    }

    /* renamed from: compositeOver--OWjLjI, reason: not valid java name */
    public static final long m371compositeOverOWjLjI(long j, long j2) {
        float f;
        float f2;
        long m361convertvNxB06k = Color.m361convertvNxB06k(j, Color.m366getColorSpaceimpl(j2));
        float m364getAlphaimpl = Color.m364getAlphaimpl(j2);
        float m364getAlphaimpl2 = Color.m364getAlphaimpl(m361convertvNxB06k);
        float f3 = 1.0f - m364getAlphaimpl2;
        float f4 = (m364getAlphaimpl * f3) + m364getAlphaimpl2;
        float m368getRedimpl = Color.m368getRedimpl(m361convertvNxB06k);
        float m368getRedimpl2 = Color.m368getRedimpl(j2);
        float f5 = 0.0f;
        if (f4 == 0.0f) {
            f = 0.0f;
        } else {
            f = (((m368getRedimpl2 * m364getAlphaimpl) * f3) + (m368getRedimpl * m364getAlphaimpl2)) / f4;
        }
        float m367getGreenimpl = Color.m367getGreenimpl(m361convertvNxB06k);
        float m367getGreenimpl2 = Color.m367getGreenimpl(j2);
        if (f4 == 0.0f) {
            f2 = 0.0f;
        } else {
            f2 = (((m367getGreenimpl2 * m364getAlphaimpl) * f3) + (m367getGreenimpl * m364getAlphaimpl2)) / f4;
        }
        float m365getBlueimpl = Color.m365getBlueimpl(m361convertvNxB06k);
        float m365getBlueimpl2 = Color.m365getBlueimpl(j2);
        if (f4 != 0.0f) {
            f5 = (((m365getBlueimpl2 * m364getAlphaimpl) * f3) + (m365getBlueimpl * m364getAlphaimpl2)) / f4;
        }
        return UncheckedColor(f, f2, f5, f4, Color.m366getColorSpaceimpl(j2));
    }

    /* renamed from: lerp-jxsXWHM, reason: not valid java name */
    public static final long m372lerpjxsXWHM(float f, long j, long j2) {
        Oklab oklab = ColorSpaces.Oklab;
        long m361convertvNxB06k = Color.m361convertvNxB06k(j, oklab);
        long m361convertvNxB06k2 = Color.m361convertvNxB06k(j2, oklab);
        float m364getAlphaimpl = Color.m364getAlphaimpl(m361convertvNxB06k);
        float m368getRedimpl = Color.m368getRedimpl(m361convertvNxB06k);
        float m367getGreenimpl = Color.m367getGreenimpl(m361convertvNxB06k);
        float m365getBlueimpl = Color.m365getBlueimpl(m361convertvNxB06k);
        float m364getAlphaimpl2 = Color.m364getAlphaimpl(m361convertvNxB06k2);
        float m368getRedimpl2 = Color.m368getRedimpl(m361convertvNxB06k2);
        float m367getGreenimpl2 = Color.m367getGreenimpl(m361convertvNxB06k2);
        float m365getBlueimpl2 = Color.m365getBlueimpl(m361convertvNxB06k2);
        if (f < 0.0f) {
            f = 0.0f;
        }
        if (f > 1.0f) {
            f = 1.0f;
        }
        return Color.m361convertvNxB06k(UncheckedColor(MathHelpersKt.lerp(m368getRedimpl, m368getRedimpl2, f), MathHelpersKt.lerp(m367getGreenimpl, m367getGreenimpl2, f), MathHelpersKt.lerp(m365getBlueimpl, m365getBlueimpl2, f), MathHelpersKt.lerp(m364getAlphaimpl, m364getAlphaimpl2, f), oklab), Color.m366getColorSpaceimpl(j2));
    }

    /* renamed from: toArgb-8_81llA, reason: not valid java name */
    public static final int m373toArgb8_81llA(long j) {
        ColorSpaces colorSpaces = ColorSpaces.INSTANCE;
        return (int) (Color.m361convertvNxB06k(j, ColorSpaces.Srgb) >>> 32);
    }

    public static final long Color(int i) {
        long j = i << 32;
        int i2 = Color.$r8$clinit;
        return j;
    }

    public static final long Color(long j) {
        long j2 = j << 32;
        int i = Color.$r8$clinit;
        return j2;
    }
}

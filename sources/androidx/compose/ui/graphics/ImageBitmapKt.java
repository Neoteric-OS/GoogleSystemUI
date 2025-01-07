package androidx.compose.ui.graphics;

import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import android.util.DisplayMetrics;
import androidx.compose.ui.graphics.colorspace.ColorSpaces;
import androidx.compose.ui.graphics.colorspace.Rgb;
import androidx.compose.ui.graphics.colorspace.TransferParameters;
import java.util.function.DoubleUnaryOperator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ImageBitmapKt {
    /* renamed from: ImageBitmap-x__-hDU$default, reason: not valid java name */
    public static AndroidImageBitmap m378ImageBitmapx__hDU$default(int i, int i2, int i3) {
        Bitmap.Config config;
        ColorSpace colorSpace;
        float[] fArr;
        ColorSpace.Rgb rgb;
        ColorSpace colorSpace2;
        Rgb rgb2 = ColorSpaces.Srgb;
        AndroidImageBitmap_androidKt.m342toBitmapConfig1JJdX4A(i3);
        Bitmap.Config m342toBitmapConfig1JJdX4A = AndroidImageBitmap_androidKt.m342toBitmapConfig1JJdX4A(i3);
        if (Intrinsics.areEqual(rgb2, rgb2)) {
            colorSpace = ColorSpace.get(ColorSpace.Named.SRGB);
        } else if (Intrinsics.areEqual(rgb2, ColorSpaces.Aces)) {
            colorSpace = ColorSpace.get(ColorSpace.Named.ACES);
        } else if (Intrinsics.areEqual(rgb2, ColorSpaces.Acescg)) {
            colorSpace = ColorSpace.get(ColorSpace.Named.ACESCG);
        } else if (Intrinsics.areEqual(rgb2, ColorSpaces.AdobeRgb)) {
            colorSpace = ColorSpace.get(ColorSpace.Named.ADOBE_RGB);
        } else if (Intrinsics.areEqual(rgb2, ColorSpaces.Bt2020)) {
            colorSpace = ColorSpace.get(ColorSpace.Named.BT2020);
        } else if (Intrinsics.areEqual(rgb2, ColorSpaces.Bt709)) {
            colorSpace = ColorSpace.get(ColorSpace.Named.BT709);
        } else if (Intrinsics.areEqual(rgb2, ColorSpaces.CieLab)) {
            colorSpace = ColorSpace.get(ColorSpace.Named.CIE_LAB);
        } else if (Intrinsics.areEqual(rgb2, ColorSpaces.CieXyz)) {
            colorSpace = ColorSpace.get(ColorSpace.Named.CIE_XYZ);
        } else if (Intrinsics.areEqual(rgb2, ColorSpaces.DciP3)) {
            colorSpace = ColorSpace.get(ColorSpace.Named.DCI_P3);
        } else if (Intrinsics.areEqual(rgb2, ColorSpaces.DisplayP3)) {
            colorSpace = ColorSpace.get(ColorSpace.Named.DISPLAY_P3);
        } else if (Intrinsics.areEqual(rgb2, ColorSpaces.ExtendedSrgb)) {
            colorSpace = ColorSpace.get(ColorSpace.Named.EXTENDED_SRGB);
        } else if (Intrinsics.areEqual(rgb2, ColorSpaces.LinearExtendedSrgb)) {
            colorSpace = ColorSpace.get(ColorSpace.Named.LINEAR_EXTENDED_SRGB);
        } else if (Intrinsics.areEqual(rgb2, ColorSpaces.LinearSrgb)) {
            colorSpace = ColorSpace.get(ColorSpace.Named.LINEAR_SRGB);
        } else if (Intrinsics.areEqual(rgb2, ColorSpaces.Ntsc1953)) {
            colorSpace = ColorSpace.get(ColorSpace.Named.NTSC_1953);
        } else if (Intrinsics.areEqual(rgb2, ColorSpaces.ProPhotoRgb)) {
            colorSpace = ColorSpace.get(ColorSpace.Named.PRO_PHOTO_RGB);
        } else {
            if (!Intrinsics.areEqual(rgb2, ColorSpaces.SmpteC)) {
                ColorSpace.Rgb.TransferParameters transferParameters = null;
                ColorSpace colorSpace3 = Intrinsics.areEqual(rgb2, ColorSpaces.Bt2020Hlg) ? ColorSpace.get(ColorSpace.Named.BT2020_HLG) : Intrinsics.areEqual(rgb2, ColorSpaces.Bt2020Pq) ? ColorSpace.get(ColorSpace.Named.BT2020_PQ) : null;
                if (colorSpace3 != null) {
                    colorSpace2 = colorSpace3;
                    config = m342toBitmapConfig1JJdX4A;
                    return new AndroidImageBitmap(Bitmap.createBitmap((DisplayMetrics) null, i, i2, config, true, colorSpace2));
                }
                if (rgb2 != null) {
                    float[] xyz$ui_graphics_release = rgb2.whitePoint.toXyz$ui_graphics_release();
                    TransferParameters transferParameters2 = rgb2.transferParameters;
                    if (transferParameters2 != null) {
                        config = m342toBitmapConfig1JJdX4A;
                        fArr = xyz$ui_graphics_release;
                        transferParameters = new ColorSpace.Rgb.TransferParameters(transferParameters2.a, transferParameters2.b, transferParameters2.c, transferParameters2.d, transferParameters2.e, transferParameters2.f, transferParameters2.gamma);
                    } else {
                        config = m342toBitmapConfig1JJdX4A;
                        fArr = xyz$ui_graphics_release;
                    }
                    if (transferParameters != null) {
                        rgb = new ColorSpace.Rgb(rgb2.name, rgb2.primaries, fArr, transferParameters);
                    } else {
                        String str = rgb2.name;
                        final Function1 function1 = rgb2.oetf;
                        final int i4 = 0;
                        DoubleUnaryOperator doubleUnaryOperator = new DoubleUnaryOperator() { // from class: androidx.compose.ui.graphics.ColorSpaceVerificationHelper$$ExternalSyntheticLambda0
                            @Override // java.util.function.DoubleUnaryOperator
                            public final double applyAsDouble(double d) {
                                int i5 = i4;
                                Function1 function12 = function1;
                                switch (i5) {
                                }
                                return ((Number) function12.invoke(Double.valueOf(d))).doubleValue();
                            }
                        };
                        final Function1 function12 = rgb2.eotf;
                        final int i5 = 1;
                        rgb = new ColorSpace.Rgb(str, rgb2.primaries, fArr, doubleUnaryOperator, new DoubleUnaryOperator() { // from class: androidx.compose.ui.graphics.ColorSpaceVerificationHelper$$ExternalSyntheticLambda0
                            @Override // java.util.function.DoubleUnaryOperator
                            public final double applyAsDouble(double d) {
                                int i52 = i5;
                                Function1 function122 = function12;
                                switch (i52) {
                                }
                                return ((Number) function122.invoke(Double.valueOf(d))).doubleValue();
                            }
                        }, rgb2.min, rgb2.max);
                    }
                    colorSpace = rgb;
                } else {
                    config = m342toBitmapConfig1JJdX4A;
                    colorSpace = ColorSpace.get(ColorSpace.Named.SRGB);
                }
                colorSpace2 = colorSpace;
                return new AndroidImageBitmap(Bitmap.createBitmap((DisplayMetrics) null, i, i2, config, true, colorSpace2));
            }
            colorSpace = ColorSpace.get(ColorSpace.Named.SMPTE_C);
        }
        config = m342toBitmapConfig1JJdX4A;
        colorSpace2 = colorSpace;
        return new AndroidImageBitmap(Bitmap.createBitmap((DisplayMetrics) null, i, i2, config, true, colorSpace2));
    }
}

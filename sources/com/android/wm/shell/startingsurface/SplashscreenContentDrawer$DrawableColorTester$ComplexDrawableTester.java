package com.android.wm.shell.startingsurface;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Trace;
import com.android.internal.graphics.palette.Palette;
import com.android.internal.graphics.palette.Quantizer;
import com.android.internal.graphics.palette.VariationalKMeansQuantizer;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import java.util.List;
import java.util.function.IntPredicate;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SplashscreenContentDrawer$DrawableColorTester$ComplexDrawableTester implements SplashscreenContentDrawer$DrawableColorTester$ColorTester {
    public static final AlphaFilterQuantizer ALPHA_FILTER_QUANTIZER = new AlphaFilterQuantizer();
    public final boolean mFilterTransparent;
    public final Palette mPalette;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class AlphaFilterQuantizer implements Quantizer {
        public IntPredicate mFilter;
        public final Quantizer mInnerQuantizer = new VariationalKMeansQuantizer();
        public float mPassFilterRatio;
        public final SplashscreenContentDrawer$DrawableColorTester$ComplexDrawableTester$AlphaFilterQuantizer$$ExternalSyntheticLambda0 mTranslucentFilter;
        public final SplashscreenContentDrawer$DrawableColorTester$ComplexDrawableTester$AlphaFilterQuantizer$$ExternalSyntheticLambda0 mTransparentFilter;

        /* JADX WARN: Type inference failed for: r0v1, types: [com.android.wm.shell.startingsurface.SplashscreenContentDrawer$DrawableColorTester$ComplexDrawableTester$AlphaFilterQuantizer$$ExternalSyntheticLambda0, java.util.function.IntPredicate] */
        /* JADX WARN: Type inference failed for: r1v1, types: [com.android.wm.shell.startingsurface.SplashscreenContentDrawer$DrawableColorTester$ComplexDrawableTester$AlphaFilterQuantizer$$ExternalSyntheticLambda0] */
        public AlphaFilterQuantizer() {
            final int i = 0;
            ?? r0 = new IntPredicate() { // from class: com.android.wm.shell.startingsurface.SplashscreenContentDrawer$DrawableColorTester$ComplexDrawableTester$AlphaFilterQuantizer$$ExternalSyntheticLambda0
                @Override // java.util.function.IntPredicate
                public final boolean test(int i2) {
                    switch (i) {
                        case 0:
                            if (((-16777216) & i2) != 0) {
                            }
                            break;
                        default:
                            if ((i2 & DarkIconDispatcher.DEFAULT_INVERSE_ICON_TINT) == -16777216) {
                            }
                            break;
                    }
                    return false;
                }
            };
            this.mTransparentFilter = r0;
            final int i2 = 1;
            this.mTranslucentFilter = new IntPredicate() { // from class: com.android.wm.shell.startingsurface.SplashscreenContentDrawer$DrawableColorTester$ComplexDrawableTester$AlphaFilterQuantizer$$ExternalSyntheticLambda0
                @Override // java.util.function.IntPredicate
                public final boolean test(int i22) {
                    switch (i2) {
                        case 0:
                            if (((-16777216) & i22) != 0) {
                            }
                            break;
                        default:
                            if ((i22 & DarkIconDispatcher.DEFAULT_INVERSE_ICON_TINT) == -16777216) {
                            }
                            break;
                    }
                    return false;
                }
            };
            this.mFilter = r0;
        }

        public final List getQuantizedColors() {
            return this.mInnerQuantizer.getQuantizedColors();
        }

        public final void quantize(int[] iArr, int i) {
            this.mPassFilterRatio = 0.0f;
            int i2 = 0;
            int i3 = 0;
            for (int length = iArr.length - 1; length > 0; length--) {
                if (this.mFilter.test(iArr[length])) {
                    i3++;
                }
            }
            if (i3 == 0) {
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_STARTING_WINDOW_enabled[1]) {
                    ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_STARTING_WINDOW, 2086008807179892616L, 0, null);
                }
                this.mInnerQuantizer.quantize(iArr, i);
                return;
            }
            this.mPassFilterRatio = i3 / iArr.length;
            int[] iArr2 = new int[i3];
            for (int length2 = iArr.length - 1; length2 > 0; length2--) {
                if (this.mFilter.test(iArr[length2])) {
                    iArr2[i2] = iArr[length2];
                    i2++;
                }
            }
            this.mInnerQuantizer.quantize(iArr2, i);
        }
    }

    public SplashscreenContentDrawer$DrawableColorTester$ComplexDrawableTester(int i, Drawable drawable) {
        int i2;
        Palette.Builder maximumColorCount;
        Trace.traceBegin(32L, "ComplexDrawableTester");
        Rect copyBounds = drawable.copyBounds();
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        int i3 = 40;
        if (intrinsicWidth <= 0 || intrinsicHeight <= 0) {
            i2 = 40;
        } else {
            i3 = Math.min(intrinsicWidth, 40);
            i2 = Math.min(intrinsicHeight, 40);
        }
        Bitmap createBitmap = Bitmap.createBitmap(i3, i2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, createBitmap.getWidth(), createBitmap.getHeight());
        drawable.draw(canvas);
        drawable.setBounds(copyBounds);
        boolean z = i != 0;
        this.mFilterTransparent = z;
        if (z) {
            AlphaFilterQuantizer alphaFilterQuantizer = ALPHA_FILTER_QUANTIZER;
            if (i != 2) {
                alphaFilterQuantizer.mFilter = alphaFilterQuantizer.mTransparentFilter;
            } else {
                alphaFilterQuantizer.mFilter = alphaFilterQuantizer.mTranslucentFilter;
            }
            maximumColorCount = new Palette.Builder(createBitmap, alphaFilterQuantizer).maximumColorCount(5);
        } else {
            maximumColorCount = new Palette.Builder(createBitmap, (Quantizer) null).maximumColorCount(5);
        }
        this.mPalette = maximumColorCount.generate();
        createBitmap.recycle();
        Trace.traceEnd(32L);
    }

    @Override // com.android.wm.shell.startingsurface.SplashscreenContentDrawer$DrawableColorTester$ColorTester
    public final int getDominantColor() {
        Palette.Swatch dominantSwatch = this.mPalette.getDominantSwatch();
        return dominantSwatch != null ? dominantSwatch.getInt() : DarkIconDispatcher.DEFAULT_INVERSE_ICON_TINT;
    }

    @Override // com.android.wm.shell.startingsurface.SplashscreenContentDrawer$DrawableColorTester$ColorTester
    public final boolean isComplexColor() {
        return this.mPalette.getSwatches().size() > 1;
    }

    @Override // com.android.wm.shell.startingsurface.SplashscreenContentDrawer$DrawableColorTester$ColorTester
    public final boolean isGrayscale() {
        List swatches = this.mPalette.getSwatches();
        if (swatches != null) {
            for (int size = swatches.size() - 1; size >= 0; size--) {
                int i = ((Palette.Swatch) swatches.get(size)).getInt();
                int red = Color.red(i);
                int green = Color.green(i);
                int blue = Color.blue(i);
                if (red != green || green != blue) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override // com.android.wm.shell.startingsurface.SplashscreenContentDrawer$DrawableColorTester$ColorTester
    public final float passFilterRatio() {
        if (this.mFilterTransparent) {
            return ALPHA_FILTER_QUANTIZER.mPassFilterRatio;
        }
        return 1.0f;
    }
}

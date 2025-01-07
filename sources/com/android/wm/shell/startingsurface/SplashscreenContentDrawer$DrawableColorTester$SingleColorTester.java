package com.android.wm.shell.startingsurface;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SplashscreenContentDrawer$DrawableColorTester$SingleColorTester implements SplashscreenContentDrawer$DrawableColorTester$ColorTester {
    public final ColorDrawable mColorDrawable;

    public SplashscreenContentDrawer$DrawableColorTester$SingleColorTester(ColorDrawable colorDrawable) {
        this.mColorDrawable = colorDrawable;
    }

    @Override // com.android.wm.shell.startingsurface.SplashscreenContentDrawer$DrawableColorTester$ColorTester
    public final int getDominantColor() {
        return this.mColorDrawable.getColor();
    }

    @Override // com.android.wm.shell.startingsurface.SplashscreenContentDrawer$DrawableColorTester$ColorTester
    public final boolean isComplexColor() {
        return false;
    }

    @Override // com.android.wm.shell.startingsurface.SplashscreenContentDrawer$DrawableColorTester$ColorTester
    public final boolean isGrayscale() {
        int color = this.mColorDrawable.getColor();
        int red = Color.red(color);
        int green = Color.green(color);
        return red == green && green == Color.blue(color);
    }

    @Override // com.android.wm.shell.startingsurface.SplashscreenContentDrawer$DrawableColorTester$ColorTester
    public final float passFilterRatio() {
        return this.mColorDrawable.getAlpha() / 255.0f;
    }
}

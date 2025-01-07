package com.android.wm.shell.back;

import android.graphics.Color;
import android.graphics.Rect;
import android.view.SurfaceControl;
import com.android.internal.graphics.ColorUtils;
import com.android.internal.view.AppearanceRegion;
import com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler;
import com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler$$ExternalSyntheticLambda6;
import com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler$$ExternalSyntheticLambda7;
import com.android.wm.shell.RootTaskDisplayAreaOrganizer;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BackAnimationBackground {
    public boolean mBackgroundIsDark;
    public SurfaceControl mBackgroundSurface;
    public EdgeBackGestureHandler$$ExternalSyntheticLambda7 mCustomizer;
    public boolean mIsRequestingStatusBarAppearance;
    public final RootTaskDisplayAreaOrganizer mRootTaskDisplayAreaOrganizer;
    public Rect mStartBounds;
    public int mStatusbarHeight;

    public BackAnimationBackground(RootTaskDisplayAreaOrganizer rootTaskDisplayAreaOrganizer) {
        this.mRootTaskDisplayAreaOrganizer = rootTaskDisplayAreaOrganizer;
    }

    public final void customizeStatusBarAppearance(int i) {
        if (this.mCustomizer == null || this.mStartBounds.isEmpty()) {
            return;
        }
        boolean z = i > this.mStatusbarHeight / 2;
        if (z == this.mIsRequestingStatusBarAppearance) {
            return;
        }
        this.mIsRequestingStatusBarAppearance = z;
        if (!z) {
            EdgeBackGestureHandler$$ExternalSyntheticLambda7 edgeBackGestureHandler$$ExternalSyntheticLambda7 = this.mCustomizer;
            Executor executor = edgeBackGestureHandler$$ExternalSyntheticLambda7.f$1;
            EdgeBackGestureHandler edgeBackGestureHandler = edgeBackGestureHandler$$ExternalSyntheticLambda7.f$0;
            edgeBackGestureHandler.getClass();
            executor.execute(new EdgeBackGestureHandler$$ExternalSyntheticLambda6(edgeBackGestureHandler, null, 1));
            return;
        }
        AppearanceRegion appearanceRegion = new AppearanceRegion(this.mBackgroundIsDark ? 0 : 8, this.mStartBounds);
        EdgeBackGestureHandler$$ExternalSyntheticLambda7 edgeBackGestureHandler$$ExternalSyntheticLambda72 = this.mCustomizer;
        Executor executor2 = edgeBackGestureHandler$$ExternalSyntheticLambda72.f$1;
        EdgeBackGestureHandler edgeBackGestureHandler2 = edgeBackGestureHandler$$ExternalSyntheticLambda72.f$0;
        edgeBackGestureHandler2.getClass();
        executor2.execute(new EdgeBackGestureHandler$$ExternalSyntheticLambda6(edgeBackGestureHandler2, appearanceRegion, 1));
    }

    public final void ensureBackground(Rect rect, int i, SurfaceControl.Transaction transaction, int i2, Rect rect2, float f) {
        if (this.mBackgroundSurface != null) {
            return;
        }
        this.mBackgroundIsDark = ColorUtils.calculateLuminance(i) < 0.5d;
        float[] fArr = {Color.red(i) / 255.0f, Color.green(i) / 255.0f, Color.blue(i) / 255.0f};
        SurfaceControl.Builder colorLayer = new SurfaceControl.Builder().setName("back-animation-background").setCallsite("BackAnimationBackground").setColorLayer();
        this.mRootTaskDisplayAreaOrganizer.attachToDisplayArea(0, colorLayer);
        SurfaceControl build = colorLayer.build();
        this.mBackgroundSurface = build;
        transaction.setColor(build, fArr).setLayer(this.mBackgroundSurface, -1).show(this.mBackgroundSurface);
        if (rect2 != null && !rect2.isEmpty()) {
            transaction.setCrop(this.mBackgroundSurface, rect2).setCornerRadius(this.mBackgroundSurface, f);
        }
        this.mStartBounds = rect;
        this.mIsRequestingStatusBarAppearance = false;
        this.mStatusbarHeight = i2;
    }
}

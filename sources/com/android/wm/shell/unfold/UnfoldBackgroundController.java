package com.android.wm.shell.unfold;

import android.content.Context;
import android.graphics.Color;
import android.view.SurfaceControl;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UnfoldBackgroundController {
    public final float[] mBackgroundColor;
    public float[] mBackgroundColorSet;
    public SurfaceControl mBackgroundLayer;
    public final float[] mSplitScreenBackgroundColor;
    public boolean mSplitScreenVisible = false;

    public UnfoldBackgroundController(Context context) {
        this.mBackgroundColor = getRGBColorFromId(R.color.unfold_background, context);
        this.mSplitScreenBackgroundColor = getRGBColorFromId(R.color.split_divider_background, context);
    }

    public static float[] getRGBColorFromId(int i, Context context) {
        int color = context.getResources().getColor(i);
        return new float[]{Color.red(color) / 255.0f, Color.green(color) / 255.0f, Color.blue(color) / 255.0f};
    }

    public final void ensureBackground(SurfaceControl.Transaction transaction) {
        float[] fArr = this.mSplitScreenVisible ? this.mSplitScreenBackgroundColor : this.mBackgroundColor;
        SurfaceControl surfaceControl = this.mBackgroundLayer;
        if (surfaceControl != null) {
            if (this.mBackgroundColorSet != fArr) {
                transaction.setColor(surfaceControl, fArr);
                this.mBackgroundColorSet = fArr;
                return;
            }
            return;
        }
        SurfaceControl build = new SurfaceControl.Builder().setName("app-unfold-background").setCallsite("AppUnfoldTransitionController").setColorLayer().build();
        this.mBackgroundLayer = build;
        transaction.setColor(build, fArr).show(this.mBackgroundLayer).setLayer(this.mBackgroundLayer, -1);
        this.mBackgroundColorSet = fArr;
    }
}

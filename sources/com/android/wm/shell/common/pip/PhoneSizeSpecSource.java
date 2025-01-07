package com.android.wm.shell.common.pip;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.Size;
import com.android.wm.shell.R;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PhoneSizeSpecSource {
    public final Context context;
    public int mDefaultMinSize;
    public float mOptimizedAspectRatio;
    public int mOverridableMinSize;
    public Size mOverrideMinSize;
    public final PipDisplayLayoutState pipDisplayLayoutState;
    public float mSystemPreferredDefaultSizePercent = 0.6f;
    public float mSystemPreferredMinimumSizePercent = 0.5f;
    public float mSquareDisplayThresholdForSystemPreferredSize = 0.95f;
    public float mSystemPreferredDefaultSizePercentForSquareDisplay = 0.5f;
    public float mSystemPreferredMinimumSizePercentForSquareDisplay = 0.4f;

    public PhoneSizeSpecSource(Context context, PipDisplayLayoutState pipDisplayLayoutState) {
        this.context = context;
        this.pipDisplayLayoutState = pipDisplayLayoutState;
        reloadResources();
    }

    public final Size adjustOverrideMinSizeToAspectRatio(float f) {
        Size overrideMinSize = getOverrideMinSize();
        if (overrideMinSize == null) {
            return null;
        }
        return ((float) overrideMinSize.getWidth()) / ((float) overrideMinSize.getHeight()) > f ? new Size(overrideMinSize.getWidth(), (int) (overrideMinSize.getWidth() / f)) : new Size((int) (overrideMinSize.getHeight() * f), overrideMinSize.getHeight());
    }

    public final Size getDefaultSize(float f) {
        Size minSize = getMinSize(f);
        if (this.mOverrideMinSize != null) {
            return minSize;
        }
        int max = Math.max(Math.round((getMIsSquareDisplay() ? this.mSystemPreferredDefaultSizePercentForSquareDisplay : this.mSystemPreferredDefaultSizePercent) * getMaxSize(f).getWidth()), minSize.getWidth());
        return new Size(max, Math.round(max / f));
    }

    public final boolean getMIsSquareDisplay() {
        PipDisplayLayoutState pipDisplayLayoutState = this.pipDisplayLayoutState;
        return ((float) Math.min(pipDisplayLayoutState.getDisplayLayout().mWidth, pipDisplayLayoutState.getDisplayLayout().mHeight)) / ((float) Math.max(pipDisplayLayoutState.getDisplayLayout().mWidth, pipDisplayLayoutState.getDisplayLayout().mHeight)) > this.mSquareDisplayThresholdForSystemPreferredSize;
    }

    public final Size getMaxSize(float f) {
        int i;
        PipDisplayLayoutState pipDisplayLayoutState = this.pipDisplayLayoutState;
        Rect insetBounds = pipDisplayLayoutState.getInsetBounds();
        Rect displayBounds = pipDisplayLayoutState.getDisplayBounds();
        int min = Math.min(displayBounds.width() - ((displayBounds.width() - insetBounds.right) + insetBounds.left), displayBounds.height() - ((displayBounds.height() - insetBounds.bottom) + insetBounds.top));
        float f2 = this.mOptimizedAspectRatio;
        if (f >= f2) {
            float f3 = 1;
            if (f <= f3 / f2) {
                float f4 = min;
                min = Math.min(Math.round((((f - f2) * f4) / (f3 + f)) + (f2 * f4)), min);
                i = Math.round(min / f);
                return new Size(min, i);
            }
        }
        if (f > 1.0f) {
            i = Math.round(min / f);
        } else {
            min = Math.round(min * f);
            i = min;
        }
        return new Size(min, i);
    }

    public final Size getMinSize(float f) {
        int i;
        int i2;
        if (this.mOverrideMinSize != null) {
            Size adjustOverrideMinSizeToAspectRatio = adjustOverrideMinSizeToAspectRatio(f);
            Intrinsics.checkNotNull(adjustOverrideMinSizeToAspectRatio);
            return adjustOverrideMinSizeToAspectRatio;
        }
        Size maxSize = getMaxSize(f);
        int round = Math.round((getMIsSquareDisplay() ? this.mSystemPreferredMinimumSizePercentForSquareDisplay : this.mSystemPreferredMinimumSizePercent) * maxSize.getWidth());
        int round2 = Math.round((getMIsSquareDisplay() ? this.mSystemPreferredMinimumSizePercentForSquareDisplay : this.mSystemPreferredMinimumSizePercent) * maxSize.getHeight());
        if (f > 1.0f) {
            i2 = Math.max(round2, this.mDefaultMinSize);
            i = Math.round(i2 * f);
        } else {
            int max = Math.max(round, this.mDefaultMinSize);
            int round3 = Math.round(max / f);
            i = max;
            i2 = round3;
        }
        return new Size(i, i2);
    }

    public final int getOverrideMinEdgeSize() {
        Size overrideMinSize = getOverrideMinSize();
        if (overrideMinSize == null) {
            return 0;
        }
        return Math.min(overrideMinSize.getWidth(), overrideMinSize.getHeight());
    }

    public final Size getOverrideMinSize() {
        Size size = this.mOverrideMinSize;
        if (size == null) {
            return null;
        }
        if (size.getWidth() >= this.mOverridableMinSize && size.getHeight() >= this.mOverridableMinSize) {
            return size;
        }
        int i = this.mOverridableMinSize;
        return new Size(i, i);
    }

    public final void reloadResources() {
        Resources resources = this.context.getResources();
        this.mDefaultMinSize = resources.getDimensionPixelSize(R.dimen.default_minimal_size_pip_resizable_task);
        this.mOverridableMinSize = resources.getDimensionPixelSize(R.dimen.overridable_minimal_size_pip_resizable_task);
        this.mSystemPreferredDefaultSizePercent = resources.getFloat(R.dimen.config_pipSystemPreferredDefaultSizePercent);
        this.mSystemPreferredMinimumSizePercent = resources.getFloat(R.dimen.config_pipSystemPreferredMinimumSizePercent);
        this.mSquareDisplayThresholdForSystemPreferredSize = resources.getFloat(R.dimen.config_pipSquareDisplayThresholdForSystemPreferredSize);
        this.mSystemPreferredDefaultSizePercentForSquareDisplay = resources.getFloat(R.dimen.config_pipSystemPreferredDefaultSizePercentForSquareDisplay);
        this.mSystemPreferredMinimumSizePercentForSquareDisplay = resources.getFloat(R.dimen.config_pipSystemPreferredMinimumSizePercentForSquareDisplay);
        float f = resources.getFloat(R.dimen.config_pipLargeScreenOptimizedAspectRatio);
        if (f > 1.0f) {
            f = 0.5625f;
        }
        this.mOptimizedAspectRatio = f;
    }
}

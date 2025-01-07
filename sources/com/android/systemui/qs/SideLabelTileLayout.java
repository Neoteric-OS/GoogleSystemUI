package com.android.systemui.qs;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.ViewGroup;
import com.android.systemui.flags.Flags;
import com.android.systemui.flags.RefactorFlag;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class SideLabelTileLayout extends TileLayout {
    public final boolean isSmallLandscapeLockscreenEnabled;

    public SideLabelTileLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isSmallLandscapeLockscreenEnabled = ((Boolean) RefactorFlag.Companion.forView$default(Flags.LOCKSCREEN_ENABLE_LANDSCAPE).isEnabled$delegate.getValue()).booleanValue();
    }

    @Override // com.android.systemui.qs.TileLayout
    public final boolean updateMaxRows(int i) {
        int i2 = this.mRows;
        int i3 = this.mMaxAllowedRows;
        this.mRows = i3;
        int i4 = this.mColumns;
        int i5 = ((i + i4) - 1) / i4;
        if (i3 > i5) {
            this.mRows = i5;
        }
        return i2 != this.mRows;
    }

    public boolean updateResources() {
        Resources resources = getResources();
        boolean z = true;
        this.mResourceColumns = Math.max(1, (this.mIsSmallLandscapeLockscreenEnabled.booleanValue() && ((ViewGroup) this).mContext.getResources().getBoolean(R.bool.is_small_screen_landscape)) ? resources.getInteger(R.integer.small_land_lockscreen_quick_settings_num_columns) : resources.getInteger(R.integer.quick_settings_num_columns));
        this.mResourceCellHeight = resources.getDimensionPixelSize(this.mResourceCellHeightResId);
        this.mCellMarginHorizontal = resources.getDimensionPixelSize(R.dimen.qs_tile_margin_horizontal);
        this.mCellMarginVertical = resources.getDimensionPixelSize(R.dimen.qs_tile_margin_vertical);
        int max = Math.max(1, this.mIsSmallLandscapeLockscreenEnabled.booleanValue() && ((ViewGroup) this).mContext.getResources().getBoolean(R.bool.is_small_screen_landscape) ? resources.getInteger(R.integer.small_land_lockscreen_quick_settings_max_rows) : resources.getInteger(R.integer.quick_settings_max_rows));
        this.mMaxAllowedRows = max;
        if (this.mLessRows) {
            this.mMaxAllowedRows = Math.max(this.mMinRows, max - 1);
        }
        this.mTempTextView.dispatchConfigurationChanged(((ViewGroup) this).mContext.getResources().getConfiguration());
        estimateCellHeight();
        if (updateColumns()) {
            requestLayout();
        } else {
            z = false;
        }
        this.mMaxAllowedRows = (this.isSmallLandscapeLockscreenEnabled && ((ViewGroup) this).mContext.getResources().getBoolean(R.bool.is_small_screen_landscape)) ? getContext().getResources().getInteger(R.integer.small_land_lockscreen_quick_settings_max_rows) : getContext().getResources().getInteger(R.integer.quick_settings_max_rows);
        return z;
    }
}

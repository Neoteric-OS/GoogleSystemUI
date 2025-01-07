package com.android.systemui.qs;

import android.content.Context;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.TextView;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.FontSizeUtils;
import com.android.systemui.flags.Flags;
import com.android.systemui.flags.RefactorFlag;
import com.android.systemui.plugins.qs.QSTileView;
import com.android.systemui.qs.QSPanel;
import com.android.systemui.qs.QSPanelControllerBase;
import com.android.systemui.qs.tileimpl.QSTileViewImpl;
import com.android.systemui.util.Utils;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class TileLayout extends ViewGroup implements QSPanel.QSTileLayout {
    public int mCellHeight;
    public int mCellMarginHorizontal;
    public int mCellMarginVertical;
    public int mCellWidth;
    public int mColumns;
    public int mEstimatedCellHeight;
    public final Boolean mIsSmallLandscapeLockscreenEnabled;
    public int mLastTileBottom;
    public final boolean mLessRows;
    public boolean mListening;
    public int mMaxAllowedRows;
    public int mMaxColumns;
    public int mMinRows;
    public final ArrayList mRecords;
    public int mResourceCellHeight;
    public int mResourceCellHeightResId;
    public int mResourceColumns;
    public int mRows;
    public float mSquishinessFraction;
    public final TextView mTempTextView;

    public TileLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mResourceCellHeightResId = R.dimen.qs_tile_height;
        boolean z = true;
        this.mRows = 1;
        this.mRecords = new ArrayList();
        this.mMaxAllowedRows = 3;
        this.mMinRows = 1;
        this.mMaxColumns = 100;
        this.mSquishinessFraction = 1.0f;
        Boolean bool = (Boolean) RefactorFlag.Companion.forView$default(Flags.LOCKSCREEN_ENABLE_LANDSCAPE).isEnabled$delegate.getValue();
        bool.booleanValue();
        this.mIsSmallLandscapeLockscreenEnabled = bool;
        if (Settings.System.getInt(context.getContentResolver(), "qs_less_rows", 0) == 0 && !Utils.useQsMediaPlayer(context)) {
            z = false;
        }
        this.mLessRows = z;
        this.mTempTextView = new TextView(context);
        updateResources();
    }

    @Override // com.android.systemui.qs.QSPanel.QSTileLayout
    public final void addTile(QSPanelControllerBase.TileRecord tileRecord) {
        this.mRecords.add(tileRecord);
        tileRecord.tile.setListening(this, this.mListening);
        QSTileViewImpl qSTileViewImpl = tileRecord.tileView;
        float f = this.mSquishinessFraction;
        if (qSTileViewImpl.squishinessFraction != f) {
            qSTileViewImpl.squishinessFraction = f;
            qSTileViewImpl.updateHeight();
        }
        addView(qSTileViewImpl);
    }

    public void estimateCellHeight() {
        FontSizeUtils.updateFontSize(this.mTempTextView, R.dimen.qs_tile_text_size);
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        this.mTempTextView.measure(makeMeasureSpec, makeMeasureSpec);
        this.mEstimatedCellHeight = (((ViewGroup) this).mContext.getResources().getDimensionPixelSize(R.dimen.qs_tile_padding) * 2) + (this.mTempTextView.getMeasuredHeight() * 2);
    }

    public int getMaxColumns() {
        return this.mMaxColumns;
    }

    public int getMinRows() {
        return this.mMinRows;
    }

    @Override // com.android.systemui.qs.QSPanel.QSTileLayout
    public final int getNumVisibleTiles() {
        return this.mRecords.size();
    }

    @Override // com.android.systemui.qs.QSPanel.QSTileLayout
    public final int getTilesHeight() {
        return getPaddingBottom() + this.mLastTileBottom;
    }

    @Override // android.view.View
    public final boolean hasOverlappingRendering() {
        return false;
    }

    public final void layoutTileRecords(int i, boolean z) {
        boolean z2 = getLayoutDirection() == 1;
        this.mLastTileBottom = 0;
        int min = Math.min(i, this.mRows * this.mColumns);
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i2 < min) {
            if (i3 == this.mColumns) {
                i4++;
                i3 = 0;
            }
            QSPanelControllerBase.TileRecord tileRecord = (QSPanelControllerBase.TileRecord) this.mRecords.get(i2);
            int i5 = (int) (((this.mCellHeight * ((this.mSquishinessFraction * 0.9f) + 0.1f)) + this.mCellMarginVertical) * i4);
            int i6 = z2 ? (this.mColumns - i3) - 1 : i3;
            int paddingStart = getPaddingStart();
            int i7 = this.mCellWidth;
            int i8 = ((this.mCellMarginHorizontal + i7) * i6) + paddingStart;
            int i9 = i7 + i8;
            int measuredHeight = tileRecord.tileView.getMeasuredHeight() + i5;
            QSTileViewImpl qSTileViewImpl = tileRecord.tileView;
            if (z) {
                qSTileViewImpl.layout(i8, i5, i9, measuredHeight);
            } else {
                qSTileViewImpl.setLeftTopRightBottom(i8, i5, i9, measuredHeight);
            }
            qSTileViewImpl.position = i2;
            this.mLastTileBottom = i5 + ((int) (qSTileViewImpl.getMeasuredHeight() * ((this.mSquishinessFraction * 0.9f) + 0.1f)));
            i2++;
            i3++;
        }
    }

    public final void onInitializeAccessibilityNodeInfoInternal(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfoInternal(accessibilityNodeInfo);
        accessibilityNodeInfo.setCollectionInfo(new AccessibilityNodeInfo.CollectionInfo(this.mRecords.size(), 1, false));
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        layoutTileRecords(this.mRecords.size(), true);
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        int size = this.mRecords.size();
        int size2 = View.MeasureSpec.getSize(i);
        int paddingStart = (size2 - getPaddingStart()) - getPaddingEnd();
        if (View.MeasureSpec.getMode(i2) == 0) {
            this.mRows = ((size + r8) - 1) / this.mColumns;
        }
        int i3 = this.mColumns;
        this.mCellWidth = (paddingStart - (this.mCellMarginHorizontal * (i3 - 1))) / i3;
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(Math.max(this.mResourceCellHeight, this.mEstimatedCellHeight), 1073741824);
        Iterator it = this.mRecords.iterator();
        View view = this;
        while (it.hasNext()) {
            QSPanelControllerBase.TileRecord tileRecord = (QSPanelControllerBase.TileRecord) it.next();
            if (tileRecord.tileView.getVisibility() != 8) {
                int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(this.mCellWidth, 1073741824);
                QSTileView qSTileView = tileRecord.tileView;
                qSTileView.measure(makeMeasureSpec2, makeMeasureSpec);
                qSTileView.updateAccessibilityOrder(view);
                this.mCellHeight = qSTileView.getMeasuredHeight();
                view = qSTileView;
            }
        }
        int i4 = this.mCellHeight;
        int i5 = this.mCellMarginVertical;
        int i6 = ((i4 + i5) * this.mRows) - i5;
        if (i6 < 0) {
            i6 = 0;
        }
        setMeasuredDimension(size2, i6);
    }

    @Override // android.view.ViewGroup
    public final void removeAllViews() {
        Iterator it = this.mRecords.iterator();
        while (it.hasNext()) {
            ((QSPanelControllerBase.TileRecord) it.next()).tile.setListening(this, false);
        }
        this.mRecords.clear();
        super.removeAllViews();
    }

    @Override // com.android.systemui.qs.QSPanel.QSTileLayout
    public final void removeTile(QSPanelControllerBase.TileRecord tileRecord) {
        this.mRecords.remove(tileRecord);
        tileRecord.tile.setListening(this, false);
        removeView(tileRecord.tileView);
    }

    public void setListening(boolean z, UiEventLogger uiEventLogger) {
        if (this.mListening == z) {
            return;
        }
        this.mListening = z;
        Iterator it = this.mRecords.iterator();
        while (it.hasNext()) {
            ((QSPanelControllerBase.TileRecord) it.next()).tile.setListening(this, this.mListening);
        }
    }

    @Override // com.android.systemui.qs.QSPanel.QSTileLayout
    public final boolean setMaxColumns(int i) {
        this.mMaxColumns = i;
        return updateColumns();
    }

    @Override // com.android.systemui.qs.QSPanel.QSTileLayout
    public final boolean setMinRows(int i) {
        if (this.mMinRows == i) {
            return false;
        }
        this.mMinRows = i;
        updateResources();
        return true;
    }

    @Override // com.android.systemui.qs.QSPanel.QSTileLayout
    public final void setSquishinessFraction(float f) {
        if (Float.compare(this.mSquishinessFraction, f) == 0) {
            return;
        }
        this.mSquishinessFraction = f;
        layoutTileRecords(this.mRecords.size(), false);
        Iterator it = this.mRecords.iterator();
        while (it.hasNext()) {
            QSTileViewImpl qSTileViewImpl = ((QSPanelControllerBase.TileRecord) it.next()).tileView;
            float f2 = this.mSquishinessFraction;
            if (qSTileViewImpl.squishinessFraction != f2) {
                qSTileViewImpl.squishinessFraction = f2;
                qSTileViewImpl.updateHeight();
            }
        }
    }

    public final boolean updateColumns() {
        int i = this.mColumns;
        int min = Math.min(this.mResourceColumns, this.mMaxColumns);
        this.mColumns = min;
        return i != min;
    }

    public abstract boolean updateMaxRows(int i);
}

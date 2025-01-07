package com.android.systemui.qs;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.LinearLayout;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.FontSizeUtils;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.qs.QSPanel;
import com.android.systemui.qs.QSPanelControllerBase;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class QuickQSPanel extends QSPanel {
    public boolean mDisabledByPolicy;
    public int mMaxTiles;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class QQSSideLabelTileLayout extends SideLabelTileLayout {
        public boolean mLastSelected;

        @Override // com.android.systemui.qs.TileLayout
        public final void estimateCellHeight() {
            FontSizeUtils.updateFontSize(this.mTempTextView, R.dimen.qs_tile_text_size);
            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
            this.mTempTextView.measure(makeMeasureSpec, makeMeasureSpec);
            this.mEstimatedCellHeight = (((ViewGroup) this).mContext.getResources().getDimensionPixelSize(R.dimen.qs_tile_padding) * 2) + this.mTempTextView.getMeasuredHeight();
        }

        @Override // android.view.View
        public final void onConfigurationChanged(Configuration configuration) {
            super.onConfigurationChanged(configuration);
            updateResources();
        }

        @Override // com.android.systemui.qs.TileLayout, android.view.View
        public final void onMeasure(int i, int i2) {
            updateMaxRows(this.mRecords.size());
            super.onMeasure(i, i2);
        }

        @Override // com.android.systemui.qs.QSPanel.QSTileLayout
        public final void setExpansion(float f, float f2) {
            if (f <= 0.0f || f >= 1.0f) {
                boolean z = f == 1.0f || f2 < 0.0f;
                if (this.mLastSelected == z) {
                    return;
                }
                setImportantForAccessibility(4);
                for (int i = 0; i < getChildCount(); i++) {
                    getChildAt(i).setSelected(z);
                }
                setImportantForAccessibility(0);
                this.mLastSelected = z;
            }
        }

        @Override // com.android.systemui.qs.TileLayout, com.android.systemui.qs.QSPanel.QSTileLayout
        public final void setListening(boolean z, UiEventLogger uiEventLogger) {
            boolean z2 = !this.mListening && z;
            super.setListening(z, uiEventLogger);
            if (z2) {
                for (int i = 0; i < this.mRecords.size(); i++) {
                    QSTile qSTile = ((QSPanelControllerBase.TileRecord) this.mRecords.get(i)).tile;
                    uiEventLogger.logWithInstanceId(QSEvent.QQS_TILE_VISIBLE, 0, qSTile.getMetricsSpec(), qSTile.getInstanceId());
                }
            }
        }

        @Override // com.android.systemui.qs.SideLabelTileLayout, com.android.systemui.qs.QSPanel.QSTileLayout
        public final boolean updateResources() {
            this.mResourceCellHeightResId = R.dimen.qs_quick_tile_size;
            boolean updateResources = super.updateResources();
            this.mMaxAllowedRows = getResources().getInteger(R.integer.quick_qs_panel_max_rows);
            return updateResources;
        }
    }

    public QuickQSPanel(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mMaxTiles = getResources().getInteger(R.integer.quick_qs_panel_max_tiles);
    }

    @Override // com.android.systemui.qs.QSPanel
    public final QSEvent closePanelEvent() {
        return QSEvent.QQS_PANEL_COLLAPSED;
    }

    @Override // com.android.systemui.qs.QSPanel
    public final String getDumpableTag() {
        return "QuickQSPanel";
    }

    @Override // com.android.systemui.qs.QSPanel
    public final QSPanel.QSTileLayout getOrCreateTileLayout() {
        QQSSideLabelTileLayout qQSSideLabelTileLayout = new QQSSideLabelTileLayout(this.mContext, null);
        qQSSideLabelTileLayout.setClipChildren(false);
        qQSSideLabelTileLayout.setClipToPadding(false);
        qQSSideLabelTileLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        qQSSideLabelTileLayout.setMaxColumns(4);
        qQSSideLabelTileLayout.setId(R.id.qqs_tile_layout);
        return qQSSideLabelTileLayout;
    }

    @Override // com.android.systemui.qs.QSPanel, android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.removeAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_COLLAPSE);
        accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_EXPAND);
    }

    @Override // com.android.systemui.qs.QSPanel, com.android.systemui.tuner.TunerService.Tunable
    public final void onTuningChanged(String str, String str2) {
        if ("qs_show_brightness".equals(str)) {
            super.onTuningChanged(str, "0");
        }
    }

    @Override // com.android.systemui.qs.QSPanel
    public final QSEvent openPanelEvent() {
        return QSEvent.QQS_PANEL_EXPANDED;
    }

    @Override // com.android.systemui.qs.QSPanel
    public final void setHorizontalContentContainerClipping() {
        this.mHorizontalContentContainer.setClipToPadding(false);
        this.mHorizontalContentContainer.setClipChildren(false);
    }

    @Override // android.view.View
    public final void setVisibility(int i) {
        if (this.mDisabledByPolicy) {
            if (getVisibility() == 8) {
                return;
            } else {
                i = 8;
            }
        }
        super.setVisibility(i);
    }

    @Override // com.android.systemui.qs.QSPanel
    public final void updatePadding() {
        setPaddingRelative(getPaddingStart(), getPaddingTop(), getPaddingEnd(), getResources().getDimensionPixelSize(R.dimen.qqs_layout_padding_bottom));
    }
}

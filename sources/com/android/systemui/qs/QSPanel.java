package com.android.systemui.qs;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.LinearLayout;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.qs.QSPanelControllerBase;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.tuner.TunerService;
import com.android.systemui.util.Utils;
import com.android.systemui.util.animation.UniqueObjectHostView;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class QSPanel extends LinearLayout implements TunerService.Tunable {
    public static final /* synthetic */ int $r8$clinit = 0;
    public View mBrightnessView;
    public boolean mCanCollapse;
    public final ArrayMap mChildrenLayoutTop;
    public final Rect mClippingRect;
    public Runnable mCollapseExpandAction;
    public int mContentMarginEnd;
    public final Context mContext;
    public boolean mExpanded;
    public View mFooter;
    public PageIndicator mFooterPageIndicator;
    public LinearLayout mHorizontalContentContainer;
    public LinearLayout mHorizontalLinearLayout;
    public boolean mListening;
    public UniqueObjectHostView mMediaHostView;
    public final int mMediaTopMargin;
    public final int mMediaTotalBottomMargin;
    public int mMovableContentStartIndex;
    public final List mOnConfigurationChangedListeners;
    public QSLogger mQsLogger;
    public boolean mShouldMoveMediaOnExpansion;
    public float mSquishinessFraction;
    public QSTileLayout mTileLayout;
    public boolean mUsingHorizontalLayout;
    public boolean mUsingMediaPlayer;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface OnConfigurationChangedListener {
    }

    public QSPanel(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mOnConfigurationChangedListeners = new ArrayList();
        this.mSquishinessFraction = 1.0f;
        this.mChildrenLayoutTop = new ArrayMap();
        this.mClippingRect = new Rect();
        this.mShouldMoveMediaOnExpansion = true;
        this.mCanCollapse = true;
        this.mUsingMediaPlayer = Utils.useQsMediaPlayer(context);
        this.mMediaTotalBottomMargin = getResources().getDimensionPixelSize(R.dimen.quick_settings_bottom_margin_media);
        this.mMediaTopMargin = getResources().getDimensionPixelSize(R.dimen.qs_tile_margin_vertical);
        this.mContext = context;
        setOrientation(1);
        this.mMovableContentStartIndex = getChildCount();
    }

    public static void switchToParent(View view, ViewGroup viewGroup, int i, String str) {
        if (viewGroup == null) {
            Log.w(str, "Trying to move view to null parent", new IllegalStateException());
            return;
        }
        ViewGroup viewGroup2 = (ViewGroup) view.getParent();
        if (viewGroup2 != viewGroup) {
            if (viewGroup2 != null) {
                viewGroup2.removeView(view);
            }
            viewGroup.addView(view, i);
        } else {
            if (viewGroup.indexOfChild(view) == i) {
                return;
            }
            viewGroup.removeView(view);
            viewGroup.addView(view, i);
        }
    }

    public QSEvent closePanelEvent() {
        return QSEvent.QS_PANEL_COLLAPSED;
    }

    public String getDumpableTag() {
        return "QSPanel";
    }

    public View getMediaPlaceholder() {
        return null;
    }

    public QSTileLayout getOrCreateTileLayout() {
        if (this.mTileLayout == null) {
            QSTileLayout qSTileLayout = (QSTileLayout) LayoutInflater.from(this.mContext).inflate(R.layout.qs_paged_tile_layout, (ViewGroup) this, false);
            this.mTileLayout = qSTileLayout;
            qSTileLayout.setLogger(this.mQsLogger);
            this.mTileLayout.setSquishinessFraction(this.mSquishinessFraction);
        }
        return this.mTileLayout;
    }

    @Override // android.view.View
    public final void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
        ((ArrayList) this.mOnConfigurationChangedListeners).forEach(new Consumer() { // from class: com.android.systemui.qs.QSPanel$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Configuration configuration2 = configuration;
                int i = QSPanel.$r8$clinit;
                QSPanelControllerBase qSPanelControllerBase = QSPanelControllerBase.this;
                boolean z = qSPanelControllerBase.mShouldUseSplitNotificationShade;
                int i2 = qSPanelControllerBase.mLastOrientation;
                int i3 = qSPanelControllerBase.mLastScreenLayout;
                boolean shouldUseSplitNotificationShade = qSPanelControllerBase.mSplitShadeStateController.shouldUseSplitNotificationShade(qSPanelControllerBase.mView.getResources());
                qSPanelControllerBase.mShouldUseSplitNotificationShade = shouldUseSplitNotificationShade;
                int i4 = configuration2.orientation;
                qSPanelControllerBase.mLastOrientation = i4;
                int i5 = configuration2.screenLayout;
                qSPanelControllerBase.mLastScreenLayout = i5;
                qSPanelControllerBase.mQSLogger.logOnConfigurationChanged(i2, i4, z, shouldUseSplitNotificationShade, i3, i5, ((QSPanel) qSPanelControllerBase.mView).getDumpableTag());
                qSPanelControllerBase.switchTileLayout(false);
                qSPanelControllerBase.onConfigurationChanged();
                boolean z2 = qSPanelControllerBase.mShouldUseSplitNotificationShade;
                if (z != z2) {
                    qSPanelControllerBase.onSplitShadeChanged(z2);
                }
            }
        });
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.mFooter = findViewById(R.id.qs_footer);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        if (this.mCanCollapse) {
            accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_COLLAPSE);
        }
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        for (int i5 = 0; i5 < getChildCount(); i5++) {
            View childAt = getChildAt(i5);
            this.mChildrenLayoutTop.put(childAt, Integer.valueOf(childAt.getTop()));
        }
        updateViewPositions();
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        QSTileLayout qSTileLayout = this.mTileLayout;
        if (qSTileLayout instanceof PagedTileLayout) {
            PageIndicator pageIndicator = this.mFooterPageIndicator;
            if (pageIndicator != null) {
                pageIndicator.setNumPages(((PagedTileLayout) qSTileLayout).getNumPages());
            }
            if (((View) this.mTileLayout).getParent() == this) {
                int size = 10000 - View.MeasureSpec.getSize(i2);
                int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(10000, 1073741824);
                ((PagedTileLayout) this.mTileLayout).mExcessHeight = size;
                i2 = makeMeasureSpec;
            }
        }
        super.onMeasure(i, i2);
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() != 8) {
                int measuredHeight = childAt.getMeasuredHeight() + paddingTop;
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) childAt.getLayoutParams();
                paddingTop = marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + measuredHeight;
            }
        }
        setMeasuredDimension(getMeasuredWidth(), paddingTop);
    }

    @Override // com.android.systemui.tuner.TunerService.Tunable
    public void onTuningChanged(String str, String str2) {
        View view;
        if (!"qs_show_brightness".equals(str) || (view = this.mBrightnessView) == null) {
            return;
        }
        view.setVisibility(TunerService.parseIntegerSwitch(str2, true) ? 0 : 8);
    }

    public QSEvent openPanelEvent() {
        return QSEvent.QS_PANEL_EXPANDED;
    }

    @Override // android.view.View
    public final boolean performAccessibilityAction(int i, Bundle bundle) {
        Runnable runnable;
        if ((i != 262144 && i != 524288) || (runnable = this.mCollapseExpandAction) == null) {
            return super.performAccessibilityAction(i, bundle);
        }
        runnable.run();
        return true;
    }

    public final void setBrightnessView(View view) {
        View view2 = this.mBrightnessView;
        if (view2 != null) {
            removeView(view2);
            this.mChildrenLayoutTop.remove(this.mBrightnessView);
            this.mMovableContentStartIndex--;
        }
        addView(view, 0);
        this.mBrightnessView = view;
        setBrightnessViewMargin();
        this.mMovableContentStartIndex++;
    }

    public final void setBrightnessViewMargin() {
        View view = this.mBrightnessView;
        if (view != null) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.rounded_slider_boundary_offset);
            marginLayoutParams.topMargin = this.mContext.getResources().getDimensionPixelSize(R.dimen.qs_brightness_margin_top) - dimensionPixelSize;
            marginLayoutParams.bottomMargin = this.mContext.getResources().getDimensionPixelSize(R.dimen.qs_brightness_margin_bottom) - dimensionPixelSize;
            this.mBrightnessView.setLayoutParams(marginLayoutParams);
        }
    }

    public final void setColumnRowLayout(boolean z) {
        this.mTileLayout.setMinRows(z ? 2 : 1);
        this.mTileLayout.setMaxColumns(z ? 2 : 4);
        LinearLayout linearLayout = z ? this.mHorizontalContentContainer : this;
        Object obj = this.mTileLayout;
        if (obj != null && ((View) obj).getParent() != linearLayout) {
            Object obj2 = this.mTileLayout;
            int i = linearLayout == this ? this.mMovableContentStartIndex : 0;
            switchToParent((View) obj2, linearLayout, i, getDumpableTag());
            int i2 = i + 1;
            View view = this.mFooter;
            if (view != null) {
                switchToParent(view, linearLayout, i2, getDumpableTag());
            }
        }
        LinearLayout linearLayout2 = this.mHorizontalLinearLayout;
        if (linearLayout2 != null) {
            linearLayout2.setVisibility(z ? 0 : 8);
        }
    }

    public void setHorizontalContentContainerClipping() {
        LinearLayout linearLayout = this.mHorizontalContentContainer;
        if (linearLayout != null) {
            linearLayout.setClipChildren(true);
            this.mHorizontalContentContainer.setClipToPadding(false);
            this.mHorizontalContentContainer.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.android.systemui.qs.QSPanel$$ExternalSyntheticLambda1
                @Override // android.view.View.OnLayoutChangeListener
                public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                    QSPanel qSPanel = QSPanel.this;
                    int i9 = QSPanel.$r8$clinit;
                    qSPanel.getClass();
                    int i10 = i3 - i;
                    if (i10 == i7 - i5 && i4 - i2 == i8 - i6) {
                        return;
                    }
                    Rect rect = qSPanel.mClippingRect;
                    rect.right = i10;
                    rect.bottom = i4 - i2;
                    qSPanel.mHorizontalContentContainer.setClipBounds(rect);
                }
            });
            Rect rect = this.mClippingRect;
            rect.left = 0;
            rect.top = -1000;
            this.mHorizontalContentContainer.setClipBounds(rect);
        }
    }

    public final void updateMediaHostContentMargins(UniqueObjectHostView uniqueObjectHostView) {
        if (this.mUsingMediaPlayer) {
            int i = this.mUsingHorizontalLayout ? this.mContentMarginEnd : 0;
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) uniqueObjectHostView.getLayoutParams();
            if (layoutParams != null) {
                layoutParams.setMarginStart(0);
                layoutParams.setMarginEnd(i);
                uniqueObjectHostView.setLayoutParams(layoutParams);
            }
        }
    }

    public void updatePadding() {
        Resources resources = this.mContext.getResources();
        setPaddingRelative(getPaddingStart(), resources.getDimensionPixelSize(R.dimen.qs_panel_padding_top), getPaddingEnd(), resources.getDimensionPixelSize(R.dimen.qs_panel_padding_bottom));
    }

    public final void updatePageIndicator() {
        PageIndicator pageIndicator;
        if (!(this.mTileLayout instanceof PagedTileLayout) || (pageIndicator = this.mFooterPageIndicator) == null) {
            return;
        }
        pageIndicator.setVisibility(8);
        PagedTileLayout pagedTileLayout = (PagedTileLayout) this.mTileLayout;
        PageIndicator pageIndicator2 = this.mFooterPageIndicator;
        pagedTileLayout.mPageIndicator = pageIndicator2;
        pageIndicator2.setNumPages(pagedTileLayout.mPages.size());
        pagedTileLayout.mPageIndicator.setLocation(pagedTileLayout.mPageIndicatorPosition);
        pagedTileLayout.mPageIndicator.mPageScrollActionListener = new PagedTileLayout$$ExternalSyntheticLambda1(pagedTileLayout);
    }

    public final void updateResources() {
        updatePadding();
        updatePageIndicator();
        setBrightnessViewMargin();
        QSTileLayout qSTileLayout = this.mTileLayout;
        if (qSTileLayout != null) {
            qSTileLayout.updateResources();
        }
    }

    public final void updateViewPositions() {
        int tilesHeight = this.mTileLayout.getTilesHeight() - this.mTileLayout.getHeight();
        boolean z = false;
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (z) {
                int i2 = (childAt != this.mMediaHostView || this.mShouldMoveMediaOnExpansion) ? tilesHeight : 0;
                Integer num = (Integer) this.mChildrenLayoutTop.get(childAt);
                if (num != null) {
                    int intValue = num.intValue() + i2;
                    childAt.setLeftTopRightBottom(childAt.getLeft(), intValue, childAt.getRight(), childAt.getHeight() + intValue);
                }
            }
            if (childAt == this.mTileLayout) {
                z = true;
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface QSTileLayout {
        void addTile(QSPanelControllerBase.TileRecord tileRecord);

        int getHeight();

        int getNumVisibleTiles();

        int getTilesHeight();

        void removeTile(QSPanelControllerBase.TileRecord tileRecord);

        void setListening(boolean z, UiEventLogger uiEventLogger);

        boolean setMaxColumns(int i);

        boolean setMinRows(int i);

        void setSquishinessFraction(float f);

        boolean updateResources();

        default void restoreInstanceState(Bundle bundle) {
        }

        default void saveInstanceState(Bundle bundle) {
        }

        default void setLogger(QSLogger qSLogger) {
        }

        default void setExpansion(float f, float f2) {
        }
    }
}

package com.android.systemui.qs;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.android.internal.policy.SystemBarUtils;
import com.android.systemui.Dumpable;
import com.android.systemui.plugins.qs.QS;
import com.android.systemui.qs.QSPanel;
import com.android.systemui.qs.customize.QSCustomizer;
import com.android.systemui.shade.TouchLogger;
import com.android.systemui.util.animation.UniqueObjectHostView;
import com.android.wm.shell.R;
import java.io.PrintWriter;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class QSContainerImpl extends FrameLayout implements Dumpable {
    public boolean mClippingEnabled;
    public int mContentHorizontalPadding;
    public int mFancyClippingBottom;
    public int mFancyClippingLeftInset;
    public final Path mFancyClippingPath;
    public final float[] mFancyClippingRadii;
    public int mFancyClippingRightInset;
    public int mFancyClippingTop;
    public QuickStatusBarHeader mHeader;
    public int mHeightOverride;
    public int mHorizontalMargins;
    public boolean mIsFullWidth;
    public QSCustomizer mQSCustomizer;
    public QSPanel mQSPanel;
    public NonInterceptingScrollView mQSPanelContainer;
    public boolean mQsDisabled;
    public float mQsExpansion;
    public int mTilesPageMargin;

    public QSContainerImpl(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mFancyClippingRadii = new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
        this.mFancyClippingPath = new Path();
        this.mHeightOverride = -1;
        this.mContentHorizontalPadding = -1;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        if (!this.mFancyClippingPath.isEmpty()) {
            canvas.translate(0.0f, -getTranslationY());
            canvas.clipOutPath(this.mFancyClippingPath);
            canvas.translate(0.0f, getTranslationY());
        }
        super.dispatchDraw(canvas);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        boolean dispatchTouchEvent = super.dispatchTouchEvent(motionEvent);
        TouchLogger.Companion.logDispatchTouch(QS.TAG, motionEvent, dispatchTouchEvent);
        return dispatchTouchEvent;
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println(getClass().getSimpleName() + " updateClippingPath: leftInset(" + this.mFancyClippingLeftInset + ") top(" + this.mFancyClippingTop + ") rightInset(" + this.mFancyClippingRightInset + ") bottom(" + this.mFancyClippingBottom + ") mClippingEnabled(" + this.mClippingEnabled + ") mIsFullWidth(" + this.mIsFullWidth + ")");
    }

    @Override // android.view.View
    public final boolean hasOverlappingRendering() {
        return false;
    }

    public final boolean isTransformedTouchPointInView(float f, float f2, View view, PointF pointF) {
        if (!this.mClippingEnabled || getTranslationY() + f2 <= this.mFancyClippingTop) {
            return super.isTransformedTouchPointInView(f, f2, view, pointF);
        }
        return false;
    }

    @Override // android.view.ViewGroup
    public final void measureChildWithMargins(View view, int i, int i2, int i3, int i4) {
        if (view != this.mQSPanelContainer) {
            super.measureChildWithMargins(view, i, i2, i3, i4);
        }
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.mQSPanelContainer = (NonInterceptingScrollView) findViewById(R.id.expanded_qs_scroll_view);
        this.mQSPanel = (QSPanel) findViewById(R.id.quick_settings_panel);
        this.mHeader = (QuickStatusBarHeader) findViewById(R.id.header);
        this.mQSCustomizer = (QSCustomizer) findViewById(R.id.qs_customize);
        setImportantForAccessibility(2);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        updateExpansion();
        updateClippingPath();
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i2);
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mQSPanelContainer.getLayoutParams();
        int paddingBottom = ((size - marginLayoutParams.topMargin) - marginLayoutParams.bottomMargin) - getPaddingBottom();
        int i3 = ((FrameLayout) this).mPaddingLeft + ((FrameLayout) this).mPaddingRight + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
        this.mQSPanelContainer.measure(FrameLayout.getChildMeasureSpec(i, i3, marginLayoutParams.width), View.MeasureSpec.makeMeasureSpec(paddingBottom, Integer.MIN_VALUE));
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(this.mQSPanelContainer.getMeasuredWidth() + i3, 1073741824), View.MeasureSpec.makeMeasureSpec(size, 1073741824));
        this.mQSCustomizer.measure(i, View.MeasureSpec.makeMeasureSpec(size, 1073741824));
    }

    @Override // android.view.View
    public final boolean performClick() {
        return true;
    }

    public final void updateClippingPath() {
        this.mFancyClippingPath.reset();
        if (!this.mClippingEnabled) {
            invalidate();
            return;
        }
        boolean z = this.mIsFullWidth;
        this.mFancyClippingPath.addRoundRect(z ? -this.mFancyClippingLeftInset : 0, this.mFancyClippingTop, z ? getWidth() + this.mFancyClippingRightInset : getWidth(), this.mFancyClippingBottom, this.mFancyClippingRadii, Path.Direction.CW);
        invalidate();
    }

    public final void updateExpansion() {
        int i = this.mHeightOverride;
        if (i == -1) {
            i = getMeasuredHeight();
        }
        setBottom(getTop() + (this.mQSCustomizer.isCustomizing() ? this.mQSCustomizer.getHeight() : Math.round(this.mQsExpansion * (i - this.mHeader.getHeight())) + this.mHeader.getHeight()));
    }

    public final void updateResources(QSPanelController qSPanelController, QuickStatusBarHeaderController quickStatusBarHeaderController) {
        UniqueObjectHostView uniqueObjectHostView;
        Context context = ((FrameLayout) this).mContext;
        int quickQsOffsetHeight = context.getResources().getBoolean(R.bool.config_use_large_screen_shade_header) ? 0 : SystemBarUtils.getQuickQsOffsetHeight(context);
        if (!((FrameLayout) this).mContext.getResources().getBoolean(R.bool.config_use_large_screen_shade_header)) {
            Context context2 = ((FrameLayout) this).mContext;
            quickQsOffsetHeight = Math.max(context2.getResources().getDimensionPixelSize(R.dimen.large_screen_shade_header_height), SystemBarUtils.getStatusBarHeight(context2));
        }
        NonInterceptingScrollView nonInterceptingScrollView = this.mQSPanelContainer;
        if (nonInterceptingScrollView != null) {
            nonInterceptingScrollView.setPaddingRelative(nonInterceptingScrollView.getPaddingStart(), quickQsOffsetHeight, this.mQSPanelContainer.getPaddingEnd(), this.mQSPanelContainer.getPaddingBottom());
        } else {
            QSPanel qSPanel = this.mQSPanel;
            qSPanel.setPaddingRelative(qSPanel.getPaddingStart(), quickQsOffsetHeight, this.mQSPanel.getPaddingEnd(), this.mQSPanel.getPaddingBottom());
        }
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.qs_horizontal_margin);
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.qs_content_horizontal_padding);
        int dimensionPixelSize3 = getResources().getDimensionPixelSize(R.dimen.qs_tiles_page_horizontal_margin);
        boolean z = (dimensionPixelSize2 == this.mContentHorizontalPadding && dimensionPixelSize == this.mHorizontalMargins && dimensionPixelSize3 == this.mTilesPageMargin) ? false : true;
        this.mContentHorizontalPadding = dimensionPixelSize2;
        this.mHorizontalMargins = dimensionPixelSize;
        this.mTilesPageMargin = dimensionPixelSize3;
        if (z) {
            for (int i = 0; i < getChildCount(); i++) {
                View childAt = getChildAt(i);
                if (childAt != this.mQSCustomizer) {
                    if (childAt.getId() != R.id.qs_footer_actions) {
                        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
                        int i2 = this.mHorizontalMargins;
                        layoutParams.rightMargin = i2;
                        layoutParams.leftMargin = i2;
                    }
                    if (childAt == this.mQSPanelContainer || childAt == this.mQSPanel) {
                        int i3 = this.mContentHorizontalPadding;
                        QSPanel qSPanel2 = (QSPanel) qSPanelController.mView;
                        UniqueObjectHostView uniqueObjectHostView2 = qSPanelController.mMediaHost.hostView;
                        uniqueObjectHostView = uniqueObjectHostView2 != null ? uniqueObjectHostView2 : null;
                        qSPanel2.mContentMarginEnd = i3;
                        qSPanel2.updateMediaHostContentMargins(uniqueObjectHostView);
                        int i4 = this.mTilesPageMargin;
                        QSPanel.QSTileLayout qSTileLayout = ((QSPanel) qSPanelController.mView).mTileLayout;
                        if (qSTileLayout instanceof PagedTileLayout) {
                            PagedTileLayout pagedTileLayout = (PagedTileLayout) qSTileLayout;
                            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) pagedTileLayout.getLayoutParams();
                            int i5 = -i4;
                            marginLayoutParams.setMarginStart(i5);
                            marginLayoutParams.setMarginEnd(i5);
                            pagedTileLayout.setLayoutParams(marginLayoutParams);
                            int size = pagedTileLayout.mPages.size();
                            for (int i6 = 0; i6 < size; i6++) {
                                View view = (View) pagedTileLayout.mPages.get(i6);
                                view.setPadding(i4, view.getPaddingTop(), i4, view.getPaddingBottom());
                            }
                        }
                    } else if (childAt == this.mHeader) {
                        int i7 = this.mContentHorizontalPadding;
                        QuickQSPanelController quickQSPanelController = quickStatusBarHeaderController.mQuickQSPanelController;
                        QuickQSPanel quickQSPanel = (QuickQSPanel) quickQSPanelController.mView;
                        UniqueObjectHostView uniqueObjectHostView3 = quickQSPanelController.mMediaHost.hostView;
                        uniqueObjectHostView = uniqueObjectHostView3 != null ? uniqueObjectHostView3 : null;
                        quickQSPanel.mContentMarginEnd = i7;
                        quickQSPanel.updateMediaHostContentMargins(uniqueObjectHostView);
                    } else if (childAt.getId() != R.id.qs_footer_actions) {
                        childAt.setPaddingRelative(this.mContentHorizontalPadding, childAt.getPaddingTop(), this.mContentHorizontalPadding, childAt.getPaddingBottom());
                    }
                }
            }
        }
    }
}

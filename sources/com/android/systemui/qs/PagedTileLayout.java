package com.android.systemui.qs;

import android.animation.AnimatorSet;
import android.app.ActivityManager;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Scroller;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.log.ConstantStringsLoggerImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.qs.QSPanel;
import com.android.systemui.qs.QSPanelControllerBase;
import com.android.systemui.qs.logging.QSLogger;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class PagedTileLayout extends ViewPager implements QSPanel.QSTileLayout {
    public static final PagedTileLayout$$ExternalSyntheticLambda0 SCROLL_CUBIC = new PagedTileLayout$$ExternalSyntheticLambda0();
    public final AnonymousClass3 mAdapter;
    public AnimatorSet mBounceAnimatorSet;
    public boolean mDistributeTiles;
    public int mExcessHeight;
    public int mLastExcessHeight;
    public float mLastExpansion;
    public int mLastMaxHeight;
    public int mLayoutDirection;
    public int mLayoutOrientation;
    public boolean mListening;
    public QSLogger mLogger;
    public int mMaxColumns;
    public int mMinRows;
    public final AnonymousClass2 mOnPageChangeListener;
    public PageIndicator mPageIndicator;
    public float mPageIndicatorPosition;
    public QSAnimator mPageListener;
    public int mPageToRestore;
    public final ArrayList mPages;
    public final boolean mRunningInTestHarness;
    Scroller mScroller;
    public final ArrayList mTiles;
    public final UiEventLogger mUiEventLogger;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.qs.PagedTileLayout$2, reason: invalid class name */
    public final class AnonymousClass2 implements ViewPager.OnPageChangeListener {
        public int mCurrentScrollState = 0;
        public boolean mIsScrollJankTraceBegin = false;

        public AnonymousClass2() {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public final void onPageScrollStateChanged(int i) {
            if (i != this.mCurrentScrollState && i == 0) {
                InteractionJankMonitor.getInstance().end(6);
                this.mIsScrollJankTraceBegin = false;
            }
            this.mCurrentScrollState = i;
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public final void onPageScrolled(int i, float f, int i2) {
            boolean z = this.mIsScrollJankTraceBegin;
            PagedTileLayout pagedTileLayout = PagedTileLayout.this;
            if (!z && this.mCurrentScrollState == 1) {
                InteractionJankMonitor.getInstance().begin(pagedTileLayout, 6);
                this.mIsScrollJankTraceBegin = true;
            }
            PageIndicator pageIndicator = pagedTileLayout.mPageIndicator;
            if (pageIndicator == null) {
                return;
            }
            float f2 = i + f;
            pagedTileLayout.mPageIndicatorPosition = f2;
            pageIndicator.setLocation(f2);
            if (pagedTileLayout.mPageListener != null) {
                if (pagedTileLayout.isLayoutRtl()) {
                    i = (pagedTileLayout.mPages.size() - 1) - i;
                }
                QSAnimator qSAnimator = pagedTileLayout.mPageListener;
                boolean z2 = i2 == 0 && i == 0;
                if (i2 != 0) {
                    i = -1;
                }
                qSAnimator.onPageChanged(i, z2);
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public final void onPageSelected(int i) {
            PagedTileLayout$$ExternalSyntheticLambda0 pagedTileLayout$$ExternalSyntheticLambda0 = PagedTileLayout.SCROLL_CUBIC;
            PagedTileLayout pagedTileLayout = PagedTileLayout.this;
            pagedTileLayout.updateSelected();
            if (pagedTileLayout.mPageIndicator == null || pagedTileLayout.mPageListener == null) {
                return;
            }
            if (pagedTileLayout.isLayoutRtl()) {
                i = (pagedTileLayout.mPages.size() - 1) - i;
            }
            pagedTileLayout.mPageListener.onPageChanged(i, i == 0);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.qs.PagedTileLayout$3, reason: invalid class name */
    public final class AnonymousClass3 extends PagerAdapter {
        public AnonymousClass3() {
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public final void destroyItem(ViewPager viewPager, int i, Object obj) {
            PagedTileLayout pagedTileLayout = PagedTileLayout.this;
            pagedTileLayout.mLogger.d(Integer.valueOf(i), "Destantiating page at");
            viewPager.removeView((View) obj);
            pagedTileLayout.updateListening();
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public final int getCount() {
            return PagedTileLayout.this.mPages.size();
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public final Object instantiateItem(ViewPager viewPager, int i) {
            PagedTileLayout pagedTileLayout = PagedTileLayout.this;
            pagedTileLayout.mLogger.d(Integer.valueOf(i), "Instantiating page at");
            if (pagedTileLayout.isLayoutRtl()) {
                i = (pagedTileLayout.mPages.size() - 1) - i;
            }
            ViewGroup viewGroup = (ViewGroup) pagedTileLayout.mPages.get(i);
            if (viewGroup.getParent() != null) {
                viewPager.removeView(viewGroup);
            }
            viewPager.addView(viewGroup);
            pagedTileLayout.updateListening();
            return viewGroup;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public final boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }
    }

    public PagedTileLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mTiles = new ArrayList();
        this.mPages = new ArrayList();
        this.mDistributeTiles = false;
        this.mPageToRestore = -1;
        this.mUiEventLogger = QSEvents.qsUiEventsLogger;
        this.mMinRows = 1;
        this.mMaxColumns = 100;
        this.mRunningInTestHarness = ActivityManager.isRunningInTestHarness();
        this.mLastMaxHeight = -1;
        AnonymousClass2 anonymousClass2 = new AnonymousClass2();
        AnonymousClass3 anonymousClass3 = new AnonymousClass3();
        this.mAdapter = anonymousClass3;
        this.mScroller = new Scroller(context, SCROLL_CUBIC);
        setAdapter(anonymousClass3);
        super.mOnPageChangeListener = anonymousClass2;
        setCurrentItem(0, false);
        this.mLayoutOrientation = getResources().getConfiguration().orientation;
        this.mLayoutDirection = getLayoutDirection();
    }

    @Override // com.android.systemui.qs.QSPanel.QSTileLayout
    public final void addTile(QSPanelControllerBase.TileRecord tileRecord) {
        this.mTiles.add(tileRecord);
        forceTilesRedistribution("adding new tile");
        requestLayout();
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public final void computeScroll() {
        if (!this.mScroller.isFinished() && this.mScroller.computeScrollOffset()) {
            if (!this.mFakeDragging) {
                beginFakeDrag();
            }
            try {
                super.fakeDragBy(getScrollX() - this.mScroller.getCurrX());
                postInvalidateOnAnimation();
            } catch (NullPointerException e) {
                this.mLogger.logException(e, "FakeDragBy called before begin");
                final int size = this.mPages.size() - 1;
                post(new Runnable() { // from class: com.android.systemui.qs.PagedTileLayout$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        PagedTileLayout pagedTileLayout = PagedTileLayout.this;
                        int i = size;
                        PagedTileLayout$$ExternalSyntheticLambda0 pagedTileLayout$$ExternalSyntheticLambda0 = PagedTileLayout.SCROLL_CUBIC;
                        pagedTileLayout.setCurrentItem(i, true);
                        AnimatorSet animatorSet = pagedTileLayout.mBounceAnimatorSet;
                        if (animatorSet != null) {
                            animatorSet.start();
                        }
                        pagedTileLayout.setOffscreenPageLimit(1);
                    }
                });
            }
        } else if (this.mFakeDragging) {
            endFakeDrag();
            AnimatorSet animatorSet = this.mBounceAnimatorSet;
            if (animatorSet != null) {
                animatorSet.start();
            }
            setOffscreenPageLimit(1);
        }
        super.computeScroll();
    }

    public final TileLayout createTileLayout() {
        TileLayout tileLayout = (TileLayout) LayoutInflater.from(getContext()).inflate(R.layout.qs_paged_page, (ViewGroup) this, false);
        tileLayout.setMinRows(this.mMinRows);
        tileLayout.setMaxColumns(this.mMaxColumns);
        tileLayout.setSelected(false);
        tileLayout.setSquishinessFraction(this.mPages.isEmpty() ? 1.0f : ((TileLayout) this.mPages.get(0)).mSquishinessFraction);
        return tileLayout;
    }

    @Override // androidx.viewpager.widget.ViewPager
    public final void endFakeDrag() {
        try {
            super.endFakeDrag();
        } catch (NullPointerException e) {
            this.mLogger.logException(e, "endFakeDrag called without velocityTracker");
        }
    }

    public final void forceTilesRedistribution(String str) {
        this.mLogger.d(str, "forcing tile redistribution across pages, reason");
        this.mDistributeTiles = true;
    }

    public final int getDeltaXForPageScrolling(int i) {
        if (i == 0 && this.mCurItem != 0) {
            return -getWidth();
        }
        if (i != 1 || this.mCurItem == this.mPages.size() - 1) {
            return 0;
        }
        return getWidth();
    }

    public final int getNumPages() {
        int size = this.mTiles.size();
        TileLayout tileLayout = (TileLayout) this.mPages.get(0);
        int max = Math.max(size / Math.max(tileLayout.mColumns * tileLayout.mRows, 1), 1);
        TileLayout tileLayout2 = (TileLayout) this.mPages.get(0);
        return size > Math.max(tileLayout2.mColumns * tileLayout2.mRows, 1) * max ? max + 1 : max;
    }

    @Override // com.android.systemui.qs.QSPanel.QSTileLayout
    public final int getNumVisibleTiles() {
        if (this.mPages.size() == 0) {
            return 0;
        }
        ArrayList arrayList = this.mPages;
        boolean isLayoutRtl = isLayoutRtl();
        int i = this.mCurItem;
        if (isLayoutRtl) {
            i = (this.mPages.size() - 1) - i;
        }
        return ((TileLayout) arrayList.get(i)).mRecords.size();
    }

    @Override // com.android.systemui.qs.QSPanel.QSTileLayout
    public final int getTilesHeight() {
        TileLayout tileLayout = (TileLayout) this.mPages.get(0);
        if (tileLayout == null) {
            return 0;
        }
        return tileLayout.getTilesHeight();
    }

    @Override // android.view.View
    public final boolean hasOverlappingRendering() {
        return false;
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        int size = this.mPages.size();
        for (int i = 0; i < size; i++) {
            View view = (View) this.mPages.get(i);
            if (view.getParent() == null) {
                view.dispatchConfigurationChanged(configuration);
            }
        }
        int i2 = this.mLayoutOrientation;
        int i3 = configuration.orientation;
        if (i2 == i3) {
            this.mLogger.d(configuration, "Orientation didn't change, tiles might be not redistributed, new config");
            return;
        }
        this.mLayoutOrientation = i3;
        forceTilesRedistribution("orientation changed to " + this.mLayoutOrientation);
        setCurrentItem(0, false);
        this.mPageToRestore = 0;
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.mPages.add(createTileLayout());
        this.mAdapter.notifyDataSetChanged();
    }

    @Override // android.view.View
    public final boolean onGenericMotionEvent(MotionEvent motionEvent) {
        float f;
        float axisValue;
        if ((motionEvent.getSource() & 2) != 0 && motionEvent.getAction() == 8) {
            if ((motionEvent.getMetaState() & 1) != 0) {
                axisValue = motionEvent.getAxisValue(9);
                f = 0.0f;
            } else {
                f = -motionEvent.getAxisValue(9);
                axisValue = motionEvent.getAxisValue(10);
            }
            if (axisValue != 0.0f || f != 0.0f) {
                int i = 0;
                if (!isLayoutRtl() ? axisValue > 0.0f || f > 0.0f : axisValue < 0.0f || f < 0.0f) {
                    i = 1;
                }
                if (this.mScroller.isFinished()) {
                    scrollByX(getDeltaXForPageScrolling(i), 300);
                }
                return true;
            }
        }
        return super.onGenericMotionEvent(motionEvent);
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        AnonymousClass3 anonymousClass3 = this.mAdapter;
        if (anonymousClass3 == null || anonymousClass3.getCount() <= 0) {
            return;
        }
        accessibilityEvent.setItemCount(this.mAdapter.getCount());
        boolean isLayoutRtl = isLayoutRtl();
        int i = this.mCurItem;
        if (isLayoutRtl) {
            i = (this.mPages.size() - 1) - i;
        }
        accessibilityEvent.setFromIndex(i);
        boolean isLayoutRtl2 = isLayoutRtl();
        int i2 = this.mCurItem;
        if (isLayoutRtl2) {
            i2 = (this.mPages.size() - 1) - i2;
        }
        accessibilityEvent.setToIndex(i2);
    }

    public final void onInitializeAccessibilityNodeInfoInternal(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfoInternal(accessibilityNodeInfo);
        if (this.mCurItem != 0) {
            accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_LEFT);
        }
        if (this.mCurItem != this.mPages.size() - 1) {
            accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_RIGHT);
        }
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (((TileLayout) this.mPages.get(0)).getParent() == null) {
            ((TileLayout) this.mPages.get(0)).layout(i, i2, i3, i4);
        }
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public final void onMeasure(int i, int i2) {
        int size = this.mTiles.size();
        if (this.mDistributeTiles || this.mLastMaxHeight != View.MeasureSpec.getSize(i2) || this.mLastExcessHeight != this.mExcessHeight) {
            this.mLastMaxHeight = View.MeasureSpec.getSize(i2);
            this.mLastExcessHeight = this.mExcessHeight;
            if (((TileLayout) this.mPages.get(0)).updateMaxRows(size) || this.mDistributeTiles) {
                this.mDistributeTiles = false;
                int numPages = getNumPages();
                int size2 = this.mPages.size();
                for (int i3 = 0; i3 < size2; i3++) {
                    ((TileLayout) this.mPages.get(i3)).removeAllViews();
                }
                PageIndicator pageIndicator = this.mPageIndicator;
                if (pageIndicator != null) {
                    pageIndicator.setNumPages(numPages);
                }
                if (size2 != numPages) {
                    while (this.mPages.size() < numPages) {
                        ConstantStringsLoggerImpl constantStringsLoggerImpl = this.mLogger.$$delegate_0;
                        constantStringsLoggerImpl.buffer.log(constantStringsLoggerImpl.tag, LogLevel.DEBUG, "Adding new page", null);
                        this.mPages.add(createTileLayout());
                    }
                    while (this.mPages.size() > numPages) {
                        ConstantStringsLoggerImpl constantStringsLoggerImpl2 = this.mLogger.$$delegate_0;
                        constantStringsLoggerImpl2.buffer.log(constantStringsLoggerImpl2.tag, LogLevel.DEBUG, "Removing page", null);
                        ArrayList arrayList = this.mPages;
                        arrayList.remove(arrayList.size() - 1);
                    }
                    setAdapter(this.mAdapter);
                    this.mAdapter.notifyDataSetChanged();
                    int i4 = this.mPageToRestore;
                    if (i4 != -1) {
                        setCurrentItem(i4, false);
                        this.mPageToRestore = -1;
                    }
                }
                TileLayout tileLayout = (TileLayout) this.mPages.get(0);
                int max = Math.max(tileLayout.mColumns * tileLayout.mRows, 1);
                int size3 = this.mTiles.size();
                this.mLogger.logTileDistributionInProgress(max, size3);
                int i5 = 0;
                for (int i6 = 0; i6 < size3; i6++) {
                    QSPanelControllerBase.TileRecord tileRecord = (QSPanelControllerBase.TileRecord) this.mTiles.get(i6);
                    if (((TileLayout) this.mPages.get(i5)).mRecords.size() == max) {
                        i5++;
                    }
                    this.mLogger.logTileDistributed(i5, tileRecord.tile.getClass().getSimpleName());
                    ((TileLayout) this.mPages.get(i5)).addTile(tileRecord);
                }
            }
            int i7 = ((TileLayout) this.mPages.get(0)).mRows;
            for (int i8 = 0; i8 < this.mPages.size(); i8++) {
                ((TileLayout) this.mPages.get(i8)).mRows = i7;
            }
        }
        super.onMeasure(i, i2);
        int childCount = getChildCount();
        int i9 = 0;
        for (int i10 = 0; i10 < childCount; i10++) {
            int measuredHeight = getChildAt(i10).getMeasuredHeight();
            if (measuredHeight > i9) {
                i9 = measuredHeight;
            }
        }
        if (((TileLayout) this.mPages.get(0)).getParent() == null) {
            ((TileLayout) this.mPages.get(0)).measure(i, i2);
            int measuredHeight2 = ((TileLayout) this.mPages.get(0)).getMeasuredHeight();
            if (measuredHeight2 > i9) {
                i9 = measuredHeight2;
            }
        }
        setMeasuredDimension(getMeasuredWidth(), getPaddingBottom() + i9);
    }

    @Override // android.view.View
    public final void onRtlPropertiesChanged(int i) {
        boolean z = this.mLayoutDirection == 1;
        int i2 = this.mCurItem;
        if (z) {
            i2 = (this.mPages.size() - 1) - i2;
        }
        super.onRtlPropertiesChanged(i);
        if (this.mLayoutDirection != i) {
            this.mLayoutDirection = i;
            setAdapter(this.mAdapter);
            setCurrentItem(i2, false);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x001e, code lost:
    
        r5 = 4096;
     */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean performAccessibilityAction(int r5, android.os.Bundle r6) {
        /*
            r4 = this;
            android.view.accessibility.AccessibilityNodeInfo$AccessibilityAction r0 = android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_LEFT
            int r0 = r0.getId()
            android.view.accessibility.AccessibilityNodeInfo$AccessibilityAction r1 = android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_RIGHT
            int r1 = r1.getId()
            r2 = 8192(0x2000, float:1.148E-41)
            r3 = 4096(0x1000, float:5.74E-42)
            if (r5 == r0) goto L14
            if (r5 != r1) goto L23
        L14:
            boolean r1 = r4.isLayoutRtl()
            if (r1 != 0) goto L20
            if (r5 != r0) goto L1e
        L1c:
            r5 = r2
            goto L23
        L1e:
            r5 = r3
            goto L23
        L20:
            if (r5 != r0) goto L1c
            goto L1e
        L23:
            boolean r6 = super.performAccessibilityAction(r5, r6)
            if (r6 == 0) goto L30
            if (r5 == r2) goto L2d
            if (r5 != r3) goto L30
        L2d:
            r4.requestAccessibilityFocus()
        L30:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.PagedTileLayout.performAccessibilityAction(int, android.os.Bundle):boolean");
    }

    @Override // com.android.systemui.qs.QSPanel.QSTileLayout
    public final void removeTile(QSPanelControllerBase.TileRecord tileRecord) {
        if (this.mTiles.remove(tileRecord)) {
            forceTilesRedistribution("removing tile");
            requestLayout();
        }
    }

    @Override // com.android.systemui.qs.QSPanel.QSTileLayout
    public final void restoreInstanceState(Bundle bundle) {
        this.mPageToRestore = bundle.getInt("current_page", -1);
    }

    @Override // com.android.systemui.qs.QSPanel.QSTileLayout
    public final void saveInstanceState(Bundle bundle) {
        int i = this.mPageToRestore;
        if (i == -1) {
            boolean isLayoutRtl = isLayoutRtl();
            int i2 = this.mCurItem;
            i = isLayoutRtl ? (this.mPages.size() - 1) - i2 : i2;
        }
        bundle.putInt("current_page", i);
    }

    public final void scrollByX(int i, int i2) {
        if (i != 0) {
            this.mScroller.startScroll(getScrollX(), getScrollY(), i, 0, i2);
            postInvalidateOnAnimation();
        }
    }

    @Override // androidx.viewpager.widget.ViewPager
    public final void setCurrentItem(int i, boolean z) {
        if (isLayoutRtl()) {
            i = (this.mPages.size() - 1) - i;
        }
        super.setCurrentItem(i, z);
    }

    @Override // com.android.systemui.qs.QSPanel.QSTileLayout
    public final void setExpansion(float f, float f2) {
        this.mLastExpansion = f;
        updateSelected();
    }

    @Override // com.android.systemui.qs.QSPanel.QSTileLayout
    public final void setListening(boolean z, UiEventLogger uiEventLogger) {
        if (this.mListening == z) {
            return;
        }
        this.mListening = z;
        updateListening();
    }

    @Override // com.android.systemui.qs.QSPanel.QSTileLayout
    public final void setLogger(QSLogger qSLogger) {
        this.mLogger = qSLogger;
    }

    @Override // com.android.systemui.qs.QSPanel.QSTileLayout
    public final boolean setMaxColumns(int i) {
        this.mMaxColumns = i;
        boolean z = false;
        for (int i2 = 0; i2 < this.mPages.size(); i2++) {
            TileLayout tileLayout = (TileLayout) this.mPages.get(i2);
            tileLayout.mMaxColumns = i;
            if (tileLayout.updateColumns()) {
                forceTilesRedistribution("maxColumns in pages changed");
                z = true;
            }
        }
        return z;
    }

    @Override // com.android.systemui.qs.QSPanel.QSTileLayout
    public final boolean setMinRows(int i) {
        this.mMinRows = i;
        boolean z = false;
        for (int i2 = 0; i2 < this.mPages.size(); i2++) {
            if (((TileLayout) this.mPages.get(i2)).setMinRows(i)) {
                forceTilesRedistribution("minRows changed in page");
                z = true;
            }
        }
        return z;
    }

    @Override // com.android.systemui.qs.QSPanel.QSTileLayout
    public final void setSquishinessFraction(float f) {
        int size = this.mPages.size();
        for (int i = 0; i < size; i++) {
            ((TileLayout) this.mPages.get(i)).setSquishinessFraction(f);
        }
    }

    public final void updateListening() {
        Iterator it = this.mPages.iterator();
        while (it.hasNext()) {
            TileLayout tileLayout = (TileLayout) it.next();
            tileLayout.setListening(tileLayout.getParent() != null && this.mListening, null);
        }
    }

    @Override // com.android.systemui.qs.QSPanel.QSTileLayout
    public final boolean updateResources() {
        boolean z = false;
        for (int i = 0; i < this.mPages.size(); i++) {
            z |= ((TileLayout) this.mPages.get(i)).updateResources();
        }
        if (z) {
            forceTilesRedistribution("resources in pages changed");
            requestLayout();
        } else {
            ConstantStringsLoggerImpl constantStringsLoggerImpl = this.mLogger.$$delegate_0;
            constantStringsLoggerImpl.buffer.log(constantStringsLoggerImpl.tag, LogLevel.DEBUG, "resource in pages didn't change, tiles might be not redistributed", null);
        }
        return z;
    }

    public final void updateSelected() {
        float f = this.mLastExpansion;
        if (f <= 0.0f || f >= 1.0f) {
            boolean z = f == 1.0f;
            setImportantForAccessibility(4);
            boolean isLayoutRtl = isLayoutRtl();
            int i = this.mCurItem;
            if (isLayoutRtl) {
                i = (this.mPages.size() - 1) - i;
            }
            int i2 = 0;
            while (i2 < this.mPages.size()) {
                TileLayout tileLayout = (TileLayout) this.mPages.get(i2);
                tileLayout.setSelected(i2 == i ? z : false);
                if (tileLayout.isSelected()) {
                    for (int i3 = 0; i3 < tileLayout.mRecords.size(); i3++) {
                        QSTile qSTile = ((QSPanelControllerBase.TileRecord) tileLayout.mRecords.get(i3)).tile;
                        this.mUiEventLogger.logWithInstanceId(QSEvent.QS_TILE_VISIBLE, 0, qSTile.getMetricsSpec(), qSTile.getInstanceId());
                    }
                }
                i2++;
            }
            setImportantForAccessibility(0);
        }
    }
}

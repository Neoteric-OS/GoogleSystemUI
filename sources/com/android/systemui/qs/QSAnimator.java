package com.android.systemui.qs;

import android.animation.ValueAnimator;
import android.util.Log;
import android.util.Pair;
import android.util.SparseArray;
import android.view.View;
import com.android.systemui.haptics.qs.QSLongPressEffect;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QSPanel;
import com.android.systemui.qs.TouchAnimator;
import com.android.systemui.qs.tileimpl.HeightOverrideable;
import com.android.systemui.qs.tileimpl.QSTileViewImpl;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.concurrency.ExecutorImpl;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QSAnimator implements QSHost.Callback, TouchAnimator.Listener, View.OnLayoutChangeListener, View.OnAttachStateChangeListener {
    public TouchAnimator mAllPagesDelayedAnimator;
    public TouchAnimator mBrightnessOpacityAnimator;
    public TouchAnimator mBrightnessTranslationAnimator;
    public final DelayableExecutor mExecutor;
    public TouchAnimator mFirstPageAnimator;
    public final QSHost mHost;
    public float mLastPosition;
    public int mLastQQSTileHeight;
    public TouchAnimator mNonfirstPageAlphaAnimator;
    public int mNumQuickTiles;
    public boolean mOnKeyguard;
    public HeightExpansionAnimator mOtherFirstPageTilesHeightAnimator;
    public final PagedTileLayout mPagedLayout;
    public HeightExpansionAnimator mQQSTileHeightAnimator;
    public int mQQSTop;
    public TouchAnimator mQQSTranslationYAnimator;
    public final QSExpansionPathInterpolator mQSExpansionPathInterpolator;
    public final QSPanelController mQsPanelController;
    public final View mQsRootView;
    public final QuickQSPanelController mQuickQSPanelController;
    public final QuickQSPanel mQuickQsPanel;
    public boolean mShowCollapsedOnKeyguard;
    public TouchAnimator mTranslationXAnimator;
    public TouchAnimator mTranslationYAnimator;
    public final ArrayList mAllViews = new ArrayList();
    public final ArrayList mAnimatedQsViews = new ArrayList();
    public boolean mOnFirstPage = true;
    public int mCurrentPage = 0;
    public final SparseArray mNonFirstPageQSAnimators = new SparseArray();
    public boolean mNeedsAnimatorUpdate = false;
    public final int[] mTmpLoc1 = new int[2];
    public final int[] mTmpLoc2 = new int[2];
    public final AnonymousClass1 mNonFirstPageListener = new TouchAnimator.Listener() { // from class: com.android.systemui.qs.QSAnimator.1
        @Override // com.android.systemui.qs.TouchAnimator.Listener
        public final void onAnimationAtEnd() {
            QSAnimator.this.mQuickQsPanel.setVisibility(4);
        }

        @Override // com.android.systemui.qs.TouchAnimator.Listener
        public final void onAnimationStarted() {
            QSAnimator.this.mQuickQsPanel.setVisibility(0);
        }

        @Override // com.android.systemui.qs.TouchAnimator.Listener
        public final void onAnimationAtStart() {
        }
    };
    public final QSAnimator$$ExternalSyntheticLambda0 mUpdateAnimators = new Runnable() { // from class: com.android.systemui.qs.QSAnimator$$ExternalSyntheticLambda0
        @Override // java.lang.Runnable
        public final void run() {
            QSAnimator qSAnimator = QSAnimator.this;
            qSAnimator.updateAnimators();
            qSAnimator.setPosition(qSAnimator.mLastPosition);
        }
    };

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class HeightExpansionAnimator {
        public final ValueAnimator mAnimator;
        public final QSAnimator mListener;
        public final AnonymousClass1 mUpdateListener;
        public final List mViews = new ArrayList();

        public HeightExpansionAnimator(QSAnimator qSAnimator, int i, int i2) {
            ValueAnimator.AnimatorUpdateListener animatorUpdateListener = new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.qs.QSAnimator.HeightExpansionAnimator.1
                public float mLastT = -1.0f;

                /* JADX WARN: Multi-variable type inference failed */
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float animatedFraction = valueAnimator.getAnimatedFraction();
                    int size = ((ArrayList) HeightExpansionAnimator.this.mViews).size();
                    int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                    for (int i3 = 0; i3 < size; i3++) {
                        View view = (View) ((ArrayList) HeightExpansionAnimator.this.mViews).get(i3);
                        if (view instanceof HeightOverrideable) {
                            QSTileViewImpl qSTileViewImpl = (QSTileViewImpl) ((HeightOverrideable) view);
                            if (qSTileViewImpl.heightOverride != intValue) {
                                qSTileViewImpl.heightOverride = intValue;
                                QSLongPressEffect qSLongPressEffect = qSTileViewImpl.longPressEffect;
                                if ((qSLongPressEffect != null ? qSLongPressEffect.state : null) != QSLongPressEffect.State.RUNNING_BACKWARDS_FROM_CANCEL) {
                                    qSTileViewImpl.updateHeight();
                                }
                            }
                        } else {
                            view.setBottom(view.getTop() + intValue);
                        }
                    }
                    if (animatedFraction == 0.0f) {
                        HeightExpansionAnimator.this.mListener.onAnimationAtStart();
                    } else if (animatedFraction == 1.0f) {
                        HeightExpansionAnimator.this.mListener.onAnimationAtEnd();
                    } else {
                        float f = this.mLastT;
                        if (f <= 0.0f || f == 1.0f) {
                            HeightExpansionAnimator.this.mListener.onAnimationStarted();
                        }
                    }
                    this.mLastT = animatedFraction;
                }
            };
            this.mListener = qSAnimator;
            ValueAnimator ofInt = ValueAnimator.ofInt(i, i2);
            this.mAnimator = ofInt;
            ofInt.setRepeatCount(-1);
            ofInt.setRepeatMode(2);
            ofInt.addUpdateListener(animatorUpdateListener);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final void resetViewsHeights() {
            int size = ((ArrayList) this.mViews).size();
            for (int i = 0; i < size; i++) {
                View view = (View) ((ArrayList) this.mViews).get(i);
                if (view instanceof HeightOverrideable) {
                    QSTileViewImpl qSTileViewImpl = (QSTileViewImpl) ((HeightOverrideable) view);
                    if (qSTileViewImpl.heightOverride != -1) {
                        qSTileViewImpl.heightOverride = -1;
                        QSLongPressEffect qSLongPressEffect = qSTileViewImpl.longPressEffect;
                        if ((qSLongPressEffect != null ? qSLongPressEffect.state : null) != QSLongPressEffect.State.RUNNING_BACKWARDS_FROM_CANCEL) {
                            qSTileViewImpl.updateHeight();
                        }
                    }
                    qSTileViewImpl.updateHeight();
                } else {
                    view.setBottom(view.getMeasuredHeight() + view.getTop());
                }
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v6, types: [com.android.systemui.qs.QSAnimator$1] */
    /* JADX WARN: Type inference failed for: r0v7, types: [com.android.systemui.qs.QSAnimator$$ExternalSyntheticLambda0] */
    public QSAnimator(View view, QuickQSPanel quickQSPanel, QSPanelController qSPanelController, QuickQSPanelController quickQSPanelController, QSHost qSHost, DelayableExecutor delayableExecutor, QSExpansionPathInterpolator qSExpansionPathInterpolator) {
        this.mQsRootView = view;
        this.mQuickQsPanel = quickQSPanel;
        this.mQsPanelController = qSPanelController;
        this.mQuickQSPanelController = quickQSPanelController;
        this.mHost = qSHost;
        this.mExecutor = delayableExecutor;
        this.mQSExpansionPathInterpolator = qSExpansionPathInterpolator;
        ((QSHostAdapter) qSHost).addCallback(this);
        View view2 = qSPanelController.mView;
        if (view2 != null) {
            view2.addOnAttachStateChangeListener(this);
        }
        view.addOnLayoutChangeListener(this);
        View view3 = qSPanelController.mView;
        if (view3 != null && view3.isAttachedToWindow()) {
            onViewAttachedToWindow(null);
        }
        QSPanel.QSTileLayout tileLayout = qSPanelController.getTileLayout();
        if (tileLayout instanceof PagedTileLayout) {
            this.mPagedLayout = (PagedTileLayout) tileLayout;
        } else {
            Log.w("QSAnimator", "QS Not using page layout");
        }
        QSPanel.QSTileLayout qSTileLayout = ((QSPanel) qSPanelController.mView).mTileLayout;
        if (qSTileLayout instanceof PagedTileLayout) {
            ((PagedTileLayout) qSTileLayout).mPageListener = this;
        }
    }

    public static void getRelativePosition(int[] iArr, View view, View view2) {
        iArr[0] = view.getWidth() / 2;
        iArr[1] = 0;
        getRelativePositionInt(iArr, view, view2);
    }

    public static void getRelativePositionInt(int[] iArr, View view, View view2) {
        if (view == view2 || view == null) {
            return;
        }
        if (!view.getClass().equals(SideLabelTileLayout.class)) {
            iArr[0] = view.getLeft() + iArr[0];
            iArr[1] = view.getTop() + iArr[1];
        }
        if (!(view instanceof PagedTileLayout)) {
            iArr[0] = iArr[0] - view.getScrollX();
            iArr[1] = iArr[1] - view.getScrollY();
        }
        getRelativePositionInt(iArr, (View) view.getParent(), view2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:44:0x01be  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x01d4 A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r10v8, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r11v2, types: [java.lang.Object, java.util.List] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void addNonFirstPageAnimators(int r23) {
        /*
            Method dump skipped, instructions count: 612
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.QSAnimator.addNonFirstPageAnimators(int):void");
    }

    public final void clearAnimationState() {
        int size = this.mAllViews.size();
        this.mQuickQsPanel.setAlpha(0.0f);
        for (int i = 0; i < size; i++) {
            View view = (View) this.mAllViews.get(i);
            view.setAlpha(1.0f);
            view.setTranslationX(0.0f);
            view.setTranslationY(0.0f);
            view.setScaleY(1.0f);
            if (view instanceof SideLabelTileLayout) {
                SideLabelTileLayout sideLabelTileLayout = (SideLabelTileLayout) view;
                sideLabelTileLayout.setClipChildren(false);
                sideLabelTileLayout.setClipToPadding(false);
            }
        }
        HeightExpansionAnimator heightExpansionAnimator = this.mQQSTileHeightAnimator;
        if (heightExpansionAnimator != null) {
            heightExpansionAnimator.resetViewsHeights();
        }
        HeightExpansionAnimator heightExpansionAnimator2 = this.mOtherFirstPageTilesHeightAnimator;
        if (heightExpansionAnimator2 != null) {
            heightExpansionAnimator2.resetViewsHeights();
        }
        for (int i2 = 0; i2 < this.mNonFirstPageQSAnimators.size(); i2++) {
            ((HeightExpansionAnimator) ((Pair) this.mNonFirstPageQSAnimators.valueAt(i2)).first).resetViewsHeights();
        }
        int size2 = this.mAnimatedQsViews.size();
        for (int i3 = 0; i3 < size2; i3++) {
            ((View) this.mAnimatedQsViews.get(i3)).setVisibility(0);
        }
    }

    @Override // com.android.systemui.qs.TouchAnimator.Listener
    public final void onAnimationAtEnd() {
        this.mQuickQsPanel.setVisibility(4);
        int size = this.mAnimatedQsViews.size();
        for (int i = 0; i < size; i++) {
            ((View) this.mAnimatedQsViews.get(i)).setVisibility(0);
        }
    }

    @Override // com.android.systemui.qs.TouchAnimator.Listener
    public final void onAnimationAtStart() {
        this.mQuickQsPanel.setVisibility(0);
    }

    @Override // com.android.systemui.qs.TouchAnimator.Listener
    public final void onAnimationStarted() {
        updateQQSVisibility();
        if (this.mOnFirstPage) {
            int size = this.mAnimatedQsViews.size();
            for (int i = 0; i < size; i++) {
                ((View) this.mAnimatedQsViews.get(i)).setVisibility(4);
            }
        }
    }

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        if (i == i5 && i2 == i6 && i3 == i7 && i4 == i8) {
            return;
        }
        ((ExecutorImpl) this.mExecutor).execute(this.mUpdateAnimators);
    }

    public final void onPageChanged(int i, boolean z) {
        if (i != -1 && this.mCurrentPage != i) {
            this.mCurrentPage = i;
            if (!z && !this.mNonFirstPageQSAnimators.contains(i)) {
                addNonFirstPageAnimators(i);
            }
        }
        if (this.mOnFirstPage == z) {
            return;
        }
        if (!z) {
            clearAnimationState();
        }
        this.mOnFirstPage = z;
    }

    @Override // com.android.systemui.qs.QSHost.Callback
    public final void onTilesChanged() {
        this.mExecutor.executeDelayed(this.mUpdateAnimators, 100L);
        this.mNeedsAnimatorUpdate = true;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        updateAnimators();
        setPosition(this.mLastPosition);
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        ((QSHostAdapter) this.mHost).removeCallback(this);
    }

    public final void setPosition(float f) {
        if (this.mNeedsAnimatorUpdate) {
            updateAnimators();
        }
        if (this.mFirstPageAnimator == null) {
            return;
        }
        if (this.mOnKeyguard) {
            f = this.mShowCollapsedOnKeyguard ? 0.0f : 1.0f;
        }
        this.mLastPosition = f;
        if (this.mOnFirstPage) {
            this.mQuickQsPanel.setAlpha(1.0f);
            this.mFirstPageAnimator.setPosition(f);
            this.mTranslationYAnimator.setPosition(f);
            this.mTranslationXAnimator.setPosition(f);
            HeightExpansionAnimator heightExpansionAnimator = this.mOtherFirstPageTilesHeightAnimator;
            if (heightExpansionAnimator != null) {
                heightExpansionAnimator.mAnimator.setCurrentFraction(f);
            }
        } else {
            this.mNonfirstPageAlphaAnimator.setPosition(f);
        }
        for (int i = 0; i < this.mNonFirstPageQSAnimators.size(); i++) {
            Pair pair = (Pair) this.mNonFirstPageQSAnimators.valueAt(i);
            if (pair != null) {
                ((HeightExpansionAnimator) pair.first).mAnimator.setCurrentFraction(f);
                ((TouchAnimator) pair.second).setPosition(f);
            }
        }
        HeightExpansionAnimator heightExpansionAnimator2 = this.mQQSTileHeightAnimator;
        if (heightExpansionAnimator2 != null) {
            heightExpansionAnimator2.mAnimator.setCurrentFraction(f);
        }
        this.mQQSTranslationYAnimator.setPosition(f);
        this.mAllPagesDelayedAnimator.setPosition(f);
        TouchAnimator touchAnimator = this.mBrightnessOpacityAnimator;
        if (touchAnimator != null) {
            touchAnimator.setPosition(f);
        }
        TouchAnimator touchAnimator2 = this.mBrightnessTranslationAnimator;
        if (touchAnimator2 != null) {
            touchAnimator2.setPosition(f);
        }
    }

    public final void translateContent(View view, View view2, View view3, int i, int i2, int[] iArr, TouchAnimator.Builder builder, TouchAnimator.Builder builder2, TouchAnimator.Builder builder3) {
        getRelativePosition(iArr, view, view3);
        int i3 = iArr[0];
        int i4 = iArr[1];
        getRelativePosition(iArr, view2, view3);
        int i5 = iArr[0];
        int i6 = iArr[1];
        builder.addFloat(view, "translationX", 0.0f, (i5 - i3) - i);
        builder.addFloat(view2, "translationX", -r9, 0.0f);
        builder3.addFloat(view, "translationY", 0.0f, (i6 - i4) - i2);
        builder2.addFloat(view2, "translationY", -r12, 0.0f);
        this.mAllViews.add(view);
        this.mAllViews.add(view2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:91:0x024c, code lost:
    
        if (r13 < ((((r30.mNumQuickTiles + r0) - 1) / r0) * r0)) goto L41;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void updateAnimators() {
        /*
            Method dump skipped, instructions count: 1284
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.QSAnimator.updateAnimators():void");
    }

    public final void updateQQSVisibility() {
        this.mQuickQsPanel.setVisibility((!this.mOnKeyguard || this.mShowCollapsedOnKeyguard) ? 0 : 4);
    }
}

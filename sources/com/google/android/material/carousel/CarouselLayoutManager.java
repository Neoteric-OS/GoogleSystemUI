package com.google.android.material.carousel;

import android.content.Context;
import android.content.res.TypedArray;
import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView$SmoothScroller$ScrollVectorProvider;
import com.android.wm.shell.R;
import com.google.android.material.R$styleable;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.carousel.KeylineState;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class CarouselLayoutManager extends RecyclerView.LayoutManager implements RecyclerView$SmoothScroller$ScrollVectorProvider {
    public final MultiBrowseCarouselStrategy carouselStrategy;
    public final KeylineState currentKeylineState;
    public final DebugItemDecoration debugItemDecoration;
    int maxScroll;
    int minScroll;
    public CarouselOrientationHelper$1 orientationHelper;
    public final View.OnLayoutChangeListener recyclerViewSizeChangeListener;
    int scrollOffset;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DebugItemDecoration extends RecyclerView.ItemDecoration {
        public final List keylines;
        public final Paint linePaint;

        public DebugItemDecoration() {
            Paint paint = new Paint();
            this.linePaint = paint;
            this.keylines = Collections.unmodifiableList(new ArrayList());
            paint.setStrokeWidth(5.0f);
            paint.setColor(-65281);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public final void onDrawOver(Canvas canvas, RecyclerView recyclerView) {
            int i;
            int i2;
            int paddingLeft;
            int paddingRight;
            this.linePaint.setStrokeWidth(recyclerView.getResources().getDimension(R.dimen.m3_carousel_debug_keyline_width));
            for (KeylineState.Keyline keyline : this.keylines) {
                Paint paint = this.linePaint;
                keyline.getClass();
                paint.setColor(ColorUtils.blendARGB(-65281, 0.0f, -16776961));
                if (((CarouselLayoutManager) recyclerView.getLayoutManager()).isHorizontal()) {
                    CarouselOrientationHelper$1 carouselOrientationHelper$1 = ((CarouselLayoutManager) recyclerView.getLayoutManager()).orientationHelper;
                    switch (carouselOrientationHelper$1.$r8$classId) {
                        case 0:
                            i = 0;
                            break;
                        default:
                            i = carouselOrientationHelper$1.val$carouselLayoutManager.getPaddingTop();
                            break;
                    }
                    float f = i;
                    CarouselOrientationHelper$1 carouselOrientationHelper$12 = ((CarouselLayoutManager) recyclerView.getLayoutManager()).orientationHelper;
                    switch (carouselOrientationHelper$12.$r8$classId) {
                        case 0:
                            i2 = carouselOrientationHelper$12.val$carouselLayoutManager.mHeight;
                            break;
                        default:
                            CarouselLayoutManager carouselLayoutManager = carouselOrientationHelper$12.val$carouselLayoutManager;
                            i2 = carouselLayoutManager.mHeight - carouselLayoutManager.getPaddingBottom();
                            break;
                    }
                    canvas.drawLine(0.0f, f, 0.0f, i2, this.linePaint);
                } else {
                    CarouselOrientationHelper$1 carouselOrientationHelper$13 = ((CarouselLayoutManager) recyclerView.getLayoutManager()).orientationHelper;
                    switch (carouselOrientationHelper$13.$r8$classId) {
                        case 0:
                            paddingLeft = carouselOrientationHelper$13.val$carouselLayoutManager.getPaddingLeft();
                            break;
                        default:
                            paddingLeft = 0;
                            break;
                    }
                    float f2 = paddingLeft;
                    CarouselOrientationHelper$1 carouselOrientationHelper$14 = ((CarouselLayoutManager) recyclerView.getLayoutManager()).orientationHelper;
                    switch (carouselOrientationHelper$14.$r8$classId) {
                        case 0:
                            CarouselLayoutManager carouselLayoutManager2 = carouselOrientationHelper$14.val$carouselLayoutManager;
                            paddingRight = carouselLayoutManager2.mWidth - carouselLayoutManager2.getPaddingRight();
                            break;
                        default:
                            paddingRight = carouselOrientationHelper$14.val$carouselLayoutManager.mWidth;
                            break;
                    }
                    canvas.drawLine(f2, 0.0f, paddingRight, 0.0f, this.linePaint);
                }
            }
        }
    }

    public CarouselLayoutManager() {
        new MultiBrowseCarouselStrategy();
        new DebugItemDecoration();
        this.recyclerViewSizeChangeListener = new View.OnLayoutChangeListener() { // from class: com.google.android.material.carousel.CarouselLayoutManager$$ExternalSyntheticLambda0
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                final CarouselLayoutManager carouselLayoutManager = CarouselLayoutManager.this;
                if (i == i5 && i2 == i6 && i3 == i7 && i4 == i8) {
                    return;
                }
                view.post(new Runnable() { // from class: com.google.android.material.carousel.CarouselLayoutManager$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        CarouselLayoutManager.this.requestLayout();
                    }
                });
            }
        };
        requestLayout();
        setOrientation(0);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final boolean canScrollHorizontally() {
        return isHorizontal();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final boolean canScrollVertically() {
        return !isHorizontal();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final int computeHorizontalScrollExtent(RecyclerView.State state) {
        getChildCount();
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final int computeHorizontalScrollOffset(RecyclerView.State state) {
        return this.scrollOffset;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final int computeHorizontalScrollRange(RecyclerView.State state) {
        return this.maxScroll - this.minScroll;
    }

    @Override // androidx.recyclerview.widget.RecyclerView$SmoothScroller$ScrollVectorProvider
    public final PointF computeScrollVectorForPosition(int i) {
        return null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final int computeVerticalScrollExtent(RecyclerView.State state) {
        getChildCount();
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final int computeVerticalScrollOffset(RecyclerView.State state) {
        return this.scrollOffset;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final int computeVerticalScrollRange(RecyclerView.State state) {
        return this.maxScroll - this.minScroll;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(-2, -2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void getDecoratedBoundsWithMargins(Rect rect, View view) {
        super.getDecoratedBoundsWithMargins(rect, view);
        float centerY = rect.centerY();
        if (isHorizontal()) {
            centerY = rect.centerX();
        }
        List list = this.currentKeylineState.keylines;
        float f = Float.MAX_VALUE;
        int i = -1;
        int i2 = -1;
        int i3 = -1;
        int i4 = -1;
        float f2 = -3.4028235E38f;
        float f3 = Float.MAX_VALUE;
        float f4 = Float.MAX_VALUE;
        for (int i5 = 0; i5 < list.size(); i5++) {
            ((KeylineState.Keyline) list.get(i5)).getClass();
            float abs = Math.abs(0.0f - centerY);
            if (0.0f <= centerY && abs <= f) {
                i = i5;
                f = abs;
            }
            if (0.0f > centerY && abs <= f3) {
                i3 = i5;
                f3 = abs;
            }
            if (0.0f <= f4) {
                i2 = i5;
                f4 = 0.0f;
            }
            if (0.0f > f2) {
                i4 = i5;
                f2 = 0.0f;
            }
        }
        if (i == -1) {
            i = i2;
        }
        if (i3 == -1) {
            i3 = i4;
        }
        KeylineState.Keyline keyline = (KeylineState.Keyline) list.get(i);
        KeylineState.Keyline keyline2 = (KeylineState.Keyline) list.get(i3);
        keyline.getClass();
        keyline2.getClass();
        if (0.0f > 0.0f) {
            throw new IllegalArgumentException();
        }
        float lerp = AnimationUtils.lerp(0.0f, 0.0f, 0.0f, 0.0f, centerY);
        float width = isHorizontal() ? (rect.width() - lerp) / 2.0f : 0.0f;
        float height = isHorizontal() ? 0.0f : (rect.height() - lerp) / 2.0f;
        rect.set((int) (rect.left + width), (int) (rect.top + height), (int) (rect.right - width), (int) (rect.bottom - height));
    }

    public final boolean isHorizontal() {
        return this.orientationHelper.orientation == 0;
    }

    public final boolean isLayoutRtl() {
        return isHorizontal() && this.mRecyclerView.getLayoutDirection() == 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void onAttachedToWindow(RecyclerView recyclerView) {
        requestLayout();
        recyclerView.addOnLayoutChangeListener(this.recyclerViewSizeChangeListener);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void onDetachedFromWindow(RecyclerView recyclerView) {
        recyclerView.removeOnLayoutChangeListener(this.recyclerViewSizeChangeListener);
    }

    /* JADX WARN: Code restructure failed: missing block: B:51:0x002e, code lost:
    
        if (r10 == 1) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0037, code lost:
    
        if (isLayoutRtl() != false) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x003a, code lost:
    
        if (r10 == 1) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0043, code lost:
    
        if (isLayoutRtl() != false) goto L30;
     */
    /* JADX WARN: Removed duplicated region for block: B:20:0x004a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x004b  */
    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.view.View onFocusSearchFailed(android.view.View r7, int r8, androidx.recyclerview.widget.RecyclerView.Recycler r9, androidx.recyclerview.widget.RecyclerView.State r10) {
        /*
            Method dump skipped, instructions count: 230
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.carousel.CarouselLayoutManager.onFocusSearchFailed(android.view.View, int, androidx.recyclerview.widget.RecyclerView$Recycler, androidx.recyclerview.widget.RecyclerView$State):android.view.View");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        if (getChildCount() > 0) {
            accessibilityEvent.setFromIndex(RecyclerView.LayoutManager.getPosition(getChildAt(0)));
            accessibilityEvent.setToIndex(RecyclerView.LayoutManager.getPosition(getChildAt(getChildCount() - 1)));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void onItemsAdded(int i, int i2) {
        getItemCount();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void onItemsRemoved(int i, int i2) {
        getItemCount();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (state.getItemCount() > 0) {
            if ((isHorizontal() ? this.mWidth : this.mHeight) > 0.0f) {
                isLayoutRtl();
                View view = recycler.tryGetViewHolderForPositionByDeadline(Long.MAX_VALUE, 0).itemView;
                throw new IllegalStateException("All children of a RecyclerView using CarouselLayoutManager must use MaskableFrameLayout as their root ViewGroup.");
            }
        }
        removeAndRecycleAllViews(recycler);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void onLayoutCompleted(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return;
        }
        RecyclerView.LayoutManager.getPosition(getChildAt(0));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final boolean requestChildRectangleOnScreen(RecyclerView recyclerView, View view, Rect rect, boolean z, boolean z2) {
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final int scrollHorizontallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (!isHorizontal() || getChildCount() == 0 || i == 0) {
            return 0;
        }
        View view = recycler.tryGetViewHolderForPositionByDeadline(Long.MAX_VALUE, 0).itemView;
        throw new IllegalStateException("All children of a RecyclerView using CarouselLayoutManager must use MaskableFrameLayout as their root ViewGroup.");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final int scrollVerticallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (!canScrollVertically() || getChildCount() == 0 || i == 0) {
            return 0;
        }
        View view = recycler.tryGetViewHolderForPositionByDeadline(Long.MAX_VALUE, 0).itemView;
        throw new IllegalStateException("All children of a RecyclerView using CarouselLayoutManager must use MaskableFrameLayout as their root ViewGroup.");
    }

    public final void setOrientation(int i) {
        CarouselOrientationHelper$1 carouselOrientationHelper$1;
        if (i != 0 && i != 1) {
            throw new IllegalArgumentException(AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "invalid orientation:"));
        }
        assertNotInLayoutOrScroll(null);
        CarouselOrientationHelper$1 carouselOrientationHelper$12 = this.orientationHelper;
        if (carouselOrientationHelper$12 == null || i != carouselOrientationHelper$12.orientation) {
            if (i == 0) {
                carouselOrientationHelper$1 = new CarouselOrientationHelper$1(this, 1);
            } else {
                if (i != 1) {
                    throw new IllegalArgumentException("invalid orientation");
                }
                carouselOrientationHelper$1 = new CarouselOrientationHelper$1(this, 0);
            }
            this.orientationHelper = carouselOrientationHelper$1;
            requestLayout();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void smoothScrollToPosition(RecyclerView recyclerView, int i) {
        LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext()) { // from class: com.google.android.material.carousel.CarouselLayoutManager.1
            @Override // androidx.recyclerview.widget.LinearSmoothScroller
            public final int calculateDxToMakeVisible(View view, int i2) {
                CarouselLayoutManager.this.getClass();
                return 0;
            }

            @Override // androidx.recyclerview.widget.LinearSmoothScroller
            public final int calculateDyToMakeVisible(View view, int i2) {
                CarouselLayoutManager.this.getClass();
                return 0;
            }

            @Override // androidx.recyclerview.widget.LinearSmoothScroller
            public final PointF computeScrollVectorForPosition(int i2) {
                CarouselLayoutManager.this.getClass();
                return null;
            }
        };
        linearSmoothScroller.mTargetPosition = i;
        startSmoothScroll(linearSmoothScroller);
    }

    public CarouselLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        new DebugItemDecoration();
        this.recyclerViewSizeChangeListener = new View.OnLayoutChangeListener() { // from class: com.google.android.material.carousel.CarouselLayoutManager$$ExternalSyntheticLambda0
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i3, int i22, int i32, int i4, int i5, int i6, int i7, int i8) {
                final CarouselLayoutManager carouselLayoutManager = CarouselLayoutManager.this;
                if (i3 == i5 && i22 == i6 && i32 == i7 && i4 == i8) {
                    return;
                }
                view.post(new Runnable() { // from class: com.google.android.material.carousel.CarouselLayoutManager$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        CarouselLayoutManager.this.requestLayout();
                    }
                });
            }
        };
        new MultiBrowseCarouselStrategy();
        requestLayout();
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.Carousel);
            obtainStyledAttributes.getInt(0, 0);
            requestLayout();
            setOrientation(obtainStyledAttributes.getInt(0, 0));
            obtainStyledAttributes.recycle();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void scrollToPosition(int i) {
    }
}

package com.android.systemui.media.controls.ui.view;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import com.android.wm.shell.shared.animation.PhysicsAnimator;
import com.android.wm.shell.shared.animation.PhysicsAnimatorKt;
import java.util.WeakHashMap;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaScrollView extends HorizontalScrollView {
    public float animationTargetX;
    public ViewGroup contentContainer;
    public MediaCarouselScrollHandler$touchListener$1 touchListener;

    public MediaScrollView(Context context) {
        this(context, null, 0, 6, null);
    }

    public final void cancelCurrentScroll() {
        long uptimeMillis = SystemClock.uptimeMillis();
        MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
        obtain.setSource(4098);
        super.onTouchEvent(obtain);
        obtain.recycle();
    }

    public final float getContentTranslation() {
        ViewGroup viewGroup = this.contentContainer;
        if (viewGroup == null) {
            viewGroup = null;
        }
        WeakHashMap weakHashMap = PhysicsAnimatorKt.animators;
        Function2 function2 = PhysicsAnimator.onAnimatorCreated;
        if (PhysicsAnimator.Companion.getInstance(viewGroup).isRunning()) {
            return this.animationTargetX;
        }
        ViewGroup viewGroup2 = this.contentContainer;
        return (viewGroup2 != null ? viewGroup2 : null).getTranslationX();
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.contentContainer = (ViewGroup) getChildAt(0);
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        MediaCarouselScrollHandler$touchListener$1 mediaCarouselScrollHandler$touchListener$1 = this.touchListener;
        return super.onInterceptTouchEvent(motionEvent) || (mediaCarouselScrollHandler$touchListener$1 != null ? mediaCarouselScrollHandler$touchListener$1.onInterceptTouchEvent(motionEvent) : false);
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (!isLaidOut() && isLayoutRtl()) {
            int scrollX = getScrollX();
            if (isLayoutRtl()) {
                ViewGroup viewGroup = this.contentContainer;
                if (viewGroup == null) {
                    viewGroup = null;
                }
                scrollX = (viewGroup.getWidth() - getWidth()) - scrollX;
            }
            ((HorizontalScrollView) this).mScrollX = scrollX;
        }
        super.onLayout(z, i, i2, i3, i4);
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        MediaCarouselScrollHandler$touchListener$1 mediaCarouselScrollHandler$touchListener$1 = this.touchListener;
        return super.onTouchEvent(motionEvent) || (mediaCarouselScrollHandler$touchListener$1 != null ? mediaCarouselScrollHandler$touchListener$1.onTouchEvent(motionEvent) : false);
    }

    @Override // android.view.View
    public final boolean overScrollBy(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z) {
        if (getContentTranslation() == 0.0f) {
            return super.overScrollBy(i, i2, i3, i4, i5, i6, i7, i8, z);
        }
        return false;
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    public final void scrollTo(int i, int i2) {
        int i3 = ((HorizontalScrollView) this).mScrollX;
        if (i3 == i && ((HorizontalScrollView) this).mScrollY == i2) {
            return;
        }
        int i4 = ((HorizontalScrollView) this).mScrollY;
        ((HorizontalScrollView) this).mScrollX = i;
        ((HorizontalScrollView) this).mScrollY = i2;
        invalidateParentCaches();
        onScrollChanged(((HorizontalScrollView) this).mScrollX, ((HorizontalScrollView) this).mScrollY, i3, i4);
        if (awakenScrollBars()) {
            return;
        }
        postInvalidateOnAnimation();
    }

    public MediaScrollView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    public /* synthetic */ MediaScrollView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    public MediaScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}

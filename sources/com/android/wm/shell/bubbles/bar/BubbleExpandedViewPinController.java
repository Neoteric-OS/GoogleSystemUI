package com.android.wm.shell.bubbles.bar;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.core.animation.ObjectAnimator;
import com.android.wm.shell.R;
import com.android.wm.shell.bubbles.BubblePositioner;
import com.android.wm.shell.bubbles.bar.BubbleBarLayerView;
import com.android.wm.shell.bubbles.bar.BubbleExpandedViewPinController;
import com.android.wm.shell.shared.bubbles.BaseBubblePinController$addEndAction$1;
import com.android.wm.shell.shared.bubbles.BaseBubblePinController$animateOut$1;
import com.android.wm.shell.shared.bubbles.BubbleBarLocation;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BubbleExpandedViewPinController {
    public final BubbleBarLayerView container;
    public final Context context;
    public RectF dismissZone;
    public ObjectAnimator dropTargetAnimator;
    public View dropTargetView;
    public boolean initialLocationOnLeft;
    public BubbleBarLayerView.AnonymousClass1 listener;
    public boolean onLeft;
    public final BubblePositioner positioner;
    public int screenCenterX;
    public final Function0 screenSizeProvider;
    public boolean stuckToDismissTarget;
    public final Lazy tempRect$delegate = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.NONE, new Function0() { // from class: com.android.wm.shell.bubbles.bar.BubbleExpandedViewPinController$tempRect$2
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return new Rect();
        }
    });
    public final Lazy exclRectWidth$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.wm.shell.bubbles.bar.BubbleExpandedViewPinController$exclRectWidth$2
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return Float.valueOf(BubbleExpandedViewPinController.this.context.getResources().getDimension(R.dimen.bubble_bar_dismiss_zone_width));
        }
    });
    public final Lazy exclRectHeight$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.wm.shell.bubbles.bar.BubbleExpandedViewPinController$exclRectHeight$2
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return Float.valueOf(BubbleExpandedViewPinController.this.context.getResources().getDimension(R.dimen.bubble_bar_dismiss_zone_height));
        }
    });

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wm.shell.bubbles.bar.BubbleExpandedViewPinController$1, reason: invalid class name */
    final class AnonymousClass1 extends Lambda implements Function0 {
        final /* synthetic */ BubblePositioner $positioner;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(BubblePositioner bubblePositioner) {
            super(0);
            this.$positioner = bubblePositioner;
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            Rect rect = this.$positioner.mPositionRect;
            return new Point(rect.width(), rect.height());
        }
    }

    public BubbleExpandedViewPinController(Context context, BubbleBarLayerView bubbleBarLayerView, BubblePositioner bubblePositioner) {
        this.screenSizeProvider = new AnonymousClass1(bubblePositioner);
        this.context = context;
        this.container = bubbleBarLayerView;
        this.positioner = bubblePositioner;
    }

    public final void animateIn(View view) {
        ObjectAnimator objectAnimator = this.dropTargetAnimator;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.ALPHA, 1.0f);
        ofFloat.m717setDuration(150L);
        ofFloat.addListener(new BaseBubblePinController$addEndAction$1(new Runnable() { // from class: com.android.wm.shell.shared.bubbles.BaseBubblePinController$animateIn$1
            @Override // java.lang.Runnable
            public final void run() {
                BubbleExpandedViewPinController.this.dropTargetAnimator = null;
            }
        }));
        this.dropTargetAnimator = ofFloat;
        ofFloat.start();
    }

    public final void animateOut(View view, Runnable runnable) {
        ObjectAnimator objectAnimator = this.dropTargetAnimator;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.ALPHA, 0.0f);
        ofFloat.m717setDuration(100L);
        ofFloat.addListener(new BaseBubblePinController$addEndAction$1(new BaseBubblePinController$animateOut$1(runnable, this)));
        this.dropTargetAnimator = ofFloat;
        ofFloat.start();
    }

    public final void onDragEnd() {
        View view = this.dropTargetView;
        if (view != null) {
            animateOut(view, new BaseBubblePinController$animateOut$1(this, view));
        }
        this.dismissZone = null;
        BubbleBarLayerView.AnonymousClass1 anonymousClass1 = this.listener;
        if (anonymousClass1 != null) {
            BubbleBarLayerView.this.mBubbleController.setBubbleBarLocation(this.onLeft ? BubbleBarLocation.LEFT : BubbleBarLocation.RIGHT);
        }
    }

    public final void updateLocation(BubbleBarLocation bubbleBarLocation) {
        View view = this.dropTargetView;
        if (view == null) {
            return;
        }
        boolean isLayoutRtl = view.isLayoutRtl();
        if (bubbleBarLocation != BubbleBarLocation.DEFAULT) {
            isLayoutRtl = bubbleBarLocation == BubbleBarLocation.LEFT;
        }
        Lazy lazy = this.tempRect$delegate;
        this.positioner.getBubbleBarExpandedViewBounds(isLayoutRtl, false, (Rect) lazy.getValue());
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.widget.FrameLayout.LayoutParams");
        }
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) layoutParams;
        layoutParams2.width = ((Rect) lazy.getValue()).width();
        layoutParams2.height = ((Rect) lazy.getValue()).height();
        view.setLayoutParams(layoutParams2);
        view.setX(((Rect) lazy.getValue()).left);
        view.setY(((Rect) lazy.getValue()).top);
    }
}

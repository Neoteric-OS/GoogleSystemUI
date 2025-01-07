package com.android.wm.shell.bubbles.bar;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.dynamicanimation.animation.DynamicAnimation;
import com.android.wm.shell.bubbles.BubbleEducationController;
import com.android.wm.shell.shared.animation.PhysicsAnimator;
import com.android.wm.shell.shared.bubbles.BubblePopupDrawable;
import com.android.wm.shell.shared.bubbles.BubblePopupView;
import com.android.wm.shell.taskview.TaskView;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BubbleEducationViewController {
    public PhysicsAnimator animator;
    public final Context context;
    public BubblePopupView educationView;
    public final BubbleBarLayerView$$ExternalSyntheticLambda1 listener;
    public ViewGroup rootView;
    public final Lazy springConfig$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.wm.shell.bubbles.bar.BubbleEducationViewController$springConfig$2
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return new PhysicsAnimator.SpringConfig(1500.0f, 0.75f);
        }
    });
    public final Lazy scrimView$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.wm.shell.bubbles.bar.BubbleEducationViewController$scrimView$2
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            View view = new View(BubbleEducationViewController.this.context);
            BubbleEducationViewController bubbleEducationViewController = BubbleEducationViewController.this;
            view.setImportantForAccessibility(2);
            view.setOnClickListener(new BubbleEducationViewController$scrimView$2$1$1(0, bubbleEducationViewController));
            return view;
        }
    });
    public final Lazy controller$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.wm.shell.bubbles.bar.BubbleEducationViewController$controller$2
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return new BubbleEducationController(BubbleEducationViewController.this.context);
        }
    });

    public BubbleEducationViewController(Context context, BubbleBarLayerView$$ExternalSyntheticLambda1 bubbleBarLayerView$$ExternalSyntheticLambda1) {
        this.context = context;
        this.listener = bubbleBarLayerView$$ExternalSyntheticLambda1;
    }

    public static void hideEducation$default(BubbleEducationViewController bubbleEducationViewController, boolean z) {
        TaskView taskView;
        BubbleEducationViewController$hideEducation$1 bubbleEducationViewController$hideEducation$1 = BubbleEducationViewController$hideEducation$1.INSTANCE;
        bubbleEducationViewController.getClass();
        if (z) {
            bubbleEducationViewController.animateTransition(new BubbleEducationViewController$hideEducation$3(bubbleEducationViewController, bubbleEducationViewController$hideEducation$1), false);
            return;
        }
        ViewGroup viewGroup = bubbleEducationViewController.rootView;
        if (viewGroup != null) {
            viewGroup.removeView(bubbleEducationViewController.educationView);
        }
        ViewGroup viewGroup2 = bubbleEducationViewController.rootView;
        if (viewGroup2 != null) {
            viewGroup2.removeView((View) bubbleEducationViewController.scrimView$delegate.getValue());
        }
        bubbleEducationViewController.educationView = null;
        bubbleEducationViewController.rootView = null;
        bubbleEducationViewController.animator = null;
        bubbleEducationViewController$hideEducation$1.invoke();
        BubbleBarExpandedView bubbleBarExpandedView = bubbleEducationViewController.listener.f$0.mExpandedView;
        if (bubbleBarExpandedView == null || (taskView = bubbleBarExpandedView.mTaskView) == null || bubbleBarExpandedView.mLayerBoundsSupplier == null) {
            return;
        }
        taskView.mObscuredTouchRegion = null;
    }

    public final void animateTransition(Function0 function0, boolean z) {
        Unit unit;
        PhysicsAnimator physicsAnimator = this.animator;
        if (physicsAnimator != null) {
            physicsAnimator.spring(DynamicAnimation.ALPHA, z ? 1.0f : 0.0f);
            physicsAnimator.spring(DynamicAnimation.SCALE_X, z ? 1.0f : 0.5f);
            physicsAnimator.spring(DynamicAnimation.SCALE_Y, z ? 1.0f : 0.5f);
            physicsAnimator.withEndActions(function0);
            physicsAnimator.start();
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            function0.invoke();
        }
    }

    public final BubblePopupView createEducationView(int i, ViewGroup viewGroup) {
        BubblePopupView bubblePopupView = (BubblePopupView) LayoutInflater.from(this.context).inflate(i, viewGroup, false);
        TypedArray obtainStyledAttributes = bubblePopupView.getContext().obtainStyledAttributes(new int[]{R.^attr-private.materialColorSurface, R.attr.dialogCornerRadius});
        Resources resources = bubblePopupView.getContext().getResources();
        int color = obtainStyledAttributes.getColor(0, -1);
        BubblePopupDrawable.Config config = new BubblePopupDrawable.Config(obtainStyledAttributes.getDimension(1, 0.0f), resources.getDimension(com.android.wm.shell.R.dimen.bubble_popup_arrow_width), resources.getDimension(com.android.wm.shell.R.dimen.bubble_popup_arrow_height), resources.getDimension(com.android.wm.shell.R.dimen.bubble_popup_arrow_corner_radius), color, resources.getDimensionPixelSize(com.android.wm.shell.R.dimen.bubble_popup_padding));
        obtainStyledAttributes.recycle();
        BubblePopupDrawable bubblePopupDrawable = new BubblePopupDrawable(config);
        bubblePopupView.popupDrawable = bubblePopupDrawable;
        bubblePopupView.setBackground(bubblePopupDrawable);
        bubblePopupView.forceLayout();
        bubblePopupView.setAlpha(0.0f);
        bubblePopupView.setScaleX(0.5f);
        bubblePopupView.setScaleY(0.5f);
        return bubblePopupView;
    }
}

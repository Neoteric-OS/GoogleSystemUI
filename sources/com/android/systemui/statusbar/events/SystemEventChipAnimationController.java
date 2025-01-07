package com.android.systemui.statusbar.events;

import android.content.Context;
import android.graphics.Rect;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.FrameLayout;
import androidx.core.animation.Animator;
import androidx.core.animation.AnimatorListenerAdapter;
import androidx.core.animation.AnimatorSet;
import androidx.core.animation.ValueAnimator;
import com.android.systemui.statusbar.phone.StatusBarContentInsetsProvider;
import com.android.systemui.statusbar.window.StatusBarWindowControllerImpl;
import com.android.systemui.util.animation.AnimationUtil$Companion;
import com.android.wm.shell.R;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SystemEventChipAnimationController implements SystemStatusAnimationCallback {
    public FrameLayout animationWindowView;
    public final int chipMinWidth;
    public final StatusBarContentInsetsProvider contentInsetsProvider;
    public final Context context;
    public BackgroundAnimatableView currentAnimatedView;
    public final int dotSize;
    public boolean initialized;
    public final StatusBarWindowControllerImpl statusBarWindowController;
    public ContextThemeWrapper themedContext;
    public int animationDirection = 1;
    public Rect chipBounds = new Rect();
    public final Rect animRect = new Rect();

    public SystemEventChipAnimationController(Context context, StatusBarWindowControllerImpl statusBarWindowControllerImpl, StatusBarContentInsetsProvider statusBarContentInsetsProvider) {
        this.context = context;
        this.statusBarWindowController = statusBarWindowControllerImpl;
        this.contentInsetsProvider = statusBarContentInsetsProvider;
        this.chipMinWidth = context.getResources().getDimensionPixelSize(R.dimen.ongoing_appops_chip_min_animation_width);
        this.dotSize = context.getResources().getDimensionPixelSize(R.dimen.ongoing_appops_dot_diameter);
    }

    public static final void access$updateAnimatedViewBoundsHeight(SystemEventChipAnimationController systemEventChipAnimationController, int i, int i2) {
        Rect rect = systemEventChipAnimationController.animRect;
        float f = i / 2;
        rect.set(rect.left, i2 - MathKt.roundToInt(f), systemEventChipAnimationController.animRect.right, MathKt.roundToInt(f) + i2);
        systemEventChipAnimationController.updateCurrentAnimatedView();
    }

    public static final void access$updateAnimatedViewBoundsWidth(SystemEventChipAnimationController systemEventChipAnimationController, int i) {
        if (systemEventChipAnimationController.animationDirection == 1) {
            Rect rect = systemEventChipAnimationController.animRect;
            int i2 = systemEventChipAnimationController.chipBounds.right;
            rect.set(i2 - i, rect.top, i2, rect.bottom);
        } else {
            Rect rect2 = systemEventChipAnimationController.animRect;
            int i3 = systemEventChipAnimationController.chipBounds.left;
            rect2.set(i3, rect2.top, i + i3, rect2.bottom);
        }
        systemEventChipAnimationController.updateCurrentAnimatedView();
    }

    @Override // com.android.systemui.statusbar.events.SystemStatusAnimationCallback
    public final Animator onSystemEventAnimationBegin() {
        this.animRect.set(this.chipBounds);
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat.setStartDelay(AnimationUtil$Companion.getFrames(7));
        ofFloat.setDuration(AnimationUtil$Companion.getFrames(5));
        ofFloat.setInterpolator(null);
        ofFloat.addUpdateListener(new SystemEventChipAnimationController$onSystemEventAnimationBegin$moveIn$1$1(this, ofFloat, 7));
        BackgroundAnimatableView backgroundAnimatableView = this.currentAnimatedView;
        View contentView = backgroundAnimatableView != null ? backgroundAnimatableView.getContentView() : null;
        if (contentView != null) {
            contentView.setAlpha(0.0f);
        }
        ValueAnimator ofFloat2 = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat2.setStartDelay(AnimationUtil$Companion.getFrames(10));
        ofFloat2.setDuration(AnimationUtil$Companion.getFrames(10));
        ofFloat2.setInterpolator(null);
        ofFloat2.addUpdateListener(new SystemEventChipAnimationController$onSystemEventAnimationBegin$moveIn$1$1(this, ofFloat2, 8));
        ValueAnimator ofInt = ValueAnimator.ofInt(this.chipMinWidth, this.chipBounds.width());
        ofInt.setStartDelay(AnimationUtil$Companion.getFrames(7));
        ofInt.setDuration(AnimationUtil$Companion.getFrames(23));
        ofInt.setInterpolator(SystemStatusAnimationSchedulerKt.STATUS_BAR_X_MOVE_IN);
        ofInt.addUpdateListener(new SystemEventChipAnimationController$onSystemEventAnimationBegin$moveIn$1$1(this, ofInt, 0));
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ofFloat, ofFloat2, ofInt);
        return animatorSet;
    }

    @Override // com.android.systemui.statusbar.events.SystemStatusAnimationCallback
    public final Animator onSystemEventAnimationFinish(boolean z) {
        AnimatorSet animatorSet;
        int i = 3;
        final int i2 = 1;
        final int i3 = 0;
        int i4 = 5;
        int i5 = 6;
        int i6 = 2;
        this.animRect.set(this.chipBounds);
        int i7 = this.chipMinWidth;
        if (z) {
            ValueAnimator ofInt = ValueAnimator.ofInt(this.chipBounds.width(), i7);
            ofInt.setDuration(AnimationUtil$Companion.getFrames(9));
            ofInt.setInterpolator(SystemStatusAnimationSchedulerKt.STATUS_CHIP_WIDTH_TO_DOT_KEYFRAME_1);
            ofInt.addUpdateListener(new SystemEventChipAnimationController$onSystemEventAnimationBegin$moveIn$1$1(this, ofInt, i4));
            int i8 = this.dotSize;
            ValueAnimator ofInt2 = ValueAnimator.ofInt(i7, i8);
            ofInt2.setStartDelay(AnimationUtil$Companion.getFrames(9));
            ofInt2.setDuration(AnimationUtil$Companion.getFrames(20));
            ofInt2.setInterpolator(SystemStatusAnimationSchedulerKt.STATUS_CHIP_WIDTH_TO_DOT_KEYFRAME_2);
            ofInt2.addUpdateListener(new SystemEventChipAnimationController$onSystemEventAnimationBegin$moveIn$1$1(this, ofInt2, i5));
            int i9 = i8 * 2;
            Rect rect = this.chipBounds;
            final int height = (rect.height() / 2) + rect.top;
            final ValueAnimator ofInt3 = ValueAnimator.ofInt(this.chipBounds.height(), i9);
            ofInt3.setStartDelay(AnimationUtil$Companion.getFrames(8));
            ofInt3.setDuration(AnimationUtil$Companion.getFrames(6));
            ofInt3.setInterpolator(SystemStatusAnimationSchedulerKt.STATUS_CHIP_HEIGHT_TO_DOT_KEYFRAME_1);
            ofInt3.addUpdateListener(new Animator.AnimatorUpdateListener(this) { // from class: com.android.systemui.statusbar.events.SystemEventChipAnimationController$createMoveOutAnimationForDot$height1$1$1
                public final /* synthetic */ SystemEventChipAnimationController this$0;

                {
                    this.this$0 = this;
                }

                @Override // androidx.core.animation.Animator.AnimatorUpdateListener
                public final void onAnimationUpdate(Animator animator) {
                    switch (i3) {
                        case 0:
                            SystemEventChipAnimationController.access$updateAnimatedViewBoundsHeight(this.this$0, ((Integer) ofInt3.getAnimatedValue()).intValue(), height);
                            break;
                        default:
                            SystemEventChipAnimationController.access$updateAnimatedViewBoundsHeight(this.this$0, ((Integer) ofInt3.getAnimatedValue()).intValue(), height);
                            break;
                    }
                }
            });
            final ValueAnimator ofInt4 = ValueAnimator.ofInt(i9, i8);
            ofInt4.setStartDelay(AnimationUtil$Companion.getFrames(14));
            ofInt4.setDuration(AnimationUtil$Companion.getFrames(15));
            ofInt4.setInterpolator(SystemStatusAnimationSchedulerKt.STATUS_CHIP_HEIGHT_TO_DOT_KEYFRAME_2);
            ofInt4.addUpdateListener(new Animator.AnimatorUpdateListener(this) { // from class: com.android.systemui.statusbar.events.SystemEventChipAnimationController$createMoveOutAnimationForDot$height1$1$1
                public final /* synthetic */ SystemEventChipAnimationController this$0;

                {
                    this.this$0 = this;
                }

                @Override // androidx.core.animation.Animator.AnimatorUpdateListener
                public final void onAnimationUpdate(Animator animator) {
                    switch (i2) {
                        case 0:
                            SystemEventChipAnimationController.access$updateAnimatedViewBoundsHeight(this.this$0, ((Integer) ofInt4.getAnimatedValue()).intValue(), height);
                            break;
                        default:
                            SystemEventChipAnimationController.access$updateAnimatedViewBoundsHeight(this.this$0, ((Integer) ofInt4.getAnimatedValue()).intValue(), height);
                            break;
                    }
                }
            });
            ValueAnimator ofInt5 = ValueAnimator.ofInt(0, i8);
            ofInt5.setStartDelay(AnimationUtil$Companion.getFrames(3));
            ofInt5.setDuration(AnimationUtil$Companion.getFrames(11));
            ofInt5.setInterpolator(SystemStatusAnimationSchedulerKt.STATUS_CHIP_MOVE_TO_DOT);
            ofInt5.addUpdateListener(new SystemEventChipAnimationController$onSystemEventAnimationBegin$moveIn$1$1(this, ofInt5, 4));
            animatorSet = new AnimatorSet();
            animatorSet.playTogether(ofInt, ofInt2, ofInt3, ofInt4, ofInt5);
        } else {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(1.0f, 0.0f);
            ofFloat.setStartDelay(AnimationUtil$Companion.getFrames(6));
            ofFloat.setDuration(AnimationUtil$Companion.getFrames(6));
            ofFloat.setInterpolator(null);
            ofFloat.addUpdateListener(new SystemEventChipAnimationController$onSystemEventAnimationBegin$moveIn$1$1(this, ofFloat, i2));
            ValueAnimator ofFloat2 = ValueAnimator.ofFloat(1.0f, 0.0f);
            ofFloat2.setDuration(AnimationUtil$Companion.getFrames(5));
            ofFloat2.setInterpolator(null);
            ofFloat2.addUpdateListener(new SystemEventChipAnimationController$onSystemEventAnimationBegin$moveIn$1$1(this, ofFloat2, i6));
            ValueAnimator ofInt6 = ValueAnimator.ofInt(this.chipBounds.width(), i7);
            ofInt6.setDuration(AnimationUtil$Companion.getFrames(23));
            ofInt6.setInterpolator(SystemStatusAnimationSchedulerKt.STATUS_BAR_X_MOVE_OUT);
            ofInt6.addUpdateListener(new SystemEventChipAnimationController$onSystemEventAnimationBegin$moveIn$1$1(this, ofInt6, i));
            animatorSet = new AnimatorSet();
            animatorSet.playTogether(ofFloat, ofFloat2, ofInt6);
        }
        animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.statusbar.events.SystemEventChipAnimationController$onSystemEventAnimationFinish$1
            @Override // androidx.core.animation.AnimatorListenerAdapter, androidx.core.animation.Animator.AnimatorListener
            public final void onAnimationEnd$1(Animator animator) {
                SystemEventChipAnimationController systemEventChipAnimationController = SystemEventChipAnimationController.this;
                FrameLayout frameLayout = systemEventChipAnimationController.animationWindowView;
                if (frameLayout == null) {
                    frameLayout = null;
                }
                Object obj = systemEventChipAnimationController.currentAnimatedView;
                Intrinsics.checkNotNull(obj);
                frameLayout.removeView((View) obj);
            }
        });
        return animatorSet;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void updateChipBounds(BackgroundAnimatableView backgroundAnimatableView, Rect rect) {
        int measuredWidth;
        int i;
        int i2 = rect.top;
        int height = rect.height();
        backgroundAnimatableView.getClass();
        View view = (View) backgroundAnimatableView;
        int measuredHeight = ((height - view.getMeasuredHeight()) / 2) + i2;
        int measuredHeight2 = view.getMeasuredHeight() + measuredHeight;
        if (this.animationDirection == 1) {
            measuredWidth = rect.right;
            i = measuredWidth - view.getMeasuredWidth();
        } else {
            int i3 = rect.left;
            measuredWidth = view.getMeasuredWidth() + i3;
            i = i3;
        }
        Rect rect2 = new Rect(i, measuredHeight, measuredWidth, measuredHeight2);
        this.chipBounds = rect2;
        this.animRect.set(rect2);
    }

    public final void updateCurrentAnimatedView() {
        BackgroundAnimatableView backgroundAnimatableView = this.currentAnimatedView;
        if (backgroundAnimatableView != null) {
            Rect rect = this.animRect;
            backgroundAnimatableView.setBoundsForAnimation(rect.left, rect.top, rect.right, rect.bottom);
        }
    }

    public static /* synthetic */ void getChipBounds$annotations() {
    }

    public static /* synthetic */ void getInitialized$annotations() {
    }
}

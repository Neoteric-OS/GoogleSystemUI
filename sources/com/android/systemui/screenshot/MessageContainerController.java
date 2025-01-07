package com.android.systemui.screenshot;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import com.android.systemui.screenshot.message.ProfileMessageController;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MessageContainerController {
    public Animator animateOut;
    public ViewGroup container;
    public ViewGroup detectionNoticeView;
    public Guideline guideline;
    public final CoroutineScope mainScope;
    public final ProfileMessageController profileMessageController;
    public final ScreenshotDetectionController screenshotDetectionController;
    public ViewGroup workProfileFirstRunView;

    public MessageContainerController(ProfileMessageController profileMessageController, ScreenshotDetectionController screenshotDetectionController, CoroutineScope coroutineScope) {
        this.profileMessageController = profileMessageController;
        this.screenshotDetectionController = screenshotDetectionController;
        this.mainScope = coroutineScope;
    }

    public static final void access$animateInMessageContainer(final MessageContainerController messageContainerController) {
        ViewGroup viewGroup = messageContainerController.container;
        if (viewGroup == null) {
            viewGroup = null;
        }
        if (viewGroup.getVisibility() == 0) {
            return;
        }
        ViewGroup viewGroup2 = messageContainerController.container;
        if (viewGroup2 == null) {
            viewGroup2 = null;
        }
        viewGroup2.setVisibility(0);
        ViewGroup viewGroup3 = messageContainerController.container;
        (viewGroup3 != null ? viewGroup3 : null).getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.android.systemui.screenshot.MessageContainerController$animateInMessageContainer$1
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public final boolean onPreDraw() {
                ViewGroup viewGroup4 = MessageContainerController.this.container;
                if (viewGroup4 == null) {
                    viewGroup4 = null;
                }
                viewGroup4.getViewTreeObserver().removeOnPreDrawListener(this);
                MessageContainerController.this.getAnimator(true).start();
                return false;
            }
        });
    }

    public final Animator getAnimator(boolean z) {
        ViewGroup viewGroup = this.container;
        if (viewGroup == null) {
            viewGroup = null;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) viewGroup.getLayoutParams();
        ViewGroup viewGroup2 = this.container;
        final int height = (viewGroup2 != null ? viewGroup2 : null).getHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
        ValueAnimator ofFloat = z ? ValueAnimator.ofFloat(0.0f, 1.0f) : ValueAnimator.ofFloat(1.0f, 0.0f);
        ofFloat.setDuration(400L);
        ofFloat.setInterpolator(new AccelerateDecelerateInterpolator());
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.screenshot.MessageContainerController$getAnimator$1$1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                Guideline guideline = MessageContainerController.this.guideline;
                if (guideline == null) {
                    guideline = null;
                }
                int i = (int) (height * floatValue);
                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) guideline.getLayoutParams();
                if (!guideline.mFilterRedundantCalls || layoutParams.guideEnd != i) {
                    layoutParams.guideEnd = i;
                    guideline.setLayoutParams(layoutParams);
                }
                ViewGroup viewGroup3 = MessageContainerController.this.container;
                (viewGroup3 != null ? viewGroup3 : null).setAlpha(floatValue);
            }
        });
        return ofFloat;
    }
}

package com.android.wm.shell.bubbles.bar;

import android.content.Context;
import android.view.View;
import androidx.dynamicanimation.animation.DynamicAnimation;
import com.android.wm.shell.bubbles.Bubble;
import com.android.wm.shell.bubbles.bar.BubbleBarExpandedView;
import com.android.wm.shell.shared.animation.PhysicsAnimator;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BubbleBarMenuViewController {
    public Bubble mBubble;
    public final Context mContext;
    public BubbleBarExpandedView.AnonymousClass3 mListener;
    public PhysicsAnimator mMenuAnimator;
    public final PhysicsAnimator.SpringConfig mMenuSpringConfig = new PhysicsAnimator.SpringConfig(1500.0f, 0.75f);
    public BubbleBarMenuView mMenuView;
    public final BubbleBarExpandedView mRootView;
    public View mScrimView;

    public BubbleBarMenuViewController(Context context, BubbleBarExpandedView bubbleBarExpandedView) {
        this.mContext = context;
        this.mRootView = bubbleBarExpandedView;
    }

    public final void animateTransition(final Runnable runnable, boolean z) {
        BubbleBarMenuView bubbleBarMenuView = this.mMenuView;
        if (bubbleBarMenuView == null) {
            return;
        }
        Function2 function2 = PhysicsAnimator.onAnimatorCreated;
        PhysicsAnimator companion = PhysicsAnimator.Companion.getInstance(bubbleBarMenuView);
        this.mMenuAnimator = companion;
        companion.defaultSpring = this.mMenuSpringConfig;
        companion.spring(DynamicAnimation.ALPHA, z ? 1.0f : 0.0f);
        companion.spring(DynamicAnimation.SCALE_Y, z ? 1.0f : 0.5f);
        companion.withEndActions(new Runnable() { // from class: com.android.wm.shell.bubbles.bar.BubbleBarMenuViewController$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                BubbleBarMenuViewController bubbleBarMenuViewController = BubbleBarMenuViewController.this;
                Runnable runnable2 = runnable;
                bubbleBarMenuViewController.mMenuAnimator = null;
                runnable2.run();
            }
        });
        companion.start();
    }

    public final void hideMenu(boolean z) {
        if (this.mMenuView == null || this.mScrimView == null) {
            return;
        }
        PhysicsAnimator physicsAnimator = this.mMenuAnimator;
        if (physicsAnimator != null) {
            physicsAnimator.cancel();
            this.mMenuAnimator = null;
        }
        BubbleBarMenuViewController$$ExternalSyntheticLambda3 bubbleBarMenuViewController$$ExternalSyntheticLambda3 = new BubbleBarMenuViewController$$ExternalSyntheticLambda3(this, 1);
        if (z) {
            animateTransition(bubbleBarMenuViewController$$ExternalSyntheticLambda3, false);
        } else {
            bubbleBarMenuViewController$$ExternalSyntheticLambda3.run();
        }
    }
}

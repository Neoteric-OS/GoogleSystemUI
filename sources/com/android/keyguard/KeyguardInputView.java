package com.android.keyguard;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class KeyguardInputView extends LinearLayout {
    public View mBouncerMessageView;
    public KeyguardPasswordViewController$$ExternalSyntheticLambda6 mOnFinishImeAnimationRunnable;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.keyguard.KeyguardInputView$1, reason: invalid class name */
    public final class AnonymousClass1 extends AnimatorListenerAdapter {
        public boolean mIsCancel;
        public final /* synthetic */ int val$cuj;

        public AnonymousClass1(int i) {
            this.val$cuj = i;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationCancel(Animator animator) {
            this.mIsCancel = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            if (this.mIsCancel) {
                InteractionJankMonitor.getInstance().cancel(this.val$cuj);
            } else {
                InteractionJankMonitor.getInstance().end(this.val$cuj);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationStart(Animator animator) {
            InteractionJankMonitor.getInstance().begin(KeyguardInputView.this, this.val$cuj);
        }
    }

    public boolean disallowInterceptTouch(MotionEvent motionEvent) {
        return false;
    }

    public abstract CharSequence getTitle();

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mBouncerMessageView = findViewById(R.id.bouncer_message_view);
    }

    public abstract void startAppearAnimation();

    public boolean startDisappearAnimation(Runnable runnable) {
        return false;
    }
}

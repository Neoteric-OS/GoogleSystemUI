package com.android.wm.shell.compatui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import com.android.wm.shell.R;
import java.util.function.BiConsumer;
import java.util.function.Function;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class ReachabilityEduLayout extends FrameLayout {
    public static final /* synthetic */ int $r8$clinit = 0;
    public int mLastBottomMargin;
    public int mLastLeftMargin;
    public int mLastRightMargin;
    public int mLastTopMargin;
    public View mMoveDownButton;
    public View mMoveLeftButton;
    public View mMoveRightButton;
    public View mMoveUpButton;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wm.shell.compatui.ReachabilityEduLayout$1, reason: invalid class name */
    public final class AnonymousClass1 extends AnimatorListenerAdapter {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ View val$view;

        public /* synthetic */ AnonymousClass1(View view, int i) {
            this.$r8$classId = i;
            this.val$view = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            switch (this.$r8$classId) {
                case 0:
                    this.val$view.setVisibility(0);
                    break;
                default:
                    this.val$view.setVisibility(4);
                    break;
            }
        }
    }

    public ReachabilityEduLayout(Context context) {
        this(context, null);
    }

    public static void hideItem(View view) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0.0f);
        ofFloat.setDuration(400L);
        ofFloat.addListener(new AnonymousClass1(view, 1));
        ofFloat.start();
    }

    public static Animator marginAnimator(final View view, Function function, final BiConsumer biConsumer, int i, int i2) {
        final FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        ValueAnimator ofInt = ValueAnimator.ofInt(((Integer) function.apply(layoutParams)).intValue(), i, i2);
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.wm.shell.compatui.ReachabilityEduLayout$$ExternalSyntheticLambda8
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                BiConsumer biConsumer2 = biConsumer;
                FrameLayout.LayoutParams layoutParams2 = layoutParams;
                View view2 = view;
                int i3 = ReachabilityEduLayout.$r8$clinit;
                biConsumer2.accept(layoutParams2, (Integer) valueAnimator.getAnimatedValue());
                view2.requestLayout();
            }
        });
        ofInt.setDuration(250L);
        return ofInt;
    }

    public static void showItem(View view) {
        view.setVisibility(0);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "alpha", 0.0f, 1.0f);
        ofFloat.setDuration(400L);
        ofFloat.addListener(new AnonymousClass1(view, 0));
        ofFloat.start();
    }

    public final void hideAllImmediately() {
        View view = this.mMoveLeftButton;
        view.setAlpha(0.0f);
        view.setVisibility(4);
        View view2 = this.mMoveRightButton;
        view2.setAlpha(0.0f);
        view2.setVisibility(4);
        View view3 = this.mMoveUpButton;
        view3.setAlpha(0.0f);
        view3.setVisibility(4);
        View view4 = this.mMoveDownButton;
        view4.setAlpha(0.0f);
        view4.setVisibility(4);
        this.mLastLeftMargin = -1;
        this.mLastRightMargin = -1;
        this.mLastTopMargin = -1;
        this.mLastBottomMargin = -1;
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.mMoveLeftButton = findViewById(R.id.reachability_move_left_button);
        this.mMoveRightButton = findViewById(R.id.reachability_move_right_button);
        this.mMoveUpButton = findViewById(R.id.reachability_move_up_button);
        this.mMoveDownButton = findViewById(R.id.reachability_move_down_button);
        this.mMoveLeftButton.measure(0, 0);
        this.mMoveRightButton.measure(0, 0);
        this.mMoveUpButton.measure(0, 0);
        this.mMoveDownButton.measure(0, 0);
    }

    public ReachabilityEduLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ReachabilityEduLayout(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public ReachabilityEduLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mLastLeftMargin = -1;
        this.mLastRightMargin = -1;
        this.mLastTopMargin = -1;
        this.mLastBottomMargin = -1;
    }
}

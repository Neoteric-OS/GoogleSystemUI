package com.android.systemui.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import com.android.app.animation.Interpolators;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ViewHierarchyAnimator$Companion$animateRemoval$2 extends AnimatorListenerAdapter {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ View $rootView;

    public /* synthetic */ ViewHierarchyAnimator$Companion$animateRemoval$2(View view, int i) {
        this.$r8$classId = i;
        this.$rootView = view;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        switch (this.$r8$classId) {
            case 0:
                this.$rootView.animate().alpha(0.0f).setInterpolator(Interpolators.ALPHA_OUT).setDuration(250 / 2).start();
                break;
            default:
                this.$rootView.setTag(R.id.tag_alpha_animator, null);
                break;
        }
    }
}

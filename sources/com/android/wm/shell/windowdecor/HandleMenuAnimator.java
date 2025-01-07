package com.android.wm.shell.windowdecor;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.core.view.ViewGroupKt$iterator$1;
import com.android.wm.shell.R;
import com.android.wm.shell.shared.animation.Interpolators;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class HandleMenuAnimator {
    public final List animators = new ArrayList();
    public final ViewGroup appInfoPill;
    public final float captionHeight;
    public final View handleMenu;
    public final int menuWidth;
    public final ViewGroup moreActionsPill;
    public final ViewGroup openInBrowserPill;
    public AnimatorSet runningAnimation;
    public final ViewGroup windowingPill;

    public HandleMenuAnimator(View view, int i, float f) {
        this.handleMenu = view;
        this.menuWidth = i;
        this.captionHeight = f;
        this.appInfoPill = (ViewGroup) view.requireViewById(R.id.app_info_pill);
        this.windowingPill = (ViewGroup) view.requireViewById(R.id.windowing_pill);
        this.moreActionsPill = (ViewGroup) view.requireViewById(R.id.more_actions_pill);
        this.openInBrowserPill = (ViewGroup) view.requireViewById(R.id.open_in_browser_pill);
    }

    public final void animateAppInfoPillFadeOut() {
        ViewGroupKt$iterator$1 viewGroupKt$iterator$1 = new ViewGroupKt$iterator$1(this.appInfoPill);
        while (viewGroupKt$iterator$1.hasNext()) {
            View view = (View) viewGroupKt$iterator$1.next();
            List list = this.animators;
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, (Property<View, Float>) View.ALPHA, 0.0f);
            ofFloat.setStartDelay(25L);
            ofFloat.setDuration(25L);
            list.add(ofFloat);
        }
    }

    public final void animateAppInfoPillOpen() {
        List list = this.animators;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.appInfoPill, (Property<ViewGroup, Float>) View.TRANSLATION_Z, 1.0f);
        ofFloat.setStartDelay(33L);
        ofFloat.setDuration(83L);
        list.add(ofFloat);
        ViewGroupKt$iterator$1 viewGroupKt$iterator$1 = new ViewGroupKt$iterator$1(this.appInfoPill);
        while (viewGroupKt$iterator$1.hasNext()) {
            View view = (View) viewGroupKt$iterator$1.next();
            List list2 = this.animators;
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, (Property<View, Float>) View.ALPHA, 1.0f);
            ofFloat2.setStartDelay(67L);
            ofFloat2.setDuration(100L);
            list2.add(ofFloat2);
        }
    }

    public final void animateMoreActionsPillOpen() {
        List list = this.animators;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.moreActionsPill, (Property<ViewGroup, Float>) View.SCALE_X, 0.5f, 1.0f);
        ofFloat.setStartDelay(50L);
        ofFloat.setDuration(180L);
        list.add(ofFloat);
        List list2 = this.animators;
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.moreActionsPill, (Property<ViewGroup, Float>) View.SCALE_Y, 0.5f, 1.0f);
        ofFloat2.setStartDelay(50L);
        ofFloat2.setDuration(180L);
        list2.add(ofFloat2);
        List list3 = this.animators;
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.moreActionsPill, (Property<ViewGroup, Float>) View.ALPHA, 1.0f);
        ofFloat3.setStartDelay(133L);
        ofFloat3.setDuration(150L);
        list3.add(ofFloat3);
        List list4 = this.animators;
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(this.moreActionsPill, (Property<ViewGroup, Float>) View.TRANSLATION_Z, 1.0f);
        ofFloat4.setStartDelay(33L);
        ofFloat4.setDuration(83L);
        list4.add(ofFloat4);
        ViewGroupKt$iterator$1 viewGroupKt$iterator$1 = new ViewGroupKt$iterator$1(this.moreActionsPill);
        while (viewGroupKt$iterator$1.hasNext()) {
            View view = (View) viewGroupKt$iterator$1.next();
            List list5 = this.animators;
            ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(view, (Property<View, Float>) View.ALPHA, 1.0f);
            ofFloat5.setStartDelay(133L);
            ofFloat5.setDuration(167L);
            ofFloat5.setInterpolator(Interpolators.FAST_OUT_SLOW_IN);
            list5.add(ofFloat5);
        }
    }

    public final void animateOpenInBrowserPill() {
        List list = this.animators;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.openInBrowserPill, (Property<ViewGroup, Float>) View.SCALE_X, 0.5f, 1.0f);
        ofFloat.setStartDelay(50L);
        ofFloat.setDuration(180L);
        list.add(ofFloat);
        List list2 = this.animators;
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.openInBrowserPill, (Property<ViewGroup, Float>) View.SCALE_Y, 0.5f, 1.0f);
        ofFloat2.setStartDelay(50L);
        ofFloat2.setDuration(180L);
        list2.add(ofFloat2);
        List list3 = this.animators;
        ViewGroup viewGroup = this.openInBrowserPill;
        Property property = View.ALPHA;
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(viewGroup, (Property<ViewGroup, Float>) property, 1.0f);
        ofFloat3.setStartDelay(133L);
        ofFloat3.setDuration(150L);
        list3.add(ofFloat3);
        List list4 = this.animators;
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(this.openInBrowserPill, (Property<ViewGroup, Float>) View.TRANSLATION_Z, 1.0f);
        ofFloat4.setStartDelay(33L);
        ofFloat4.setDuration(83L);
        list4.add(ofFloat4);
        Button button = (Button) this.openInBrowserPill.requireViewById(R.id.open_in_browser_button);
        List list5 = this.animators;
        ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(button, (Property<Button, Float>) property, 1.0f);
        ofFloat5.setStartDelay(133L);
        ofFloat5.setDuration(167L);
        ofFloat5.setInterpolator(Interpolators.FAST_OUT_SLOW_IN);
        list5.add(ofFloat5);
    }

    public final void animateWindowingPillOpen() {
        List list = this.animators;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.windowingPill, (Property<ViewGroup, Float>) View.SCALE_X, 0.5f, 1.0f);
        ofFloat.setStartDelay(50L);
        ofFloat.setDuration(180L);
        list.add(ofFloat);
        List list2 = this.animators;
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.windowingPill, (Property<ViewGroup, Float>) View.SCALE_Y, 0.5f, 1.0f);
        ofFloat2.setStartDelay(50L);
        ofFloat2.setDuration(180L);
        list2.add(ofFloat2);
        List list3 = this.animators;
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.windowingPill, (Property<ViewGroup, Float>) View.ALPHA, 1.0f);
        ofFloat3.setStartDelay(133L);
        ofFloat3.setDuration(150L);
        list3.add(ofFloat3);
        List list4 = this.animators;
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(this.windowingPill, (Property<ViewGroup, Float>) View.TRANSLATION_Z, 1.0f);
        ofFloat4.setStartDelay(33L);
        ofFloat4.setDuration(83L);
        list4.add(ofFloat4);
        ViewGroupKt$iterator$1 viewGroupKt$iterator$1 = new ViewGroupKt$iterator$1(this.windowingPill);
        while (viewGroupKt$iterator$1.hasNext()) {
            View view = (View) viewGroupKt$iterator$1.next();
            List list5 = this.animators;
            ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(view, (Property<View, Float>) View.ALPHA, 1.0f);
            ofFloat5.setStartDelay(133L);
            ofFloat5.setDuration(167L);
            ofFloat5.setInterpolator(Interpolators.FAST_OUT_SLOW_IN);
            list5.add(ofFloat5);
        }
    }

    public final void moreActionsPillClose() {
        List list = this.animators;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.moreActionsPill, (Property<ViewGroup, Float>) View.SCALE_X, 0.5f);
        ofFloat.setDuration(50L);
        list.add(ofFloat);
        List list2 = this.animators;
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.moreActionsPill, (Property<ViewGroup, Float>) View.SCALE_Y, 0.5f);
        ofFloat2.setDuration(50L);
        list2.add(ofFloat2);
        List list3 = this.animators;
        ViewGroup viewGroup = this.moreActionsPill;
        Property property = View.ALPHA;
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(viewGroup, (Property<ViewGroup, Float>) property, 0.0f);
        ofFloat3.setDuration(50L);
        list3.add(ofFloat3);
        List list4 = this.animators;
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(this.moreActionsPill, (Property<ViewGroup, Float>) property, 0.0f);
        ofFloat4.setDuration(50L);
        list4.add(ofFloat4);
        float f = (-this.captionHeight) / 2;
        List list5 = this.animators;
        ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(this.moreActionsPill, (Property<ViewGroup, Float>) View.TRANSLATION_Y, f);
        ofFloat5.setDuration(50L);
        list5.add(ofFloat5);
    }

    public final void openInBrowserPillClose() {
        List list = this.animators;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.openInBrowserPill, (Property<ViewGroup, Float>) View.SCALE_X, 0.5f);
        ofFloat.setDuration(50L);
        list.add(ofFloat);
        List list2 = this.animators;
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.openInBrowserPill, (Property<ViewGroup, Float>) View.SCALE_Y, 0.5f);
        ofFloat2.setDuration(50L);
        list2.add(ofFloat2);
        List list3 = this.animators;
        ViewGroup viewGroup = this.openInBrowserPill;
        Property property = View.ALPHA;
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(viewGroup, (Property<ViewGroup, Float>) property, 0.0f);
        ofFloat3.setDuration(50L);
        list3.add(ofFloat3);
        List list4 = this.animators;
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(this.openInBrowserPill, (Property<ViewGroup, Float>) property, 0.0f);
        ofFloat4.setDuration(50L);
        list4.add(ofFloat4);
        float f = (-this.captionHeight) / 2;
        List list5 = this.animators;
        ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(this.openInBrowserPill, (Property<ViewGroup, Float>) View.TRANSLATION_Y, f);
        ofFloat5.setDuration(50L);
        list5.add(ofFloat5);
    }

    public final void prepareMenuForAnimation() {
        ViewGroupKt$iterator$1 viewGroupKt$iterator$1 = new ViewGroupKt$iterator$1(this.appInfoPill);
        while (viewGroupKt$iterator$1.hasNext()) {
            ((View) viewGroupKt$iterator$1.next()).setAlpha(0.0f);
        }
        this.windowingPill.setAlpha(0.0f);
        this.moreActionsPill.setAlpha(0.0f);
        this.openInBrowserPill.setAlpha(0.0f);
        View view = this.handleMenu;
        int i = this.menuWidth;
        view.setPivotX(i / 2.0f);
        this.handleMenu.setPivotY(0.0f);
        this.windowingPill.setPivotX(i / 2.0f);
        this.windowingPill.setPivotY(this.appInfoPill.getMeasuredHeight());
        this.moreActionsPill.setPivotX(i / 2.0f);
        this.moreActionsPill.setPivotY(this.appInfoPill.getMeasuredHeight());
        this.openInBrowserPill.setPivotX(i / 2.0f);
        this.openInBrowserPill.setPivotY(this.appInfoPill.getMeasuredHeight());
    }

    public final void runAnimations(final Function0 function0) {
        AnimatorSet animatorSet = this.runningAnimation;
        if (animatorSet != null) {
            animatorSet.removeAllListeners();
            animatorSet.cancel();
        }
        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.playTogether(this.animators);
        this.animators.clear();
        animatorSet2.addListener(new Animator.AnimatorListener(function0, this) { // from class: com.android.wm.shell.windowdecor.HandleMenuAnimator$runAnimations$lambda$49$$inlined$doOnEnd$1
            public final /* synthetic */ Lambda $after$inlined;
            public final /* synthetic */ HandleMenuAnimator this$0;

            /* JADX WARN: Multi-variable type inference failed */
            {
                this.$after$inlined = (Lambda) function0;
                this.this$0 = this;
            }

            /* JADX WARN: Type inference failed for: r1v1, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
            @Override // android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                this.$after$inlined.invoke();
                this.this$0.runningAnimation = null;
            }

            @Override // android.animation.Animator.AnimatorListener
            public final void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public final void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public final void onAnimationStart(Animator animator) {
            }
        });
        animatorSet2.start();
        this.runningAnimation = animatorSet2;
    }

    public final void windowingPillClose() {
        List list = this.animators;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.windowingPill, (Property<ViewGroup, Float>) View.SCALE_X, 0.5f);
        ofFloat.setDuration(50L);
        list.add(ofFloat);
        List list2 = this.animators;
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.windowingPill, (Property<ViewGroup, Float>) View.SCALE_Y, 0.5f);
        ofFloat2.setDuration(50L);
        list2.add(ofFloat2);
        List list3 = this.animators;
        ViewGroup viewGroup = this.windowingPill;
        Property property = View.ALPHA;
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(viewGroup, (Property<ViewGroup, Float>) property, 0.0f);
        ofFloat3.setDuration(50L);
        list3.add(ofFloat3);
        List list4 = this.animators;
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(this.windowingPill, (Property<ViewGroup, Float>) property, 0.0f);
        ofFloat4.setDuration(50L);
        list4.add(ofFloat4);
    }
}

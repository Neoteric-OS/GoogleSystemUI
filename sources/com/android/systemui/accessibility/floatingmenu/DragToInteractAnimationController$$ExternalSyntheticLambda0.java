package com.android.systemui.accessibility.floatingmenu;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.view.MotionEvent;
import androidx.dynamicanimation.animation.DynamicAnimation;
import com.android.systemui.accessibility.floatingmenu.DragToInteractAnimationController;
import com.android.systemui.accessibility.floatingmenu.MenuAnimationController;
import com.android.systemui.accessibility.floatingmenu.MenuViewLayer;
import com.android.wm.shell.shared.bubbles.DismissCircleView;
import com.android.wm.shell.shared.magnetictarget.MagnetizedObject;
import com.android.wm.shell.shared.magnetictarget.MagnetizedObject$MagneticTarget$updateLocationOnScreen$1;
import java.util.function.BiConsumer;
import kotlin.Pair;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class DragToInteractAnimationController$$ExternalSyntheticLambda0 implements BiConsumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ DragToInteractAnimationController$$ExternalSyntheticLambda0(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        int i = this.$r8$classId;
        Object obj3 = this.f$0;
        switch (i) {
            case 0:
                final DragToInteractAnimationController dragToInteractAnimationController = (DragToInteractAnimationController) obj3;
                dragToInteractAnimationController.getClass();
                final DismissCircleView dismissCircleView = (DismissCircleView) ((Pair) obj2).getFirst();
                MenuView menuView = dragToInteractAnimationController.mMenuView;
                DragToInteractAnimationController.AnonymousClass1 anonymousClass1 = new DragToInteractAnimationController.AnonymousClass1(menuView.getContext(), menuView, new MenuAnimationController.MenuPositionProperty(DynamicAnimation.TRANSLATION_X), new MenuAnimationController.MenuPositionProperty(DynamicAnimation.TRANSLATION_Y));
                anonymousClass1.flingToTargetEnabled = false;
                MagnetizedObject.MagneticTarget magneticTarget = new MagnetizedObject.MagneticTarget(dismissCircleView, (int) dragToInteractAnimationController.mMinInteractSize);
                anonymousClass1.associatedTargets.add(magneticTarget);
                dismissCircleView.post(new MagnetizedObject$MagneticTarget$updateLocationOnScreen$1(magneticTarget));
                ValueAnimator ofFloat = ValueAnimator.ofFloat(1.0f, 0.0f);
                ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.accessibility.floatingmenu.DragToInteractAnimationController$$ExternalSyntheticLambda2
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                        DragToInteractAnimationController dragToInteractAnimationController2 = DragToInteractAnimationController.this;
                        DismissCircleView dismissCircleView2 = dismissCircleView;
                        dragToInteractAnimationController2.getClass();
                        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                        float max = Math.max(floatValue, dragToInteractAnimationController2.mSizePercent);
                        dismissCircleView2.setScaleX(max);
                        dismissCircleView2.setScaleY(max);
                        dragToInteractAnimationController2.mMenuView.setAlpha(Math.max(floatValue, 0.7f));
                    }
                });
                ofFloat.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.accessibility.floatingmenu.DragToInteractAnimationController.2
                    @Override // android.animation.Animator.AnimatorListener
                    public final void onAnimationEnd(Animator animator, boolean z) {
                        super.onAnimationEnd(animator, z);
                        if (z) {
                            dismissCircleView.setScaleX(1.0f);
                            dismissCircleView.setScaleY(1.0f);
                            DragToInteractAnimationController.this.mMenuView.setAlpha(1.0f);
                        }
                    }
                });
                dragToInteractAnimationController.mInteractMap.put(Integer.valueOf(dismissCircleView.getId()), new android.util.Pair(anonymousClass1, ofFloat));
                break;
            case 1:
                ((MagnetizedObject) ((android.util.Pair) obj2).first).magnetListener = (MenuViewLayer.AnonymousClass3) obj3;
                break;
            default:
                ((MagnetizedObject) ((android.util.Pair) obj2).first).maybeConsumeMotionEvent((MotionEvent) obj3);
                break;
        }
    }
}

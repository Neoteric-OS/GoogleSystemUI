package com.android.systemui.animation;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.android.systemui.animation.ActivityTransitionAnimator;
import com.android.systemui.animation.DialogTransitionAnimator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class Expandable$Companion$fromView$1 implements Expandable {
    public final /* synthetic */ View $view;

    public Expandable$Companion$fromView$1(View view) {
        this.$view = view;
    }

    @Override // com.android.systemui.animation.Expandable
    public final ActivityTransitionAnimator.Controller activityTransitionController(Integer num) {
        View view = this.$view;
        if (!(view instanceof LaunchableView)) {
            throw new IllegalArgumentException("An ActivityTransitionAnimator.Controller was created from a View that does not implement LaunchableView. This can lead to subtle bugs where the visibility of the View we are launching from is not what we expected.");
        }
        if (view.getParent() instanceof ViewGroup) {
            return new GhostedViewTransitionAnimatorController(view, num, 32);
        }
        Log.e("ActivityTransitionAnimator", "Skipping animation as view " + view + " is not attached to a ViewGroup", new Exception());
        return null;
    }

    @Override // com.android.systemui.animation.Expandable
    public final DialogTransitionAnimator.Controller dialogTransitionController(DialogCuj dialogCuj) {
        View view = this.$view;
        if (!(view instanceof LaunchableView)) {
            throw new IllegalArgumentException("A DialogTransitionAnimator.Controller was created from a View that does not implement LaunchableView. This can lead to subtle bugs where the visibility of the View we are launching from is not what we expected.");
        }
        if (view.getParent() instanceof ViewGroup) {
            return new ViewDialogTransitionAnimatorController(view, dialogCuj);
        }
        Log.e("DialogTransitionAnimator", "Skipping animation as view " + view + " is not attached to a ViewGroup", new Exception());
        return null;
    }
}

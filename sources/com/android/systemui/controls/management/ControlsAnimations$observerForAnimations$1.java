package com.android.systemui.controls.management;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import com.android.wm.shell.R;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ControlsAnimations$observerForAnimations$1 implements LifecycleObserver {
    public final /* synthetic */ ViewGroup $view;
    public final /* synthetic */ Window $window;
    public boolean showAnimation;

    public ControlsAnimations$observerForAnimations$1(Intent intent, ViewGroup viewGroup, boolean z, Window window) {
        this.$view = viewGroup;
        this.$window = window;
        this.showAnimation = intent.getBooleanExtra("extra_animate", false);
        viewGroup.setTransitionGroup(true);
        viewGroup.setTransitionAlpha(0.0f);
        if (ControlsAnimations.translationY == -1.0f) {
            if (z) {
                ControlsAnimations.translationY = viewGroup.getContext().getResources().getDimensionPixelSize(R.dimen.global_actions_controls_y_translation);
            } else {
                ControlsAnimations.translationY = 0.0f;
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public final void enterAnimation() {
        if (this.showAnimation) {
            ControlsAnimations.enterAnimation(this.$view).start();
            this.showAnimation = false;
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public final void resetAnimation() {
        this.$view.setTranslationY(0.0f);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public final void setup() {
        Window window = this.$window;
        ViewGroup viewGroup = this.$view;
        window.setAllowEnterTransitionOverlap(true);
        int id = viewGroup.getId();
        WindowTransition windowTransition = new WindowTransition(new Function1() { // from class: com.android.systemui.controls.management.ControlsAnimations$enterWindowTransition$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ControlsAnimations.enterAnimation((View) obj);
            }
        });
        windowTransition.addTarget(id);
        window.setEnterTransition(windowTransition);
        int id2 = viewGroup.getId();
        WindowTransition windowTransition2 = new WindowTransition(new Function1() { // from class: com.android.systemui.controls.management.ControlsAnimations$exitWindowTransition$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ControlsAnimations.exitAnimation((View) obj, null);
            }
        });
        windowTransition2.addTarget(id2);
        window.setExitTransition(windowTransition2);
        int id3 = viewGroup.getId();
        WindowTransition windowTransition3 = new WindowTransition(new Function1() { // from class: com.android.systemui.controls.management.ControlsAnimations$enterWindowTransition$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ControlsAnimations.enterAnimation((View) obj);
            }
        });
        windowTransition3.addTarget(id3);
        window.setReenterTransition(windowTransition3);
        int id4 = viewGroup.getId();
        WindowTransition windowTransition4 = new WindowTransition(new Function1() { // from class: com.android.systemui.controls.management.ControlsAnimations$exitWindowTransition$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ControlsAnimations.exitAnimation((View) obj, null);
            }
        });
        windowTransition4.addTarget(id4);
        window.setReturnTransition(windowTransition4);
    }
}

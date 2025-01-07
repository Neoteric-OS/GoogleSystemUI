package com.google.android.systemui.assist.uihints;

import android.animation.ObjectAnimator;
import android.os.Handler;
import android.util.Property;
import android.view.View;
import com.android.systemui.navigationbar.NavigationBarControllerImpl;
import com.android.systemui.navigationbar.views.NavigationBarView;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NavBarFadeController {
    public final Handler handler;
    public final NavBarFader navBarFader;
    public final NavBarFadeController$onTimeout$1 onTimeout = new Runnable() { // from class: com.google.android.systemui.assist.uihints.NavBarFadeController$onTimeout$1
        @Override // java.lang.Runnable
        public final void run() {
            NavBarFadeController navBarFadeController = NavBarFadeController.this;
            navBarFadeController.systemVisible = true;
            navBarFadeController.ngaVisible = true;
            navBarFadeController.update();
        }
    };
    public boolean systemVisible = true;
    public boolean ngaVisible = true;

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.android.systemui.assist.uihints.NavBarFadeController$onTimeout$1] */
    public NavBarFadeController(NavBarFader navBarFader, Handler handler) {
        this.navBarFader = navBarFader;
        this.handler = handler;
    }

    public final void update() {
        boolean z = this.systemVisible & this.ngaVisible;
        NavBarFadeController$onTimeout$1 navBarFadeController$onTimeout$1 = this.onTimeout;
        Handler handler = this.handler;
        handler.removeCallbacks(navBarFadeController$onTimeout$1);
        if (!z) {
            handler.postDelayed(navBarFadeController$onTimeout$1, 10000L);
        }
        NavBarFader navBarFader = this.navBarFader;
        NavigationBarControllerImpl navigationBarControllerImpl = navBarFader.navigationBarController;
        navigationBarControllerImpl.mDisplayTracker.getClass();
        NavigationBarView navigationBarView = navigationBarControllerImpl.getNavigationBarView(0);
        if (navigationBarView == null) {
            return;
        }
        float f = z ? 1.0f : 0.0f;
        if (f == navBarFader.targetAlpha) {
            return;
        }
        navBarFader.animator.cancel();
        float alpha = navigationBarView.getAlpha();
        navBarFader.targetAlpha = f;
        ObjectAnimator duration = ObjectAnimator.ofFloat(navigationBarView, (Property<NavigationBarView, Float>) View.ALPHA, alpha, f).setDuration((long) (Math.abs(f - alpha) * 80));
        navBarFader.animator = duration;
        if (z) {
            duration.setStartDelay(80L);
        }
        navBarFader.animator.start();
    }
}

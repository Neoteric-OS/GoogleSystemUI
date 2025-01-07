package com.google.android.systemui.assist.uihints;

import android.animation.ObjectAnimator;
import com.android.systemui.navigationbar.NavigationBarControllerImpl;
import com.android.systemui.navigationbar.views.NavigationBarView;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NavBarFader {
    public ObjectAnimator animator = new ObjectAnimator();
    public final NavigationBarControllerImpl navigationBarController;
    public float targetAlpha;

    public NavBarFader(NavigationBarControllerImpl navigationBarControllerImpl) {
        this.navigationBarController = navigationBarControllerImpl;
        navigationBarControllerImpl.mDisplayTracker.getClass();
        NavigationBarView navigationBarView = navigationBarControllerImpl.getNavigationBarView(0);
        this.targetAlpha = navigationBarView != null ? navigationBarView.getAlpha() : 1.0f;
    }
}

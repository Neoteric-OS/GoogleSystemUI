package com.google.android.systemui.ailabs.ui.data.repository;

import android.content.ComponentName;
import android.content.Intent;
import android.view.ViewGroup;
import androidx.compose.ui.layout.LayoutCoordinates;
import com.android.systemui.animation.ActivityTransitionAnimator;
import com.android.systemui.animation.TransitionAnimator;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;
import dagger.Lazy;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class StrideLockscreenRepository {
    public final ActivityStarter activityStarter;
    public final Lazy keyguardViewController;
    public final Function1 launchStrideActivity;

    public StrideLockscreenRepository(ActivityStarter activityStarter, Lazy lazy) {
        this.activityStarter = activityStarter;
        this.keyguardViewController = lazy;
        new Function1() { // from class: com.google.android.systemui.ailabs.ui.data.repository.StrideLockscreenRepository$launchStrideActivity$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                final LayoutCoordinates layoutCoordinates = (LayoutCoordinates) obj;
                Intent intent = new Intent("android.intent.action.MAIN");
                final StrideLockscreenRepository strideLockscreenRepository = StrideLockscreenRepository.this;
                intent.setComponent(new ComponentName("com.google.android.apps.stride", "com.google.android.apps.stride.MainActivity"));
                intent.addFlags(335544320);
                strideLockscreenRepository.activityStarter.startActivity(intent, true, new ActivityTransitionAnimator.Controller() { // from class: com.google.android.systemui.ailabs.ui.data.repository.StrideLockscreenRepository$launchStrideActivity$1$1$1
                    @Override // com.android.systemui.animation.TransitionAnimator.Controller
                    public final TransitionAnimator.State createAnimatorState() {
                        int width = getTransitionContainer().getWidth();
                        int height = getTransitionContainer().getHeight();
                        LayoutCoordinates layoutCoordinates2 = layoutCoordinates;
                        if (layoutCoordinates2 == null) {
                            return new TransitionAnimator.State(height / 3, height, width / 2, width, 0.0f, 0.0f, 48);
                        }
                        long mo484localToRootMKHz9U = layoutCoordinates2.mo484localToRootMKHz9U(0L);
                        int i = (int) (mo484localToRootMKHz9U & 4294967295L);
                        float f = ((int) (4294967295L & r2)) / 2.0f;
                        return new TransitionAnimator.State((int) (Float.intBitsToFloat(i) - f), (int) (Float.intBitsToFloat(i) + f), width - ((int) (layoutCoordinates2.mo481getSizeYbymL2g() >> 32)), width, 0.0f, 0.0f, 48);
                    }

                    @Override // com.android.systemui.animation.TransitionAnimator.Controller
                    public final ViewGroup getTransitionContainer() {
                        return (ViewGroup) ((StatusBarKeyguardViewManager) StrideLockscreenRepository.this.keyguardViewController.get()).getViewRootImpl().getView();
                    }

                    @Override // com.android.systemui.animation.TransitionAnimator.Controller
                    public final boolean isLaunching() {
                        return true;
                    }

                    @Override // com.android.systemui.animation.TransitionAnimator.Controller
                    public final void setTransitionContainer(ViewGroup viewGroup) {
                    }
                });
                return Unit.INSTANCE;
            }
        };
    }
}

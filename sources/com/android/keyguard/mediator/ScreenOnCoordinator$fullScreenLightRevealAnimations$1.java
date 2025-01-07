package com.android.keyguard.mediator;

import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl;
import java.util.function.Function;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class ScreenOnCoordinator$fullScreenLightRevealAnimations$1 implements Function {
    public static final ScreenOnCoordinator$fullScreenLightRevealAnimations$1 INSTANCE = new ScreenOnCoordinator$fullScreenLightRevealAnimations$1();

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return ((DaggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl) obj).getFullScreenLightRevealAnimations();
    }
}

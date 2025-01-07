package com.android.keyguard.mediator;

import com.android.systemui.unfold.FoldAodAnimationController;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl;
import java.util.function.Function;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class ScreenOnCoordinator$foldAodAnimationController$1 implements Function {
    public static final ScreenOnCoordinator$foldAodAnimationController$1 INSTANCE = new ScreenOnCoordinator$foldAodAnimationController$1();

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return (FoldAodAnimationController) ((DaggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl) obj).foldAodAnimationControllerProvider.get();
    }
}

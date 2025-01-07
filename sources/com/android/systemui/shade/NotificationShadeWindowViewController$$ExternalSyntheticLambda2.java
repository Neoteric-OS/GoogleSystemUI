package com.android.systemui.shade;

import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl;
import java.util.function.Function;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class NotificationShadeWindowViewController$$ExternalSyntheticLambda2 implements Function {
    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return (NotificationPanelUnfoldAnimationController) ((DaggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl) obj).notificationPanelUnfoldAnimationControllerProvider.get();
    }
}

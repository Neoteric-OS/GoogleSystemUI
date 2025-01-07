package com.android.systemui.statusbar.dagger;

import android.service.dreams.IDreamManager;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import dagger.Lazy;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CentralSurfacesDependenciesModule$1 {
    public final /* synthetic */ Lazy val$alternateBouncerInteractor;
    public final /* synthetic */ IDreamManager val$dreamManager;
    public final /* synthetic */ KeyguardStateController val$keyguardStateController;

    public CentralSurfacesDependenciesModule$1(IDreamManager iDreamManager, KeyguardStateController keyguardStateController, Lazy lazy) {
        this.val$dreamManager = iDreamManager;
        this.val$keyguardStateController = keyguardStateController;
        this.val$alternateBouncerInteractor = lazy;
    }
}

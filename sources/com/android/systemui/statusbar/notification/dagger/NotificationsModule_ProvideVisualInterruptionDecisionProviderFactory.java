package com.android.systemui.statusbar.notification.dagger;

import com.android.systemui.statusbar.notification.interruption.VisualInterruptionDecisionProvider;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;
import dagger.internal.Preconditions;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class NotificationsModule_ProvideVisualInterruptionDecisionProviderFactory implements Provider {
    public static VisualInterruptionDecisionProvider provideVisualInterruptionDecisionProvider(DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider) {
        VisualInterruptionDecisionProvider visualInterruptionDecisionProvider = (VisualInterruptionDecisionProvider) switchingProvider.get();
        Preconditions.checkNotNullFromProvides(visualInterruptionDecisionProvider);
        return visualInterruptionDecisionProvider;
    }
}

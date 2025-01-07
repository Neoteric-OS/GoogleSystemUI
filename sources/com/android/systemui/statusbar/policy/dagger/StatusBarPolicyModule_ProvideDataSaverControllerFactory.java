package com.android.systemui.statusbar.policy.dagger;

import com.android.systemui.statusbar.connectivity.NetworkController;
import com.android.systemui.statusbar.connectivity.NetworkControllerImpl;
import com.android.systemui.statusbar.policy.DataSaverControllerImpl;
import dagger.internal.Preconditions;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class StatusBarPolicyModule_ProvideDataSaverControllerFactory implements Provider {
    public static DataSaverControllerImpl provideDataSaverController(NetworkController networkController) {
        DataSaverControllerImpl dataSaverControllerImpl = ((NetworkControllerImpl) networkController).mDataSaverController;
        Preconditions.checkNotNullFromProvides(dataSaverControllerImpl);
        return dataSaverControllerImpl;
    }
}

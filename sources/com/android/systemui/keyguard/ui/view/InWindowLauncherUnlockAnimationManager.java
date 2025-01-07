package com.android.systemui.keyguard.ui.view;

import com.android.systemui.keyguard.domain.interactor.InWindowLauncherUnlockAnimationInteractor;
import com.android.systemui.keyguard.ui.binder.InWindowLauncherAnimationViewBinder;
import com.android.systemui.keyguard.ui.viewmodel.InWindowLauncherAnimationViewModel;
import com.android.systemui.shared.system.smartspace.ILauncherUnlockAnimationController$Stub$Proxy;
import com.android.systemui.shared.system.smartspace.ISysuiUnlockAnimationController;
import com.android.systemui.shared.system.smartspace.SmartspaceState;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class InWindowLauncherUnlockAnimationManager extends ISysuiUnlockAnimationController.Stub {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final InWindowLauncherUnlockAnimationInteractor interactor;
    public ILauncherUnlockAnimationController$Stub$Proxy launcherAnimationController;
    public Float manualUnlockAmount;
    public boolean preparedForUnlock;
    public final CoroutineScope scope;
    public final InWindowLauncherAnimationViewModel viewModel;

    public InWindowLauncherUnlockAnimationManager(InWindowLauncherUnlockAnimationInteractor inWindowLauncherUnlockAnimationInteractor, InWindowLauncherAnimationViewModel inWindowLauncherAnimationViewModel, CoroutineScope coroutineScope) {
        this.interactor = inWindowLauncherUnlockAnimationInteractor;
        this.viewModel = inWindowLauncherAnimationViewModel;
        this.scope = coroutineScope;
    }

    @Override // com.android.systemui.shared.system.smartspace.ISysuiUnlockAnimationController
    public final void onLauncherSmartspaceStateUpdated(SmartspaceState smartspaceState) {
        this.interactor.repository.launcherSmartspaceState.setValue(smartspaceState);
    }

    @Override // com.android.systemui.shared.system.smartspace.ISysuiUnlockAnimationController
    public final void setLauncherUnlockController(String str, ILauncherUnlockAnimationController$Stub$Proxy iLauncherUnlockAnimationController$Stub$Proxy) {
        StateFlowImpl stateFlowImpl = this.interactor.repository.launcherActivityClass;
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, str);
        this.launcherAnimationController = iLauncherUnlockAnimationController$Stub$Proxy;
        InWindowLauncherAnimationViewBinder.bind(this.viewModel, this, this.scope);
    }
}

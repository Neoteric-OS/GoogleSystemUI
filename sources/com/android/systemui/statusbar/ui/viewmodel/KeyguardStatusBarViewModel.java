package com.android.systemui.statusbar.ui.viewmodel;

import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import com.android.systemui.scene.domain.interactor.SceneInteractor;
import com.android.systemui.statusbar.domain.interactor.KeyguardStatusBarInteractor;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class KeyguardStatusBarViewModel {
    public final Flow isBatteryCharging;
    public final FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 isKeyguardUserSwitcherEnabled;
    public final ReadonlyStateFlow isVisible;
    public final FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2 showingHeadsUpStatusBar;

    public KeyguardStatusBarViewModel(CoroutineScope coroutineScope, SceneInteractor sceneInteractor, KeyguardInteractor keyguardInteractor, KeyguardStatusBarInteractor keyguardStatusBarInteractor, BatteryController batteryController) {
        Boolean bool = Boolean.FALSE;
        FlowKt.stateIn(FlowKt.combine(sceneInteractor.currentScene, keyguardInteractor.isDozing, new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(bool), new KeyguardStatusBarViewModel$isVisible$1(4, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), bool);
        FlowConflatedKt.conflatedCallbackFlow(new KeyguardStatusBarViewModel$isBatteryCharging$1(batteryController, null));
        FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 flowKt__ZipKt$combine$$inlined$unsafeFlow$1 = keyguardStatusBarInteractor.isKeyguardUserSwitcherEnabled;
    }
}

package com.android.systemui.keyguard.ui.binder;

import com.android.systemui.keyguard.ui.view.InWindowLauncherUnlockAnimationManager;
import com.android.systemui.keyguard.ui.viewmodel.InWindowLauncherAnimationViewModel;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class InWindowLauncherAnimationViewBinder {
    public static final void bind(InWindowLauncherAnimationViewModel inWindowLauncherAnimationViewModel, InWindowLauncherUnlockAnimationManager inWindowLauncherUnlockAnimationManager, CoroutineScope coroutineScope) {
        BuildersKt.launch$default(coroutineScope, null, null, new InWindowLauncherAnimationViewBinder$bind$1(inWindowLauncherAnimationViewModel, inWindowLauncherUnlockAnimationManager, null), 3);
        BuildersKt.launch$default(coroutineScope, null, null, new InWindowLauncherAnimationViewBinder$bind$2(inWindowLauncherAnimationViewModel, inWindowLauncherUnlockAnimationManager, null), 3);
    }
}

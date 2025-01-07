package com.android.systemui.scene.domain.resolver;

import com.android.compose.animation.scene.SceneKey;
import com.android.systemui.deviceentry.domain.interactor.DeviceEntryInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardEnabledInteractor;
import com.android.systemui.scene.shared.model.SceneFamilies;
import com.android.systemui.scene.shared.model.Scenes;
import java.util.Set;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class HomeSceneFamilyResolver implements SceneResolver {
    public static final Set homeScenes = SetsKt.setOf(Scenes.Gone, Scenes.Lockscreen);
    public final ReadonlyStateFlow resolvedScene;
    public final SceneKey targetFamily = SceneFamilies.Home;

    public HomeSceneFamilyResolver(CoroutineScope coroutineScope, DeviceEntryInteractor deviceEntryInteractor, KeyguardEnabledInteractor keyguardEnabledInteractor) {
        StateFlow stateFlow = (StateFlow) deviceEntryInteractor.canSwipeToEnter$delegate.getValue();
        HomeSceneFamilyResolver$resolvedScene$1 homeSceneFamilyResolver$resolvedScene$1 = new HomeSceneFamilyResolver$resolvedScene$1(5, this, HomeSceneFamilyResolver.class, "homeScene", "homeScene(ZLjava/lang/Boolean;ZZ)Lcom/android/compose/animation/scene/SceneKey;", 4);
        ReadonlyStateFlow readonlyStateFlow = keyguardEnabledInteractor.isKeyguardEnabled;
        ReadonlyStateFlow readonlyStateFlow2 = deviceEntryInteractor.isDeviceEntered;
        ReadonlyStateFlow readonlyStateFlow3 = deviceEntryInteractor.isUnlocked;
        this.resolvedScene = FlowKt.stateIn(FlowKt.combine(readonlyStateFlow, stateFlow, readonlyStateFlow2, readonlyStateFlow3, homeSceneFamilyResolver$resolvedScene$1), coroutineScope, SharingStarted.Companion.Eagerly, homeScene(((Boolean) readonlyStateFlow.getValue()).booleanValue(), (Boolean) ((StateFlow) deviceEntryInteractor.canSwipeToEnter$delegate.getValue()).getValue(), ((Boolean) ((StateFlowImpl) readonlyStateFlow2.$$delegate_0).getValue()).booleanValue(), ((Boolean) ((StateFlowImpl) readonlyStateFlow3.$$delegate_0).getValue()).booleanValue()));
    }

    public static SceneKey homeScene(boolean z, Boolean bool, boolean z2, boolean z3) {
        return !z ? Scenes.Gone : Intrinsics.areEqual(bool, Boolean.TRUE) ? Scenes.Lockscreen : !z2 ? Scenes.Lockscreen : !z3 ? Scenes.Lockscreen : Scenes.Gone;
    }
}

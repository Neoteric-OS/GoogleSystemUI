package com.android.systemui.keyguard.domain.interactor;

import android.util.Log;
import com.android.systemui.CoreStartable;
import com.android.systemui.biometrics.domain.interactor.FingerprintPropertyInteractor;
import com.android.systemui.common.ui.domain.interactor.ConfigurationInteractor;
import com.android.systemui.keyguard.data.repository.KeyguardBlueprintRepository;
import com.android.systemui.keyguard.ui.view.layout.blueprints.transitions.IntraBlueprintTransition;
import com.android.systemui.keyguard.ui.view.layout.sections.SmartspaceSection;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.shade.domain.interactor.ShadeInteractorImpl;
import com.android.systemui.util.Assert;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.SharedFlowImpl;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardBlueprintInteractor implements CoreStartable {
    public final CoroutineScope applicationScope;
    public final StateFlowImpl blueprint;
    public final KeyguardBlueprintInteractor$special$$inlined$map$1 blueprintId;
    public final ConfigurationInteractor configurationInteractor;
    public final FingerprintPropertyInteractor fingerprintPropertyInteractor;
    public final KeyguardBlueprintRepository keyguardBlueprintRepository;
    public final SharedFlowImpl refreshTransition;
    public final SmartspaceSection smartspaceSection;

    public KeyguardBlueprintInteractor(KeyguardBlueprintRepository keyguardBlueprintRepository, CoroutineScope coroutineScope, ShadeInteractor shadeInteractor, ConfigurationInteractor configurationInteractor, FingerprintPropertyInteractor fingerprintPropertyInteractor, SmartspaceSection smartspaceSection) {
        this.keyguardBlueprintRepository = keyguardBlueprintRepository;
        this.applicationScope = coroutineScope;
        this.configurationInteractor = configurationInteractor;
        this.fingerprintPropertyInteractor = fingerprintPropertyInteractor;
        this.smartspaceSection = smartspaceSection;
        this.blueprint = keyguardBlueprintRepository.blueprint;
        this.refreshTransition = keyguardBlueprintRepository.refreshTransition;
        this.blueprintId = new KeyguardBlueprintInteractor$special$$inlined$map$1(((ShadeInteractorImpl) shadeInteractor).$$delegate_1.isShadeLayoutWide());
    }

    public final void refreshBlueprint(IntraBlueprintTransition.Config config) {
        IntraBlueprintTransition.Type type;
        final KeyguardBlueprintRepository keyguardBlueprintRepository = this.keyguardBlueprintRepository;
        keyguardBlueprintRepository.f36assert.getClass();
        Assert.isMainThread();
        IntraBlueprintTransition.Config config2 = keyguardBlueprintRepository.targetTransitionConfig;
        if (((config2 == null || (type = config2.type) == null) ? Integer.MIN_VALUE : type.getPriority()) < config.type.getPriority()) {
            if (keyguardBlueprintRepository.targetTransitionConfig == null) {
                keyguardBlueprintRepository.handler.post(new Runnable() { // from class: com.android.systemui.keyguard.data.repository.KeyguardBlueprintRepository$refreshBlueprint$scheduleCallback$1
                    @Override // java.lang.Runnable
                    public final void run() {
                        KeyguardBlueprintRepository.this.f36assert.getClass();
                        Assert.isMainThread();
                        KeyguardBlueprintRepository keyguardBlueprintRepository2 = KeyguardBlueprintRepository.this;
                        IntraBlueprintTransition.Config config3 = keyguardBlueprintRepository2.targetTransitionConfig;
                        if (config3 != null && !keyguardBlueprintRepository2.refreshTransition.tryEmit(config3)) {
                            Log.e("KeyguardBlueprintRepository", "refreshBlueprint: Failed to emit blueprint refresh: " + config3);
                        }
                        KeyguardBlueprintRepository.this.targetTransitionConfig = null;
                    }
                });
            }
            keyguardBlueprintRepository.targetTransitionConfig = config;
        }
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        KeyguardBlueprintInteractor$start$1 keyguardBlueprintInteractor$start$1 = new KeyguardBlueprintInteractor$start$1(this, null);
        CoroutineScope coroutineScope = this.applicationScope;
        BuildersKt.launch$default(coroutineScope, null, null, keyguardBlueprintInteractor$start$1, 3);
        BuildersKt.launch$default(coroutineScope, null, null, new KeyguardBlueprintInteractor$start$2(this, null), 3);
        BuildersKt.launch$default(coroutineScope, null, null, new KeyguardBlueprintInteractor$start$3(this, null), 3);
    }

    public final void refreshBlueprint(IntraBlueprintTransition.Type type) {
        refreshBlueprint(new IntraBlueprintTransition.Config(type, null, 14));
    }
}

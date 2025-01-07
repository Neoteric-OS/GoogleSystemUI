package com.google.android.systemui.columbus.legacy.gates;

import com.google.android.systemui.columbus.legacy.ColumbusSettings;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SilenceAlertsDisabled extends Gate {
    public final CoroutineDispatcher bgDispatcher;
    public final ColumbusSettings columbusSettings;
    public final SilenceAlertsDisabled$settingsChangeListener$1 settingsChangeListener = new ColumbusSettings.ColumbusSettingsChangeListener() { // from class: com.google.android.systemui.columbus.legacy.gates.SilenceAlertsDisabled$settingsChangeListener$1
        @Override // com.google.android.systemui.columbus.legacy.ColumbusSettings.ColumbusSettingsChangeListener
        public final void onAlertSilenceEnabledChange(boolean z) {
            SilenceAlertsDisabled silenceAlertsDisabled = SilenceAlertsDisabled.this;
            BuildersKt.launch$default(silenceAlertsDisabled.coroutineScope, null, null, new SilenceAlertsDisabled$updateSilenceAlertsEnabled$1(silenceAlertsDisabled, z, null), 3);
        }
    };

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.android.systemui.columbus.legacy.gates.SilenceAlertsDisabled$settingsChangeListener$1] */
    public SilenceAlertsDisabled(ColumbusSettings columbusSettings, CoroutineDispatcher coroutineDispatcher) {
        this.columbusSettings = columbusSettings;
        this.bgDispatcher = coroutineDispatcher;
    }

    @Override // com.google.android.systemui.columbus.legacy.gates.Gate
    public final void onActivate() {
        this.columbusSettings.registerColumbusSettingsChangeListener(this.settingsChangeListener);
        SilenceAlertsDisabled$onActivate$1 silenceAlertsDisabled$onActivate$1 = new SilenceAlertsDisabled$onActivate$1(this, null);
        BuildersKt.launch$default(this.coroutineScope, this.mainDispatcher, null, silenceAlertsDisabled$onActivate$1, 2);
    }

    @Override // com.google.android.systemui.columbus.legacy.gates.Gate
    public final void onDeactivate() {
        this.columbusSettings.listeners.remove(this.settingsChangeListener);
    }
}

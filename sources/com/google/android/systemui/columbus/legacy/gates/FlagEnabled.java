package com.google.android.systemui.columbus.legacy.gates;

import com.google.android.systemui.columbus.legacy.ColumbusSettings;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FlagEnabled extends Gate {
    public final CoroutineDispatcher bgDispatcher;
    public boolean columbusEnabled;
    public final ColumbusSettings columbusSettings;
    public final FlagEnabled$settingsChangeListener$1 settingsChangeListener = new ColumbusSettings.ColumbusSettingsChangeListener() { // from class: com.google.android.systemui.columbus.legacy.gates.FlagEnabled$settingsChangeListener$1
        @Override // com.google.android.systemui.columbus.legacy.ColumbusSettings.ColumbusSettingsChangeListener
        public final void onColumbusEnabledChange(boolean z) {
            FlagEnabled flagEnabled = FlagEnabled.this;
            BuildersKt.launch$default(flagEnabled.coroutineScope, null, null, new FlagEnabled$settingsChangeListener$1$onColumbusEnabledChange$1(flagEnabled, z, null), 3);
        }
    };

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.android.systemui.columbus.legacy.gates.FlagEnabled$settingsChangeListener$1] */
    public FlagEnabled(ColumbusSettings columbusSettings, CoroutineDispatcher coroutineDispatcher) {
        this.columbusSettings = columbusSettings;
        this.bgDispatcher = coroutineDispatcher;
    }

    @Override // com.google.android.systemui.columbus.legacy.gates.Gate
    public final void onActivate() {
        this.columbusSettings.registerColumbusSettingsChangeListener(this.settingsChangeListener);
        BuildersKt.launch$default(this.coroutineScope, null, null, new FlagEnabled$onActivate$1(this, null), 3);
    }

    @Override // com.google.android.systemui.columbus.legacy.gates.Gate
    public final void onDeactivate() {
        this.columbusSettings.listeners.remove(this.settingsChangeListener);
    }

    @Override // com.google.android.systemui.columbus.legacy.gates.Gate
    public final String toString() {
        return super.toString() + BuildersKt.runBlocking(this.mainDispatcher, new FlagEnabled$toString$1(this, null));
    }
}

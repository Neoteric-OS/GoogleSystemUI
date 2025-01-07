package com.google.android.systemui.columbus.legacy;

import com.google.android.systemui.columbus.ColumbusStarter;
import com.google.android.systemui.columbus.legacy.ColumbusSettings;
import dagger.Lazy;
import java.io.PrintWriter;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ColumbusServiceWrapper implements ColumbusStarter {
    public final Lazy columbusService;
    public final ColumbusSettings columbusSettings;
    public final ColumbusServiceWrapper$settingsChangeListener$1 settingsChangeListener;
    public boolean started;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.google.android.systemui.columbus.legacy.ColumbusServiceWrapper$settingsChangeListener$1, com.google.android.systemui.columbus.legacy.ColumbusSettings$ColumbusSettingsChangeListener, java.lang.Object] */
    public ColumbusServiceWrapper(ColumbusSettings columbusSettings, Lazy lazy, Lazy lazy2, Lazy lazy3) {
        this.columbusSettings = columbusSettings;
        this.columbusService = lazy;
        ?? r0 = new ColumbusSettings.ColumbusSettingsChangeListener() { // from class: com.google.android.systemui.columbus.legacy.ColumbusServiceWrapper$settingsChangeListener$1
            @Override // com.google.android.systemui.columbus.legacy.ColumbusSettings.ColumbusSettingsChangeListener
            public final void onColumbusEnabledChange(boolean z) {
                if (z) {
                    ColumbusServiceWrapper columbusServiceWrapper = ColumbusServiceWrapper.this;
                    columbusServiceWrapper.columbusSettings.listeners.remove(columbusServiceWrapper.settingsChangeListener);
                    columbusServiceWrapper.started = true;
                    columbusServiceWrapper.columbusService.get();
                }
            }
        };
        this.settingsChangeListener = r0;
        if (columbusSettings.isColumbusEnabled()) {
            columbusSettings.listeners.remove(r0);
            this.started = true;
            lazy.get();
        } else {
            columbusSettings.registerColumbusSettingsChangeListener(r0);
            lazy2.get();
        }
        lazy3.get();
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        if (this.started) {
            ((ColumbusService) this.columbusService.get()).dump(printWriter, strArr);
        }
    }
}

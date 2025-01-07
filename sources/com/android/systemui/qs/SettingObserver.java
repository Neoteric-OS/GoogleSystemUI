package com.android.systemui.qs;

import android.database.ContentObserver;
import android.os.Handler;
import android.provider.Settings;
import com.android.systemui.util.settings.GlobalSettings;
import com.android.systemui.util.settings.GlobalSettingsImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class SettingObserver extends ContentObserver {
    public final int mDefaultValue;
    public boolean mListening;
    public int mObservedValue;
    public final String mSettingName;
    public final GlobalSettings mSettingsProxy;

    public SettingObserver(GlobalSettings globalSettings, Handler handler, String str, int i) {
        super(handler);
        this.mSettingsProxy = globalSettings;
        this.mSettingName = str;
        this.mDefaultValue = i;
        this.mObservedValue = i;
    }

    public abstract void handleValueChanged(int i, boolean z);

    @Override // android.database.ContentObserver
    public final void onChange(boolean z) {
        int i = this.mSettingsProxy.getInt(this.mDefaultValue, this.mSettingName);
        boolean z2 = i != this.mObservedValue;
        this.mObservedValue = i;
        handleValueChanged(i, z2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v3, types: [com.android.systemui.qs.SettingObserver$$ExternalSyntheticLambda0] */
    public final void setListening(boolean z) {
        if (z == this.mListening) {
            return;
        }
        this.mListening = z;
        if (!z) {
            this.mSettingsProxy.unregisterContentObserverAsync(this);
            this.mObservedValue = this.mDefaultValue;
            return;
        }
        this.mObservedValue = this.mSettingsProxy.getInt(this.mDefaultValue, this.mSettingName);
        GlobalSettings globalSettings = this.mSettingsProxy;
        String str = this.mSettingName;
        ((GlobalSettingsImpl) globalSettings).getClass();
        globalSettings.registerContentObserverAsync(Settings.Global.getUriFor(str), this, new Runnable() { // from class: com.android.systemui.qs.SettingObserver$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                SettingObserver settingObserver = SettingObserver.this;
                settingObserver.mObservedValue = settingObserver.mSettingsProxy.getInt(settingObserver.mDefaultValue, settingObserver.mSettingName);
            }
        });
    }
}

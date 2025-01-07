package com.android.systemui.keyguard.domain.interactor;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.provider.Settings;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class NaturalScrollingSettingObserver {
    public final Context context;
    public boolean isInitialized;
    public boolean isNaturalScrollingEnabled = true;

    public NaturalScrollingSettingObserver(Context context, final Handler handler) {
        this.context = context;
        context.getContentResolver().registerContentObserver(Settings.System.getUriFor("touchpad_natural_scrolling"), false, new ContentObserver(handler) { // from class: com.android.systemui.keyguard.domain.interactor.NaturalScrollingSettingObserver$contentObserver$1
            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                NaturalScrollingSettingObserver.this.update();
            }
        });
    }

    public final void update() {
        boolean z = true;
        try {
            if (Settings.System.getIntForUser(this.context.getContentResolver(), "touchpad_natural_scrolling", -2) != 1) {
                z = false;
            }
        } catch (Settings.SettingNotFoundException unused) {
        }
        this.isNaturalScrollingEnabled = z;
    }
}

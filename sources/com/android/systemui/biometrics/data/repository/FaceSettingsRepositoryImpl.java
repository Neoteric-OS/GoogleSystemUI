package com.android.systemui.biometrics.data.repository;

import android.os.Handler;
import com.android.systemui.util.settings.SecureSettings;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FaceSettingsRepositoryImpl {
    public final Handler mainHandler;
    public final SecureSettings secureSettings;
    public final ConcurrentHashMap userSettings = new ConcurrentHashMap();

    public FaceSettingsRepositoryImpl(Handler handler, SecureSettings secureSettings) {
        this.mainHandler = handler;
        this.secureSettings = secureSettings;
    }
}

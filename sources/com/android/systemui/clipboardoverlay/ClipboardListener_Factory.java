package com.android.systemui.clipboardoverlay;

import android.app.KeyguardManager;
import android.content.Context;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.user.utils.UserScopedServiceImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ClipboardListener_Factory implements Provider {
    public static ClipboardListener newInstance(Context context, DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider, ClipboardToast clipboardToast, UserScopedServiceImpl userScopedServiceImpl, KeyguardManager keyguardManager, UiEventLogger uiEventLogger) {
        return new ClipboardListener(context, switchingProvider, clipboardToast, userScopedServiceImpl, keyguardManager, uiEventLogger);
    }
}

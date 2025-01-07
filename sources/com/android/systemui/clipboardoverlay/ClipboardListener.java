package com.android.systemui.clipboardoverlay;

import android.app.KeyguardManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.UserHandle;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.CoreStartable;
import com.android.systemui.user.utils.UserScopedServiceImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ClipboardListener implements CoreStartable, ClipboardManager.OnPrimaryClipChangedListener {
    static final String EXTRA_SUPPRESS_OVERLAY = "com.android.systemui.SUPPRESS_CLIPBOARD_OVERLAY";
    static final String SHELL_PACKAGE = "com.android.shell";
    public final ClipboardManager mClipboardManager;
    public ClipboardOverlayController mClipboardOverlay;
    public final ClipboardToast mClipboardToast;
    public final Context mContext;
    public final KeyguardManager mKeyguardManager;
    public final DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider mOverlayProvider;
    public final UiEventLogger mUiEventLogger;

    public ClipboardListener(Context context, DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider, ClipboardToast clipboardToast, UserScopedServiceImpl userScopedServiceImpl, KeyguardManager keyguardManager, UiEventLogger uiEventLogger) {
        this.mContext = context;
        this.mOverlayProvider = switchingProvider;
        this.mClipboardToast = clipboardToast;
        this.mClipboardManager = (ClipboardManager) userScopedServiceImpl.forUser(UserHandle.CURRENT);
        this.mKeyguardManager = keyguardManager;
        this.mUiEventLogger = uiEventLogger;
    }

    public static boolean shouldSuppressOverlay(ClipData clipData, String str, boolean z) {
        if ((!z && !SHELL_PACKAGE.equals(str)) || clipData == null || clipData.getDescription().getExtras() == null) {
            return false;
        }
        return clipData.getDescription().getExtras().getBoolean(EXTRA_SUPPRESS_OVERLAY, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0171  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0223  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0229  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0211  */
    @Override // android.content.ClipboardManager.OnPrimaryClipChangedListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onPrimaryClipChanged() {
        /*
            Method dump skipped, instructions count: 647
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.clipboardoverlay.ClipboardListener.onPrimaryClipChanged():void");
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        this.mClipboardManager.addPrimaryClipChangedListener(this);
    }
}

package com.google.android.systemui.assist.uihints;

import android.app.PendingIntent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import com.android.systemui.navigationbar.NavigationModeController;
import com.android.systemui.shared.system.QuickStepContract;
import java.util.Locale;
import java.util.Set;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NgaMessageHandler {
    public static final boolean VERBOSE;
    public final AssistantPresenceHandler mAssistantPresenceHandler;
    public final Set mConfigInfoListeners;
    public final Set mGoBackListeners;
    public final Handler mHandler;
    public boolean mIsGestureNav;
    public final Set mNavBarVisibilityListeners;
    public final NgaUiController mNgaUiController;
    public final Set mSwipeListeners;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ConfigInfo {
        public final PendingIntent capabilitiesCallback;
        public final boolean ngaIsAssistant;

        public ConfigInfo(Bundle bundle) {
            this.ngaIsAssistant = bundle.getBoolean("nga_is_assistant");
            this.capabilitiesCallback = (PendingIntent) bundle.getParcelable("capabilities_callback");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface ConfigInfoListener {
        void onConfigInfo(ConfigInfo configInfo);
    }

    static {
        String str = Build.TYPE;
        Locale locale = Locale.ROOT;
        VERBOSE = str.toLowerCase(locale).contains("debug") || str.toLowerCase(locale).equals("eng");
    }

    public NgaMessageHandler(NgaUiController ngaUiController, AssistantPresenceHandler assistantPresenceHandler, NavigationModeController navigationModeController, Set set, Set set2, Set set3, Set set4, Handler handler) {
        this.mNgaUiController = ngaUiController;
        this.mAssistantPresenceHandler = assistantPresenceHandler;
        this.mConfigInfoListeners = set;
        this.mGoBackListeners = set2;
        this.mSwipeListeners = set3;
        this.mNavBarVisibilityListeners = set4;
        this.mHandler = handler;
        this.mIsGestureNav = QuickStepContract.isGesturalMode(navigationModeController.addListener(new NavigationModeController.ModeChangedListener() { // from class: com.google.android.systemui.assist.uihints.NgaMessageHandler$$ExternalSyntheticLambda0
            @Override // com.android.systemui.navigationbar.NavigationModeController.ModeChangedListener
            public final void onNavigationModeChanged(int i) {
                NgaMessageHandler.this.mIsGestureNav = QuickStepContract.isGesturalMode(i);
            }
        }));
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01f6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void processBundle(final android.os.Bundle r31, final com.google.android.systemui.assist.AssistManagerGoogle$$ExternalSyntheticLambda2 r32) {
        /*
            Method dump skipped, instructions count: 531
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.assist.uihints.NgaMessageHandler.processBundle(android.os.Bundle, com.google.android.systemui.assist.AssistManagerGoogle$$ExternalSyntheticLambda2):void");
    }
}

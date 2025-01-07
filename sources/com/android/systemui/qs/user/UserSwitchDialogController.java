package com.android.systemui.qs.user;

import android.content.DialogInterface;
import android.content.Intent;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UserSwitchDialogController {
    public static final Intent USER_SETTINGS_INTENT = new Intent("android.settings.USER_SETTINGS");
    public final ActivityStarter activityStarter;
    public final SystemUIDialog.Factory dialogFactory;
    public final DialogTransitionAnimator dialogTransitionAnimator;
    public final FalsingManager falsingManager;
    public final UiEventLogger uiEventLogger;
    public final DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider userDetailViewAdapterProvider;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface DialogShower extends DialogInterface {
    }

    public UserSwitchDialogController(DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider, ActivityStarter activityStarter, FalsingManager falsingManager, DialogTransitionAnimator dialogTransitionAnimator, UiEventLogger uiEventLogger, SystemUIDialog.Factory factory) {
        this.userDetailViewAdapterProvider = switchingProvider;
        this.activityStarter = activityStarter;
        this.falsingManager = falsingManager;
        this.dialogTransitionAnimator = dialogTransitionAnimator;
        this.uiEventLogger = uiEventLogger;
        this.dialogFactory = factory;
    }
}

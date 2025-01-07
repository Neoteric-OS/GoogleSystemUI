package com.android.systemui.controls.settings;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.settings.UserFileManager;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.util.settings.SecureSettings;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ControlsSettingsDialogManagerImpl implements ControlsSettingsDialogManager {
    public final ActivityStarter activityStarter;
    public final ControlsSettingsRepository controlsSettingsRepository;
    public AlertDialog dialog;
    public final Function2 dialogProvider;
    public final SecureSettings secureSettings;
    public final UserFileManager userFileManager;
    public final UserTracker userTracker;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DialogListener implements DialogInterface.OnClickListener, DialogInterface.OnCancelListener {
        public final int attempts;
        public final Function0 onComplete;
        public final SharedPreferences prefs;

        public DialogListener(SharedPreferences sharedPreferences, int i, Function0 function0) {
            this.prefs = sharedPreferences;
            this.attempts = i;
            this.onComplete = function0;
        }

        @Override // android.content.DialogInterface.OnCancelListener
        public final void onCancel(DialogInterface dialogInterface) {
            if (dialogInterface == null) {
                return;
            }
            if (this.attempts < 2) {
                this.prefs.edit().putInt("show_settings_attempts", this.attempts + 1).apply();
            }
            this.onComplete.invoke();
        }

        @Override // android.content.DialogInterface.OnClickListener
        public final void onClick(DialogInterface dialogInterface, int i) {
            if (dialogInterface == null) {
                return;
            }
            if (i == -1) {
                final List mutableListOf = CollectionsKt__CollectionsKt.mutableListOf("lockscreen_allow_trivial_controls");
                if (!((Boolean) ((ControlsSettingsRepositoryImpl) ControlsSettingsDialogManagerImpl.this.controlsSettingsRepository).canShowControlsInLockscreen.getValue()).booleanValue()) {
                    mutableListOf.add("lockscreen_show_controls");
                }
                final ControlsSettingsDialogManagerImpl controlsSettingsDialogManagerImpl = ControlsSettingsDialogManagerImpl.this;
                final Function0 function0 = this.onComplete;
                controlsSettingsDialogManagerImpl.getClass();
                controlsSettingsDialogManagerImpl.activityStarter.dismissKeyguardThenExecute(new ActivityStarter.OnDismissAction() { // from class: com.android.systemui.controls.settings.ControlsSettingsDialogManagerImpl$turnOnSettingSecurely$action$1
                    @Override // com.android.systemui.plugins.ActivityStarter.OnDismissAction
                    public final boolean onDismiss() {
                        for (String str : mutableListOf) {
                            ControlsSettingsDialogManagerImpl controlsSettingsDialogManagerImpl2 = controlsSettingsDialogManagerImpl;
                            controlsSettingsDialogManagerImpl2.secureSettings.putIntForUser(str, 1, ((UserTrackerImpl) controlsSettingsDialogManagerImpl2.userTracker).getUserId());
                        }
                        function0.invoke();
                        return true;
                    }
                }, new ControlsSettingsDialogManagerImpl$maybeShowDialog$1(1, function0), true);
            } else {
                this.onComplete.invoke();
            }
            if (this.attempts != 2) {
                this.prefs.edit().putInt("show_settings_attempts", 2).apply();
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SettingsDialog extends AlertDialog {
        public SettingsDialog(int i, Context context) {
            super(context, i);
        }
    }

    public ControlsSettingsDialogManagerImpl(SecureSettings secureSettings, UserFileManager userFileManager, ControlsSettingsRepository controlsSettingsRepository, UserTracker userTracker, ActivityStarter activityStarter, Function2 function2) {
        this.secureSettings = secureSettings;
        this.userFileManager = userFileManager;
        this.controlsSettingsRepository = controlsSettingsRepository;
        this.userTracker = userTracker;
        this.activityStarter = activityStarter;
        this.dialogProvider = function2;
    }

    public ControlsSettingsDialogManagerImpl(SecureSettings secureSettings, UserFileManager userFileManager, ControlsSettingsRepository controlsSettingsRepository, UserTracker userTracker, ActivityStarter activityStarter) {
        this(secureSettings, userFileManager, controlsSettingsRepository, userTracker, activityStarter, new Function2() { // from class: com.android.systemui.controls.settings.ControlsSettingsDialogManagerImpl.1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return new SettingsDialog(((Number) obj2).intValue(), (Context) obj);
            }
        });
    }
}

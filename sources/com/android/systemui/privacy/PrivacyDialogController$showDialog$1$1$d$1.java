package com.android.systemui.privacy;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.UserHandle;
import com.android.systemui.plugins.ActivityStarter;
import kotlin.Unit;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final /* synthetic */ class PrivacyDialogController$showDialog$1$1$d$1 extends FunctionReferenceImpl implements Function4 {
    @Override // kotlin.jvm.functions.Function4
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        PrivacyDialog privacyDialog;
        String str = (String) obj;
        int intValue = ((Number) obj2).intValue();
        Intent intent = (Intent) obj4;
        final PrivacyDialogController privacyDialogController = (PrivacyDialogController) this.receiver;
        if (intent == null) {
            privacyDialogController.getClass();
            intent = new Intent("android.intent.action.MANAGE_APP_PERMISSIONS");
            intent.putExtra("android.intent.extra.PACKAGE_NAME", str);
            intent.putExtra("android.intent.extra.USER", UserHandle.of(intValue));
        }
        privacyDialogController.uiEventLogger.log(PrivacyDialogEvent.PRIVACY_DIALOG_ITEM_CLICKED_TO_APP_SETTINGS, intValue, str);
        privacyDialogController.privacyLogger.logStartSettingsActivityFromDialog(intValue, str);
        if (!privacyDialogController.keyguardStateController.isUnlocked() && (privacyDialog = privacyDialogController.dialog) != null) {
            privacyDialog.hide();
        }
        privacyDialogController.activityStarter.startActivity(intent, true, new ActivityStarter.Callback() { // from class: com.android.systemui.privacy.PrivacyDialogController$startActivity$1
            @Override // com.android.systemui.plugins.ActivityStarter.Callback
            public final void onActivityStarted(int i) {
                boolean isStartResultSuccessful = ActivityManager.isStartResultSuccessful(i);
                PrivacyDialogController privacyDialogController2 = PrivacyDialogController.this;
                if (isStartResultSuccessful) {
                    PrivacyDialog privacyDialog2 = privacyDialogController2.dialog;
                    if (privacyDialog2 != null) {
                        privacyDialog2.dismiss();
                        return;
                    }
                    return;
                }
                PrivacyDialog privacyDialog3 = privacyDialogController2.dialog;
                if (privacyDialog3 != null) {
                    privacyDialog3.show();
                }
            }
        });
        return Unit.INSTANCE;
    }
}

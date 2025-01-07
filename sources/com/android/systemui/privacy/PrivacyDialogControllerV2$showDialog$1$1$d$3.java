package com.android.systemui.privacy;

import android.content.Intent;
import com.android.systemui.plugins.ActivityStarter;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final /* synthetic */ class PrivacyDialogControllerV2$showDialog$1$1$d$3 extends FunctionReferenceImpl implements Function0 {
    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        PrivacyDialogV2 privacyDialogV2;
        PrivacyDialogControllerV2 privacyDialogControllerV2 = (PrivacyDialogControllerV2) this.receiver;
        privacyDialogControllerV2.uiEventLogger.log(PrivacyDialogEvent.PRIVACY_DIALOG_CLICK_TO_PRIVACY_DASHBOARD);
        privacyDialogControllerV2.privacyLogger.logStartPrivacyDashboardFromDialog();
        Intent intent = new Intent("android.intent.action.REVIEW_PERMISSION_USAGE");
        if (!privacyDialogControllerV2.keyguardStateController.isUnlocked() && (privacyDialogV2 = privacyDialogControllerV2.dialog) != null) {
            privacyDialogV2.hide();
        }
        privacyDialogControllerV2.activityStarter.startActivity(intent, true, (ActivityStarter.Callback) new PrivacyDialogControllerV2$startActivity$1(privacyDialogControllerV2));
        return Unit.INSTANCE;
    }
}

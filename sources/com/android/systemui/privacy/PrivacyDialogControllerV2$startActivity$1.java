package com.android.systemui.privacy;

import android.app.ActivityManager;
import com.android.systemui.plugins.ActivityStarter;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PrivacyDialogControllerV2$startActivity$1 implements ActivityStarter.Callback {
    public final /* synthetic */ PrivacyDialogControllerV2 this$0;

    public PrivacyDialogControllerV2$startActivity$1(PrivacyDialogControllerV2 privacyDialogControllerV2) {
        this.this$0 = privacyDialogControllerV2;
    }

    @Override // com.android.systemui.plugins.ActivityStarter.Callback
    public final void onActivityStarted(int i) {
        boolean isStartResultSuccessful = ActivityManager.isStartResultSuccessful(i);
        PrivacyDialogControllerV2 privacyDialogControllerV2 = this.this$0;
        if (isStartResultSuccessful) {
            PrivacyDialogV2 privacyDialogV2 = privacyDialogControllerV2.dialog;
            if (privacyDialogV2 != null) {
                privacyDialogV2.dismiss();
                return;
            }
            return;
        }
        PrivacyDialogV2 privacyDialogV22 = privacyDialogControllerV2.dialog;
        if (privacyDialogV22 != null) {
            privacyDialogV22.show();
        }
    }
}

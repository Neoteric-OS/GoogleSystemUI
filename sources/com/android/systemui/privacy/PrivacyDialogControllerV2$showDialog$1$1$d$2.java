package com.android.systemui.privacy;

import android.app.ActivityManager;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final /* synthetic */ class PrivacyDialogControllerV2$showDialog$1$1$d$2 extends FunctionReferenceImpl implements Function2 {
    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        String str = (String) obj;
        int intValue = ((Number) obj2).intValue();
        PrivacyDialogControllerV2 privacyDialogControllerV2 = (PrivacyDialogControllerV2) this.receiver;
        privacyDialogControllerV2.uiEventLogger.log(PrivacyDialogEvent.PRIVACY_DIALOG_ITEM_CLICKED_TO_CLOSE_APP, intValue, str);
        privacyDialogControllerV2.privacyLogger.logCloseAppFromDialog(intValue, str);
        ActivityManager.getService().stopAppForUser(str, intValue);
        return Unit.INSTANCE;
    }
}

package com.android.systemui.statusbar.pipeline.shared;

import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import com.android.keyguard.logging.BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2$$ExternalSyntheticOutline0;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.statusbar.pipeline.shared.data.model.DefaultConnectionModel;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final /* synthetic */ class ConnectivityInputLogger$logDefaultConnectionsChanged$2 extends FunctionReferenceImpl implements Function1 {
    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        LogMessage logMessage = (LogMessage) obj;
        ((DefaultConnectionModel) this.receiver).getClass();
        boolean bool1 = logMessage.getBool1();
        boolean bool2 = logMessage.getBool2();
        boolean bool3 = logMessage.getBool3();
        boolean bool4 = logMessage.getBool4();
        String str = logMessage.getInt1() == 1 ? "true" : "false";
        StringBuilder m = BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2$$ExternalSyntheticOutline0.m("DefaultConnectionModel(wifi.isDefault=", ", mobile.isDefault=", ", carrierMerged.isDefault=", bool1, bool2);
        BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2$$ExternalSyntheticOutline0.m(m, bool3, ", ethernet.isDefault=", bool4, ", isValidated=");
        return ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(m, str, ")");
    }
}

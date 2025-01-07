package com.android.systemui.biometrics;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.biometrics.BiometricSourceType;
import java.util.Objects;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BiometricNotificationBroadcastReceiver extends BroadcastReceiver {
    public final Context mContext;
    public final BiometricNotificationDialogFactory mNotificationDialogFactory;

    public BiometricNotificationBroadcastReceiver(Context context, BiometricNotificationDialogFactory biometricNotificationDialogFactory) {
        this.mContext = context;
        this.mNotificationDialogFactory = biometricNotificationDialogFactory;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        char c;
        String action = intent.getAction();
        int hashCode = action.hashCode();
        if (hashCode != -2132453382) {
            if (hashCode == -244988429 && action.equals("face_action_show_reenroll_dialog")) {
                c = 0;
            }
            c = 65535;
        } else {
            if (action.equals("fingerprint_action_show_reenroll_dialog")) {
                c = 1;
            }
            c = 65535;
        }
        if (c == 0) {
            BiometricNotificationDialogFactory biometricNotificationDialogFactory = this.mNotificationDialogFactory;
            int userId = this.mContext.getUserId();
            Context context2 = this.mContext;
            Objects.requireNonNull(context2);
            biometricNotificationDialogFactory.createReenrollDialog(userId, new BiometricNotificationBroadcastReceiver$$ExternalSyntheticLambda0(context2), BiometricSourceType.FACE, false).show();
            return;
        }
        if (c != 1) {
            return;
        }
        BiometricNotificationDialogFactory biometricNotificationDialogFactory2 = this.mNotificationDialogFactory;
        int userId2 = this.mContext.getUserId();
        Context context3 = this.mContext;
        Objects.requireNonNull(context3);
        biometricNotificationDialogFactory2.createReenrollDialog(userId2, new BiometricNotificationBroadcastReceiver$$ExternalSyntheticLambda0(context3), BiometricSourceType.FINGERPRINT, intent.getBooleanExtra("is_reenroll_forced", false)).show();
    }
}

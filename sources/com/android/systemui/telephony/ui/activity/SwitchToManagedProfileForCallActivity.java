package com.android.systemui.telephony.ui.activity;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.UserHandle;
import android.telecom.TelecomManager;
import android.util.Log;
import com.android.internal.app.AlertActivity;
import com.android.internal.app.AlertController;
import com.android.wm.shell.R;
import kotlin.Pair;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SwitchToManagedProfileForCallActivity extends AlertActivity implements DialogInterface.OnClickListener {
    public int managedProfileUserId = -10000;
    public Uri phoneNumber;
    public Intent positiveActionIntent;
    public final TelecomManager telecomManager;

    public SwitchToManagedProfileForCallActivity(TelecomManager telecomManager) {
        this.telecomManager = telecomManager;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        if (i == -1) {
            try {
                Context applicationContext = getApplicationContext();
                Intent intent = this.positiveActionIntent;
                if (intent == null) {
                    intent = null;
                }
                applicationContext.startActivityAsUser(intent, ActivityOptions.makeOpenCrossProfileAppsAnimation().toBundle(), UserHandle.of(this.managedProfileUserId));
            } catch (Exception e) {
                Log.e("SwitchToManagedProfileForCallActivity", "Failed to launch activity", e);
            }
        }
        finish();
    }

    public final void onCreate(Bundle bundle) {
        Pair pair;
        getWindow().addSystemFlags(524288);
        super.onCreate(bundle);
        Uri data = getIntent().getData();
        if (data == null) {
            data = Uri.EMPTY;
        }
        this.phoneNumber = data;
        this.managedProfileUserId = getIntent().getIntExtra("android.telecom.extra.MANAGED_PROFILE_USER_ID", -10000);
        AlertController.AlertParams alertParams = ((AlertActivity) this).mAlertParams;
        alertParams.mTitle = getString(R.string.call_from_work_profile_title);
        alertParams.mMessage = getString(R.string.call_from_work_profile_text);
        alertParams.mNegativeButtonText = getString(R.string.call_from_work_profile_close);
        alertParams.mPositiveButtonListener = this;
        alertParams.mNegativeButtonListener = this;
        TelecomManager telecomManager = this.telecomManager;
        if ((telecomManager != null ? telecomManager.getDefaultDialerPackage(UserHandle.of(this.managedProfileUserId)) : null) != null) {
            Uri uri = this.phoneNumber;
            pair = new Pair(new Intent("android.intent.action.CALL", uri != null ? uri : null), Integer.valueOf(R.string.call_from_work_profile_action));
        } else {
            pair = new Pair(new Intent("android.intent.action.VIEW", Uri.parse("market://search?q=dialer")), Integer.valueOf(R.string.install_dialer_on_work_profile_action));
        }
        Intent intent = (Intent) pair.component1();
        int intValue = ((Number) pair.component2()).intValue();
        this.positiveActionIntent = intent;
        ((AlertActivity) this).mAlertParams.mPositiveButtonText = getString(intValue);
        setupAlert();
    }
}

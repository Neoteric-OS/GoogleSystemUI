package com.android.systemui.power;

import android.content.DialogInterface;
import android.content.Intent;
import com.android.settingslib.fuelgauge.BatterySaverUtils;
import com.android.systemui.plugins.ActivityStarter;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class PowerNotificationWarnings$$ExternalSyntheticLambda1 implements DialogInterface.OnClickListener {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ PowerNotificationWarnings f$0;

    public /* synthetic */ PowerNotificationWarnings$$ExternalSyntheticLambda1(PowerNotificationWarnings powerNotificationWarnings, int i) {
        this.$r8$classId = i;
        this.f$0 = powerNotificationWarnings;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        int i2 = this.$r8$classId;
        PowerNotificationWarnings powerNotificationWarnings = this.f$0;
        switch (i2) {
            case 0:
                powerNotificationWarnings.mUsbHighTempDialog = null;
                break;
            case 1:
                String string = powerNotificationWarnings.mContext.getString(R.string.high_temp_alarm_help_url);
                Intent intent = new Intent();
                intent.setClassName("com.android.settings", "com.android.settings.HelpTrampoline");
                intent.putExtra("android.intent.extra.TEXT", string);
                powerNotificationWarnings.mActivityStarter.startActivity(intent, true, (ActivityStarter.Callback) new PowerNotificationWarnings$$ExternalSyntheticLambda4(0, powerNotificationWarnings));
                break;
            case 2:
                BatterySaverUtils.setPowerSaveMode(powerNotificationWarnings.mContext, true, false, 1);
                powerNotificationWarnings.logEvent(BatteryWarningEvents$LowBatteryWarningEvent.SAVER_CONFIRM_OK);
                break;
            default:
                powerNotificationWarnings.getClass();
                powerNotificationWarnings.logEvent(BatteryWarningEvents$LowBatteryWarningEvent.SAVER_CONFIRM_CANCEL);
                break;
        }
    }
}

package com.google.android.systemui.screenprotector;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class ScreenProtectorNotificationActivity extends Activity implements View.OnClickListener {
    public TextView mDisableNotification;
    public TextView mGoToSettings;
    public TextView mGotIt;
    public TextView mMessage;
    public byte mResponse = 0;
    public TextView mTitle;

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        if (view == this.mGoToSettings) {
            this.mResponse = (byte) 2;
            Log.i("ScreenProtectorNotificationActivity", "Button clicked - Go To Settings");
            Bundle bundle = new Bundle();
            bundle.putString(":settings:fragment_args_key", "touch_sensitivity");
            Intent intent = new Intent("com.google.android.settings.touch.TOUCH_SENSITIVITY_SETTINGS");
            intent.addFlags(402653184);
            intent.addCategory("android.intent.category.DEFAULT");
            intent.putExtra(":settings:show_fragment_args", bundle);
            startActivity(intent);
        } else if (view == this.mDisableNotification) {
            this.mResponse = (byte) 4;
            Log.i("ScreenProtectorNotificationActivity", "Button clicked - Disable Notification");
            ScreenProtectorSharedPreferenceUtils.getSharedPreferences(getApplicationContext()).edit().putBoolean("notification_enabled", false).apply();
        } else if (view == this.mGotIt) {
            this.mResponse = (byte) 3;
            Log.i("ScreenProtectorNotificationActivity", "Button clicked -  Got It");
        }
        finish();
    }

    @Override // android.app.Activity
    public final void onCreate(Bundle bundle) {
        Window window = getWindow();
        window.addSystemFlags(524288);
        window.setType(2008);
        requestWindowFeature(1);
        super.onCreate(bundle);
        setContentView(R.layout.screen_protector_notification_dialog);
        int intExtra = getIntent().getIntExtra("notify_id", 0);
        this.mDisableNotification = (TextView) findViewById(R.id.disableNotification);
        this.mGotIt = (TextView) findViewById(R.id.gotIt);
        this.mGoToSettings = (TextView) findViewById(R.id.goToSettings);
        this.mMessage = (TextView) findViewById(R.id.message);
        this.mTitle = (TextView) findViewById(R.id.title);
        this.mDisableNotification.setText(getString(R.string.screen_protector_disable_notification));
        this.mGotIt.setText(getString(R.string.screen_protector_got_it));
        this.mGoToSettings.setText(getString(R.string.screen_protector_go_to_settings));
        if (intExtra == 2) {
            this.mMessage.setText(getString(R.string.screen_protector_absent_message));
        } else {
            this.mMessage.setText(getString(R.string.screen_protector_present_message));
        }
        this.mTitle.setText(getString(R.string.screen_protector_mode_title));
        ScreenProtectorSharedPreferenceUtils.getSharedPreferences(getApplicationContext()).edit().putInt("notification_response", -1).apply();
        this.mDisableNotification.setOnClickListener(this);
        this.mGotIt.setOnClickListener(this);
        this.mGoToSettings.setOnClickListener(this);
    }

    @Override // android.app.Activity
    public final void onDestroy() {
        Context applicationContext = getApplicationContext();
        ScreenProtectorSharedPreferenceUtils.getSharedPreferences(applicationContext).edit().putInt("notification_response", this.mResponse).apply();
        super.onDestroy();
    }
}

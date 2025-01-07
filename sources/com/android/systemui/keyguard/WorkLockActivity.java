package com.android.systemui.keyguard;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityOptions;
import android.app.KeyguardManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.UserHandle;
import android.os.UserManager;
import android.widget.ImageView;
import android.window.OnBackInvokedCallback;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class WorkLockActivity extends Activity {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final BroadcastDispatcher mBroadcastDispatcher;
    public KeyguardManager mKgm;
    public final PackageManager mPackageManager;
    public final UserManager mUserManager;
    public final WorkLockActivity$$ExternalSyntheticLambda0 mBackCallback = new OnBackInvokedCallback() { // from class: com.android.systemui.keyguard.WorkLockActivity$$ExternalSyntheticLambda0
        @Override // android.window.OnBackInvokedCallback
        public final void onBackInvoked() {
            WorkLockActivity workLockActivity = WorkLockActivity.this;
            int i = WorkLockActivity.$r8$clinit;
            workLockActivity.getClass();
        }
    };
    public final AnonymousClass1 mLockEventReceiver = new BroadcastReceiver() { // from class: com.android.systemui.keyguard.WorkLockActivity.1
        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            int targetUserId = WorkLockActivity.this.getTargetUserId();
            if (intent.getIntExtra("android.intent.extra.user_handle", targetUserId) != targetUserId || WorkLockActivity.this.getKeyguardManager().isDeviceLocked(targetUserId)) {
                return;
            }
            WorkLockActivity.this.finish();
        }
    };

    /* JADX WARN: Type inference failed for: r0v0, types: [com.android.systemui.keyguard.WorkLockActivity$$ExternalSyntheticLambda0] */
    /* JADX WARN: Type inference failed for: r0v1, types: [com.android.systemui.keyguard.WorkLockActivity$1] */
    public WorkLockActivity(BroadcastDispatcher broadcastDispatcher, UserManager userManager, PackageManager packageManager) {
        this.mBroadcastDispatcher = broadcastDispatcher;
        this.mUserManager = userManager;
        this.mPackageManager = packageManager;
    }

    public Drawable getBadgedIcon() {
        String stringExtra = getIntent().getStringExtra("android.intent.extra.PACKAGE_NAME");
        if (stringExtra.isEmpty()) {
            return null;
        }
        try {
            UserManager userManager = this.mUserManager;
            PackageManager packageManager = this.mPackageManager;
            return userManager.getBadgedIconForUser(packageManager.getApplicationIcon(packageManager.getApplicationInfoAsUser(stringExtra, PackageManager.ApplicationInfoFlags.of(0L), getTargetUserId())), UserHandle.of(getTargetUserId()));
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    public final KeyguardManager getKeyguardManager() {
        if (this.mKgm == null) {
            this.mKgm = (KeyguardManager) getSystemService("keyguard");
        }
        return this.mKgm;
    }

    public final int getTargetUserId() {
        return getIntent().getIntExtra("android.intent.extra.USER_ID", UserHandle.myUserId());
    }

    @Override // android.app.Activity
    public final void onActivityResult(int i, int i2, Intent intent) {
        if (i != 1 || i2 == -1) {
            return;
        }
        Intent intent2 = new Intent("android.intent.action.MAIN");
        intent2.addCategory("android.intent.category.HOME");
        intent2.setFlags(268435456);
        startActivity(intent2);
    }

    @Override // android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mBroadcastDispatcher.registerReceiver(this.mLockEventReceiver, new IntentFilter("android.intent.action.DEVICE_LOCKED_CHANGED"), null, UserHandle.ALL);
        if (!getKeyguardManager().isDeviceLocked(getTargetUserId())) {
            finish();
            return;
        }
        setOverlayWithDecorCaptionEnabled(true);
        setContentView(R.layout.auth_biometric_background);
        Drawable badgedIcon = getBadgedIcon();
        if (badgedIcon != null) {
            ((ImageView) findViewById(R.id.icon)).setImageDrawable(badgedIcon);
        }
        getOnBackInvokedDispatcher().registerOnBackInvokedCallback(0, this.mBackCallback);
    }

    @Override // android.app.Activity
    public final void onDestroy() {
        unregisterBroadcastReceiver();
        getOnBackInvokedDispatcher().unregisterOnBackInvokedCallback(this.mBackCallback);
        super.onDestroy();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public final void onWindowFocusChanged(boolean z) {
        Intent createConfirmDeviceCredentialIntent;
        if (!z || isFinishing() || !getKeyguardManager().isDeviceLocked(getTargetUserId()) || (createConfirmDeviceCredentialIntent = getKeyguardManager().createConfirmDeviceCredentialIntent(null, null, getTargetUserId(), true)) == null) {
            return;
        }
        ActivityOptions makeBasic = ActivityOptions.makeBasic();
        makeBasic.setLaunchTaskId(getTaskId());
        PendingIntent activity = PendingIntent.getActivity(this, -1, getIntent(), 1409286144, makeBasic.toBundle());
        if (activity != null) {
            createConfirmDeviceCredentialIntent.putExtra("android.intent.extra.INTENT", activity.getIntentSender());
        }
        ActivityOptions makeBasic2 = ActivityOptions.makeBasic();
        makeBasic2.setLaunchTaskId(getTaskId());
        makeBasic2.setTaskOverlay(true, true);
        createConfirmDeviceCredentialIntent.putExtra("android.app.KeyguardManager.FORCE_TASK_OVERLAY", true);
        startActivityForResult(createConfirmDeviceCredentialIntent, 1, makeBasic2.toBundle());
    }

    public void unregisterBroadcastReceiver() {
        this.mBroadcastDispatcher.unregisterReceiver(this.mLockEventReceiver);
    }

    @Override // android.app.Activity
    public final void onBackPressed() {
    }

    @Override // android.app.Activity
    public final void setTaskDescription(ActivityManager.TaskDescription taskDescription) {
    }
}

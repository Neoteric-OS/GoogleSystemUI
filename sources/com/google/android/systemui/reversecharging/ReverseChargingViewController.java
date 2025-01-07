package com.google.android.systemui.reversecharging;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import androidx.compose.foundation.text.ValidatingOffsetMappingKt$$ExternalSyntheticOutline0;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.statusbar.NotificationShadeWindowController;
import com.android.systemui.statusbar.phone.ui.StatusBarIconController;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.wm.shell.R;
import com.google.android.systemui.keyguard.domain.interactor.AmbientIndicationInteractor;
import com.google.android.systemui.statusbar.KeyguardIndicationControllerGoogle;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ReverseChargingViewController extends BroadcastReceiver implements LifecycleOwner, BatteryController.BatteryStateChangeCallback {
    public static final boolean DEBUG = Log.isLoggable("ReverseChargingViewCtrl", 3);
    public final AmbientIndicationInteractor mAmbientIndicationInteractor;
    public final BatteryController mBatteryController;
    public final BroadcastDispatcher mBroadcastDispatcher;
    public String mContentDescription;
    public final Context mContext;
    public final KeyguardIndicationControllerGoogle mKeyguardIndicationController;
    public int mLevel;
    public final LifecycleRegistry mLifecycle = new LifecycleRegistry(this);
    public final Executor mMainExecutor;
    public String mName;
    public final NotificationShadeWindowController mNotificationShadeWindowController;
    public boolean mProvidingBattery;
    public boolean mReverse;
    public String mReverseCharging;
    public String mSlotReverseCharging;
    public final StatusBarIconController mStatusBarIconController;

    public ReverseChargingViewController(Context context, BatteryController batteryController, NotificationShadeWindowController notificationShadeWindowController, StatusBarIconController statusBarIconController, BroadcastDispatcher broadcastDispatcher, Executor executor, KeyguardIndicationControllerGoogle keyguardIndicationControllerGoogle, AmbientIndicationInteractor ambientIndicationInteractor) {
        this.mBatteryController = batteryController;
        this.mStatusBarIconController = statusBarIconController;
        this.mNotificationShadeWindowController = notificationShadeWindowController;
        this.mContext = context;
        this.mBroadcastDispatcher = broadcastDispatcher;
        this.mMainExecutor = executor;
        this.mKeyguardIndicationController = keyguardIndicationControllerGoogle;
        this.mAmbientIndicationInteractor = ambientIndicationInteractor;
        this.mReverseCharging = context.getString(R.string.charging_reverse_text);
        this.mSlotReverseCharging = context.getString(R.string.status_bar_google_reverse_charging);
        this.mContentDescription = context.getString(R.string.reverse_charging_on_notification_title);
    }

    @Override // androidx.lifecycle.LifecycleOwner
    public final Lifecycle getLifecycle() {
        return this.mLifecycle;
    }

    @Override // com.android.systemui.statusbar.policy.BatteryController.BatteryStateChangeCallback
    public final void onBatteryLevelChanged(int i, boolean z, boolean z2) {
        this.mReverse = this.mBatteryController.isReverseOn();
        if (DEBUG) {
            Log.d("ReverseChargingViewCtrl", "onBatteryLevelChanged(): rtx=" + (this.mReverse ? 1 : 0) + " level=" + this.mLevel + " name=" + this.mName + " this=" + this);
        }
        this.mMainExecutor.execute(new ReverseChargingViewController$$ExternalSyntheticLambda0(this));
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.LOCALE_CHANGED")) {
            this.mReverseCharging = this.mContext.getString(R.string.charging_reverse_text);
            this.mSlotReverseCharging = this.mContext.getString(R.string.status_bar_google_reverse_charging);
            this.mContentDescription = this.mContext.getString(R.string.reverse_charging_on_notification_title);
            if (DEBUG) {
                Log.d("ReverseChargingViewCtrl", "onReceive(): ACTION_LOCALE_CHANGED this=" + this);
            }
            this.mMainExecutor.execute(new ReverseChargingViewController$$ExternalSyntheticLambda0(this));
        }
    }

    @Override // com.android.systemui.statusbar.policy.BatteryController.BatteryStateChangeCallback
    public final void onReverseChanged(int i, String str, boolean z) {
        this.mReverse = z;
        this.mLevel = i;
        this.mName = str;
        this.mProvidingBattery = z && i >= 0;
        if (DEBUG) {
            StringBuilder m = ValidatingOffsetMappingKt$$ExternalSyntheticOutline0.m(z ? 1 : 0, i, "onReverseChanged(): rtx=", " level=", " name=");
            m.append(str);
            m.append(" this=");
            m.append(this);
            Log.d("ReverseChargingViewCtrl", m.toString());
        }
        this.mMainExecutor.execute(new ReverseChargingViewController$$ExternalSyntheticLambda0(this));
    }
}

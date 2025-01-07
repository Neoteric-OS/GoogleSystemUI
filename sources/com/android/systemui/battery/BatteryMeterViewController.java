package com.android.systemui.battery;

import android.R;
import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerExecutor;
import android.provider.Settings;
import android.text.TextUtils;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import com.android.keyguard.KeyguardClockSwitchController$$ExternalSyntheticOutline0;
import com.android.keyguard.LegacyLockIconViewController$$ExternalSyntheticOutline0;
import com.android.settingslib.graph.ThemedBatteryDrawable;
import com.android.settingslib.graph.ThemedBatteryDrawable$sam$java_lang_Runnable$0;
import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.flags.FeatureFlagsClassicRelease;
import com.android.systemui.flags.Flags;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.android.systemui.statusbar.phone.StatusBarLocation;
import com.android.systemui.statusbar.phone.ui.StatusBarIconController;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.statusbar.policy.BatteryControllerImpl;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.tuner.TunerService;
import com.android.systemui.util.ViewController;
import java.io.PrintWriter;
import java.util.Objects;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BatteryMeterViewController extends ViewController {
    public final BatteryController mBatteryController;
    public final AnonymousClass3 mBatteryStateChangeCallback;
    public final ConfigurationController mConfigurationController;
    public final AnonymousClass1 mConfigurationListener;
    public final ContentResolver mContentResolver;
    public final FeatureFlags mFeatureFlags;
    public boolean mIgnoreTunerUpdates;
    public boolean mIsSubscribedForTunerUpdates;
    public final StatusBarLocation mLocation;
    public final Handler mMainHandler;
    public final SettingObserver mSettingObserver;
    public final String mSlotBattery;
    public final AnonymousClass2 mTunable;
    public final TunerService mTunerService;
    public final UserTracker.Callback mUserChangedCallback;
    public final UserTracker mUserTracker;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Factory {
        public final BatteryController mBatteryController;
        public final ConfigurationController mConfigurationController;
        public final ContentResolver mContentResolver;
        public final FeatureFlags mFeatureFlags;
        public final Handler mMainHandler;
        public final TunerService mTunerService;
        public final UserTracker mUserTracker;

        public Factory(UserTracker userTracker, ConfigurationController configurationController, TunerService tunerService, Handler handler, ContentResolver contentResolver, FeatureFlags featureFlags, BatteryController batteryController) {
            this.mUserTracker = userTracker;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SettingObserver extends ContentObserver {
        public SettingObserver(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public final void onChange(boolean z, Uri uri) {
            super.onChange(z, uri);
            ((BatteryMeterView) BatteryMeterViewController.this.mView).updateShowPercent();
            if (TextUtils.equals(uri.getLastPathSegment(), "battery_estimates_last_update_time")) {
                ((BatteryMeterView) BatteryMeterViewController.this.mView).updatePercentText();
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.android.systemui.battery.BatteryMeterViewController$1] */
    /* JADX WARN: Type inference failed for: r0v1, types: [com.android.systemui.battery.BatteryMeterViewController$2] */
    /* JADX WARN: Type inference failed for: r0v2, types: [com.android.systemui.battery.BatteryMeterViewController$3] */
    public BatteryMeterViewController(BatteryMeterView batteryMeterView, StatusBarLocation statusBarLocation, UserTracker userTracker, ConfigurationController configurationController, TunerService tunerService, Handler handler, ContentResolver contentResolver, FeatureFlags featureFlags, BatteryController batteryController) {
        super(batteryMeterView);
        this.mConfigurationListener = new ConfigurationController.ConfigurationListener() { // from class: com.android.systemui.battery.BatteryMeterViewController.1
            @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
            public final void onDensityOrFontScaleChanged() {
                ((BatteryMeterView) BatteryMeterViewController.this.mView).scaleBatteryMeterViews();
            }
        };
        this.mTunable = new TunerService.Tunable() { // from class: com.android.systemui.battery.BatteryMeterViewController.2
            @Override // com.android.systemui.tuner.TunerService.Tunable
            public final void onTuningChanged(String str, String str2) {
                if ("icon_blacklist".equals(str)) {
                    BatteryMeterViewController batteryMeterViewController = BatteryMeterViewController.this;
                    ((BatteryMeterView) batteryMeterViewController.mView).setVisibility(StatusBarIconController.getIconHideList(batteryMeterViewController.mView.getContext(), str2).contains(batteryMeterViewController.mSlotBattery) ? 8 : 0);
                }
            }
        };
        this.mBatteryStateChangeCallback = new BatteryController.BatteryStateChangeCallback() { // from class: com.android.systemui.battery.BatteryMeterViewController.3
            @Override // com.android.systemui.statusbar.policy.BatteryController.BatteryStateChangeCallback, com.android.systemui.Dumpable
            public final void dump(PrintWriter printWriter, String[] strArr) {
                printWriter.print(toString());
                StringBuilder sb = new StringBuilder(" location=");
                BatteryMeterViewController batteryMeterViewController = BatteryMeterViewController.this;
                sb.append(batteryMeterViewController.mLocation);
                printWriter.println(sb.toString());
                BatteryMeterView batteryMeterView2 = (BatteryMeterView) batteryMeterViewController.mView;
                String m = batteryMeterView2.mDrawable == null ? null : AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(new StringBuilder(), ((ThemedBatteryDrawable) batteryMeterView2.mDrawable.getDrawable()).powerSaveEnabled, "");
                String m2 = batteryMeterView2.mDrawable == null ? null : AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(new StringBuilder(), batteryMeterView2.mDrawable.displayShield, "");
                String m3 = batteryMeterView2.mDrawable == null ? null : AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(new StringBuilder(), ((ThemedBatteryDrawable) batteryMeterView2.mDrawable.getDrawable()).charging, "");
                TextView textView = batteryMeterView2.mBatteryPercentView;
                CharSequence text = textView != null ? textView.getText() : null;
                printWriter.println("  BatteryMeterView:");
                printWriter.println("    mDrawable.getPowerSave: " + m);
                printWriter.println("    mDrawable.getDisplayShield: " + m2);
                printWriter.println("    mDrawable.getCharging: " + m3);
                printWriter.println("    mBatteryPercentView.getText(): " + ((Object) text));
                printWriter.println("    mTextColor: #" + Integer.toHexString(batteryMeterView2.mTextColor));
                StringBuilder m4 = LegacyLockIconViewController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(new StringBuilder("    mBatteryStateUnknown: "), batteryMeterView2.mBatteryStateUnknown, printWriter, "    mIsIncompatibleCharging: "), batteryMeterView2.mIsIncompatibleCharging, printWriter, "    mPluggedIn: "), batteryMeterView2.mPluggedIn, printWriter, "    mLevel: "), batteryMeterView2.mLevel, printWriter, "    mMode: ");
                m4.append(batteryMeterView2.mShowPercentMode);
                printWriter.println(m4.toString());
            }

            @Override // com.android.systemui.statusbar.policy.BatteryController.BatteryStateChangeCallback
            public final void onBatteryLevelChanged(int i, boolean z, boolean z2) {
                ((BatteryMeterView) BatteryMeterViewController.this.mView).onBatteryLevelChanged(i, z);
            }

            @Override // com.android.systemui.statusbar.policy.BatteryController.BatteryStateChangeCallback
            public final void onBatteryUnknownStateChanged(boolean z) {
                ((BatteryMeterView) BatteryMeterViewController.this.mView).onBatteryUnknownStateChanged(z);
            }

            @Override // com.android.systemui.statusbar.policy.BatteryController.BatteryStateChangeCallback
            public final void onIsBatteryDefenderChanged(boolean z) {
                BatteryMeterView batteryMeterView2 = (BatteryMeterView) BatteryMeterViewController.this.mView;
                boolean z2 = batteryMeterView2.mIsBatteryDefender != z;
                batteryMeterView2.mIsBatteryDefender = z;
                if (z2) {
                    batteryMeterView2.updateContentDescription();
                    batteryMeterView2.scaleBatteryMeterViews();
                }
            }

            @Override // com.android.systemui.statusbar.policy.BatteryController.BatteryStateChangeCallback
            public final void onIsIncompatibleChargingChanged(boolean z) {
                BatteryMeterViewController batteryMeterViewController = BatteryMeterViewController.this;
                if (((FeatureFlagsClassicRelease) batteryMeterViewController.mFeatureFlags).isEnabled(Flags.INCOMPATIBLE_CHARGING_BATTERY_ICON)) {
                    BatteryMeterView batteryMeterView2 = (BatteryMeterView) batteryMeterViewController.mView;
                    boolean z2 = batteryMeterView2.mIsIncompatibleCharging != z;
                    batteryMeterView2.mIsIncompatibleCharging = z;
                    if (z2) {
                        AccessorizedBatteryDrawable accessorizedBatteryDrawable = batteryMeterView2.mDrawable;
                        boolean isCharging = batteryMeterView2.isCharging();
                        ThemedBatteryDrawable themedBatteryDrawable = (ThemedBatteryDrawable) accessorizedBatteryDrawable.getDrawable();
                        themedBatteryDrawable.charging = isCharging;
                        themedBatteryDrawable.unscheduleSelf(new ThemedBatteryDrawable$sam$java_lang_Runnable$0(themedBatteryDrawable.invalidateRunnable));
                        themedBatteryDrawable.scheduleSelf(new ThemedBatteryDrawable$sam$java_lang_Runnable$0(themedBatteryDrawable.invalidateRunnable), 0L);
                        batteryMeterView2.updateContentDescription();
                    }
                }
            }

            @Override // com.android.systemui.statusbar.policy.BatteryController.BatteryStateChangeCallback
            public final void onPowerSaveChanged(boolean z) {
                BatteryMeterView batteryMeterView2 = (BatteryMeterView) BatteryMeterViewController.this.mView;
                if (z == batteryMeterView2.mPowerSaveEnabled) {
                    return;
                }
                batteryMeterView2.mPowerSaveEnabled = z;
                ThemedBatteryDrawable themedBatteryDrawable = (ThemedBatteryDrawable) batteryMeterView2.mDrawable.getDrawable();
                themedBatteryDrawable.powerSaveEnabled = z;
                themedBatteryDrawable.unscheduleSelf(new ThemedBatteryDrawable$sam$java_lang_Runnable$0(themedBatteryDrawable.invalidateRunnable));
                themedBatteryDrawable.scheduleSelf(new ThemedBatteryDrawable$sam$java_lang_Runnable$0(themedBatteryDrawable.invalidateRunnable), 0L);
            }
        };
        this.mUserChangedCallback = new UserTracker.Callback() { // from class: com.android.systemui.battery.BatteryMeterViewController.4
            @Override // com.android.systemui.settings.UserTracker.Callback
            public final void onUserChanged(int i, Context context) {
                BatteryMeterViewController batteryMeterViewController = BatteryMeterViewController.this;
                ContentResolver contentResolver2 = batteryMeterViewController.mContentResolver;
                SettingObserver settingObserver = batteryMeterViewController.mSettingObserver;
                contentResolver2.unregisterContentObserver(settingObserver);
                batteryMeterViewController.mContentResolver.registerContentObserver(Settings.System.getUriFor("status_bar_show_battery_percent"), false, settingObserver, i);
                ((BatteryMeterView) batteryMeterViewController.mView).updateShowPercent();
            }
        };
        this.mLocation = statusBarLocation;
        this.mUserTracker = userTracker;
        this.mConfigurationController = configurationController;
        this.mTunerService = tunerService;
        this.mMainHandler = handler;
        this.mContentResolver = contentResolver;
        this.mFeatureFlags = featureFlags;
        this.mBatteryController = batteryController;
        Objects.requireNonNull(batteryController);
        batteryMeterView.mBatteryEstimateFetcher = new BatteryMeterViewController$$ExternalSyntheticLambda0(batteryController);
        this.mSlotBattery = batteryMeterView.getResources().getString(R.string.status_bar_zen);
        this.mSettingObserver = new SettingObserver(handler);
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewAttached() {
        ((ConfigurationControllerImpl) this.mConfigurationController).addCallback(this.mConfigurationListener);
        if (!this.mIsSubscribedForTunerUpdates && !this.mIgnoreTunerUpdates) {
            this.mTunerService.addTunable(this.mTunable, "icon_blacklist");
            this.mIsSubscribedForTunerUpdates = true;
        }
        this.mBatteryController.addCallback(this.mBatteryStateChangeCallback);
        UserTrackerImpl userTrackerImpl = (UserTrackerImpl) this.mUserTracker;
        int userId = userTrackerImpl.getUserId();
        ContentResolver contentResolver = this.mContentResolver;
        Uri uriFor = Settings.System.getUriFor("status_bar_show_battery_percent");
        SettingObserver settingObserver = this.mSettingObserver;
        contentResolver.registerContentObserver(uriFor, false, settingObserver, userId);
        this.mContentResolver.registerContentObserver(Settings.Global.getUriFor("battery_estimates_last_update_time"), false, settingObserver);
        userTrackerImpl.addCallback(this.mUserChangedCallback, new HandlerExecutor(this.mMainHandler));
        ((BatteryMeterView) this.mView).updateShowPercent();
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewDetached() {
        ((ConfigurationControllerImpl) this.mConfigurationController).removeCallback(this.mConfigurationListener);
        if (this.mIsSubscribedForTunerUpdates) {
            this.mTunerService.removeTunable(this.mTunable);
            this.mIsSubscribedForTunerUpdates = false;
        }
        ((BatteryControllerImpl) this.mBatteryController).removeCallback(this.mBatteryStateChangeCallback);
        ((UserTrackerImpl) this.mUserTracker).removeCallback(this.mUserChangedCallback);
        this.mContentResolver.unregisterContentObserver(this.mSettingObserver);
    }
}

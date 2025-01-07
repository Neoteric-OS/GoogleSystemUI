package com.android.systemui.power;

import android.app.Dialog;
import android.app.KeyguardManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.os.UserHandle;
import android.provider.Settings;
import android.text.Annotation;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.util.Log;
import android.util.Slog;
import android.view.View;
import com.android.internal.logging.UiEventLogger;
import com.android.settingslib.fuelgauge.BatterySaverUtils;
import com.android.systemui.SystemUIApplication;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.animation.Expandable;
import com.android.systemui.bluetooth.qsdialog.BluetoothTileDialogViewModel$showDialog$1$$ExternalSyntheticOutline0;
import com.android.systemui.broadcast.BroadcastSender;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.power.PowerUI;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.statusbar.policy.BatteryControllerImpl;
import com.android.wm.shell.R;
import com.google.android.systemui.power.PowerNotificationWarningsGoogleImpl;
import dagger.Lazy;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.Locale;
import java.util.Objects;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class PowerNotificationWarnings implements PowerUI.WarningsUI {
    public static final boolean DEBUG = PowerUI.DEBUG;
    public static final String[] SHOWING_STRINGS = {"SHOWING_NOTHING", "SHOWING_WARNING", "SHOWING_SAVER", "SHOWING_INVALID_CHARGER", "SHOWING_AUTO_SAVER_SUGGESTION"};
    public final ActivityStarter mActivityStarter;
    public final Lazy mBatteryControllerLazy;
    public final BroadcastSender mBroadcastSender;
    public final Context mContext;
    public BatteryStateSnapshot mCurrentBatterySnapshot;
    public final DialogTransitionAnimator mDialogTransitionAnimator;
    public final Handler mHandler;
    public SystemUIDialog mHighTempDialog;
    public boolean mHighTempWarning;
    public final KeyguardManager mKeyguard;
    public final NotificationManager mNoMan;
    public final Intent mOpenBatterySaverSettings;
    public final Intent mOpenBatterySettings;
    public final PowerManager mPowerMan;
    public SystemUIDialog mSaverConfirmation;
    public boolean mShowAutoSaverSuggestion;
    public int mShowing;
    public final SystemUIDialog.Factory mSystemUIDialogFactory;
    public SystemUIDialog mThermalShutdownDialog;
    public final UiEventLogger mUiEventLogger;
    SystemUIDialog mUsbHighTempDialog;
    public final boolean mUseExtraSaverConfirmation;
    public final UserTracker mUserTracker;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.power.PowerNotificationWarnings$1, reason: invalid class name */
    public final class AnonymousClass1 implements DialogInterface.OnClickListener {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ PowerNotificationWarnings this$0;
        public final /* synthetic */ String val$url;

        public /* synthetic */ AnonymousClass1(PowerNotificationWarnings powerNotificationWarnings, String str, int i) {
            this.$r8$classId = i;
            this.this$0 = powerNotificationWarnings;
            this.val$url = str;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public final void onClick(DialogInterface dialogInterface, int i) {
            switch (this.$r8$classId) {
                case 0:
                    this.this$0.mActivityStarter.startActivity(new Intent("android.intent.action.VIEW").setData(Uri.parse(this.val$url)).setFlags(268435456), true, (ActivityStarter.Callback) new PowerNotificationWarnings$$ExternalSyntheticLambda4(1, this));
                    break;
                default:
                    this.this$0.mActivityStarter.startActivity(new Intent("android.intent.action.VIEW").setData(Uri.parse(this.val$url)).setFlags(268435456), true, (ActivityStarter.Callback) new PowerNotificationWarnings$$ExternalSyntheticLambda4(2, this));
                    break;
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Receiver extends BroadcastReceiver {
        public Receiver() {
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            CharSequence charSequence;
            int i = 1;
            String action = intent.getAction();
            Slog.i("PowerUI.Notification", "Received " + action);
            if (action.equals("PNW.batterySaverSettings")) {
                PowerNotificationWarnings.this.logEvent(BatteryWarningEvents$LowBatteryWarningEvent.LOW_BATTERY_NOTIFICATION_SETTINGS);
                PowerNotificationWarnings powerNotificationWarnings = PowerNotificationWarnings.this;
                powerNotificationWarnings.getClass();
                powerNotificationWarnings.updateNotification();
                PowerNotificationWarnings powerNotificationWarnings2 = PowerNotificationWarnings.this;
                powerNotificationWarnings2.mContext.startActivityAsUser(powerNotificationWarnings2.mOpenBatterySaverSettings, ((UserTrackerImpl) powerNotificationWarnings2.mUserTracker).getUserHandle());
                return;
            }
            if (action.equals("PNW.startSaver")) {
                PowerNotificationWarnings.this.logEvent(BatteryWarningEvents$LowBatteryWarningEvent.LOW_BATTERY_NOTIFICATION_TURN_ON);
                BatterySaverUtils.setPowerSaveMode(PowerNotificationWarnings.this.mContext, true, true, 5);
                PowerNotificationWarnings powerNotificationWarnings3 = PowerNotificationWarnings.this;
                powerNotificationWarnings3.getClass();
                powerNotificationWarnings3.updateNotification();
                return;
            }
            if (action.equals("PNW.startSaverConfirmation")) {
                PowerNotificationWarnings powerNotificationWarnings4 = PowerNotificationWarnings.this;
                powerNotificationWarnings4.getClass();
                powerNotificationWarnings4.updateNotification();
                final PowerNotificationWarnings powerNotificationWarnings5 = PowerNotificationWarnings.this;
                Bundle extras = intent.getExtras();
                if (powerNotificationWarnings5.mSaverConfirmation != null || powerNotificationWarnings5.mUseExtraSaverConfirmation) {
                    return;
                }
                SystemUIDialog create = powerNotificationWarnings5.mSystemUIDialogFactory.create();
                boolean z = extras.getBoolean("extra_confirm_only");
                final int i2 = extras.getInt("extra_power_save_mode_trigger", 0);
                final int i3 = extras.getInt("extra_power_save_mode_trigger_level", 0);
                String charSequence2 = powerNotificationWarnings5.mContext.getText(R.string.help_uri_battery_saver_learn_more_link_target).toString();
                if (TextUtils.isEmpty(charSequence2)) {
                    charSequence = powerNotificationWarnings5.mContext.getText(R.string.battery_low_intro);
                } else {
                    SpannableString spannableString = new SpannableString(powerNotificationWarnings5.mContext.getText(android.R.string.battery_saver_notification_channel_name));
                    SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(spannableString);
                    for (Annotation annotation : (Annotation[]) spannableString.getSpans(0, spannableString.length(), Annotation.class)) {
                        if ("url".equals(annotation.getValue())) {
                            int spanStart = spannableString.getSpanStart(annotation);
                            int spanEnd = spannableString.getSpanEnd(annotation);
                            URLSpan uRLSpan = new URLSpan(charSequence2) { // from class: com.android.systemui.power.PowerNotificationWarnings.3
                                @Override // android.text.style.URLSpan, android.text.style.ClickableSpan
                                public final void onClick(View view) {
                                    SystemUIDialog systemUIDialog = PowerNotificationWarnings.this.mSaverConfirmation;
                                    if (systemUIDialog != null) {
                                        systemUIDialog.dismiss();
                                    }
                                    PowerNotificationWarnings.this.mBroadcastSender.closeSystemDialogs();
                                    Uri parse = Uri.parse(getURL());
                                    Context context2 = view.getContext();
                                    Intent flags = new Intent("android.intent.action.VIEW", parse).setFlags(268435456);
                                    try {
                                        context2.startActivity(flags);
                                    } catch (ActivityNotFoundException unused) {
                                        Log.w("PowerUI.Notification", "Activity was not found for intent, " + flags.toString());
                                    }
                                }

                                @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
                                public final void updateDrawState(TextPaint textPaint) {
                                    super.updateDrawState(textPaint);
                                    textPaint.setUnderlineText(false);
                                }
                            };
                            spannableStringBuilder.setSpan(uRLSpan, spanStart, spanEnd, spannableString.getSpanFlags(uRLSpan));
                        }
                    }
                    charSequence = spannableStringBuilder;
                }
                create.setMessage(charSequence);
                if (Objects.equals(Locale.getDefault().getLanguage(), Locale.ENGLISH.getLanguage())) {
                    create.setMessageHyphenationFrequency(0);
                }
                create.setMessageMovementMethod(LinkMovementMethod.getInstance());
                if (z) {
                    create.setTitle(R.string.battery_saver_confirmation_title_generic);
                    create.setPositiveButton(android.R.string.content_description_expanded, new DialogInterface.OnClickListener() { // from class: com.android.systemui.power.PowerNotificationWarnings$$ExternalSyntheticLambda7
                        @Override // android.content.DialogInterface.OnClickListener
                        public final void onClick(DialogInterface dialogInterface, int i4) {
                            PowerNotificationWarnings powerNotificationWarnings6 = PowerNotificationWarnings.this;
                            int i5 = i2;
                            int i6 = i3;
                            ContentResolver contentResolver = powerNotificationWarnings6.mContext.getContentResolver();
                            Settings.Global.putInt(contentResolver, "automatic_power_save_mode", i5);
                            Settings.Global.putInt(contentResolver, "low_power_trigger_level", i6);
                            UserTrackerImpl userTrackerImpl = (UserTrackerImpl) powerNotificationWarnings6.mUserTracker;
                            Settings.Secure.putIntForUser(contentResolver, "low_power_warning_acknowledged", 1, userTrackerImpl.getUserId());
                            Settings.Secure.putIntForUser(contentResolver, "extra_low_power_warning_acknowledged", 1, userTrackerImpl.getUserId());
                        }
                    });
                } else {
                    create.setTitle(R.string.battery_saver_confirmation_title);
                    create.setPositiveButton(R.string.battery_saver_confirmation_ok, new PowerNotificationWarnings$$ExternalSyntheticLambda1(powerNotificationWarnings5, 2));
                    create.setNegativeButton$1(android.R.string.cancel, new PowerNotificationWarnings$$ExternalSyntheticLambda1(powerNotificationWarnings5, 3));
                }
                SystemUIDialog.setShowForAllUsers(create);
                create.setOnDismissListener(new PowerNotificationWarnings$$ExternalSyntheticLambda3(powerNotificationWarnings5, 1));
                Lazy lazy = powerNotificationWarnings5.mBatteryControllerLazy;
                WeakReference weakReference = (WeakReference) ((BatteryControllerImpl) ((BatteryController) lazy.get())).mPowerSaverStartExpandable.get();
                if (weakReference == null || weakReference.get() == null) {
                    create.show();
                } else {
                    DialogTransitionAnimator.Controller m = BluetoothTileDialogViewModel$showDialog$1$$ExternalSyntheticOutline0.m(58, "start_power_saver", (Expandable) weakReference.get());
                    if (m != null) {
                        powerNotificationWarnings5.mDialogTransitionAnimator.show(create, m, false);
                    } else {
                        create.show();
                    }
                }
                powerNotificationWarnings5.logEvent(BatteryWarningEvents$LowBatteryWarningEvent.SAVER_CONFIRM_DIALOG);
                powerNotificationWarnings5.mSaverConfirmation = create;
                ((BatteryControllerImpl) ((BatteryController) lazy.get())).mPowerSaverStartExpandable.set(null);
                return;
            }
            if (action.equals("PNW.dismissedWarning")) {
                PowerNotificationWarnings.this.logEvent(BatteryWarningEvents$LowBatteryWarningEvent.LOW_BATTERY_NOTIFICATION_CANCEL);
                ((PowerNotificationWarningsGoogleImpl) PowerNotificationWarnings.this).dismissLowBatteryWarning();
                return;
            }
            if ("PNW.clickedTempWarning".equals(action)) {
                PowerNotificationWarnings.this.dismissHighTemperatureWarningInternal();
                PowerNotificationWarnings powerNotificationWarnings6 = PowerNotificationWarnings.this;
                if (powerNotificationWarnings6.mHighTempDialog != null) {
                    return;
                }
                SystemUIDialog create2 = powerNotificationWarnings6.mSystemUIDialogFactory.create();
                create2.setIconAttribute(android.R.attr.alertDialogIcon);
                create2.setTitle(R.string.high_temp_title);
                create2.setMessage(R.string.high_temp_dialog_message);
                create2.setPositiveButton(android.R.string.ok, null);
                SystemUIDialog.setShowForAllUsers(create2);
                create2.setOnDismissListener(new PowerNotificationWarnings$$ExternalSyntheticLambda3(powerNotificationWarnings6, 3));
                String string = powerNotificationWarnings6.mContext.getString(R.string.high_temp_dialog_help_url);
                if (!string.isEmpty()) {
                    create2.setNeutralButton(R.string.high_temp_dialog_help_text, new AnonymousClass1(powerNotificationWarnings6, string, 0), true);
                }
                create2.show();
                powerNotificationWarnings6.mHighTempDialog = create2;
                return;
            }
            if ("PNW.dismissedTempWarning".equals(action)) {
                PowerNotificationWarnings.this.dismissHighTemperatureWarningInternal();
                return;
            }
            if ("PNW.clickedThermalShutdownWarning".equals(action)) {
                PowerNotificationWarnings.this.dismissThermalShutdownWarning();
                PowerNotificationWarnings powerNotificationWarnings7 = PowerNotificationWarnings.this;
                if (powerNotificationWarnings7.mThermalShutdownDialog != null) {
                    return;
                }
                SystemUIDialog create3 = powerNotificationWarnings7.mSystemUIDialogFactory.create();
                create3.setIconAttribute(android.R.attr.alertDialogIcon);
                create3.setTitle(R.string.thermal_shutdown_title);
                create3.setMessage(R.string.thermal_shutdown_dialog_message);
                create3.setPositiveButton(android.R.string.ok, null);
                SystemUIDialog.setShowForAllUsers(create3);
                create3.setOnDismissListener(new PowerNotificationWarnings$$ExternalSyntheticLambda3(powerNotificationWarnings7, 2));
                String string2 = powerNotificationWarnings7.mContext.getString(R.string.thermal_shutdown_dialog_help_url);
                if (!string2.isEmpty()) {
                    create3.setNeutralButton(R.string.thermal_shutdown_dialog_help_text, new AnonymousClass1(powerNotificationWarnings7, string2, i), true);
                }
                create3.show();
                powerNotificationWarnings7.mThermalShutdownDialog = create3;
                return;
            }
            if ("PNW.dismissedThermalShutdownWarning".equals(action)) {
                PowerNotificationWarnings.this.dismissThermalShutdownWarning();
                return;
            }
            if ("PNW.autoSaverSuggestion".equals(action)) {
                PowerNotificationWarnings powerNotificationWarnings8 = PowerNotificationWarnings.this;
                powerNotificationWarnings8.mShowAutoSaverSuggestion = true;
                powerNotificationWarnings8.updateNotification();
                return;
            }
            if ("PNW.dismissAutoSaverSuggestion".equals(action)) {
                PowerNotificationWarnings powerNotificationWarnings9 = PowerNotificationWarnings.this;
                powerNotificationWarnings9.mShowAutoSaverSuggestion = false;
                powerNotificationWarnings9.updateNotification();
                return;
            }
            if (!"PNW.enableAutoSaver".equals(action)) {
                if ("PNW.autoSaverNoThanks".equals(action)) {
                    PowerNotificationWarnings powerNotificationWarnings10 = PowerNotificationWarnings.this;
                    powerNotificationWarnings10.mShowAutoSaverSuggestion = false;
                    powerNotificationWarnings10.updateNotification();
                    Settings.Secure.putInt(context.getContentResolver(), "suppress_auto_battery_saver_suggestion", 1);
                    return;
                }
                return;
            }
            PowerNotificationWarnings powerNotificationWarnings11 = PowerNotificationWarnings.this;
            powerNotificationWarnings11.mShowAutoSaverSuggestion = false;
            powerNotificationWarnings11.updateNotification();
            PowerNotificationWarnings powerNotificationWarnings12 = PowerNotificationWarnings.this;
            powerNotificationWarnings12.getClass();
            Intent intent2 = new Intent("com.android.settings.BATTERY_SAVER_SCHEDULE_SETTINGS");
            intent2.setFlags(268468224);
            powerNotificationWarnings12.mActivityStarter.startActivity(intent2, true);
        }
    }

    static {
        new AudioAttributes.Builder().setContentType(4).setUsage(13).build();
    }

    public PowerNotificationWarnings(Context context, ActivityStarter activityStarter, BroadcastSender broadcastSender, Lazy lazy, DialogTransitionAnimator dialogTransitionAnimator, UiEventLogger uiEventLogger, UserTracker userTracker, SystemUIDialog.Factory factory) {
        Handler handler = new Handler(Looper.getMainLooper());
        this.mHandler = handler;
        Receiver receiver = new Receiver();
        new Intent("android.intent.action.POWER_USAGE_SUMMARY").setFlags(1551892480);
        this.mOpenBatterySaverSettings = new Intent("android.settings.BATTERY_SAVER_SETTINGS").setFlags(1551892480);
        this.mContext = context;
        this.mSystemUIDialogFactory = factory;
        this.mNoMan = (NotificationManager) context.getSystemService(NotificationManager.class);
        this.mKeyguard = (KeyguardManager) context.getSystemService(KeyguardManager.class);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("PNW.batterySaverSettings");
        intentFilter.addAction("PNW.startSaver");
        intentFilter.addAction("PNW.dismissedWarning");
        intentFilter.addAction("PNW.clickedTempWarning");
        intentFilter.addAction("PNW.dismissedTempWarning");
        intentFilter.addAction("PNW.clickedThermalShutdownWarning");
        intentFilter.addAction("PNW.dismissedThermalShutdownWarning");
        intentFilter.addAction("PNW.startSaverConfirmation");
        intentFilter.addAction("PNW.autoSaverSuggestion");
        intentFilter.addAction("PNW.enableAutoSaver");
        intentFilter.addAction("PNW.autoSaverNoThanks");
        intentFilter.addAction("PNW.dismissAutoSaverSuggestion");
        context.registerReceiverAsUser(receiver, UserHandle.ALL, intentFilter, "android.permission.DEVICE_POWER", handler, 2);
        this.mActivityStarter = activityStarter;
        this.mBroadcastSender = broadcastSender;
        this.mBatteryControllerLazy = lazy;
        this.mDialogTransitionAnimator = dialogTransitionAnimator;
        this.mUiEventLogger = uiEventLogger;
        this.mUserTracker = userTracker;
        this.mUseExtraSaverConfirmation = context.getResources().getBoolean(R.bool.config_extra_battery_saver_confirmation);
    }

    public final void dismissHighTemperatureWarningInternal() {
        this.mNoMan.cancelAsUser("high_temp", 4, UserHandle.ALL);
        this.mHighTempWarning = false;
    }

    public void dismissThermalShutdownWarning() {
        this.mNoMan.cancelAsUser("high_temp", 39, UserHandle.ALL);
    }

    public void dump(PrintWriter printWriter) {
        printWriter.print("mWarning=");
        printWriter.println(false);
        printWriter.print("mPlaySound=");
        printWriter.println(false);
        printWriter.print("mInvalidCharger=");
        printWriter.println(false);
        printWriter.print("mShowing=");
        printWriter.println(SHOWING_STRINGS[this.mShowing]);
        printWriter.print("mSaverConfirmation=");
        printWriter.println(this.mSaverConfirmation != null ? "not null" : null);
        printWriter.print("mSaverEnabledConfirmation=");
        printWriter.print("mHighTempWarning=");
        printWriter.println(this.mHighTempWarning);
        printWriter.print("mHighTempDialog=");
        printWriter.println(this.mHighTempDialog != null ? "not null" : null);
        printWriter.print("mThermalShutdownDialog=");
        printWriter.println(this.mThermalShutdownDialog != null ? "not null" : null);
        printWriter.print("mUsbHighTempDialog=");
        printWriter.println(this.mUsbHighTempDialog != null ? "not null" : null);
    }

    public Dialog getSaverConfirmationDialog() {
        return this.mSaverConfirmation;
    }

    public final void logEvent(BatteryWarningEvents$LowBatteryWarningEvent batteryWarningEvents$LowBatteryWarningEvent) {
        UiEventLogger uiEventLogger = this.mUiEventLogger;
        if (uiEventLogger != null) {
            uiEventLogger.log(batteryWarningEvents$LowBatteryWarningEvent);
        }
    }

    public final PendingIntent pendingBroadcast(String str) {
        return PendingIntent.getBroadcastAsUser(this.mContext, 0, new Intent(str).setPackage(this.mContext.getPackageName()).setFlags(268435456), 67108864, UserHandle.CURRENT);
    }

    public final void updateNotification() {
        if (DEBUG) {
            Slog.d("PowerUI.Notification", "updateNotification mWarning=false mPlaySound=false mInvalidCharger=false");
        }
        if (!this.mShowAutoSaverSuggestion) {
            NotificationManager notificationManager = this.mNoMan;
            UserHandle userHandle = UserHandle.ALL;
            notificationManager.cancelAsUser("low_battery", 2, userHandle);
            this.mNoMan.cancelAsUser("low_battery", 3, userHandle);
            this.mNoMan.cancelAsUser("auto_saver", 49, userHandle);
            this.mShowing = 0;
            return;
        }
        if (this.mShowing != 4) {
            String string = this.mContext.getString(R.string.auto_saver_text);
            Notification.Builder contentText = new Notification.Builder(this.mContext, "HNT").setSmallIcon(R.drawable.ic_power_saver).setWhen(0L).setShowWhen(false).setContentTitle(this.mContext.getString(R.string.auto_saver_title)).setStyle(new Notification.BigTextStyle().bigText(string)).setContentText(string);
            contentText.setContentIntent(pendingBroadcast("PNW.enableAutoSaver"));
            contentText.setDeleteIntent(pendingBroadcast("PNW.dismissAutoSaverSuggestion"));
            contentText.addAction(0, this.mContext.getString(R.string.no_auto_saver_action), pendingBroadcast("PNW.autoSaverNoThanks"));
            SystemUIApplication.overrideNotificationAppName(this.mContext, contentText, false);
            this.mNoMan.notifyAsUser("auto_saver", 49, contentText.build(), UserHandle.ALL);
        }
        this.mShowing = 4;
    }
}

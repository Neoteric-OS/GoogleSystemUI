package com.google.android.systemui.statusbar.policy;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.PowerManager;
import android.util.Log;
import androidx.compose.foundation.text.ValidatingOffsetMappingKt$$ExternalSyntheticOutline0;
import androidx.exifinterface.media.ExifInterface$$ExternalSyntheticOutline0;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.demomode.DemoModeController;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.power.EnhancedEstimates;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.statusbar.policy.BatteryControllerImpl;
import com.android.systemui.statusbar.policy.BatteryControllerLogger;
import com.android.systemui.util.settings.SecureSettings;
import com.google.android.systemui.power.PowerUtils;
import com.google.android.systemui.reversecharging.ReverseChargingController;
import java.io.PrintWriter;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BatteryControllerImplGoogle extends BatteryControllerImpl implements ReverseChargingController.ReverseChargingChangeCallback {
    protected final ContentObserver mContentObserver;
    public final UserTracker mContentResolverProvider;
    public boolean mExtremeSaver;
    public String mName;
    public boolean mReverse;
    public final ReverseChargingController mReverseChargingController;
    public int mRtxLevel;
    public final SecureSettings mSecureSettings;
    public final UserTracker mUserTracker;
    public static final Uri IS_EBS_ENABLED_OBSERVABLE_URI = new Uri.Builder().scheme("content").authority("com.google.android.flipendo.api").appendPath("get_flipendo_state").build();
    public static final boolean DEBUG = Log.isLoggable("BatteryControllerGoogle", 3);

    public BatteryControllerImplGoogle(Context context, EnhancedEstimates enhancedEstimates, PowerManager powerManager, BroadcastDispatcher broadcastDispatcher, DemoModeController demoModeController, DumpManager dumpManager, BatteryControllerLogger batteryControllerLogger, Handler handler, Handler handler2, UserTracker userTracker, ReverseChargingController reverseChargingController, SecureSettings secureSettings, UserTracker userTracker2) {
        super(context, enhancedEstimates, powerManager, broadcastDispatcher, demoModeController, dumpManager, batteryControllerLogger, handler, handler2);
        this.mReverseChargingController = reverseChargingController;
        this.mContentResolverProvider = userTracker;
        this.mSecureSettings = secureSettings;
        this.mUserTracker = userTracker2;
        this.mContentObserver = new ContentObserver(handler2) { // from class: com.google.android.systemui.statusbar.policy.BatteryControllerImplGoogle.1
            @Override // android.database.ContentObserver
            public final void onChange(boolean z, Uri uri) {
                if (BatteryControllerImplGoogle.DEBUG) {
                    Log.d("BatteryControllerGoogle", "Change in EBS value " + uri.toSafeString());
                }
                BatteryControllerImplGoogle batteryControllerImplGoogle = BatteryControllerImplGoogle.this;
                boolean isFlipendoEnabled = PowerUtils.isFlipendoEnabled(((UserTrackerImpl) batteryControllerImplGoogle.mContentResolverProvider).getUserContext().getContentResolver());
                if (isFlipendoEnabled == batteryControllerImplGoogle.mExtremeSaver) {
                    return;
                }
                batteryControllerImplGoogle.mExtremeSaver = isFlipendoEnabled;
                batteryControllerImplGoogle.dispatchSafeChange(new BatteryControllerImplGoogle$$ExternalSyntheticLambda0(batteryControllerImplGoogle, 1));
            }
        };
    }

    @Override // com.android.systemui.statusbar.policy.BatteryControllerImpl, com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        super.dump(printWriter, strArr);
        printWriter.print("  mReverse=");
        printWriter.println(this.mReverse);
        printWriter.print("  mExtremeSaver=");
        printWriter.println(this.mExtremeSaver);
    }

    @Override // com.android.systemui.statusbar.policy.BatteryControllerImpl
    public final void init$9() {
        super.init$9();
        this.mReverse = false;
        this.mRtxLevel = -1;
        this.mName = null;
        this.mReverseChargingController.init(this);
        this.mReverseChargingController.addCallback((ReverseChargingController.ReverseChargingChangeCallback) this);
        try {
            ContentResolver contentResolver = ((UserTrackerImpl) this.mContentResolverProvider).getUserContext().getContentResolver();
            Uri uri = IS_EBS_ENABLED_OBSERVABLE_URI;
            contentResolver.registerContentObserver(uri, false, this.mContentObserver, -1);
            this.mContentObserver.onChange(false, uri);
        } catch (Exception e) {
            Log.w("BatteryControllerGoogle", "Couldn't register to observe provider", e);
        }
    }

    @Override // com.android.systemui.statusbar.policy.BatteryControllerImpl
    public final boolean isBatteryDefenderMode(int i) {
        if (i != 4) {
            return false;
        }
        boolean isChargeLimitEnabledForUser = PowerUtils.isChargeLimitEnabledForUser(this.mSecureSettings, ((UserTrackerImpl) this.mUserTracker).getUserId());
        return !isChargeLimitEnabledForUser || (isChargeLimitEnabledForUser && this.mLevel >= 80);
    }

    @Override // com.android.systemui.statusbar.policy.BatteryController
    public final boolean isExtremeSaverOn() {
        return this.mExtremeSaver;
    }

    @Override // com.android.systemui.statusbar.policy.BatteryController
    public final boolean isReverseOn() {
        return this.mReverse;
    }

    @Override // com.android.systemui.statusbar.policy.BatteryController
    public final boolean isReverseSupported() {
        return this.mReverseChargingController.isReverseSupported();
    }

    @Override // com.android.systemui.statusbar.policy.BatteryControllerImpl, android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        this.mReverseChargingController.handleIntentForReverseCharging(intent);
    }

    public final void onReverseChargingChanged(int i, String str, boolean z) {
        this.mReverse = z;
        this.mRtxLevel = i;
        this.mName = str;
        if (DEBUG) {
            StringBuilder m = ValidatingOffsetMappingKt$$ExternalSyntheticOutline0.m(z ? 1 : 0, i, "onReverseChargingChanged(): rtx=", " level=", " name=");
            m.append(str);
            m.append(" this=");
            m.append(this);
            Log.d("BatteryControllerGoogle", m.toString());
        }
        dispatchSafeChange(new BatteryControllerImplGoogle$$ExternalSyntheticLambda0(this, 0));
    }

    public void setBatteryLevel(int i) {
        this.mLevel = i;
    }

    @Override // com.android.systemui.statusbar.policy.BatteryController
    public final void setReverseState(boolean z) {
        ReverseChargingController reverseChargingController = this.mReverseChargingController;
        if (reverseChargingController.isReverseSupported()) {
            if (ReverseChargingController.DEBUG) {
                ExifInterface$$ExternalSyntheticOutline0.m("setReverseState(): rtx=", "ReverseChargingControl", z ? 1 : 0);
            }
            reverseChargingController.mStopReverseAtAcUnplug = false;
            reverseChargingController.setReverseStateInternal(2, z);
        }
    }

    @Override // com.android.systemui.statusbar.policy.BatteryControllerImpl, com.android.systemui.statusbar.policy.CallbackController
    public final void addCallback(BatteryController.BatteryStateChangeCallback batteryStateChangeCallback) {
        super.addCallback(batteryStateChangeCallback);
        batteryStateChangeCallback.onReverseChanged(this.mRtxLevel, this.mName, this.mReverse);
        batteryStateChangeCallback.onExtremeBatterySaverChanged(this.mExtremeSaver);
    }
}

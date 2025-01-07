package com.android.keyguard;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.telephony.TelephonyCallback;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import androidx.collection.MutableObjectList$$ExternalSyntheticOutline0;
import androidx.compose.animation.ChangeSize$$ExternalSyntheticOutline0;
import com.android.keyguard.logging.CarrierTextManagerLogger;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.statusbar.pipeline.satellite.ui.viewmodel.DeviceBasedSatelliteViewModel;
import com.android.systemui.statusbar.pipeline.satellite.ui.viewmodel.DeviceBasedSatelliteViewModelImpl;
import com.android.systemui.statusbar.pipeline.wifi.data.repository.WifiRepository;
import com.android.systemui.telephony.TelephonyListenerManager;
import com.android.systemui.util.kotlin.JavaAdapter;
import java.util.Arrays;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import kotlinx.coroutines.StandaloneCoroutine;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CarrierTextManager {
    public final Executor mBgExecutor;
    public CarrierTextCallback mCarrierTextCallback;
    public final Context mContext;
    public final DeviceBasedSatelliteViewModel mDeviceBasedSatelliteViewModel;
    public final boolean mIsEmergencyCallCapable;
    public final JavaAdapter mJavaAdapter;
    protected KeyguardUpdateMonitor mKeyguardUpdateMonitor;
    public final CarrierTextManagerLogger mLogger;
    public final Executor mMainExecutor;
    public String mSatelliteCarrierText;
    public StandaloneCoroutine mSatelliteConnectionJob;
    public final CharSequence mSeparator;
    public final boolean mShowAirplaneMode;
    public final boolean mShowMissingSim;
    public final boolean[] mSimErrorState;
    public final int mSimSlotsNumber;
    public boolean mTelephonyCapable;
    public final TelephonyListenerManager mTelephonyListenerManager;
    public final TelephonyManager mTelephonyManager;
    public final WakefulnessLifecycle mWakefulnessLifecycle;
    public final WifiRepository mWifiRepository;
    public final AtomicBoolean mNetworkSupported = new AtomicBoolean();
    public final AnonymousClass1 mWakefulnessObserver = new WakefulnessLifecycle.Observer() { // from class: com.android.keyguard.CarrierTextManager.1
        @Override // com.android.systemui.keyguard.WakefulnessLifecycle.Observer
        public final void onFinishedWakingUp() {
            CarrierTextCallback carrierTextCallback = CarrierTextManager.this.mCarrierTextCallback;
            if (carrierTextCallback != null) {
                carrierTextCallback.finishedWakingUp();
            }
        }

        @Override // com.android.systemui.keyguard.WakefulnessLifecycle.Observer
        public final void onStartedGoingToSleep() {
            CarrierTextCallback carrierTextCallback = CarrierTextManager.this.mCarrierTextCallback;
            if (carrierTextCallback != null) {
                carrierTextCallback.startedGoingToSleep();
            }
        }
    };
    protected final KeyguardUpdateMonitorCallback mCallback = new KeyguardUpdateMonitorCallback() { // from class: com.android.keyguard.CarrierTextManager.2
        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onRefreshCarrierInfo() {
            CarrierTextManager carrierTextManager = CarrierTextManager.this;
            carrierTextManager.mLogger.logUpdateCarrierTextForReason(1);
            carrierTextManager.updateCarrierText();
        }

        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onSimStateChanged(int i, int i2, int i3) {
            CarrierTextManager carrierTextManager = CarrierTextManager.this;
            if (i2 < 0 || i2 >= carrierTextManager.mSimSlotsNumber) {
                StringBuilder m = MutableObjectList$$ExternalSyntheticOutline0.m("onSimStateChanged() - slotId invalid: ", " mTelephonyCapable: ", i2);
                m.append(Boolean.toString(carrierTextManager.mTelephonyCapable));
                Log.d("CarrierTextController", m.toString());
                return;
            }
            CarrierTextManagerLogger carrierTextManagerLogger = carrierTextManager.mLogger;
            carrierTextManagerLogger.logSimStateChangedCallback(i, i2, i3);
            StatusMode statusForIccState = carrierTextManager.getStatusForIccState(i3);
            StatusMode statusMode = StatusMode.SimIoError;
            boolean[] zArr = carrierTextManager.mSimErrorState;
            if (statusForIccState == statusMode) {
                zArr[i2] = true;
                carrierTextManagerLogger.logUpdateCarrierTextForReason(3);
                carrierTextManager.updateCarrierText();
            } else if (zArr[i2]) {
                zArr[i2] = false;
                carrierTextManagerLogger.logUpdateCarrierTextForReason(3);
                carrierTextManager.updateCarrierText();
            }
        }

        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onTelephonyCapable(boolean z) {
            CarrierTextManager carrierTextManager = CarrierTextManager.this;
            carrierTextManager.mLogger.logUpdateCarrierTextForReason(2);
            carrierTextManager.mTelephonyCapable = z;
            carrierTextManager.updateCarrierText();
        }
    };
    public final AnonymousClass3 mPhoneStateListener = new TelephonyCallback.ActiveDataSubscriptionIdListener() { // from class: com.android.keyguard.CarrierTextManager.3
        @Override // android.telephony.TelephonyCallback.ActiveDataSubscriptionIdListener
        public final void onActiveDataSubscriptionIdChanged(int i) {
            if (CarrierTextManager.this.mNetworkSupported.get()) {
                CarrierTextManager carrierTextManager = CarrierTextManager.this;
                if (carrierTextManager.mCarrierTextCallback != null) {
                    carrierTextManager.mLogger.logUpdateCarrierTextForReason(4);
                    CarrierTextManager.this.updateCarrierText();
                }
            }
        }
    };

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Builder {
        public final Executor mBgExecutor;
        public final Context mContext;
        public String mDebugLocation;
        public final DeviceBasedSatelliteViewModel mDeviceBasedSatelliteViewModel;
        public final JavaAdapter mJavaAdapter;
        public final KeyguardUpdateMonitor mKeyguardUpdateMonitor;
        public final CarrierTextManagerLogger mLogger;
        public final Executor mMainExecutor;
        public final String mSeparator;
        public boolean mShowAirplaneMode;
        public boolean mShowMissingSim;
        public final TelephonyListenerManager mTelephonyListenerManager;
        public final TelephonyManager mTelephonyManager;
        public final WakefulnessLifecycle mWakefulnessLifecycle;
        public final WifiRepository mWifiRepository;

        public Builder(Context context, Resources resources, WifiRepository wifiRepository, DeviceBasedSatelliteViewModel deviceBasedSatelliteViewModel, JavaAdapter javaAdapter, TelephonyManager telephonyManager, TelephonyListenerManager telephonyListenerManager, WakefulnessLifecycle wakefulnessLifecycle, Executor executor, Executor executor2, KeyguardUpdateMonitor keyguardUpdateMonitor, CarrierTextManagerLogger carrierTextManagerLogger) {
            this.mContext = context;
            this.mSeparator = resources.getString(R.string.lock_pattern_view_aspect);
            this.mWifiRepository = wifiRepository;
            this.mDeviceBasedSatelliteViewModel = deviceBasedSatelliteViewModel;
            this.mJavaAdapter = javaAdapter;
            this.mTelephonyManager = telephonyManager;
            this.mTelephonyListenerManager = telephonyListenerManager;
            this.mWakefulnessLifecycle = wakefulnessLifecycle;
            this.mMainExecutor = executor;
            this.mBgExecutor = executor2;
            this.mKeyguardUpdateMonitor = keyguardUpdateMonitor;
            this.mLogger = carrierTextManagerLogger;
        }

        public final CarrierTextManager build() {
            this.mLogger.location = this.mDebugLocation;
            Context context = this.mContext;
            boolean z = this.mShowAirplaneMode;
            boolean z2 = this.mShowMissingSim;
            TelephonyManager telephonyManager = this.mTelephonyManager;
            Executor executor = this.mMainExecutor;
            Executor executor2 = this.mBgExecutor;
            return new CarrierTextManager(context, this.mSeparator, z, z2, this.mWifiRepository, this.mDeviceBasedSatelliteViewModel, this.mJavaAdapter, telephonyManager, this.mTelephonyListenerManager, this.mWakefulnessLifecycle, executor, executor2, this.mKeyguardUpdateMonitor, this.mLogger);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class CarrierTextCallbackInfo {
        public final boolean airplaneMode;
        public final boolean anySimReady;
        public final CharSequence carrierText;
        public final boolean isInSatelliteMode;
        public final CharSequence[] listOfCarriers;
        public final int[] subscriptionIds;

        public CarrierTextCallbackInfo(CharSequence charSequence, CharSequence[] charSequenceArr, boolean z, int[] iArr) {
            this(charSequence, charSequenceArr, z, false, iArr, false);
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("CarrierTextCallbackInfo{carrierText=");
            sb.append((Object) this.carrierText);
            sb.append(", listOfCarriers=");
            sb.append(Arrays.toString(this.listOfCarriers));
            sb.append(", anySimReady=");
            sb.append(this.anySimReady);
            sb.append(", isInSatelliteMode=");
            sb.append(this.isInSatelliteMode);
            sb.append(", subscriptionIds=");
            sb.append(Arrays.toString(this.subscriptionIds));
            sb.append(", airplaneMode=");
            return ChangeSize$$ExternalSyntheticOutline0.m(sb, this.airplaneMode, '}');
        }

        public CarrierTextCallbackInfo(CharSequence charSequence, CharSequence[] charSequenceArr, boolean z, boolean z2, int[] iArr, boolean z3) {
            this.carrierText = charSequence;
            this.listOfCarriers = charSequenceArr;
            this.anySimReady = z;
            this.isInSatelliteMode = z2;
            this.subscriptionIds = iArr;
            this.airplaneMode = z3;
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class StatusMode {
        public static final /* synthetic */ StatusMode[] $VALUES;
        public static final StatusMode NetworkLocked;
        public static final StatusMode Normal;
        public static final StatusMode SimIoError;
        public static final StatusMode SimLocked;
        public static final StatusMode SimMissing;
        public static final StatusMode SimMissingLocked;
        public static final StatusMode SimNotReady;
        public static final StatusMode SimPermDisabled;
        public static final StatusMode SimPukLocked;
        public static final StatusMode SimRestricted;
        public static final StatusMode SimUnknown;

        static {
            StatusMode statusMode = new StatusMode("Normal", 0);
            Normal = statusMode;
            StatusMode statusMode2 = new StatusMode("NetworkLocked", 1);
            NetworkLocked = statusMode2;
            StatusMode statusMode3 = new StatusMode("SimMissing", 2);
            SimMissing = statusMode3;
            StatusMode statusMode4 = new StatusMode("SimMissingLocked", 3);
            SimMissingLocked = statusMode4;
            StatusMode statusMode5 = new StatusMode("SimPukLocked", 4);
            SimPukLocked = statusMode5;
            StatusMode statusMode6 = new StatusMode("SimLocked", 5);
            SimLocked = statusMode6;
            StatusMode statusMode7 = new StatusMode("SimPermDisabled", 6);
            SimPermDisabled = statusMode7;
            StatusMode statusMode8 = new StatusMode("SimNotReady", 7);
            SimNotReady = statusMode8;
            StatusMode statusMode9 = new StatusMode("SimIoError", 8);
            SimIoError = statusMode9;
            StatusMode statusMode10 = new StatusMode("SimRestricted", 9);
            SimRestricted = statusMode10;
            StatusMode statusMode11 = new StatusMode("SimUnknown", 10);
            SimUnknown = statusMode11;
            $VALUES = new StatusMode[]{statusMode, statusMode2, statusMode3, statusMode4, statusMode5, statusMode6, statusMode7, statusMode8, statusMode9, statusMode10, statusMode11};
        }

        public static StatusMode valueOf(String str) {
            return (StatusMode) Enum.valueOf(StatusMode.class, str);
        }

        public static StatusMode[] values() {
            return (StatusMode[]) $VALUES.clone();
        }
    }

    static {
        boolean z = KeyguardConstants.DEBUG;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.android.keyguard.CarrierTextManager$1] */
    /* JADX WARN: Type inference failed for: r0v3, types: [com.android.keyguard.CarrierTextManager$3] */
    public CarrierTextManager(Context context, CharSequence charSequence, boolean z, boolean z2, WifiRepository wifiRepository, DeviceBasedSatelliteViewModel deviceBasedSatelliteViewModel, JavaAdapter javaAdapter, TelephonyManager telephonyManager, TelephonyListenerManager telephonyListenerManager, WakefulnessLifecycle wakefulnessLifecycle, Executor executor, Executor executor2, KeyguardUpdateMonitor keyguardUpdateMonitor, CarrierTextManagerLogger carrierTextManagerLogger) {
        this.mContext = context;
        this.mIsEmergencyCallCapable = telephonyManager.isVoiceCapable();
        this.mShowAirplaneMode = z;
        this.mShowMissingSim = z2;
        this.mWifiRepository = wifiRepository;
        this.mDeviceBasedSatelliteViewModel = deviceBasedSatelliteViewModel;
        this.mJavaAdapter = javaAdapter;
        this.mTelephonyManager = telephonyManager;
        this.mSeparator = charSequence;
        this.mTelephonyListenerManager = telephonyListenerManager;
        this.mWakefulnessLifecycle = wakefulnessLifecycle;
        int supportedModemCount = telephonyManager.getSupportedModemCount();
        this.mSimSlotsNumber = supportedModemCount;
        this.mSimErrorState = new boolean[supportedModemCount];
        this.mMainExecutor = executor;
        this.mBgExecutor = executor2;
        this.mKeyguardUpdateMonitor = keyguardUpdateMonitor;
        this.mLogger = carrierTextManagerLogger;
        executor2.execute(new CarrierTextManager$$ExternalSyntheticLambda5(1, this, executor));
    }

    public static CharSequence concatenate(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3) {
        boolean isEmpty = TextUtils.isEmpty(charSequence);
        boolean isEmpty2 = TextUtils.isEmpty(charSequence2);
        if (isEmpty || isEmpty2) {
            return !isEmpty ? charSequence : !isEmpty2 ? charSequence2 : "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(charSequence);
        sb.append(charSequence3);
        sb.append(charSequence2);
        return sb.toString();
    }

    public final CharSequence getCarrierTextForSimState(int i, CharSequence charSequence) {
        switch (getStatusForIccState(i).ordinal()) {
            case 0:
                return charSequence;
            case 1:
                return makeCarrierStringOnEmergencyCapable(this.mContext.getText(com.android.wm.shell.R.string.keyguard_network_locked_message), charSequence);
            case 2:
            case 3:
            case 9:
            case 10:
            default:
                return null;
            case 4:
                return makeCarrierStringOnLocked(this.mContext.getText(com.android.wm.shell.R.string.keyguard_sim_puk_locked_message), charSequence);
            case 5:
                return makeCarrierStringOnLocked(this.mContext.getText(com.android.wm.shell.R.string.keyguard_sim_locked_message), charSequence);
            case 6:
                return makeCarrierStringOnEmergencyCapable(this.mContext.getText(com.android.wm.shell.R.string.keyguard_permanent_disabled_sim_message_short), charSequence);
            case 7:
                return "";
            case 8:
                return makeCarrierStringOnEmergencyCapable(this.mContext.getText(com.android.wm.shell.R.string.keyguard_sim_error_message_short), charSequence);
        }
    }

    public StatusMode getStatusForIccState(int i) {
        if (!this.mKeyguardUpdateMonitor.mDeviceProvisioned && (i == 1 || i == 7)) {
            return StatusMode.SimMissingLocked;
        }
        StatusMode statusMode = StatusMode.SimUnknown;
        switch (i) {
            case 0:
                return statusMode;
            case 1:
                return StatusMode.SimMissing;
            case 2:
                return StatusMode.SimLocked;
            case 3:
                return StatusMode.SimPukLocked;
            case 4:
                return StatusMode.NetworkLocked;
            case 5:
                return StatusMode.Normal;
            case 6:
                return StatusMode.SimNotReady;
            case 7:
                return StatusMode.SimPermDisabled;
            case 8:
                return StatusMode.SimIoError;
            case 9:
                return StatusMode.SimRestricted;
            default:
                return statusMode;
        }
    }

    public final void handleSetListening(CarrierTextCallback carrierTextCallback) {
        AnonymousClass3 anonymousClass3 = this.mPhoneStateListener;
        CarrierTextManagerLogger carrierTextManagerLogger = this.mLogger;
        TelephonyListenerManager telephonyListenerManager = this.mTelephonyListenerManager;
        if (carrierTextCallback == null) {
            this.mCarrierTextCallback = null;
            this.mMainExecutor.execute(new CarrierTextManager$$ExternalSyntheticLambda4(2, this));
            telephonyListenerManager.mTelephonyCallback.mActiveDataSubscriptionIdListeners.remove(anonymousClass3);
            telephonyListenerManager.updateListening();
            StandaloneCoroutine standaloneCoroutine = this.mSatelliteConnectionJob;
            if (standaloneCoroutine != null) {
                carrierTextManagerLogger.logStopListeningForSatelliteCarrierText("#handleSetListening has null callback");
                standaloneCoroutine.cancelInternal(new CancellationException("#handleSetListening has null callback"));
                return;
            }
            return;
        }
        this.mCarrierTextCallback = carrierTextCallback;
        if (!this.mNetworkSupported.get()) {
            this.mMainExecutor.execute(new CarrierTextManager$$ExternalSyntheticLambda4(3, carrierTextCallback));
            return;
        }
        this.mMainExecutor.execute(new CarrierTextManager$$ExternalSyntheticLambda4(1, this));
        telephonyListenerManager.mTelephonyCallback.mActiveDataSubscriptionIdListeners.add(anonymousClass3);
        telephonyListenerManager.updateListening();
        StandaloneCoroutine standaloneCoroutine2 = this.mSatelliteConnectionJob;
        if (standaloneCoroutine2 != null) {
            carrierTextManagerLogger.logStopListeningForSatelliteCarrierText("Starting new job");
            standaloneCoroutine2.cancelInternal(new CancellationException("Starting new job"));
        }
        carrierTextManagerLogger.logStartListeningForSatelliteCarrierText();
        this.mSatelliteConnectionJob = this.mJavaAdapter.alwaysCollectFlow(((DeviceBasedSatelliteViewModelImpl) this.mDeviceBasedSatelliteViewModel).carrierText, new Consumer() { // from class: com.android.keyguard.CarrierTextManager$$ExternalSyntheticLambda1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                CarrierTextManager carrierTextManager = CarrierTextManager.this;
                String str = (String) obj;
                CarrierTextManagerLogger carrierTextManagerLogger2 = carrierTextManager.mLogger;
                carrierTextManagerLogger2.logUpdateCarrierTextForReason(5);
                carrierTextManagerLogger2.logNewSatelliteCarrierText(str);
                carrierTextManager.mSatelliteCarrierText = str;
                carrierTextManager.updateCarrierText();
            }
        });
    }

    public final CharSequence makeCarrierStringOnEmergencyCapable(CharSequence charSequence, CharSequence charSequence2) {
        return this.mIsEmergencyCallCapable ? concatenate(charSequence, charSequence2, this.mSeparator) : charSequence;
    }

    public final CharSequence makeCarrierStringOnLocked(CharSequence charSequence, CharSequence charSequence2) {
        boolean isEmpty = TextUtils.isEmpty(charSequence);
        boolean isEmpty2 = TextUtils.isEmpty(charSequence2);
        return (isEmpty || isEmpty2) ? !isEmpty ? charSequence : !isEmpty2 ? charSequence2 : "" : this.mContext.getString(com.android.wm.shell.R.string.keyguard_carrier_name_with_sim_locked_template, charSequence2, charSequence);
    }

    public void postToCallback(CarrierTextCallbackInfo carrierTextCallbackInfo) {
        CarrierTextCallback carrierTextCallback = this.mCarrierTextCallback;
        if (carrierTextCallback != null) {
            this.mMainExecutor.execute(new CarrierTextManager$$ExternalSyntheticLambda5(0, carrierTextCallback, carrierTextCallbackInfo));
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x00ad, code lost:
    
        if (kotlin.jvm.internal.Intrinsics.areEqual(r13, "<unknown ssid>") == false) goto L26;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void updateCarrierText() {
        /*
            Method dump skipped, instructions count: 512
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.keyguard.CarrierTextManager.updateCarrierText():void");
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface CarrierTextCallback {
        void updateCarrierInfo(CarrierTextCallbackInfo carrierTextCallbackInfo);

        default void finishedWakingUp() {
        }

        default void startedGoingToSleep() {
        }
    }
}

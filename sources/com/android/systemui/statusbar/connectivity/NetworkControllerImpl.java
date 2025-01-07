package com.android.systemui.statusbar.connectivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.vcn.VcnTransportInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerExecutor;
import android.os.Looper;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.telephony.CarrierConfigManager;
import android.telephony.CellSignalStrength;
import android.telephony.ServiceState;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyCallback;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.IndentingPrintWriter;
import android.util.Log;
import android.util.MathUtils;
import android.util.SparseArray;
import androidx.emoji2.text.ConcurrencyHelpers$$ExternalSyntheticLambda0;
import com.android.keyguard.ClockEventController$zenModeCallback$1$$ExternalSyntheticOutline0;
import com.android.keyguard.KeyguardClockSwitchController$$ExternalSyntheticOutline0;
import com.android.settingslib.AccessibilityContentDescriptions;
import com.android.settingslib.SignalIcon$IconGroup;
import com.android.settingslib.SignalIcon$MobileIconGroup;
import com.android.settingslib.mobile.MobileMappings;
import com.android.settingslib.mobile.MobileStatusTracker;
import com.android.settingslib.mobile.TelephonyIcons;
import com.android.settingslib.net.DataUsageController;
import com.android.settingslib.wifi.WifiStatusTracker;
import com.android.settingslib.wifi.WifiStatusTracker$$ExternalSyntheticLambda0;
import com.android.systemui.Dumpable;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.demomode.DemoMode;
import com.android.systemui.demomode.DemoModeController;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.qs.tiles.dialog.InternetDialogManager;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.pipeline.StatusBarPipelineFlags;
import com.android.systemui.statusbar.policy.DataSaverControllerImpl;
import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import com.android.systemui.statusbar.policy.DeviceProvisionedControllerImpl;
import com.android.systemui.telephony.TelephonyListenerManager;
import com.android.systemui.util.CarrierConfigTracker;
import com.android.wifitrackerlib.WifiPickerTracker;
import com.android.wm.shell.R;
import dalvik.annotation.optimization.NeverCompile;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class NetworkControllerImpl extends BroadcastReceiver implements NetworkController, DemoMode, Dumpable {
    public final AccessPointControllerImpl mAccessPoints;
    public int mActiveMobileDataSubscription;
    public boolean mAirplaneMode;
    public final Executor mBgExecutor;
    public final Looper mBgLooper;
    public final BroadcastDispatcher mBroadcastDispatcher;
    public final CallbackHandler mCallbackHandler;
    public final NetworkControllerImpl$$ExternalSyntheticLambda2 mClearForceValidated;
    public MobileMappings.Config mConfig;
    public final BitSet mConnectedTransports;
    public final Context mContext;
    public List mCurrentSubscriptions;
    public final DataSaverControllerImpl mDataSaverController;
    public final DataUsageController mDataUsageController;
    public MobileSignalController mDefaultSignalController;
    public boolean mDemoInetCondition;
    public final DemoModeController mDemoModeController;
    public int mEmergencySource;
    final EthernetSignalController mEthernetSignalController;
    public boolean mForceCellularValidated;
    public final boolean mHasMobileDataFeature;
    public boolean mHasNoSubs;
    public final String[] mHistory;
    public int mHistoryIndex;
    public boolean mInetCondition;
    public final InternetDialogManager mInternetDialogManager;
    public boolean mIsEmergency;
    public NetworkCapabilities mLastDefaultNetworkCapabilities;
    ServiceState mLastServiceState;
    boolean mListening;
    public Locale mLocale;
    public final Object mLock;
    public final LogBuffer mLogBuffer;
    public final Handler mMainHandler;
    public final MobileSignalControllerFactory mMobileFactory;
    final SparseArray mMobileSignalControllers;
    public boolean mNoDefaultNetwork;
    public boolean mNoNetworksAvailable;
    public final TelephonyManager mPhone;
    public final NetworkControllerImpl$$ExternalSyntheticLambda6 mPhoneStateListener;
    public final Handler mReceiverHandler;
    public final NetworkControllerImpl$$ExternalSyntheticLambda2 mRegisterListeners;
    public boolean mSimDetected;
    public final MobileStatusTracker.SubscriptionDefaults mSubDefaults;
    public SubListener mSubscriptionListener;
    public final SubscriptionManager mSubscriptionManager;
    public final TelephonyListenerManager mTelephonyListenerManager;
    public final UserTracker.Callback mUserChangedCallback;
    public boolean mUserSetup;
    public final UserTracker mUserTracker;
    public final BitSet mValidatedTransports;
    public final WifiManager mWifiManager;
    final WifiSignalController mWifiSignalController;
    public static final boolean DEBUG = Log.isLoggable("NetworkController", 3);
    public static final boolean CHATTY = Log.isLoggable("NetworkControllerChat", 3);
    public static final SimpleDateFormat SSDF = new SimpleDateFormat("MM-dd HH:mm:ss.SSS");

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.connectivity.NetworkControllerImpl$8, reason: invalid class name */
    public final class AnonymousClass8 implements Comparator {
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            int simSlotIndex;
            int simSlotIndex2;
            SubscriptionInfo subscriptionInfo = (SubscriptionInfo) obj;
            SubscriptionInfo subscriptionInfo2 = (SubscriptionInfo) obj2;
            if (subscriptionInfo.getSimSlotIndex() == subscriptionInfo2.getSimSlotIndex()) {
                simSlotIndex = subscriptionInfo.getSubscriptionId();
                simSlotIndex2 = subscriptionInfo2.getSubscriptionId();
            } else {
                simSlotIndex = subscriptionInfo.getSimSlotIndex();
                simSlotIndex2 = subscriptionInfo2.getSimSlotIndex();
            }
            return simSlotIndex - simSlotIndex2;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SubListener extends SubscriptionManager.OnSubscriptionsChangedListener {
        public SubListener(Looper looper) {
            super(looper);
        }

        @Override // android.telephony.SubscriptionManager.OnSubscriptionsChangedListener
        public final void onSubscriptionsChanged() {
            NetworkControllerImpl networkControllerImpl = NetworkControllerImpl.this;
            boolean z = NetworkControllerImpl.DEBUG;
            networkControllerImpl.updateMobileControllers();
        }
    }

    /* renamed from: -$$Nest$mgetProcessedTransportTypes, reason: not valid java name */
    public static int[] m874$$Nest$mgetProcessedTransportTypes(NetworkControllerImpl networkControllerImpl, NetworkCapabilities networkCapabilities) {
        int[] transportTypes = networkCapabilities.getTransportTypes();
        int i = 0;
        while (true) {
            if (i >= transportTypes.length) {
                break;
            }
            if (transportTypes[i] == 0) {
                if (((networkCapabilities.getTransportInfo() == null || !(networkCapabilities.getTransportInfo() instanceof VcnTransportInfo)) ? null : networkCapabilities.getTransportInfo().getWifiInfo()) != null) {
                    transportTypes[i] = 1;
                    break;
                }
            }
            i++;
        }
        return transportTypes;
    }

    public NetworkControllerImpl(Context context, Looper looper, Executor executor, SubscriptionManager subscriptionManager, CallbackHandler callbackHandler, DeviceProvisionedController deviceProvisionedController, BroadcastDispatcher broadcastDispatcher, UserTracker userTracker, ConnectivityManager connectivityManager, TelephonyManager telephonyManager, TelephonyListenerManager telephonyListenerManager, WifiManager wifiManager, AccessPointControllerImpl accessPointControllerImpl, StatusBarPipelineFlags statusBarPipelineFlags, DemoModeController demoModeController, CarrierConfigTracker carrierConfigTracker, WifiStatusTrackerFactory wifiStatusTrackerFactory, MobileSignalControllerFactory mobileSignalControllerFactory, Handler handler, InternetDialogManager internetDialogManager, DumpManager dumpManager, LogBuffer logBuffer) {
        this(context, connectivityManager, telephonyManager, telephonyListenerManager, wifiManager, subscriptionManager, MobileMappings.Config.readConfig(context), looper, executor, callbackHandler, accessPointControllerImpl, statusBarPipelineFlags, new DataUsageController(context), new MobileStatusTracker.SubscriptionDefaults(), deviceProvisionedController, broadcastDispatcher, userTracker, demoModeController, carrierConfigTracker, wifiStatusTrackerFactory, mobileSignalControllerFactory, handler, dumpManager, logBuffer);
        this.mReceiverHandler.post(this.mRegisterListeners);
        this.mInternetDialogManager = internetDialogManager;
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void addCallback(Object obj) {
        SignalCallback signalCallback = (SignalCallback) obj;
        signalCallback.setSubs(this.mCurrentSubscriptions);
        boolean z = this.mAirplaneMode;
        SignalIcon$MobileIconGroup signalIcon$MobileIconGroup = TelephonyIcons.CARRIER_NETWORK_CHANGE;
        signalCallback.setIsAirplaneMode(new IconState(R.drawable.stat_sys_airplane_mode, this.mContext.getString(R.string.accessibility_airplane_mode), z));
        signalCallback.setNoSims(this.mHasNoSubs, this.mSimDetected);
        signalCallback.setConnectivityStatus(this.mNoDefaultNetwork, !this.mInetCondition, this.mNoNetworksAvailable);
        this.mWifiSignalController.notifyListeners(signalCallback);
        this.mEthernetSignalController.notifyListeners(signalCallback);
        for (int i = 0; i < this.mMobileSignalControllers.size(); i++) {
            ((MobileSignalController) this.mMobileSignalControllers.valueAt(i)).notifyListeners(signalCallback);
        }
        this.mCallbackHandler.obtainMessage(7, 1, 0, signalCallback).sendToTarget();
    }

    public final SubscriptionInfo addDemoModeSignalController(int i, int i2) {
        SubscriptionInfo subscriptionInfo = new SubscriptionInfo(i, "", i2, "", "", 0, 0, "", 0, null, null, null, "", false, null, null);
        MobileSignalControllerFactory mobileSignalControllerFactory = this.mMobileFactory;
        MobileMappings.Config config = this.mConfig;
        boolean z = this.mHasMobileDataFeature;
        TelephonyManager createForSubscriptionId = this.mPhone.createForSubscriptionId(subscriptionInfo.getSubscriptionId());
        MobileStatusTracker.SubscriptionDefaults subscriptionDefaults = this.mSubDefaults;
        Looper looper = this.mReceiverHandler.getLooper();
        mobileSignalControllerFactory.getClass();
        MobileStatusTrackerFactory mobileStatusTrackerFactory = new MobileStatusTrackerFactory(createForSubscriptionId, looper, subscriptionInfo, subscriptionDefaults);
        MobileSignalController mobileSignalController = new MobileSignalController(mobileSignalControllerFactory.context, config, z, createForSubscriptionId, mobileSignalControllerFactory.callbackHandler, this, mobileSignalControllerFactory.mobileMappings, subscriptionInfo, subscriptionDefaults, looper, mobileStatusTrackerFactory);
        this.mMobileSignalControllers.put(i, mobileSignalController);
        ((MobileState) mobileSignalController.mCurrentState).userSetup = true;
        return subscriptionInfo;
    }

    @Override // com.android.systemui.demomode.DemoMode
    public final List demoCommands() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("network");
        return arrayList;
    }

    @Override // com.android.systemui.demomode.DemoModeCommandReceiver
    public final void dispatchDemoCommand(Bundle bundle, String str) {
        if (this.mDemoModeController.isInDemoMode) {
            String string = bundle.getString("airplane");
            if (string != null) {
                boolean equals = string.equals("show");
                CallbackHandler callbackHandler = this.mCallbackHandler;
                SignalIcon$MobileIconGroup signalIcon$MobileIconGroup = TelephonyIcons.CARRIER_NETWORK_CHANGE;
                callbackHandler.setIsAirplaneMode(new IconState(R.drawable.stat_sys_airplane_mode, this.mContext.getString(R.string.accessibility_airplane_mode), equals));
            }
            String string2 = bundle.getString("fully");
            if (string2 != null) {
                this.mDemoInetCondition = Boolean.parseBoolean(string2);
                BitSet bitSet = new BitSet();
                if (this.mDemoInetCondition) {
                    bitSet.set(this.mWifiSignalController.mTransportType);
                }
                this.mWifiSignalController.updateConnectivity(bitSet, bitSet);
                for (int i = 0; i < this.mMobileSignalControllers.size(); i++) {
                    MobileSignalController mobileSignalController = (MobileSignalController) this.mMobileSignalControllers.valueAt(i);
                    if (this.mDemoInetCondition) {
                        bitSet.set(mobileSignalController.mTransportType);
                    }
                    mobileSignalController.updateConnectivity(bitSet, bitSet);
                }
            }
            String string3 = bundle.getString("sims");
            if (string3 != null) {
                int constrain = MathUtils.constrain(Integer.parseInt(string3), 1, 8);
                ArrayList arrayList = new ArrayList();
                if (constrain != this.mMobileSignalControllers.size()) {
                    this.mMobileSignalControllers.clear();
                    int activeSubscriptionInfoCountMax = this.mSubscriptionManager.getActiveSubscriptionInfoCountMax();
                    for (int i2 = activeSubscriptionInfoCountMax; i2 < activeSubscriptionInfoCountMax + constrain; i2++) {
                        arrayList.add(addDemoModeSignalController(i2, i2));
                    }
                    this.mCallbackHandler.setSubs(arrayList);
                    for (int i3 = 0; i3 < this.mMobileSignalControllers.size(); i3++) {
                        ((MobileSignalController) this.mMobileSignalControllers.get(this.mMobileSignalControllers.keyAt(i3))).notifyListeners();
                    }
                }
            }
            String string4 = bundle.getString("nosim");
            if (string4 != null) {
                boolean equals2 = string4.equals("show");
                this.mHasNoSubs = equals2;
                this.mCallbackHandler.setNoSims(equals2, this.mSimDetected);
            }
            String string5 = bundle.getString("mobile");
            if (string5 != null) {
                boolean equals3 = string5.equals("show");
                String string6 = bundle.getString("datatype");
                String string7 = bundle.getString("slot");
                int constrain2 = MathUtils.constrain(TextUtils.isEmpty(string7) ? 0 : Integer.parseInt(string7), 0, 8);
                String string8 = bundle.getString("carrierid");
                int parseInt = TextUtils.isEmpty(string8) ? 0 : Integer.parseInt(string8);
                ArrayList arrayList2 = new ArrayList();
                while (this.mMobileSignalControllers.size() <= constrain2) {
                    int size = this.mMobileSignalControllers.size();
                    arrayList2.add(addDemoModeSignalController(size, size));
                }
                if (!arrayList2.isEmpty()) {
                    this.mCallbackHandler.setSubs(arrayList2);
                }
                MobileSignalController mobileSignalController2 = (MobileSignalController) this.mMobileSignalControllers.valueAt(constrain2);
                if (parseInt != 0) {
                    ((MobileState) mobileSignalController2.mCurrentState).carrierId = parseInt;
                }
                MobileState mobileState = (MobileState) mobileSignalController2.mCurrentState;
                mobileState.dataSim = string6 != null;
                mobileState.isDefault = string6 != null;
                mobileState.dataConnected = string6 != null;
                if (string6 != null) {
                    mobileState.iconGroup = string6.equals("1x") ? TelephonyIcons.ONE_X : string6.equals("3g") ? TelephonyIcons.THREE_G : string6.equals("4g") ? TelephonyIcons.FOUR_G : string6.equals("4g+") ? TelephonyIcons.FOUR_G_PLUS : string6.equals("5g") ? TelephonyIcons.NR_5G : string6.equals("5ge") ? TelephonyIcons.LTE_CA_5G_E : string6.equals("5g+") ? TelephonyIcons.NR_5G_PLUS : string6.equals("e") ? TelephonyIcons.E : string6.equals("g") ? TelephonyIcons.G : string6.equals("h") ? TelephonyIcons.H : string6.equals("h+") ? TelephonyIcons.H_PLUS : string6.equals("lte") ? TelephonyIcons.LTE : string6.equals("lte+") ? TelephonyIcons.LTE_PLUS : string6.equals("dis") ? TelephonyIcons.DATA_DISABLED : string6.equals("not") ? TelephonyIcons.NOT_DEFAULT_DATA : TelephonyIcons.UNKNOWN;
                }
                boolean containsKey = bundle.containsKey("roam");
                ConnectivityState connectivityState = mobileSignalController2.mCurrentState;
                if (containsKey) {
                    ((MobileState) connectivityState).roaming = "show".equals(bundle.getString("roam"));
                }
                String string9 = bundle.getString("level");
                char c = 65535;
                if (string9 != null) {
                    MobileState mobileState2 = (MobileState) connectivityState;
                    int min = string9.equals("null") ? -1 : Math.min(Integer.parseInt(string9), CellSignalStrength.getNumSignalStrengthLevels());
                    mobileState2.level = min;
                    mobileState2.connected = min >= 0;
                }
                if (bundle.containsKey("inflate")) {
                    for (int i4 = 0; i4 < this.mMobileSignalControllers.size(); i4++) {
                        ((MobileSignalController) this.mMobileSignalControllers.valueAt(i4)).mInflateSignalStrengths = "true".equals(bundle.getString("inflate"));
                    }
                }
                String string10 = bundle.getString("activity");
                if (string10 != null) {
                    ((MobileState) connectivityState).dataConnected = true;
                    int hashCode = string10.hashCode();
                    if (hashCode != 3365) {
                        if (hashCode != 110414) {
                            if (hashCode == 100357129 && string10.equals("inout")) {
                                c = 0;
                            }
                        } else if (string10.equals("out")) {
                            c = 2;
                        }
                    } else if (string10.equals("in")) {
                        c = 1;
                    }
                    if (c == 0) {
                        mobileSignalController2.setActivity(3);
                    } else if (c == 1) {
                        mobileSignalController2.setActivity(1);
                    } else if (c != 2) {
                        mobileSignalController2.setActivity(0);
                    } else {
                        mobileSignalController2.setActivity(2);
                    }
                } else {
                    mobileSignalController2.setActivity(0);
                }
                ((MobileState) connectivityState).enabled = equals3;
                mobileSignalController2.notifyListeners();
            }
            String string11 = bundle.getString("carriernetworkchange");
            if (string11 != null) {
                boolean equals4 = string11.equals("show");
                for (int i5 = 0; i5 < this.mMobileSignalControllers.size(); i5++) {
                    MobileSignalController mobileSignalController3 = (MobileSignalController) this.mMobileSignalControllers.valueAt(i5);
                    ((MobileState) mobileSignalController3.mCurrentState).carrierNetworkChangeMode = equals4;
                    mobileSignalController3.updateTelephony();
                }
            }
        }
    }

    public void doUpdateMobileControllers() {
        List completeActiveSubscriptionInfoList = this.mSubscriptionManager.getCompleteActiveSubscriptionInfoList();
        if (completeActiveSubscriptionInfoList == null) {
            completeActiveSubscriptionInfoList = Collections.emptyList();
        }
        if (completeActiveSubscriptionInfoList.size() == 2) {
            SubscriptionInfo subscriptionInfo = completeActiveSubscriptionInfoList.get(0);
            SubscriptionInfo subscriptionInfo2 = completeActiveSubscriptionInfoList.get(1);
            if (subscriptionInfo.getGroupUuid() != null && subscriptionInfo.getGroupUuid().equals(subscriptionInfo2.getGroupUuid()) && (subscriptionInfo.isOpportunistic() || subscriptionInfo2.isOpportunistic())) {
                if (CarrierConfigManager.getDefaultConfig().getBoolean("always_show_primary_signal_bar_in_opportunistic_network_boolean")) {
                    if (!subscriptionInfo.isOpportunistic()) {
                        subscriptionInfo = subscriptionInfo2;
                    }
                    completeActiveSubscriptionInfoList.remove(subscriptionInfo);
                } else {
                    if (subscriptionInfo.getSubscriptionId() == this.mActiveMobileDataSubscription) {
                        subscriptionInfo = subscriptionInfo2;
                    }
                    completeActiveSubscriptionInfoList.remove(subscriptionInfo);
                }
            }
        }
        if (hasCorrectMobileControllers(completeActiveSubscriptionInfoList)) {
            updateNoSims();
            return;
        }
        synchronized (this.mLock) {
            setCurrentSubscriptionsLocked(completeActiveSubscriptionInfoList);
        }
        updateNoSims();
        recalculateEmergency();
    }

    @Override // com.android.systemui.Dumpable
    @NeverCompile
    public final void dump(PrintWriter printWriter, String[] strArr) {
        String str;
        String[] strArr2;
        StringBuilder m = KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(printWriter, "NetworkController state:", "  mUserSetup=");
        m.append(this.mUserSetup);
        printWriter.println(m.toString());
        printWriter.println("  - telephony ------");
        printWriter.print("  hasVoiceCallingFeature()=");
        int i = 0;
        printWriter.println(this.mPhone.getPhoneType() != 0);
        StringBuilder m2 = KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(new StringBuilder("  mListening="), this.mListening, printWriter, "  mActiveMobileDataSubscription=");
        m2.append(this.mActiveMobileDataSubscription);
        printWriter.println(m2.toString());
        printWriter.println("  - connectivity ------");
        printWriter.print("  mConnectedTransports=");
        printWriter.println(this.mConnectedTransports);
        printWriter.print("  mValidatedTransports=");
        printWriter.println(this.mValidatedTransports);
        printWriter.print("  mInetCondition=");
        printWriter.println(this.mInetCondition);
        printWriter.print("  mAirplaneMode=");
        printWriter.println(this.mAirplaneMode);
        printWriter.print("  mLocale=");
        printWriter.println(this.mLocale);
        printWriter.print("  mLastServiceState=");
        printWriter.println(this.mLastServiceState);
        printWriter.print("  mIsEmergency=");
        printWriter.println(this.mIsEmergency);
        printWriter.print("  mEmergencySource=");
        int i2 = this.mEmergencySource;
        if (i2 > 300) {
            str = "ASSUMED_VOICE_CONTROLLER(" + (i2 - 200) + ")";
        } else if (i2 > 300) {
            str = "NO_SUB(" + (i2 - 300) + ")";
        } else if (i2 > 200) {
            str = "VOICE_CONTROLLER(" + (i2 - 200) + ")";
        } else if (i2 > 100) {
            str = "FIRST_CONTROLLER(" + (i2 - 100) + ")";
        } else {
            str = i2 == 0 ? "NO_CONTROLLERS" : "UNKNOWN_SOURCE";
        }
        printWriter.println(str);
        printWriter.println("  - DefaultNetworkCallback -----");
        int i3 = 0;
        for (int i4 = 0; i4 < 16; i4++) {
            if (this.mHistory[i4] != null) {
                i3++;
            }
        }
        for (int i5 = this.mHistoryIndex + 15; i5 >= (this.mHistoryIndex + 16) - i3; i5 += -1) {
            printWriter.println("  Previous NetworkCallback(" + ((this.mHistoryIndex + 16) - i5) + "): " + this.mHistory[i5 & 15]);
        }
        printWriter.println("  - config ------");
        for (int i6 = 0; i6 < this.mMobileSignalControllers.size(); i6++) {
            ((MobileSignalController) this.mMobileSignalControllers.valueAt(i6)).dump(printWriter);
        }
        this.mWifiSignalController.dump(printWriter);
        this.mEthernetSignalController.dump(printWriter);
        AccessPointControllerImpl accessPointControllerImpl = this.mAccessPoints;
        accessPointControllerImpl.getClass();
        IndentingPrintWriter indentingPrintWriter = new IndentingPrintWriter(printWriter);
        indentingPrintWriter.println("AccessPointControllerImpl:");
        indentingPrintWriter.increaseIndent();
        indentingPrintWriter.println("Callbacks: " + Arrays.toString(accessPointControllerImpl.mCallbacks.toArray()));
        indentingPrintWriter.println("WifiPickerTracker: " + accessPointControllerImpl.mWifiPickerTracker.toString());
        if (accessPointControllerImpl.mWifiPickerTracker != null && !accessPointControllerImpl.mCallbacks.isEmpty()) {
            indentingPrintWriter.println("Connected: " + accessPointControllerImpl.mWifiPickerTracker.mConnectedWifiEntry);
            StringBuilder sb = new StringBuilder("Other wifi entries: ");
            WifiPickerTracker wifiPickerTracker = accessPointControllerImpl.mWifiPickerTracker;
            wifiPickerTracker.getClass();
            sb.append(Arrays.toString(new ArrayList(wifiPickerTracker.mWifiEntries).toArray()));
            indentingPrintWriter.println(sb.toString());
        } else if (accessPointControllerImpl.mWifiPickerTracker != null) {
            indentingPrintWriter.println("WifiPickerTracker not started, cannot get reliable entries");
        }
        indentingPrintWriter.decreaseIndent();
        CallbackHandler callbackHandler = this.mCallbackHandler;
        callbackHandler.getClass();
        printWriter.println("  - CallbackHandler -----");
        int i7 = 0;
        while (true) {
            strArr2 = callbackHandler.mHistory;
            if (i >= 64) {
                break;
            }
            if (strArr2[i] != null) {
                i7++;
            }
            i++;
        }
        for (int i8 = callbackHandler.mHistoryIndex + 63; i8 >= (callbackHandler.mHistoryIndex + 64) - i7; i8 += -1) {
            printWriter.println("  Previous Callback(" + ((callbackHandler.mHistoryIndex + 64) - i8) + "): " + strArr2[i8 & 63]);
        }
    }

    public final MobileSignalController getControllerWithSubId(int i) {
        if (!SubscriptionManager.isValidSubscriptionId(i)) {
            if (DEBUG) {
                Log.e("NetworkController", "No data sim selected");
            }
            return this.mDefaultSignalController;
        }
        if (this.mMobileSignalControllers.indexOfKey(i) >= 0) {
            return (MobileSignalController) this.mMobileSignalControllers.get(i);
        }
        if (DEBUG) {
            ClockEventController$zenModeCallback$1$$ExternalSyntheticOutline0.m("Cannot find controller for data sub: ", "NetworkController", i);
        }
        return this.mDefaultSignalController;
    }

    public final int getNumberSubscriptions() {
        return this.mMobileSignalControllers.size();
    }

    public void handleConfigurationChanged() {
        updateMobileControllers();
        for (int i = 0; i < this.mMobileSignalControllers.size(); i++) {
            MobileSignalController mobileSignalController = (MobileSignalController) this.mMobileSignalControllers.valueAt(i);
            mobileSignalController.mConfig = this.mConfig;
            Context context = mobileSignalController.mContext;
            int subscriptionId = mobileSignalController.mSubscriptionInfo.getSubscriptionId();
            CarrierConfigManager carrierConfigManager = (CarrierConfigManager) context.getSystemService(CarrierConfigManager.class);
            PersistableBundle configForSubId = carrierConfigManager != null ? carrierConfigManager.getConfigForSubId(subscriptionId) : null;
            mobileSignalController.mInflateSignalStrengths = configForSubId != null && configForSubId.getBoolean("inflate_signal_strength_bool", false);
            MobileMappings.Config config = mobileSignalController.mConfig;
            mobileSignalController.mMobileMappingsProxy.getClass();
            mobileSignalController.mNetworkToIconLookup = MobileMappings.mapIconSets(config);
            mobileSignalController.mDefaultIcons = !mobileSignalController.mConfig.showAtLeast3G ? TelephonyIcons.G : TelephonyIcons.THREE_G;
            mobileSignalController.updateTelephony();
        }
        refreshLocale();
    }

    public boolean hasCorrectMobileControllers(List list) {
        if (list.size() != this.mMobileSignalControllers.size()) {
            return false;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (this.mMobileSignalControllers.indexOfKey(((SubscriptionInfo) it.next()).getSubscriptionId()) < 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isUserSetup() {
        return this.mUserSetup;
    }

    public final void notifyAllListeners() {
        notifyListeners$1();
        for (int i = 0; i < this.mMobileSignalControllers.size(); i++) {
            ((MobileSignalController) this.mMobileSignalControllers.valueAt(i)).notifyListeners();
        }
        this.mWifiSignalController.notifyListeners();
        this.mEthernetSignalController.notifyListeners();
    }

    public final void notifyListeners$1() {
        CallbackHandler callbackHandler = this.mCallbackHandler;
        boolean z = this.mAirplaneMode;
        SignalIcon$MobileIconGroup signalIcon$MobileIconGroup = TelephonyIcons.CARRIER_NETWORK_CHANGE;
        callbackHandler.setIsAirplaneMode(new IconState(R.drawable.stat_sys_airplane_mode, this.mContext.getString(R.string.accessibility_airplane_mode), z));
        this.mCallbackHandler.setNoSims(this.mHasNoSubs, this.mSimDetected);
    }

    @Override // com.android.systemui.demomode.DemoModeCommandReceiver
    public final void onDemoModeFinished() {
        if (DEBUG) {
            Log.d("NetworkController", "Exiting demo mode");
        }
        updateMobileControllers();
        for (int i = 0; i < this.mMobileSignalControllers.size(); i++) {
            MobileSignalController mobileSignalController = (MobileSignalController) this.mMobileSignalControllers.valueAt(i);
            mobileSignalController.mCurrentState.copyFrom(mobileSignalController.mLastState);
        }
        WifiSignalController wifiSignalController = this.mWifiSignalController;
        wifiSignalController.mCurrentState.copyFrom(wifiSignalController.mLastState);
        this.mReceiverHandler.post(this.mRegisterListeners);
        notifyAllListeners();
    }

    @Override // com.android.systemui.demomode.DemoModeCommandReceiver
    public final void onDemoModeStarted() {
        if (DEBUG) {
            Log.d("NetworkController", "Entering demo mode");
        }
        this.mListening = false;
        for (int i = 0; i < this.mMobileSignalControllers.size(); i++) {
            MobileSignalController mobileSignalController = (MobileSignalController) this.mMobileSignalControllers.valueAt(i);
            mobileSignalController.mMobileStatusTracker.setListening(false);
            mobileSignalController.mContext.getContentResolver().unregisterContentObserver(mobileSignalController.mObserver);
        }
        this.mSubscriptionManager.removeOnSubscriptionsChangedListener(this.mSubscriptionListener);
        this.mBroadcastDispatcher.unregisterReceiver(this);
        this.mDemoInetCondition = this.mInetCondition;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, final Intent intent) {
        char c;
        if (CHATTY) {
            Log.d("NetworkController", "onReceive: intent=" + intent);
        }
        String action = intent.getAction();
        LogBuffer logBuffer = this.mLogBuffer;
        LogMessage obtain = logBuffer.obtain("NetworkController", LogLevel.INFO, new NetworkControllerImpl$$ExternalSyntheticLambda8(), null);
        ((LogMessageImpl) obtain).str1 = action;
        logBuffer.commit(obtain);
        switch (action.hashCode()) {
            case -2104353374:
                if (action.equals("android.intent.action.SERVICE_STATE")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case -1465084191:
                if (action.equals("android.intent.action.ACTION_DEFAULT_VOICE_SUBSCRIPTION_CHANGED")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -1172645946:
                if (action.equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1138588223:
                if (action.equals("android.telephony.action.CARRIER_CONFIG_CHANGED")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case -1076576821:
                if (action.equals("android.intent.action.AIRPLANE_MODE")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -372321735:
                if (action.equals("android.telephony.action.SUBSCRIPTION_CARRIER_IDENTITY_CHANGED")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -229777127:
                if (action.equals("android.intent.action.SIM_STATE_CHANGED")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -25388475:
                if (action.equals("android.intent.action.ACTION_DEFAULT_DATA_SUBSCRIPTION_CHANGED")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 464243859:
                if (action.equals("android.settings.panel.action.INTERNET_CONNECTIVITY")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                updateConnectivity();
                return;
            case 1:
                refreshLocale();
                updateAirplaneMode(false);
                return;
            case 2:
                recalculateEmergency();
                return;
            case 3:
                break;
            case 4:
                int intExtra = intent.getIntExtra("android.telephony.extra.SUBSCRIPTION_ID", -1);
                if (!SubscriptionManager.isValidSubscriptionId(intExtra) || this.mMobileSignalControllers.indexOfKey(intExtra) < 0) {
                    return;
                }
                ((MobileSignalController) this.mMobileSignalControllers.get(intExtra)).handleBroadcast(intent);
                return;
            case 5:
                if (intent.getBooleanExtra("rebroadcastOnUnlock", false)) {
                    return;
                }
                updateMobileControllers();
                return;
            case 6:
                this.mLastServiceState = ServiceState.newFromBundle(intent.getExtras());
                if (this.mMobileSignalControllers.size() == 0) {
                    recalculateEmergency();
                    return;
                }
                return;
            case 7:
                this.mConfig = MobileMappings.Config.readConfig(this.mContext);
                this.mReceiverHandler.post(new NetworkControllerImpl$$ExternalSyntheticLambda2(6, this));
                return;
            case '\b':
                this.mMainHandler.post(new NetworkControllerImpl$$ExternalSyntheticLambda2(2, this));
                return;
            default:
                int intExtra2 = intent.getIntExtra("android.telephony.extra.SUBSCRIPTION_INDEX", -1);
                if (!SubscriptionManager.isValidSubscriptionId(intExtra2)) {
                    final WifiSignalController wifiSignalController = this.mWifiSignalController;
                    wifiSignalController.getClass();
                    wifiSignalController.doInBackground(new Runnable() { // from class: com.android.systemui.statusbar.connectivity.WifiSignalController$$ExternalSyntheticLambda2
                        @Override // java.lang.Runnable
                        public final void run() {
                            WifiSignalController wifiSignalController2 = WifiSignalController.this;
                            Intent intent2 = intent;
                            WifiStatusTracker wifiStatusTracker = wifiSignalController2.mWifiTracker;
                            if (wifiStatusTracker.mWifiManager != null && intent2.getAction().equals("android.net.wifi.WIFI_STATE_CHANGED")) {
                                wifiStatusTracker.updateWifiState();
                            }
                            wifiSignalController2.copyWifiStates();
                            wifiSignalController2.notifyListenersIfNecessary();
                        }
                    });
                    return;
                } else if (this.mMobileSignalControllers.indexOfKey(intExtra2) >= 0) {
                    ((MobileSignalController) this.mMobileSignalControllers.get(intExtra2)).handleBroadcast(intent);
                    return;
                } else {
                    updateMobileControllers();
                    return;
                }
        }
        for (int i = 0; i < this.mMobileSignalControllers.size(); i++) {
            ((MobileSignalController) this.mMobileSignalControllers.valueAt(i)).handleBroadcast(intent);
        }
        this.mConfig = MobileMappings.Config.readConfig(this.mContext);
        this.mReceiverHandler.post(new NetworkControllerImpl$$ExternalSyntheticLambda2(6, this));
    }

    public final void pushConnectivityToSignals() {
        for (int i = 0; i < this.mMobileSignalControllers.size(); i++) {
            ((MobileSignalController) this.mMobileSignalControllers.valueAt(i)).updateConnectivity(this.mConnectedTransports, this.mValidatedTransports);
        }
        this.mWifiSignalController.updateConnectivity(this.mConnectedTransports, this.mValidatedTransports);
        EthernetSignalController ethernetSignalController = this.mEthernetSignalController;
        BitSet bitSet = this.mConnectedTransports;
        BitSet bitSet2 = this.mValidatedTransports;
        int i2 = ethernetSignalController.mTransportType;
        boolean z = bitSet.get(i2);
        ConnectivityState connectivityState = ethernetSignalController.mCurrentState;
        connectivityState.connected = z;
        connectivityState.inetCondition = bitSet2.get(i2) ? 1 : 0;
        ethernetSignalController.notifyListenersIfNecessary();
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x0014, code lost:
    
        if (r0.isEmergencyOnly() != false) goto L37;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v13, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v16 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void recalculateEmergency() {
        /*
            r7 = this;
            android.util.SparseArray r0 = r7.mMobileSignalControllers
            int r0 = r0.size()
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L18
            r7.mEmergencySource = r1
            android.telephony.ServiceState r0 = r7.mLastServiceState
            if (r0 == 0) goto L63
            boolean r0 = r0.isEmergencyOnly()
            if (r0 == 0) goto L63
            goto Ld5
        L18:
            com.android.settingslib.mobile.MobileStatusTracker$SubscriptionDefaults r0 = r7.mSubDefaults
            r0.getClass()
            int r0 = android.telephony.SubscriptionManager.getDefaultVoiceSubscriptionId()
            boolean r3 = android.telephony.SubscriptionManager.isValidSubscriptionId(r0)
            java.lang.String r4 = "NetworkController"
            if (r3 != 0) goto L68
            r3 = r1
        L2a:
            android.util.SparseArray r5 = r7.mMobileSignalControllers
            int r5 = r5.size()
            if (r3 >= r5) goto L68
            android.util.SparseArray r5 = r7.mMobileSignalControllers
            java.lang.Object r5 = r5.valueAt(r3)
            com.android.systemui.statusbar.connectivity.MobileSignalController r5 = (com.android.systemui.statusbar.connectivity.MobileSignalController) r5
            com.android.systemui.statusbar.connectivity.ConnectivityState r6 = r5.mCurrentState
            com.android.systemui.statusbar.connectivity.MobileState r6 = (com.android.systemui.statusbar.connectivity.MobileState) r6
            boolean r6 = r6.isEmergency
            if (r6 != 0) goto L65
            android.telephony.SubscriptionInfo r0 = r5.mSubscriptionInfo
            int r0 = r0.getSubscriptionId()
            int r0 = r0 + 100
            r7.mEmergencySource = r0
            boolean r0 = com.android.systemui.statusbar.connectivity.NetworkControllerImpl.DEBUG
            if (r0 == 0) goto L63
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "Found emergency "
            r0.<init>(r2)
            java.lang.String r2 = r5.mTag
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r4, r0)
        L63:
            r2 = r1
            goto Ld5
        L65:
            int r3 = r3 + 1
            goto L2a
        L68:
            android.util.SparseArray r3 = r7.mMobileSignalControllers
            int r3 = r3.indexOfKey(r0)
            if (r3 < 0) goto L8c
            int r2 = r0 + 200
            r7.mEmergencySource = r2
            boolean r2 = com.android.systemui.statusbar.connectivity.NetworkControllerImpl.DEBUG
            if (r2 == 0) goto L7d
            java.lang.String r2 = "Getting emergency from "
            androidx.exifinterface.media.ExifInterface$$ExternalSyntheticOutline0.m(r2, r4, r0)
        L7d:
            android.util.SparseArray r2 = r7.mMobileSignalControllers
            java.lang.Object r0 = r2.get(r0)
            com.android.systemui.statusbar.connectivity.MobileSignalController r0 = (com.android.systemui.statusbar.connectivity.MobileSignalController) r0
            com.android.systemui.statusbar.connectivity.ConnectivityState r0 = r0.mCurrentState
            com.android.systemui.statusbar.connectivity.MobileState r0 = (com.android.systemui.statusbar.connectivity.MobileState) r0
            boolean r2 = r0.isEmergency
            goto Ld5
        L8c:
            android.util.SparseArray r3 = r7.mMobileSignalControllers
            int r3 = r3.size()
            if (r3 != r2) goto Lc8
            android.util.SparseArray r0 = r7.mMobileSignalControllers
            int r0 = r0.keyAt(r1)
            int r0 = r0 + 400
            r7.mEmergencySource = r0
            boolean r0 = com.android.systemui.statusbar.connectivity.NetworkControllerImpl.DEBUG
            if (r0 == 0) goto Lb9
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "Getting assumed emergency from "
            r0.<init>(r2)
            android.util.SparseArray r2 = r7.mMobileSignalControllers
            int r2 = r2.keyAt(r1)
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r4, r0)
        Lb9:
            android.util.SparseArray r0 = r7.mMobileSignalControllers
            java.lang.Object r0 = r0.valueAt(r1)
            com.android.systemui.statusbar.connectivity.MobileSignalController r0 = (com.android.systemui.statusbar.connectivity.MobileSignalController) r0
            com.android.systemui.statusbar.connectivity.ConnectivityState r0 = r0.mCurrentState
            com.android.systemui.statusbar.connectivity.MobileState r0 = (com.android.systemui.statusbar.connectivity.MobileState) r0
            boolean r2 = r0.isEmergency
            goto Ld5
        Lc8:
            boolean r3 = com.android.systemui.statusbar.connectivity.NetworkControllerImpl.DEBUG
            if (r3 == 0) goto Ld1
            java.lang.String r3 = "Cannot find controller for voice sub: "
            com.android.keyguard.ClockEventController$zenModeCallback$1$$ExternalSyntheticOutline0.m(r3, r4, r0)
        Ld1:
            int r0 = r0 + 300
            r7.mEmergencySource = r0
        Ld5:
            r7.mIsEmergency = r2
            com.android.systemui.statusbar.connectivity.CallbackHandler r7 = r7.mCallbackHandler
            android.os.Message r7 = r7.obtainMessage(r1, r2, r1)
            r7.sendToTarget()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.connectivity.NetworkControllerImpl.recalculateEmergency():void");
    }

    public final void refreshLocale() {
        Locale locale = this.mContext.getResources().getConfiguration().locale;
        if (locale.equals(this.mLocale)) {
            return;
        }
        this.mLocale = locale;
        WifiStatusTracker wifiStatusTracker = this.mWifiSignalController.mWifiTracker;
        wifiStatusTracker.updateStatusLabel();
        wifiStatusTracker.mMainThreadHandler.post(new WifiStatusTracker$$ExternalSyntheticLambda0(0, wifiStatusTracker));
        notifyAllListeners();
    }

    public void registerListeners() {
        for (int i = 0; i < this.mMobileSignalControllers.size(); i++) {
            ((MobileSignalController) this.mMobileSignalControllers.valueAt(i)).registerListener();
        }
        if (this.mSubscriptionListener == null) {
            this.mSubscriptionListener = new SubListener(this.mBgLooper);
        }
        this.mSubscriptionManager.addOnSubscriptionsChangedListener(this.mSubscriptionListener);
        TelephonyListenerManager telephonyListenerManager = this.mTelephonyListenerManager;
        telephonyListenerManager.mTelephonyCallback.mActiveDataSubscriptionIdListeners.add(this.mPhoneStateListener);
        telephonyListenerManager.updateListening();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.telephony.action.CARRIER_CONFIG_CHANGED");
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.addAction("android.intent.action.AIRPLANE_MODE");
        intentFilter.addAction("android.intent.action.SERVICE_STATE");
        intentFilter.addAction("android.intent.action.SIM_STATE_CHANGED");
        intentFilter.addAction("android.settings.panel.action.INTERNET_CONNECTIVITY");
        intentFilter.addAction("android.intent.action.ACTION_DEFAULT_DATA_SUBSCRIPTION_CHANGED");
        intentFilter.addAction("android.intent.action.ACTION_DEFAULT_VOICE_SUBSCRIPTION_CHANGED");
        intentFilter.addAction("android.telephony.action.SERVICE_PROVIDERS_UPDATED");
        intentFilter.addAction("android.telephony.action.SUBSCRIPTION_CARRIER_IDENTITY_CHANGED");
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        this.mBroadcastDispatcher.registerReceiverWithHandler(this, intentFilter, this.mReceiverHandler);
        this.mListening = true;
        this.mReceiverHandler.post(new NetworkControllerImpl$$ExternalSyntheticLambda2(1, this));
        Handler handler = this.mReceiverHandler;
        WifiSignalController wifiSignalController = this.mWifiSignalController;
        Objects.requireNonNull(wifiSignalController);
        handler.post(new NetworkControllerImpl$$ExternalSyntheticLambda2(7, wifiSignalController));
        this.mReceiverHandler.post(new NetworkControllerImpl$$ExternalSyntheticLambda2(0, this));
        updateMobileControllers();
        this.mReceiverHandler.post(new NetworkControllerImpl$$ExternalSyntheticLambda2(3, this));
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void removeCallback(Object obj) {
        this.mCallbackHandler.obtainMessage(7, 0, 0, (SignalCallback) obj).sendToTarget();
    }

    public void setCurrentSubscriptionsLocked(List list) {
        SparseArray sparseArray;
        int i;
        int i2;
        Collections.sort(list, new AnonymousClass8());
        Locale locale = Locale.US;
        List list2 = this.mCurrentSubscriptions;
        Log.i("NetworkController", "Subscriptions changed: ".concat(String.format(locale, "old=%s, new=%s", list2 != null ? (List) list2.stream().map(new NetworkControllerImpl$$ExternalSyntheticLambda7()).collect(Collectors.toList()) : null, list != null ? (List) list.stream().map(new NetworkControllerImpl$$ExternalSyntheticLambda7()).collect(Collectors.toList()) : null)));
        this.mCurrentSubscriptions = list;
        SparseArray sparseArray2 = new SparseArray();
        for (int i3 = 0; i3 < this.mMobileSignalControllers.size(); i3++) {
            sparseArray2.put(this.mMobileSignalControllers.keyAt(i3), (MobileSignalController) this.mMobileSignalControllers.valueAt(i3));
        }
        this.mMobileSignalControllers.clear();
        int size = list.size();
        int i4 = 0;
        while (i4 < size) {
            int subscriptionId = ((SubscriptionInfo) list.get(i4)).getSubscriptionId();
            if (sparseArray2.indexOfKey(subscriptionId) >= 0) {
                this.mMobileSignalControllers.put(subscriptionId, (MobileSignalController) sparseArray2.get(subscriptionId));
                sparseArray2.remove(subscriptionId);
                i2 = i4;
                i = size;
                sparseArray = sparseArray2;
            } else {
                MobileSignalControllerFactory mobileSignalControllerFactory = this.mMobileFactory;
                MobileMappings.Config config = this.mConfig;
                boolean z = this.mHasMobileDataFeature;
                TelephonyManager createForSubscriptionId = this.mPhone.createForSubscriptionId(subscriptionId);
                SubscriptionInfo subscriptionInfo = (SubscriptionInfo) list.get(i4);
                MobileStatusTracker.SubscriptionDefaults subscriptionDefaults = this.mSubDefaults;
                Looper looper = this.mReceiverHandler.getLooper();
                mobileSignalControllerFactory.getClass();
                sparseArray = sparseArray2;
                i = size;
                MobileSignalController mobileSignalController = new MobileSignalController(mobileSignalControllerFactory.context, config, z, createForSubscriptionId, mobileSignalControllerFactory.callbackHandler, this, mobileSignalControllerFactory.mobileMappings, subscriptionInfo, subscriptionDefaults, looper, new MobileStatusTrackerFactory(createForSubscriptionId, looper, subscriptionInfo, subscriptionDefaults));
                ((MobileState) mobileSignalController.mCurrentState).userSetup = this.mUserSetup;
                mobileSignalController.notifyListenersIfNecessary();
                this.mMobileSignalControllers.put(subscriptionId, mobileSignalController);
                i2 = i4;
                if (((SubscriptionInfo) list.get(i2)).getSimSlotIndex() == 0) {
                    this.mDefaultSignalController = mobileSignalController;
                }
                if (this.mListening) {
                    mobileSignalController.registerListener();
                }
            }
            i4 = i2 + 1;
            size = i;
            sparseArray2 = sparseArray;
        }
        SparseArray sparseArray3 = sparseArray2;
        if (this.mListening) {
            int i5 = 0;
            while (i5 < sparseArray3.size()) {
                SparseArray sparseArray4 = sparseArray3;
                int keyAt = sparseArray4.keyAt(i5);
                if (sparseArray4.get(keyAt) == this.mDefaultSignalController) {
                    this.mDefaultSignalController = null;
                }
                MobileSignalController mobileSignalController2 = (MobileSignalController) sparseArray4.get(keyAt);
                mobileSignalController2.mMobileStatusTracker.setListening(false);
                mobileSignalController2.mContext.getContentResolver().unregisterContentObserver(mobileSignalController2.mObserver);
                i5++;
                sparseArray3 = sparseArray4;
            }
        }
        this.mCallbackHandler.setSubs(list);
        notifyAllListeners();
        pushConnectivityToSignals();
        updateAirplaneMode(true);
    }

    public void setNoNetworksAvailable(boolean z) {
        this.mNoNetworksAvailable = z;
    }

    public final void updateAirplaneMode(boolean z) {
        boolean z2 = Settings.Global.getInt(this.mContext.getContentResolver(), "airplane_mode_on", 0) == 1;
        if (z2 != this.mAirplaneMode || z) {
            this.mAirplaneMode = z2;
            for (int i = 0; i < this.mMobileSignalControllers.size(); i++) {
                MobileSignalController mobileSignalController = (MobileSignalController) this.mMobileSignalControllers.valueAt(i);
                ((MobileState) mobileSignalController.mCurrentState).airplaneMode = this.mAirplaneMode;
                mobileSignalController.notifyListenersIfNecessary();
            }
            notifyListeners$1();
        }
    }

    public final void updateConnectivity() {
        this.mConnectedTransports.clear();
        this.mValidatedTransports.clear();
        NetworkCapabilities networkCapabilities = this.mLastDefaultNetworkCapabilities;
        boolean z = false;
        if (networkCapabilities != null) {
            for (int i : networkCapabilities.getTransportTypes()) {
                if (i == 0 || i == 1 || i == 3) {
                    if (i == 0) {
                        NetworkCapabilities networkCapabilities2 = this.mLastDefaultNetworkCapabilities;
                        if (((networkCapabilities2.getTransportInfo() == null || !(networkCapabilities2.getTransportInfo() instanceof VcnTransportInfo)) ? null : networkCapabilities2.getTransportInfo().getWifiInfo()) != null) {
                            this.mConnectedTransports.set(1);
                            if (this.mLastDefaultNetworkCapabilities.hasCapability(16)) {
                                this.mValidatedTransports.set(1);
                            }
                        }
                    }
                    this.mConnectedTransports.set(i);
                    if (this.mLastDefaultNetworkCapabilities.hasCapability(16)) {
                        this.mValidatedTransports.set(i);
                    }
                }
            }
        }
        if (this.mForceCellularValidated) {
            this.mValidatedTransports.set(0);
        }
        if (CHATTY) {
            Log.d("NetworkController", "updateConnectivity: mConnectedTransports=" + this.mConnectedTransports);
            Log.d("NetworkController", "updateConnectivity: mValidatedTransports=" + this.mValidatedTransports);
        }
        this.mInetCondition = this.mValidatedTransports.get(0) || this.mValidatedTransports.get(1) || this.mValidatedTransports.get(3);
        pushConnectivityToSignals();
        if (!this.mConnectedTransports.get(0) && !this.mConnectedTransports.get(1) && !this.mConnectedTransports.get(3)) {
            z = true;
        }
        this.mNoDefaultNetwork = z;
        this.mCallbackHandler.setConnectivityStatus(z, !this.mInetCondition, this.mNoNetworksAvailable);
    }

    public final void updateMobileControllers() {
        if (this.mListening) {
            doUpdateMobileControllers();
        }
    }

    public void updateNoSims() {
        boolean z = false;
        boolean z2 = this.mHasMobileDataFeature && this.mMobileSignalControllers.size() == 0;
        int activeModemCount = this.mPhone.getActiveModemCount();
        int i = 0;
        while (true) {
            if (i < activeModemCount) {
                int simState = this.mPhone.getSimState(i);
                if (simState != 1 && simState != 0) {
                    z = true;
                    break;
                }
                i++;
            } else {
                break;
            }
        }
        if (z2 == this.mHasNoSubs && z == this.mSimDetected) {
            return;
        }
        this.mHasNoSubs = z2;
        this.mSimDetected = z;
        this.mCallbackHandler.setNoSims(z2, z);
    }

    /* JADX WARN: Type inference failed for: r0v23, types: [com.android.systemui.statusbar.connectivity.NetworkControllerImpl$$ExternalSyntheticLambda6] */
    public NetworkControllerImpl(Context context, ConnectivityManager connectivityManager, TelephonyManager telephonyManager, TelephonyListenerManager telephonyListenerManager, WifiManager wifiManager, SubscriptionManager subscriptionManager, MobileMappings.Config config, Looper looper, Executor executor, CallbackHandler callbackHandler, AccessPointControllerImpl accessPointControllerImpl, StatusBarPipelineFlags statusBarPipelineFlags, DataUsageController dataUsageController, MobileStatusTracker.SubscriptionDefaults subscriptionDefaults, final DeviceProvisionedController deviceProvisionedController, BroadcastDispatcher broadcastDispatcher, UserTracker userTracker, DemoModeController demoModeController, CarrierConfigTracker carrierConfigTracker, WifiStatusTrackerFactory wifiStatusTrackerFactory, MobileSignalControllerFactory mobileSignalControllerFactory, Handler handler, DumpManager dumpManager, LogBuffer logBuffer) {
        this.mLock = new Object();
        this.mActiveMobileDataSubscription = -1;
        this.mMobileSignalControllers = new SparseArray();
        this.mConnectedTransports = new BitSet();
        this.mValidatedTransports = new BitSet();
        this.mAirplaneMode = false;
        this.mNoDefaultNetwork = false;
        this.mNoNetworksAvailable = true;
        this.mLocale = null;
        this.mCurrentSubscriptions = new ArrayList();
        this.mHistory = new String[16];
        UserTracker.Callback callback = new UserTracker.Callback() { // from class: com.android.systemui.statusbar.connectivity.NetworkControllerImpl.2
            @Override // com.android.systemui.settings.UserTracker.Callback
            public final void onUserChanged(int i, Context context2) {
                NetworkControllerImpl networkControllerImpl = NetworkControllerImpl.this;
                networkControllerImpl.mAccessPoints.mCurrentUser = i;
                networkControllerImpl.updateConnectivity();
            }
        };
        this.mUserChangedCallback = callback;
        this.mClearForceValidated = new NetworkControllerImpl$$ExternalSyntheticLambda2(4, this);
        this.mRegisterListeners = new NetworkControllerImpl$$ExternalSyntheticLambda2(5, this);
        this.mContext = context;
        this.mTelephonyListenerManager = telephonyListenerManager;
        this.mConfig = config;
        this.mMainHandler = handler;
        Handler handler2 = new Handler(looper);
        this.mReceiverHandler = handler2;
        this.mBgLooper = looper;
        this.mBgExecutor = executor;
        this.mCallbackHandler = callbackHandler;
        this.mDataSaverController = new DataSaverControllerImpl(context);
        this.mBroadcastDispatcher = broadcastDispatcher;
        this.mMobileFactory = mobileSignalControllerFactory;
        this.mSubscriptionManager = subscriptionManager;
        this.mSubDefaults = subscriptionDefaults;
        boolean isDataCapable = telephonyManager.isDataCapable();
        this.mHasMobileDataFeature = isDataCapable;
        this.mDemoModeController = demoModeController;
        this.mLogBuffer = logBuffer;
        this.mPhone = telephonyManager;
        this.mWifiManager = wifiManager;
        this.mLocale = context.getResources().getConfiguration().locale;
        this.mAccessPoints = accessPointControllerImpl;
        this.mDataUsageController = dataUsageController;
        dataUsageController.getClass();
        this.mWifiSignalController = new WifiSignalController(context, isDataCapable, callbackHandler, this, wifiManager, wifiStatusTrackerFactory, handler2);
        EthernetSignalController ethernetSignalController = new EthernetSignalController("EthernetSignalController", context, 3, callbackHandler, this);
        int[][] iArr = EthernetIcons.ETHERNET_ICONS;
        int[] iArr2 = AccessibilityContentDescriptions.ETHERNET_CONNECTION_VALUES;
        SignalIcon$IconGroup signalIcon$IconGroup = new SignalIcon$IconGroup("Ethernet Icons", iArr, null, iArr2, 0, 0, 0, 0, iArr2[0]);
        ethernetSignalController.mLastState.iconGroup = signalIcon$IconGroup;
        ethernetSignalController.mCurrentState.iconGroup = signalIcon$IconGroup;
        this.mEthernetSignalController = ethernetSignalController;
        updateAirplaneMode(true);
        ((UserTrackerImpl) userTracker).addCallback(callback, new HandlerExecutor(handler));
        DeviceProvisionedControllerImpl deviceProvisionedControllerImpl = (DeviceProvisionedControllerImpl) deviceProvisionedController;
        deviceProvisionedControllerImpl.addCallback(new DeviceProvisionedController.DeviceProvisionedListener() { // from class: com.android.systemui.statusbar.connectivity.NetworkControllerImpl.4
            @Override // com.android.systemui.statusbar.policy.DeviceProvisionedController.DeviceProvisionedListener
            public final void onUserSetupChanged() {
                boolean isCurrentUserSetup = ((DeviceProvisionedControllerImpl) deviceProvisionedController).isCurrentUserSetup();
                boolean z = NetworkControllerImpl.DEBUG;
                NetworkControllerImpl networkControllerImpl = NetworkControllerImpl.this;
                networkControllerImpl.mReceiverHandler.post(new NetworkControllerImpl$$ExternalSyntheticLambda12(networkControllerImpl, isCurrentUserSetup));
            }
        });
        handler2.post(new NetworkControllerImpl$$ExternalSyntheticLambda12(this, deviceProvisionedControllerImpl.isCurrentUserSetup()));
        WifiManager.ScanResultsCallback scanResultsCallback = new WifiManager.ScanResultsCallback() { // from class: com.android.systemui.statusbar.connectivity.NetworkControllerImpl.5
            @Override // android.net.wifi.WifiManager.ScanResultsCallback
            public final void onScanResultsAvailable() {
                NetworkControllerImpl networkControllerImpl = NetworkControllerImpl.this;
                networkControllerImpl.mNoNetworksAvailable = true;
                Iterator<ScanResult> it = networkControllerImpl.mWifiManager.getScanResults().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    } else if (!it.next().SSID.equals(((WifiState) NetworkControllerImpl.this.mWifiSignalController.mCurrentState).ssid)) {
                        NetworkControllerImpl.this.mNoNetworksAvailable = false;
                        break;
                    }
                }
                NetworkControllerImpl networkControllerImpl2 = NetworkControllerImpl.this;
                boolean z = networkControllerImpl2.mNoDefaultNetwork;
                if (z) {
                    networkControllerImpl2.mCallbackHandler.setConnectivityStatus(z, true ^ networkControllerImpl2.mInetCondition, networkControllerImpl2.mNoNetworksAvailable);
                }
            }
        };
        if (wifiManager != null) {
            wifiManager.registerScanResultsCallback(new ConcurrencyHelpers$$ExternalSyntheticLambda0(handler2), scanResultsCallback);
        }
        connectivityManager.registerDefaultNetworkCallback(new ConnectivityManager.NetworkCallback() { // from class: com.android.systemui.statusbar.connectivity.NetworkControllerImpl.6
            public Network mLastNetwork;
            public NetworkCapabilities mLastNetworkCapabilities;

            @Override // android.net.ConnectivityManager.NetworkCallback
            public final void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
                NetworkCapabilities networkCapabilities2 = this.mLastNetworkCapabilities;
                boolean z = networkCapabilities2 != null && networkCapabilities2.hasCapability(16);
                boolean hasCapability = networkCapabilities.hasCapability(16);
                if (network.equals(this.mLastNetwork) && hasCapability == z) {
                    int[] m874$$Nest$mgetProcessedTransportTypes = NetworkControllerImpl.m874$$Nest$mgetProcessedTransportTypes(NetworkControllerImpl.this, networkCapabilities);
                    Arrays.sort(m874$$Nest$mgetProcessedTransportTypes);
                    NetworkCapabilities networkCapabilities3 = this.mLastNetworkCapabilities;
                    int[] m874$$Nest$mgetProcessedTransportTypes2 = networkCapabilities3 != null ? NetworkControllerImpl.m874$$Nest$mgetProcessedTransportTypes(NetworkControllerImpl.this, networkCapabilities3) : null;
                    if (m874$$Nest$mgetProcessedTransportTypes2 != null) {
                        Arrays.sort(m874$$Nest$mgetProcessedTransportTypes2);
                    }
                    if (Arrays.equals(m874$$Nest$mgetProcessedTransportTypes, m874$$Nest$mgetProcessedTransportTypes2)) {
                        return;
                    }
                }
                this.mLastNetwork = network;
                this.mLastNetworkCapabilities = networkCapabilities;
                NetworkControllerImpl.this.mLastDefaultNetworkCapabilities = networkCapabilities;
                String str = NetworkControllerImpl.SSDF.format(Long.valueOf(System.currentTimeMillis())) + ",onCapabilitiesChanged: network=" + network + ",networkCapabilities=" + networkCapabilities;
                NetworkControllerImpl networkControllerImpl = NetworkControllerImpl.this;
                String[] strArr = networkControllerImpl.mHistory;
                int i = networkControllerImpl.mHistoryIndex;
                strArr[i] = str;
                networkControllerImpl.mHistoryIndex = (i + 1) % 16;
                networkControllerImpl.updateConnectivity();
            }

            @Override // android.net.ConnectivityManager.NetworkCallback
            public final void onLost(Network network) {
                this.mLastNetwork = null;
                this.mLastNetworkCapabilities = null;
                NetworkControllerImpl.this.mLastDefaultNetworkCapabilities = null;
                String str = NetworkControllerImpl.SSDF.format(Long.valueOf(System.currentTimeMillis())) + ",onLost: network=" + network;
                NetworkControllerImpl networkControllerImpl = NetworkControllerImpl.this;
                String[] strArr = networkControllerImpl.mHistory;
                int i = networkControllerImpl.mHistoryIndex;
                strArr[i] = str;
                networkControllerImpl.mHistoryIndex = (i + 1) % 16;
                networkControllerImpl.updateConnectivity();
            }
        }, handler2);
        this.mPhoneStateListener = new TelephonyCallback.ActiveDataSubscriptionIdListener() { // from class: com.android.systemui.statusbar.connectivity.NetworkControllerImpl$$ExternalSyntheticLambda6
            @Override // android.telephony.TelephonyCallback.ActiveDataSubscriptionIdListener
            public final void onActiveDataSubscriptionIdChanged(final int i) {
                final NetworkControllerImpl networkControllerImpl = NetworkControllerImpl.this;
                networkControllerImpl.mBgExecutor.execute(new Runnable() { // from class: com.android.systemui.statusbar.connectivity.NetworkControllerImpl$$ExternalSyntheticLambda11
                    @Override // java.lang.Runnable
                    public final void run() {
                        NetworkControllerImpl networkControllerImpl2 = NetworkControllerImpl.this;
                        int i2 = i;
                        int i3 = networkControllerImpl2.mActiveMobileDataSubscription;
                        if (networkControllerImpl2.mValidatedTransports.get(0)) {
                            SubscriptionInfo activeSubscriptionInfo = networkControllerImpl2.mSubscriptionManager.getActiveSubscriptionInfo(i3);
                            SubscriptionInfo activeSubscriptionInfo2 = networkControllerImpl2.mSubscriptionManager.getActiveSubscriptionInfo(i2);
                            if (activeSubscriptionInfo != null && activeSubscriptionInfo2 != null && activeSubscriptionInfo.getGroupUuid() != null && activeSubscriptionInfo.getGroupUuid().equals(activeSubscriptionInfo2.getGroupUuid())) {
                                if (NetworkControllerImpl.DEBUG) {
                                    Log.d("NetworkController", ": mForceCellularValidated to true.");
                                }
                                networkControllerImpl2.mForceCellularValidated = true;
                                networkControllerImpl2.mReceiverHandler.removeCallbacks(networkControllerImpl2.mClearForceValidated);
                                networkControllerImpl2.mReceiverHandler.postDelayed(networkControllerImpl2.mClearForceValidated, 2000L);
                            }
                        }
                        networkControllerImpl2.mActiveMobileDataSubscription = i2;
                        networkControllerImpl2.doUpdateMobileControllers();
                    }
                });
            }
        };
        dumpManager.registerNormalDumpable("NetworkController", this);
    }
}

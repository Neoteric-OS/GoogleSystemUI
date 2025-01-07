package com.android.systemui.statusbar.connectivity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.telephony.CellSignalStrength;
import android.telephony.CellSignalStrengthCdma;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyDisplayInfo;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import com.android.settingslib.SignalIcon$IconGroup;
import com.android.settingslib.SignalIcon$MobileIconGroup;
import com.android.settingslib.Utils;
import com.android.settingslib.graph.SignalDrawable;
import com.android.settingslib.mobile.MobileIconCarrierIdOverridesImpl;
import com.android.settingslib.mobile.MobileMappings;
import com.android.settingslib.mobile.MobileStatusTracker;
import com.android.settingslib.mobile.TelephonyIcons;
import com.android.systemui.statusbar.pipeline.mobile.util.MobileMappingsProxyImpl;
import com.android.wm.shell.R;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.BitSet;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MobileSignalController extends SignalController {
    public static final SimpleDateFormat SSDF = new SimpleDateFormat("MM-dd HH:mm:ss.SSS");
    public MobileMappings.Config mConfig;
    public SignalIcon$MobileIconGroup mDefaultIcons;
    public final MobileStatusTracker.SubscriptionDefaults mDefaults;
    boolean mInflateSignalStrengths;
    public final MobileMappingsProxyImpl mMobileMappingsProxy;
    public final String[] mMobileStatusHistory;
    public int mMobileStatusHistoryIndex;
    final MobileStatusTracker mMobileStatusTracker;
    public final String mNetworkNameDefault;
    public final String mNetworkNameSeparator;
    public Map mNetworkToIconLookup;
    public final AnonymousClass2 mObserver;
    public final TelephonyManager mPhone;
    public final SubscriptionInfo mSubscriptionInfo;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.connectivity.MobileSignalController$1, reason: invalid class name */
    public final class AnonymousClass1 {
        public String mLastStatus;

        public AnonymousClass1() {
        }

        public final void onMobileStatusChanged(boolean z, MobileStatusTracker.MobileStatus mobileStatus) {
            boolean z2 = SignalController.DEBUG;
            MobileSignalController mobileSignalController = MobileSignalController.this;
            if (z2) {
                Log.d(mobileSignalController.mTag, "onMobileStatusChanged= updateTelephony=" + z + " mobileStatus=" + mobileStatus.toString());
            }
            String mobileStatus2 = mobileStatus.toString();
            if (!mobileStatus2.equals(this.mLastStatus)) {
                this.mLastStatus = mobileStatus2;
                String str = MobileSignalController.SSDF.format(Long.valueOf(System.currentTimeMillis())) + "," + mobileStatus2;
                int i = mobileSignalController.mMobileStatusHistoryIndex;
                mobileSignalController.mMobileStatusHistory[i] = str;
                mobileSignalController.mMobileStatusHistoryIndex = (i + 1) % 64;
            }
            MobileState mobileState = (MobileState) mobileSignalController.mCurrentState;
            mobileState.getClass();
            mobileState.activityIn = mobileStatus.activityIn;
            mobileState.activityOut = mobileStatus.activityOut;
            mobileState.dataSim = mobileStatus.dataSim;
            mobileState.carrierNetworkChangeMode = mobileStatus.carrierNetworkChangeMode;
            mobileState.dataState = mobileStatus.dataState;
            mobileState.signalStrength = mobileStatus.signalStrength;
            mobileState.telephonyDisplayInfo = mobileStatus.telephonyDisplayInfo;
            mobileState.serviceState = mobileStatus.serviceState;
            if (z) {
                mobileSignalController.updateTelephony();
            } else {
                mobileSignalController.notifyListenersIfNecessary();
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class QsInfo {
        public final CharSequence description;
        public final IconState icon;
        public final int ratTypeIcon;

        public QsInfo(int i, IconState iconState, CharSequence charSequence) {
            this.ratTypeIcon = i;
            this.icon = iconState;
            this.description = charSequence;
        }

        public final String toString() {
            return "QsInfo: ratTypeIcon=" + this.ratTypeIcon + " icon=" + this.icon;
        }
    }

    /* JADX WARN: Type inference failed for: r1v10, types: [com.android.systemui.statusbar.connectivity.MobileSignalController$2] */
    public MobileSignalController(Context context, MobileMappings.Config config, boolean z, TelephonyManager telephonyManager, CallbackHandler callbackHandler, NetworkControllerImpl networkControllerImpl, MobileMappingsProxyImpl mobileMappingsProxyImpl, SubscriptionInfo subscriptionInfo, MobileStatusTracker.SubscriptionDefaults subscriptionDefaults, Looper looper, MobileStatusTrackerFactory mobileStatusTrackerFactory) {
        super("MobileSignalController(" + subscriptionInfo.getSubscriptionId() + ")", context, 0, callbackHandler, networkControllerImpl);
        this.mInflateSignalStrengths = false;
        this.mMobileStatusHistory = new String[64];
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        this.mConfig = config;
        this.mPhone = telephonyManager;
        this.mDefaults = subscriptionDefaults;
        this.mSubscriptionInfo = subscriptionInfo;
        this.mMobileMappingsProxy = mobileMappingsProxyImpl;
        this.mNetworkNameSeparator = getTextIfExists(R.string.status_bar_network_name_separator).toString();
        String charSequence = getTextIfExists(android.R.string.lockscreen_pattern_wrong).toString();
        this.mNetworkNameDefault = charSequence;
        this.mNetworkToIconLookup = MobileMappings.mapIconSets(this.mConfig);
        this.mDefaultIcons = !this.mConfig.showAtLeast3G ? TelephonyIcons.G : TelephonyIcons.THREE_G;
        charSequence = subscriptionInfo.getCarrierName() != null ? subscriptionInfo.getCarrierName().toString() : charSequence;
        MobileState mobileState = (MobileState) this.mLastState;
        MobileState mobileState2 = (MobileState) this.mCurrentState;
        mobileState2.networkName = charSequence;
        mobileState.networkName = charSequence;
        mobileState2.networkNameData = charSequence;
        mobileState.networkNameData = charSequence;
        mobileState2.enabled = z;
        mobileState.enabled = z;
        SignalIcon$MobileIconGroup signalIcon$MobileIconGroup = this.mDefaultIcons;
        mobileState2.iconGroup = signalIcon$MobileIconGroup;
        mobileState.iconGroup = signalIcon$MobileIconGroup;
        this.mObserver = new ContentObserver(new Handler(looper)) { // from class: com.android.systemui.statusbar.connectivity.MobileSignalController.2
            @Override // android.database.ContentObserver
            public final void onChange(boolean z2) {
                MobileSignalController.this.updateTelephony();
            }
        };
        this.mMobileStatusTracker = new MobileStatusTracker(mobileStatusTrackerFactory.phone, mobileStatusTrackerFactory.receiverLooper, mobileStatusTrackerFactory.info, mobileStatusTrackerFactory.defaults, anonymousClass1);
    }

    @Override // com.android.systemui.statusbar.connectivity.SignalController
    public final ConnectivityState cleanState() {
        MobileState mobileState = new MobileState();
        mobileState.networkName = null;
        mobileState.networkNameData = null;
        mobileState.dataSim = false;
        mobileState.dataConnected = false;
        mobileState.isEmergency = false;
        mobileState.airplaneMode = false;
        mobileState.carrierNetworkChangeMode = false;
        mobileState.isDefault = false;
        mobileState.userSetup = false;
        mobileState.roaming = false;
        mobileState.dataState = 0;
        mobileState.defaultDataOff = false;
        mobileState.telephonyDisplayInfo = new TelephonyDisplayInfo(0, 0, false);
        mobileState.carrierId = -1;
        mobileState.networkTypeResIdCache = new NetworkTypeResIdCache();
        return mobileState;
    }

    @Override // com.android.systemui.statusbar.connectivity.SignalController
    public final void dump(PrintWriter printWriter) {
        String[] strArr;
        super.dump(printWriter);
        printWriter.println("  mSubscription=" + this.mSubscriptionInfo + ",");
        printWriter.println("  mInflateSignalStrengths=" + this.mInflateSignalStrengths + ",");
        printWriter.println("  isDataDisabled=" + (this.mPhone.isDataConnectionAllowed() ^ true) + ",");
        printWriter.println("  mNetworkToIconLookup=" + this.mNetworkToIconLookup + ",");
        StringBuilder sb = new StringBuilder("  mMobileStatusTracker.isListening=");
        sb.append(this.mMobileStatusTracker.mListening);
        printWriter.println(sb.toString());
        printWriter.println("  MobileStatusHistory");
        int i = 0;
        int i2 = 0;
        while (true) {
            strArr = this.mMobileStatusHistory;
            if (i >= 64) {
                break;
            }
            if (strArr[i] != null) {
                i2++;
            }
            i++;
        }
        for (int i3 = this.mMobileStatusHistoryIndex + 63; i3 >= (this.mMobileStatusHistoryIndex + 64) - i2; i3 += -1) {
            printWriter.println("  Previous MobileStatus(" + ((this.mMobileStatusHistoryIndex + 64) - i3) + "): " + strArr[i3 & 63]);
        }
        dumpTableData(printWriter);
    }

    @Override // com.android.systemui.statusbar.connectivity.SignalController
    public final int getCurrentIconId() {
        MobileState mobileState = (MobileState) this.mCurrentState;
        SignalIcon$IconGroup signalIcon$IconGroup = mobileState.iconGroup;
        if (signalIcon$IconGroup == TelephonyIcons.CARRIER_NETWORK_CHANGE) {
            int numSignalStrengthLevels = this.mInflateSignalStrengths ? CellSignalStrength.getNumSignalStrengthLevels() + 1 : CellSignalStrength.getNumSignalStrengthLevels();
            int i = SignalDrawable.$r8$clinit;
            return (numSignalStrengthLevels << 8) | 196608;
        }
        if (!mobileState.connected) {
            if (mobileState.enabled) {
                return SignalDrawable.getState(0, this.mInflateSignalStrengths ? CellSignalStrength.getNumSignalStrengthLevels() + 1 : CellSignalStrength.getNumSignalStrengthLevels(), true);
            }
            return 0;
        }
        int i2 = mobileState.level;
        boolean z = this.mInflateSignalStrengths;
        if (z) {
            i2++;
        }
        return SignalDrawable.getState(i2, z ? CellSignalStrength.getNumSignalStrengthLevels() + 1 : CellSignalStrength.getNumSignalStrengthLevels(), (mobileState.userSetup && (signalIcon$IconGroup == TelephonyIcons.DATA_DISABLED || (signalIcon$IconGroup == TelephonyIcons.NOT_DEFAULT_DATA && mobileState.defaultDataOff))) || (mobileState.inetCondition == 0));
    }

    public final void handleBroadcast(Intent intent) {
        String action = intent.getAction();
        boolean equals = action.equals("android.telephony.action.SERVICE_PROVIDERS_UPDATED");
        ConnectivityState connectivityState = this.mCurrentState;
        if (!equals) {
            if (!action.equals("android.intent.action.ACTION_DEFAULT_DATA_SUBSCRIPTION_CHANGED")) {
                if (action.equals("android.telephony.action.SUBSCRIPTION_CARRIER_IDENTITY_CHANGED")) {
                    ((MobileState) connectivityState).carrierId = intent.getIntExtra("android.telephony.extra.CARRIER_ID", -1);
                    return;
                }
                return;
            }
            this.mDefaults.getClass();
            int activeDataSubscriptionId = SubscriptionManager.getActiveDataSubscriptionId();
            if (SubscriptionManager.isValidSubscriptionId(activeDataSubscriptionId)) {
                ((MobileState) connectivityState).dataSim = activeDataSubscriptionId == this.mSubscriptionInfo.getSubscriptionId();
            } else {
                ((MobileState) connectivityState).dataSim = true;
            }
            notifyListenersIfNecessary();
            return;
        }
        boolean booleanExtra = intent.getBooleanExtra("android.telephony.extra.SHOW_SPN", false);
        String stringExtra = intent.getStringExtra("android.telephony.extra.SPN");
        String stringExtra2 = intent.getStringExtra("android.telephony.extra.DATA_SPN");
        boolean booleanExtra2 = intent.getBooleanExtra("android.telephony.extra.SHOW_PLMN", false);
        String stringExtra3 = intent.getStringExtra("android.telephony.extra.PLMN");
        if (SignalController.CHATTY) {
            Log.d("CarrierLabel", "updateNetworkName showSpn=" + booleanExtra + " spn=" + stringExtra + " dataSpn=" + stringExtra2 + " showPlmn=" + booleanExtra2 + " plmn=" + stringExtra3);
        }
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        if (booleanExtra2 && stringExtra3 != null) {
            sb.append(stringExtra3);
            sb2.append(stringExtra3);
        }
        String str = this.mNetworkNameSeparator;
        if (booleanExtra && stringExtra != null) {
            if (sb.length() != 0) {
                sb.append(str);
            }
            sb.append(stringExtra);
        }
        int length = sb.length();
        String str2 = this.mNetworkNameDefault;
        if (length != 0) {
            ((MobileState) connectivityState).networkName = sb.toString();
        } else {
            ((MobileState) connectivityState).networkName = str2;
        }
        if (booleanExtra && stringExtra2 != null) {
            if (sb2.length() != 0) {
                sb2.append(str);
            }
            sb2.append(stringExtra2);
        }
        if (sb2.length() != 0) {
            ((MobileState) connectivityState).networkNameData = sb2.toString();
        } else {
            ((MobileState) connectivityState).networkNameData = str2;
        }
        notifyListenersIfNecessary();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.android.systemui.statusbar.connectivity.SignalController
    public final void notifyListeners(SignalCallback signalCallback) {
        int i;
        String str;
        QsInfo qsInfo;
        boolean z;
        SignalIcon$IconGroup signalIcon$IconGroup;
        int subscriptionId = this.mSubscriptionInfo.getSubscriptionId();
        WifiState wifiState = (WifiState) this.mNetworkController.mWifiSignalController.mCurrentState;
        if (wifiState.isDefault && wifiState.isCarrierMerged && wifiState.subId == subscriptionId) {
            return;
        }
        ConnectivityState connectivityState = this.mCurrentState;
        SignalIcon$MobileIconGroup signalIcon$MobileIconGroup = (SignalIcon$MobileIconGroup) connectivityState.iconGroup;
        String charSequence = getTextIfExists(getContentDescription()).toString();
        CharSequence textIfExists = getTextIfExists(signalIcon$MobileIconGroup.dataContentDescription);
        String spanned = Html.fromHtml(textIfExists.toString(), 0).toString();
        MobileState mobileState = (MobileState) connectivityState;
        if (mobileState.inetCondition == 0) {
            spanned = this.mContext.getString(R.string.data_connection_no_internet);
        }
        String str2 = spanned;
        Context context = this.mContext;
        SignalIcon$MobileIconGroup signalIcon$MobileIconGroup2 = (SignalIcon$MobileIconGroup) mobileState.iconGroup;
        int i2 = mobileState.carrierId;
        NetworkTypeResIdCache networkTypeResIdCache = mobileState.networkTypeResIdCache;
        Integer num = networkTypeResIdCache.lastCarrierId;
        if (num == null || num.intValue() != i2 || !Intrinsics.areEqual(networkTypeResIdCache.lastIconGroup, signalIcon$MobileIconGroup2)) {
            networkTypeResIdCache.lastCarrierId = Integer.valueOf(i2);
            networkTypeResIdCache.lastIconGroup = signalIcon$MobileIconGroup2;
            MobileIconCarrierIdOverridesImpl mobileIconCarrierIdOverridesImpl = networkTypeResIdCache.overrides;
            mobileIconCarrierIdOverridesImpl.getClass();
            int overrideFor = !MobileIconCarrierIdOverridesImpl.MAPPING.containsKey(Integer.valueOf(i2)) ? 0 : mobileIconCarrierIdOverridesImpl.getOverrideFor(i2, context.getResources(), signalIcon$MobileIconGroup2.name);
            if (overrideFor > 0) {
                networkTypeResIdCache.cachedResId = overrideFor;
                networkTypeResIdCache.isOverridden = true;
            } else {
                networkTypeResIdCache.cachedResId = signalIcon$MobileIconGroup2.dataType;
                networkTypeResIdCache.isOverridden = false;
            }
        }
        int i3 = networkTypeResIdCache.cachedResId;
        IconState iconState = null;
        if (!mobileState.dataSim) {
            i = 0;
            str = null;
        } else {
            if (!mobileState.isDefault) {
                qsInfo = new QsInfo(0, null, null);
                SignalIcon$IconGroup signalIcon$IconGroup2 = mobileState.iconGroup;
                Object[] objArr = (signalIcon$IconGroup2 != TelephonyIcons.DATA_DISABLED || signalIcon$IconGroup2 == TelephonyIcons.NOT_DEFAULT_DATA) && mobileState.userSetup;
                IconState iconState2 = new IconState(getCurrentIconId(), charSequence, (mobileState.enabled || mobileState.airplaneMode) ? false : true);
                z = mobileState.dataConnected;
                if ((z || !mobileState.isDefault) && objArr == false && !this.mConfig.alwaysShowDataRatIcon) {
                    i3 = 0;
                }
                signalCallback.setMobileDataIndicators(new MobileDataIndicators(iconState2, qsInfo.icon, i3, qsInfo.ratTypeIcon, (z || mobileState.carrierNetworkChangeMode || !mobileState.activityIn) ? false : true, (z || mobileState.carrierNetworkChangeMode || !mobileState.activityOut) ? false : true, str2, textIfExists, qsInfo.description, this.mSubscriptionInfo.getSubscriptionId(), mobileState.roaming, (mobileState.enabled || mobileState.airplaneMode) ? false : true));
            }
            i = (mobileState.dataConnected || (((signalIcon$IconGroup = mobileState.iconGroup) == TelephonyIcons.DATA_DISABLED || signalIcon$IconGroup == TelephonyIcons.NOT_DEFAULT_DATA) && mobileState.userSetup) || this.mConfig.alwaysShowDataRatIcon) ? i3 : 0;
            IconState iconState3 = new IconState(getCurrentIconId(), charSequence, mobileState.enabled && !mobileState.isEmergency);
            str = mobileState.isEmergency ? null : mobileState.networkName;
            iconState = iconState3;
        }
        qsInfo = new QsInfo(i, iconState, str);
        SignalIcon$IconGroup signalIcon$IconGroup22 = mobileState.iconGroup;
        if (signalIcon$IconGroup22 != TelephonyIcons.DATA_DISABLED) {
        }
        IconState iconState22 = new IconState(getCurrentIconId(), charSequence, (mobileState.enabled || mobileState.airplaneMode) ? false : true);
        z = mobileState.dataConnected;
        if (z) {
        }
        i3 = 0;
        if (mobileState.enabled) {
        }
        signalCallback.setMobileDataIndicators(new MobileDataIndicators(iconState22, qsInfo.icon, i3, qsInfo.ratTypeIcon, (z || mobileState.carrierNetworkChangeMode || !mobileState.activityIn) ? false : true, (z || mobileState.carrierNetworkChangeMode || !mobileState.activityOut) ? false : true, str2, textIfExists, qsInfo.description, this.mSubscriptionInfo.getSubscriptionId(), mobileState.roaming, (mobileState.enabled || mobileState.airplaneMode) ? false : true));
    }

    public final void registerListener() {
        this.mMobileStatusTracker.setListening(true);
        ContentResolver contentResolver = this.mContext.getContentResolver();
        Uri uriFor = Settings.Global.getUriFor("mobile_data");
        AnonymousClass2 anonymousClass2 = this.mObserver;
        contentResolver.registerContentObserver(uriFor, true, anonymousClass2);
        this.mContext.getContentResolver().registerContentObserver(Settings.Global.getUriFor("mobile_data" + this.mSubscriptionInfo.getSubscriptionId()), true, anonymousClass2);
    }

    public void setActivity(int i) {
        ConnectivityState connectivityState = this.mCurrentState;
        ((MobileState) connectivityState).activityIn = i == 3 || i == 1;
        ((MobileState) connectivityState).activityOut = i == 3 || i == 2;
        notifyListenersIfNecessary();
    }

    public final void updateConnectivity(BitSet bitSet, BitSet bitSet2) {
        int i = this.mTransportType;
        boolean z = bitSet2.get(i);
        MobileState mobileState = (MobileState) this.mCurrentState;
        boolean z2 = bitSet.get(i);
        mobileState.isDefault = z2;
        mobileState.inetCondition = (z || !z2) ? 1 : 0;
        notifyListenersIfNecessary();
    }

    public final void updateTelephony() {
        SignalStrength signalStrength;
        TelephonyDisplayInfo telephonyDisplayInfo;
        int level;
        boolean z = SignalController.DEBUG;
        ConnectivityState connectivityState = this.mCurrentState;
        if (z) {
            StringBuilder sb = new StringBuilder("updateTelephonySignalStrength: hasService=");
            MobileState mobileState = (MobileState) connectivityState;
            sb.append(Utils.isInService(mobileState.serviceState));
            sb.append(" ss=");
            sb.append(mobileState.signalStrength);
            sb.append(" displayInfo=");
            sb.append(mobileState.telephonyDisplayInfo);
            Log.d(this.mTag, sb.toString());
        }
        MobileState mobileState2 = (MobileState) connectivityState;
        SignalIcon$IconGroup signalIcon$IconGroup = mobileState2.iconGroup;
        SignalIcon$MobileIconGroup signalIcon$MobileIconGroup = TelephonyIcons.NOT_DEFAULT_DATA;
        boolean z2 = false;
        NetworkControllerImpl networkControllerImpl = this.mNetworkController;
        if (signalIcon$IconGroup != signalIcon$MobileIconGroup) {
            mobileState2.defaultDataOff = false;
        } else {
            networkControllerImpl.mSubDefaults.getClass();
            mobileState2.defaultDataOff = networkControllerImpl.getControllerWithSubId(SubscriptionManager.getActiveDataSubscriptionId()) == null ? false : !r2.mPhone.isDataConnectionAllowed();
        }
        boolean isInService = Utils.isInService(mobileState2.serviceState);
        mobileState2.connected = isInService;
        if (isInService) {
            SignalStrength signalStrength2 = mobileState2.signalStrength;
            if (signalStrength2 != null) {
                if (signalStrength2.isGsm() || !this.mConfig.alwaysShowCdmaRssi) {
                    level = signalStrength2.getLevel();
                } else {
                    List cellSignalStrengths = signalStrength2.getCellSignalStrengths(CellSignalStrengthCdma.class);
                    if (!cellSignalStrengths.isEmpty()) {
                        level = ((CellSignalStrengthCdma) cellSignalStrengths.get(0)).getLevel();
                    }
                }
                mobileState2.level = level;
            }
            level = 0;
            mobileState2.level = level;
        }
        mobileState2.carrierId = this.mPhone.getSimCarrierId();
        TelephonyDisplayInfo telephonyDisplayInfo2 = mobileState2.telephonyDisplayInfo;
        this.mMobileMappingsProxy.getClass();
        String num = telephonyDisplayInfo2.getOverrideNetworkType() == 0 ? Integer.toString(telephonyDisplayInfo2.getNetworkType()) : MobileMappings.toDisplayIconKey(telephonyDisplayInfo2.getOverrideNetworkType());
        if (this.mNetworkToIconLookup.get(num) != null) {
            mobileState2.iconGroup = (SignalIcon$IconGroup) this.mNetworkToIconLookup.get(num);
        } else {
            mobileState2.iconGroup = this.mDefaultIcons;
        }
        mobileState2.dataConnected = mobileState2.connected && mobileState2.dataState == 2;
        mobileState2.roaming = !((MobileState) connectivityState).carrierNetworkChangeMode && ((signalStrength = mobileState2.signalStrength) == null || signalStrength.isGsm() ? !((telephonyDisplayInfo = mobileState2.telephonyDisplayInfo) == null || !telephonyDisplayInfo.isRoaming()) : this.mPhone.getCdmaEnhancedRoamingIndicatorDisplayNumber() != 1);
        if (((MobileState) connectivityState).carrierNetworkChangeMode) {
            mobileState2.iconGroup = TelephonyIcons.CARRIER_NETWORK_CHANGE;
        } else if (!this.mPhone.isDataConnectionAllowed() && !this.mConfig.alwaysShowDataRatIcon) {
            int subscriptionId = this.mSubscriptionInfo.getSubscriptionId();
            this.mDefaults.getClass();
            if (subscriptionId != SubscriptionManager.getDefaultDataSubscriptionId()) {
                mobileState2.iconGroup = signalIcon$MobileIconGroup;
            } else {
                mobileState2.iconGroup = TelephonyIcons.DATA_DISABLED;
            }
        }
        ServiceState serviceState = mobileState2.serviceState;
        if ((serviceState != null && serviceState.isEmergencyOnly()) != mobileState2.isEmergency) {
            ServiceState serviceState2 = mobileState2.serviceState;
            if (serviceState2 != null && serviceState2.isEmergencyOnly()) {
                z2 = true;
            }
            mobileState2.isEmergency = z2;
            networkControllerImpl.recalculateEmergency();
        }
        String str = mobileState2.networkName;
        String str2 = this.mNetworkNameDefault;
        if (str.equals(str2) && !TextUtils.isEmpty(mobileState2.getOperatorAlphaShort())) {
            mobileState2.networkName = mobileState2.getOperatorAlphaShort();
        }
        if (mobileState2.networkNameData.equals(str2) && mobileState2.dataSim && !TextUtils.isEmpty(mobileState2.getOperatorAlphaShort())) {
            mobileState2.networkNameData = mobileState2.getOperatorAlphaShort();
        }
        notifyListenersIfNecessary();
    }
}

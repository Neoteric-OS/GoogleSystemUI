package com.android.wifitrackerlib;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityDiagnosticsManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.text.format.DateUtils;
import androidx.core.os.BuildCompat;
import com.android.wm.shell.R;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringJoiner;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class Utils {
    public static ScanResult getBestScanResultByLevel(List list) {
        if (list.isEmpty()) {
            return null;
        }
        return (ScanResult) Collections.max(list, Comparator.comparingInt(new Utils$$ExternalSyntheticLambda1()));
    }

    public static String getConnectedDescription(Context context, WifiConfiguration wifiConfiguration, NetworkCapabilities networkCapabilities, WifiInfo wifiInfo, boolean z, boolean z2, ConnectivityDiagnosticsManager.ConnectivityReport connectivityReport) {
        StringJoiner stringJoiner = new StringJoiner(context.getString(R.string.wifitrackerlib_summary_separator));
        boolean hasCapability = networkCapabilities.hasCapability(16);
        boolean hasCapability2 = networkCapabilities.hasCapability(17);
        boolean hasCapability3 = networkCapabilities.hasCapability(24);
        boolean z3 = wifiConfiguration != null && wifiConfiguration.isNoInternetAccessExpected();
        boolean z4 = !hasCapability && networkCapabilities.isPrivateDnsBroken();
        boolean z5 = (hasCapability || hasCapability3 || connectivityReport != null || z3) ? false : true;
        boolean z6 = networkCapabilities.hasCapability(22) || networkCapabilities.hasCapability(26);
        String suggestionOrSpecifierLabel = (wifiConfiguration == null || !(wifiConfiguration.fromWifiNetworkSuggestion || wifiConfiguration.fromWifiNetworkSpecifier)) ? null : getSuggestionOrSpecifierLabel(context, wifiConfiguration);
        boolean z7 = hasCapability ? z : !(z5 || hasCapability2 || z4 || z3) || z6;
        if (TextUtils.isEmpty(suggestionOrSpecifierLabel)) {
            if (z7) {
                stringJoiner.add(context.getResources().getStringArray(R.array.wifitrackerlib_wifi_status)[NetworkInfo.DetailedState.CONNECTED.ordinal()]);
            }
        } else if (z7 || (z && hasCapability3)) {
            stringJoiner.add(context.getString(R.string.wifitrackerlib_connected_via_app, suggestionOrSpecifierLabel));
        } else {
            stringJoiner.add(context.getString(R.string.wifitrackerlib_available_via_app, suggestionOrSpecifierLabel));
        }
        if (z7 && wifiInfo != null && wifiInfo.getCurrentSecurityType() == 1) {
            stringJoiner.add(context.getString(R.string.wifi_connected_less_secure, getSecurityString(context, Arrays.asList(1), false)));
        }
        if (z2) {
            stringJoiner.add(context.getString(R.string.wifi_connected_low_quality));
        }
        if (hasCapability2) {
            stringJoiner.add(context.getString(context.getResources().getIdentifier("network_available_sign_in", "string", "android")));
        } else if (hasCapability3) {
            stringJoiner.add(context.getString(R.string.wifitrackerlib_wifi_limited_connection));
        } else if (z5) {
            stringJoiner.add(context.getString(R.string.wifitrackerlib_checking_for_internet_access));
        } else if (z4) {
            stringJoiner.add(context.getString(R.string.wifitrackerlib_private_dns_broken));
        } else if (!hasCapability) {
            if (z3) {
                stringJoiner.add(context.getString(R.string.wifitrackerlib_wifi_connected_cannot_provide_internet));
            } else {
                stringJoiner.add(context.getString(R.string.wifitrackerlib_wifi_no_internet));
            }
        }
        return stringJoiner.toString();
    }

    public static String getConnectingDescription(Context context, NetworkInfo networkInfo) {
        NetworkInfo.DetailedState detailedState;
        if (context == null || networkInfo == null || (detailedState = networkInfo.getDetailedState()) == null) {
            return "";
        }
        String[] stringArray = context.getResources().getStringArray(R.array.wifitrackerlib_wifi_status);
        int ordinal = detailedState.ordinal();
        return ordinal >= stringArray.length ? "" : stringArray[ordinal];
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00c1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String getDisconnectedDescription(com.android.wifitrackerlib.WifiTrackerInjector r4, android.content.Context r5, android.net.wifi.WifiConfiguration r6, boolean r7) {
        /*
            java.lang.String r4 = ""
            if (r5 == 0) goto L101
            if (r6 != 0) goto L8
            goto L101
        L8:
            java.util.StringJoiner r0 = new java.util.StringJoiner
            r1 = 2131954720(0x7f130c20, float:1.9545947E38)
            java.lang.String r1 = r5.getString(r1)
            r0.<init>(r1)
            if (r7 == 0) goto L21
            r7 = 2131954734(0x7f130c2e, float:1.9545976E38)
            java.lang.String r7 = r5.getString(r7)
            r0.add(r7)
            goto L48
        L21:
            boolean r7 = r6.fromWifiNetworkSuggestion
            if (r7 == 0) goto L3e
            java.lang.String r7 = getSuggestionOrSpecifierLabel(r5, r6)
            boolean r1 = android.text.TextUtils.isEmpty(r7)
            if (r1 != 0) goto L48
            r1 = 2131954693(0x7f130c05, float:1.9545892E38)
            java.lang.Object[] r7 = new java.lang.Object[]{r7}
            java.lang.String r7 = r5.getString(r1, r7)
            r0.add(r7)
            goto L48
        L3e:
            r7 = 2131954745(0x7f130c39, float:1.9545998E38)
            java.lang.String r7 = r5.getString(r7)
            r0.add(r7)
        L48:
            android.net.wifi.WifiConfiguration$NetworkSelectionStatus r7 = r6.getNetworkSelectionStatus()
            int r1 = r7.getNetworkSelectionStatus()
            r2 = 2
            r3 = 2131954732(0x7f130c2c, float:1.9545972E38)
            if (r1 == 0) goto La8
            int r1 = r7.getNetworkSelectionDisableReason()
            switch(r1) {
                case 1: goto La0;
                case 2: goto L9b;
                case 3: goto L93;
                case 4: goto L8b;
                case 5: goto L9b;
                case 6: goto L82;
                case 7: goto L5d;
                case 8: goto L79;
                case 9: goto L9b;
                case 10: goto L5d;
                case 11: goto L5d;
                case 12: goto L67;
                case 13: goto L5e;
                default: goto L5d;
            }
        L5d:
            goto Lb9
        L5e:
            r4 = 2131954733(0x7f130c2d, float:1.9545974E38)
            java.lang.String r4 = r5.getString(r4)
            goto Lf4
        L67:
            boolean r1 = r7.hasEverConnected()
            if (r1 != 0) goto Lb9
            int r7 = r7.getDisableReasonCounter(r2)
            if (r7 <= 0) goto Lb9
            java.lang.String r4 = r5.getString(r3)
            goto Lf4
        L79:
            r4 = 2131954728(0x7f130c28, float:1.9545963E38)
            java.lang.String r4 = r5.getString(r4)
            goto Lf4
        L82:
            r4 = 2131954742(0x7f130c36, float:1.9545992E38)
            java.lang.String r4 = r5.getString(r4)
            goto Lf4
        L8b:
            r4 = 2131954741(0x7f130c35, float:1.954599E38)
            java.lang.String r4 = r5.getString(r4)
            goto Lf4
        L93:
            r4 = 2131954731(0x7f130c2b, float:1.954597E38)
            java.lang.String r4 = r5.getString(r4)
            goto Lf4
        L9b:
            java.lang.String r4 = r5.getString(r3)
            goto Lf4
        La0:
            r4 = 2131954730(0x7f130c2a, float:1.9545967E38)
            java.lang.String r4 = r5.getString(r4)
            goto Lf4
        La8:
            int r1 = r7.getDisableReasonCounter(r2)
            if (r1 <= 0) goto Lb9
            boolean r7 = r7.hasEverConnected()
            if (r7 != 0) goto Lb9
            java.lang.String r4 = r5.getString(r3)
            goto Lf4
        Lb9:
            int r6 = r6.getRecentFailureReason()
            r7 = 17
            if (r6 == r7) goto Led
            switch(r6) {
                case 1002: goto Led;
                case 1003: goto Le5;
                case 1004: goto Led;
                case 1005: goto Ldd;
                case 1006: goto Ld5;
                case 1007: goto Ldd;
                case 1008: goto Ldd;
                case 1009: goto Lcd;
                case 1010: goto Lcd;
                case 1011: goto Lc5;
                default: goto Lc4;
            }
        Lc4:
            goto Lf4
        Lc5:
            r4 = 2131954740(0x7f130c34, float:1.9545988E38)
            java.lang.String r4 = r5.getString(r4)
            goto Lf4
        Lcd:
            r4 = 2131954738(0x7f130c32, float:1.9545984E38)
            java.lang.String r4 = r5.getString(r4)
            goto Lf4
        Ld5:
            r4 = 2131954737(0x7f130c31, float:1.9545982E38)
            java.lang.String r4 = r5.getString(r4)
            goto Lf4
        Ldd:
            r4 = 2131954736(0x7f130c30, float:1.954598E38)
            java.lang.String r4 = r5.getString(r4)
            goto Lf4
        Le5:
            r4 = 2131954744(0x7f130c38, float:1.9545996E38)
            java.lang.String r4 = r5.getString(r4)
            goto Lf4
        Led:
            r4 = 2131954723(0x7f130c23, float:1.9545953E38)
            java.lang.String r4 = r5.getString(r4)
        Lf4:
            boolean r5 = android.text.TextUtils.isEmpty(r4)
            if (r5 != 0) goto Lfd
            r0.add(r4)
        Lfd:
            java.lang.String r4 = r0.toString()
        L101:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wifitrackerlib.Utils.getDisconnectedDescription(com.android.wifitrackerlib.WifiTrackerInjector, android.content.Context, android.net.wifi.WifiConfiguration, boolean):java.lang.String");
    }

    public static String getMeteredDescription(Context context, WifiEntry wifiEntry) {
        return context != null ? (wifiEntry.canSetMeteredChoice() || wifiEntry.getMeteredChoice() == 1) ? wifiEntry.getMeteredChoice() == 1 ? context.getString(R.string.wifitrackerlib_wifi_metered_label) : wifiEntry.getMeteredChoice() == 2 ? context.getString(R.string.wifitrackerlib_wifi_unmetered_label) : wifiEntry.isMetered() ? context.getString(R.string.wifitrackerlib_wifi_metered_label) : "" : "" : "";
    }

    public static InetAddress getNetworkPart(InetAddress inetAddress, int i) {
        byte[] address = inetAddress.getAddress();
        if (i < 0 || i > address.length * 8) {
            throw new IllegalArgumentException("IP address with " + address.length + " bytes has invalid prefix length " + i);
        }
        int i2 = i / 8;
        byte b = (byte) (255 << (8 - (i % 8)));
        if (i2 < address.length) {
            address[i2] = (byte) (b & address[i2]);
        }
        while (true) {
            i2++;
            if (i2 >= address.length) {
                try {
                    return InetAddress.getByAddress(address);
                } catch (UnknownHostException e) {
                    throw new IllegalArgumentException("getNetworkPart error - " + e.toString());
                }
            }
            address[i2] = 0;
        }
    }

    public static String getNetworkSelectionDescription(WifiConfiguration wifiConfiguration) {
        if (wifiConfiguration == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        WifiConfiguration.NetworkSelectionStatus networkSelectionStatus = wifiConfiguration.getNetworkSelectionStatus();
        if (networkSelectionStatus.getNetworkSelectionStatus() != 0) {
            sb.append(" (" + networkSelectionStatus.getNetworkStatusString());
            if (networkSelectionStatus.getDisableTime() > 0) {
                sb.append(" " + DateUtils.formatElapsedTime((System.currentTimeMillis() - networkSelectionStatus.getDisableTime()) / 1000));
            }
            sb.append(")");
        }
        int maxNetworkSelectionDisableReason = WifiConfiguration.NetworkSelectionStatus.getMaxNetworkSelectionDisableReason();
        for (int i = 0; i <= maxNetworkSelectionDisableReason; i++) {
            int disableReasonCounter = networkSelectionStatus.getDisableReasonCounter(i);
            if (disableReasonCounter != 0) {
                sb.append(" ");
                sb.append(WifiConfiguration.NetworkSelectionStatus.getNetworkSelectionDisableReasonString(i));
                sb.append("=");
                sb.append(disableReasonCounter);
            }
        }
        return sb.toString();
    }

    public static String getSecurityString(Context context, List list, boolean z) {
        if (list.size() == 0) {
            return z ? "" : context.getString(R.string.wifitrackerlib_wifi_security_none);
        }
        if (list.size() == 1) {
            int intValue = ((Integer) list.get(0)).intValue();
            if (intValue == 9) {
                return z ? context.getString(R.string.wifitrackerlib_wifi_security_short_eap_wpa3) : context.getString(R.string.wifitrackerlib_wifi_security_eap_wpa3);
            }
            switch (intValue) {
                case 0:
                    return z ? "" : context.getString(R.string.wifitrackerlib_wifi_security_none);
                case 1:
                    return context.getString(R.string.wifitrackerlib_wifi_security_wep);
                case 2:
                    return z ? context.getString(R.string.wifitrackerlib_wifi_security_short_wpa_wpa2) : context.getString(R.string.wifitrackerlib_wifi_security_wpa_wpa2);
                case 3:
                    return z ? context.getString(R.string.wifitrackerlib_wifi_security_short_eap_wpa_wpa2) : context.getString(R.string.wifitrackerlib_wifi_security_eap_wpa_wpa2);
                case 4:
                    return z ? context.getString(R.string.wifitrackerlib_wifi_security_short_sae) : context.getString(R.string.wifitrackerlib_wifi_security_sae);
                case 5:
                    return z ? context.getString(R.string.wifitrackerlib_wifi_security_short_eap_suiteb) : context.getString(R.string.wifitrackerlib_wifi_security_eap_suiteb);
                case 6:
                    return z ? context.getString(R.string.wifitrackerlib_wifi_security_short_owe) : context.getString(R.string.wifitrackerlib_wifi_security_owe);
            }
        }
        if (list.size() == 2) {
            if (list.contains(0) && list.contains(6)) {
                StringJoiner stringJoiner = new StringJoiner("/");
                stringJoiner.add(context.getString(R.string.wifitrackerlib_wifi_security_none));
                stringJoiner.add(z ? context.getString(R.string.wifitrackerlib_wifi_security_short_owe) : context.getString(R.string.wifitrackerlib_wifi_security_owe));
                return stringJoiner.toString();
            }
            if (list.contains(2) && list.contains(4)) {
                return z ? context.getString(R.string.wifitrackerlib_wifi_security_short_wpa_wpa2_wpa3) : context.getString(R.string.wifitrackerlib_wifi_security_wpa_wpa2_wpa3);
            }
            if (list.contains(3) && list.contains(9)) {
                return z ? context.getString(R.string.wifitrackerlib_wifi_security_short_eap_wpa_wpa2_wpa3) : context.getString(R.string.wifitrackerlib_wifi_security_eap_wpa_wpa2_wpa3);
            }
        }
        return z ? "" : context.getString(R.string.wifitrackerlib_wifi_security_none);
    }

    public static List getSecurityTypesFromWifiConfiguration(WifiConfiguration wifiConfiguration) {
        if (wifiConfiguration.allowedKeyManagement.get(14)) {
            return Arrays.asList(8);
        }
        if (wifiConfiguration.allowedKeyManagement.get(13)) {
            return Arrays.asList(7);
        }
        if (wifiConfiguration.allowedKeyManagement.get(10)) {
            return Arrays.asList(5);
        }
        if (wifiConfiguration.allowedKeyManagement.get(9)) {
            return Arrays.asList(6);
        }
        if (wifiConfiguration.allowedKeyManagement.get(8)) {
            return Arrays.asList(4);
        }
        if (wifiConfiguration.allowedKeyManagement.get(4)) {
            return Arrays.asList(2);
        }
        if (wifiConfiguration.allowedKeyManagement.get(2)) {
            return (wifiConfiguration.requirePmf && !wifiConfiguration.allowedPairwiseCiphers.get(1) && wifiConfiguration.allowedProtocols.get(1)) ? Arrays.asList(9) : Arrays.asList(3, 9);
        }
        if (wifiConfiguration.allowedKeyManagement.get(1)) {
            return Arrays.asList(2);
        }
        if (wifiConfiguration.allowedKeyManagement.get(0) && wifiConfiguration.wepKeys != null) {
            int i = 0;
            while (true) {
                String[] strArr = wifiConfiguration.wepKeys;
                if (i >= strArr.length) {
                    break;
                }
                if (strArr[i] != null) {
                    return Arrays.asList(1);
                }
                i++;
            }
        }
        return Arrays.asList(0);
    }

    public static int getSingleSecurityTypeFromMultipleSecurityTypes(List list) {
        if (list.size() == 0) {
            return -1;
        }
        if (list.size() == 1) {
            return ((Integer) list.get(0)).intValue();
        }
        if (list.size() == 2) {
            if (list.contains(0)) {
                return 0;
            }
            if (list.contains(2)) {
                return 2;
            }
            if (list.contains(3)) {
                return 3;
            }
        }
        return ((Integer) list.get(0)).intValue();
    }

    public static String getStandardString(int i, Context context) {
        if (i == 1) {
            return context.getString(R.string.wifitrackerlib_wifi_standard_legacy);
        }
        switch (i) {
            case 4:
                return context.getString(R.string.wifitrackerlib_wifi_standard_11n);
            case 5:
                return context.getString(R.string.wifitrackerlib_wifi_standard_11ac);
            case 6:
                return context.getString(R.string.wifitrackerlib_wifi_standard_11ax);
            case 7:
                return context.getString(R.string.wifitrackerlib_wifi_standard_11ad);
            case 8:
                return context.getString(R.string.wifitrackerlib_wifi_standard_11be);
            default:
                return context.getString(R.string.wifitrackerlib_wifi_standard_unknown);
        }
    }

    public static String getSuggestionOrSpecifierLabel(Context context, WifiConfiguration wifiConfiguration) {
        SubscriptionManager subscriptionManager;
        List<SubscriptionInfo> activeSubscriptionInfoList;
        int i;
        String str;
        TelephonyManager telephonyManager;
        TelephonyManager createForSubscriptionId;
        CharSequence simCarrierIdName;
        if (context == null || wifiConfiguration == null) {
            return "";
        }
        if (wifiConfiguration.carrierId != -1 && (subscriptionManager = (SubscriptionManager) context.getSystemService("telephony_subscription_service")) != null && (activeSubscriptionInfoList = subscriptionManager.getActiveSubscriptionInfoList()) != null && !activeSubscriptionInfoList.isEmpty()) {
            int defaultDataSubscriptionId = SubscriptionManager.getDefaultDataSubscriptionId();
            i = -1;
            for (SubscriptionInfo subscriptionInfo : activeSubscriptionInfoList) {
                if (subscriptionInfo.getCarrierId() == wifiConfiguration.carrierId && (i = subscriptionInfo.getSubscriptionId()) == defaultDataSubscriptionId) {
                    break;
                }
            }
        } else {
            i = -1;
        }
        String str2 = null;
        if (i != -1 && (telephonyManager = (TelephonyManager) context.getSystemService("phone")) != null && (createForSubscriptionId = telephonyManager.createForSubscriptionId(i)) != null && (simCarrierIdName = createForSubscriptionId.getSimCarrierIdName()) != null) {
            str2 = simCarrierIdName.toString();
        }
        if (!TextUtils.isEmpty(str2)) {
            return str2;
        }
        try {
            str = context.getPackageManager().getApplicationInfo(wifiConfiguration.creatorName, 0).loadLabel(context.getPackageManager()).toString();
        } catch (PackageManager.NameNotFoundException unused) {
            str = "";
        }
        return !TextUtils.isEmpty(str) ? str : wifiConfiguration.creatorName;
    }

    public static String getVerboseSummary(WifiEntry wifiEntry) {
        String obj;
        StringJoiner stringJoiner = new StringJoiner(" ");
        synchronized (wifiEntry) {
            try {
                StringJoiner stringJoiner2 = new StringJoiner(" ");
                if (wifiEntry.getConnectedState() == 2 && wifiEntry.mWifiInfo != null) {
                    stringJoiner2.add("f = " + wifiEntry.mWifiInfo.getFrequency());
                    String bssid = wifiEntry.mWifiInfo.getBSSID();
                    if (bssid != null) {
                        stringJoiner2.add(bssid);
                    }
                    stringJoiner2.add("standard = " + wifiEntry.getStandardString());
                    stringJoiner2.add("rssi = " + wifiEntry.mWifiInfo.getRssi());
                    stringJoiner2.add("score = " + wifiEntry.mWifiInfo.getScore());
                    stringJoiner2.add(String.format(" tx=%.1f,", Double.valueOf(wifiEntry.mWifiInfo.getSuccessfulTxPacketsPerSecond())));
                    stringJoiner2.add(String.format("%.1f,", Double.valueOf(wifiEntry.mWifiInfo.getRetriedTxPacketsPerSecond())));
                    stringJoiner2.add(String.format("%.1f ", Double.valueOf(wifiEntry.mWifiInfo.getLostTxPacketsPerSecond())));
                    stringJoiner2.add(String.format("rx=%.1f", Double.valueOf(wifiEntry.mWifiInfo.getSuccessfulRxPacketsPerSecond())));
                    int i = BuildCompat.$r8$clinit;
                    if (wifiEntry.mWifiInfo.getApMldMacAddress() != null) {
                        stringJoiner2.add("mldMac = " + wifiEntry.mWifiInfo.getApMldMacAddress());
                        stringJoiner2.add("linkId = " + wifiEntry.mWifiInfo.getApMloLinkId());
                        stringJoiner2.add("affLinks = " + Arrays.toString(wifiEntry.mWifiInfo.getAffiliatedMloLinks().toArray()));
                    }
                }
                obj = stringJoiner2.toString();
            } catch (Throwable th) {
                throw th;
            }
        }
        if (!TextUtils.isEmpty(obj)) {
            stringJoiner.add(obj);
        }
        StringBuilder sb = new StringBuilder();
        if (wifiEntry.getConnectedState() == 2) {
            sb.append("hasInternet:");
            sb.append(wifiEntry.hasInternetAccess());
            sb.append(", isDefaultNetwork:");
            sb.append(wifiEntry.isDefaultNetwork());
            sb.append(", isLowQuality:");
            sb.append(wifiEntry.isLowQuality());
        }
        String sb2 = sb.toString();
        if (!TextUtils.isEmpty(sb2)) {
            stringJoiner.add(sb2);
        }
        String scanResultDescription = wifiEntry.getScanResultDescription();
        if (!TextUtils.isEmpty(scanResultDescription)) {
            stringJoiner.add(scanResultDescription);
        }
        String networkSelectionDescription = wifiEntry.getNetworkSelectionDescription();
        if (!TextUtils.isEmpty(networkSelectionDescription)) {
            stringJoiner.add(networkSelectionDescription);
        }
        return stringJoiner.toString();
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0046, code lost:
    
        if (((android.content.ComponentName) r3.second).getPackageName().equals(r5) != false) goto L36;
     */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0071 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x002c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean isDeviceOrProfileOwner(android.content.Context r4, java.lang.String r5, int r6) {
        /*
            r0 = 0
            if (r5 != 0) goto L4
            goto L49
        L4:
            java.lang.Class<android.app.admin.DevicePolicyManager> r1 = android.app.admin.DevicePolicyManager.class
            java.lang.Object r1 = r4.getSystemService(r1)
            android.app.admin.DevicePolicyManager r1 = (android.app.admin.DevicePolicyManager) r1
            if (r1 != 0) goto L10
        Le:
            r3 = r0
            goto L29
        L10:
            android.os.UserHandle r2 = r1.getDeviceOwnerUser()     // Catch: java.lang.Exception -> L73
            android.content.ComponentName r1 = r1.getDeviceOwnerComponentOnAnyUser()     // Catch: java.lang.Exception -> L73
            if (r2 == 0) goto Le
            if (r1 != 0) goto L1d
            goto Le
        L1d:
            java.lang.String r3 = r1.getPackageName()
            if (r3 != 0) goto L24
            goto Le
        L24:
            android.util.Pair r3 = new android.util.Pair
            r3.<init>(r2, r1)
        L29:
            if (r3 != 0) goto L2c
            goto L49
        L2c:
            java.lang.Object r1 = r3.first
            android.os.UserHandle r1 = (android.os.UserHandle) r1
            android.os.UserHandle r2 = android.os.UserHandle.getUserHandleForUid(r6)
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L49
            java.lang.Object r1 = r3.second
            android.content.ComponentName r1 = (android.content.ComponentName) r1
            java.lang.String r1 = r1.getPackageName()
            boolean r1 = r1.equals(r5)
            if (r1 == 0) goto L49
            goto L71
        L49:
            r1 = 0
            if (r5 != 0) goto L4e
        L4c:
            r4 = r1
            goto L6f
        L4e:
            java.lang.String r2 = r4.getPackageName()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L5b
            android.os.UserHandle r6 = android.os.UserHandle.getUserHandleForUid(r6)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L5b
            android.content.Context r4 = r4.createPackageContextAsUser(r2, r1, r6)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L5b
            goto L5c
        L5b:
            r4 = r0
        L5c:
            if (r4 != 0) goto L5f
            goto L68
        L5f:
            java.lang.Class<android.app.admin.DevicePolicyManager> r6 = android.app.admin.DevicePolicyManager.class
            java.lang.Object r4 = r4.getSystemService(r6)
            r0 = r4
            android.app.admin.DevicePolicyManager r0 = (android.app.admin.DevicePolicyManager) r0
        L68:
            if (r0 != 0) goto L6b
            goto L4c
        L6b:
            boolean r4 = r0.isProfileOwnerApp(r5)
        L6f:
            if (r4 == 0) goto L72
        L71:
            r1 = 1
        L72:
            return r1
        L73:
            r4 = move-exception
            java.lang.RuntimeException r5 = new java.lang.RuntimeException
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r0 = "getDeviceOwner error - "
            r6.<init>(r0)
            java.lang.String r4 = r4.toString()
            r6.append(r4)
            java.lang.String r4 = r6.toString()
            r5.<init>(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wifitrackerlib.Utils.isDeviceOrProfileOwner(android.content.Context, java.lang.String, int):boolean");
    }
}

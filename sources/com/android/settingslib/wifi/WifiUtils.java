package com.android.settingslib.wifi;

import android.R;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.os.SystemClock;
import android.util.ArraySet;
import com.android.systemui.qs.tiles.dialog.InternetAdapter$InternetViewHolder$$ExternalSyntheticLambda2;
import com.android.systemui.qs.tiles.dialog.InternetAdapter$InternetViewHolder$$ExternalSyntheticLambda3;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.StandaloneCoroutine;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class WifiUtils {
    public static final Companion Companion = new Companion();
    public static final int[] WIFI_PIE = {R.drawable.ic_volume, R.drawable.ic_volume_bluetooth_ad2p, R.drawable.ic_volume_bluetooth_in_call, R.drawable.ic_volume_off, R.drawable.ic_volume_off_small};
    public static final int[] NO_INTERNET_WIFI_PIE = {com.android.wm.shell.R.drawable.ic_no_internet_wifi_signal_0, com.android.wm.shell.R.drawable.ic_no_internet_wifi_signal_1, com.android.wm.shell.R.drawable.ic_no_internet_wifi_signal_2, com.android.wm.shell.R.drawable.ic_no_internet_wifi_signal_3, com.android.wm.shell.R.drawable.ic_no_internet_wifi_signal_4};

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class InternetIconInjector {
        public final Context context;

        public InternetIconInjector(Context context) {
            this.context = context;
        }
    }

    public static final StandaloneCoroutine checkWepAllowed(Context context, ContextScope contextScope, String str, InternetAdapter$InternetViewHolder$$ExternalSyntheticLambda2 internetAdapter$InternetViewHolder$$ExternalSyntheticLambda2, InternetAdapter$InternetViewHolder$$ExternalSyntheticLambda3 internetAdapter$InternetViewHolder$$ExternalSyntheticLambda3) {
        return BuildersKt.launch$default(contextScope, null, null, new WifiUtils$Companion$checkWepAllowed$2(context, internetAdapter$InternetViewHolder$$ExternalSyntheticLambda3, internetAdapter$InternetViewHolder$$ExternalSyntheticLambda2, 2009, str, null), 3);
    }

    public static final String getVisibilityStatus(AccessPoint accessPoint) {
        return Companion.getVisibilityStatus(accessPoint);
    }

    public static final String verboseScanResultSummary(AccessPoint accessPoint, ScanResult scanResult, String str, long j) {
        return Companion.verboseScanResultSummary(accessPoint, scanResult, str, j);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
        public final String getVisibilityStatus(AccessPoint accessPoint) {
            String str;
            int i;
            StringBuilder sb;
            StringBuilder sb2;
            Iterator it;
            int i2;
            int i3;
            int i4;
            int i5;
            StringBuilder sb3;
            int i6;
            int i7;
            StringBuilder sb4;
            int i8;
            StringBuilder sb5;
            StringBuilder sb6;
            int i9;
            int i10;
            Iterator it2;
            int i11;
            WifiInfo wifiInfo = accessPoint.mInfo;
            StringBuilder sb7 = new StringBuilder();
            StringBuilder sb8 = new StringBuilder();
            StringBuilder sb9 = new StringBuilder();
            StringBuilder sb10 = new StringBuilder();
            if (!accessPoint.isActive() || wifiInfo == null) {
                str = null;
            } else {
                str = wifiInfo.getBSSID();
                if (str != null) {
                    sb7.append(" ");
                    sb7.append(str);
                }
                sb7.append(" standard = ");
                sb7.append(wifiInfo.getWifiStandard());
                sb7.append(" rssi=");
                sb7.append(wifiInfo.getRssi());
                sb7.append("  score=");
                sb7.append(wifiInfo.getScore());
                if (accessPoint.mSpeed != 0) {
                    sb7.append(" speed=");
                    sb7.append(AccessPoint.getSpeedLabel(accessPoint.mSpeed, accessPoint.mContext));
                }
                sb7.append(String.format(" tx=%.1f,", Arrays.copyOf(new Object[]{Double.valueOf(wifiInfo.getSuccessfulTxPacketsPerSecond())}, 1)));
                sb7.append(String.format("%.1f,", Arrays.copyOf(new Object[]{Double.valueOf(wifiInfo.getRetriedTxPacketsPerSecond())}, 1)));
                sb7.append(String.format("%.1f ", Arrays.copyOf(new Object[]{Double.valueOf(wifiInfo.getLostTxPacketsPerSecond())}, 1)));
                sb7.append(String.format("rx=%.1f", Arrays.copyOf(new Object[]{Double.valueOf(wifiInfo.getSuccessfulRxPacketsPerSecond())}, 1)));
            }
            String str2 = str;
            long elapsedRealtime = SystemClock.elapsedRealtime();
            ArraySet arraySet = new ArraySet();
            synchronized (accessPoint.mLock) {
                arraySet.addAll((Collection) accessPoint.mScanResults);
                arraySet.addAll((Collection) accessPoint.mExtraScanResults);
            }
            Iterator it3 = arraySet.iterator();
            int i12 = -127;
            int i13 = 0;
            int i14 = 0;
            int i15 = 0;
            int i16 = -127;
            int i17 = -127;
            while (it3.hasNext()) {
                ScanResult scanResult = (ScanResult) it3.next();
                if (scanResult == null) {
                    i = i16;
                    sb = sb7;
                    sb2 = sb9;
                    it = it3;
                    i2 = i17;
                    i3 = i13;
                    i4 = i14;
                    StringBuilder sb11 = sb10;
                    i5 = i12;
                    sb3 = sb11;
                } else {
                    int i18 = scanResult.frequency;
                    int i19 = i12;
                    if (i18 < 4900 || i18 > 5900) {
                        sb = sb7;
                        StringBuilder sb12 = sb10;
                        i5 = i19;
                        i4 = i14;
                        it = it3;
                        i2 = i17;
                        if (i18 < 2400 || i18 > 2500) {
                            sb2 = sb9;
                            i3 = i13;
                            int i20 = i16;
                            if (i18 < 58320 || i18 > 70200) {
                                i = i20;
                                sb3 = sb12;
                            } else {
                                int i21 = i4 + 1;
                                int i22 = scanResult.level;
                                if (i22 > i5) {
                                    i5 = i22;
                                }
                                if (i21 <= 4) {
                                    i6 = i20;
                                    sb3 = sb12;
                                    sb3.append(verboseScanResultSummary(accessPoint, scanResult, str2, elapsedRealtime));
                                } else {
                                    i6 = i20;
                                    sb3 = sb12;
                                }
                                i14 = i21;
                                i16 = i6;
                                i13 = i3;
                                i17 = i2;
                                it3 = it;
                                sb7 = sb;
                                sb9 = sb2;
                                int i23 = i5;
                                sb10 = sb3;
                                i12 = i23;
                            }
                        } else {
                            i15++;
                            int i24 = scanResult.level;
                            if (i24 > i2) {
                                i2 = i24;
                            }
                            if (i15 <= 4) {
                                i7 = i16;
                                sb4 = sb9;
                                i8 = i13;
                                sb8.append(verboseScanResultSummary(accessPoint, scanResult, str2, elapsedRealtime));
                            } else {
                                i7 = i16;
                                sb4 = sb9;
                                i8 = i13;
                            }
                            i16 = i7;
                            i14 = i4;
                            i13 = i8;
                            i12 = i5;
                            i17 = i2;
                            it3 = it;
                            sb7 = sb;
                            sb10 = sb12;
                            sb9 = sb4;
                        }
                    } else {
                        int i25 = i13 + 1;
                        int i26 = scanResult.level;
                        int i27 = i26 > i16 ? i26 : i16;
                        if (i25 <= 4) {
                            it2 = it3;
                            i11 = i17;
                            sb5 = sb7;
                            sb6 = sb10;
                            i9 = i19;
                            i10 = i14;
                            sb9.append(verboseScanResultSummary(accessPoint, scanResult, str2, elapsedRealtime));
                        } else {
                            sb5 = sb7;
                            sb6 = sb10;
                            i9 = i19;
                            i10 = i14;
                            it2 = it3;
                            i11 = i17;
                        }
                        i13 = i25;
                        i14 = i10;
                        i12 = i9;
                        i17 = i11;
                        i16 = i27;
                        it3 = it2;
                        sb7 = sb5;
                        sb10 = sb6;
                    }
                }
                i16 = i;
                i14 = i4;
                i13 = i3;
                i17 = i2;
                it3 = it;
                sb7 = sb;
                sb9 = sb2;
                int i232 = i5;
                sb10 = sb3;
                i12 = i232;
            }
            int i28 = i16;
            int i29 = i17;
            StringBuilder sb13 = sb7;
            StringBuilder sb14 = sb9;
            int i30 = i13;
            int i31 = i14;
            StringBuilder sb15 = sb10;
            int i32 = i12;
            sb13.append(" [");
            if (i15 > 0) {
                sb13.append("(");
                sb13.append(i15);
                sb13.append(")");
                if (i15 > 4) {
                    sb13.append("max=");
                    sb13.append(i29);
                    sb13.append(",");
                }
                sb13.append(sb8.toString());
            }
            sb13.append(";");
            if (i30 > 0) {
                sb13.append("(");
                sb13.append(i30);
                sb13.append(")");
                if (i30 > 4) {
                    sb13.append("max=");
                    sb13.append(i28);
                    sb13.append(",");
                }
                sb13.append(sb14.toString());
            }
            sb13.append(";");
            if (i31 > 0) {
                sb13.append("(");
                sb13.append(i31);
                sb13.append(")");
                if (i31 > 4) {
                    sb13.append("max=");
                    sb13.append(i32);
                    sb13.append(",");
                }
                sb13.append(sb15.toString());
            }
            sb13.append("]");
            return sb13.toString();
        }

        public final String verboseScanResultSummary(AccessPoint accessPoint, ScanResult scanResult, String str, long j) {
            StringBuilder sb = new StringBuilder(" \n{");
            sb.append(scanResult.BSSID);
            if (Intrinsics.areEqual(scanResult.BSSID, str)) {
                sb.append("*");
            }
            sb.append("=");
            sb.append(scanResult.frequency);
            sb.append(",");
            sb.append(scanResult.level);
            TimestampedScoredNetwork timestampedScoredNetwork = (TimestampedScoredNetwork) accessPoint.mScoredNetworkCache.get(scanResult.BSSID);
            int calculateBadge = timestampedScoredNetwork == null ? 0 : timestampedScoredNetwork.mScore.calculateBadge(scanResult.level);
            if (calculateBadge != 0) {
                sb.append(",");
                sb.append(AccessPoint.getSpeedLabel(calculateBadge, accessPoint.mContext));
            }
            int i = ((int) (j - (scanResult.timestamp / 1000))) / 1000;
            sb.append(",");
            sb.append(i);
            sb.append("s}");
            return sb.toString();
        }

        public static /* synthetic */ void getACTION_WIFI_DIALOG$annotations() {
        }

        public static /* synthetic */ void getEXTRA_CHOSEN_WIFI_ENTRY_KEY$annotations() {
        }

        public static /* synthetic */ void getEXTRA_CONNECT_FOR_CALLER$annotations() {
        }
    }
}

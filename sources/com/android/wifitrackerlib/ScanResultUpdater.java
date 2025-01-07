package com.android.wifitrackerlib;

import android.net.wifi.ScanResult;
import android.util.ArrayMap;
import android.util.Pair;
import java.time.Clock;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ScanResultUpdater {
    public final Clock mClock;
    public final long mMaxScanAgeMillis;
    public final Map mScanResultsBySsidAndBssid = new ArrayMap();
    public final Object mLock = new Object();

    public ScanResultUpdater(Clock clock, long j) {
        this.mMaxScanAgeMillis = j;
        this.mClock = clock;
    }

    public final List getScanResults(long j) {
        ArrayList arrayList;
        if (j > this.mMaxScanAgeMillis) {
            throw new IllegalArgumentException("maxScanAgeMillis argument cannot be greater than mMaxScanAgeMillis!");
        }
        synchronized (this.mLock) {
            try {
                arrayList = new ArrayList();
                for (ScanResult scanResult : ((ArrayMap) this.mScanResultsBySsidAndBssid).values()) {
                    if (this.mClock.millis() - (scanResult.timestamp / 1000) <= j) {
                        arrayList.add(scanResult);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return arrayList;
    }

    public final void update(List list) {
        synchronized (this.mLock) {
            try {
                synchronized (this.mLock) {
                    ((ArrayMap) this.mScanResultsBySsidAndBssid).entrySet().removeIf(new Predicate() { // from class: com.android.wifitrackerlib.ScanResultUpdater$$ExternalSyntheticLambda0
                        @Override // java.util.function.Predicate
                        public final boolean test(Object obj) {
                            ScanResultUpdater scanResultUpdater = ScanResultUpdater.this;
                            return scanResultUpdater.mClock.millis() - (((ScanResult) ((Map.Entry) obj).getValue()).timestamp / 1000) > scanResultUpdater.mMaxScanAgeMillis;
                        }
                    });
                }
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    ScanResult scanResult = (ScanResult) it.next();
                    Pair pair = new Pair(scanResult.SSID, scanResult.BSSID);
                    ScanResult scanResult2 = (ScanResult) ((ArrayMap) this.mScanResultsBySsidAndBssid).get(pair);
                    if (scanResult2 != null && scanResult2.timestamp >= scanResult.timestamp) {
                    }
                    ((ArrayMap) this.mScanResultsBySsidAndBssid).put(pair, scanResult);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}

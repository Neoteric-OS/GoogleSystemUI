package com.android.settingslib.bluetooth;

import android.util.SparseIntArray;
import java.util.Objects;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class HearingAidInfo {
    public static final SparseIntArray ASHA_DEVICE_MODE_TO_INTERNAL_MODE_MAPPING;
    public static final SparseIntArray ASHA_DEVICE_SIDE_TO_INTERNAL_SIDE_MAPPING;
    public static final SparseIntArray HAP_DEVICE_TYPE_TO_INTERNAL_MODE_MAPPING;
    public final long mHiSyncId;
    public final int mMode;
    public final int mSide;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        ASHA_DEVICE_SIDE_TO_INTERNAL_SIDE_MAPPING = sparseIntArray;
        sparseIntArray.put(-1, -1);
        sparseIntArray.put(0, 0);
        sparseIntArray.put(1, 1);
        SparseIntArray sparseIntArray2 = new SparseIntArray();
        ASHA_DEVICE_MODE_TO_INTERNAL_MODE_MAPPING = sparseIntArray2;
        sparseIntArray2.put(-1, -1);
        sparseIntArray2.put(0, 0);
        sparseIntArray2.put(1, 1);
        SparseIntArray sparseIntArray3 = new SparseIntArray();
        HAP_DEVICE_TYPE_TO_INTERNAL_MODE_MAPPING = sparseIntArray3;
        sparseIntArray3.put(-1, -1);
        sparseIntArray3.put(0, 1);
        sparseIntArray3.put(1, 0);
        sparseIntArray3.put(2, 2);
        sparseIntArray3.put(3, -1);
    }

    public HearingAidInfo(int i, int i2, long j) {
        this.mSide = i;
        this.mMode = i2;
        this.mHiSyncId = j;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HearingAidInfo)) {
            return false;
        }
        HearingAidInfo hearingAidInfo = (HearingAidInfo) obj;
        return this.mSide == hearingAidInfo.mSide && this.mMode == hearingAidInfo.mMode && this.mHiSyncId == hearingAidInfo.mHiSyncId;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(this.mSide), Integer.valueOf(this.mMode), Long.valueOf(this.mHiSyncId));
    }

    public final String toString() {
        return "HearingAidInfo{mSide=" + this.mSide + ", mMode=" + this.mMode + ", mHiSyncId=" + this.mHiSyncId + '}';
    }
}

package androidx.appsearch.builtintypes;

import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class Timer extends Thing {
    public final long mBaseTimeMillis;
    public final long mBaseTimeMillisInElapsedRealtime;
    public final int mBootCount;
    public final long mDurationMillis;
    public final long mOriginalDurationMillis;
    public final long mRemainingDurationMillis;
    public final String mRingtone;
    public final boolean mShouldVibrate;
    public final long mStartTimeMillis;
    public final int mStatus;

    public Timer(String str, String str2, int i, long j, long j2, String str3, List list, String str4, String str5, String str6, List list2, long j3, long j4, long j5, long j6, long j7, int i2, long j8, String str7, int i3, boolean z) {
        super(str, str2, i, j, j2, str3, list, str4, str5, str6, list2);
        this.mDurationMillis = j3;
        this.mOriginalDurationMillis = j4;
        this.mStartTimeMillis = j5;
        this.mBaseTimeMillis = j6;
        this.mBaseTimeMillisInElapsedRealtime = j7;
        this.mBootCount = i2;
        this.mRemainingDurationMillis = j8;
        this.mRingtone = str7;
        this.mStatus = i3;
        this.mShouldVibrate = z;
    }
}

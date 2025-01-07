package androidx.appsearch.builtintypes;

import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class Stopwatch extends Thing {
    public final long mAccumulatedDurationMillis;
    public final long mBaseTimeMillis;
    public final long mBaseTimeMillisInElapsedRealtime;
    public final int mBootCount;
    public final List mLaps;
    public final int mStatus;

    public Stopwatch(String str, String str2, int i, long j, long j2, String str3, List list, String str4, String str5, String str6, List list2, long j3, long j4, int i2, int i3, long j5, List list3) {
        super(str, str2, i, j, j2, str3, list, str4, str5, str6, list2);
        this.mBaseTimeMillis = j3;
        this.mBaseTimeMillisInElapsedRealtime = j4;
        this.mBootCount = i2;
        this.mStatus = i3;
        this.mAccumulatedDurationMillis = j5;
        list3.getClass();
        this.mLaps = list3;
    }
}

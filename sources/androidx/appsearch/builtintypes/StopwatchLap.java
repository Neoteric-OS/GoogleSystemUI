package androidx.appsearch.builtintypes;

import androidx.appsearch.builtintypes.Thing;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class StopwatchLap extends Thing {
    public final long mAccumulatedLapDurationMillis;
    public final long mLapDurationMillis;
    public final int mLapNumber;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Builder extends Thing.BuilderImpl {
    }

    public StopwatchLap(String str, String str2, int i, long j, long j2, String str3, List list, String str4, String str5, String str6, List list2, int i2, long j3, long j4) {
        super(str, str2, i, j, j2, str3, list, str4, str5, str6, list2);
        this.mLapNumber = i2;
        this.mLapDurationMillis = j3;
        this.mAccumulatedLapDurationMillis = j4;
    }
}

package androidx.appsearch.builtintypes;

import androidx.appsearch.builtintypes.Thing;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class Alarm extends Thing {
    public final String mBlackoutPeriodEndDate;
    public final String mBlackoutPeriodStartDate;
    public final int mComputingDevice;
    public final int[] mDaysOfWeek;
    public final boolean mEnabled;
    public final int mHour;
    public final int mMinute;
    public final AlarmInstance mNextInstance;
    public final AlarmInstance mPreviousInstance;
    public final String mRingtone;
    public final boolean mShouldVibrate;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Builder extends Thing.BuilderImpl {
        public String mBlackoutPeriodStartDate;
        public int[] mDaysOfWeek;
        public boolean mEnabled;
        public int mHour;
        public int mMinute;
    }

    public Alarm(String str, String str2, int i, long j, long j2, String str3, List list, String str4, String str5, String str6, List list2, boolean z, int[] iArr, int i2, int i3, String str7, String str8, String str9, boolean z2, AlarmInstance alarmInstance, AlarmInstance alarmInstance2, int i4) {
        super(str, str2, i, j, j2, str3, list, str4, str5, str6, list2);
        this.mEnabled = z;
        this.mDaysOfWeek = iArr;
        this.mHour = i2;
        this.mMinute = i3;
        this.mBlackoutPeriodStartDate = str7;
        this.mBlackoutPeriodEndDate = str8;
        this.mRingtone = str9;
        this.mShouldVibrate = z2;
        this.mPreviousInstance = alarmInstance;
        this.mNextInstance = alarmInstance2;
        this.mComputingDevice = i4;
    }
}

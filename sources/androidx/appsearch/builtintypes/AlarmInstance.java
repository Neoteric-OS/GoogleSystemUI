package androidx.appsearch.builtintypes;

import androidx.appsearch.builtintypes.Thing;
import androidx.appsearch.utils.DateTimeFormatValidator;
import androidx.core.util.Preconditions;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class AlarmInstance extends Thing {
    public final String mScheduledTime;
    public final long mSnoozeDurationMillis;
    public final int mStatus;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Builder extends Thing.BuilderImpl {
        public final String mScheduledTime;

        public Builder(String str, String str2, String str3) {
            super(str, str2);
            str3.getClass();
            Preconditions.checkArgument("scheduledTime must be in the format: yyyy-MM-ddTHH:mm:ss", DateTimeFormatValidator.validateDateFormat("yyyy-MM-dd'T'HH:mm", str3) || DateTimeFormatValidator.validateDateFormat("yyyy-MM-dd'T'HH:mm:ss", str3));
            this.mScheduledTime = str3;
        }
    }

    public AlarmInstance(String str, String str2, int i, long j, long j2, String str3, List list, String str4, String str5, String str6, List list2, String str7, int i2, long j3) {
        super(str, str2, i, j, j2, str3, list, str4, str5, str6, list2);
        str7.getClass();
        this.mScheduledTime = str7;
        this.mStatus = i2;
        this.mSnoozeDurationMillis = j3;
    }
}

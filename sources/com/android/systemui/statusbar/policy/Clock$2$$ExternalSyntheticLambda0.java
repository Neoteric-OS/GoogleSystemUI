package com.android.systemui.statusbar.policy;

import com.android.systemui.statusbar.policy.Clock;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class Clock$2$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId = 0;
    public final /* synthetic */ Clock.AnonymousClass2 f$0;
    public final /* synthetic */ Object f$1;

    public /* synthetic */ Clock$2$$ExternalSyntheticLambda0(Clock.AnonymousClass2 anonymousClass2, String str) {
        this.f$0 = anonymousClass2;
        this.f$1 = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                Clock.AnonymousClass2 anonymousClass2 = this.f$0;
                String str = (String) this.f$1;
                anonymousClass2.this$0.mCalendar = Calendar.getInstance(TimeZone.getTimeZone(str));
                Clock clock = anonymousClass2.this$0;
                SimpleDateFormat simpleDateFormat = clock.mClockFormat;
                if (simpleDateFormat != null) {
                    simpleDateFormat.setTimeZone(clock.mCalendar.getTimeZone());
                    break;
                }
                break;
            default:
                Clock.AnonymousClass2 anonymousClass22 = this.f$0;
                Locale locale = (Locale) this.f$1;
                if (!locale.equals(anonymousClass22.this$0.mLocale)) {
                    Clock clock2 = anonymousClass22.this$0;
                    clock2.mLocale = locale;
                    clock2.mContentDescriptionFormatString = "";
                    clock2.mDateTimePatternGenerator = null;
                    break;
                }
                break;
        }
    }

    public /* synthetic */ Clock$2$$ExternalSyntheticLambda0(Clock.AnonymousClass2 anonymousClass2, Locale locale) {
        this.f$0 = anonymousClass2;
        this.f$1 = locale;
    }
}

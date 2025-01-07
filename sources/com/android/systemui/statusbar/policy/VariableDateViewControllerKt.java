package com.android.systemui.statusbar.policy;

import android.icu.text.DateFormat;
import android.icu.text.DisplayContext;
import android.icu.util.Calendar;
import android.text.TextUtils;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Date;
import java.util.Locale;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class VariableDateViewControllerKt {
    public static final VariableDateViewControllerKt$EMPTY_FORMAT$1 EMPTY_FORMAT = new DateFormat() { // from class: com.android.systemui.statusbar.policy.VariableDateViewControllerKt$EMPTY_FORMAT$1
        @Override // android.icu.text.DateFormat
        public final StringBuffer format(Calendar calendar, StringBuffer stringBuffer, FieldPosition fieldPosition) {
            return null;
        }

        @Override // android.icu.text.DateFormat
        public final void parse(String str, Calendar calendar, ParsePosition parsePosition) {
        }
    };

    public static final DateFormat getFormatFromPattern(String str) {
        if (TextUtils.equals(str, "")) {
            return EMPTY_FORMAT;
        }
        DateFormat instanceForSkeleton = DateFormat.getInstanceForSkeleton(str, Locale.getDefault());
        instanceForSkeleton.setContext(DisplayContext.CAPITALIZATION_FOR_BEGINNING_OF_SENTENCE);
        return instanceForSkeleton;
    }

    public static final String getTextForFormat(Date date, DateFormat dateFormat) {
        return dateFormat == EMPTY_FORMAT ? "" : dateFormat.format(date);
    }
}

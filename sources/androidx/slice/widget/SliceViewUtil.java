package androidx.slice.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.format.DateUtils;
import java.util.Calendar;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class SliceViewUtil {
    public static int getColorAttr(int i, Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(new int[]{i});
        int color = obtainStyledAttributes.getColor(0, 0);
        obtainStyledAttributes.recycle();
        return color;
    }

    public static Drawable getDrawable(int i, Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(new int[]{i});
        Drawable drawable = obtainStyledAttributes.getDrawable(0);
        obtainStyledAttributes.recycle();
        return drawable;
    }

    public static CharSequence getTimestampString(Context context, long j) {
        return (j < System.currentTimeMillis() || DateUtils.isToday(j)) ? DateUtils.getRelativeTimeSpanString(j, Calendar.getInstance().getTimeInMillis(), 60000L, 262144) : DateUtils.formatDateTime(context, j, 8);
    }
}

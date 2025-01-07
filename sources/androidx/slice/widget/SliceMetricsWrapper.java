package androidx.slice.widget;

import android.app.slice.SliceMetrics;
import android.content.Context;
import android.net.Uri;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SliceMetricsWrapper {
    public final SliceMetrics mSliceMetrics;

    public SliceMetricsWrapper(Context context, Uri uri) {
        this.mSliceMetrics = new SliceMetrics(context, uri);
    }
}

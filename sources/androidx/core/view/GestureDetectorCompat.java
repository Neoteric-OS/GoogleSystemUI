package androidx.core.view;

import android.content.Context;
import android.view.GestureDetector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class GestureDetectorCompat {
    public final GestureDetector mDetector;

    public GestureDetectorCompat(Context context, GestureDetector.OnGestureListener onGestureListener) {
        this.mDetector = new GestureDetector(context, onGestureListener, null);
    }
}

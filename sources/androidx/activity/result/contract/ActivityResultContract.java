package androidx.activity.result.contract;

import android.content.Context;
import android.content.Intent;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ActivityResultContract {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SynchronousResult {
        public final Object value;

        public SynchronousResult(Object obj) {
            this.value = obj;
        }
    }

    public abstract Intent createIntent(Object obj);

    public SynchronousResult getSynchronousResult(Context context, Object obj) {
        return null;
    }

    public abstract Object parseResult(Intent intent, int i);
}

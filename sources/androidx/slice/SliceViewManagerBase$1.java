package androidx.slice;

import android.os.Handler;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SliceViewManagerBase$1 implements Executor {
    public final /* synthetic */ Handler val$h;

    public SliceViewManagerBase$1(Handler handler) {
        this.val$h = handler;
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        this.val$h.post(runnable);
    }
}

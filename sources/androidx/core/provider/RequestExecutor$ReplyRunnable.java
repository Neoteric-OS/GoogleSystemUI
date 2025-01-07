package androidx.core.provider;

import android.os.Handler;
import androidx.core.provider.FontRequestWorker;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class RequestExecutor$ReplyRunnable implements Runnable {
    public FontRequestWorker.AnonymousClass1 mCallable;
    public FontRequestWorker.AnonymousClass2 mConsumer;
    public Handler mHandler;

    @Override // java.lang.Runnable
    public final void run() {
        final Object obj;
        try {
            obj = this.mCallable.call();
        } catch (Exception unused) {
            obj = null;
        }
        final FontRequestWorker.AnonymousClass2 anonymousClass2 = this.mConsumer;
        this.mHandler.post(new Runnable() { // from class: androidx.core.provider.RequestExecutor$ReplyRunnable.1
            @Override // java.lang.Runnable
            public final void run() {
                FontRequestWorker.AnonymousClass2.this.accept(obj);
            }
        });
    }
}

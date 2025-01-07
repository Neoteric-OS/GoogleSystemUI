package androidx.appsearch.platformstorage;

import android.app.appsearch.GlobalSearchSession;
import android.app.appsearch.exceptions.AppSearchException;
import android.app.appsearch.observer.ObserverCallback;
import androidx.collection.ArrayMap;
import com.google.android.systemui.smartspace.NextClockAlarmController$observerCallback$1;
import java.io.Closeable;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class GlobalSearchSessionImpl implements Closeable {
    public final Executor mExecutor;
    public final ArrayMap mObserverCallbacksLocked = new ArrayMap(0);
    public final GlobalSearchSession mPlatformSession;

    public GlobalSearchSessionImpl(GlobalSearchSession globalSearchSession, Executor executor, FeaturesImpl featuresImpl) {
        globalSearchSession.getClass();
        this.mPlatformSession = globalSearchSession;
        executor.getClass();
        this.mExecutor = executor;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.mPlatformSession.close();
    }

    public final void unregisterObserverCallback(NextClockAlarmController$observerCallback$1 nextClockAlarmController$observerCallback$1) {
        nextClockAlarmController$observerCallback$1.getClass();
        synchronized (this.mObserverCallbacksLocked) {
            ObserverCallback observerCallback = (ObserverCallback) this.mObserverCallbacksLocked.get(nextClockAlarmController$observerCallback$1);
            if (observerCallback == null) {
                return;
            }
            try {
                this.mPlatformSession.unregisterObserverCallback("com.google.android.deskclock", observerCallback);
                this.mObserverCallbacksLocked.remove(nextClockAlarmController$observerCallback$1);
            } catch (AppSearchException e) {
                throw new androidx.appsearch.exceptions.AppSearchException(e.getResultCode(), e.getMessage(), e.getCause());
            }
        }
    }
}

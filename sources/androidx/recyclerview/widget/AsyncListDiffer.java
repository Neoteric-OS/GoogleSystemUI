package androidx.recyclerview.widget;

import android.os.Handler;
import android.os.Looper;
import androidx.recyclerview.widget.AsyncDifferConfig;
import com.android.systemui.bluetooth.qsdialog.BluetoothTileDialogDelegate;
import com.android.systemui.bluetooth.qsdialog.BluetoothTileDialogDelegate$Adapter$diffUtilCallback$1;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AsyncListDiffer {
    public static final MainThreadExecutor sMainThreadExecutor = new MainThreadExecutor();
    public final AsyncDifferConfig mConfig;
    public List mList;
    public final List mListeners;
    public final MainThreadExecutor mMainThreadExecutor;
    public int mMaxScheduledGeneration;
    public List mReadOnlyList;
    public final AdapterListUpdateCallback mUpdateCallback;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MainThreadExecutor implements Executor {
        public final Handler mHandler = new Handler(Looper.getMainLooper());

        @Override // java.util.concurrent.Executor
        public final void execute(Runnable runnable) {
            this.mHandler.post(runnable);
        }
    }

    public AsyncListDiffer(BluetoothTileDialogDelegate.Adapter adapter, BluetoothTileDialogDelegate$Adapter$diffUtilCallback$1 bluetoothTileDialogDelegate$Adapter$diffUtilCallback$1) {
        AdapterListUpdateCallback adapterListUpdateCallback = new AdapterListUpdateCallback(adapter);
        synchronized (AsyncDifferConfig.Builder.sExecutorLock) {
            try {
                if (AsyncDifferConfig.Builder.sDiffExecutor == null) {
                    AsyncDifferConfig.Builder.sDiffExecutor = Executors.newFixedThreadPool(2);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        AsyncDifferConfig asyncDifferConfig = new AsyncDifferConfig(AsyncDifferConfig.Builder.sDiffExecutor, bluetoothTileDialogDelegate$Adapter$diffUtilCallback$1);
        this.mListeners = new CopyOnWriteArrayList();
        this.mReadOnlyList = Collections.emptyList();
        this.mUpdateCallback = adapterListUpdateCallback;
        this.mConfig = asyncDifferConfig;
        this.mMainThreadExecutor = sMainThreadExecutor;
    }
}

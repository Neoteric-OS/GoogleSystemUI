package androidx.recyclerview.widget;

import com.android.systemui.bluetooth.qsdialog.BluetoothTileDialogDelegate$Adapter$diffUtilCallback$1;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AsyncDifferConfig {
    public final Executor mBackgroundThreadExecutor;
    public final BluetoothTileDialogDelegate$Adapter$diffUtilCallback$1 mDiffCallback;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Builder {
        public static Executor sDiffExecutor;
        public static final Object sExecutorLock = new Object();
    }

    public AsyncDifferConfig(Executor executor, BluetoothTileDialogDelegate$Adapter$diffUtilCallback$1 bluetoothTileDialogDelegate$Adapter$diffUtilCallback$1) {
        this.mBackgroundThreadExecutor = executor;
        this.mDiffCallback = bluetoothTileDialogDelegate$Adapter$diffUtilCallback$1;
    }
}

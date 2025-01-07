package androidx.compose.ui.platform;

import android.view.Choreographer;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AndroidUiDispatcher$dispatchCallback$1 implements Choreographer.FrameCallback, Runnable {
    public final /* synthetic */ AndroidUiDispatcher this$0;

    public AndroidUiDispatcher$dispatchCallback$1(AndroidUiDispatcher androidUiDispatcher) {
        this.this$0 = androidUiDispatcher;
    }

    @Override // android.view.Choreographer.FrameCallback
    public final void doFrame(long j) {
        this.this$0.handler.removeCallbacks(this);
        AndroidUiDispatcher.access$performTrampolineDispatch(this.this$0);
        AndroidUiDispatcher androidUiDispatcher = this.this$0;
        synchronized (androidUiDispatcher.lock) {
            if (androidUiDispatcher.scheduledFrameDispatch) {
                androidUiDispatcher.scheduledFrameDispatch = false;
                List list = androidUiDispatcher.toRunOnFrame;
                androidUiDispatcher.toRunOnFrame = androidUiDispatcher.spareToRunOnFrame;
                androidUiDispatcher.spareToRunOnFrame = list;
                ArrayList arrayList = (ArrayList) list;
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    ((Choreographer.FrameCallback) arrayList.get(i)).doFrame(j);
                }
                arrayList.clear();
            }
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        AndroidUiDispatcher.access$performTrampolineDispatch(this.this$0);
        AndroidUiDispatcher androidUiDispatcher = this.this$0;
        synchronized (androidUiDispatcher.lock) {
            if (androidUiDispatcher.toRunOnFrame.isEmpty()) {
                androidUiDispatcher.choreographer.removeFrameCallback(this);
                androidUiDispatcher.scheduledFrameDispatch = false;
            }
        }
    }
}

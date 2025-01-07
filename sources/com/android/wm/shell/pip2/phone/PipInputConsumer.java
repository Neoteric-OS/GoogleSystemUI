package com.android.wm.shell.pip2.phone;

import android.os.Binder;
import android.os.IBinder;
import android.os.Looper;
import android.view.BatchedInputEventReceiver;
import android.view.Choreographer;
import android.view.IWindowManager;
import android.view.InputChannel;
import android.view.InputEvent;
import com.android.wm.shell.common.ShellExecutor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PipInputConsumer {
    public InputEventReceiver mInputEventReceiver;
    public PipTouchHandler$$ExternalSyntheticLambda10 mListener;
    public final ShellExecutor mMainExecutor;
    public PipTouchHandler$$ExternalSyntheticLambda10 mRegistrationListener;
    public final IWindowManager mWindowManager;
    public final IBinder mToken = new Binder();
    public final String mName = "pip_input_consumer";

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class InputEventReceiver extends BatchedInputEventReceiver {
        public InputEventReceiver(InputChannel inputChannel, Looper looper, Choreographer choreographer) {
            super(inputChannel, looper, choreographer);
        }

        public final void onInputEvent(InputEvent inputEvent) {
            try {
                PipTouchHandler$$ExternalSyntheticLambda10 pipTouchHandler$$ExternalSyntheticLambda10 = PipInputConsumer.this.mListener;
                if (pipTouchHandler$$ExternalSyntheticLambda10 != null) {
                    pipTouchHandler$$ExternalSyntheticLambda10.onInputEvent(inputEvent);
                }
            } finally {
                finishInputEvent(inputEvent, true);
            }
        }
    }

    public PipInputConsumer(IWindowManager iWindowManager, ShellExecutor shellExecutor) {
        this.mWindowManager = iWindowManager;
        this.mMainExecutor = shellExecutor;
    }
}

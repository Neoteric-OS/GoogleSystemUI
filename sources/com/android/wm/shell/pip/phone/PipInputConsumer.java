package com.android.wm.shell.pip.phone;

import android.os.Binder;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.view.BatchedInputEventReceiver;
import android.view.Choreographer;
import android.view.IWindowManager;
import android.view.InputChannel;
import android.view.InputEvent;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.pip.phone.PipInputConsumer.InputEventReceiver;
import com.android.wm.shell.protolog.ShellProtoLogGroup;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PipInputConsumer {
    public InputEventReceiver mInputEventReceiver;
    public PipController$$ExternalSyntheticLambda10 mListener;
    public final ShellExecutor mMainExecutor;
    public PipController$$ExternalSyntheticLambda10 mRegistrationListener;
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
                PipController$$ExternalSyntheticLambda10 pipController$$ExternalSyntheticLambda10 = PipInputConsumer.this.mListener;
                if (pipController$$ExternalSyntheticLambda10 != null) {
                    pipController$$ExternalSyntheticLambda10.onInputEvent(inputEvent);
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

    public final void registerInputConsumer() {
        if (this.mInputEventReceiver != null) {
            return;
        }
        final InputChannel inputChannel = new InputChannel();
        try {
            this.mWindowManager.destroyInputConsumer(this.mToken, 0);
            this.mWindowManager.createInputConsumer(this.mToken, this.mName, 0, inputChannel);
        } catch (RemoteException e) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[4]) {
                ProtoLogImpl_411527699.e(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, -678909581970427432L, 0, "PipInputConsumer", String.valueOf(e));
            }
        }
        ((HandlerExecutor) this.mMainExecutor).execute(new Runnable() { // from class: com.android.wm.shell.pip.phone.PipInputConsumer$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                PipInputConsumer pipInputConsumer = PipInputConsumer.this;
                InputChannel inputChannel2 = inputChannel;
                pipInputConsumer.getClass();
                pipInputConsumer.mInputEventReceiver = pipInputConsumer.new InputEventReceiver(inputChannel2, Looper.myLooper(), Choreographer.getInstance());
                PipController$$ExternalSyntheticLambda10 pipController$$ExternalSyntheticLambda10 = pipInputConsumer.mRegistrationListener;
                if (pipController$$ExternalSyntheticLambda10 != null) {
                    pipController$$ExternalSyntheticLambda10.f$0.onRegistrationChanged(true);
                }
            }
        });
    }
}

package com.android.systemui.shared.system;

import android.os.Looper;
import android.os.Trace;
import android.util.Log;
import android.view.BatchedInputEventReceiver;
import android.view.Choreographer;
import android.view.InputChannel;
import android.view.InputEvent;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class InputChannelCompat$InputEventReceiver {
    public final String mName;
    public final AnonymousClass1 mReceiver;

    /* JADX WARN: Type inference failed for: r1v1, types: [com.android.systemui.shared.system.InputChannelCompat$InputEventReceiver$1] */
    public InputChannelCompat$InputEventReceiver(String str, InputChannel inputChannel, Looper looper, Choreographer choreographer, final InputChannelCompat$InputEventListener inputChannelCompat$InputEventListener) {
        this.mName = str;
        this.mReceiver = new BatchedInputEventReceiver(inputChannel, looper, choreographer) { // from class: com.android.systemui.shared.system.InputChannelCompat$InputEventReceiver.1
            public final void onInputEvent(InputEvent inputEvent) {
                inputChannelCompat$InputEventListener.onInputEvent(inputEvent);
                finishInputEvent(inputEvent, true);
            }
        };
    }

    public final void dispose() {
        dispose();
        StringBuilder sb = new StringBuilder("InputMonitorCompat-");
        String str = this.mName;
        sb.append(str);
        sb.append(" receiver disposed");
        Trace.instant(4L, sb.toString());
        Log.d("InputMonitorCompat", "Input event receiver for monitor (" + str + ") disposed");
    }
}

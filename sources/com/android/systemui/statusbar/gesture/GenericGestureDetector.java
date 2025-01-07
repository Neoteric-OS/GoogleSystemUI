package com.android.systemui.statusbar.gesture;

import android.os.Looper;
import android.view.Choreographer;
import android.view.InputEvent;
import android.view.MotionEvent;
import com.android.systemui.shared.system.InputChannelCompat$InputEventListener;
import com.android.systemui.shared.system.InputChannelCompat$InputEventReceiver;
import com.android.systemui.shared.system.InputMonitorCompat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class GenericGestureDetector {
    public final Map callbacks = new LinkedHashMap();
    public InputMonitorCompat inputMonitor;
    public InputChannelCompat$InputEventReceiver inputReceiver;
    public final String tag;

    public GenericGestureDetector(String str) {
        this.tag = str;
    }

    public final void addOnGestureDetectedCallback(String str, Function1 function1) {
        synchronized (this.callbacks) {
            boolean isEmpty = this.callbacks.isEmpty();
            this.callbacks.put(str, function1);
            if (isEmpty) {
                startGestureListening$frameworks__base__packages__SystemUI__android_common__SystemUI_core();
            }
        }
    }

    public final void onGestureDetected$frameworks__base__packages__SystemUI__android_common__SystemUI_core(MotionEvent motionEvent) {
        ArrayList arrayList;
        synchronized (this.callbacks) {
            arrayList = new ArrayList(this.callbacks.values());
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((Function1) it.next()).invoke(motionEvent);
        }
    }

    public abstract void onInputEvent(InputEvent inputEvent);

    public final void removeOnGestureDetectedCallback(String str) {
        synchronized (this.callbacks) {
            this.callbacks.remove(str);
            if (this.callbacks.isEmpty()) {
                stopGestureListening$frameworks__base__packages__SystemUI__android_common__SystemUI_core();
            }
        }
    }

    public void startGestureListening$frameworks__base__packages__SystemUI__android_common__SystemUI_core() {
        stopGestureListening$frameworks__base__packages__SystemUI__android_common__SystemUI_core();
        InputMonitorCompat inputMonitorCompat = new InputMonitorCompat(this.tag, 0);
        this.inputReceiver = inputMonitorCompat.getInputReceiver(Looper.getMainLooper(), Choreographer.getInstance(), new InputChannelCompat$InputEventListener() { // from class: com.android.systemui.statusbar.gesture.GenericGestureDetector$startGestureListening$1$1
            @Override // com.android.systemui.shared.system.InputChannelCompat$InputEventListener
            public final void onInputEvent(InputEvent inputEvent) {
                GenericGestureDetector.this.onInputEvent(inputEvent);
            }
        });
        this.inputMonitor = inputMonitorCompat;
    }

    public void stopGestureListening$frameworks__base__packages__SystemUI__android_common__SystemUI_core() {
        InputMonitorCompat inputMonitorCompat = this.inputMonitor;
        if (inputMonitorCompat != null) {
            this.inputMonitor = null;
            inputMonitorCompat.dispose();
        }
        InputChannelCompat$InputEventReceiver inputChannelCompat$InputEventReceiver = this.inputReceiver;
        if (inputChannelCompat$InputEventReceiver != null) {
            this.inputReceiver = null;
            inputChannelCompat$InputEventReceiver.dispose();
        }
    }
}

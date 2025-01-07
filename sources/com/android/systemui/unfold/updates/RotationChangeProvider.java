package com.android.systemui.unfold.updates;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.os.Trace;
import android.view.Display;
import com.android.systemui.unfold.util.CallbackController;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class RotationChangeProvider implements CallbackController {
    public final Handler bgHandler;
    public final Handler callbackHandler;
    public final Context context;
    public final DisplayManager displayManager;
    public final CopyOnWriteArrayList listeners = new CopyOnWriteArrayList();
    public final RotationDisplayListener displayListener = new RotationDisplayListener();
    public final AtomicInteger lastRotation = new AtomicInteger(-1);

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface RotationListener {
        void onRotationChanged(int i);
    }

    public RotationChangeProvider(DisplayManager displayManager, Context context, Handler handler, Handler handler2) {
        this.displayManager = displayManager;
        this.context = context;
        this.bgHandler = handler;
        this.callbackHandler = handler2;
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class RotationDisplayListener implements DisplayManager.DisplayListener {
        public RotationDisplayListener() {
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public final void onDisplayChanged(int i) {
            Trace.beginSection("RotationChangeProvider.RotationDisplayListener#onDisplayChanged");
            try {
                Display display = RotationChangeProvider.this.context.getDisplay();
                if (display == null) {
                    return;
                }
                if (i == display.getDisplayId()) {
                    int rotation = display.getRotation();
                    if (RotationChangeProvider.this.lastRotation.getAndSet(rotation) != rotation) {
                        Iterator it = RotationChangeProvider.this.listeners.iterator();
                        while (it.hasNext()) {
                            ((RotationListener) it.next()).onRotationChanged(rotation);
                        }
                    }
                }
            } finally {
                Trace.endSection();
            }
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public final void onDisplayAdded(int i) {
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public final void onDisplayRemoved(int i) {
        }
    }
}

package com.google.android.systemui.elmyra.gates;

import com.google.android.systemui.elmyra.gates.Gate;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class Gate {
    public boolean mActive = false;
    public Listener mListener;
    public final Executor mNotifyExecutor;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Listener {
        void onGateChanged(Gate gate);
    }

    public Gate(Executor executor) {
        this.mNotifyExecutor = executor;
    }

    public final void activate() {
        if (this.mActive) {
            return;
        }
        this.mActive = true;
        onActivate();
    }

    public final void deactivate() {
        if (this.mActive) {
            this.mActive = false;
            onDeactivate();
        }
    }

    public abstract boolean isBlocked();

    public final boolean isBlocking() {
        return this.mActive && isBlocked();
    }

    public final void notifyListener() {
        if (!this.mActive || this.mListener == null) {
            return;
        }
        this.mNotifyExecutor.execute(new Runnable() { // from class: com.google.android.systemui.elmyra.gates.Gate$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                Gate gate = Gate.this;
                Gate.Listener listener = gate.mListener;
                if (listener != null) {
                    listener.onGateChanged(gate);
                }
            }
        });
    }

    public abstract void onActivate();

    public abstract void onDeactivate();

    public String toString() {
        return getClass().getSimpleName();
    }
}

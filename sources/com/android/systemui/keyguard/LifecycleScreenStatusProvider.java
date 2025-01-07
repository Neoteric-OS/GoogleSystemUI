package com.android.systemui.keyguard;

import android.os.Trace;
import com.android.app.tracing.TraceUtilsKt;
import com.android.systemui.keyguard.ScreenLifecycle;
import com.android.systemui.unfold.updates.DeviceFoldStateProvider;
import com.android.systemui.unfold.util.CallbackController;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LifecycleScreenStatusProvider implements CallbackController, ScreenLifecycle.Observer {
    public final List listeners;

    public LifecycleScreenStatusProvider(ScreenLifecycle screenLifecycle) {
        screenLifecycle.mObservers.add(this);
        this.listeners = new CopyOnWriteArrayList();
    }

    @Override // com.android.systemui.unfold.util.CallbackController
    public final void addCallback(Object obj) {
        ((CopyOnWriteArrayList) this.listeners).add((DeviceFoldStateProvider.ScreenStatusListener) obj);
    }

    @Override // com.android.systemui.keyguard.ScreenLifecycle.Observer
    public final void onScreenTurnedOn() {
        boolean isEnabled = Trace.isEnabled();
        if (isEnabled) {
            TraceUtilsKt.beginSlice("LifecycleScreenStatusProvider#onScreenTurnedOn");
        }
        try {
            Iterator it = ((CopyOnWriteArrayList) this.listeners).iterator();
            while (it.hasNext()) {
                ((DeviceFoldStateProvider.ScreenStatusListener) it.next()).onScreenTurnedOn();
            }
        } finally {
            if (isEnabled) {
                TraceUtilsKt.endSlice();
            }
        }
    }

    @Override // com.android.systemui.keyguard.ScreenLifecycle.Observer
    public final void onScreenTurningOff() {
        boolean isEnabled = Trace.isEnabled();
        if (isEnabled) {
            TraceUtilsKt.beginSlice("LifecycleScreenStatusProvider#onScreenTurningOff");
        }
        try {
            Iterator it = ((CopyOnWriteArrayList) this.listeners).iterator();
            while (it.hasNext()) {
                ((DeviceFoldStateProvider.ScreenStatusListener) it.next()).onScreenTurningOff();
            }
        } finally {
            if (isEnabled) {
                TraceUtilsKt.endSlice();
            }
        }
    }

    @Override // com.android.systemui.keyguard.ScreenLifecycle.Observer
    public final void onScreenTurningOn() {
        boolean isEnabled = Trace.isEnabled();
        if (isEnabled) {
            TraceUtilsKt.beginSlice("LifecycleScreenStatusProvider#onScreenTurningOn");
        }
        try {
            Iterator it = ((CopyOnWriteArrayList) this.listeners).iterator();
            while (it.hasNext()) {
                ((DeviceFoldStateProvider.ScreenStatusListener) it.next()).onScreenTurningOn();
            }
        } finally {
            if (isEnabled) {
                TraceUtilsKt.endSlice();
            }
        }
    }
}

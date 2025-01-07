package com.google.android.systemui.columbus.legacy.gates;

import android.view.KeyEvent;
import com.android.systemui.statusbar.CommandQueue;
import dagger.Lazy;
import java.util.Set;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SystemKeyPress extends TransientGate {
    public final Set blockingKeys;
    public final Lazy commandQueue;
    public final long gateDuration = 500;
    public final SystemKeyPress$commandQueueCallbacks$1 commandQueueCallbacks = new CommandQueue.Callbacks() { // from class: com.google.android.systemui.columbus.legacy.gates.SystemKeyPress$commandQueueCallbacks$1
        @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
        public final void handleSystemKey(KeyEvent keyEvent) {
            SystemKeyPress systemKeyPress = SystemKeyPress.this;
            if (systemKeyPress.blockingKeys.contains(Integer.valueOf(keyEvent.getKeyCode()))) {
                systemKeyPress.blockForMillis(systemKeyPress.gateDuration);
            }
        }
    };

    /* JADX WARN: Type inference failed for: r3v1, types: [com.google.android.systemui.columbus.legacy.gates.SystemKeyPress$commandQueueCallbacks$1] */
    public SystemKeyPress(Lazy lazy, Set set) {
        this.commandQueue = lazy;
        this.blockingKeys = set;
    }

    @Override // com.google.android.systemui.columbus.legacy.gates.Gate
    public final void onActivate() {
        ((CommandQueue) this.commandQueue.get()).addCallback((CommandQueue.Callbacks) this.commandQueueCallbacks);
    }

    @Override // com.google.android.systemui.columbus.legacy.gates.Gate
    public final void onDeactivate() {
        ((CommandQueue) this.commandQueue.get()).removeCallback((CommandQueue.Callbacks) this.commandQueueCallbacks);
    }
}

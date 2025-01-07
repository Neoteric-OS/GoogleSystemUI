package com.google.android.systemui.columbus.legacy.gates;

import com.google.android.systemui.columbus.legacy.gates.Gate;
import kotlinx.coroutines.BuildersKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class KeyguardProximity extends Gate {
    public boolean isListening;
    public final KeyguardVisibility keyguardGate;
    public final KeyguardProximity$keyguardListener$1 keyguardListener;
    public final Proximity proximity;
    public final KeyguardProximity$keyguardListener$1 proximityListener;

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.android.systemui.columbus.legacy.gates.KeyguardProximity$keyguardListener$1] */
    /* JADX WARN: Type inference failed for: r1v2, types: [com.google.android.systemui.columbus.legacy.gates.KeyguardProximity$keyguardListener$1] */
    public KeyguardProximity(KeyguardVisibility keyguardVisibility, Proximity proximity) {
        this.keyguardGate = keyguardVisibility;
        this.proximity = proximity;
        final int i = 0;
        this.keyguardListener = new Gate.Listener(this) { // from class: com.google.android.systemui.columbus.legacy.gates.KeyguardProximity$keyguardListener$1
            public final /* synthetic */ KeyguardProximity this$0;

            {
                this.this$0 = this;
            }

            @Override // com.google.android.systemui.columbus.legacy.gates.Gate.Listener
            public final void onGateChanged(Gate gate) {
                switch (i) {
                    case 0:
                        KeyguardProximity keyguardProximity = this.this$0;
                        keyguardProximity.getClass();
                        BuildersKt.launch$default(keyguardProximity.coroutineScope, null, null, new KeyguardProximity$updateProximityListener$1(keyguardProximity, null), 3);
                        break;
                    default:
                        KeyguardProximity keyguardProximity2 = this.this$0;
                        BuildersKt.launch$default(keyguardProximity2.coroutineScope, null, null, new KeyguardProximity$updateBlocking$1(keyguardProximity2, null), 3);
                        break;
                }
            }
        };
        final int i2 = 1;
        this.proximityListener = new Gate.Listener(this) { // from class: com.google.android.systemui.columbus.legacy.gates.KeyguardProximity$keyguardListener$1
            public final /* synthetic */ KeyguardProximity this$0;

            {
                this.this$0 = this;
            }

            @Override // com.google.android.systemui.columbus.legacy.gates.Gate.Listener
            public final void onGateChanged(Gate gate) {
                switch (i2) {
                    case 0:
                        KeyguardProximity keyguardProximity = this.this$0;
                        keyguardProximity.getClass();
                        BuildersKt.launch$default(keyguardProximity.coroutineScope, null, null, new KeyguardProximity$updateProximityListener$1(keyguardProximity, null), 3);
                        break;
                    default:
                        KeyguardProximity keyguardProximity2 = this.this$0;
                        BuildersKt.launch$default(keyguardProximity2.coroutineScope, null, null, new KeyguardProximity$updateBlocking$1(keyguardProximity2, null), 3);
                        break;
                }
            }
        };
    }

    @Override // com.google.android.systemui.columbus.legacy.gates.Gate
    public final void onActivate() {
        this.keyguardGate.registerListener(this.keyguardListener);
        BuildersKt.launch$default(this.coroutineScope, null, null, new KeyguardProximity$updateProximityListener$1(this, null), 3);
    }

    @Override // com.google.android.systemui.columbus.legacy.gates.Gate
    public final void onDeactivate() {
        this.keyguardGate.unregisterListener(this.keyguardListener);
        BuildersKt.launch$default(this.coroutineScope, null, null, new KeyguardProximity$updateProximityListener$1(this, null), 3);
    }

    @Override // com.google.android.systemui.columbus.legacy.gates.Gate
    public final String toString() {
        return super.toString() + BuildersKt.runBlocking(this.mainDispatcher, new KeyguardProximity$toString$1(this, null));
    }
}

package com.android.compose.animation.scene;

import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TransitionKey extends Key {
    public static final TransitionKey PredictiveBack = new TransitionKey("PredictiveBack");

    public TransitionKey(String str) {
        super(new Object(), str);
    }

    @Override // com.android.compose.animation.scene.Key
    public final String toString() {
        return ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(new StringBuilder("TransitionKey(debugName="), this.debugName, ")");
    }
}

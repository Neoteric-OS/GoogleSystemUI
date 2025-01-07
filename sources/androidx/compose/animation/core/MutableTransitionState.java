package androidx.compose.animation.core;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MutableTransitionState extends TransitionState {
    public final MutableState currentState$delegate;
    public final MutableState targetState$delegate;

    public MutableTransitionState(Object obj) {
        this.currentState$delegate = SnapshotStateKt.mutableStateOf$default(obj);
        SnapshotStateKt.mutableStateOf$default(obj);
    }
}

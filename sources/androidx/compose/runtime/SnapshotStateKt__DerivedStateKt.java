package androidx.compose.runtime;

import androidx.compose.runtime.internal.SnapshotThreadLocal;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
abstract /* synthetic */ class SnapshotStateKt__DerivedStateKt {
    public static final SnapshotThreadLocal calculationBlockNestedLevel = new SnapshotThreadLocal();
    public static final SnapshotThreadLocal derivedStateObservers = new SnapshotThreadLocal();
}

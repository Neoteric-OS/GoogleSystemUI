package com.android.systemui.scene.session.shared;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import java.util.HashMap;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SessionStorage {
    public final MutableState _storage$delegate;

    public SessionStorage() {
        SnapshotStateKt.mutableStateOf$default(new HashMap());
    }
}

package com.android.systemui.qs.tiles.base.viewmodel;

import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QSTileCoroutineScopeFactory {
    public final CoroutineScope applicationScope;

    public QSTileCoroutineScopeFactory(CoroutineScope coroutineScope) {
        this.applicationScope = coroutineScope;
    }
}

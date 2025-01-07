package com.android.systemui.volume.panel.component.anc.data.repository;

import androidx.slice.SliceViewManagerWrapper;
import kotlin.coroutines.CoroutineContext;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AncSliceRepositoryImpl {
    public final CoroutineContext mainCoroutineContext;
    public final SliceViewManagerWrapper sliceViewManager;

    public AncSliceRepositoryImpl(CoroutineContext coroutineContext, SliceViewManagerWrapper sliceViewManagerWrapper) {
        this.mainCoroutineContext = coroutineContext;
        this.sliceViewManager = sliceViewManagerWrapper;
    }
}

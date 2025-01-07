package com.android.systemui.common.ui.view;

import android.view.View;
import kotlinx.coroutines.DisposableHandle;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ViewExtKt$onLayoutChanged$2 implements DisposableHandle {
    public final /* synthetic */ View.OnLayoutChangeListener $listener;
    public final /* synthetic */ View $this_onLayoutChanged;

    public ViewExtKt$onLayoutChanged$2(View view, View.OnLayoutChangeListener onLayoutChangeListener) {
        this.$this_onLayoutChanged = view;
        this.$listener = onLayoutChangeListener;
    }

    @Override // kotlinx.coroutines.DisposableHandle
    public final void dispose() {
        this.$this_onLayoutChanged.removeOnLayoutChangeListener(this.$listener);
    }
}

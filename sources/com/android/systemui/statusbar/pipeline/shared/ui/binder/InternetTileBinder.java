package com.android.systemui.statusbar.pipeline.shared.ui.binder;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleKt;
import com.android.systemui.qs.tiles.InternetTileNewImpl;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class InternetTileBinder {
    public static void bind(Lifecycle lifecycle, ReadonlyStateFlow readonlyStateFlow, InternetTileNewImpl.AnonymousClass1 anonymousClass1) {
        BuildersKt.launch$default(LifecycleKt.getCoroutineScope(lifecycle), null, null, new InternetTileBinder$bind$1(lifecycle, readonlyStateFlow, anonymousClass1, null), 3);
    }
}

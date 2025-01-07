package com.android.systemui.qs.panels.ui.compose.infinitegrid;

import com.android.systemui.qs.panels.ui.viewmodel.IconTilesViewModel;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final /* synthetic */ class InfiniteGridLayout$EditTileGrid$2 extends FunctionReferenceImpl implements Function2 {
    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        boolean booleanValue = ((Boolean) obj2).booleanValue();
        ((IconTilesViewModel) this.receiver).resize((TileSpec) obj, booleanValue);
        return Unit.INSTANCE;
    }
}

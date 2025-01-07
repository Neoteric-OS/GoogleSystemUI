package com.android.systemui.qs.panels.ui.compose.infinitegrid;

import com.android.systemui.animation.Expandable;
import com.android.systemui.qs.panels.ui.viewmodel.TileViewModel;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final /* synthetic */ class TileKt$Tile$1$1 extends FunctionReferenceImpl implements Function1 {
    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        ((TileViewModel) this.receiver).tile.click((Expandable) obj);
        return Unit.INSTANCE;
    }
}

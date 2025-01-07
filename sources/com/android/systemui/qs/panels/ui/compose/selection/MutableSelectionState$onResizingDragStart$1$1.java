package com.android.systemui.qs.panels.ui.compose.selection;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class MutableSelectionState$onResizingDragStart$1$1 extends Lambda implements Function0 {
    final /* synthetic */ Selection $it;
    final /* synthetic */ MutableSelectionState this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MutableSelectionState$onResizingDragStart$1$1(MutableSelectionState mutableSelectionState, Selection selection) {
        super(0);
        this.this$0 = mutableSelectionState;
        this.$it = selection;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        this.this$0.onResize.invoke(this.$it.tileSpec);
        return Unit.INSTANCE;
    }
}

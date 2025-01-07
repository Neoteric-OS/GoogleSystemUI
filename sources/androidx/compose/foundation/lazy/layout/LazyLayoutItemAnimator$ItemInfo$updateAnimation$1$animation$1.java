package androidx.compose.foundation.lazy.layout;

import androidx.compose.ui.node.DrawModifierNode;
import androidx.compose.ui.node.DrawModifierNodeKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class LazyLayoutItemAnimator$ItemInfo$updateAnimation$1$animation$1 extends Lambda implements Function0 {
    final /* synthetic */ LazyLayoutItemAnimator this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyLayoutItemAnimator$ItemInfo$updateAnimation$1$animation$1(LazyLayoutItemAnimator lazyLayoutItemAnimator) {
        super(0);
        this.this$0 = lazyLayoutItemAnimator;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        DrawModifierNode drawModifierNode = this.this$0.displayingNode;
        if (drawModifierNode != null) {
            DrawModifierNodeKt.invalidateDraw(drawModifierNode);
        }
        return Unit.INSTANCE;
    }
}

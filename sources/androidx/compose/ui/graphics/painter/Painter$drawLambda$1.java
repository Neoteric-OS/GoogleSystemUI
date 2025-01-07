package androidx.compose.ui.graphics.painter;

import androidx.compose.ui.graphics.drawscope.DrawScope;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class Painter$drawLambda$1 extends Lambda implements Function1 {
    final /* synthetic */ Painter this$0;

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        this.this$0.onDraw((DrawScope) obj);
        return Unit.INSTANCE;
    }
}

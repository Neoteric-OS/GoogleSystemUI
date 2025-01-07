package androidx.compose.foundation.layout;

import androidx.compose.ui.Alignment;
import androidx.compose.ui.BiasAlignment;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class WrapContentElement$Companion$height$1 extends Lambda implements Function2 {
    final /* synthetic */ Alignment.Vertical $align;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WrapContentElement$Companion$height$1(BiasAlignment.Vertical vertical) {
        super(2);
        this.$align = vertical;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        long j = ((IntSize) obj).packedValue;
        return new IntOffset((((BiasAlignment.Vertical) this.$align).align(0, (int) (j & 4294967295L)) & 4294967295L) | (0 << 32));
    }
}

package androidx.compose.foundation;

import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.unit.Constraints;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class ImageKt$Image$1 implements MeasurePolicy {
    public static final ImageKt$Image$1 INSTANCE = new ImageKt$Image$1();

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* renamed from: measure-3p2s80s */
    public final MeasureResult mo2measure3p2s80s(MeasureScope measureScope, List list, long j) {
        MeasureResult layout$1;
        layout$1 = measureScope.layout$1(Constraints.m657getMinWidthimpl(j), Constraints.m656getMinHeightimpl(j), MapsKt.emptyMap(), new Function1() { // from class: androidx.compose.foundation.ImageKt$Image$1.1
            @Override // kotlin.jvm.functions.Function1
            public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return Unit.INSTANCE;
            }
        });
        return layout$1;
    }
}

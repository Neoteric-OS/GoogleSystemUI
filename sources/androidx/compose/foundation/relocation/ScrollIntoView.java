package androidx.compose.foundation.relocation;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.unit.IntSizeKt;
import kotlin.Unit;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ScrollIntoView {
    public static final Object scrollIntoView(BringIntoViewParent bringIntoViewParent, final LayoutCoordinates layoutCoordinates, final Rect rect, ContinuationImpl continuationImpl) {
        Object bringChildIntoView;
        boolean isAttached = layoutCoordinates.isAttached();
        Unit unit = Unit.INSTANCE;
        return (isAttached && (bringChildIntoView = bringIntoViewParent.bringChildIntoView(layoutCoordinates, new Function0() { // from class: androidx.compose.foundation.relocation.ScrollIntoView__ScrollIntoViewRequesterKt$scrollIntoView$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Rect rect2 = Rect.this;
                if (rect2 != null) {
                    return rect2;
                }
                LayoutCoordinates layoutCoordinates2 = layoutCoordinates;
                if (!layoutCoordinates2.isAttached()) {
                    layoutCoordinates2 = null;
                }
                if (layoutCoordinates2 != null) {
                    return RectKt.m324Recttz77jQw(0L, IntSizeKt.m685toSizeozmzZPI(layoutCoordinates2.mo481getSizeYbymL2g()));
                }
                return null;
            }
        }, continuationImpl)) == CoroutineSingletons.COROUTINE_SUSPENDED) ? bringChildIntoView : unit;
    }
}

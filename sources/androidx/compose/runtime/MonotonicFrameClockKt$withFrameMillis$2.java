package androidx.compose.runtime;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MonotonicFrameClockKt$withFrameMillis$2 extends Lambda implements Function1 {
    final /* synthetic */ Function1 $onFrame;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MonotonicFrameClockKt$withFrameMillis$2(Function1 function1) {
        super(1);
        this.$onFrame = function1;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        return this.$onFrame.invoke(Long.valueOf(((Number) obj).longValue() / 1000000));
    }
}

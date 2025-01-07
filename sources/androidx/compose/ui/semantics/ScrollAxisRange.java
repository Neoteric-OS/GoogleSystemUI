package androidx.compose.ui.semantics;

import androidx.compose.animation.ChangeSize$$ExternalSyntheticOutline0;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ScrollAxisRange {
    public final Lambda maxValue;
    public final boolean reverseScrolling;
    public final Lambda value;

    /* JADX WARN: Multi-variable type inference failed */
    public ScrollAxisRange(Function0 function0, Function0 function02, boolean z) {
        this.value = (Lambda) function0;
        this.maxValue = (Lambda) function02;
        this.reverseScrolling = z;
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
    /* JADX WARN: Type inference failed for: r1v6, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
    public final String toString() {
        StringBuilder sb = new StringBuilder("ScrollAxisRange(value=");
        sb.append(((Number) this.value.invoke()).floatValue());
        sb.append(", maxValue=");
        sb.append(((Number) this.maxValue.invoke()).floatValue());
        sb.append(", reverseScrolling=");
        return ChangeSize$$ExternalSyntheticOutline0.m(sb, this.reverseScrolling, ')');
    }
}

package androidx.compose.foundation.text;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TextRangeLayoutMeasureResult {
    public final int height;
    public final Lambda place;
    public final int width;

    /* JADX WARN: Multi-variable type inference failed */
    public TextRangeLayoutMeasureResult(int i, int i2, Function0 function0) {
        this.width = i;
        this.height = i2;
        this.place = (Lambda) function0;
    }
}

package androidx.compose.animation.core;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TwoWayConverterImpl implements TwoWayConverter {
    public final Lambda convertFromVector;
    public final Lambda convertToVector;

    /* JADX WARN: Multi-variable type inference failed */
    public TwoWayConverterImpl(Function1 function1, Function1 function12) {
        this.convertToVector = (Lambda) function1;
        this.convertFromVector = (Lambda) function12;
    }
}

package androidx.compose.foundation.layout;

import androidx.compose.ui.Modifier;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class OffsetKt {
    public static final Modifier offset(Modifier modifier, Function1 function1) {
        return modifier.then(new OffsetPxElement(function1, true));
    }

    /* renamed from: offset-VpY3zN4$default, reason: not valid java name */
    public static Modifier m93offsetVpY3zN4$default(float f) {
        return new OffsetElement(f, 0);
    }
}

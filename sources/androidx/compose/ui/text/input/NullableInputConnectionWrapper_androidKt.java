package androidx.compose.ui.text.input;

import android.view.inputmethod.InputConnection;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class NullableInputConnectionWrapper_androidKt {
    public static final NullableInputConnectionWrapper NullableInputConnectionWrapper(InputConnection inputConnection, Function1 function1) {
        return new NullableInputConnectionWrapperApi34(inputConnection, function1);
    }
}

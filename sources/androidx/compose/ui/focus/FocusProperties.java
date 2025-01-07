package androidx.compose.ui.focus;

import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface FocusProperties {
    boolean getCanFocus();

    void setCanFocus(boolean z);

    default void setEnter(Function1 function1) {
    }

    default void setExit(Function1 function1) {
    }
}

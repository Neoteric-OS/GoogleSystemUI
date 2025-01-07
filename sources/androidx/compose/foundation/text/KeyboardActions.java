package androidx.compose.foundation.text;

import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyboardActions {
    public static final KeyboardActions Default = new KeyboardActions(63, null, null);
    public final Function1 onDone;
    public final Function1 onSearch;

    public KeyboardActions(int i, Function1 function1, Function1 function12) {
        function1 = (i & 1) != 0 ? null : function1;
        function12 = (i & 16) != 0 ? null : function12;
        this.onDone = function1;
        this.onSearch = function12;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof KeyboardActions)) {
            return false;
        }
        KeyboardActions keyboardActions = (KeyboardActions) obj;
        return this.onDone == keyboardActions.onDone && this.onSearch == keyboardActions.onSearch;
    }

    public final int hashCode() {
        Function1 function1 = this.onDone;
        int hashCode = (function1 != null ? function1.hashCode() : 0) * 923521;
        Function1 function12 = this.onSearch;
        return (hashCode + (function12 != null ? function12.hashCode() : 0)) * 31;
    }
}

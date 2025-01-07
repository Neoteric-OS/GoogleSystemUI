package androidx.compose.ui.semantics;

import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SemanticsPropertyKey {
    public boolean isImportantForAccessibility;
    public final Function2 mergePolicy;
    public final String name;

    public SemanticsPropertyKey(String str, Function2 function2) {
        this.name = str;
        this.mergePolicy = function2;
    }

    public final void setValue(SemanticsPropertyReceiver semanticsPropertyReceiver, Object obj) {
        ((SemanticsConfiguration) semanticsPropertyReceiver).set(this, obj);
    }

    public final String toString() {
        return "AccessibilityKey: " + this.name;
    }

    public /* synthetic */ SemanticsPropertyKey(String str) {
        this(str, new Function2() { // from class: androidx.compose.ui.semantics.SemanticsPropertyKey.1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return obj == null ? obj2 : obj;
            }
        });
    }

    public SemanticsPropertyKey(String str, boolean z, Function2 function2) {
        this(str, function2);
        this.isImportantForAccessibility = z;
    }
}

package androidx.compose.ui.semantics;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CustomAccessibilityAction {
    public final Lambda action;
    public final String label;

    /* JADX WARN: Multi-variable type inference failed */
    public CustomAccessibilityAction(String str, Function0 function0) {
        this.label = str;
        this.action = (Lambda) function0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CustomAccessibilityAction)) {
            return false;
        }
        CustomAccessibilityAction customAccessibilityAction = (CustomAccessibilityAction) obj;
        return Intrinsics.areEqual(this.label, customAccessibilityAction.label) && this.action == customAccessibilityAction.action;
    }

    public final int hashCode() {
        return this.action.hashCode() + (this.label.hashCode() * 31);
    }

    public final String toString() {
        return "CustomAccessibilityAction(label=" + this.label + ", action=" + this.action + ')';
    }
}

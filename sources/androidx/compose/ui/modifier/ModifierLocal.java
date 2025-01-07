package androidx.compose.ui.modifier;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ModifierLocal {
    public final Lambda defaultFactory;

    /* JADX WARN: Multi-variable type inference failed */
    public ModifierLocal(Function0 function0) {
        this.defaultFactory = (Lambda) function0;
    }
}

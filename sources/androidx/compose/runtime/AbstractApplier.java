package androidx.compose.runtime;

import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class AbstractApplier implements Applier {
    public Object current;
    public final Object root;
    public final List stack = new ArrayList();

    public AbstractApplier(Object obj) {
        this.root = obj;
        this.current = obj;
    }

    public final void clear() {
        this.stack.clear();
        this.current = this.root;
        onClear();
    }

    @Override // androidx.compose.runtime.Applier
    public final void down(Object obj) {
        this.stack.add(this.current);
        this.current = obj;
    }

    @Override // androidx.compose.runtime.Applier
    public final Object getCurrent() {
        return this.current;
    }

    public abstract void onClear();

    @Override // androidx.compose.runtime.Applier
    public final void up() {
        if (this.stack.isEmpty()) {
            PreconditionsKt.throwIllegalStateException("empty stack");
        }
        this.current = this.stack.remove(((ArrayList) r0).size() - 1);
    }
}

package androidx.compose.runtime;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class OffsetApplier implements Applier {
    public final Applier applier;
    public int nesting;
    public final int offset;

    public OffsetApplier(Applier applier, int i) {
        this.applier = applier;
        this.offset = i;
    }

    @Override // androidx.compose.runtime.Applier
    public final void down(Object obj) {
        this.nesting++;
        this.applier.down(obj);
    }

    @Override // androidx.compose.runtime.Applier
    public final Object getCurrent() {
        return this.applier.getCurrent();
    }

    @Override // androidx.compose.runtime.Applier
    public final void insertBottomUp(int i, Object obj) {
        this.applier.insertBottomUp(i + (this.nesting == 0 ? this.offset : 0), obj);
    }

    @Override // androidx.compose.runtime.Applier
    public final void insertTopDown(int i, Object obj) {
        this.applier.insertTopDown(i + (this.nesting == 0 ? this.offset : 0), obj);
    }

    @Override // androidx.compose.runtime.Applier
    public final void move(int i, int i2, int i3) {
        int i4 = this.nesting == 0 ? this.offset : 0;
        this.applier.move(i + i4, i2 + i4, i3);
    }

    @Override // androidx.compose.runtime.Applier
    public final void remove(int i, int i2) {
        this.applier.remove(i + (this.nesting == 0 ? this.offset : 0), i2);
    }

    @Override // androidx.compose.runtime.Applier
    public final void up() {
        if (!(this.nesting > 0)) {
            ComposerKt.composeImmediateRuntimeError("OffsetApplier up called with no corresponding down");
        }
        this.nesting--;
        this.applier.up();
    }
}

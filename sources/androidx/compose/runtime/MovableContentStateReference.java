package androidx.compose.runtime;

import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MovableContentStateReference {
    public final Anchor anchor;
    public final CompositionImpl composition;
    public final MovableContent content;
    public List invalidations;
    public final PersistentCompositionLocalMap locals;
    public final Object parameter;
    public final SlotTable slotTable;

    public MovableContentStateReference(MovableContent movableContent, Object obj, CompositionImpl compositionImpl, SlotTable slotTable, Anchor anchor, List list, PersistentCompositionLocalMap persistentCompositionLocalMap) {
        this.content = movableContent;
        this.parameter = obj;
        this.composition = compositionImpl;
        this.slotTable = slotTable;
        this.anchor = anchor;
        this.invalidations = list;
        this.locals = persistentCompositionLocalMap;
    }
}

package androidx.compose.runtime;

import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerImpl.CompositionContextImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ComposablesKt {
    public static final int getCurrentCompositeKeyHash(Composer composer) {
        OpaqueKey opaqueKey = ComposerKt.invocation;
        return ((ComposerImpl) composer).compoundKeyHash;
    }

    public static final void invalidApplier() {
        throw new IllegalStateException("Invalid applier");
    }

    public static final CompositionContext rememberCompositionContext(Composer composer) {
        OpaqueKey opaqueKey = ComposerKt.invocation;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.getClass();
        composerImpl.startGroup(206, ComposerKt.reference);
        if (composerImpl.inserting) {
            SlotWriter.markGroup$default(composerImpl.writer);
        }
        Object nextSlot = composerImpl.nextSlot();
        ComposerImpl.CompositionContextHolder compositionContextHolder = nextSlot instanceof ComposerImpl.CompositionContextHolder ? (ComposerImpl.CompositionContextHolder) nextSlot : null;
        if (compositionContextHolder == null) {
            compositionContextHolder = new ComposerImpl.CompositionContextHolder(composerImpl.new CompositionContextImpl(composerImpl.compoundKeyHash, composerImpl.forceRecomposeScopes, composerImpl.sourceMarkersEnabled, composerImpl.composition.observerHolder));
            composerImpl.updateValue(compositionContextHolder);
        }
        PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl.currentCompositionLocalScope();
        ComposerImpl.CompositionContextImpl compositionContextImpl = compositionContextHolder.ref;
        ((SnapshotMutableStateImpl) compositionContextImpl.compositionLocalScope$delegate).setValue(currentCompositionLocalScope);
        composerImpl.end(false);
        return compositionContextImpl;
    }
}

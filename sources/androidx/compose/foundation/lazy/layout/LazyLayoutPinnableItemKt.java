package androidx.compose.foundation.lazy.layout;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.Composer$Companion$Empty$1;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DynamicProvidableCompositionLocal;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.SnapshotMutableIntStateImpl;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.layout.PinnableContainer;
import androidx.compose.ui.layout.PinnableContainerKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class LazyLayoutPinnableItemKt {
    public static final void LazyLayoutPinnableItem(final Object obj, final int i, final LazyLayoutPinnedItemList lazyLayoutPinnedItemList, final Function2 function2, Composer composer, final int i2) {
        int i3;
        LazyLayoutPinnableItem lazyLayoutPinnableItem;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-2079116560);
        if ((i2 & 6) == 0) {
            i3 = (composerImpl.changedInstance(obj) ? 4 : 2) | i2;
        } else {
            i3 = i2;
        }
        if ((i2 & 48) == 0) {
            i3 |= composerImpl.changed(i) ? 32 : 16;
        }
        if ((i2 & 384) == 0) {
            i3 |= composerImpl.changedInstance(lazyLayoutPinnedItemList) ? 256 : 128;
        }
        if ((i2 & 3072) == 0) {
            i3 |= composerImpl.changedInstance(function2) ? 2048 : 1024;
        }
        if ((i3 & 1171) == 1170 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            boolean changed = composerImpl.changed(obj) | composerImpl.changed(lazyLayoutPinnedItemList);
            Object rememberedValue = composerImpl.rememberedValue();
            Composer$Companion$Empty$1 composer$Companion$Empty$1 = Composer.Companion.Empty;
            if (changed || rememberedValue == composer$Companion$Empty$1) {
                rememberedValue = new LazyLayoutPinnableItem(obj, lazyLayoutPinnedItemList);
                composerImpl.updateRememberedValue(rememberedValue);
            }
            final LazyLayoutPinnableItem lazyLayoutPinnableItem2 = (LazyLayoutPinnableItem) rememberedValue;
            ((SnapshotMutableIntStateImpl) lazyLayoutPinnableItem2.index$delegate).setIntValue(i);
            DynamicProvidableCompositionLocal dynamicProvidableCompositionLocal = PinnableContainerKt.LocalPinnableContainer;
            PinnableContainer pinnableContainer = (PinnableContainer) composerImpl.consume(dynamicProvidableCompositionLocal);
            Snapshot currentThreadSnapshot = Snapshot.Companion.getCurrentThreadSnapshot();
            Function1 readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
            Snapshot makeCurrentNonObservable = Snapshot.Companion.makeCurrentNonObservable(currentThreadSnapshot);
            MutableState mutableState = lazyLayoutPinnableItem2._parentPinnableContainer$delegate;
            try {
                if (pinnableContainer != ((PinnableContainer) ((SnapshotMutableStateImpl) mutableState).getValue())) {
                    ((SnapshotMutableStateImpl) mutableState).setValue(pinnableContainer);
                    if (lazyLayoutPinnableItem2.getPinsCount() > 0) {
                        MutableState mutableState2 = lazyLayoutPinnableItem2.parentHandle$delegate;
                        PinnableContainer.PinnedHandle pinnedHandle = (PinnableContainer.PinnedHandle) ((SnapshotMutableStateImpl) mutableState2).getValue();
                        if (pinnedHandle != null) {
                            ((LazyLayoutPinnableItem) pinnedHandle).release();
                        }
                        if (pinnableContainer != null) {
                            lazyLayoutPinnableItem = (LazyLayoutPinnableItem) pinnableContainer;
                            lazyLayoutPinnableItem.pin();
                        } else {
                            lazyLayoutPinnableItem = null;
                        }
                        ((SnapshotMutableStateImpl) mutableState2).setValue(lazyLayoutPinnableItem);
                    }
                }
                Snapshot.Companion.restoreNonObservable(currentThreadSnapshot, makeCurrentNonObservable, readObserver);
                boolean changed2 = composerImpl.changed(lazyLayoutPinnableItem2);
                Object rememberedValue2 = composerImpl.rememberedValue();
                if (changed2 || rememberedValue2 == composer$Companion$Empty$1) {
                    rememberedValue2 = new Function1() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutPinnableItemKt$LazyLayoutPinnableItem$1$1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            final LazyLayoutPinnableItem lazyLayoutPinnableItem3 = LazyLayoutPinnableItem.this;
                            return new DisposableEffectResult() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutPinnableItemKt$LazyLayoutPinnableItem$1$1$invoke$$inlined$onDispose$1
                                @Override // androidx.compose.runtime.DisposableEffectResult
                                public final void dispose() {
                                    LazyLayoutPinnableItem lazyLayoutPinnableItem4 = LazyLayoutPinnableItem.this;
                                    int pinsCount = lazyLayoutPinnableItem4.getPinsCount();
                                    for (int i4 = 0; i4 < pinsCount; i4++) {
                                        lazyLayoutPinnableItem4.release();
                                    }
                                }
                            };
                        }
                    };
                    composerImpl.updateRememberedValue(rememberedValue2);
                }
                EffectsKt.DisposableEffect(lazyLayoutPinnableItem2, (Function1) rememberedValue2, composerImpl);
                CompositionLocalKt.CompositionLocalProvider(dynamicProvidableCompositionLocal.defaultProvidedValue$runtime_release(lazyLayoutPinnableItem2), function2, composerImpl, ((i3 >> 6) & 112) | 8);
            } catch (Throwable th) {
                Snapshot.Companion.restoreNonObservable(currentThreadSnapshot, makeCurrentNonObservable, readObserver);
                throw th;
            }
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutPinnableItemKt$LazyLayoutPinnableItem$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    ((Number) obj3).intValue();
                    LazyLayoutPinnableItemKt.LazyLayoutPinnableItem(obj, i, lazyLayoutPinnedItemList, function2, (Composer) obj2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }
}

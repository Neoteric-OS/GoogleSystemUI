package com.android.systemui.qs.panels.ui.compose;

import android.view.DragEvent;
import androidx.compose.foundation.draganddrop.AndroidDragAndDropSource_androidKt;
import androidx.compose.foundation.draganddrop.DragAndDropTargetKt;
import androidx.compose.foundation.lazy.grid.LazyGridItemInfo;
import androidx.compose.foundation.lazy.grid.LazyGridMeasureResult;
import androidx.compose.foundation.lazy.grid.LazyGridMeasuredItem;
import androidx.compose.foundation.lazy.grid.LazyGridState;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draganddrop.DragAndDropEvent;
import androidx.compose.ui.draganddrop.DragAndDropTarget;
import androidx.compose.ui.draganddrop.DragAndDrop_androidKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.unit.IntRectKt;
import com.android.systemui.qs.panels.shared.model.SizedTile;
import com.android.systemui.qs.panels.shared.model.SizedTileImpl;
import com.android.systemui.qs.panels.ui.model.GridCell;
import com.android.systemui.qs.panels.ui.model.TileGridCell;
import com.android.systemui.qs.panels.ui.viewmodel.EditTileViewModel;
import java.util.Iterator;
import java.util.function.Predicate;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class DragAndDropStateKt {
    public static final Modifier dragAndDropRemoveZone(Modifier modifier, final DragAndDropState dragAndDropState, final Function1 function1, Composer composer, int i) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceGroup(-1244306089);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        composerImpl.startReplaceGroup(-341233096);
        boolean changed = composerImpl.changed(dragAndDropState);
        Object rememberedValue = composerImpl.rememberedValue();
        if (changed || rememberedValue == Composer.Companion.Empty) {
            rememberedValue = new DragAndDropTarget() { // from class: com.android.systemui.qs.panels.ui.compose.DragAndDropStateKt$dragAndDropRemoveZone$target$1$1
                @Override // androidx.compose.ui.draganddrop.DragAndDropTarget
                public final boolean onDrop(DragAndDropEvent dragAndDropEvent) {
                    EditTileListState editTileListState = (EditTileListState) DragAndDropState.this;
                    SizedTile sizedTile = (SizedTile) ((SnapshotMutableStateImpl) editTileListState._draggedCell).getValue();
                    if (sizedTile == null) {
                        return false;
                    }
                    function1.invoke(((EditTileViewModel) sizedTile.getTile()).tileSpec);
                    ((SnapshotMutableStateImpl) editTileListState._draggedCell).setValue(null);
                    editTileListState.regenerateGrid();
                    return true;
                }

                @Override // androidx.compose.ui.draganddrop.DragAndDropTarget
                public final void onEntered(DragAndDropEvent dragAndDropEvent) {
                    EditTileListState editTileListState = (EditTileListState) DragAndDropState.this;
                    final SizedTile sizedTile = (SizedTile) ((SnapshotMutableStateImpl) editTileListState._draggedCell).getValue();
                    if (sizedTile == null) {
                        return;
                    }
                    editTileListState._tiles.removeIf(new Predicate() { // from class: com.android.systemui.qs.panels.ui.compose.EditTileListState$movedOutOfBounds$1
                        @Override // java.util.function.Predicate
                        public final boolean test(Object obj) {
                            GridCell gridCell = (GridCell) obj;
                            return (gridCell instanceof TileGridCell) && ((TileGridCell) gridCell).tile.tileSpec.equals(((EditTileViewModel) SizedTile.this.getTile()).tileSpec);
                        }
                    });
                }
            };
            composerImpl.updateRememberedValue(rememberedValue);
        }
        composerImpl.end(false);
        Modifier dragAndDropTarget = DragAndDropTargetKt.dragAndDropTarget(modifier, new Function1() { // from class: com.android.systemui.qs.panels.ui.compose.DragAndDropStateKt$dragAndDropRemoveZone$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(DragAndDrop_androidKt.mimeTypes((DragAndDropEvent) obj).contains("qstile/tilespec"));
            }
        }, (DragAndDropStateKt$dragAndDropRemoveZone$target$1$1) rememberedValue);
        composerImpl.end(false);
        return dragAndDropTarget;
    }

    public static final Modifier dragAndDropTileList(Modifier modifier, final LazyGridState lazyGridState, final Function0 function0, final EditTileListState editTileListState, final Function1 function1, Composer composer, int i) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceGroup(168293444);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        composerImpl.startReplaceGroup(2134603099);
        boolean z = (((i & 7168) ^ 3072) > 2048 && composerImpl.changed(editTileListState)) || (i & 3072) == 2048;
        Object rememberedValue = composerImpl.rememberedValue();
        if (z || rememberedValue == Composer.Companion.Empty) {
            rememberedValue = new DragAndDropTarget() { // from class: com.android.systemui.qs.panels.ui.compose.DragAndDropStateKt$dragAndDropTileList$target$1$1
                @Override // androidx.compose.ui.draganddrop.DragAndDropTarget
                public final boolean onDrop(DragAndDropEvent dragAndDropEvent) {
                    EditTileListState editTileListState2 = EditTileListState.this;
                    SizedTile sizedTile = (SizedTile) ((SnapshotMutableStateImpl) editTileListState2._draggedCell).getValue();
                    if (sizedTile == null) {
                        return false;
                    }
                    function1.invoke(((EditTileViewModel) sizedTile.getTile()).tileSpec);
                    ((SnapshotMutableStateImpl) editTileListState2._draggedCell).setValue(null);
                    editTileListState2.regenerateGrid();
                    return true;
                }

                @Override // androidx.compose.ui.draganddrop.DragAndDropTarget
                public final void onEnded(DragAndDropEvent dragAndDropEvent) {
                    EditTileListState editTileListState2 = EditTileListState.this;
                    ((SnapshotMutableStateImpl) editTileListState2._draggedCell).setValue(null);
                    editTileListState2.regenerateGrid();
                }

                @Override // androidx.compose.ui.draganddrop.DragAndDropTarget
                public final void onMoved(DragAndDropEvent dragAndDropEvent) {
                    Object obj;
                    int indexOf;
                    int i2;
                    long j = ((Offset) function0.invoke()).packedValue;
                    DragEvent dragEvent = dragAndDropEvent.dragEvent;
                    long m314minusMKHz9U = Offset.m314minusMKHz9U((Float.floatToRawIntBits(dragEvent.getX()) << 32) | (Float.floatToRawIntBits(dragEvent.getY()) & 4294967295L), j);
                    Iterator it = ((LazyGridMeasureResult) lazyGridState.getLayoutInfo()).visibleItemsInfo.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            obj = null;
                            break;
                        }
                        obj = it.next();
                        LazyGridMeasuredItem lazyGridMeasuredItem = (LazyGridMeasuredItem) ((LazyGridItemInfo) obj);
                        IntRect m681IntRectVbeCjmY = IntRectKt.m681IntRectVbeCjmY(lazyGridMeasuredItem.offset, lazyGridMeasuredItem.size);
                        if (new Rect(m681IntRectVbeCjmY.left, m681IntRectVbeCjmY.top, m681IntRectVbeCjmY.right, m681IntRectVbeCjmY.bottom).m319containsk4lQ0M(m314minusMKHz9U)) {
                            break;
                        }
                    }
                    LazyGridItemInfo lazyGridItemInfo = (LazyGridItemInfo) obj;
                    if (lazyGridItemInfo != null) {
                        LazyGridMeasuredItem lazyGridMeasuredItem2 = (LazyGridMeasuredItem) lazyGridItemInfo;
                        long j2 = lazyGridMeasuredItem2.offset;
                        long j3 = lazyGridMeasuredItem2.size;
                        boolean z2 = lazyGridMeasuredItem2.span != 1 && Float.intBitsToFloat((int) (m314minusMKHz9U >> 32)) > ((float) ((int) (IntOffset.m676plusqkQi6aY(j2, (4294967295L & ((j3 << 32) >> 33)) | ((j3 >> 33) << 32)) >> 32)));
                        EditTileListState editTileListState2 = EditTileListState.this;
                        SizedTile sizedTile = (SizedTile) ((SnapshotMutableStateImpl) editTileListState2._draggedCell).getValue();
                        if (sizedTile == null || (indexOf = editTileListState2.indexOf(((EditTileViewModel) sizedTile.getTile()).tileSpec)) == (i2 = lazyGridMeasuredItem2.index)) {
                            return;
                        }
                        if (z2) {
                            i2++;
                        }
                        SnapshotStateList snapshotStateList = editTileListState2._tiles;
                        if (indexOf != -1) {
                            GridCell gridCell = (GridCell) snapshotStateList.remove(indexOf);
                            editTileListState2.regenerateGrid();
                            snapshotStateList.add(RangesKt.coerceIn(i2, 0, snapshotStateList.size()), gridCell);
                        } else {
                            snapshotStateList.add(RangesKt.coerceIn(i2, 0, snapshotStateList.size()), new TileGridCell(sizedTile, 0));
                        }
                        editTileListState2.regenerateGrid();
                    }
                }
            };
            composerImpl.updateRememberedValue(rememberedValue);
        }
        composerImpl.end(false);
        Modifier dragAndDropTarget = DragAndDropTargetKt.dragAndDropTarget(modifier, new Function1() { // from class: com.android.systemui.qs.panels.ui.compose.DragAndDropStateKt$dragAndDropTileList$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(DragAndDrop_androidKt.mimeTypes((DragAndDropEvent) obj).contains("qstile/tilespec"));
            }
        }, (DragAndDropStateKt$dragAndDropTileList$target$1$1) rememberedValue);
        composerImpl.end(false);
        return dragAndDropTarget;
    }

    public static final Modifier dragAndDropTileSource(Modifier modifier, SizedTileImpl sizedTileImpl, DragAndDropState dragAndDropState, Function0 function0, Composer composer, int i) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceGroup(1208043256);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        Modifier dragAndDropSource = AndroidDragAndDropSource_androidKt.dragAndDropSource(modifier, new DragAndDropStateKt$dragAndDropTileSource$1(sizedTileImpl, function0, SnapshotStateKt.rememberUpdatedState(dragAndDropState, composerImpl), null));
        composerImpl.end(false);
        return dragAndDropSource;
    }
}

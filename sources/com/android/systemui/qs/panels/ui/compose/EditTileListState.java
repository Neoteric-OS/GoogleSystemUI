package com.android.systemui.qs.panels.ui.compose;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import com.android.systemui.qs.panels.ui.model.GridCell;
import com.android.systemui.qs.panels.ui.model.TileGridCell;
import com.android.systemui.qs.panels.ui.model.TileGridCellKt;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class EditTileListState implements DragAndDropState {
    public final MutableState _draggedCell = SnapshotStateKt.mutableStateOf$default(null);
    public final SnapshotStateList _tiles;
    public final int columns;

    public EditTileListState(int i, List list) {
        this.columns = i;
        List gridCells = TileGridCellKt.toGridCells(i, 0, list);
        SnapshotStateList snapshotStateList = new SnapshotStateList();
        snapshotStateList.addAll(gridCells);
        this._tiles = snapshotStateList;
    }

    public final int indexOf(TileSpec tileSpec) {
        int i = 0;
        for (GridCell gridCell : this._tiles) {
            if ((gridCell instanceof TileGridCell) && Intrinsics.areEqual(((TileGridCell) gridCell).tile.tileSpec, tileSpec)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public final void regenerateGrid() {
        ArrayList arrayList = new ArrayList();
        SnapshotStateList snapshotStateList = this._tiles;
        ListIterator listIterator = snapshotStateList.listIterator();
        while (listIterator.hasNext()) {
            Object next = listIterator.next();
            if (next instanceof TileGridCell) {
                arrayList.add(next);
            }
        }
        List gridCells = TileGridCellKt.toGridCells(this.columns, 0, arrayList);
        snapshotStateList.clear();
        snapshotStateList.addAll(gridCells);
    }
}

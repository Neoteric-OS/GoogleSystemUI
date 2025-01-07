package androidx.compose.runtime.changelist;

import androidx.compose.runtime.Applier;
import androidx.compose.runtime.SlotWriter;
import androidx.compose.runtime.internal.RememberEventDispatcher;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ChangeList extends OperationsDebugStringFormattable {
    public final Operations operations = new Operations();

    public final void executeAndFlushAllPendingChanges(Applier applier, SlotWriter slotWriter, RememberEventDispatcher rememberEventDispatcher) {
        this.operations.executeAndFlushAllPendingOperations(applier, slotWriter, rememberEventDispatcher);
    }
}

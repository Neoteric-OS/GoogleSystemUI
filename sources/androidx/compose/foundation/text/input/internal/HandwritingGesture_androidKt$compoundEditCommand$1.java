package androidx.compose.foundation.text.input.internal;

import androidx.compose.ui.text.input.EditCommand;
import androidx.compose.ui.text.input.EditingBuffer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class HandwritingGesture_androidKt$compoundEditCommand$1 implements EditCommand {
    public final /* synthetic */ EditCommand[] $editCommands;

    public HandwritingGesture_androidKt$compoundEditCommand$1(EditCommand[] editCommandArr) {
        this.$editCommands = editCommandArr;
    }

    @Override // androidx.compose.ui.text.input.EditCommand
    public final void applyTo(EditingBuffer editingBuffer) {
        for (EditCommand editCommand : this.$editCommands) {
            editCommand.applyTo(editingBuffer);
        }
    }
}

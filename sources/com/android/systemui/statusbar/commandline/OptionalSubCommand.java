package com.android.systemui.statusbar.commandline;

import android.util.IndentingPrintWriter;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class OptionalSubCommand implements Describable {
    public final ParseableCommand cmd;
    public boolean isPresent;
    public final String longName;
    public boolean validationStatus = true;

    public OptionalSubCommand(ParseableCommand parseableCommand) {
        this.cmd = parseableCommand;
        this.longName = parseableCommand.name;
    }

    @Override // com.android.systemui.statusbar.commandline.Describable
    public final void describe(IndentingPrintWriter indentingPrintWriter) {
        this.cmd.help(indentingPrintWriter);
    }

    @Override // com.android.systemui.statusbar.commandline.Describable
    public final String getDescription() {
        return null;
    }

    @Override // com.android.systemui.statusbar.commandline.Describable
    public final String getLongName() {
        return this.longName;
    }

    @Override // com.android.systemui.statusbar.commandline.Describable
    public final String getShortName() {
        return null;
    }
}

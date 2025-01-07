package com.android.systemui.statusbar.commandline;

import android.util.IndentingPrintWriter;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class ParseableCommandKt {
    public static final void indented(IndentingPrintWriter indentingPrintWriter, Function0 function0) {
        indentingPrintWriter.increaseIndent();
        function0.invoke();
        indentingPrintWriter.decreaseIndent();
    }
}

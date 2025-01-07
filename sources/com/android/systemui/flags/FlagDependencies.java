package com.android.systemui.flags;

import android.util.IndentingPrintWriter;
import com.android.systemui.CoreStartable;
import com.android.systemui.util.DumpUtilsKt;
import java.io.PrintWriter;
import java.util.ArrayList;
import kotlin.collections.EmptyIterator;
import kotlin.collections.EmptyList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FlagDependencies implements CoreStartable {
    public final EmptyList allDependencies;
    public final FlagDependenciesNotifier handler;
    public final EmptyList unmetDependencies;

    public FlagDependencies(FeatureFlagsClassic featureFlagsClassic, FlagDependenciesNotifier flagDependenciesNotifier) {
        this.handler = flagDependenciesNotifier;
        new ArrayList();
        EmptyList emptyList = EmptyList.INSTANCE;
        this.allDependencies = emptyList;
        this.unmetDependencies = emptyList;
    }

    @Override // com.android.systemui.CoreStartable, com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        IndentingPrintWriter asIndenting = DumpUtilsKt.asIndenting(printWriter);
        EmptyList emptyList = this.allDependencies;
        PrintWriter append = asIndenting.append("allDependencies").append((CharSequence) ": ");
        emptyList.getClass();
        append.println(0);
        asIndenting.increaseIndent();
        try {
            EmptyIterator emptyIterator = EmptyIterator.INSTANCE;
            asIndenting.decreaseIndent();
            EmptyList emptyList2 = this.unmetDependencies;
            PrintWriter append2 = asIndenting.append("unmetDependencies").append((CharSequence) ": ");
            emptyList2.getClass();
            append2.println(0);
            asIndenting.increaseIndent();
        } finally {
            asIndenting.decreaseIndent();
        }
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        this.handler.getClass();
    }
}

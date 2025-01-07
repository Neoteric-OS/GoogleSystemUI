package androidx.activity;

import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FullyDrawnReporter {
    public final Object lock = new Object();
    public final List onReportCallbacks = new ArrayList();
    public boolean reportedFullyDrawn;

    public FullyDrawnReporter(Function0 function0) {
    }
}

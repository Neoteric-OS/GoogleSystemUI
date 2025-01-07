package com.android.systemui.log.table;

import com.android.systemui.log.table.TableLogBuffer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface Diffable {
    void logDiffs(Object obj, TableLogBuffer.TableRowLoggerImpl tableRowLoggerImpl);

    default void logFull(TableLogBuffer.TableRowLoggerImpl tableRowLoggerImpl) {
    }
}

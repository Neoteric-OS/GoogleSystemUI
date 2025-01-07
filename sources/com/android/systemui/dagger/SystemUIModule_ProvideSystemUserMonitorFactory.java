package com.android.systemui.dagger;

import com.android.systemui.log.table.TableLogBuffer;
import com.android.systemui.process.condition.SystemProcessCondition;
import com.android.systemui.shared.condition.Monitor;
import dagger.internal.Provider;
import java.util.Collections;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class SystemUIModule_ProvideSystemUserMonitorFactory implements Provider {
    public static Monitor provideSystemUserMonitor(Executor executor, SystemProcessCondition systemProcessCondition, TableLogBuffer tableLogBuffer) {
        return new Monitor(executor, Collections.singleton(systemProcessCondition), tableLogBuffer);
    }
}

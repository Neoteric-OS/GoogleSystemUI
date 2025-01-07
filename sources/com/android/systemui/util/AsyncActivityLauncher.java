package com.android.systemui.util;

import android.app.IActivityTaskManager;
import android.content.Context;
import java.util.concurrent.Executor;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AsyncActivityLauncher {
    public final IActivityTaskManager activityTaskManager;
    public final Executor backgroundExecutor;
    public final Context context;
    public final Executor mainExecutor;
    public Function1 pendingCallback;

    public AsyncActivityLauncher(Context context, IActivityTaskManager iActivityTaskManager, Executor executor, Executor executor2) {
        this.context = context;
        this.activityTaskManager = iActivityTaskManager;
        this.backgroundExecutor = executor;
        this.mainExecutor = executor2;
    }
}

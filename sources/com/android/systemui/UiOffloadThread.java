package com.android.systemui;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class UiOffloadThread {
    public final ExecutorService mExecutorService = Executors.newSingleThreadExecutor();
}

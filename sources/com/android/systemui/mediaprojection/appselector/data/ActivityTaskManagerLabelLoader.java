package com.android.systemui.mediaprojection.appselector.data;

import android.content.ComponentName;
import android.content.pm.PackageManager;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ActivityTaskManagerLabelLoader {
    public final CoroutineDispatcher coroutineDispatcher;
    public final PackageManager packageManager;

    public ActivityTaskManagerLabelLoader(CoroutineDispatcher coroutineDispatcher, PackageManager packageManager) {
        this.coroutineDispatcher = coroutineDispatcher;
        this.packageManager = packageManager;
    }

    public final Object loadLabel(int i, ComponentName componentName, Continuation continuation) {
        return BuildersKt.withContext(this.coroutineDispatcher, new ActivityTaskManagerLabelLoader$loadLabel$2(this, componentName, i, null), continuation);
    }
}

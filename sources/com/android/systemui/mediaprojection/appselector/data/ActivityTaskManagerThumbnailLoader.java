package com.android.systemui.mediaprojection.appselector.data;

import com.android.systemui.shared.system.ActivityManagerWrapper;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ActivityTaskManagerThumbnailLoader {
    public final ActivityManagerWrapper activityManager;
    public final CoroutineDispatcher coroutineDispatcher;

    public ActivityTaskManagerThumbnailLoader(CoroutineDispatcher coroutineDispatcher, ActivityManagerWrapper activityManagerWrapper) {
        this.coroutineDispatcher = coroutineDispatcher;
        this.activityManager = activityManagerWrapper;
    }

    public final Object captureThumbnail(int i, Continuation continuation) {
        return BuildersKt.withContext(this.coroutineDispatcher, new ActivityTaskManagerThumbnailLoader$captureThumbnail$2(this, i, null), continuation);
    }

    public final Object loadThumbnail(int i, Continuation continuation) {
        return BuildersKt.withContext(this.coroutineDispatcher, new ActivityTaskManagerThumbnailLoader$loadThumbnail$2(this, i, null), continuation);
    }
}

package com.android.systemui.qs.panels.data.repository;

import com.android.systemui.qs.pipeline.data.repository.InstalledTilesComponentRepository;
import com.android.systemui.settings.UserTracker;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.BuildersKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class IconAndNameCustomRepository {
    public final CoroutineContext backgroundContext;
    public final InstalledTilesComponentRepository installedTilesComponentRepository;
    public final UserTracker userTracker;

    public IconAndNameCustomRepository(InstalledTilesComponentRepository installedTilesComponentRepository, UserTracker userTracker, CoroutineContext coroutineContext) {
        this.installedTilesComponentRepository = installedTilesComponentRepository;
        this.userTracker = userTracker;
        this.backgroundContext = coroutineContext;
    }

    public final Object getCustomTileData(Continuation continuation) {
        return BuildersKt.withContext(this.backgroundContext, new IconAndNameCustomRepository$getCustomTileData$2(this, null), continuation);
    }
}

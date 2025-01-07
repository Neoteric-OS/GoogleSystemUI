package com.android.systemui.qs.pipeline.domain.interactor;

import com.android.systemui.qs.pipeline.data.repository.AutoAddSettingRepository;
import com.android.systemui.qs.pipeline.data.repository.QSSettingsRestoredRepository;
import com.android.systemui.qs.pipeline.data.repository.TileSpecSettingsRepository;
import com.android.systemui.qs.pipeline.shared.logging.QSPipelineLogger;
import java.util.Set;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class RestoreReconciliationInteractor {
    public final CoroutineScope applicationScope;
    public final AutoAddSettingRepository autoAddRepository;
    public final CoroutineDispatcher backgroundDispatcher;
    public final QSPipelineLogger qsPipelineLogger;
    public final QSSettingsRestoredRepository qsSettingsRestoredRepository;
    public final Set restoreProcessors;
    public final TileSpecSettingsRepository tileSpecRepository;

    public RestoreReconciliationInteractor(TileSpecSettingsRepository tileSpecSettingsRepository, AutoAddSettingRepository autoAddSettingRepository, QSSettingsRestoredRepository qSSettingsRestoredRepository, Set set, QSPipelineLogger qSPipelineLogger, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher) {
        this.tileSpecRepository = tileSpecSettingsRepository;
        this.autoAddRepository = autoAddSettingRepository;
        this.qsSettingsRestoredRepository = qSSettingsRestoredRepository;
        this.restoreProcessors = set;
        this.qsPipelineLogger = qSPipelineLogger;
        this.applicationScope = coroutineScope;
        this.backgroundDispatcher = coroutineDispatcher;
    }

    public final void start() {
        BuildersKt.launch$default(this.applicationScope, this.backgroundDispatcher, null, new RestoreReconciliationInteractor$start$1(this, null), 2);
    }
}

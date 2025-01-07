package com.android.systemui.communal.domain.interactor;

import com.android.systemui.communal.data.repository.CommunalTutorialDisabledRepositoryImpl;
import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import com.android.systemui.log.table.DiffableKt;
import com.android.systemui.log.table.TableLogBuffer;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CommunalTutorialInteractor {
    public final CommunalSettingsInteractor communalSettingsInteractor;
    public final CommunalTutorialDisabledRepositoryImpl communalTutorialRepository;
    public final ReadonlyStateFlow isTutorialAvailable;
    public final Flow tutorialStateToUpdate;

    public CommunalTutorialInteractor(CoroutineScope coroutineScope, CommunalTutorialDisabledRepositoryImpl communalTutorialDisabledRepositoryImpl, KeyguardInteractor keyguardInteractor, CommunalSettingsInteractor communalSettingsInteractor, CommunalInteractor communalInteractor, TableLogBuffer tableLogBuffer) {
        this.communalTutorialRepository = communalTutorialDisabledRepositoryImpl;
        this.communalSettingsInteractor = communalSettingsInteractor;
        this.isTutorialAvailable = FlowKt.stateIn(DiffableKt.logDiffsForTable((Flow) FlowKt.combine(communalInteractor.isCommunalAvailable, keyguardInteractor.isKeyguardVisible, communalTutorialDisabledRepositoryImpl.tutorialSettingState, new CommunalTutorialInteractor$isTutorialAvailable$1(4, null)), tableLogBuffer, "", "isTutorialAvailable", false), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), Boolean.FALSE);
        this.tutorialStateToUpdate = FlowKt.distinctUntilChanged(new FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1(FlowKt.transformLatest(communalTutorialDisabledRepositoryImpl.tutorialSettingState, new CommunalTutorialInteractor$special$$inlined$flatMapLatest$1(null, communalInteractor, this))));
        BuildersKt.launch$default(coroutineScope, null, null, new CommunalTutorialInteractor$listenForTransitionToUpdateTutorialState$1(this, null), 3);
    }
}

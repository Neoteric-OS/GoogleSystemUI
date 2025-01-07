package com.android.systemui.communal;

import com.android.systemui.CoreStartable;
import com.android.systemui.communal.data.repository.CommunalMediaRepositoryImpl;
import com.android.systemui.communal.data.repository.CommunalSmartspaceRepositoryImpl;
import com.android.systemui.communal.domain.interactor.CommunalInteractor;
import com.android.systemui.communal.domain.interactor.CommunalSettingsInteractor;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CommunalOngoingContentStartable implements CoreStartable {
    public final CoroutineScope bgScope;
    public final CommunalInteractor communalInteractor;
    public final CommunalMediaRepositoryImpl communalMediaRepository;
    public final CommunalSettingsInteractor communalSettingsInteractor;
    public final CommunalSmartspaceRepositoryImpl communalSmartspaceRepository;

    public CommunalOngoingContentStartable(CoroutineScope coroutineScope, CommunalInteractor communalInteractor, CommunalMediaRepositoryImpl communalMediaRepositoryImpl, CommunalSettingsInteractor communalSettingsInteractor, CommunalSmartspaceRepositoryImpl communalSmartspaceRepositoryImpl) {
        this.bgScope = coroutineScope;
        this.communalInteractor = communalInteractor;
        this.communalMediaRepository = communalMediaRepositoryImpl;
        this.communalSettingsInteractor = communalSettingsInteractor;
        this.communalSmartspaceRepository = communalSmartspaceRepositoryImpl;
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        if (this.communalSettingsInteractor.isCommunalFlagEnabled()) {
            BuildersKt.launch$default(this.bgScope, null, null, new CommunalOngoingContentStartable$start$1(this, null), 3);
        }
    }
}

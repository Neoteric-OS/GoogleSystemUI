package com.android.systemui.assist.domain.interactor;

import com.android.systemui.assist.data.repository.AssistRepository;
import kotlinx.coroutines.flow.ReadonlySharedFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AssistInteractor {
    public final ReadonlySharedFlow latestInvocationType;
    public final AssistRepository repository;

    public AssistInteractor(AssistRepository assistRepository) {
        this.repository = assistRepository;
        this.latestInvocationType = assistRepository.latestInvocationType;
    }
}

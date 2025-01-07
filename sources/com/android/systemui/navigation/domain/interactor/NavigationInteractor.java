package com.android.systemui.navigation.domain.interactor;

import com.android.systemui.navigation.data.repository.NavigationRepository;
import kotlinx.coroutines.flow.Flow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class NavigationInteractor {
    public final Flow isGesturalMode;

    public NavigationInteractor(NavigationRepository navigationRepository) {
        Flow flow = navigationRepository.isGesturalMode;
    }
}

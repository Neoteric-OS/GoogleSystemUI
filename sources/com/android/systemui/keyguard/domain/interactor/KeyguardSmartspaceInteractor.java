package com.android.systemui.keyguard.domain.interactor;

import com.android.systemui.keyguard.data.repository.KeyguardSmartspaceRepositoryImpl;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardSmartspaceInteractor {
    public final ReadonlyStateFlow bcSmartspaceVisibility;
    public final ReadonlyStateFlow isWeatherEnabled;
    public final KeyguardSmartspaceRepositoryImpl keyguardSmartspaceRepository;

    public KeyguardSmartspaceInteractor(KeyguardSmartspaceRepositoryImpl keyguardSmartspaceRepositoryImpl) {
        this.keyguardSmartspaceRepository = keyguardSmartspaceRepositoryImpl;
        this.bcSmartspaceVisibility = keyguardSmartspaceRepositoryImpl.bcSmartspaceVisibility;
        this.isWeatherEnabled = keyguardSmartspaceRepositoryImpl.isWeatherEnabled;
    }
}

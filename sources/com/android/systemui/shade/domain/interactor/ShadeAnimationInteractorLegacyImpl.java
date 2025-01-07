package com.android.systemui.shade.domain.interactor;

import com.android.systemui.shade.data.repository.ShadeAnimationRepository;
import com.android.systemui.shade.data.repository.ShadeRepository;
import com.android.systemui.shade.data.repository.ShadeRepositoryImpl;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ShadeAnimationInteractorLegacyImpl extends ShadeAnimationInteractor {
    public final ReadonlyStateFlow isAnyCloseAnimationRunning;

    public ShadeAnimationInteractorLegacyImpl(ShadeAnimationRepository shadeAnimationRepository, ShadeRepository shadeRepository) {
        super(shadeAnimationRepository);
        this.isAnyCloseAnimationRunning = ((ShadeRepositoryImpl) shadeRepository).legacyIsClosing;
    }

    @Override // com.android.systemui.shade.domain.interactor.ShadeAnimationInteractor
    public final StateFlow isAnyCloseAnimationRunning() {
        return this.isAnyCloseAnimationRunning;
    }
}

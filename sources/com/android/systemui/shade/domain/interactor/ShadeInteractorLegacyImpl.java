package com.android.systemui.shade.domain.interactor;

import com.android.app.tracing.FlowTracing;
import com.android.systemui.keyguard.data.repository.KeyguardRepositoryImpl;
import com.android.systemui.shade.data.repository.ShadeRepository;
import com.android.systemui.shade.data.repository.ShadeRepositoryImpl;
import com.android.systemui.statusbar.notification.stack.domain.interactor.SharedNotificationContainerInteractor;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SafeFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StartedEagerly;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ShadeInteractorLegacyImpl implements BaseShadeInteractor {
    public final ReadonlyStateFlow anyExpansion;
    public final ReadonlyStateFlow isAnyExpanded;
    public final StateFlow isQsBypassingShade;
    public final ReadonlyStateFlow isQsExpanded;
    public final StateFlow isQsFullscreen;
    public final SafeFlow isUserInteractingWithQs;
    public final FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 isUserInteractingWithShade;
    public final ReadonlyStateFlow qsExpansion;
    public final ReadonlyStateFlow shadeExpansion;

    public ShadeInteractorLegacyImpl(CoroutineScope coroutineScope, KeyguardRepositoryImpl keyguardRepositoryImpl, SharedNotificationContainerInteractor sharedNotificationContainerInteractor, ShadeRepository shadeRepository) {
        FlowTracing flowTracing = FlowTracing.INSTANCE;
        ShadeRepositoryImpl shadeRepositoryImpl = (ShadeRepositoryImpl) shadeRepository;
        ReadonlyStateFlow readonlyStateFlow = shadeRepositoryImpl.lockscreenShadeExpansion;
        ShadeInteractorLegacyImpl$shadeExpansion$1 shadeInteractorLegacyImpl$shadeExpansion$1 = new ShadeInteractorLegacyImpl$shadeExpansion$1(null);
        ReadonlyStateFlow readonlyStateFlow2 = keyguardRepositoryImpl.statusBarState;
        Flow flow = sharedNotificationContainerInteractor.isSplitShadeEnabled;
        ReadonlyStateFlow readonlyStateFlow3 = shadeRepositoryImpl.legacyShadeExpansion;
        ReadonlyStateFlow readonlyStateFlow4 = shadeRepositoryImpl.qsExpansion;
        FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1 traceAsCounter$default = FlowTracing.traceAsCounter$default(FlowKt.distinctUntilChanged(FlowKt.combine(readonlyStateFlow, readonlyStateFlow2, readonlyStateFlow3, readonlyStateFlow4, flow, shadeInteractorLegacyImpl$shadeExpansion$1)), new Function1() { // from class: com.android.systemui.shade.domain.interactor.ShadeInteractorLegacyImpl$shadeExpansion$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Integer.valueOf((int) (((Number) obj).floatValue() * 100.0f));
            }
        });
        StartedEagerly startedEagerly = SharingStarted.Companion.Eagerly;
        ReadonlyStateFlow stateIn = FlowKt.stateIn(traceAsCounter$default, coroutineScope, startedEagerly, Float.valueOf(0.0f));
        this.shadeExpansion = stateIn;
        this.qsExpansion = readonlyStateFlow4;
        this.isQsExpanded = shadeRepositoryImpl.legacyIsQsExpanded;
        this.isQsBypassingShade = shadeRepositoryImpl.legacyExpandImmediate;
        this.isQsFullscreen = shadeRepositoryImpl.legacyQsFullscreen;
        this.anyExpansion = FlowKt.stateIn(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(stateIn, readonlyStateFlow4, new ShadeInteractorKt$createAnyExpansionFlow$1(3, null)), coroutineScope, startedEagerly, Float.valueOf(0.0f));
        this.isAnyExpanded = FlowKt.stateIn(shadeRepositoryImpl.legacyExpandedOrAwaitingInputTransfer, coroutineScope, startedEagerly, Boolean.FALSE);
        this.isUserInteractingWithShade = new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(new SafeFlow(new ShadeInteractorLegacyImpl$userInteractingFlow$1(shadeRepositoryImpl.legacyShadeTracking, readonlyStateFlow3, null)), shadeRepositoryImpl.legacyLockscreenShadeTracking, new ShadeInteractorLegacyImpl$isUserInteractingWithShade$1(3, null));
        this.isUserInteractingWithQs = new SafeFlow(new ShadeInteractorLegacyImpl$userInteractingFlow$1(shadeRepositoryImpl.legacyQsTracking, readonlyStateFlow4, null));
    }

    @Override // com.android.systemui.shade.domain.interactor.BaseShadeInteractor
    public final void expandNotificationShade(String str) {
        throw new UnsupportedOperationException("expandNotificationShade() is not supported in legacy shade");
    }

    @Override // com.android.systemui.shade.domain.interactor.BaseShadeInteractor
    public final void expandQuickSettingsShade() {
        throw new UnsupportedOperationException("expandQuickSettingsShade() is not supported in legacy shade");
    }

    @Override // com.android.systemui.shade.domain.interactor.BaseShadeInteractor
    public final StateFlow getAnyExpansion() {
        return this.anyExpansion;
    }

    @Override // com.android.systemui.shade.domain.interactor.BaseShadeInteractor
    public final StateFlow getQsExpansion() {
        return this.qsExpansion;
    }

    @Override // com.android.systemui.shade.domain.interactor.BaseShadeInteractor
    public final StateFlow getShadeExpansion() {
        return this.shadeExpansion;
    }

    @Override // com.android.systemui.shade.domain.interactor.BaseShadeInteractor
    public final StateFlow isAnyExpanded() {
        return this.isAnyExpanded;
    }

    @Override // com.android.systemui.shade.domain.interactor.BaseShadeInteractor
    public final Flow isQsBypassingShade() {
        return this.isQsBypassingShade;
    }

    @Override // com.android.systemui.shade.domain.interactor.BaseShadeInteractor
    public final StateFlow isQsExpanded() {
        return this.isQsExpanded;
    }

    @Override // com.android.systemui.shade.domain.interactor.BaseShadeInteractor
    public final Flow isQsFullscreen() {
        return this.isQsFullscreen;
    }

    @Override // com.android.systemui.shade.domain.interactor.BaseShadeInteractor
    public final Flow isUserInteractingWithQs() {
        return this.isUserInteractingWithQs;
    }

    @Override // com.android.systemui.shade.domain.interactor.BaseShadeInteractor
    public final Flow isUserInteractingWithShade() {
        return this.isUserInteractingWithShade;
    }
}

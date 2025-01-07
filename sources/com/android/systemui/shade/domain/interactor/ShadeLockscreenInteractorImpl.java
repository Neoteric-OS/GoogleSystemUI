package com.android.systemui.shade.domain.interactor;

import com.android.compose.animation.scene.OverlayKey;
import com.android.keyguard.LockIconViewController;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.scene.domain.interactor.SceneInteractor;
import com.android.systemui.scene.shared.logger.SceneLogger;
import com.android.systemui.scene.shared.model.Overlays;
import com.android.systemui.scene.shared.model.Scenes;
import com.android.systemui.shade.shared.model.ShadeMode;
import java.util.Set;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ShadeLockscreenInteractorImpl implements ShadeLockscreenInteractor {
    public final CoroutineScope backgroundScope;
    public final LockIconViewController lockIconViewController;
    public final CoroutineDispatcher mainDispatcher;
    public final SceneInteractor sceneInteractor;
    public final ShadeInteractor shadeInteractor;

    public ShadeLockscreenInteractorImpl(CoroutineDispatcher coroutineDispatcher, CoroutineScope coroutineScope, ShadeInteractor shadeInteractor, SceneInteractor sceneInteractor, LockIconViewController lockIconViewController) {
        this.mainDispatcher = coroutineDispatcher;
        this.backgroundScope = coroutineScope;
        this.shadeInteractor = shadeInteractor;
        this.sceneInteractor = sceneInteractor;
        this.lockIconViewController = lockIconViewController;
    }

    @Override // com.android.systemui.shade.domain.interactor.ShadeLockscreenInteractor
    public final void dozeTimeTick() {
        this.lockIconViewController.dozeTimeTick();
    }

    @Override // com.android.systemui.shade.domain.interactor.ShadeLockscreenInteractor
    public final void expandToNotifications() {
        ((ShadeInteractorImpl) this.shadeInteractor).expandNotificationShade("ShadeLockscreenInteractorImpl.expandToNotifications");
    }

    @Override // com.android.systemui.shade.domain.interactor.ShadeLockscreenInteractor
    public final boolean isExpanded() {
        return ((Boolean) ((ShadeInteractorImpl) this.shadeInteractor).baseShadeInteractor.isAnyExpanded().getValue()).booleanValue();
    }

    @Override // com.android.systemui.shade.domain.interactor.ShadeLockscreenInteractor
    public final void resetViews(boolean z) {
        ShadeInteractorImpl shadeInteractorImpl = (ShadeInteractorImpl) this.shadeInteractor;
        if (!Intrinsics.areEqual(shadeInteractorImpl.$$delegate_1.getShadeMode().getValue(), ShadeMode.Dual.INSTANCE)) {
            shadeInteractorImpl.expandNotificationShade("ShadeLockscreenInteractorImpl.resetViews");
            return;
        }
        OverlayKey overlayKey = Overlays.QuickSettingsShade;
        SceneInteractor sceneInteractor = this.sceneInteractor;
        sceneInteractor.getClass();
        if (overlayKey == null) {
            throw new IllegalStateException(("No overlay key provided for requested change. Current transition state is " + ((StateFlowImpl) sceneInteractor.transitionState.$$delegate_0).getValue() + ". Logging reason for overlay change was: ShadeLockscreenInteractorImpl.resetViews").toString());
        }
        if ((overlayKey == null || ((Set) sceneInteractor.currentOverlays.getValue()).contains(overlayKey)) && !Intrinsics.areEqual(overlayKey, (Object) null)) {
            SceneLogger.logOverlayChangeRequested$default(sceneInteractor.logger, overlayKey, null, "ShadeLockscreenInteractorImpl.resetViews", 2);
            sceneInteractor.repository.dataSource.hideOverlay(overlayKey);
        }
    }

    @Override // com.android.systemui.shade.domain.interactor.ShadeLockscreenInteractor
    public final void showAodUi() {
        SceneInteractor.changeScene$default(this.sceneInteractor, Scenes.Lockscreen, "showAodUi", null, KeyguardState.AOD, 4);
    }

    @Override // com.android.systemui.shade.domain.interactor.ShadeLockscreenInteractor
    public final void transitionToExpandedShade(long j) {
        BuildersKt.launch$default(this.backgroundScope, null, null, new ShadeLockscreenInteractorImpl$transitionToExpandedShade$1(j, this, null), 3);
    }

    @Override // com.android.systemui.shade.domain.interactor.ShadeLockscreenInteractor
    public final void blockExpansionForCurrentTouch() {
    }

    @Override // com.android.systemui.shade.domain.interactor.ShadeLockscreenInteractor
    public final void resetViewGroupFade() {
    }

    @Override // com.android.systemui.shade.domain.interactor.ShadeLockscreenInteractor
    public final void setKeyguardStatusBarAlpha(float f) {
    }

    @Override // com.android.systemui.shade.domain.interactor.ShadeLockscreenInteractor
    public final void setOverStretchAmount(float f) {
    }

    @Override // com.android.systemui.shade.domain.interactor.ShadeLockscreenInteractor
    public final void setPulsing(boolean z) {
    }

    @Override // com.android.systemui.shade.domain.interactor.ShadeLockscreenInteractor
    public final void startBouncerPreHideAnimation() {
    }

    @Override // com.android.systemui.shade.domain.interactor.ShadeLockscreenInteractor
    public final void setKeyguardTransitionProgress(int i, float f) {
    }
}

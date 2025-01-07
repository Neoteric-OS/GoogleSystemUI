package com.android.systemui.shade;

import android.view.MotionEvent;
import com.android.systemui.shade.domain.interactor.PanelExpansionInteractor;
import com.android.systemui.shade.domain.interactor.ShadeBackActionInteractor;
import com.android.systemui.shade.domain.interactor.ShadeLockscreenInteractor;
import com.android.systemui.statusbar.phone.CentralSurfacesImpl;
import com.android.systemui.statusbar.phone.CentralSurfacesImpl$$ExternalSyntheticLambda1;
import com.android.systemui.statusbar.phone.CentralSurfacesImpl$$ExternalSyntheticLambda25;
import com.android.systemui.statusbar.policy.BrightnessMirrorController$$ExternalSyntheticLambda0;
import com.android.systemui.statusbar.policy.HeadsUpManager;
import kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ShadeSurfaceImpl implements ShadeSurface, ShadeViewController, ShadeBackActionInteractor, ShadeLockscreenInteractor, PanelExpansionInteractor {
    public final FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2 legacyPanelExpansion;
    public final ShadeHeadsUpTrackerEmptyImpl shadeHeadsUpTracker = new ShadeHeadsUpTrackerEmptyImpl();
    public final ShadeFoldAnimatorEmptyImpl shadeFoldAnimator = new ShadeFoldAnimatorEmptyImpl();

    public ShadeSurfaceImpl() {
        new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(Float.valueOf(0.0f));
        StateFlowKt.MutableStateFlow(Float.valueOf(0.0f));
    }

    @Override // com.android.systemui.shade.domain.interactor.ShadeBackActionInteractor
    public final boolean canBeCollapsed() {
        return false;
    }

    @Override // com.android.systemui.shade.domain.interactor.ShadeBackActionInteractor
    public final boolean closeUserSwitcherIfOpen() {
        return false;
    }

    @Override // com.android.systemui.shade.domain.interactor.PanelExpansionInteractor
    public final int getBarState() {
        return 0;
    }

    @Override // com.android.systemui.shade.ShadeViewController
    public final ShadeFoldAnimator getShadeFoldAnimator$1() {
        return this.shadeFoldAnimator;
    }

    @Override // com.android.systemui.shade.ShadeViewController
    public final ShadeHeadsUpTracker getShadeHeadsUpTracker$1() {
        return this.shadeHeadsUpTracker;
    }

    @Override // com.android.systemui.shade.ShadeViewController
    public final boolean handleExternalInterceptTouch(MotionEvent motionEvent) {
        return false;
    }

    @Override // com.android.systemui.shade.ShadeViewController
    public final boolean handleExternalTouch(MotionEvent motionEvent) {
        return false;
    }

    @Override // com.android.systemui.shade.domain.interactor.PanelExpansionInteractor
    public final boolean isCollapsing() {
        return false;
    }

    @Override // com.android.systemui.shade.domain.interactor.ShadeLockscreenInteractor
    public final boolean isExpanded() {
        return false;
    }

    @Override // com.android.systemui.shade.domain.interactor.PanelExpansionInteractor
    public final boolean isFullyCollapsed() {
        return false;
    }

    @Override // com.android.systemui.shade.domain.interactor.PanelExpansionInteractor
    public final boolean isFullyExpanded() {
        return false;
    }

    @Override // com.android.systemui.shade.domain.interactor.PanelExpansionInteractor
    public final boolean isPanelExpanded() {
        return false;
    }

    @Override // com.android.systemui.shade.domain.interactor.PanelExpansionInteractor
    public final boolean isTracking() {
        return false;
    }

    @Override // com.android.systemui.shade.ShadeViewController
    public final boolean isViewEnabled() {
        return false;
    }

    @Override // com.android.systemui.shade.domain.interactor.PanelExpansionInteractor
    public final boolean shouldHideStatusBarIconsWhenExpanded() {
        return false;
    }

    @Override // com.android.systemui.shade.domain.interactor.ShadeBackActionInteractor
    public final void animateCollapseQs(boolean z) {
    }

    @Override // com.android.systemui.shade.domain.interactor.ShadeLockscreenInteractor
    public final void blockExpansionForCurrentTouch() {
    }

    @Override // com.android.systemui.shade.ShadeSurface
    public final void cancelAnimation() {
    }

    @Override // com.android.systemui.shade.ShadeViewController
    public final void cancelInputFocusTransfer() {
    }

    @Override // com.android.systemui.shade.ShadeSurface
    public final void cancelPendingCollapse() {
    }

    @Override // com.android.systemui.shade.domain.interactor.ShadeLockscreenInteractor
    public final void dozeTimeTick() {
    }

    @Override // com.android.systemui.shade.domain.interactor.ShadeLockscreenInteractor
    public final void expandToNotifications() {
    }

    @Override // com.android.systemui.shade.ShadeSurface
    public final void fadeOut(CentralSurfacesImpl$$ExternalSyntheticLambda1 centralSurfacesImpl$$ExternalSyntheticLambda1) {
    }

    @Override // com.android.systemui.shade.ShadeViewController
    public final void finishInputFocusTransfer(float f) {
    }

    @Override // com.android.systemui.shade.domain.interactor.ShadeBackActionInteractor
    public final void onBackPressed() {
    }

    @Override // com.android.systemui.shade.ShadeSurface
    public final void onThemeChanged() {
    }

    @Override // com.android.systemui.shade.ShadeSurface
    public final void resetAlpha() {
    }

    @Override // com.android.systemui.shade.ShadeSurface
    public final void resetTranslation() {
    }

    @Override // com.android.systemui.shade.domain.interactor.ShadeLockscreenInteractor
    public final void resetViewGroupFade() {
    }

    @Override // com.android.systemui.shade.domain.interactor.ShadeLockscreenInteractor
    public final void resetViews(boolean z) {
    }

    @Override // com.android.systemui.shade.ShadeViewController
    public final void setAlphaChangeAnimationEndAction(BrightnessMirrorController$$ExternalSyntheticLambda0 brightnessMirrorController$$ExternalSyntheticLambda0) {
    }

    @Override // com.android.systemui.shade.ShadeSurface
    public final void setBouncerShowing(boolean z) {
    }

    @Override // com.android.systemui.shade.ShadeSurface
    public final void setImportantForAccessibility(int i) {
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

    @Override // com.android.systemui.shade.ShadeViewController
    public final void setQsScrimEnabled(boolean z) {
    }

    @Override // com.android.systemui.shade.ShadeSurface
    public final void setTouchAndAnimationDisabled(boolean z) {
    }

    @Override // com.android.systemui.shade.ShadeSurface
    public final void setWillPlayDelayedDozeAmountAnimation() {
    }

    @Override // com.android.systemui.shade.domain.interactor.ShadeLockscreenInteractor
    public final void showAodUi() {
    }

    @Override // com.android.systemui.shade.domain.interactor.ShadeLockscreenInteractor
    public final void startBouncerPreHideAnimation() {
    }

    @Override // com.android.systemui.shade.ShadeViewController
    public final void startExpandLatencyTracking() {
    }

    @Override // com.android.systemui.shade.ShadeViewController
    public final void startInputFocusTransfer() {
    }

    @Override // com.android.systemui.shade.domain.interactor.ShadeLockscreenInteractor
    public final void transitionToExpandedShade(long j) {
    }

    @Override // com.android.systemui.shade.ShadeSurface
    public final void updateExpansionAndVisibility() {
    }

    @Override // com.android.systemui.shade.ShadeSurface
    public final void updateResources() {
    }

    @Override // com.android.systemui.shade.ShadeViewController
    public final void updateSystemUiStateFlags() {
    }

    @Override // com.android.systemui.shade.ShadeViewController
    public final void updateTouchableRegion() {
    }

    @Override // com.android.systemui.shade.ShadeViewController
    public final void setAlpha(int i, boolean z) {
    }

    @Override // com.android.systemui.shade.ShadeViewController
    public final void setAmbientIndicationTop(int i, boolean z) {
    }

    @Override // com.android.systemui.shade.ShadeSurface
    public final void setDozing(boolean z, boolean z2) {
    }

    @Override // com.android.systemui.shade.domain.interactor.ShadeLockscreenInteractor
    public final void setKeyguardTransitionProgress(int i, float f) {
    }

    @Override // com.android.systemui.shade.ShadeSurface
    public final void initDependencies(CentralSurfacesImpl centralSurfacesImpl, CentralSurfacesImpl$$ExternalSyntheticLambda25 centralSurfacesImpl$$ExternalSyntheticLambda25, HeadsUpManager headsUpManager) {
    }
}

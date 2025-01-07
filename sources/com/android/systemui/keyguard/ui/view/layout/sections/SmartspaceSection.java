package com.android.systemui.keyguard.ui.view.layout.sections;

import android.app.smartspace.SmartspaceSession;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import com.android.systemui.keyguard.KeyguardUnlockAnimationController;
import com.android.systemui.keyguard.domain.interactor.KeyguardBlueprintInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardSmartspaceInteractor;
import com.android.systemui.keyguard.shared.model.KeyguardSection;
import com.android.systemui.keyguard.ui.binder.KeyguardSmartspaceViewBinder;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardClockViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardSmartspaceViewModel;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt$repeatWhenAttached$1;
import com.android.systemui.statusbar.lockscreen.LockscreenSmartspaceController;
import com.android.wm.shell.R;
import dagger.Lazy;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SmartspaceSection extends KeyguardSection {
    public final Lazy blueprintInteractor;
    public final Context context;
    public ViewGroup dateWeatherView;
    public RepeatWhenAttachedKt$repeatWhenAttached$1 disposableHandle;
    public final KeyguardClockViewModel keyguardClockViewModel;
    public final KeyguardSmartspaceInteractor keyguardSmartspaceInteractor;
    public final KeyguardSmartspaceViewModel keyguardSmartspaceViewModel;
    public final KeyguardUnlockAnimationController keyguardUnlockAnimationController;
    public int pastVisibility = -1;
    public final LockscreenSmartspaceController smartspaceController;
    public View smartspaceView;
    public SmartspaceSection$addViews$1 smartspaceVisibilityListener;
    public View weatherView;

    public SmartspaceSection(Context context, KeyguardClockViewModel keyguardClockViewModel, KeyguardSmartspaceViewModel keyguardSmartspaceViewModel, KeyguardSmartspaceInteractor keyguardSmartspaceInteractor, LockscreenSmartspaceController lockscreenSmartspaceController, KeyguardUnlockAnimationController keyguardUnlockAnimationController, Lazy lazy) {
        this.context = context;
        this.keyguardClockViewModel = keyguardClockViewModel;
        this.keyguardSmartspaceViewModel = keyguardSmartspaceViewModel;
        this.keyguardSmartspaceInteractor = keyguardSmartspaceInteractor;
        this.smartspaceController = lockscreenSmartspaceController;
        this.keyguardUnlockAnimationController = keyguardUnlockAnimationController;
        this.blueprintInteractor = lazy;
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [com.android.systemui.keyguard.ui.view.layout.sections.SmartspaceSection$addViews$1] */
    @Override // com.android.systemui.keyguard.shared.model.KeyguardSection
    public final void addViews(ConstraintLayout constraintLayout) {
        ViewTreeObserver viewTreeObserver;
        KeyguardSmartspaceViewModel keyguardSmartspaceViewModel = this.keyguardSmartspaceViewModel;
        if (keyguardSmartspaceViewModel.isSmartspaceEnabled) {
            LockscreenSmartspaceController lockscreenSmartspaceController = this.smartspaceController;
            lockscreenSmartspaceController.execution.assertIsMainThread();
            if (!lockscreenSmartspaceController.isEnabled) {
                throw new RuntimeException("Cannot build view when not enabled");
            }
            View buildView = lockscreenSmartspaceController.buildView("general_view", constraintLayout, lockscreenSmartspaceController.plugin, lockscreenSmartspaceController.configPlugin);
            lockscreenSmartspaceController.connectSession();
            this.smartspaceView = buildView;
            lockscreenSmartspaceController.execution.assertIsMainThread();
            if (!lockscreenSmartspaceController.isEnabled) {
                throw new RuntimeException("Cannot build view when not enabled");
            }
            if (!lockscreenSmartspaceController.isDateWeatherDecoupled) {
                throw new RuntimeException("Cannot build weather view when not decoupled");
            }
            View buildView2 = lockscreenSmartspaceController.buildView("weather_view", constraintLayout, lockscreenSmartspaceController.weatherPlugin, null);
            lockscreenSmartspaceController.connectSession();
            this.weatherView = buildView2;
            this.dateWeatherView = (ViewGroup) lockscreenSmartspaceController.buildAndConnectDateView(constraintLayout);
            View view = this.smartspaceView;
            this.pastVisibility = view != null ? view.getVisibility() : 8;
            constraintLayout.addView(this.smartspaceView);
            if (keyguardSmartspaceViewModel.isDateWeatherDecoupled) {
                constraintLayout.addView(this.dateWeatherView);
                ViewGroup viewGroup = this.dateWeatherView;
                int i = 0;
                if (viewGroup != null && viewGroup.getChildCount() == 0) {
                    i = 1;
                }
                int i2 = i ^ 1;
                ViewGroup viewGroup2 = this.dateWeatherView;
                if (viewGroup2 != null) {
                    viewGroup2.addView(this.weatherView, i2);
                }
            }
            View view2 = this.smartspaceView;
            this.keyguardUnlockAnimationController.lockscreenSmartspace = view2;
            this.smartspaceVisibilityListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.android.systemui.keyguard.ui.view.layout.sections.SmartspaceSection$addViews$1
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public final void onGlobalLayout() {
                    int visibility;
                    SmartspaceSection smartspaceSection = SmartspaceSection.this;
                    View view3 = smartspaceSection.smartspaceView;
                    if (view3 == null || smartspaceSection.pastVisibility == (visibility = view3.getVisibility())) {
                        return;
                    }
                    StateFlowImpl stateFlowImpl = smartspaceSection.keyguardSmartspaceInteractor.keyguardSmartspaceRepository._bcSmartspaceVisibility;
                    Integer valueOf = Integer.valueOf(visibility);
                    stateFlowImpl.getClass();
                    stateFlowImpl.updateState(null, valueOf);
                    smartspaceSection.pastVisibility = visibility;
                }
            };
            if (view2 == null || (viewTreeObserver = view2.getViewTreeObserver()) == null) {
                return;
            }
            viewTreeObserver.addOnGlobalLayoutListener(this.smartspaceVisibilityListener);
        }
    }

    @Override // com.android.systemui.keyguard.shared.model.KeyguardSection
    public final void applyConstraints(ConstraintSet constraintSet) {
        int i;
        KeyguardSmartspaceViewModel keyguardSmartspaceViewModel = this.keyguardSmartspaceViewModel;
        if (keyguardSmartspaceViewModel.isSmartspaceEnabled) {
            Context context = this.context;
            int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.status_view_margin_horizontal) + context.getResources().getDimensionPixelSize(R.dimen.below_clock_padding_start);
            Context context2 = this.context;
            int dimensionPixelSize2 = context2.getResources().getDimensionPixelSize(R.dimen.status_view_margin_horizontal) + context2.getResources().getDimensionPixelSize(R.dimen.below_clock_padding_end);
            constraintSet.constrainHeight(R.id.date_smartspace_view, -2);
            constraintSet.constrainWidth(R.id.date_smartspace_view, -2);
            constraintSet.connect(R.id.date_smartspace_view, 6, 0, 6, dimensionPixelSize);
            constraintSet.constrainHeight(R.id.bc_smartspace_view, -2);
            constraintSet.constrainWidth(R.id.bc_smartspace_view, 0);
            constraintSet.connect(R.id.bc_smartspace_view, 6, 0, 6, dimensionPixelSize);
            KeyguardClockViewModel keyguardClockViewModel = this.keyguardClockViewModel;
            constraintSet.connect(R.id.bc_smartspace_view, 7, ((Boolean) ((StateFlowImpl) keyguardClockViewModel.clockShouldBeCentered.$$delegate_0).getValue()).booleanValue() ? 0 : R.id.split_shade_guideline, 7, dimensionPixelSize2);
            ReadonlyStateFlow readonlyStateFlow = keyguardClockViewModel.hasCustomWeatherDataDisplay;
            if (((Boolean) ((StateFlowImpl) readonlyStateFlow.$$delegate_0).getValue()).booleanValue()) {
                constraintSet.clear(R.id.date_smartspace_view, 3);
                constraintSet.connect(R.id.date_smartspace_view, 4, R.id.bc_smartspace_view, 3);
            } else {
                constraintSet.clear(R.id.date_smartspace_view, 4);
                constraintSet.connect(R.id.date_smartspace_view, 3, R.id.lockscreen_clock_view, 4);
                constraintSet.connect(R.id.bc_smartspace_view, 3, R.id.date_smartspace_view, 4);
            }
            constraintSet.createBarrier(R.id.smart_space_barrier_bottom, 3, 0, R.id.bc_smartspace_view, R.id.date_smartspace_view);
            SmartspaceSession smartspaceSession = this.smartspaceController.session;
            if (smartspaceSession != null) {
                smartspaceSession.requestSmartspaceUpdate();
            }
            boolean booleanValue = ((Boolean) ((StateFlowImpl) keyguardSmartspaceViewModel.isWeatherVisible.$$delegate_0).getValue()).booleanValue();
            if (booleanValue) {
                i = 0;
            } else {
                if (booleanValue) {
                    throw new NoWhenBranchMatchedException();
                }
                i = 8;
            }
            constraintSet.setVisibility(R.id.weather_smartspace_view, i);
            constraintSet.setAlpha(R.id.weather_smartspace_view, i == 0 ? 1.0f : 0.0f);
            int i2 = ((Boolean) ((StateFlowImpl) readonlyStateFlow.$$delegate_0).getValue()).booleanValue() ? 8 : 0;
            constraintSet.setVisibility(R.id.date_smartspace_view, i2);
            constraintSet.setAlpha(R.id.date_smartspace_view, i2 == 0 ? 1.0f : 0.0f);
        }
    }

    @Override // com.android.systemui.keyguard.shared.model.KeyguardSection
    public final void bindData(ConstraintLayout constraintLayout) {
        KeyguardSmartspaceViewModel keyguardSmartspaceViewModel = this.keyguardSmartspaceViewModel;
        if (keyguardSmartspaceViewModel.isSmartspaceEnabled) {
            RepeatWhenAttachedKt$repeatWhenAttached$1 repeatWhenAttachedKt$repeatWhenAttached$1 = this.disposableHandle;
            if (repeatWhenAttachedKt$repeatWhenAttached$1 != null) {
                repeatWhenAttachedKt$repeatWhenAttached$1.dispose();
            }
            this.disposableHandle = KeyguardSmartspaceViewBinder.bind(constraintLayout, this.keyguardClockViewModel, keyguardSmartspaceViewModel, (KeyguardBlueprintInteractor) this.blueprintInteractor.get());
        }
    }

    @Override // com.android.systemui.keyguard.shared.model.KeyguardSection
    public final void onRebuildBegin() {
        LockscreenSmartspaceController lockscreenSmartspaceController = this.smartspaceController;
        lockscreenSmartspaceController.suppressDisconnects = true;
        lockscreenSmartspaceController.disconnect();
    }

    @Override // com.android.systemui.keyguard.shared.model.KeyguardSection
    public final void onRebuildEnd() {
        LockscreenSmartspaceController lockscreenSmartspaceController = this.smartspaceController;
        lockscreenSmartspaceController.suppressDisconnects = false;
        lockscreenSmartspaceController.disconnect();
    }

    @Override // com.android.systemui.keyguard.shared.model.KeyguardSection
    public final void removeViews(ConstraintLayout constraintLayout) {
        ViewTreeObserver viewTreeObserver;
        if (this.keyguardSmartspaceViewModel.isSmartspaceEnabled) {
            for (View view : CollectionsKt__CollectionsKt.listOf(this.smartspaceView, this.dateWeatherView)) {
                if (view != null && Intrinsics.areEqual(view.getParent(), constraintLayout)) {
                    constraintLayout.removeView(view);
                }
            }
            View view2 = this.smartspaceView;
            if (view2 != null && (viewTreeObserver = view2.getViewTreeObserver()) != null) {
                viewTreeObserver.removeOnGlobalLayoutListener(this.smartspaceVisibilityListener);
            }
            this.smartspaceVisibilityListener = null;
            RepeatWhenAttachedKt$repeatWhenAttached$1 repeatWhenAttachedKt$repeatWhenAttached$1 = this.disposableHandle;
            if (repeatWhenAttachedKt$repeatWhenAttached$1 != null) {
                repeatWhenAttachedKt$repeatWhenAttached$1.dispose();
            }
        }
    }
}

package com.android.systemui.keyguard.ui.view.layout.sections;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import com.android.internal.policy.SystemBarUtils;
import com.android.systemui.keyguard.domain.interactor.KeyguardBlueprintInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardClockInteractor;
import com.android.systemui.keyguard.shared.model.BurnInModel;
import com.android.systemui.keyguard.shared.model.KeyguardSection;
import com.android.systemui.keyguard.ui.binder.KeyguardClockViewBinder;
import com.android.systemui.keyguard.ui.viewmodel.AodBurnInViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardClockViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardRootViewModel;
import com.android.systemui.plugins.clocks.ClockController;
import com.android.systemui.plugins.clocks.ClockFaceLayout;
import com.android.systemui.util.kotlin.DisposableHandles;
import com.android.systemui.util.ui.AnimatedValue;
import com.android.wm.shell.R;
import dagger.Lazy;
import java.util.Iterator;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ClockSection extends KeyguardSection {
    public final AodBurnInViewModel aodBurnInViewModel;
    public final Lazy blueprintInteractor;
    public final KeyguardClockInteractor clockInteractor;
    public final Context context;
    public DisposableHandles disposableHandle;
    public final KeyguardClockViewModel keyguardClockViewModel;
    public final KeyguardRootViewModel rootViewModel;

    public ClockSection(KeyguardClockInteractor keyguardClockInteractor, KeyguardClockViewModel keyguardClockViewModel, Context context, Lazy lazy, KeyguardRootViewModel keyguardRootViewModel, AodBurnInViewModel aodBurnInViewModel) {
        this.clockInteractor = keyguardClockInteractor;
        this.keyguardClockViewModel = keyguardClockViewModel;
        this.context = context;
        this.blueprintInteractor = lazy;
        this.rootViewModel = keyguardRootViewModel;
        this.aodBurnInViewModel = aodBurnInViewModel;
    }

    @Override // com.android.systemui.keyguard.shared.model.KeyguardSection
    public final void applyConstraints(ConstraintSet constraintSet) {
        Object obj;
        KeyguardClockViewModel keyguardClockViewModel = this.keyguardClockViewModel;
        ClockController clockController = (ClockController) ((StateFlowImpl) keyguardClockViewModel.currentClock.$$delegate_0).getValue();
        if (clockController != null) {
            int i = ((Boolean) ((StateFlowImpl) keyguardClockViewModel.clockShouldBeCentered.$$delegate_0).getValue()).booleanValue() ? 0 : R.id.split_shade_guideline;
            constraintSet.connect(R.id.lockscreen_clock_view_large, 6, 0, 6);
            constraintSet.connect(R.id.lockscreen_clock_view_large, 7, i, 7);
            constraintSet.connect(R.id.lockscreen_clock_view_large, 4, R.id.device_entry_icon_view, 3);
            int dimensionPixelSize = keyguardClockViewModel.resources.getDimensionPixelSize(R.dimen.keyguard_smartspace_top_offset) + keyguardClockViewModel.resources.getDimensionPixelSize(R.dimen.small_clock_padding_top) + SystemBarUtils.getStatusBarHeight(keyguardClockViewModel.systemBarUtils.context);
            Context context = this.context;
            Resources resourcesForApplication = context.getPackageManager().getResourcesForApplication(context.getPackageName());
            int identifier = resourcesForApplication.getIdentifier("date_weather_view_height", "dimen", context.getPackageName());
            int dimensionPixelSize2 = (identifier == 0 ? 0 : resourcesForApplication.getDimensionPixelSize(identifier)) + dimensionPixelSize;
            Context context2 = this.context;
            Resources resourcesForApplication2 = context2.getPackageManager().getResourcesForApplication(context2.getPackageName());
            int identifier2 = resourcesForApplication2.getIdentifier("enhanced_smartspace_height", "dimen", context2.getPackageName());
            constraintSet.connect(R.id.lockscreen_clock_view_large, 3, 0, 3, (identifier2 == 0 ? 0 : resourcesForApplication2.getDimensionPixelSize(identifier2)) + dimensionPixelSize2);
            constraintSet.constrainWidth(R.id.lockscreen_clock_view_large, -2);
            constraintSet.constrainHeight(R.id.lockscreen_clock_view_large, -2);
            constraintSet.get(R.id.lockscreen_clock_view_large).layout.heightMax = 0;
            constraintSet.constrainWidth(R.id.lockscreen_clock_view, -2);
            constraintSet.constrainHeight(R.id.lockscreen_clock_view, this.context.getResources().getDimensionPixelSize(R.dimen.small_clock_height));
            constraintSet.connect(R.id.lockscreen_clock_view, 6, 0, 6, this.context.getResources().getDimensionPixelSize(R.dimen.status_view_margin_horizontal) + this.context.getResources().getDimensionPixelSize(R.dimen.clock_padding_start));
            int smallClockTopMargin = keyguardClockViewModel.getSmallClockTopMargin();
            ConstraintSet.Layout layout = constraintSet.get(R.id.small_clock_guideline_top).layout;
            layout.mIsGuideline = true;
            layout.orientation = 0;
            constraintSet.setGuidelineBegin(R.id.small_clock_guideline_top, smallClockTopMargin);
            constraintSet.connect(R.id.lockscreen_clock_view, 3, R.id.small_clock_guideline_top, 4);
            ConstraintSet.Transform transform = constraintSet.get(R.id.lockscreen_clock_view_large).transform;
            transform.transformPivotY = Float.NaN;
            transform.transformPivotX = Float.NaN;
            Context context3 = this.context;
            Resources resourcesForApplication3 = context3.getPackageManager().getResourcesForApplication(context3.getPackageName());
            int identifier3 = resourcesForApplication3.getIdentifier("enhanced_smartspace_height", "dimen", context3.getPackageName());
            constraintSet.createBarrier(R.id.weather_clock_bc_smartspace_bottom, 3, identifier3 == 0 ? 0 : resourcesForApplication3.getDimensionPixelSize(identifier3), R.id.weather_clock_time);
            AnimatedValue animatedValue = (AnimatedValue) ((StateFlowImpl) this.rootViewModel.isNotifIconContainerVisible.$$delegate_0).getValue();
            if (animatedValue instanceof AnimatedValue.Animating) {
                obj = ((AnimatedValue.Animating) animatedValue).value;
            } else {
                if (!(animatedValue instanceof AnimatedValue.NotAnimating)) {
                    throw new NoWhenBranchMatchedException();
                }
                obj = ((AnimatedValue.NotAnimating) animatedValue).value;
            }
            if (((Boolean) obj).booleanValue() && ((Boolean) ((StateFlowImpl) keyguardClockViewModel.hasAodIcons.$$delegate_0).getValue()).booleanValue()) {
                constraintSet.createBarrier(R.id.weather_clock_date_and_icons_barrier_bottom, 3, 0, R.id.aod_notification_icon_container, R.id.weather_clock_bc_smartspace_bottom);
            } else {
                constraintSet.createBarrier(R.id.weather_clock_date_and_icons_barrier_bottom, 3, 0, R.id.weather_clock_bc_smartspace_bottom);
            }
            getNonTargetClockFace(clockController).applyConstraints(constraintSet);
            getTargetClockFace(clockController).applyConstraints(constraintSet);
            ClockSectionKt.setVisibility(constraintSet, getTargetClockFace(clockController).getViews(), 0);
            ClockSectionKt.setVisibility(constraintSet, getNonTargetClockFace(clockController).getViews(), 8);
            Iterator it = getTargetClockFace(clockController).getViews().iterator();
            while (it.hasNext()) {
                constraintSet.setAlpha(((View) it.next()).getId(), 1.0f);
            }
            Iterator it2 = getNonTargetClockFace(clockController).getViews().iterator();
            while (it2.hasNext()) {
                constraintSet.setAlpha(((View) it2.next()).getId(), 0.0f);
            }
            if (((Boolean) ((StateFlowImpl) keyguardClockViewModel.isLargeClockVisible.$$delegate_0).getValue()).booleanValue()) {
                List views = getTargetClockFace(clockController).getViews();
                AodBurnInViewModel aodBurnInViewModel = this.aodBurnInViewModel;
                float f = ((BurnInModel) ((StateFlowImpl) aodBurnInViewModel.movement.$$delegate_0).getValue()).scale;
                Iterator it3 = views.iterator();
                while (it3.hasNext()) {
                    constraintSet.get(((View) it3.next()).getId()).transform.scaleX = f;
                }
                List views2 = getTargetClockFace(clockController).getViews();
                float f2 = ((BurnInModel) ((StateFlowImpl) aodBurnInViewModel.movement.$$delegate_0).getValue()).scale;
                Iterator it4 = views2.iterator();
                while (it4.hasNext()) {
                    constraintSet.get(((View) it4.next()).getId()).transform.scaleY = f2;
                }
            } else {
                constraintSet.connect(R.id.bc_smartspace_view, 3, R.id.date_smartspace_view, 4);
            }
            constraintSet.applyDeltaFrom(constraintSet);
        }
    }

    @Override // com.android.systemui.keyguard.shared.model.KeyguardSection
    public final void bindData(ConstraintLayout constraintLayout) {
        DisposableHandles disposableHandles = this.disposableHandle;
        if (disposableHandles != null) {
            disposableHandles.dispose();
        }
        this.disposableHandle = KeyguardClockViewBinder.bind(this, constraintLayout, this.keyguardClockViewModel, this.clockInteractor, (KeyguardBlueprintInteractor) this.blueprintInteractor.get(), this.rootViewModel, this.aodBurnInViewModel);
    }

    public final ClockFaceLayout getNonTargetClockFace(ClockController clockController) {
        return ((Boolean) ((StateFlowImpl) this.keyguardClockViewModel.isLargeClockVisible.$$delegate_0).getValue()).booleanValue() ? clockController.getSmallClock().getLayout() : clockController.getLargeClock().getLayout();
    }

    public final ClockFaceLayout getTargetClockFace(ClockController clockController) {
        return ((Boolean) ((StateFlowImpl) this.keyguardClockViewModel.isLargeClockVisible.$$delegate_0).getValue()).booleanValue() ? clockController.getLargeClock().getLayout() : clockController.getSmallClock().getLayout();
    }

    @Override // com.android.systemui.keyguard.shared.model.KeyguardSection
    public final void removeViews(ConstraintLayout constraintLayout) {
        DisposableHandles disposableHandles = this.disposableHandle;
        if (disposableHandles != null) {
            disposableHandles.dispose();
        }
    }

    @Override // com.android.systemui.keyguard.shared.model.KeyguardSection
    public final void addViews(ConstraintLayout constraintLayout) {
    }
}

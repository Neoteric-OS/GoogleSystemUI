package com.android.systemui.controls.ui;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.Icon;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.service.controls.Control;
import android.service.controls.actions.ControlAction;
import android.service.controls.templates.ControlTemplate;
import android.service.controls.templates.RangeTemplate;
import android.service.controls.templates.StatelessTemplate;
import android.service.controls.templates.TemperatureControlTemplate;
import android.service.controls.templates.ThumbnailTemplate;
import android.service.controls.templates.ToggleRangeTemplate;
import android.service.controls.templates.ToggleTemplate;
import android.util.Log;
import android.util.MathUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.app.animation.Interpolators;
import com.android.internal.graphics.ColorUtils;
import com.android.systemui.controls.ControlsMetricsLoggerImpl;
import com.android.systemui.controls.controller.ControlInfo;
import com.android.systemui.controls.controller.ControlsBindingControllerImpl;
import com.android.systemui.controls.controller.ControlsController;
import com.android.systemui.controls.controller.ControlsControllerImpl;
import com.android.systemui.controls.controller.ControlsProviderLifecycleManager;
import com.android.systemui.controls.ui.ControlActionCoordinatorImpl;
import com.android.systemui.controls.ui.RenderInfo;
import com.android.systemui.util.concurrency.DelayableExecutor;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ControlViewHolder {
    public final GradientDrawable baseLayer;
    public Behavior behavior;
    public final DelayableExecutor bgExecutor;
    public final CanUseIconPredicate canUseIconPredicate;
    public final ImageView chevronIcon;
    public final ClipDrawable clipLayer;
    public final Context context;
    public final ControlActionCoordinatorImpl controlActionCoordinator;
    public final ControlsController controlsController;
    public final ControlsMetricsLoggerImpl controlsMetricsLogger;
    public final int currentUserId;
    public ControlWithState cws;
    public final ImageView icon;
    public boolean isLoading;
    public ControlAction lastAction;
    public Dialog lastChallengeDialog;
    public final ViewGroup layout;
    public CharSequence nextStatusText;
    public final Function0 onDialogCancel;
    public ValueAnimator stateAnimator;
    public final TextView status;
    public Animator statusAnimator;
    public final TextView subtitle;
    public final TextView title;
    public final float toggleBackgroundIntensity;
    public final DelayableExecutor uiExecutor;
    public final int uid;
    public boolean userInteractionInProgress;
    public Dialog visibleDialog;
    public static final Set FORCE_PANEL_DEVICES = SetsKt.setOf(49, 50);
    public static final int[] ATTR_ENABLED = {R.attr.state_enabled};
    public static final int[] ATTR_DISABLED = {-16842910};

    public ControlViewHolder(ViewGroup viewGroup, ControlsController controlsController, DelayableExecutor delayableExecutor, DelayableExecutor delayableExecutor2, ControlActionCoordinatorImpl controlActionCoordinatorImpl, ControlsMetricsLoggerImpl controlsMetricsLoggerImpl, int i, int i2) {
        this.layout = viewGroup;
        this.controlsController = controlsController;
        this.uiExecutor = delayableExecutor;
        this.bgExecutor = delayableExecutor2;
        this.controlActionCoordinator = controlActionCoordinatorImpl;
        this.controlsMetricsLogger = controlsMetricsLoggerImpl;
        this.uid = i;
        this.currentUserId = i2;
        this.canUseIconPredicate = new CanUseIconPredicate(i2);
        this.toggleBackgroundIntensity = viewGroup.getContext().getResources().getFraction(com.android.wm.shell.R.fraction.controls_toggle_bg_intensity, 1, 1);
        this.icon = (ImageView) viewGroup.requireViewById(com.android.wm.shell.R.id.icon);
        TextView textView = (TextView) viewGroup.requireViewById(com.android.wm.shell.R.id.status);
        this.status = textView;
        this.nextStatusText = "";
        this.title = (TextView) viewGroup.requireViewById(com.android.wm.shell.R.id.title);
        this.subtitle = (TextView) viewGroup.requireViewById(com.android.wm.shell.R.id.subtitle);
        this.chevronIcon = (ImageView) viewGroup.requireViewById(com.android.wm.shell.R.id.chevron_icon);
        this.context = viewGroup.getContext();
        this.onDialogCancel = new Function0() { // from class: com.android.systemui.controls.ui.ControlViewHolder$onDialogCancel$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                ControlViewHolder.this.lastChallengeDialog = null;
                return Unit.INSTANCE;
            }
        };
        LayerDrawable layerDrawable = (LayerDrawable) viewGroup.getBackground();
        layerDrawable.mutate();
        this.clipLayer = (ClipDrawable) layerDrawable.findDrawableByLayerId(com.android.wm.shell.R.id.clip_layer);
        this.baseLayer = (GradientDrawable) layerDrawable.findDrawableByLayerId(com.android.wm.shell.R.id.background);
        textView.setSelected(true);
    }

    public final void action(ControlAction controlAction) {
        this.lastAction = controlAction;
        ControlWithState controlWithState = this.cws;
        ComponentName componentName = (controlWithState != null ? controlWithState : null).componentName;
        if (controlWithState == null) {
            controlWithState = null;
        }
        ControlInfo controlInfo = controlWithState.ci;
        ControlsControllerImpl controlsControllerImpl = (ControlsControllerImpl) this.controlsController;
        if (controlsControllerImpl.confirmAvailability()) {
            ControlsBindingControllerImpl controlsBindingControllerImpl = (ControlsBindingControllerImpl) controlsControllerImpl.bindingController;
            if (controlsBindingControllerImpl.statefulControlSubscriber == null) {
                Log.w("ControlsBindingControllerImpl", "No actions can occur outside of an active subscription. Ignoring.");
                return;
            }
            ControlsProviderLifecycleManager retrieveLifecycleManager = controlsBindingControllerImpl.retrieveLifecycleManager(componentName);
            retrieveLifecycleManager.getClass();
            retrieveLifecycleManager.invokeOrQueue(new ControlsProviderLifecycleManager.Action(retrieveLifecycleManager, controlInfo.controlId, controlAction, 0));
        }
    }

    public final void animateStatusChange(Function0 function0, boolean z) {
        Animator animator = this.statusAnimator;
        if (animator != null) {
            animator.cancel();
        }
        if (!z) {
            function0.invoke();
            return;
        }
        if (this.isLoading) {
            function0.invoke();
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.status, "alpha", 0.45f);
            ofFloat.setRepeatMode(2);
            ofFloat.setRepeatCount(-1);
            ofFloat.setDuration(500L);
            ofFloat.setInterpolator(Interpolators.LINEAR);
            ofFloat.setStartDelay(900L);
            ofFloat.start();
            this.statusAnimator = ofFloat;
            return;
        }
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.status, "alpha", 0.0f);
        ofFloat2.setDuration(200L);
        Interpolator interpolator = Interpolators.LINEAR;
        ofFloat2.setInterpolator(interpolator);
        ofFloat2.addListener(new ControlViewHolder$animateStatusChange$2$1(function0));
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.status, "alpha", 1.0f);
        ofFloat3.setDuration(200L);
        ofFloat3.setInterpolator(interpolator);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(ofFloat2, ofFloat3);
        animatorSet.addListener(new ControlViewHolder$animateStatusChange$2$1(this, 0));
        animatorSet.start();
        this.statusAnimator = animatorSet;
    }

    public final void applyRenderInfo$frameworks__base__packages__SystemUI__android_common__SystemUI_core(int i, final boolean z, boolean z2) {
        List listOf;
        ColorStateList color;
        ColorStateList customColor;
        int deviceType = (getControlStatus() == 1 || getControlStatus() == 0) ? getDeviceType() : -1000;
        RenderInfo.Companion companion = RenderInfo.Companion;
        Context context = this.context;
        ControlWithState controlWithState = this.cws;
        if (controlWithState == null) {
            controlWithState = null;
        }
        final RenderInfo lookup = RenderInfo.Companion.lookup(context, controlWithState.componentName, deviceType, i);
        final ColorStateList colorStateList = this.context.getResources().getColorStateList(lookup.foreground, this.context.getTheme());
        final CharSequence charSequence = this.nextStatusText;
        ControlWithState controlWithState2 = this.cws;
        if (controlWithState2 == null) {
            controlWithState2 = null;
        }
        final Control control = controlWithState2.control;
        if (Intrinsics.areEqual(charSequence, this.status.getText())) {
            z2 = false;
        }
        animateStatusChange(new Function0() { // from class: com.android.systemui.controls.ui.ControlViewHolder$applyRenderInfo$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                ControlViewHolder.this.updateStatusRow$frameworks__base__packages__SystemUI__android_common__SystemUI_core(z, charSequence, lookup.icon, colorStateList, control);
                return Unit.INSTANCE;
            }
        }, z2);
        int color2 = this.context.getResources().getColor(com.android.wm.shell.R.color.control_default_background, this.context.getTheme());
        if (z) {
            ControlWithState controlWithState3 = this.cws;
            Control control2 = (controlWithState3 != null ? controlWithState3 : null).control;
            listOf = CollectionsKt__CollectionsKt.listOf(Integer.valueOf((control2 == null || (customColor = control2.getCustomColor()) == null) ? this.context.getResources().getColor(lookup.enabledBackground, this.context.getTheme()) : customColor.getColorForState(new int[]{R.attr.state_enabled}, customColor.getDefaultColor())), 255);
        } else {
            listOf = CollectionsKt__CollectionsKt.listOf(Integer.valueOf(this.context.getResources().getColor(com.android.wm.shell.R.color.control_default_background, this.context.getTheme())), 0);
        }
        final int intValue = ((Number) listOf.get(0)).intValue();
        int intValue2 = ((Number) listOf.get(1)).intValue();
        if (this.behavior instanceof ToggleRangeBehavior) {
            color2 = ColorUtils.blendARGB(color2, intValue, this.toggleBackgroundIntensity);
        }
        final int i2 = color2;
        final Drawable drawable = this.clipLayer.getDrawable();
        if (drawable != null) {
            this.clipLayer.setAlpha(0);
            ValueAnimator valueAnimator = this.stateAnimator;
            if (valueAnimator != null) {
                valueAnimator.cancel();
            }
            if (!z2) {
                drawable.setAlpha(intValue2);
                if (drawable instanceof GradientDrawable) {
                    ((GradientDrawable) drawable).setColor(intValue);
                }
                this.baseLayer.setColor(i2);
                this.layout.setAlpha(1.0f);
                return;
            }
            final int defaultColor = (!(drawable instanceof GradientDrawable) || (color = ((GradientDrawable) drawable).getColor()) == null) ? intValue : color.getDefaultColor();
            ColorStateList color3 = this.baseLayer.getColor();
            final int defaultColor2 = color3 != null ? color3.getDefaultColor() : i2;
            final float alpha = this.layout.getAlpha();
            ValueAnimator ofInt = ValueAnimator.ofInt(this.clipLayer.getAlpha(), intValue2);
            ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.controls.ui.ControlViewHolder$startBackgroundAnimation$1$1
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                    int intValue3 = ((Integer) valueAnimator2.getAnimatedValue()).intValue();
                    int blendARGB = ColorUtils.blendARGB(defaultColor, intValue, valueAnimator2.getAnimatedFraction());
                    int blendARGB2 = ColorUtils.blendARGB(defaultColor2, i2, valueAnimator2.getAnimatedFraction());
                    float lerp = MathUtils.lerp(alpha, 1.0f, valueAnimator2.getAnimatedFraction());
                    ControlViewHolder controlViewHolder = this;
                    Drawable drawable2 = drawable;
                    controlViewHolder.getClass();
                    drawable2.setAlpha(intValue3);
                    if (drawable2 instanceof GradientDrawable) {
                        ((GradientDrawable) drawable2).setColor(blendARGB);
                    }
                    controlViewHolder.baseLayer.setColor(blendARGB2);
                    controlViewHolder.layout.setAlpha(lerp);
                }
            });
            ofInt.addListener(new ControlViewHolder$animateStatusChange$2$1(this, 1));
            ofInt.setDuration(700L);
            ofInt.setInterpolator(Interpolators.CONTROL_STATE);
            ofInt.start();
            this.stateAnimator = ofInt;
        }
    }

    public final Behavior bindBehavior(Behavior behavior, Supplier supplier, int i) {
        Behavior behavior2 = (Behavior) supplier.get();
        if (behavior == null || behavior.getClass() != behavior2.getClass()) {
            behavior2.initialize(this);
            this.layout.setAccessibilityDelegate(null);
            behavior = behavior2;
        }
        ControlWithState controlWithState = this.cws;
        behavior.bind(controlWithState != null ? controlWithState : null, i);
        return behavior;
    }

    public final void bindData(ControlWithState controlWithState, boolean z) {
        if (this.userInteractionInProgress) {
            return;
        }
        this.cws = controlWithState;
        int controlStatus = getControlStatus();
        ControlInfo controlInfo = controlWithState.ci;
        if (controlStatus == 0 || getControlStatus() == 2) {
            this.title.setText(controlInfo.controlTitle);
            this.subtitle.setText(controlInfo.controlSubtitle);
        } else {
            Control control = controlWithState.control;
            if (control != null) {
                this.title.setText(control.getTitle());
                this.subtitle.setText(control.getSubtitle());
                this.chevronIcon.setVisibility(usePanel() ? 0 : 4);
            }
        }
        if (controlWithState.control != null) {
            this.layout.setClickable(true);
            this.layout.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.android.systemui.controls.ui.ControlViewHolder$bindData$2$1
                @Override // android.view.View.OnLongClickListener
                public final boolean onLongClick(View view) {
                    ControlViewHolder controlViewHolder = ControlViewHolder.this;
                    controlViewHolder.controlActionCoordinator.longPress(controlViewHolder);
                    return true;
                }
            });
            String str = controlInfo.controlId;
            ControlActionCoordinatorImpl controlActionCoordinatorImpl = this.controlActionCoordinator;
            if (!controlActionCoordinatorImpl.isLocked()) {
                ControlActionCoordinatorImpl.Action action = controlActionCoordinatorImpl.pendingAction;
                if (Intrinsics.areEqual(action != null ? action.controlId : null, str)) {
                    ControlActionCoordinatorImpl.Action action2 = controlActionCoordinatorImpl.pendingAction;
                    if (action2 != null) {
                        action2.invoke();
                    }
                    controlActionCoordinatorImpl.pendingAction = null;
                }
            }
        }
        boolean z2 = this.isLoading;
        this.isLoading = false;
        Behavior behavior = this.behavior;
        int controlStatus2 = getControlStatus();
        ControlWithState controlWithState2 = this.cws;
        if (controlWithState2 == null) {
            controlWithState2 = null;
        }
        Control control2 = controlWithState2.control;
        ControlTemplate controlTemplate = control2 != null ? control2.getControlTemplate() : null;
        if (controlTemplate == null) {
            controlTemplate = ControlTemplate.NO_TEMPLATE;
        }
        this.behavior = bindBehavior(behavior, findBehaviorClass(controlStatus2, controlTemplate, getDeviceType()), 0);
        updateContentDescription();
        if (!z2 || this.isLoading) {
            return;
        }
        this.controlsMetricsLogger.refreshEnd(this, z);
    }

    public final Supplier findBehaviorClass(int i, ControlTemplate controlTemplate, int i2) {
        return i != 1 ? ControlViewHolder$findBehaviorClass$1.INSTANCE : controlTemplate.equals(ControlTemplate.NO_TEMPLATE) ? ControlViewHolder$findBehaviorClass$1.INSTANCE$2 : controlTemplate instanceof ThumbnailTemplate ? new Supplier() { // from class: com.android.systemui.controls.ui.ControlViewHolder$findBehaviorClass$3
            @Override // java.util.function.Supplier
            public final Object get() {
                return new ThumbnailBehavior(ControlViewHolder.this.currentUserId);
            }
        } : i2 == 50 ? ControlViewHolder$findBehaviorClass$1.INSTANCE$3 : controlTemplate instanceof ToggleTemplate ? ControlViewHolder$findBehaviorClass$1.INSTANCE$4 : controlTemplate instanceof StatelessTemplate ? ControlViewHolder$findBehaviorClass$1.INSTANCE$5 : controlTemplate instanceof ToggleRangeTemplate ? ControlViewHolder$findBehaviorClass$1.INSTANCE$6 : controlTemplate instanceof RangeTemplate ? ControlViewHolder$findBehaviorClass$1.INSTANCE$7 : controlTemplate instanceof TemperatureControlTemplate ? ControlViewHolder$findBehaviorClass$1.INSTANCE$8 : ControlViewHolder$findBehaviorClass$1.INSTANCE$1;
    }

    public final int getControlStatus() {
        ControlWithState controlWithState = this.cws;
        if (controlWithState == null) {
            controlWithState = null;
        }
        Control control = controlWithState.control;
        if (control != null) {
            return control.getStatus();
        }
        return 0;
    }

    public final int getDeviceType() {
        ControlWithState controlWithState = this.cws;
        Control control = (controlWithState != null ? controlWithState : null).control;
        if (control != null) {
            return control.getDeviceType();
        }
        if (controlWithState == null) {
            controlWithState = null;
        }
        return controlWithState.ci.deviceType;
    }

    public final void setErrorStatus() {
        final String string = this.context.getResources().getString(com.android.wm.shell.R.string.controls_error_failed);
        animateStatusChange(new Function0() { // from class: com.android.systemui.controls.ui.ControlViewHolder$setErrorStatus$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                ControlViewHolder.this.setStatusText(string, true);
                return Unit.INSTANCE;
            }
        }, true);
    }

    public final void setStatusText(CharSequence charSequence, boolean z) {
        if (z) {
            this.status.setAlpha(1.0f);
            this.status.setText(charSequence);
            updateContentDescription();
        }
        this.nextStatusText = charSequence;
    }

    public final void updateContentDescription() {
        this.layout.setContentDescription(((Object) this.title.getText()) + " " + ((Object) this.subtitle.getText()) + " " + ((Object) this.status.getText()));
    }

    public final void updateStatusRow$frameworks__base__packages__SystemUI__android_common__SystemUI_core(boolean z, CharSequence charSequence, Drawable drawable, ColorStateList colorStateList, Control control) {
        Icon customIcon;
        this.status.setEnabled(z);
        this.icon.setEnabled(z);
        this.chevronIcon.setEnabled(z);
        this.status.setText(charSequence);
        updateContentDescription();
        this.status.setTextColor(colorStateList);
        Unit unit = null;
        if (control != null && (customIcon = control.getCustomIcon()) != null) {
            if (!((Boolean) this.canUseIconPredicate.invoke(customIcon)).booleanValue()) {
                customIcon = null;
            }
            if (customIcon != null) {
                this.icon.setImageIcon(customIcon);
                this.icon.setImageTintList(customIcon.getTintList());
                unit = Unit.INSTANCE;
            }
        }
        if (unit == null) {
            if (drawable instanceof StateListDrawable) {
                if (this.icon.getDrawable() == null || !(this.icon.getDrawable() instanceof StateListDrawable)) {
                    this.icon.setImageDrawable(drawable);
                }
                this.icon.setImageState(z ? ATTR_ENABLED : ATTR_DISABLED, true);
            } else {
                this.icon.setImageDrawable(drawable);
            }
            if (getDeviceType() != 52) {
                this.icon.setImageTintList(colorStateList);
            }
        }
        this.chevronIcon.setImageTintList(this.icon.getImageTintList());
    }

    public final boolean usePanel() {
        if (!FORCE_PANEL_DEVICES.contains(Integer.valueOf(getDeviceType()))) {
            ControlWithState controlWithState = this.cws;
            if (controlWithState == null) {
                controlWithState = null;
            }
            Control control = controlWithState.control;
            ControlTemplate controlTemplate = control != null ? control.getControlTemplate() : null;
            if (controlTemplate == null) {
                controlTemplate = ControlTemplate.NO_TEMPLATE;
            }
            if (!controlTemplate.equals(ControlTemplate.NO_TEMPLATE)) {
                return false;
            }
        }
        return true;
    }
}

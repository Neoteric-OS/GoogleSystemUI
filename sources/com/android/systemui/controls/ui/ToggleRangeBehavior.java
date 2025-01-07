package com.android.systemui.controls.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.service.controls.Control;
import android.service.controls.actions.FloatAction;
import android.service.controls.templates.ControlTemplate;
import android.service.controls.templates.RangeTemplate;
import android.service.controls.templates.TemperatureControlTemplate;
import android.service.controls.templates.ToggleRangeTemplate;
import android.util.Log;
import android.util.MathUtils;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.android.app.animation.Interpolators;
import com.android.wm.shell.R;
import java.util.Arrays;
import java.util.IllegalFormatException;
import java.util.Set;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ToggleRangeBehavior implements Behavior {
    public Drawable clipLayer;
    public int colorOffset;
    public Context context;
    public Control control;
    public ControlViewHolder cvh;
    public boolean isChecked;
    public boolean isToggleable;
    public ValueAnimator rangeAnimator;
    public RangeTemplate rangeTemplate;
    public String templateId;
    public CharSequence currentStatusText = "";
    public String currentRangeValue = "";

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ToggleRangeGestureListener extends GestureDetector.SimpleOnGestureListener {
        public boolean isDragging;
        public final View v;

        public ToggleRangeGestureListener(View view) {
            this.v = view;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public final boolean onDown(MotionEvent motionEvent) {
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public final void onLongPress(MotionEvent motionEvent) {
            if (this.isDragging) {
                return;
            }
            ControlViewHolder controlViewHolder = ToggleRangeBehavior.this.cvh;
            ControlActionCoordinatorImpl controlActionCoordinatorImpl = (controlViewHolder != null ? controlViewHolder : null).controlActionCoordinator;
            if (controlViewHolder == null) {
                controlViewHolder = null;
            }
            controlActionCoordinatorImpl.longPress(controlViewHolder);
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public final boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (!this.isDragging) {
                this.v.getParent().requestDisallowInterceptTouchEvent(true);
                ToggleRangeBehavior toggleRangeBehavior = ToggleRangeBehavior.this;
                ControlViewHolder controlViewHolder = toggleRangeBehavior.cvh;
                (controlViewHolder != null ? controlViewHolder : null).userInteractionInProgress = true;
                if (controlViewHolder == null) {
                    controlViewHolder = null;
                }
                Context context = toggleRangeBehavior.context;
                if (context == null) {
                    context = null;
                }
                controlViewHolder.status.setTextSize(0, context.getResources().getDimensionPixelSize(R.dimen.control_status_expanded));
                this.isDragging = true;
            }
            int width = (int) (10000 * ((-f) / this.v.getWidth()));
            ToggleRangeBehavior toggleRangeBehavior2 = ToggleRangeBehavior.this;
            Drawable drawable = toggleRangeBehavior2.clipLayer;
            toggleRangeBehavior2.updateRange((drawable != null ? drawable : null).getLevel() + width, true, true);
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public final boolean onSingleTapUp(MotionEvent motionEvent) {
            ToggleRangeBehavior toggleRangeBehavior = ToggleRangeBehavior.this;
            if (!toggleRangeBehavior.isToggleable) {
                return false;
            }
            ControlViewHolder controlViewHolder = toggleRangeBehavior.cvh;
            ControlActionCoordinatorImpl controlActionCoordinatorImpl = (controlViewHolder != null ? controlViewHolder : null).controlActionCoordinator;
            if (controlViewHolder == null) {
                controlViewHolder = null;
            }
            String str = toggleRangeBehavior.templateId;
            controlActionCoordinatorImpl.toggle(controlViewHolder, str != null ? str : null, toggleRangeBehavior.isChecked);
            return true;
        }
    }

    @Override // com.android.systemui.controls.ui.Behavior
    public final void bind(ControlWithState controlWithState, int i) {
        Control control = controlWithState.control;
        Intrinsics.checkNotNull(control);
        this.control = control;
        this.colorOffset = i;
        this.currentStatusText = control.getStatusText();
        ControlViewHolder controlViewHolder = this.cvh;
        if (controlViewHolder == null) {
            controlViewHolder = null;
        }
        controlViewHolder.layout.setOnLongClickListener(null);
        ControlViewHolder controlViewHolder2 = this.cvh;
        if (controlViewHolder2 == null) {
            controlViewHolder2 = null;
        }
        this.clipLayer = ((LayerDrawable) controlViewHolder2.layout.getBackground()).findDrawableByLayerId(R.id.clip_layer);
        Control control2 = this.control;
        if (control2 == null) {
            control2 = null;
        }
        ControlTemplate controlTemplate = control2.getControlTemplate();
        if (setupTemplate(controlTemplate)) {
            this.templateId = controlTemplate.getTemplateId();
            RangeTemplate rangeTemplate = this.rangeTemplate;
            if (rangeTemplate == null) {
                rangeTemplate = null;
            }
            float currentValue = rangeTemplate.getCurrentValue();
            RangeTemplate rangeTemplate2 = this.rangeTemplate;
            if (rangeTemplate2 == null) {
                rangeTemplate2 = null;
            }
            float minValue = rangeTemplate2.getMinValue();
            RangeTemplate rangeTemplate3 = this.rangeTemplate;
            if (rangeTemplate3 == null) {
                rangeTemplate3 = null;
            }
            updateRange((int) MathUtils.constrainedMap(0.0f, 10000.0f, minValue, rangeTemplate3.getMaxValue(), currentValue), this.isChecked, false);
            ControlViewHolder controlViewHolder3 = this.cvh;
            if (controlViewHolder3 == null) {
                controlViewHolder3 = null;
            }
            controlViewHolder3.applyRenderInfo$frameworks__base__packages__SystemUI__android_common__SystemUI_core(i, this.isChecked, true);
            ControlViewHolder controlViewHolder4 = this.cvh;
            (controlViewHolder4 != null ? controlViewHolder4 : null).layout.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.android.systemui.controls.ui.ToggleRangeBehavior$bind$1
                @Override // android.view.View.AccessibilityDelegate
                public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                    super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                    float levelToRangeValue = ToggleRangeBehavior.this.levelToRangeValue(0);
                    ToggleRangeBehavior toggleRangeBehavior = ToggleRangeBehavior.this;
                    Drawable drawable = toggleRangeBehavior.clipLayer;
                    if (drawable == null) {
                        drawable = null;
                    }
                    float levelToRangeValue2 = toggleRangeBehavior.levelToRangeValue(drawable.getLevel());
                    float levelToRangeValue3 = ToggleRangeBehavior.this.levelToRangeValue(10000);
                    RangeTemplate rangeTemplate4 = ToggleRangeBehavior.this.rangeTemplate;
                    double stepValue = (rangeTemplate4 != null ? rangeTemplate4 : null).getStepValue();
                    int i2 = (stepValue == Math.floor(stepValue) ? 1 : 0) ^ 1;
                    if (ToggleRangeBehavior.this.isChecked) {
                        accessibilityNodeInfo.setRangeInfo(AccessibilityNodeInfo.RangeInfo.obtain(i2, levelToRangeValue, levelToRangeValue3, levelToRangeValue2));
                    }
                    accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS);
                }

                @Override // android.view.View.AccessibilityDelegate
                public final boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
                    return true;
                }

                @Override // android.view.View.AccessibilityDelegate
                public final boolean performAccessibilityAction(View view, int i2, Bundle bundle) {
                    if (i2 == 16) {
                        ToggleRangeBehavior toggleRangeBehavior = ToggleRangeBehavior.this;
                        if (toggleRangeBehavior.isToggleable) {
                            ControlViewHolder controlViewHolder5 = toggleRangeBehavior.cvh;
                            ControlActionCoordinatorImpl controlActionCoordinatorImpl = (controlViewHolder5 != null ? controlViewHolder5 : null).controlActionCoordinator;
                            if (controlViewHolder5 == null) {
                                controlViewHolder5 = null;
                            }
                            String str = toggleRangeBehavior.templateId;
                            controlActionCoordinatorImpl.toggle(controlViewHolder5, str != null ? str : null, toggleRangeBehavior.isChecked);
                            return true;
                        }
                    } else {
                        if (i2 == 32) {
                            ControlViewHolder controlViewHolder6 = ToggleRangeBehavior.this.cvh;
                            (controlViewHolder6 != null ? controlViewHolder6 : null).controlActionCoordinator.longPress(controlViewHolder6 != null ? controlViewHolder6 : null);
                            return true;
                        }
                        if (i2 == AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS.getId() && bundle != null && bundle.containsKey("android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE")) {
                            float f = bundle.getFloat("android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE");
                            ToggleRangeBehavior toggleRangeBehavior2 = ToggleRangeBehavior.this;
                            RangeTemplate rangeTemplate4 = toggleRangeBehavior2.rangeTemplate;
                            if (rangeTemplate4 == null) {
                                rangeTemplate4 = null;
                            }
                            float minValue2 = rangeTemplate4.getMinValue();
                            RangeTemplate rangeTemplate5 = toggleRangeBehavior2.rangeTemplate;
                            int constrainedMap = (int) MathUtils.constrainedMap(0.0f, 10000.0f, minValue2, (rangeTemplate5 != null ? rangeTemplate5 : null).getMaxValue(), f);
                            ToggleRangeBehavior toggleRangeBehavior3 = ToggleRangeBehavior.this;
                            toggleRangeBehavior3.updateRange(constrainedMap, toggleRangeBehavior3.isChecked, true);
                            ToggleRangeBehavior.this.endUpdateRange();
                            return true;
                        }
                    }
                    return super.performAccessibilityAction(view, i2, bundle);
                }
            });
        }
    }

    public final void endUpdateRange() {
        ControlViewHolder controlViewHolder = this.cvh;
        if (controlViewHolder == null) {
            controlViewHolder = null;
        }
        Context context = this.context;
        if (context == null) {
            context = null;
        }
        controlViewHolder.status.setTextSize(0, context.getResources().getDimensionPixelSize(R.dimen.control_status_normal));
        ControlViewHolder controlViewHolder2 = this.cvh;
        if (controlViewHolder2 == null) {
            controlViewHolder2 = null;
        }
        CharSequence charSequence = this.currentStatusText;
        controlViewHolder2.setStatusText(((Object) charSequence) + " " + this.currentRangeValue, true);
        final ControlViewHolder controlViewHolder3 = this.cvh;
        ControlActionCoordinatorImpl controlActionCoordinatorImpl = (controlViewHolder3 != null ? controlViewHolder3 : null).controlActionCoordinator;
        if (controlViewHolder3 == null) {
            controlViewHolder3 = null;
        }
        RangeTemplate rangeTemplate = this.rangeTemplate;
        if (rangeTemplate == null) {
            rangeTemplate = null;
        }
        final String templateId = rangeTemplate.getTemplateId();
        Drawable drawable = this.clipLayer;
        if (drawable == null) {
            drawable = null;
        }
        final float findNearestStep = findNearestStep(levelToRangeValue(drawable.getLevel()));
        controlActionCoordinatorImpl.controlsMetricsLogger.drag(controlViewHolder3, controlActionCoordinatorImpl.isLocked());
        ControlWithState controlWithState = controlViewHolder3.cws;
        String str = (controlWithState != null ? controlWithState : null).ci.controlId;
        Function0 function0 = new Function0() { // from class: com.android.systemui.controls.ui.ControlActionCoordinatorImpl$setValue$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                ControlViewHolder.this.action(new FloatAction(templateId, findNearestStep));
                return Unit.INSTANCE;
            }
        };
        if (controlWithState == null) {
            controlWithState = null;
        }
        Control control = controlWithState.control;
        controlActionCoordinatorImpl.bouncerOrRun(controlActionCoordinatorImpl.createAction(str, function0, false, control != null ? control.isAuthRequired() : true));
        ControlViewHolder controlViewHolder4 = this.cvh;
        (controlViewHolder4 != null ? controlViewHolder4 : null).userInteractionInProgress = false;
    }

    public final float findNearestStep(float f) {
        RangeTemplate rangeTemplate = this.rangeTemplate;
        if (rangeTemplate == null) {
            rangeTemplate = null;
        }
        float minValue = rangeTemplate.getMinValue();
        float f2 = Float.MAX_VALUE;
        while (true) {
            RangeTemplate rangeTemplate2 = this.rangeTemplate;
            if (rangeTemplate2 == null) {
                rangeTemplate2 = null;
            }
            if (minValue > rangeTemplate2.getMaxValue()) {
                RangeTemplate rangeTemplate3 = this.rangeTemplate;
                return (rangeTemplate3 != null ? rangeTemplate3 : null).getMaxValue();
            }
            float abs = Math.abs(f - minValue);
            if (abs >= f2) {
                RangeTemplate rangeTemplate4 = this.rangeTemplate;
                return minValue - (rangeTemplate4 != null ? rangeTemplate4 : null).getStepValue();
            }
            RangeTemplate rangeTemplate5 = this.rangeTemplate;
            if (rangeTemplate5 == null) {
                rangeTemplate5 = null;
            }
            minValue += rangeTemplate5.getStepValue();
            f2 = abs;
        }
    }

    public final String format(String str, String str2, float f) {
        try {
            return String.format(str, Arrays.copyOf(new Object[]{Float.valueOf(findNearestStep(f))}, 1));
        } catch (IllegalFormatException e) {
            Log.w("ControlsUiController", "Illegal format in range template", e);
            return str2.equals("") ? "" : this.format(str2, "", f);
        }
    }

    @Override // com.android.systemui.controls.ui.Behavior
    public final void initialize(ControlViewHolder controlViewHolder) {
        this.cvh = controlViewHolder;
        this.context = controlViewHolder.context;
        final ToggleRangeGestureListener toggleRangeGestureListener = new ToggleRangeGestureListener(controlViewHolder.layout);
        Context context = this.context;
        if (context == null) {
            context = null;
        }
        final GestureDetector gestureDetector = new GestureDetector(context, toggleRangeGestureListener);
        controlViewHolder.layout.setOnTouchListener(new View.OnTouchListener() { // from class: com.android.systemui.controls.ui.ToggleRangeBehavior$initialize$1
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                if (!gestureDetector.onTouchEvent(motionEvent) && motionEvent.getAction() == 1 && toggleRangeGestureListener.isDragging) {
                    view.getParent().requestDisallowInterceptTouchEvent(false);
                    toggleRangeGestureListener.isDragging = false;
                    this.endUpdateRange();
                }
                return false;
            }
        });
    }

    public final float levelToRangeValue(int i) {
        RangeTemplate rangeTemplate = this.rangeTemplate;
        if (rangeTemplate == null) {
            rangeTemplate = null;
        }
        float minValue = rangeTemplate.getMinValue();
        RangeTemplate rangeTemplate2 = this.rangeTemplate;
        return MathUtils.constrainedMap(minValue, (rangeTemplate2 != null ? rangeTemplate2 : null).getMaxValue(), 0.0f, 10000.0f, i);
    }

    public final boolean setupTemplate(ControlTemplate controlTemplate) {
        if (controlTemplate instanceof ToggleRangeTemplate) {
            ToggleRangeTemplate toggleRangeTemplate = (ToggleRangeTemplate) controlTemplate;
            this.rangeTemplate = toggleRangeTemplate.getRange();
            this.isToggleable = true;
            this.isChecked = toggleRangeTemplate.isChecked();
            return true;
        }
        if (!(controlTemplate instanceof RangeTemplate)) {
            if (controlTemplate instanceof TemperatureControlTemplate) {
                return setupTemplate(((TemperatureControlTemplate) controlTemplate).getTemplate());
            }
            Log.e("ControlsUiController", "Unsupported template type: " + controlTemplate);
            return false;
        }
        RangeTemplate rangeTemplate = (RangeTemplate) controlTemplate;
        this.rangeTemplate = rangeTemplate;
        float currentValue = rangeTemplate.getCurrentValue();
        RangeTemplate rangeTemplate2 = this.rangeTemplate;
        if (rangeTemplate2 == null) {
            rangeTemplate2 = null;
        }
        this.isChecked = !(currentValue == rangeTemplate2.getMinValue());
        return true;
    }

    public final void updateRange(int i, boolean z, boolean z2) {
        ControlViewHolder controlViewHolder;
        int max = Math.max(0, Math.min(10000, i));
        Drawable drawable = this.clipLayer;
        if (drawable == null) {
            drawable = null;
        }
        if (drawable.getLevel() == 0 && max > 0) {
            ControlViewHolder controlViewHolder2 = this.cvh;
            if (controlViewHolder2 == null) {
                controlViewHolder2 = null;
            }
            controlViewHolder2.applyRenderInfo$frameworks__base__packages__SystemUI__android_common__SystemUI_core(this.colorOffset, z, false);
        }
        ValueAnimator valueAnimator = this.rangeAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        if (z2) {
            boolean z3 = max == 0 || max == 10000;
            Drawable drawable2 = this.clipLayer;
            if (drawable2 == null) {
                drawable2 = null;
            }
            if (drawable2.getLevel() != max) {
                ControlViewHolder controlViewHolder3 = this.cvh;
                ControlActionCoordinatorImpl controlActionCoordinatorImpl = (controlViewHolder3 != null ? controlViewHolder3 : null).controlActionCoordinator;
                if (controlViewHolder3 == null) {
                    controlViewHolder3 = null;
                }
                controlActionCoordinatorImpl.getClass();
                int i2 = z3 ? 26 : 27;
                ViewGroup viewGroup = controlViewHolder3.layout;
                controlActionCoordinatorImpl.vibrator.getClass();
                viewGroup.performHapticFeedback(i2);
                Drawable drawable3 = this.clipLayer;
                if (drawable3 == null) {
                    drawable3 = null;
                }
                drawable3.setLevel(max);
            }
        } else {
            Drawable drawable4 = this.clipLayer;
            if (drawable4 == null) {
                drawable4 = null;
            }
            if (max != drawable4.getLevel()) {
                ControlViewHolder controlViewHolder4 = this.cvh;
                if (controlViewHolder4 == null) {
                    controlViewHolder4 = null;
                }
                ValueAnimator ofInt = ValueAnimator.ofInt(controlViewHolder4.clipLayer.getLevel(), max);
                ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.controls.ui.ToggleRangeBehavior$updateRange$1$1
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                        ControlViewHolder controlViewHolder5 = ToggleRangeBehavior.this.cvh;
                        if (controlViewHolder5 == null) {
                            controlViewHolder5 = null;
                        }
                        controlViewHolder5.clipLayer.setLevel(((Integer) valueAnimator2.getAnimatedValue()).intValue());
                    }
                });
                ofInt.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.controls.ui.ToggleRangeBehavior$updateRange$1$2
                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public final void onAnimationEnd(Animator animator) {
                        ToggleRangeBehavior.this.rangeAnimator = null;
                    }
                });
                ofInt.setDuration(700L);
                ofInt.setInterpolator(Interpolators.CONTROL_STATE);
                ofInt.start();
                this.rangeAnimator = ofInt;
            }
        }
        if (!z) {
            ControlViewHolder controlViewHolder5 = this.cvh;
            controlViewHolder = controlViewHolder5 != null ? controlViewHolder5 : null;
            CharSequence charSequence = this.currentStatusText;
            Set set = ControlViewHolder.FORCE_PANEL_DEVICES;
            controlViewHolder.setStatusText(charSequence, false);
            return;
        }
        float levelToRangeValue = levelToRangeValue(max);
        RangeTemplate rangeTemplate = this.rangeTemplate;
        if (rangeTemplate == null) {
            rangeTemplate = null;
        }
        String format = format(rangeTemplate.getFormatString().toString(), "%.1f", levelToRangeValue);
        this.currentRangeValue = format;
        if (z2) {
            ControlViewHolder controlViewHolder6 = this.cvh;
            (controlViewHolder6 != null ? controlViewHolder6 : null).setStatusText(format, true);
            return;
        }
        ControlViewHolder controlViewHolder7 = this.cvh;
        controlViewHolder = controlViewHolder7 != null ? controlViewHolder7 : null;
        String str = ((Object) this.currentStatusText) + " " + format;
        Set set2 = ControlViewHolder.FORCE_PANEL_DEVICES;
        controlViewHolder.setStatusText(str, false);
    }
}

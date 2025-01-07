package com.android.systemui.controls.ui;

import android.app.PendingIntent;
import android.content.Context;
import android.service.controls.Control;
import android.service.controls.actions.BooleanAction;
import android.service.controls.actions.CommandAction;
import android.util.Log;
import com.android.systemui.broadcast.BroadcastSender;
import com.android.systemui.controls.ControlsMetricsLoggerImpl;
import com.android.systemui.controls.settings.ControlsSettingsRepository;
import com.android.systemui.controls.settings.ControlsSettingsRepositoryImpl;
import com.android.systemui.controls.ui.ControlActionCoordinatorImpl;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.statusbar.VibratorHelper;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.concurrency.ExecutorImpl;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ControlActionCoordinatorImpl {
    public final Set actionsInProgress = new LinkedHashSet();
    public Context activityContext;
    public final ActivityStarter activityStarter;
    public final DelayableExecutor bgExecutor;
    public final BroadcastSender broadcastSender;
    public final Context context;
    public final ControlsMetricsLoggerImpl controlsMetricsLogger;
    public final ControlsSettingsRepository controlsSettingsRepository;
    public DetailDialog dialog;
    public final KeyguardStateController keyguardStateController;
    public Action pendingAction;
    public final Optional taskViewFactory;
    public final DelayableExecutor uiExecutor;
    public final VibratorHelper vibrator;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Action {
        public final boolean authIsRequired;
        public final boolean blockable;
        public final String controlId;
        public final Function0 f;

        public Action(String str, Function0 function0, boolean z, boolean z2) {
            this.controlId = str;
            this.f = function0;
            this.blockable = z;
            this.authIsRequired = z2;
        }

        public final void invoke() {
            if (this.blockable) {
                final ControlActionCoordinatorImpl controlActionCoordinatorImpl = ControlActionCoordinatorImpl.this;
                Set set = controlActionCoordinatorImpl.actionsInProgress;
                final String str = this.controlId;
                if (!set.add(str)) {
                    return;
                }
                controlActionCoordinatorImpl.uiExecutor.executeDelayed(new Runnable() { // from class: com.android.systemui.controls.ui.ControlActionCoordinatorImpl$shouldRunAction$1
                    @Override // java.lang.Runnable
                    public final void run() {
                        ControlActionCoordinatorImpl.this.actionsInProgress.remove(str);
                    }
                }, 3000L);
            }
            this.f.invoke();
        }
    }

    public ControlActionCoordinatorImpl(Context context, DelayableExecutor delayableExecutor, DelayableExecutor delayableExecutor2, ActivityStarter activityStarter, BroadcastSender broadcastSender, KeyguardStateController keyguardStateController, Optional optional, ControlsMetricsLoggerImpl controlsMetricsLoggerImpl, VibratorHelper vibratorHelper, ControlsSettingsRepository controlsSettingsRepository) {
        this.context = context;
        this.bgExecutor = delayableExecutor;
        this.uiExecutor = delayableExecutor2;
        this.activityStarter = activityStarter;
        this.broadcastSender = broadcastSender;
        this.keyguardStateController = keyguardStateController;
        this.taskViewFactory = optional;
        this.controlsMetricsLogger = controlsMetricsLoggerImpl;
        this.vibrator = vibratorHelper;
        this.controlsSettingsRepository = controlsSettingsRepository;
    }

    public final void bouncerOrRun(final Action action) {
        boolean z = action.authIsRequired || !((Boolean) ((StateFlowImpl) ((ControlsSettingsRepositoryImpl) this.controlsSettingsRepository).allowActionOnTrivialControlsInLockscreen.$$delegate_0).getValue()).booleanValue();
        if (!((KeyguardStateControllerImpl) this.keyguardStateController).mShowing || !z) {
            action.invoke();
            return;
        }
        if (isLocked()) {
            this.broadcastSender.closeSystemDialogs();
            this.pendingAction = action;
        }
        this.activityStarter.dismissKeyguardThenExecute(new ActivityStarter.OnDismissAction() { // from class: com.android.systemui.controls.ui.ControlActionCoordinatorImpl$bouncerOrRun$1
            @Override // com.android.systemui.plugins.ActivityStarter.OnDismissAction
            public final boolean onDismiss() {
                Log.d("ControlsUiController", "Device unlocked, invoking controls action");
                ControlActionCoordinatorImpl.Action.this.invoke();
                return true;
            }
        }, new Runnable() { // from class: com.android.systemui.controls.ui.ControlActionCoordinatorImpl$bouncerOrRun$2
            @Override // java.lang.Runnable
            public final void run() {
                ControlActionCoordinatorImpl.this.pendingAction = null;
            }
        }, true);
    }

    public final Action createAction(String str, Function0 function0, boolean z, boolean z2) {
        return new Action(str, function0, z, z2);
    }

    public final boolean isLocked() {
        return !this.keyguardStateController.isUnlocked();
    }

    public final void longPress(final ControlViewHolder controlViewHolder) {
        this.controlsMetricsLogger.longPress(controlViewHolder, isLocked());
        ControlWithState controlWithState = controlViewHolder.cws;
        String str = (controlWithState != null ? controlWithState : null).ci.controlId;
        Function0 function0 = new Function0() { // from class: com.android.systemui.controls.ui.ControlActionCoordinatorImpl$longPress$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                ControlViewHolder controlViewHolder2 = ControlViewHolder.this;
                ControlWithState controlWithState2 = controlViewHolder2.cws;
                if (controlWithState2 == null) {
                    controlWithState2 = null;
                }
                Control control = controlWithState2.control;
                if (control != null) {
                    ControlActionCoordinatorImpl controlActionCoordinatorImpl = this;
                    controlViewHolder2.layout.performHapticFeedback(0);
                    PendingIntent appIntent = control.getAppIntent();
                    controlActionCoordinatorImpl.getClass();
                    ((ExecutorImpl) controlActionCoordinatorImpl.bgExecutor).execute(new ControlActionCoordinatorImpl$showDetail$1(controlActionCoordinatorImpl, appIntent, controlViewHolder2));
                }
                return Unit.INSTANCE;
            }
        };
        if (controlWithState == null) {
            controlWithState = null;
        }
        Control control = controlWithState.control;
        bouncerOrRun(createAction(str, function0, false, control != null ? control.isAuthRequired() : true));
    }

    public final void toggle(final ControlViewHolder controlViewHolder, final String str, final boolean z) {
        this.controlsMetricsLogger.touch(controlViewHolder, isLocked());
        ControlWithState controlWithState = controlViewHolder.cws;
        String str2 = (controlWithState != null ? controlWithState : null).ci.controlId;
        Function0 function0 = new Function0() { // from class: com.android.systemui.controls.ui.ControlActionCoordinatorImpl$toggle$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                ControlViewHolder.this.layout.performHapticFeedback(6);
                ControlViewHolder.this.action(new BooleanAction(str, !z));
                return Unit.INSTANCE;
            }
        };
        if (controlWithState == null) {
            controlWithState = null;
        }
        Control control = controlWithState.control;
        bouncerOrRun(createAction(str2, function0, true, control != null ? control.isAuthRequired() : true));
    }

    public final void touch(final ControlViewHolder controlViewHolder, final String str, final Control control) {
        this.controlsMetricsLogger.touch(controlViewHolder, isLocked());
        boolean usePanel = controlViewHolder.usePanel();
        ControlWithState controlWithState = controlViewHolder.cws;
        String str2 = (controlWithState != null ? controlWithState : null).ci.controlId;
        Function0 function0 = new Function0() { // from class: com.android.systemui.controls.ui.ControlActionCoordinatorImpl$touch$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                ControlViewHolder.this.layout.performHapticFeedback(6);
                if (ControlViewHolder.this.usePanel()) {
                    ControlActionCoordinatorImpl controlActionCoordinatorImpl = this;
                    ControlViewHolder controlViewHolder2 = ControlViewHolder.this;
                    PendingIntent appIntent = control.getAppIntent();
                    controlActionCoordinatorImpl.getClass();
                    ((ExecutorImpl) controlActionCoordinatorImpl.bgExecutor).execute(new ControlActionCoordinatorImpl$showDetail$1(controlActionCoordinatorImpl, appIntent, controlViewHolder2));
                } else {
                    ControlViewHolder.this.action(new CommandAction(str));
                }
                return Unit.INSTANCE;
            }
        };
        if (controlWithState == null) {
            controlWithState = null;
        }
        Control control2 = controlWithState.control;
        bouncerOrRun(createAction(str2, function0, usePanel, control2 != null ? control2.isAuthRequired() : true));
    }
}

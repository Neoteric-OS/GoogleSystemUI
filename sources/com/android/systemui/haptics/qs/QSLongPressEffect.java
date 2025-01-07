package com.android.systemui.haptics.qs;

import android.os.VibrationEffect;
import androidx.room.TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0;
import com.android.systemui.animation.ActivityTransitionAnimator;
import com.android.systemui.animation.DelegateTransitionAnimatorController;
import com.android.systemui.haptics.qs.QSLongPressEffect;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.qs.tileimpl.QSTileViewImpl$initLongPressEffectCallback$1;
import com.android.systemui.statusbar.VibratorHelper;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class QSLongPressEffect {
    public QSTileViewImpl$initLongPressEffectCallback$1 callback;
    public final int[] durations;
    public int effectDuration;
    public QSLongPressEffect$createExpandableFromView$1 expandable;
    public final FalsingManager falsingManager;
    public final KeyguardStateController keyguardStateController;
    public final LogBuffer logBuffer;
    public VibrationEffect longPressHint;
    public QSTile qsTile;
    public final VibrationEffect snapEffect;
    public State state = State.IDLE;
    public final VibratorHelper vibratorHelper;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class State {
        public static final /* synthetic */ State[] $VALUES;
        public static final State CLICKED;
        public static final State IDLE;
        public static final State LONG_CLICKED;
        public static final State RUNNING_BACKWARDS_FROM_CANCEL;
        public static final State RUNNING_BACKWARDS_FROM_UP;
        public static final State RUNNING_FORWARD;
        public static final State TIMEOUT_WAIT;

        static {
            State state = new State("IDLE", 0);
            IDLE = state;
            State state2 = new State("TIMEOUT_WAIT", 1);
            TIMEOUT_WAIT = state2;
            State state3 = new State("RUNNING_FORWARD", 2);
            RUNNING_FORWARD = state3;
            State state4 = new State("RUNNING_BACKWARDS_FROM_UP", 3);
            RUNNING_BACKWARDS_FROM_UP = state4;
            State state5 = new State("RUNNING_BACKWARDS_FROM_CANCEL", 4);
            RUNNING_BACKWARDS_FROM_CANCEL = state5;
            State state6 = new State("CLICKED", 5);
            CLICKED = state6;
            State state7 = new State("LONG_CLICKED", 6);
            LONG_CLICKED = state7;
            State[] stateArr = {state, state2, state3, state4, state5, state6, state7};
            $VALUES = stateArr;
            EnumEntriesKt.enumEntries(stateArr);
        }

        public static State valueOf(String str) {
            return (State) Enum.valueOf(State.class, str);
        }

        public static State[] values() {
            return (State[]) $VALUES.clone();
        }
    }

    public QSLongPressEffect(VibratorHelper vibratorHelper, KeyguardStateController keyguardStateController, FalsingManager falsingManager, LogBuffer logBuffer) {
        int[] iArr;
        this.vibratorHelper = vibratorHelper;
        this.keyguardStateController = keyguardStateController;
        this.falsingManager = falsingManager;
        this.logBuffer = logBuffer;
        if (vibratorHelper != null) {
            iArr = vibratorHelper.mVibrator.getPrimitiveDurations(8, 3);
        } else {
            iArr = null;
        }
        this.durations = iArr;
        this.snapEffect = VibrationEffect.startComposition().addPrimitive(1, 0.5f, 0).compose();
    }

    public final DelegateTransitionAnimatorController createTransitionControllerDelegate(final ActivityTransitionAnimator.Controller controller) {
        return new DelegateTransitionAnimatorController(controller) { // from class: com.android.systemui.haptics.qs.QSLongPressEffect$createTransitionControllerDelegate$delegated$1
            @Override // com.android.systemui.animation.DelegateTransitionAnimatorController, com.android.systemui.animation.ActivityTransitionAnimator.Controller
            public final void onTransitionAnimationCancelled() {
                QSLongPressEffect qSLongPressEffect = this;
                if (qSLongPressEffect.state == QSLongPressEffect.State.LONG_CLICKED) {
                    qSLongPressEffect.setState(QSLongPressEffect.State.RUNNING_BACKWARDS_FROM_CANCEL);
                    QSTileViewImpl$initLongPressEffectCallback$1 qSTileViewImpl$initLongPressEffectCallback$1 = qSLongPressEffect.callback;
                    if (qSTileViewImpl$initLongPressEffectCallback$1 != null) {
                        qSTileViewImpl$initLongPressEffectCallback$1.onReverseAnimator(false);
                    }
                }
                this.delegate.onTransitionAnimationCancelled();
            }
        };
    }

    public final State getStateForClick() {
        QSTile.State state;
        QSTile.State state2;
        QSTile qSTile = this.qsTile;
        boolean z = (qSTile == null || (state2 = qSTile.getState()) == null || state2.state != 0) ? false : true;
        QSTile qSTile2 = this.qsTile;
        return (z || !((qSTile2 == null || (state = qSTile2.getState()) == null || !state.handlesLongClick) ? false : true) || ((KeyguardStateControllerImpl) this.keyguardStateController).mShowing) ? State.IDLE : State.CLICKED;
    }

    public final void logEvent(String str, State state, String str2) {
        LogLevel logLevel = LogLevel.DEBUG;
        QSLongPressEffect$logEvent$2 qSLongPressEffect$logEvent$2 = new Function1() { // from class: com.android.systemui.haptics.qs.QSLongPressEffect$logEvent$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                String str1 = logMessage.getStr1();
                String str22 = logMessage.getStr2();
                String str3 = logMessage.getStr3();
                StringBuilder m = TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0.m("[long-press effect on ", str1, " tile] ", str22, " on state: ");
                m.append(str3);
                return m.toString();
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("QSLongPressEffect", logLevel, qSLongPressEffect$logEvent$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = str;
        logMessageImpl.str2 = str2;
        logMessageImpl.str3 = state.name();
        logBuffer.commit(obtain);
    }

    public final void setState(State state) {
        this.state = state;
    }
}

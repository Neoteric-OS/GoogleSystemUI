package com.google.android.systemui.elmyra;

import android.content.Context;
import android.metrics.LogMaker;
import android.os.PowerManager;
import android.os.SystemClock;
import android.util.Log;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.Dumpable;
import com.google.android.systemui.elmyra.actions.Action;
import com.google.android.systemui.elmyra.feedback.FeedbackEffect;
import com.google.android.systemui.elmyra.gates.Gate;
import com.google.android.systemui.elmyra.sensors.GestureSensor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ElmyraService implements Dumpable {
    public final List mActions;
    public final List mFeedbackEffects;
    public final List mGates;
    public final AnonymousClass1 mGestureListener;
    public final GestureSensor mGestureSensor;
    public Action mLastActiveAction;
    public long mLastPrimedGesture;
    public int mLastStage;
    public final MetricsLogger mLogger;
    public final PowerManager mPowerManager;
    public final UiEventLogger mUiEventLogger;
    public final PowerManager.WakeLock mWakeLock;
    public final AnonymousClass1 mActionListener = new AnonymousClass1();
    public final AnonymousClass1 mGateListener = new AnonymousClass1();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.google.android.systemui.elmyra.ElmyraService$1, reason: invalid class name */
    public final class AnonymousClass1 implements Gate.Listener, GestureSensor.Listener {
        public /* synthetic */ AnonymousClass1() {
        }

        @Override // com.google.android.systemui.elmyra.gates.Gate.Listener
        public void onGateChanged(Gate gate) {
            ElmyraService.this.updateSensorListener$1();
        }

        @Override // com.google.android.systemui.elmyra.sensors.GestureSensor.Listener
        public void onGestureDetected(GestureSensor.DetectionProperties detectionProperties) {
            ElmyraService elmyraService = ElmyraService.this;
            elmyraService.mWakeLock.acquire(2000L);
            boolean isInteractive = elmyraService.mPowerManager.isInteractive();
            LogMaker latency = new LogMaker(999).setType(4).setSubtype((detectionProperties == null || !detectionProperties.mHostSuspended) ? !isInteractive ? 2 : 1 : 3).setLatency(isInteractive ? SystemClock.uptimeMillis() - elmyraService.mLastPrimedGesture : 0L);
            elmyraService.mLastPrimedGesture = 0L;
            Action updateActiveAction = elmyraService.updateActiveAction();
            if (updateActiveAction != null) {
                Log.i("Elmyra/ElmyraService", "Triggering " + updateActiveAction);
                updateActiveAction.onTrigger(detectionProperties);
                for (int i = 0; i < ((ArrayList) elmyraService.mFeedbackEffects).size(); i++) {
                    ((FeedbackEffect) ((ArrayList) elmyraService.mFeedbackEffects).get(i)).onResolve(detectionProperties);
                }
                latency.setPackageName(updateActiveAction.getClass().getName());
            }
            elmyraService.mUiEventLogger.log((detectionProperties == null || !detectionProperties.mHostSuspended) ? !isInteractive ? ElmyraEvent.ELMYRA_TRIGGERED_SCREEN_OFF : ElmyraEvent.ELMYRA_TRIGGERED_SCREEN_ON : ElmyraEvent.ELMYRA_TRIGGERED_AP_SUSPENDED);
            elmyraService.mLogger.write(latency);
        }

        @Override // com.google.android.systemui.elmyra.sensors.GestureSensor.Listener
        public void onGestureProgress(int i, float f) {
            ElmyraService elmyraService = ElmyraService.this;
            Action updateActiveAction = elmyraService.updateActiveAction();
            if (updateActiveAction != null) {
                updateActiveAction.onProgress(i, f);
                for (int i2 = 0; i2 < ((ArrayList) elmyraService.mFeedbackEffects).size(); i2++) {
                    ((FeedbackEffect) ((ArrayList) elmyraService.mFeedbackEffects).get(i2)).onProgress(i, f);
                }
            }
            if (i != elmyraService.mLastStage) {
                long uptimeMillis = SystemClock.uptimeMillis();
                if (i == 2) {
                    elmyraService.mUiEventLogger.log(ElmyraEvent.ELMYRA_PRIMED);
                    elmyraService.mLogger.action(998);
                    elmyraService.mLastPrimedGesture = uptimeMillis;
                } else if (i == 0 && elmyraService.mLastPrimedGesture != 0) {
                    elmyraService.mUiEventLogger.log(ElmyraEvent.ELMYRA_RELEASED);
                    elmyraService.mLogger.write(new LogMaker(997).setType(4).setLatency(uptimeMillis - elmyraService.mLastPrimedGesture));
                }
                elmyraService.mLastStage = i;
            }
        }
    }

    public ElmyraService(Context context, ServiceConfigurationGoogle serviceConfigurationGoogle, UiEventLogger uiEventLogger) {
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        this.mUiEventLogger = uiEventLogger;
        this.mLogger = new MetricsLogger();
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        this.mPowerManager = powerManager;
        this.mWakeLock = powerManager.newWakeLock(1, "Elmyra/ElmyraService");
        ArrayList arrayList = new ArrayList(serviceConfigurationGoogle.mActions);
        this.mActions = arrayList;
        final int i = 0;
        arrayList.forEach(new Consumer(this) { // from class: com.google.android.systemui.elmyra.ElmyraService$$ExternalSyntheticLambda0
            public final /* synthetic */ ElmyraService f$0;

            {
                this.f$0 = this;
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                int i2 = i;
                ElmyraService elmyraService = this.f$0;
                switch (i2) {
                    case 0:
                        ((Action) obj).mListener = elmyraService.mActionListener;
                        break;
                    default:
                        ((Gate) obj).mListener = elmyraService.mGateListener;
                        break;
                }
            }
        });
        this.mFeedbackEffects = new ArrayList(serviceConfigurationGoogle.mFeedbackEffects);
        ArrayList arrayList2 = new ArrayList(serviceConfigurationGoogle.mGates);
        this.mGates = arrayList2;
        final int i2 = 1;
        arrayList2.forEach(new Consumer(this) { // from class: com.google.android.systemui.elmyra.ElmyraService$$ExternalSyntheticLambda0
            public final /* synthetic */ ElmyraService f$0;

            {
                this.f$0 = this;
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                int i22 = i2;
                ElmyraService elmyraService = this.f$0;
                switch (i22) {
                    case 0:
                        ((Action) obj).mListener = elmyraService.mActionListener;
                        break;
                    default:
                        ((Gate) obj).mListener = elmyraService.mGateListener;
                        break;
                }
            }
        });
        GestureSensor gestureSensor = serviceConfigurationGoogle.mGestureSensor;
        this.mGestureSensor = gestureSensor;
        if (gestureSensor != null) {
            gestureSensor.setGestureListener(anonymousClass1);
        }
        updateSensorListener$1();
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("ElmyraService state:");
        printWriter.println("  Gates:");
        int i = 0;
        while (true) {
            if (i >= ((ArrayList) this.mGates).size()) {
                break;
            }
            printWriter.print("    ");
            if (((Gate) ((ArrayList) this.mGates).get(i)).mActive) {
                printWriter.print(((Gate) ((ArrayList) this.mGates).get(i)).isBlocking() ? "X " : "O ");
            } else {
                printWriter.print("- ");
            }
            printWriter.println(((Gate) ((ArrayList) this.mGates).get(i)).toString());
            i++;
        }
        printWriter.println("  Actions:");
        for (int i2 = 0; i2 < ((ArrayList) this.mActions).size(); i2++) {
            printWriter.print("    ");
            printWriter.print(((Action) ((ArrayList) this.mActions).get(i2)).isAvailable() ? "O " : "X ");
            printWriter.println(((Action) ((ArrayList) this.mActions).get(i2)).toString());
        }
        printWriter.println("  Active: " + this.mLastActiveAction);
        printWriter.println("  Feedback Effects:");
        for (int i3 = 0; i3 < ((ArrayList) this.mFeedbackEffects).size(); i3++) {
            printWriter.print("    ");
            printWriter.println(((FeedbackEffect) ((ArrayList) this.mFeedbackEffects).get(i3)).toString());
        }
        StringBuilder sb = new StringBuilder("  Gesture Sensor: ");
        GestureSensor gestureSensor = this.mGestureSensor;
        sb.append(gestureSensor.toString());
        printWriter.println(sb.toString());
        if (gestureSensor instanceof Dumpable) {
            ((Dumpable) gestureSensor).dump(printWriter, strArr);
        }
    }

    public final void stopListening$2() {
        GestureSensor gestureSensor = this.mGestureSensor;
        if (gestureSensor == null || !gestureSensor.isListening()) {
            return;
        }
        gestureSensor.stopListening();
        for (int i = 0; i < ((ArrayList) this.mFeedbackEffects).size(); i++) {
            ((FeedbackEffect) ((ArrayList) this.mFeedbackEffects).get(i)).onRelease();
        }
        Action updateActiveAction = updateActiveAction();
        if (updateActiveAction != null) {
            updateActiveAction.onProgress(0, 0.0f);
        }
    }

    public final Action updateActiveAction() {
        Action action;
        int i = 0;
        while (true) {
            if (i >= ((ArrayList) this.mActions).size()) {
                action = null;
                break;
            }
            if (((Action) ((ArrayList) this.mActions).get(i)).isAvailable()) {
                action = (Action) ((ArrayList) this.mActions).get(i);
                break;
            }
            i++;
        }
        Action action2 = this.mLastActiveAction;
        if (action2 != null && action != action2) {
            Log.i("Elmyra/ElmyraService", "Switching action from " + this.mLastActiveAction + " to " + action);
            this.mLastActiveAction.onProgress(0, 0.0f);
        }
        this.mLastActiveAction = action;
        return action;
    }

    public final void updateSensorListener$1() {
        Gate gate;
        Action updateActiveAction = updateActiveAction();
        int i = 0;
        if (updateActiveAction == null) {
            Log.i("Elmyra/ElmyraService", "No available actions");
            while (i < ((ArrayList) this.mGates).size()) {
                ((Gate) ((ArrayList) this.mGates).get(i)).deactivate();
                i++;
            }
            stopListening$2();
            return;
        }
        for (int i2 = 0; i2 < ((ArrayList) this.mGates).size(); i2++) {
            ((Gate) ((ArrayList) this.mGates).get(i2)).activate();
        }
        while (true) {
            if (i >= ((ArrayList) this.mGates).size()) {
                gate = null;
                break;
            } else {
                if (((Gate) ((ArrayList) this.mGates).get(i)).isBlocking()) {
                    gate = (Gate) ((ArrayList) this.mGates).get(i);
                    break;
                }
                i++;
            }
        }
        if (gate != null) {
            Log.i("Elmyra/ElmyraService", "Gated by " + gate);
            stopListening$2();
            return;
        }
        Log.i("Elmyra/ElmyraService", "Unblocked; current action: " + updateActiveAction);
        GestureSensor gestureSensor = this.mGestureSensor;
        if (gestureSensor == null || gestureSensor.isListening()) {
            return;
        }
        gestureSensor.startListening();
    }
}

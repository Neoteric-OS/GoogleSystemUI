package com.google.android.systemui.columbus.legacy;

import android.os.PowerManager;
import android.util.Log;
import com.android.systemui.Dumpable;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.util.time.SystemClockImpl;
import com.google.android.systemui.columbus.legacy.PowerManagerWrapper;
import com.google.android.systemui.columbus.legacy.actions.Action;
import com.google.android.systemui.columbus.legacy.feedback.FeedbackEffect;
import com.google.android.systemui.columbus.legacy.gates.Gate;
import com.google.android.systemui.columbus.legacy.sensors.GestureController;
import com.google.android.systemui.columbus.legacy.sensors.GestureSensor;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ColumbusService implements Dumpable {
    public final ColumbusService$actionListener$1 actionListener;
    public final List actions;
    public final DelayableExecutor delayableExecutor;
    public final Set effects;
    public final ColumbusService$gateListener$1 gateListener;
    public final Set gates;
    public final GestureController gestureController;
    public final ColumbusService$gestureListener$1 gestureListener;
    public Action lastActiveAction;
    public long lastStateChangeTimestamp;
    public ExecutorImpl.ExecutionToken removeStartListeningRunnable;
    public ExecutorImpl.ExecutionToken removeStopListeningRunnable;
    public final ColumbusService$stopListeningRunnable$1 startListeningRunnable;
    public final ColumbusService$stopListeningRunnable$1 stopListeningRunnable;
    public final SystemClock systemClock;
    public final PowerManagerWrapper.WakeLockWrapper wakeLock;

    /* JADX WARN: Type inference failed for: r2v2, types: [com.google.android.systemui.columbus.legacy.ColumbusService$actionListener$1] */
    /* JADX WARN: Type inference failed for: r2v3, types: [com.google.android.systemui.columbus.legacy.ColumbusService$gateListener$1] */
    /* JADX WARN: Type inference failed for: r2v5, types: [com.google.android.systemui.columbus.legacy.ColumbusService$stopListeningRunnable$1] */
    /* JADX WARN: Type inference failed for: r2v6, types: [com.google.android.systemui.columbus.legacy.ColumbusService$stopListeningRunnable$1] */
    public ColumbusService(List list, Set set, Set set2, GestureController gestureController, PowerManagerWrapper powerManagerWrapper, DelayableExecutor delayableExecutor, SystemClock systemClock) {
        this.actions = list;
        this.effects = set;
        this.gates = set2;
        this.gestureController = gestureController;
        this.delayableExecutor = delayableExecutor;
        this.systemClock = systemClock;
        PowerManager powerManager = powerManagerWrapper.powerManager;
        this.wakeLock = new PowerManagerWrapper.WakeLockWrapper(powerManager != null ? powerManager.newWakeLock(1, "Columbus/Service") : null);
        this.actionListener = new Action.Listener() { // from class: com.google.android.systemui.columbus.legacy.ColumbusService$actionListener$1
            @Override // com.google.android.systemui.columbus.legacy.actions.Action.Listener
            public final void onActionAvailabilityChanged(Action action) {
                ColumbusService.this.updateSensorListener();
            }
        };
        this.gateListener = new Gate.Listener() { // from class: com.google.android.systemui.columbus.legacy.ColumbusService$gateListener$1
            @Override // com.google.android.systemui.columbus.legacy.gates.Gate.Listener
            public final void onGateChanged(Gate gate) {
                ColumbusService.this.updateSensorListener();
            }
        };
        this.gestureListener = new ColumbusService$gestureListener$1(this);
        final int i = 1;
        this.startListeningRunnable = new Runnable(this) { // from class: com.google.android.systemui.columbus.legacy.ColumbusService$stopListeningRunnable$1
            public final /* synthetic */ ColumbusService this$0;

            {
                this.this$0 = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                switch (i) {
                    case 0:
                        ColumbusService columbusService = this.this$0;
                        GestureController gestureController2 = columbusService.gestureController;
                        GestureSensor gestureSensor = gestureController2.gestureSensor;
                        if (gestureSensor.isListening()) {
                            gestureSensor.stopListening();
                            Iterator it = gestureController2.softGates.iterator();
                            while (it.hasNext()) {
                                ((Gate) it.next()).unregisterListener(gestureController2.softGateListener);
                            }
                            ((SystemClockImpl) columbusService.systemClock).getClass();
                            columbusService.lastStateChangeTimestamp = android.os.SystemClock.elapsedRealtime();
                            Iterator it2 = columbusService.effects.iterator();
                            while (it2.hasNext()) {
                                ((FeedbackEffect) it2.next()).onGestureDetected(0, null);
                            }
                            Action updateActiveAction = columbusService.updateActiveAction();
                            if (updateActiveAction != null) {
                                updateActiveAction.onGestureDetected(0, null);
                                break;
                            }
                        }
                        break;
                    default:
                        ColumbusService columbusService2 = this.this$0;
                        GestureController gestureController3 = columbusService2.gestureController;
                        GestureSensor gestureSensor2 = gestureController3.gestureSensor;
                        if (!gestureSensor2.isListening()) {
                            Iterator it3 = gestureController3.softGates.iterator();
                            while (it3.hasNext()) {
                                ((Gate) it3.next()).registerListener(gestureController3.softGateListener);
                            }
                            gestureSensor2.startListening();
                            ((SystemClockImpl) columbusService2.systemClock).getClass();
                            columbusService2.lastStateChangeTimestamp = android.os.SystemClock.elapsedRealtime();
                            break;
                        }
                        break;
                }
            }
        };
        final int i2 = 0;
        this.stopListeningRunnable = new Runnable(this) { // from class: com.google.android.systemui.columbus.legacy.ColumbusService$stopListeningRunnable$1
            public final /* synthetic */ ColumbusService this$0;

            {
                this.this$0 = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                switch (i2) {
                    case 0:
                        ColumbusService columbusService = this.this$0;
                        GestureController gestureController2 = columbusService.gestureController;
                        GestureSensor gestureSensor = gestureController2.gestureSensor;
                        if (gestureSensor.isListening()) {
                            gestureSensor.stopListening();
                            Iterator it = gestureController2.softGates.iterator();
                            while (it.hasNext()) {
                                ((Gate) it.next()).unregisterListener(gestureController2.softGateListener);
                            }
                            ((SystemClockImpl) columbusService.systemClock).getClass();
                            columbusService.lastStateChangeTimestamp = android.os.SystemClock.elapsedRealtime();
                            Iterator it2 = columbusService.effects.iterator();
                            while (it2.hasNext()) {
                                ((FeedbackEffect) it2.next()).onGestureDetected(0, null);
                            }
                            Action updateActiveAction = columbusService.updateActiveAction();
                            if (updateActiveAction != null) {
                                updateActiveAction.onGestureDetected(0, null);
                                break;
                            }
                        }
                        break;
                    default:
                        ColumbusService columbusService2 = this.this$0;
                        GestureController gestureController3 = columbusService2.gestureController;
                        GestureSensor gestureSensor2 = gestureController3.gestureSensor;
                        if (!gestureSensor2.isListening()) {
                            Iterator it3 = gestureController3.softGates.iterator();
                            while (it3.hasNext()) {
                                ((Gate) it3.next()).registerListener(gestureController3.softGateListener);
                            }
                            gestureSensor2.startListening();
                            ((SystemClockImpl) columbusService2.systemClock).getClass();
                            columbusService2.lastStateChangeTimestamp = android.os.SystemClock.elapsedRealtime();
                            break;
                        }
                        break;
                }
            }
        };
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((Action) it.next()).listeners.add(this.actionListener);
        }
        this.gestureController.gestureListener = this.gestureListener;
        updateSensorListener();
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("ColumbusService state:");
        printWriter.println("  Gates:");
        Iterator it = this.gates.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Gate gate = (Gate) it.next();
            printWriter.print("    ");
            if (gate.isActive()) {
                printWriter.print(gate.isBlocking() ? "X " : "O ");
            } else {
                printWriter.print("- ");
            }
            printWriter.println(gate.toString());
        }
        printWriter.println("  Actions:");
        for (Action action : this.actions) {
            printWriter.print("    ");
            printWriter.print(action.isAvailable ? "O " : "X ");
            printWriter.println(action.toString());
        }
        printWriter.println("  Active: " + this.lastActiveAction);
        printWriter.println("  Feedback Effects:");
        for (FeedbackEffect feedbackEffect : this.effects) {
            printWriter.print("    ");
            printWriter.println(feedbackEffect.toString());
        }
        this.gestureController.dump(printWriter, strArr);
    }

    public final void stopListening$1() {
        ExecutorImpl.ExecutionToken executionToken = this.removeStartListeningRunnable;
        if (executionToken != null) {
            executionToken.run();
        }
        this.removeStartListeningRunnable = null;
        ExecutorImpl.ExecutionToken executionToken2 = this.removeStopListeningRunnable;
        if (executionToken2 != null) {
            executionToken2.run();
        }
        ((SystemClockImpl) this.systemClock).getClass();
        this.removeStopListeningRunnable = this.delayableExecutor.executeDelayed(this.stopListeningRunnable, Math.max(0L, 1000 - (android.os.SystemClock.elapsedRealtime() - this.lastStateChangeTimestamp)));
    }

    public final Action updateActiveAction() {
        Object obj;
        Iterator it = this.actions.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (((Action) obj).isAvailable) {
                break;
            }
        }
        Action action = (Action) obj;
        Action action2 = this.lastActiveAction;
        if (action2 != null && action != action2) {
            Log.i("Columbus/Service", "Switching action from " + action2 + " to " + action);
            action2.onGestureDetected(0, null);
        }
        this.lastActiveAction = action;
        return action;
    }

    public final void updateSensorListener() {
        Object obj;
        Action updateActiveAction = updateActiveAction();
        ColumbusService$gateListener$1 columbusService$gateListener$1 = this.gateListener;
        if (updateActiveAction == null) {
            Log.i("Columbus/Service", "No available actions");
            Iterator it = this.gates.iterator();
            while (it.hasNext()) {
                ((Gate) it.next()).unregisterListener(columbusService$gateListener$1);
            }
            stopListening$1();
            return;
        }
        Iterator it2 = this.gates.iterator();
        while (it2.hasNext()) {
            ((Gate) it2.next()).registerListener(columbusService$gateListener$1);
        }
        Iterator it3 = this.gates.iterator();
        while (true) {
            if (!it3.hasNext()) {
                obj = null;
                break;
            } else {
                obj = it3.next();
                if (((Gate) obj).isBlocking()) {
                    break;
                }
            }
        }
        Gate gate = (Gate) obj;
        if (gate != null) {
            Log.i("Columbus/Service", "Gated by " + gate);
            stopListening$1();
            return;
        }
        Log.i("Columbus/Service", "Unblocked; current action: " + updateActiveAction);
        ExecutorImpl.ExecutionToken executionToken = this.removeStopListeningRunnable;
        if (executionToken != null) {
            executionToken.run();
        }
        this.removeStopListeningRunnable = null;
        ExecutorImpl.ExecutionToken executionToken2 = this.removeStartListeningRunnable;
        if (executionToken2 != null) {
            executionToken2.run();
        }
        ((SystemClockImpl) this.systemClock).getClass();
        this.removeStartListeningRunnable = this.delayableExecutor.executeDelayed(this.startListeningRunnable, Math.max(0L, 1000 - (android.os.SystemClock.elapsedRealtime() - this.lastStateChangeTimestamp)));
    }
}

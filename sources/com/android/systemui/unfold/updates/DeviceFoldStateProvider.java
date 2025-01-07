package com.android.systemui.unfold.updates;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Handler;
import android.os.Trace;
import android.util.Log;
import androidx.core.util.Consumer;
import com.android.systemui.keyguard.LifecycleScreenStatusProvider;
import com.android.systemui.shared.system.TaskStackChangeListeners;
import com.android.systemui.unfold.config.ResourceUnfoldTransitionConfig;
import com.android.systemui.unfold.system.ActivityManagerActivityTypeProvider;
import com.android.systemui.unfold.updates.FoldProvider;
import com.android.systemui.unfold.updates.RotationChangeProvider;
import com.android.systemui.unfold.updates.hinge.HingeAngleProvider;
import com.android.systemui.unfold.util.CallbackController;
import com.android.systemui.unfold.util.UnfoldKeyguardVisibilityManagerImpl;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__IndentKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DeviceFoldStateProvider implements CallbackController {
    public final ActivityManagerActivityTypeProvider activityTypeProvider;
    public final Context context;
    public final FoldProvider foldProvider;
    public final int halfOpenedTimeoutMillis;
    public final HingeAngleProvider hingeAngleProvider;
    public boolean isFolded;
    public boolean isScreenOn;
    public boolean isStarted;
    public Integer lastFoldUpdate;
    public float lastHingeAngle;
    public float lastHingeAngleBeforeTransition;
    public final Handler progressHandler;
    public final RotationChangeProvider rotationChangeProvider;
    public final LifecycleScreenStatusProvider screenStatusProvider;
    public final UnfoldKeyguardVisibilityManagerImpl unfoldKeyguardVisibilityProvider;
    public final CopyOnWriteArrayList outputListeners = new CopyOnWriteArrayList();
    public final HingeAngleListener hingeAngleListener = new HingeAngleListener();
    public final ScreenStatusListener screenListener = new ScreenStatusListener();
    public final FoldStateListener foldStateListener = new FoldStateListener();
    public final DeviceFoldStateProvider$timeoutRunnable$1 timeoutRunnable = new DeviceFoldStateProvider$timeoutRunnable$1(this);
    public final FoldRotationListener rotationListener = new FoldRotationListener();
    public final DeviceFoldStateProvider$progressExecutor$1 progressExecutor = new Executor() { // from class: com.android.systemui.unfold.updates.DeviceFoldStateProvider$progressExecutor$1
        @Override // java.util.concurrent.Executor
        public final void execute(Runnable runnable) {
            DeviceFoldStateProvider.this.progressHandler.post(runnable);
        }
    };
    public boolean isUnfoldHandled = true;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class FoldRotationListener implements RotationChangeProvider.RotationListener {
        public FoldRotationListener() {
        }

        @Override // com.android.systemui.unfold.updates.RotationChangeProvider.RotationListener
        public final void onRotationChanged(int i) {
            DeviceFoldStateProvider deviceFoldStateProvider = DeviceFoldStateProvider.this;
            deviceFoldStateProvider.assertInProgressThread$2();
            if (deviceFoldStateProvider.isTransitionInProgress()) {
                deviceFoldStateProvider.notifyFoldUpdate(2, deviceFoldStateProvider.lastHingeAngle);
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class FoldStateListener implements FoldProvider.FoldCallback {
        public FoldStateListener() {
        }

        @Override // com.android.systemui.unfold.updates.FoldProvider.FoldCallback
        public final void onFoldUpdated(boolean z) {
            DeviceFoldStateProvider deviceFoldStateProvider = DeviceFoldStateProvider.this;
            deviceFoldStateProvider.assertInProgressThread$2();
            deviceFoldStateProvider.isFolded = z;
            deviceFoldStateProvider.lastHingeAngle = 0.0f;
            Handler handler = deviceFoldStateProvider.progressHandler;
            DeviceFoldStateProvider$timeoutRunnable$1 deviceFoldStateProvider$timeoutRunnable$1 = deviceFoldStateProvider.timeoutRunnable;
            HingeAngleProvider hingeAngleProvider = deviceFoldStateProvider.hingeAngleProvider;
            if (z) {
                hingeAngleProvider.stop();
                deviceFoldStateProvider.notifyFoldUpdate(4, deviceFoldStateProvider.lastHingeAngle);
                handler.removeCallbacks(deviceFoldStateProvider$timeoutRunnable$1);
                deviceFoldStateProvider.isUnfoldHandled = false;
                return;
            }
            deviceFoldStateProvider.notifyFoldUpdate(0, 0.0f);
            if (deviceFoldStateProvider.isTransitionInProgress()) {
                handler.removeCallbacks(deviceFoldStateProvider$timeoutRunnable$1);
            }
            handler.postDelayed(deviceFoldStateProvider$timeoutRunnable$1, deviceFoldStateProvider.halfOpenedTimeoutMillis);
            hingeAngleProvider.start();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class HingeAngleListener implements Consumer {
        public HingeAngleListener() {
        }

        @Override // androidx.core.util.Consumer
        public final void accept(Object obj) {
            Integer num;
            float floatValue = ((Number) obj).floatValue();
            DeviceFoldStateProvider.this.assertInProgressThread$2();
            DeviceFoldStateProvider deviceFoldStateProvider = DeviceFoldStateProvider.this;
            deviceFoldStateProvider.assertInProgressThread$2();
            boolean z = DeviceFoldStateProviderKt.DEBUG;
            if (z) {
                Log.d("DeviceFoldProvider", "Hinge angle: " + floatValue + ", lastHingeAngle: " + deviceFoldStateProvider.lastHingeAngle + ", lastHingeAngleBeforeTransition: " + deviceFoldStateProvider.lastHingeAngleBeforeTransition);
            }
            boolean z2 = deviceFoldStateProvider.isTransitionInProgress() && ((num = deviceFoldStateProvider.lastFoldUpdate) == null || ((floatValue > deviceFoldStateProvider.lastHingeAngle ? 1 : (floatValue == deviceFoldStateProvider.lastHingeAngle ? 0 : -1)) < 0 ? 1 : 0) != num.intValue());
            boolean z3 = floatValue - deviceFoldStateProvider.lastHingeAngleBeforeTransition > 7.5f;
            if (z2 || z3) {
                deviceFoldStateProvider.lastHingeAngleBeforeTransition = deviceFoldStateProvider.lastHingeAngle;
            }
            float f = deviceFoldStateProvider.lastHingeAngleBeforeTransition;
            int i = floatValue < f ? 1 : 0;
            boolean z4 = Math.abs(floatValue - f) > 7.5f;
            boolean z5 = 180.0f - floatValue < 15.0f;
            Integer num2 = deviceFoldStateProvider.lastFoldUpdate;
            boolean z6 = num2 == null || num2.intValue() != i;
            boolean z7 = deviceFoldStateProvider.isUnfoldHandled;
            boolean z8 = deviceFoldStateProvider.context.getResources().getConfiguration().smallestScreenWidthDp > 600;
            if (z4 && z6 && !z5 && z7) {
                Boolean bool = deviceFoldStateProvider.activityTypeProvider._isHomeActivity;
                Integer num3 = null;
                if (bool != null) {
                    boolean booleanValue = bool.booleanValue();
                    deviceFoldStateProvider.unfoldKeyguardVisibilityProvider.getClass();
                    boolean areEqual = Intrinsics.areEqual((Object) null, Boolean.TRUE);
                    if (z) {
                        Log.d("DeviceFoldProvider", "isHomeActivity=" + booleanValue + ", isOnKeyguard=" + areEqual);
                    }
                    if (!booleanValue && !areEqual) {
                        num3 = 60;
                    }
                }
                if ((num3 == null || floatValue < num3.intValue()) && z8) {
                    deviceFoldStateProvider.notifyFoldUpdate(i, floatValue);
                }
            }
            if (deviceFoldStateProvider.isTransitionInProgress()) {
                if (z5) {
                    deviceFoldStateProvider.notifyFoldUpdate(3, floatValue);
                    deviceFoldStateProvider.progressHandler.removeCallbacks(deviceFoldStateProvider.timeoutRunnable);
                } else {
                    boolean isTransitionInProgress = deviceFoldStateProvider.isTransitionInProgress();
                    Handler handler = deviceFoldStateProvider.progressHandler;
                    DeviceFoldStateProvider$timeoutRunnable$1 deviceFoldStateProvider$timeoutRunnable$1 = deviceFoldStateProvider.timeoutRunnable;
                    if (isTransitionInProgress) {
                        handler.removeCallbacks(deviceFoldStateProvider$timeoutRunnable$1);
                    }
                    handler.postDelayed(deviceFoldStateProvider$timeoutRunnable$1, deviceFoldStateProvider.halfOpenedTimeoutMillis);
                }
            }
            deviceFoldStateProvider.lastHingeAngle = floatValue;
            Iterator it = deviceFoldStateProvider.outputListeners.iterator();
            while (it.hasNext()) {
                ((FoldStateProvider$FoldUpdatesListener) it.next()).onHingeAngleUpdate(floatValue);
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ScreenStatusListener {
        public ScreenStatusListener() {
        }

        public final void onScreenTurnedOn() {
            final DeviceFoldStateProvider deviceFoldStateProvider = DeviceFoldStateProvider.this;
            deviceFoldStateProvider.progressHandler.post(new DeviceFoldStateProvider$timeoutRunnable$1(new Function0() { // from class: com.android.systemui.unfold.updates.DeviceFoldStateProvider$ScreenStatusListener$onScreenTurnedOn$1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    DeviceFoldStateProvider deviceFoldStateProvider2 = DeviceFoldStateProvider.this;
                    if (!deviceFoldStateProvider2.isFolded && !deviceFoldStateProvider2.isUnfoldHandled) {
                        Iterator it = deviceFoldStateProvider2.outputListeners.iterator();
                        while (it.hasNext()) {
                            ((FoldStateProvider$FoldUpdatesListener) it.next()).onUnfoldedScreenAvailable();
                        }
                        DeviceFoldStateProvider.this.isUnfoldHandled = true;
                    }
                    return Unit.INSTANCE;
                }
            }));
        }

        public final void onScreenTurningOff() {
            final DeviceFoldStateProvider deviceFoldStateProvider = DeviceFoldStateProvider.this;
            deviceFoldStateProvider.progressHandler.post(new DeviceFoldStateProvider$timeoutRunnable$1(new Function0() { // from class: com.android.systemui.unfold.updates.DeviceFoldStateProvider$ScreenStatusListener$onScreenTurningOff$1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    DeviceFoldStateProvider deviceFoldStateProvider2 = DeviceFoldStateProvider.this;
                    deviceFoldStateProvider2.isScreenOn = false;
                    deviceFoldStateProvider2.assertInProgressThread$2();
                    boolean z = deviceFoldStateProvider2.isScreenOn;
                    HingeAngleProvider hingeAngleProvider = deviceFoldStateProvider2.hingeAngleProvider;
                    if (!z || deviceFoldStateProvider2.isFolded) {
                        hingeAngleProvider.stop();
                    } else {
                        hingeAngleProvider.start();
                    }
                    return Unit.INSTANCE;
                }
            }));
        }

        public final void onScreenTurningOn() {
            final DeviceFoldStateProvider deviceFoldStateProvider = DeviceFoldStateProvider.this;
            deviceFoldStateProvider.progressHandler.post(new DeviceFoldStateProvider$timeoutRunnable$1(new Function0() { // from class: com.android.systemui.unfold.updates.DeviceFoldStateProvider$ScreenStatusListener$onScreenTurningOn$1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    DeviceFoldStateProvider deviceFoldStateProvider2 = DeviceFoldStateProvider.this;
                    deviceFoldStateProvider2.isScreenOn = true;
                    deviceFoldStateProvider2.assertInProgressThread$2();
                    boolean z = deviceFoldStateProvider2.isScreenOn;
                    HingeAngleProvider hingeAngleProvider = deviceFoldStateProvider2.hingeAngleProvider;
                    if (!z || deviceFoldStateProvider2.isFolded) {
                        hingeAngleProvider.stop();
                    } else {
                        hingeAngleProvider.start();
                    }
                    return Unit.INSTANCE;
                }
            }));
        }
    }

    /* JADX WARN: Type inference failed for: r2v7, types: [com.android.systemui.unfold.updates.DeviceFoldStateProvider$progressExecutor$1] */
    public DeviceFoldStateProvider(ResourceUnfoldTransitionConfig resourceUnfoldTransitionConfig, Context context, LifecycleScreenStatusProvider lifecycleScreenStatusProvider, ActivityManagerActivityTypeProvider activityManagerActivityTypeProvider, UnfoldKeyguardVisibilityManagerImpl unfoldKeyguardVisibilityManagerImpl, FoldProvider foldProvider, HingeAngleProvider hingeAngleProvider, RotationChangeProvider rotationChangeProvider, Handler handler) {
        this.context = context;
        this.screenStatusProvider = lifecycleScreenStatusProvider;
        this.activityTypeProvider = activityManagerActivityTypeProvider;
        this.unfoldKeyguardVisibilityProvider = unfoldKeyguardVisibilityManagerImpl;
        this.foldProvider = foldProvider;
        this.hingeAngleProvider = hingeAngleProvider;
        this.rotationChangeProvider = rotationChangeProvider;
        this.progressHandler = handler;
        this.halfOpenedTimeoutMillis = ((Number) resourceUnfoldTransitionConfig.halfFoldedTimeoutMillis$delegate.getValue()).intValue();
    }

    @Override // com.android.systemui.unfold.util.CallbackController
    public final void addCallback(Object obj) {
        this.outputListeners.add((FoldStateProvider$FoldUpdatesListener) obj);
    }

    public final void assertInProgressThread$2() {
        Handler handler = this.progressHandler;
        if (handler.getLooper().isCurrentThread()) {
            return;
        }
        Thread thread = handler.getLooper().getThread();
        Thread currentThread = Thread.currentThread();
        throw new IllegalStateException(StringsKt__IndentKt.trimMargin$default("should be called from the progress thread.\n                progressThread=" + thread + " tid=" + thread.getId() + "\n                Thread.currentThread()=" + currentThread + " tid=" + currentThread.getId()).toString());
    }

    public final boolean isTransitionInProgress() {
        Integer num = this.lastFoldUpdate;
        if (num != null && num.intValue() == 0) {
            return true;
        }
        Integer num2 = this.lastFoldUpdate;
        return num2 != null && num2.intValue() == 1;
    }

    public final void notifyFoldUpdate(int i, float f) {
        if (DeviceFoldStateProviderKt.DEBUG) {
            Log.d("DeviceFoldProvider", DeviceFoldStateProviderKt.name(i));
        }
        boolean isTransitionInProgress = isTransitionInProgress();
        Iterator it = this.outputListeners.iterator();
        while (it.hasNext()) {
            ((FoldStateProvider$FoldUpdatesListener) it.next()).onFoldUpdate(i);
        }
        this.lastFoldUpdate = Integer.valueOf(i);
        if (isTransitionInProgress != isTransitionInProgress()) {
            this.lastHingeAngleBeforeTransition = f;
        }
    }

    @Override // com.android.systemui.unfold.util.CallbackController
    public final void removeCallback(Object obj) {
        this.outputListeners.remove((FoldStateProvider$FoldUpdatesListener) obj);
    }

    public final void start() {
        Boolean bool;
        ActivityManager.RunningTaskInfo runningTaskInfo;
        if (this.isStarted) {
            return;
        }
        this.foldProvider.registerCallback(this.foldStateListener, this.progressExecutor);
        this.screenStatusProvider.addCallback(this.screenListener);
        this.hingeAngleProvider.addCallback(this.hingeAngleListener);
        RotationChangeProvider rotationChangeProvider = this.rotationChangeProvider;
        FoldRotationListener foldRotationListener = this.rotationListener;
        rotationChangeProvider.getClass();
        rotationChangeProvider.bgHandler.post(new RotationChangeProvider$addCallback$1(rotationChangeProvider, foldRotationListener));
        ActivityManagerActivityTypeProvider activityManagerActivityTypeProvider = this.activityTypeProvider;
        ActivityManager activityManager = activityManagerActivityTypeProvider.activityManager;
        try {
            Trace.beginSection("isOnHomeActivity");
            List<ActivityManager.RunningTaskInfo> runningTasks = activityManager.getRunningTasks(1);
            if (runningTasks == null || (runningTaskInfo = (ActivityManager.RunningTaskInfo) CollectionsKt.firstOrNull((List) runningTasks)) == null) {
                bool = null;
            } else {
                bool = Boolean.valueOf(runningTaskInfo.topActivityType == 2);
            }
            Trace.endSection();
            activityManagerActivityTypeProvider._isHomeActivity = bool;
            TaskStackChangeListeners.INSTANCE.registerTaskStackListener(activityManagerActivityTypeProvider.taskStackChangeListener);
            this.isStarted = true;
        } catch (Throwable th) {
            Trace.endSection();
            throw th;
        }
    }
}

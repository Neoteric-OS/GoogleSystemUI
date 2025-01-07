package com.android.systemui.keyguard;

import android.app.IWallpaperManager;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.SystemClock;
import android.os.Trace;
import android.os.TraceNameSupplier;
import android.util.DisplayMetrics;
import com.android.systemui.keyguard.ScreenLifecycle;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.util.time.SystemClockImpl;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardLifecyclesDispatcher {
    public final KeyguardLifecycleHandler mHandler;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class KeyguardLifecycleHandler extends Handler {
        public final ScreenLifecycle mScreenLifecycle;
        public final WakefulnessLifecycle mWakefulnessLifecycle;

        public KeyguardLifecycleHandler(Looper looper, ScreenLifecycle screenLifecycle, WakefulnessLifecycle wakefulnessLifecycle) {
            super(looper);
            this.mScreenLifecycle = screenLifecycle;
            this.mWakefulnessLifecycle = wakefulnessLifecycle;
        }

        public final String getTraceName(Message message) {
            String str;
            if ((message.getCallback() instanceof TraceNameSupplier) || message.getCallback() != null) {
                return super.getTraceName(message);
            }
            switch (message.what) {
                case 0:
                    str = "SCREEN_TURNING_ON";
                    break;
                case 1:
                    str = "SCREEN_TURNED_ON";
                    break;
                case 2:
                    str = "SCREEN_TURNING_OFF";
                    break;
                case 3:
                    str = "SCREEN_TURNED_OFF";
                    break;
                case 4:
                    str = "STARTED_WAKING_UP";
                    break;
                case 5:
                    str = "FINISHED_WAKING_UP";
                    break;
                case 6:
                    str = "STARTED_GOING_TO_SLEEP";
                    break;
                case 7:
                    str = "FINISHED_GOING_TO_SLEEP";
                    break;
                default:
                    str = "UNKNOWN";
                    break;
            }
            return "KeyguardLifecycleHandler#".concat(str);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            int i = message.what;
            ScreenLifecycle screenLifecycle = this.mScreenLifecycle;
            WakefulnessLifecycle wakefulnessLifecycle = this.mWakefulnessLifecycle;
            switch (i) {
                case 0:
                    screenLifecycle.mScreenState = 1;
                    Trace.traceCounter(4096L, "screenState", 1);
                    final int i2 = 1;
                    screenLifecycle.dispatch(new Consumer() { // from class: com.android.systemui.keyguard.ScreenLifecycle$$ExternalSyntheticLambda0
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            ScreenLifecycle.Observer observer = (ScreenLifecycle.Observer) obj;
                            switch (i2) {
                                case 0:
                                    observer.onScreenTurnedOn();
                                    break;
                                case 1:
                                    observer.onScreenTurningOn();
                                    break;
                                case 2:
                                    observer.onScreenTurningOff();
                                    break;
                                default:
                                    observer.onScreenTurnedOff();
                                    break;
                            }
                        }
                    });
                    return;
                case 1:
                    screenLifecycle.mScreenState = 2;
                    Trace.traceCounter(4096L, "screenState", 2);
                    final int i3 = 0;
                    screenLifecycle.dispatch(new Consumer() { // from class: com.android.systemui.keyguard.ScreenLifecycle$$ExternalSyntheticLambda0
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            ScreenLifecycle.Observer observer = (ScreenLifecycle.Observer) obj;
                            switch (i3) {
                                case 0:
                                    observer.onScreenTurnedOn();
                                    break;
                                case 1:
                                    observer.onScreenTurningOn();
                                    break;
                                case 2:
                                    observer.onScreenTurningOff();
                                    break;
                                default:
                                    observer.onScreenTurnedOff();
                                    break;
                            }
                        }
                    });
                    return;
                case 2:
                    screenLifecycle.mScreenState = 3;
                    Trace.traceCounter(4096L, "screenState", 3);
                    final int i4 = 2;
                    screenLifecycle.dispatch(new Consumer() { // from class: com.android.systemui.keyguard.ScreenLifecycle$$ExternalSyntheticLambda0
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            ScreenLifecycle.Observer observer = (ScreenLifecycle.Observer) obj;
                            switch (i4) {
                                case 0:
                                    observer.onScreenTurnedOn();
                                    break;
                                case 1:
                                    observer.onScreenTurningOn();
                                    break;
                                case 2:
                                    observer.onScreenTurningOff();
                                    break;
                                default:
                                    observer.onScreenTurnedOff();
                                    break;
                            }
                        }
                    });
                    return;
                case 3:
                    screenLifecycle.mScreenState = 0;
                    Trace.traceCounter(4096L, "screenState", 0);
                    final int i5 = 3;
                    screenLifecycle.dispatch(new Consumer() { // from class: com.android.systemui.keyguard.ScreenLifecycle$$ExternalSyntheticLambda0
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            ScreenLifecycle.Observer observer = (ScreenLifecycle.Observer) obj;
                            switch (i5) {
                                case 0:
                                    observer.onScreenTurnedOn();
                                    break;
                                case 1:
                                    observer.onScreenTurningOn();
                                    break;
                                case 2:
                                    observer.onScreenTurningOff();
                                    break;
                                default:
                                    observer.onScreenTurnedOff();
                                    break;
                            }
                        }
                    });
                    return;
                case 4:
                    int i6 = message.arg1;
                    if (wakefulnessLifecycle.mWakefulness == 1) {
                        return;
                    }
                    wakefulnessLifecycle.mWakefulness = 1;
                    Trace.traceCounter(4096L, "wakefulness", 1);
                    wakefulnessLifecycle.mLastWakeReason = i6;
                    ((SystemClockImpl) wakefulnessLifecycle.mSystemClock).getClass();
                    SystemClock.uptimeMillis();
                    wakefulnessLifecycle.mLastWakeOriginLocation = null;
                    if (wakefulnessLifecycle.mLastWakeReason != 1) {
                        DisplayMetrics displayMetrics = wakefulnessLifecycle.mDisplayMetrics;
                        wakefulnessLifecycle.mLastWakeOriginLocation = new Point(displayMetrics.widthPixels / 2, displayMetrics.heightPixels);
                    } else {
                        wakefulnessLifecycle.mLastWakeOriginLocation = wakefulnessLifecycle.getPowerButtonOrigin();
                    }
                    IWallpaperManager iWallpaperManager = wakefulnessLifecycle.mWallpaperManagerService;
                    if (iWallpaperManager != null) {
                        try {
                            Point point = wakefulnessLifecycle.mLastWakeOriginLocation;
                            iWallpaperManager.notifyWakingUp(point.x, point.y, new Bundle());
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                    final int i7 = 0;
                    wakefulnessLifecycle.dispatch(new Consumer() { // from class: com.android.systemui.keyguard.WakefulnessLifecycle$$ExternalSyntheticLambda0
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            WakefulnessLifecycle.Observer observer = (WakefulnessLifecycle.Observer) obj;
                            switch (i7) {
                                case 0:
                                    observer.onStartedWakingUp();
                                    break;
                                case 1:
                                    observer.onFinishedWakingUp();
                                    break;
                                case 2:
                                    observer.onPostFinishedWakingUp();
                                    break;
                                case 3:
                                    observer.onFinishedGoingToSleep$1();
                                    break;
                                default:
                                    observer.onStartedGoingToSleep();
                                    break;
                            }
                        }
                    });
                    return;
                case 5:
                    if (wakefulnessLifecycle.mWakefulness == 2) {
                        return;
                    }
                    wakefulnessLifecycle.mWakefulness = 2;
                    Trace.traceCounter(4096L, "wakefulness", 2);
                    final int i8 = 1;
                    wakefulnessLifecycle.dispatch(new Consumer() { // from class: com.android.systemui.keyguard.WakefulnessLifecycle$$ExternalSyntheticLambda0
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            WakefulnessLifecycle.Observer observer = (WakefulnessLifecycle.Observer) obj;
                            switch (i8) {
                                case 0:
                                    observer.onStartedWakingUp();
                                    break;
                                case 1:
                                    observer.onFinishedWakingUp();
                                    break;
                                case 2:
                                    observer.onPostFinishedWakingUp();
                                    break;
                                case 3:
                                    observer.onFinishedGoingToSleep$1();
                                    break;
                                default:
                                    observer.onStartedGoingToSleep();
                                    break;
                            }
                        }
                    });
                    final int i9 = 2;
                    wakefulnessLifecycle.dispatch(new Consumer() { // from class: com.android.systemui.keyguard.WakefulnessLifecycle$$ExternalSyntheticLambda0
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            WakefulnessLifecycle.Observer observer = (WakefulnessLifecycle.Observer) obj;
                            switch (i9) {
                                case 0:
                                    observer.onStartedWakingUp();
                                    break;
                                case 1:
                                    observer.onFinishedWakingUp();
                                    break;
                                case 2:
                                    observer.onPostFinishedWakingUp();
                                    break;
                                case 3:
                                    observer.onFinishedGoingToSleep$1();
                                    break;
                                default:
                                    observer.onStartedGoingToSleep();
                                    break;
                            }
                        }
                    });
                    return;
                case 6:
                    int i10 = message.arg1;
                    if (wakefulnessLifecycle.mWakefulness == 3) {
                        return;
                    }
                    wakefulnessLifecycle.mWakefulness = 3;
                    Trace.traceCounter(4096L, "wakefulness", 3);
                    wakefulnessLifecycle.mLastSleepReason = i10;
                    wakefulnessLifecycle.mLastSleepOriginLocation = null;
                    if (i10 != 4) {
                        DisplayMetrics displayMetrics2 = wakefulnessLifecycle.mDisplayMetrics;
                        wakefulnessLifecycle.mLastSleepOriginLocation = new Point(displayMetrics2.widthPixels / 2, displayMetrics2.heightPixels);
                    } else {
                        wakefulnessLifecycle.mLastSleepOriginLocation = wakefulnessLifecycle.getPowerButtonOrigin();
                    }
                    IWallpaperManager iWallpaperManager2 = wakefulnessLifecycle.mWallpaperManagerService;
                    if (iWallpaperManager2 != null) {
                        try {
                            Point point2 = wakefulnessLifecycle.mLastSleepOriginLocation;
                            iWallpaperManager2.notifyGoingToSleep(point2.x, point2.y, new Bundle());
                        } catch (RemoteException e2) {
                            e2.printStackTrace();
                        }
                    }
                    final int i11 = 4;
                    wakefulnessLifecycle.dispatch(new Consumer() { // from class: com.android.systemui.keyguard.WakefulnessLifecycle$$ExternalSyntheticLambda0
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            WakefulnessLifecycle.Observer observer = (WakefulnessLifecycle.Observer) obj;
                            switch (i11) {
                                case 0:
                                    observer.onStartedWakingUp();
                                    break;
                                case 1:
                                    observer.onFinishedWakingUp();
                                    break;
                                case 2:
                                    observer.onPostFinishedWakingUp();
                                    break;
                                case 3:
                                    observer.onFinishedGoingToSleep$1();
                                    break;
                                default:
                                    observer.onStartedGoingToSleep();
                                    break;
                            }
                        }
                    });
                    return;
                case 7:
                    if (wakefulnessLifecycle.mWakefulness == 0) {
                        return;
                    }
                    wakefulnessLifecycle.mWakefulness = 0;
                    Trace.traceCounter(4096L, "wakefulness", 0);
                    final int i12 = 3;
                    wakefulnessLifecycle.dispatch(new Consumer() { // from class: com.android.systemui.keyguard.WakefulnessLifecycle$$ExternalSyntheticLambda0
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            WakefulnessLifecycle.Observer observer = (WakefulnessLifecycle.Observer) obj;
                            switch (i12) {
                                case 0:
                                    observer.onStartedWakingUp();
                                    break;
                                case 1:
                                    observer.onFinishedWakingUp();
                                    break;
                                case 2:
                                    observer.onPostFinishedWakingUp();
                                    break;
                                case 3:
                                    observer.onFinishedGoingToSleep$1();
                                    break;
                                default:
                                    observer.onStartedGoingToSleep();
                                    break;
                            }
                        }
                    });
                    return;
                default:
                    throw new IllegalArgumentException("Unknown message: " + message);
            }
        }
    }

    public KeyguardLifecyclesDispatcher(Looper looper, ScreenLifecycle screenLifecycle, WakefulnessLifecycle wakefulnessLifecycle) {
        this.mHandler = new KeyguardLifecycleHandler(looper, screenLifecycle, wakefulnessLifecycle);
    }
}

package com.android.systemui.doze;

import android.app.AlarmManager;
import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.text.format.Formatter;
import android.util.Log;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import androidx.appsearch.platformstorage.converter.GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0;
import com.android.app.tracing.TraceUtils;
import com.android.app.viewcapture.data.ViewNode;
import com.android.internal.util.Preconditions;
import com.android.systemui.clipboardoverlay.ClipboardOverlayController$$ExternalSyntheticLambda8;
import com.android.systemui.doze.DozeMachine;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.statusbar.StatusBarStateControllerImpl;
import com.android.systemui.statusbar.phone.DozeParameters;
import com.android.systemui.statusbar.phone.DozeServiceHost;
import com.android.systemui.statusbar.phone.DozeServiceHost$$ExternalSyntheticLambda2;
import com.android.systemui.util.AlarmTimeout;
import com.android.systemui.util.Assert;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.android.systemui.util.wakelock.WakeLock;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DozeUi implements DozeMachine.Part {
    public final DelayableExecutor mBgExecutor;
    public final boolean mCanAnimateTransition;
    public final Context mContext;
    public final DozeLog mDozeLog;
    public final DozeParameters mDozeParameters;
    public final Handler mHandler;
    public final DozeServiceHost mHost;
    public DozeMachine mMachine;
    public volatile boolean mTimeTickScheduled;
    public final AlarmTimeout mTimeTicker;
    public final WakeLock mWakeLock;
    public volatile long mLastTimeTickElapsed = 0;
    public final AnonymousClass1 mCancelTimeTickerRunnable = new Runnable() { // from class: com.android.systemui.doze.DozeUi.1
        @Override // java.lang.Runnable
        public final void run() {
            DozeUi dozeUi = DozeUi.this;
            DozeLog dozeLog = dozeUi.mDozeLog;
            boolean z = dozeUi.mTimeTickScheduled;
            DozeLogger dozeLogger = dozeLog.mLogger;
            dozeLogger.getClass();
            LogLevel logLevel = LogLevel.INFO;
            DozeLogger$logPendingUnscheduleTimeTick$2 dozeLogger$logPendingUnscheduleTimeTick$2 = DozeLogger$logPendingUnscheduleTimeTick$2.INSTANCE;
            LogBuffer logBuffer = dozeLogger.buffer;
            LogMessage obtain = logBuffer.obtain("DozeLog", logLevel, dozeLogger$logPendingUnscheduleTimeTick$2, null);
            LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
            logMessageImpl.bool1 = false;
            logMessageImpl.bool2 = z;
            logBuffer.commit(obtain);
            if (DozeUi.this.mTimeTickScheduled) {
                return;
            }
            DozeUi.this.mTimeTicker.cancel();
        }
    };

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.doze.DozeUi$2, reason: invalid class name */
    public final class AnonymousClass2 {
        public final /* synthetic */ int val$reason;

        public AnonymousClass2(int i) {
            this.val$reason = i;
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.android.systemui.doze.DozeUi$1] */
    public DozeUi(Context context, AlarmManager alarmManager, WakeLock wakeLock, DozeServiceHost dozeServiceHost, Handler handler, Handler handler2, DozeParameters dozeParameters, DelayableExecutor delayableExecutor, DozeLog dozeLog) {
        this.mContext = context;
        this.mWakeLock = wakeLock;
        this.mHost = dozeServiceHost;
        this.mHandler = handler;
        this.mBgExecutor = delayableExecutor;
        this.mCanAnimateTransition = !dozeParameters.getDisplayNeedsBlanking();
        this.mDozeParameters = dozeParameters;
        this.mTimeTicker = new AlarmTimeout(alarmManager, new AlarmManager.OnAlarmListener() { // from class: com.android.systemui.doze.DozeUi$$ExternalSyntheticLambda1
            @Override // android.app.AlarmManager.OnAlarmListener
            public final void onAlarm() {
                DozeUi dozeUi = DozeUi.this;
                dozeUi.getClass();
                long elapsedRealtime = SystemClock.elapsedRealtime() - dozeUi.mLastTimeTickElapsed;
                if (elapsedRealtime > 90000) {
                    String formatShortElapsedTime = Formatter.formatShortElapsedTime(dozeUi.mContext, elapsedRealtime);
                    DozeLogger dozeLogger = dozeUi.mDozeLog.mLogger;
                    dozeLogger.getClass();
                    LogLevel logLevel = LogLevel.ERROR;
                    DozeLogger$logMissedTick$2 dozeLogger$logMissedTick$2 = new Function1() { // from class: com.android.systemui.doze.DozeLogger$logMissedTick$2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Missed AOD time tick by ", ((LogMessage) obj).getStr1());
                        }
                    };
                    LogBuffer logBuffer = dozeLogger.buffer;
                    LogMessage obtain = logBuffer.obtain("DozeLog", logLevel, dozeLogger$logMissedTick$2, null);
                    ((LogMessageImpl) obtain).str1 = formatShortElapsedTime;
                    logBuffer.commit(obtain);
                    Log.e("DozeMachine", "Missed AOD time tick by " + formatShortElapsedTime);
                }
                Handler handler3 = dozeUi.mHandler;
                DozeServiceHost dozeServiceHost2 = dozeUi.mHost;
                Objects.requireNonNull(dozeServiceHost2);
                handler3.post(new DozeUi$$ExternalSyntheticLambda0(dozeServiceHost2));
                dozeUi.mHandler.post(dozeUi.mWakeLock.wrap(new ClipboardOverlayController$$ExternalSyntheticLambda8()));
                dozeUi.mTimeTickScheduled = false;
                dozeUi.scheduleTimeTick();
            }
        }, "doze_time_tick", handler2);
        this.mDozeLog = dozeLog;
    }

    public final void scheduleTimeTick() {
        if (this.mTimeTickScheduled) {
            return;
        }
        this.mTimeTickScheduled = true;
        long currentTimeMillis = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentTimeMillis);
        calendar.set(14, 0);
        calendar.set(13, 0);
        calendar.add(12, 1);
        long timeInMillis = calendar.getTimeInMillis() - System.currentTimeMillis();
        if (this.mTimeTicker.schedule(timeInMillis, 2)) {
            DozeLogger dozeLogger = this.mDozeLog.mLogger;
            dozeLogger.getClass();
            LogLevel logLevel = LogLevel.DEBUG;
            DozeLogger$logTimeTickScheduled$2 dozeLogger$logTimeTickScheduled$2 = new Function1() { // from class: com.android.systemui.doze.DozeLogger$logTimeTickScheduled$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    LogMessage logMessage = (LogMessage) obj;
                    SimpleDateFormat simpleDateFormat = DozeLoggerKt.DATE_FORMAT;
                    return GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("Time tick scheduledAt=", simpleDateFormat.format(new Date(logMessage.getLong1())), " triggerAt=", simpleDateFormat.format(new Date(logMessage.getLong2())));
                }
            };
            LogBuffer logBuffer = dozeLogger.buffer;
            LogMessage obtain = logBuffer.obtain("DozeLog", logLevel, dozeLogger$logTimeTickScheduled$2, null);
            LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
            logMessageImpl.long1 = currentTimeMillis;
            logMessageImpl.long2 = timeInMillis + currentTimeMillis;
            logBuffer.commit(obtain);
        }
        this.mLastTimeTickElapsed = SystemClock.elapsedRealtime();
    }

    @Override // com.android.systemui.doze.DozeMachine.Part
    public final void setDozeMachine(DozeMachine dozeMachine) {
        this.mMachine = dozeMachine;
    }

    @Override // com.android.systemui.doze.DozeMachine.Part
    public final void transitionTo(DozeMachine.State state, DozeMachine.State state2) {
        int ordinal = state2.ordinal();
        DozeServiceHost dozeServiceHost = this.mHost;
        boolean z = false;
        switch (ordinal) {
            case 1:
                if (!dozeServiceHost.mDozingRequested) {
                    dozeServiceHost.mDozingRequested = true;
                    dozeServiceHost.updateDozing();
                    dozeServiceHost.mDozeLog.traceDozing(((StatusBarStateControllerImpl) dozeServiceHost.mStatusBarStateController).mIsDozing);
                    dozeServiceHost.mCentralSurfaces.updateIsKeyguard(false);
                    break;
                }
                break;
            case 2:
            case 3:
            case 10:
                if (this.mTimeTickScheduled) {
                    this.mTimeTickScheduled = false;
                    DozeLog dozeLog = this.mDozeLog;
                    boolean z2 = this.mTimeTickScheduled;
                    DozeLogger dozeLogger = dozeLog.mLogger;
                    dozeLogger.getClass();
                    LogLevel logLevel = LogLevel.INFO;
                    DozeLogger$logPendingUnscheduleTimeTick$2 dozeLogger$logPendingUnscheduleTimeTick$2 = DozeLogger$logPendingUnscheduleTimeTick$2.INSTANCE;
                    LogBuffer logBuffer = dozeLogger.buffer;
                    LogMessage obtain = logBuffer.obtain("DozeLog", logLevel, dozeLogger$logPendingUnscheduleTimeTick$2, null);
                    LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
                    logMessageImpl.bool1 = true;
                    logMessageImpl.bool2 = z2;
                    logBuffer.commit(obtain);
                    ((ExecutorImpl) this.mBgExecutor).execute(this.mCancelTimeTickerRunnable);
                    break;
                }
                break;
            case 4:
            case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                if (state == DozeMachine.State.DOZE_AOD_PAUSED || state == DozeMachine.State.DOZE) {
                    dozeServiceHost.getClass();
                    TraceUtils.trace("DozeServiceHost#dozeTimeTick", new DozeServiceHost$$ExternalSyntheticLambda2(dozeServiceHost));
                    this.mHandler.postDelayed(this.mWakeLock.wrap(new DozeUi$$ExternalSyntheticLambda0(dozeServiceHost)), 500L);
                }
                scheduleTimeTick();
                break;
            case 5:
                scheduleTimeTick();
                DozeMachine dozeMachine = this.mMachine;
                dozeMachine.getClass();
                Assert.isMainThread();
                DozeMachine.State state3 = dozeMachine.mState;
                Preconditions.checkState(state3 == DozeMachine.State.DOZE_REQUEST_PULSE || state3 == DozeMachine.State.DOZE_PULSING || state3 == DozeMachine.State.DOZE_PULSING_BRIGHT || state3 == DozeMachine.State.DOZE_PULSE_DONE, "must be in pulsing state, but is " + dozeMachine.mState);
                int i = dozeMachine.mPulseReason;
                dozeServiceHost.pulseWhileDozing(new AnonymousClass2(i), i);
                break;
            case 9:
                if (dozeServiceHost.mDozingRequested) {
                    dozeServiceHost.mDozingRequested = false;
                    dozeServiceHost.updateDozing();
                    dozeServiceHost.mDozeLog.traceDozing(((StatusBarStateControllerImpl) dozeServiceHost.mStatusBarStateController).mIsDozing);
                }
                if (this.mTimeTickScheduled) {
                    this.mTimeTickScheduled = false;
                    DozeLog dozeLog2 = this.mDozeLog;
                    boolean z3 = this.mTimeTickScheduled;
                    DozeLogger dozeLogger2 = dozeLog2.mLogger;
                    dozeLogger2.getClass();
                    LogLevel logLevel2 = LogLevel.INFO;
                    DozeLogger$logPendingUnscheduleTimeTick$2 dozeLogger$logPendingUnscheduleTimeTick$22 = DozeLogger$logPendingUnscheduleTimeTick$2.INSTANCE;
                    LogBuffer logBuffer2 = dozeLogger2.buffer;
                    LogMessage obtain2 = logBuffer2.obtain("DozeLog", logLevel2, dozeLogger$logPendingUnscheduleTimeTick$22, null);
                    LogMessageImpl logMessageImpl2 = (LogMessageImpl) obtain2;
                    logMessageImpl2.bool1 = true;
                    logMessageImpl2.bool2 = z3;
                    logBuffer2.commit(obtain2);
                    ((ExecutorImpl) this.mBgExecutor).execute(this.mCancelTimeTickerRunnable);
                    break;
                }
                break;
            case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                scheduleTimeTick();
                break;
        }
        switch (state2.ordinal()) {
            case 5:
            case 6:
            case 7:
            case 8:
                int i2 = dozeServiceHost.mWakefulnessLifecycle.mWakefulness;
                if (i2 != 2 && i2 != 1) {
                    dozeServiceHost.mAnimateWakeup = true;
                    break;
                }
                break;
            case 9:
                break;
            default:
                if (this.mCanAnimateTransition && this.mDozeParameters.getAlwaysOn()) {
                    z = true;
                }
                int i3 = dozeServiceHost.mWakefulnessLifecycle.mWakefulness;
                if (i3 != 2 && i3 != 1) {
                    dozeServiceHost.mAnimateWakeup = z;
                    break;
                }
                break;
        }
    }
}

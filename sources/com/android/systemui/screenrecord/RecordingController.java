package com.android.systemui.screenrecord;

import android.app.BroadcastOptions;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Process;
import android.os.UserHandle;
import com.android.keyguard.logging.KeyguardUpdateMonitorLogger$allowFingerprintOnCurrentOccludingActivityChanged$2$$ExternalSyntheticOutline0;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.flags.FeatureFlagsClassicRelease;
import com.android.systemui.flags.Flags;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.mediaprojection.MediaProjectionMetricsLogger;
import com.android.systemui.mediaprojection.SessionCreationSource;
import com.android.systemui.mediaprojection.devicepolicy.ScreenCaptureDevicePolicyResolver;
import com.android.systemui.mediaprojection.devicepolicy.ScreenCaptureDisabledDialogDelegate;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.settings.UserContextProvider;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.systemui.statusbar.policy.CallbackController;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$29;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;
import dagger.Lazy;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class RecordingController implements CallbackController {
    public final BroadcastDispatcher mBroadcastDispatcher;
    public final Lazy mDevicePolicyResolver;
    public final FeatureFlags mFlags;
    public final Bundle mInteractiveBroadcastOption;
    public boolean mIsRecording;
    public boolean mIsStarting;
    public final Executor mMainExecutor;
    public final MediaProjectionMetricsLogger mMediaProjectionMetricsLogger;
    public final RecordingControllerLogger mRecordingControllerLogger;
    public final ScreenCaptureDisabledDialogDelegate mScreenCaptureDisabledDialogDelegate;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$29 mScreenRecordPermissionDialogDelegateFactory;
    public PendingIntent mStopIntent;
    public final UserTracker mUserTracker;
    public AnonymousClass3 mCountDownTimer = null;
    public final CopyOnWriteArrayList mListeners = new CopyOnWriteArrayList();
    final UserTracker.Callback mUserChangedCallback = new UserTracker.Callback() { // from class: com.android.systemui.screenrecord.RecordingController.1
        @Override // com.android.systemui.settings.UserTracker.Callback
        public final void onUserChanged(int i, Context context) {
            RecordingController.this.stopRecording$1();
        }
    };
    protected final BroadcastReceiver mStateChangeReceiver = new BroadcastReceiver() { // from class: com.android.systemui.screenrecord.RecordingController.2
        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            if (intent == null || !"com.android.systemui.screenrecord.UPDATE_STATE".equals(intent.getAction())) {
                return;
            }
            if (!intent.hasExtra("extra_state")) {
                RecordingControllerLogger recordingControllerLogger = RecordingController.this.mRecordingControllerLogger;
                recordingControllerLogger.getClass();
                LogLevel logLevel = LogLevel.ERROR;
                RecordingControllerLogger$logIntentMissingState$2 recordingControllerLogger$logIntentMissingState$2 = new Function1() { // from class: com.android.systemui.screenrecord.RecordingControllerLogger$logIntentMissingState$2
                    @Override // kotlin.jvm.functions.Function1
                    public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                        return "Received update intent with no state";
                    }
                };
                LogBuffer logBuffer = recordingControllerLogger.logger;
                logBuffer.commit(logBuffer.obtain("RecordingController", logLevel, recordingControllerLogger$logIntentMissingState$2, null));
                return;
            }
            boolean booleanExtra = intent.getBooleanExtra("extra_state", false);
            RecordingControllerLogger recordingControllerLogger2 = RecordingController.this.mRecordingControllerLogger;
            recordingControllerLogger2.getClass();
            LogLevel logLevel2 = LogLevel.DEBUG;
            RecordingControllerLogger$logIntentStateUpdated$2 recordingControllerLogger$logIntentStateUpdated$2 = new Function1() { // from class: com.android.systemui.screenrecord.RecordingControllerLogger$logIntentStateUpdated$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return KeyguardUpdateMonitorLogger$allowFingerprintOnCurrentOccludingActivityChanged$2$$ExternalSyntheticOutline0.m("Update intent has state. isRecording=", ((LogMessage) obj).getBool1());
                }
            };
            LogBuffer logBuffer2 = recordingControllerLogger2.logger;
            LogMessage obtain = logBuffer2.obtain("RecordingController", logLevel2, recordingControllerLogger$logIntentStateUpdated$2, null);
            ((LogMessageImpl) obtain).bool1 = booleanExtra;
            logBuffer2.commit(obtain);
            RecordingController.this.updateState(booleanExtra);
        }
    };

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.screenrecord.RecordingController$3, reason: invalid class name */
    public final class AnonymousClass3 extends CountDownTimer {
        public final /* synthetic */ PendingIntent val$startIntent;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(long j, PendingIntent pendingIntent) {
            super(j, 1000L);
            this.val$startIntent = pendingIntent;
        }

        @Override // android.os.CountDownTimer
        public final void onFinish() {
            RecordingController recordingController = RecordingController.this;
            recordingController.mIsStarting = false;
            recordingController.mIsRecording = true;
            Iterator it = recordingController.mListeners.iterator();
            while (it.hasNext()) {
                ((RecordingStateChangeCallback) it.next()).onCountdownEnd();
            }
            try {
                this.val$startIntent.send(RecordingController.this.mInteractiveBroadcastOption);
                RecordingController recordingController2 = RecordingController.this;
                ((UserTrackerImpl) recordingController2.mUserTracker).addCallback(recordingController2.mUserChangedCallback, recordingController2.mMainExecutor);
                IntentFilter intentFilter = new IntentFilter("com.android.systemui.screenrecord.UPDATE_STATE");
                RecordingController recordingController3 = RecordingController.this;
                recordingController3.mBroadcastDispatcher.registerReceiver(recordingController3.mStateChangeReceiver, intentFilter, null, UserHandle.ALL);
                RecordingControllerLogger recordingControllerLogger = RecordingController.this.mRecordingControllerLogger;
                recordingControllerLogger.getClass();
                LogLevel logLevel = LogLevel.DEBUG;
                RecordingControllerLogger$logSentStartIntent$2 recordingControllerLogger$logSentStartIntent$2 = new Function1() { // from class: com.android.systemui.screenrecord.RecordingControllerLogger$logSentStartIntent$2
                    @Override // kotlin.jvm.functions.Function1
                    public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                        return "Sent start intent";
                    }
                };
                LogBuffer logBuffer = recordingControllerLogger.logger;
                logBuffer.commit(logBuffer.obtain("RecordingController", logLevel, recordingControllerLogger$logSentStartIntent$2, null));
            } catch (PendingIntent.CanceledException e) {
                RecordingControllerLogger recordingControllerLogger2 = RecordingController.this.mRecordingControllerLogger;
                recordingControllerLogger2.getClass();
                LogLevel logLevel2 = LogLevel.ERROR;
                RecordingControllerLogger$logPendingIntentCancelled$2 recordingControllerLogger$logPendingIntentCancelled$2 = new Function1() { // from class: com.android.systemui.screenrecord.RecordingControllerLogger$logPendingIntentCancelled$2
                    @Override // kotlin.jvm.functions.Function1
                    public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                        return "Pending intent was cancelled";
                    }
                };
                LogBuffer logBuffer2 = recordingControllerLogger2.logger;
                logBuffer2.commit(logBuffer2.obtain("RecordingController", logLevel2, recordingControllerLogger$logPendingIntentCancelled$2, e));
            }
        }

        @Override // android.os.CountDownTimer
        public final void onTick(long j) {
            Iterator it = RecordingController.this.mListeners.iterator();
            while (it.hasNext()) {
                ((RecordingStateChangeCallback) it.next()).onCountdown(j);
            }
        }
    }

    public RecordingController(Executor executor, BroadcastDispatcher broadcastDispatcher, FeatureFlags featureFlags, Lazy lazy, UserTracker userTracker, RecordingControllerLogger recordingControllerLogger, MediaProjectionMetricsLogger mediaProjectionMetricsLogger, ScreenCaptureDisabledDialogDelegate screenCaptureDisabledDialogDelegate, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$29 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$29) {
        this.mMainExecutor = executor;
        this.mFlags = featureFlags;
        this.mDevicePolicyResolver = lazy;
        this.mBroadcastDispatcher = broadcastDispatcher;
        this.mUserTracker = userTracker;
        this.mRecordingControllerLogger = recordingControllerLogger;
        this.mMediaProjectionMetricsLogger = mediaProjectionMetricsLogger;
        this.mScreenCaptureDisabledDialogDelegate = screenCaptureDisabledDialogDelegate;
        this.mScreenRecordPermissionDialogDelegateFactory = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$29;
        BroadcastOptions makeBasic = BroadcastOptions.makeBasic();
        makeBasic.setInteractive(true);
        this.mInteractiveBroadcastOption = makeBasic.toBundle();
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void addCallback(Object obj) {
        this.mListeners.add((RecordingStateChangeCallback) obj);
    }

    public final void cancelCountdown$1() {
        AnonymousClass3 anonymousClass3 = this.mCountDownTimer;
        RecordingControllerLogger recordingControllerLogger = this.mRecordingControllerLogger;
        if (anonymousClass3 != null) {
            recordingControllerLogger.getClass();
            LogLevel logLevel = LogLevel.DEBUG;
            RecordingControllerLogger$logCountdownCancelled$2 recordingControllerLogger$logCountdownCancelled$2 = new Function1() { // from class: com.android.systemui.screenrecord.RecordingControllerLogger$logCountdownCancelled$2
                @Override // kotlin.jvm.functions.Function1
                public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    return "Record countdown cancelled";
                }
            };
            LogBuffer logBuffer = recordingControllerLogger.logger;
            logBuffer.commit(logBuffer.obtain("RecordingController", logLevel, recordingControllerLogger$logCountdownCancelled$2, null));
            this.mCountDownTimer.cancel();
        } else {
            recordingControllerLogger.getClass();
            LogLevel logLevel2 = LogLevel.ERROR;
            RecordingControllerLogger$logCountdownCancelErrorNoTimer$2 recordingControllerLogger$logCountdownCancelErrorNoTimer$2 = new Function1() { // from class: com.android.systemui.screenrecord.RecordingControllerLogger$logCountdownCancelErrorNoTimer$2
                @Override // kotlin.jvm.functions.Function1
                public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    return "Couldn't cancel countdown because timer was null";
                }
            };
            LogBuffer logBuffer2 = recordingControllerLogger.logger;
            logBuffer2.commit(logBuffer2.obtain("RecordingController", logLevel2, recordingControllerLogger$logCountdownCancelErrorNoTimer$2, null));
        }
        this.mIsStarting = false;
        Iterator it = this.mListeners.iterator();
        while (it.hasNext()) {
            ((RecordingStateChangeCallback) it.next()).onCountdownEnd();
        }
    }

    public final SystemUIDialog createScreenRecordDialog(Runnable runnable) {
        if (((FeatureFlagsClassicRelease) this.mFlags).isEnabled(Flags.WM_ENABLE_PARTIAL_SCREEN_SHARING_ENTERPRISE_POLICIES) && ((ScreenCaptureDevicePolicyResolver) this.mDevicePolicyResolver.get()).isScreenCaptureCompletelyDisabled(UserHandle.of(UserHandle.myUserId()))) {
            ScreenCaptureDisabledDialogDelegate screenCaptureDisabledDialogDelegate = this.mScreenCaptureDisabledDialogDelegate;
            screenCaptureDisabledDialogDelegate.getClass();
            SystemUIDialog systemUIDialog = new SystemUIDialog(screenCaptureDisabledDialogDelegate.context);
            screenCaptureDisabledDialogDelegate.initDialog(systemUIDialog);
            return systemUIDialog;
        }
        this.mMediaProjectionMetricsLogger.notifyProjectionInitiated(Process.myUid(), SessionCreationSource.SYSTEM_UI_SCREEN_RECORDER);
        UserHandle of = UserHandle.of(UserHandle.myUserId());
        int myUid = Process.myUid();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$29 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$29 = this.mScreenRecordPermissionDialogDelegateFactory;
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$29.getClass();
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$29.this$0;
        ActivityStarter activityStarter = (ActivityStarter) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) switchingProvider.wMComponentImpl).activityStarterImplProvider.get();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) switchingProvider.wMComponentImpl;
        return new ScreenRecordPermissionDialogDelegate(of, myUid, this, activityStarter, (UserContextProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideUserTrackerProvider.get(), runnable, (MediaProjectionMetricsLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.mediaProjectionMetricsLoggerProvider.get(), (SystemUIDialog.Factory) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.factoryProvider6.get(), (Context) switchingProvider.sysUIGoogleGlobalRootComponentImpl.provideApplicationContextProvider.get()).createDialog();
    }

    public final synchronized boolean isRecording() {
        return this.mIsRecording;
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void removeCallback(Object obj) {
        this.mListeners.remove((RecordingStateChangeCallback) obj);
    }

    public final void stopRecording$1() {
        RecordingControllerLogger recordingControllerLogger = this.mRecordingControllerLogger;
        try {
            if (this.mStopIntent != null) {
                recordingControllerLogger.getClass();
                LogLevel logLevel = LogLevel.DEBUG;
                RecordingControllerLogger$logRecordingStopped$2 recordingControllerLogger$logRecordingStopped$2 = new Function1() { // from class: com.android.systemui.screenrecord.RecordingControllerLogger$logRecordingStopped$2
                    @Override // kotlin.jvm.functions.Function1
                    public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                        return "Stopping recording";
                    }
                };
                LogBuffer logBuffer = recordingControllerLogger.logger;
                logBuffer.commit(logBuffer.obtain("RecordingController", logLevel, recordingControllerLogger$logRecordingStopped$2, null));
                this.mStopIntent.send(this.mInteractiveBroadcastOption);
            } else {
                recordingControllerLogger.getClass();
                LogLevel logLevel2 = LogLevel.ERROR;
                RecordingControllerLogger$logRecordingStopErrorNoStopIntent$2 recordingControllerLogger$logRecordingStopErrorNoStopIntent$2 = new Function1() { // from class: com.android.systemui.screenrecord.RecordingControllerLogger$logRecordingStopErrorNoStopIntent$2
                    @Override // kotlin.jvm.functions.Function1
                    public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                        return "Couldn't stop recording because stop intent was null";
                    }
                };
                LogBuffer logBuffer2 = recordingControllerLogger.logger;
                logBuffer2.commit(logBuffer2.obtain("RecordingController", logLevel2, recordingControllerLogger$logRecordingStopErrorNoStopIntent$2, null));
            }
            updateState(false);
        } catch (PendingIntent.CanceledException e) {
            recordingControllerLogger.getClass();
            LogLevel logLevel3 = LogLevel.DEBUG;
            RecordingControllerLogger$logRecordingStopError$2 recordingControllerLogger$logRecordingStopError$2 = new Function1() { // from class: com.android.systemui.screenrecord.RecordingControllerLogger$logRecordingStopError$2
                @Override // kotlin.jvm.functions.Function1
                public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    return "Couldn't stop recording";
                }
            };
            LogBuffer logBuffer3 = recordingControllerLogger.logger;
            logBuffer3.commit(logBuffer3.obtain("RecordingController", logLevel3, recordingControllerLogger$logRecordingStopError$2, e));
        }
    }

    public final synchronized void updateState(boolean z) {
        try {
            RecordingControllerLogger recordingControllerLogger = this.mRecordingControllerLogger;
            recordingControllerLogger.getClass();
            LogLevel logLevel = LogLevel.DEBUG;
            RecordingControllerLogger$logStateUpdated$2 recordingControllerLogger$logStateUpdated$2 = new Function1() { // from class: com.android.systemui.screenrecord.RecordingControllerLogger$logStateUpdated$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return KeyguardUpdateMonitorLogger$allowFingerprintOnCurrentOccludingActivityChanged$2$$ExternalSyntheticOutline0.m("Updating state. isRecording=", ((LogMessage) obj).getBool1());
                }
            };
            LogBuffer logBuffer = recordingControllerLogger.logger;
            LogMessage obtain = logBuffer.obtain("RecordingController", logLevel, recordingControllerLogger$logStateUpdated$2, null);
            ((LogMessageImpl) obtain).bool1 = z;
            logBuffer.commit(obtain);
            if (!z && this.mIsRecording) {
                ((UserTrackerImpl) this.mUserTracker).removeCallback(this.mUserChangedCallback);
                this.mBroadcastDispatcher.unregisterReceiver(this.mStateChangeReceiver);
            }
            this.mIsRecording = z;
            Iterator it = this.mListeners.iterator();
            while (it.hasNext()) {
                RecordingStateChangeCallback recordingStateChangeCallback = (RecordingStateChangeCallback) it.next();
                if (z) {
                    recordingStateChangeCallback.onRecordingStart();
                } else {
                    recordingStateChangeCallback.onRecordingEnd();
                }
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface RecordingStateChangeCallback {
        default void onCountdown(long j) {
        }

        default void onCountdownEnd() {
        }

        default void onRecordingEnd() {
        }

        default void onRecordingStart() {
        }
    }
}

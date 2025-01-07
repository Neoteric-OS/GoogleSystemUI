package com.android.systemui.media.controls.domain.pipeline;

import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import androidx.appsearch.platformstorage.converter.GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0;
import androidx.room.TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.media.controls.domain.pipeline.LegacyMediaDataManagerImpl;
import com.android.systemui.media.controls.domain.pipeline.MediaDataManager;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$onMediaDataLoaded$2$1;
import com.android.systemui.media.controls.shared.model.MediaData;
import com.android.systemui.media.controls.shared.model.SmartspaceMediaData;
import com.android.systemui.media.controls.util.MediaControllerFactory;
import com.android.systemui.media.controls.util.MediaFlags;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.NotificationMediaManager;
import com.android.systemui.statusbar.StatusBarStateControllerImpl;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.util.time.SystemClockImpl;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaTimeoutListener implements MediaDataManager.Listener {
    public final Executor bgExecutor;
    public final MediaTimeoutLogger logger;
    public final DelayableExecutor mainExecutor;
    public final MediaControllerFactory mediaControllerFactory;
    public final MediaFlags mediaFlags;
    public final Map mediaListeners = new LinkedHashMap();
    public final Map recommendationListeners = new LinkedHashMap();
    public Function1 sessionCallback;
    public Function2 stateCallback;
    public final SystemClock systemClock;
    public Function2 timeoutCallback;
    public final Executor uiExecutor;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class PlaybackStateListener extends MediaController.Callback {
        public ExecutorImpl.ExecutionToken cancellation;
        public boolean destroyed;
        public long expiration = Long.MAX_VALUE;
        public String key;
        public PlaybackState lastState;
        public MediaController mediaController;
        public Boolean resumption;
        public boolean timedOut;

        public PlaybackStateListener(String str, MediaData mediaData) {
            this.key = str;
            MediaTimeoutListener.this.bgExecutor.execute(new MediaTimeoutListener$onMediaDataLoaded$2$1.AnonymousClass1(2, this, mediaData));
        }

        public final void doTimeout() {
            this.cancellation = null;
            MediaTimeoutLogger mediaTimeoutLogger = MediaTimeoutListener.this.logger;
            String str = this.key;
            mediaTimeoutLogger.getClass();
            LogLevel logLevel = LogLevel.DEBUG;
            MediaTimeoutLogger$logTimeout$2 mediaTimeoutLogger$logTimeout$2 = new Function1() { // from class: com.android.systemui.media.controls.domain.pipeline.MediaTimeoutLogger$logTimeout$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("execute timeout for ", ((LogMessage) obj).getStr1());
                }
            };
            LogBuffer logBuffer = mediaTimeoutLogger.buffer;
            LogMessage obtain = logBuffer.obtain("MediaTimeout", logLevel, mediaTimeoutLogger$logTimeout$2, null);
            ((LogMessageImpl) obtain).str1 = str;
            logBuffer.commit(obtain);
            this.timedOut = true;
            this.expiration = Long.MAX_VALUE;
            Function2 function2 = MediaTimeoutListener.this.timeoutCallback;
            ((LegacyMediaDataManagerImpl.AnonymousClass1) (function2 != null ? function2 : null)).invoke(this.key, Boolean.TRUE);
        }

        public final void expireMediaTimeout(String str, String str2) {
            ExecutorImpl.ExecutionToken executionToken = this.cancellation;
            if (executionToken != null) {
                MediaTimeoutLogger mediaTimeoutLogger = MediaTimeoutListener.this.logger;
                mediaTimeoutLogger.getClass();
                LogLevel logLevel = LogLevel.VERBOSE;
                MediaTimeoutLogger$logTimeoutCancelled$2 mediaTimeoutLogger$logTimeoutCancelled$2 = new Function1() { // from class: com.android.systemui.media.controls.domain.pipeline.MediaTimeoutLogger$logTimeoutCancelled$2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        LogMessage logMessage = (LogMessage) obj;
                        return GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("timeout cancelled for ", logMessage.getStr1(), ", reason: ", logMessage.getStr2());
                    }
                };
                LogBuffer logBuffer = mediaTimeoutLogger.buffer;
                LogMessage obtain = logBuffer.obtain("MediaTimeout", logLevel, mediaTimeoutLogger$logTimeoutCancelled$2, null);
                LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
                logMessageImpl.str1 = str;
                logMessageImpl.str2 = str2;
                logBuffer.commit(obtain);
                executionToken.run();
            }
            this.expiration = Long.MAX_VALUE;
            this.cancellation = null;
        }

        public final boolean isPlaying$1() {
            PlaybackState playbackState = this.lastState;
            if (playbackState != null) {
                return NotificationMediaManager.isPlayingState(playbackState.getState());
            }
            return false;
        }

        @Override // android.media.session.MediaController.Callback
        public final void onPlaybackStateChanged(PlaybackState playbackState) {
            processState(playbackState, true, this.resumption);
        }

        @Override // android.media.session.MediaController.Callback
        public final void onSessionDestroyed() {
            MediaTimeoutLogger mediaTimeoutLogger = MediaTimeoutListener.this.logger;
            String str = this.key;
            mediaTimeoutLogger.getClass();
            LogLevel logLevel = LogLevel.DEBUG;
            MediaTimeoutLogger$logSessionDestroyed$2 mediaTimeoutLogger$logSessionDestroyed$2 = new Function1() { // from class: com.android.systemui.media.controls.domain.pipeline.MediaTimeoutLogger$logSessionDestroyed$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("session destroyed ", ((LogMessage) obj).getStr1());
                }
            };
            LogBuffer logBuffer = mediaTimeoutLogger.buffer;
            LogMessage obtain = logBuffer.obtain("MediaTimeout", logLevel, mediaTimeoutLogger$logSessionDestroyed$2, null);
            ((LogMessageImpl) obtain).str1 = str;
            logBuffer.commit(obtain);
            if (Intrinsics.areEqual(this.resumption, Boolean.TRUE)) {
                MediaTimeoutListener.this.bgExecutor.execute(new MediaTimeoutListener$PlaybackStateListener$destroy$1(this, 1));
                return;
            }
            Function1 function1 = MediaTimeoutListener.this.sessionCallback;
            ((LegacyMediaDataManagerImpl.AnonymousClass3) (function1 != null ? function1 : null)).invoke(this.key);
            MediaTimeoutListener.this.bgExecutor.execute(new MediaTimeoutListener$PlaybackStateListener$destroy$1(this, 0));
            ExecutorImpl.ExecutionToken executionToken = this.cancellation;
            if (executionToken != null) {
                executionToken.run();
            }
            this.destroyed = true;
        }

        /* JADX WARN: Code restructure failed: missing block: B:22:0x0073, code lost:
        
            if (com.android.systemui.media.controls.domain.pipeline.MediaProcessingHelperKt.areCustomActionListsEqual(r6 != null ? r6.getCustomActions() : null, r11 != null ? r11.getCustomActions() : null) != false) goto L32;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void processState(final android.media.session.PlaybackState r11, boolean r12, java.lang.Boolean r13) {
            /*
                Method dump skipped, instructions count: 378
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener.PlaybackStateListener.processState(android.media.session.PlaybackState, boolean, java.lang.Boolean):void");
        }

        public final void setMediaData(MediaData mediaData) {
            this.destroyed = false;
            MediaController mediaController = this.mediaController;
            if (mediaController != null) {
                mediaController.unregisterCallback(this);
            }
            MediaSession.Token token = mediaData.token;
            MediaController create = token != null ? MediaTimeoutListener.this.mediaControllerFactory.create(token) : null;
            this.mediaController = create;
            if (create != null) {
                create.registerCallback(this);
            }
            MediaController mediaController2 = this.mediaController;
            processState(mediaController2 != null ? mediaController2.getPlaybackState() : null, false, Boolean.valueOf(mediaData.resumption));
        }
    }

    public MediaTimeoutListener(MediaControllerFactory mediaControllerFactory, Executor executor, Executor executor2, DelayableExecutor delayableExecutor, MediaTimeoutLogger mediaTimeoutLogger, SysuiStatusBarStateController sysuiStatusBarStateController, SystemClock systemClock, MediaFlags mediaFlags) {
        this.mediaControllerFactory = mediaControllerFactory;
        this.bgExecutor = executor;
        this.uiExecutor = executor2;
        this.mainExecutor = delayableExecutor;
        this.logger = mediaTimeoutLogger;
        this.systemClock = systemClock;
        this.mediaFlags = mediaFlags;
        ((StatusBarStateControllerImpl) sysuiStatusBarStateController).addCallback(new StatusBarStateController.StateListener() { // from class: com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener.1
            @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
            public final void onDozingChanged(boolean z) {
                if (z) {
                    return;
                }
                MediaTimeoutListener mediaTimeoutListener = MediaTimeoutListener.this;
                for (Map.Entry entry : mediaTimeoutListener.mediaListeners.entrySet()) {
                    String str = (String) entry.getKey();
                    PlaybackStateListener playbackStateListener = (PlaybackStateListener) entry.getValue();
                    if (playbackStateListener.cancellation != null) {
                        long j = playbackStateListener.expiration;
                        ((SystemClockImpl) mediaTimeoutListener.systemClock).getClass();
                        if (j <= android.os.SystemClock.elapsedRealtime()) {
                            playbackStateListener.expireMediaTimeout(str, "timeout happened while dozing");
                            playbackStateListener.doTimeout();
                        }
                    }
                }
                Iterator it = mediaTimeoutListener.recommendationListeners.entrySet().iterator();
                if (it.hasNext()) {
                    Map.Entry entry2 = (Map.Entry) it.next();
                    entry2.getValue().getClass();
                    throw new ClassCastException();
                }
            }
        });
    }

    @Override // com.android.systemui.media.controls.domain.pipeline.MediaDataManager.Listener
    public final void onMediaDataLoaded(final String str, String str2, final MediaData mediaData, boolean z, int i, boolean z2) {
        Object obj;
        PlaybackStateListener playbackStateListener = (PlaybackStateListener) this.mediaListeners.get(str);
        MediaTimeoutLogger mediaTimeoutLogger = this.logger;
        if (playbackStateListener == null) {
            obj = null;
        } else {
            if (!playbackStateListener.destroyed) {
                return;
            }
            mediaTimeoutLogger.getClass();
            LogLevel logLevel = LogLevel.DEBUG;
            MediaTimeoutLogger$logReuseListener$2 mediaTimeoutLogger$logReuseListener$2 = new Function1() { // from class: com.android.systemui.media.controls.domain.pipeline.MediaTimeoutLogger$logReuseListener$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("reuse listener: ", ((LogMessage) obj2).getStr1());
                }
            };
            LogBuffer logBuffer = mediaTimeoutLogger.buffer;
            LogMessage obtain = logBuffer.obtain("MediaTimeout", logLevel, mediaTimeoutLogger$logReuseListener$2, null);
            ((LogMessageImpl) obtain).str1 = str;
            logBuffer.commit(obtain);
            obj = playbackStateListener;
        }
        if (str2 != null && !str.equals(str2)) {
            obj = TypeIntrinsics.asMutableMap(this.mediaListeners).remove(str2);
            boolean z3 = obj != null;
            mediaTimeoutLogger.getClass();
            LogLevel logLevel2 = LogLevel.DEBUG;
            MediaTimeoutLogger$logMigrateListener$2 mediaTimeoutLogger$logMigrateListener$2 = new Function1() { // from class: com.android.systemui.media.controls.domain.pipeline.MediaTimeoutLogger$logMigrateListener$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    LogMessage logMessage = (LogMessage) obj2;
                    String str1 = logMessage.getStr1();
                    String str22 = logMessage.getStr2();
                    boolean bool1 = logMessage.getBool1();
                    StringBuilder m = TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0.m("migrate from ", str1, " to ", str22, ", had listener? ");
                    m.append(bool1);
                    return m.toString();
                }
            };
            LogBuffer logBuffer2 = mediaTimeoutLogger.buffer;
            LogMessage obtain2 = logBuffer2.obtain("MediaTimeout", logLevel2, mediaTimeoutLogger$logMigrateListener$2, null);
            LogMessageImpl logMessageImpl = (LogMessageImpl) obtain2;
            logMessageImpl.str1 = str2;
            logMessageImpl.str2 = str;
            logMessageImpl.bool1 = z3;
            logBuffer2.commit(obtain2);
        }
        final PlaybackStateListener playbackStateListener2 = (PlaybackStateListener) obj;
        if (playbackStateListener2 != null) {
            this.bgExecutor.execute(new Runnable() { // from class: com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$onMediaDataLoaded$2$1

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$onMediaDataLoaded$2$1$1, reason: invalid class name */
                public final class AnonymousClass1 implements Runnable {
                    public final /* synthetic */ Object $key;
                    public final /* synthetic */ int $r8$classId;
                    public final /* synthetic */ Object this$0;

                    public /* synthetic */ AnonymousClass1(int i, Object obj, Object obj2) {
                        this.$r8$classId = i;
                        this.this$0 = obj;
                        this.$key = obj2;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        switch (this.$r8$classId) {
                            case 0:
                                MediaTimeoutListener.PlaybackStateListener playbackStateListener = (MediaTimeoutListener.PlaybackStateListener) ((MediaTimeoutListener) this.this$0).mediaListeners.get((String) this.$key);
                                if (playbackStateListener != null && playbackStateListener.isPlaying$1()) {
                                    MediaTimeoutLogger mediaTimeoutLogger = ((MediaTimeoutListener) this.this$0).logger;
                                    String str = (String) this.$key;
                                    mediaTimeoutLogger.getClass();
                                    LogLevel logLevel = LogLevel.DEBUG;
                                    MediaTimeoutLogger$logDelayedUpdate$2 mediaTimeoutLogger$logDelayedUpdate$2 = MediaTimeoutLogger$logDelayedUpdate$2.INSTANCE;
                                    LogBuffer logBuffer = mediaTimeoutLogger.buffer;
                                    LogMessage obtain = logBuffer.obtain("MediaTimeout", logLevel, mediaTimeoutLogger$logDelayedUpdate$2, null);
                                    ((LogMessageImpl) obtain).str1 = str;
                                    logBuffer.commit(obtain);
                                    Function2 function2 = ((MediaTimeoutListener) this.this$0).timeoutCallback;
                                    ((LegacyMediaDataManagerImpl.AnonymousClass1) (function2 != null ? function2 : null)).invoke((String) this.$key, Boolean.FALSE);
                                    break;
                                }
                                break;
                            case 1:
                                Function2 function22 = ((MediaTimeoutListener) this.this$0).timeoutCallback;
                                if (function22 == null) {
                                    function22 = null;
                                }
                                MediaTimeoutListener.PlaybackStateListener playbackStateListener2 = (MediaTimeoutListener.PlaybackStateListener) this.$key;
                                ((LegacyMediaDataManagerImpl.AnonymousClass1) function22).invoke(playbackStateListener2.key, Boolean.valueOf(playbackStateListener2.timedOut));
                                break;
                            default:
                                ((MediaTimeoutListener.PlaybackStateListener) this.this$0).setMediaData((MediaData) this.$key);
                                break;
                        }
                    }
                }

                @Override // java.lang.Runnable
                public final void run() {
                    boolean isPlaying$1 = MediaTimeoutListener.PlaybackStateListener.this.isPlaying$1();
                    MediaTimeoutLogger mediaTimeoutLogger2 = this.logger;
                    String str3 = str;
                    mediaTimeoutLogger2.getClass();
                    LogLevel logLevel3 = LogLevel.DEBUG;
                    MediaTimeoutLogger$logUpdateListener$2 mediaTimeoutLogger$logUpdateListener$2 = new Function1() { // from class: com.android.systemui.media.controls.domain.pipeline.MediaTimeoutLogger$logUpdateListener$2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            LogMessage logMessage = (LogMessage) obj2;
                            return "updating " + logMessage.getStr1() + ", was playing? " + logMessage.getBool1();
                        }
                    };
                    LogBuffer logBuffer3 = mediaTimeoutLogger2.buffer;
                    LogMessage obtain3 = logBuffer3.obtain("MediaTimeout", logLevel3, mediaTimeoutLogger$logUpdateListener$2, null);
                    LogMessageImpl logMessageImpl2 = (LogMessageImpl) obtain3;
                    logMessageImpl2.str1 = str3;
                    logMessageImpl2.bool1 = isPlaying$1;
                    logBuffer3.commit(obtain3);
                    MediaTimeoutListener.PlaybackStateListener.this.setMediaData(mediaData);
                    MediaTimeoutListener.PlaybackStateListener playbackStateListener3 = MediaTimeoutListener.PlaybackStateListener.this;
                    String str4 = str;
                    playbackStateListener3.key = str4;
                    this.mediaListeners.put(str4, playbackStateListener3);
                    if (isPlaying$1 != MediaTimeoutListener.PlaybackStateListener.this.isPlaying$1()) {
                        MediaTimeoutListener mediaTimeoutListener = this;
                        ExecutorImpl executorImpl = (ExecutorImpl) mediaTimeoutListener.mainExecutor;
                        executorImpl.execute(new AnonymousClass1(0, mediaTimeoutListener, str));
                    }
                }
            });
        } else {
            this.mediaListeners.put(str, new PlaybackStateListener(str, mediaData));
        }
    }

    @Override // com.android.systemui.media.controls.domain.pipeline.MediaDataManager.Listener
    public final void onMediaDataRemoved(String str, boolean z) {
        PlaybackStateListener playbackStateListener = (PlaybackStateListener) this.mediaListeners.remove(str);
        if (playbackStateListener != null) {
            MediaTimeoutListener.this.bgExecutor.execute(new MediaTimeoutListener$PlaybackStateListener$destroy$1(playbackStateListener, 0));
            ExecutorImpl.ExecutionToken executionToken = playbackStateListener.cancellation;
            if (executionToken != null) {
                executionToken.run();
            }
            playbackStateListener.destroyed = true;
        }
    }

    @Override // com.android.systemui.media.controls.domain.pipeline.MediaDataManager.Listener
    public final void onSmartspaceMediaDataLoaded(String str, SmartspaceMediaData smartspaceMediaData, boolean z) {
        this.mediaFlags.isPersistentSsCardEnabled();
    }

    @Override // com.android.systemui.media.controls.domain.pipeline.MediaDataManager.Listener
    public final void onSmartspaceMediaDataRemoved(String str, boolean z) {
        this.mediaFlags.isPersistentSsCardEnabled();
    }
}

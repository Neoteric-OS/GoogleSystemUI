package com.android.systemui.media.controls.domain.resume;

import android.content.ComponentName;
import android.content.Context;
import android.media.MediaDescription;
import android.media.browse.MediaBrowser;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import androidx.appsearch.platformstorage.converter.GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import java.util.List;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ResumeMediaBrowser {
    public final MediaBrowserFactory mBrowserFactory;
    public final Callback mCallback;
    public final ComponentName mComponentName;
    public final Context mContext;
    public final ResumeMediaBrowserLogger mLogger;
    public MediaBrowser mMediaBrowser;
    public MediaController mMediaController;
    public final int mUserId;
    public final SessionDestroyCallback mMediaControllerCallback = new SessionDestroyCallback();
    public final AnonymousClass1 mSubscriptionCallback = new MediaBrowser.SubscriptionCallback() { // from class: com.android.systemui.media.controls.domain.resume.ResumeMediaBrowser.1
        @Override // android.media.browse.MediaBrowser.SubscriptionCallback
        public final void onChildrenLoaded(String str, List list) {
            ResumeMediaBrowser resumeMediaBrowser;
            MediaBrowser mediaBrowser;
            if (list.size() == 0) {
                Log.d("ResumeMediaBrowser", "No children found for " + ResumeMediaBrowser.this.mComponentName);
                Callback callback = ResumeMediaBrowser.this.mCallback;
                if (callback != null) {
                    callback.onError();
                }
            } else {
                MediaBrowser.MediaItem mediaItem = (MediaBrowser.MediaItem) list.get(0);
                MediaDescription description = mediaItem.getDescription();
                if (!mediaItem.isPlayable() || (mediaBrowser = (resumeMediaBrowser = ResumeMediaBrowser.this).mMediaBrowser) == null) {
                    Log.d("ResumeMediaBrowser", "Child found but not playable for " + ResumeMediaBrowser.this.mComponentName);
                    Callback callback2 = ResumeMediaBrowser.this.mCallback;
                    if (callback2 != null) {
                        callback2.onError();
                    }
                } else {
                    Callback callback3 = resumeMediaBrowser.mCallback;
                    if (callback3 != null) {
                        callback3.addTrack(description, mediaBrowser.getServiceComponent(), ResumeMediaBrowser.this);
                    }
                }
            }
            ResumeMediaBrowser.this.disconnect();
        }

        @Override // android.media.browse.MediaBrowser.SubscriptionCallback
        public final void onError(String str) {
            Log.d("ResumeMediaBrowser", "Subscribe error for " + ResumeMediaBrowser.this.mComponentName + ": " + str);
            Callback callback = ResumeMediaBrowser.this.mCallback;
            if (callback != null) {
                callback.onError();
            }
            ResumeMediaBrowser.this.disconnect();
        }

        @Override // android.media.browse.MediaBrowser.SubscriptionCallback
        public final void onError(String str, Bundle bundle) {
            Log.d("ResumeMediaBrowser", "Subscribe error for " + ResumeMediaBrowser.this.mComponentName + ": " + str + ", options: " + bundle);
            Callback callback = ResumeMediaBrowser.this.mCallback;
            if (callback != null) {
                callback.onError();
            }
            ResumeMediaBrowser.this.disconnect();
        }
    };
    public final AnonymousClass2 mConnectionCallback = new AnonymousClass2(this, 0);

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.media.controls.domain.resume.ResumeMediaBrowser$2, reason: invalid class name */
    public final class AnonymousClass2 extends MediaBrowser.ConnectionCallback {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ ResumeMediaBrowser this$0;

        public /* synthetic */ AnonymousClass2(ResumeMediaBrowser resumeMediaBrowser, int i) {
            this.$r8$classId = i;
            this.this$0 = resumeMediaBrowser;
        }

        @Override // android.media.browse.MediaBrowser.ConnectionCallback
        public final void onConnected() {
            switch (this.$r8$classId) {
                case 0:
                    Log.d("ResumeMediaBrowser", "Service connected for " + this.this$0.mComponentName);
                    this.this$0.updateMediaController();
                    if (this.this$0.isBrowserConnected()) {
                        String root = this.this$0.mMediaBrowser.getRoot();
                        if (!TextUtils.isEmpty(root)) {
                            Callback callback = this.this$0.mCallback;
                            if (callback != null) {
                                callback.onConnected();
                            }
                            ResumeMediaBrowser resumeMediaBrowser = this.this$0;
                            MediaBrowser mediaBrowser = resumeMediaBrowser.mMediaBrowser;
                            if (mediaBrowser != null) {
                                mediaBrowser.subscribe(root, resumeMediaBrowser.mSubscriptionCallback);
                                break;
                            }
                        }
                    }
                    Callback callback2 = this.this$0.mCallback;
                    if (callback2 != null) {
                        callback2.onError();
                    }
                    this.this$0.disconnect();
                    break;
                default:
                    Log.d("ResumeMediaBrowser", "Connected for restart " + this.this$0.mMediaBrowser.isConnected());
                    this.this$0.updateMediaController();
                    if (!this.this$0.isBrowserConnected()) {
                        Callback callback3 = this.this$0.mCallback;
                        if (callback3 != null) {
                            callback3.onError();
                        }
                        this.this$0.disconnect();
                        break;
                    } else {
                        MediaController createMediaController = this.this$0.createMediaController(this.this$0.mMediaBrowser.getSessionToken());
                        createMediaController.getTransportControls();
                        createMediaController.getTransportControls().prepare();
                        createMediaController.getTransportControls().play();
                        Callback callback4 = this.this$0.mCallback;
                        if (callback4 != null) {
                            callback4.onConnected();
                            break;
                        }
                    }
                    break;
            }
        }

        @Override // android.media.browse.MediaBrowser.ConnectionCallback
        public final void onConnectionFailed() {
            switch (this.$r8$classId) {
                case 0:
                    Log.d("ResumeMediaBrowser", "Connection failed for " + this.this$0.mComponentName);
                    Callback callback = this.this$0.mCallback;
                    if (callback != null) {
                        callback.onError();
                    }
                    this.this$0.disconnect();
                    break;
                default:
                    Callback callback2 = this.this$0.mCallback;
                    if (callback2 != null) {
                        callback2.onError();
                    }
                    this.this$0.disconnect();
                    break;
            }
        }

        @Override // android.media.browse.MediaBrowser.ConnectionCallback
        public final void onConnectionSuspended() {
            switch (this.$r8$classId) {
                case 0:
                    Log.d("ResumeMediaBrowser", "Connection suspended for " + this.this$0.mComponentName);
                    Callback callback = this.this$0.mCallback;
                    if (callback != null) {
                        callback.onError();
                    }
                    this.this$0.disconnect();
                    break;
                default:
                    Callback callback2 = this.this$0.mCallback;
                    if (callback2 != null) {
                        callback2.onError();
                    }
                    this.this$0.disconnect();
                    break;
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SessionDestroyCallback extends MediaController.Callback {
        public SessionDestroyCallback() {
        }

        @Override // android.media.session.MediaController.Callback
        public final void onSessionDestroyed() {
            ResumeMediaBrowser resumeMediaBrowser = ResumeMediaBrowser.this;
            ResumeMediaBrowserLogger resumeMediaBrowserLogger = resumeMediaBrowser.mLogger;
            boolean isBrowserConnected = resumeMediaBrowser.isBrowserConnected();
            ComponentName componentName = ResumeMediaBrowser.this.mComponentName;
            resumeMediaBrowserLogger.getClass();
            LogLevel logLevel = LogLevel.DEBUG;
            ResumeMediaBrowserLogger$logSessionDestroyed$2 resumeMediaBrowserLogger$logSessionDestroyed$2 = new Function1() { // from class: com.android.systemui.media.controls.domain.resume.ResumeMediaBrowserLogger$logSessionDestroyed$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    LogMessage logMessage = (LogMessage) obj;
                    return "Session destroyed. Active browser = " + logMessage.getBool1() + ". Browser component = " + logMessage.getStr1() + ".";
                }
            };
            LogBuffer logBuffer = resumeMediaBrowserLogger.buffer;
            LogMessage obtain = logBuffer.obtain("MediaBrowser", logLevel, resumeMediaBrowserLogger$logSessionDestroyed$2, null);
            LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
            logMessageImpl.bool1 = isBrowserConnected;
            logMessageImpl.str1 = componentName.toShortString();
            logBuffer.commit(obtain);
            ResumeMediaBrowser.this.disconnect();
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.android.systemui.media.controls.domain.resume.ResumeMediaBrowser$1] */
    public ResumeMediaBrowser(Context context, Callback callback, ComponentName componentName, MediaBrowserFactory mediaBrowserFactory, ResumeMediaBrowserLogger resumeMediaBrowserLogger, int i) {
        this.mContext = context;
        this.mCallback = callback;
        this.mComponentName = componentName;
        this.mBrowserFactory = mediaBrowserFactory;
        this.mLogger = resumeMediaBrowserLogger;
        this.mUserId = i;
    }

    public final void connectBrowser(MediaBrowser mediaBrowser, String str) {
        ComponentName componentName = this.mComponentName;
        ResumeMediaBrowserLogger resumeMediaBrowserLogger = this.mLogger;
        resumeMediaBrowserLogger.getClass();
        LogLevel logLevel = LogLevel.DEBUG;
        ResumeMediaBrowserLogger$logConnection$2 resumeMediaBrowserLogger$logConnection$2 = new Function1() { // from class: com.android.systemui.media.controls.domain.resume.ResumeMediaBrowserLogger$logConnection$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("Connecting browser for component ", logMessage.getStr1(), " due to ", logMessage.getStr2());
            }
        };
        LogBuffer logBuffer = resumeMediaBrowserLogger.buffer;
        LogMessage obtain = logBuffer.obtain("MediaBrowser", logLevel, resumeMediaBrowserLogger$logConnection$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = componentName.toShortString();
        logMessageImpl.str2 = str;
        logBuffer.commit(obtain);
        disconnect();
        this.mMediaBrowser = mediaBrowser;
        mediaBrowser.connect();
        updateMediaController();
    }

    public MediaController createMediaController(MediaSession.Token token) {
        return new MediaController(this.mContext, token);
    }

    public final void disconnect() {
        if (this.mMediaBrowser != null) {
            ComponentName componentName = this.mComponentName;
            ResumeMediaBrowserLogger resumeMediaBrowserLogger = this.mLogger;
            resumeMediaBrowserLogger.getClass();
            LogLevel logLevel = LogLevel.DEBUG;
            ResumeMediaBrowserLogger$logDisconnect$2 resumeMediaBrowserLogger$logDisconnect$2 = new Function1() { // from class: com.android.systemui.media.controls.domain.resume.ResumeMediaBrowserLogger$logDisconnect$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Disconnecting browser for component ", ((LogMessage) obj).getStr1());
                }
            };
            LogBuffer logBuffer = resumeMediaBrowserLogger.buffer;
            LogMessage obtain = logBuffer.obtain("MediaBrowser", logLevel, resumeMediaBrowserLogger$logDisconnect$2, null);
            ((LogMessageImpl) obtain).str1 = componentName.toShortString();
            logBuffer.commit(obtain);
            this.mMediaBrowser.disconnect();
        }
        this.mMediaBrowser = null;
        updateMediaController();
    }

    public final boolean isBrowserConnected() {
        MediaBrowser mediaBrowser = this.mMediaBrowser;
        return mediaBrowser != null && mediaBrowser.isConnected();
    }

    public final void updateMediaController() {
        MediaController mediaController = this.mMediaController;
        MediaSession.Token sessionToken = mediaController != null ? mediaController.getSessionToken() : null;
        MediaSession.Token sessionToken2 = !isBrowserConnected() ? null : this.mMediaBrowser.getSessionToken();
        if (sessionToken == null && sessionToken2 == null) {
            return;
        }
        if (sessionToken == null || !sessionToken.equals(sessionToken2)) {
            MediaController mediaController2 = this.mMediaController;
            SessionDestroyCallback sessionDestroyCallback = this.mMediaControllerCallback;
            if (mediaController2 != null) {
                mediaController2.unregisterCallback(sessionDestroyCallback);
            }
            if (sessionToken2 == null) {
                this.mMediaController = null;
                return;
            }
            MediaController createMediaController = createMediaController(sessionToken2);
            this.mMediaController = createMediaController;
            createMediaController.registerCallback(sessionDestroyCallback);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Callback {
        public abstract void addTrack(MediaDescription mediaDescription, ComponentName componentName, ResumeMediaBrowser resumeMediaBrowser);

        public void onConnected() {
        }

        public void onError() {
        }
    }
}

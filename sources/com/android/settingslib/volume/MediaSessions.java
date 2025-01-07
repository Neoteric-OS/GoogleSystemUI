package com.android.settingslib.volume;

import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaMetadata;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.media.session.MediaSessionManager;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerExecutor;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.Slog;
import androidx.activity.result.ActivityResultRegistry$$ExternalSyntheticOutline0;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import androidx.collection.MutableObjectList$$ExternalSyntheticOutline0;
import androidx.constraintlayout.core.PriorityGoalRow$GoalVariableAccessor$$ExternalSyntheticOutline0;
import com.android.settingslib.bluetooth.CachedBluetoothDevice$$ExternalSyntheticOutline0;
import com.android.settingslib.bluetooth.LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0;
import com.android.systemui.plugins.VolumeDialogController;
import com.android.systemui.volume.VolumeDialogControllerImpl;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaSessions {
    public static final String TAG;
    public final VolumeDialogControllerImpl.MediaSessionsCallbacks mCallbacks;
    public final Context mContext;
    public final H mHandler;
    public final HandlerExecutor mHandlerExecutor;
    public boolean mInit;
    public final MediaSessionManager mMgr;
    public final Map mRecords = new HashMap();
    public final AnonymousClass1 mSessionsListener = new MediaSessionManager.OnActiveSessionsChangedListener() { // from class: com.android.settingslib.volume.MediaSessions.1
        @Override // android.media.session.MediaSessionManager.OnActiveSessionsChangedListener
        public final void onActiveSessionsChanged(List list) {
            MediaSessions.this.onActiveSessionsUpdatedH(list);
        }
    };
    public final AnonymousClass2 mRemoteSessionCallback = new MediaSessionManager.RemoteSessionCallback() { // from class: com.android.settingslib.volume.MediaSessions.2
        public final void onDefaultRemoteSessionChanged(MediaSession.Token token) {
            MediaSessions.this.mHandler.obtainMessage(3, token).sendToTarget();
        }

        public final void onVolumeChanged(MediaSession.Token token, int i) {
            MediaSessions.this.mHandler.obtainMessage(2, i, 0, token).sendToTarget();
        }
    };

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class H extends Handler {
        public H(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            int intValue;
            int i = message.what;
            if (i == 1) {
                MediaSessions mediaSessions = MediaSessions.this;
                mediaSessions.onActiveSessionsUpdatedH(mediaSessions.mMgr.getActiveSessions(null));
                return;
            }
            if (i != 2) {
                if (i != 3) {
                    return;
                }
                MediaSessions mediaSessions2 = MediaSessions.this;
                MediaSession.Token token = (MediaSession.Token) message.obj;
                mediaSessions2.getClass();
                MediaController mediaController = token != null ? new MediaController(mediaSessions2.mContext, token) : null;
                String packageName = mediaController != null ? mediaController.getPackageName() : null;
                if (D.BUG) {
                    Log.d(MediaSessions.TAG, AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("onUpdateRemoteSessionListH ", packageName));
                }
                if (mediaSessions2.mInit) {
                    mediaSessions2.mHandler.sendEmptyMessage(1);
                    return;
                }
                return;
            }
            MediaSessions mediaSessions3 = MediaSessions.this;
            MediaSession.Token token2 = (MediaSession.Token) message.obj;
            int i2 = message.arg1;
            mediaSessions3.getClass();
            MediaController mediaController2 = new MediaController(mediaSessions3.mContext, token2);
            if (D.BUG) {
                Log.d(MediaSessions.TAG, "remoteVolumeChangedH " + mediaController2.getPackageName() + " " + Util.audioManagerFlagsToString(i2));
            }
            MediaSession.Token sessionToken = mediaController2.getSessionToken();
            VolumeDialogControllerImpl.MediaSessionsCallbacks mediaSessionsCallbacks = mediaSessions3.mCallbacks;
            if (mediaSessionsCallbacks.showForSession(sessionToken)) {
                mediaSessionsCallbacks.addStream(sessionToken, "onRemoteVolumeChanged");
                synchronized (mediaSessionsCallbacks.mRemoteStreams) {
                    intValue = ((Integer) mediaSessionsCallbacks.mRemoteStreams.get(sessionToken)).intValue();
                }
                boolean shouldShowUI = VolumeDialogControllerImpl.this.shouldShowUI(i2);
                String str = VolumeDialogControllerImpl.TAG;
                Slog.d(str, "onRemoteVolumeChanged: stream: " + intValue + " showui? " + shouldShowUI);
                boolean updateActiveStreamW = VolumeDialogControllerImpl.this.updateActiveStreamW(intValue);
                if (shouldShowUI) {
                    updateActiveStreamW |= VolumeDialogControllerImpl.this.checkRoutedToBluetoothW(3);
                }
                if (updateActiveStreamW) {
                    Slog.d(str, "onRemoteChanged: updatingState");
                    VolumeDialogControllerImpl volumeDialogControllerImpl = VolumeDialogControllerImpl.this;
                    volumeDialogControllerImpl.mCallbacks.onStateChanged(volumeDialogControllerImpl.mState);
                }
                if (shouldShowUI) {
                    VolumeDialogControllerImpl volumeDialogControllerImpl2 = VolumeDialogControllerImpl.this;
                    volumeDialogControllerImpl2.mCallbacks.onShowRequested(2, volumeDialogControllerImpl2.mKeyguardManager.isKeyguardLocked(), volumeDialogControllerImpl2.mActivityManager.getLockTaskModeState());
                }
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MediaControllerRecord extends MediaController.Callback {
        public final MediaController controller;
        public String name;
        public boolean sentRemote;

        public MediaControllerRecord(MediaController mediaController) {
            this.controller = mediaController;
        }

        public final String cb(String str) {
            StringBuilder m = PriorityGoalRow$GoalVariableAccessor$$ExternalSyntheticOutline0.m(str, " ");
            m.append(this.controller.getPackageName());
            m.append(" ");
            return m.toString();
        }

        @Override // android.media.session.MediaController.Callback
        public final void onAudioInfoChanged(MediaController.PlaybackInfo playbackInfo) {
            if (D.BUG) {
                String str = MediaSessions.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append(cb("onAudioInfoChanged"));
                sb.append(Util.playbackInfoToString(playbackInfo));
                sb.append(" sentRemote=");
                CachedBluetoothDevice$$ExternalSyntheticOutline0.m(sb, this.sentRemote, str);
            }
            boolean z = playbackInfo != null && playbackInfo.getPlaybackType() == 2;
            if (!z && this.sentRemote) {
                MediaSessions.this.mCallbacks.onRemoteRemoved(this.controller.getSessionToken());
                this.sentRemote = false;
            } else if (z) {
                MediaSessions.this.updateRemoteH(this.controller.getSessionToken(), this.name, playbackInfo);
                this.sentRemote = true;
            }
        }

        @Override // android.media.session.MediaController.Callback
        public final void onExtrasChanged(Bundle bundle) {
            if (D.BUG) {
                Log.d(MediaSessions.TAG, cb("onExtrasChanged") + bundle);
            }
        }

        @Override // android.media.session.MediaController.Callback
        public final void onMetadataChanged(MediaMetadata mediaMetadata) {
            if (D.BUG) {
                String str = MediaSessions.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append(cb("onMetadataChanged"));
                sb.append(mediaMetadata == null ? null : mediaMetadata.getDescription().toString());
                Log.d(str, sb.toString());
            }
        }

        @Override // android.media.session.MediaController.Callback
        public final void onPlaybackStateChanged(PlaybackState playbackState) {
            if (D.BUG) {
                Log.d(MediaSessions.TAG, cb("onPlaybackStateChanged") + Util.playbackStateToString(playbackState));
            }
        }

        @Override // android.media.session.MediaController.Callback
        public final void onQueueChanged(List list) {
            if (D.BUG) {
                Log.d(MediaSessions.TAG, cb("onQueueChanged") + list);
            }
        }

        @Override // android.media.session.MediaController.Callback
        public final void onQueueTitleChanged(CharSequence charSequence) {
            if (D.BUG) {
                Log.d(MediaSessions.TAG, cb("onQueueTitleChanged") + ((Object) charSequence));
            }
        }

        @Override // android.media.session.MediaController.Callback
        public final void onSessionDestroyed() {
            if (D.BUG) {
                Log.d(MediaSessions.TAG, cb("onSessionDestroyed"));
            }
        }

        @Override // android.media.session.MediaController.Callback
        public final void onSessionEvent(String str, Bundle bundle) {
            if (D.BUG) {
                Log.d(MediaSessions.TAG, cb("onSessionEvent") + "event=" + str + " extras=" + bundle);
            }
        }
    }

    static {
        String concat = "vol.".concat("MediaSessions");
        if (concat.length() >= 23) {
            concat = concat.substring(0, 23);
        }
        TAG = concat;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.android.settingslib.volume.MediaSessions$1] */
    /* JADX WARN: Type inference failed for: r0v2, types: [com.android.settingslib.volume.MediaSessions$2] */
    public MediaSessions(Context context, Looper looper, VolumeDialogControllerImpl.MediaSessionsCallbacks mediaSessionsCallbacks) {
        this.mContext = context;
        H h = new H(looper);
        this.mHandler = h;
        this.mHandlerExecutor = new HandlerExecutor(h);
        this.mMgr = (MediaSessionManager) context.getSystemService("media_session");
        this.mCallbacks = mediaSessionsCallbacks;
    }

    public final void init() {
        if (D.BUG) {
            Log.d(TAG, "init");
        }
        MediaSessionManager mediaSessionManager = this.mMgr;
        AnonymousClass1 anonymousClass1 = this.mSessionsListener;
        H h = this.mHandler;
        mediaSessionManager.addOnActiveSessionsChangedListener(anonymousClass1, null, h);
        this.mInit = true;
        h.sendEmptyMessage(1);
        this.mMgr.registerRemoteSessionCallback(this.mHandlerExecutor, this.mRemoteSessionCallback);
    }

    public final void onActiveSessionsUpdatedH(List list) {
        boolean z = D.BUG;
        String str = TAG;
        if (z) {
            Log.d(str, "onActiveSessionsUpdatedH n=" + list.size());
        }
        HashSet hashSet = new HashSet(this.mRecords.keySet());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            MediaController mediaController = (MediaController) it.next();
            MediaSession.Token sessionToken = mediaController.getSessionToken();
            MediaController.PlaybackInfo playbackInfo = mediaController.getPlaybackInfo();
            hashSet.remove(sessionToken);
            if (!this.mRecords.containsKey(sessionToken)) {
                MediaControllerRecord mediaControllerRecord = new MediaControllerRecord(mediaController);
                PackageManager packageManager = this.mContext.getPackageManager();
                String packageName = mediaController.getPackageName();
                try {
                    String trim = Objects.toString(packageManager.getApplicationInfo(packageName, 0).loadLabel(packageManager), "").trim();
                    if (trim.length() > 0) {
                        packageName = trim;
                    }
                } catch (PackageManager.NameNotFoundException unused) {
                }
                mediaControllerRecord.name = packageName;
                this.mRecords.put(sessionToken, mediaControllerRecord);
                mediaController.registerCallback(mediaControllerRecord, this.mHandler);
            }
            MediaControllerRecord mediaControllerRecord2 = (MediaControllerRecord) this.mRecords.get(sessionToken);
            if (playbackInfo != null && playbackInfo.getPlaybackType() == 2) {
                updateRemoteH(sessionToken, mediaControllerRecord2.name, playbackInfo);
                mediaControllerRecord2.sentRemote = true;
            }
        }
        Iterator it2 = hashSet.iterator();
        while (it2.hasNext()) {
            MediaSession.Token token = (MediaSession.Token) it2.next();
            MediaControllerRecord mediaControllerRecord3 = (MediaControllerRecord) this.mRecords.get(token);
            mediaControllerRecord3.controller.unregisterCallback(mediaControllerRecord3);
            this.mRecords.remove(token);
            if (D.BUG) {
                StringBuilder sb = new StringBuilder("Removing ");
                sb.append(mediaControllerRecord3.name);
                sb.append(" sentRemote=");
                CachedBluetoothDevice$$ExternalSyntheticOutline0.m(sb, mediaControllerRecord3.sentRemote, str);
            }
            if (mediaControllerRecord3.sentRemote) {
                this.mCallbacks.onRemoteRemoved(token);
                mediaControllerRecord3.sentRemote = false;
            }
        }
    }

    public final void updateRemoteH(MediaSession.Token token, String str, MediaController.PlaybackInfo playbackInfo) {
        int intValue;
        VolumeDialogControllerImpl.MediaSessionsCallbacks mediaSessionsCallbacks = this.mCallbacks;
        if (mediaSessionsCallbacks == null || !mediaSessionsCallbacks.showForSession(token)) {
            return;
        }
        mediaSessionsCallbacks.addStream(token, "onRemoteUpdate");
        synchronized (mediaSessionsCallbacks.mRemoteStreams) {
            intValue = ((Integer) mediaSessionsCallbacks.mRemoteStreams.get(token)).intValue();
        }
        String str2 = VolumeDialogControllerImpl.TAG;
        StringBuilder m = MutableObjectList$$ExternalSyntheticOutline0.m("onRemoteUpdate: stream: ", " volume: ", intValue);
        m.append(playbackInfo.getCurrentVolume());
        Slog.d(str2, m.toString());
        boolean z = true;
        boolean z2 = VolumeDialogControllerImpl.this.mState.states.indexOfKey(intValue) < 0;
        VolumeDialogController.StreamState streamStateW = VolumeDialogControllerImpl.this.streamStateW(intValue);
        streamStateW.dynamic = true;
        streamStateW.levelMin = 0;
        streamStateW.levelMax = playbackInfo.getMaxVolume();
        if (streamStateW.level != playbackInfo.getCurrentVolume()) {
            streamStateW.level = playbackInfo.getCurrentVolume();
            z2 = true;
        }
        if (Objects.equals(streamStateW.remoteLabel, str)) {
            z = z2;
        } else {
            streamStateW.name = -1;
            streamStateW.remoteLabel = str;
        }
        if (z) {
            StringBuilder m2 = ActivityResultRegistry$$ExternalSyntheticOutline0.m("onRemoteUpdate: ", str, ": ");
            m2.append(streamStateW.level);
            m2.append(" of ");
            LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0.m(m2, streamStateW.levelMax, str2);
            VolumeDialogControllerImpl volumeDialogControllerImpl = VolumeDialogControllerImpl.this;
            volumeDialogControllerImpl.mCallbacks.onStateChanged(volumeDialogControllerImpl.mState);
        }
    }
}

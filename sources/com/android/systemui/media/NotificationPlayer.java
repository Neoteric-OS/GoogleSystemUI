package com.android.systemui.media;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Looper;
import android.os.PowerManager;
import android.os.SystemClock;
import android.util.Log;
import androidx.compose.runtime.collection.MutableVectorKt$$ExternalSyntheticOutline0;
import java.lang.Thread;
import java.util.LinkedList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class NotificationPlayer implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener {
    public AudioManager mAudioManagerWithAudioFocus;
    public CreationAndCompletionThread mCompletionThread;
    public Looper mLooper;
    public MediaPlayer mPlayer;
    public CmdThread mThread;
    public PowerManager.WakeLock mWakeLock;
    public final LinkedList mCmdQueue = new LinkedList();
    public final Object mCompletionHandlingLock = new Object();
    public final Object mPlayerLock = new Object();
    public final Object mQueueAudioFocusLock = new Object();
    public int mNotificationRampTimeMs = 0;
    public int mState = 2;
    public final String mTag = "RingtonePlayer";

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class CmdThread extends Thread {
        public CmdThread() {
            super("NotificationPlayer-" + NotificationPlayer.this.mTag);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            Command command;
            while (true) {
                synchronized (NotificationPlayer.this.mCmdQueue) {
                    Log.d(NotificationPlayer.this.mTag, "RemoveFirst");
                    command = (Command) NotificationPlayer.this.mCmdQueue.removeFirst();
                }
                try {
                    int i = command.code;
                    if (i == 1) {
                        Log.d(NotificationPlayer.this.mTag, "PLAY");
                        NotificationPlayer.m832$$Nest$mstartSound(NotificationPlayer.this, command);
                    } else if (i == 2) {
                        Log.d(NotificationPlayer.this.mTag, "STOP");
                        NotificationPlayer.m833$$Nest$mstopSound(NotificationPlayer.this, command);
                    }
                    synchronized (NotificationPlayer.this.mCmdQueue) {
                        try {
                            if (NotificationPlayer.this.mCmdQueue.size() == 0) {
                                break;
                            }
                        } finally {
                        }
                    }
                } catch (Throwable th) {
                    synchronized (NotificationPlayer.this.mCmdQueue) {
                        try {
                            if (NotificationPlayer.this.mCmdQueue.size() != 0) {
                                throw th;
                            }
                            NotificationPlayer notificationPlayer = NotificationPlayer.this;
                            notificationPlayer.mThread = null;
                            PowerManager.WakeLock wakeLock = notificationPlayer.mWakeLock;
                            if (wakeLock != null) {
                                wakeLock.release();
                            }
                            return;
                        } finally {
                        }
                    }
                }
            }
            NotificationPlayer notificationPlayer2 = NotificationPlayer.this;
            notificationPlayer2.mThread = null;
            PowerManager.WakeLock wakeLock2 = notificationPlayer2.mWakeLock;
            if (wakeLock2 != null) {
                wakeLock2.release();
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Command {
        public AudioAttributes attributes;
        public int code;
        public Context context;
        public boolean looping;
        public long requestTime;
        public Uri uri;
        public float volume;

        public final String toString() {
            return "{ code=" + this.code + " looping=" + this.looping + " attributes=" + this.attributes + " volume=" + this.volume + " uri=" + this.uri + " }";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class CreationAndCompletionThread extends Thread {
        public final Command mCmd;

        public CreationAndCompletionThread(Command command) {
            this.mCmd = command;
        }

        /* JADX WARN: Removed duplicated region for block: B:45:0x0175 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        @Override // java.lang.Thread, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void run() {
            /*
                Method dump skipped, instructions count: 434
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.media.NotificationPlayer.CreationAndCompletionThread.run():void");
        }
    }

    /* renamed from: -$$Nest$mstartSound, reason: not valid java name */
    public static void m832$$Nest$mstartSound(NotificationPlayer notificationPlayer, Command command) {
        notificationPlayer.getClass();
        try {
            Log.d(notificationPlayer.mTag, "startSound()");
            synchronized (notificationPlayer.mCompletionHandlingLock) {
                try {
                    Looper looper = notificationPlayer.mLooper;
                    if (looper != null && looper.getThread().getState() != Thread.State.TERMINATED) {
                        Log.d(notificationPlayer.mTag, "in startSound quitting looper " + notificationPlayer.mLooper);
                        notificationPlayer.mLooper.quit();
                    }
                    CreationAndCompletionThread creationAndCompletionThread = notificationPlayer.new CreationAndCompletionThread(command);
                    notificationPlayer.mCompletionThread = creationAndCompletionThread;
                    synchronized (creationAndCompletionThread) {
                        notificationPlayer.mCompletionThread.start();
                        notificationPlayer.mCompletionThread.wait();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            long uptimeMillis = SystemClock.uptimeMillis() - command.requestTime;
            if (uptimeMillis > 1000) {
                Log.w(notificationPlayer.mTag, "Notification sound delayed by " + uptimeMillis + "msecs");
            }
        } catch (Exception e) {
            Log.w(notificationPlayer.mTag, "error loading sound for " + command.uri, e);
        }
    }

    /* renamed from: -$$Nest$mstopSound, reason: not valid java name */
    public static void m833$$Nest$mstopSound(NotificationPlayer notificationPlayer, Command command) {
        MediaPlayer mediaPlayer;
        synchronized (notificationPlayer.mPlayerLock) {
            mediaPlayer = notificationPlayer.mPlayer;
            notificationPlayer.mPlayer = null;
        }
        if (mediaPlayer == null) {
            Log.w(notificationPlayer.mTag, "STOP command without a player");
            return;
        }
        long uptimeMillis = SystemClock.uptimeMillis() - command.requestTime;
        if (uptimeMillis > 1000) {
            Log.w(notificationPlayer.mTag, "Notification stop delayed by " + uptimeMillis + "msecs");
        }
        try {
            mediaPlayer.stop();
        } catch (Exception e) {
            Log.w(notificationPlayer.mTag, "Failed to stop MediaPlayer", e);
        }
        Log.i(notificationPlayer.mTag, "About to release MediaPlayer piid:" + mediaPlayer.getPlayerIId() + " due to notif cancelled");
        try {
            mediaPlayer.release();
        } catch (Exception e2) {
            Log.w(notificationPlayer.mTag, "Failed to release MediaPlayer", e2);
        }
        synchronized (notificationPlayer.mQueueAudioFocusLock) {
            if (notificationPlayer.mAudioManagerWithAudioFocus != null) {
                Log.d(notificationPlayer.mTag, "in STOP: abandonning AudioFocus");
                try {
                    notificationPlayer.mAudioManagerWithAudioFocus.abandonAudioFocus(null);
                } catch (Exception e3) {
                    Log.w(notificationPlayer.mTag, "Failed to abandon audio focus", e3);
                }
                notificationPlayer.mAudioManagerWithAudioFocus = null;
            }
        }
        synchronized (notificationPlayer.mCompletionHandlingLock) {
            try {
                Looper looper = notificationPlayer.mLooper;
                if (looper != null && looper.getThread().getState() != Thread.State.TERMINATED) {
                    Log.d(notificationPlayer.mTag, "in STOP: quitting looper " + notificationPlayer.mLooper);
                    notificationPlayer.mLooper.quit();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public final void onCompletion(MediaPlayer mediaPlayer) {
        synchronized (this.mQueueAudioFocusLock) {
            try {
                if (this.mAudioManagerWithAudioFocus != null) {
                    Log.d(this.mTag, "onCompletion() abandoning AudioFocus");
                    this.mAudioManagerWithAudioFocus.abandonAudioFocus(null);
                    this.mAudioManagerWithAudioFocus = null;
                } else {
                    Log.d(this.mTag, "onCompletion() no need to abandon AudioFocus");
                }
            } finally {
            }
        }
        synchronized (this.mCmdQueue) {
            synchronized (this.mCompletionHandlingLock) {
                try {
                    Log.d(this.mTag, "onCompletion queue size=" + this.mCmdQueue.size());
                    if (this.mCmdQueue.size() == 0) {
                        if (this.mLooper != null) {
                            Log.d(this.mTag, "in onCompletion quitting looper " + this.mLooper);
                            this.mLooper.quit();
                        }
                        this.mCompletionThread = null;
                    }
                } finally {
                }
            }
        }
        synchronized (this.mPlayerLock) {
            try {
                if (mediaPlayer == this.mPlayer) {
                    this.mPlayer = null;
                }
            } finally {
            }
        }
        if (mediaPlayer != null) {
            Log.i("NotificationPlayer", "About to release MediaPlayer piid:" + mediaPlayer.getPlayerIId() + " due to onCompletion");
            mediaPlayer.release();
        }
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        Log.e(this.mTag, MutableVectorKt$$ExternalSyntheticOutline0.m(i, i2, "error ", " (extra=", ") playing notification"));
        onCompletion(mediaPlayer);
        return true;
    }
}

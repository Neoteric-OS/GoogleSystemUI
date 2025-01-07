package com.android.systemui.media;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.AudioAttributes;
import android.media.IAudioService;
import android.media.IRingtonePlayer;
import android.media.Ringtone;
import android.media.VolumeShaper;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.PowerManager;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemClock;
import android.os.UserHandle;
import android.provider.MediaStore;
import android.util.Log;
import com.android.systemui.CoreStartable;
import com.android.systemui.media.NotificationPlayer;
import com.android.systemui.media.NotificationPlayer.CmdThread;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class RingtonePlayer implements CoreStartable {
    public final Context mContext;
    public final NotificationPlayer mAsyncPlayer = new NotificationPlayer();
    public final HashMap mClients = new HashMap();
    public final AnonymousClass1 mCallback = new IRingtonePlayer.Stub() { // from class: com.android.systemui.media.RingtonePlayer.1
        public final String getTitle(Uri uri) {
            return Ringtone.getTitle(RingtonePlayer.m834$$Nest$mgetContextForUser(RingtonePlayer.this, Binder.getCallingUserHandle()), uri, false, false);
        }

        public final boolean isPlaying(IBinder iBinder) {
            Client client;
            Log.d("RingtonePlayer", "isPlaying(token=" + iBinder + ")");
            synchronized (RingtonePlayer.this.mClients) {
                client = (Client) RingtonePlayer.this.mClients.get(iBinder);
            }
            if (client != null) {
                return client.mRingtone.isPlaying();
            }
            return false;
        }

        public final ParcelFileDescriptor openRingtone(Uri uri) {
            ContentResolver contentResolver = RingtonePlayer.m834$$Nest$mgetContextForUser(RingtonePlayer.this, Binder.getCallingUserHandle()).getContentResolver();
            if (uri.toString().startsWith(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI.toString())) {
                Cursor query = contentResolver.query(uri, new String[]{"is_ringtone", "is_alarm", "is_notification"}, null, null, null);
                try {
                    if (query.moveToFirst() && (query.getInt(0) != 0 || query.getInt(1) != 0 || query.getInt(2) != 0)) {
                        try {
                            ParcelFileDescriptor openFileDescriptor = contentResolver.openFileDescriptor(uri, "r");
                            query.close();
                            return openFileDescriptor;
                        } catch (IOException e) {
                            throw new SecurityException(e);
                        }
                    }
                    query.close();
                } catch (Throwable th) {
                    if (query != null) {
                        try {
                            query.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    }
                    throw th;
                }
            }
            throw new SecurityException("Uri is not ringtone, alarm, or notification: " + uri);
        }

        public final void play(IBinder iBinder, Uri uri, AudioAttributes audioAttributes, float f, boolean z) {
            playWithVolumeShaping(iBinder, uri, audioAttributes, f, z, null);
        }

        public final void playAsync(Uri uri, UserHandle userHandle, boolean z, AudioAttributes audioAttributes, float f) {
            Log.d("RingtonePlayer", "playAsync(uri=" + uri + ", user=" + userHandle + ")");
            if (Binder.getCallingUid() != 1000) {
                throw new SecurityException("Async playback only available from system UID.");
            }
            if (UserHandle.ALL.equals(userHandle)) {
                userHandle = UserHandle.SYSTEM;
            }
            RingtonePlayer ringtonePlayer = RingtonePlayer.this;
            NotificationPlayer notificationPlayer = ringtonePlayer.mAsyncPlayer;
            Context m834$$Nest$mgetContextForUser = RingtonePlayer.m834$$Nest$mgetContextForUser(ringtonePlayer, userHandle);
            Log.d(notificationPlayer.mTag, "play uri=" + uri.toString());
            NotificationPlayer.Command command = new NotificationPlayer.Command();
            command.requestTime = SystemClock.uptimeMillis();
            command.code = 1;
            command.context = m834$$Nest$mgetContextForUser;
            command.uri = uri;
            command.looping = z;
            command.attributes = audioAttributes;
            command.volume = f;
            synchronized (notificationPlayer.mCmdQueue) {
                notificationPlayer.mCmdQueue.add(command);
                if (notificationPlayer.mThread == null) {
                    PowerManager.WakeLock wakeLock = notificationPlayer.mWakeLock;
                    if (wakeLock != null) {
                        wakeLock.acquire();
                    }
                    NotificationPlayer.CmdThread cmdThread = notificationPlayer.new CmdThread();
                    notificationPlayer.mThread = cmdThread;
                    cmdThread.start();
                }
                notificationPlayer.mState = 1;
            }
        }

        public final void playWithVolumeShaping(IBinder iBinder, Uri uri, AudioAttributes audioAttributes, float f, boolean z, VolumeShaper.Configuration configuration) {
            Client client;
            Log.d("RingtonePlayer", "play(token=" + iBinder + ", uri=" + uri + ", uid=" + Binder.getCallingUid() + ")");
            synchronized (RingtonePlayer.this.mClients) {
                try {
                    client = (Client) RingtonePlayer.this.mClients.get(iBinder);
                    if (client == null) {
                        client = RingtonePlayer.this.new Client(iBinder, uri, Binder.getCallingUserHandle(), audioAttributes, configuration);
                        iBinder.linkToDeath(client, 0);
                        RingtonePlayer.this.mClients.put(iBinder, client);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            client.mRingtone.setLooping(z);
            client.mRingtone.setVolume(f);
            client.mRingtone.play();
        }

        public final void setPlaybackProperties(IBinder iBinder, float f, boolean z, boolean z2) {
            Client client;
            synchronized (RingtonePlayer.this.mClients) {
                client = (Client) RingtonePlayer.this.mClients.get(iBinder);
            }
            if (client != null) {
                client.mRingtone.setVolume(f);
                client.mRingtone.setLooping(z);
                client.mRingtone.setHapticGeneratorEnabled(z2);
            }
        }

        public final void stop(IBinder iBinder) {
            Client client;
            Log.d("RingtonePlayer", "stop(token=" + iBinder + ")");
            synchronized (RingtonePlayer.this.mClients) {
                client = (Client) RingtonePlayer.this.mClients.remove(iBinder);
            }
            if (client != null) {
                client.mToken.unlinkToDeath(client, 0);
                client.mRingtone.stop();
            }
        }

        public final void stopAsync() {
            Log.d("RingtonePlayer", "stopAsync()");
            if (Binder.getCallingUid() != 1000) {
                throw new SecurityException("Async playback only available from system UID.");
            }
            NotificationPlayer notificationPlayer = RingtonePlayer.this.mAsyncPlayer;
            Log.d(notificationPlayer.mTag, "stop");
            synchronized (notificationPlayer.mCmdQueue) {
                try {
                    if (notificationPlayer.mState != 2) {
                        NotificationPlayer.Command command = new NotificationPlayer.Command();
                        command.requestTime = SystemClock.uptimeMillis();
                        command.code = 2;
                        notificationPlayer.mCmdQueue.add(command);
                        if (notificationPlayer.mThread == null) {
                            PowerManager.WakeLock wakeLock = notificationPlayer.mWakeLock;
                            if (wakeLock != null) {
                                wakeLock.acquire();
                            }
                            NotificationPlayer.CmdThread cmdThread = notificationPlayer.new CmdThread();
                            notificationPlayer.mThread = cmdThread;
                            cmdThread.start();
                        }
                        notificationPlayer.mState = 2;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    };

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Client implements IBinder.DeathRecipient {
        public final Ringtone mRingtone;
        public final IBinder mToken;

        public Client(IBinder iBinder, Uri uri, UserHandle userHandle, AudioAttributes audioAttributes, VolumeShaper.Configuration configuration) {
            this.mToken = iBinder;
            Ringtone ringtone = new Ringtone(RingtonePlayer.m834$$Nest$mgetContextForUser(RingtonePlayer.this, userHandle), false);
            this.mRingtone = ringtone;
            ringtone.setAudioAttributesField(audioAttributes);
            ringtone.setUri(uri, configuration);
            ringtone.createLocalMediaPlayer();
        }

        @Override // android.os.IBinder.DeathRecipient
        public final void binderDied() {
            Log.d("RingtonePlayer", "binderDied() token=" + this.mToken);
            synchronized (RingtonePlayer.this.mClients) {
                RingtonePlayer.this.mClients.remove(this.mToken);
            }
            this.mRingtone.stop();
        }
    }

    /* renamed from: -$$Nest$mgetContextForUser, reason: not valid java name */
    public static Context m834$$Nest$mgetContextForUser(RingtonePlayer ringtonePlayer, UserHandle userHandle) {
        ringtonePlayer.getClass();
        try {
            Context context = ringtonePlayer.mContext;
            return context.createPackageContextAsUser(context.getPackageName(), 0, userHandle);
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [com.android.systemui.media.RingtonePlayer$1] */
    public RingtonePlayer(Context context) {
        this.mContext = context;
    }

    @Override // com.android.systemui.CoreStartable, com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("Clients:");
        synchronized (this.mClients) {
            try {
                for (Client client : this.mClients.values()) {
                    printWriter.print("  mToken=");
                    printWriter.print(client.mToken);
                    printWriter.print(" mUri=");
                    printWriter.println(client.mRingtone.getUri());
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        NotificationPlayer notificationPlayer = this.mAsyncPlayer;
        Context context = this.mContext;
        synchronized (notificationPlayer.mCmdQueue) {
            if (notificationPlayer.mWakeLock != null || notificationPlayer.mThread != null) {
                throw new RuntimeException("assertion failed mWakeLock=" + notificationPlayer.mWakeLock + " mThread=" + notificationPlayer.mThread);
            }
            notificationPlayer.mWakeLock = ((PowerManager) context.getSystemService("power")).newWakeLock(1, notificationPlayer.mTag);
        }
        try {
            IAudioService.Stub.asInterface(ServiceManager.getService("audio")).setRingtonePlayer(this.mCallback);
        } catch (RemoteException e) {
            Log.e("RingtonePlayer", "Problem registering RingtonePlayer: " + e);
        }
    }
}

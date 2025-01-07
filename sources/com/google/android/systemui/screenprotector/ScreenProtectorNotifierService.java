package com.google.android.systemui.screenprotector;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.IServiceCallback;
import android.os.Looper;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.provider.Settings;
import android.util.Log;
import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import androidx.collection.MutableObjectList$$ExternalSyntheticOutline0;
import androidx.constraintlayout.motion.widget.MotionLayout$$ExternalSyntheticOutline0;
import com.android.keyguard.logging.BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2$$ExternalSyntheticOutline0;
import com.android.wm.shell.R;
import com.google.input.algos.spd.IScreenProtectorDetectorListener;
import com.google.input.algos.spd.IScreenProtectorDetectorService;
import com.google.input.algos.spd.ScreenProtectorDetectorPacket;
import com.google.input.algos.spd.ScreenProtectorNotifierPacket;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ScreenProtectorNotifierService {
    public static final String INTERFACE_NAME = ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(new StringBuilder(), IScreenProtectorDetectorService.DESCRIPTOR, "/default");
    public final Context mContext;
    public IScreenProtectorDetectorService mDetectorService;
    public final AnonymousClass2 mListener;
    public NotificationManager mNotificationManager;
    public AnonymousClass4 mScreenProtectorModeObserver;
    public final AnonymousClass1 mServiceCallback;
    public AnonymousClass3 mSharedPreferenceChangeListener;
    public final Object mServiceLock = new Object();
    public int mNotifyId = 0;
    public byte mStatus = -1;

    /* renamed from: -$$Nest$mupdateScreenProtectorNotification, reason: not valid java name */
    public static void m1768$$Nest$mupdateScreenProtectorNotification(ScreenProtectorNotifierService screenProtectorNotifierService, int i) {
        String string;
        String string2 = screenProtectorNotifierService.mContext.getString(R.string.screen_protector_mode_title);
        if (i == 1) {
            string = screenProtectorNotifierService.mContext.getString(R.string.screen_protector_present_message);
        } else if (i != 2) {
            return;
        } else {
            string = screenProtectorNotifierService.mContext.getString(R.string.screen_protector_absent_message);
        }
        Log.i("ScreenProtectorNotifierService", "Creating ScreenProtectorNotification: notifyID = " + screenProtectorNotifierService.mNotifyId + " -> " + i + ".");
        int i2 = screenProtectorNotifierService.mNotifyId;
        if (i2 != 0 && i2 != i) {
            screenProtectorNotifierService.mNotificationManager.cancelAsUser("ScreenProtectorNotifierService", i2, UserHandle.ALL);
        }
        screenProtectorNotifierService.mNotifyId = i;
        Intent intent = new Intent(screenProtectorNotifierService.mContext, (Class<?>) ScreenProtectorNotificationActivity.class);
        intent.addFlags(268435456);
        intent.putExtra("notify_id", i);
        PendingIntent activityAsUser = PendingIntent.getActivityAsUser(screenProtectorNotifierService.mContext, 0, intent, 201326592, null, UserHandle.CURRENT);
        NotificationChannel notificationChannel = new NotificationChannel("ScreenProtectorNotificationChannel", string2, 2);
        Notification.Builder style = new Notification.Builder(screenProtectorNotifierService.mContext, "ScreenProtectorNotificationChannel").setAutoCancel(true).setContentIntent(activityAsUser).setContentTitle(string2).setContentText(string).setVisibility(1).setSmallIcon(android.R.drawable.stat_sys_warning).setStyle(new Notification.BigTextStyle().bigText(string));
        String string3 = screenProtectorNotifierService.mContext.getString(android.R.string.notification_channel_retail_mode);
        Bundle bundle = new Bundle();
        bundle.putString("android.substName", string3);
        style.addExtras(bundle);
        screenProtectorNotifierService.mNotificationManager.createNotificationChannel(notificationChannel);
        screenProtectorNotifierService.mNotificationManager.notifyAsUser("ScreenProtectorNotifierService", screenProtectorNotifierService.mNotifyId, style.build(), UserHandle.ALL);
    }

    public ScreenProtectorNotifierService(Context context) {
        IServiceCallback.Stub stub = new IServiceCallback.Stub() { // from class: com.google.android.systemui.screenprotector.ScreenProtectorNotifierService.1
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r1v1, types: [android.database.ContentObserver, com.google.android.systemui.screenprotector.ScreenProtectorNotifierService$4] */
            /* JADX WARN: Type inference failed for: r5v8, types: [android.content.SharedPreferences$OnSharedPreferenceChangeListener, com.google.android.systemui.screenprotector.ScreenProtectorNotifierService$3] */
            public final void onRegistration(String str, final IBinder iBinder) {
                if (iBinder == null) {
                    Log.e("ScreenProtectorNotifierService", "ServiceCallback.onRegistration() binder is null.");
                    return;
                }
                String str2 = ScreenProtectorNotifierService.INTERFACE_NAME;
                if (!str2.equals(str)) {
                    Log.e("ScreenProtectorNotifierService", MotionLayout$$ExternalSyntheticOutline0.m("onServiceRegistration name mismatch – received name: \"", str, "\", expected name: \"", str2, "\""));
                    return;
                }
                final ScreenProtectorNotifierService screenProtectorNotifierService = ScreenProtectorNotifierService.this;
                screenProtectorNotifierService.getClass();
                try {
                    iBinder.linkToDeath(new IBinder.DeathRecipient() { // from class: com.google.android.systemui.screenprotector.ScreenProtectorNotifierService$$ExternalSyntheticLambda0
                        @Override // android.os.IBinder.DeathRecipient
                        public final void binderDied() {
                            ScreenProtectorNotifierService screenProtectorNotifierService2 = ScreenProtectorNotifierService.this;
                            IBinder iBinder2 = iBinder;
                            synchronized (screenProtectorNotifierService2.mServiceLock) {
                                try {
                                    if (((IScreenProtectorDetectorService.Stub.Proxy) screenProtectorNotifierService2.mDetectorService).mRemote == iBinder2) {
                                        screenProtectorNotifierService2.mDetectorService = null;
                                    }
                                } catch (Throwable th) {
                                    throw th;
                                }
                            }
                            screenProtectorNotifierService2.mContext.getContentResolver().unregisterContentObserver(screenProtectorNotifierService2.mScreenProtectorModeObserver);
                            Context context2 = screenProtectorNotifierService2.mContext;
                            ScreenProtectorSharedPreferenceUtils.getSharedPreferences(context2).unregisterOnSharedPreferenceChangeListener(screenProtectorNotifierService2.mSharedPreferenceChangeListener);
                            ScreenProtectorSharedPreferenceUtils.getSharedPreferences(screenProtectorNotifierService2.mContext).edit().putInt("notification_response", -1).apply();
                        }
                    }, 0);
                    NotificationManager notificationManager = (NotificationManager) screenProtectorNotifierService.mContext.getSystemService(NotificationManager.class);
                    screenProtectorNotifierService.mNotificationManager = notificationManager;
                    if (notificationManager == null) {
                        Log.i("ScreenProtectorNotifierService", "Failed to initialize mNotificationManager.");
                    } else {
                        ?? r5 = new SharedPreferences.OnSharedPreferenceChangeListener() { // from class: com.google.android.systemui.screenprotector.ScreenProtectorNotifierService.3
                            @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
                            public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str3) {
                                if (str3.equals("notification_enabled")) {
                                    ScreenProtectorNotifierService.this.updateNotificationPreference();
                                    return;
                                }
                                if (str3.equals("notification_response")) {
                                    ScreenProtectorNotifierService screenProtectorNotifierService2 = ScreenProtectorNotifierService.this;
                                    byte b = (byte) ScreenProtectorSharedPreferenceUtils.getSharedPreferences(screenProtectorNotifierService2.mContext).getInt("notification_response", -1);
                                    if (b == -1) {
                                        return;
                                    }
                                    StringBuilder m = MutableObjectList$$ExternalSyntheticOutline0.m("updateNotificationResponse() response = ", ", status = ", b);
                                    m.append((int) screenProtectorNotifierService2.mStatus);
                                    Log.i("ScreenProtectorNotifierService", m.toString());
                                    ScreenProtectorNotifierPacket screenProtectorNotifierPacket = new ScreenProtectorNotifierPacket();
                                    screenProtectorNotifierPacket.detectorStatus = screenProtectorNotifierService2.mStatus;
                                    screenProtectorNotifierPacket.response = b;
                                    synchronized (screenProtectorNotifierService2.mServiceLock) {
                                        try {
                                            ((IScreenProtectorDetectorService.Stub.Proxy) screenProtectorNotifierService2.mDetectorService).updateNotifierPacket(screenProtectorNotifierPacket);
                                        } catch (RemoteException e) {
                                            Log.e("ScreenProtectorNotifierService", "Failed to updateNotifierPacket.", e);
                                        }
                                    }
                                }
                            }
                        };
                        screenProtectorNotifierService.mSharedPreferenceChangeListener = r5;
                        ScreenProtectorSharedPreferenceUtils.getSharedPreferences(screenProtectorNotifierService.mContext).registerOnSharedPreferenceChangeListener(r5);
                        ContentResolver contentResolver = screenProtectorNotifierService.mContext.getContentResolver();
                        Uri uriFor = Settings.Secure.getUriFor("touch_sensitivity_enabled");
                        ?? r1 = new ContentObserver(new Handler(Looper.getMainLooper())) { // from class: com.google.android.systemui.screenprotector.ScreenProtectorNotifierService.4
                            @Override // android.database.ContentObserver
                            public final void onChange(boolean z) {
                                super.onChange(z);
                                ScreenProtectorNotifierService.this.updateScreenProtectorMode();
                            }
                        };
                        screenProtectorNotifierService.mScreenProtectorModeObserver = r1;
                        contentResolver.registerContentObserver(uriFor, false, r1);
                        synchronized (screenProtectorNotifierService.mServiceLock) {
                            screenProtectorNotifierService.getScreenProtectorDetectorService();
                            screenProtectorNotifierService.registerScreenProtectorDetectorLister();
                        }
                        screenProtectorNotifierService.updateNotificationPreference();
                        screenProtectorNotifierService.updateScreenProtectorMode();
                    }
                } catch (RemoteException e) {
                    Log.e("ScreenProtectorNotifierService", "Failed to link to death on IScreenProtectorDetectorService.", e);
                }
                Log.i("ScreenProtectorNotifierService", "IServiceCallback registered - " + ScreenProtectorNotifierService.INTERFACE_NAME);
            }
        };
        this.mListener = new AnonymousClass2();
        this.mContext = context;
        String str = INTERFACE_NAME;
        if (ServiceManager.isDeclared(str)) {
            try {
                ServiceManager.registerForNotifications(str, stub);
                return;
            } catch (RemoteException e) {
                Log.e("ScreenProtectorNotifierService", "Failed to register for notifications.", e);
                return;
            }
        }
        Log.i("ScreenProtectorNotifierService", str + "not declared in manifest, will not send notification.");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v5, types: [com.google.input.algos.spd.IScreenProtectorDetectorService] */
    public final void getScreenProtectorDetectorService() {
        IScreenProtectorDetectorService.Stub.Proxy proxy;
        if (this.mDetectorService != null) {
            return;
        }
        String str = INTERFACE_NAME;
        IBinder service = ServiceManager.getService(str);
        if (service == null) {
            Log.e("ScreenProtectorNotifierService", "Failed to get IScreenProtectorDetectorService despite being declared.");
            return;
        }
        int i = IScreenProtectorDetectorService.Stub.$r8$clinit;
        IInterface queryLocalInterface = service.queryLocalInterface(IScreenProtectorDetectorService.DESCRIPTOR);
        if (queryLocalInterface == null || !(queryLocalInterface instanceof IScreenProtectorDetectorService)) {
            IScreenProtectorDetectorService.Stub.Proxy proxy2 = new IScreenProtectorDetectorService.Stub.Proxy();
            proxy2.mRemote = service;
            proxy = proxy2;
        } else {
            proxy = (IScreenProtectorDetectorService) queryLocalInterface;
        }
        this.mDetectorService = proxy;
        Log.i("ScreenProtectorNotifierService", "Service initialized - " + str);
    }

    public final void registerScreenProtectorDetectorLister() {
        IScreenProtectorDetectorService iScreenProtectorDetectorService = this.mDetectorService;
        if (iScreenProtectorDetectorService != null) {
            try {
                AnonymousClass2 anonymousClass2 = this.mListener;
                IScreenProtectorDetectorService.Stub.Proxy proxy = (IScreenProtectorDetectorService.Stub.Proxy) iScreenProtectorDetectorService;
                Parcel obtain = Parcel.obtain(proxy.mRemote);
                try {
                    obtain.writeInterfaceToken(IScreenProtectorDetectorService.DESCRIPTOR);
                    obtain.writeStrongInterface(anonymousClass2);
                    if (proxy.mRemote.transact(1, obtain, null, 1)) {
                    } else {
                        throw new RemoteException("Method registerListener is unimplemented.");
                    }
                } finally {
                    obtain.recycle();
                }
            } catch (RemoteException e) {
                Log.e("ScreenProtectorNotifierService", "Failed to registerListener.", e);
            }
        }
    }

    public final void updateNotificationPreference() {
        boolean z = ScreenProtectorSharedPreferenceUtils.getSharedPreferences(this.mContext).getBoolean("notification_enabled", true);
        synchronized (this.mServiceLock) {
            try {
                ((IScreenProtectorDetectorService.Stub.Proxy) this.mDetectorService).updateScreenProtectorNotificationPreference(z);
            } catch (RemoteException e) {
                Log.e("ScreenProtectorNotifierService", "Failed to updateNotificationPreference.", e);
            }
        }
    }

    public final void updateScreenProtectorMode() {
        boolean z = Settings.Secure.getInt(this.mContext.getContentResolver(), "touch_sensitivity_enabled", 0) != 0;
        synchronized (this.mServiceLock) {
            try {
                ((IScreenProtectorDetectorService.Stub.Proxy) this.mDetectorService).updateScreenProtectorMode(z);
            } catch (RemoteException e) {
                Log.e("ScreenProtectorNotifierService", "Failed to updateScreenProtectorMode.", e);
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.google.android.systemui.screenprotector.ScreenProtectorNotifierService$2, reason: invalid class name */
    public final class AnonymousClass2 extends Binder implements IScreenProtectorDetectorListener {
        public AnonymousClass2() {
            markVintfStability();
            attachInterface(this, IScreenProtectorDetectorListener.DESCRIPTOR);
        }

        public final int getMaxTransactionId() {
            return 16777214;
        }

        public final String getTransactionName(int i) {
            if (i == 1) {
                return "onScreenProtectorDetectorStatusUpdated";
            }
            switch (i) {
                case 16777214:
                    return "getInterfaceHash";
                case 16777215:
                    return "getInterfaceVersion";
                default:
                    return null;
            }
        }

        @Override // android.os.Binder
        public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            String str = IScreenProtectorDetectorListener.DESCRIPTOR;
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(str);
            }
            if (i == 1598968902) {
                parcel2.writeString(str);
                return true;
            }
            if (i == 16777215) {
                parcel2.writeNoException();
                parcel2.writeInt(3);
                return true;
            }
            if (i == 16777214) {
                parcel2.writeNoException();
                parcel2.writeString("50025a76b5b1024970a3d2d096d90171e2fdca67");
                return true;
            }
            if (i != 1) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            ScreenProtectorDetectorPacket screenProtectorDetectorPacket = (ScreenProtectorDetectorPacket) parcel.readTypedObject(ScreenProtectorDetectorPacket.CREATOR);
            parcel.enforceNoDataAvail();
            ScreenProtectorNotifierService screenProtectorNotifierService = ScreenProtectorNotifierService.this;
            boolean z = screenProtectorNotifierService.mContext.getResources().getBoolean(R.bool.config_adaptive_touch_sensitivity_enabled) && Settings.Secure.getInt(screenProtectorNotifierService.mContext.getContentResolver(), "adaptive_touch_sensitivity_enabled", 1) != 0;
            boolean z2 = Settings.Secure.getInt(ScreenProtectorNotifierService.this.mContext.getContentResolver(), "touch_sensitivity_enabled", 0) != 0;
            String str2 = screenProtectorDetectorPacket.status == 1 ? "PRESENT" : "ABSENT";
            StringBuilder m = BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2$$ExternalSyntheticOutline0.m("ATS=", ", SPM=", ", SPD=", z, z2);
            m.append(str2);
            Log.i("ScreenProtectorNotifierService", m.toString());
            if (z) {
                if (!z2) {
                    byte b = screenProtectorDetectorPacket.status;
                    if (b == 0) {
                        SystemProperties.set("debug.touch_sensitivity_mode", "0");
                        Log.i("ScreenProtectorNotifierService", "AdaptiveTouchSensitivity set SPM to 0 (ABSENT)");
                    } else if (b == 1) {
                        SystemProperties.set("debug.touch_sensitivity_mode", "1");
                        Log.i("ScreenProtectorNotifierService", "AdaptiveTouchSensitivity set SPM to 1 (PRESENT)");
                    }
                }
            } else if (ScreenProtectorSharedPreferenceUtils.getSharedPreferences(ScreenProtectorNotifierService.this.mContext).getBoolean("notification_enabled", true)) {
                ScreenProtectorNotifierService screenProtectorNotifierService2 = ScreenProtectorNotifierService.this;
                byte b2 = screenProtectorDetectorPacket.status;
                screenProtectorNotifierService2.mStatus = b2;
                if (z2 && b2 == 0) {
                    ScreenProtectorNotifierService.m1768$$Nest$mupdateScreenProtectorNotification(screenProtectorNotifierService2, 2);
                } else if (!z2 && b2 == 1) {
                    ScreenProtectorNotifierService.m1768$$Nest$mupdateScreenProtectorNotification(screenProtectorNotifierService2, 1);
                }
            } else {
                Log.i("ScreenProtectorNotifierService", "Skip detector's packet due to notification disabled by user.");
            }
            return true;
        }

        @Override // android.os.IInterface
        public final IBinder asBinder() {
            return this;
        }
    }
}

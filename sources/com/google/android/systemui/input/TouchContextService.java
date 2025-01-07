package com.google.android.systemui.input;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.hardware.display.DisplayManager;
import android.hardware.display.DisplayManagerGlobal;
import android.media.AudioManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.IServiceCallback;
import android.os.Looper;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.Trace;
import android.provider.Settings;
import android.util.Log;
import android.view.DisplayInfo;
import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import androidx.constraintlayout.motion.widget.MotionLayout$$ExternalSyntheticOutline0;
import com.android.internal.os.BackgroundThread;
import com.android.wm.shell.R;
import com.android.wm.shell.sysui.ShellInterface;
import com.google.input.ContextPacket;
import com.google.input.ITouchContextService;
import com.google.input.ITouchContextService$Stub$Proxy;
import com.google.input.ImeState;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TouchContextService {
    public static final String INTERFACE_NAME = ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(new StringBuilder(), ITouchContextService.DESCRIPTOR, "/default");
    public final AnonymousClass5 mAdaptiveTouchSensitivityObserver;
    public final AudioManager mAudioManager;
    public final AnonymousClass3 mAudioModeListener;
    public final ContentResolver mContentResolver;
    public final Context mContext;
    public final ContextPacket mContextPacket;
    public final AnonymousClass4 mDisplayImeChangeListener;
    public final AnonymousClass2 mDisplayListener;
    public final DisplayManager mDisplayManager;
    public final AnonymousClass5 mScreenProtectorModeObserver;
    public final AnonymousClass1 mServiceCallback;
    public final Object mServiceLock;
    public final ShellInterface mShellInterface;
    public ITouchContextService mTouchContextService;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.google.android.systemui.input.TouchContextService$4, reason: invalid class name */
    public final class AnonymousClass4 {
        public AnonymousClass4() {
        }
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [com.google.android.systemui.input.TouchContextService$3] */
    /* JADX WARN: Type inference failed for: r2v3, types: [com.google.android.systemui.input.TouchContextService$5] */
    /* JADX WARN: Type inference failed for: r2v4, types: [com.google.android.systemui.input.TouchContextService$5] */
    public TouchContextService(Context context, ShellInterface shellInterface) {
        IServiceCallback.Stub stub = new IServiceCallback.Stub() { // from class: com.google.android.systemui.input.TouchContextService.1
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r1v10, types: [com.google.input.ITouchContextService] */
            public final void onRegistration(String str, final IBinder iBinder) {
                ITouchContextService$Stub$Proxy iTouchContextService$Stub$Proxy;
                if (iBinder == null) {
                    Log.e("TouchContextService.java", "ServiceCallback.onRegistration: binder is `null`.");
                    return;
                }
                String str2 = TouchContextService.INTERFACE_NAME;
                if (!str2.equals(str)) {
                    Log.e("TouchContextService.java", MotionLayout$$ExternalSyntheticOutline0.m("onServiceRegistration name mismatch – received name: \"", str, "\", expected name: \"", str2, "\""));
                    return;
                }
                final TouchContextService touchContextService = TouchContextService.this;
                touchContextService.getClass();
                try {
                    iBinder.linkToDeath(new IBinder.DeathRecipient() { // from class: com.google.android.systemui.input.TouchContextService$$ExternalSyntheticLambda0
                        @Override // android.os.IBinder.DeathRecipient
                        public final void binderDied() {
                            TouchContextService touchContextService2 = TouchContextService.this;
                            IBinder iBinder2 = iBinder;
                            synchronized (touchContextService2.mServiceLock) {
                                try {
                                    if (((ITouchContextService$Stub$Proxy) touchContextService2.mTouchContextService).mRemote == iBinder2) {
                                        touchContextService2.mTouchContextService = null;
                                    }
                                } catch (Throwable th) {
                                    throw th;
                                }
                            }
                            touchContextService2.mDisplayManager.unregisterDisplayListener(touchContextService2.mDisplayListener);
                            touchContextService2.mAudioManager.removeOnModeChangedListener(touchContextService2.mAudioModeListener);
                            touchContextService2.mShellInterface.removeDisplayImeChangeListener(touchContextService2.mDisplayImeChangeListener);
                            touchContextService2.mContentResolver.unregisterContentObserver(touchContextService2.mAdaptiveTouchSensitivityObserver);
                            touchContextService2.mContentResolver.unregisterContentObserver(touchContextService2.mScreenProtectorModeObserver);
                        }
                    }, 0);
                    synchronized (touchContextService.mServiceLock) {
                        IInterface queryLocalInterface = iBinder.queryLocalInterface(ITouchContextService.DESCRIPTOR);
                        if (queryLocalInterface == null || !(queryLocalInterface instanceof ITouchContextService)) {
                            ITouchContextService$Stub$Proxy iTouchContextService$Stub$Proxy2 = new ITouchContextService$Stub$Proxy();
                            iTouchContextService$Stub$Proxy2.mRemote = iBinder;
                            iTouchContextService$Stub$Proxy = iTouchContextService$Stub$Proxy2;
                        } else {
                            iTouchContextService$Stub$Proxy = (ITouchContextService) queryLocalInterface;
                        }
                        touchContextService.mTouchContextService = iTouchContextService$Stub$Proxy;
                    }
                    touchContextService.mAudioManager.addOnModeChangedListener(BackgroundThread.getExecutor(), touchContextService.mAudioModeListener);
                    touchContextService.mDisplayManager.registerDisplayListener(touchContextService.mDisplayListener, BackgroundThread.getHandler());
                    BackgroundThread.getHandler().post(new Runnable() { // from class: com.google.android.systemui.input.TouchContextService$$ExternalSyntheticLambda1
                        @Override // java.lang.Runnable
                        public final void run() {
                            TouchContextService.this.mDisplayListener.onDisplayChanged(0);
                        }
                    });
                    touchContextService.mShellInterface.addDisplayImeChangeListener(touchContextService.mDisplayImeChangeListener, BackgroundThread.getExecutor());
                    touchContextService.mContentResolver.registerContentObserver(Settings.Secure.getUriFor("adaptive_touch_sensitivity_enabled"), false, touchContextService.mAdaptiveTouchSensitivityObserver);
                    touchContextService.mContentResolver.registerContentObserver(Settings.Secure.getUriFor("touch_sensitivity_enabled"), false, touchContextService.mScreenProtectorModeObserver);
                    touchContextService.updateAdaptiveTouchSensitivity();
                    touchContextService.updateScreenProtectorMode();
                    touchContextService.updateTouchContext();
                } catch (RemoteException e) {
                    Log.e("TouchContextService.java", "Failed to link to death on ITouchContextService. Binder is probably dead.", e);
                }
            }
        };
        this.mDisplayListener = new AnonymousClass2();
        this.mAudioModeListener = new AudioManager.OnModeChangedListener() { // from class: com.google.android.systemui.input.TouchContextService.3
            @Override // android.media.AudioManager.OnModeChangedListener
            public final void onModeChanged(int i) {
                synchronized (TouchContextService.this.mContextPacket) {
                    try {
                        TouchContextService touchContextService = TouchContextService.this;
                        ContextPacket contextPacket = touchContextService.mContextPacket;
                        byte b = (byte) i;
                        if (contextPacket.audioMode == b) {
                            return;
                        }
                        contextPacket.audioMode = b;
                        touchContextService.updateTouchContext();
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
        };
        this.mDisplayImeChangeListener = new AnonymousClass4();
        final int i = 0;
        this.mAdaptiveTouchSensitivityObserver = new ContentObserver(this, new Handler(Looper.getMainLooper())) { // from class: com.google.android.systemui.input.TouchContextService.5
            public final /* synthetic */ TouchContextService this$0;

            {
                this.this$0 = this;
            }

            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                switch (i) {
                    case 0:
                        super.onChange(z);
                        this.this$0.updateAdaptiveTouchSensitivity();
                        this.this$0.updateTouchContext();
                        break;
                    default:
                        super.onChange(z);
                        this.this$0.updateScreenProtectorMode();
                        this.this$0.updateTouchContext();
                        break;
                }
            }
        };
        final int i2 = 1;
        this.mScreenProtectorModeObserver = new ContentObserver(this, new Handler(Looper.getMainLooper())) { // from class: com.google.android.systemui.input.TouchContextService.5
            public final /* synthetic */ TouchContextService this$0;

            {
                this.this$0 = this;
            }

            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                switch (i2) {
                    case 0:
                        super.onChange(z);
                        this.this$0.updateAdaptiveTouchSensitivity();
                        this.this$0.updateTouchContext();
                        break;
                    default:
                        super.onChange(z);
                        this.this$0.updateScreenProtectorMode();
                        this.this$0.updateTouchContext();
                        break;
                }
            }
        };
        this.mServiceLock = new Object();
        ContextPacket contextPacket = new ContextPacket();
        this.mContextPacket = contextPacket;
        this.mDisplayManager = (DisplayManager) context.getSystemService(DisplayManager.class);
        this.mAudioManager = (AudioManager) context.getSystemService(AudioManager.class);
        this.mContext = context;
        this.mContentResolver = context.getContentResolver();
        this.mShellInterface = shellInterface;
        contextPacket.orientation = (byte) -1;
        contextPacket.audioMode = (byte) 0;
        contextPacket.adaptiveTouchSensitivity = (byte) -1;
        contextPacket.screenProtectorMode = (byte) -1;
        contextPacket.imeState = new ImeState();
        try {
            String str = INTERFACE_NAME;
            if (!ServiceManager.isDeclared(str)) {
                Log.d("TouchContextService.java", "No ITouchContextService declared in manifest, not sending input context.");
                return;
            }
            try {
                ServiceManager.registerForNotifications(str, stub);
            } catch (RemoteException e) {
                Log.e("TouchContextService.java", "Failed to register for notifications.", e);
            }
        } catch (SecurityException e2) {
            Log.e("TouchContextService.java", "Unable to check if AIDL service is declared. " + e2);
        }
    }

    public final void updateAdaptiveTouchSensitivity() {
        synchronized (this.mContextPacket) {
            try {
                if (this.mContext.getResources().getBoolean(R.bool.config_adaptive_touch_sensitivity_enabled)) {
                    this.mContextPacket.adaptiveTouchSensitivity = (byte) Settings.Secure.getInt(this.mContentResolver, "adaptive_touch_sensitivity_enabled", 1);
                } else {
                    this.mContextPacket.adaptiveTouchSensitivity = (byte) 0;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void updateScreenProtectorMode() {
        synchronized (this.mContextPacket) {
            this.mContextPacket.screenProtectorMode = (byte) Settings.Secure.getInt(this.mContentResolver, "touch_sensitivity_enabled", 0);
        }
    }

    public final void updateTouchContext() {
        synchronized (this.mServiceLock) {
            if (this.mTouchContextService == null) {
                Log.e("TouchContextService.java", "mTouchContextService is null.");
            }
            ITouchContextService iTouchContextService = this.mTouchContextService;
            if (iTouchContextService == null) {
                Log.e("TouchContextService.java", "Failed to get touch context service, dropping context packet.");
                return;
            }
            try {
                synchronized (this.mContextPacket) {
                    ContextPacket contextPacket = this.mContextPacket;
                    ITouchContextService$Stub$Proxy iTouchContextService$Stub$Proxy = (ITouchContextService$Stub$Proxy) iTouchContextService;
                    Parcel obtain = Parcel.obtain(iTouchContextService$Stub$Proxy.mRemote);
                    try {
                        obtain.writeInterfaceToken(ITouchContextService.DESCRIPTOR);
                        obtain.writeTypedObject(contextPacket, 0);
                        if (!iTouchContextService$Stub$Proxy.mRemote.transact(1, obtain, null, 1)) {
                            throw new RemoteException("Method updateContext is unimplemented.");
                        }
                    } finally {
                        obtain.recycle();
                    }
                }
            } catch (RemoteException e) {
                Log.e("TouchContextService.java", "Failed to send input context packet.", e);
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.google.android.systemui.input.TouchContextService$2, reason: invalid class name */
    public final class AnonymousClass2 implements DisplayManager.DisplayListener {
        public AnonymousClass2() {
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public final void onDisplayChanged(int i) {
            Trace.beginSection("TouchContextService.mDisplayListener#onDisplayChanged");
            if (i != 0) {
                return;
            }
            try {
                DisplayInfo displayInfo = DisplayManagerGlobal.getInstance().getDisplayInfo(i);
                if (displayInfo == null) {
                    Log.e("TouchContextService.java", "could not get DisplayInfo for display " + i + ".");
                    return;
                }
                int i2 = displayInfo.rotation;
                synchronized (TouchContextService.this.mContextPacket) {
                    TouchContextService touchContextService = TouchContextService.this;
                    ContextPacket contextPacket = touchContextService.mContextPacket;
                    byte b = (byte) i2;
                    if (contextPacket.orientation == b) {
                        return;
                    }
                    contextPacket.orientation = b;
                    touchContextService.updateTouchContext();
                }
            } finally {
                Trace.endSection();
            }
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public final void onDisplayAdded(int i) {
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public final void onDisplayRemoved(int i) {
        }
    }
}

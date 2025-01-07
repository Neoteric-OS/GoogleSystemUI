package com.android.systemui;

import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.os.StrictMode;
import android.os.Trace;
import android.view.Choreographer;
import android.view.View;
import android.view.ViewRootImpl;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import com.android.settingslib.utils.ThreadUtils;
import com.android.systemui.util.Assert;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Stack;
import java.util.function.Supplier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class DejankUtils {
    public static final boolean STRICT_MODE_ENABLED;
    public static final DejankUtils$$ExternalSyntheticLambda0 sAnimationCallbackRunnable;
    public static final Stack sBlockingIpcs;
    public static final Choreographer sChoreographer;
    public static final Handler sHandler;
    public static boolean sImmediate;
    public static final Object sLock;
    public static final ArrayList sPendingRunnables;
    public static final AnonymousClass1 sProxy = null;
    public static final Random sRandom;
    public static boolean sTemporarilyIgnoreStrictMode;
    public static final HashSet sWhitelistedFrameworkClasses;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.DejankUtils$1, reason: invalid class name */
    public final class AnonymousClass1 implements Binder.ProxyTransactListener {
        public final Object onTransactStarted(IBinder iBinder, int i) {
            return null;
        }

        public final Object onTransactStarted(IBinder iBinder, int i, int i2) {
            String interfaceDescriptor;
            Object obj = DejankUtils.sLock;
            synchronized (obj) {
                boolean z = true;
                if ((i2 & 1) != 1) {
                    if (!DejankUtils.sBlockingIpcs.empty()) {
                        if (ThreadUtils.sMainThread == null) {
                            ThreadUtils.sMainThread = Looper.getMainLooper().getThread();
                        }
                        if (Thread.currentThread() != ThreadUtils.sMainThread) {
                            z = false;
                        }
                        if (z && !DejankUtils.sTemporarilyIgnoreStrictMode) {
                            try {
                                interfaceDescriptor = iBinder.getInterfaceDescriptor();
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                            synchronized (obj) {
                                try {
                                    if (DejankUtils.sWhitelistedFrameworkClasses.contains(interfaceDescriptor)) {
                                        return null;
                                    }
                                    StrictMode.noteSlowCall("IPC detected on critical path: " + ((String) DejankUtils.sBlockingIpcs.peek()));
                                    return null;
                                } finally {
                                }
                            }
                        }
                    }
                }
                return null;
            }
        }

        public final void onTransactEnded(Object obj) {
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x000b, code lost:
    
        if (android.os.SystemProperties.getBoolean("persist.sysui.strictmode", false) != false) goto L6;
     */
    static {
        /*
            boolean r0 = android.os.Build.IS_ENG
            if (r0 != 0) goto Ld
            java.lang.String r0 = "persist.sysui.strictmode"
            r1 = 0
            boolean r0 = android.os.SystemProperties.getBoolean(r0, r1)
            if (r0 == 0) goto Le
        Ld:
            r1 = 1
        Le:
            com.android.systemui.DejankUtils.STRICT_MODE_ENABLED = r1
            android.view.Choreographer r0 = android.view.Choreographer.getInstance()
            com.android.systemui.DejankUtils.sChoreographer = r0
            android.os.Handler r0 = new android.os.Handler
            r0.<init>()
            com.android.systemui.DejankUtils.sHandler = r0
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            com.android.systemui.DejankUtils.sPendingRunnables = r0
            java.util.Random r0 = new java.util.Random
            r0.<init>()
            com.android.systemui.DejankUtils.sRandom = r0
            java.util.Stack r0 = new java.util.Stack
            r0.<init>()
            com.android.systemui.DejankUtils.sBlockingIpcs = r0
            java.util.HashSet r0 = new java.util.HashSet
            r0.<init>()
            com.android.systemui.DejankUtils.sWhitelistedFrameworkClasses = r0
            java.lang.Object r2 = new java.lang.Object
            r2.<init>()
            com.android.systemui.DejankUtils.sLock = r2
            com.android.systemui.DejankUtils$1 r2 = new com.android.systemui.DejankUtils$1
            r2.<init>()
            if (r1 == 0) goto L76
            java.lang.String r1 = "android.view.IWindowSession"
            r0.add(r1)
            java.lang.String r1 = "com.android.internal.policy.IKeyguardStateCallback"
            r0.add(r1)
            java.lang.String r1 = "android.os.IPowerManager"
            r0.add(r1)
            java.lang.String r1 = "com.android.internal.statusbar.IStatusBarService"
            r0.add(r1)
            android.os.Binder.setProxyTransactListener(r2)
            android.os.StrictMode$ThreadPolicy$Builder r0 = new android.os.StrictMode$ThreadPolicy$Builder
            r0.<init>()
            android.os.StrictMode$ThreadPolicy$Builder r0 = r0.detectCustomSlowCalls()
            android.os.StrictMode$ThreadPolicy$Builder r0 = r0.penaltyFlashScreen()
            android.os.StrictMode$ThreadPolicy$Builder r0 = r0.penaltyLog()
            android.os.StrictMode$ThreadPolicy r0 = r0.build()
            android.os.StrictMode.setThreadPolicy(r0)
        L76:
            com.android.systemui.DejankUtils$$ExternalSyntheticLambda0 r0 = new com.android.systemui.DejankUtils$$ExternalSyntheticLambda0
            r0.<init>()
            com.android.systemui.DejankUtils.sAnimationCallbackRunnable = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.DejankUtils.<clinit>():void");
    }

    public static void notifyRendererOfExpensiveFrame(View view, String str) {
        ViewRootImpl viewRootImpl;
        if (view == null || (viewRootImpl = view.getViewRootImpl()) == null) {
            return;
        }
        if (Trace.isTagEnabled(4096L)) {
            final int nextInt = sRandom.nextInt();
            Trace.asyncTraceForTrackBegin(4096L, "DejankUtils", DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("notifyRendererOfExpensiveFrame (", str, ")"), nextInt);
            postAfterTraversal(new Runnable() { // from class: com.android.systemui.DejankUtils$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    Trace.asyncTraceForTrackEnd(4096L, "DejankUtils", nextInt);
                }
            });
        }
        viewRootImpl.notifyRendererOfExpensiveFrame();
    }

    public static void postAfterTraversal(Runnable runnable) {
        if (sImmediate) {
            runnable.run();
            return;
        }
        Assert.isMainThread();
        sPendingRunnables.add(runnable);
        sChoreographer.postCallback(1, sAnimationCallbackRunnable, null);
    }

    public static void setImmediate(boolean z) {
        sImmediate = z;
    }

    public static void startDetectingBlockingIpcs(String str) {
        if (STRICT_MODE_ENABLED) {
            synchronized (sLock) {
                sBlockingIpcs.push(str);
            }
        }
    }

    public static void stopDetectingBlockingIpcs(String str) {
        if (STRICT_MODE_ENABLED) {
            synchronized (sLock) {
                sBlockingIpcs.remove(str);
            }
        }
    }

    public static void whitelistIpcs(Runnable runnable) {
        if (!STRICT_MODE_ENABLED || sTemporarilyIgnoreStrictMode) {
            runnable.run();
            return;
        }
        Object obj = sLock;
        synchronized (obj) {
            sTemporarilyIgnoreStrictMode = true;
        }
        try {
            runnable.run();
            synchronized (obj) {
                sTemporarilyIgnoreStrictMode = false;
            }
        } catch (Throwable th) {
            synchronized (sLock) {
                sTemporarilyIgnoreStrictMode = false;
                throw th;
            }
        }
    }

    public static Object whitelistIpcs(Supplier supplier) {
        if (STRICT_MODE_ENABLED && !sTemporarilyIgnoreStrictMode) {
            Object obj = sLock;
            synchronized (obj) {
                sTemporarilyIgnoreStrictMode = true;
            }
            try {
                Object obj2 = supplier.get();
                synchronized (obj) {
                    sTemporarilyIgnoreStrictMode = false;
                }
                return obj2;
            } catch (Throwable th) {
                synchronized (sLock) {
                    sTemporarilyIgnoreStrictMode = false;
                    throw th;
                }
            }
        }
        return supplier.get();
    }
}

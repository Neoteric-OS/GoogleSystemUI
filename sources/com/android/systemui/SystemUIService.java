package com.android.systemui;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Process;
import android.os.SystemProperties;
import android.os.Trace;
import android.os.UserHandle;
import android.util.Log;
import android.util.Slog;
import androidx.collection.MutableObjectList$$ExternalSyntheticOutline0;
import com.android.internal.os.BinderInternal;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.dump.DumpHandler;
import com.android.systemui.dump.DumpsysEntry;
import com.android.systemui.dump.LogBufferEulogizer;
import com.android.systemui.dump.LogBufferFreezer;
import com.android.systemui.dump.SystemUIAuxiliaryDumpService;
import com.android.systemui.shared.system.UncaughtExceptionPreHandlerManager;
import com.android.systemui.statusbar.policy.BatteryStateNotifier;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.android.wm.shell.R;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.Thread;
import java.util.Iterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class SystemUIService extends Service {
    public final BatteryStateNotifier mBatteryStateNotifier;
    public final BroadcastDispatcher mBroadcastDispatcher;
    public final DumpHandler mDumpHandler;
    public final LogBufferEulogizer mLogBufferEulogizer;
    public final LogBufferFreezer mLogBufferFreezer;
    public final Handler mMainHandler;
    public final UncaughtExceptionPreHandlerManager mUncaughtExceptionPreHandlerManager;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.SystemUIService$1, reason: invalid class name */
    public final class AnonymousClass1 implements BinderInternal.BinderProxyCountEventListener {
        public final void onLimitReached(int i) {
            StringBuilder m = MutableObjectList$$ExternalSyntheticOutline0.m("uid ", " sent too many Binder proxies to uid ", i);
            m.append(Process.myUid());
            Slog.w("SystemUIService", m.toString());
        }
    }

    public SystemUIService(Handler handler, DumpHandler dumpHandler, BroadcastDispatcher broadcastDispatcher, LogBufferEulogizer logBufferEulogizer, LogBufferFreezer logBufferFreezer, BatteryStateNotifier batteryStateNotifier, UncaughtExceptionPreHandlerManager uncaughtExceptionPreHandlerManager) {
        this.mMainHandler = handler;
        this.mDumpHandler = dumpHandler;
        this.mBroadcastDispatcher = broadcastDispatcher;
        this.mLogBufferEulogizer = logBufferEulogizer;
        this.mLogBufferFreezer = logBufferFreezer;
        this.mBatteryStateNotifier = batteryStateNotifier;
        this.mUncaughtExceptionPreHandlerManager = uncaughtExceptionPreHandlerManager;
    }

    @Override // android.app.Service
    public final void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        if (strArr.length == 0) {
            strArr = new String[]{"--dump-priority", "CRITICAL"};
        }
        this.mDumpHandler.dump(fileDescriptor, printWriter, strArr);
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        ((SystemUIApplication) getApplication()).startSystemUserServicesIfNeeded();
        final LogBufferFreezer logBufferFreezer = this.mLogBufferFreezer;
        BroadcastDispatcher broadcastDispatcher = this.mBroadcastDispatcher;
        logBufferFreezer.getClass();
        BroadcastDispatcher.registerReceiver$default(broadcastDispatcher, new BroadcastReceiver() { // from class: com.android.systemui.dump.LogBufferFreezer$attach$1
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context, Intent intent) {
                final LogBufferFreezer logBufferFreezer2 = LogBufferFreezer.this;
                logBufferFreezer2.getClass();
                Trace.instantForTrack(4096L, "bugreport", "BUGREPORT_STARTED broadcast received");
                ExecutorImpl.ExecutionToken executionToken = logBufferFreezer2.pendingToken;
                if (executionToken != null) {
                    executionToken.run();
                }
                Log.i("LogBufferFreezer", "Freezing log buffers");
                DumpManager dumpManager = logBufferFreezer2.dumpManager;
                synchronized (dumpManager) {
                    Iterator it = dumpManager.buffers.values().iterator();
                    while (it.hasNext()) {
                        ((DumpsysEntry.LogBufferEntry) it.next()).buffer.freeze();
                    }
                }
                logBufferFreezer2.pendingToken = logBufferFreezer2.executor.executeDelayed(new Runnable() { // from class: com.android.systemui.dump.LogBufferFreezer$onBugreportStarted$1
                    @Override // java.lang.Runnable
                    public final void run() {
                        Log.i("LogBufferFreezer", "Unfreezing log buffers");
                        LogBufferFreezer logBufferFreezer3 = LogBufferFreezer.this;
                        logBufferFreezer3.pendingToken = null;
                        DumpManager dumpManager2 = logBufferFreezer3.dumpManager;
                        synchronized (dumpManager2) {
                            Iterator it2 = dumpManager2.buffers.values().iterator();
                            while (it2.hasNext()) {
                                ((DumpsysEntry.LogBufferEntry) it2.next()).buffer.unfreeze();
                            }
                        }
                    }
                }, logBufferFreezer2.freezeDuration);
            }
        }, new IntentFilter("com.android.internal.intent.action.BUGREPORT_STARTED"), logBufferFreezer.executor, UserHandle.ALL, 0, 48);
        this.mUncaughtExceptionPreHandlerManager.registerHandler(new Thread.UncaughtExceptionHandler() { // from class: com.android.systemui.SystemUIService$$ExternalSyntheticLambda0
            @Override // java.lang.Thread.UncaughtExceptionHandler
            public final void uncaughtException(Thread thread, Throwable th) {
                SystemUIService.this.mLogBufferEulogizer.record(th);
            }
        });
        if (getResources().getBoolean(R.bool.config_showNotificationForUnknownBatteryState)) {
            BatteryStateNotifier batteryStateNotifier = this.mBatteryStateNotifier;
            batteryStateNotifier.controller.addCallback(batteryStateNotifier);
        }
        boolean z = Build.IS_DEBUGGABLE;
        if (z && SystemProperties.getBoolean("debug.crash_sysui", false)) {
            throw new RuntimeException();
        }
        if (z) {
            BinderInternal.nSetBinderProxyCountEnabled(true);
            BinderInternal.nSetBinderProxyCountWatermarks(1000, 900, 950);
            BinderInternal.setBinderProxyCountCallback(new AnonymousClass1(), this.mMainHandler);
        }
        startServiceAsUser(new Intent(getApplicationContext(), (Class<?>) SystemUIAuxiliaryDumpService.class), UserHandle.SYSTEM);
    }
}

package com.android.systemui.doze;

import android.content.Context;
import android.content.res.Configuration;
import android.os.PowerManager;
import android.os.SystemClock;
import android.service.dreams.DreamService;
import android.util.Log;
import com.android.keyguard.logging.CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0;
import com.android.systemui.doze.DozeMachine;
import com.android.systemui.doze.dagger.DozeComponent$Builder;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.plugins.DozeServicePlugin;
import com.android.systemui.plugins.Plugin;
import com.android.systemui.plugins.PluginListener;
import com.android.systemui.plugins.PluginManager;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.Executor;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class DozeService extends DreamService implements DozeMachine.Service, DozeServicePlugin.RequestDoze, PluginListener {
    public static final boolean DEBUG = Log.isLoggable("DozeService", 3);
    public final Executor mBgExecutor;
    public final DozeComponent$Builder mDozeComponentBuilder;
    public final DozeLog mDozeLog;
    public DozeMachine mDozeMachine;
    public DozeServicePlugin mDozePlugin;
    public final PluginManager mPluginManager;

    public static void $r8$lambda$7HIus5yemVGjtUEynnG9kyHCKyo(DozeService dozeService, float f) {
        DozeLogger dozeLogger = dozeService.mDozeLog.mLogger;
        dozeLogger.getClass();
        LogLevel logLevel = LogLevel.INFO;
        DozeLogger$logDozeScreenBrightnessFloat$2 dozeLogger$logDozeScreenBrightnessFloat$2 = new Function1() { // from class: com.android.systemui.doze.DozeLogger$logDozeScreenBrightnessFloat$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return "Doze screen brightness " + (logMessage.getBool1() ? "set" : "requested") + " (float), brightness=" + logMessage.getDouble1();
            }
        };
        LogBuffer logBuffer = dozeLogger.buffer;
        LogMessage obtain = logBuffer.obtain("DozeLog", logLevel, dozeLogger$logDozeScreenBrightnessFloat$2, null);
        double d = f;
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.double1 = d;
        logMessageImpl.bool1 = false;
        logBuffer.commit(obtain);
        super.setDozeScreenBrightnessFloat(f);
        DozeLogger dozeLogger2 = dozeService.mDozeLog.mLogger;
        dozeLogger2.getClass();
        LogBuffer logBuffer2 = dozeLogger2.buffer;
        LogMessage obtain2 = logBuffer2.obtain("DozeLog", logLevel, dozeLogger$logDozeScreenBrightnessFloat$2, null);
        LogMessageImpl logMessageImpl2 = (LogMessageImpl) obtain2;
        logMessageImpl2.double1 = d;
        logMessageImpl2.bool1 = true;
        logBuffer2.commit(obtain2);
    }

    /* renamed from: $r8$lambda$k1ekHIF-OHFPt_UaoBk_lbqcv4U, reason: not valid java name */
    public static void m802$r8$lambda$k1ekHIFOHFPt_UaoBk_lbqcv4U(DozeService dozeService, int i) {
        DozeLogger dozeLogger = dozeService.mDozeLog.mLogger;
        dozeLogger.getClass();
        LogLevel logLevel = LogLevel.INFO;
        DozeLogger$logDozeScreenBrightness$2 dozeLogger$logDozeScreenBrightness$2 = new Function1() { // from class: com.android.systemui.doze.DozeLogger$logDozeScreenBrightness$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0.m("Doze screen brightness ", logMessage.getBool1() ? "set" : "requested", " (int), brightness=", logMessage.getInt1());
            }
        };
        LogBuffer logBuffer = dozeLogger.buffer;
        LogMessage obtain = logBuffer.obtain("DozeLog", logLevel, dozeLogger$logDozeScreenBrightness$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.int1 = i;
        logMessageImpl.bool1 = false;
        logBuffer.commit(obtain);
        super.setDozeScreenBrightness(i);
        DozeLogger dozeLogger2 = dozeService.mDozeLog.mLogger;
        dozeLogger2.getClass();
        LogBuffer logBuffer2 = dozeLogger2.buffer;
        LogMessage obtain2 = logBuffer2.obtain("DozeLog", logLevel, dozeLogger$logDozeScreenBrightness$2, null);
        LogMessageImpl logMessageImpl2 = (LogMessageImpl) obtain2;
        logMessageImpl2.int1 = i;
        logMessageImpl2.bool1 = true;
        logBuffer2.commit(obtain2);
    }

    public DozeService(DozeComponent$Builder dozeComponent$Builder, PluginManager pluginManager, DozeLog dozeLog, Executor executor) {
        this.mDozeLog = dozeLog;
        this.mBgExecutor = executor;
        this.mDozeComponentBuilder = dozeComponent$Builder;
        setDebug(DEBUG);
        this.mPluginManager = pluginManager;
    }

    public final void dumpOnHandler(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dumpOnHandler(fileDescriptor, printWriter, strArr);
        DozeMachine dozeMachine = this.mDozeMachine;
        if (dozeMachine != null) {
            printWriter.print(" state=");
            printWriter.println(dozeMachine.mState);
            printWriter.print(" mUiModeType=");
            printWriter.println(dozeMachine.mUiModeType);
            printWriter.print(" wakeLockHeldForCurrentState=");
            printWriter.println(dozeMachine.mWakeLockHeldForCurrentState);
            printWriter.print(" wakeLock=");
            printWriter.println(dozeMachine.mWakeLock);
            printWriter.println("Parts:");
            for (DozeMachine.Part part : dozeMachine.mParts) {
                part.dump(printWriter);
            }
        }
    }

    @Override // android.app.Service, android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mDozeMachine.onConfigurationChanged(configuration);
    }

    @Override // android.service.dreams.DreamService, android.app.Service
    public final void onCreate() {
        super.onCreate();
        setWindowless(true);
        this.mPluginManager.addPluginListener((PluginListener) this, DozeServicePlugin.class, false);
        DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory = (DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory) this.mDozeComponentBuilder;
        daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory.getClass();
        DozeMachine dozeMachine = (DozeMachine) new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl(daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory.sysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory.sysUIGoogleSysUIComponentImpl, this).dozeMachineProvider.get();
        this.mDozeMachine = dozeMachine;
        dozeMachine.onConfigurationChanged(getResources().getConfiguration());
    }

    @Override // android.service.dreams.DreamService, android.app.Service
    public final void onDestroy() {
        PluginManager pluginManager = this.mPluginManager;
        if (pluginManager != null) {
            pluginManager.removePluginListener(this);
        }
        super.onDestroy();
        for (DozeMachine.Part part : this.mDozeMachine.mParts) {
            part.destroy();
        }
        this.mDozeMachine = null;
    }

    @Override // android.service.dreams.DreamService
    public final void onDreamingStarted() {
        super.onDreamingStarted();
        this.mDozeMachine.requestState(DozeMachine.State.INITIALIZED);
        startDozing();
        DozeServicePlugin dozeServicePlugin = this.mDozePlugin;
        if (dozeServicePlugin != null) {
            dozeServicePlugin.onDreamingStarted();
        }
    }

    @Override // android.service.dreams.DreamService
    public final void onDreamingStopped() {
        super.onDreamingStopped();
        this.mDozeMachine.requestState(DozeMachine.State.FINISH);
        DozeServicePlugin dozeServicePlugin = this.mDozePlugin;
        if (dozeServicePlugin != null) {
            dozeServicePlugin.onDreamingStopped();
        }
    }

    @Override // com.android.systemui.plugins.PluginListener
    public final void onPluginConnected(Plugin plugin, Context context) {
        DozeServicePlugin dozeServicePlugin = (DozeServicePlugin) plugin;
        this.mDozePlugin = dozeServicePlugin;
        dozeServicePlugin.setDozeRequester(this);
    }

    @Override // com.android.systemui.plugins.PluginListener
    public final void onPluginDisconnected(Plugin plugin) {
        DozeServicePlugin dozeServicePlugin = this.mDozePlugin;
        if (dozeServicePlugin != null) {
            dozeServicePlugin.onDreamingStopped();
            this.mDozePlugin = null;
        }
    }

    @Override // com.android.systemui.plugins.DozeServicePlugin.RequestDoze
    public final void onRequestHideDoze() {
        DozeMachine dozeMachine = this.mDozeMachine;
        if (dozeMachine != null) {
            dozeMachine.requestState(DozeMachine.State.DOZE);
        }
    }

    @Override // com.android.systemui.plugins.DozeServicePlugin.RequestDoze
    public final void onRequestShowDoze() {
        DozeMachine dozeMachine = this.mDozeMachine;
        if (dozeMachine != null) {
            dozeMachine.requestState(DozeMachine.State.DOZE_AOD);
        }
    }

    @Override // com.android.systemui.doze.DozeMachine.Service
    public final void requestWakeUp(int i) {
        PowerManager powerManager = (PowerManager) getSystemService(PowerManager.class);
        long uptimeMillis = SystemClock.uptimeMillis();
        int i2 = 3;
        if (i != 3) {
            if (i != 4) {
                if (i != 6) {
                    if (i != 9) {
                        i2 = i != 10 ? 4 : 17;
                    }
                }
            }
            i2 = 15;
        } else {
            i2 = 16;
        }
        powerManager.wakeUp(uptimeMillis, i2, "com.android.systemui:NODOZE ".concat(DozeLog.reasonToString(i)));
    }

    @Override // com.android.systemui.doze.DozeMachine.Service
    public final void setDozeScreenBrightness(final int i) {
        this.mBgExecutor.execute(new Runnable() { // from class: com.android.systemui.doze.DozeService$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                DozeService.m802$r8$lambda$k1ekHIFOHFPt_UaoBk_lbqcv4U(DozeService.this, i);
            }
        });
    }

    @Override // com.android.systemui.doze.DozeMachine.Service
    public final void setDozeScreenBrightnessFloat(final float f) {
        this.mBgExecutor.execute(new Runnable() { // from class: com.android.systemui.doze.DozeService$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                DozeService.$r8$lambda$7HIus5yemVGjtUEynnG9kyHCKyo(DozeService.this, f);
            }
        });
    }

    @Override // com.android.systemui.doze.DozeMachine.Service
    public final void setDozeScreenState(int i) {
        this.mDozeLog.traceDisplayState(i, false);
        super.setDozeScreenState(i);
        this.mDozeLog.traceDisplayState(i, true);
        DozeMachine dozeMachine = this.mDozeMachine;
        if (dozeMachine != null) {
            for (DozeMachine.Part part : dozeMachine.mParts) {
                part.onScreenState(i);
            }
        }
    }
}

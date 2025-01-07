package com.android.systemui;

import android.R;
import android.app.ActivityThread;
import android.app.Application;
import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Process;
import android.os.SystemProperties;
import android.os.Trace;
import android.util.Log;
import android.util.TimingsTraceLog;
import android.view.SurfaceControl;
import android.view.ThreadedRenderer;
import android.view.View;
import com.android.app.viewcapture.ViewCapture$$ExternalSyntheticLambda10;
import com.android.internal.protolog.ProtoLog;
import com.android.systemui.SystemUIAppComponentFactoryBase;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.flags.SystemPropertiesHelper;
import com.android.systemui.process.ProcessWrapper;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.phone.CentralSurfaces;
import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.util.NotificationChannels;
import com.android.systemui.util.concurrency.GlobalConcurrencyModule_ProvideMainLooperFactory;
import com.google.android.systemui.SystemUIGoogleInitializer;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
import dagger.internal.Preconditions;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;
import java.util.TreeMap;
import javax.inject.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class SystemUIApplication extends Application implements SystemUIAppComponentFactoryBase.ContextInitializer {
    public BootCompleteCacheImpl mBootCompleteCache;
    public SystemUIAppComponentFactoryBase.ContextAvailableCallback mContextAvailableCallback;
    public SystemUIGoogleInitializer mInitializer;
    public ProcessWrapper mProcessWrapper;
    public CoreStartable[] mServices;
    public boolean mServicesStarted;
    public DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl mSysUIComponent;

    public SystemUIApplication() {
        String currentProcessName = ActivityThread.currentProcessName();
        if (currentProcessName == null || !currentProcessName.contains(":")) {
            Trace.registerWithPerfetto();
        }
        ProtoLog.REQUIRE_PROTOLOGTOOL = false;
    }

    public static void notifyBootCompleted(CoreStartable coreStartable) {
        if (Trace.isEnabled()) {
            Trace.traceBegin(4096L, coreStartable.getClass().getSimpleName().concat(".onBootCompleted()"));
        }
        coreStartable.onBootCompleted();
        Trace.endSection();
    }

    public static void overrideNotificationAppName(Context context, Notification.Builder builder, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putString("android.substName", z ? context.getString(R.string.notification_channel_security) : context.getString(R.string.notification_channel_retail_mode));
        builder.addExtras(bundle);
    }

    public static void startStartable(CoreStartable coreStartable) {
        if (Trace.isEnabled()) {
            Trace.traceBegin(4096L, coreStartable.getClass().getSimpleName().concat(".start()"));
        }
        coreStartable.start();
        Trace.endSection();
    }

    public static void timeInitialization(String str, Runnable runnable, TimingsTraceLog timingsTraceLog, String str2) {
        long currentTimeMillis = System.currentTimeMillis();
        timingsTraceLog.traceBegin(str2 + " " + str);
        runnable.run();
        timingsTraceLog.traceEnd();
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        if (currentTimeMillis2 > 1000) {
            Log.w("SystemUIService", "Initialization of " + str + " took " + currentTimeMillis2 + " ms");
        }
    }

    @Override // android.content.ContextWrapper
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

    @Override // android.app.Application, android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        if (this.mServicesStarted) {
            ConfigurationController configurationController = (ConfigurationController) this.mSysUIComponent.provideGlobalConfigurationControllerProvider.get();
            if (Trace.isEnabled()) {
                Trace.traceBegin(4096L, configurationController.getClass().getSimpleName().concat(".onConfigurationChanged()"));
            }
            ((ConfigurationControllerImpl) configurationController).onConfigurationChanged(configuration);
            Trace.endSection();
        }
    }

    @Override // android.app.Application
    public final void onCreate() {
        final int i = 0;
        super.onCreate();
        TimingsTraceLog timingsTraceLog = new TimingsTraceLog("SystemUIBootTiming", 4096L);
        timingsTraceLog.traceBegin("DependencyInjection");
        SystemUIGoogleInitializer onContextAvailable = this.mContextAvailableCallback.onContextAvailable(this);
        this.mInitializer = onContextAvailable;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = onContextAvailable.mSysUIComponent;
        this.mSysUIComponent = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
        this.mBootCompleteCache = (BootCompleteCacheImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bootCompleteCacheImplProvider.get();
        timingsTraceLog.traceEnd();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.mInitializer.mRootComponent;
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.getClass();
        GlobalConcurrencyModule_ProvideMainLooperFactory.provideMainLooper().setTraceTag(4096L);
        this.mProcessWrapper = new ProcessWrapper();
        setTheme(com.android.wm.shell.R.style.Theme_SystemUI);
        ((SystemPropertiesHelper) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.systemPropertiesHelperProvider.get()).getClass();
        View.setTraceLayoutSteps(SystemProperties.getBoolean("persist.debug.trace_layouts", false));
        ((SystemPropertiesHelper) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.systemPropertiesHelperProvider.get()).getClass();
        View.setTracedRequestLayoutClassClass(SystemProperties.get("persist.debug.trace_request_layout_class", (String) null));
        this.mProcessWrapper.getClass();
        if (!ProcessWrapper.isSystemUser()) {
            String currentProcessName = ActivityThread.currentProcessName();
            if (currentProcessName == null || !currentProcessName.contains(":")) {
                startSecondaryUserServicesIfNeeded();
                return;
            }
            return;
        }
        IntentFilter intentFilter = new IntentFilter("android.intent.action.LOCKED_BOOT_COMPLETED");
        intentFilter.setPriority(1000);
        int gPUContextPriority = SurfaceControl.getGPUContextPriority();
        Log.i("SystemUIService", "Found SurfaceFlinger's GPU Priority: " + gPUContextPriority);
        if (gPUContextPriority == ThreadedRenderer.EGL_CONTEXT_PRIORITY_REALTIME_NV) {
            Log.i("SystemUIService", "Setting SysUI's GPU Context priority to: " + ThreadedRenderer.EGL_CONTEXT_PRIORITY_HIGH_IMG);
            ThreadedRenderer.setContextPriority(ThreadedRenderer.EGL_CONTEXT_PRIORITY_HIGH_IMG);
        }
        registerReceiver(new BroadcastReceiver(this) { // from class: com.android.systemui.SystemUIApplication.1
            public final /* synthetic */ SystemUIApplication this$0;

            {
                this.this$0 = this;
            }

            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context, Intent intent) {
                switch (i) {
                    case 0:
                        if (!this.this$0.mBootCompleteCache.bootComplete.get()) {
                            this.this$0.unregisterReceiver(this);
                            this.this$0.mBootCompleteCache.setBootComplete();
                            SystemUIApplication systemUIApplication = this.this$0;
                            if (systemUIApplication.mServicesStarted) {
                                int length = systemUIApplication.mServices.length;
                                for (int i2 = 0; i2 < length; i2++) {
                                    SystemUIApplication.notifyBootCompleted(this.this$0.mServices[i2]);
                                }
                                break;
                            }
                        }
                        break;
                    default:
                        if ("android.intent.action.LOCALE_CHANGED".equals(intent.getAction()) && this.this$0.mBootCompleteCache.bootComplete.get()) {
                            NotificationChannels.createAll(context);
                            break;
                        }
                        break;
                }
            }
        }, intentFilter);
        final int i2 = 1;
        registerReceiver(new BroadcastReceiver(this) { // from class: com.android.systemui.SystemUIApplication.1
            public final /* synthetic */ SystemUIApplication this$0;

            {
                this.this$0 = this;
            }

            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context, Intent intent) {
                switch (i2) {
                    case 0:
                        if (!this.this$0.mBootCompleteCache.bootComplete.get()) {
                            this.this$0.unregisterReceiver(this);
                            this.this$0.mBootCompleteCache.setBootComplete();
                            SystemUIApplication systemUIApplication = this.this$0;
                            if (systemUIApplication.mServicesStarted) {
                                int length = systemUIApplication.mServices.length;
                                for (int i22 = 0; i22 < length; i22++) {
                                    SystemUIApplication.notifyBootCompleted(this.this$0.mServices[i22]);
                                }
                                break;
                            }
                        }
                        break;
                    default:
                        if ("android.intent.action.LOCALE_CHANGED".equals(intent.getAction()) && this.this$0.mBootCompleteCache.bootComplete.get()) {
                            NotificationChannels.createAll(context);
                            break;
                        }
                        break;
                }
            }
        }, new IntentFilter("android.intent.action.LOCALE_CHANGED"));
    }

    @Override // com.android.systemui.SystemUIAppComponentFactoryBase.ContextInitializer
    public final void setContextAvailableCallback(SystemUIAppComponentFactoryBase.ContextAvailableCallback contextAvailableCallback) {
        this.mContextAvailableCallback = contextAvailableCallback;
    }

    public final void startSecondaryUserServicesIfNeeded() {
        this.mProcessWrapper.getClass();
        if (ProcessWrapper.isSystemUser()) {
            return;
        }
        TreeMap treeMap = new TreeMap(Comparator.comparing(new ViewCapture$$ExternalSyntheticLambda10()));
        treeMap.putAll(Collections.singletonMap(NotificationChannels.class, this.mSysUIComponent.notificationChannelsProvider));
        startServicesIfNeeded(treeMap, "StartSecondaryServices", null);
    }

    public final void startServicesIfNeeded(Map map, String str, final String str2) {
        ArrayDeque arrayDeque;
        if (this.mServicesStarted) {
            return;
        }
        this.mServices = new CoreStartable[map.size() + (str2 == null ? 0 : 1)];
        if (!this.mBootCompleteCache.bootComplete.get()) {
            ((SystemPropertiesHelper) this.mInitializer.mRootComponent.systemPropertiesHelperProvider.get()).getClass();
            if ("1".equals(SystemProperties.get("sys.boot_completed"))) {
                this.mBootCompleteCache.setBootComplete();
            }
        }
        DumpManager dumpManager = (DumpManager) this.mSysUIComponent.sysUIGoogleGlobalRootComponentImpl.dumpManagerProvider.get();
        Process.myUserHandle().getIdentifier();
        TimingsTraceLog timingsTraceLog = new TimingsTraceLog("SystemUIBootTiming", 4096L);
        timingsTraceLog.traceBegin(str);
        HashSet hashSet = new HashSet();
        timingsTraceLog.traceBegin("Topologically start Core Startables");
        ArrayDeque arrayDeque2 = new ArrayDeque(((TreeMap) map).entrySet());
        int i = 0;
        final int i2 = 0;
        while (true) {
            arrayDeque = new ArrayDeque(map.size());
            boolean z = false;
            while (!arrayDeque2.isEmpty()) {
                final Map.Entry entry = (Map.Entry) arrayDeque2.removeFirst();
                Class cls = (Class) entry.getKey();
                this.mSysUIComponent.getClass();
                Set of = Set.of(CentralSurfaces.class);
                Preconditions.checkNotNullFromProvides(of);
                Set set = (Set) Collections.singletonMap(SysuiStatusBarStateController.class, of).get(cls);
                if (set == null || hashSet.containsAll(set)) {
                    final String name = cls.getName();
                    timeInitialization(name, new Runnable() { // from class: com.android.systemui.SystemUIApplication$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            SystemUIApplication systemUIApplication = SystemUIApplication.this;
                            int i3 = i2;
                            String str3 = name;
                            Map.Entry entry2 = entry;
                            CoreStartable[] coreStartableArr = systemUIApplication.mServices;
                            Provider provider = (Provider) entry2.getValue();
                            if (Trace.isEnabled()) {
                                Trace.traceBegin(4096L, "Provider<" + str3 + ">.get()");
                            }
                            CoreStartable coreStartable = (CoreStartable) provider.get();
                            Trace.endSection();
                            SystemUIApplication.startStartable(coreStartable);
                            coreStartableArr[i3] = coreStartable;
                        }
                    }, timingsTraceLog, str);
                    hashSet.add(cls);
                    i2++;
                    z = true;
                } else {
                    arrayDeque.add(entry);
                }
            }
            i++;
            if (!z || arrayDeque.isEmpty()) {
                break;
            } else {
                arrayDeque2 = arrayDeque;
            }
        }
        if (!arrayDeque.isEmpty()) {
            while (!arrayDeque.isEmpty()) {
                Class cls2 = (Class) ((Map.Entry) arrayDeque.removeFirst()).getKey();
                this.mSysUIComponent.getClass();
                Set of2 = Set.of(CentralSurfaces.class);
                Preconditions.checkNotNullFromProvides(of2);
                Set<Class> set2 = (Set) Collections.singletonMap(SysuiStatusBarStateController.class, of2).get(cls2);
                StringJoiner stringJoiner = new StringJoiner(", ");
                for (Class cls3 : set2) {
                    if (!hashSet.contains(cls3)) {
                        stringJoiner.add(cls3.getName());
                    }
                }
                Log.e("SystemUIService", "Failed to start " + cls2.getName() + ". Missing dependencies: [" + stringJoiner + "]");
            }
            throw new RuntimeException("Failed to start all CoreStartables. Check logcat!");
        }
        Log.i("SystemUIService", "Topological CoreStartables completed in " + i + " iterations");
        timingsTraceLog.traceEnd();
        if (str2 != null) {
            timeInitialization(str2, new Runnable() { // from class: com.android.systemui.SystemUIApplication$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    SystemUIApplication systemUIApplication = SystemUIApplication.this;
                    String str3 = str2;
                    CoreStartable[] coreStartableArr = systemUIApplication.mServices;
                    int length = coreStartableArr.length - 1;
                    if (Trace.isEnabled()) {
                        Trace.traceBegin(4096L, str3 + ".newInstance()");
                    }
                    try {
                        try {
                            Class[] clsArr = new Class[0];
                            CoreStartable coreStartable = (CoreStartable) Class.forName(str3).getDeclaredConstructor(null).newInstance(null);
                            Trace.endSection();
                            SystemUIApplication.startStartable(coreStartable);
                            coreStartableArr[length] = coreStartable;
                        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
                            throw new RuntimeException(e);
                        }
                    } catch (Throwable th) {
                        Trace.endSection();
                        throw th;
                    }
                }
            }, timingsTraceLog, str);
        }
        int i3 = 0;
        while (true) {
            CoreStartable[] coreStartableArr = this.mServices;
            if (i3 >= coreStartableArr.length) {
                break;
            }
            CoreStartable coreStartable = coreStartableArr[i3];
            if (this.mBootCompleteCache.bootComplete.get()) {
                notifyBootCompleted(coreStartable);
            }
            if (coreStartable.isDumpCritical()) {
                dumpManager.getClass();
                dumpManager.registerCriticalDumpable(coreStartable.getClass().getCanonicalName(), coreStartable);
            } else {
                dumpManager.registerNormalDumpable(coreStartable);
            }
            i3++;
        }
        InitController initController = (InitController) this.mSysUIComponent.initControllerProvider.get();
        while (!initController.mTasks.isEmpty()) {
            ((Runnable) initController.mTasks.remove(0)).run();
        }
        initController.mTasksExecuted = true;
        timingsTraceLog.traceEnd();
        this.mServicesStarted = true;
    }

    public final void startSystemUserServicesIfNeeded() {
        this.mProcessWrapper.getClass();
        if (!ProcessWrapper.isSystemUser()) {
            Log.wtf("SystemUIService", "Tried starting SystemUser services on non-SystemUser");
            return;
        }
        SystemUIGoogleInitializer systemUIGoogleInitializer = this.mInitializer;
        Resources resources = getResources();
        systemUIGoogleInitializer.getClass();
        String string = resources.getString(com.android.wm.shell.R.string.config_systemUIVendorServiceComponent);
        TreeMap treeMap = new TreeMap(Comparator.comparing(new ViewCapture$$ExternalSyntheticLambda10()));
        treeMap.putAll(this.mSysUIComponent.getStartables());
        treeMap.putAll(Collections.singletonMap(NotificationChannels.class, this.mSysUIComponent.notificationChannelsProvider));
        startServicesIfNeeded(treeMap, "StartServices", string);
    }
}

package com.google.android.systemui.power.batteryhealth;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.util.Log;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import androidx.exifinterface.media.ExifInterface$$ExternalSyntheticOutline0;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.wm.shell.R;
import java.util.Arrays;
import java.util.Set;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ObservableProperty;
import kotlin.reflect.KProperty;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.StandaloneCoroutine;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class HealthService extends Service {
    public static final /* synthetic */ KProperty[] $$delegatedProperties;
    public static final boolean healthDebugEnabled;
    public static final Set supportedCallers;
    public final Context context;
    public final boolean healthFeatureEnabled;
    public final HealthManager healthManager;
    public StandaloneCoroutine subscribeJob;
    public final RemoteCallbackList healthListeners = new RemoteCallbackList();
    public final ContextScope mainScope = CoroutineScopeKt.MainScope();
    public final HealthService$binder$1 binder = new HealthService$binder$1(this);
    public final HealthService$special$$inlined$observable$1 registeredListenerNum$delegate = new ObservableProperty() { // from class: com.google.android.systemui.power.batteryhealth.HealthService$special$$inlined$observable$1
        {
            super(0);
        }

        @Override // kotlin.properties.ObservableProperty
        public final void afterChange(Object obj, Object obj2) {
            int intValue = ((Number) obj2).intValue();
            int intValue2 = ((Number) obj).intValue();
            Log.i("HealthService", "registered listeners num from " + intValue2 + " to " + intValue);
            HealthService healthService = HealthService.this;
            if (intValue2 == 0 && intValue == 1) {
                healthService.subscribeJob = BuildersKt.launch$default(healthService.mainScope, null, null, new HealthService$registeredListenerNum$2$1(healthService, null), 3);
            }
            if (intValue == 0) {
                StandaloneCoroutine standaloneCoroutine = healthService.subscribeJob;
                (standaloneCoroutine != null ? standaloneCoroutine : null).cancel(null);
            }
        }
    };

    static {
        MutablePropertyReference1Impl mutablePropertyReference1Impl = new MutablePropertyReference1Impl(HealthService.class, "registeredListenerNum", "getRegisteredListenerNum()I", 0);
        Reflection.factory.getClass();
        $$delegatedProperties = new KProperty[]{mutablePropertyReference1Impl};
        supportedCallers = SetsKt.setOf("com.android.settings", "com.android.systemui", "com.google.android.apps.diagnosticstool", "com.google.android.apps.pixel.support", "com.google.android.settings.intelligence");
        healthDebugEnabled = Build.IS_DEBUGGABLE;
    }

    /* JADX WARN: Type inference failed for: r1v6, types: [com.google.android.systemui.power.batteryhealth.HealthService$special$$inlined$observable$1] */
    public HealthService(Context context, HealthManager healthManager, Resources resources) {
        this.context = context;
        this.healthManager = healthManager;
        this.healthFeatureEnabled = resources.getBoolean(R.bool.config_battery_index_enabled);
    }

    public static final String[] access$ensureSupportedCallers(HealthService healthService) {
        healthService.getClass();
        int callingUid = Binder.getCallingUid();
        ExifInterface$$ExternalSyntheticOutline0.m("ensureSupportedCallers: pkg=", "HealthService", callingUid);
        String[] packagesForUid = healthService.context.getPackageManager().getPackagesForUid(callingUid);
        if (packagesForUid != null) {
            for (String str : packagesForUid) {
                if (!supportedCallers.contains(str)) {
                }
            }
            throw new SecurityException(AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("ensureSupportedCallers: ", Arrays.toString(packagesForUid)));
        }
        return packagesForUid;
    }

    public static final Object access$notifyListeners(HealthService healthService, Function1 function1, Continuation continuation) {
        healthService.getClass();
        Object coroutineScope = CoroutineScopeKt.coroutineScope(continuation, new HealthService$notifyListeners$2(healthService, function1, null));
        return coroutineScope == CoroutineSingletons.COROUTINE_SUSPENDED ? coroutineScope : Unit.INSTANCE;
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        Log.i("HealthService", "HealthService bound");
        return this.healthFeatureEnabled ? this.binder : new Binder();
    }

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        if (healthDebugEnabled) {
            HealthManager healthManager = this.healthManager;
            healthManager.getClass();
            if (HealthManager.healthDebugEnabled) {
                Log.d("HealthManager", "register healthDebugReceiver");
                BroadcastDispatcher.registerReceiver$default(healthManager.broadcastDispatcher, healthManager.healthDebugReceiver, new IntentFilter("com.google.android.systemui.BATTERY_HEALTH_DEBUG"), null, null, 0, 60);
            }
        }
    }

    @Override // android.app.Service
    public final void onDestroy() {
        super.onDestroy();
        if (healthDebugEnabled) {
            HealthManager healthManager = this.healthManager;
            healthManager.getClass();
            if (HealthManager.healthDebugEnabled) {
                Log.d("HealthManager", "unregister healthDebugReceiver");
                healthManager.broadcastDispatcher.unregisterReceiver(healthManager.healthDebugReceiver);
            }
        }
    }

    public static /* synthetic */ void getHealthListeners$annotations() {
    }

    public static /* synthetic */ void getSubscribeJob$annotations() {
    }
}

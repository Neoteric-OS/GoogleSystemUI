package com.google.android.systemui.power.batteryhealth;

import android.app.AlarmManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.wm.shell.R;
import java.time.Duration;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.StandaloneCoroutine;
import vendor.google.google_battery.IGoogleBattery;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class HealthManager {
    public static final boolean healthDebugEnabled = Build.IS_DEBUGGABLE;
    public static final Duration updatePeriod = Duration.ofDays(1);
    public final AlarmManager alarmManager;
    public final CoroutineDispatcher bgDispatcher;
    public final HealthManager$healthDebugReceiver$1 bootCompletedReceiver;
    public final BroadcastDispatcher broadcastDispatcher;
    public final Context context;
    public IGoogleBattery googleBattery;
    public final HealthManager$healthDebugReceiver$1 healthDebugReceiver;
    public final StandaloneCoroutine initializer;
    public final CoroutineScope mainScope;
    public final boolean periodicUpdateEnabled;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.google.android.systemui.power.batteryhealth.HealthManager$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;

        public AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return HealthManager.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            Unit unit = Unit.INSTANCE;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                HealthManager healthManager = HealthManager.this;
                this.label = 1;
                boolean z = HealthManager.healthDebugEnabled;
                healthManager.getClass();
                Object withContext = BuildersKt.withContext(healthManager.bgDispatcher, new HealthManager$runOnBackground$2(null, new HealthManager$initHalInterface$2(healthManager, null)), this);
                if (withContext != coroutineSingletons) {
                    withContext = unit;
                }
                if (withContext != coroutineSingletons) {
                    withContext = unit;
                }
                if (withContext == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return unit;
        }
    }

    /* JADX WARN: Type inference failed for: r1v3, types: [com.google.android.systemui.power.batteryhealth.HealthManager$healthDebugReceiver$1] */
    /* JADX WARN: Type inference failed for: r1v4, types: [com.google.android.systemui.power.batteryhealth.HealthManager$healthDebugReceiver$1] */
    public HealthManager(Context context, AlarmManager alarmManager, BroadcastDispatcher broadcastDispatcher, CoroutineDispatcher coroutineDispatcher, CoroutineScope coroutineScope) {
        this.context = context;
        this.alarmManager = alarmManager;
        this.broadcastDispatcher = broadcastDispatcher;
        this.bgDispatcher = coroutineDispatcher;
        this.mainScope = coroutineScope;
        this.periodicUpdateEnabled = context.getResources().getBoolean(R.bool.config_battery_health_periodic_update_enabled);
        final int i = 1;
        this.bootCompletedReceiver = new BroadcastReceiver(this) { // from class: com.google.android.systemui.power.batteryhealth.HealthManager$healthDebugReceiver$1
            public final /* synthetic */ HealthManager this$0;

            {
                this.this$0 = this;
            }

            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context2, Intent intent) {
                switch (i) {
                    case 0:
                        Log.d("HealthManager", "onReceive: " + intent);
                        if (HealthManager.healthDebugEnabled && "com.google.android.systemui.BATTERY_HEALTH_DEBUG".equals(intent.getAction())) {
                            HealthManager healthManager = this.this$0;
                            BuildersKt.launch$default(healthManager.mainScope, null, null, new HealthManager$healthDebugReceiver$1$onReceive$1(healthManager, null), 3);
                            break;
                        }
                        break;
                    default:
                        Log.d("HealthManager", "onReceive: " + intent);
                        if (StringsKt__StringsJVMKt.equals(intent.getAction(), "android.intent.action.BOOT_COMPLETED", false)) {
                            this.this$0.broadcastDispatcher.unregisterReceiver(this);
                            HealthManager healthManager2 = this.this$0;
                            BuildersKt.launch$default(healthManager2.mainScope, null, null, new HealthManager$bootCompletedReceiver$1$onReceive$1(healthManager2, null), 3);
                            break;
                        }
                        break;
                }
            }
        };
        final int i2 = 0;
        this.healthDebugReceiver = new BroadcastReceiver(this) { // from class: com.google.android.systemui.power.batteryhealth.HealthManager$healthDebugReceiver$1
            public final /* synthetic */ HealthManager this$0;

            {
                this.this$0 = this;
            }

            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context2, Intent intent) {
                switch (i2) {
                    case 0:
                        Log.d("HealthManager", "onReceive: " + intent);
                        if (HealthManager.healthDebugEnabled && "com.google.android.systemui.BATTERY_HEALTH_DEBUG".equals(intent.getAction())) {
                            HealthManager healthManager = this.this$0;
                            BuildersKt.launch$default(healthManager.mainScope, null, null, new HealthManager$healthDebugReceiver$1$onReceive$1(healthManager, null), 3);
                            break;
                        }
                        break;
                    default:
                        Log.d("HealthManager", "onReceive: " + intent);
                        if (StringsKt__StringsJVMKt.equals(intent.getAction(), "android.intent.action.BOOT_COMPLETED", false)) {
                            this.this$0.broadcastDispatcher.unregisterReceiver(this);
                            HealthManager healthManager2 = this.this$0;
                            BuildersKt.launch$default(healthManager2.mainScope, null, null, new HealthManager$bootCompletedReceiver$1$onReceive$1(healthManager2, null), 3);
                            break;
                        }
                        break;
                }
            }
        };
        this.initializer = BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass1(null), 3);
    }

    public static /* synthetic */ void getGoogleBattery$annotations() {
    }

    public static /* synthetic */ void getInitializer$annotations() {
    }
}

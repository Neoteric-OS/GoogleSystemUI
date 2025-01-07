package com.google.android.systemui.power;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Parcel;
import android.os.RemoteException;
import android.provider.Settings;
import android.util.Log;
import com.android.internal.logging.UiEventLogger;
import com.android.settingslib.fuelgauge.BatteryStatus;
import com.android.settingslib.mobile.MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0;
import com.android.systemui.util.time.SystemClockImpl;
import com.android.wm.shell.R;
import com.google.android.systemui.power.DwellTempDefenderNotification;
import com.google.android.systemui.power.batteryevent.repository.GoogleBatteryManagerWrapperImpl;
import java.util.concurrent.Executor;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.ExecutorsKt;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.android.HandlerContext;
import kotlinx.coroutines.internal.ContextScope;
import kotlinx.coroutines.internal.MainDispatcherLoader;
import kotlinx.coroutines.scheduling.DefaultScheduler;
import vendor.google.google_battery.IGoogleBattery;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BatteryDefenderNotificationHandler {
    public static final /* synthetic */ int $r8$clinit = 0;
    public static final long POST_NOTIFICATION_THRESHOLD_MILLIS;
    public final ContextScope backgroundCoroutineScope;
    public final CoroutineDispatcher backgroundDispatcher;
    public int batteryLevel;
    public final Context context;
    public boolean dockDefendEnabled;
    public final Lazy dockDefenderNotification$delegate;
    public final Lazy dwellTempDefenderNotification$delegate;
    public final GoogleBatteryManagerWrapperImpl googleBatteryManagerWrapper;
    public boolean inDefenderSection;
    public final CoroutineDispatcher mainDispatcher;
    public final NotificationManager notificationManager;
    public final Lazy sharedPreferences$delegate;
    public final SystemClockImpl systemClock;
    public final UiEventLogger uiEventLogger;

    static {
        int i = Duration.$r8$clinit;
        POST_NOTIFICATION_THRESHOLD_MILLIS = Duration.m1777getInWholeMillisecondsimpl(DurationKt.toDuration(10, DurationUnit.MINUTES));
    }

    public BatteryDefenderNotificationHandler(Context context, UiEventLogger uiEventLogger, Executor executor) {
        DefaultScheduler defaultScheduler = Dispatchers.Default;
        HandlerContext handlerContext = MainDispatcherLoader.dispatcher;
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NotificationManager.class);
        GoogleBatteryManagerWrapperImpl googleBatteryManagerWrapperImpl = new GoogleBatteryManagerWrapperImpl();
        SystemClockImpl systemClockImpl = new SystemClockImpl();
        this.context = context;
        this.uiEventLogger = uiEventLogger;
        this.mainDispatcher = handlerContext;
        this.notificationManager = notificationManager;
        this.googleBatteryManagerWrapper = googleBatteryManagerWrapperImpl;
        this.systemClock = systemClockImpl;
        this.dockDefendEnabled = context.getResources().getBoolean(R.bool.config_tablet_device);
        CoroutineDispatcher from = ExecutorsKt.from(executor);
        this.backgroundDispatcher = from;
        this.backgroundCoroutineScope = CoroutineScopeKt.CoroutineScope(CoroutineContext.DefaultImpls.plus(from, SupervisorKt.SupervisorJob$default()));
        this.batteryLevel = -1;
        this.sharedPreferences$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.google.android.systemui.power.BatteryDefenderNotificationHandler$sharedPreferences$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return BatteryDefenderNotificationHandler.this.context.getApplicationContext().getSharedPreferences("defender_shared_prefs", 0);
            }
        });
        this.dockDefenderNotification$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.google.android.systemui.power.BatteryDefenderNotificationHandler$dockDefenderNotification$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                BatteryDefenderNotificationHandler batteryDefenderNotificationHandler = BatteryDefenderNotificationHandler.this;
                return new DockDefenderNotification(batteryDefenderNotificationHandler.context, batteryDefenderNotificationHandler.notificationManager);
            }
        });
        this.dwellTempDefenderNotification$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.google.android.systemui.power.BatteryDefenderNotificationHandler$dwellTempDefenderNotification$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                BatteryDefenderNotificationHandler batteryDefenderNotificationHandler = BatteryDefenderNotificationHandler.this;
                return new DwellTempDefenderNotification(batteryDefenderNotificationHandler.context, batteryDefenderNotificationHandler.notificationManager, batteryDefenderNotificationHandler.uiEventLogger);
            }
        });
    }

    public static final Object access$bypassDefenderMode(BatteryDefenderNotificationHandler batteryDefenderNotificationHandler, final int i, SuspendLambda suspendLambda) {
        if (i == 4) {
            Settings.Global.putInt(batteryDefenderNotificationHandler.context.getContentResolver(), "dock_defender_bypass", 1);
        } else {
            batteryDefenderNotificationHandler.getClass();
        }
        Function1 function1 = new Function1() { // from class: com.google.android.systemui.power.BatteryDefenderNotificationHandler$bypassDefenderMode$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                int i2 = i;
                IGoogleBattery.Stub.Proxy proxy = (IGoogleBattery.Stub.Proxy) ((IGoogleBattery) obj);
                Parcel obtain = Parcel.obtain(proxy.mRemote);
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IGoogleBattery.DESCRIPTOR);
                    obtain.writeInt(i2);
                    if (!proxy.mRemote.transact(22, obtain, obtain2, 0)) {
                        throw new RemoteException("Method clearBatteryDefenders is unimplemented.");
                    }
                    obtain2.readException();
                    obtain2.recycle();
                    obtain.recycle();
                    return Unit.INSTANCE;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }
        };
        batteryDefenderNotificationHandler.getClass();
        Object withContext = BuildersKt.withContext(batteryDefenderNotificationHandler.backgroundDispatcher, new BatteryDefenderNotificationHandler$withGoogleBattery$2(batteryDefenderNotificationHandler, function1, null), suspendLambda);
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        Unit unit = Unit.INSTANCE;
        if (withContext != coroutineSingletons) {
            withContext = unit;
        }
        return withContext == coroutineSingletons ? withContext : unit;
    }

    public final DwellTempDefenderNotification getDwellTempDefenderNotification() {
        return (DwellTempDefenderNotification) this.dwellTempDefenderNotification$delegate.getValue();
    }

    public final void onBatteryDefenderEvent(int i, DwellTempDefenderNotification.BatteryDefendType batteryDefendType) {
        boolean isPluggedIn = BatteryStatus.isPluggedIn(i);
        Log.d("BatteryDefenderNotification", "onBatteryDefenderEvent, pluggedIn:" + isPluggedIn + ", inDefenderSection:" + this.inDefenderSection);
        if (this.inDefenderSection) {
            DwellTempDefenderNotification dwellTempDefenderNotification = getDwellTempDefenderNotification();
            MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0.m("updateNotificationIfNeeded, notificationVisible:", "DwellTempDefenderNotification", dwellTempDefenderNotification.notificationVisible);
            if (!dwellTempDefenderNotification.notificationVisible || isPluggedIn == dwellTempDefenderNotification.lastPlugged) {
                return;
            }
            dwellTempDefenderNotification.lastPlugged = isPluggedIn;
            dwellTempDefenderNotification.sendDefenderNotification(isPluggedIn, batteryDefendType);
            return;
        }
        this.inDefenderSection = true;
        DwellTempDefenderNotification dwellTempDefenderNotification2 = getDwellTempDefenderNotification();
        Log.d("DwellTempDefenderNotification", "showNotification, postNotificationVisible:" + dwellTempDefenderNotification2.postNotificationVisible + "->true");
        if (dwellTempDefenderNotification2.postNotificationVisible) {
            dwellTempDefenderNotification2.cancelPostNotification();
        }
        dwellTempDefenderNotification2.sendDefenderNotification(isPluggedIn, batteryDefendType);
        dwellTempDefenderNotification2.notificationVisible = true;
        dwellTempDefenderNotification2.lastPlugged = isPluggedIn;
        UiEventLogger uiEventLogger = this.uiEventLogger;
        if (uiEventLogger != null) {
            uiEventLogger.log(BatteryMetricEvent.BATTERY_DEFENDER_NOTIFICATION);
        }
        BuildersKt.launch$default(this.backgroundCoroutineScope, null, null, new BatteryDefenderNotificationHandler$onBatteryDefenderEvent$1(this, null), 3);
    }

    public final void setDockDefenderEnabled(boolean z) {
        this.dockDefendEnabled = z;
    }
}

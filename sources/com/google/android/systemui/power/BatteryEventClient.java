package com.google.android.systemui.power;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.systemui.power.batteryevent.aidl.IBatteryEventService;
import com.google.android.systemui.power.batteryevent.aidl.IBatteryEventService$Stub$Proxy;
import com.google.android.systemui.power.batteryevent.aidl.SurfaceType;
import com.google.android.systemui.power.batteryevent.domain.BatteryEventService$binder$1;
import java.util.ArrayList;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BatteryEventClient {
    public final CoroutineDispatcher backgroundDispatcher;
    public String callerTag;
    public final BatteryEventClient$connection$1 connection;
    public final Context context;
    public final CoroutineScope coroutineScope;
    public final Function3 emptyCallback;
    public final BatteryEventClient$listener$1 listener;
    public final BatteryEventClient$logWithCaller$1 logWithCaller;
    public Function3 onBatteryEventUpdate;
    public IBatteryEventService service;
    public final ArrayList subscribedBatteryEvents;
    public SurfaceType surfaceType;

    /* JADX WARN: Type inference failed for: r1v5, types: [com.google.android.systemui.power.BatteryEventClient$connection$1] */
    public BatteryEventClient(Context context, CoroutineDispatcher coroutineDispatcher, CoroutineScope coroutineScope) {
        this.context = context;
        this.coroutineScope = coroutineScope;
        this.backgroundDispatcher = coroutineDispatcher;
        Function3 function3 = new Function3() { // from class: com.google.android.systemui.power.BatteryEventClient$emptyCallback$1
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                ((Number) obj2).intValue();
                ((Number) obj3).intValue();
                BatteryEventClient.this.logWithCaller.d("No callback for battery event update");
                return Unit.INSTANCE;
            }
        };
        this.emptyCallback = function3;
        this.onBatteryEventUpdate = function3;
        this.callerTag = "--";
        this.subscribedBatteryEvents = new ArrayList();
        this.listener = new BatteryEventClient$listener$1(this);
        this.connection = new ServiceConnection() { // from class: com.google.android.systemui.power.BatteryEventClient$connection$1
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r2v3, types: [com.google.android.systemui.power.batteryevent.aidl.IBatteryEventService] */
            @Override // android.content.ServiceConnection
            public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                IBatteryEventService$Stub$Proxy iBatteryEventService$Stub$Proxy;
                Unit unit;
                BatteryEventClient batteryEventClient = BatteryEventClient.this;
                int i = BatteryEventService$binder$1.$r8$clinit;
                if (iBinder == null) {
                    iBatteryEventService$Stub$Proxy = null;
                } else {
                    IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.systemui.power.batteryevent.aidl.IBatteryEventService");
                    if (queryLocalInterface == null || !(queryLocalInterface instanceof IBatteryEventService)) {
                        IBatteryEventService$Stub$Proxy iBatteryEventService$Stub$Proxy2 = new IBatteryEventService$Stub$Proxy();
                        iBatteryEventService$Stub$Proxy2.mRemote = iBinder;
                        iBatteryEventService$Stub$Proxy = iBatteryEventService$Stub$Proxy2;
                    } else {
                        iBatteryEventService$Stub$Proxy = (IBatteryEventService) queryLocalInterface;
                    }
                }
                batteryEventClient.service = iBatteryEventService$Stub$Proxy;
                try {
                    BatteryEventClient batteryEventClient2 = BatteryEventClient.this;
                    IBatteryEventService iBatteryEventService = batteryEventClient2.service;
                    if (iBatteryEventService != null) {
                        iBatteryEventService.registerBatteryEventsCallback(batteryEventClient2.listener, batteryEventClient2.subscribedBatteryEvents, batteryEventClient2.surfaceType);
                        unit = Unit.INSTANCE;
                    } else {
                        unit = null;
                    }
                    if (unit == null) {
                        BatteryEventClient batteryEventClient3 = BatteryEventClient.this;
                        BatteryEventClient$logWithCaller$1 batteryEventClient$logWithCaller$1 = batteryEventClient3.logWithCaller;
                        SurfaceType surfaceType = batteryEventClient3.surfaceType;
                        batteryEventClient$logWithCaller$1.w("bound service for " + (surfaceType != null ? surfaceType.name() : null) + " failed");
                    }
                } catch (RemoteException e) {
                    Log.e("BatteryEventClient", "[" + BatteryEventClient.this.logWithCaller.this$0.callerTag + "] unexpected exception for registerBatteryEventCallback", e);
                }
            }

            @Override // android.content.ServiceConnection
            public final void onServiceDisconnected(ComponentName componentName) {
                BatteryEventClient.this.logWithCaller.d("onServiceDisconnected");
                BatteryEventClient batteryEventClient = BatteryEventClient.this;
                batteryEventClient.callerTag = "--";
                batteryEventClient.onBatteryEventUpdate = batteryEventClient.emptyCallback;
                batteryEventClient.subscribedBatteryEvents.clear();
                BatteryEventClient batteryEventClient2 = BatteryEventClient.this;
                batteryEventClient2.surfaceType = null;
                batteryEventClient2.service = null;
            }
        };
        this.logWithCaller = new BatteryEventClient$logWithCaller$1(this);
    }
}

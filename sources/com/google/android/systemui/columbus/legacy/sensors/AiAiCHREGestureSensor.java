package com.google.android.systemui.columbus.legacy.sensors;

import android.app.ambientcontext.AmbientContextCallback;
import android.app.ambientcontext.AmbientContextEvent;
import android.app.ambientcontext.AmbientContextEventRequest;
import android.app.ambientcontext.AmbientContextManager;
import android.frameworks.stats.IStats;
import android.frameworks.stats.VendorAtom;
import android.frameworks.stats.VendorAtomValue;
import android.os.Handler;
import android.os.ServiceManager;
import android.util.Log;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.util.time.SystemClockImpl;
import com.google.android.systemui.columbus.ColumbusEvent;
import com.google.android.systemui.columbus.legacy.sensors.GestureSensor;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AiAiCHREGestureSensor extends GestureSensor {
    public final AiAiCHREGestureSensor$ambientContextCallback$1 ambientContextCallback;
    public final AmbientContextManager ambientContextManager;
    public final Handler bgHandler;
    public boolean isListening;
    public final Executor mainExecutor;
    public final UiEventLogger uiEventLogger;

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.android.systemui.columbus.legacy.sensors.AiAiCHREGestureSensor$ambientContextCallback$1] */
    public AiAiCHREGestureSensor(UiEventLogger uiEventLogger, AmbientContextManager ambientContextManager, Handler handler, Executor executor, final SystemClock systemClock) {
        this.uiEventLogger = uiEventLogger;
        this.ambientContextManager = ambientContextManager;
        this.bgHandler = handler;
        this.mainExecutor = executor;
        this.ambientContextCallback = new AmbientContextCallback() { // from class: com.google.android.systemui.columbus.legacy.sensors.AiAiCHREGestureSensor$ambientContextCallback$1
            public final void onEvents(List list) {
                IStats iStats;
                Object obj;
                Log.i("Columbus/GestureSensor", "Received events from AmbientContextManager: " + list);
                Iterator it = list.iterator();
                while (true) {
                    iStats = null;
                    if (!it.hasNext()) {
                        obj = null;
                        break;
                    } else {
                        obj = it.next();
                        if (((AmbientContextEvent) obj).getEventType() == 3) {
                            break;
                        }
                    }
                }
                AmbientContextEvent ambientContextEvent = (AmbientContextEvent) obj;
                if (ambientContextEvent == null) {
                    Log.e("Columbus/GestureSensor", "Receiving events but not EVENT_BACK_DOUBLE_TAP");
                    return;
                }
                ((SystemClockImpl) SystemClock.this).getClass();
                long currentTimeMillis = System.currentTimeMillis() - ambientContextEvent.getStartTime().toEpochMilli();
                String str = ColumbusMetrics.ISTATS_INSTANCE_NAME;
                VendorAtom vendorAtom = new VendorAtom();
                vendorAtom.reverseDomainName = "";
                vendorAtom.atomId = 100139;
                vendorAtom.values = new VendorAtomValue[]{new VendorAtomValue(1, Long.valueOf(currentTimeMillis))};
                try {
                    String str2 = ColumbusMetrics.ISTATS_INSTANCE_NAME;
                    if (ServiceManager.isDeclared(str2)) {
                        iStats = IStats.Stub.asInterface(ServiceManager.waitForDeclaredService(str2));
                    } else {
                        Log.e("Columbus/Metrics", "IStats is not registered");
                    }
                    if (iStats != null) {
                        ((IStats.Stub.Proxy) iStats).reportVendorAtom(vendorAtom);
                        if (ColumbusMetrics.DEBUG) {
                            Log.d("Columbus/Metrics", "Report vendor atom OK, " + vendorAtom);
                        }
                    }
                } catch (Exception e) {
                    Log.e("Columbus/Metrics", "Failed to log atom to IStats service, " + e);
                }
                AiAiCHREGestureSensor aiAiCHREGestureSensor = this;
                int eventType = ambientContextEvent.getEventType();
                aiAiCHREGestureSensor.getClass();
                int i = eventType != 3 ? 0 : 1;
                GestureSensor.DetectionProperties detectionProperties = new GestureSensor.DetectionProperties(false);
                GestureController$gestureSensorListener$1 gestureController$gestureSensorListener$1 = aiAiCHREGestureSensor.listener;
                if (gestureController$gestureSensorListener$1 != null) {
                    gestureController$gestureSensorListener$1.onGestureDetected(i, detectionProperties);
                }
            }

            public final void onRegistrationComplete(int i) {
                Log.i("Columbus/GestureSensor", "registerObserver completes with status: " + i);
            }
        };
    }

    @Override // com.google.android.systemui.columbus.legacy.sensors.GestureSensor
    public final boolean isListening() {
        return this.isListening;
    }

    @Override // com.google.android.systemui.columbus.legacy.sensors.GestureSensor
    public final void startListening() {
        this.isListening = true;
        Log.i("Columbus/GestureSensor", "startListening with AmbientContextManager.registerObserver");
        final AmbientContextEventRequest build = new AmbientContextEventRequest.Builder().addEventType(3).build();
        if (this.ambientContextManager == null) {
            Log.e("Columbus/GestureSensor", "AmbientContextManager not found.");
        } else {
            this.bgHandler.post(new Runnable() { // from class: com.google.android.systemui.columbus.legacy.sensors.AiAiCHREGestureSensor$startListening$1
                @Override // java.lang.Runnable
                public final void run() {
                    AiAiCHREGestureSensor aiAiCHREGestureSensor = AiAiCHREGestureSensor.this;
                    AmbientContextManager ambientContextManager = aiAiCHREGestureSensor.ambientContextManager;
                    if (ambientContextManager != null) {
                        ambientContextManager.registerObserver(build, aiAiCHREGestureSensor.mainExecutor, aiAiCHREGestureSensor.ambientContextCallback);
                    }
                    AiAiCHREGestureSensor.this.uiEventLogger.log(ColumbusEvent.COLUMBUS_MODE_LOW_POWER_ACTIVE);
                }
            });
        }
    }

    @Override // com.google.android.systemui.columbus.legacy.sensors.GestureSensor
    public final void stopListening() {
        Log.i("Columbus/GestureSensor", "stopListening with AmbientContextManager.unregisterObserver");
        if (this.ambientContextManager == null) {
            Log.e("Columbus/GestureSensor", "AmbientContextManager not found.");
        } else {
            this.bgHandler.post(new Runnable() { // from class: com.google.android.systemui.columbus.legacy.sensors.AiAiCHREGestureSensor$stopListening$1
                @Override // java.lang.Runnable
                public final void run() {
                    AmbientContextManager ambientContextManager = AiAiCHREGestureSensor.this.ambientContextManager;
                    if (ambientContextManager != null) {
                        ambientContextManager.unregisterObserver();
                    }
                    AiAiCHREGestureSensor.this.uiEventLogger.log(ColumbusEvent.COLUMBUS_MODE_INACTIVE);
                }
            });
            this.isListening = false;
        }
    }
}

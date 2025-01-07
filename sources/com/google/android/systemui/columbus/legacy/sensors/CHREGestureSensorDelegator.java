package com.google.android.systemui.columbus.legacy.sensors;

import android.app.ambientcontext.AmbientContextManager;
import android.os.Handler;
import android.util.Log;
import com.android.systemui.Dumpable;
import com.google.android.systemui.columbus.legacy.sensors.CHREGestureSensorDelegator$startListening$1;
import dagger.Lazy;
import java.io.Closeable;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CHREGestureSensorDelegator extends GestureSensor implements Dumpable {
    public final Lazy aiAiCHREGestureSensor;
    public final AmbientContextManager ambientContextManager;
    public final Executor bgExecutor;
    public final Handler bgHandler;
    public final CHREGestureSensor chreGestureSensor;
    public GestureSensor gestureSensor;

    public CHREGestureSensorDelegator(AmbientContextManager ambientContextManager, CHREGestureSensor cHREGestureSensor, Lazy lazy, Executor executor, Handler handler) {
        this.ambientContextManager = ambientContextManager;
        this.chreGestureSensor = cHREGestureSensor;
        this.aiAiCHREGestureSensor = lazy;
        this.bgExecutor = executor;
        this.bgHandler = handler;
        this.gestureSensor = cHREGestureSensor;
    }

    public static final void access$switchSensor(CHREGestureSensorDelegator cHREGestureSensorDelegator, boolean z) {
        GestureSensor gestureSensor;
        cHREGestureSensorDelegator.getClass();
        Log.i("Columbus/GestureSensor", "CHREGestureSensorDelegator switchSensor, AiAi = " + z);
        boolean isListening = cHREGestureSensorDelegator.gestureSensor.isListening();
        if (isListening) {
            cHREGestureSensorDelegator.gestureSensor.stopListening();
        }
        cHREGestureSensorDelegator.gestureSensor.setGestureListener(null);
        cHREGestureSensorDelegator.gestureSensor.close();
        if (z) {
            Object obj = cHREGestureSensorDelegator.aiAiCHREGestureSensor.get();
            Intrinsics.checkNotNull(obj);
            gestureSensor = (GestureSensor) obj;
        } else {
            gestureSensor = cHREGestureSensorDelegator.chreGestureSensor;
        }
        cHREGestureSensorDelegator.gestureSensor = gestureSensor;
        gestureSensor.setGestureListener(cHREGestureSensorDelegator.listener);
        if (isListening) {
            cHREGestureSensorDelegator.gestureSensor.startListening();
        }
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        Closeable closeable = this.gestureSensor;
        Dumpable dumpable = closeable instanceof Dumpable ? (Dumpable) closeable : null;
        if (dumpable != null) {
            dumpable.dump(printWriter, strArr);
        }
    }

    @Override // com.google.android.systemui.columbus.legacy.sensors.GestureSensor
    public final boolean isListening() {
        return this.gestureSensor.isListening();
    }

    @Override // com.google.android.systemui.columbus.legacy.sensors.GestureSensor
    public final void setGestureListener(GestureController$gestureSensorListener$1 gestureController$gestureSensorListener$1) {
        Log.i("Columbus/GestureSensor", "CHREGestureSensorDelegator setGestureListener to " + this.gestureSensor);
        this.listener = gestureController$gestureSensorListener$1;
        this.bgHandler.post(new CHREGestureSensorDelegator$startListening$1.AnonymousClass1(this, gestureController$gestureSensorListener$1));
    }

    @Override // com.google.android.systemui.columbus.legacy.sensors.GestureSensor
    public final void startListening() {
        AmbientContextManager ambientContextManager = this.ambientContextManager;
        if (ambientContextManager != null) {
            ambientContextManager.queryAmbientContextServiceStatus(Collections.singleton(3), this.bgExecutor, new Consumer() { // from class: com.google.android.systemui.columbus.legacy.sensors.CHREGestureSensorDelegator$startListening$1

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.google.android.systemui.columbus.legacy.sensors.CHREGestureSensorDelegator$startListening$1$1, reason: invalid class name */
                public final class AnonymousClass1 implements Runnable {
                    public final /* synthetic */ int $r8$classId = 1;
                    public final /* synthetic */ Object $statusCode;
                    public final /* synthetic */ CHREGestureSensorDelegator this$0;

                    public AnonymousClass1(CHREGestureSensorDelegator cHREGestureSensorDelegator, GestureController$gestureSensorListener$1 gestureController$gestureSensorListener$1) {
                        this.this$0 = cHREGestureSensorDelegator;
                        this.$statusCode = gestureController$gestureSensorListener$1;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        switch (this.$r8$classId) {
                            case 0:
                                Integer num = (Integer) this.$statusCode;
                                if (num == null || num.intValue() != 1) {
                                    CHREGestureSensorDelegator cHREGestureSensorDelegator = this.this$0;
                                    if (cHREGestureSensorDelegator.gestureSensor instanceof AiAiCHREGestureSensor) {
                                        CHREGestureSensorDelegator.access$switchSensor(cHREGestureSensorDelegator, false);
                                        break;
                                    }
                                }
                                Integer num2 = (Integer) this.$statusCode;
                                if (num2 != null && num2.intValue() == 1) {
                                    CHREGestureSensorDelegator cHREGestureSensorDelegator2 = this.this$0;
                                    if (cHREGestureSensorDelegator2.gestureSensor instanceof CHREGestureSensor) {
                                        CHREGestureSensorDelegator.access$switchSensor(cHREGestureSensorDelegator2, true);
                                        break;
                                    }
                                }
                                break;
                            default:
                                this.this$0.gestureSensor.setGestureListener((GestureController$gestureSensorListener$1) this.$statusCode);
                                break;
                        }
                    }

                    public AnonymousClass1(Integer num, CHREGestureSensorDelegator cHREGestureSensorDelegator) {
                        this.$statusCode = num;
                        this.this$0 = cHREGestureSensorDelegator;
                    }
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    Integer num = (Integer) obj;
                    Log.i("Columbus/GestureSensor", "CHREGestureSensorDelegator received statusCode = " + num);
                    CHREGestureSensorDelegator cHREGestureSensorDelegator = CHREGestureSensorDelegator.this;
                    cHREGestureSensorDelegator.bgHandler.post(new AnonymousClass1(num, cHREGestureSensorDelegator));
                }
            });
        }
        Log.i("Columbus/GestureSensor", "CHREGestureSensorDelegator startListening, gestureSensor = " + this.gestureSensor);
        this.bgHandler.post(new CHREGestureSensorDelegator$stopListening$1(this, 1));
    }

    @Override // com.google.android.systemui.columbus.legacy.sensors.GestureSensor
    public final void stopListening() {
        Log.i("Columbus/GestureSensor", "CHREGestureSensorDelegator stopListening, gestureSensor = " + this.gestureSensor);
        this.bgHandler.post(new CHREGestureSensorDelegator$stopListening$1(this, 0));
    }
}

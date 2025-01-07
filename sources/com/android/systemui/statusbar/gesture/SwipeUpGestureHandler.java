package com.android.systemui.statusbar.gesture;

import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.view.InputEvent;
import android.view.MotionEvent;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class SwipeUpGestureHandler extends GenericGestureDetector {
    public final SwipeUpGestureLogger logger;
    public final String loggerTag;
    public boolean monitoringCurrentTouch;
    public long startTime;
    public float startY;
    public final int swipeDistanceThreshold;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public SwipeUpGestureHandler(android.content.Context r2, com.android.systemui.statusbar.gesture.SwipeUpGestureLogger r3, java.lang.String r4) {
        /*
            r1 = this;
            java.lang.Class<com.android.systemui.statusbar.gesture.SwipeUpGestureHandler> r0 = com.android.systemui.statusbar.gesture.SwipeUpGestureHandler.class
            kotlin.jvm.internal.ClassReference r0 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r0)
            java.lang.String r0 = r0.getSimpleName()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            r1.<init>(r0)
            r1.logger = r3
            r1.loggerTag = r4
            android.content.res.Resources r2 = r2.getResources()
            r3 = 17105681(0x1050311, float:2.4430442E-38)
            int r2 = r2.getDimensionPixelSize(r3)
            r1.swipeDistanceThreshold = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.gesture.SwipeUpGestureHandler.<init>(android.content.Context, com.android.systemui.statusbar.gesture.SwipeUpGestureLogger, java.lang.String):void");
    }

    @Override // com.android.systemui.statusbar.gesture.GenericGestureDetector
    public final void onInputEvent(InputEvent inputEvent) {
        if (inputEvent instanceof MotionEvent) {
            MotionEvent motionEvent = (MotionEvent) inputEvent;
            int actionMasked = motionEvent.getActionMasked();
            String str = this.loggerTag;
            SwipeUpGestureLogger swipeUpGestureLogger = this.logger;
            if (actionMasked == 0) {
                if (!startOfGestureIsWithinBounds(motionEvent)) {
                    this.monitoringCurrentTouch = false;
                    return;
                }
                int y = (int) motionEvent.getY();
                swipeUpGestureLogger.getClass();
                LogLevel logLevel = LogLevel.DEBUG;
                SwipeUpGestureLogger$logGestureDetectionStarted$2 swipeUpGestureLogger$logGestureDetectionStarted$2 = new Function1() { // from class: com.android.systemui.statusbar.gesture.SwipeUpGestureLogger$logGestureDetectionStarted$2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return AnnotationValue$1$$ExternalSyntheticOutline0.m(((LogMessage) obj).getInt1(), "Beginning gesture detection. y=");
                    }
                };
                LogBuffer logBuffer = swipeUpGestureLogger.buffer;
                LogMessage obtain = logBuffer.obtain(str, logLevel, swipeUpGestureLogger$logGestureDetectionStarted$2, null);
                ((LogMessageImpl) obtain).int1 = y;
                logBuffer.commit(obtain);
                this.startY = motionEvent.getY();
                this.startTime = motionEvent.getEventTime();
                this.monitoringCurrentTouch = true;
                return;
            }
            if (actionMasked != 1) {
                if (actionMasked == 2) {
                    if (this.monitoringCurrentTouch) {
                        float y2 = motionEvent.getY();
                        float f = this.startY;
                        if (y2 >= f || f - motionEvent.getY() < this.swipeDistanceThreshold || motionEvent.getEventTime() - this.startTime >= 500) {
                            return;
                        }
                        this.monitoringCurrentTouch = false;
                        int y3 = (int) motionEvent.getY();
                        swipeUpGestureLogger.getClass();
                        LogLevel logLevel2 = LogLevel.INFO;
                        SwipeUpGestureLogger$logGestureDetected$2 swipeUpGestureLogger$logGestureDetected$2 = new Function1() { // from class: com.android.systemui.statusbar.gesture.SwipeUpGestureLogger$logGestureDetected$2
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return AnnotationValue$1$$ExternalSyntheticOutline0.m(((LogMessage) obj).getInt1(), "Gesture detected; notifying callbacks. y=");
                            }
                        };
                        LogBuffer logBuffer2 = swipeUpGestureLogger.buffer;
                        LogMessage obtain2 = logBuffer2.obtain(str, logLevel2, swipeUpGestureLogger$logGestureDetected$2, null);
                        ((LogMessageImpl) obtain2).int1 = y3;
                        logBuffer2.commit(obtain2);
                        onGestureDetected$frameworks__base__packages__SystemUI__android_common__SystemUI_core(motionEvent);
                        return;
                    }
                    return;
                }
                if (actionMasked != 3) {
                    return;
                }
            }
            if (this.monitoringCurrentTouch) {
                int y4 = (int) motionEvent.getY();
                swipeUpGestureLogger.getClass();
                LogLevel logLevel3 = LogLevel.DEBUG;
                SwipeUpGestureLogger$logGestureDetectionEndedWithoutTriggering$2 swipeUpGestureLogger$logGestureDetectionEndedWithoutTriggering$2 = new Function1() { // from class: com.android.systemui.statusbar.gesture.SwipeUpGestureLogger$logGestureDetectionEndedWithoutTriggering$2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return AnnotationValue$1$$ExternalSyntheticOutline0.m(((LogMessage) obj).getInt1(), "Gesture finished; no swipe up gesture detected. Final y=");
                    }
                };
                LogBuffer logBuffer3 = swipeUpGestureLogger.buffer;
                LogMessage obtain3 = logBuffer3.obtain(str, logLevel3, swipeUpGestureLogger$logGestureDetectionEndedWithoutTriggering$2, null);
                ((LogMessageImpl) obtain3).int1 = y4;
                logBuffer3.commit(obtain3);
            }
            this.monitoringCurrentTouch = false;
        }
    }

    @Override // com.android.systemui.statusbar.gesture.GenericGestureDetector
    public final void startGestureListening$frameworks__base__packages__SystemUI__android_common__SystemUI_core() {
        super.startGestureListening$frameworks__base__packages__SystemUI__android_common__SystemUI_core();
        SwipeUpGestureLogger swipeUpGestureLogger = this.logger;
        swipeUpGestureLogger.getClass();
        LogLevel logLevel = LogLevel.VERBOSE;
        SwipeUpGestureLogger$logInputListeningStarted$2 swipeUpGestureLogger$logInputListeningStarted$2 = new Function1() { // from class: com.android.systemui.statusbar.gesture.SwipeUpGestureLogger$logInputListeningStarted$2
            @Override // kotlin.jvm.functions.Function1
            public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return "Input listening started ";
            }
        };
        LogBuffer logBuffer = swipeUpGestureLogger.buffer;
        logBuffer.commit(logBuffer.obtain(this.loggerTag, logLevel, swipeUpGestureLogger$logInputListeningStarted$2, null));
    }

    public abstract boolean startOfGestureIsWithinBounds(MotionEvent motionEvent);

    @Override // com.android.systemui.statusbar.gesture.GenericGestureDetector
    public final void stopGestureListening$frameworks__base__packages__SystemUI__android_common__SystemUI_core() {
        super.stopGestureListening$frameworks__base__packages__SystemUI__android_common__SystemUI_core();
        SwipeUpGestureLogger swipeUpGestureLogger = this.logger;
        swipeUpGestureLogger.getClass();
        LogLevel logLevel = LogLevel.VERBOSE;
        SwipeUpGestureLogger$logInputListeningStopped$2 swipeUpGestureLogger$logInputListeningStopped$2 = new Function1() { // from class: com.android.systemui.statusbar.gesture.SwipeUpGestureLogger$logInputListeningStopped$2
            @Override // kotlin.jvm.functions.Function1
            public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return "Input listening stopped ";
            }
        };
        LogBuffer logBuffer = swipeUpGestureLogger.buffer;
        logBuffer.commit(logBuffer.obtain(this.loggerTag, logLevel, swipeUpGestureLogger$logInputListeningStopped$2, null));
    }
}

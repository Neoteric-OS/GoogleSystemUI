package com.android.systemui.util.wakelock;

import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.os.PowerManager;
import android.util.Log;
import androidx.room.TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ClientTrackingWakeLock implements WakeLock {
    public final ConcurrentHashMap activeClients = new ConcurrentHashMap();
    public final WakeLockLogger logger;
    public final long maxTimeout;
    public final PowerManager.WakeLock pmWakeLock;

    public ClientTrackingWakeLock(PowerManager.WakeLock wakeLock, WakeLockLogger wakeLockLogger, long j) {
        this.pmWakeLock = wakeLock;
        this.logger = wakeLockLogger;
        this.maxTimeout = j;
    }

    @Override // com.android.systemui.util.wakelock.WakeLock
    public final void acquire(String str) {
        int incrementAndGet = ((AtomicInteger) this.activeClients.computeIfAbsent(str, ClientTrackingWakeLock$acquire$count$1.INSTANCE)).incrementAndGet();
        WakeLockLogger wakeLockLogger = this.logger;
        if (wakeLockLogger != null) {
            PowerManager.WakeLock wakeLock = this.pmWakeLock;
            LogLevel logLevel = LogLevel.DEBUG;
            WakeLockLogger$logAcquire$2 wakeLockLogger$logAcquire$2 = new Function1() { // from class: com.android.systemui.util.wakelock.WakeLockLogger$logAcquire$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    LogMessage logMessage = (LogMessage) obj;
                    String str1 = logMessage.getStr1();
                    String str2 = logMessage.getStr2();
                    int int1 = logMessage.getInt1();
                    StringBuilder m = TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0.m("Acquire tag=", str1, " reason=", str2, " count=");
                    m.append(int1);
                    return m.toString();
                }
            };
            LogBuffer logBuffer = wakeLockLogger.buffer;
            LogMessage obtain = logBuffer.obtain("WakeLock", logLevel, wakeLockLogger$logAcquire$2, null);
            LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
            logMessageImpl.str1 = wakeLock.getTag();
            logMessageImpl.str2 = str;
            logMessageImpl.int1 = incrementAndGet;
            logBuffer.commit(obtain);
        }
        long j = this.maxTimeout;
        if (j == -1) {
            this.pmWakeLock.acquire();
        } else {
            this.pmWakeLock.acquire(j);
        }
    }

    @Override // com.android.systemui.util.wakelock.WakeLock
    public final void release(String str) {
        AtomicInteger atomicInteger = (AtomicInteger) this.activeClients.get(str);
        int decrementAndGet = atomicInteger != null ? atomicInteger.decrementAndGet() : -1;
        if (decrementAndGet < 0) {
            Log.wtf("WakeLock", "Releasing WakeLock with invalid reason: ".concat(str));
            AtomicInteger atomicInteger2 = (AtomicInteger) this.activeClients.get(str);
            if (atomicInteger2 != null) {
                atomicInteger2.incrementAndGet();
                return;
            }
            return;
        }
        WakeLockLogger wakeLockLogger = this.logger;
        if (wakeLockLogger != null) {
            PowerManager.WakeLock wakeLock = this.pmWakeLock;
            LogLevel logLevel = LogLevel.DEBUG;
            WakeLockLogger$logRelease$2 wakeLockLogger$logRelease$2 = new Function1() { // from class: com.android.systemui.util.wakelock.WakeLockLogger$logRelease$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    LogMessage logMessage = (LogMessage) obj;
                    String str1 = logMessage.getStr1();
                    String str2 = logMessage.getStr2();
                    int int1 = logMessage.getInt1();
                    StringBuilder m = TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0.m("Release tag=", str1, " reason=", str2, " count=");
                    m.append(int1);
                    return m.toString();
                }
            };
            LogBuffer logBuffer = wakeLockLogger.buffer;
            LogMessage obtain = logBuffer.obtain("WakeLock", logLevel, wakeLockLogger$logRelease$2, null);
            LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
            logMessageImpl.str1 = wakeLock.getTag();
            logMessageImpl.str2 = str;
            logMessageImpl.int1 = decrementAndGet;
            logBuffer.commit(obtain);
        }
        this.pmWakeLock.release();
    }

    public final String toString() {
        return AnnotationValue$1$$ExternalSyntheticOutline0.m(this.activeClients.reduceValuesToInt(Long.MAX_VALUE, ClientTrackingWakeLock$activeClients$1.INSTANCE, 0, ClientTrackingWakeLock$activeClients$2.INSTANCE), "active clients=");
    }

    @Override // com.android.systemui.util.wakelock.WakeLock
    public final Runnable wrap(Runnable runnable) {
        acquire("wrap");
        return new WakeLock$$ExternalSyntheticLambda0(runnable, this);
    }
}

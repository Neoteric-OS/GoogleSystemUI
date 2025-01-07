package com.android.systemui.util;

import android.os.Looper;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class Assert {
    public static final Looper sMainLooper = Looper.getMainLooper();
    public static Thread sTestThread = null;

    public static void isMainThread() {
        Looper looper = sMainLooper;
        if (looper.isCurrentThread()) {
            return;
        }
        Thread thread = sTestThread;
        if (thread == null || thread != Thread.currentThread()) {
            throw new IllegalStateException("should be called from the main thread. sMainLooper.threadName=" + looper.getThread().getName() + " Thread.currentThread()=" + Thread.currentThread().getName());
        }
    }

    public static void isNotMainThread() {
        if (sMainLooper.isCurrentThread()) {
            Thread thread = sTestThread;
            if (thread == null || thread == Thread.currentThread()) {
                throw new IllegalStateException("should not be called from the main thread.");
            }
        }
    }

    public static void runWithCurrentThreadAsMainThread(Runnable runnable) {
        if (sMainLooper.isCurrentThread()) {
            runnable.run();
            return;
        }
        Thread currentThread = Thread.currentThread();
        Thread thread = sTestThread;
        if (thread == currentThread) {
            runnable.run();
            return;
        }
        if (thread == null) {
            sTestThread = currentThread;
            runnable.run();
            sTestThread = null;
        } else {
            throw new AssertionError("Can't run with current thread (" + currentThread + ") as main thread; test thread is already set to " + thread);
        }
    }

    public static void setTestThread(Thread thread) {
        sTestThread = thread;
    }

    public static void setTestableLooper(Looper looper) {
        setTestThread(looper == null ? null : looper.getThread());
    }
}

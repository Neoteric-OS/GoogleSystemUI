package com.airbnb.lottie;

import android.os.Handler;
import android.os.Looper;
import com.airbnb.lottie.utils.Logger;
import com.airbnb.lottie.utils.LottieThreadFactory;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LottieTask {
    public static final Executor EXECUTOR = Executors.newCachedThreadPool(new LottieThreadFactory());
    public final Set successListeners = new LinkedHashSet(1);
    public final Set failureListeners = new LinkedHashSet(1);
    public final Handler handler = new Handler(Looper.getMainLooper());
    public volatile LottieResult result = null;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class LottieFutureTask extends FutureTask {
        public LottieTask lottieTask;

        @Override // java.util.concurrent.FutureTask
        public final void done() {
            try {
                if (isCancelled()) {
                    return;
                }
                try {
                    this.lottieTask.setResult((LottieResult) get());
                } catch (InterruptedException | ExecutionException e) {
                    this.lottieTask.setResult(new LottieResult(e));
                }
            } finally {
                this.lottieTask = null;
            }
        }
    }

    public LottieTask(LottieComposition lottieComposition) {
        setResult(new LottieResult(lottieComposition));
    }

    public final synchronized void addFailureListener(LottieListener lottieListener) {
        Throwable th;
        try {
            LottieResult lottieResult = this.result;
            if (lottieResult != null && (th = lottieResult.exception) != null) {
                lottieListener.onResult(th);
            }
            this.failureListeners.add(lottieListener);
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public final synchronized void addListener(LottieListener lottieListener) {
        LottieComposition lottieComposition;
        try {
            LottieResult lottieResult = this.result;
            if (lottieResult != null && (lottieComposition = lottieResult.value) != null) {
                lottieListener.onResult(lottieComposition);
            }
            this.successListeners.add(lottieListener);
        } catch (Throwable th) {
            throw th;
        }
    }

    public final void notifyListenersInternal() {
        LottieResult lottieResult = this.result;
        if (lottieResult == null) {
            return;
        }
        LottieComposition lottieComposition = lottieResult.value;
        if (lottieComposition != null) {
            synchronized (this) {
                Iterator it = new ArrayList(this.successListeners).iterator();
                while (it.hasNext()) {
                    ((LottieListener) it.next()).onResult(lottieComposition);
                }
            }
            return;
        }
        Throwable th = lottieResult.exception;
        synchronized (this) {
            ArrayList arrayList = new ArrayList(this.failureListeners);
            if (arrayList.isEmpty()) {
                Logger.warning("Lottie encountered an error but no failure listener was added:", th);
                return;
            }
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                ((LottieListener) it2.next()).onResult(th);
            }
        }
    }

    public final void setResult(LottieResult lottieResult) {
        if (this.result != null) {
            throw new IllegalStateException("A task may only be set once.");
        }
        this.result = lottieResult;
        if (Looper.myLooper() == Looper.getMainLooper()) {
            notifyListenersInternal();
        } else {
            this.handler.post(new Runnable() { // from class: com.airbnb.lottie.LottieTask$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    LottieTask.this.notifyListenersInternal();
                }
            });
        }
    }

    public LottieTask(Callable callable, boolean z) {
        if (z) {
            try {
                setResult((LottieResult) callable.call());
                return;
            } catch (Throwable th) {
                setResult(new LottieResult(th));
                return;
            }
        }
        Executor executor = EXECUTOR;
        LottieFutureTask lottieFutureTask = new LottieFutureTask(callable);
        lottieFutureTask.lottieTask = this;
        executor.execute(lottieFutureTask);
    }
}

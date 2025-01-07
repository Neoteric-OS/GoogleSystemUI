package androidx.asynclayoutinflater.view;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.util.Pools$SynchronizedPool;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AsyncLayoutInflater {
    public Handler mHandler;
    public AnonymousClass1 mHandlerCallback;
    public InflateThread mInflateThread;
    public BasicInflater mInflater;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: androidx.asynclayoutinflater.view.AsyncLayoutInflater$1, reason: invalid class name */
    public final class AnonymousClass1 implements Handler.Callback {
        public AnonymousClass1() {
        }

        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            InflateRequest inflateRequest = (InflateRequest) message.obj;
            if (inflateRequest.view == null) {
                inflateRequest.view = inflateRequest.mInflater.inflate(inflateRequest.resid, inflateRequest.parent, false);
            }
            Executor executor = inflateRequest.mExecutor;
            if (executor != null) {
                executor.execute(new AsyncLayoutInflater$1$$ExternalSyntheticLambda0(this, inflateRequest, 0));
                return true;
            }
            AsyncLayoutInflater.triggerCallbacks(inflateRequest, AsyncLayoutInflater.this.mInflateThread);
            return true;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class BasicInflater extends LayoutInflater {
        public static final String[] sClassPrefixList = {"android.widget.", "android.webkit.", "android.app."};

        public BasicInflater(Context context) {
            super(context);
        }

        @Override // android.view.LayoutInflater
        public final LayoutInflater cloneInContext(Context context) {
            return new BasicInflater(context);
        }

        @Override // android.view.LayoutInflater
        public final View onCreateView(String str, AttributeSet attributeSet) {
            View createView;
            String[] strArr = sClassPrefixList;
            for (int i = 0; i < 3; i++) {
                try {
                    createView = createView(str, strArr[i], attributeSet);
                } catch (ClassNotFoundException unused) {
                }
                if (createView != null) {
                    return createView;
                }
            }
            return super.onCreateView(str, attributeSet);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class InflateRequest {
        public OnInflateFinishedListener callback;
        public Executor mExecutor;
        public Handler mHandler;
        public BasicInflater mInflater;
        public ViewGroup parent;
        public int resid;
        public View view;
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class InflateThread extends Thread {
        public static final InflateThread sInstance;
        public ArrayBlockingQueue mQueue;
        public Pools$SynchronizedPool mRequestPool;

        static {
            InflateThread inflateThread = new InflateThread();
            inflateThread.mQueue = new ArrayBlockingQueue(10);
            inflateThread.mRequestPool = new Pools$SynchronizedPool(10);
            sInstance = inflateThread;
            inflateThread.setName("AsyncLayoutInflator");
            inflateThread.start();
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            Executor executor;
            while (true) {
                try {
                    InflateRequest inflateRequest = (InflateRequest) this.mQueue.take();
                    try {
                        inflateRequest.view = inflateRequest.mInflater.inflate(inflateRequest.resid, inflateRequest.parent, false);
                    } catch (RuntimeException e) {
                        Log.w("AsyncLayoutInflater", "Failed to inflate resource in the background! Retrying on the UI thread", e);
                    }
                    if (inflateRequest.view == null || (executor = inflateRequest.mExecutor) == null) {
                        Message.obtain(inflateRequest.mHandler, 0, inflateRequest).sendToTarget();
                    } else {
                        executor.execute(new AsyncLayoutInflater$1$$ExternalSyntheticLambda0(this, inflateRequest, 1));
                    }
                } catch (InterruptedException e2) {
                    Log.w("AsyncLayoutInflater", e2);
                }
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface OnInflateFinishedListener {
        void onInflateFinished(View view, ViewGroup viewGroup);
    }

    public AsyncLayoutInflater(Context context) {
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        this.mInflater = new BasicInflater(context);
        this.mHandler = new Handler(Looper.myLooper(), anonymousClass1);
        this.mInflateThread = InflateThread.sInstance;
    }

    public static void triggerCallbacks(InflateRequest inflateRequest, InflateThread inflateThread) {
        inflateRequest.callback.onInflateFinished(inflateRequest.view, inflateRequest.parent);
        inflateThread.getClass();
        inflateRequest.callback = null;
        inflateRequest.mInflater = null;
        inflateRequest.mHandler = null;
        inflateRequest.parent = null;
        inflateRequest.resid = 0;
        inflateRequest.view = null;
        inflateRequest.mExecutor = null;
        inflateThread.mRequestPool.release(inflateRequest);
    }

    public final void inflateInternal(int i, ViewGroup viewGroup, OnInflateFinishedListener onInflateFinishedListener, BasicInflater basicInflater, Executor executor) {
        InflateThread inflateThread = this.mInflateThread;
        InflateRequest inflateRequest = (InflateRequest) inflateThread.mRequestPool.acquire();
        if (inflateRequest == null) {
            inflateRequest = new InflateRequest();
        }
        inflateRequest.mInflater = basicInflater;
        inflateRequest.mHandler = this.mHandler;
        inflateRequest.resid = i;
        inflateRequest.parent = viewGroup;
        inflateRequest.callback = onInflateFinishedListener;
        inflateRequest.mExecutor = executor;
        try {
            inflateThread.mQueue.put(inflateRequest);
        } catch (InterruptedException e) {
            throw new RuntimeException("Failed to enqueue async inflate request", e);
        }
    }
}

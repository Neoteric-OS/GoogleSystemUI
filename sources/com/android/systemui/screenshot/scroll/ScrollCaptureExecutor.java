package com.android.systemui.screenshot.scroll;

import android.app.ActivityManager;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.IScrollCaptureResponseListener;
import android.view.ScrollCaptureResponse;
import androidx.appsearch.app.GenericDocument$$ExternalSyntheticOutline0;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ScrollCaptureExecutor {
    public final boolean isLowRamDevice;
    public CallbackToFutureAdapter.SafeFuture lastScrollCaptureRequest;
    public ScrollCaptureResponse lastScrollCaptureResponse;
    public CallbackToFutureAdapter.SafeFuture longScreenshotFuture;
    public final LongScreenshotData longScreenshotHolder;
    public final Executor mainExecutor;
    public final ScrollCaptureClient scrollCaptureClient;
    public final ScrollCaptureController scrollCaptureController;

    public ScrollCaptureExecutor(ActivityManager activityManager, ScrollCaptureClient scrollCaptureClient, ScrollCaptureController scrollCaptureController, LongScreenshotData longScreenshotData, Executor executor) {
        this.scrollCaptureClient = scrollCaptureClient;
        this.scrollCaptureController = scrollCaptureController;
        this.longScreenshotHolder = longScreenshotData;
        this.mainExecutor = executor;
        this.isLowRamDevice = activityManager.isLowRamDevice();
    }

    public final void requestScrollCapture(final int i, IBinder iBinder, final Function1 function1) {
        if (this.isLowRamDevice) {
            Log.d("ScrollCaptureExecutor", "Long screenshots not supported on this device");
            return;
        }
        final ScrollCaptureClient scrollCaptureClient = this.scrollCaptureClient;
        scrollCaptureClient.mHostWindowToken = iBinder;
        CallbackToFutureAdapter.SafeFuture safeFuture = this.lastScrollCaptureRequest;
        if (safeFuture != null) {
            safeFuture.cancel(true);
        }
        final CallbackToFutureAdapter.SafeFuture future = CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: com.android.systemui.screenshot.scroll.ScrollCaptureClient$$ExternalSyntheticLambda1
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                int i2 = i;
                ScrollCaptureClient scrollCaptureClient2 = ScrollCaptureClient.this;
                try {
                    scrollCaptureClient2.mWindowManagerService.requestScrollCapture(i2, scrollCaptureClient2.mHostWindowToken, -1, new IScrollCaptureResponseListener.Stub() { // from class: com.android.systemui.screenshot.scroll.ScrollCaptureClient.1
                        public AnonymousClass1() {
                        }

                        public final void onScrollCaptureResponse(ScrollCaptureResponse scrollCaptureResponse) {
                            CallbackToFutureAdapter.Completer.this.set(scrollCaptureResponse);
                        }
                    });
                } catch (RemoteException e) {
                    completer.setException(e);
                }
                return GenericDocument$$ExternalSyntheticOutline0.m("ScrollCaptureClient#request(displayId=", ", taskId=-1)", i2);
            }
        });
        future.delegate.addListener(new Runnable() { // from class: com.android.systemui.screenshot.scroll.ScrollCaptureExecutor$requestScrollCapture$scrollRequest$1$1
            @Override // java.lang.Runnable
            public final void run() {
                ScrollCaptureExecutor scrollCaptureExecutor = ScrollCaptureExecutor.this;
                CallbackToFutureAdapter.SafeFuture safeFuture2 = future;
                scrollCaptureExecutor.getClass();
                ScrollCaptureResponse scrollCaptureResponse = null;
                try {
                    ScrollCaptureResponse scrollCaptureResponse2 = scrollCaptureExecutor.lastScrollCaptureResponse;
                    if (scrollCaptureResponse2 != null) {
                        scrollCaptureResponse2.close();
                    }
                    scrollCaptureExecutor.lastScrollCaptureResponse = null;
                    if (!safeFuture2.isCancelled()) {
                        Object obj = safeFuture2.delegate.get();
                        scrollCaptureExecutor.lastScrollCaptureResponse = (ScrollCaptureResponse) obj;
                        ScrollCaptureResponse scrollCaptureResponse3 = (ScrollCaptureResponse) obj;
                        if (scrollCaptureResponse3.isConnected()) {
                            Log.d("ScrollCaptureExecutor", "ScrollCapture: connected to window [" + scrollCaptureResponse3.getWindowTitle() + "]");
                            scrollCaptureResponse = scrollCaptureResponse3;
                        } else {
                            Log.d("ScrollCaptureExecutor", "ScrollCapture: " + scrollCaptureResponse3.getDescription() + " [" + scrollCaptureResponse3.getWindowTitle() + "]");
                        }
                    }
                } catch (InterruptedException e) {
                    Log.e("ScrollCaptureExecutor", "requestScrollCapture interrupted", e);
                } catch (ExecutionException e2) {
                    Log.e("ScrollCaptureExecutor", "requestScrollCapture failed", e2);
                }
                if (scrollCaptureResponse != null) {
                    function1.invoke(scrollCaptureResponse);
                }
            }
        }, this.mainExecutor);
        this.lastScrollCaptureRequest = future;
    }
}

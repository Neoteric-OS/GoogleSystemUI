package com.android.systemui.screenshot.scroll;

import android.util.Log;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.android.systemui.screenshot.ScreenshotEvent;
import com.android.systemui.screenshot.scroll.ScrollCaptureClient;
import com.android.systemui.screenshot.scroll.ScrollCaptureController;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class ScrollCaptureController$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ ScrollCaptureController f$0;

    public /* synthetic */ ScrollCaptureController$$ExternalSyntheticLambda0(ScrollCaptureController scrollCaptureController, int i) {
        this.$r8$classId = i;
        this.f$0 = scrollCaptureController;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        ScrollCaptureController scrollCaptureController = this.f$0;
        switch (i) {
            case 0:
                scrollCaptureController.mCancelled = true;
                CallbackToFutureAdapter.SafeFuture safeFuture = scrollCaptureController.mSessionFuture;
                if (safeFuture != null) {
                    safeFuture.cancel(true);
                }
                CallbackToFutureAdapter.SafeFuture safeFuture2 = scrollCaptureController.mTileFuture;
                if (safeFuture2 != null) {
                    safeFuture2.cancel(true);
                }
                ScrollCaptureClient.Session session = scrollCaptureController.mSession;
                if (session != null) {
                    Log.d("Screenshot", "end()");
                    CallbackToFutureAdapter.getFuture(new ScrollCaptureClient$SessionWrapper$$ExternalSyntheticLambda1((ScrollCaptureClient.SessionWrapper) session, 1));
                }
                scrollCaptureController.mEventLogger.log(ScreenshotEvent.SCREENSHOT_LONG_SCREENSHOT_FAILURE, 0, scrollCaptureController.mWindowOwner);
                break;
            case 1:
                try {
                    scrollCaptureController.mSession = (ScrollCaptureClient.Session) scrollCaptureController.mSessionFuture.delegate.get();
                    scrollCaptureController.mEventLogger.log(ScreenshotEvent.SCREENSHOT_LONG_SCREENSHOT_STARTED, 0, scrollCaptureController.mWindowOwner);
                    scrollCaptureController.requestNextTile(0);
                    break;
                } catch (InterruptedException | ExecutionException e) {
                    Log.e("Screenshot", "session start failed!");
                    scrollCaptureController.mCaptureCompleter.setException(e);
                    scrollCaptureController.mEventLogger.log(ScreenshotEvent.SCREENSHOT_LONG_SCREENSHOT_FAILURE, 0, scrollCaptureController.mWindowOwner);
                    return;
                }
            case 2:
                scrollCaptureController.getClass();
                try {
                    scrollCaptureController.onCaptureResult((ScrollCaptureClient.CaptureResult) scrollCaptureController.mTileFuture.delegate.get());
                    break;
                } catch (InterruptedException | ExecutionException e2) {
                    Log.e("Screenshot", "requestTile failed!", e2);
                    scrollCaptureController.mCaptureCompleter.setException(e2);
                    return;
                } catch (CancellationException unused) {
                    Log.e("Screenshot", "requestTile cancelled");
                    return;
                }
            default:
                scrollCaptureController.mCaptureCompleter.set(new ScrollCaptureController.LongScreenshot(scrollCaptureController.mSession, scrollCaptureController.mImageTileSet));
                break;
        }
    }
}

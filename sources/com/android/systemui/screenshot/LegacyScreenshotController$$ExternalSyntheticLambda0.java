package com.android.systemui.screenshot;

import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.BlendMode;
import android.graphics.Rect;
import android.media.ImageReader;
import android.os.DeadObjectException;
import android.os.RemoteException;
import android.os.UserHandle;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.IScrollCaptureConnection;
import android.view.ScrollCaptureResponse;
import android.view.View;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.concurrent.futures.ResolvableFuture;
import androidx.profileinstaller.ProfileInstallReceiver$$ExternalSyntheticLambda0;
import com.android.systemui.screenshot.scroll.ScrollCaptureClient;
import com.android.systemui.screenshot.scroll.ScrollCaptureController;
import com.android.systemui.screenshot.scroll.ScrollCaptureExecutor;
import com.android.systemui.screenshot.ui.ScreenshotAnimationController;
import com.android.systemui.screenshot.ui.ScreenshotAnimationController$getActionsAnimator$1;
import com.android.systemui.screenshot.ui.viewmodel.ScreenshotViewModel;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class LegacyScreenshotController$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId = 0;
    public final /* synthetic */ LegacyScreenshotController f$0;
    public final /* synthetic */ UserHandle f$1;
    public final /* synthetic */ ScrollCaptureResponse f$2;

    public /* synthetic */ LegacyScreenshotController$$ExternalSyntheticLambda0(LegacyScreenshotController legacyScreenshotController, UserHandle userHandle, ScrollCaptureResponse scrollCaptureResponse) {
        this.f$0 = legacyScreenshotController;
        this.f$1 = userHandle;
        this.f$2 = scrollCaptureResponse;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                LegacyScreenshotController legacyScreenshotController = this.f$0;
                UserHandle userHandle = this.f$1;
                ScrollCaptureResponse scrollCaptureResponse = this.f$2;
                legacyScreenshotController.mUiEventLogger.log(ScreenshotEvent.SCREENSHOT_LONG_SCREENSHOT_REQUESTED, 0, scrollCaptureResponse.getPackageName());
                int displayId = legacyScreenshotController.mDisplay.getDisplayId();
                DisplayMetrics displayMetrics = new DisplayMetrics();
                legacyScreenshotController.mDisplay.getRealMetrics(displayMetrics);
                Bitmap captureDisplay = legacyScreenshotController.mImageCapture.captureDisplay(displayId, new Rect(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels));
                if (captureDisplay != null) {
                    final LegacyScreenshotController$$ExternalSyntheticLambda0 legacyScreenshotController$$ExternalSyntheticLambda0 = new LegacyScreenshotController$$ExternalSyntheticLambda0(legacyScreenshotController, scrollCaptureResponse, userHandle);
                    ScreenshotShelfViewProxy screenshotShelfViewProxy = legacyScreenshotController.mViewProxy;
                    ScreenshotViewModel screenshotViewModel = screenshotShelfViewProxy.viewModel;
                    screenshotViewModel._scrollingScrim.setValue(captureDisplay);
                    Rect rect = new Rect(scrollCaptureResponse.getBoundsInWindow());
                    Rect windowBounds = scrollCaptureResponse.getWindowBounds();
                    rect.offset(windowBounds != null ? windowBounds.left : 0, windowBounds != null ? windowBounds.top : 0);
                    rect.intersect(new Rect(0, 0, screenshotShelfViewProxy.context.getResources().getDisplayMetrics().widthPixels, screenshotShelfViewProxy.context.getResources().getDisplayMetrics().heightPixels));
                    screenshotViewModel._scrollableRect.setValue(rect);
                    ScreenshotAnimationController screenshotAnimationController = screenshotShelfViewProxy.animationController;
                    screenshotAnimationController.scrollingScrim.setImageTintBlendMode(BlendMode.SRC_ATOP);
                    ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 0.3f);
                    ofFloat.addUpdateListener(new ScreenshotAnimationController$getActionsAnimator$1(screenshotAnimationController, 1));
                    Iterator it = screenshotAnimationController.fadeUI.iterator();
                    while (it.hasNext()) {
                        ((View) it.next()).setAlpha(0.0f);
                    }
                    screenshotAnimationController.screenshotPreview.setAlpha(0.0f);
                    ofFloat.setDuration(200L);
                    ofFloat.start();
                    screenshotShelfViewProxy.view.post(new Runnable() { // from class: com.android.systemui.screenshot.ScreenshotShelfViewProxy$prepareScrollingTransition$1
                        @Override // java.lang.Runnable
                        public final void run() {
                            legacyScreenshotController$$ExternalSyntheticLambda0.run();
                        }
                    });
                    break;
                } else {
                    Log.e("Screenshot", "Failed to capture current screenshot for scroll transition!");
                    break;
                }
            default:
                LegacyScreenshotController legacyScreenshotController2 = this.f$0;
                final ScrollCaptureResponse scrollCaptureResponse2 = this.f$2;
                UserHandle userHandle2 = this.f$1;
                legacyScreenshotController2.getClass();
                final LegacyScreenshotController$$ExternalSyntheticLambda2 legacyScreenshotController$$ExternalSyntheticLambda2 = new LegacyScreenshotController$$ExternalSyntheticLambda2(legacyScreenshotController2, userHandle2, 0);
                ScreenshotShelfViewProxy screenshotShelfViewProxy2 = legacyScreenshotController2.mViewProxy;
                Objects.requireNonNull(screenshotShelfViewProxy2);
                final LegacyScreenshotController$$ExternalSyntheticLambda9 legacyScreenshotController$$ExternalSyntheticLambda9 = new LegacyScreenshotController$$ExternalSyntheticLambda9(3, screenshotShelfViewProxy2);
                final LegacyScreenshotController$$ExternalSyntheticLambda4 legacyScreenshotController$$ExternalSyntheticLambda4 = new LegacyScreenshotController$$ExternalSyntheticLambda4(screenshotShelfViewProxy2);
                final ScrollCaptureExecutor scrollCaptureExecutor = legacyScreenshotController2.mScrollCaptureExecutor;
                scrollCaptureExecutor.lastScrollCaptureResponse = null;
                CallbackToFutureAdapter.SafeFuture safeFuture = scrollCaptureExecutor.longScreenshotFuture;
                if (safeFuture != null) {
                    safeFuture.cancel(true);
                }
                final ScrollCaptureController scrollCaptureController = scrollCaptureExecutor.scrollCaptureController;
                scrollCaptureController.mCancelled = false;
                final CallbackToFutureAdapter.SafeFuture future = CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: com.android.systemui.screenshot.scroll.ScrollCaptureController$$ExternalSyntheticLambda5
                    @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
                    public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                        final ScrollCaptureResponse scrollCaptureResponse3 = scrollCaptureResponse2;
                        final ScrollCaptureController scrollCaptureController2 = ScrollCaptureController.this;
                        scrollCaptureController2.mCaptureCompleter = completer;
                        scrollCaptureController2.mWindowOwner = scrollCaptureResponse3.getPackageName();
                        CallbackToFutureAdapter.Completer completer2 = scrollCaptureController2.mCaptureCompleter;
                        ScrollCaptureController$$ExternalSyntheticLambda0 scrollCaptureController$$ExternalSyntheticLambda0 = new ScrollCaptureController$$ExternalSyntheticLambda0(scrollCaptureController2, 0);
                        Executor executor = scrollCaptureController2.mBgExecutor;
                        ResolvableFuture resolvableFuture = completer2.cancellationFuture;
                        if (resolvableFuture != null) {
                            resolvableFuture.addListener(scrollCaptureController$$ExternalSyntheticLambda0, executor);
                        }
                        scrollCaptureController2.mBgExecutor.execute(new Runnable() { // from class: com.android.systemui.screenshot.scroll.ScrollCaptureController$$ExternalSyntheticLambda1
                            @Override // java.lang.Runnable
                            public final void run() {
                                ScrollCaptureController scrollCaptureController3 = ScrollCaptureController.this;
                                final ScrollCaptureResponse scrollCaptureResponse4 = scrollCaptureResponse3;
                                final float f = Settings.Secure.getFloat(scrollCaptureController3.mContext.getContentResolver(), "screenshot.scroll_max_pages", 3.0f);
                                final ScrollCaptureClient scrollCaptureClient = scrollCaptureController3.mClient;
                                final IScrollCaptureConnection connection = scrollCaptureResponse4.getConnection();
                                CallbackToFutureAdapter.SafeFuture future2 = CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: com.android.systemui.screenshot.scroll.ScrollCaptureClient$$ExternalSyntheticLambda0
                                    @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
                                    public final Object attachCompleter(CallbackToFutureAdapter.Completer completer3) {
                                        IScrollCaptureConnection iScrollCaptureConnection = connection;
                                        ScrollCaptureResponse scrollCaptureResponse5 = scrollCaptureResponse4;
                                        ScrollCaptureClient scrollCaptureClient2 = ScrollCaptureClient.this;
                                        if (iScrollCaptureConnection == null || !iScrollCaptureConnection.asBinder().isBinderAlive()) {
                                            completer3.setException(new DeadObjectException("No active connection!"));
                                            return "";
                                        }
                                        ScrollCaptureClient.SessionWrapper sessionWrapper = new ScrollCaptureClient.SessionWrapper(iScrollCaptureConnection, scrollCaptureResponse5.getWindowBounds(), scrollCaptureResponse5.getBoundsInWindow(), f, scrollCaptureClient2.mBgExecutor);
                                        ImageReader newInstance = ImageReader.newInstance(sessionWrapper.mTileWidth, sessionWrapper.mTileHeight, 1, 30, 256L);
                                        sessionWrapper.mReader = newInstance;
                                        sessionWrapper.mStartCompleter = completer3;
                                        newInstance.setOnImageAvailableListenerWithExecutor(sessionWrapper, sessionWrapper.mBgExecutor);
                                        try {
                                            sessionWrapper.mCancellationSignal = sessionWrapper.mConnection.startCapture(sessionWrapper.mReader.getSurface(), sessionWrapper);
                                            ScrollCaptureClient$SessionWrapper$$ExternalSyntheticLambda0 scrollCaptureClient$SessionWrapper$$ExternalSyntheticLambda0 = new ScrollCaptureClient$SessionWrapper$$ExternalSyntheticLambda0(sessionWrapper, 0);
                                            ProfileInstallReceiver$$ExternalSyntheticLambda0 profileInstallReceiver$$ExternalSyntheticLambda0 = new ProfileInstallReceiver$$ExternalSyntheticLambda0();
                                            ResolvableFuture resolvableFuture2 = completer3.cancellationFuture;
                                            if (resolvableFuture2 != null) {
                                                resolvableFuture2.addListener(scrollCaptureClient$SessionWrapper$$ExternalSyntheticLambda0, profileInstallReceiver$$ExternalSyntheticLambda0);
                                            }
                                            sessionWrapper.mStarted = true;
                                        } catch (RemoteException e) {
                                            sessionWrapper.mReader.close();
                                            completer3.setException(e);
                                        }
                                        return "IScrollCaptureCallbacks#onCaptureStarted";
                                    }
                                });
                                scrollCaptureController3.mSessionFuture = future2;
                                future2.delegate.addListener(new ScrollCaptureController$$ExternalSyntheticLambda0(scrollCaptureController3, 1), scrollCaptureController3.mContext.getMainExecutor());
                            }
                        });
                        return "<batch scroll capture>";
                    }
                });
                future.delegate.addListener(new Runnable() { // from class: com.android.systemui.screenshot.scroll.ScrollCaptureExecutor$executeBatchScrollCapture$1$1
                    /* JADX WARN: Removed duplicated region for block: B:10:0x0044  */
                    /* JADX WARN: Removed duplicated region for block: B:13:? A[RETURN, SYNTHETIC] */
                    /* JADX WARN: Removed duplicated region for block: B:14:0x0031  */
                    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
                    @Override // java.lang.Runnable
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct add '--show-bad-code' argument
                    */
                    public final void run() {
                        /*
                            r5 = this;
                            com.android.systemui.screenshot.scroll.ScrollCaptureExecutor r0 = com.android.systemui.screenshot.scroll.ScrollCaptureExecutor.this
                            androidx.concurrent.futures.CallbackToFutureAdapter$SafeFuture r1 = r2
                            java.lang.Runnable r2 = r3
                            r0.getClass()
                            r0 = 0
                            androidx.concurrent.futures.CallbackToFutureAdapter$SafeFuture$1 r1 = r1.delegate     // Catch: java.lang.Throwable -> L17
                            java.lang.Object r1 = r1.get()     // Catch: java.lang.Throwable -> L17
                            kotlin.Unit r3 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L13
                            goto L20
                        L13:
                            r3 = move-exception
                            goto L1a
                        L15:
                            r3 = r1
                            goto L19
                        L17:
                            r1 = move-exception
                            goto L15
                        L19:
                            r1 = r0
                        L1a:
                            kotlin.Result$Failure r4 = new kotlin.Result$Failure
                            r4.<init>(r3)
                            r3 = r4
                        L20:
                            java.lang.Throwable r3 = kotlin.Result.m1771exceptionOrNullimpl(r3)
                            if (r3 == 0) goto L31
                            java.lang.String r1 = "ScrollCaptureExecutor"
                            java.lang.String r4 = "Caught exception"
                            android.util.Log.e(r1, r4, r3)
                            r2.run()
                            goto L42
                        L31:
                            com.android.systemui.screenshot.scroll.ScrollCaptureController$LongScreenshot r1 = (com.android.systemui.screenshot.scroll.ScrollCaptureController.LongScreenshot) r1
                            if (r1 == 0) goto L41
                            com.android.systemui.screenshot.scroll.ImageTileSet r3 = r1.mImageTileSet
                            int r3 = r3.getHeight()
                            if (r3 != 0) goto L41
                            r2.run()
                            goto L42
                        L41:
                            r0 = r1
                        L42:
                            if (r0 == 0) goto L60
                            com.android.systemui.screenshot.scroll.ScrollCaptureExecutor r1 = com.android.systemui.screenshot.scroll.ScrollCaptureExecutor.this
                            java.lang.Runnable r2 = r4
                            com.android.systemui.screenshot.LegacyScreenshotController$$ExternalSyntheticLambda4 r5 = r5
                            com.android.systemui.screenshot.scroll.LongScreenshotData r3 = r1.longScreenshotHolder
                            java.util.concurrent.atomic.AtomicReference r3 = r3.mLongScreenshot
                            r3.set(r0)
                            com.android.systemui.screenshot.scroll.ScrollCaptureExecutor$executeBatchScrollCapture$1$1$1$1 r3 = new com.android.systemui.screenshot.scroll.ScrollCaptureExecutor$executeBatchScrollCapture$1$1$1$1
                            r3.<init>(r5, r0)
                            com.android.systemui.screenshot.scroll.LongScreenshotData r5 = r1.longScreenshotHolder
                            java.util.concurrent.atomic.AtomicReference r5 = r5.mTransitionDestinationCallback
                            r5.set(r3)
                            r2.run()
                        L60:
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.screenshot.scroll.ScrollCaptureExecutor$executeBatchScrollCapture$1$1.run():void");
                    }
                }, scrollCaptureExecutor.mainExecutor);
                scrollCaptureExecutor.longScreenshotFuture = future;
                break;
        }
    }

    public /* synthetic */ LegacyScreenshotController$$ExternalSyntheticLambda0(LegacyScreenshotController legacyScreenshotController, ScrollCaptureResponse scrollCaptureResponse, UserHandle userHandle) {
        this.f$0 = legacyScreenshotController;
        this.f$2 = scrollCaptureResponse;
        this.f$1 = userHandle;
    }
}

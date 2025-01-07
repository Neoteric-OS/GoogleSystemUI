package com.android.systemui.screenshot;

import android.net.Uri;
import android.os.UserManager;
import android.util.Log;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.screenshot.ImageExporter;
import com.android.systemui.screenshot.ScreenshotNotificationsController;
import com.android.systemui.screenshot.TakeScreenshotService;
import com.android.wm.shell.R;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$65;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class HeadlessScreenshotHandler implements ScreenshotHandler {
    public final ImageCaptureImpl imageCapture;
    public final ImageExporter imageExporter;
    public final Executor mainExecutor;
    public final ScreenshotNotificationsController.Factory notificationsControllerFactory;
    public final UiEventLogger uiEventLogger;
    public final UserManager userManager;

    public HeadlessScreenshotHandler(ImageExporter imageExporter, Executor executor, ImageCaptureImpl imageCaptureImpl, UserManager userManager, UiEventLogger uiEventLogger, ScreenshotNotificationsController.Factory factory) {
        this.imageExporter = imageExporter;
        this.mainExecutor = executor;
        this.imageCapture = imageCaptureImpl;
        this.userManager = userManager;
        this.uiEventLogger = uiEventLogger;
        this.notificationsControllerFactory = factory;
    }

    public static final void access$logScreenshotResultStatus(HeadlessScreenshotHandler headlessScreenshotHandler, Uri uri, ScreenshotData screenshotData) {
        if (uri == null) {
            headlessScreenshotHandler.uiEventLogger.log(ScreenshotEvent.SCREENSHOT_NOT_SAVED, 0, screenshotData.getPackageNameString());
            ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$65) headlessScreenshotHandler.notificationsControllerFactory).create(screenshotData.displayId).notifyScreenshotError(R.string.screenshot_failed_to_save_text);
        } else {
            headlessScreenshotHandler.uiEventLogger.log(ScreenshotEvent.SCREENSHOT_SAVED, 0, screenshotData.getPackageNameString());
            if (headlessScreenshotHandler.userManager.isManagedProfile(screenshotData.getUserOrDefault().getIdentifier())) {
                headlessScreenshotHandler.uiEventLogger.log(ScreenshotEvent.SCREENSHOT_SAVED_TO_WORK_PROFILE, 0, screenshotData.getPackageNameString());
            }
        }
    }

    @Override // com.android.systemui.screenshot.ScreenshotHandler
    public final void handleScreenshot(final ScreenshotData screenshotData, final TakeScreenshotExecutorImpl$sam$java_util_function_Consumer$0 takeScreenshotExecutorImpl$sam$java_util_function_Consumer$0, final TakeScreenshotService.RequestCallback requestCallback) {
        int i = screenshotData.displayId;
        if (screenshotData.type == 1) {
            screenshotData.bitmap = this.imageCapture.captureDisplay(i, null);
        }
        if (screenshotData.bitmap == null) {
            Log.e("HeadlessScreenshotHandler", "handleScreenshot: Screenshot bitmap was null");
            ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$65) this.notificationsControllerFactory).create(i).notifyScreenshotError(R.string.screenshot_failed_to_capture_text);
            requestCallback.reportError();
            return;
        }
        final CallbackToFutureAdapter.SafeFuture export = this.imageExporter.export(Executors.newSingleThreadExecutor(), UUID.randomUUID(), screenshotData.bitmap, screenshotData.getUserOrDefault(), screenshotData.displayId);
        export.delegate.addListener(new Runnable() { // from class: com.android.systemui.screenshot.HeadlessScreenshotHandler$handleScreenshot$1
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    ImageExporter.Result result = (ImageExporter.Result) CallbackToFutureAdapter.SafeFuture.this.delegate.get();
                    Log.d("HeadlessScreenshotHandler", "Saved screenshot: " + result);
                    HeadlessScreenshotHandler.access$logScreenshotResultStatus(this, result.uri, screenshotData);
                    takeScreenshotExecutorImpl$sam$java_util_function_Consumer$0.accept(result.uri);
                    requestCallback.onFinish();
                } catch (Exception e) {
                    Log.d("HeadlessScreenshotHandler", "Failed to store screenshot", e);
                    takeScreenshotExecutorImpl$sam$java_util_function_Consumer$0.accept(null);
                    requestCallback.reportError();
                }
            }
        }, this.mainExecutor);
    }
}

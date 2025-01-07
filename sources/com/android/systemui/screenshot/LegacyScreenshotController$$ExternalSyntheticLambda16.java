package com.android.systemui.screenshot;

import android.os.UserHandle;
import android.view.ScrollCaptureResponse;
import com.android.systemui.screenshot.LegacyScreenshotController;
import com.google.android.systemui.screenshot.ScreenshotActionsProviderGoogle;
import java.util.UUID;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class LegacyScreenshotController$$ExternalSyntheticLambda16 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;
    public final /* synthetic */ UUID f$1;
    public final /* synthetic */ UserHandle f$2;

    public /* synthetic */ LegacyScreenshotController$$ExternalSyntheticLambda16(Object obj, UUID uuid, UserHandle userHandle, int i) {
        this.$r8$classId = i;
        this.f$0 = obj;
        this.f$1 = uuid;
        this.f$2 = userHandle;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                final LegacyScreenshotController legacyScreenshotController = (LegacyScreenshotController) this.f$0;
                final UUID uuid = this.f$1;
                final UserHandle userHandle = this.f$2;
                legacyScreenshotController.mScrollCaptureExecutor.requestScrollCapture(legacyScreenshotController.mDisplay.getDisplayId(), legacyScreenshotController.mWindow.getDecorView().getWindowToken(), new Function1() { // from class: com.android.systemui.screenshot.LegacyScreenshotController$$ExternalSyntheticLambda17
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        ScreenshotActionsProviderGoogle screenshotActionsProviderGoogle;
                        UUID uuid2 = uuid;
                        UserHandle userHandle2 = userHandle;
                        ScrollCaptureResponse scrollCaptureResponse = (ScrollCaptureResponse) obj;
                        LegacyScreenshotController legacyScreenshotController2 = LegacyScreenshotController.this;
                        legacyScreenshotController2.mUiEventLogger.log(ScreenshotEvent.SCREENSHOT_LONG_SCREENSHOT_IMPRESSION, 0, scrollCaptureResponse.getPackageName());
                        LegacyScreenshotController$$ExternalSyntheticLambda0 legacyScreenshotController$$ExternalSyntheticLambda0 = new LegacyScreenshotController$$ExternalSyntheticLambda0(legacyScreenshotController2, userHandle2, scrollCaptureResponse);
                        ScreenshotActionsController screenshotActionsController = legacyScreenshotController2.mActionsController;
                        if (uuid2.equals(screenshotActionsController.currentScreenshotId) && (screenshotActionsProviderGoogle = (ScreenshotActionsProviderGoogle) screenshotActionsController.actionProviders.get(uuid2)) != null) {
                            screenshotActionsProviderGoogle.onScrollChipReady(legacyScreenshotController$$ExternalSyntheticLambda0);
                        }
                        return Unit.INSTANCE;
                    }
                });
                legacyScreenshotController.mWindow.peekDecorView().getViewRootImpl().setActivityConfigCallback(new LegacyScreenshotController.AnonymousClass3(legacyScreenshotController, uuid, userHandle));
                break;
            default:
                LegacyScreenshotController.AnonymousClass3 anonymousClass3 = (LegacyScreenshotController.AnonymousClass3) this.f$0;
                final UUID uuid2 = this.f$1;
                final UserHandle userHandle2 = this.f$2;
                final LegacyScreenshotController legacyScreenshotController2 = anonymousClass3.this$0;
                legacyScreenshotController2.mScrollCaptureExecutor.requestScrollCapture(legacyScreenshotController2.mDisplay.getDisplayId(), legacyScreenshotController2.mWindow.getDecorView().getWindowToken(), new Function1() { // from class: com.android.systemui.screenshot.LegacyScreenshotController$$ExternalSyntheticLambda17
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        ScreenshotActionsProviderGoogle screenshotActionsProviderGoogle;
                        UUID uuid22 = uuid2;
                        UserHandle userHandle22 = userHandle2;
                        ScrollCaptureResponse scrollCaptureResponse = (ScrollCaptureResponse) obj;
                        LegacyScreenshotController legacyScreenshotController22 = LegacyScreenshotController.this;
                        legacyScreenshotController22.mUiEventLogger.log(ScreenshotEvent.SCREENSHOT_LONG_SCREENSHOT_IMPRESSION, 0, scrollCaptureResponse.getPackageName());
                        LegacyScreenshotController$$ExternalSyntheticLambda0 legacyScreenshotController$$ExternalSyntheticLambda0 = new LegacyScreenshotController$$ExternalSyntheticLambda0(legacyScreenshotController22, userHandle22, scrollCaptureResponse);
                        ScreenshotActionsController screenshotActionsController = legacyScreenshotController22.mActionsController;
                        if (uuid22.equals(screenshotActionsController.currentScreenshotId) && (screenshotActionsProviderGoogle = (ScreenshotActionsProviderGoogle) screenshotActionsController.actionProviders.get(uuid22)) != null) {
                            screenshotActionsProviderGoogle.onScrollChipReady(legacyScreenshotController$$ExternalSyntheticLambda0);
                        }
                        return Unit.INSTANCE;
                    }
                });
                break;
        }
    }
}

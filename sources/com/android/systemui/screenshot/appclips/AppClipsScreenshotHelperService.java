package com.android.systemui.screenshot.appclips;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.window.ScreenCapture;
import com.android.wm.shell.bubbles.BubbleController;
import com.android.wm.shell.bubbles.BubbleController$BubblesImpl$$ExternalSyntheticLambda4;
import com.android.wm.shell.bubbles.Bubbles;
import com.android.wm.shell.common.HandlerExecutor;
import java.util.Optional;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class AppClipsScreenshotHelperService extends Service {
    public final Optional mOptionalBubbles;

    public AppClipsScreenshotHelperService(Optional optional) {
        this.mOptionalBubbles = optional;
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return new AnonymousClass1();
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.screenshot.appclips.AppClipsScreenshotHelperService$1, reason: invalid class name */
    public final class AnonymousClass1 extends Binder implements IAppClipsScreenshotHelperService {
        public static final /* synthetic */ int $r8$clinit = 0;

        public AnonymousClass1() {
            attachInterface(this, "com.android.systemui.screenshot.appclips.IAppClipsScreenshotHelperService");
        }

        @Override // android.os.Binder
        public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface("com.android.systemui.screenshot.appclips.IAppClipsScreenshotHelperService");
            }
            if (i == 1598968902) {
                parcel2.writeString("com.android.systemui.screenshot.appclips.IAppClipsScreenshotHelperService");
                return true;
            }
            if (i != 1) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            int readInt = parcel.readInt();
            parcel.enforceNoDataAvail();
            ScreenshotHardwareBufferInternal takeScreenshot = takeScreenshot(readInt);
            parcel2.writeNoException();
            parcel2.writeTypedObject(takeScreenshot, 1);
            return true;
        }

        @Override // com.android.systemui.screenshot.appclips.IAppClipsScreenshotHelperService
        public final ScreenshotHardwareBufferInternal takeScreenshot(int i) {
            if (AppClipsScreenshotHelperService.this.mOptionalBubbles.isEmpty()) {
                return null;
            }
            BubbleController.BubblesImpl bubblesImpl = (BubbleController.BubblesImpl) ((Bubbles) AppClipsScreenshotHelperService.this.mOptionalBubbles.get());
            bubblesImpl.getClass();
            ScreenCapture.SynchronousScreenCaptureListener createSyncCaptureListener = ScreenCapture.createSyncCaptureListener();
            ((HandlerExecutor) BubbleController.this.mMainExecutor).execute(new BubbleController$BubblesImpl$$ExternalSyntheticLambda4(bubblesImpl, i, createSyncCaptureListener));
            ScreenCapture.ScreenshotHardwareBuffer buffer = createSyncCaptureListener.getBuffer();
            if (buffer == null) {
                return null;
            }
            return new ScreenshotHardwareBufferInternal(buffer);
        }

        @Override // android.os.IInterface
        public final IBinder asBinder() {
            return this;
        }
    }
}

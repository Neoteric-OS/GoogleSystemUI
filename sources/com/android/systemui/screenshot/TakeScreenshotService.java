package com.android.systemui.screenshot;

import android.app.Service;
import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.os.UserManager;
import android.util.Log;
import android.widget.Toast;
import com.android.internal.logging.UiEventLogger;
import com.android.internal.util.ScreenshotRequest;
import com.android.systemui.screenshot.ScreenshotNotificationsController;
import com.android.systemui.screenshot.TakeScreenshotService;
import com.android.wm.shell.R;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$65;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import java.util.function.Supplier;
import kotlinx.coroutines.BuildersKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class TakeScreenshotService extends Service {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final Executor mBgExecutor;
    public final Context mContext;
    public final DevicePolicyManager mDevicePolicyManager;
    public final ScreenshotNotificationsController mNotificationsController;
    public final TakeScreenshotExecutor mTakeScreenshotExecutor;
    public final UiEventLogger mUiEventLogger;
    public final UserManager mUserManager;
    public final AnonymousClass1 mCloseSystemDialogs = new BroadcastReceiver() { // from class: com.android.systemui.screenshot.TakeScreenshotService.1
        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            TakeScreenshotExecutorImpl takeScreenshotExecutorImpl;
            LegacyScreenshotController legacyScreenshotController;
            LegacyScreenshotController legacyScreenshotController2;
            if (!"android.intent.action.CLOSE_SYSTEM_DIALOGS".equals(intent.getAction()) || (legacyScreenshotController = (takeScreenshotExecutorImpl = (TakeScreenshotExecutorImpl) TakeScreenshotService.this.mTakeScreenshotExecutor).screenshotController) == null || legacyScreenshotController.mActionExecutor.isPendingSharedTransition || (legacyScreenshotController2 = takeScreenshotExecutorImpl.screenshotController) == null) {
                return;
            }
            ScreenshotEvent screenshotEvent = ScreenshotEvent.SCREENSHOT_REQUESTED_GLOBAL_ACTIONS;
            legacyScreenshotController2.getClass();
            legacyScreenshotController2.mViewProxy.requestDismissal(ScreenshotEvent.SCREENSHOT_DISMISSED_OTHER, null);
        }
    };
    public final Handler mHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.android.systemui.screenshot.TakeScreenshotService$$ExternalSyntheticLambda0
        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            final TakeScreenshotService takeScreenshotService = TakeScreenshotService.this;
            int i = TakeScreenshotService.$r8$clinit;
            final Messenger messenger = message.replyTo;
            Consumer consumer = new Consumer() { // from class: com.android.systemui.screenshot.TakeScreenshotService$$ExternalSyntheticLambda1
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    Messenger messenger2 = messenger;
                    Uri uri = (Uri) obj;
                    int i2 = TakeScreenshotService.$r8$clinit;
                    try {
                        messenger2.send(Message.obtain(null, 1, uri));
                    } catch (RemoteException e) {
                        Log.d("Screenshot", "ignored remote exception", e);
                    }
                }
            };
            final TakeScreenshotService.RequestCallbackImpl requestCallbackImpl = new TakeScreenshotService.RequestCallbackImpl(messenger);
            final ScreenshotRequest screenshotRequest = (ScreenshotRequest) message.obj;
            if (!takeScreenshotService.mUserManager.isUserUnlocked()) {
                Log.w("Screenshot", "Skipping screenshot because storage is locked!");
                takeScreenshotService.logFailedRequest(screenshotRequest);
                takeScreenshotService.mNotificationsController.notifyScreenshotError(R.string.screenshot_failed_to_save_user_locked_text);
                requestCallbackImpl.reportError();
                return true;
            }
            if (takeScreenshotService.mDevicePolicyManager.getScreenCaptureDisabled(null, -1)) {
                takeScreenshotService.mBgExecutor.execute(new Runnable() { // from class: com.android.systemui.screenshot.TakeScreenshotService$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        final TakeScreenshotService takeScreenshotService2 = TakeScreenshotService.this;
                        ScreenshotRequest screenshotRequest2 = screenshotRequest;
                        TakeScreenshotService.RequestCallbackImpl requestCallbackImpl2 = requestCallbackImpl;
                        int i2 = TakeScreenshotService.$r8$clinit;
                        Log.w("Screenshot", "Skipping screenshot because an IT admin has disabled screenshots on the device");
                        takeScreenshotService2.logFailedRequest(screenshotRequest2);
                        final String string = takeScreenshotService2.mDevicePolicyManager.getResources().getString("SystemUi.SCREENSHOT_BLOCKED_BY_ADMIN", new Supplier() { // from class: com.android.systemui.screenshot.TakeScreenshotService$$ExternalSyntheticLambda3
                            @Override // java.util.function.Supplier
                            public final Object get() {
                                return TakeScreenshotService.this.mContext.getString(R.string.screenshot_blocked_by_admin);
                            }
                        });
                        takeScreenshotService2.mHandler.post(new Runnable() { // from class: com.android.systemui.screenshot.TakeScreenshotService$$ExternalSyntheticLambda4
                            @Override // java.lang.Runnable
                            public final void run() {
                                TakeScreenshotService takeScreenshotService3 = TakeScreenshotService.this;
                                Toast.makeText(takeScreenshotService3.mContext, string, 0).show();
                            }
                        });
                        requestCallbackImpl2.reportError();
                    }
                });
                return true;
            }
            Log.d("Screenshot", "Processing screenshot data");
            TakeScreenshotExecutorImpl takeScreenshotExecutorImpl = (TakeScreenshotExecutorImpl) takeScreenshotService.mTakeScreenshotExecutor;
            takeScreenshotExecutorImpl.getClass();
            BuildersKt.launch$default(takeScreenshotExecutorImpl.mainScope, null, null, new TakeScreenshotExecutorImpl$executeScreenshotsAsync$1(takeScreenshotExecutorImpl, screenshotRequest, requestCallbackImpl, consumer, null), 3);
            return true;
        }
    });

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface RequestCallback {
        void onFinish();

        void reportError();
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class RequestCallbackImpl implements RequestCallback {
        public final Messenger mReplyTo;

        public RequestCallbackImpl(Messenger messenger) {
            this.mReplyTo = messenger;
        }

        @Override // com.android.systemui.screenshot.TakeScreenshotService.RequestCallback
        public final void onFinish() {
            Messenger messenger = this.mReplyTo;
            int i = TakeScreenshotService.$r8$clinit;
            try {
                messenger.send(Message.obtain((Handler) null, 2));
            } catch (RemoteException e) {
                Log.d("Screenshot", "ignored remote exception", e);
            }
        }

        @Override // com.android.systemui.screenshot.TakeScreenshotService.RequestCallback
        public final void reportError() {
            Messenger messenger = this.mReplyTo;
            int i = TakeScreenshotService.$r8$clinit;
            try {
                messenger.send(Message.obtain(null, 1, null));
            } catch (RemoteException e) {
                Log.d("Screenshot", "ignored remote exception", e);
            }
            try {
                this.mReplyTo.send(Message.obtain((Handler) null, 2));
            } catch (RemoteException e2) {
                Log.d("Screenshot", "ignored remote exception", e2);
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.android.systemui.screenshot.TakeScreenshotService$1] */
    public TakeScreenshotService(UserManager userManager, DevicePolicyManager devicePolicyManager, UiEventLogger uiEventLogger, ScreenshotNotificationsController.Factory factory, Context context, Executor executor, TakeScreenshotExecutor takeScreenshotExecutor) {
        this.mUserManager = userManager;
        this.mDevicePolicyManager = devicePolicyManager;
        this.mUiEventLogger = uiEventLogger;
        this.mNotificationsController = ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$65) factory).create(0);
        this.mContext = context;
        this.mBgExecutor = executor;
        this.mTakeScreenshotExecutor = takeScreenshotExecutor;
    }

    public final void logFailedRequest(ScreenshotRequest screenshotRequest) {
        ComponentName topComponent = screenshotRequest.getTopComponent();
        String packageName = topComponent == null ? "" : topComponent.getPackageName();
        this.mUiEventLogger.log(ScreenshotEvent.getScreenshotSource(screenshotRequest.getSource()), 0, packageName);
        this.mUiEventLogger.log(ScreenshotEvent.SCREENSHOT_CAPTURE_FAILED, 0, packageName);
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        registerReceiver(this.mCloseSystemDialogs, new IntentFilter("android.intent.action.CLOSE_SYSTEM_DIALOGS"), 2);
        return new Messenger(this.mHandler).getBinder();
    }

    @Override // android.app.Service
    public final void onDestroy() {
        super.onDestroy();
        TakeScreenshotExecutorImpl takeScreenshotExecutorImpl = (TakeScreenshotExecutorImpl) this.mTakeScreenshotExecutor;
        LegacyScreenshotController legacyScreenshotController = takeScreenshotExecutorImpl.screenshotController;
        if (legacyScreenshotController != null) {
            legacyScreenshotController.removeWindow();
            ScreenshotSoundControllerImpl screenshotSoundControllerImpl = legacyScreenshotController.mScreenshotSoundController;
            if (screenshotSoundControllerImpl != null) {
                BuildersKt.launch$default(screenshotSoundControllerImpl.coroutineScope, null, null, new ScreenshotSoundControllerImpl$releaseScreenshotSoundAsync$1(screenshotSoundControllerImpl, null), 3);
            }
            legacyScreenshotController.mBroadcastDispatcher.unregisterReceiver(legacyScreenshotController.mCopyBroadcastReceiver);
            legacyScreenshotController.mContext.release();
            legacyScreenshotController.mBgExecutor.shutdown();
        }
        takeScreenshotExecutorImpl.screenshotController = null;
    }

    @Override // android.app.Service
    public final boolean onUnbind(Intent intent) {
        LegacyScreenshotController legacyScreenshotController = ((TakeScreenshotExecutorImpl) this.mTakeScreenshotExecutor).screenshotController;
        if (legacyScreenshotController != null) {
            legacyScreenshotController.removeWindow();
        }
        unregisterReceiver(this.mCloseSystemDialogs);
        return false;
    }

    @Override // android.app.Service
    public final void onCreate() {
    }
}

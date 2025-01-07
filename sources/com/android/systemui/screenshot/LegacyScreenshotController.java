package com.android.systemui.screenshot;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.assist.AssistContent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Insets;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.IBinder;
import android.os.Process;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.MathUtils;
import android.view.Display;
import android.view.ScrollCaptureResponse;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewRootImpl;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.window.WindowContext;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import com.android.internal.logging.UiEventLogger;
import com.android.internal.policy.PhoneWindow;
import com.android.settingslib.applications.InterestingConfigChanges;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.broadcast.BroadcastSender;
import com.android.systemui.screenshot.AssistContentRequester;
import com.android.systemui.screenshot.ImageExporter;
import com.android.systemui.screenshot.ScreenshotActionsController;
import com.android.systemui.screenshot.ScreenshotActionsController.ActionsCallback;
import com.android.systemui.screenshot.ScreenshotNotificationsController;
import com.android.systemui.screenshot.TakeScreenshotService;
import com.android.systemui.screenshot.scroll.ScrollCaptureExecutor;
import com.android.systemui.screenshot.ui.ScreenshotAnimationController;
import com.android.systemui.screenshot.ui.ScreenshotAnimationController$getActionsAnimator$1;
import com.android.systemui.screenshot.ui.ScreenshotAnimationController$getEntranceAnimation$$inlined$doOnEnd$1;
import com.android.systemui.screenshot.ui.ScreenshotAnimationController$runLongScreenshotTransition$$inlined$doOnEnd$1;
import com.android.systemui.screenshot.ui.ScreenshotShelfView;
import com.android.systemui.screenshot.ui.binder.ActionButtonViewBinder;
import com.android.systemui.screenshot.ui.binder.ScreenshotShelfViewBinder;
import com.android.systemui.screenshot.ui.viewmodel.AnimationState;
import com.android.systemui.screenshot.ui.viewmodel.ScreenshotViewModel;
import com.android.systemui.util.Assert;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$65;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$67;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$68;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$69;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$70;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$71;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;
import com.google.android.systemui.screenshot.ScreenshotActionsProviderGoogle;
import com.google.android.systemui.screenshot.SmartActionsProvider;
import com.google.android.systemui.screenshot.ThumbnailObserverGoogle;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class LegacyScreenshotController implements ScreenshotHandler {
    public final ActionExecutor mActionExecutor;
    public final ScreenshotActionsController mActionsController;
    public final AnnouncementResolver mAnnouncementResolver;
    public final AssistContentRequester mAssistContentRequester;
    public boolean mAttachRequested;
    public final ExecutorService mBgExecutor;
    public final BroadcastDispatcher mBroadcastDispatcher;
    public final BroadcastSender mBroadcastSender;
    public final InterestingConfigChanges mConfigChanges;
    public final WindowContext mContext;
    public final AnonymousClass1 mCopyBroadcastReceiver;
    public TakeScreenshotService.RequestCallback mCurrentRequestCallback;
    public boolean mDetachRequested;
    public final Display mDisplay;
    public final ImageCaptureImpl mImageCapture;
    public final ImageExporter mImageExporter;
    public final Executor mMainExecutor;
    public final MessageContainerController mMessageContainerController;
    public final ScreenshotNotificationsController mNotificationsController;
    public String mPackageName = "";
    public Bitmap mScreenBitmap;
    public Animator mScreenshotAnimation;
    public final TimeoutHandler mScreenshotHandler;
    public final ScreenshotSoundControllerImpl mScreenshotSoundController;
    public final ScrollCaptureExecutor mScrollCaptureExecutor;
    public final UiEventLogger mUiEventLogger;
    public final UserManager mUserManager;
    public final ScreenshotShelfViewProxy mViewProxy;
    public final PhoneWindow mWindow;
    public final WindowManager.LayoutParams mWindowLayoutParams;
    public final WindowManager mWindowManager;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.screenshot.LegacyScreenshotController$2, reason: invalid class name */
    public final class AnonymousClass2 {
        public AnonymousClass2() {
        }

        public final void onDismiss() {
            LegacyScreenshotController.this.finishDismiss();
        }

        public final void onUserInteraction() {
            LegacyScreenshotController.this.mScreenshotHandler.resetTimeout();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.screenshot.LegacyScreenshotController$3, reason: invalid class name */
    public final class AnonymousClass3 implements ViewRootImpl.ActivityConfigCallback {
        public final /* synthetic */ UserHandle val$owner;
        public final /* synthetic */ UUID val$requestId;

        public AnonymousClass3(UUID uuid, UserHandle userHandle) {
            this.val$requestId = uuid;
            this.val$owner = userHandle;
        }

        public final void onConfigurationChanged(Configuration configuration, int i) {
            LegacyScreenshotController legacyScreenshotController = LegacyScreenshotController.this;
            if (legacyScreenshotController.mConfigChanges.applyNewConfig(legacyScreenshotController.mContext.getResources())) {
                Iterator it = LegacyScreenshotController.this.mActionsController.actionProviders.values().iterator();
                while (it.hasNext()) {
                    ((ScreenshotActionsProviderGoogle) it.next()).onScrollClick = null;
                }
                LegacyScreenshotController.this.mScreenshotHandler.postDelayed(new LegacyScreenshotController$$ExternalSyntheticLambda16(this, this.val$requestId, this.val$owner, 1), 150L);
                LegacyScreenshotController legacyScreenshotController2 = LegacyScreenshotController.this;
                ScreenshotShelfViewProxy screenshotShelfViewProxy = legacyScreenshotController2.mViewProxy;
                screenshotShelfViewProxy.view.updateInsets(legacyScreenshotController2.mWindowManager.getCurrentWindowMetrics().getWindowInsets());
                Animator animator = LegacyScreenshotController.this.mScreenshotAnimation;
                if (animator == null || !animator.isRunning()) {
                    return;
                }
                LegacyScreenshotController.this.mScreenshotAnimation.end();
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v7, types: [android.content.BroadcastReceiver, com.android.systemui.screenshot.LegacyScreenshotController$1] */
    public LegacyScreenshotController(Context context, WindowManager windowManager, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$67 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$67, ScreenshotNotificationsController.Factory factory, UiEventLogger uiEventLogger, ImageExporter imageExporter, ImageCaptureImpl imageCaptureImpl, Executor executor, ScrollCaptureExecutor scrollCaptureExecutor, TimeoutHandler timeoutHandler, BroadcastSender broadcastSender, BroadcastDispatcher broadcastDispatcher, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$68 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$68, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$71 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$71, UserManager userManager, AssistContentRequester assistContentRequester, MessageContainerController messageContainerController, DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider, AnnouncementResolver announcementResolver, Display display) {
        InterestingConfigChanges interestingConfigChanges = new InterestingConfigChanges(-2147474556);
        this.mConfigChanges = interestingConfigChanges;
        this.mNotificationsController = ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$65) factory).create(display.getDisplayId());
        this.mUiEventLogger = uiEventLogger;
        this.mImageExporter = imageExporter;
        this.mImageCapture = imageCaptureImpl;
        this.mMainExecutor = executor;
        this.mScrollCaptureExecutor = scrollCaptureExecutor;
        this.mBgExecutor = Executors.newSingleThreadExecutor();
        this.mBroadcastSender = broadcastSender;
        this.mBroadcastDispatcher = broadcastDispatcher;
        this.mScreenshotHandler = timeoutHandler;
        timeoutHandler.mDefaultTimeout = 6000;
        this.mDisplay = display;
        this.mWindowManager = windowManager;
        WindowContext createWindowContext = context.createDisplayContext(display).createWindowContext(2036, null);
        this.mContext = createWindowContext;
        this.mUserManager = userManager;
        this.mMessageContainerController = messageContainerController;
        this.mAssistContentRequester = assistContentRequester;
        this.mAnnouncementResolver = announcementResolver;
        int displayId = display.getDisplayId();
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider2 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$67.this$0;
        UiEventLogger uiEventLogger2 = (UiEventLogger) switchingProvider2.sysUIGoogleGlobalRootComponentImpl.provideUiEventLoggerProvider.get();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) switchingProvider2.wMComponentImpl;
        ScreenshotViewModel screenshotViewModel = (ScreenshotViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesScreenshotViewModelProvider.get();
        WindowManager windowManager2 = (WindowManager) switchingProvider2.sysUIGoogleGlobalRootComponentImpl.provideWindowManagerProvider.get();
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        ScreenshotShelfViewProxy screenshotShelfViewProxy = new ScreenshotShelfViewProxy(uiEventLogger2, screenshotViewModel, windowManager2, new ScreenshotShelfViewBinder(new ActionButtonViewBinder()), (ThumbnailObserverGoogle) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesThumbnailObserverProvider.get(), createWindowContext, displayId);
        this.mViewProxy = screenshotShelfViewProxy;
        timeoutHandler.mOnTimeout = new LegacyScreenshotController$$ExternalSyntheticLambda9(0, this);
        WindowManager.LayoutParams floatingWindowParams = FloatingWindowUtil.getFloatingWindowParams();
        this.mWindowLayoutParams = floatingWindowParams;
        floatingWindowParams.setTitle("ScreenshotAnimation");
        PhoneWindow phoneWindow = new PhoneWindow(createWindowContext);
        phoneWindow.requestFeature(1);
        phoneWindow.requestFeature(13);
        phoneWindow.setBackgroundDrawableResource(R.color.transparent);
        this.mWindow = phoneWindow;
        phoneWindow.setWindowManager(windowManager, (IBinder) null, (String) null);
        interestingConfigChanges.applyNewConfig(context.getResources());
        ScreenshotShelfView screenshotShelfView = screenshotShelfViewProxy.view;
        messageContainerController.getClass();
        messageContainerController.container = (ViewGroup) screenshotShelfView.requireViewById(com.android.wm.shell.R.id.screenshot_message_container);
        messageContainerController.guideline = (Guideline) screenshotShelfView.requireViewById(com.android.wm.shell.R.id.guideline);
        ViewGroup viewGroup = messageContainerController.container;
        messageContainerController.workProfileFirstRunView = (ViewGroup) (viewGroup == null ? null : viewGroup).requireViewById(com.android.wm.shell.R.id.work_profile_first_run);
        ViewGroup viewGroup2 = messageContainerController.container;
        messageContainerController.detectionNoticeView = (ViewGroup) (viewGroup2 == null ? null : viewGroup2).requireViewById(com.android.wm.shell.R.id.screenshot_detection_notice);
        ViewGroup viewGroup3 = messageContainerController.container;
        (viewGroup3 == null ? null : viewGroup3).setVisibility(8);
        Guideline guideline = messageContainerController.guideline;
        guideline = guideline == null ? null : guideline;
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) guideline.getLayoutParams();
        if (!guideline.mFilterRedundantCalls || layoutParams.guideEnd != 0) {
            layoutParams.guideEnd = 0;
            guideline.setLayoutParams(layoutParams);
        }
        ViewGroup viewGroup4 = messageContainerController.workProfileFirstRunView;
        (viewGroup4 == null ? null : viewGroup4).setVisibility(8);
        ViewGroup viewGroup5 = messageContainerController.detectionNoticeView;
        (viewGroup5 == null ? null : viewGroup5).setVisibility(8);
        screenshotShelfViewProxy.callbacks = new AnonymousClass2();
        phoneWindow.setContentView(screenshotShelfView);
        Function0 function0 = new Function0() { // from class: com.android.systemui.screenshot.LegacyScreenshotController$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                LegacyScreenshotController.this.finishDismiss();
                return Unit.INSTANCE;
            }
        };
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider3 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$71.this$0;
        ActionExecutor actionExecutor = new ActionExecutor((ActionIntentExecutor) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) switchingProvider3.wMComponentImpl).actionIntentExecutorProvider.get(), (CoroutineScope) switchingProvider3.sysUIGoogleGlobalRootComponentImpl.applicationScopeProvider.get(), phoneWindow, screenshotShelfViewProxy, function0);
        this.mActionExecutor = actionExecutor;
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider4 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$68.this$0;
        this.mActionsController = new ScreenshotActionsController((ScreenshotViewModel) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) switchingProvider4.wMComponentImpl).providesScreenshotViewModelProvider.get(), (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$69) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) switchingProvider4.wMComponentImpl).factoryProvider70.get(), actionExecutor);
        if (display.getDisplayId() == 0) {
            this.mScreenshotSoundController = (ScreenshotSoundControllerImpl) switchingProvider.get();
        } else {
            this.mScreenshotSoundController = null;
        }
        ?? r1 = new BroadcastReceiver() { // from class: com.android.systemui.screenshot.LegacyScreenshotController.1
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context2, Intent intent) {
                if ("com.android.systemui.COPY".equals(intent.getAction())) {
                    LegacyScreenshotController.this.mViewProxy.requestDismissal(ScreenshotEvent.SCREENSHOT_DISMISSED_OTHER, null);
                }
            }
        };
        this.mCopyBroadcastReceiver = r1;
        broadcastDispatcher.registerReceiver(r1, new IntentFilter("com.android.systemui.COPY"), null, null, 4, "com.android.systemui.permission.SELF");
    }

    public final void finishDismiss() {
        Log.d("Screenshot", "finishDismiss");
        this.mActionsController.currentScreenshotId = null;
        ScrollCaptureExecutor scrollCaptureExecutor = this.mScrollCaptureExecutor;
        CallbackToFutureAdapter.SafeFuture safeFuture = scrollCaptureExecutor.lastScrollCaptureRequest;
        if (safeFuture != null) {
            safeFuture.cancel(true);
        }
        scrollCaptureExecutor.lastScrollCaptureRequest = null;
        ScrollCaptureResponse scrollCaptureResponse = scrollCaptureExecutor.lastScrollCaptureResponse;
        if (scrollCaptureResponse != null) {
            scrollCaptureResponse.close();
        }
        scrollCaptureExecutor.lastScrollCaptureResponse = null;
        CallbackToFutureAdapter.SafeFuture safeFuture2 = scrollCaptureExecutor.longScreenshotFuture;
        if (safeFuture2 != null) {
            safeFuture2.cancel(true);
        }
        TakeScreenshotService.RequestCallback requestCallback = this.mCurrentRequestCallback;
        if (requestCallback != null) {
            requestCallback.onFinish();
            this.mCurrentRequestCallback = null;
        }
        this.mViewProxy.reset();
        removeWindow();
        this.mScreenshotHandler.removeMessages(2);
    }

    @Override // com.android.systemui.screenshot.ScreenshotHandler
    public final void handleScreenshot(final ScreenshotData screenshotData, TakeScreenshotExecutorImpl$sam$java_util_function_Consumer$0 takeScreenshotExecutorImpl$sam$java_util_function_Consumer$0, TakeScreenshotService.RequestCallback requestCallback) {
        boolean z;
        final boolean z2;
        ScreenshotViewModel screenshotViewModel;
        Drawable drawable;
        UserHandle userHandle;
        Assert.isMainThread();
        this.mCurrentRequestCallback = requestCallback;
        if (screenshotData.type == 1 && screenshotData.bitmap == null) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            this.mDisplay.getRealMetrics(displayMetrics);
            Rect rect = new Rect(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
            screenshotData.bitmap = this.mImageCapture.captureDisplay(this.mDisplay.getDisplayId(), rect);
            screenshotData.screenBounds = rect;
        }
        Bitmap bitmap = screenshotData.bitmap;
        if (bitmap == null) {
            Log.e("Screenshot", "handleScreenshot: Screenshot bitmap was null");
            this.mNotificationsController.notifyScreenshotError(com.android.wm.shell.R.string.screenshot_failed_to_capture_text);
            TakeScreenshotService.RequestCallback requestCallback2 = this.mCurrentRequestCallback;
            if (requestCallback2 != null) {
                requestCallback2.reportError();
                return;
            }
            return;
        }
        this.mScreenBitmap = bitmap;
        String str = this.mPackageName;
        this.mPackageName = screenshotData.getPackageNameString();
        if (Settings.Secure.getInt(this.mContext.createContextAsUser(Process.myUserHandle(), 0).getContentResolver(), "user_setup_complete", 0) != 1) {
            Log.w("Screenshot", "User setup not complete, displaying toast only");
            ScreenshotSoundControllerImpl screenshotSoundControllerImpl = this.mScreenshotSoundController;
            if (screenshotSoundControllerImpl != null) {
                BuildersKt.launch$default(screenshotSoundControllerImpl.coroutineScope, null, null, new ScreenshotSoundControllerImpl$playScreenshotSoundAsync$1(screenshotSoundControllerImpl, null), 3);
            }
            saveScreenshotInBackground(screenshotData, UUID.randomUUID(), takeScreenshotExecutorImpl$sam$java_util_function_Consumer$0, new LegacyScreenshotController$$ExternalSyntheticLambda15(0, this));
            return;
        }
        this.mBroadcastSender.sendBroadcast(new Intent("com.android.systemui.SCREENSHOT"), "com.android.systemui.permission.SELF");
        int i = this.mContext.getResources().getConfiguration().orientation;
        this.mScreenBitmap.setHasAlpha(false);
        this.mScreenBitmap.prepareToDraw();
        withWindowAttached(new LegacyScreenshotController$$ExternalSyntheticLambda2(this, screenshotData, 2));
        final ScreenshotShelfViewProxy screenshotShelfViewProxy = this.mViewProxy;
        screenshotShelfViewProxy.reset();
        ScreenshotShelfView screenshotShelfView = screenshotShelfViewProxy.view;
        if (screenshotShelfView.isAttachedToWindow() && !screenshotShelfViewProxy.isDismissing) {
            this.mUiEventLogger.log(ScreenshotEvent.SCREENSHOT_REENTERED, 0, str);
        }
        screenshotShelfViewProxy.packageName = this.mPackageName;
        ScreenshotActionsController screenshotActionsController = this.mActionsController;
        screenshotActionsController.getClass();
        final UUID randomUUID = UUID.randomUUID();
        screenshotActionsController.currentScreenshotId = randomUUID;
        Map map = screenshotActionsController.actionProviders;
        Intrinsics.checkNotNull(randomUUID);
        ScreenshotActionsController.ActionsCallback actionsCallback = screenshotActionsController.new ActionsCallback(randomUUID);
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider = screenshotActionsController.actionsProviderFactory.this$0;
        Context context = switchingProvider.sysUIGoogleGlobalRootComponentImpl.context;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) switchingProvider.wMComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        SmartActionsProvider smartActionsProvider = new SmartActionsProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (CoroutineScope) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.applicationScopeProvider.get(), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgDispatcherProvider.get(), (ScreenshotNotificationSmartActionsProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesScrnshtNotifSmartActionsProvider.get());
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2 = switchingProvider.sysUIGoogleGlobalRootComponentImpl;
        map.put(randomUUID, new ScreenshotActionsProviderGoogle(context, smartActionsProvider, (UiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2.provideUiEventLoggerProvider.get(), (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$70) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.factoryProvider69.get(), (ThumbnailObserverGoogle) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesThumbnailObserverProvider.get(), (CoroutineScope) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2.applicationScopeProvider.get(), randomUUID, screenshotData, screenshotActionsController.actionExecutor, actionsCallback));
        saveScreenshotInBackground(screenshotData, randomUUID, takeScreenshotExecutorImpl$sam$java_util_function_Consumer$0, new Consumer() { // from class: com.android.systemui.screenshot.LegacyScreenshotController$$ExternalSyntheticLambda5
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ScreenshotActionsProviderGoogle screenshotActionsProviderGoogle;
                LegacyScreenshotController legacyScreenshotController = LegacyScreenshotController.this;
                ScreenshotData screenshotData2 = screenshotData;
                UUID uuid = randomUUID;
                ImageExporter.Result result = (ImageExporter.Result) obj;
                legacyScreenshotController.getClass();
                Uri uri = result.uri;
                if (uri != null) {
                    ScreenshotSavedResult screenshotSavedResult = new ScreenshotSavedResult(uri, screenshotData2.getUserOrDefault(), result.timestamp);
                    ScreenshotActionsController screenshotActionsController2 = legacyScreenshotController.mActionsController;
                    if (!uuid.equals(screenshotActionsController2.currentScreenshotId) || (screenshotActionsProviderGoogle = (ScreenshotActionsProviderGoogle) screenshotActionsController2.actionProviders.get(uuid)) == null) {
                        return;
                    }
                    screenshotActionsProviderGoogle.setCompletedScreenshot(screenshotSavedResult);
                }
            }
        });
        int i2 = screenshotData.taskId;
        if (i2 >= 0) {
            AssistContentRequester.Callback callback = new AssistContentRequester.Callback() { // from class: com.android.systemui.screenshot.LegacyScreenshotController$$ExternalSyntheticLambda6
                @Override // com.android.systemui.screenshot.AssistContentRequester.Callback
                public final void onAssistContentAvailable(AssistContent assistContent) {
                    LegacyScreenshotController.this.mActionsController.onAssistContent(randomUUID, assistContent);
                }
            };
            AssistContentRequester assistContentRequester = this.mAssistContentRequester;
            assistContentRequester.mSystemInteractionExecutor.execute(new AssistContentRequester$$ExternalSyntheticLambda0(assistContentRequester, callback, i2));
        } else {
            screenshotActionsController.onAssistContent(randomUUID, null);
        }
        setWindowFocusable(true);
        screenshotShelfView.requestFocus();
        withWindowAttached(new LegacyScreenshotController$$ExternalSyntheticLambda16(this, randomUUID, screenshotData.userHandle, 0));
        View decorView = this.mWindow.getDecorView();
        if (decorView.isAttachedToWindow() || this.mAttachRequested) {
            z = true;
        } else {
            z = true;
            this.mAttachRequested = true;
            this.mWindowManager.addView(decorView, this.mWindowLayoutParams);
            decorView.requestApplyInsets();
            ViewGroup viewGroup = (ViewGroup) decorView.requireViewById(R.id.content);
            viewGroup.setClipChildren(false);
            viewGroup.setClipToPadding(false);
        }
        if (screenshotData.type == 3) {
            if (screenshotData.screenBounds != null) {
                Bitmap bitmap2 = screenshotData.bitmap;
                Insets insets = screenshotData.insets;
                int width = (bitmap2.getWidth() - insets.left) - insets.right;
                int height = (bitmap2.getHeight() - insets.top) - insets.bottom;
                if (height != 0 && width != 0 && bitmap2.getWidth() != 0 && bitmap2.getHeight() != 0 && Math.abs((width / height) - (r1.width() / r1.height())) < 0.1f) {
                    z2 = false;
                    final Runnable runnable = new Runnable() { // from class: com.android.systemui.screenshot.LegacyScreenshotController$$ExternalSyntheticLambda7
                        @Override // java.lang.Runnable
                        public final void run() {
                            LegacyScreenshotController legacyScreenshotController = LegacyScreenshotController.this;
                            ScreenshotData screenshotData2 = screenshotData;
                            boolean z3 = z2;
                            legacyScreenshotController.getClass();
                            Rect rect2 = screenshotData2.screenBounds;
                            final LegacyScreenshotController$$ExternalSyntheticLambda2 legacyScreenshotController$$ExternalSyntheticLambda2 = new LegacyScreenshotController$$ExternalSyntheticLambda2(legacyScreenshotController, screenshotData2, 1);
                            Animator animator = legacyScreenshotController.mScreenshotAnimation;
                            if (animator != null && animator.isRunning()) {
                                legacyScreenshotController.mScreenshotAnimation.cancel();
                            }
                            final ScreenshotShelfViewProxy screenshotShelfViewProxy2 = legacyScreenshotController.mViewProxy;
                            screenshotShelfViewProxy2.getClass();
                            Function0 function0 = new Function0() { // from class: com.android.systemui.screenshot.ScreenshotShelfViewProxy$createScreenshotDropInAnimation$entrance$1
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    ScreenshotViewModel screenshotViewModel2 = ScreenshotShelfViewProxy.this.viewModel;
                                    AnimationState animationState = AnimationState.ENTRANCE_REVEAL;
                                    StateFlowImpl stateFlowImpl = screenshotViewModel2._animationState;
                                    stateFlowImpl.getClass();
                                    stateFlowImpl.updateState(null, animationState);
                                    return Unit.INSTANCE;
                                }
                            };
                            final ScreenshotAnimationController screenshotAnimationController = screenshotShelfViewProxy2.animationController;
                            screenshotAnimationController.getClass();
                            AnimatorSet animatorSet = new AnimatorSet();
                            Rect rect3 = new Rect();
                            screenshotAnimationController.screenshotPreview.getHitRect(rect3);
                            final float width2 = rect2.width() / rect3.width();
                            final float height2 = rect2.height() / rect3.height();
                            final PointF pointF = new PointF(rect2.exactCenterX(), rect2.exactCenterY());
                            final PointF pointF2 = new PointF(rect3.exactCenterX(), rect3.exactCenterY());
                            ValueAnimator ofFloat = ValueAnimator.ofFloat(pointF.y, pointF2.y);
                            ofFloat.setDuration(500L);
                            ofFloat.setInterpolator(screenshotAnimationController.fastOutSlowIn);
                            ofFloat.addUpdateListener(new ScreenshotAnimationController$getActionsAnimator$1(screenshotAnimationController, 4));
                            ValueAnimator ofFloat2 = ValueAnimator.ofFloat(0.0f, 1.0f);
                            ofFloat2.setDuration(234L);
                            ofFloat2.setInterpolator(screenshotAnimationController.fastOutSlowIn);
                            ofFloat2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.screenshot.ui.ScreenshotAnimationController$getPreviewAnimator$2
                                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                                    float animatedFraction = valueAnimator.getAnimatedFraction();
                                    ScreenshotAnimationController.this.screenshotPreview.setScaleX(MathUtils.lerp(width2, 1.0f, animatedFraction));
                                    ScreenshotAnimationController.this.screenshotPreview.setScaleY(MathUtils.lerp(height2, 1.0f, animatedFraction));
                                    ScreenshotAnimationController.this.screenshotPreview.setX(MathUtils.lerp(pointF.x, pointF2.x, animatedFraction) - (ScreenshotAnimationController.this.screenshotPreview.getWidth() / 2.0f));
                                }
                            });
                            AnimatorSet animatorSet2 = new AnimatorSet();
                            animatorSet2.play(ofFloat2).with(ofFloat);
                            animatorSet2.addListener(new Animator.AnimatorListener() { // from class: com.android.systemui.screenshot.ui.ScreenshotAnimationController$getPreviewAnimator$$inlined$doOnEnd$1
                                @Override // android.animation.Animator.AnimatorListener
                                public final void onAnimationEnd(Animator animator2) {
                                    ScreenshotAnimationController.this.screenshotPreview.setScaleX(1.0f);
                                    ScreenshotAnimationController.this.screenshotPreview.setScaleY(1.0f);
                                    ScreenshotAnimationController.this.screenshotPreview.setX(pointF2.x - (r4.getWidth() / 2.0f));
                                    ScreenshotAnimationController.this.screenshotPreview.setY(pointF2.y - (r4.getHeight() / 2.0f));
                                }

                                @Override // android.animation.Animator.AnimatorListener
                                public final void onAnimationCancel(Animator animator2) {
                                }

                                @Override // android.animation.Animator.AnimatorListener
                                public final void onAnimationRepeat(Animator animator2) {
                                }

                                @Override // android.animation.Animator.AnimatorListener
                                public final void onAnimationStart(Animator animator2) {
                                }
                            });
                            animatorSet2.addListener(new ScreenshotAnimationController$getEntranceAnimation$$inlined$doOnEnd$1(screenshotAnimationController, 5));
                            if (z3) {
                                ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(screenshotAnimationController.flashView, "alpha", 0.0f, 1.0f);
                                ofFloat3.setDuration(133L);
                                ofFloat3.setInterpolator(screenshotAnimationController.fastOutSlowIn);
                                ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(screenshotAnimationController.flashView, "alpha", 1.0f, 0.0f);
                                ofFloat4.setDuration(217L);
                                ofFloat4.setInterpolator(screenshotAnimationController.fastOutSlowIn);
                                ofFloat3.addListener(new ScreenshotAnimationController$getEntranceAnimation$$inlined$doOnEnd$1(screenshotAnimationController, 2));
                                ofFloat4.addListener(new ScreenshotAnimationController$getEntranceAnimation$$inlined$doOnEnd$1(screenshotAnimationController, 0));
                                animatorSet.play(ofFloat4).after(ofFloat3);
                                animatorSet.play(animatorSet2).with(ofFloat4);
                                animatorSet.addListener(new ScreenshotAnimationController$getEntranceAnimation$$inlined$doOnEnd$1(screenshotAnimationController, 3));
                            }
                            float height3 = screenshotAnimationController.view.getHeight() - screenshotAnimationController.actionContainer.getTop();
                            ValueAnimator ofFloat5 = ValueAnimator.ofFloat(height3, 0.0f);
                            ofFloat5.setDuration(500L);
                            ofFloat5.setInterpolator(screenshotAnimationController.fastOutSlowIn);
                            ofFloat5.addUpdateListener(new ScreenshotAnimationController$getActionsAnimator$1(screenshotAnimationController, 0));
                            screenshotAnimationController.actionContainer.setTranslationY(height3);
                            animatorSet.play(ofFloat5).with(animatorSet2);
                            ValueAnimator ofFloat6 = ValueAnimator.ofFloat(0.0f);
                            ofFloat6.setDuration(0L);
                            ofFloat6.setStartDelay(200L);
                            ofFloat6.addListener(new ScreenshotAnimationController$runLongScreenshotTransition$$inlined$doOnEnd$1(1, function0));
                            animatorSet.play(ofFloat6).with(ofFloat5);
                            ValueAnimator ofFloat7 = ValueAnimator.ofFloat(0.0f, 1.0f);
                            ofFloat7.addUpdateListener(new ScreenshotAnimationController$getActionsAnimator$1(screenshotAnimationController, 3));
                            animatorSet.play(ofFloat7).after(animatorSet2);
                            animatorSet.addListener(new ScreenshotAnimationController$getEntranceAnimation$$inlined$doOnEnd$1(screenshotAnimationController, 4));
                            animatorSet.addListener(new ScreenshotAnimationController$getEntranceAnimation$$inlined$doOnEnd$1(screenshotAnimationController, 1));
                            screenshotAnimationController.animator = animatorSet;
                            animatorSet.addListener(new ScreenshotShelfViewProxy$startLongScreenshotTransition$$inlined$doOnEnd$1(screenshotShelfViewProxy2, 2));
                            animatorSet.addListener(new ScreenshotShelfViewProxy$startLongScreenshotTransition$$inlined$doOnEnd$1(screenshotShelfViewProxy2, 1));
                            legacyScreenshotController.mScreenshotAnimation = animatorSet;
                            animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.screenshot.LegacyScreenshotController.5
                                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                public final void onAnimationEnd(Animator animator2) {
                                    super.onAnimationEnd(animator2);
                                    LegacyScreenshotController$$ExternalSyntheticLambda2.this.run();
                                }
                            });
                            ScreenshotSoundControllerImpl screenshotSoundControllerImpl2 = legacyScreenshotController.mScreenshotSoundController;
                            if (screenshotSoundControllerImpl2 != null) {
                                BuildersKt.launch$default(screenshotSoundControllerImpl2.coroutineScope, null, null, new ScreenshotSoundControllerImpl$playScreenshotSoundAsync$1(screenshotSoundControllerImpl2, null), 3);
                            }
                            legacyScreenshotController.mScreenshotAnimation.start();
                        }
                    };
                    screenshotShelfView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.android.systemui.screenshot.ScreenshotShelfViewProxy$prepareEntranceAnimation$1
                        @Override // android.view.ViewTreeObserver.OnPreDrawListener
                        public final boolean onPreDraw() {
                            Reflection.getOrCreateKotlinClass(ScreenshotShelfViewProxy$prepareEntranceAnimation$1.class).getSimpleName();
                            ScreenshotShelfViewProxy.this.view.getViewTreeObserver().removeOnPreDrawListener(this);
                            runnable.run();
                            return true;
                        }
                    });
                    screenshotShelfViewProxy.getClass();
                    Bitmap bitmap3 = screenshotData.bitmap;
                    screenshotViewModel = screenshotShelfViewProxy.viewModel;
                    screenshotViewModel._preview.setValue(bitmap3);
                    drawable = AppCompatResources.getDrawable(com.android.wm.shell.R.drawable.overlay_badge_background, screenshotShelfViewProxy.context);
                    userHandle = screenshotData.userHandle;
                    if (drawable != null && userHandle != null) {
                        screenshotViewModel._badge.setValue(screenshotShelfViewProxy.context.getPackageManager().getUserBadgedIcon(drawable, userHandle));
                    }
                    this.mWindow.getDecorView().setOnApplyWindowInsetsListener(new LegacyScreenshotController$$ExternalSyntheticLambda8());
                }
            }
            screenshotData.insets = Insets.NONE;
            screenshotData.screenBounds = new Rect(0, 0, screenshotData.bitmap.getWidth(), screenshotData.bitmap.getHeight());
        }
        z2 = z;
        final Runnable runnable2 = new Runnable() { // from class: com.android.systemui.screenshot.LegacyScreenshotController$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                LegacyScreenshotController legacyScreenshotController = LegacyScreenshotController.this;
                ScreenshotData screenshotData2 = screenshotData;
                boolean z3 = z2;
                legacyScreenshotController.getClass();
                Rect rect2 = screenshotData2.screenBounds;
                final LegacyScreenshotController$$ExternalSyntheticLambda2 legacyScreenshotController$$ExternalSyntheticLambda2 = new LegacyScreenshotController$$ExternalSyntheticLambda2(legacyScreenshotController, screenshotData2, 1);
                Animator animator = legacyScreenshotController.mScreenshotAnimation;
                if (animator != null && animator.isRunning()) {
                    legacyScreenshotController.mScreenshotAnimation.cancel();
                }
                final ScreenshotShelfViewProxy screenshotShelfViewProxy2 = legacyScreenshotController.mViewProxy;
                screenshotShelfViewProxy2.getClass();
                Function0 function0 = new Function0() { // from class: com.android.systemui.screenshot.ScreenshotShelfViewProxy$createScreenshotDropInAnimation$entrance$1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        ScreenshotViewModel screenshotViewModel2 = ScreenshotShelfViewProxy.this.viewModel;
                        AnimationState animationState = AnimationState.ENTRANCE_REVEAL;
                        StateFlowImpl stateFlowImpl = screenshotViewModel2._animationState;
                        stateFlowImpl.getClass();
                        stateFlowImpl.updateState(null, animationState);
                        return Unit.INSTANCE;
                    }
                };
                final ScreenshotAnimationController screenshotAnimationController = screenshotShelfViewProxy2.animationController;
                screenshotAnimationController.getClass();
                AnimatorSet animatorSet = new AnimatorSet();
                Rect rect3 = new Rect();
                screenshotAnimationController.screenshotPreview.getHitRect(rect3);
                final float width2 = rect2.width() / rect3.width();
                final float height2 = rect2.height() / rect3.height();
                final PointF pointF = new PointF(rect2.exactCenterX(), rect2.exactCenterY());
                final PointF pointF2 = new PointF(rect3.exactCenterX(), rect3.exactCenterY());
                ValueAnimator ofFloat = ValueAnimator.ofFloat(pointF.y, pointF2.y);
                ofFloat.setDuration(500L);
                ofFloat.setInterpolator(screenshotAnimationController.fastOutSlowIn);
                ofFloat.addUpdateListener(new ScreenshotAnimationController$getActionsAnimator$1(screenshotAnimationController, 4));
                ValueAnimator ofFloat2 = ValueAnimator.ofFloat(0.0f, 1.0f);
                ofFloat2.setDuration(234L);
                ofFloat2.setInterpolator(screenshotAnimationController.fastOutSlowIn);
                ofFloat2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.screenshot.ui.ScreenshotAnimationController$getPreviewAnimator$2
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                        float animatedFraction = valueAnimator.getAnimatedFraction();
                        ScreenshotAnimationController.this.screenshotPreview.setScaleX(MathUtils.lerp(width2, 1.0f, animatedFraction));
                        ScreenshotAnimationController.this.screenshotPreview.setScaleY(MathUtils.lerp(height2, 1.0f, animatedFraction));
                        ScreenshotAnimationController.this.screenshotPreview.setX(MathUtils.lerp(pointF.x, pointF2.x, animatedFraction) - (ScreenshotAnimationController.this.screenshotPreview.getWidth() / 2.0f));
                    }
                });
                AnimatorSet animatorSet2 = new AnimatorSet();
                animatorSet2.play(ofFloat2).with(ofFloat);
                animatorSet2.addListener(new Animator.AnimatorListener() { // from class: com.android.systemui.screenshot.ui.ScreenshotAnimationController$getPreviewAnimator$$inlined$doOnEnd$1
                    @Override // android.animation.Animator.AnimatorListener
                    public final void onAnimationEnd(Animator animator2) {
                        ScreenshotAnimationController.this.screenshotPreview.setScaleX(1.0f);
                        ScreenshotAnimationController.this.screenshotPreview.setScaleY(1.0f);
                        ScreenshotAnimationController.this.screenshotPreview.setX(pointF2.x - (r4.getWidth() / 2.0f));
                        ScreenshotAnimationController.this.screenshotPreview.setY(pointF2.y - (r4.getHeight() / 2.0f));
                    }

                    @Override // android.animation.Animator.AnimatorListener
                    public final void onAnimationCancel(Animator animator2) {
                    }

                    @Override // android.animation.Animator.AnimatorListener
                    public final void onAnimationRepeat(Animator animator2) {
                    }

                    @Override // android.animation.Animator.AnimatorListener
                    public final void onAnimationStart(Animator animator2) {
                    }
                });
                animatorSet2.addListener(new ScreenshotAnimationController$getEntranceAnimation$$inlined$doOnEnd$1(screenshotAnimationController, 5));
                if (z3) {
                    ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(screenshotAnimationController.flashView, "alpha", 0.0f, 1.0f);
                    ofFloat3.setDuration(133L);
                    ofFloat3.setInterpolator(screenshotAnimationController.fastOutSlowIn);
                    ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(screenshotAnimationController.flashView, "alpha", 1.0f, 0.0f);
                    ofFloat4.setDuration(217L);
                    ofFloat4.setInterpolator(screenshotAnimationController.fastOutSlowIn);
                    ofFloat3.addListener(new ScreenshotAnimationController$getEntranceAnimation$$inlined$doOnEnd$1(screenshotAnimationController, 2));
                    ofFloat4.addListener(new ScreenshotAnimationController$getEntranceAnimation$$inlined$doOnEnd$1(screenshotAnimationController, 0));
                    animatorSet.play(ofFloat4).after(ofFloat3);
                    animatorSet.play(animatorSet2).with(ofFloat4);
                    animatorSet.addListener(new ScreenshotAnimationController$getEntranceAnimation$$inlined$doOnEnd$1(screenshotAnimationController, 3));
                }
                float height3 = screenshotAnimationController.view.getHeight() - screenshotAnimationController.actionContainer.getTop();
                ValueAnimator ofFloat5 = ValueAnimator.ofFloat(height3, 0.0f);
                ofFloat5.setDuration(500L);
                ofFloat5.setInterpolator(screenshotAnimationController.fastOutSlowIn);
                ofFloat5.addUpdateListener(new ScreenshotAnimationController$getActionsAnimator$1(screenshotAnimationController, 0));
                screenshotAnimationController.actionContainer.setTranslationY(height3);
                animatorSet.play(ofFloat5).with(animatorSet2);
                ValueAnimator ofFloat6 = ValueAnimator.ofFloat(0.0f);
                ofFloat6.setDuration(0L);
                ofFloat6.setStartDelay(200L);
                ofFloat6.addListener(new ScreenshotAnimationController$runLongScreenshotTransition$$inlined$doOnEnd$1(1, function0));
                animatorSet.play(ofFloat6).with(ofFloat5);
                ValueAnimator ofFloat7 = ValueAnimator.ofFloat(0.0f, 1.0f);
                ofFloat7.addUpdateListener(new ScreenshotAnimationController$getActionsAnimator$1(screenshotAnimationController, 3));
                animatorSet.play(ofFloat7).after(animatorSet2);
                animatorSet.addListener(new ScreenshotAnimationController$getEntranceAnimation$$inlined$doOnEnd$1(screenshotAnimationController, 4));
                animatorSet.addListener(new ScreenshotAnimationController$getEntranceAnimation$$inlined$doOnEnd$1(screenshotAnimationController, 1));
                screenshotAnimationController.animator = animatorSet;
                animatorSet.addListener(new ScreenshotShelfViewProxy$startLongScreenshotTransition$$inlined$doOnEnd$1(screenshotShelfViewProxy2, 2));
                animatorSet.addListener(new ScreenshotShelfViewProxy$startLongScreenshotTransition$$inlined$doOnEnd$1(screenshotShelfViewProxy2, 1));
                legacyScreenshotController.mScreenshotAnimation = animatorSet;
                animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.screenshot.LegacyScreenshotController.5
                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public final void onAnimationEnd(Animator animator2) {
                        super.onAnimationEnd(animator2);
                        LegacyScreenshotController$$ExternalSyntheticLambda2.this.run();
                    }
                });
                ScreenshotSoundControllerImpl screenshotSoundControllerImpl2 = legacyScreenshotController.mScreenshotSoundController;
                if (screenshotSoundControllerImpl2 != null) {
                    BuildersKt.launch$default(screenshotSoundControllerImpl2.coroutineScope, null, null, new ScreenshotSoundControllerImpl$playScreenshotSoundAsync$1(screenshotSoundControllerImpl2, null), 3);
                }
                legacyScreenshotController.mScreenshotAnimation.start();
            }
        };
        screenshotShelfView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.android.systemui.screenshot.ScreenshotShelfViewProxy$prepareEntranceAnimation$1
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public final boolean onPreDraw() {
                Reflection.getOrCreateKotlinClass(ScreenshotShelfViewProxy$prepareEntranceAnimation$1.class).getSimpleName();
                ScreenshotShelfViewProxy.this.view.getViewTreeObserver().removeOnPreDrawListener(this);
                runnable2.run();
                return true;
            }
        });
        screenshotShelfViewProxy.getClass();
        Bitmap bitmap32 = screenshotData.bitmap;
        screenshotViewModel = screenshotShelfViewProxy.viewModel;
        screenshotViewModel._preview.setValue(bitmap32);
        drawable = AppCompatResources.getDrawable(com.android.wm.shell.R.drawable.overlay_badge_background, screenshotShelfViewProxy.context);
        userHandle = screenshotData.userHandle;
        if (drawable != null) {
            screenshotViewModel._badge.setValue(screenshotShelfViewProxy.context.getPackageManager().getUserBadgedIcon(drawable, userHandle));
        }
        this.mWindow.getDecorView().setOnApplyWindowInsetsListener(new LegacyScreenshotController$$ExternalSyntheticLambda8());
    }

    public final void logScreenshotResultStatus(Uri uri, UserHandle userHandle) {
        if (uri == null) {
            this.mUiEventLogger.log(ScreenshotEvent.SCREENSHOT_NOT_SAVED, 0, this.mPackageName);
            this.mNotificationsController.notifyScreenshotError(com.android.wm.shell.R.string.screenshot_failed_to_save_text);
        } else {
            this.mUiEventLogger.log(ScreenshotEvent.SCREENSHOT_SAVED, 0, this.mPackageName);
            if (this.mUserManager.isManagedProfile(userHandle.getIdentifier())) {
                this.mUiEventLogger.log(ScreenshotEvent.SCREENSHOT_SAVED_TO_WORK_PROFILE, 0, this.mPackageName);
            }
        }
    }

    public final void removeWindow() {
        View peekDecorView = this.mWindow.peekDecorView();
        if (peekDecorView != null && peekDecorView.isAttachedToWindow()) {
            this.mWindowManager.removeViewImmediate(peekDecorView);
            this.mDetachRequested = false;
        }
        if (this.mAttachRequested && !this.mDetachRequested) {
            this.mDetachRequested = true;
            withWindowAttached(new LegacyScreenshotController$$ExternalSyntheticLambda9(1, this));
        }
        this.mViewProxy.stopInputListening();
    }

    public final void saveScreenshotInBackground(final ScreenshotData screenshotData, UUID uuid, final TakeScreenshotExecutorImpl$sam$java_util_function_Consumer$0 takeScreenshotExecutorImpl$sam$java_util_function_Consumer$0, final Consumer consumer) {
        final CallbackToFutureAdapter.SafeFuture export = this.mImageExporter.export(this.mBgExecutor, uuid, screenshotData.bitmap, screenshotData.getUserOrDefault(), this.mDisplay.getDisplayId());
        export.delegate.addListener(new Runnable() { // from class: com.android.systemui.screenshot.LegacyScreenshotController$$ExternalSyntheticLambda13
            @Override // java.lang.Runnable
            public final void run() {
                LegacyScreenshotController legacyScreenshotController = LegacyScreenshotController.this;
                CallbackToFutureAdapter.SafeFuture safeFuture = export;
                ScreenshotData screenshotData2 = screenshotData;
                Consumer consumer2 = consumer;
                TakeScreenshotExecutorImpl$sam$java_util_function_Consumer$0 takeScreenshotExecutorImpl$sam$java_util_function_Consumer$02 = takeScreenshotExecutorImpl$sam$java_util_function_Consumer$0;
                legacyScreenshotController.getClass();
                try {
                    ImageExporter.Result result = (ImageExporter.Result) safeFuture.delegate.get();
                    Log.d("Screenshot", "Saved screenshot: " + result);
                    legacyScreenshotController.logScreenshotResultStatus(result.uri, screenshotData2.userHandle);
                    consumer2.accept(result);
                    takeScreenshotExecutorImpl$sam$java_util_function_Consumer$02.accept(result.uri);
                } catch (Exception e) {
                    Log.d("Screenshot", "Failed to store screenshot", e);
                    takeScreenshotExecutorImpl$sam$java_util_function_Consumer$02.accept(null);
                }
            }
        }, this.mMainExecutor);
    }

    public final void setWindowFocusable(boolean z) {
        View peekDecorView;
        WindowManager.LayoutParams layoutParams = this.mWindowLayoutParams;
        int i = layoutParams.flags;
        if (z) {
            layoutParams.flags = i & (-9);
        } else {
            layoutParams.flags = i | 8;
        }
        if (layoutParams.flags == i || (peekDecorView = this.mWindow.peekDecorView()) == null || !peekDecorView.isAttachedToWindow()) {
            return;
        }
        this.mWindowManager.updateViewLayout(peekDecorView, this.mWindowLayoutParams);
    }

    public final void withWindowAttached(final Runnable runnable) {
        final View decorView = this.mWindow.getDecorView();
        if (decorView.isAttachedToWindow()) {
            runnable.run();
        } else {
            decorView.getViewTreeObserver().addOnWindowAttachListener(new ViewTreeObserver.OnWindowAttachListener() { // from class: com.android.systemui.screenshot.LegacyScreenshotController.4
                @Override // android.view.ViewTreeObserver.OnWindowAttachListener
                public final void onWindowAttached() {
                    LegacyScreenshotController.this.mAttachRequested = false;
                    decorView.getViewTreeObserver().removeOnWindowAttachListener(this);
                    runnable.run();
                }

                @Override // android.view.ViewTreeObserver.OnWindowAttachListener
                public final void onWindowDetached() {
                }
            });
        }
    }
}

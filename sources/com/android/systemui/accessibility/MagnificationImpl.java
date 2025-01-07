package com.android.systemui.accessibility;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.SparseArray;
import android.view.Display;
import android.view.IWindowManager;
import android.view.SurfaceControl;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.AccelerateInterpolator;
import com.android.app.viewcapture.ViewCaptureAwareWindowManager;
import com.android.internal.graphics.SfVsyncFrameCallbackProvider;
import com.android.keyguard.KeyguardClockSwitchController$$ExternalSyntheticOutline0;
import com.android.keyguard.LegacyLockIconViewController$$ExternalSyntheticOutline0;
import com.android.systemui.CoreStartable;
import com.android.systemui.accessibility.MagnificationSettingsController;
import com.android.systemui.model.SysUiState;
import com.android.systemui.recents.OverviewProxyService;
import com.android.systemui.settings.DisplayTracker;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.util.settings.SecureSettings;
import com.android.wm.shell.R;
import java.io.PrintWriter;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class MagnificationImpl implements CommandQueue.Callbacks, CoreStartable {
    static final int DELAY_SHOW_MAGNIFICATION_TIMEOUT_MS = 300;
    public final AccessibilityLogger mA11yLogger;
    public final AccessibilityManager mAccessibilityManager;
    public final CommandQueue mCommandQueue;
    public final DisplayTracker mDisplayTracker;
    DisplayIdIndexSupplier mFullscreenMagnificationControllerSupplier;
    public final AnonymousClass1 mHandler;
    public MagnificationConnectionImpl mMagnificationConnectionImpl;
    final MagnificationSettingsController.Callback mMagnificationSettingsControllerCallback;
    DisplayIdIndexSupplier mMagnificationSettingsSupplier;
    public final ModeSwitchesController mModeSwitchesController;
    public final OverviewProxyService mOverviewProxyService;
    public final SysUiState mSysUiState;
    SparseArray mUsersScales;
    DisplayIdIndexSupplier mWindowMagnificationControllerSupplier;
    final WindowMagnifierCallback mWindowMagnifierCallback;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.accessibility.MagnificationImpl$3, reason: invalid class name */
    public final class AnonymousClass3 implements WindowMagnifierCallback, MagnificationSettingsController.Callback {
        public /* synthetic */ AnonymousClass3() {
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class FullscreenMagnificationControllerSupplier extends DisplayIdIndexSupplier {
        public final Context mContext;
        public final DisplayManager mDisplayManager;
        public final Executor mExecutor;
        public final AnonymousClass1 mHandler;
        public final IWindowManager mIWindowManager;

        public FullscreenMagnificationControllerSupplier(Context context, DisplayManager displayManager, AnonymousClass1 anonymousClass1, Executor executor, IWindowManager iWindowManager) {
            super(displayManager);
            this.mContext = context;
            this.mHandler = anonymousClass1;
            this.mExecutor = executor;
            this.mDisplayManager = displayManager;
            this.mIWindowManager = iWindowManager;
        }

        @Override // com.android.systemui.accessibility.DisplayIdIndexSupplier
        public final Object createInstance(Display display) {
            Context createWindowContext = this.mContext.createWindowContext(display, 2032, null);
            MagnificationImpl$WindowMagnificationControllerSupplier$$ExternalSyntheticLambda0 magnificationImpl$WindowMagnificationControllerSupplier$$ExternalSyntheticLambda0 = new MagnificationImpl$WindowMagnificationControllerSupplier$$ExternalSyntheticLambda0(this, 1);
            createWindowContext.setTheme(R.style.Theme_SystemUI);
            return new FullscreenMagnificationController(createWindowContext, this.mHandler, this.mExecutor, this.mDisplayManager, (AccessibilityManager) createWindowContext.getSystemService(AccessibilityManager.class), (WindowManager) createWindowContext.getSystemService(WindowManager.class), this.mIWindowManager, magnificationImpl$WindowMagnificationControllerSupplier$$ExternalSyntheticLambda0, new SurfaceControl.Transaction(), null);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SettingsSupplier extends DisplayIdIndexSupplier {
        public final Context mContext;
        public final SecureSettings mSecureSettings;
        public final AnonymousClass3 mSettingsControllerCallback;
        public final ViewCaptureAwareWindowManager mViewCaptureAwareWindowManager;

        public SettingsSupplier(Context context, AnonymousClass3 anonymousClass3, DisplayManager displayManager, SecureSettings secureSettings, ViewCaptureAwareWindowManager viewCaptureAwareWindowManager) {
            super(displayManager);
            this.mContext = context;
            this.mSettingsControllerCallback = anonymousClass3;
            this.mSecureSettings = secureSettings;
            this.mViewCaptureAwareWindowManager = viewCaptureAwareWindowManager;
        }

        @Override // com.android.systemui.accessibility.DisplayIdIndexSupplier
        public final Object createInstance(Display display) {
            Context createWindowContext = this.mContext.createWindowContext(display, 2032, null);
            createWindowContext.setTheme(R.style.Theme_SystemUI);
            return new MagnificationSettingsController(createWindowContext, new SfVsyncFrameCallbackProvider(), this.mSettingsControllerCallback, this.mSecureSettings, null, this.mViewCaptureAwareWindowManager);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class WindowMagnificationControllerSupplier extends DisplayIdIndexSupplier {
        public final Context mContext;
        public final AnonymousClass1 mHandler;
        public final SecureSettings mSecureSettings;
        public final SysUiState mSysUiState;
        public final ViewCaptureAwareWindowManager mViewCaptureAwareWindowManager;
        public final AnonymousClass3 mWindowMagnifierCallback;

        public WindowMagnificationControllerSupplier(Context context, AnonymousClass1 anonymousClass1, AnonymousClass3 anonymousClass3, DisplayManager displayManager, SysUiState sysUiState, SecureSettings secureSettings, ViewCaptureAwareWindowManager viewCaptureAwareWindowManager) {
            super(displayManager);
            this.mContext = context;
            this.mHandler = anonymousClass1;
            this.mWindowMagnifierCallback = anonymousClass3;
            this.mSysUiState = sysUiState;
            this.mSecureSettings = secureSettings;
            this.mViewCaptureAwareWindowManager = viewCaptureAwareWindowManager;
        }

        @Override // com.android.systemui.accessibility.DisplayIdIndexSupplier
        public final Object createInstance(Display display) {
            Context createWindowContext = this.mContext.createWindowContext(display, 2032, null);
            createWindowContext.setTheme(R.style.Theme_SystemUI);
            MagnificationImpl$WindowMagnificationControllerSupplier$$ExternalSyntheticLambda0 magnificationImpl$WindowMagnificationControllerSupplier$$ExternalSyntheticLambda0 = new MagnificationImpl$WindowMagnificationControllerSupplier$$ExternalSyntheticLambda0(this, 0);
            Resources resources = createWindowContext.getResources();
            ValueAnimator valueAnimator = new ValueAnimator();
            valueAnimator.setDuration(resources.getInteger(android.R.integer.config_longAnimTime));
            valueAnimator.setInterpolator(new AccelerateInterpolator(2.5f));
            valueAnimator.setFloatValues(0.0f, 1.0f);
            return new WindowMagnificationController(createWindowContext, this.mHandler, new WindowMagnificationAnimationController(createWindowContext, valueAnimator), new SurfaceControl.Transaction(), this.mWindowMagnifierCallback, this.mSysUiState, this.mSecureSettings, magnificationImpl$WindowMagnificationControllerSupplier$$ExternalSyntheticLambda0, new SfVsyncFrameCallbackProvider(), new MagnificationImpl$WindowMagnificationControllerSupplier$$ExternalSyntheticLambda1(), this.mViewCaptureAwareWindowManager);
        }
    }

    public MagnificationImpl(Context context, Handler handler, Executor executor, CommandQueue commandQueue, ModeSwitchesController modeSwitchesController, SysUiState sysUiState, OverviewProxyService overviewProxyService, SecureSettings secureSettings, DisplayTracker displayTracker, DisplayManager displayManager, AccessibilityLogger accessibilityLogger, IWindowManager iWindowManager, AccessibilityManager accessibilityManager, ViewCaptureAwareWindowManager viewCaptureAwareWindowManager) {
        this(context, handler.getLooper(), executor, commandQueue, modeSwitchesController, sysUiState, overviewProxyService, secureSettings, displayTracker, displayManager, accessibilityLogger, iWindowManager, accessibilityManager, viewCaptureAwareWindowManager);
    }

    @Override // com.android.systemui.CoreStartable, com.android.systemui.Dumpable
    public final void dump(final PrintWriter printWriter, String[] strArr) {
        printWriter.println("Magnification");
        DisplayIdIndexSupplier displayIdIndexSupplier = this.mWindowMagnificationControllerSupplier;
        Consumer consumer = new Consumer() { // from class: com.android.systemui.accessibility.MagnificationImpl$$ExternalSyntheticLambda1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                PrintWriter printWriter2 = printWriter;
                WindowMagnificationController windowMagnificationController = (WindowMagnificationController) obj;
                printWriter2.println("WindowMagnificationController (displayId=" + windowMagnificationController.mDisplayId + "):");
                StringBuilder m = LegacyLockIconViewController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(new StringBuilder("      mOverlapWithGestureInsets:"), windowMagnificationController.mOverlapWithGestureInsets, printWriter2, "      mScale:"), windowMagnificationController.mScale, printWriter2, "      mWindowBounds:");
                m.append(windowMagnificationController.mWindowBounds);
                printWriter2.println(m.toString());
                StringBuilder sb = new StringBuilder("      mMirrorViewBounds:");
                sb.append(windowMagnificationController.isActivated() ? windowMagnificationController.mMirrorViewBounds : "empty");
                printWriter2.println(sb.toString());
                StringBuilder sb2 = new StringBuilder("      mMagnificationFrameBoundary:");
                sb2.append(windowMagnificationController.isActivated() ? windowMagnificationController.mMagnificationFrameBoundary : "empty");
                printWriter2.println(sb2.toString());
                StringBuilder sb3 = new StringBuilder("      mMagnificationFrame:");
                sb3.append(windowMagnificationController.isActivated() ? windowMagnificationController.mMagnificationFrame : "empty");
                printWriter2.println(sb3.toString());
                StringBuilder sb4 = new StringBuilder("      mSourceBounds:");
                sb4.append(windowMagnificationController.mSourceBounds.isEmpty() ? "empty" : windowMagnificationController.mSourceBounds);
                printWriter2.println(sb4.toString());
                StringBuilder m2 = LegacyLockIconViewController$$ExternalSyntheticOutline0.m(LegacyLockIconViewController$$ExternalSyntheticOutline0.m(new StringBuilder("      mSystemGestureTop:"), windowMagnificationController.mSystemGestureTop, printWriter2, "      mMagnificationFrameOffsetX:"), windowMagnificationController.mMagnificationFrameOffsetX, printWriter2, "      mMagnificationFrameOffsetY:");
                m2.append(windowMagnificationController.mMagnificationFrameOffsetY);
                printWriter2.println(m2.toString());
            }
        };
        for (int i = 0; i < displayIdIndexSupplier.mSparseArray.size(); i++) {
            consumer.accept(displayIdIndexSupplier.mSparseArray.valueAt(i));
        }
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void requestMagnificationConnection(boolean z) {
        if (!z) {
            this.mAccessibilityManager.setMagnificationConnection(null);
            return;
        }
        if (this.mMagnificationConnectionImpl == null) {
            this.mMagnificationConnectionImpl = new MagnificationConnectionImpl(this, this.mHandler);
        }
        this.mAccessibilityManager.setMagnificationConnection(this.mMagnificationConnectionImpl);
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        this.mCommandQueue.addCallback((CommandQueue.Callbacks) this);
        this.mOverviewProxyService.addCallback(new OverviewProxyService.OverviewProxyListener() { // from class: com.android.systemui.accessibility.MagnificationImpl.2
            @Override // com.android.systemui.recents.OverviewProxyService.OverviewProxyListener
            public final void onConnectionChanged(boolean z) {
                if (z) {
                    MagnificationImpl magnificationImpl = MagnificationImpl.this;
                    DisplayIdIndexSupplier displayIdIndexSupplier = magnificationImpl.mWindowMagnificationControllerSupplier;
                    magnificationImpl.mDisplayTracker.getClass();
                    WindowMagnificationController windowMagnificationController = (WindowMagnificationController) displayIdIndexSupplier.mSparseArray.get(0);
                    if (windowMagnificationController != null) {
                        windowMagnificationController.updateSysUIState(true);
                        return;
                    }
                    SysUiState sysUiState = magnificationImpl.mSysUiState;
                    sysUiState.setFlag(524288L, false);
                    sysUiState.commitUpdate(0);
                }
            }
        });
    }

    public final void toggleSettingsPanelVisibility(int i) {
        MagnificationSettingsController magnificationSettingsController = (MagnificationSettingsController) this.mMagnificationSettingsSupplier.get(i);
        if (magnificationSettingsController != null) {
            if (!magnificationSettingsController.mWindowMagnificationSettings.mIsVisible) {
                magnificationSettingsController.onConfigurationChanged(magnificationSettingsController.mContext.getResources().getConfiguration());
                magnificationSettingsController.mContext.registerComponentCallbacks(magnificationSettingsController);
            }
            WindowMagnificationSettings windowMagnificationSettings = magnificationSettingsController.mWindowMagnificationSettings;
            if (windowMagnificationSettings.mIsVisible) {
                windowMagnificationSettings.hideSettingPanel(true);
            } else {
                windowMagnificationSettings.showSettingPanel(true);
            }
        }
    }

    /* JADX WARN: Type inference failed for: r11v0, types: [com.android.systemui.accessibility.MagnificationImpl$1] */
    public MagnificationImpl(Context context, Looper looper, Executor executor, CommandQueue commandQueue, ModeSwitchesController modeSwitchesController, SysUiState sysUiState, OverviewProxyService overviewProxyService, SecureSettings secureSettings, DisplayTracker displayTracker, DisplayManager displayManager, AccessibilityLogger accessibilityLogger, IWindowManager iWindowManager, AccessibilityManager accessibilityManager, ViewCaptureAwareWindowManager viewCaptureAwareWindowManager) {
        this.mUsersScales = new SparseArray();
        AnonymousClass3 anonymousClass3 = new AnonymousClass3();
        this.mWindowMagnifierCallback = anonymousClass3;
        AnonymousClass3 anonymousClass32 = new AnonymousClass3();
        this.mMagnificationSettingsControllerCallback = anonymousClass32;
        ?? r11 = new Handler(looper) { // from class: com.android.systemui.accessibility.MagnificationImpl.1
            @Override // android.os.Handler
            public final void handleMessage(Message message) {
                MagnificationModeSwitch magnificationModeSwitch;
                if (message.what == 1) {
                    int i = message.arg1;
                    int i2 = message.arg2;
                    MagnificationImpl magnificationImpl = MagnificationImpl.this;
                    MagnificationSettingsController magnificationSettingsController = (MagnificationSettingsController) magnificationImpl.mMagnificationSettingsSupplier.get(i);
                    if ((magnificationSettingsController != null ? magnificationSettingsController.mWindowMagnificationSettings.mIsVisible : false) || (magnificationModeSwitch = (MagnificationModeSwitch) magnificationImpl.mModeSwitchesController.mSwitchSupplier.get(i)) == null) {
                        return;
                    }
                    magnificationModeSwitch.showButton(i2, true);
                }
            }
        };
        this.mHandler = r11;
        this.mAccessibilityManager = accessibilityManager;
        this.mCommandQueue = commandQueue;
        this.mModeSwitchesController = modeSwitchesController;
        this.mSysUiState = sysUiState;
        this.mOverviewProxyService = overviewProxyService;
        this.mDisplayTracker = displayTracker;
        this.mA11yLogger = accessibilityLogger;
        this.mWindowMagnificationControllerSupplier = new WindowMagnificationControllerSupplier(context, r11, anonymousClass3, displayManager, sysUiState, secureSettings, viewCaptureAwareWindowManager);
        this.mFullscreenMagnificationControllerSupplier = new FullscreenMagnificationControllerSupplier(context, displayManager, r11, executor, iWindowManager);
        this.mMagnificationSettingsSupplier = new SettingsSupplier(context, anonymousClass32, displayManager, secureSettings, viewCaptureAwareWindowManager);
        modeSwitchesController.mClickListenerDelegate = new MagnificationImpl$$ExternalSyntheticLambda0(this);
    }
}

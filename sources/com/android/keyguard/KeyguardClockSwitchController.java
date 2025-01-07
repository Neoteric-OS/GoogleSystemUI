package com.android.keyguard;

import android.database.ContentObserver;
import android.view.View;
import android.view.ViewGroup;
import com.android.systemui.Dumpable;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.flags.FeatureFlagsClassic;
import com.android.systemui.flags.Flags;
import com.android.systemui.flags.UnreleasedFlag;
import com.android.systemui.keyguard.KeyguardUnlockAnimationController;
import com.android.systemui.keyguard.domain.interactor.KeyguardClockInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import com.android.systemui.keyguard.ui.view.InWindowLauncherUnlockAnimationManager;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.plugins.clocks.ClockController;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.shared.clocks.ClockRegistry;
import com.android.systemui.statusbar.lockscreen.LockscreenSmartspaceController;
import com.android.systemui.statusbar.notification.icon.ui.viewbinder.NotificationIconContainerAlwaysOnDisplayViewBinder;
import com.android.systemui.util.Assert;
import com.android.systemui.util.ThreadAssert;
import com.android.systemui.util.ViewController;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.android.systemui.util.settings.SecureSettings;
import com.android.wm.shell.R;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardClockSwitchController extends ViewController implements Dumpable {
    public final Executor mBgExecutor;
    public final boolean mCanShowDoubleLineClock;
    public final AnonymousClass4 mClockChangedListener;
    public final ClockEventController mClockEventController;
    public final ClockRegistry mClockRegistry;
    public int mCurrentClockSize;
    public final AnonymousClass1 mDoubleLineClockObserver;
    public final DumpManager mDumpManager;
    public final FeatureFlagsClassic mFeatureFlags;
    public boolean mIsActiveDreamLockscreenHosted;
    final Consumer mIsActiveDreamLockscreenHostedCallback;
    public final KeyguardClockInteractor mKeyguardClockInteractor;
    public final KeyguardSliceViewController mKeyguardSliceViewController;
    public final KeyguardUnlockAnimationController mKeyguardUnlockAnimationController;
    public final AnonymousClass3 mKeyguardUnlockAnimationListener;
    public final LogBuffer mLogBuffer;
    public final SecureSettings mSecureSettings;
    public final AnonymousClass1 mShowWeatherObserver;
    public final boolean mShownOnSecondaryDisplay;
    public final LockscreenSmartspaceController mSmartspaceController;
    public ViewGroup mStatusArea;
    public final StatusBarStateController mStatusBarStateController;
    public final DelayableExecutor mUiExecutor;

    /* JADX WARN: Type inference failed for: r2v2, types: [com.android.keyguard.KeyguardClockSwitchController$1] */
    /* JADX WARN: Type inference failed for: r2v3, types: [com.android.keyguard.KeyguardClockSwitchController$1] */
    /* JADX WARN: Type inference failed for: r2v4, types: [com.android.keyguard.KeyguardClockSwitchController$3] */
    public KeyguardClockSwitchController(KeyguardClockSwitch keyguardClockSwitch, StatusBarStateController statusBarStateController, ClockRegistry clockRegistry, KeyguardSliceViewController keyguardSliceViewController, LockscreenSmartspaceController lockscreenSmartspaceController, NotificationIconContainerAlwaysOnDisplayViewBinder notificationIconContainerAlwaysOnDisplayViewBinder, KeyguardUnlockAnimationController keyguardUnlockAnimationController, SecureSettings secureSettings, DelayableExecutor delayableExecutor, Executor executor, DumpManager dumpManager, ClockEventController clockEventController, LogBuffer logBuffer, KeyguardInteractor keyguardInteractor, KeyguardClockInteractor keyguardClockInteractor, FeatureFlagsClassic featureFlagsClassic, InWindowLauncherUnlockAnimationManager inWindowLauncherUnlockAnimationManager) {
        super(keyguardClockSwitch);
        this.mCurrentClockSize = 1;
        final int i = 0;
        this.mShownOnSecondaryDisplay = false;
        this.mIsActiveDreamLockscreenHosted = false;
        this.mCanShowDoubleLineClock = true;
        this.mIsActiveDreamLockscreenHostedCallback = new Consumer() { // from class: com.android.keyguard.KeyguardClockSwitchController$$ExternalSyntheticLambda4
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                KeyguardClockSwitchController keyguardClockSwitchController = KeyguardClockSwitchController.this;
                Boolean bool = (Boolean) obj;
                if (keyguardClockSwitchController.mIsActiveDreamLockscreenHosted == bool.booleanValue()) {
                    return;
                }
                keyguardClockSwitchController.mIsActiveDreamLockscreenHosted = bool.booleanValue();
                if (keyguardClockSwitchController.mStatusArea != null) {
                    ((ExecutorImpl) keyguardClockSwitchController.mUiExecutor).execute(new KeyguardClockSwitchController$$ExternalSyntheticLambda3(keyguardClockSwitchController, 0));
                }
            }
        };
        this.mDoubleLineClockObserver = new ContentObserver(this) { // from class: com.android.keyguard.KeyguardClockSwitchController.1
            public final /* synthetic */ KeyguardClockSwitchController this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(null);
                this.this$0 = this;
            }

            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                switch (i) {
                    case 0:
                        this.this$0.getClass();
                        break;
                    default:
                        this.this$0.getClass();
                        break;
                }
            }
        };
        final int i2 = 1;
        this.mShowWeatherObserver = new ContentObserver(this) { // from class: com.android.keyguard.KeyguardClockSwitchController.1
            public final /* synthetic */ KeyguardClockSwitchController this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(null);
                this.this$0 = this;
            }

            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                switch (i2) {
                    case 0:
                        this.this$0.getClass();
                        break;
                    default:
                        this.this$0.getClass();
                        break;
                }
            }
        };
        this.mKeyguardUnlockAnimationListener = new KeyguardUnlockAnimationController.KeyguardUnlockAnimationListener() { // from class: com.android.keyguard.KeyguardClockSwitchController.3
            @Override // com.android.systemui.keyguard.KeyguardUnlockAnimationController.KeyguardUnlockAnimationListener
            public final void onUnlockAnimationFinished() {
                ViewGroup viewGroup = KeyguardClockSwitchController.this.mStatusArea;
                if (viewGroup != null) {
                    viewGroup.setClipChildren(true);
                }
            }
        };
        this.mStatusBarStateController = statusBarStateController;
        this.mClockRegistry = clockRegistry;
        this.mKeyguardSliceViewController = keyguardSliceViewController;
        this.mSmartspaceController = lockscreenSmartspaceController;
        this.mSecureSettings = secureSettings;
        this.mUiExecutor = delayableExecutor;
        this.mBgExecutor = executor;
        this.mKeyguardUnlockAnimationController = keyguardUnlockAnimationController;
        this.mDumpManager = dumpManager;
        this.mClockEventController = clockEventController;
        this.mLogBuffer = logBuffer;
        keyguardClockSwitch.mLogBuffer = logBuffer;
        this.mFeatureFlags = featureFlagsClassic;
        this.mKeyguardClockInteractor = keyguardClockInteractor;
        this.mClockChangedListener = new AnonymousClass4();
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(new StringBuilder("currentClockSizeLarge: "), this.mCurrentClockSize == 0, printWriter, "mCanShowDoubleLineClock: "), this.mCanShowDoubleLineClock, printWriter);
        KeyguardClockSwitch keyguardClockSwitch = (KeyguardClockSwitch) this.mView;
        keyguardClockSwitch.getClass();
        printWriter.println("KeyguardClockSwitch:");
        printWriter.println("  mSmallClockFrame = null");
        printWriter.println("  mLargeClockFrame = null");
        StringBuilder m = KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(printWriter, "  mStatusArea = null", "  mDisplayedClockSize = ");
        m.append(keyguardClockSwitch.mDisplayedClockSize);
        printWriter.println(m.toString());
        ClockRegistry clockRegistry = this.mClockRegistry;
        clockRegistry.getClass();
        printWriter.println("ClockRegistry:");
        printWriter.println("  settings = " + clockRegistry.settings);
        for (Map.Entry entry : clockRegistry.availableClocks.entrySet()) {
            printWriter.println("  availableClocks[" + ((String) entry.getKey()) + "] = " + ((ClockRegistry.ClockInfo) entry.getValue()));
        }
        ClockController clock = getClock();
        if (clock != null) {
            clock.dump(printWriter);
        }
        this.mClockEventController.getClass();
    }

    public final ClockController getClock() {
        return (ClockController) this.mKeyguardClockInteractor.currentClock.getValue();
    }

    @Override // com.android.systemui.util.ViewController
    public final void onInit() {
        this.mKeyguardSliceViewController.init$9();
        DumpManager dumpManager = this.mDumpManager;
        dumpManager.unregisterDumpable("KeyguardClockSwitchController");
        DumpManager.registerDumpable$default(dumpManager, "KeyguardClockSwitchController", this);
        UnreleasedFlag unreleasedFlag = Flags.NULL_FLAG;
        this.mFeatureFlags.getClass();
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewAttached() {
        ClockRegistry clockRegistry = this.mClockRegistry;
        ThreadAssert threadAssert = clockRegistry.f43assert;
        Assert.isMainThread();
        clockRegistry.clockChangeListeners.add(this.mClockChangedListener);
        clockRegistry.createCurrentClock();
        ((KeyguardClockSwitch) this.mView).getResources().getDimensionPixelSize(R.dimen.keyguard_clock_top_margin);
        ((KeyguardClockSwitch) this.mView).getResources().getDimensionPixelSize(R.dimen.keyguard_large_clock_top_margin);
        ((KeyguardClockSwitch) this.mView).getResources().getInteger(R.integer.keyguard_date_weather_view_invisibility);
        if (!this.mShownOnSecondaryDisplay) {
            this.mStatusArea = (ViewGroup) ((KeyguardClockSwitch) this.mView).findViewById(R.id.keyguard_status_area);
            this.mBgExecutor.execute(new KeyguardClockSwitchController$$ExternalSyntheticLambda3(this, 1));
            this.mKeyguardUnlockAnimationController.listeners.add(this.mKeyguardUnlockAnimationListener);
            if (this.mSmartspaceController.isEnabled) {
                View findViewById = ((KeyguardClockSwitch) this.mView).findViewById(R.id.keyguard_slice_view);
                this.mStatusArea.indexOfChild(findViewById);
                findViewById.setVisibility(8);
                removeViewsFromStatusArea();
                return;
            }
            return;
        }
        ((KeyguardClockSwitch) this.mView).getClass();
        ClockEventController clockEventController = this.mClockEventController;
        clockEventController.largeClockOnSecondaryDisplay = true;
        clockEventController.updateFontSizes();
        if (this.mCanShowDoubleLineClock) {
            this.mCurrentClockSize = 0;
            getClock();
            KeyguardClockSwitch keyguardClockSwitch = (KeyguardClockSwitch) this.mView;
            Integer num = keyguardClockSwitch.mDisplayedClockSize;
            if (num == null || num.intValue() != 0) {
                if (keyguardClockSwitch.mChildrenAreLaidOut) {
                    keyguardClockSwitch.updateClockViews(true, false);
                    throw null;
                }
                keyguardClockSwitch.mDisplayedClockSize = 0;
            }
        }
        ((KeyguardClockSwitch) this.mView).findViewById(R.id.keyguard_slice_view).setVisibility(8);
        View findViewById2 = ((KeyguardClockSwitch) this.mView).findViewById(R.id.left_aligned_notification_icon_container);
        if (findViewById2 != null) {
            findViewById2.setVisibility(8);
        }
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewDetached() {
        this.mClockRegistry.unregisterClockChangeListener(this.mClockChangedListener);
        this.mBgExecutor.execute(new KeyguardClockSwitchController$$ExternalSyntheticLambda3(this, 2));
        this.mKeyguardUnlockAnimationController.listeners.remove(this.mKeyguardUnlockAnimationListener);
    }

    public final void removeViewsFromStatusArea() {
        for (int childCount = this.mStatusArea.getChildCount() - 1; childCount >= 0; childCount--) {
            if (this.mStatusArea.getChildAt(childCount).getTag(R.id.tag_smartspace_view) != null) {
                this.mStatusArea.removeViewAt(childCount);
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.keyguard.KeyguardClockSwitchController$4, reason: invalid class name */
    public final class AnonymousClass4 implements ClockRegistry.ClockChangeListener {
        @Override // com.android.systemui.shared.clocks.ClockRegistry.ClockChangeListener
        public final void onCurrentClockChanged() {
        }
    }
}

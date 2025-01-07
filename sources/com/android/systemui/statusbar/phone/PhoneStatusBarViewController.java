package com.android.systemui.statusbar.phone;

import android.content.res.Resources;
import android.view.MotionEvent;
import android.view.View;
import androidx.compose.runtime.collection.MutableVectorKt$$ExternalSyntheticOutline0;
import com.android.systemui.Gefingerpoken;
import com.android.systemui.battery.BatteryMeterView;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.android.systemui.shade.BaseShadeControllerImpl;
import com.android.systemui.shade.ShadeController;
import com.android.systemui.shade.ShadeLogger;
import com.android.systemui.shade.ShadeViewController;
import com.android.systemui.shade.domain.interactor.PanelExpansionInteractor;
import com.android.systemui.statusbar.phone.userswitcher.StatusBarUserSwitcherContainer;
import com.android.systemui.statusbar.policy.Clock;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.window.StatusBarWindowStateController;
import com.android.systemui.unfold.util.ScopedUnfoldTransitionProgressProvider;
import com.android.systemui.user.ui.binder.StatusBarUserChipViewBinder;
import com.android.systemui.user.ui.viewmodel.StatusBarUserChipViewModel;
import com.android.systemui.util.ViewController;
import com.android.systemui.util.view.ViewUtil;
import com.android.wm.shell.R;
import dagger.internal.DelegateFactory;
import java.util.Arrays;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PhoneStatusBarViewController extends ViewController {
    public BatteryMeterView battery;
    public final CentralSurfaces centralSurfaces;
    public Clock clock;
    public final ConfigurationController configurationController;
    public final PhoneStatusBarViewController$configurationListener$1 configurationListener;
    public final DarkIconDispatcher darkIconDispatcher;
    public View endSideContainer;
    public final PhoneStatusBarViewController$iconsOnTouchListener$1 iconsOnTouchListener;
    public final PanelExpansionInteractor panelExpansionInteractor;
    public final ScopedUnfoldTransitionProgressProvider progressProvider;
    public final ShadeController shadeController;
    public final ShadeLogger shadeLogger;
    public final ShadeViewController shadeViewController;
    public View startSideContainer;
    public final StatusBarContentInsetsProvider statusBarContentInsetsProvider;
    public final StatusBarWindowStateController statusBarWindowStateController;
    public final StatusOverlayHoverListenerFactory statusOverlayHoverListenerFactory;
    public final ViewUtil viewUtil;
    public final DelegateFactory windowRootView;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.phone.PhoneStatusBarViewController$1, reason: invalid class name */
    public final class AnonymousClass1 {
        public /* synthetic */ AnonymousClass1() {
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class PhoneStatusBarViewTouchHandler implements Gefingerpoken {
        public PhoneStatusBarViewTouchHandler() {
        }

        @Override // com.android.systemui.Gefingerpoken
        public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            PhoneStatusBarViewController.this.onTouch(motionEvent);
            return false;
        }

        @Override // com.android.systemui.Gefingerpoken
        public final boolean onTouchEvent(MotionEvent motionEvent) {
            PhoneStatusBarViewController phoneStatusBarViewController = PhoneStatusBarViewController.this;
            phoneStatusBarViewController.onTouch(motionEvent);
            if (!((CentralSurfacesImpl) phoneStatusBarViewController.centralSurfaces).mCommandQueue.panelsEnabled()) {
                if (motionEvent.getAction() == 0) {
                    String.format(MutableVectorKt$$ExternalSyntheticOutline0.m((int) motionEvent.getX(), (int) motionEvent.getY(), "onTouchForwardedFromStatusBar: panel disabled, ignoring touch at (", ",", ")"), Arrays.copyOf(new Object[0], 0));
                }
                return false;
            }
            int action = motionEvent.getAction();
            ShadeViewController shadeViewController = phoneStatusBarViewController.shadeViewController;
            if (action == 0) {
                boolean isViewEnabled = shadeViewController.isViewEnabled();
                ShadeLogger shadeLogger = phoneStatusBarViewController.shadeLogger;
                if (!isViewEnabled) {
                    shadeLogger.logMotionEvent(motionEvent, "onTouchForwardedFromStatusBar: panel view disabled");
                    return true;
                }
                if (phoneStatusBarViewController.panelExpansionInteractor.isFullyCollapsed() && motionEvent.getY() < 1.0f) {
                    shadeLogger.logMotionEvent(motionEvent, "top edge touch ignored");
                    return true;
                }
            }
            return shadeViewController.handleExternalTouch(motionEvent);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class StatusBarViewsCenterProvider {
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [com.android.systemui.statusbar.phone.PhoneStatusBarViewController$iconsOnTouchListener$1] */
    /* JADX WARN: Type inference failed for: r2v2, types: [com.android.systemui.statusbar.phone.PhoneStatusBarViewController$configurationListener$1] */
    public PhoneStatusBarViewController(PhoneStatusBarView phoneStatusBarView, ScopedUnfoldTransitionProgressProvider scopedUnfoldTransitionProgressProvider, CentralSurfaces centralSurfaces, StatusBarWindowStateController statusBarWindowStateController, ShadeController shadeController, ShadeViewController shadeViewController, PanelExpansionInteractor panelExpansionInteractor, DelegateFactory delegateFactory, ShadeLogger shadeLogger, StatusBarUserChipViewModel statusBarUserChipViewModel, ViewUtil viewUtil, ConfigurationController configurationController, StatusOverlayHoverListenerFactory statusOverlayHoverListenerFactory, DarkIconDispatcher darkIconDispatcher, StatusBarContentInsetsProvider statusBarContentInsetsProvider) {
        super(phoneStatusBarView);
        this.progressProvider = scopedUnfoldTransitionProgressProvider;
        this.centralSurfaces = centralSurfaces;
        this.statusBarWindowStateController = statusBarWindowStateController;
        this.shadeController = shadeController;
        this.shadeViewController = shadeViewController;
        this.panelExpansionInteractor = panelExpansionInteractor;
        this.shadeLogger = shadeLogger;
        this.viewUtil = viewUtil;
        this.configurationController = configurationController;
        this.statusOverlayHoverListenerFactory = statusOverlayHoverListenerFactory;
        this.darkIconDispatcher = darkIconDispatcher;
        this.statusBarContentInsetsProvider = statusBarContentInsetsProvider;
        this.iconsOnTouchListener = new View.OnTouchListener() { // from class: com.android.systemui.statusbar.phone.PhoneStatusBarViewController$iconsOnTouchListener$1
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getSource() != 8194) {
                    return false;
                }
                if (motionEvent.getAction() == 1) {
                    view.performClick();
                    ((BaseShadeControllerImpl) PhoneStatusBarViewController.this.shadeController).animateExpandShade();
                }
                return true;
            }
        };
        this.configurationListener = new ConfigurationController.ConfigurationListener() { // from class: com.android.systemui.statusbar.phone.PhoneStatusBarViewController$configurationListener$1
            @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
            public final void onDensityOrFontScaleChanged() {
                Clock clock = PhoneStatusBarViewController.this.clock;
                if (clock == null) {
                    clock = null;
                }
                clock.onDensityOrFontScaleChanged();
            }
        };
        phoneStatusBarView.mTouchEventHandler = new PhoneStatusBarViewTouchHandler();
        phoneStatusBarView.mHasCornerCutoutFetcher = new AnonymousClass1();
        phoneStatusBarView.updateCutoutLocation();
        phoneStatusBarView.mInsetsFetcher = new AnonymousClass1();
        phoneStatusBarView.updateSafeInsets();
        StatusBarUserChipViewBinder.bind((StatusBarUserSwitcherContainer) phoneStatusBarView.findViewById(R.id.user_switcher_container), statusBarUserChipViewModel);
    }

    public final void onTouch(MotionEvent motionEvent) {
        if (this.statusBarWindowStateController.windowState == 0) {
            ((CentralSurfacesImpl) this.centralSurfaces).setInteracting(1, !(motionEvent.getAction() == 1 || motionEvent.getAction() == 3) || this.shadeController.isExpandedVisible());
        }
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewAttached() {
        this.clock = (Clock) ((PhoneStatusBarView) this.mView).requireViewById(R.id.clock);
        BatteryMeterView batteryMeterView = (BatteryMeterView) ((PhoneStatusBarView) this.mView).requireViewById(R.id.battery);
        this.battery = batteryMeterView;
        DarkIconDispatcher darkIconDispatcher = this.darkIconDispatcher;
        darkIconDispatcher.addDarkReceiver(batteryMeterView);
        Clock clock = this.clock;
        if (clock == null) {
            clock = null;
        }
        darkIconDispatcher.addDarkReceiver(clock);
        View requireViewById = ((PhoneStatusBarView) this.mView).requireViewById(R.id.system_icons);
        this.endSideContainer = requireViewById;
        StatusOverlayHoverListenerFactory statusOverlayHoverListenerFactory = this.statusOverlayHoverListenerFactory;
        ReadonlyStateFlow readonlyStateFlow = new ReadonlyStateFlow(statusOverlayHoverListenerFactory.darkIconDispatcher.mDarkChangeFlow);
        Resources resources = statusOverlayHoverListenerFactory.resources;
        StatusOverlayHoverListenerFactory$createDarkAwareListener$$inlined$map$1 statusOverlayHoverListenerFactory$createDarkAwareListener$$inlined$map$1 = new StatusOverlayHoverListenerFactory$createDarkAwareListener$$inlined$map$1(readonlyStateFlow, statusOverlayHoverListenerFactory, requireViewById);
        ConfigurationController configurationController = statusOverlayHoverListenerFactory.configurationController;
        requireViewById.setOnHoverListener(new StatusOverlayHoverListener(requireViewById, configurationController, resources, statusOverlayHoverListenerFactory$createDarkAwareListener$$inlined$map$1));
        View view = this.endSideContainer;
        if (view == null) {
            view = null;
        }
        PhoneStatusBarViewController$iconsOnTouchListener$1 phoneStatusBarViewController$iconsOnTouchListener$1 = this.iconsOnTouchListener;
        view.setOnTouchListener(phoneStatusBarViewController$iconsOnTouchListener$1);
        View requireViewById2 = ((PhoneStatusBarView) this.mView).requireViewById(R.id.status_bar_start_side_content);
        this.startSideContainer = requireViewById2;
        requireViewById2.setOnHoverListener(new StatusOverlayHoverListener(requireViewById2, configurationController, statusOverlayHoverListenerFactory.resources, new StatusOverlayHoverListenerFactory$createDarkAwareListener$$inlined$map$1(new ReadonlyStateFlow(statusOverlayHoverListenerFactory.darkIconDispatcher.mDarkChangeFlow), statusOverlayHoverListenerFactory, requireViewById2)));
        View view2 = this.startSideContainer;
        (view2 != null ? view2 : null).setOnTouchListener(phoneStatusBarViewController$iconsOnTouchListener$1);
        ScopedUnfoldTransitionProgressProvider scopedUnfoldTransitionProgressProvider = this.progressProvider;
        if (scopedUnfoldTransitionProgressProvider != null) {
            scopedUnfoldTransitionProgressProvider.setReadyToHandleTransition(true);
        }
        ((ConfigurationControllerImpl) this.configurationController).addCallback(this.configurationListener);
    }

    @Override // com.android.systemui.util.ViewController
    public void onViewDetached() {
        BatteryMeterView batteryMeterView = this.battery;
        if (batteryMeterView == null) {
            batteryMeterView = null;
        }
        DarkIconDispatcher darkIconDispatcher = this.darkIconDispatcher;
        darkIconDispatcher.removeDarkReceiver(batteryMeterView);
        Clock clock = this.clock;
        if (clock == null) {
            clock = null;
        }
        darkIconDispatcher.removeDarkReceiver(clock);
        View view = this.startSideContainer;
        if (view == null) {
            view = null;
        }
        view.setOnHoverListener(null);
        View view2 = this.endSideContainer;
        if (view2 == null) {
            view2 = null;
        }
        view2.setOnHoverListener(null);
        ScopedUnfoldTransitionProgressProvider scopedUnfoldTransitionProgressProvider = this.progressProvider;
        if (scopedUnfoldTransitionProgressProvider != null) {
            scopedUnfoldTransitionProgressProvider.setReadyToHandleTransition(false);
        }
        ((ConfigurationControllerImpl) this.configurationController).removeCallback(this.configurationListener);
    }

    @Override // com.android.systemui.util.ViewController
    public final void onInit() {
    }
}

package com.android.systemui.accessibility.floatingmenu;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.StatusBarManager;
import android.content.BroadcastReceiver;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.provider.Settings;
import android.provider.SettingsStringUtil;
import android.util.ArraySet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.WindowMetrics;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate;
import com.android.internal.accessibility.dialog.AccessibilityTargetHelper;
import com.android.modules.expresslog.Counter;
import com.android.systemui.Prefs;
import com.android.systemui.accessibility.floatingmenu.DragToInteractView;
import com.android.systemui.navigationbar.NavigationModeController;
import com.android.systemui.plugins.PluginManager;
import com.android.systemui.util.settings.SecureSettings;
import com.android.systemui.util.settings.SecureSettingsImpl;
import com.android.wm.shell.R;
import com.android.wm.shell.bubbles.DismissViewUtils;
import com.android.wm.shell.shared.bubbles.DismissCircleView;
import com.android.wm.shell.shared.bubbles.DismissView;
import com.android.wm.shell.shared.magnetictarget.MagnetizedObject;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import kotlin.Pair;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MenuViewLayer extends FrameLayout implements ViewTreeObserver.OnComputeInternalInsetsListener, View.OnClickListener, ComponentCallbacks {
    public final AccessibilityManager mAccessibilityManager;
    final Runnable mDismissMenuAction;
    public final DismissView mDismissView;
    public final MenuViewLayer$$ExternalSyntheticLambda4 mDockTooltipObserver;
    public final DragToInteractAnimationController mDragToInteractAnimationController;
    public final DragToInteractView mDragToInteractView;
    public Optional mEduTooltipView;
    public final MenuViewLayerController mFloatingMenu;
    public final Handler mHandler;
    public final Rect mImeInsetsRect;
    public boolean mIsNotificationShown;
    public final MenuAnimationController mMenuAnimationController;
    public final MenuListViewTouchHandler mMenuListViewTouchHandler;
    public final MenuView mMenuView;
    public final MenuViewAppearance mMenuViewAppearance;
    public final MenuViewModel mMenuViewModel;
    public final MenuMessageView mMessageView;
    public final MenuViewLayer$$ExternalSyntheticLambda4 mMigrationTooltipObserver;
    public final MenuViewLayer$$ExternalSyntheticLambda7 mNavigationModeChangedListender;
    public final NavigationModeController mNavigationModeController;
    public MenuNotificationActionReceiver mNotificationActionReceiver;
    public final MenuNotificationFactory mNotificationFactory;
    public final NotificationManager mNotificationManager;
    public final SecureSettings mSecureSettings;
    public boolean mShouldShowDockTooltip;
    public final StatusBarManager mStatusBarManager;
    public final WindowManager mWindowManager;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MenuNotificationActionReceiver extends BroadcastReceiver {
        public MenuNotificationActionReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("com.android.systemui.accessibility.floatingmenu.action.UNDO".equals(action)) {
                MenuViewLayer.this.dismissNotification();
                MenuViewLayer.this.undo();
            } else if ("com.android.systemui.accessibility.floatingmenu.action.DELETE".equals(action)) {
                MenuViewLayer.this.dismissNotification();
                MenuViewLayer.this.mDismissMenuAction.run();
            }
        }
    }

    /* renamed from: $r8$lambda$EoXQA-mcH59afcqT97RTQWdI8-g, reason: not valid java name */
    public static void m780$r8$lambda$EoXQAmcH59afcqT97RTQWdI8g(MenuViewLayer menuViewLayer, MenuEduTooltipView menuEduTooltipView) {
        menuViewLayer.getClass();
        if (menuEduTooltipView.getTag().equals("migration")) {
            menuViewLayer.mMenuViewModel.mInfoRepository.mSecureSettings.putIntForUser("accessibility_floating_menu_migration_tooltip_prompt", 0, -2);
        }
        if (menuEduTooltipView.getTag().equals("dock")) {
            Prefs.putBoolean(menuViewLayer.mMenuViewModel.mInfoRepository.mContext, "HasSeenAccessibilityFloatingMenuDockTooltip", true);
            menuViewLayer.mMenuView.clearAnimation();
            menuViewLayer.mShouldShowDockTooltip = false;
        }
        menuViewLayer.removeView(menuEduTooltipView);
        MenuListViewTouchHandler menuListViewTouchHandler = menuViewLayer.mMenuListViewTouchHandler;
        menuListViewTouchHandler.getClass();
        menuListViewTouchHandler.mOnActionDownEnd = Optional.ofNullable(null);
        menuViewLayer.mEduTooltipView = Optional.empty();
    }

    /* renamed from: $r8$lambda$_8a3GjMs-PEVxDMzbacNAbGLn-A, reason: not valid java name */
    public static void m781$r8$lambda$_8a3GjMsPEVxDMzbacNAbGLnA(MenuViewLayer menuViewLayer, boolean z) {
        menuViewLayer.getClass();
        if (z) {
            Optional of = Optional.of(new MenuEduTooltipView(((FrameLayout) menuViewLayer).mContext, menuViewLayer.mMenuViewAppearance));
            menuViewLayer.mEduTooltipView = of;
            of.ifPresent(new MenuViewLayer$$ExternalSyntheticLambda9(menuViewLayer, 2));
        }
    }

    public static void $r8$lambda$hJVOPKG3uKij0Zkewl6JRXHaXWw(MenuViewLayer menuViewLayer) {
        if (menuViewLayer.mShouldShowDockTooltip) {
            menuViewLayer.mEduTooltipView.ifPresent(new MenuViewLayer$$ExternalSyntheticLambda9(menuViewLayer, 0));
            Optional of = Optional.of(new MenuEduTooltipView(((FrameLayout) menuViewLayer).mContext, menuViewLayer.mMenuViewAppearance));
            menuViewLayer.mEduTooltipView = of;
            of.ifPresent(new MenuViewLayer$$ExternalSyntheticLambda9(menuViewLayer, 1));
            MenuAnimationController menuAnimationController = menuViewLayer.mMenuAnimationController;
            menuAnimationController.fadeInNowIfEnabled();
            TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, menuAnimationController.isOnLeftSide() ? -0.5f : 0.5f, 1, 0.0f, 1, 0.0f);
            translateAnimation.setDuration(600L);
            translateAnimation.setRepeatMode(2);
            translateAnimation.setInterpolator(new OvershootInterpolator());
            translateAnimation.setRepeatCount(-1);
            translateAnimation.setStartOffset(600L);
            menuAnimationController.mMenuView.startAnimation(translateAnimation);
        }
        if (!menuViewLayer.mMenuView.mIsMoveToTucked) {
            menuViewLayer.setClipBounds(null);
        }
        MenuView menuView = menuViewLayer.mMenuView;
        PointF menuPosition = menuView.mMenuViewAppearance.getMenuPosition();
        menuView.mBoundsInParent.offsetTo((int) menuPosition.x, (int) menuPosition.y);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.android.systemui.accessibility.floatingmenu.MenuViewLayer$$ExternalSyntheticLambda4] */
    /* JADX WARN: Type inference failed for: r0v2, types: [com.android.systemui.accessibility.floatingmenu.MenuViewLayer$$ExternalSyntheticLambda4] */
    /* JADX WARN: Type inference failed for: r6v12, types: [com.android.systemui.accessibility.floatingmenu.MenuViewLayer$$ExternalSyntheticLambda7] */
    public MenuViewLayer(Context context, WindowManager windowManager, AccessibilityManager accessibilityManager, MenuViewModel menuViewModel, MenuViewAppearance menuViewAppearance, MenuView menuView, MenuViewLayerController menuViewLayerController, SecureSettings secureSettings, NavigationModeController navigationModeController) {
        super(context);
        this.mHandler = new Handler(Looper.getMainLooper());
        final int i = 0;
        this.mDockTooltipObserver = new Observer(this) { // from class: com.android.systemui.accessibility.floatingmenu.MenuViewLayer$$ExternalSyntheticLambda4
            public final /* synthetic */ MenuViewLayer f$0;

            {
                this.f$0 = this;
            }

            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                int i2 = i;
                boolean booleanValue = ((Boolean) obj).booleanValue();
                switch (i2) {
                    case 0:
                        this.f$0.mShouldShowDockTooltip = !booleanValue;
                        break;
                    default:
                        MenuViewLayer.m781$r8$lambda$_8a3GjMsPEVxDMzbacNAbGLnA(this.f$0, booleanValue);
                        break;
                }
            }
        };
        final int i2 = 1;
        this.mMigrationTooltipObserver = new Observer(this) { // from class: com.android.systemui.accessibility.floatingmenu.MenuViewLayer$$ExternalSyntheticLambda4
            public final /* synthetic */ MenuViewLayer f$0;

            {
                this.f$0 = this;
            }

            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                int i22 = i2;
                boolean booleanValue = ((Boolean) obj).booleanValue();
                switch (i22) {
                    case 0:
                        this.f$0.mShouldShowDockTooltip = !booleanValue;
                        break;
                    default:
                        MenuViewLayer.m781$r8$lambda$_8a3GjMsPEVxDMzbacNAbGLnA(this.f$0, booleanValue);
                        break;
                }
            }
        };
        this.mImeInsetsRect = new Rect();
        this.mEduTooltipView = Optional.empty();
        this.mDismissMenuAction = new Runnable() { // from class: com.android.systemui.accessibility.floatingmenu.MenuViewLayer.1
            @Override // java.lang.Runnable
            public final void run() {
                MenuViewLayer.this.mAccessibilityManager.enableShortcutsForTargets(false, 1, new ArraySet(MenuViewLayer.this.mAccessibilityManager.getAccessibilityShortcutTargets(1)), MenuViewLayer.this.mSecureSettings.getRealUserHandle(-2));
                MenuViewLayerController menuViewLayerController2 = MenuViewLayer.this.mFloatingMenu;
                if (menuViewLayerController2.mIsShowing) {
                    menuViewLayerController2.mIsShowing = false;
                    menuViewLayerController2.mWindowManager.removeView(menuViewLayerController2.mMenuViewLayer);
                }
            }
        };
        setLayoutDirection(0);
        this.mWindowManager = windowManager;
        this.mAccessibilityManager = accessibilityManager;
        this.mFloatingMenu = menuViewLayerController;
        this.mSecureSettings = secureSettings;
        this.mMenuViewModel = menuViewModel;
        this.mMenuViewAppearance = menuViewAppearance;
        this.mMenuView = menuView;
        RecyclerView recyclerView = menuView.mTargetFeaturesView;
        RecyclerViewAccessibilityDelegate recyclerViewAccessibilityDelegate = new RecyclerViewAccessibilityDelegate(recyclerView) { // from class: com.android.systemui.accessibility.floatingmenu.MenuViewLayer.2
            @Override // androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate
            public final AccessibilityDelegateCompat getItemDelegate() {
                MenuViewLayer menuViewLayer = MenuViewLayer.this;
                return new MenuItemAccessibilityDelegate(this, menuViewLayer.mMenuAnimationController, menuViewLayer);
            }
        };
        recyclerView.mAccessibilityDelegate = recyclerViewAccessibilityDelegate;
        ViewCompat.setAccessibilityDelegate(recyclerView, recyclerViewAccessibilityDelegate);
        MenuAnimationController menuAnimationController = menuView.mMenuAnimationController;
        this.mMenuAnimationController = menuAnimationController;
        menuAnimationController.mSpringAnimationsEndAction = new MenuViewLayer$$ExternalSyntheticLambda0(this, 3);
        DismissView dismissView = new DismissView(context);
        this.mDismissView = dismissView;
        DragToInteractView dragToInteractView = new DragToInteractView(context);
        this.mDragToInteractView = dragToInteractView;
        DismissViewUtils.setup(dismissView);
        dismissView.circle.setId(R.id.action_remove_menu);
        this.mNotificationFactory = new MenuNotificationFactory(context);
        this.mNotificationManager = (NotificationManager) context.getSystemService(NotificationManager.class);
        this.mStatusBarManager = (StatusBarManager) context.getSystemService(StatusBarManager.class);
        this.mNavigationModeController = navigationModeController;
        this.mNavigationModeChangedListender = new NavigationModeController.ModeChangedListener() { // from class: com.android.systemui.accessibility.floatingmenu.MenuViewLayer$$ExternalSyntheticLambda7
            @Override // com.android.systemui.navigationbar.NavigationModeController.ModeChangedListener
            public final void onNavigationModeChanged(int i3) {
                MenuViewLayer.this.mMenuView.onPositionChanged(false);
            }
        };
        DragToInteractAnimationController dragToInteractAnimationController = new DragToInteractAnimationController(dragToInteractView, menuView);
        this.mDragToInteractAnimationController = dragToInteractAnimationController;
        dragToInteractAnimationController.mInteractMap.forEach(new DragToInteractAnimationController$$ExternalSyntheticLambda0(i2, new MagnetizedObject.MagnetListener() { // from class: com.android.systemui.accessibility.floatingmenu.MenuViewLayer.3
            @Override // com.android.wm.shell.shared.magnetictarget.MagnetizedObject.MagnetListener
            public final void onReleasedInTarget(MagnetizedObject.MagneticTarget magneticTarget, MagnetizedObject magnetizedObject) {
                MenuViewLayer.this.dispatchAccessibilityAction(magneticTarget.targetView.getId());
            }

            @Override // com.android.wm.shell.shared.magnetictarget.MagnetizedObject.MagnetListener
            public final void onStuckToTarget(MagnetizedObject.MagneticTarget magneticTarget, MagnetizedObject magnetizedObject) {
                MenuViewLayer.this.mDragToInteractAnimationController.animateInteractMenu(magneticTarget.targetView.getId(), true);
            }

            @Override // com.android.wm.shell.shared.magnetictarget.MagnetizedObject.MagnetListener
            public final void onUnstuckFromTarget(MagnetizedObject.MagneticTarget magneticTarget, MagnetizedObject magnetizedObject, float f, float f2, boolean z) {
                MenuViewLayer.this.mDragToInteractAnimationController.animateInteractMenu(magneticTarget.targetView.getId(), false);
            }
        }));
        MenuListViewTouchHandler menuListViewTouchHandler = new MenuListViewTouchHandler(menuAnimationController, dragToInteractAnimationController);
        this.mMenuListViewTouchHandler = menuListViewTouchHandler;
        menuView.mTargetFeaturesView.mOnItemTouchListeners.add(menuListViewTouchHandler);
        menuView.mMoveToTuckedListener = this;
        MenuMessageView menuMessageView = new MenuMessageView(context);
        this.mMessageView = menuMessageView;
        menuView.mFeaturesChangeListener = new MenuViewLayer$$ExternalSyntheticLambda8(this);
        addView(menuView, 0);
        addView(dragToInteractView, 1);
        addView(menuMessageView, 2);
        setClipChildren(true);
        setClickable(false);
        setFocusable(false);
        setImportantForAccessibility(2);
    }

    public final void dismissNotification() {
        if (this.mNotificationActionReceiver != null) {
            getContext().unregisterReceiver(this.mNotificationActionReceiver);
            this.mNotificationActionReceiver = null;
        }
        if (this.mIsNotificationShown) {
            this.mNotificationManager.cancel(1009);
            this.mIsNotificationShown = false;
        }
    }

    public final void dispatchAccessibilityAction(int i) {
        if (i == R.id.action_remove_menu) {
            hideMenuAndShowMessage();
            this.mMenuView.getClass();
            Counter.logIncrement("accessibility.value_fab_shortcut_dismiss");
        } else if (i == R.id.action_edit) {
            this.mMenuAnimationController.flingMenuThenSpringToEdge(this.mMenuView.mMenuViewAppearance.getMenuPosition().x, 100.0f, 0.0f);
            List list = new SettingsStringUtil.ColonDelimitedSet.OfStrings(((SecureSettingsImpl) this.mSecureSettings).getStringForUser(-2, "accessibility_button_targets")).stream().toList();
            Intent intent = new Intent("android.settings.ACCESSIBILITY_SHORTCUT_SETTINGS");
            Bundle bundle = new Bundle();
            Bundle bundle2 = new Bundle();
            bundle2.putStringArray("targets", (String[]) list.toArray(new String[0]));
            bundle.putBundle(":settings:show_fragment_args", bundle2);
            intent.replaceExtras(bundle);
            intent.setFlags(335544320);
            if (!getContext().getPackageManager().queryIntentActivities(intent, PackageManager.ResolveInfoFlags.of(65536L)).isEmpty()) {
                ((FrameLayout) this).mContext.startActivityAsUser(intent, UserHandle.CURRENT);
                this.mStatusBarManager.collapsePanels();
            }
            this.mMenuView.getClass();
            Counter.logIncrement("accessibility.value_fab_shortcut_edit");
        }
        this.mDismissView.hide();
        this.mDragToInteractView.hide();
        this.mDragToInteractAnimationController.animateInteractMenu(i, false);
    }

    public DragToInteractAnimationController getDragToInteractAnimationController() {
        return this.mDragToInteractAnimationController;
    }

    public void hideMenuAndShowMessage() {
        this.mHandler.postDelayed(this.mDismissMenuAction, this.mAccessibilityManager.getRecommendedTimeoutMillis(3000, 6));
        this.mMessageView.setVisibility(0);
        this.mMenuAnimationController.startShrinkAnimation(new MenuViewLayer$$ExternalSyntheticLambda0(this, 2));
    }

    public void hideMenuAndShowNotification() {
        this.mMenuAnimationController.startShrinkAnimation(new MenuViewLayer$$ExternalSyntheticLambda0(this, 0));
        if (this.mNotificationActionReceiver == null) {
            this.mNotificationActionReceiver = new MenuNotificationActionReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.android.systemui.accessibility.floatingmenu.action.UNDO");
            intentFilter.addAction("com.android.systemui.accessibility.floatingmenu.action.DELETE");
            getContext().registerReceiver(this.mNotificationActionReceiver, intentFilter, 2);
        }
        if (this.mIsNotificationShown) {
            return;
        }
        NotificationManager notificationManager = this.mNotificationManager;
        MenuNotificationFactory menuNotificationFactory = this.mNotificationFactory;
        CharSequence text = menuNotificationFactory.mContext.getText(R.string.accessibility_floating_button_hidden_notification_title);
        notificationManager.notify(1009, new Notification.Builder(menuNotificationFactory.mContext, PluginManager.NOTIFICATION_CHANNEL_ID).setContentTitle(text).setContentText(menuNotificationFactory.mContext.getText(R.string.accessibility_floating_button_hidden_notification_text)).setSmallIcon(R.drawable.ic_settings_24dp).setContentIntent(PendingIntent.getBroadcast(menuNotificationFactory.mContext, 0, new Intent("com.android.systemui.accessibility.floatingmenu.action.UNDO"), 67108864)).setDeleteIntent(PendingIntent.getBroadcastAsUser(menuNotificationFactory.mContext, 0, new Intent("com.android.systemui.accessibility.floatingmenu.action.DELETE"), 1409286144, UserHandle.CURRENT)).setColor(menuNotificationFactory.mContext.getResources().getColor(android.R.color.system_notification_accent_color)).setLocalOnly(true).setCategory("sys").build());
        this.mIsNotificationShown = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        MenuView menuView = this.mMenuView;
        MenuViewModel menuViewModel = menuView.mMenuViewModel;
        MutableLiveData mutableLiveData = menuViewModel.mPercentagePositionData;
        Objects.requireNonNull(mutableLiveData);
        mutableLiveData.setValue(menuViewModel.mInfoRepository.mPercentagePosition);
        mutableLiveData.observeForever(menuView.mPercentagePositionObserver);
        MenuViewModel menuViewModel2 = menuView.mMenuViewModel;
        MutableLiveData mutableLiveData2 = menuViewModel2.mFadeEffectInfoData;
        Objects.requireNonNull(mutableLiveData2);
        SecureSettings secureSettings = menuViewModel2.mInfoRepository.mSecureSettings;
        mutableLiveData2.setValue(new MenuFadeEffectInfo(secureSettings.getFloatForUser(0.55f, -2, "accessibility_floating_menu_opacity"), secureSettings.getIntForUser("accessibility_floating_menu_fade_enabled", 1, -2) == 1));
        mutableLiveData2.observeForever(menuView.mFadeEffectInfoObserver);
        MenuViewModel menuViewModel3 = menuView.mMenuViewModel;
        MutableLiveData mutableLiveData3 = menuViewModel3.mTargetFeaturesData;
        Objects.requireNonNull(mutableLiveData3);
        mutableLiveData3.setValue(AccessibilityTargetHelper.getTargets(menuViewModel3.mInfoRepository.mContext, 1));
        mutableLiveData3.observeForever(menuView.mTargetFeaturesObserver);
        MenuViewModel menuViewModel4 = menuView.mMenuViewModel;
        MutableLiveData mutableLiveData4 = menuViewModel4.mSizeTypeData;
        Objects.requireNonNull(mutableLiveData4);
        mutableLiveData4.setValue(Integer.valueOf(menuViewModel4.mInfoRepository.mSecureSettings.getIntForUser("accessibility_floating_menu_size", 0, -2)));
        mutableLiveData4.observeForever(menuView.mSizeTypeObserver);
        MenuViewModel menuViewModel5 = menuView.mMenuViewModel;
        MutableLiveData mutableLiveData5 = menuViewModel5.mMoveToTuckedData;
        Objects.requireNonNull(mutableLiveData5);
        mutableLiveData5.setValue(Boolean.valueOf(Prefs.getBoolean(menuViewModel5.mInfoRepository.mContext, "HasAccessibilityFloatingMenuTucked")));
        mutableLiveData5.observeForever(menuView.mMoveToTuckedObserver);
        menuView.setVisibility(0);
        MenuInfoRepository menuInfoRepository = menuView.mMenuViewModel.mInfoRepository;
        SecureSettings secureSettings2 = menuInfoRepository.mSecureSettings;
        ((SecureSettingsImpl) secureSettings2).getClass();
        secureSettings2.registerContentObserverForUserSync(Settings.Secure.getUriFor("accessibility_button_targets"), false, menuInfoRepository.mMenuTargetFeaturesContentObserver, -2);
        secureSettings2.registerContentObserverForUserSync(Settings.Secure.getUriFor("accessibility_floating_menu_size"), false, menuInfoRepository.mMenuSizeContentObserver, -2);
        secureSettings2.registerContentObserverForUserSync(Settings.Secure.getUriFor("accessibility_floating_menu_fade_enabled"), false, menuInfoRepository.mMenuFadeOutContentObserver, -2);
        secureSettings2.registerContentObserverForUserSync(Settings.Secure.getUriFor("accessibility_floating_menu_opacity"), false, menuInfoRepository.mMenuFadeOutContentObserver, -2);
        menuInfoRepository.mContext.registerComponentCallbacks(menuInfoRepository.mComponentCallbacks);
        menuView.getViewTreeObserver().addOnComputeInternalInsetsListener(menuView);
        menuView.getViewTreeObserver().addOnDrawListener(menuView.mSystemGestureExcludeUpdater);
        setOnClickListener(this);
        setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() { // from class: com.android.systemui.accessibility.floatingmenu.MenuViewLayer$$ExternalSyntheticLambda1
            @Override // android.view.View.OnApplyWindowInsetsListener
            public final WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                MenuViewLayer menuViewLayer = MenuViewLayer.this;
                WindowMetrics currentWindowMetrics = menuViewLayer.mWindowManager.getCurrentWindowMetrics();
                WindowInsets windowInsets2 = currentWindowMetrics.getWindowInsets();
                Rect rect = windowInsets2.getInsets(WindowInsets.Type.ime()).toRect();
                if (!rect.equals(menuViewLayer.mImeInsetsRect)) {
                    float height = (new Rect(currentWindowMetrics.getBounds()).height() - windowInsets2.getInsetsIgnoringVisibility(135).toRect().top) - rect.bottom;
                    MenuViewAppearance menuViewAppearance = menuViewLayer.mMenuViewAppearance;
                    menuViewAppearance.mIsImeShowing = windowInsets2.isVisible(WindowInsets.Type.ime());
                    menuViewAppearance.mImeTop = height;
                    menuViewLayer.mMenuView.onEdgeChanged();
                    menuViewLayer.mMenuView.onPositionChanged(true);
                    menuViewLayer.mImeInsetsRect.set(rect);
                }
                return windowInsets;
            }
        });
        getViewTreeObserver().addOnComputeInternalInsetsListener(this);
        MenuViewModel menuViewModel6 = this.mMenuViewModel;
        MutableLiveData mutableLiveData6 = menuViewModel6.mDockTooltipData;
        Objects.requireNonNull(mutableLiveData6);
        mutableLiveData6.setValue(Boolean.valueOf(Prefs.getBoolean(menuViewModel6.mInfoRepository.mContext, "HasSeenAccessibilityFloatingMenuDockTooltip")));
        mutableLiveData6.observeForever(this.mDockTooltipObserver);
        this.mMenuViewModel.getMigrationTooltipVisibilityData().observeForever(this.mMigrationTooltipObserver);
        this.mMessageView.mUndoButton.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.accessibility.floatingmenu.MenuViewLayer$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MenuViewLayer.this.undo();
            }
        });
        getContext().registerComponentCallbacks(this);
        this.mNavigationModeController.addListener(this.mNavigationModeChangedListender);
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        this.mEduTooltipView.ifPresent(new MenuViewLayer$$ExternalSyntheticLambda9(this, 0));
    }

    public final void onComputeInternalInsets(ViewTreeObserver.InternalInsetsInfo internalInsetsInfo) {
        internalInsetsInfo.setTouchableInsets(3);
        if (this.mEduTooltipView.isPresent()) {
            int x = (int) getX();
            int y = (int) getY();
            internalInsetsInfo.touchableRegion.union(new Rect(x, y, getWidth() + x, getHeight() + y));
        }
    }

    @Override // android.view.View, android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        DragToInteractView dragToInteractView = this.mDragToInteractView;
        DragToInteractView.Config config = dragToInteractView.config;
        if (config == null) {
            Log.e(DragToInteractView.TAG, "The view isn't ready. Should be called after `setup`");
        }
        if (config != null) {
            dragToInteractView.updatePadding();
            dragToInteractView.getLayoutParams().height = dragToInteractView.getResources().getDimensionPixelSize(R.dimen.floating_dismiss_gradient_height);
            int dimensionPixelSize = dragToInteractView.getResources().getDimensionPixelSize(R.dimen.dismiss_circle_size);
            Iterator it = dragToInteractView.interactMap.entrySet().iterator();
            while (it.hasNext()) {
                DismissCircleView dismissCircleView = (DismissCircleView) ((Pair) ((Map.Entry) it.next()).getValue()).getFirst();
                dismissCircleView.getLayoutParams().width = dimensionPixelSize;
                dismissCircleView.getLayoutParams().height = dimensionPixelSize;
                dismissCircleView.requestLayout();
            }
        }
        this.mDismissView.updateResources();
        this.mDragToInteractAnimationController.updateResources();
        MenuAnimationController menuAnimationController = this.mMenuAnimationController;
        menuAnimationController.cancelAnimation(DynamicAnimation.TRANSLATION_X);
        menuAnimationController.cancelAnimation(DynamicAnimation.TRANSLATION_Y);
        PointF pointF = menuAnimationController.mAnimationEndPosition;
        float f = pointF.x;
        MenuView menuView = menuAnimationController.mMenuView;
        menuView.setTranslationX(f);
        menuAnimationController.mAnimationEndPosition.x = f;
        float f2 = pointF.y;
        menuView.setTranslationY(f2);
        menuAnimationController.mAnimationEndPosition.y = f2;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        MenuView menuView = this.mMenuView;
        menuView.setVisibility(8);
        menuView.mBoundsInParent.setEmpty();
        MenuViewModel menuViewModel = menuView.mMenuViewModel;
        MutableLiveData mutableLiveData = menuViewModel.mPercentagePositionData;
        Objects.requireNonNull(mutableLiveData);
        mutableLiveData.setValue(menuViewModel.mInfoRepository.mPercentagePosition);
        mutableLiveData.removeObserver(menuView.mPercentagePositionObserver);
        MenuViewModel menuViewModel2 = menuView.mMenuViewModel;
        MutableLiveData mutableLiveData2 = menuViewModel2.mFadeEffectInfoData;
        Objects.requireNonNull(mutableLiveData2);
        SecureSettings secureSettings = menuViewModel2.mInfoRepository.mSecureSettings;
        mutableLiveData2.setValue(new MenuFadeEffectInfo(secureSettings.getFloatForUser(0.55f, -2, "accessibility_floating_menu_opacity"), secureSettings.getIntForUser("accessibility_floating_menu_fade_enabled", 1, -2) == 1));
        mutableLiveData2.removeObserver(menuView.mFadeEffectInfoObserver);
        MenuViewModel menuViewModel3 = menuView.mMenuViewModel;
        MutableLiveData mutableLiveData3 = menuViewModel3.mTargetFeaturesData;
        Objects.requireNonNull(mutableLiveData3);
        mutableLiveData3.setValue(AccessibilityTargetHelper.getTargets(menuViewModel3.mInfoRepository.mContext, 1));
        mutableLiveData3.removeObserver(menuView.mTargetFeaturesObserver);
        MenuViewModel menuViewModel4 = menuView.mMenuViewModel;
        MutableLiveData mutableLiveData4 = menuViewModel4.mSizeTypeData;
        Objects.requireNonNull(mutableLiveData4);
        mutableLiveData4.setValue(Integer.valueOf(menuViewModel4.mInfoRepository.mSecureSettings.getIntForUser("accessibility_floating_menu_size", 0, -2)));
        mutableLiveData4.removeObserver(menuView.mSizeTypeObserver);
        MenuViewModel menuViewModel5 = menuView.mMenuViewModel;
        MutableLiveData mutableLiveData5 = menuViewModel5.mMoveToTuckedData;
        Objects.requireNonNull(mutableLiveData5);
        mutableLiveData5.setValue(Boolean.valueOf(Prefs.getBoolean(menuViewModel5.mInfoRepository.mContext, "HasAccessibilityFloatingMenuTucked")));
        mutableLiveData5.removeObserver(menuView.mMoveToTuckedObserver);
        MenuInfoRepository menuInfoRepository = menuView.mMenuViewModel.mInfoRepository;
        menuInfoRepository.mContext.getContentResolver().unregisterContentObserver(menuInfoRepository.mMenuTargetFeaturesContentObserver);
        menuInfoRepository.mContext.getContentResolver().unregisterContentObserver(menuInfoRepository.mMenuSizeContentObserver);
        menuInfoRepository.mContext.getContentResolver().unregisterContentObserver(menuInfoRepository.mMenuFadeOutContentObserver);
        menuInfoRepository.mContext.unregisterComponentCallbacks(menuInfoRepository.mComponentCallbacks);
        menuView.getViewTreeObserver().removeOnComputeInternalInsetsListener(menuView);
        menuView.getViewTreeObserver().removeOnDrawListener(menuView.mSystemGestureExcludeUpdater);
        setOnClickListener(null);
        setOnApplyWindowInsetsListener(null);
        getViewTreeObserver().removeOnComputeInternalInsetsListener(this);
        MenuViewModel menuViewModel6 = this.mMenuViewModel;
        MutableLiveData mutableLiveData6 = menuViewModel6.mDockTooltipData;
        Objects.requireNonNull(mutableLiveData6);
        mutableLiveData6.setValue(Boolean.valueOf(Prefs.getBoolean(menuViewModel6.mInfoRepository.mContext, "HasSeenAccessibilityFloatingMenuDockTooltip")));
        mutableLiveData6.removeObserver(this.mDockTooltipObserver);
        this.mMenuViewModel.getMigrationTooltipVisibilityData().removeObserver(this.mMigrationTooltipObserver);
        this.mHandler.removeCallbacksAndMessages(null);
        getContext().unregisterComponentCallbacks(this);
        this.mNavigationModeController.removeListener(this.mNavigationModeChangedListender);
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        MenuView menuView = this.mMenuView;
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        if (!menuView.mIsMoveToTucked || !menuView.mBoundsInParent.contains(x, y)) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        menuView.mMenuAnimationController.fadeInNowIfEnabled();
        menuView.mMenuAnimationController.moveOutEdgeAndShow();
        menuView.mMenuAnimationController.fadeOutIfEnabled();
        return true;
    }

    public final void undo() {
        this.mHandler.removeCallbacksAndMessages(null);
        this.mMessageView.setVisibility(8);
        this.mMenuView.onEdgeChanged();
        this.mMenuView.onPositionChanged(false);
        this.mMenuView.setVisibility(0);
        MenuView menuView = this.mMenuAnimationController.mMenuView;
        menuView.animate().cancel();
        menuView.animate().scaleX(1.0f).scaleY(1.0f).alpha(1.0f).translationY(menuView.getTranslationY()).start();
    }

    @Override // android.content.ComponentCallbacks
    public final void onLowMemory() {
    }
}

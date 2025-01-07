package androidx.appcompat.app;

import android.R;
import android.app.Activity;
import android.app.Dialog;
import android.app.UiModeManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Insets;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.appcompat.R$styleable;
import androidx.appcompat.app.AppCompatDelegateImpl;
import androidx.appcompat.app.TwilightManager;
import androidx.appcompat.app.WindowDecorActionBar;
import androidx.appcompat.app.WindowDecorActionBar.ActionModeImpl;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.view.StandaloneActionMode;
import androidx.appcompat.view.SupportActionModeWrapper;
import androidx.appcompat.view.SupportMenuInflater;
import androidx.appcompat.view.menu.ListMenuPresenter;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.view.menu.MenuWrapperICS;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.ActionBarOverlayLayout;
import androidx.appcompat.widget.ActionMenuPresenter;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.AppCompatDrawableManager;
import androidx.appcompat.widget.ContentFrameLayout;
import androidx.appcompat.widget.DecorContentParent;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.ToolbarWidgetWrapper;
import androidx.appcompat.widget.ViewStubCompat;
import androidx.collection.SimpleArrayMap;
import androidx.core.app.NavUtils;
import androidx.core.content.PermissionChecker;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.core.view.ViewPropertyAnimatorListenerAdapter;
import androidx.core.view.WindowInsetsCompat;
import androidx.slice.compat.SlicePermissionActivity;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.List;
import java.util.WeakHashMap;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AppCompatDelegateImpl extends AppCompatDelegate implements MenuBuilder.Callback, LayoutInflater.Factory2 {
    public WindowDecorActionBar mActionBar;
    public AnonymousClass3 mActionMenuPresenterCallback;
    public ActionMode mActionMode;
    public PopupWindow mActionModePopup;
    public ActionBarContextView mActionModeView;
    public int mActivityHandlesConfigFlags;
    public boolean mActivityHandlesConfigFlagsChecked;
    public final AppCompatCallback mAppCompatCallback;
    public AppCompatViewInflater mAppCompatViewInflater;
    public AppCompatWindowCallback mAppCompatWindowCallback;
    public AutoTimeNightModeManager mAutoBatteryNightModeManager;
    public AutoTimeNightModeManager mAutoTimeNightModeManager;
    public AppCompatDelegateImpl$Api33Impl$$ExternalSyntheticLambda0 mBackCallback;
    public boolean mBaseContextAttached;
    public boolean mClosingActionMenu;
    public final Context mContext;
    public boolean mCreated;
    public DecorContentParent mDecorContentParent;
    public boolean mDestroyed;
    public OnBackInvokedDispatcher mDispatcher;
    public Configuration mEffectiveConfiguration;
    public boolean mEnableDefaultActionBarUp;
    public boolean mFeatureIndeterminateProgress;
    public boolean mFeatureProgress;
    public boolean mHasActionBar;
    public final Object mHost;
    public int mInvalidatePanelMenuFeatures;
    public boolean mInvalidatePanelMenuPosted;
    public boolean mIsFloating;
    public final int mLocalNightMode;
    public boolean mLongPressBackDown;
    public SupportMenuInflater mMenuInflater;
    public boolean mOverlayActionBar;
    public boolean mOverlayActionMode;
    public AnonymousClass3 mPanelMenuPresenterCallback;
    public PanelFeatureState[] mPanels;
    public PanelFeatureState mPreparedPanel;
    public AnonymousClass2 mShowActionModePopup;
    public View mStatusGuard;
    public ViewGroup mSubDecor;
    public boolean mSubDecorInstalled;
    public Rect mTempRect1;
    public Rect mTempRect2;
    public int mThemeResId;
    public CharSequence mTitle;
    public TextView mTitleView;
    public Window mWindow;
    public boolean mWindowNoTitle;
    public static final SimpleArrayMap sLocalNightModes = new SimpleArrayMap(0);
    public static final int[] sWindowBackgroundStyleable = {R.attr.windowBackground};
    public static final boolean sCanReturnDifferentContext = !"robolectric".equals(Build.FINGERPRINT);
    public ViewPropertyAnimatorCompat mFadeAnim = null;
    public final boolean mHandleNativeActionModes = true;
    public final AnonymousClass2 mInvalidatePanelMenuRunnable = new AnonymousClass2(this, 0);

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: androidx.appcompat.app.AppCompatDelegateImpl$2, reason: invalid class name */
    public final class AnonymousClass2 implements Runnable {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ AppCompatDelegateImpl this$0;

        public /* synthetic */ AnonymousClass2(AppCompatDelegateImpl appCompatDelegateImpl, int i) {
            this.$r8$classId = i;
            this.this$0 = appCompatDelegateImpl;
        }

        @Override // java.lang.Runnable
        public final void run() {
            ViewGroup viewGroup;
            switch (this.$r8$classId) {
                case 0:
                    AppCompatDelegateImpl appCompatDelegateImpl = this.this$0;
                    if ((appCompatDelegateImpl.mInvalidatePanelMenuFeatures & 1) != 0) {
                        appCompatDelegateImpl.doInvalidatePanelMenu(0);
                    }
                    AppCompatDelegateImpl appCompatDelegateImpl2 = this.this$0;
                    if ((appCompatDelegateImpl2.mInvalidatePanelMenuFeatures & 4096) != 0) {
                        appCompatDelegateImpl2.doInvalidatePanelMenu(108);
                    }
                    AppCompatDelegateImpl appCompatDelegateImpl3 = this.this$0;
                    appCompatDelegateImpl3.mInvalidatePanelMenuPosted = false;
                    appCompatDelegateImpl3.mInvalidatePanelMenuFeatures = 0;
                    break;
                default:
                    AppCompatDelegateImpl appCompatDelegateImpl4 = this.this$0;
                    appCompatDelegateImpl4.mActionModePopup.showAtLocation(appCompatDelegateImpl4.mActionModeView, 55, 0, 0);
                    ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = this.this$0.mFadeAnim;
                    if (viewPropertyAnimatorCompat != null) {
                        viewPropertyAnimatorCompat.cancel();
                    }
                    AppCompatDelegateImpl appCompatDelegateImpl5 = this.this$0;
                    if (!(appCompatDelegateImpl5.mSubDecorInstalled && (viewGroup = appCompatDelegateImpl5.mSubDecor) != null && viewGroup.isLaidOut())) {
                        this.this$0.mActionModeView.setAlpha(1.0f);
                        this.this$0.mActionModeView.setVisibility(0);
                        break;
                    } else {
                        this.this$0.mActionModeView.setAlpha(0.0f);
                        AppCompatDelegateImpl appCompatDelegateImpl6 = this.this$0;
                        ViewPropertyAnimatorCompat animate = ViewCompat.animate(appCompatDelegateImpl6.mActionModeView);
                        animate.alpha(1.0f);
                        appCompatDelegateImpl6.mFadeAnim = animate;
                        this.this$0.mFadeAnim.setListener(new AnonymousClass7(1, this));
                        break;
                    }
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: androidx.appcompat.app.AppCompatDelegateImpl$3, reason: invalid class name */
    public final class AnonymousClass3 implements OnApplyWindowInsetsListener, MenuPresenter.Callback {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ AppCompatDelegateImpl this$0;

        public /* synthetic */ AnonymousClass3(AppCompatDelegateImpl appCompatDelegateImpl, int i) {
            this.$r8$classId = i;
            this.this$0 = appCompatDelegateImpl;
        }

        @Override // androidx.core.view.OnApplyWindowInsetsListener
        public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
            boolean z;
            View view2;
            WindowInsetsCompat windowInsetsCompat2;
            boolean z2;
            int systemWindowInsetTop = windowInsetsCompat.getSystemWindowInsetTop();
            AppCompatDelegateImpl appCompatDelegateImpl = this.this$0;
            appCompatDelegateImpl.getClass();
            int systemWindowInsetTop2 = windowInsetsCompat.getSystemWindowInsetTop();
            ActionBarContextView actionBarContextView = appCompatDelegateImpl.mActionModeView;
            if (actionBarContextView == null || !(actionBarContextView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
                z = false;
            } else {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) appCompatDelegateImpl.mActionModeView.getLayoutParams();
                if (appCompatDelegateImpl.mActionModeView.isShown()) {
                    if (appCompatDelegateImpl.mTempRect1 == null) {
                        appCompatDelegateImpl.mTempRect1 = new Rect();
                        appCompatDelegateImpl.mTempRect2 = new Rect();
                    }
                    Rect rect = appCompatDelegateImpl.mTempRect1;
                    Rect rect2 = appCompatDelegateImpl.mTempRect2;
                    rect.set(windowInsetsCompat.getSystemWindowInsetLeft(), windowInsetsCompat.getSystemWindowInsetTop(), windowInsetsCompat.getSystemWindowInsetRight(), windowInsetsCompat.getSystemWindowInsetBottom());
                    Insets systemWindowInsets = appCompatDelegateImpl.mSubDecor.computeSystemWindowInsets(new WindowInsets.Builder().setSystemWindowInsets(Insets.of(rect)).build(), rect2).getSystemWindowInsets();
                    rect.set(systemWindowInsets.left, systemWindowInsets.top, systemWindowInsets.right, systemWindowInsets.bottom);
                    int i = rect.top;
                    int i2 = rect.left;
                    int i3 = rect.right;
                    ViewGroup viewGroup = appCompatDelegateImpl.mSubDecor;
                    WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                    WindowInsetsCompat rootWindowInsets = ViewCompat.Api23Impl.getRootWindowInsets(viewGroup);
                    int systemWindowInsetLeft = rootWindowInsets == null ? 0 : rootWindowInsets.getSystemWindowInsetLeft();
                    int systemWindowInsetRight = rootWindowInsets == null ? 0 : rootWindowInsets.getSystemWindowInsetRight();
                    if (marginLayoutParams.topMargin == i && marginLayoutParams.leftMargin == i2 && marginLayoutParams.rightMargin == i3) {
                        z2 = false;
                    } else {
                        marginLayoutParams.topMargin = i;
                        marginLayoutParams.leftMargin = i2;
                        marginLayoutParams.rightMargin = i3;
                        z2 = true;
                    }
                    if (i <= 0 || appCompatDelegateImpl.mStatusGuard != null) {
                        View view3 = appCompatDelegateImpl.mStatusGuard;
                        if (view3 != null) {
                            ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) view3.getLayoutParams();
                            int i4 = marginLayoutParams2.height;
                            int i5 = marginLayoutParams.topMargin;
                            if (i4 != i5 || marginLayoutParams2.leftMargin != systemWindowInsetLeft || marginLayoutParams2.rightMargin != systemWindowInsetRight) {
                                marginLayoutParams2.height = i5;
                                marginLayoutParams2.leftMargin = systemWindowInsetLeft;
                                marginLayoutParams2.rightMargin = systemWindowInsetRight;
                                appCompatDelegateImpl.mStatusGuard.setLayoutParams(marginLayoutParams2);
                            }
                        }
                    } else {
                        View view4 = new View(appCompatDelegateImpl.mContext);
                        appCompatDelegateImpl.mStatusGuard = view4;
                        view4.setVisibility(8);
                        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, marginLayoutParams.topMargin, 51);
                        layoutParams.leftMargin = systemWindowInsetLeft;
                        layoutParams.rightMargin = systemWindowInsetRight;
                        appCompatDelegateImpl.mSubDecor.addView(appCompatDelegateImpl.mStatusGuard, -1, layoutParams);
                    }
                    View view5 = appCompatDelegateImpl.mStatusGuard;
                    r7 = view5 != null;
                    if (r7 && view5.getVisibility() != 0) {
                        View view6 = appCompatDelegateImpl.mStatusGuard;
                        view6.setBackgroundColor((view6.getWindowSystemUiVisibility() & 8192) != 0 ? appCompatDelegateImpl.mContext.getColor(com.android.wm.shell.R.color.abc_decor_view_status_guard_light) : appCompatDelegateImpl.mContext.getColor(com.android.wm.shell.R.color.abc_decor_view_status_guard));
                    }
                    if (!appCompatDelegateImpl.mOverlayActionMode && r7) {
                        systemWindowInsetTop2 = 0;
                    }
                    boolean z3 = r7;
                    r7 = z2;
                    z = z3;
                } else if (marginLayoutParams.topMargin != 0) {
                    marginLayoutParams.topMargin = 0;
                    z = false;
                } else {
                    z = false;
                    r7 = false;
                }
                if (r7) {
                    appCompatDelegateImpl.mActionModeView.setLayoutParams(marginLayoutParams);
                }
            }
            View view7 = appCompatDelegateImpl.mStatusGuard;
            if (view7 != null) {
                view7.setVisibility(z ? 0 : 8);
            }
            if (systemWindowInsetTop != systemWindowInsetTop2) {
                int systemWindowInsetLeft2 = windowInsetsCompat.getSystemWindowInsetLeft();
                int systemWindowInsetRight2 = windowInsetsCompat.getSystemWindowInsetRight();
                int systemWindowInsetBottom = windowInsetsCompat.getSystemWindowInsetBottom();
                WindowInsetsCompat.BuilderImpl30 builderImpl30 = new WindowInsetsCompat.BuilderImpl30(windowInsetsCompat);
                builderImpl30.setSystemWindowInsets(androidx.core.graphics.Insets.of(systemWindowInsetLeft2, systemWindowInsetTop2, systemWindowInsetRight2, systemWindowInsetBottom));
                windowInsetsCompat2 = builderImpl30.build();
                view2 = view;
            } else {
                view2 = view;
                windowInsetsCompat2 = windowInsetsCompat;
            }
            return ViewCompat.onApplyWindowInsets(view2, windowInsetsCompat2);
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter.Callback
        public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
            PanelFeatureState panelFeatureState;
            switch (this.$r8$classId) {
                case 2:
                    this.this$0.checkCloseActionMenu(menuBuilder);
                    break;
                default:
                    MenuBuilder rootMenu = menuBuilder.getRootMenu();
                    int i = 0;
                    boolean z2 = rootMenu != menuBuilder;
                    if (z2) {
                        menuBuilder = rootMenu;
                    }
                    AppCompatDelegateImpl appCompatDelegateImpl = this.this$0;
                    PanelFeatureState[] panelFeatureStateArr = appCompatDelegateImpl.mPanels;
                    int length = panelFeatureStateArr != null ? panelFeatureStateArr.length : 0;
                    while (true) {
                        if (i >= length) {
                            panelFeatureState = null;
                        } else {
                            panelFeatureState = panelFeatureStateArr[i];
                            if (panelFeatureState == null || panelFeatureState.menu != menuBuilder) {
                                i++;
                            }
                        }
                    }
                    if (panelFeatureState != null) {
                        if (!z2) {
                            appCompatDelegateImpl.closePanel(panelFeatureState, z);
                            break;
                        } else {
                            appCompatDelegateImpl.callOnPanelClosed(panelFeatureState.featureId, panelFeatureState, rootMenu);
                            appCompatDelegateImpl.closePanel(panelFeatureState, true);
                            break;
                        }
                    }
                    break;
            }
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter.Callback
        public boolean onOpenSubMenu(MenuBuilder menuBuilder) {
            Window.Callback callback;
            switch (this.$r8$classId) {
                case 2:
                    Window.Callback callback2 = this.this$0.mWindow.getCallback();
                    if (callback2 != null) {
                        callback2.onMenuOpened(108, menuBuilder);
                        break;
                    }
                    break;
                default:
                    if (menuBuilder == menuBuilder.getRootMenu()) {
                        AppCompatDelegateImpl appCompatDelegateImpl = this.this$0;
                        if (appCompatDelegateImpl.mHasActionBar && (callback = appCompatDelegateImpl.mWindow.getCallback()) != null && !appCompatDelegateImpl.mDestroyed) {
                            callback.onMenuOpened(108, menuBuilder);
                            break;
                        }
                    }
                    break;
            }
            return true;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: androidx.appcompat.app.AppCompatDelegateImpl$7, reason: invalid class name */
    public final class AnonymousClass7 extends ViewPropertyAnimatorListenerAdapter {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ Object this$0;

        public /* synthetic */ AnonymousClass7(int i, Object obj) {
            this.$r8$classId = i;
            this.this$0 = obj;
        }

        @Override // androidx.core.view.ViewPropertyAnimatorListener
        public final void onAnimationEnd() {
            Object obj = this.this$0;
            switch (this.$r8$classId) {
                case 0:
                    AppCompatDelegateImpl appCompatDelegateImpl = (AppCompatDelegateImpl) obj;
                    appCompatDelegateImpl.mActionModeView.setAlpha(1.0f);
                    appCompatDelegateImpl.mFadeAnim.setListener(null);
                    appCompatDelegateImpl.mFadeAnim = null;
                    break;
                case 1:
                    AnonymousClass2 anonymousClass2 = (AnonymousClass2) obj;
                    anonymousClass2.this$0.mActionModeView.setAlpha(1.0f);
                    anonymousClass2.this$0.mFadeAnim.setListener(null);
                    anonymousClass2.this$0.mFadeAnim = null;
                    break;
                default:
                    ActionModeCallbackWrapperV9 actionModeCallbackWrapperV9 = (ActionModeCallbackWrapperV9) obj;
                    AppCompatDelegateImpl.this.mActionModeView.setVisibility(8);
                    AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
                    PopupWindow popupWindow = appCompatDelegateImpl2.mActionModePopup;
                    if (popupWindow != null) {
                        popupWindow.dismiss();
                    } else if (appCompatDelegateImpl2.mActionModeView.getParent() instanceof View) {
                        View view = (View) appCompatDelegateImpl2.mActionModeView.getParent();
                        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                        ViewCompat.Api20Impl.requestApplyInsets(view);
                    }
                    appCompatDelegateImpl2.mActionModeView.killMode();
                    appCompatDelegateImpl2.mFadeAnim.setListener(null);
                    appCompatDelegateImpl2.mFadeAnim = null;
                    ViewGroup viewGroup = appCompatDelegateImpl2.mSubDecor;
                    WeakHashMap weakHashMap2 = ViewCompat.sViewPropertyAnimatorMap;
                    ViewCompat.Api20Impl.requestApplyInsets(viewGroup);
                    break;
            }
        }

        @Override // androidx.core.view.ViewPropertyAnimatorListenerAdapter, androidx.core.view.ViewPropertyAnimatorListener
        public void onAnimationStart() {
            Object obj = this.this$0;
            switch (this.$r8$classId) {
                case 0:
                    AppCompatDelegateImpl appCompatDelegateImpl = (AppCompatDelegateImpl) obj;
                    appCompatDelegateImpl.mActionModeView.setVisibility(0);
                    if (appCompatDelegateImpl.mActionModeView.getParent() instanceof View) {
                        View view = (View) appCompatDelegateImpl.mActionModeView.getParent();
                        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                        ViewCompat.Api20Impl.requestApplyInsets(view);
                        break;
                    }
                    break;
                case 1:
                    ((AnonymousClass2) obj).this$0.mActionModeView.setVisibility(0);
                    break;
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ActionModeCallbackWrapperV9 {
        public final SupportActionModeWrapper.CallbackWrapper mWrapped;

        public ActionModeCallbackWrapperV9(SupportActionModeWrapper.CallbackWrapper callbackWrapper) {
            this.mWrapped = callbackWrapper;
        }

        public final void onDestroyActionMode(ActionMode actionMode) {
            SupportActionModeWrapper.CallbackWrapper callbackWrapper = this.mWrapped;
            callbackWrapper.mWrappedCallback.onDestroyActionMode(callbackWrapper.getActionModeWrapper(actionMode));
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (appCompatDelegateImpl.mActionModePopup != null) {
                appCompatDelegateImpl.mWindow.getDecorView().removeCallbacks(appCompatDelegateImpl.mShowActionModePopup);
            }
            if (appCompatDelegateImpl.mActionModeView != null) {
                ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = appCompatDelegateImpl.mFadeAnim;
                if (viewPropertyAnimatorCompat != null) {
                    viewPropertyAnimatorCompat.cancel();
                }
                ViewPropertyAnimatorCompat animate = ViewCompat.animate(appCompatDelegateImpl.mActionModeView);
                animate.alpha(0.0f);
                appCompatDelegateImpl.mFadeAnim = animate;
                animate.setListener(new AnonymousClass7(2, this));
            }
            appCompatDelegateImpl.mActionMode = null;
            ViewGroup viewGroup = appCompatDelegateImpl.mSubDecor;
            WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
            ViewCompat.Api20Impl.requestApplyInsets(viewGroup);
            appCompatDelegateImpl.updateBackInvokedCallbackState();
        }

        public final boolean onPrepareActionMode(ActionMode actionMode, MenuBuilder menuBuilder) {
            ViewGroup viewGroup = AppCompatDelegateImpl.this.mSubDecor;
            WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
            ViewCompat.Api20Impl.requestApplyInsets(viewGroup);
            SupportActionModeWrapper.CallbackWrapper callbackWrapper = this.mWrapped;
            ActionMode.Callback callback = callbackWrapper.mWrappedCallback;
            SupportActionModeWrapper actionModeWrapper = callbackWrapper.getActionModeWrapper(actionMode);
            SimpleArrayMap simpleArrayMap = callbackWrapper.mMenus;
            Menu menu = (Menu) simpleArrayMap.get(menuBuilder);
            if (menu == null) {
                menu = new MenuWrapperICS(callbackWrapper.mContext, menuBuilder);
                simpleArrayMap.put(menuBuilder, menu);
            }
            return callback.onPrepareActionMode(actionModeWrapper, menu);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class AppCompatWindowCallback implements Window.Callback {
        public boolean mDispatchKeyEventBypassEnabled;
        public boolean mOnContentChangedBypassEnabled;
        public boolean mOnPanelClosedBypassEnabled;
        public final Window.Callback mWrapped;

        public AppCompatWindowCallback(Window.Callback callback) {
            if (callback == null) {
                throw new IllegalArgumentException("Window callback may not be null");
            }
            this.mWrapped = callback;
        }

        public final void bypassOnContentChanged(Window.Callback callback) {
            try {
                this.mOnContentChangedBypassEnabled = true;
                callback.onContentChanged();
            } finally {
                this.mOnContentChangedBypassEnabled = false;
            }
        }

        @Override // android.view.Window.Callback
        public final boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
            return this.mWrapped.dispatchGenericMotionEvent(motionEvent);
        }

        @Override // android.view.Window.Callback
        public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return this.mDispatchKeyEventBypassEnabled ? this.mWrapped.dispatchKeyEvent(keyEvent) : AppCompatDelegateImpl.this.dispatchKeyEvent(keyEvent) || this.mWrapped.dispatchKeyEvent(keyEvent);
        }

        /* JADX WARN: Code restructure failed: missing block: B:32:0x0067, code lost:
        
            if (r5 != false) goto L17;
         */
        /* JADX WARN: Code restructure failed: missing block: B:8:0x0039, code lost:
        
            if (r0 != false) goto L17;
         */
        /* JADX WARN: Removed duplicated region for block: B:11:0x006e  */
        /* JADX WARN: Removed duplicated region for block: B:14:? A[RETURN, SYNTHETIC] */
        @Override // android.view.Window.Callback
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final boolean dispatchKeyShortcutEvent(android.view.KeyEvent r6) {
            /*
                r5 = this;
                android.view.Window$Callback r0 = r5.mWrapped
                boolean r0 = r0.dispatchKeyShortcutEvent(r6)
                r1 = 1
                if (r0 != 0) goto L6f
                androidx.appcompat.app.AppCompatDelegateImpl r5 = androidx.appcompat.app.AppCompatDelegateImpl.this
                int r0 = r6.getKeyCode()
                r5.initWindowDecorActionBar()
                androidx.appcompat.app.WindowDecorActionBar r2 = r5.mActionBar
                r3 = 0
                if (r2 == 0) goto L3d
                androidx.appcompat.app.WindowDecorActionBar$ActionModeImpl r2 = r2.mActionMode
                if (r2 != 0) goto L1d
            L1b:
                r0 = r3
                goto L39
            L1d:
                androidx.appcompat.view.menu.MenuBuilder r2 = r2.mMenu
                if (r2 == 0) goto L1b
                int r4 = r6.getDeviceId()
                android.view.KeyCharacterMap r4 = android.view.KeyCharacterMap.load(r4)
                int r4 = r4.getKeyboardType()
                if (r4 == r1) goto L31
                r4 = r1
                goto L32
            L31:
                r4 = r3
            L32:
                r2.setQwertyMode(r4)
                boolean r0 = r2.performShortcut(r0, r6, r3)
            L39:
                if (r0 == 0) goto L3d
            L3b:
                r5 = r1
                goto L6b
            L3d:
                androidx.appcompat.app.AppCompatDelegateImpl$PanelFeatureState r0 = r5.mPreparedPanel
                if (r0 == 0) goto L52
                int r2 = r6.getKeyCode()
                boolean r0 = r5.performPanelShortcut(r0, r2, r6)
                if (r0 == 0) goto L52
                androidx.appcompat.app.AppCompatDelegateImpl$PanelFeatureState r5 = r5.mPreparedPanel
                if (r5 == 0) goto L3b
                r5.isHandled = r1
                goto L3b
            L52:
                androidx.appcompat.app.AppCompatDelegateImpl$PanelFeatureState r0 = r5.mPreparedPanel
                if (r0 != 0) goto L6a
                androidx.appcompat.app.AppCompatDelegateImpl$PanelFeatureState r0 = r5.getPanelState(r3)
                r5.preparePanel(r0, r6)
                int r2 = r6.getKeyCode()
                boolean r5 = r5.performPanelShortcut(r0, r2, r6)
                r0.isPrepared = r3
                if (r5 == 0) goto L6a
                goto L3b
            L6a:
                r5 = r3
            L6b:
                if (r5 == 0) goto L6e
                goto L6f
            L6e:
                r1 = r3
            L6f:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.AppCompatWindowCallback.dispatchKeyShortcutEvent(android.view.KeyEvent):boolean");
        }

        @Override // android.view.Window.Callback
        public final boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
            return this.mWrapped.dispatchPopulateAccessibilityEvent(accessibilityEvent);
        }

        @Override // android.view.Window.Callback
        public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
            return this.mWrapped.dispatchTouchEvent(motionEvent);
        }

        @Override // android.view.Window.Callback
        public final boolean dispatchTrackballEvent(MotionEvent motionEvent) {
            return this.mWrapped.dispatchTrackballEvent(motionEvent);
        }

        @Override // android.view.Window.Callback
        public final void onActionModeFinished(android.view.ActionMode actionMode) {
            this.mWrapped.onActionModeFinished(actionMode);
        }

        @Override // android.view.Window.Callback
        public final void onActionModeStarted(android.view.ActionMode actionMode) {
            this.mWrapped.onActionModeStarted(actionMode);
        }

        @Override // android.view.Window.Callback
        public final void onAttachedToWindow() {
            this.mWrapped.onAttachedToWindow();
        }

        @Override // android.view.Window.Callback
        public final void onContentChanged() {
            if (this.mOnContentChangedBypassEnabled) {
                this.mWrapped.onContentChanged();
            }
        }

        @Override // android.view.Window.Callback
        public final boolean onCreatePanelMenu(int i, Menu menu) {
            if (i != 0 || (menu instanceof MenuBuilder)) {
                return this.mWrapped.onCreatePanelMenu(i, menu);
            }
            return false;
        }

        @Override // android.view.Window.Callback
        public final View onCreatePanelView(int i) {
            return this.mWrapped.onCreatePanelView(i);
        }

        @Override // android.view.Window.Callback
        public final void onDetachedFromWindow() {
            this.mWrapped.onDetachedFromWindow();
        }

        @Override // android.view.Window.Callback
        public final boolean onMenuItemSelected(int i, MenuItem menuItem) {
            return this.mWrapped.onMenuItemSelected(i, menuItem);
        }

        @Override // android.view.Window.Callback
        public final boolean onMenuOpened(int i, Menu menu) {
            onMenuOpened$androidx$appcompat$view$WindowCallbackWrapper(i, menu);
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (i == 108) {
                appCompatDelegateImpl.initWindowDecorActionBar();
                WindowDecorActionBar windowDecorActionBar = appCompatDelegateImpl.mActionBar;
                if (windowDecorActionBar != null && true != windowDecorActionBar.mLastMenuVisibility) {
                    windowDecorActionBar.mLastMenuVisibility = true;
                    if (windowDecorActionBar.mMenuVisibilityListeners.size() > 0) {
                        windowDecorActionBar.mMenuVisibilityListeners.get(0).getClass();
                        throw new ClassCastException();
                    }
                }
            } else {
                appCompatDelegateImpl.getClass();
            }
            return true;
        }

        public final boolean onMenuOpened$androidx$appcompat$view$WindowCallbackWrapper(int i, Menu menu) {
            return this.mWrapped.onMenuOpened(i, menu);
        }

        @Override // android.view.Window.Callback
        public final void onPanelClosed(int i, Menu menu) {
            if (this.mOnPanelClosedBypassEnabled) {
                this.mWrapped.onPanelClosed(i, menu);
                return;
            }
            onPanelClosed$androidx$appcompat$view$WindowCallbackWrapper(i, menu);
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (i != 108) {
                if (i != 0) {
                    appCompatDelegateImpl.getClass();
                    return;
                }
                PanelFeatureState panelState = appCompatDelegateImpl.getPanelState(i);
                if (panelState.isOpen) {
                    appCompatDelegateImpl.closePanel(panelState, false);
                    return;
                }
                return;
            }
            appCompatDelegateImpl.initWindowDecorActionBar();
            WindowDecorActionBar windowDecorActionBar = appCompatDelegateImpl.mActionBar;
            if (windowDecorActionBar == null || !windowDecorActionBar.mLastMenuVisibility) {
                return;
            }
            windowDecorActionBar.mLastMenuVisibility = false;
            if (windowDecorActionBar.mMenuVisibilityListeners.size() <= 0) {
                return;
            }
            windowDecorActionBar.mMenuVisibilityListeners.get(0).getClass();
            throw new ClassCastException();
        }

        public final void onPanelClosed$androidx$appcompat$view$WindowCallbackWrapper(int i, Menu menu) {
            this.mWrapped.onPanelClosed(i, menu);
        }

        @Override // android.view.Window.Callback
        public final void onPointerCaptureChanged(boolean z) {
            this.mWrapped.onPointerCaptureChanged(z);
        }

        @Override // android.view.Window.Callback
        public final boolean onPreparePanel(int i, View view, Menu menu) {
            MenuBuilder menuBuilder = menu instanceof MenuBuilder ? (MenuBuilder) menu : null;
            if (i == 0 && menuBuilder == null) {
                return false;
            }
            if (menuBuilder != null) {
                menuBuilder.mOverrideVisibleItems = true;
            }
            boolean onPreparePanel = this.mWrapped.onPreparePanel(i, view, menu);
            if (menuBuilder != null) {
                menuBuilder.mOverrideVisibleItems = false;
            }
            return onPreparePanel;
        }

        @Override // android.view.Window.Callback
        public final void onProvideKeyboardShortcuts(List list, Menu menu, int i) {
            MenuBuilder menuBuilder = AppCompatDelegateImpl.this.getPanelState(0).menu;
            if (menuBuilder != null) {
                onProvideKeyboardShortcuts$androidx$appcompat$view$WindowCallbackWrapper(list, menuBuilder, i);
            } else {
                onProvideKeyboardShortcuts$androidx$appcompat$view$WindowCallbackWrapper(list, menu, i);
            }
        }

        public final void onProvideKeyboardShortcuts$androidx$appcompat$view$WindowCallbackWrapper(List list, Menu menu, int i) {
            this.mWrapped.onProvideKeyboardShortcuts(list, menu, i);
        }

        @Override // android.view.Window.Callback
        public final boolean onSearchRequested(SearchEvent searchEvent) {
            return this.mWrapped.onSearchRequested(searchEvent);
        }

        @Override // android.view.Window.Callback
        public final void onWindowAttributesChanged(WindowManager.LayoutParams layoutParams) {
            this.mWrapped.onWindowAttributesChanged(layoutParams);
        }

        @Override // android.view.Window.Callback
        public final void onWindowFocusChanged(boolean z) {
            this.mWrapped.onWindowFocusChanged(z);
        }

        @Override // android.view.Window.Callback
        public final android.view.ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
            return null;
        }

        @Override // android.view.Window.Callback
        public final android.view.ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i) {
            ViewGroup viewGroup;
            Context context;
            int i2 = 0;
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (!appCompatDelegateImpl.mHandleNativeActionModes || i != 0) {
                return this.mWrapped.onWindowStartingActionMode(callback, i);
            }
            SupportActionModeWrapper.CallbackWrapper callbackWrapper = new SupportActionModeWrapper.CallbackWrapper(appCompatDelegateImpl.mContext, callback);
            AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
            androidx.appcompat.view.ActionMode actionMode = appCompatDelegateImpl2.mActionMode;
            if (actionMode != null) {
                actionMode.finish();
            }
            ActionModeCallbackWrapperV9 actionModeCallbackWrapperV9 = appCompatDelegateImpl2.new ActionModeCallbackWrapperV9(callbackWrapper);
            appCompatDelegateImpl2.initWindowDecorActionBar();
            WindowDecorActionBar windowDecorActionBar = appCompatDelegateImpl2.mActionBar;
            if (windowDecorActionBar != null) {
                WindowDecorActionBar.ActionModeImpl actionModeImpl = windowDecorActionBar.mActionMode;
                if (actionModeImpl != null) {
                    actionModeImpl.finish();
                }
                windowDecorActionBar.mOverlayLayout.setHideOnContentScrollEnabled(false);
                windowDecorActionBar.mContextView.killMode();
                WindowDecorActionBar.ActionModeImpl actionModeImpl2 = windowDecorActionBar.new ActionModeImpl(windowDecorActionBar.mContextView.getContext(), actionModeCallbackWrapperV9);
                MenuBuilder menuBuilder = actionModeImpl2.mMenu;
                menuBuilder.stopDispatchingItemsChanged();
                try {
                    if (actionModeImpl2.mCallback.mWrapped.onCreateActionMode(actionModeImpl2, menuBuilder)) {
                        windowDecorActionBar.mActionMode = actionModeImpl2;
                        actionModeImpl2.invalidate();
                        windowDecorActionBar.mContextView.initForMode(actionModeImpl2);
                        windowDecorActionBar.animateToMode(true);
                    } else {
                        actionModeImpl2 = null;
                    }
                    appCompatDelegateImpl2.mActionMode = actionModeImpl2;
                } finally {
                    menuBuilder.startDispatchingItemsChanged();
                }
            }
            if (appCompatDelegateImpl2.mActionMode == null) {
                ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = appCompatDelegateImpl2.mFadeAnim;
                if (viewPropertyAnimatorCompat != null) {
                    viewPropertyAnimatorCompat.cancel();
                }
                androidx.appcompat.view.ActionMode actionMode2 = appCompatDelegateImpl2.mActionMode;
                if (actionMode2 != null) {
                    actionMode2.finish();
                }
                if (appCompatDelegateImpl2.mActionModeView == null) {
                    if (appCompatDelegateImpl2.mIsFloating) {
                        TypedValue typedValue = new TypedValue();
                        Resources.Theme theme = appCompatDelegateImpl2.mContext.getTheme();
                        theme.resolveAttribute(com.android.wm.shell.R.attr.actionBarTheme, typedValue, true);
                        if (typedValue.resourceId != 0) {
                            Resources.Theme newTheme = appCompatDelegateImpl2.mContext.getResources().newTheme();
                            newTheme.setTo(theme);
                            newTheme.applyStyle(typedValue.resourceId, true);
                            context = new ContextThemeWrapper(0, appCompatDelegateImpl2.mContext);
                            context.getTheme().setTo(newTheme);
                        } else {
                            context = appCompatDelegateImpl2.mContext;
                        }
                        appCompatDelegateImpl2.mActionModeView = new ActionBarContextView(context);
                        PopupWindow popupWindow = new PopupWindow(context, (AttributeSet) null, com.android.wm.shell.R.attr.actionModePopupWindowStyle);
                        appCompatDelegateImpl2.mActionModePopup = popupWindow;
                        popupWindow.setWindowLayoutType(2);
                        appCompatDelegateImpl2.mActionModePopup.setContentView(appCompatDelegateImpl2.mActionModeView);
                        appCompatDelegateImpl2.mActionModePopup.setWidth(-1);
                        context.getTheme().resolveAttribute(com.android.wm.shell.R.attr.actionBarSize, typedValue, true);
                        appCompatDelegateImpl2.mActionModeView.mContentHeight = TypedValue.complexToDimensionPixelSize(typedValue.data, context.getResources().getDisplayMetrics());
                        appCompatDelegateImpl2.mActionModePopup.setHeight(-2);
                        appCompatDelegateImpl2.mShowActionModePopup = new AnonymousClass2(appCompatDelegateImpl2, r0);
                    } else {
                        ViewStubCompat viewStubCompat = (ViewStubCompat) appCompatDelegateImpl2.mSubDecor.findViewById(com.android.wm.shell.R.id.action_mode_bar_stub);
                        if (viewStubCompat != null) {
                            appCompatDelegateImpl2.initWindowDecorActionBar();
                            WindowDecorActionBar windowDecorActionBar2 = appCompatDelegateImpl2.mActionBar;
                            Context themedContext = windowDecorActionBar2 != null ? windowDecorActionBar2.getThemedContext() : null;
                            if (themedContext == null) {
                                themedContext = appCompatDelegateImpl2.mContext;
                            }
                            viewStubCompat.mInflater = LayoutInflater.from(themedContext);
                            appCompatDelegateImpl2.mActionModeView = (ActionBarContextView) viewStubCompat.inflate();
                        }
                    }
                }
                if (appCompatDelegateImpl2.mActionModeView != null) {
                    ViewPropertyAnimatorCompat viewPropertyAnimatorCompat2 = appCompatDelegateImpl2.mFadeAnim;
                    if (viewPropertyAnimatorCompat2 != null) {
                        viewPropertyAnimatorCompat2.cancel();
                    }
                    appCompatDelegateImpl2.mActionModeView.killMode();
                    Context context2 = appCompatDelegateImpl2.mActionModeView.getContext();
                    ActionBarContextView actionBarContextView = appCompatDelegateImpl2.mActionModeView;
                    StandaloneActionMode standaloneActionMode = new StandaloneActionMode();
                    standaloneActionMode.mContext = context2;
                    standaloneActionMode.mContextView = actionBarContextView;
                    standaloneActionMode.mCallback = actionModeCallbackWrapperV9;
                    MenuBuilder menuBuilder2 = new MenuBuilder(actionBarContextView.getContext());
                    menuBuilder2.mDefaultShowAsAction = 1;
                    standaloneActionMode.mMenu = menuBuilder2;
                    menuBuilder2.mCallback = standaloneActionMode;
                    if (actionModeCallbackWrapperV9.mWrapped.onCreateActionMode(standaloneActionMode, menuBuilder2)) {
                        standaloneActionMode.invalidate();
                        appCompatDelegateImpl2.mActionModeView.initForMode(standaloneActionMode);
                        appCompatDelegateImpl2.mActionMode = standaloneActionMode;
                        if (((appCompatDelegateImpl2.mSubDecorInstalled && (viewGroup = appCompatDelegateImpl2.mSubDecor) != null && viewGroup.isLaidOut()) ? 1 : 0) != 0) {
                            appCompatDelegateImpl2.mActionModeView.setAlpha(0.0f);
                            ViewPropertyAnimatorCompat animate = ViewCompat.animate(appCompatDelegateImpl2.mActionModeView);
                            animate.alpha(1.0f);
                            appCompatDelegateImpl2.mFadeAnim = animate;
                            animate.setListener(new AnonymousClass7(i2, appCompatDelegateImpl2));
                        } else {
                            appCompatDelegateImpl2.mActionModeView.setAlpha(1.0f);
                            appCompatDelegateImpl2.mActionModeView.setVisibility(0);
                            if (appCompatDelegateImpl2.mActionModeView.getParent() instanceof View) {
                                View view = (View) appCompatDelegateImpl2.mActionModeView.getParent();
                                WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                                ViewCompat.Api20Impl.requestApplyInsets(view);
                            }
                        }
                        if (appCompatDelegateImpl2.mActionModePopup != null) {
                            appCompatDelegateImpl2.mWindow.getDecorView().post(appCompatDelegateImpl2.mShowActionModePopup);
                        }
                    } else {
                        appCompatDelegateImpl2.mActionMode = null;
                    }
                }
                appCompatDelegateImpl2.updateBackInvokedCallbackState();
                appCompatDelegateImpl2.mActionMode = appCompatDelegateImpl2.mActionMode;
            }
            appCompatDelegateImpl2.updateBackInvokedCallbackState();
            androidx.appcompat.view.ActionMode actionMode3 = appCompatDelegateImpl2.mActionMode;
            if (actionMode3 != null) {
                return callbackWrapper.getActionModeWrapper(actionMode3);
            }
            return null;
        }

        @Override // android.view.Window.Callback
        public final boolean onSearchRequested() {
            return this.mWrapped.onSearchRequested();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class AutoTimeNightModeManager {
        public final /* synthetic */ int $r8$classId;
        public AppCompatDelegateImpl$AutoNightModeManager$1 mReceiver;
        public final Object mTwilightManager;
        public final /* synthetic */ AppCompatDelegateImpl this$0;

        public AutoTimeNightModeManager() {
        }

        public final void cleanup() {
            AppCompatDelegateImpl$AutoNightModeManager$1 appCompatDelegateImpl$AutoNightModeManager$1 = this.mReceiver;
            if (appCompatDelegateImpl$AutoNightModeManager$1 != null) {
                try {
                    AppCompatDelegateImpl.this.mContext.unregisterReceiver(appCompatDelegateImpl$AutoNightModeManager$1);
                } catch (IllegalArgumentException unused) {
                }
                this.mReceiver = null;
            }
        }

        public final int getApplyableNightMode() {
            boolean z;
            long j;
            switch (this.$r8$classId) {
                case 0:
                    TwilightManager twilightManager = (TwilightManager) this.mTwilightManager;
                    TwilightManager.TwilightState twilightState = twilightManager.mTwilightState;
                    if (twilightState.nextUpdate > System.currentTimeMillis()) {
                        z = twilightState.isNight;
                    } else {
                        Location lastKnownLocationForProvider = PermissionChecker.checkSelfPermission(twilightManager.mContext, "android.permission.ACCESS_COARSE_LOCATION") == 0 ? twilightManager.getLastKnownLocationForProvider("network") : null;
                        Location lastKnownLocationForProvider2 = PermissionChecker.checkSelfPermission(twilightManager.mContext, "android.permission.ACCESS_FINE_LOCATION") == 0 ? twilightManager.getLastKnownLocationForProvider("gps") : null;
                        if (lastKnownLocationForProvider2 == null || lastKnownLocationForProvider == null ? lastKnownLocationForProvider2 != null : lastKnownLocationForProvider2.getTime() > lastKnownLocationForProvider.getTime()) {
                            lastKnownLocationForProvider = lastKnownLocationForProvider2;
                        }
                        if (lastKnownLocationForProvider != null) {
                            long currentTimeMillis = System.currentTimeMillis();
                            if (TwilightCalculator.sInstance == null) {
                                TwilightCalculator.sInstance = new TwilightCalculator();
                            }
                            TwilightCalculator twilightCalculator = TwilightCalculator.sInstance;
                            twilightCalculator.calculateTwilight(lastKnownLocationForProvider.getLatitude(), lastKnownLocationForProvider.getLongitude(), currentTimeMillis - 86400000);
                            twilightCalculator.calculateTwilight(lastKnownLocationForProvider.getLatitude(), lastKnownLocationForProvider.getLongitude(), currentTimeMillis);
                            z = twilightCalculator.state == 1;
                            long j2 = twilightCalculator.sunrise;
                            long j3 = twilightCalculator.sunset;
                            twilightCalculator.calculateTwilight(lastKnownLocationForProvider.getLatitude(), lastKnownLocationForProvider.getLongitude(), currentTimeMillis + 86400000);
                            long j4 = twilightCalculator.sunrise;
                            if (j2 == -1 || j3 == -1) {
                                j = 43200000 + currentTimeMillis;
                            } else {
                                if (currentTimeMillis > j3) {
                                    j2 = j4;
                                } else if (currentTimeMillis > j2) {
                                    j2 = j3;
                                }
                                j = j2 + 60000;
                            }
                            twilightState.isNight = z;
                            twilightState.nextUpdate = j;
                        } else {
                            Log.i("TwilightManager", "Could not get last known location. This is probably because the app does not have any location permissions. Falling back to hardcoded sunrise/sunset values.");
                            int i = Calendar.getInstance().get(11);
                            if (i < 6 || i >= 22) {
                                z = true;
                            }
                        }
                    }
                    if (z) {
                    }
                    break;
                default:
                    if (((PowerManager) this.mTwilightManager).isPowerSaveMode()) {
                    }
                    break;
            }
            return 1;
        }

        /* JADX WARN: Type inference failed for: r1v7, types: [androidx.appcompat.app.AppCompatDelegateImpl$AutoNightModeManager$1] */
        public final void setup() {
            IntentFilter intentFilter;
            cleanup();
            switch (this.$r8$classId) {
                case 0:
                    intentFilter = new IntentFilter();
                    intentFilter.addAction("android.intent.action.TIME_SET");
                    intentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
                    intentFilter.addAction("android.intent.action.TIME_TICK");
                    break;
                default:
                    intentFilter = new IntentFilter();
                    intentFilter.addAction("android.os.action.POWER_SAVE_MODE_CHANGED");
                    break;
            }
            if (intentFilter.countActions() == 0) {
                return;
            }
            if (this.mReceiver == null) {
                this.mReceiver = new BroadcastReceiver() { // from class: androidx.appcompat.app.AppCompatDelegateImpl$AutoNightModeManager$1
                    @Override // android.content.BroadcastReceiver
                    public final void onReceive(Context context, Intent intent) {
                        AppCompatDelegateImpl.AutoTimeNightModeManager autoTimeNightModeManager = AppCompatDelegateImpl.AutoTimeNightModeManager.this;
                        switch (autoTimeNightModeManager.$r8$classId) {
                            case 0:
                                autoTimeNightModeManager.this$0.applyApplicationSpecificConfig(true);
                                break;
                            default:
                                autoTimeNightModeManager.this$0.applyApplicationSpecificConfig(true);
                                break;
                        }
                    }
                };
            }
            AppCompatDelegateImpl.this.mContext.registerReceiver(this.mReceiver, intentFilter);
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public AutoTimeNightModeManager(AppCompatDelegateImpl appCompatDelegateImpl, TwilightManager twilightManager) {
            this();
            this.$r8$classId = 0;
            this.this$0 = appCompatDelegateImpl;
            this.mTwilightManager = twilightManager;
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public AutoTimeNightModeManager(AppCompatDelegateImpl appCompatDelegateImpl, Context context) {
            this();
            this.$r8$classId = 1;
            this.this$0 = appCompatDelegateImpl;
            this.mTwilightManager = (PowerManager) context.getApplicationContext().getSystemService("power");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ListMenuDecorView extends ContentFrameLayout {
        public ListMenuDecorView(ContextThemeWrapper contextThemeWrapper) {
            super(contextThemeWrapper);
        }

        @Override // android.view.ViewGroup, android.view.View
        public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return AppCompatDelegateImpl.this.dispatchKeyEvent(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        @Override // android.view.ViewGroup
        public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            if (motionEvent.getAction() == 0) {
                int x = (int) motionEvent.getX();
                int y = (int) motionEvent.getY();
                if (x < -5 || y < -5 || x > getWidth() + 5 || y > getHeight() + 5) {
                    AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
                    appCompatDelegateImpl.closePanel(appCompatDelegateImpl.getPanelState(0), true);
                    return true;
                }
            }
            return super.onInterceptTouchEvent(motionEvent);
        }

        @Override // android.view.View
        public final void setBackgroundResource(int i) {
            setBackgroundDrawable(AppCompatResources.getDrawable(i, getContext()));
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class PanelFeatureState {
        public int background;
        public View createdPanelView;
        public ListMenuDecorView decorView;
        public int featureId;
        public Bundle frozenActionViewState;
        public int gravity;
        public boolean isHandled;
        public boolean isOpen;
        public boolean isPrepared;
        public ListMenuPresenter listMenuPresenter;
        public ContextThemeWrapper listPresenterContext;
        public MenuBuilder menu;
        public boolean refreshDecorView;
        public boolean refreshMenuContent;
        public View shownPanelView;
        public int windowAnimations;
    }

    public AppCompatDelegateImpl(Context context, Window window, AppCompatCallback appCompatCallback, Object obj) {
        SlicePermissionActivity slicePermissionActivity = null;
        this.mLocalNightMode = -100;
        this.mContext = context;
        this.mHost = obj;
        if (obj instanceof Dialog) {
            while (true) {
                if (context != null) {
                    if (!(context instanceof SlicePermissionActivity)) {
                        if (!(context instanceof ContextWrapper)) {
                            break;
                        } else {
                            context = ((ContextWrapper) context).getBaseContext();
                        }
                    } else {
                        slicePermissionActivity = (SlicePermissionActivity) context;
                        break;
                    }
                } else {
                    break;
                }
            }
            if (slicePermissionActivity != null) {
                this.mLocalNightMode = ((AppCompatDelegateImpl) slicePermissionActivity.getDelegate()).mLocalNightMode;
            }
        }
        if (this.mLocalNightMode == -100) {
            SimpleArrayMap simpleArrayMap = sLocalNightModes;
            Integer num = (Integer) simpleArrayMap.get(this.mHost.getClass().getName());
            if (num != null) {
                this.mLocalNightMode = num.intValue();
                simpleArrayMap.remove(this.mHost.getClass().getName());
            }
        }
        if (window != null) {
            attachToWindow(window);
        }
        AppCompatDrawableManager.preload();
    }

    public static Configuration createOverrideAppConfiguration(Context context, int i, Configuration configuration, boolean z) {
        int i2 = i != 1 ? i != 2 ? z ? 0 : context.getApplicationContext().getResources().getConfiguration().uiMode & 48 : 32 : 16;
        Configuration configuration2 = new Configuration();
        configuration2.fontScale = 0.0f;
        if (configuration != null) {
            configuration2.setTo(configuration);
        }
        configuration2.uiMode = i2 | (configuration2.uiMode & (-49));
        return configuration2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:17:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00ab A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0125  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x007e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean applyApplicationSpecificConfig(boolean r10) {
        /*
            Method dump skipped, instructions count: 340
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.applyApplicationSpecificConfig(boolean):boolean");
    }

    public final void attachToWindow(Window window) {
        Drawable drawable;
        AppCompatDelegateImpl$Api33Impl$$ExternalSyntheticLambda0 appCompatDelegateImpl$Api33Impl$$ExternalSyntheticLambda0;
        int resourceId;
        if (this.mWindow != null) {
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        Window.Callback callback = window.getCallback();
        if (callback instanceof AppCompatWindowCallback) {
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        AppCompatWindowCallback appCompatWindowCallback = new AppCompatWindowCallback(callback);
        this.mAppCompatWindowCallback = appCompatWindowCallback;
        window.setCallback(appCompatWindowCallback);
        Context context = this.mContext;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes((AttributeSet) null, sWindowBackgroundStyleable);
        if (!obtainStyledAttributes.hasValue(0) || (resourceId = obtainStyledAttributes.getResourceId(0, 0)) == 0) {
            drawable = null;
        } else {
            AppCompatDrawableManager appCompatDrawableManager = AppCompatDrawableManager.get();
            synchronized (appCompatDrawableManager) {
                drawable = appCompatDrawableManager.mResourceManager.getDrawable(context, resourceId, true);
            }
        }
        if (drawable != null) {
            window.setBackgroundDrawable(drawable);
        }
        obtainStyledAttributes.recycle();
        this.mWindow = window;
        OnBackInvokedDispatcher onBackInvokedDispatcher = this.mDispatcher;
        if (onBackInvokedDispatcher == null) {
            if (onBackInvokedDispatcher != null && (appCompatDelegateImpl$Api33Impl$$ExternalSyntheticLambda0 = this.mBackCallback) != null) {
                onBackInvokedDispatcher.unregisterOnBackInvokedCallback(appCompatDelegateImpl$Api33Impl$$ExternalSyntheticLambda0);
                this.mBackCallback = null;
            }
            Object obj = this.mHost;
            if (!(obj instanceof Activity) || ((Activity) obj).getWindow() == null) {
                this.mDispatcher = null;
            } else {
                this.mDispatcher = ((Activity) this.mHost).getOnBackInvokedDispatcher();
            }
            updateBackInvokedCallbackState();
        }
    }

    public final void callOnPanelClosed(int i, PanelFeatureState panelFeatureState, MenuBuilder menuBuilder) {
        if (menuBuilder == null) {
            if (panelFeatureState == null && i >= 0) {
                PanelFeatureState[] panelFeatureStateArr = this.mPanels;
                if (i < panelFeatureStateArr.length) {
                    panelFeatureState = panelFeatureStateArr[i];
                }
            }
            if (panelFeatureState != null) {
                menuBuilder = panelFeatureState.menu;
            }
        }
        if ((panelFeatureState == null || panelFeatureState.isOpen) && !this.mDestroyed) {
            AppCompatWindowCallback appCompatWindowCallback = this.mAppCompatWindowCallback;
            Window.Callback callback = this.mWindow.getCallback();
            appCompatWindowCallback.getClass();
            try {
                appCompatWindowCallback.mOnPanelClosedBypassEnabled = true;
                callback.onPanelClosed(i, menuBuilder);
            } finally {
                appCompatWindowCallback.mOnPanelClosedBypassEnabled = false;
            }
        }
    }

    public final void checkCloseActionMenu(MenuBuilder menuBuilder) {
        ActionMenuPresenter actionMenuPresenter;
        if (this.mClosingActionMenu) {
            return;
        }
        this.mClosingActionMenu = true;
        ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) this.mDecorContentParent;
        actionBarOverlayLayout.pullChildren();
        ActionMenuView actionMenuView = actionBarOverlayLayout.mDecorToolbar.mToolbar.mMenuView;
        if (actionMenuView != null && (actionMenuPresenter = actionMenuView.mPresenter) != null) {
            actionMenuPresenter.hideOverflowMenu();
            ActionMenuPresenter.OverflowPopup overflowPopup = actionMenuPresenter.mActionButtonPopup;
            if (overflowPopup != null && overflowPopup.isShowing()) {
                overflowPopup.mPopup.dismiss();
            }
        }
        Window.Callback callback = this.mWindow.getCallback();
        if (callback != null && !this.mDestroyed) {
            callback.onPanelClosed(108, menuBuilder);
        }
        this.mClosingActionMenu = false;
    }

    public final void closePanel(PanelFeatureState panelFeatureState, boolean z) {
        ListMenuDecorView listMenuDecorView;
        DecorContentParent decorContentParent;
        ActionMenuPresenter actionMenuPresenter;
        if (z && panelFeatureState.featureId == 0 && (decorContentParent = this.mDecorContentParent) != null) {
            ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) decorContentParent;
            actionBarOverlayLayout.pullChildren();
            ActionMenuView actionMenuView = actionBarOverlayLayout.mDecorToolbar.mToolbar.mMenuView;
            if (actionMenuView != null && (actionMenuPresenter = actionMenuView.mPresenter) != null && actionMenuPresenter.isOverflowMenuShowing()) {
                checkCloseActionMenu(panelFeatureState.menu);
                return;
            }
        }
        WindowManager windowManager = (WindowManager) this.mContext.getSystemService("window");
        if (windowManager != null && panelFeatureState.isOpen && (listMenuDecorView = panelFeatureState.decorView) != null) {
            windowManager.removeView(listMenuDecorView);
            if (z) {
                callOnPanelClosed(panelFeatureState.featureId, panelFeatureState, null);
            }
        }
        panelFeatureState.isPrepared = false;
        panelFeatureState.isHandled = false;
        panelFeatureState.isOpen = false;
        panelFeatureState.shownPanelView = null;
        panelFeatureState.refreshDecorView = true;
        if (this.mPreparedPanel == panelFeatureState) {
            this.mPreparedPanel = null;
        }
        if (panelFeatureState.featureId == 0) {
            updateBackInvokedCallbackState();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:61:0x00d0, code lost:
    
        if (r7.hideOverflowMenu() != false) goto L88;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x00f4, code lost:
    
        if (r7.showOverflowMenu() != false) goto L88;
     */
    /* JADX WARN: Removed duplicated region for block: B:64:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:70:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean dispatchKeyEvent(android.view.KeyEvent r7) {
        /*
            Method dump skipped, instructions count: 322
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.dispatchKeyEvent(android.view.KeyEvent):boolean");
    }

    public final void doInvalidatePanelMenu(int i) {
        PanelFeatureState panelState = getPanelState(i);
        if (panelState.menu != null) {
            Bundle bundle = new Bundle();
            panelState.menu.saveActionViewStates(bundle);
            if (bundle.size() > 0) {
                panelState.frozenActionViewState = bundle;
            }
            panelState.menu.stopDispatchingItemsChanged();
            panelState.menu.clear();
        }
        panelState.refreshMenuContent = true;
        panelState.refreshDecorView = true;
        if ((i == 108 || i == 0) && this.mDecorContentParent != null) {
            PanelFeatureState panelState2 = getPanelState(0);
            panelState2.isPrepared = false;
            preparePanel(panelState2, null);
        }
    }

    public final void ensureSubDecor() {
        ViewGroup viewGroup;
        int i = 1;
        int i2 = 0;
        if (this.mSubDecorInstalled) {
            return;
        }
        Context context = this.mContext;
        int[] iArr = R$styleable.AppCompatTheme;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(iArr);
        if (!obtainStyledAttributes.hasValue(117)) {
            obtainStyledAttributes.recycle();
            throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
        }
        if (obtainStyledAttributes.getBoolean(126, false)) {
            requestWindowFeature(1);
        } else if (obtainStyledAttributes.getBoolean(117, false)) {
            requestWindowFeature(108);
        }
        if (obtainStyledAttributes.getBoolean(118, false)) {
            requestWindowFeature(109);
        }
        if (obtainStyledAttributes.getBoolean(119, false)) {
            requestWindowFeature(10);
        }
        this.mIsFloating = obtainStyledAttributes.getBoolean(0, false);
        obtainStyledAttributes.recycle();
        ensureWindow();
        this.mWindow.getDecorView();
        LayoutInflater from = LayoutInflater.from(this.mContext);
        if (this.mWindowNoTitle) {
            viewGroup = this.mOverlayActionMode ? (ViewGroup) from.inflate(com.android.wm.shell.R.layout.abc_screen_simple_overlay_action_mode, (ViewGroup) null) : (ViewGroup) from.inflate(com.android.wm.shell.R.layout.abc_screen_simple, (ViewGroup) null);
        } else if (this.mIsFloating) {
            viewGroup = (ViewGroup) from.inflate(com.android.wm.shell.R.layout.abc_dialog_title_material, (ViewGroup) null);
            this.mOverlayActionBar = false;
            this.mHasActionBar = false;
        } else if (this.mHasActionBar) {
            TypedValue typedValue = new TypedValue();
            this.mContext.getTheme().resolveAttribute(com.android.wm.shell.R.attr.actionBarTheme, typedValue, true);
            viewGroup = (ViewGroup) LayoutInflater.from(typedValue.resourceId != 0 ? new ContextThemeWrapper(typedValue.resourceId, this.mContext) : this.mContext).inflate(com.android.wm.shell.R.layout.abc_screen_toolbar, (ViewGroup) null);
            DecorContentParent decorContentParent = (DecorContentParent) viewGroup.findViewById(com.android.wm.shell.R.id.decor_content_parent);
            this.mDecorContentParent = decorContentParent;
            Window.Callback callback = this.mWindow.getCallback();
            ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) decorContentParent;
            actionBarOverlayLayout.pullChildren();
            actionBarOverlayLayout.mDecorToolbar.mWindowCallback = callback;
            if (this.mOverlayActionBar) {
                ((ActionBarOverlayLayout) this.mDecorContentParent).initFeature(109);
            }
            if (this.mFeatureProgress) {
                ((ActionBarOverlayLayout) this.mDecorContentParent).initFeature(2);
            }
            if (this.mFeatureIndeterminateProgress) {
                ((ActionBarOverlayLayout) this.mDecorContentParent).initFeature(5);
            }
        } else {
            viewGroup = null;
        }
        if (viewGroup == null) {
            StringBuilder sb = new StringBuilder("AppCompat does not support the current theme features: { windowActionBar: ");
            sb.append(this.mHasActionBar);
            sb.append(", windowActionBarOverlay: ");
            sb.append(this.mOverlayActionBar);
            sb.append(", android:windowIsFloating: ");
            sb.append(this.mIsFloating);
            sb.append(", windowActionModeOverlay: ");
            sb.append(this.mOverlayActionMode);
            sb.append(", windowNoTitle: ");
            throw new IllegalArgumentException(AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, this.mWindowNoTitle, " }"));
        }
        AnonymousClass3 anonymousClass3 = new AnonymousClass3(this, i2);
        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
        ViewCompat.Api21Impl.setOnApplyWindowInsetsListener(viewGroup, anonymousClass3);
        if (this.mDecorContentParent == null) {
            this.mTitleView = (TextView) viewGroup.findViewById(com.android.wm.shell.R.id.title);
        }
        try {
            Class[] clsArr = new Class[0];
            Method method = viewGroup.getClass().getMethod("makeOptionalFitsSystemWindows", null);
            if (!method.isAccessible()) {
                method.setAccessible(true);
            }
            method.invoke(viewGroup, null);
        } catch (IllegalAccessException e) {
            Log.d("ViewUtils", "Could not invoke makeOptionalFitsSystemWindows", e);
        } catch (NoSuchMethodException unused) {
            Log.d("ViewUtils", "Could not find method makeOptionalFitsSystemWindows. Oh well...");
        } catch (InvocationTargetException e2) {
            Log.d("ViewUtils", "Could not invoke makeOptionalFitsSystemWindows", e2);
        }
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout) viewGroup.findViewById(com.android.wm.shell.R.id.action_bar_activity_content);
        ViewGroup viewGroup2 = (ViewGroup) this.mWindow.findViewById(R.id.content);
        if (viewGroup2 != null) {
            while (viewGroup2.getChildCount() > 0) {
                View childAt = viewGroup2.getChildAt(0);
                viewGroup2.removeViewAt(0);
                contentFrameLayout.addView(childAt);
            }
            viewGroup2.setId(-1);
            contentFrameLayout.setId(R.id.content);
            if (viewGroup2 instanceof FrameLayout) {
                ((FrameLayout) viewGroup2).setForeground(null);
            }
        }
        this.mWindow.setContentView(viewGroup);
        contentFrameLayout.mAttachListener = new AnonymousClass3(this, i);
        this.mSubDecor = viewGroup;
        Object obj = this.mHost;
        CharSequence title = obj instanceof Activity ? ((Activity) obj).getTitle() : this.mTitle;
        if (!TextUtils.isEmpty(title)) {
            DecorContentParent decorContentParent2 = this.mDecorContentParent;
            if (decorContentParent2 != null) {
                ActionBarOverlayLayout actionBarOverlayLayout2 = (ActionBarOverlayLayout) decorContentParent2;
                actionBarOverlayLayout2.pullChildren();
                ToolbarWidgetWrapper toolbarWidgetWrapper = actionBarOverlayLayout2.mDecorToolbar;
                if (!toolbarWidgetWrapper.mTitleSet) {
                    toolbarWidgetWrapper.mTitle = title;
                    if ((toolbarWidgetWrapper.mDisplayOpts & 8) != 0) {
                        Toolbar toolbar = toolbarWidgetWrapper.mToolbar;
                        toolbar.setTitle(title);
                        if (toolbarWidgetWrapper.mTitleSet) {
                            ViewCompat.setAccessibilityPaneTitle(toolbar.getRootView(), title);
                        }
                    }
                }
            } else {
                WindowDecorActionBar windowDecorActionBar = this.mActionBar;
                if (windowDecorActionBar != null) {
                    ToolbarWidgetWrapper toolbarWidgetWrapper2 = windowDecorActionBar.mDecorToolbar;
                    if (!toolbarWidgetWrapper2.mTitleSet) {
                        toolbarWidgetWrapper2.mTitle = title;
                        if ((toolbarWidgetWrapper2.mDisplayOpts & 8) != 0) {
                            Toolbar toolbar2 = toolbarWidgetWrapper2.mToolbar;
                            toolbar2.setTitle(title);
                            if (toolbarWidgetWrapper2.mTitleSet) {
                                ViewCompat.setAccessibilityPaneTitle(toolbar2.getRootView(), title);
                            }
                        }
                    }
                } else {
                    TextView textView = this.mTitleView;
                    if (textView != null) {
                        textView.setText(title);
                    }
                }
            }
        }
        ContentFrameLayout contentFrameLayout2 = (ContentFrameLayout) this.mSubDecor.findViewById(R.id.content);
        View decorView = this.mWindow.getDecorView();
        contentFrameLayout2.mDecorPadding.set(decorView.getPaddingLeft(), decorView.getPaddingTop(), decorView.getPaddingRight(), decorView.getPaddingBottom());
        if (contentFrameLayout2.isLaidOut()) {
            contentFrameLayout2.requestLayout();
        }
        TypedArray obtainStyledAttributes2 = this.mContext.obtainStyledAttributes(iArr);
        if (contentFrameLayout2.mMinWidthMajor == null) {
            contentFrameLayout2.mMinWidthMajor = new TypedValue();
        }
        obtainStyledAttributes2.getValue(124, contentFrameLayout2.mMinWidthMajor);
        if (contentFrameLayout2.mMinWidthMinor == null) {
            contentFrameLayout2.mMinWidthMinor = new TypedValue();
        }
        obtainStyledAttributes2.getValue(125, contentFrameLayout2.mMinWidthMinor);
        if (obtainStyledAttributes2.hasValue(122)) {
            if (contentFrameLayout2.mFixedWidthMajor == null) {
                contentFrameLayout2.mFixedWidthMajor = new TypedValue();
            }
            obtainStyledAttributes2.getValue(122, contentFrameLayout2.mFixedWidthMajor);
        }
        if (obtainStyledAttributes2.hasValue(123)) {
            if (contentFrameLayout2.mFixedWidthMinor == null) {
                contentFrameLayout2.mFixedWidthMinor = new TypedValue();
            }
            obtainStyledAttributes2.getValue(123, contentFrameLayout2.mFixedWidthMinor);
        }
        if (obtainStyledAttributes2.hasValue(120)) {
            if (contentFrameLayout2.mFixedHeightMajor == null) {
                contentFrameLayout2.mFixedHeightMajor = new TypedValue();
            }
            obtainStyledAttributes2.getValue(120, contentFrameLayout2.mFixedHeightMajor);
        }
        if (obtainStyledAttributes2.hasValue(121)) {
            if (contentFrameLayout2.mFixedHeightMinor == null) {
                contentFrameLayout2.mFixedHeightMinor = new TypedValue();
            }
            obtainStyledAttributes2.getValue(121, contentFrameLayout2.mFixedHeightMinor);
        }
        obtainStyledAttributes2.recycle();
        contentFrameLayout2.requestLayout();
        this.mSubDecorInstalled = true;
        PanelFeatureState panelState = getPanelState(0);
        if (this.mDestroyed || panelState.menu != null) {
            return;
        }
        invalidatePanelMenu(108);
    }

    public final void ensureWindow() {
        if (this.mWindow == null) {
            Object obj = this.mHost;
            if (obj instanceof Activity) {
                attachToWindow(((Activity) obj).getWindow());
            }
        }
        if (this.mWindow == null) {
            throw new IllegalStateException("We have not been given a Window");
        }
    }

    public final AutoTimeNightModeManager getAutoTimeNightModeManager(Context context) {
        if (this.mAutoTimeNightModeManager == null) {
            if (TwilightManager.sInstance == null) {
                Context applicationContext = context.getApplicationContext();
                TwilightManager.sInstance = new TwilightManager(applicationContext, (LocationManager) applicationContext.getSystemService("location"));
            }
            this.mAutoTimeNightModeManager = new AutoTimeNightModeManager(this, TwilightManager.sInstance);
        }
        return this.mAutoTimeNightModeManager;
    }

    public final PanelFeatureState getPanelState(int i) {
        PanelFeatureState[] panelFeatureStateArr = this.mPanels;
        if (panelFeatureStateArr == null || panelFeatureStateArr.length <= i) {
            PanelFeatureState[] panelFeatureStateArr2 = new PanelFeatureState[i + 1];
            if (panelFeatureStateArr != null) {
                System.arraycopy(panelFeatureStateArr, 0, panelFeatureStateArr2, 0, panelFeatureStateArr.length);
            }
            this.mPanels = panelFeatureStateArr2;
            panelFeatureStateArr = panelFeatureStateArr2;
        }
        PanelFeatureState panelFeatureState = panelFeatureStateArr[i];
        if (panelFeatureState != null) {
            return panelFeatureState;
        }
        PanelFeatureState panelFeatureState2 = new PanelFeatureState();
        panelFeatureState2.featureId = i;
        panelFeatureState2.refreshDecorView = false;
        panelFeatureStateArr[i] = panelFeatureState2;
        return panelFeatureState2;
    }

    public final void initWindowDecorActionBar() {
        ensureSubDecor();
        if (this.mHasActionBar && this.mActionBar == null) {
            Object obj = this.mHost;
            if (obj instanceof Activity) {
                this.mActionBar = new WindowDecorActionBar((Activity) this.mHost, this.mOverlayActionBar);
            } else if (obj instanceof Dialog) {
                this.mActionBar = new WindowDecorActionBar((Dialog) this.mHost);
            }
            WindowDecorActionBar windowDecorActionBar = this.mActionBar;
            if (windowDecorActionBar != null) {
                windowDecorActionBar.setDefaultDisplayHomeAsUpEnabled(this.mEnableDefaultActionBarUp);
            }
        }
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public final void installViewFactory() {
        LayoutInflater from = LayoutInflater.from(this.mContext);
        if (from.getFactory() == null) {
            from.setFactory2(this);
        } else {
            if (from.getFactory2() instanceof AppCompatDelegateImpl) {
                return;
            }
            Log.i("AppCompatDelegate", "The Activity's LayoutInflater already has a Factory installed so we can not install AppCompat's");
        }
    }

    public final void invalidatePanelMenu(int i) {
        this.mInvalidatePanelMenuFeatures = (1 << i) | this.mInvalidatePanelMenuFeatures;
        if (this.mInvalidatePanelMenuPosted) {
            return;
        }
        View decorView = this.mWindow.getDecorView();
        AnonymousClass2 anonymousClass2 = this.mInvalidatePanelMenuRunnable;
        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
        decorView.postOnAnimation(anonymousClass2);
        this.mInvalidatePanelMenuPosted = true;
    }

    public final int mapNightMode(int i, Context context) {
        if (i == -100) {
            return -1;
        }
        if (i != -1) {
            if (i == 0) {
                if (((UiModeManager) context.getApplicationContext().getSystemService("uimode")).getNightMode() == 0) {
                    return -1;
                }
                return getAutoTimeNightModeManager(context).getApplyableNightMode();
            }
            if (i != 1 && i != 2) {
                if (i != 3) {
                    throw new IllegalStateException("Unknown value set for night mode. Please use one of the MODE_NIGHT values from AppCompatDelegate.");
                }
                if (this.mAutoBatteryNightModeManager == null) {
                    this.mAutoBatteryNightModeManager = new AutoTimeNightModeManager(this, context);
                }
                return this.mAutoBatteryNightModeManager.getApplyableNightMode();
            }
        }
        return i;
    }

    public final boolean onBackPressed() {
        ToolbarWidgetWrapper toolbarWidgetWrapper;
        Toolbar.ExpandedActionViewMenuPresenter expandedActionViewMenuPresenter;
        MenuItemImpl menuItemImpl;
        boolean z = this.mLongPressBackDown;
        this.mLongPressBackDown = false;
        PanelFeatureState panelState = getPanelState(0);
        if (panelState.isOpen) {
            if (!z) {
                closePanel(panelState, true);
            }
            return true;
        }
        androidx.appcompat.view.ActionMode actionMode = this.mActionMode;
        if (actionMode != null) {
            actionMode.finish();
            return true;
        }
        initWindowDecorActionBar();
        WindowDecorActionBar windowDecorActionBar = this.mActionBar;
        if (windowDecorActionBar == null || (toolbarWidgetWrapper = windowDecorActionBar.mDecorToolbar) == null || (expandedActionViewMenuPresenter = toolbarWidgetWrapper.mToolbar.mExpandedMenuPresenter) == null || (menuItemImpl = expandedActionViewMenuPresenter.mCurrentExpandedItem) == null) {
            return false;
        }
        if (expandedActionViewMenuPresenter == null) {
            menuItemImpl = null;
        }
        if (menuItemImpl != null) {
            menuItemImpl.collapseActionView();
        }
        return true;
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public final void onCreate() {
        String str;
        this.mBaseContextAttached = true;
        applyApplicationSpecificConfig(false);
        ensureWindow();
        Object obj = this.mHost;
        if (obj instanceof Activity) {
            try {
                Activity activity = (Activity) obj;
                try {
                    str = NavUtils.getParentActivityName(activity, activity.getComponentName());
                } catch (PackageManager.NameNotFoundException e) {
                    throw new IllegalArgumentException(e);
                }
            } catch (IllegalArgumentException unused) {
                str = null;
            }
            if (str != null) {
                WindowDecorActionBar windowDecorActionBar = this.mActionBar;
                if (windowDecorActionBar == null) {
                    this.mEnableDefaultActionBarUp = true;
                } else {
                    windowDecorActionBar.setDefaultDisplayHomeAsUpEnabled(true);
                }
            }
            synchronized (AppCompatDelegate.sActivityDelegatesLock) {
                AppCompatDelegate.removeDelegateFromActives(this);
                AppCompatDelegate.sActivityDelegates.add(new WeakReference(this));
            }
        }
        this.mEffectiveConfiguration = new Configuration(this.mContext.getResources().getConfiguration());
        this.mCreated = true;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x0111, code lost:
    
        if (r11.equals("ImageButton") == false) goto L24;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // android.view.LayoutInflater.Factory2
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.view.View onCreateView(android.view.View r10, java.lang.String r11, android.content.Context r12, android.util.AttributeSet r13) {
        /*
            Method dump skipped, instructions count: 620
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.onCreateView(android.view.View, java.lang.String, android.content.Context, android.util.AttributeSet):android.view.View");
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
    @Override // androidx.appcompat.app.AppCompatDelegate
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onDestroy() {
        /*
            r3 = this;
            java.lang.Object r0 = r3.mHost
            boolean r0 = r0 instanceof android.app.Activity
            if (r0 == 0) goto L11
            java.lang.Object r0 = androidx.appcompat.app.AppCompatDelegate.sActivityDelegatesLock
            monitor-enter(r0)
            androidx.appcompat.app.AppCompatDelegate.removeDelegateFromActives(r3)     // Catch: java.lang.Throwable -> Le
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Le
            goto L11
        Le:
            r3 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Le
            throw r3
        L11:
            boolean r0 = r3.mInvalidatePanelMenuPosted
            if (r0 == 0) goto L20
            android.view.Window r0 = r3.mWindow
            android.view.View r0 = r0.getDecorView()
            androidx.appcompat.app.AppCompatDelegateImpl$2 r1 = r3.mInvalidatePanelMenuRunnable
            r0.removeCallbacks(r1)
        L20:
            r0 = 1
            r3.mDestroyed = r0
            int r0 = r3.mLocalNightMode
            r1 = -100
            if (r0 == r1) goto L4d
            java.lang.Object r0 = r3.mHost
            boolean r1 = r0 instanceof android.app.Activity
            if (r1 == 0) goto L4d
            android.app.Activity r0 = (android.app.Activity) r0
            boolean r0 = r0.isChangingConfigurations()
            if (r0 == 0) goto L4d
            androidx.collection.SimpleArrayMap r0 = androidx.appcompat.app.AppCompatDelegateImpl.sLocalNightModes
            java.lang.Object r1 = r3.mHost
            java.lang.Class r1 = r1.getClass()
            java.lang.String r1 = r1.getName()
            int r2 = r3.mLocalNightMode
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r0.put(r1, r2)
            goto L5c
        L4d:
            androidx.collection.SimpleArrayMap r0 = androidx.appcompat.app.AppCompatDelegateImpl.sLocalNightModes
            java.lang.Object r1 = r3.mHost
            java.lang.Class r1 = r1.getClass()
            java.lang.String r1 = r1.getName()
            r0.remove(r1)
        L5c:
            androidx.appcompat.app.AppCompatDelegateImpl$AutoTimeNightModeManager r0 = r3.mAutoTimeNightModeManager
            if (r0 == 0) goto L63
            r0.cleanup()
        L63:
            androidx.appcompat.app.AppCompatDelegateImpl$AutoTimeNightModeManager r3 = r3.mAutoBatteryNightModeManager
            if (r3 == 0) goto L6a
            r3.cleanup()
        L6a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.onDestroy():void");
    }

    @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
    public final boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
        PanelFeatureState panelFeatureState;
        Window.Callback callback = this.mWindow.getCallback();
        if (callback != null && !this.mDestroyed) {
            MenuBuilder rootMenu = menuBuilder.getRootMenu();
            PanelFeatureState[] panelFeatureStateArr = this.mPanels;
            int length = panelFeatureStateArr != null ? panelFeatureStateArr.length : 0;
            int i = 0;
            while (true) {
                if (i < length) {
                    panelFeatureState = panelFeatureStateArr[i];
                    if (panelFeatureState != null && panelFeatureState.menu == rootMenu) {
                        break;
                    }
                    i++;
                } else {
                    panelFeatureState = null;
                    break;
                }
            }
            if (panelFeatureState != null) {
                return callback.onMenuItemSelected(panelFeatureState.featureId, menuItem);
            }
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0044, code lost:
    
        if (r6.isOverflowMenuShowing() != false) goto L20;
     */
    @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onMenuModeChange(androidx.appcompat.view.menu.MenuBuilder r6) {
        /*
            Method dump skipped, instructions count: 238
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.onMenuModeChange(androidx.appcompat.view.menu.MenuBuilder):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:64:0x0171, code lost:
    
        if (r15.mAdapter.getCount() > 0) goto L87;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x0151, code lost:
    
        if (r15 != null) goto L76;
     */
    /* JADX WARN: Removed duplicated region for block: B:36:0x01ce  */
    /* JADX WARN: Removed duplicated region for block: B:38:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void openPanel(androidx.appcompat.app.AppCompatDelegateImpl.PanelFeatureState r14, android.view.KeyEvent r15) {
        /*
            Method dump skipped, instructions count: 469
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.openPanel(androidx.appcompat.app.AppCompatDelegateImpl$PanelFeatureState, android.view.KeyEvent):void");
    }

    public final boolean performPanelShortcut(PanelFeatureState panelFeatureState, int i, KeyEvent keyEvent) {
        MenuBuilder menuBuilder;
        if (keyEvent.isSystem()) {
            return false;
        }
        if ((panelFeatureState.isPrepared || preparePanel(panelFeatureState, keyEvent)) && (menuBuilder = panelFeatureState.menu) != null) {
            return menuBuilder.performShortcut(i, keyEvent, 1);
        }
        return false;
    }

    public final boolean preparePanel(PanelFeatureState panelFeatureState, KeyEvent keyEvent) {
        DecorContentParent decorContentParent;
        DecorContentParent decorContentParent2;
        Resources.Theme theme;
        DecorContentParent decorContentParent3;
        DecorContentParent decorContentParent4;
        if (this.mDestroyed) {
            return false;
        }
        if (panelFeatureState.isPrepared) {
            return true;
        }
        PanelFeatureState panelFeatureState2 = this.mPreparedPanel;
        if (panelFeatureState2 != null && panelFeatureState2 != panelFeatureState) {
            closePanel(panelFeatureState2, false);
        }
        Window.Callback callback = this.mWindow.getCallback();
        int i = panelFeatureState.featureId;
        if (callback != null) {
            panelFeatureState.createdPanelView = callback.onCreatePanelView(i);
        }
        boolean z = i == 0 || i == 108;
        if (z && (decorContentParent4 = this.mDecorContentParent) != null) {
            ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) decorContentParent4;
            actionBarOverlayLayout.pullChildren();
            actionBarOverlayLayout.mDecorToolbar.mMenuPrepared = true;
        }
        if (panelFeatureState.createdPanelView == null) {
            MenuBuilder menuBuilder = panelFeatureState.menu;
            if (menuBuilder == null || panelFeatureState.refreshMenuContent) {
                if (menuBuilder == null) {
                    Context context = this.mContext;
                    if ((i == 0 || i == 108) && this.mDecorContentParent != null) {
                        TypedValue typedValue = new TypedValue();
                        Resources.Theme theme2 = context.getTheme();
                        theme2.resolveAttribute(com.android.wm.shell.R.attr.actionBarTheme, typedValue, true);
                        if (typedValue.resourceId != 0) {
                            theme = context.getResources().newTheme();
                            theme.setTo(theme2);
                            theme.applyStyle(typedValue.resourceId, true);
                            theme.resolveAttribute(com.android.wm.shell.R.attr.actionBarWidgetTheme, typedValue, true);
                        } else {
                            theme2.resolveAttribute(com.android.wm.shell.R.attr.actionBarWidgetTheme, typedValue, true);
                            theme = null;
                        }
                        if (typedValue.resourceId != 0) {
                            if (theme == null) {
                                theme = context.getResources().newTheme();
                                theme.setTo(theme2);
                            }
                            theme.applyStyle(typedValue.resourceId, true);
                        }
                        if (theme != null) {
                            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(0, context);
                            contextThemeWrapper.getTheme().setTo(theme);
                            context = contextThemeWrapper;
                        }
                    }
                    MenuBuilder menuBuilder2 = new MenuBuilder(context);
                    menuBuilder2.mCallback = this;
                    MenuBuilder menuBuilder3 = panelFeatureState.menu;
                    if (menuBuilder2 != menuBuilder3) {
                        if (menuBuilder3 != null) {
                            menuBuilder3.removeMenuPresenter(panelFeatureState.listMenuPresenter);
                        }
                        panelFeatureState.menu = menuBuilder2;
                        ListMenuPresenter listMenuPresenter = panelFeatureState.listMenuPresenter;
                        if (listMenuPresenter != null) {
                            menuBuilder2.addMenuPresenter(listMenuPresenter, menuBuilder2.mContext);
                        }
                    }
                    if (panelFeatureState.menu == null) {
                        return false;
                    }
                }
                if (z && (decorContentParent2 = this.mDecorContentParent) != null) {
                    if (this.mActionMenuPresenterCallback == null) {
                        this.mActionMenuPresenterCallback = new AnonymousClass3(this, 2);
                    }
                    ((ActionBarOverlayLayout) decorContentParent2).setMenu(panelFeatureState.menu, this.mActionMenuPresenterCallback);
                }
                panelFeatureState.menu.stopDispatchingItemsChanged();
                if (!callback.onCreatePanelMenu(i, panelFeatureState.menu)) {
                    MenuBuilder menuBuilder4 = panelFeatureState.menu;
                    if (menuBuilder4 != null) {
                        if (menuBuilder4 != null) {
                            menuBuilder4.removeMenuPresenter(panelFeatureState.listMenuPresenter);
                        }
                        panelFeatureState.menu = null;
                    }
                    if (z && (decorContentParent = this.mDecorContentParent) != null) {
                        ((ActionBarOverlayLayout) decorContentParent).setMenu(null, this.mActionMenuPresenterCallback);
                    }
                    return false;
                }
                panelFeatureState.refreshMenuContent = false;
            }
            panelFeatureState.menu.stopDispatchingItemsChanged();
            Bundle bundle = panelFeatureState.frozenActionViewState;
            if (bundle != null) {
                panelFeatureState.menu.restoreActionViewStates(bundle);
                panelFeatureState.frozenActionViewState = null;
            }
            if (!callback.onPreparePanel(0, panelFeatureState.createdPanelView, panelFeatureState.menu)) {
                if (z && (decorContentParent3 = this.mDecorContentParent) != null) {
                    ((ActionBarOverlayLayout) decorContentParent3).setMenu(null, this.mActionMenuPresenterCallback);
                }
                panelFeatureState.menu.startDispatchingItemsChanged();
                return false;
            }
            panelFeatureState.menu.setQwertyMode(KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1);
            panelFeatureState.menu.startDispatchingItemsChanged();
        }
        panelFeatureState.isPrepared = true;
        panelFeatureState.isHandled = false;
        this.mPreparedPanel = panelFeatureState;
        return true;
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public final boolean requestWindowFeature(int i) {
        if (i == 8) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR id when requesting this feature.");
            i = 108;
        } else if (i == 9) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY id when requesting this feature.");
            i = 109;
        }
        if (this.mWindowNoTitle && i == 108) {
            return false;
        }
        if (this.mHasActionBar && i == 1) {
            this.mHasActionBar = false;
        }
        if (i == 1) {
            throwFeatureRequestIfSubDecorInstalled();
            this.mWindowNoTitle = true;
            return true;
        }
        if (i == 2) {
            throwFeatureRequestIfSubDecorInstalled();
            this.mFeatureProgress = true;
            return true;
        }
        if (i == 5) {
            throwFeatureRequestIfSubDecorInstalled();
            this.mFeatureIndeterminateProgress = true;
            return true;
        }
        if (i == 10) {
            throwFeatureRequestIfSubDecorInstalled();
            this.mOverlayActionMode = true;
            return true;
        }
        if (i == 108) {
            throwFeatureRequestIfSubDecorInstalled();
            this.mHasActionBar = true;
            return true;
        }
        if (i != 109) {
            return this.mWindow.requestFeature(i);
        }
        throwFeatureRequestIfSubDecorInstalled();
        this.mOverlayActionBar = true;
        return true;
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public final void setContentView(View view) {
        ensureSubDecor();
        ViewGroup viewGroup = (ViewGroup) this.mSubDecor.findViewById(R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
        this.mAppCompatWindowCallback.bypassOnContentChanged(this.mWindow.getCallback());
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public final void setTitle(CharSequence charSequence) {
        this.mTitle = charSequence;
        DecorContentParent decorContentParent = this.mDecorContentParent;
        if (decorContentParent != null) {
            ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) decorContentParent;
            actionBarOverlayLayout.pullChildren();
            ToolbarWidgetWrapper toolbarWidgetWrapper = actionBarOverlayLayout.mDecorToolbar;
            if (toolbarWidgetWrapper.mTitleSet) {
                return;
            }
            toolbarWidgetWrapper.mTitle = charSequence;
            if ((toolbarWidgetWrapper.mDisplayOpts & 8) != 0) {
                Toolbar toolbar = toolbarWidgetWrapper.mToolbar;
                toolbar.setTitle(charSequence);
                if (toolbarWidgetWrapper.mTitleSet) {
                    ViewCompat.setAccessibilityPaneTitle(toolbar.getRootView(), charSequence);
                    return;
                }
                return;
            }
            return;
        }
        WindowDecorActionBar windowDecorActionBar = this.mActionBar;
        if (windowDecorActionBar == null) {
            TextView textView = this.mTitleView;
            if (textView != null) {
                textView.setText(charSequence);
                return;
            }
            return;
        }
        ToolbarWidgetWrapper toolbarWidgetWrapper2 = windowDecorActionBar.mDecorToolbar;
        if (toolbarWidgetWrapper2.mTitleSet) {
            return;
        }
        toolbarWidgetWrapper2.mTitle = charSequence;
        if ((toolbarWidgetWrapper2.mDisplayOpts & 8) != 0) {
            Toolbar toolbar2 = toolbarWidgetWrapper2.mToolbar;
            toolbar2.setTitle(charSequence);
            if (toolbarWidgetWrapper2.mTitleSet) {
                ViewCompat.setAccessibilityPaneTitle(toolbar2.getRootView(), charSequence);
            }
        }
    }

    public final void throwFeatureRequestIfSubDecorInstalled() {
        if (this.mSubDecorInstalled) {
            throw new AndroidRuntimeException("Window feature must be requested before adding content");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v3, types: [android.window.OnBackInvokedCallback, androidx.appcompat.app.AppCompatDelegateImpl$Api33Impl$$ExternalSyntheticLambda0] */
    public final void updateBackInvokedCallbackState() {
        AppCompatDelegateImpl$Api33Impl$$ExternalSyntheticLambda0 appCompatDelegateImpl$Api33Impl$$ExternalSyntheticLambda0;
        boolean z = false;
        if (this.mDispatcher != null && (getPanelState(0).isOpen || this.mActionMode != null)) {
            z = true;
        }
        if (z && this.mBackCallback == null) {
            OnBackInvokedDispatcher onBackInvokedDispatcher = this.mDispatcher;
            ?? r1 = new OnBackInvokedCallback() { // from class: androidx.appcompat.app.AppCompatDelegateImpl$Api33Impl$$ExternalSyntheticLambda0
                @Override // android.window.OnBackInvokedCallback
                public final void onBackInvoked() {
                    AppCompatDelegateImpl.this.onBackPressed();
                }
            };
            onBackInvokedDispatcher.registerOnBackInvokedCallback(1000000, r1);
            this.mBackCallback = r1;
            return;
        }
        if (z || (appCompatDelegateImpl$Api33Impl$$ExternalSyntheticLambda0 = this.mBackCallback) == null) {
            return;
        }
        this.mDispatcher.unregisterOnBackInvokedCallback(appCompatDelegateImpl$Api33Impl$$ExternalSyntheticLambda0);
        this.mBackCallback = null;
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public final void setContentView(int i) {
        ensureSubDecor();
        ViewGroup viewGroup = (ViewGroup) this.mSubDecor.findViewById(R.id.content);
        viewGroup.removeAllViews();
        LayoutInflater.from(this.mContext).inflate(i, viewGroup);
        this.mAppCompatWindowCallback.bypassOnContentChanged(this.mWindow.getCallback());
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public final void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        ensureSubDecor();
        ViewGroup viewGroup = (ViewGroup) this.mSubDecor.findViewById(R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view, layoutParams);
        this.mAppCompatWindowCallback.bypassOnContentChanged(this.mWindow.getCallback());
    }

    @Override // android.view.LayoutInflater.Factory
    public final View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }
}

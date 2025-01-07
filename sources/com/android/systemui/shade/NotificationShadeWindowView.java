package com.android.systemui.shade;

import android.app.ActivityManager;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Insets;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.session.MediaSessionLegacyHelper;
import android.net.Uri;
import android.os.Bundle;
import android.os.Trace;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.ActionMode;
import android.view.DisplayCutout;
import android.view.InputDevice;
import android.view.InputQueue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.widget.FrameLayout;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryController;
import androidx.savedstate.SavedStateRegistryOwner;
import com.android.internal.view.FloatingActionMode;
import com.android.internal.widget.floatingtoolbar.FloatingToolbar;
import com.android.keyguard.KeyguardSecurityContainer;
import com.android.keyguard.KeyguardSecurityModel;
import com.android.systemui.bouncer.ui.BouncerViewImpl;
import com.android.systemui.keyevent.domain.interactor.SysUIKeyEventHandler;
import com.android.systemui.keyguard.domain.interactor.KeyguardKeyEventInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardKeyEventInteractor$dispatchMenuKeyEvent$1;
import com.android.systemui.lifecycle.ViewLifecycleOwner;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.android.systemui.power.shared.model.WakefulnessModel;
import com.android.systemui.res.R$styleable;
import com.android.systemui.scene.ui.view.WindowRootView$LayoutParams;
import com.android.systemui.shade.NotificationShadeWindowViewController;
import com.android.systemui.statusbar.DragDownHelper;
import com.android.systemui.statusbar.NotificationInsetsImpl;
import com.android.systemui.statusbar.StatusBarStateControllerImpl;
import com.android.systemui.statusbar.phone.CentralSurfacesImpl;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;
import com.android.wm.shell.R;
import java.io.File;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class NotificationShadeWindowView extends FrameLayout {
    public static final /* synthetic */ int $r8$clinit = 0;
    public NotificationInsetsImpl layoutInsetsController;
    public int leftInset;
    public final AnonymousClass1 mFakeWindow;
    public ActionMode mFloatingActionMode;
    public View mFloatingActionModeOriginatingView;
    public FloatingToolbar mFloatingToolbar;
    public NotificationShadeWindowView$$ExternalSyntheticLambda0 mFloatingToolbarPreDrawListener;
    public NotificationShadeWindowViewController.AnonymousClass1 mInteractionEventHandler;
    public int rightInset;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ActionModeCallback2Wrapper extends ActionMode.Callback2 {
        public final ActionMode.Callback mWrapped;

        public ActionModeCallback2Wrapper(ActionMode.Callback callback) {
            this.mWrapped = callback;
        }

        @Override // android.view.ActionMode.Callback
        public final boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            return this.mWrapped.onActionItemClicked(actionMode, menuItem);
        }

        @Override // android.view.ActionMode.Callback
        public final boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            return this.mWrapped.onCreateActionMode(actionMode, menu);
        }

        @Override // android.view.ActionMode.Callback
        public final void onDestroyActionMode(ActionMode actionMode) {
            this.mWrapped.onDestroyActionMode(actionMode);
            NotificationShadeWindowView notificationShadeWindowView = NotificationShadeWindowView.this;
            if (actionMode == notificationShadeWindowView.mFloatingActionMode) {
                notificationShadeWindowView.cleanupFloatingActionModeViews();
                NotificationShadeWindowView.this.mFloatingActionMode = null;
            }
            NotificationShadeWindowView.this.requestFitSystemWindows();
        }

        @Override // android.view.ActionMode.Callback2
        public final void onGetContentRect(ActionMode actionMode, View view, Rect rect) {
            ActionMode.Callback callback = this.mWrapped;
            if (callback instanceof ActionMode.Callback2) {
                ((ActionMode.Callback2) callback).onGetContentRect(actionMode, view, rect);
            } else {
                super.onGetContentRect(actionMode, view, rect);
            }
        }

        @Override // android.view.ActionMode.Callback
        public final boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            NotificationShadeWindowView.this.requestFitSystemWindows();
            return this.mWrapped.onPrepareActionMode(actionMode, menu);
        }
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.android.systemui.shade.NotificationShadeWindowView$1] */
    public NotificationShadeWindowView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mFakeWindow = new Window(((FrameLayout) this).mContext) { // from class: com.android.systemui.shade.NotificationShadeWindowView.1
            @Override // android.view.Window
            public final View getCurrentFocus() {
                return null;
            }

            @Override // android.view.Window
            public final View getDecorView() {
                return NotificationShadeWindowView.this;
            }

            @Override // android.view.Window
            public final WindowInsetsController getInsetsController() {
                return null;
            }

            @Override // android.view.Window
            public final LayoutInflater getLayoutInflater() {
                return null;
            }

            @Override // android.view.Window
            public final int getNavigationBarColor() {
                return 0;
            }

            @Override // android.view.Window
            public final int getStatusBarColor() {
                return 0;
            }

            @Override // android.view.Window
            public final int getVolumeControlStream() {
                return 0;
            }

            @Override // android.view.Window
            public final boolean isFloating() {
                return false;
            }

            @Override // android.view.Window
            public final boolean isShortcutKey(int i, KeyEvent keyEvent) {
                return false;
            }

            @Override // android.view.Window
            public final View peekDecorView() {
                return null;
            }

            @Override // android.view.Window
            public final boolean performContextMenuIdentifierAction(int i, int i2) {
                return false;
            }

            @Override // android.view.Window
            public final boolean performPanelIdentifierAction(int i, int i2, int i3) {
                return false;
            }

            @Override // android.view.Window
            public final boolean performPanelShortcut(int i, int i2, KeyEvent keyEvent, int i3) {
                return false;
            }

            @Override // android.view.Window
            public final Bundle saveHierarchyState() {
                return null;
            }

            @Override // android.view.Window
            public final void setContentView(int i) {
            }

            @Override // android.view.Window
            public final boolean superDispatchGenericMotionEvent(MotionEvent motionEvent) {
                return false;
            }

            @Override // android.view.Window
            public final boolean superDispatchKeyEvent(KeyEvent keyEvent) {
                return false;
            }

            @Override // android.view.Window
            public final boolean superDispatchKeyShortcutEvent(KeyEvent keyEvent) {
                return false;
            }

            @Override // android.view.Window
            public final boolean superDispatchTouchEvent(MotionEvent motionEvent) {
                return false;
            }

            @Override // android.view.Window
            public final boolean superDispatchTrackballEvent(MotionEvent motionEvent) {
                return false;
            }

            @Override // android.view.Window
            public final void setContentView(View view) {
            }

            @Override // android.view.Window
            public final void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
            }

            public final void alwaysReadCloseOnTouchAttr() {
            }

            public final void clearContentView() {
            }

            @Override // android.view.Window
            public final void closeAllPanels() {
            }

            @Override // android.view.Window
            public final void closePanel(int i) {
            }

            @Override // android.view.Window
            public final void invalidatePanelMenu(int i) {
            }

            @Override // android.view.Window
            public final void onActive() {
            }

            @Override // android.view.Window
            public final void onConfigurationChanged(Configuration configuration) {
            }

            public final void onMultiWindowModeChanged() {
            }

            public final void onPictureInPictureModeChanged(boolean z) {
            }

            @Override // android.view.Window
            public final void restoreHierarchyState(Bundle bundle) {
            }

            @Override // android.view.Window
            public final void setBackgroundDrawable(Drawable drawable) {
            }

            @Override // android.view.Window
            public final void setDecorCaptionShade(int i) {
            }

            @Override // android.view.Window
            public final void setNavigationBarColor(int i) {
            }

            @Override // android.view.Window
            public final void setResizingCaptionDrawable(Drawable drawable) {
            }

            @Override // android.view.Window
            public final void setStatusBarColor(int i) {
            }

            @Override // android.view.Window
            public final void setTitle(CharSequence charSequence) {
            }

            @Override // android.view.Window
            public final void setTitleColor(int i) {
            }

            @Override // android.view.Window
            public final void setVolumeControlStream(int i) {
            }

            @Override // android.view.Window
            public final void takeInputQueue(InputQueue.Callback callback) {
            }

            @Override // android.view.Window
            public final void takeKeyEvents(boolean z) {
            }

            @Override // android.view.Window
            public final void takeSurface(SurfaceHolder.Callback2 callback2) {
            }

            @Override // android.view.Window
            public final void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
            }

            @Override // android.view.Window
            public final void openPanel(int i, KeyEvent keyEvent) {
            }

            @Override // android.view.Window
            public final void setChildDrawable(int i, Drawable drawable) {
            }

            @Override // android.view.Window
            public final void setChildInt(int i, int i2) {
            }

            @Override // android.view.Window
            public final void setFeatureDrawable(int i, Drawable drawable) {
            }

            @Override // android.view.Window
            public final void setFeatureDrawableAlpha(int i, int i2) {
            }

            @Override // android.view.Window
            public final void setFeatureDrawableResource(int i, int i2) {
            }

            @Override // android.view.Window
            public final void setFeatureDrawableUri(int i, Uri uri) {
            }

            @Override // android.view.Window
            public final void setFeatureInt(int i, int i2) {
            }

            @Override // android.view.Window
            public final void togglePanel(int i, KeyEvent keyEvent) {
            }
        };
        setMotionEventSplittingEnabled(false);
    }

    public final void cleanupFloatingActionModeViews() {
        FloatingToolbar floatingToolbar = this.mFloatingToolbar;
        if (floatingToolbar != null) {
            floatingToolbar.dismiss();
            this.mFloatingToolbar = null;
        }
        View view = this.mFloatingActionModeOriginatingView;
        if (view != null) {
            if (this.mFloatingToolbarPreDrawListener != null) {
                view.getViewTreeObserver().removeOnPreDrawListener(this.mFloatingToolbarPreDrawListener);
                this.mFloatingToolbarPreDrawListener = null;
            }
            this.mFloatingActionModeOriginatingView = null;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int keyCode;
        NotificationShadeWindowViewController.this.mFalsingCollector.onKeyEvent(keyEvent);
        KeyguardKeyEventInteractor keyguardKeyEventInteractor = NotificationShadeWindowViewController.this.mSysUIKeyEventHandler.keyguardKeyEventInteractor;
        if (keyguardKeyEventInteractor.statusBarStateController.getState() == 1) {
            BouncerViewImpl bouncerViewImpl = keyguardKeyEventInteractor.statusBarKeyguardViewManager.mPrimaryBouncerView;
            if (bouncerViewImpl.getDelegate() != null && bouncerViewImpl.getDelegate().$securityContainerController.interceptMediaKey(keyEvent)) {
                return true;
            }
        }
        if (super.dispatchKeyEvent(keyEvent)) {
            return true;
        }
        SysUIKeyEventHandler sysUIKeyEventHandler = NotificationShadeWindowViewController.this.mSysUIKeyEventHandler;
        KeyguardKeyEventInteractor keyguardKeyEventInteractor2 = sysUIKeyEventHandler.keyguardKeyEventInteractor;
        StatusBarStateController statusBarStateController = keyguardKeyEventInteractor2.statusBarStateController;
        if (statusBarStateController.isDozing() && ((keyCode = keyEvent.getKeyCode()) == 24 || keyCode == 25)) {
            Context context = keyguardKeyEventInteractor2.context;
            keyguardKeyEventInteractor2.mediaSessionLegacyHelperWrapper.getClass();
            MediaSessionLegacyHelper.getHelper(context).sendVolumeKeyEvent(keyEvent, Integer.MIN_VALUE, true);
            return true;
        }
        if (keyEvent.getAction() != 0) {
            boolean isConfirmKey = KeyEvent.isConfirmKey(keyEvent.getKeyCode());
            PowerInteractor powerInteractor = keyguardKeyEventInteractor2.powerInteractor;
            StatusBarKeyguardViewManager statusBarKeyguardViewManager = keyguardKeyEventInteractor2.statusBarKeyguardViewManager;
            if (isConfirmKey && ((WakefulnessModel) ((StateFlowImpl) powerInteractor.detailedWakefulness.$$delegate_0).getValue()).isAwake()) {
                int state = statusBarStateController.getState();
                if (state == 1) {
                    statusBarKeyguardViewManager.showPrimaryBouncer(true);
                    return true;
                }
                if (state == 2) {
                    keyguardKeyEventInteractor2.shadeController.animateCollapseShade(0, true, false, 1.0f);
                    return true;
                }
            } else if (keyEvent.getKeyCode() == 82 && ((WakefulnessModel) ((StateFlowImpl) powerInteractor.detailedWakefulness.$$delegate_0).getValue()).isAwake() && statusBarStateController.getState() != 0) {
                BouncerViewImpl bouncerViewImpl2 = statusBarKeyguardViewManager.mPrimaryBouncerView;
                if (bouncerViewImpl2.getDelegate() != null) {
                    boolean z = ((KeyguardSecurityContainer) bouncerViewImpl2.getDelegate().$securityContainerController.mView).getResources().getBoolean(R.bool.config_disableMenuKeyInLockScreen);
                    boolean isRunningInTestHarness = ActivityManager.isRunningInTestHarness();
                    boolean exists = new File("/data/local/enable_menu_key").exists();
                    if (!z || isRunningInTestHarness || exists) {
                        statusBarKeyguardViewManager.dismissWithAction(new KeyguardKeyEventInteractor$dispatchMenuKeyEvent$1(), null, false, null);
                        return true;
                    }
                }
            }
        }
        if (keyEvent.getKeyCode() != 4) {
            return false;
        }
        if (keyEvent.getAction() == 0) {
            return true;
        }
        sysUIKeyEventHandler.backActionInteractor.onBackRequested();
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchKeyEventPreIme(KeyEvent keyEvent) {
        InputDevice device;
        KeyguardKeyEventInteractor keyguardKeyEventInteractor = NotificationShadeWindowViewController.this.mSysUIKeyEventHandler.keyguardKeyEventInteractor;
        keyguardKeyEventInteractor.getClass();
        if (keyEvent.getAction() == 0 && (device = keyEvent.getDevice()) != null && device.isFullKeyboard() && device.isExternal()) {
            PowerInteractor.onUserTouch$default(keyguardKeyEventInteractor.powerInteractor);
        }
        if (keyEvent.getKeyCode() == 4 && keyguardKeyEventInteractor.statusBarStateController.getState() == 1) {
            BouncerViewImpl bouncerViewImpl = keyguardKeyEventInteractor.statusBarKeyguardViewManager.mPrimaryBouncerView;
            if (bouncerViewImpl.getDelegate() != null && bouncerViewImpl.getDelegate().$securityContainerController.mCurrentSecurityMode == KeyguardSecurityModel.SecurityMode.Password) {
                return keyguardKeyEventInteractor.backActionInteractor.onBackRequested();
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0223  */
    /* JADX WARN: Type inference failed for: r11v5, types: [android.view.View] */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean dispatchTouchEvent(android.view.MotionEvent r15) {
        /*
            Method dump skipped, instructions count: 605
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.shade.NotificationShadeWindowView.dispatchTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.View
    public WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        int i;
        int i2;
        Insets insetsIgnoringVisibility = windowInsets.getInsetsIgnoringVisibility(WindowInsets.Type.systemBars());
        if (getFitsSystemWindows()) {
            if (insetsIgnoringVisibility.top != getPaddingTop() || insetsIgnoringVisibility.bottom != getPaddingBottom()) {
                setPadding(0, 0, 0, 0);
            }
        } else if (getPaddingLeft() != 0 || getPaddingRight() != 0 || getPaddingTop() != 0 || getPaddingBottom() != 0) {
            setPadding(0, 0, 0, 0);
        }
        this.leftInset = 0;
        this.rightInset = 0;
        DisplayCutout displayCutout = getRootWindowInsets().getDisplayCutout();
        NotificationInsetsImpl notificationInsetsImpl = this.layoutInsetsController;
        if (notificationInsetsImpl == null) {
            notificationInsetsImpl = null;
        }
        notificationInsetsImpl.getClass();
        Insets insetsIgnoringVisibility2 = windowInsets.getInsetsIgnoringVisibility(WindowInsets.Type.systemBars());
        if (displayCutout != null) {
            i2 = displayCutout.getSafeInsetLeft();
            i = displayCutout.getSafeInsetRight();
        } else {
            i = 0;
            i2 = 0;
        }
        Pair pair = new Pair(Integer.valueOf(Math.max(insetsIgnoringVisibility2.left, i2)), Integer.valueOf(Math.max(insetsIgnoringVisibility2.right, i)));
        this.leftInset = ((Number) pair.first).intValue();
        this.rightInset = ((Number) pair.second).intValue();
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (childAt.getLayoutParams() instanceof WindowRootView$LayoutParams) {
                WindowRootView$LayoutParams windowRootView$LayoutParams = (WindowRootView$LayoutParams) childAt.getLayoutParams();
                if (!windowRootView$LayoutParams.ignoreRightInset) {
                    int i4 = ((FrameLayout.LayoutParams) windowRootView$LayoutParams).rightMargin;
                    int i5 = this.rightInset;
                    if (i4 != i5 || ((FrameLayout.LayoutParams) windowRootView$LayoutParams).leftMargin != this.leftInset) {
                        windowRootView$LayoutParams.setMargins(this.leftInset, ((ViewGroup.MarginLayoutParams) windowRootView$LayoutParams).topMargin, i5, ((ViewGroup.MarginLayoutParams) windowRootView$LayoutParams).bottomMargin);
                        childAt.requestLayout();
                    }
                }
            }
        }
        return windowInsets;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        onAttachedToWindow$com$android$systemui$scene$ui$view$WindowRootView();
        setWillNotDraw(true);
    }

    public final void onAttachedToWindow$com$android$systemui$scene$ui$view$WindowRootView() {
        super.onAttachedToWindow();
        Object parent = getParent();
        if (!(parent instanceof View) || ((View) parent).getId() == 16908290) {
            if (ViewTreeLifecycleOwner.get(this) != null) {
                throw new IllegalStateException(("root " + this + " already has a LifecycleOwner").toString());
            }
            Object parent2 = getParent();
            if ((parent2 instanceof View) && ((View) parent2).getId() != 16908290) {
                throw new IllegalStateException("ComposeInitializer.onAttachedToWindow(View) must be called on the content child.Outside of activities and dialogs, this is usually the top-most View of a window.");
            }
            final ViewLifecycleOwner viewLifecycleOwner = new ViewLifecycleOwner(this);
            Object obj = new SavedStateRegistryOwner() { // from class: com.android.systemui.compose.ComposeInitializer$onAttachedToWindow$savedStateRegistryOwner$1
                public final SavedStateRegistry savedStateRegistry;

                {
                    SavedStateRegistryController savedStateRegistryController = new SavedStateRegistryController(this);
                    savedStateRegistryController.performRestore(null);
                    this.savedStateRegistry = savedStateRegistryController.savedStateRegistry;
                }

                @Override // androidx.lifecycle.LifecycleOwner
                public final Lifecycle getLifecycle() {
                    return ViewLifecycleOwner.this.registry;
                }

                @Override // androidx.savedstate.SavedStateRegistryOwner
                public final SavedStateRegistry getSavedStateRegistry() {
                    return this.savedStateRegistry;
                }
            };
            viewLifecycleOwner.onCreate();
            setTag(R.id.view_tree_lifecycle_owner, viewLifecycleOwner);
            setTag(R.id.view_tree_saved_state_registry_owner, obj);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Object parent = getParent();
        if (!(parent instanceof View) || ((View) parent).getId() == 16908290) {
            ((ViewLifecycleOwner) ViewTreeLifecycleOwner.get(this)).onDestroy();
            setTag(R.id.view_tree_lifecycle_owner, null);
            setTag(R.id.view_tree_saved_state_registry_owner, null);
        }
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    /* JADX WARN: Code restructure failed: missing block: B:42:0x00a2, code lost:
    
        if (com.android.systemui.shade.NotificationShadeWindowViewController.m861$$Nest$mdidNotificationPanelInterceptEvent(r2, r9) != false) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00a9, code lost:
    
        if (com.android.systemui.shade.NotificationShadeWindowViewController.m861$$Nest$mdidNotificationPanelInterceptEvent(r2, r9) != false) goto L13;
     */
    /* JADX WARN: Removed duplicated region for block: B:14:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x00c3  */
    @Override // android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onInterceptTouchEvent(android.view.MotionEvent r9) {
        /*
            r8 = this;
            com.android.systemui.shade.NotificationShadeWindowViewController$1 r0 = r8.mInteractionEventHandler
            r1 = 0
            r0.mLastInterceptWasDragDownHelper = r1
            com.android.systemui.shade.NotificationShadeWindowViewController r2 = com.android.systemui.shade.NotificationShadeWindowViewController.this
            com.android.systemui.statusbar.SysuiStatusBarStateController r3 = r2.mStatusBarStateController
            r4 = r3
            com.android.systemui.statusbar.StatusBarStateControllerImpl r4 = (com.android.systemui.statusbar.StatusBarStateControllerImpl) r4
            boolean r4 = r4.mIsDozing
            com.android.systemui.shade.ShadeLogger r5 = r2.mShadeLogger
            r6 = 1
            if (r4 == 0) goto L39
            com.android.systemui.statusbar.phone.DozeServiceHost r4 = r2.mDozeServiceHost
            boolean r4 = r4.mPulsing
            if (r4 != 0) goto L39
            com.android.systemui.dock.DockManager r4 = r2.mDockManager
            com.google.android.systemui.dreamliner.DockObserver r4 = (com.google.android.systemui.dreamliner.DockObserver) r4
            boolean r4 = r4.isDocked()
            if (r4 != 0) goto L39
            com.android.keyguard.LockIconViewController r4 = r2.mLockIconViewController
            boolean r4 = r4.willHandleTouchWhileDozing(r9)
            if (r4 != 0) goto L39
            int r0 = r9.getAction()
            if (r0 != 0) goto L36
            java.lang.String r0 = "NSWVC: capture all touch events in always-on"
            r5.d(r0)
        L36:
            r1 = r6
            goto Lbb
        L39:
            com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager r4 = r2.mStatusBarKeyguardViewManager
            r4.getClass()
            com.android.systemui.bouncer.domain.interactor.PrimaryBouncerInteractor r4 = r2.mPrimaryBouncerInteractor
            boolean r4 = r4.isBouncerShowing()
            if (r4 != 0) goto L51
            com.android.systemui.bouncer.domain.interactor.AlternateBouncerInteractor r4 = r2.mAlternateBouncerInteractor
            boolean r4 = r4.isVisibleState()
            if (r4 == 0) goto L4f
            goto L51
        L4f:
            r4 = r1
            goto L52
        L51:
            r4 = r6
        L52:
            com.android.systemui.shade.domain.interactor.PanelExpansionInteractor r7 = r2.mPanelExpansionInteractor
            boolean r7 = r7.isFullyExpanded()
            if (r7 == 0) goto Lac
            if (r4 != 0) goto Lac
            com.android.systemui.statusbar.StatusBarStateControllerImpl r3 = (com.android.systemui.statusbar.StatusBarStateControllerImpl) r3
            boolean r7 = r3.mIsDozing
            if (r7 != 0) goto Lac
            com.android.systemui.statusbar.DragDownHelper r4 = r2.mDragDownHelper
            com.android.systemui.statusbar.LockscreenShadeTransitionController r4 = r4.dragDownCallback
            r7 = 0
            boolean r4 = r4.isDragDownEnabledForView$frameworks__base__packages__SystemUI__android_common__SystemUI_core(r7)
            if (r4 == 0) goto La5
            int r1 = r3.mState
            if (r1 != r6) goto L88
            float r1 = r9.getX()
            float r3 = r9.getY()
            r4 = 0
            com.android.systemui.shade.QuickSettingsController r7 = r2.mQuickSettingsController
            boolean r1 = r7.shouldQuickSettingsIntercept(r1, r3, r4)
            if (r1 == 0) goto L88
            java.lang.String r0 = "NSWVC: QS intercepted"
            r5.d(r0)
            goto L36
        L88:
            com.android.systemui.statusbar.DragDownHelper r1 = r2.mDragDownHelper
            boolean r1 = r1.onInterceptTouchEvent(r9)
            if (r1 == 0) goto L9e
            r0.mLastInterceptWasDragDownHelper = r6
            int r0 = r9.getAction()
            if (r0 != 0) goto Lbb
            java.lang.String r0 = "NSWVC: drag down helper intercepted"
            r5.d(r0)
            goto Lbb
        L9e:
            boolean r0 = com.android.systemui.shade.NotificationShadeWindowViewController.m861$$Nest$mdidNotificationPanelInterceptEvent(r2, r9)
            if (r0 == 0) goto Lbb
            goto L36
        La5:
            boolean r0 = com.android.systemui.shade.NotificationShadeWindowViewController.m861$$Nest$mdidNotificationPanelInterceptEvent(r2, r9)
            if (r0 == 0) goto Lbb
            goto L36
        Lac:
            if (r4 != 0) goto Lbb
            boolean r0 = com.android.systemui.shade.NotificationShadeWindowViewController.m861$$Nest$mdidNotificationPanelInterceptEvent(r2, r9)
            if (r0 == 0) goto Lbb
            java.lang.String r0 = "NSWVC: intercepted for HUN/PULSING"
            r5.d(r0)
            goto L36
        Lbb:
            if (r1 != 0) goto Lc1
            boolean r1 = super.onInterceptTouchEvent(r9)
        Lc1:
            if (r1 == 0) goto Lda
            com.android.systemui.shade.NotificationShadeWindowViewController$1 r8 = r8.mInteractionEventHandler
            r8.getClass()
            android.view.MotionEvent r9 = android.view.MotionEvent.obtain(r9)
            r0 = 3
            r9.setAction(r0)
            com.android.systemui.shade.NotificationShadeWindowViewController r8 = com.android.systemui.shade.NotificationShadeWindowViewController.this
            com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout r8 = r8.mStackScrollLayout
            r8.onInterceptTouchEvent(r9)
            r9.recycle()
        Lda:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.shade.NotificationShadeWindowView.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        Trace.beginSection("NotificationShadeWindowView#onMeasure");
        super.onMeasure(i, i2);
        Trace.endSection();
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        NotificationShadeWindowViewController.AnonymousClass1 anonymousClass1 = this.mInteractionEventHandler;
        NotificationShadeWindowViewController notificationShadeWindowViewController = NotificationShadeWindowViewController.this;
        boolean z = ((StatusBarStateControllerImpl) notificationShadeWindowViewController.mStatusBarStateController).mIsDozing ? !notificationShadeWindowViewController.mDozeServiceHost.mPulsing : false;
        notificationShadeWindowViewController.mStatusBarKeyguardViewManager.getClass();
        if (anonymousClass1.mLastInterceptWasDragDownHelper) {
            DragDownHelper dragDownHelper = notificationShadeWindowViewController.mDragDownHelper;
            if (dragDownHelper.isDraggingDown) {
                z |= dragDownHelper.onTouchEvent(motionEvent) || z;
            }
        }
        if (!z && notificationShadeWindowViewController.mShadeViewController.handleExternalTouch(motionEvent)) {
            z = true;
        }
        if (!z) {
            z = super.onTouchEvent(motionEvent);
        }
        if (!z) {
            NotificationShadeWindowViewController.AnonymousClass1 anonymousClass12 = this.mInteractionEventHandler;
            anonymousClass12.getClass();
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 1 || actionMasked == 3) {
                ((CentralSurfacesImpl) NotificationShadeWindowViewController.this.mService).setInteracting(1, false);
            }
        }
        return z;
    }

    @Override // android.view.View, android.view.ViewParent
    public final void requestLayout() {
        Trace.instant(4096L, "NotificationShadeWindowView#requestLayout");
        super.requestLayout();
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [com.android.systemui.shade.NotificationShadeWindowView$$ExternalSyntheticLambda0] */
    @Override // android.view.ViewGroup, android.view.ViewParent
    public final ActionMode startActionModeForChild(View view, ActionMode.Callback callback, int i) {
        if (i != 1) {
            return super.startActionModeForChild(view, callback, i);
        }
        ActionModeCallback2Wrapper actionModeCallback2Wrapper = new ActionModeCallback2Wrapper(callback);
        ActionMode actionMode = this.mFloatingActionMode;
        if (actionMode != null) {
            actionMode.finish();
        }
        cleanupFloatingActionModeViews();
        this.mFloatingToolbar = new FloatingToolbar(this.mFakeWindow);
        final ActionMode floatingActionMode = new FloatingActionMode(((FrameLayout) this).mContext, actionModeCallback2Wrapper, view, this.mFloatingToolbar);
        this.mFloatingActionModeOriginatingView = view;
        this.mFloatingToolbarPreDrawListener = new ViewTreeObserver.OnPreDrawListener() { // from class: com.android.systemui.shade.NotificationShadeWindowView$$ExternalSyntheticLambda0
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public final boolean onPreDraw() {
                FloatingActionMode floatingActionMode2 = floatingActionMode;
                int i2 = NotificationShadeWindowView.$r8$clinit;
                floatingActionMode2.updateViewLocationInWindow();
                return true;
            }
        };
        if (!actionModeCallback2Wrapper.mWrapped.onCreateActionMode(floatingActionMode, floatingActionMode.getMenu())) {
            return null;
        }
        this.mFloatingActionMode = floatingActionMode;
        floatingActionMode.invalidate();
        this.mFloatingActionModeOriginatingView.getViewTreeObserver().addOnPreDrawListener(this.mFloatingToolbarPreDrawListener);
        return floatingActionMode;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public final FrameLayout.LayoutParams generateDefaultLayoutParams() {
        return new WindowRootView$LayoutParams(-1, -1);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public final FrameLayout.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        Context context = getContext();
        WindowRootView$LayoutParams windowRootView$LayoutParams = new WindowRootView$LayoutParams(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.StatusBarWindowView_Layout);
        windowRootView$LayoutParams.ignoreRightInset = obtainStyledAttributes.getBoolean(0, false);
        obtainStyledAttributes.recycle();
        return windowRootView$LayoutParams;
    }
}

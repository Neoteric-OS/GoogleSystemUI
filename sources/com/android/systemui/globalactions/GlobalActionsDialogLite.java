package com.android.systemui.globalactions;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.ActivityManager;
import android.app.Dialog;
import android.app.IActivityManager;
import android.app.admin.DevicePolicyManager;
import android.app.trust.TrustManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.UserInfo;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.os.Trace;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.Settings;
import android.sysprop.TelephonyProperties;
import android.telecom.TelecomManager;
import android.telephony.ServiceState;
import android.telephony.TelephonyCallback;
import android.util.ArraySet;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.GestureDetector;
import android.view.IWindowManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.collection.MutableObjectList$$ExternalSyntheticOutline0;
import androidx.compose.foundation.text.input.internal.RecordingInputConnection$$ExternalSyntheticOutline0;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.Observer;
import com.android.app.animation.Interpolators;
import com.android.internal.colorextraction.ColorExtractor;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.logging.UiEventLogger;
import com.android.internal.statusbar.IStatusBarService;
import com.android.internal.util.EmergencyAffordanceManager;
import com.android.internal.util.ScreenshotHelper;
import com.android.internal.widget.LockPatternUtils;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.animation.Expandable;
import com.android.systemui.bluetooth.qsdialog.BluetoothTileDialogViewModel$showDialog$1$$ExternalSyntheticOutline0;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.colorextraction.SysuiColorExtractor;
import com.android.systemui.globalactions.GlobalActionsDialogLite;
import com.android.systemui.globalactions.data.repository.GlobalActionsRepository;
import com.android.systemui.globalactions.domain.interactor.GlobalActionsInteractor;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.BcSmartspaceDataPlugin;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.android.systemui.plugins.GlobalActions;
import com.android.systemui.plugins.GlobalActionsPanelPlugin;
import com.android.systemui.plugins.clocks.WeatherData;
import com.android.systemui.scrim.ScrimDrawable;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.shade.BaseShadeControllerImpl;
import com.android.systemui.shade.NotificationShadeWindowControllerImpl;
import com.android.systemui.shade.ShadeController;
import com.android.systemui.statusbar.NotificationShadeWindowController;
import com.android.systemui.statusbar.VibratorHelper;
import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.android.systemui.statusbar.phone.LightBarController;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.systemui.statusbar.window.StatusBarWindowControllerImpl;
import com.android.systemui.telephony.TelephonyListenerManager;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.android.systemui.util.RingerModeTrackerImpl;
import com.android.systemui.util.settings.GlobalSettings;
import com.android.systemui.util.settings.GlobalSettingsImpl;
import com.android.systemui.util.settings.SecureSettings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class GlobalActionsDialogLite implements DialogInterface.OnDismissListener, DialogInterface.OnShowListener, ConfigurationController.ConfigurationListener, GlobalActionsPanelPlugin.Callbacks, LifecycleOwner {
    static final String GLOBAL_ACTION_KEY_POWER = "power";
    public final ActivityStarter mActivityStarter;
    public MyAdapter mAdapter;
    public final AnonymousClass7 mAirplaneModeObserver;
    public AirplaneModeAction mAirplaneModeOn;
    public final AudioManager mAudioManager;
    public final Executor mBackgroundExecutor;
    public final BroadcastDispatcher mBroadcastDispatcher;
    public final AnonymousClass5 mBroadcastReceiver;
    public final ConfigurationController mConfigurationController;
    public final Context mContext;
    public final DevicePolicyManager mDevicePolicyManager;
    protected ActionsDialogLite mDialog;
    public final DialogTransitionAnimator mDialogTransitionAnimator;
    public final EmergencyAffordanceManager mEmergencyAffordanceManager;
    public final GlobalSettings mGlobalSettings;
    public final AnonymousClass8 mHandler;
    public final boolean mHasTelephony;
    public final boolean mHasVibrator;
    public final IActivityManager mIActivityManager;
    public final IWindowManager mIWindowManager;
    public final GlobalActionsInteractor mInteractor;
    public final KeyguardStateController mKeyguardStateController;
    public final KeyguardUpdateMonitor mKeyguardUpdateMonitor;
    public final LightBarController mLightBarController;
    public final LockPatternUtils mLockPatternUtils;
    public final Handler mMainHandler;
    public final MetricsLogger mMetricsLogger;
    public final NotificationShadeWindowController mNotificationShadeWindowController;
    public int mOrientation;
    public MyAdapter mOverflowAdapter;
    public final AnonymousClass6 mPhoneStateListener;
    public MyAdapter mPowerAdapter;
    public final Resources mResources;
    public final RingerModeTrackerImpl mRingerModeTracker;
    public final ScreenshotHelper mScreenshotHelper;
    public final SecureSettings mSecureSettings;
    public final SelectedUserInteractor mSelectedUserInteractor;
    public final ShadeController mShadeController;
    public final boolean mShowSilentToggle;
    public Action mSilentModeAction;
    public int mSmallestScreenWidthDp;
    public final IStatusBarService mStatusBarService;
    public final StatusBarWindowControllerImpl mStatusBarWindowController;
    public final SysuiColorExtractor mSysuiColorExtractor;
    public final TelecomManager mTelecomManager;
    public final TelephonyListenerManager mTelephonyListenerManager;
    public final TrustManager mTrustManager;
    public final UiEventLogger mUiEventLogger;
    public final UserManager mUserManager;
    public final UserTracker mUserTracker;
    public final GlobalActions.GlobalActionsManager mWindowManagerFuncs;
    public final LifecycleRegistry mLifecycle = new LifecycleRegistry(this);
    protected final ArrayList mItems = new ArrayList();
    protected final ArrayList mOverflowItems = new ArrayList();
    protected final ArrayList mPowerItems = new ArrayList();
    public boolean mKeyguardShowing = false;
    public boolean mDeviceProvisioned = false;
    public ToggleState mAirplaneState = ToggleState.Off;
    public boolean mIsWaitingForEcmExit = false;
    public int mDialogPressDelay = 850;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.globalactions.GlobalActionsDialogLite$1, reason: invalid class name */
    public final class AnonymousClass1 extends SinglePressAction {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ GlobalActionsDialogLite this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(GlobalActionsDialogLite globalActionsDialogLite, int i) {
            super(R.drawable.ic_screenshot, R.string.gpsNotifMessage);
            this.$r8$classId = i;
            switch (i) {
                case 1:
                    this.this$0 = globalActionsDialogLite;
                    super(R.drawable.ic_accessibility_hearing_aid_foreground, R.string.global_action_silent_mode_off_status);
                    break;
                case 2:
                    this.this$0 = globalActionsDialogLite;
                    super(R.drawable.ic_thread_network, R.string.gpsVerifYes);
                    break;
                case 3:
                    this.this$0 = globalActionsDialogLite;
                    super(R.drawable.ic_lockscreen_soundon_normal, R.string.global_actions_airplane_mode_off_status);
                    break;
                default:
                    this.this$0 = globalActionsDialogLite;
                    break;
            }
        }

        @Override // com.android.systemui.globalactions.GlobalActionsDialogLite.Action
        public final void onPress() {
            switch (this.$r8$classId) {
                case 0:
                    Intent intent = new Intent("android.settings.SETTINGS");
                    intent.addFlags(335544320);
                    this.this$0.mContext.startActivity(intent);
                    break;
                case 1:
                    Intent intent2 = new Intent("android.intent.action.ASSIST");
                    intent2.addFlags(335544320);
                    this.this$0.mContext.startActivity(intent2);
                    break;
                case 2:
                    Intent intent3 = new Intent("android.intent.action.VOICE_ASSIST");
                    intent3.addFlags(335544320);
                    this.this$0.mContext.startActivity(intent3);
                    break;
                default:
                    postDelayed(new GlobalActionsDialogLite$$ExternalSyntheticLambda0(2, this), r0.mDialogPressDelay);
                    break;
            }
        }

        @Override // com.android.systemui.globalactions.GlobalActionsDialogLite.Action
        public final boolean showBeforeProvisioning() {
            switch (this.$r8$classId) {
                case 0:
                    return true;
                case 1:
                    return true;
                case 2:
                    return true;
                default:
                    return false;
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Action {
        View create(Context context, View view, ViewGroup viewGroup, LayoutInflater layoutInflater);

        Drawable getIcon(Context context);

        CharSequence getMessage();

        int getMessageResId();

        boolean isEnabled();

        void onPress();

        default boolean shouldShow() {
            return true;
        }

        boolean showBeforeProvisioning();
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    class ActionsDialogLite extends SystemUIDialog implements DialogInterface, ColorExtractor.OnColorsChangedListener {
        public static final /* synthetic */ int $r8$clinit = 0;
        public final MyAdapter mAdapter;
        public ScrimDrawable mBackgroundDrawable;
        public final SysuiColorExtractor mColorExtractor;
        public ViewGroup mContainer;
        public final Context mContext;
        public final GestureDetector mGestureDetector;
        protected GestureDetector.SimpleOnGestureListener mGestureListener;
        public GlobalActionsLayout mGlobalActionsLayout;
        public final boolean mKeyguardShowing;
        public final KeyguardStateController mKeyguardStateController;
        public final KeyguardUpdateMonitor mKeyguardUpdateMonitor;
        public final LightBarController mLightBarController;
        public final LockPatternUtils mLockPatternUtils;
        public final NotificationShadeWindowController mNotificationShadeWindowController;
        public final GlobalActionsDialogLite$ActionsDialogLite$$ExternalSyntheticLambda8 mOnBackInvokedCallback;
        public final GlobalActionsDialogLite$$ExternalSyntheticLambda0 mOnRefreshCallback;
        public final MyAdapter mOverflowAdapter;
        public GlobalActionsPopupMenu mOverflowPopup;
        public OnBackInvokedDispatcher mOverriddenBackDispatcher;
        public final MyAdapter mPowerOptionsAdapter;
        public Dialog mPowerOptionsDialog;
        public final SelectedUserInteractor mSelectedUserInteractor;
        public final ShadeController mShadeController;
        public final StatusBarWindowControllerImpl mStatusBarWindowController;
        public final UiEventLogger mUiEventLogger;
        public float mWindowDimAmount;

        /* renamed from: -$$Nest$mopenShadeAndDismiss, reason: not valid java name */
        public static void m805$$Nest$mopenShadeAndDismiss(ActionsDialogLite actionsDialogLite) {
            actionsDialogLite.mUiEventLogger.log(GlobalActionsEvent.GA_CLOSE_TAP_OUTSIDE);
            if (((KeyguardStateControllerImpl) actionsDialogLite.mKeyguardStateController).mShowing) {
                ((BaseShadeControllerImpl) actionsDialogLite.mShadeController).animateExpandQs();
            } else {
                ((BaseShadeControllerImpl) actionsDialogLite.mShadeController).animateExpandShade();
            }
            actionsDialogLite.dismiss();
        }

        /* JADX WARN: Type inference failed for: r2v2, types: [com.android.systemui.globalactions.GlobalActionsDialogLite$ActionsDialogLite$$ExternalSyntheticLambda8] */
        public ActionsDialogLite(Context context, MyAdapter myAdapter, MyAdapter myAdapter2, SysuiColorExtractor sysuiColorExtractor, LightBarController lightBarController, KeyguardStateController keyguardStateController, NotificationShadeWindowController notificationShadeWindowController, StatusBarWindowControllerImpl statusBarWindowControllerImpl, GlobalActionsDialogLite$$ExternalSyntheticLambda0 globalActionsDialogLite$$ExternalSyntheticLambda0, boolean z, MyAdapter myAdapter3, UiEventLogger uiEventLogger, ShadeController shadeController, KeyguardUpdateMonitor keyguardUpdateMonitor, LockPatternUtils lockPatternUtils, SelectedUserInteractor selectedUserInteractor) {
            super(context, com.android.wm.shell.R.style.Theme_SystemUI_Dialog_GlobalActionsLite, false);
            new Binder();
            this.mOnBackInvokedCallback = new OnBackInvokedCallback() { // from class: com.android.systemui.globalactions.GlobalActionsDialogLite$ActionsDialogLite$$ExternalSyntheticLambda8
                @Override // android.window.OnBackInvokedCallback
                public final void onBackInvoked() {
                    GlobalActionsDialogLite.ActionsDialogLite actionsDialogLite = GlobalActionsDialogLite.ActionsDialogLite.this;
                    int i = GlobalActionsDialogLite.ActionsDialogLite.$r8$clinit;
                    actionsDialogLite.mUiEventLogger.log(GlobalActionsDialogLite.GlobalActionsEvent.GA_CLOSE_BACK);
                    actionsDialogLite.dismiss();
                }
            };
            this.mGestureListener = new GestureDetector.SimpleOnGestureListener() { // from class: com.android.systemui.globalactions.GlobalActionsDialogLite.ActionsDialogLite.1
                @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
                public final boolean onDown(MotionEvent motionEvent) {
                    return true;
                }

                @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
                public final boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                    if (f2 <= 0.0f || Math.abs(f2) <= Math.abs(f)) {
                        return false;
                    }
                    float y = motionEvent.getY();
                    ActionsDialogLite actionsDialogLite = ActionsDialogLite.this;
                    if (y > actionsDialogLite.mStatusBarWindowController.mBarHeight) {
                        return false;
                    }
                    ActionsDialogLite.m805$$Nest$mopenShadeAndDismiss(actionsDialogLite);
                    return true;
                }

                @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
                public final boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                    if (f2 >= 0.0f || f2 <= f) {
                        return false;
                    }
                    float y = motionEvent.getY();
                    ActionsDialogLite actionsDialogLite = ActionsDialogLite.this;
                    if (y > actionsDialogLite.mStatusBarWindowController.mBarHeight) {
                        return false;
                    }
                    ActionsDialogLite.m805$$Nest$mopenShadeAndDismiss(actionsDialogLite);
                    return true;
                }

                @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
                public final boolean onSingleTapUp(MotionEvent motionEvent) {
                    ActionsDialogLite.this.mUiEventLogger.log(GlobalActionsEvent.GA_CLOSE_TAP_OUTSIDE);
                    ActionsDialogLite.this.cancel();
                    return false;
                }
            };
            this.mContext = context;
            this.mAdapter = myAdapter;
            this.mOverflowAdapter = myAdapter2;
            this.mPowerOptionsAdapter = myAdapter3;
            this.mColorExtractor = sysuiColorExtractor;
            this.mLightBarController = lightBarController;
            this.mKeyguardStateController = keyguardStateController;
            this.mNotificationShadeWindowController = notificationShadeWindowController;
            this.mStatusBarWindowController = statusBarWindowControllerImpl;
            this.mOnRefreshCallback = globalActionsDialogLite$$ExternalSyntheticLambda0;
            this.mKeyguardShowing = z;
            this.mUiEventLogger = uiEventLogger;
            this.mShadeController = shadeController;
            this.mKeyguardUpdateMonitor = keyguardUpdateMonitor;
            this.mLockPatternUtils = lockPatternUtils;
            this.mGestureDetector = new GestureDetector(context, this.mGestureListener);
            this.mSelectedUserInteractor = selectedUserInteractor;
        }

        @Override // android.app.Dialog, android.content.DialogInterface
        public final void dismiss() {
            GlobalActionsPopupMenu globalActionsPopupMenu = this.mOverflowPopup;
            if (globalActionsPopupMenu != null) {
                globalActionsPopupMenu.dismiss();
            }
            Dialog dialog = this.mPowerOptionsDialog;
            if (dialog != null) {
                dialog.dismiss();
            }
            ((NotificationShadeWindowControllerImpl) this.mNotificationShadeWindowController).setRequestTopUi("GlobalActionsDialogLite", false);
            super.dismiss();
        }

        @Override // com.android.systemui.statusbar.phone.SystemUIDialog
        public final int getHeight() {
            return -1;
        }

        @Override // android.app.Dialog
        public OnBackInvokedDispatcher getOnBackInvokedDispatcher() {
            OnBackInvokedDispatcher onBackInvokedDispatcher = this.mOverriddenBackDispatcher;
            return onBackInvokedDispatcher != null ? onBackInvokedDispatcher : super.getOnBackInvokedDispatcher();
        }

        @Override // com.android.systemui.statusbar.phone.SystemUIDialog
        public final int getWidth() {
            return -1;
        }

        @Override // android.app.Dialog
        public final void onBackPressed() {
            super.onBackPressed();
            this.mUiEventLogger.log(GlobalActionsEvent.GA_CLOSE_BACK);
        }

        public final void onColorsChanged(ColorExtractor colorExtractor, int i) {
            if (this.mKeyguardShowing) {
                if ((i & 2) != 0) {
                    updateColors(colorExtractor.getColors(2), true);
                }
            } else if ((i & 1) != 0) {
                updateColors(colorExtractor.getColors(1), true);
            }
        }

        @Override // com.android.systemui.statusbar.phone.SystemUIDialog, android.app.AlertDialog, android.app.Dialog
        public final void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            getWindow().setTitle(getContext().getString(com.android.wm.shell.R.string.accessibility_quick_settings_power_menu));
            setContentView(com.android.wm.shell.R.layout.global_actions_grid_lite);
            ViewGroup viewGroup = (ViewGroup) findViewById(R.id.content);
            viewGroup.setClipChildren(false);
            viewGroup.setClipToPadding(false);
            ViewGroup viewGroup2 = (ViewGroup) viewGroup.getParent();
            viewGroup2.setClipChildren(false);
            viewGroup2.setClipToPadding(false);
            GlobalActionsLayout globalActionsLayout = (GlobalActionsLayout) findViewById(com.android.wm.shell.R.id.global_actions_view);
            this.mGlobalActionsLayout = globalActionsLayout;
            globalActionsLayout.getListView().setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.android.systemui.globalactions.GlobalActionsDialogLite.ActionsDialogLite.2
                @Override // android.view.View.AccessibilityDelegate
                public final boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
                    accessibilityEvent.getText().add(ActionsDialogLite.this.mContext.getString(R.string.grant_credentials_permission_message_footer));
                    return true;
                }
            });
            this.mGlobalActionsLayout.setImportantForAccessibility(2);
            GlobalActionsLayout globalActionsLayout2 = this.mGlobalActionsLayout;
            globalActionsLayout2.mRotationListener = new GlobalActionsDialogLite$ActionsDialogLite$$ExternalSyntheticLambda0(this);
            globalActionsLayout2.mAdapter = this.mAdapter;
            ViewGroup viewGroup3 = (ViewGroup) findViewById(com.android.wm.shell.R.id.global_actions_container);
            this.mContainer = viewGroup3;
            viewGroup3.setOnTouchListener(new View.OnTouchListener() { // from class: com.android.systemui.globalactions.GlobalActionsDialogLite$ActionsDialogLite$$ExternalSyntheticLambda1
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    GlobalActionsDialogLite.ActionsDialogLite.this.mGestureDetector.onTouchEvent(motionEvent);
                    return view.onTouchEvent(motionEvent);
                }
            });
            View findViewById = findViewById(com.android.wm.shell.R.id.global_actions_overflow_button);
            if (findViewById != null) {
                if (this.mOverflowAdapter.getCount() > 0) {
                    findViewById.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.globalactions.GlobalActionsDialogLite$ActionsDialogLite$$ExternalSyntheticLambda2
                        /* JADX WARN: Type inference failed for: r0v10, types: [com.android.systemui.globalactions.GlobalActionsDialogLite$ActionsDialogLite$$ExternalSyntheticLambda5] */
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            final GlobalActionsDialogLite.ActionsDialogLite actionsDialogLite = GlobalActionsDialogLite.ActionsDialogLite.this;
                            int i = GlobalActionsDialogLite.ActionsDialogLite.$r8$clinit;
                            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(actionsDialogLite.mContext, com.android.wm.shell.R.style.Control_ListPopupWindow);
                            GlobalActionsPopupMenu globalActionsPopupMenu = new GlobalActionsPopupMenu(contextThemeWrapper);
                            globalActionsPopupMenu.mMenuVerticalPadding = 0;
                            globalActionsPopupMenu.mGlobalActionsSidePadding = 0;
                            globalActionsPopupMenu.mMaximumWidthThresholdDp = 800;
                            globalActionsPopupMenu.mContext = contextThemeWrapper;
                            Resources resources = contextThemeWrapper.getResources();
                            globalActionsPopupMenu.setBackgroundDrawable(resources.getDrawable(com.android.wm.shell.R.drawable.global_actions_popup_bg, contextThemeWrapper.getTheme()));
                            globalActionsPopupMenu.setInputMethodMode(2);
                            globalActionsPopupMenu.setModal(true);
                            globalActionsPopupMenu.mGlobalActionsSidePadding = resources.getDimensionPixelSize(com.android.wm.shell.R.dimen.global_actions_side_margin);
                            globalActionsPopupMenu.mMenuVerticalPadding = resources.getDimensionPixelSize(com.android.wm.shell.R.dimen.control_menu_vertical_padding);
                            globalActionsPopupMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.android.systemui.globalactions.GlobalActionsDialogLite$ActionsDialogLite$$ExternalSyntheticLambda4
                                @Override // android.widget.AdapterView.OnItemClickListener
                                public final void onItemClick(AdapterView adapterView, View view2, int i2, long j) {
                                    GlobalActionsDialogLite.MyAdapter myAdapter = GlobalActionsDialogLite.ActionsDialogLite.this.mOverflowAdapter;
                                    GlobalActionsDialogLite.Action action = (GlobalActionsDialogLite.Action) myAdapter.this$0.mOverflowItems.get(i2);
                                    if (action instanceof GlobalActionsDialogLite.SilentModeTriStateAction) {
                                        return;
                                    }
                                    GlobalActionsDialogLite globalActionsDialogLite = myAdapter.this$0;
                                    if (globalActionsDialogLite.mDialog != null) {
                                        globalActionsDialogLite.mDialogTransitionAnimator.disableAllCurrentDialogsExitAnimations();
                                        myAdapter.this$0.mDialog.dismiss();
                                    } else {
                                        Log.w("GlobalActionsDialogLite", "Action clicked while mDialog is null.");
                                    }
                                    action.onPress();
                                }
                            });
                            globalActionsPopupMenu.mOnItemLongClickListener = new AdapterView.OnItemLongClickListener() { // from class: com.android.systemui.globalactions.GlobalActionsDialogLite$ActionsDialogLite$$ExternalSyntheticLambda5
                                @Override // android.widget.AdapterView.OnItemLongClickListener
                                public final boolean onItemLongClick(AdapterView adapterView, View view2, int i2, long j) {
                                    GlobalActionsDialogLite.MyAdapter myAdapter = GlobalActionsDialogLite.ActionsDialogLite.this.mOverflowAdapter;
                                    GlobalActionsDialogLite.Action action = (GlobalActionsDialogLite.Action) myAdapter.this$0.mOverflowItems.get(i2);
                                    if (!(action instanceof GlobalActionsDialogLite.LongPressAction)) {
                                        return false;
                                    }
                                    GlobalActionsDialogLite globalActionsDialogLite = myAdapter.this$0;
                                    if (globalActionsDialogLite.mDialog != null) {
                                        globalActionsDialogLite.mDialogTransitionAnimator.disableAllCurrentDialogsExitAnimations();
                                        myAdapter.this$0.mDialog.dismiss();
                                    } else {
                                        Log.w("GlobalActionsDialogLite", "Action long-clicked while mDialog is null.");
                                    }
                                    return ((GlobalActionsDialogLite.LongPressAction) action).onLongPress();
                                }
                            };
                            globalActionsPopupMenu.setAnchorView(actionsDialogLite.findViewById(com.android.wm.shell.R.id.global_actions_overflow_button));
                            globalActionsPopupMenu.setAdapter(actionsDialogLite.mOverflowAdapter);
                            actionsDialogLite.mOverflowPopup = globalActionsPopupMenu;
                            globalActionsPopupMenu.show();
                        }
                    });
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mGlobalActionsLayout.getLayoutParams();
                    layoutParams.setMarginEnd(0);
                    this.mGlobalActionsLayout.setLayoutParams(layoutParams);
                } else {
                    findViewById.setVisibility(8);
                    LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.mGlobalActionsLayout.getLayoutParams();
                    layoutParams2.setMarginEnd(this.mContext.getResources().getDimensionPixelSize(com.android.wm.shell.R.dimen.global_actions_side_margin));
                    this.mGlobalActionsLayout.setLayoutParams(layoutParams2);
                }
            }
            if (this.mBackgroundDrawable == null) {
                this.mBackgroundDrawable = new ScrimDrawable();
            }
            int selectedUserId = this.mSelectedUserInteractor.getSelectedUserId();
            boolean userHasTrust = this.mKeyguardUpdateMonitor.getUserHasTrust(selectedUserId);
            if (this.mKeyguardShowing && userHasTrust) {
                this.mLockPatternUtils.requireCredentialEntry(selectedUserId);
                final View inflate = LayoutInflater.from(this.mContext).inflate(com.android.wm.shell.R.layout.global_actions_toast, this.mContainer, false);
                final int recommendedTimeoutMillis = ((AccessibilityManager) getContext().getSystemService("accessibility")).getRecommendedTimeoutMillis(3500, 2);
                inflate.setVisibility(0);
                inflate.setAlpha(0.0f);
                this.mContainer.addView(inflate);
                inflate.animate().alpha(1.0f).setDuration(333L).setListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.globalactions.GlobalActionsDialogLite.ActionsDialogLite.3
                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public final void onAnimationEnd(Animator animator) {
                        inflate.animate().alpha(0.0f).setDuration(333L).setStartDelay(recommendedTimeoutMillis).setListener(null);
                    }
                });
            }
            this.mWindowDimAmount = getWindow().getAttributes().dimAmount;
            getOnBackInvokedDispatcher().registerOnBackInvokedCallback(0, this.mOnBackInvokedCallback);
        }

        @Override // android.app.Dialog, android.view.Window.Callback
        public final void onDetachedFromWindow() {
            getOnBackInvokedDispatcher().unregisterOnBackInvokedCallback(this.mOnBackInvokedCallback);
        }

        @Override // android.app.Dialog
        public final boolean onTouchEvent(MotionEvent motionEvent) {
            return this.mGestureDetector.onTouchEvent(motionEvent) || super.onTouchEvent(motionEvent);
        }

        public void setBackDispatcherOverride(OnBackInvokedDispatcher onBackInvokedDispatcher) {
            this.mOverriddenBackDispatcher = onBackInvokedDispatcher;
        }

        @Override // android.app.Dialog
        public final void show() {
            super.show();
            ((NotificationShadeWindowControllerImpl) this.mNotificationShadeWindowController).setRequestTopUi("GlobalActionsDialogLite", true);
            if (getWindow().getAttributes().windowAnimations == 0) {
                startAnimation(true, null);
                setDismissOverride(new GlobalActionsDialogLite$ActionsDialogLite$$ExternalSyntheticLambda3(this, 0));
            }
        }

        @Override // com.android.systemui.statusbar.phone.SystemUIDialog
        public final void start() {
            this.mGlobalActionsLayout.updateList();
            LightBarController lightBarController = this.mLightBarController;
            if (!lightBarController.mGlobalActionsVisible) {
                lightBarController.mGlobalActionsVisible = true;
                lightBarController.reevaluate();
            }
            if (this.mBackgroundDrawable != null) {
                this.mColorExtractor.addOnColorsChangedListener(this);
                updateColors(this.mColorExtractor.mNeutralColorsLock, false);
            }
        }

        public final void startAnimation(final boolean z, final GlobalActionsDialogLite$ActionsDialogLite$$ExternalSyntheticLambda3 globalActionsDialogLite$ActionsDialogLite$$ExternalSyntheticLambda3) {
            float dimension;
            ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            Resources resources = getContext().getResources();
            if (z) {
                dimension = resources.getDimension(R.dimen.preference_fragment_padding_vertical_material);
                ofFloat.setInterpolator(Interpolators.STANDARD);
                ofFloat.setDuration(resources.getInteger(R.integer.config_activityDefaultDur));
            } else {
                dimension = resources.getDimension(R.dimen.preference_icon_minWidth);
                ofFloat.setInterpolator(Interpolators.STANDARD_ACCELERATE);
                ofFloat.setDuration(resources.getInteger(R.integer.config_activityShortDur));
            }
            final float f = dimension;
            final Window window = getWindow();
            final int rotation = window.getWindowManager().getDefaultDisplay().getRotation();
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.globalactions.GlobalActionsDialogLite$ActionsDialogLite$$ExternalSyntheticLambda6
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    GlobalActionsDialogLite.ActionsDialogLite actionsDialogLite = GlobalActionsDialogLite.ActionsDialogLite.this;
                    boolean z2 = z;
                    Window window2 = window;
                    float f2 = f;
                    int i = rotation;
                    int i2 = GlobalActionsDialogLite.ActionsDialogLite.$r8$clinit;
                    actionsDialogLite.getClass();
                    float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    float f3 = z2 ? floatValue : 1.0f - floatValue;
                    actionsDialogLite.mGlobalActionsLayout.setAlpha(f3);
                    window2.setDimAmount(actionsDialogLite.mWindowDimAmount * f3);
                    float f4 = z2 ? (1.0f - floatValue) * f2 : f2 * floatValue;
                    if (i == 0) {
                        actionsDialogLite.mGlobalActionsLayout.setTranslationX(f4);
                        return;
                    }
                    if (i == 1) {
                        actionsDialogLite.mGlobalActionsLayout.setTranslationY(-f4);
                    } else if (i == 2) {
                        actionsDialogLite.mGlobalActionsLayout.setTranslationX(-f4);
                    } else {
                        if (i != 3) {
                            return;
                        }
                        actionsDialogLite.mGlobalActionsLayout.setTranslationY(f4);
                    }
                }
            });
            ofFloat.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.globalactions.GlobalActionsDialogLite.ActionsDialogLite.4
                public int mPreviousLayerType;

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationEnd(Animator animator) {
                    ActionsDialogLite.this.mGlobalActionsLayout.setLayerType(this.mPreviousLayerType, null);
                    GlobalActionsDialogLite$ActionsDialogLite$$ExternalSyntheticLambda3 globalActionsDialogLite$ActionsDialogLite$$ExternalSyntheticLambda32 = globalActionsDialogLite$ActionsDialogLite$$ExternalSyntheticLambda3;
                    if (globalActionsDialogLite$ActionsDialogLite$$ExternalSyntheticLambda32 != null) {
                        globalActionsDialogLite$ActionsDialogLite$$ExternalSyntheticLambda32.run();
                    }
                }

                @Override // android.animation.Animator.AnimatorListener
                public final void onAnimationStart(Animator animator, boolean z2) {
                    this.mPreviousLayerType = ActionsDialogLite.this.mGlobalActionsLayout.getLayerType();
                    ActionsDialogLite.this.mGlobalActionsLayout.setLayerType(2, null);
                }
            });
            ofFloat.start();
        }

        @Override // com.android.systemui.statusbar.phone.SystemUIDialog
        public final void stop() {
            LightBarController lightBarController = this.mLightBarController;
            if (lightBarController.mGlobalActionsVisible) {
                lightBarController.mGlobalActionsVisible = false;
                lightBarController.reevaluate();
            }
            this.mColorExtractor.removeOnColorsChangedListener(this);
        }

        public final void updateColors(ColorExtractor.GradientColors gradientColors, boolean z) {
            ScrimDrawable scrimDrawable = this.mBackgroundDrawable;
            if (scrimDrawable == null) {
                return;
            }
            scrimDrawable.setColor(DarkIconDispatcher.DEFAULT_INVERSE_ICON_TINT, z);
            View decorView = getWindow().getDecorView();
            if (gradientColors.supportsDarkText()) {
                decorView.setSystemUiVisibility(8208);
            } else {
                decorView.setSystemUiVisibility(0);
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    class BugReportAction extends SinglePressAction implements LongPressAction {

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.globalactions.GlobalActionsDialogLite$BugReportAction$1, reason: invalid class name */
        public final class AnonymousClass1 implements Runnable {
            public final /* synthetic */ int $r8$classId;
            public final /* synthetic */ SinglePressAction this$1;

            public /* synthetic */ AnonymousClass1(SinglePressAction singlePressAction, int i) {
                this.$r8$classId = i;
                this.this$1 = singlePressAction;
            }

            @Override // java.lang.Runnable
            public final void run() {
                switch (this.$r8$classId) {
                    case 0:
                        try {
                            GlobalActionsDialogLite.this.mMetricsLogger.action(292);
                            GlobalActionsDialogLite.this.mUiEventLogger.log(GlobalActionsEvent.GA_BUGREPORT_PRESS);
                            if (!GlobalActionsDialogLite.this.mIActivityManager.launchBugReportHandlerApp()) {
                                Log.w("GlobalActionsDialogLite", "Bugreport handler could not be launched");
                                Trace.instantForTrack(4096L, "bugreport", "BugReportAction#requestingInteractiveBugReport");
                                Log.d("GlobalActionsDialogLite", "BugReportAction#requestingInteractiveBugReport");
                                GlobalActionsDialogLite.this.mIActivityManager.requestInteractiveBugReport();
                                break;
                            }
                        } catch (RemoteException unused) {
                            return;
                        }
                        break;
                    default:
                        GlobalActionsDialogLite globalActionsDialogLite = GlobalActionsDialogLite.this;
                        globalActionsDialogLite.mScreenshotHelper.takeScreenshot(0, globalActionsDialogLite.mHandler, (Consumer) null);
                        GlobalActionsDialogLite.this.mMetricsLogger.action(1282);
                        GlobalActionsDialogLite.this.mUiEventLogger.log(GlobalActionsEvent.GA_SCREENSHOT_PRESS);
                        break;
                }
            }
        }

        public BugReportAction() {
            super(R.drawable.ic_jog_dial_vibrate_on, R.string.call_notification_hang_up_action);
        }

        @Override // com.android.systemui.globalactions.GlobalActionsDialogLite.LongPressAction
        public final boolean onLongPress() {
            GlobalActionsDialogLite globalActionsDialogLite = GlobalActionsDialogLite.this;
            if (ActivityManager.isUserAMonkey()) {
                return false;
            }
            try {
                globalActionsDialogLite.mMetricsLogger.action(293);
                globalActionsDialogLite.mUiEventLogger.log(GlobalActionsEvent.GA_BUGREPORT_LONG_PRESS);
                Trace.instantForTrack(4096L, "bugreport", "BugReportAction#requestingFullBugReport");
                Log.d("GlobalActionsDialogLite", "BugReportAction#requestingFullBugReport");
                globalActionsDialogLite.mIActivityManager.requestFullBugReport();
            } catch (RemoteException unused) {
            }
            return false;
        }

        @Override // com.android.systemui.globalactions.GlobalActionsDialogLite.Action
        public final void onPress() {
            if (ActivityManager.isUserAMonkey()) {
                return;
            }
            Trace.instantForTrack(4096L, "bugreport", "BugReportAction#onPress");
            Log.d("GlobalActionsDialogLite", "BugReportAction#onPress");
            postDelayed(new AnonymousClass1(this, 0), r0.mDialogPressDelay);
        }

        @Override // com.android.systemui.globalactions.GlobalActionsDialogLite.Action
        public final boolean showBeforeProvisioning() {
            if (!Build.isDebuggable()) {
                return false;
            }
            GlobalActionsDialogLite globalActionsDialogLite = GlobalActionsDialogLite.this;
            return globalActionsDialogLite.mSecureSettings.getIntForUser("bugreport_in_power_menu", 0, globalActionsDialogLite.getCurrentUser().id) != 0 && globalActionsDialogLite.getCurrentUser().isAdmin();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class EmergencyAction extends SinglePressAction {
        public final /* synthetic */ GlobalActionsDialogLite this$0;

        public EmergencyAction(GlobalActionsDialogLite globalActionsDialogLite, int i) {
            super(i, R.string.global_action_toggle_silent_mode);
        }

        @Override // com.android.systemui.globalactions.GlobalActionsDialogLite.SinglePressAction, com.android.systemui.globalactions.GlobalActionsDialogLite.Action
        public final View create(Context context, View view, ViewGroup viewGroup, LayoutInflater layoutInflater) {
            View create = super.create(context, view, viewGroup, layoutInflater);
            int color = context.getResources().getColor(com.android.wm.shell.R.color.global_actions_lite_text);
            int color2 = context.getResources().getColor(com.android.wm.shell.R.color.global_actions_lite_emergency_icon);
            int color3 = context.getResources().getColor(com.android.wm.shell.R.color.global_actions_lite_emergency_background);
            TextView textView = (TextView) create.findViewById(R.id.message);
            textView.setTextColor(color);
            textView.setSelected(true);
            ImageView imageView = (ImageView) create.findViewById(R.id.icon);
            imageView.getDrawable().setTint(color2);
            imageView.setBackgroundTintList(ColorStateList.valueOf(color3));
            create.setBackgroundTintList(ColorStateList.valueOf(color3));
            return create;
        }

        @Override // com.android.systemui.globalactions.GlobalActionsDialogLite.Action
        public final boolean showBeforeProvisioning() {
            return true;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class EmergencyAffordanceAction extends EmergencyAction {
        public EmergencyAffordanceAction() {
            super(GlobalActionsDialogLite.this, R.drawable.edit_query_background_selected);
        }

        @Override // com.android.systemui.globalactions.GlobalActionsDialogLite.Action
        public final void onPress() {
            GlobalActionsDialogLite.this.mEmergencyAffordanceManager.performEmergencyCall();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    class EmergencyDialerAction extends EmergencyAction {
        public EmergencyDialerAction() {
            super(GlobalActionsDialogLite.this, com.android.wm.shell.R.drawable.ic_emergency_star);
        }

        @Override // com.android.systemui.globalactions.GlobalActionsDialogLite.Action
        public final void onPress() {
            GlobalActionsDialogLite globalActionsDialogLite = GlobalActionsDialogLite.this;
            globalActionsDialogLite.mMetricsLogger.action(1569);
            globalActionsDialogLite.mUiEventLogger.log(GlobalActionsEvent.GA_EMERGENCY_DIALER_PRESS);
            if (globalActionsDialogLite.mTelecomManager != null) {
                globalActionsDialogLite.mShadeController.cancelExpansionAndCollapseShade();
                Intent createLaunchEmergencyDialerIntent = globalActionsDialogLite.mTelecomManager.createLaunchEmergencyDialerIntent(null);
                createLaunchEmergencyDialerIntent.addFlags(343932928);
                createLaunchEmergencyDialerIntent.putExtra("com.android.phone.EmergencyDialer.extra.ENTRY_TYPE", 2);
                globalActionsDialogLite.mContext.startActivityAsUser(createLaunchEmergencyDialerIntent, ((UserTrackerImpl) globalActionsDialogLite.mUserTracker).getUserHandle());
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public enum GlobalActionsEvent implements UiEventLogger.UiEventEnum {
        GA_POWER_MENU_OPEN(337),
        GA_POWER_MENU_CLOSE(471),
        GA_BUGREPORT_PRESS(344),
        GA_BUGREPORT_LONG_PRESS(345),
        GA_EMERGENCY_DIALER_PRESS(346),
        GA_SCREENSHOT_PRESS(347),
        /* JADX INFO: Fake field, exist only in values array */
        GA_SCREENSHOT_LONG_PRESS(348),
        GA_SHUTDOWN_PRESS(802),
        GA_SHUTDOWN_LONG_PRESS(803),
        GA_REBOOT_PRESS(349),
        GA_REBOOT_LONG_PRESS(804),
        GA_LOCKDOWN_PRESS(354),
        GA_OPEN_QS(805),
        /* JADX INFO: Fake field, exist only in values array */
        GA_OPEN_POWER_VOLUP(806),
        /* JADX INFO: Fake field, exist only in values array */
        GA_CLOSE_POWER_VOLUP(807),
        /* JADX INFO: Fake field, exist only in values array */
        GA_CLOSE_LONG_PRESS_POWER(808),
        GA_CLOSE_BACK(809),
        GA_CLOSE_TAP_OUTSIDE(810),
        /* JADX INFO: Fake field, exist only in values array */
        GA_CLOSE_POWER_VOLUP(811),
        GA_SYSTEM_UPDATE_PRESS(1716);

        private final int mId;

        GlobalActionsEvent(int i) {
            this.mId = i;
        }

        public final int getId() {
            return this.mId;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    class LockDownAction extends SinglePressAction {
        public LockDownAction() {
            super(R.drawable.ic_lock_airplane_mode, R.string.global_actions);
        }

        @Override // com.android.systemui.globalactions.GlobalActionsDialogLite.Action
        public final void onPress() {
            GlobalActionsDialogLite globalActionsDialogLite = GlobalActionsDialogLite.this;
            globalActionsDialogLite.mLockPatternUtils.requireStrongAuth(32, -1);
            globalActionsDialogLite.mUiEventLogger.log(GlobalActionsEvent.GA_LOCKDOWN_PRESS);
            try {
                globalActionsDialogLite.mIWindowManager.lockNow((Bundle) null);
                globalActionsDialogLite.mBackgroundExecutor.execute(new GlobalActionsDialogLite$$ExternalSyntheticLambda0(1, this));
            } catch (RemoteException e) {
                Log.e("GlobalActionsDialogLite", "Error while trying to lock device.", e);
            }
        }

        @Override // com.android.systemui.globalactions.GlobalActionsDialogLite.Action
        public final boolean showBeforeProvisioning() {
            return false;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface LongPressAction extends Action {
        boolean onLongPress();
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class PowerOptionsAction extends SinglePressAction {
        public PowerOptionsAction() {
            super(com.android.wm.shell.R.drawable.ic_settings_power, R.string.global_actions_toggle_airplane_mode);
        }

        @Override // com.android.systemui.globalactions.GlobalActionsDialogLite.Action
        public final void onPress() {
            ActionsDialogLite actionsDialogLite = GlobalActionsDialogLite.this.mDialog;
            if (actionsDialogLite != null) {
                Context context = actionsDialogLite.mContext;
                MyAdapter myAdapter = actionsDialogLite.mPowerOptionsAdapter;
                ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(com.android.wm.shell.R.layout.global_actions_power_dialog, (ViewGroup) null);
                for (int i = 0; i < myAdapter.getCount(); i++) {
                    viewGroup.addView(myAdapter.getView(i, null, viewGroup));
                }
                Resources resources = context.getResources();
                Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(1);
                dialog.setContentView(viewGroup);
                Window window = dialog.getWindow();
                window.setType(2020);
                window.setTitle("");
                window.setBackgroundDrawable(resources.getDrawable(com.android.wm.shell.R.drawable.control_background, context.getTheme()));
                window.addFlags(131072);
                actionsDialogLite.mPowerOptionsDialog = dialog;
                dialog.show();
            }
        }

        @Override // com.android.systemui.globalactions.GlobalActionsDialogLite.Action
        public final boolean showBeforeProvisioning() {
            return true;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class RestartAction extends SinglePressAction implements LongPressAction {
        public RestartAction() {
            super(R.drawable.ic_qs_bluetooth, R.string.gnss_service);
        }

        @Override // com.android.systemui.globalactions.GlobalActionsDialogLite.LongPressAction
        public final boolean onLongPress() {
            if (ActivityManager.isUserAMonkey()) {
                return false;
            }
            GlobalActionsDialogLite globalActionsDialogLite = GlobalActionsDialogLite.this;
            globalActionsDialogLite.mUiEventLogger.log(GlobalActionsEvent.GA_REBOOT_LONG_PRESS);
            if (globalActionsDialogLite.mUserManager.hasUserRestriction("no_safe_boot")) {
                return false;
            }
            globalActionsDialogLite.mWindowManagerFuncs.reboot(true);
            return true;
        }

        @Override // com.android.systemui.globalactions.GlobalActionsDialogLite.Action
        public final void onPress() {
            if (ActivityManager.isUserAMonkey()) {
                return;
            }
            GlobalActionsDialogLite globalActionsDialogLite = GlobalActionsDialogLite.this;
            globalActionsDialogLite.mUiEventLogger.log(GlobalActionsEvent.GA_REBOOT_PRESS);
            globalActionsDialogLite.mWindowManagerFuncs.reboot(false);
        }

        @Override // com.android.systemui.globalactions.GlobalActionsDialogLite.Action
        public final boolean showBeforeProvisioning() {
            return true;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    class ScreenshotAction extends SinglePressAction {
        public ScreenshotAction() {
            super(R.drawable.ic_qs_night_display_on, R.string.gnss_time_update_service);
        }

        @Override // com.android.systemui.globalactions.GlobalActionsDialogLite.Action
        public final void onPress() {
            postDelayed(new BugReportAction.AnonymousClass1(this, 1), r0.mDialogPressDelay);
        }

        @Override // com.android.systemui.globalactions.GlobalActionsDialogLite.Action
        public final boolean shouldShow() {
            return 1 == GlobalActionsDialogLite.this.mContext.getResources().getInteger(R.integer.config_navBarInteractionMode);
        }

        @Override // com.android.systemui.globalactions.GlobalActionsDialogLite.Action
        public final boolean showBeforeProvisioning() {
            return false;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class ShutDownAction extends SinglePressAction implements LongPressAction {
        public ShutDownAction() {
            super(R.drawable.ic_lock_power_off, R.string.global_actions_airplane_mode_on_status);
        }

        @Override // com.android.systemui.globalactions.GlobalActionsDialogLite.LongPressAction
        public final boolean onLongPress() {
            if (ActivityManager.isUserAMonkey()) {
                return false;
            }
            GlobalActionsDialogLite globalActionsDialogLite = GlobalActionsDialogLite.this;
            globalActionsDialogLite.mUiEventLogger.log(GlobalActionsEvent.GA_SHUTDOWN_LONG_PRESS);
            if (globalActionsDialogLite.mUserManager.hasUserRestriction("no_safe_boot")) {
                return false;
            }
            globalActionsDialogLite.mWindowManagerFuncs.reboot(true);
            return true;
        }

        @Override // com.android.systemui.globalactions.GlobalActionsDialogLite.Action
        public final void onPress() {
            if (ActivityManager.isUserAMonkey()) {
                return;
            }
            GlobalActionsDialogLite globalActionsDialogLite = GlobalActionsDialogLite.this;
            globalActionsDialogLite.mUiEventLogger.log(GlobalActionsEvent.GA_SHUTDOWN_PRESS);
            globalActionsDialogLite.mWindowManagerFuncs.shutdown();
        }

        @Override // com.android.systemui.globalactions.GlobalActionsDialogLite.Action
        public final boolean showBeforeProvisioning() {
            return true;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class SystemUpdateAction extends SinglePressAction {
        public SystemUpdateAction() {
            super(com.android.wm.shell.R.drawable.ic_system_update, com.android.wm.shell.R.string.system_update_settings_list_item_title);
        }

        @Override // com.android.systemui.globalactions.GlobalActionsDialogLite.Action
        public final void onPress() {
            GlobalActionsDialogLite globalActionsDialogLite = GlobalActionsDialogLite.this;
            globalActionsDialogLite.mUiEventLogger.log(GlobalActionsEvent.GA_SYSTEM_UPDATE_PRESS);
            Intent intent = new Intent("android.settings.SYSTEM_UPDATE_SETTINGS");
            intent.addFlags(270532608);
            globalActionsDialogLite.mActivityStarter.postStartActivityDismissingKeyguard(intent, 0);
        }

        @Override // com.android.systemui.globalactions.GlobalActionsDialogLite.Action
        public final boolean showBeforeProvisioning() {
            return false;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    enum ToggleState {
        Off(false),
        TurningOn(true),
        TurningOff(true),
        On(false);

        private final boolean mInTransition;

        ToggleState(boolean z) {
            this.mInTransition = z;
        }

        public final boolean inTransition() {
            return this.mInTransition;
        }
    }

    /* renamed from: -$$Nest$mchangeAirplaneModeSystemSetting, reason: not valid java name */
    public static void m804$$Nest$mchangeAirplaneModeSystemSetting(GlobalActionsDialogLite globalActionsDialogLite, boolean z) {
        GlobalSettings globalSettings = globalActionsDialogLite.mGlobalSettings;
        globalSettings.getClass();
        Settings.Global.putString(((GlobalSettingsImpl) globalSettings).mContentResolver, "airplane_mode_on", String.valueOf(z ? 1 : 0));
        Intent intent = new Intent("android.intent.action.AIRPLANE_MODE");
        intent.addFlags(536870912);
        intent.putExtra(WeatherData.STATE_KEY, z);
        globalActionsDialogLite.mContext.sendBroadcastAsUser(intent, UserHandle.ALL);
        if (globalActionsDialogLite.mHasTelephony) {
            return;
        }
        globalActionsDialogLite.mAirplaneState = z ? ToggleState.On : ToggleState.Off;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v0, types: [android.database.ContentObserver, com.android.systemui.globalactions.GlobalActionsDialogLite$7] */
    /* JADX WARN: Type inference failed for: r11v1, types: [com.android.systemui.globalactions.GlobalActionsDialogLite$8] */
    /* JADX WARN: Type inference failed for: r8v6, types: [android.content.BroadcastReceiver, com.android.systemui.globalactions.GlobalActionsDialogLite$5] */
    /* JADX WARN: Type inference failed for: r9v1, types: [com.android.systemui.globalactions.GlobalActionsDialogLite$6, java.lang.Object] */
    public GlobalActionsDialogLite(Context context, GlobalActions.GlobalActionsManager globalActionsManager, AudioManager audioManager, DevicePolicyManager devicePolicyManager, LockPatternUtils lockPatternUtils, BroadcastDispatcher broadcastDispatcher, TelephonyListenerManager telephonyListenerManager, GlobalSettings globalSettings, SecureSettings secureSettings, VibratorHelper vibratorHelper, Resources resources, ConfigurationController configurationController, ActivityStarter activityStarter, UserTracker userTracker, KeyguardStateController keyguardStateController, UserManager userManager, TrustManager trustManager, IActivityManager iActivityManager, TelecomManager telecomManager, MetricsLogger metricsLogger, SysuiColorExtractor sysuiColorExtractor, IStatusBarService iStatusBarService, LightBarController lightBarController, NotificationShadeWindowController notificationShadeWindowController, StatusBarWindowControllerImpl statusBarWindowControllerImpl, IWindowManager iWindowManager, Executor executor, UiEventLogger uiEventLogger, RingerModeTrackerImpl ringerModeTrackerImpl, Handler handler, PackageManager packageManager, ShadeController shadeController, KeyguardUpdateMonitor keyguardUpdateMonitor, DialogTransitionAnimator dialogTransitionAnimator, SelectedUserInteractor selectedUserInteractor, GlobalActionsInteractor globalActionsInteractor) {
        ?? r8 = new BroadcastReceiver() { // from class: com.android.systemui.globalactions.GlobalActionsDialogLite.5
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context2, Intent intent) {
                String action = intent.getAction();
                if ("android.intent.action.CLOSE_SYSTEM_DIALOGS".equals(action) || "android.intent.action.SCREEN_OFF".equals(action)) {
                    String stringExtra = intent.getStringExtra("reason");
                    if ("globalactions".equals(stringExtra)) {
                        return;
                    }
                    GlobalActionsDialogLite.this.mDialogTransitionAnimator.disableAllCurrentDialogsExitAnimations();
                    AnonymousClass8 anonymousClass8 = GlobalActionsDialogLite.this.mHandler;
                    anonymousClass8.sendMessage(anonymousClass8.obtainMessage(0, stringExtra));
                    return;
                }
                if (!"android.intent.action.EMERGENCY_CALLBACK_MODE_CHANGED".equals(action) || intent.getBooleanExtra("android.telephony.extra.PHONE_IN_ECM_STATE", false)) {
                    return;
                }
                GlobalActionsDialogLite globalActionsDialogLite = GlobalActionsDialogLite.this;
                if (globalActionsDialogLite.mIsWaitingForEcmExit) {
                    globalActionsDialogLite.mIsWaitingForEcmExit = false;
                    GlobalActionsDialogLite.m804$$Nest$mchangeAirplaneModeSystemSetting(globalActionsDialogLite, true);
                }
            }
        };
        this.mBroadcastReceiver = r8;
        ?? r9 = new TelephonyCallback.ServiceStateListener() { // from class: com.android.systemui.globalactions.GlobalActionsDialogLite.6
            @Override // android.telephony.TelephonyCallback.ServiceStateListener
            public final void onServiceStateChanged(ServiceState serviceState) {
                GlobalActionsDialogLite globalActionsDialogLite = GlobalActionsDialogLite.this;
                if (globalActionsDialogLite.mHasTelephony) {
                    if (globalActionsDialogLite.mAirplaneModeOn == null) {
                        Log.d("GlobalActionsDialogLite", "Service changed before actions created");
                        return;
                    }
                    boolean z = serviceState.getState() == 3;
                    GlobalActionsDialogLite globalActionsDialogLite2 = GlobalActionsDialogLite.this;
                    ToggleState toggleState = z ? ToggleState.On : ToggleState.Off;
                    globalActionsDialogLite2.mAirplaneState = toggleState;
                    globalActionsDialogLite2.mAirplaneModeOn.mState = toggleState;
                    globalActionsDialogLite2.mAdapter.notifyDataSetChanged();
                    GlobalActionsDialogLite.this.mOverflowAdapter.notifyDataSetChanged();
                    GlobalActionsDialogLite.this.mPowerAdapter.notifyDataSetChanged();
                }
            }
        };
        this.mPhoneStateListener = r9;
        ?? r10 = new ContentObserver(this.mMainHandler) { // from class: com.android.systemui.globalactions.GlobalActionsDialogLite.7
            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                GlobalActionsDialogLite.this.onAirplaneModeChanged();
            }
        };
        this.mAirplaneModeObserver = r10;
        this.mHandler = new Handler() { // from class: com.android.systemui.globalactions.GlobalActionsDialogLite.8
            @Override // android.os.Handler
            public final void handleMessage(Message message) {
                int i = message.what;
                GlobalActionsDialogLite globalActionsDialogLite = GlobalActionsDialogLite.this;
                if (i != 0) {
                    if (i != 1) {
                        return;
                    }
                    globalActionsDialogLite.refreshSilentMode();
                    globalActionsDialogLite.mAdapter.notifyDataSetChanged();
                    return;
                }
                if (globalActionsDialogLite.mDialog != null) {
                    if (BcSmartspaceDataPlugin.UI_SURFACE_DREAM.equals(message.obj)) {
                        globalActionsDialogLite.mDialog.hide();
                        globalActionsDialogLite.mDialog.dismiss();
                    } else {
                        globalActionsDialogLite.mDialog.dismiss();
                    }
                    globalActionsDialogLite.mDialog = null;
                }
            }
        };
        this.mContext = context;
        this.mWindowManagerFuncs = globalActionsManager;
        this.mAudioManager = audioManager;
        this.mDevicePolicyManager = devicePolicyManager;
        this.mLockPatternUtils = lockPatternUtils;
        this.mTelephonyListenerManager = telephonyListenerManager;
        this.mKeyguardStateController = keyguardStateController;
        this.mBroadcastDispatcher = broadcastDispatcher;
        this.mGlobalSettings = globalSettings;
        this.mSecureSettings = secureSettings;
        this.mResources = resources;
        this.mConfigurationController = configurationController;
        this.mActivityStarter = activityStarter;
        this.mUserTracker = userTracker;
        this.mUserManager = userManager;
        this.mTrustManager = trustManager;
        this.mIActivityManager = iActivityManager;
        this.mTelecomManager = telecomManager;
        this.mMetricsLogger = metricsLogger;
        this.mUiEventLogger = uiEventLogger;
        this.mSysuiColorExtractor = sysuiColorExtractor;
        this.mLightBarController = lightBarController;
        this.mNotificationShadeWindowController = notificationShadeWindowController;
        this.mStatusBarWindowController = statusBarWindowControllerImpl;
        this.mIWindowManager = iWindowManager;
        this.mBackgroundExecutor = executor;
        this.mRingerModeTracker = ringerModeTrackerImpl;
        this.mMainHandler = handler;
        this.mSmallestScreenWidthDp = resources.getConfiguration().smallestScreenWidthDp;
        this.mOrientation = resources.getConfiguration().orientation;
        this.mShadeController = shadeController;
        this.mKeyguardUpdateMonitor = keyguardUpdateMonitor;
        this.mDialogTransitionAnimator = dialogTransitionAnimator;
        this.mSelectedUserInteractor = selectedUserInteractor;
        this.mInteractor = globalActionsInteractor;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.CLOSE_SYSTEM_DIALOGS");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.EMERGENCY_CALLBACK_MODE_CHANGED");
        broadcastDispatcher.registerReceiver(r8, intentFilter);
        this.mHasTelephony = packageManager.hasSystemFeature("android.hardware.telephony");
        telephonyListenerManager.mTelephonyCallback.mServiceStateListeners.add(r9);
        telephonyListenerManager.updateListening();
        globalSettings.registerContentObserverSync(Settings.Global.getUriFor("airplane_mode_on"), true, r10);
        this.mHasVibrator = vibratorHelper.hasVibrator();
        boolean z = resources.getBoolean(R.bool.config_use_strict_phone_number_comparation_for_russia);
        this.mShowSilentToggle = !z;
        if (!z) {
            ringerModeTrackerImpl.ringerMode.observe(this, new Observer() { // from class: com.android.systemui.globalactions.GlobalActionsDialogLite$$ExternalSyntheticLambda1
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    sendEmptyMessage(1);
                }
            });
        }
        this.mEmergencyAffordanceManager = new EmergencyAffordanceManager(context);
        this.mScreenshotHelper = new ScreenshotHelper(context);
        ((ConfigurationControllerImpl) configurationController).addCallback(this);
    }

    public final void addIfShouldShowAction(List list, Action action) {
        if (shouldShowAction(action)) {
            list.add(action);
        }
    }

    public void createActionItems() {
        String[] strArr;
        String[] strArr2;
        if (this.mHasVibrator) {
            this.mSilentModeAction = new SilentModeTriStateAction(this.mAudioManager, this.mHandler);
        } else {
            this.mSilentModeAction = new AirplaneModeAction(this, 1);
        }
        this.mAirplaneModeOn = new AirplaneModeAction(this, 0);
        onAirplaneModeChanged();
        this.mItems.clear();
        this.mOverflowItems.clear();
        this.mPowerItems.clear();
        String[] defaultActions = getDefaultActions();
        ShutDownAction shutDownAction = new ShutDownAction();
        RestartAction restartAction = new RestartAction();
        ArraySet arraySet = new ArraySet();
        ArrayList<Action> arrayList = new ArrayList();
        if (this.mEmergencyAffordanceManager.needsEmergencyAffordance()) {
            addIfShouldShowAction(arrayList, new EmergencyAffordanceAction());
            arraySet.add("emergency");
        }
        boolean z = false;
        int i = 0;
        boolean z2 = false;
        UserInfo userInfo = null;
        while (i < defaultActions.length) {
            String str = defaultActions[i];
            if (arraySet.contains(str)) {
                strArr = defaultActions;
            } else {
                if (GLOBAL_ACTION_KEY_POWER.equals(str)) {
                    addIfShouldShowAction(arrayList, shutDownAction);
                } else if ("airplane".equals(str)) {
                    addIfShouldShowAction(arrayList, this.mAirplaneModeOn);
                } else if ("bugreport".equals(str)) {
                    if (!z2) {
                        userInfo = getCurrentUser();
                        z2 = true;
                    }
                    if (shouldDisplayBugReport(userInfo)) {
                        addIfShouldShowAction(arrayList, new BugReportAction());
                    }
                } else if ("silent".equals(str)) {
                    if (this.mShowSilentToggle) {
                        addIfShouldShowAction(arrayList, this.mSilentModeAction);
                    }
                } else if (!"users".equals(str)) {
                    strArr = defaultActions;
                    if ("settings".equals(str)) {
                        addIfShouldShowAction(arrayList, new AnonymousClass1(this, 0));
                    } else if ("lockdown".equals(str)) {
                        if (!z2) {
                            userInfo = getCurrentUser();
                            z2 = true;
                        }
                        if (shouldDisplayLockdown(userInfo)) {
                            addIfShouldShowAction(arrayList, new LockDownAction());
                        }
                    } else if ("voiceassist".equals(str)) {
                        addIfShouldShowAction(arrayList, new AnonymousClass1(this, 2));
                    } else if ("assist".equals(str)) {
                        addIfShouldShowAction(arrayList, new AnonymousClass1(this, 1));
                    } else if ("restart".equals(str)) {
                        addIfShouldShowAction(arrayList, restartAction);
                    } else if ("screenshot".equals(str)) {
                        addIfShouldShowAction(arrayList, new ScreenshotAction());
                    } else if ("logout".equals(str)) {
                        if (this.mDevicePolicyManager.isLogoutEnabled()) {
                            if (!z2) {
                                userInfo = getCurrentUser();
                                z2 = true;
                            }
                            if (userInfo != null) {
                                if (!z2) {
                                    userInfo = getCurrentUser();
                                    z2 = true;
                                }
                                if (userInfo.id != 0) {
                                    addIfShouldShowAction(arrayList, new AnonymousClass1(this, 3));
                                }
                            }
                        }
                    } else if ("emergency".equals(str)) {
                        if (shouldDisplayEmergency()) {
                            addIfShouldShowAction(arrayList, new EmergencyDialerAction());
                        }
                    } else if ("system_update".equals(str)) {
                        addIfShouldShowAction(arrayList, new SystemUpdateAction());
                    } else {
                        Log.e("GlobalActionsDialogLite", "Invalid global action key " + str);
                    }
                    arraySet.add(str);
                } else if (SystemProperties.getBoolean("fw.power_user_switcher", z)) {
                    if (!z2) {
                        userInfo = getCurrentUser();
                        z2 = true;
                    }
                    if (this.mUserManager.isUserSwitcherEnabled()) {
                        for (final UserInfo userInfo2 : this.mUserManager.getUsers()) {
                            if (userInfo2.supportsSwitchToByUser()) {
                                boolean z3 = userInfo != null ? userInfo.id == userInfo2.id : userInfo2.id == 0;
                                String str2 = userInfo2.iconPath;
                                Drawable createFromPath = str2 != null ? Drawable.createFromPath(str2) : null;
                                strArr2 = defaultActions;
                                String str3 = userInfo2.name;
                                if (str3 == null) {
                                    str3 = "Primary";
                                }
                                addIfShouldShowAction(arrayList, new SinglePressAction(createFromPath, str3.concat(z3 ? " ✔" : "")) { // from class: com.android.systemui.globalactions.GlobalActionsDialogLite.4
                                    @Override // com.android.systemui.globalactions.GlobalActionsDialogLite.Action
                                    public final void onPress() {
                                        try {
                                            GlobalActionsDialogLite.this.mIActivityManager.switchUser(userInfo2.id);
                                        } catch (RemoteException e) {
                                            Log.e("GlobalActionsDialogLite", "Couldn't switch user " + e);
                                        }
                                    }

                                    @Override // com.android.systemui.globalactions.GlobalActionsDialogLite.Action
                                    public final boolean showBeforeProvisioning() {
                                        return false;
                                    }
                                });
                            } else {
                                strArr2 = defaultActions;
                            }
                            defaultActions = strArr2;
                        }
                    }
                }
                strArr = defaultActions;
                arraySet.add(str);
            }
            i++;
            defaultActions = strArr;
            z = false;
        }
        if (arrayList.contains(shutDownAction) && arrayList.contains(restartAction) && arrayList.size() > getMaxShownPowerItems()) {
            int min = Math.min(arrayList.indexOf(restartAction), arrayList.indexOf(shutDownAction));
            arrayList.remove(shutDownAction);
            arrayList.remove(restartAction);
            this.mPowerItems.add(shutDownAction);
            this.mPowerItems.add(restartAction);
            arrayList.add(min, new PowerOptionsAction());
        }
        for (Action action : arrayList) {
            if (this.mItems.size() < getMaxShownPowerItems()) {
                this.mItems.add(action);
            } else {
                this.mOverflowItems.add(action);
            }
        }
    }

    public final void destroy() {
        this.mBroadcastDispatcher.unregisterReceiver(this.mBroadcastReceiver);
        TelephonyListenerManager telephonyListenerManager = this.mTelephonyListenerManager;
        telephonyListenerManager.mTelephonyCallback.mServiceStateListeners.remove(this.mPhoneStateListener);
        telephonyListenerManager.updateListening();
        this.mGlobalSettings.unregisterContentObserverSync(this.mAirplaneModeObserver);
        ((ConfigurationControllerImpl) this.mConfigurationController).removeCallback(this);
    }

    @Override // com.android.systemui.plugins.GlobalActionsPanelPlugin.Callbacks
    public final void dismissGlobalActionsMenu() {
        removeMessages(0);
        sendEmptyMessage(0);
    }

    public final UserInfo getCurrentUser() {
        return ((UserTrackerImpl) this.mUserTracker).getUserInfo();
    }

    public String[] getDefaultActions() {
        return this.mResources.getStringArray(R.array.config_gnssParameters);
    }

    @Override // androidx.lifecycle.LifecycleOwner
    public final Lifecycle getLifecycle() {
        return this.mLifecycle;
    }

    public int getMaxShownPowerItems() {
        return this.mResources.getInteger(com.android.wm.shell.R.integer.power_menu_lite_max_rows) * this.mResources.getInteger(com.android.wm.shell.R.integer.power_menu_lite_max_columns);
    }

    public BugReportAction makeBugReportActionForTesting() {
        return new BugReportAction();
    }

    public EmergencyDialerAction makeEmergencyDialerActionForTesting() {
        return new EmergencyDialerAction();
    }

    public ScreenshotAction makeScreenshotActionForTesting() {
        return new ScreenshotAction();
    }

    public final void onAirplaneModeChanged() {
        if (this.mHasTelephony || this.mAirplaneModeOn == null) {
            return;
        }
        ToggleState toggleState = this.mGlobalSettings.getInt(0, "airplane_mode_on") == 1 ? ToggleState.On : ToggleState.Off;
        this.mAirplaneState = toggleState;
        this.mAirplaneModeOn.mState = toggleState;
    }

    @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
    public final void onConfigChanged(Configuration configuration) {
        ActionsDialogLite actionsDialogLite = this.mDialog;
        if (actionsDialogLite == null || !actionsDialogLite.isShowing()) {
            return;
        }
        int i = configuration.smallestScreenWidthDp;
        if (i == this.mSmallestScreenWidthDp && configuration.orientation == this.mOrientation) {
            return;
        }
        this.mSmallestScreenWidthDp = i;
        this.mOrientation = configuration.orientation;
        ActionsDialogLite actionsDialogLite2 = this.mDialog;
        actionsDialogLite2.mOnRefreshCallback.run();
        GlobalActionsPopupMenu globalActionsPopupMenu = actionsDialogLite2.mOverflowPopup;
        if (globalActionsPopupMenu != null) {
            globalActionsPopupMenu.dismiss();
        }
        Dialog dialog = actionsDialogLite2.mPowerOptionsDialog;
        if (dialog != null) {
            dialog.dismiss();
        }
        actionsDialogLite2.mGlobalActionsLayout.updateList();
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        if (this.mDialog == dialogInterface) {
            this.mDialog = null;
        }
        this.mUiEventLogger.log(GlobalActionsEvent.GA_POWER_MENU_CLOSE);
        this.mWindowManagerFuncs.onGlobalActionsHidden();
        this.mLifecycle.setCurrentState(Lifecycle.State.CREATED);
        GlobalActionsRepository globalActionsRepository = this.mInteractor.repository;
        Boolean bool = Boolean.FALSE;
        StateFlowImpl stateFlowImpl = globalActionsRepository._isVisible;
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, bool);
    }

    @Override // android.content.DialogInterface.OnShowListener
    public final void onShow(DialogInterface dialogInterface) {
        this.mMetricsLogger.visible(1568);
        this.mUiEventLogger.log(GlobalActionsEvent.GA_POWER_MENU_OPEN);
        GlobalActionsRepository globalActionsRepository = this.mInteractor.repository;
        Boolean bool = Boolean.TRUE;
        StateFlowImpl stateFlowImpl = globalActionsRepository._isVisible;
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, bool);
    }

    public final void refreshSilentMode() {
        if (this.mHasVibrator) {
            return;
        }
        boolean z = this.mRingerModeTracker.ringerMode.getValue().intValue() != 2;
        ((AirplaneModeAction) this.mSilentModeAction).mState = z ? ToggleState.On : ToggleState.Off;
    }

    public void setZeroDialogPressDelayForTesting() {
        this.mDialogPressDelay = 0;
    }

    public boolean shouldDisplayBugReport(UserInfo userInfo) {
        return (userInfo == null || !userInfo.isAdmin() || this.mSecureSettings.getIntForUser("bugreport_in_power_menu", 0, userInfo.id) == 0) ? false : true;
    }

    public boolean shouldDisplayEmergency() {
        return this.mHasTelephony;
    }

    public boolean shouldDisplayLockdown(UserInfo userInfo) {
        if (userInfo == null) {
            return false;
        }
        int i = userInfo.id;
        if (!((KeyguardStateControllerImpl) this.mKeyguardStateController).mSecure) {
            return false;
        }
        int strongAuthForUser = this.mLockPatternUtils.getStrongAuthForUser(i);
        return strongAuthForUser == 0 || strongAuthForUser == 4;
    }

    public boolean shouldShowAction(Action action) {
        if (this.mKeyguardShowing) {
            action.getClass();
        }
        if (this.mDeviceProvisioned || action.showBeforeProvisioning()) {
            return action.shouldShow();
        }
        return false;
    }

    public final void showOrHideDialog(boolean z, boolean z2, Expandable expandable) {
        this.mKeyguardShowing = z;
        this.mDeviceProvisioned = z2;
        ActionsDialogLite actionsDialogLite = this.mDialog;
        if (actionsDialogLite != null && actionsDialogLite.isShowing()) {
            this.mWindowManagerFuncs.onGlobalActionsShown();
            this.mDialog.dismiss();
            this.mDialog = null;
            return;
        }
        createActionItems();
        this.mAdapter = new MyAdapter(this, 0);
        this.mOverflowAdapter = new MyAdapter(this, 1);
        this.mPowerAdapter = new MyAdapter(this, 2);
        ActionsDialogLite actionsDialogLite2 = new ActionsDialogLite(this.mContext, this.mAdapter, this.mOverflowAdapter, this.mSysuiColorExtractor, this.mLightBarController, this.mKeyguardStateController, this.mNotificationShadeWindowController, this.mStatusBarWindowController, new GlobalActionsDialogLite$$ExternalSyntheticLambda0(0, this), this.mKeyguardShowing, this.mPowerAdapter, this.mUiEventLogger, this.mShadeController, this.mKeyguardUpdateMonitor, this.mLockPatternUtils, this.mSelectedUserInteractor);
        actionsDialogLite2.setOnDismissListener(this);
        actionsDialogLite2.setOnShowListener(this);
        this.mDialog = actionsDialogLite2;
        refreshSilentMode();
        this.mAirplaneModeOn.mState = this.mAirplaneState;
        this.mAdapter.notifyDataSetChanged();
        this.mLifecycle.setCurrentState(Lifecycle.State.RESUMED);
        WindowManager.LayoutParams attributes = this.mDialog.getWindow().getAttributes();
        attributes.setTitle("ActionsDialog");
        attributes.layoutInDisplayCutoutMode = 3;
        this.mDialog.getWindow().setAttributes(attributes);
        this.mDialog.getWindow().addFlags(131072);
        DialogTransitionAnimator.Controller m = expandable != null ? BluetoothTileDialogViewModel$showDialog$1$$ExternalSyntheticOutline0.m(58, "global_actions", expandable) : null;
        if (m != null) {
            this.mDialogTransitionAnimator.show(this.mDialog, m, false);
        } else {
            this.mDialog.show();
        }
        this.mWindowManagerFuncs.onGlobalActionsShown();
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class SinglePressAction implements Action {
        public final Drawable mIcon;
        public final int mIconResId;
        public final CharSequence mMessage;
        public final int mMessageResId;

        public SinglePressAction(int i, int i2) {
            this.mIconResId = i;
            this.mMessageResId = i2;
            this.mMessage = null;
            this.mIcon = null;
        }

        @Override // com.android.systemui.globalactions.GlobalActionsDialogLite.Action
        public View create(Context context, View view, ViewGroup viewGroup, LayoutInflater layoutInflater) {
            GlobalActionsDialogLite.this.getClass();
            View inflate = layoutInflater.inflate(com.android.wm.shell.R.layout.global_actions_grid_item_lite, viewGroup, false);
            inflate.setId(View.generateViewId());
            ImageView imageView = (ImageView) inflate.findViewById(R.id.icon);
            TextView textView = (TextView) inflate.findViewById(R.id.message);
            textView.setSelected(true);
            imageView.setImageDrawable(getIcon(context));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            CharSequence charSequence = this.mMessage;
            if (charSequence != null) {
                textView.setText(charSequence);
            } else {
                textView.setText(this.mMessageResId);
            }
            return inflate;
        }

        @Override // com.android.systemui.globalactions.GlobalActionsDialogLite.Action
        public final Drawable getIcon(Context context) {
            Drawable drawable = this.mIcon;
            return drawable != null ? drawable : context.getDrawable(this.mIconResId);
        }

        @Override // com.android.systemui.globalactions.GlobalActionsDialogLite.Action
        public final CharSequence getMessage() {
            return this.mMessage;
        }

        @Override // com.android.systemui.globalactions.GlobalActionsDialogLite.Action
        public final int getMessageResId() {
            return this.mMessageResId;
        }

        @Override // com.android.systemui.globalactions.GlobalActionsDialogLite.Action
        public final boolean isEnabled() {
            return true;
        }

        public SinglePressAction(Drawable drawable, CharSequence charSequence) {
            this.mIconResId = R.drawable.ic_menu_allfriends;
            this.mMessageResId = 0;
            this.mMessage = charSequence;
            this.mIcon = drawable;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class AirplaneModeAction implements Action {
        public final /* synthetic */ int $r8$classId;
        public final int mDisabledIconResid;
        public final int mDisabledStatusMessageResId;
        public final int mEnabledIconResId;
        public final int mEnabledStatusMessageResId;
        public ToggleState mState;
        public final /* synthetic */ GlobalActionsDialogLite this$0;

        public AirplaneModeAction(int i, int i2, int i3, int i4) {
            this.mState = ToggleState.Off;
            this.mEnabledIconResId = i;
            this.mDisabledIconResid = i2;
            this.mEnabledStatusMessageResId = i3;
            this.mDisabledStatusMessageResId = i4;
        }

        @Override // com.android.systemui.globalactions.GlobalActionsDialogLite.Action
        public final View create(Context context, View view, ViewGroup viewGroup, LayoutInflater layoutInflater) {
            View inflate = layoutInflater.inflate(com.android.wm.shell.R.layout.global_actions_grid_item_v2, viewGroup, false);
            ViewGroup.LayoutParams layoutParams = inflate.getLayoutParams();
            layoutParams.width = -2;
            inflate.setLayoutParams(layoutParams);
            ImageView imageView = (ImageView) inflate.findViewById(R.id.icon);
            TextView textView = (TextView) inflate.findViewById(R.id.message);
            boolean isEnabled = isEnabled();
            if (textView != null) {
                textView.setText(getMessageResId());
                textView.setEnabled(isEnabled);
                textView.setSelected(true);
            }
            if (imageView != null) {
                ToggleState toggleState = this.mState;
                imageView.setImageDrawable(context.getDrawable((toggleState == ToggleState.On || toggleState == ToggleState.TurningOn) ? this.mEnabledIconResId : this.mDisabledIconResid));
                imageView.setEnabled(isEnabled);
            }
            inflate.setEnabled(isEnabled);
            return inflate;
        }

        @Override // com.android.systemui.globalactions.GlobalActionsDialogLite.Action
        public final Drawable getIcon(Context context) {
            ToggleState toggleState = this.mState;
            return context.getDrawable((toggleState == ToggleState.On || toggleState == ToggleState.TurningOn) ? this.mEnabledIconResId : this.mDisabledIconResid);
        }

        @Override // com.android.systemui.globalactions.GlobalActionsDialogLite.Action
        public final CharSequence getMessage() {
            return null;
        }

        @Override // com.android.systemui.globalactions.GlobalActionsDialogLite.Action
        public final int getMessageResId() {
            ToggleState toggleState = this.mState;
            return (toggleState == ToggleState.On || toggleState == ToggleState.TurningOn) ? this.mEnabledStatusMessageResId : this.mDisabledStatusMessageResId;
        }

        @Override // com.android.systemui.globalactions.GlobalActionsDialogLite.Action
        public final boolean isEnabled() {
            return !this.mState.inTransition();
        }

        @Override // com.android.systemui.globalactions.GlobalActionsDialogLite.Action
        public final void onPress() {
            if (this.mState.inTransition()) {
                Log.w("GlobalActionsDialogLite", "shouldn't be able to toggle when in transition");
            }
            boolean z = this.mState != ToggleState.On;
            switch (this.$r8$classId) {
                case 0:
                    GlobalActionsDialogLite globalActionsDialogLite = this.this$0;
                    if (!globalActionsDialogLite.mHasTelephony || !((Boolean) TelephonyProperties.in_ecm_mode().orElse(Boolean.FALSE)).booleanValue()) {
                        GlobalActionsDialogLite.m804$$Nest$mchangeAirplaneModeSystemSetting(globalActionsDialogLite, z);
                        break;
                    } else {
                        globalActionsDialogLite.mIsWaitingForEcmExit = true;
                        Intent intent = new Intent("android.telephony.action.SHOW_NOTICE_ECM_BLOCK_OTHERS", (Uri) null);
                        intent.addFlags(268435456);
                        globalActionsDialogLite.mContext.startActivity(intent);
                        break;
                    }
                    break;
                default:
                    GlobalActionsDialogLite globalActionsDialogLite2 = this.this$0;
                    if (!z) {
                        globalActionsDialogLite2.mAudioManager.setRingerMode(2);
                        break;
                    } else {
                        globalActionsDialogLite2.mAudioManager.setRingerMode(0);
                        break;
                    }
            }
            switch (this.$r8$classId) {
                case 0:
                    GlobalActionsDialogLite globalActionsDialogLite3 = this.this$0;
                    if (globalActionsDialogLite3.mHasTelephony && !((Boolean) TelephonyProperties.in_ecm_mode().orElse(Boolean.FALSE)).booleanValue()) {
                        ToggleState toggleState = z ? ToggleState.TurningOn : ToggleState.TurningOff;
                        this.mState = toggleState;
                        globalActionsDialogLite3.mAirplaneState = toggleState;
                        break;
                    }
                    break;
                default:
                    this.mState = z ? ToggleState.On : ToggleState.Off;
                    break;
            }
        }

        @Override // com.android.systemui.globalactions.GlobalActionsDialogLite.Action
        public final boolean showBeforeProvisioning() {
            switch (this.$r8$classId) {
            }
            return false;
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public AirplaneModeAction(GlobalActionsDialogLite globalActionsDialogLite, int i) {
            this(R.drawable.ic_jog_dial_decline, R.drawable.ic_jog_dial_sound_on, R.string.grant_permissions_header_text, R.string.grant_credentials_permission_message_header);
            this.$r8$classId = i;
            switch (i) {
                case 1:
                    this.this$0 = globalActionsDialogLite;
                    this(R.drawable.ic_audio_notification, R.drawable.ic_audio_media_mute, R.string.gpsNotifTitle, R.string.gpsNotifTicker);
                    break;
                default:
                    this.this$0 = globalActionsDialogLite;
                    break;
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MyAdapter extends BaseAdapter {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ GlobalActionsDialogLite this$0;

        public /* synthetic */ MyAdapter(GlobalActionsDialogLite globalActionsDialogLite, int i) {
            this.$r8$classId = i;
            this.this$0 = globalActionsDialogLite;
        }

        @Override // android.widget.BaseAdapter, android.widget.ListAdapter
        public boolean areAllItemsEnabled() {
            switch (this.$r8$classId) {
                case 0:
                    return false;
                default:
                    return super.areAllItemsEnabled();
            }
        }

        public int countItems(boolean z) {
            int i = 0;
            for (int i2 = 0; i2 < this.this$0.mItems.size(); i2++) {
                ((Action) this.this$0.mItems.get(i2)).getClass();
                if (!z) {
                    i++;
                }
            }
            return i;
        }

        @Override // android.widget.Adapter
        public final int getCount() {
            switch (this.$r8$classId) {
                case 0:
                    return countItems(false) + countItems(true);
                case 1:
                    return this.this$0.mOverflowItems.size();
                default:
                    return this.this$0.mPowerItems.size();
            }
        }

        @Override // android.widget.Adapter
        public Action getItem(int i) {
            int i2 = 0;
            for (int i3 = 0; i3 < this.this$0.mItems.size(); i3++) {
                Action action = (Action) this.this$0.mItems.get(i3);
                if (this.this$0.shouldShowAction(action)) {
                    if (i2 == i) {
                        return action;
                    }
                    i2++;
                }
            }
            StringBuilder m = MutableObjectList$$ExternalSyntheticOutline0.m("position ", " out of range of showable actions, filtered count=", i);
            m.append(getCount());
            m.append(", keyguardshowing=");
            m.append(this.this$0.mKeyguardShowing);
            m.append(", provisioned=");
            m.append(this.this$0.mDeviceProvisioned);
            throw new IllegalArgumentException(m.toString());
        }

        @Override // android.widget.Adapter
        public final long getItemId(int i) {
            switch (this.$r8$classId) {
            }
            return i;
        }

        @Override // android.widget.Adapter
        public final View getView(int i, View view, ViewGroup viewGroup) {
            TextView textView = null;
            byte b = 0;
            switch (this.$r8$classId) {
                case 0:
                    Action item = getItem(i);
                    Context context = this.this$0.mContext;
                    View create = item.create(context, view, viewGroup, LayoutInflater.from(context));
                    create.setOnClickListener(new GlobalActionsDialogLite$MyAdapter$$ExternalSyntheticLambda0(this, i));
                    if (item instanceof LongPressAction) {
                        create.setOnLongClickListener(new GlobalActionsDialogLite$MyAdapter$$ExternalSyntheticLambda1(this, i));
                    }
                    return create;
                case 1:
                    Action action = (Action) this.this$0.mOverflowItems.get(i);
                    if (action == null) {
                        RecordingInputConnection$$ExternalSyntheticOutline0.m("No overflow action found at position: ", "GlobalActionsDialogLite", i);
                    } else {
                        if (view == null) {
                            view = LayoutInflater.from(this.this$0.mContext).inflate(com.android.wm.shell.R.layout.controls_more_item, viewGroup, false);
                        }
                        textView = (TextView) view;
                        if (action.getMessageResId() != 0) {
                            textView.setText(action.getMessageResId());
                        } else {
                            textView.setText(action.getMessage());
                        }
                    }
                    return textView;
                default:
                    Action action2 = (Action) this.this$0.mPowerItems.get(i);
                    if (action2 == null) {
                        RecordingInputConnection$$ExternalSyntheticOutline0.m("No power options action found at position: ", "GlobalActionsDialogLite", i);
                        return null;
                    }
                    if (view == null) {
                        view = LayoutInflater.from(this.this$0.mContext).inflate(com.android.wm.shell.R.layout.global_actions_power_item, viewGroup, false);
                    }
                    view.setOnClickListener(new GlobalActionsDialogLite$MyAdapter$$ExternalSyntheticLambda0(this, i, b));
                    if (action2 instanceof LongPressAction) {
                        view.setOnLongClickListener(new GlobalActionsDialogLite$MyAdapter$$ExternalSyntheticLambda1(this, i, b));
                    }
                    ImageView imageView = (ImageView) view.findViewById(R.id.icon);
                    TextView textView2 = (TextView) view.findViewById(R.id.message);
                    textView2.setSelected(true);
                    imageView.setImageDrawable(action2.getIcon(this.this$0.mContext));
                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    if (action2.getMessage() != null) {
                        textView2.setText(action2.getMessage());
                    } else {
                        textView2.setText(action2.getMessageResId());
                    }
                    return view;
            }
        }

        @Override // android.widget.BaseAdapter, android.widget.ListAdapter
        public boolean isEnabled(int i) {
            switch (this.$r8$classId) {
                case 0:
                    return getItem(i).isEnabled();
                default:
                    return super.isEnabled(i);
            }
        }

        @Override // android.widget.Adapter
        public final Object getItem(int i) {
            switch (this.$r8$classId) {
                case 0:
                    return getItem(i);
                case 1:
                    return (Action) this.this$0.mOverflowItems.get(i);
                default:
                    return (Action) this.this$0.mPowerItems.get(i);
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SilentModeTriStateAction implements Action, View.OnClickListener {
        public static final int[] ITEM_IDS = {R.id.packages_list, R.id.paddedBounds, R.id.pageDeleteDropTarget};
        public final AudioManager mAudioManager;
        public final AnonymousClass8 mHandler;

        public SilentModeTriStateAction(AudioManager audioManager, AnonymousClass8 anonymousClass8) {
            this.mAudioManager = audioManager;
            this.mHandler = anonymousClass8;
        }

        @Override // com.android.systemui.globalactions.GlobalActionsDialogLite.Action
        public final View create(Context context, View view, ViewGroup viewGroup, LayoutInflater layoutInflater) {
            View inflate = layoutInflater.inflate(R.layout.fragment_bread_crumbs, viewGroup, false);
            int ringerMode = this.mAudioManager.getRingerMode();
            int i = 0;
            while (i < 3) {
                View findViewById = inflate.findViewById(ITEM_IDS[i]);
                findViewById.setSelected(ringerMode == i);
                findViewById.setTag(Integer.valueOf(i));
                findViewById.setOnClickListener(this);
                i++;
            }
            return inflate;
        }

        @Override // com.android.systemui.globalactions.GlobalActionsDialogLite.Action
        public final Drawable getIcon(Context context) {
            return null;
        }

        @Override // com.android.systemui.globalactions.GlobalActionsDialogLite.Action
        public final CharSequence getMessage() {
            return null;
        }

        @Override // com.android.systemui.globalactions.GlobalActionsDialogLite.Action
        public final int getMessageResId() {
            return 0;
        }

        @Override // com.android.systemui.globalactions.GlobalActionsDialogLite.Action
        public final boolean isEnabled() {
            return true;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            if (view.getTag() instanceof Integer) {
                this.mAudioManager.setRingerMode(((Integer) view.getTag()).intValue());
                sendEmptyMessageDelayed(0, 300L);
            }
        }

        @Override // com.android.systemui.globalactions.GlobalActionsDialogLite.Action
        public final boolean showBeforeProvisioning() {
            return false;
        }

        @Override // com.android.systemui.globalactions.GlobalActionsDialogLite.Action
        public final void onPress() {
        }
    }
}

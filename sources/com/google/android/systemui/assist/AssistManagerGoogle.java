package com.google.android.systemui.assist;

import android.app.ActivityManager;
import android.app.ActivityOptions;
import android.app.SearchManager;
import android.app.StatusBarManager;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.metrics.LogMaker;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.SystemClock;
import android.provider.Settings;
import android.service.voice.VisualQueryAttentionResult;
import android.util.Log;
import android.view.IWindowManager;
import com.android.app.viewcapture.ViewCaptureAwareWindowManager;
import com.android.internal.app.AssistUtils;
import com.android.internal.app.IVisualQueryDetectionAttentionListener;
import com.android.internal.app.IVisualQueryRecognitionStatusListener;
import com.android.internal.app.IVoiceInteractionSessionListener;
import com.android.internal.app.IVoiceInteractionSessionShowCallback;
import com.android.internal.logging.MetricsLogger;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.systemui.assist.AssistDisclosure;
import com.android.systemui.assist.AssistLogger;
import com.android.systemui.assist.AssistManager$1;
import com.android.systemui.assist.AssistManager$UiController;
import com.android.systemui.assist.AssistantSessionEvent;
import com.android.systemui.assist.PhoneStateMonitor;
import com.android.systemui.assist.domain.interactor.AssistInteractor;
import com.android.systemui.dreams.conditions.AssistantAttentionCondition;
import com.android.systemui.model.SysUiState;
import com.android.systemui.navigationbar.NavigationModeController;
import com.android.systemui.recents.OverviewProxyService;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.shared.recents.IOverviewProxy;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import com.android.systemui.statusbar.policy.DeviceProvisionedControllerImpl;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.android.systemui.util.settings.SecureSettings;
import com.android.wm.shell.R;
import com.google.android.systemui.assist.AssistManagerGoogle;
import com.google.android.systemui.assist.uihints.AssistantPresenceHandler;
import com.google.android.systemui.assist.uihints.GoogleDefaultUiController;
import com.google.android.systemui.assist.uihints.NgaMessageHandler;
import com.google.android.systemui.assist.uihints.NgaUiController;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.IntPredicate;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AssistManagerGoogle {
    public final ActivityManager mActivityManager;
    public final AssistDisclosure mAssistDisclosure;
    public final AssistLogger mAssistLogger;
    public int[] mAssistOverrideInvocationTypes;
    public final AssistUtils mAssistUtils;
    public final AssistantPresenceHandler mAssistantPresenceHandler;
    public boolean mCheckAssistantStatus;
    public final CommandQueue mCommandQueue;
    public final Context mContext;
    public final GoogleDefaultUiController mDefaultUiController;
    public final DeviceProvisionedController mDeviceProvisionedController;
    public boolean mGoogleIsAssistant;
    public final AssistInteractor mInteractor;
    public int mNavigationMode;
    public boolean mNgaIsAssistant;
    public final NgaMessageHandler mNgaMessageHandler;
    public final NgaUiController mNgaUiController;
    public final AssistManagerGoogle$$ExternalSyntheticLambda2 mOnProcessBundle;
    public final OpaEnabledReceiver mOpaEnabledReceiver;
    public final OverviewProxyService mOverviewProxyService;
    public final PhoneStateMonitor mPhoneStateMonitor;
    public final SecureSettings mSecureSettings;
    public final SelectedUserInteractor mSelectedUserInteractor;
    public boolean mSqueezeSetUp;
    public final Lazy mSysUiState;
    public AssistManager$UiController mUiController;
    public final Handler mUiHandler;
    public final UserTracker mUserTracker;
    public final List mVisualQueryAttentionListeners = new ArrayList();
    public final AssistManager$1 mVisualQueryDetectionAttentionListener = new IVisualQueryDetectionAttentionListener.Stub() { // from class: com.android.systemui.assist.AssistManager$1
        public final void onAttentionGained(VisualQueryAttentionResult visualQueryAttentionResult) {
            AssistManagerGoogle.m911$$Nest$mhandleVisualAttentionChanged(AssistManagerGoogle.this, true);
        }

        public final void onAttentionLost(int i) {
            AssistManagerGoogle.m911$$Nest$mhandleVisualAttentionChanged(AssistManagerGoogle.this, false);
        }
    };
    public final IWindowManager mWindowManagerService;

    /* renamed from: -$$Nest$mhandleVisualAttentionChanged, reason: not valid java name */
    public static void m911$$Nest$mhandleVisualAttentionChanged(AssistManagerGoogle assistManagerGoogle, boolean z) {
        Consumer consumer;
        StatusBarManager statusBarManager = (StatusBarManager) assistManagerGoogle.mContext.getSystemService(StatusBarManager.class);
        if (statusBarManager != null) {
            statusBarManager.setIconVisibility("assist_attention", z);
        }
        List list = assistManagerGoogle.mVisualQueryAttentionListeners;
        if (z) {
            final int i = 0;
            consumer = new Consumer() { // from class: com.android.systemui.assist.AssistManager$$ExternalSyntheticLambda1
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    AssistantAttentionCondition.AnonymousClass1 anonymousClass1 = (AssistantAttentionCondition.AnonymousClass1) obj;
                    switch (i) {
                        case 0:
                            AssistantAttentionCondition.this.updateCondition(true);
                            break;
                        default:
                            AssistantAttentionCondition.this.updateCondition(false);
                            break;
                    }
                }
            };
        } else {
            final int i2 = 1;
            consumer = new Consumer() { // from class: com.android.systemui.assist.AssistManager$$ExternalSyntheticLambda1
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    AssistantAttentionCondition.AnonymousClass1 anonymousClass1 = (AssistantAttentionCondition.AnonymousClass1) obj;
                    switch (i2) {
                        case 0:
                            AssistantAttentionCondition.this.updateCondition(true);
                            break;
                        default:
                            AssistantAttentionCondition.this.updateCondition(false);
                            break;
                    }
                }
            };
        }
        ((ArrayList) list).forEach(consumer);
    }

    /* JADX WARN: Type inference failed for: r7v1, types: [com.android.systemui.assist.AssistManager$1] */
    public AssistManagerGoogle(DeviceProvisionedController deviceProvisionedController, Context context, AssistUtils assistUtils, NgaUiController ngaUiController, CommandQueue commandQueue, OpaEnabledReceiver opaEnabledReceiver, PhoneStateMonitor phoneStateMonitor, OverviewProxyService overviewProxyService, OpaEnabledDispatcher opaEnabledDispatcher, KeyguardUpdateMonitor keyguardUpdateMonitor, NavigationModeController navigationModeController, AssistantPresenceHandler assistantPresenceHandler, NgaMessageHandler ngaMessageHandler, Lazy lazy, Handler handler, GoogleDefaultUiController googleDefaultUiController, IWindowManager iWindowManager, AssistLogger assistLogger, UserTracker userTracker, SecureSettings secureSettings, SelectedUserInteractor selectedUserInteractor, ActivityManager activityManager, AssistInteractor assistInteractor, ViewCaptureAwareWindowManager viewCaptureAwareWindowManager) {
        this.mContext = context;
        this.mDeviceProvisionedController = deviceProvisionedController;
        this.mCommandQueue = commandQueue;
        this.mAssistUtils = assistUtils;
        this.mAssistDisclosure = new AssistDisclosure(context, handler, viewCaptureAwareWindowManager);
        this.mOverviewProxyService = overviewProxyService;
        this.mPhoneStateMonitor = phoneStateMonitor;
        this.mAssistLogger = assistLogger;
        this.mUserTracker = userTracker;
        this.mSecureSettings = secureSettings;
        this.mSelectedUserInteractor = selectedUserInteractor;
        this.mActivityManager = activityManager;
        this.mInteractor = assistInteractor;
        assistUtils.registerVoiceInteractionSessionListener(new IVoiceInteractionSessionListener.Stub() { // from class: com.google.android.systemui.assist.AssistManagerGoogle.2
            public final void onSetUiHints(Bundle bundle) {
                String string = bundle.getString("action");
                if ("set_assist_gesture_constrained".equals(string)) {
                    SysUiState sysUiState = (SysUiState) AssistManagerGoogle.this.mSysUiState.get();
                    sysUiState.setFlag(8192L, bundle.getBoolean("should_constrain", false));
                    sysUiState.commitUpdate(0);
                } else if (!"show_global_actions".equals(string)) {
                    AssistManagerGoogle assistManagerGoogle = AssistManagerGoogle.this;
                    assistManagerGoogle.mNgaMessageHandler.processBundle(bundle, assistManagerGoogle.mOnProcessBundle);
                } else {
                    try {
                        AssistManagerGoogle.this.mWindowManagerService.showGlobalActions();
                    } catch (RemoteException e) {
                        Log.e("AssistManagerGoogle", "showGlobalActions failed", e);
                    }
                }
            }

            public final void onVoiceSessionHidden() {
                AssistManagerGoogle.this.mAssistLogger.reportAssistantSessionEvent(AssistantSessionEvent.ASSISTANT_SESSION_CLOSE);
            }

            public final void onVoiceSessionShown() {
                AssistManagerGoogle.this.mAssistLogger.reportAssistantSessionEvent(AssistantSessionEvent.ASSISTANT_SESSION_UPDATE);
            }

            public final void onVoiceSessionWindowVisibilityChanged(boolean z) {
            }
        });
        if (context.getResources().getBoolean(R.bool.config_enableVisualQueryAttentionDetection)) {
            assistUtils.subscribeVisualQueryRecognitionStatus(new IVisualQueryRecognitionStatusListener.Stub() { // from class: com.android.systemui.assist.AssistManager$5
                public final void onStartPerceiving() {
                    AssistManagerGoogle assistManagerGoogle = AssistManagerGoogle.this;
                    assistManagerGoogle.mAssistUtils.enableVisualQueryDetection(assistManagerGoogle.mVisualQueryDetectionAttentionListener);
                    StatusBarManager statusBarManager = (StatusBarManager) AssistManagerGoogle.this.mContext.getSystemService(StatusBarManager.class);
                    if (statusBarManager != null) {
                        statusBarManager.setIcon("assist_attention", R.drawable.ic_assistant_attention_indicator, 0, "Attention Icon for Assistant");
                        statusBarManager.setIconVisibility("assist_attention", false);
                    }
                }

                public final void onStopPerceiving() {
                    AssistManagerGoogle.m911$$Nest$mhandleVisualAttentionChanged(AssistManagerGoogle.this, false);
                    AssistManagerGoogle.this.mAssistUtils.disableVisualQueryDetection();
                    StatusBarManager statusBarManager = (StatusBarManager) AssistManagerGoogle.this.mContext.getSystemService(StatusBarManager.class);
                    if (statusBarManager != null) {
                        statusBarManager.removeIcon("assist_attention");
                    }
                }
            });
        }
        this.mSysUiState = lazy;
        overviewProxyService.addCallback(new OverviewProxyService.OverviewProxyListener() { // from class: com.android.systemui.assist.AssistManager$2
            @Override // com.android.systemui.recents.OverviewProxyService.OverviewProxyListener
            public final void onAssistantGestureCompletion(float f) {
                AssistManagerGoogle assistManagerGoogle = AssistManagerGoogle.this;
                assistManagerGoogle.mCheckAssistantStatus = true;
                assistManagerGoogle.mUiController.onGestureCompletion(f / assistManagerGoogle.mContext.getResources().getDisplayMetrics().density);
            }

            @Override // com.android.systemui.recents.OverviewProxyService.OverviewProxyListener
            public final void onAssistantProgress(float f) {
                AssistManagerGoogle.this.onInvocationProgress(1, f);
            }
        });
        this.mCheckAssistantStatus = true;
        this.mUiHandler = handler;
        this.mOpaEnabledReceiver = opaEnabledReceiver;
        addOpaEnabledListener(opaEnabledDispatcher);
        keyguardUpdateMonitor.registerCallback(new KeyguardUpdateMonitorCallback() { // from class: com.google.android.systemui.assist.AssistManagerGoogle.1
            @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
            public final void onUserSwitching(int i) {
                OpaEnabledReceiver opaEnabledReceiver2 = AssistManagerGoogle.this.mOpaEnabledReceiver;
                opaEnabledReceiver2.updateOpaEnabledState(true, null);
                opaEnabledReceiver2.mContentResolver.unregisterContentObserver(opaEnabledReceiver2.mContentObserver);
                opaEnabledReceiver2.registerContentObserver();
                opaEnabledReceiver2.mContext.unregisterReceiver(opaEnabledReceiver2.mBroadcastReceiver);
                opaEnabledReceiver2.registerEnabledReceiver(i);
            }
        });
        this.mNgaUiController = ngaUiController;
        this.mDefaultUiController = googleDefaultUiController;
        this.mUiController = googleDefaultUiController;
        this.mNavigationMode = navigationModeController.addListener(new NavigationModeController.ModeChangedListener() { // from class: com.google.android.systemui.assist.AssistManagerGoogle$$ExternalSyntheticLambda0
            @Override // com.android.systemui.navigationbar.NavigationModeController.ModeChangedListener
            public final void onNavigationModeChanged(int i) {
                AssistManagerGoogle.this.mNavigationMode = i;
            }
        });
        this.mAssistantPresenceHandler = assistantPresenceHandler;
        assistantPresenceHandler.mAssistantPresenceChangeListeners.add(new AssistManagerGoogle$$ExternalSyntheticLambda1(this));
        this.mNgaMessageHandler = ngaMessageHandler;
        this.mOnProcessBundle = new AssistManagerGoogle$$ExternalSyntheticLambda2(0, this);
        this.mWindowManagerService = iWindowManager;
    }

    public final void addOpaEnabledListener(OpaEnabledListener opaEnabledListener) {
        OpaEnabledReceiver opaEnabledReceiver = this.mOpaEnabledReceiver;
        opaEnabledReceiver.mListeners.add(opaEnabledListener);
        opaEnabledListener.onOpaEnabledReceived(opaEnabledReceiver.mContext, opaEnabledReceiver.mIsOpaEligible, opaEnabledReceiver.mIsAGSAAssistant, opaEnabledReceiver.mIsOpaEnabled, opaEnabledReceiver.mIsLongPressHomeEnabled);
    }

    public final void hideAssist() {
        this.mAssistUtils.hideCurrentSession();
    }

    public final void onInvocationProgress(int i, float f) {
        if (f == 0.0f || f == 1.0f) {
            this.mCheckAssistantStatus = true;
            if (i == 2) {
                this.mSqueezeSetUp = Settings.Secure.getInt(this.mContext.getContentResolver(), "assist_gesture_setup_complete", 0) == 1;
            }
        }
        if (this.mCheckAssistantStatus) {
            AssistantPresenceHandler assistantPresenceHandler = this.mAssistantPresenceHandler;
            ComponentName assistComponentForUser = assistantPresenceHandler.mAssistUtils.getAssistComponentForUser(-2);
            assistantPresenceHandler.updateAssistantPresence(assistComponentForUser != null && "com.google.android.googlequicksearchbox/com.google.android.voiceinteraction.GsaVoiceInteractionService".equals(assistComponentForUser.flattenToString()), assistantPresenceHandler.mNgaIsAssistant);
            this.mCheckAssistantStatus = false;
        }
        if (i != 2 || this.mSqueezeSetUp) {
            this.mUiController.onInvocationProgress(i, f);
        }
    }

    public final boolean shouldOverrideAssist(final int i) {
        int[] iArr = this.mAssistOverrideInvocationTypes;
        return iArr != null && Arrays.stream(iArr).anyMatch(new IntPredicate() { // from class: com.android.systemui.assist.AssistManager$$ExternalSyntheticLambda0
            @Override // java.util.function.IntPredicate
            public final boolean test(int i2) {
                return i2 == i;
            }
        });
    }

    public final void startAssist(Bundle bundle) {
        final Intent assistIntent;
        if (this.mActivityManager.getLockTaskModeState() == 1) {
            return;
        }
        if ((bundle == null || !bundle.containsKey("invocation_type")) ? false : shouldOverrideAssist(bundle.getInt("invocation_type"))) {
            try {
                IOverviewProxy iOverviewProxy = this.mOverviewProxyService.mOverviewProxy;
                if (iOverviewProxy == null) {
                    Log.w("AssistManager", "No OverviewProxyService to invoke assistant override");
                    return;
                }
                int i = bundle.getInt("invocation_type");
                IOverviewProxy.Stub.Proxy proxy = (IOverviewProxy.Stub.Proxy) iOverviewProxy;
                Parcel obtain = Parcel.obtain(proxy.mRemote);
                try {
                    obtain.writeInterfaceToken("com.android.systemui.shared.recents.IOverviewProxy");
                    obtain.writeInt(i);
                    proxy.mRemote.transact(29, obtain, null, 1);
                    obtain.recycle();
                    return;
                } catch (Throwable th) {
                    obtain.recycle();
                    throw th;
                }
            } catch (RemoteException e) {
                Log.w("AssistManager", "Unable to invoke assistant via OverviewProxyService override", e);
                return;
            }
        }
        ComponentName assistComponentForUser = this.mAssistUtils.getAssistComponentForUser(this.mSelectedUserInteractor.getSelectedUserId());
        if (assistComponentForUser == null) {
            return;
        }
        boolean equals = assistComponentForUser.equals(this.mAssistUtils.getActiveServiceComponentName());
        if (bundle == null) {
            bundle = new Bundle();
        }
        Bundle bundle2 = bundle;
        int i2 = bundle2.getInt("invocation_type", 0);
        int phoneState = this.mPhoneStateMonitor.getPhoneState();
        bundle2.putInt("invocation_phone_state", phoneState);
        bundle2.putLong("invocation_time_ms", SystemClock.elapsedRealtime());
        this.mAssistLogger.reportAssistantInvocationEventFromLegacy(i2, true, assistComponentForUser, Integer.valueOf(phoneState));
        MetricsLogger.action(new LogMaker(1716).setType(1).setSubtype((phoneState << 4) | (i2 << 1) | ((this.mAssistantPresenceHandler.mNgaIsAssistant ? 1 : 0) << 8)));
        this.mInteractor.repository._latestInvocationType.tryEmit(Integer.valueOf(i2));
        if (equals) {
            this.mAssistUtils.showSessionForActiveService(bundle2, 4, this.mContext.getAttributionTag(), (IVoiceInteractionSessionShowCallback) null, (IBinder) null);
            return;
        }
        if (((DeviceProvisionedControllerImpl) this.mDeviceProvisionedController).deviceProvisioned.get()) {
            this.mCommandQueue.animateCollapsePanels(3, false);
            boolean z = this.mSecureSettings.getIntForUser("assist_structure_enabled", 1, -2) != 0;
            SearchManager searchManager = (SearchManager) this.mContext.getSystemService("search");
            if (searchManager == null || (assistIntent = searchManager.getAssistIntent(z)) == null) {
                return;
            }
            assistIntent.setComponent(assistComponentForUser);
            assistIntent.putExtras(bundle2);
            if (z && AssistUtils.isDisclosureEnabled(this.mContext)) {
                AssistDisclosure assistDisclosure = this.mAssistDisclosure;
                AssistDisclosure.AnonymousClass1 anonymousClass1 = assistDisclosure.mShowRunnable;
                Handler handler = assistDisclosure.mHandler;
                handler.removeCallbacks(anonymousClass1);
                handler.post(assistDisclosure.mShowRunnable);
            }
            try {
                final ActivityOptions makeCustomAnimation = ActivityOptions.makeCustomAnimation(this.mContext, R.anim.search_launch_enter, R.anim.search_launch_exit);
                assistIntent.addFlags(268435456);
                AsyncTask.execute(new Runnable() { // from class: com.android.systemui.assist.AssistManager$4
                    @Override // java.lang.Runnable
                    public final void run() {
                        AssistManagerGoogle.this.mContext.startActivityAsUser(assistIntent, makeCustomAnimation.toBundle(), ((UserTrackerImpl) AssistManagerGoogle.this.mUserTracker).getUserHandle());
                    }
                });
            } catch (ActivityNotFoundException unused) {
                Log.w("AssistManager", "Activity not found for " + assistIntent.getAction());
            }
        }
    }
}

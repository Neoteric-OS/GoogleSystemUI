package com.android.systemui.biometrics;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.biometrics.IBiometricSysuiReceiver;
import android.hardware.biometrics.PromptInfo;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.UserManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.android.app.animation.Interpolators;
import com.android.app.viewcapture.ViewCaptureAwareWindowManager;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.internal.widget.LockPatternUtils;
import com.android.keyguard.ClockEventController$zenModeCallback$1$$ExternalSyntheticOutline0;
import com.android.keyguard.KeyguardClockSwitchController$$ExternalSyntheticOutline0;
import com.android.keyguard.LegacyLockIconViewController$$ExternalSyntheticOutline0;
import com.android.settingslib.bluetooth.LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0;
import com.android.systemui.biometrics.AuthContainerView.AnonymousClass1;
import com.android.systemui.biometrics.domain.interactor.PromptSelectorInteractor;
import com.android.systemui.biometrics.domain.interactor.PromptSelectorInteractorImpl;
import com.android.systemui.biometrics.shared.model.BiometricModalities;
import com.android.systemui.biometrics.shared.model.PromptKind;
import com.android.systemui.biometrics.ui.CredentialView;
import com.android.systemui.biometrics.ui.binder.BiometricViewBinder;
import com.android.systemui.biometrics.ui.binder.BiometricViewBinderKt;
import com.android.systemui.biometrics.ui.binder.Spaghetti;
import com.android.systemui.biometrics.ui.viewmodel.CredentialViewModel;
import com.android.systemui.biometrics.ui.viewmodel.PromptViewModel;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.statusbar.VibratorHelper;
import com.android.wm.shell.R;
import com.google.android.msdl.domain.MSDLPlayer;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;
import dagger.internal.Provider;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AuthContainerView extends LinearLayout implements AuthDialog, WakefulnessLifecycle.Observer, CredentialView.Host {
    public final AuthContainerView$$ExternalSyntheticLambda1 mBackCallback;
    public final ImageView mBackgroundView;
    final BiometricCallback mBiometricCallback;
    public final Spaghetti mBiometricView;
    public final Config mConfig;
    int mContainerState;
    public byte[] mCredentialAttestation;
    public View mCredentialView;
    public final DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider mCredentialViewModelProvider;
    public final int mEffectiveUserId;
    public final Set mFailedModalities;
    public final InteractionJankMonitor mInteractionJankMonitor;
    public final ViewGroup mLayout;
    public final Interpolator mLinearOutSlowIn;
    public final LockPatternUtils mLockPatternUtils;
    public final AuthPanelController mPanelController;
    public final View mPanelView;
    public Integer mPendingCallbackReason;
    public final Provider mPromptSelectorInteractorProvider;
    public final PromptViewModel mPromptViewModel;
    public final float mTranslationY;
    public final WakefulnessLifecycle mWakefulnessLifecycle;
    public final ViewCaptureAwareWindowManager mWindowManager;
    public final IBinder mWindowToken;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class BiometricCallback implements Spaghetti.Callback {
        public BiometricCallback() {
        }

        @Override // com.android.systemui.biometrics.ui.binder.Spaghetti.Callback
        public final void onAuthenticated() {
            AuthContainerView.this.animateAway(4, true);
        }

        @Override // com.android.systemui.biometrics.ui.binder.Spaghetti.Callback
        public final void onAuthenticatedAndConfirmed() {
            AuthContainerView.this.animateAway(3, true);
        }

        @Override // com.android.systemui.biometrics.ui.binder.Spaghetti.Callback
        public final void onButtonNegative() {
            AuthContainerView.this.animateAway(2, true);
        }

        @Override // com.android.systemui.biometrics.ui.binder.Spaghetti.Callback
        public final void onButtonTryAgain() {
            AuthContainerView authContainerView = AuthContainerView.this;
            authContainerView.mFailedModalities.clear();
            Config config = authContainerView.mConfig;
            IBiometricSysuiReceiver currentReceiver = config.mCallback.getCurrentReceiver(config.mRequestId);
            if (currentReceiver == null) {
                Log.w("AuthController", "Skip onTryAgainPressed");
                return;
            }
            try {
                currentReceiver.onTryAgainPressed();
            } catch (RemoteException e) {
                Log.e("AuthController", "RemoteException when handling try again", e);
            }
        }

        @Override // com.android.systemui.biometrics.ui.binder.Spaghetti.Callback
        public final void onContentViewMoreOptionsButtonPressed() {
            AuthContainerView.this.animateAway(8, true);
        }

        @Override // com.android.systemui.biometrics.ui.binder.Spaghetti.Callback
        public final void onError() {
            AuthContainerView.this.animateAway(5, true);
        }

        @Override // com.android.systemui.biometrics.ui.binder.Spaghetti.Callback
        public final void onStartDelayedFingerprintSensor() {
            Config config = AuthContainerView.this.mConfig;
            IBiometricSysuiReceiver currentReceiver = config.mCallback.getCurrentReceiver(config.mRequestId);
            if (currentReceiver == null) {
                Log.e("AuthController", "onStartUdfpsNow: Receiver is null");
                return;
            }
            try {
                currentReceiver.onStartFingerprintNow();
            } catch (RemoteException e) {
                Log.e("AuthController", "RemoteException when sending onDialogAnimatedIn", e);
            }
        }

        @Override // com.android.systemui.biometrics.ui.binder.Spaghetti.Callback
        public final void onUseDeviceCredential() {
            AuthContainerView authContainerView = AuthContainerView.this;
            Config config = authContainerView.mConfig;
            IBiometricSysuiReceiver currentReceiver = config.mCallback.getCurrentReceiver(config.mRequestId);
            if (currentReceiver == null) {
                Log.w("AuthController", "Skip onDeviceCredentialPressed");
            } else {
                try {
                    currentReceiver.onDeviceCredentialPressed();
                } catch (RemoteException e) {
                    Log.e("AuthController", "RemoteException when handling credential button", e);
                }
            }
            authContainerView.addCredentialView(false, true);
            authContainerView.mConfig.mPromptInfo.setAuthenticators(32768);
        }

        @Override // com.android.systemui.biometrics.ui.binder.Spaghetti.Callback
        public final void onUserCanceled() {
            AuthContainerView authContainerView = AuthContainerView.this;
            authContainerView.sendEarlyUserCanceled();
            authContainerView.animateAway(1, true);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Config {
        public AuthController mCallback;
        public Context mContext;
        public String mOpPackageName;
        public long mOperationId;
        public PromptInfo mPromptInfo;
        public long mRequestId;
        public boolean mRequireConfirmation;
        public int[] mSensorIds;
        public boolean mSkipIntro;
        public int mUserId;
    }

    /* JADX WARN: Type inference failed for: r4v1, types: [com.android.systemui.biometrics.AuthContainerView$$ExternalSyntheticLambda1] */
    public AuthContainerView(Config config, CoroutineScope coroutineScope, List list, List list2, WakefulnessLifecycle wakefulnessLifecycle, UserManager userManager, LockPatternUtils lockPatternUtils, InteractionJankMonitor interactionJankMonitor, Provider provider, PromptViewModel promptViewModel, DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider, VibratorHelper vibratorHelper, Lazy lazy, MSDLPlayer mSDLPlayer) {
        super(config.mContext);
        this.mWindowToken = new Binder();
        this.mContainerState = 0;
        this.mFailedModalities = new HashSet();
        this.mBackCallback = new OnBackInvokedCallback() { // from class: com.android.systemui.biometrics.AuthContainerView$$ExternalSyntheticLambda1
            @Override // android.window.OnBackInvokedCallback
            public final void onBackInvoked() {
                AuthContainerView.this.onBackInvoked();
            }
        };
        this.mConfig = config;
        this.mLockPatternUtils = lockPatternUtils;
        int credentialOwnerProfile = userManager.getCredentialOwnerProfile(config.mUserId);
        this.mEffectiveUserId = credentialOwnerProfile;
        this.mWindowManager = new ViewCaptureAwareWindowManager((WindowManager) getContext().getSystemService(WindowManager.class), lazy);
        this.mWakefulnessLifecycle = wakefulnessLifecycle;
        this.mPromptViewModel = promptViewModel;
        this.mTranslationY = getResources().getDimension(R.dimen.biometric_dialog_animation_translation_offset);
        this.mLinearOutSlowIn = Interpolators.LINEAR_OUT_SLOW_IN;
        BiometricCallback biometricCallback = new BiometricCallback();
        this.mBiometricCallback = biometricCallback;
        BiometricModalities biometricModalities = new BiometricModalities(Utils.findFirstSensorProperties(list, config.mSensorIds), Utils.findFirstSensorProperties(list2, config.mSensorIds));
        boolean z = ((LinearLayout) this).mContext.getResources().getConfiguration().orientation == 2;
        this.mPromptSelectorInteractorProvider = provider;
        ((PromptSelectorInteractorImpl) ((PromptSelectorInteractor) provider.get())).setPrompt(config.mPromptInfo, credentialOwnerProfile, config.mRequestId, biometricModalities, config.mOperationId, config.mOpPackageName, false, z);
        LayoutInflater from = LayoutInflater.from(((LinearLayout) this).mContext);
        PromptKind promptKind = (PromptKind) ((StateFlowImpl) promptViewModel.promptKind.$$delegate_0).getValue();
        if (!promptKind.isBiometric()) {
            this.mLayout = (FrameLayout) from.inflate(R.layout.auth_container_view, (ViewGroup) this, false);
        } else if (promptKind.isTwoPaneLandscapeBiometric()) {
            this.mLayout = (ConstraintLayout) from.inflate(R.layout.biometric_prompt_two_pane_layout, (ViewGroup) this, false);
        } else {
            this.mLayout = (ConstraintLayout) from.inflate(R.layout.biometric_prompt_one_pane_layout, (ViewGroup) this, false);
        }
        addView(this.mLayout);
        ImageView imageView = (ImageView) this.mLayout.findViewById(R.id.background);
        this.mBackgroundView = imageView;
        View findViewById = this.mLayout.findViewById(R.id.panel);
        this.mPanelView = findViewById;
        this.mPanelController = new AuthPanelController(((LinearLayout) this).mContext, findViewById);
        this.mInteractionJankMonitor = interactionJankMonitor;
        this.mCredentialViewModelProvider = switchingProvider;
        ReadonlyStateFlow readonlyStateFlow = promptViewModel.promptKind;
        if (((PromptKind) ((StateFlowImpl) readonlyStateFlow.$$delegate_0).getValue()).isBiometric()) {
            ViewGroup viewGroup = this.mLayout;
            this.mBiometricView = BiometricViewBinder.bind(viewGroup, promptViewModel, new AnonymousClass1(viewGroup, "transit", 450L), imageView, biometricCallback, coroutineScope, vibratorHelper, mSDLPlayer);
        } else if (((PromptKind) ((StateFlowImpl) readonlyStateFlow.$$delegate_0).getValue()).isCredential()) {
            addCredentialView(true, false);
        } else {
            ((PromptSelectorInteractorImpl) ((PromptSelectorInteractor) provider.get())).resetPrompt(config.mRequestId);
        }
        setOnKeyListener(new View.OnKeyListener() { // from class: com.android.systemui.biometrics.AuthContainerView$$ExternalSyntheticLambda2
            @Override // android.view.View.OnKeyListener
            public final boolean onKey(View view, int i, KeyEvent keyEvent) {
                AuthContainerView authContainerView = AuthContainerView.this;
                authContainerView.getClass();
                if (i != 4) {
                    return false;
                }
                if (keyEvent.getAction() == 1) {
                    authContainerView.onBackInvoked();
                }
                return true;
            }
        });
        setImportantForAccessibility(2);
        requestFocus();
    }

    public static WindowManager.LayoutParams getLayoutParams(IBinder iBinder, CharSequence charSequence) {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-1, -1, 2038, 17309698, -3);
        layoutParams.privateFlags |= 16;
        layoutParams.setFitInsetsTypes(layoutParams.getFitInsetsTypes() & (~WindowInsets.Type.ime()) & (~WindowInsets.Type.systemBars()));
        layoutParams.layoutInDisplayCutoutMode = 3;
        layoutParams.setTitle("BiometricPrompt");
        layoutParams.accessibilityTitle = charSequence;
        layoutParams.dimAmount = 0.5f;
        layoutParams.token = iBinder;
        return layoutParams;
    }

    public final void addCredentialView(boolean z, boolean z2) {
        int i;
        LayoutInflater from = LayoutInflater.from(((LinearLayout) this).mContext);
        PromptKind credentialType = Utils.getCredentialType(this.mLockPatternUtils, this.mEffectiveUserId);
        if (credentialType instanceof PromptKind.Pattern) {
            i = R.layout.auth_credential_pattern_view;
        } else if (credentialType instanceof PromptKind.Pin) {
            i = R.layout.auth_credential_pin_view;
        } else {
            if (!(credentialType instanceof PromptKind.Password)) {
                throw new IllegalStateException("Unknown credential type: " + credentialType);
            }
            i = R.layout.auth_credential_password_view;
        }
        this.mCredentialView = from.inflate(i, this.mLayout, false);
        this.mBackgroundView.setOnClickListener(null);
        this.mBackgroundView.setImportantForAccessibility(2);
        CredentialViewModel credentialViewModel = (CredentialViewModel) this.mCredentialViewModelProvider.get();
        AuthContainerView$$ExternalSyntheticOutline0.m(z2, credentialViewModel._animateContents, null);
        ((CredentialView) this.mCredentialView).init(credentialViewModel, this, this.mPanelController, z, this.mBiometricCallback);
        this.mLayout.addView(this.mCredentialView);
    }

    public final void animateAway(int i, boolean z) {
        int i2 = this.mContainerState;
        if (i2 == 1) {
            Log.w("AuthContainerView", "startDismiss(): waiting for onDialogAnimatedIn");
            this.mContainerState = 2;
            return;
        }
        if (i2 == 4) {
            Log.w("AuthContainerView", "Already dismissing, sendReason: " + z + " reason: " + i);
            return;
        }
        this.mContainerState = 4;
        if (isAttachedToWindow() && getRootWindowInsets().isVisible(WindowInsets.Type.ime())) {
            getWindowInsetsController().hide(WindowInsets.Type.ime());
        }
        if (z) {
            this.mPendingCallbackReason = Integer.valueOf(i);
        } else {
            this.mPendingCallbackReason = null;
        }
        final AuthContainerView$$ExternalSyntheticLambda3 authContainerView$$ExternalSyntheticLambda3 = new AuthContainerView$$ExternalSyntheticLambda3(this, 0);
        this.mConfig.getClass();
        postOnAnimation(new Runnable() { // from class: com.android.systemui.biometrics.AuthContainerView$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                final AuthContainerView authContainerView = AuthContainerView.this;
                authContainerView.animate().alpha(0.0f).translationY(authContainerView.mTranslationY).setDuration(350L).setInterpolator(authContainerView.mLinearOutSlowIn).setListener(authContainerView.new AnonymousClass1(authContainerView, "dismiss", 350L)).setUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.biometrics.AuthContainerView$$ExternalSyntheticLambda7
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                        AuthContainerView authContainerView2 = AuthContainerView.this;
                        if (authContainerView2.mWindowManager == null || authContainerView2.getViewRootImpl() == null) {
                            Log.w("AuthContainerView", "skip updateViewLayout() for dim animation.");
                            return;
                        }
                        WindowManager.LayoutParams layoutParams = authContainerView2.getViewRootImpl().mWindowAttributes;
                        layoutParams.dimAmount = (1.0f - ((Float) valueAnimator.getAnimatedValue()).floatValue()) * 0.5f;
                        authContainerView2.mWindowManager.updateViewLayout(authContainerView2, layoutParams);
                    }
                }).withLayer().withEndAction(authContainerView$$ExternalSyntheticLambda3).start();
            }
        });
    }

    public final void dismissWithoutCallback(boolean z) {
        if (z) {
            animateAway(0, false);
            return;
        }
        if (this.mContainerState == 1) {
            View view = this.mCredentialView;
            if (view != null && view.isAttachedToWindow()) {
                this.mCredentialView.animate().cancel();
            }
            this.mPanelView.animate().cancel();
            ViewPropertyAnimator animate = this.mBiometricView.view.animate();
            if (animate != null) {
                animate.cancel();
            }
            animate().cancel();
            onDialogAnimatedIn();
        }
        removeWindowIfAttached();
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("    isAttachedToWindow=" + isAttachedToWindow());
        StringBuilder m = LegacyLockIconViewController$$ExternalSyntheticOutline0.m(new StringBuilder("    containerState="), this.mContainerState, printWriter, "    pendingCallbackReason=");
        m.append(this.mPendingCallbackReason);
        printWriter.println(m.toString());
        KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(new StringBuilder("    config exist="), this.mConfig != null, printWriter);
        if (this.mConfig != null) {
            KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(new StringBuilder("    config.sensorIds exist="), this.mConfig.mSensorIds != null, printWriter);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mContainerState == 4) {
            return;
        }
        this.mWakefulnessLifecycle.mObservers.add(this);
        if (this.mConfig.mSkipIntro) {
            this.mContainerState = 3;
        } else {
            this.mContainerState = 1;
            setY(this.mTranslationY);
            setAlpha(0.0f);
            this.mConfig.getClass();
            postOnAnimation(new AuthContainerView$$ExternalSyntheticLambda3(this, 1));
        }
        OnBackInvokedDispatcher findOnBackInvokedDispatcher = findOnBackInvokedDispatcher();
        if (findOnBackInvokedDispatcher != null) {
            findOnBackInvokedDispatcher.registerOnBackInvokedCallback(0, this.mBackCallback);
        }
    }

    public void onBackInvoked() {
        sendEarlyUserCanceled();
        animateAway(1, true);
    }

    public final void onCredentialAttemptsRemaining(int i, String str) {
        if (i == 1) {
            AlertDialog create = new AlertDialog.Builder(((LinearLayout) this).mContext).setTitle(R.string.biometric_dialog_last_attempt_before_wipe_dialog_title).setMessage(str).setPositiveButton(android.R.string.ok, (DialogInterface.OnClickListener) null).create();
            create.getWindow().setType(2009);
            create.show();
        } else if (i <= 0) {
            AlertDialog create2 = new AlertDialog.Builder(((LinearLayout) this).mContext).setMessage(str).setPositiveButton(R.string.failed_attempts_now_wiping_dialog_dismiss, (DialogInterface.OnClickListener) null).setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.android.systemui.biometrics.AuthContainerView$$ExternalSyntheticLambda8
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    AuthContainerView.this.animateAway(5, true);
                }
            }).create();
            create2.getWindow().setType(2009);
            create2.show();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        if (findOnBackInvokedDispatcher() != null) {
            findOnBackInvokedDispatcher().unregisterOnBackInvokedCallback(this.mBackCallback);
        }
        super.onDetachedFromWindow();
        this.mWakefulnessLifecycle.removeObserver(this);
    }

    public final void onDialogAnimatedIn() {
        int i = this.mContainerState;
        if (i == 2) {
            Log.d("AuthContainerView", "onDialogAnimatedIn(): mPendingDismissDialog=true, dismissing now");
            animateAway(1, true);
            return;
        }
        if (i == 4 || i == 5) {
            LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0.m(new StringBuilder("onDialogAnimatedIn(): ignore, already animating out or gone - state: "), this.mContainerState, "AuthContainerView");
            return;
        }
        this.mContainerState = 3;
        Spaghetti spaghetti = this.mBiometricView;
        if (spaghetti != null) {
            boolean z = spaghetti.modalities.getHasFaceAndFingerprint() && !this.mConfig.mRequireConfirmation;
            Config config = this.mConfig;
            AuthController authController = config.mCallback;
            long j = config.mRequestId;
            boolean z2 = !z;
            IBiometricSysuiReceiver currentReceiver = authController.getCurrentReceiver(j);
            if (currentReceiver == null) {
                Log.w("AuthController", "Skip onDialogAnimatedIn");
            } else {
                try {
                    currentReceiver.onDialogAnimatedIn(z2);
                } catch (RemoteException e) {
                    Log.e("AuthController", "RemoteException when sending onDialogAnimatedIn", e);
                }
            }
            Spaghetti spaghetti2 = this.mBiometricView;
            PromptViewModel promptViewModel = spaghetti2.viewModel;
            if (z) {
                PromptViewModel.showAuthenticating$default(promptViewModel, null, 3);
            } else {
                promptViewModel.ensureFingerprintHasStarted(false);
                PromptViewModel.showAuthenticating$default(promptViewModel, BiometricViewBinderKt.access$asDefaultHelpMessage(spaghetti2.modalities, spaghetti2.applicationContext), 2);
            }
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        AuthPanelController authPanelController = this.mPanelController;
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        authPanelController.mContainerWidth = measuredWidth;
        authPanelController.mContainerHeight = measuredHeight;
    }

    @Override // com.android.systemui.keyguard.WakefulnessLifecycle.Observer
    public final void onStartedGoingToSleep() {
        animateAway(1, true);
    }

    public final void removeWindowIfAttached() {
        Log.d("AuthContainerView", "pendingCallback: " + this.mPendingCallbackReason);
        Integer num = this.mPendingCallbackReason;
        if (num != null) {
            AuthController authController = this.mConfig.mCallback;
            int intValue = num.intValue();
            byte[] bArr = this.mCredentialAttestation;
            long j = this.mConfig.mRequestId;
            AuthDialog authDialog = authController.mCurrentDialog;
            if (authDialog == null || j == ((AuthContainerView) authDialog).mConfig.mRequestId) {
                switch (intValue) {
                    case 1:
                        authController.sendResultAndCleanUp(3, bArr);
                        break;
                    case 2:
                        authController.sendResultAndCleanUp(2, bArr);
                        break;
                    case 3:
                        authController.sendResultAndCleanUp(1, bArr);
                        break;
                    case 4:
                        authController.sendResultAndCleanUp(4, bArr);
                        break;
                    case 5:
                        authController.sendResultAndCleanUp(5, bArr);
                        break;
                    case 6:
                        authController.sendResultAndCleanUp(6, bArr);
                        break;
                    case 7:
                        authController.sendResultAndCleanUp(7, bArr);
                        break;
                    case 8:
                        authController.sendResultAndCleanUp(8, bArr);
                        break;
                    default:
                        ClockEventController$zenModeCallback$1$$ExternalSyntheticOutline0.m("Unhandled reason: ", "AuthController", intValue);
                        break;
                }
            } else {
                Log.w("AuthController", "requestId doesn't match, skip onDismissed");
            }
            this.mPendingCallbackReason = null;
        }
        if (this.mContainerState == 5) {
            return;
        }
        this.mContainerState = 5;
        if (isAttachedToWindow()) {
            this.mWindowManager.removeViewImmediate(this);
        }
    }

    public final void sendEarlyUserCanceled() {
        Config config = this.mConfig;
        IBiometricSysuiReceiver currentReceiver = config.mCallback.getCurrentReceiver(config.mRequestId);
        if (currentReceiver == null) {
            Log.w("AuthController", "Skip onSystemEvent");
            return;
        }
        try {
            currentReceiver.onSystemEvent(1);
        } catch (RemoteException e) {
            Log.e("AuthController", "RemoteException when sending system event", e);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.biometrics.AuthContainerView$1, reason: invalid class name */
    public final class AnonymousClass1 implements Animator.AnimatorListener {
        public final /* synthetic */ long val$timeout;
        public final /* synthetic */ String val$type;
        public final /* synthetic */ View val$v;

        public AnonymousClass1(View view, String str, long j) {
            this.val$v = view;
            this.val$type = str;
            this.val$timeout = j;
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationCancel(Animator animator) {
            if (this.val$v.isAttachedToWindow()) {
                AuthContainerView.this.mInteractionJankMonitor.cancel(56);
            } else {
                Log.w("AuthContainerView", "Un-attached view should not cancel Jank trace.");
            }
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            if (this.val$v.isAttachedToWindow()) {
                AuthContainerView.this.mInteractionJankMonitor.end(56);
            } else {
                Log.w("AuthContainerView", "Un-attached view should not end Jank trace.");
            }
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationStart(Animator animator) {
            if (this.val$v.isAttachedToWindow()) {
                AuthContainerView.this.mInteractionJankMonitor.begin(InteractionJankMonitor.Configuration.Builder.withView(56, this.val$v).setTag(this.val$type).setTimeout(this.val$timeout));
            } else {
                Log.w("AuthContainerView", "Un-attached view should not begin Jank trace.");
            }
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationRepeat(Animator animator) {
        }
    }
}

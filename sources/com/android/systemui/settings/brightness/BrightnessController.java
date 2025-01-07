package com.android.systemui.settings.brightness;

import android.animation.ValueAnimator;
import android.content.Context;
import android.database.ContentObserver;
import android.hardware.display.BrightnessInfo;
import android.hardware.display.DisplayManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerExecutor;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.provider.Settings;
import android.service.vr.IVrManager;
import android.service.vr.IVrStateCallbacks;
import android.util.Log;
import android.util.MathUtils;
import com.android.internal.display.BrightnessSynchronizer;
import com.android.internal.logging.MetricsLogger;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.android.settingslib.display.BrightnessUtils;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.settings.DisplayTracker;
import com.android.systemui.settings.DisplayTrackerImpl;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.util.settings.SecureSettings;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.function.Predicate;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BrightnessController implements ToggleSlider$Listener {
    public static final Uri BRIGHTNESS_MODE_URI = Settings.System.getUriFor("screen_brightness_mode");
    public volatile boolean mAutomatic;
    public final Handler mBackgroundHandler;
    public final BrightnessObserver mBrightnessObserver;
    public final Context mContext;
    public final BrightnessSliderController mControl;
    public boolean mControlValueInitialized;
    public final int mDisplayId;
    public final DisplayManager mDisplayManager;
    public final DisplayTracker mDisplayTracker;
    public boolean mExternalChange;
    public final AnonymousClass7 mHandlerCallback;
    public volatile boolean mIsVrModeEnabled;
    public boolean mListening;
    public final LogBuffer mLogBuffer;
    public final Executor mMainExecutor;
    public final Handler mMainHandler;
    public final SecureSettings mSecureSettings;
    public ValueAnimator mSliderAnimator;
    public final UserTracker.Callback mUserChangedCallback;
    public final UserTracker mUserTracker;
    public final IVrManager mVrManager;
    public final DisplayTracker.Callback mBrightnessListener = new DisplayTracker.Callback() { // from class: com.android.systemui.settings.brightness.BrightnessController.1
        @Override // com.android.systemui.settings.DisplayTracker.Callback
        public final void onDisplayChanged() {
            BrightnessController brightnessController = BrightnessController.this;
            brightnessController.mBackgroundHandler.post(brightnessController.mUpdateSliderRunnable);
        }
    };
    public boolean mTrackingTouch = false;
    public float mBrightnessMin = 0.0f;
    public float mBrightnessMax = 1.0f;
    public final AnonymousClass2 mStartListeningRunnable = new AnonymousClass2(this, 0);
    public final AnonymousClass2 mStopListeningRunnable = new AnonymousClass2(this, 2);
    public final AnonymousClass2 mUpdateModeRunnable = new AnonymousClass2(this, 3);
    public final AnonymousClass2 mUpdateSliderRunnable = new AnonymousClass2(this, 4);
    public final AnonymousClass6 mVrStateCallbacks = new IVrStateCallbacks.Stub() { // from class: com.android.systemui.settings.brightness.BrightnessController.6
        public final void onVrStateChanged(boolean z) {
            BrightnessController.this.mMainHandler.obtainMessage(4, z ? 1 : 0, 0).sendToTarget();
        }
    };

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.settings.brightness.BrightnessController$2, reason: invalid class name */
    public final class AnonymousClass2 implements Runnable {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ BrightnessController this$0;

        public /* synthetic */ AnonymousClass2(BrightnessController brightnessController, int i) {
            this.$r8$classId = i;
            this.this$0 = brightnessController;
        }

        @Override // java.lang.Runnable
        public final void run() {
            switch (this.$r8$classId) {
                case 0:
                    BrightnessController brightnessController = this.this$0;
                    if (brightnessController.mListening) {
                        return;
                    }
                    brightnessController.mListening = true;
                    IVrManager iVrManager = brightnessController.mVrManager;
                    if (iVrManager != null) {
                        try {
                            iVrManager.registerListener(brightnessController.mVrStateCallbacks);
                            BrightnessController brightnessController2 = this.this$0;
                            brightnessController2.mIsVrModeEnabled = brightnessController2.mVrManager.getVrModeState();
                        } catch (RemoteException e) {
                            Log.e("CentralSurfaces.BrightnessController", "Failed to register VR mode state listener: ", e);
                        }
                    }
                    BrightnessObserver brightnessObserver = this.this$0.mBrightnessObserver;
                    if (!brightnessObserver.mObserving) {
                        brightnessObserver.mObserving = true;
                        BrightnessController.this.mSecureSettings.registerContentObserverForUserAsync(BrightnessController.BRIGHTNESS_MODE_URI, brightnessObserver);
                    }
                    BrightnessController brightnessController3 = this.this$0;
                    DisplayTracker displayTracker = brightnessController3.mDisplayTracker;
                    DisplayTracker.Callback callback = brightnessController3.mBrightnessListener;
                    HandlerExecutor handlerExecutor = new HandlerExecutor(this.this$0.mMainHandler);
                    DisplayTrackerImpl displayTrackerImpl = (DisplayTrackerImpl) displayTracker;
                    synchronized (displayTrackerImpl.brightnessCallbacks) {
                        try {
                            if (displayTrackerImpl.brightnessCallbacks.isEmpty()) {
                                displayTrackerImpl.displayManager.registerDisplayListener(displayTrackerImpl.displayBrightnessChangedListener, displayTrackerImpl.backgroundHandler, 8L);
                            }
                            displayTrackerImpl.brightnessCallbacks.add(new DisplayTrackerImpl.DisplayTrackerDataItem(new WeakReference(callback), handlerExecutor));
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                    BrightnessController brightnessController4 = this.this$0;
                    ((UserTrackerImpl) brightnessController4.mUserTracker).addCallback(brightnessController4.mUserChangedCallback, brightnessController4.mMainExecutor);
                    this.this$0.mUpdateModeRunnable.run();
                    this.this$0.mUpdateSliderRunnable.run();
                    this.this$0.mMainHandler.sendEmptyMessage(2);
                    return;
                case 1:
                    int userId = ((UserTrackerImpl) this.this$0.mUserTracker).getUserId();
                    RestrictedLockUtils.EnforcedAdmin checkIfRestrictionEnforced = RestrictedLockUtilsInternal.checkIfRestrictionEnforced(this.this$0.mContext, "no_config_brightness", userId);
                    if (checkIfRestrictionEnforced == null && RestrictedLockUtilsInternal.hasBaseUserRestriction(this.this$0.mContext, "no_config_brightness", userId)) {
                        checkIfRestrictionEnforced = new RestrictedLockUtils.EnforcedAdmin();
                    }
                    BrightnessSliderController brightnessSliderController = this.this$0.mControl;
                    if (checkIfRestrictionEnforced == null) {
                        ToggleSeekBar toggleSeekBar = ((BrightnessSliderView) brightnessSliderController.mView).mSlider;
                        toggleSeekBar.mAdminBlocker = null;
                        toggleSeekBar.setEnabled(true);
                        return;
                    } else {
                        BrightnessSliderView brightnessSliderView = (BrightnessSliderView) brightnessSliderController.mView;
                        BrightnessSliderController$$ExternalSyntheticLambda1 brightnessSliderController$$ExternalSyntheticLambda1 = new BrightnessSliderController$$ExternalSyntheticLambda1(brightnessSliderController, checkIfRestrictionEnforced);
                        ToggleSeekBar toggleSeekBar2 = brightnessSliderView.mSlider;
                        toggleSeekBar2.mAdminBlocker = brightnessSliderController$$ExternalSyntheticLambda1;
                        toggleSeekBar2.setEnabled(false);
                        return;
                    }
                case 2:
                    BrightnessController brightnessController5 = this.this$0;
                    if (brightnessController5.mListening) {
                        brightnessController5.mListening = false;
                        IVrManager iVrManager2 = brightnessController5.mVrManager;
                        if (iVrManager2 != null) {
                            try {
                                iVrManager2.unregisterListener(brightnessController5.mVrStateCallbacks);
                            } catch (RemoteException e2) {
                                Log.e("CentralSurfaces.BrightnessController", "Failed to unregister VR mode state listener: ", e2);
                            }
                        }
                        BrightnessObserver brightnessObserver2 = this.this$0.mBrightnessObserver;
                        BrightnessController.this.mSecureSettings.unregisterContentObserverAsync(brightnessObserver2);
                        brightnessObserver2.mObserving = false;
                        BrightnessController brightnessController6 = this.this$0;
                        DisplayTracker displayTracker2 = brightnessController6.mDisplayTracker;
                        final DisplayTracker.Callback callback2 = brightnessController6.mBrightnessListener;
                        DisplayTrackerImpl displayTrackerImpl2 = (DisplayTrackerImpl) displayTracker2;
                        synchronized (displayTrackerImpl2.displayCallbacks) {
                            final int i = 0;
                            if (((ArrayList) displayTrackerImpl2.displayCallbacks).removeIf(new Predicate() { // from class: com.android.systemui.settings.DisplayTrackerImpl$removeCallback$1$changed$1
                                @Override // java.util.function.Predicate
                                public final boolean test(Object obj) {
                                    switch (i) {
                                        case 0:
                                            DisplayTracker.Callback callback3 = callback2;
                                            DisplayTracker.Callback callback4 = (DisplayTracker.Callback) ((DisplayTrackerImpl.DisplayTrackerDataItem) obj).callback.get();
                                            if (callback4 != null) {
                                                return callback4.equals(callback3);
                                            }
                                            return true;
                                        default:
                                            DisplayTracker.Callback callback5 = callback2;
                                            DisplayTracker.Callback callback6 = (DisplayTracker.Callback) ((DisplayTrackerImpl.DisplayTrackerDataItem) obj).callback.get();
                                            if (callback6 != null) {
                                                return callback6.equals(callback5);
                                            }
                                            return true;
                                    }
                                }
                            }) && displayTrackerImpl2.displayCallbacks.isEmpty()) {
                                displayTrackerImpl2.displayManager.unregisterDisplayListener(displayTrackerImpl2.displayChangedListener);
                            }
                        }
                        synchronized (displayTrackerImpl2.brightnessCallbacks) {
                            final int i2 = 1;
                            if (((ArrayList) displayTrackerImpl2.brightnessCallbacks).removeIf(new Predicate() { // from class: com.android.systemui.settings.DisplayTrackerImpl$removeCallback$1$changed$1
                                @Override // java.util.function.Predicate
                                public final boolean test(Object obj) {
                                    switch (i2) {
                                        case 0:
                                            DisplayTracker.Callback callback3 = callback2;
                                            DisplayTracker.Callback callback4 = (DisplayTracker.Callback) ((DisplayTrackerImpl.DisplayTrackerDataItem) obj).callback.get();
                                            if (callback4 != null) {
                                                return callback4.equals(callback3);
                                            }
                                            return true;
                                        default:
                                            DisplayTracker.Callback callback5 = callback2;
                                            DisplayTracker.Callback callback6 = (DisplayTracker.Callback) ((DisplayTrackerImpl.DisplayTrackerDataItem) obj).callback.get();
                                            if (callback6 != null) {
                                                return callback6.equals(callback5);
                                            }
                                            return true;
                                    }
                                }
                            }) && displayTrackerImpl2.brightnessCallbacks.isEmpty()) {
                                displayTrackerImpl2.displayManager.unregisterDisplayListener(displayTrackerImpl2.displayBrightnessChangedListener);
                            }
                        }
                        BrightnessController brightnessController7 = this.this$0;
                        ((UserTrackerImpl) brightnessController7.mUserTracker).removeCallback(brightnessController7.mUserChangedCallback);
                        this.this$0.mMainHandler.sendEmptyMessage(3);
                        return;
                    }
                    return;
                case 3:
                    int intForUser = Settings.System.getIntForUser(this.this$0.mContext.getContentResolver(), "screen_brightness_mode", 0, ((UserTrackerImpl) this.this$0.mUserTracker).getUserId());
                    this.this$0.mAutomatic = intForUser != 0;
                    return;
                default:
                    boolean z = this.this$0.mIsVrModeEnabled;
                    BrightnessInfo brightnessInfo = this.this$0.mContext.getDisplay().getBrightnessInfo();
                    if (brightnessInfo == null) {
                        return;
                    }
                    BrightnessController brightnessController8 = this.this$0;
                    brightnessController8.mBrightnessMax = brightnessInfo.brightnessMaximum;
                    brightnessController8.mBrightnessMin = brightnessInfo.brightnessMinimum;
                    this.this$0.mMainHandler.obtainMessage(1, Float.floatToIntBits(brightnessInfo.brightness), z ? 1 : 0).sendToTarget();
                    return;
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class BrightnessObserver extends ContentObserver {
        public boolean mObserving;

        public BrightnessObserver(Handler handler) {
            super(handler);
            this.mObserving = false;
        }

        @Override // android.database.ContentObserver
        public final void onChange(boolean z, Uri uri) {
            if (z) {
                return;
            }
            if (BrightnessController.BRIGHTNESS_MODE_URI.equals(uri)) {
                BrightnessController brightnessController = BrightnessController.this;
                brightnessController.mBackgroundHandler.post(brightnessController.mUpdateModeRunnable);
                BrightnessController brightnessController2 = BrightnessController.this;
                brightnessController2.mBackgroundHandler.post(brightnessController2.mUpdateSliderRunnable);
                return;
            }
            BrightnessController brightnessController3 = BrightnessController.this;
            brightnessController3.mBackgroundHandler.post(brightnessController3.mUpdateModeRunnable);
            BrightnessController brightnessController4 = BrightnessController.this;
            brightnessController4.mBackgroundHandler.post(brightnessController4.mUpdateSliderRunnable);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Factory {
    }

    /* renamed from: -$$Nest$mupdateSlider, reason: not valid java name */
    public static void m856$$Nest$mupdateSlider(final BrightnessController brightnessController, float f) {
        float f2 = brightnessController.mBrightnessMin;
        float f3 = brightnessController.mBrightnessMax;
        ValueAnimator valueAnimator = brightnessController.mSliderAnimator;
        if (valueAnimator != null && valueAnimator.isStarted()) {
            brightnessController.mSliderAnimator.cancel();
        }
        if (BrightnessSynchronizer.floatEquals(f, BrightnessUtils.convertGammaToLinearFloat(((BrightnessSliderView) brightnessController.mControl.mView).mSlider.getProgress(), f2, f3))) {
            return;
        }
        int convertLinearToGammaFloat = BrightnessUtils.convertLinearToGammaFloat(f, f2, f3);
        if (!brightnessController.mControlValueInitialized || !((BrightnessSliderView) brightnessController.mControl.mView).isVisibleToUser() || (!brightnessController.mAutomatic && !brightnessController.mTrackingTouch)) {
            brightnessController.mControl.setValue(convertLinearToGammaFloat);
            brightnessController.mControlValueInitialized = true;
        }
        ValueAnimator ofInt = ValueAnimator.ofInt(((BrightnessSliderView) brightnessController.mControl.mView).mSlider.getProgress(), convertLinearToGammaFloat);
        brightnessController.mSliderAnimator = ofInt;
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.settings.brightness.BrightnessController$$ExternalSyntheticLambda0
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                BrightnessController brightnessController2 = BrightnessController.this;
                brightnessController2.mExternalChange = true;
                brightnessController2.mControl.setValue(((Integer) valueAnimator2.getAnimatedValue()).intValue());
                brightnessController2.mExternalChange = false;
            }
        });
        brightnessController.mSliderAnimator.setDuration((Math.abs(((BrightnessSliderView) brightnessController.mControl.mView).mSlider.getProgress() - convertLinearToGammaFloat) * 3000) / 65535);
        brightnessController.mSliderAnimator.start();
    }

    /* JADX WARN: Type inference failed for: r0v8, types: [com.android.systemui.settings.brightness.BrightnessController$6] */
    public BrightnessController(Context context, BrightnessSliderController brightnessSliderController, UserTracker userTracker, DisplayTracker displayTracker, DisplayManager displayManager, SecureSettings secureSettings, LogBuffer logBuffer, IVrManager iVrManager, Executor executor, Looper looper, Handler handler) {
        Handler.Callback callback = new Handler.Callback() { // from class: com.android.systemui.settings.brightness.BrightnessController.7
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                BrightnessController brightnessController = BrightnessController.this;
                brightnessController.mExternalChange = true;
                try {
                    int i = message.what;
                    if (i == 1) {
                        BrightnessController.m856$$Nest$mupdateSlider(brightnessController, Float.intBitsToFloat(message.arg1));
                    } else if (i == 2) {
                        brightnessController.mControl.mListener = brightnessController;
                    } else if (i == 3) {
                        brightnessController.mControl.mListener = null;
                    } else {
                        if (i != 4) {
                            brightnessController.mExternalChange = false;
                            return false;
                        }
                        boolean z = message.arg1 != 0;
                        if (brightnessController.mIsVrModeEnabled != z) {
                            brightnessController.mIsVrModeEnabled = z;
                            brightnessController.mBackgroundHandler.post(brightnessController.mUpdateSliderRunnable);
                        }
                    }
                    BrightnessController.this.mExternalChange = false;
                    return true;
                } catch (Throwable th) {
                    BrightnessController.this.mExternalChange = false;
                    throw th;
                }
            }
        };
        this.mUserChangedCallback = new UserTracker.Callback() { // from class: com.android.systemui.settings.brightness.BrightnessController.8
            @Override // com.android.systemui.settings.UserTracker.Callback
            public final void onUserChanged(int i, Context context2) {
                BrightnessController brightnessController = BrightnessController.this;
                brightnessController.mBackgroundHandler.post(brightnessController.mUpdateModeRunnable);
                brightnessController.mBackgroundHandler.post(brightnessController.mUpdateSliderRunnable);
            }
        };
        this.mContext = context;
        this.mControl = brightnessSliderController;
        brightnessSliderController.setMax(65535);
        this.mMainExecutor = executor;
        this.mBackgroundHandler = handler;
        this.mUserTracker = userTracker;
        this.mDisplayTracker = displayTracker;
        this.mSecureSettings = secureSettings;
        this.mDisplayId = context.getDisplayId();
        this.mDisplayManager = displayManager;
        this.mVrManager = iVrManager;
        this.mLogBuffer = logBuffer;
        Handler handler2 = new Handler(looper, callback);
        this.mMainHandler = handler2;
        this.mBrightnessObserver = new BrightnessObserver(handler2);
    }

    public final void logBrightnessChange(float f, boolean z, int i) {
        LogLevel logLevel = LogLevel.DEBUG;
        BrightnessController$$ExternalSyntheticLambda1 brightnessController$$ExternalSyntheticLambda1 = new BrightnessController$$ExternalSyntheticLambda1();
        LogBuffer logBuffer = this.mLogBuffer;
        LogMessage obtain = logBuffer.obtain("CentralSurfaces.BrightnessController", logLevel, brightnessController$$ExternalSyntheticLambda1, null);
        ((LogMessageImpl) obtain).int1 = i;
        double d = f;
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.double1 = d;
        logMessageImpl.bool1 = z;
        logBuffer.commit(obtain);
    }

    public final void onChanged(int i, boolean z, boolean z2) {
        boolean z3 = !this.mTrackingTouch && z;
        this.mTrackingTouch = z;
        if (this.mExternalChange) {
            return;
        }
        ValueAnimator valueAnimator = this.mSliderAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        int i2 = this.mAutomatic ? 219 : 218;
        float f = this.mBrightnessMin;
        float f2 = this.mBrightnessMax;
        final float min = MathUtils.min(BrightnessUtils.convertGammaToLinearFloat(i, f, f2), f2);
        if (z2) {
            MetricsLogger.action(this.mContext, i2, BrightnessSynchronizer.brightnessFloatToInt(min));
        }
        this.mDisplayManager.setTemporaryBrightness(this.mDisplayId, min);
        if (z3) {
            logBrightnessChange(min, true, this.mDisplayId);
        }
        if (z) {
            return;
        }
        AsyncTask.execute(new Runnable() { // from class: com.android.systemui.settings.brightness.BrightnessController.9
            @Override // java.lang.Runnable
            public final void run() {
                BrightnessController brightnessController = BrightnessController.this;
                brightnessController.logBrightnessChange(min, false, brightnessController.mDisplayId);
                BrightnessController brightnessController2 = BrightnessController.this;
                brightnessController2.mDisplayManager.setBrightness(brightnessController2.mDisplayId, min);
            }
        });
    }
}

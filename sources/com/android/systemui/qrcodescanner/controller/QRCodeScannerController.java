package com.android.systemui.qrcodescanner.controller;

import android.R;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.provider.DeviceConfig;
import android.provider.Settings;
import com.android.keyguard.ClockEventController$zenModeCallback$1$$ExternalSyntheticOutline0;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.policy.CallbackController;
import com.android.systemui.util.DeviceConfigProxy;
import com.android.systemui.util.settings.SecureSettings;
import com.android.systemui.util.settings.SecureSettingsImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class QRCodeScannerController implements CallbackController {
    public final boolean mConfigEnableLockScreenButton;
    public final Context mContext;
    public final DeviceConfigProxy mDeviceConfigProxy;
    public final Executor mExecutor;
    public boolean mQRCodeScannerEnabled;
    public final SecureSettings mSecureSettings;
    public final UserTracker mUserTracker;
    public final ArrayList mCallbacks = new ArrayList();
    public HashMap mQRCodeScannerPreferenceObserver = new HashMap();
    public QRCodeScannerController$$ExternalSyntheticLambda2 mOnDefaultQRCodeScannerChangedListener = null;
    public UserTracker.Callback mUserChangedListener = null;
    public Intent mIntent = null;
    public String mQRCodeScannerActivity = null;
    public final AtomicInteger mQRCodeScannerPreferenceChangeEvents = new AtomicInteger(0);
    public final AtomicInteger mDefaultQRCodeScannerChangeEvents = new AtomicInteger(0);
    public Boolean mIsCameraAvailable = null;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.qrcodescanner.controller.QRCodeScannerController$1, reason: invalid class name */
    public final class AnonymousClass1 extends ContentObserver {
        public AnonymousClass1() {
            super(null);
        }

        @Override // android.database.ContentObserver
        public final void onChange(boolean z) {
            QRCodeScannerController.this.mExecutor.execute(new QRCodeScannerController$$ExternalSyntheticLambda0(3, this));
        }
    }

    public QRCodeScannerController(Context context, Executor executor, SecureSettings secureSettings, DeviceConfigProxy deviceConfigProxy, UserTracker userTracker) {
        this.mContext = context;
        this.mExecutor = executor;
        this.mSecureSettings = secureSettings;
        this.mDeviceConfigProxy = deviceConfigProxy;
        this.mUserTracker = userTracker;
        this.mConfigEnableLockScreenButton = context.getResources().getBoolean(R.bool.config_enableQrCodeScannerOnLockScreen);
        executor.execute(new QRCodeScannerController$$ExternalSyntheticLambda0(2, this));
    }

    public final boolean isAbleToLaunchScannerActivity() {
        Intent intent = this.mIntent;
        if (intent != null) {
            return intent.getComponent() == null ? false : this.mContext.getPackageManager().queryIntentActivities(intent, 819200).isEmpty() ^ true;
        }
        return false;
    }

    public final boolean isCameraAvailable() {
        if (this.mIsCameraAvailable == null) {
            this.mIsCameraAvailable = Boolean.valueOf(this.mContext.getPackageManager().hasSystemFeature("android.hardware.camera"));
        }
        return this.mIsCameraAvailable.booleanValue();
    }

    public final void registerQRCodePreferenceObserver() {
        if (this.mConfigEnableLockScreenButton) {
            int userId = ((UserTrackerImpl) this.mUserTracker).getUserId();
            if (this.mQRCodeScannerPreferenceObserver.getOrDefault(Integer.valueOf(userId), null) != null) {
                return;
            }
            this.mExecutor.execute(new QRCodeScannerController$$ExternalSyntheticLambda0(0, this));
            this.mQRCodeScannerPreferenceObserver.put(Integer.valueOf(userId), new AnonymousClass1());
            SecureSettings secureSettings = this.mSecureSettings;
            ((SecureSettingsImpl) secureSettings).getClass();
            secureSettings.registerContentObserverForUserSync(Settings.Secure.getUriFor("lock_screen_show_qr_code_scanner"), false, (ContentObserver) this.mQRCodeScannerPreferenceObserver.get(Integer.valueOf(userId)), userId);
        }
    }

    /* JADX WARN: Type inference failed for: r2v4, types: [android.provider.DeviceConfig$OnPropertiesChangedListener, com.android.systemui.qrcodescanner.controller.QRCodeScannerController$$ExternalSyntheticLambda2] */
    public final void registerQRCodeScannerChangeObservers(int... iArr) {
        if (isCameraAvailable()) {
            for (int i : iArr) {
                if (i == 0) {
                    this.mDefaultQRCodeScannerChangeEvents.incrementAndGet();
                    if (this.mOnDefaultQRCodeScannerChangedListener == null) {
                        this.mExecutor.execute(new QRCodeScannerController$$ExternalSyntheticLambda0(1, this));
                        ?? r2 = new DeviceConfig.OnPropertiesChangedListener() { // from class: com.android.systemui.qrcodescanner.controller.QRCodeScannerController$$ExternalSyntheticLambda2
                            public final void onPropertiesChanged(DeviceConfig.Properties properties) {
                                QRCodeScannerController qRCodeScannerController = QRCodeScannerController.this;
                                qRCodeScannerController.getClass();
                                if ("systemui".equals(properties.getNamespace()) && properties.getKeyset().contains("default_qr_code_scanner")) {
                                    qRCodeScannerController.updateQRCodeScannerActivityDetails();
                                    qRCodeScannerController.updateQRCodeScannerPreferenceDetails(true);
                                }
                            }
                        };
                        this.mOnDefaultQRCodeScannerChangedListener = r2;
                        Executor executor = this.mExecutor;
                        this.mDeviceConfigProxy.getClass();
                        DeviceConfig.addOnPropertiesChangedListener("systemui", executor, (DeviceConfig.OnPropertiesChangedListener) r2);
                    }
                } else if (i != 1) {
                    ClockEventController$zenModeCallback$1$$ExternalSyntheticOutline0.m("Unrecognised event: ", "QRCodeScannerController", i);
                } else {
                    this.mQRCodeScannerPreferenceChangeEvents.incrementAndGet();
                    registerQRCodePreferenceObserver();
                    if (this.mUserChangedListener == null) {
                        UserTracker.Callback callback = new UserTracker.Callback() { // from class: com.android.systemui.qrcodescanner.controller.QRCodeScannerController.2
                            @Override // com.android.systemui.settings.UserTracker.Callback
                            public final void onUserChanged(int i2, Context context) {
                                QRCodeScannerController qRCodeScannerController = QRCodeScannerController.this;
                                qRCodeScannerController.registerQRCodePreferenceObserver();
                                qRCodeScannerController.updateQRCodeScannerPreferenceDetails(true);
                            }
                        };
                        this.mUserChangedListener = callback;
                        ((UserTrackerImpl) this.mUserTracker).addCallback(callback, this.mExecutor);
                    }
                }
            }
        }
    }

    public final void unregisterQRCodeScannerChangeObservers(int... iArr) {
        if (isCameraAvailable()) {
            for (int i : iArr) {
                if (i != 0) {
                    if (i != 1) {
                        ClockEventController$zenModeCallback$1$$ExternalSyntheticOutline0.m("Unrecognised event: ", "QRCodeScannerController", i);
                    } else {
                        UserTracker userTracker = this.mUserTracker;
                        if (userTracker != null && this.mQRCodeScannerPreferenceChangeEvents.decrementAndGet() == 0) {
                            if (this.mConfigEnableLockScreenButton) {
                                this.mQRCodeScannerPreferenceObserver.forEach(new BiConsumer() { // from class: com.android.systemui.qrcodescanner.controller.QRCodeScannerController$$ExternalSyntheticLambda3
                                    @Override // java.util.function.BiConsumer
                                    public final void accept(Object obj, Object obj2) {
                                        QRCodeScannerController.this.mSecureSettings.unregisterContentObserverSync((ContentObserver) obj2);
                                    }
                                });
                                this.mQRCodeScannerPreferenceObserver = new HashMap();
                                ((SecureSettingsImpl) this.mSecureSettings).putStringForUser("show_qr_code_scanner_setting", null, ((UserTrackerImpl) userTracker).getUserId());
                            }
                            ((UserTrackerImpl) userTracker).removeCallback(this.mUserChangedListener);
                            this.mUserChangedListener = null;
                            this.mQRCodeScannerEnabled = false;
                        }
                    }
                } else if (this.mOnDefaultQRCodeScannerChangedListener != null && this.mDefaultQRCodeScannerChangeEvents.decrementAndGet() == 0) {
                    QRCodeScannerController$$ExternalSyntheticLambda2 qRCodeScannerController$$ExternalSyntheticLambda2 = this.mOnDefaultQRCodeScannerChangedListener;
                    this.mDeviceConfigProxy.getClass();
                    DeviceConfig.removeOnPropertiesChangedListener(qRCodeScannerController$$ExternalSyntheticLambda2);
                    this.mOnDefaultQRCodeScannerChangedListener = null;
                }
            }
        }
    }

    public final void updateQRCodeScannerActivityDetails() {
        ArrayList arrayList;
        this.mDeviceConfigProxy.getClass();
        String string = DeviceConfig.getString("systemui", "default_qr_code_scanner", "");
        if (Objects.equals(string, "")) {
            string = this.mContext.getResources().getString(R.string.config_defaultSupervisionProfileOwnerComponent);
        }
        String str = this.mQRCodeScannerActivity;
        Intent intent = new Intent();
        if (string != null) {
            intent.setComponent(ComponentName.unflattenFromString(string));
            intent.addFlags(335544320);
        }
        if (intent.getComponent() == null ? false : !this.mContext.getPackageManager().queryIntentActivities(intent, 537698816).isEmpty()) {
            this.mQRCodeScannerActivity = string;
            this.mIntent = intent;
        } else {
            this.mQRCodeScannerActivity = null;
            this.mIntent = null;
        }
        if (Objects.equals(this.mQRCodeScannerActivity, str)) {
            return;
        }
        synchronized (this.mCallbacks) {
            arrayList = (ArrayList) this.mCallbacks.clone();
        }
        arrayList.forEach(new QRCodeScannerController$$ExternalSyntheticLambda5(0));
    }

    public final void updateQRCodeScannerPreferenceDetails(boolean z) {
        ArrayList arrayList;
        if (this.mConfigEnableLockScreenButton) {
            boolean z2 = this.mQRCodeScannerEnabled;
            this.mQRCodeScannerEnabled = this.mSecureSettings.getIntForUser("lock_screen_show_qr_code_scanner", 0, ((UserTrackerImpl) this.mUserTracker).getUserId()) != 0;
            if (z) {
                ((SecureSettingsImpl) this.mSecureSettings).putStringForUser("show_qr_code_scanner_setting", this.mQRCodeScannerActivity, ((UserTrackerImpl) this.mUserTracker).getUserId());
            }
            if (Boolean.valueOf(this.mQRCodeScannerEnabled).equals(Boolean.valueOf(z2))) {
                return;
            }
            synchronized (this.mCallbacks) {
                arrayList = (ArrayList) this.mCallbacks.clone();
            }
            arrayList.forEach(new QRCodeScannerController$$ExternalSyntheticLambda5(1));
        }
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void addCallback(Callback callback) {
        if (isCameraAvailable()) {
            synchronized (this.mCallbacks) {
                this.mCallbacks.add(callback);
            }
        }
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void removeCallback(Callback callback) {
        if (isCameraAvailable()) {
            synchronized (this.mCallbacks) {
                this.mCallbacks.remove(callback);
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Callback {
        void onQRCodeScannerActivityChanged();

        default void onQRCodeScannerPreferenceChanged() {
        }
    }
}

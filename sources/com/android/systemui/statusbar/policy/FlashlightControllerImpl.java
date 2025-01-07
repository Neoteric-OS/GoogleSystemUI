package com.android.systemui.statusbar.policy;

import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.text.TextUtils;
import android.util.Log;
import com.android.systemui.Dumpable;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.util.settings.SecureSettings;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FlashlightControllerImpl implements CallbackController, Dumpable {
    public final AtomicReference mCameraId;
    public final CameraManager mCameraManager;
    public final Executor mExecutor;
    public boolean mFlashlightEnabled;
    public final boolean mHasFlashlight;
    public final ArrayList mListeners = new ArrayList(1);
    public final SecureSettings mSecureSettings;
    public boolean mTorchAvailable;
    public final AnonymousClass1 mTorchCallback;

    /* JADX WARN: Type inference failed for: r2v1, types: [com.android.systemui.statusbar.policy.FlashlightControllerImpl$1] */
    public FlashlightControllerImpl(DumpManager dumpManager, CameraManager cameraManager, Executor executor, SecureSettings secureSettings, PackageManager packageManager) {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        this.mTorchCallback = new CameraManager.TorchCallback() { // from class: com.android.systemui.statusbar.policy.FlashlightControllerImpl.1
            @Override // android.hardware.camera2.CameraManager.TorchCallback
            public final void onTorchModeChanged(String str, boolean z) {
                boolean z2;
                if (TextUtils.equals(str, (CharSequence) FlashlightControllerImpl.this.mCameraId.get())) {
                    setCameraAvailable(true);
                    synchronized (FlashlightControllerImpl.this) {
                        FlashlightControllerImpl flashlightControllerImpl = FlashlightControllerImpl.this;
                        z2 = flashlightControllerImpl.mFlashlightEnabled != z;
                        flashlightControllerImpl.mFlashlightEnabled = z;
                    }
                    if (z2) {
                        Log.d("FlashlightController", "dispatchModeChanged(" + z + ")");
                        FlashlightControllerImpl.this.dispatchListeners(1, z);
                    }
                    FlashlightControllerImpl.this.mSecureSettings.putInt(1, "flashlight_available");
                    FlashlightControllerImpl.this.mSecureSettings.putInt(z ? 1 : 0, "flashlight_enabled");
                }
            }

            @Override // android.hardware.camera2.CameraManager.TorchCallback
            public final void onTorchModeUnavailable(String str) {
                if (TextUtils.equals(str, (CharSequence) FlashlightControllerImpl.this.mCameraId.get())) {
                    setCameraAvailable(false);
                    FlashlightControllerImpl.this.mSecureSettings.putInt(0, "flashlight_available");
                }
            }

            public final void setCameraAvailable(boolean z) {
                boolean z2;
                synchronized (FlashlightControllerImpl.this) {
                    FlashlightControllerImpl flashlightControllerImpl = FlashlightControllerImpl.this;
                    z2 = flashlightControllerImpl.mTorchAvailable != z;
                    flashlightControllerImpl.mTorchAvailable = z;
                }
                if (z2) {
                    Log.d("FlashlightController", "dispatchAvailabilityChanged(" + z + ")");
                    FlashlightControllerImpl.this.dispatchListeners(2, z);
                }
            }
        };
        this.mCameraManager = cameraManager;
        this.mExecutor = executor;
        this.mCameraId = new AtomicReference(null);
        this.mSecureSettings = secureSettings;
        this.mHasFlashlight = packageManager.hasSystemFeature("android.hardware.camera.flash");
        if (atomicBoolean.getAndSet(true)) {
            return;
        }
        dumpManager.getClass();
        DumpManager.registerDumpable$default(dumpManager, "FlashlightControllerImpl", this);
        executor.execute(new FlashlightControllerImpl$$ExternalSyntheticLambda0(this));
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void addCallback(Object obj) {
        FlashlightController$FlashlightListener flashlightController$FlashlightListener = (FlashlightController$FlashlightListener) obj;
        synchronized (this.mListeners) {
            try {
                if (this.mCameraId.get() == null) {
                    this.mExecutor.execute(new FlashlightControllerImpl$$ExternalSyntheticLambda0(this));
                }
                cleanUpListenersLocked(flashlightController$FlashlightListener);
                this.mListeners.add(new WeakReference(flashlightController$FlashlightListener));
                flashlightController$FlashlightListener.onFlashlightAvailabilityChanged(isAvailable());
                flashlightController$FlashlightListener.onFlashlightChanged(isEnabled());
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void cleanUpListenersLocked(FlashlightController$FlashlightListener flashlightController$FlashlightListener) {
        for (int size = this.mListeners.size() - 1; size >= 0; size--) {
            FlashlightController$FlashlightListener flashlightController$FlashlightListener2 = (FlashlightController$FlashlightListener) ((WeakReference) this.mListeners.get(size)).get();
            if (flashlightController$FlashlightListener2 == null || flashlightController$FlashlightListener2 == flashlightController$FlashlightListener) {
                this.mListeners.remove(size);
            }
        }
    }

    public final void dispatchListeners(int i, boolean z) {
        synchronized (this.mListeners) {
            try {
                ArrayList arrayList = new ArrayList(this.mListeners);
                int size = arrayList.size();
                boolean z2 = false;
                for (int i2 = 0; i2 < size; i2++) {
                    FlashlightController$FlashlightListener flashlightController$FlashlightListener = (FlashlightController$FlashlightListener) ((WeakReference) arrayList.get(i2)).get();
                    if (flashlightController$FlashlightListener == null) {
                        z2 = true;
                    } else if (i == 0) {
                        flashlightController$FlashlightListener.onFlashlightError();
                    } else if (i == 1) {
                        flashlightController$FlashlightListener.onFlashlightChanged(z);
                    } else if (i == 2) {
                        flashlightController$FlashlightListener.onFlashlightAvailabilityChanged(z);
                    }
                }
                if (z2) {
                    cleanUpListenersLocked(null);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("FlashlightController state:");
        printWriter.print("  mCameraId=");
        printWriter.println(this.mCameraId);
        printWriter.print("  mFlashlightEnabled=");
        printWriter.println(this.mFlashlightEnabled);
        printWriter.print("  mTorchAvailable=");
        printWriter.println(this.mTorchAvailable);
    }

    public final synchronized boolean isAvailable() {
        return this.mTorchAvailable;
    }

    public final synchronized boolean isEnabled() {
        return this.mFlashlightEnabled;
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void removeCallback(Object obj) {
        FlashlightController$FlashlightListener flashlightController$FlashlightListener = (FlashlightController$FlashlightListener) obj;
        synchronized (this.mListeners) {
            cleanUpListenersLocked(flashlightController$FlashlightListener);
        }
    }

    public final void setFlashlight(final boolean z) {
        if (this.mHasFlashlight) {
            if (this.mCameraId.get() == null) {
                this.mExecutor.execute(new FlashlightControllerImpl$$ExternalSyntheticLambda0(this));
            }
            this.mExecutor.execute(new Runnable() { // from class: com.android.systemui.statusbar.policy.FlashlightControllerImpl$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    FlashlightControllerImpl flashlightControllerImpl = FlashlightControllerImpl.this;
                    boolean z2 = z;
                    if (flashlightControllerImpl.mCameraId.get() == null) {
                        return;
                    }
                    synchronized (flashlightControllerImpl) {
                        if (flashlightControllerImpl.mFlashlightEnabled != z2) {
                            try {
                                flashlightControllerImpl.mCameraManager.setTorchMode((String) flashlightControllerImpl.mCameraId.get(), z2);
                            } catch (CameraAccessException e) {
                                Log.e("FlashlightController", "Couldn't set torch mode", e);
                                flashlightControllerImpl.dispatchListeners(1, false);
                            }
                        }
                    }
                }
            });
        }
    }
}

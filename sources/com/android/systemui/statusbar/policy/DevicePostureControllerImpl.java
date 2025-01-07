package com.android.systemui.statusbar.policy;

import android.R;
import android.content.Context;
import android.hardware.devicestate.DeviceState;
import android.hardware.devicestate.DeviceStateManager;
import android.hardware.devicestate.DeviceStateUtil;
import android.os.Trace;
import android.util.SparseIntArray;
import com.android.app.tracing.ListenersTracing$forEachTraced$1$1$1;
import com.android.app.tracing.TraceUtilsKt;
import com.android.systemui.statusbar.policy.DevicePostureController;
import com.android.systemui.util.Assert;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import kotlin.jvm.JvmClassMappingKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DevicePostureControllerImpl implements DevicePostureController {
    public DeviceState mCurrentDeviceState;
    public final List mSupportedStates;
    public final List mListeners = new CopyOnWriteArrayList();
    public int mCurrentDevicePosture = 0;
    public final SparseIntArray mDeviceStateToPostureMap = new SparseIntArray();

    public DevicePostureControllerImpl(Context context, DeviceStateManager deviceStateManager, Executor executor) {
        for (String str : context.getResources().getStringArray(R.array.config_disableApkUnlessMatchedSku_skus_list)) {
            String[] split = str.split(":");
            if (split.length == 2) {
                try {
                    this.mDeviceStateToPostureMap.put(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
                } catch (NumberFormatException unused) {
                }
            }
        }
        this.mSupportedStates = deviceStateManager.getSupportedDeviceStates();
        deviceStateManager.registerCallback(executor, new DeviceStateManager.DeviceStateCallback() { // from class: com.android.systemui.statusbar.policy.DevicePostureControllerImpl.1
            public final void onDeviceStateChanged(DeviceState deviceState) {
                DevicePostureControllerImpl.this.mCurrentDeviceState = deviceState;
                Assert.isMainThread();
                int i = DevicePostureControllerImpl.this.mDeviceStateToPostureMap.get(deviceState.getIdentifier(), 0);
                DevicePostureControllerImpl devicePostureControllerImpl = DevicePostureControllerImpl.this;
                if (i != devicePostureControllerImpl.mCurrentDevicePosture || i == 1000) {
                    devicePostureControllerImpl.mCurrentDevicePosture = i;
                    Iterator it = ((CopyOnWriteArrayList) devicePostureControllerImpl.mListeners).iterator();
                    while (it.hasNext()) {
                        Object next = it.next();
                        boolean isEnabled = Trace.isEnabled();
                        if (isEnabled) {
                            TraceUtilsKt.beginSlice("DevicePostureControllerImpl#".concat(((Class) new ListenersTracing$forEachTraced$1$1$1(next, JvmClassMappingKt.class, "javaClass", "getJavaClass(Ljava/lang/Object;)Ljava/lang/Class;", 1).get()).getName()));
                        }
                        try {
                            ((DevicePostureController.Callback) next).onPostureChanged(DevicePostureControllerImpl.this.getDevicePosture());
                            if (isEnabled) {
                                TraceUtilsKt.endSlice();
                            }
                        } catch (Throwable th) {
                            if (isEnabled) {
                                TraceUtilsKt.endSlice();
                            }
                            throw th;
                        }
                    }
                }
            }
        });
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void addCallback(Object obj) {
        Assert.isMainThread();
        ((CopyOnWriteArrayList) this.mListeners).add((DevicePostureController.Callback) obj);
    }

    public final int getDevicePosture() {
        int i = this.mCurrentDevicePosture;
        return i == 1000 ? DeviceStateUtil.calculateBaseStateIdentifier(this.mCurrentDeviceState, this.mSupportedStates) : i;
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void removeCallback(Object obj) {
        Assert.isMainThread();
        ((CopyOnWriteArrayList) this.mListeners).remove((DevicePostureController.Callback) obj);
    }
}

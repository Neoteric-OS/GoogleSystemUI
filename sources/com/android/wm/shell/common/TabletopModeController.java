package com.android.wm.shell.common;

import android.R;
import android.content.Context;
import android.content.res.Configuration;
import android.os.SystemProperties;
import android.util.ArraySet;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.wm.shell.common.DevicePostureController;
import com.android.wm.shell.common.DisplayController;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.sysui.ShellInit;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TabletopModeController implements DevicePostureController.OnDevicePostureChangedListener, DisplayController.OnDisplaysChangedListener {
    public static final boolean PREFER_TOP_HALF_IN_TABLETOP = SystemProperties.getBoolean("persist.wm.debug.prefer_top_half_in_tabletop", true);
    public final Context mContext;
    public final DevicePostureController mDevicePostureController;
    public final DisplayController mDisplayController;
    public Boolean mLastIsInTabletopModeForCallback;
    public final ShellExecutor mMainExecutor;
    final Runnable mOnEnterTabletopModeCallback;
    public final Set mTabletopModeRotations = new ArraySet();
    public final List mListeners = new ArrayList();
    public int mDevicePosture = 0;
    public int mDisplayRotation = -1;

    public TabletopModeController(Context context, ShellInit shellInit, DevicePostureController devicePostureController, DisplayController displayController, ShellExecutor shellExecutor) {
        final int i = 0;
        this.mOnEnterTabletopModeCallback = new Runnable(this) { // from class: com.android.wm.shell.common.TabletopModeController$$ExternalSyntheticLambda1
            public final /* synthetic */ TabletopModeController f$0;

            {
                this.f$0 = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                int i2 = i;
                TabletopModeController tabletopModeController = this.f$0;
                switch (i2) {
                    case 0:
                        if (tabletopModeController.isInTabletopMode()) {
                            Boolean bool = tabletopModeController.mLastIsInTabletopModeForCallback;
                            if (bool == null || !bool.booleanValue()) {
                                ((ArrayList) tabletopModeController.mListeners).forEach(new TabletopModeController$$ExternalSyntheticLambda0(true));
                                tabletopModeController.mLastIsInTabletopModeForCallback = Boolean.TRUE;
                                break;
                            }
                        }
                        break;
                    default:
                        tabletopModeController.onInit();
                        break;
                }
            }
        };
        this.mContext = context;
        this.mDevicePostureController = devicePostureController;
        this.mDisplayController = displayController;
        this.mMainExecutor = shellExecutor;
        final int i2 = 1;
        shellInit.addInitCallback(new Runnable(this) { // from class: com.android.wm.shell.common.TabletopModeController$$ExternalSyntheticLambda1
            public final /* synthetic */ TabletopModeController f$0;

            {
                this.f$0 = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                int i22 = i2;
                TabletopModeController tabletopModeController = this.f$0;
                switch (i22) {
                    case 0:
                        if (tabletopModeController.isInTabletopMode()) {
                            Boolean bool = tabletopModeController.mLastIsInTabletopModeForCallback;
                            if (bool == null || !bool.booleanValue()) {
                                ((ArrayList) tabletopModeController.mListeners).forEach(new TabletopModeController$$ExternalSyntheticLambda0(true));
                                tabletopModeController.mLastIsInTabletopModeForCallback = Boolean.TRUE;
                                break;
                            }
                        }
                        break;
                    default:
                        tabletopModeController.onInit();
                        break;
                }
            }
        }, this);
    }

    public final boolean isInTabletopMode() {
        if (this.mDevicePosture == 2) {
            if (((ArraySet) this.mTabletopModeRotations).contains(Integer.valueOf(this.mDisplayRotation))) {
                return true;
            }
        }
        return false;
    }

    public final void onDevicePostureOrDisplayRotationChanged(int i, int i2) {
        boolean isInTabletopMode = isInTabletopMode();
        this.mDevicePosture = i;
        this.mDisplayRotation = i2;
        boolean isInTabletopMode2 = isInTabletopMode();
        Runnable runnable = this.mOnEnterTabletopModeCallback;
        HandlerExecutor handlerExecutor = (HandlerExecutor) this.mMainExecutor;
        handlerExecutor.removeCallbacks(runnable);
        if (!isInTabletopMode && isInTabletopMode2) {
            handlerExecutor.executeDelayed(this.mOnEnterTabletopModeCallback, 1000L);
            return;
        }
        Boolean bool = this.mLastIsInTabletopModeForCallback;
        if (bool == null || bool.booleanValue()) {
            ((ArrayList) this.mListeners).forEach(new TabletopModeController$$ExternalSyntheticLambda0(false));
            this.mLastIsInTabletopModeForCallback = Boolean.FALSE;
        }
    }

    @Override // com.android.wm.shell.common.DisplayController.OnDisplaysChangedListener
    public final void onDisplayConfigurationChanged(int i, Configuration configuration) {
        int displayRotation = configuration.windowConfiguration.getDisplayRotation();
        if (i != 0 || displayRotation == this.mDisplayRotation) {
            return;
        }
        onDevicePostureOrDisplayRotationChanged(this.mDevicePosture, displayRotation);
    }

    public void onInit() {
        DevicePostureController devicePostureController = this.mDevicePostureController;
        if (!devicePostureController.mListeners.contains(this)) {
            devicePostureController.mListeners.add(this);
            int i = devicePostureController.mDevicePosture;
            if (this.mDevicePosture != i) {
                onDevicePostureOrDisplayRotationChanged(i, this.mDisplayRotation);
            }
        }
        this.mDisplayController.addDisplayWindowListener(this);
        int[] intArray = this.mContext.getResources().getIntArray(R.array.config_device_state_postures);
        if (intArray == null || intArray.length == 0) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_FOLDABLE_enabled[4]) {
                ProtoLogImpl_411527699.e(ShellProtoLogGroup.WM_SHELL_FOLDABLE, 1209522085953878031L, 0, null);
                return;
            }
            return;
        }
        for (int i2 : intArray) {
            if (i2 == 0) {
                ((ArraySet) this.mTabletopModeRotations).add(0);
            } else if (i2 == 90) {
                ((ArraySet) this.mTabletopModeRotations).add(1);
            } else if (i2 == 180) {
                ((ArraySet) this.mTabletopModeRotations).add(2);
            } else if (i2 == 270) {
                ((ArraySet) this.mTabletopModeRotations).add(3);
            } else if (ProtoLogImpl_411527699.Cache.WM_SHELL_FOLDABLE_enabled[4]) {
                ProtoLogImpl_411527699.e(ShellProtoLogGroup.WM_SHELL_FOLDABLE, -8225940378403785830L, 1, Long.valueOf(i2));
            }
        }
    }
}

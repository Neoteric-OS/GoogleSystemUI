package com.android.keyguard;

import android.R;
import android.app.Presentation;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.devicestate.DeviceState;
import android.hardware.devicestate.DeviceStateManager;
import android.hardware.display.DisplayManager;
import android.media.MediaRouter;
import android.os.Trace;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.Display;
import android.view.DisplayAddress;
import android.view.DisplayInfo;
import android.view.WindowManager;
import com.android.systemui.navigationbar.NavigationBarControllerImpl;
import com.android.systemui.navigationbar.views.NavigationBarView;
import com.android.systemui.settings.DisplayTracker;
import com.android.systemui.settings.DisplayTrackerImpl;
import com.android.systemui.shared.clocks.ClockRegistry;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$60;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;
import dagger.Lazy;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardDisplayManager {
    public static final boolean DEBUG = KeyguardConstants.DEBUG;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$60 mConnectedDisplayKeyguardPresentationFactory;
    public final Context mContext;
    public final DeviceStateHelper mDeviceStateHelper;
    public final DisplayTracker.Callback mDisplayCallback;
    public final DisplayManager mDisplayService;
    public final DisplayTracker mDisplayTracker;
    public final KeyguardStateController mKeyguardStateController;
    public final AnonymousClass2 mMediaRouterCallback;
    public final Lazy mNavigationBarControllerLazy;
    public boolean mShowing;
    public MediaRouter mMediaRouter = null;
    public final DisplayInfo mTmpDisplayInfo = new DisplayInfo();
    public final SparseArray mPresentations = new SparseArray();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DeviceStateHelper implements DeviceStateManager.DeviceStateCallback {
        public final int mConcurrentState;
        public boolean mIsInConcurrentDisplayState;
        public final DisplayAddress.Physical mRearDisplayPhysicalAddress;

        public DeviceStateHelper(Context context, DeviceStateManager deviceStateManager, Executor executor) {
            String string = context.getResources().getString(R.string.config_satellite_sim_plmn_identifier);
            if (TextUtils.isEmpty(string)) {
                this.mRearDisplayPhysicalAddress = null;
            } else {
                this.mRearDisplayPhysicalAddress = DisplayAddress.fromPhysicalDisplayId(Long.parseLong(string));
            }
            this.mConcurrentState = context.getResources().getInteger(R.integer.config_deviceStateRearDisplay);
            deviceStateManager.registerCallback(executor, this);
        }

        public final void onDeviceStateChanged(DeviceState deviceState) {
            this.mIsInConcurrentDisplayState = deviceState.getIdentifier() == this.mConcurrentState;
        }
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [com.android.keyguard.KeyguardDisplayManager$2] */
    public KeyguardDisplayManager(Context context, Lazy lazy, DisplayTracker displayTracker, Executor executor, Executor executor2, DeviceStateHelper deviceStateHelper, KeyguardStateController keyguardStateController, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$60 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$60) {
        DisplayTracker.Callback callback = new DisplayTracker.Callback() { // from class: com.android.keyguard.KeyguardDisplayManager.1
            @Override // com.android.systemui.settings.DisplayTracker.Callback
            public final void onDisplayAdded(int i) {
                Trace.beginSection("KeyguardDisplayManager#onDisplayAdded(displayId=" + i + ")");
                KeyguardDisplayManager keyguardDisplayManager = KeyguardDisplayManager.this;
                Display display = keyguardDisplayManager.mDisplayService.getDisplay(i);
                if (keyguardDisplayManager.mShowing) {
                    keyguardDisplayManager.updateNavigationBarVisibility(i, false);
                    keyguardDisplayManager.showPresentation(display);
                }
                Trace.endSection();
            }

            @Override // com.android.systemui.settings.DisplayTracker.Callback
            public final void onDisplayRemoved(int i) {
                Trace.beginSection("KeyguardDisplayManager#onDisplayRemoved(displayId=" + i + ")");
                KeyguardDisplayManager keyguardDisplayManager = KeyguardDisplayManager.this;
                Presentation presentation = (Presentation) keyguardDisplayManager.mPresentations.get(i);
                if (presentation != null) {
                    presentation.dismiss();
                    keyguardDisplayManager.mPresentations.remove(i);
                }
                Trace.endSection();
            }
        };
        this.mDisplayCallback = callback;
        this.mMediaRouterCallback = new MediaRouter.SimpleCallback() { // from class: com.android.keyguard.KeyguardDisplayManager.2
            @Override // android.media.MediaRouter.Callback
            public final void onRoutePresentationDisplayChanged(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
                if (KeyguardDisplayManager.DEBUG) {
                    Log.d("KeyguardDisplayManager", "onRoutePresentationDisplayChanged: info=" + routeInfo);
                }
                KeyguardDisplayManager keyguardDisplayManager = KeyguardDisplayManager.this;
                keyguardDisplayManager.updateDisplays(keyguardDisplayManager.mShowing);
            }

            @Override // android.media.MediaRouter.SimpleCallback, android.media.MediaRouter.Callback
            public final void onRouteSelected(MediaRouter mediaRouter, int i, MediaRouter.RouteInfo routeInfo) {
                if (KeyguardDisplayManager.DEBUG) {
                    Log.d("KeyguardDisplayManager", "onRouteSelected: type=" + i + ", info=" + routeInfo);
                }
                KeyguardDisplayManager keyguardDisplayManager = KeyguardDisplayManager.this;
                keyguardDisplayManager.updateDisplays(keyguardDisplayManager.mShowing);
            }

            @Override // android.media.MediaRouter.SimpleCallback, android.media.MediaRouter.Callback
            public final void onRouteUnselected(MediaRouter mediaRouter, int i, MediaRouter.RouteInfo routeInfo) {
                if (KeyguardDisplayManager.DEBUG) {
                    Log.d("KeyguardDisplayManager", "onRouteUnselected: type=" + i + ", info=" + routeInfo);
                }
                KeyguardDisplayManager keyguardDisplayManager = KeyguardDisplayManager.this;
                keyguardDisplayManager.updateDisplays(keyguardDisplayManager.mShowing);
            }
        };
        this.mContext = context;
        this.mNavigationBarControllerLazy = lazy;
        executor2.execute(new Runnable() { // from class: com.android.keyguard.KeyguardDisplayManager$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                KeyguardDisplayManager keyguardDisplayManager = KeyguardDisplayManager.this;
                keyguardDisplayManager.mMediaRouter = (MediaRouter) keyguardDisplayManager.mContext.getSystemService(MediaRouter.class);
            }
        });
        this.mDisplayService = (DisplayManager) context.getSystemService(DisplayManager.class);
        this.mDisplayTracker = displayTracker;
        ((DisplayTrackerImpl) displayTracker).addDisplayChangeCallback(callback, executor);
        this.mDeviceStateHelper = deviceStateHelper;
        this.mKeyguardStateController = keyguardStateController;
        this.mConnectedDisplayKeyguardPresentationFactory = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$60;
    }

    public final boolean showPresentation(Display display) {
        DisplayAddress.Physical physical;
        boolean z = DEBUG;
        if (display != null) {
            int displayId = display.getDisplayId();
            this.mDisplayTracker.getClass();
            if (displayId != 0) {
                display.getDisplayInfo(this.mTmpDisplayInfo);
                int i = this.mTmpDisplayInfo.flags;
                if ((i & 4) != 0) {
                    if (z) {
                        Log.i("KeyguardDisplayManager", "Do not show KeyguardPresentation on a private display");
                    }
                } else {
                    if ((i & 512) == 0) {
                        if (((KeyguardStateControllerImpl) this.mKeyguardStateController).mOccluded) {
                            DeviceStateHelper deviceStateHelper = this.mDeviceStateHelper;
                            if (deviceStateHelper.mIsInConcurrentDisplayState && (physical = deviceStateHelper.mRearDisplayPhysicalAddress) != null && physical.equals(display.getAddress())) {
                                if (z) {
                                    Log.i("KeyguardDisplayManager", "Do not show KeyguardPresentation when occluded and concurrent display is active");
                                }
                            }
                        }
                        if (z) {
                            Log.i("KeyguardDisplayManager", "Keyguard enabled on display: " + display);
                        }
                        final int displayId2 = display.getDisplayId();
                        if (((Presentation) this.mPresentations.get(displayId2)) == null) {
                            DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$60 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$60 = this.mConnectedDisplayKeyguardPresentationFactory;
                            daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$60.getClass();
                            DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$60.this$0;
                            DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = switchingProvider.sysUIGoogleGlobalRootComponentImpl;
                            Context context = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context;
                            DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) switchingProvider.wMComponentImpl;
                            final ConnectedDisplayKeyguardPresentation connectedDisplayKeyguardPresentation = new ConnectedDisplayKeyguardPresentation(display, context, new DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl), (ClockRegistry) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClockRegistryProvider.get(), DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.m1518$$Nest$mclockEventController(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl));
                            connectedDisplayKeyguardPresentation.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.android.keyguard.KeyguardDisplayManager$$ExternalSyntheticLambda1
                                @Override // android.content.DialogInterface.OnDismissListener
                                public final void onDismiss(DialogInterface dialogInterface) {
                                    KeyguardDisplayManager keyguardDisplayManager = KeyguardDisplayManager.this;
                                    ConnectedDisplayKeyguardPresentation connectedDisplayKeyguardPresentation2 = connectedDisplayKeyguardPresentation;
                                    int i2 = displayId2;
                                    if (connectedDisplayKeyguardPresentation2.equals(keyguardDisplayManager.mPresentations.get(i2))) {
                                        keyguardDisplayManager.mPresentations.remove(i2);
                                    }
                                }
                            });
                            try {
                                connectedDisplayKeyguardPresentation.show();
                            } catch (WindowManager.InvalidDisplayException e) {
                                Log.w("KeyguardDisplayManager", "Invalid display:", e);
                                connectedDisplayKeyguardPresentation = null;
                            }
                            if (connectedDisplayKeyguardPresentation != null) {
                                this.mPresentations.append(displayId2, connectedDisplayKeyguardPresentation);
                                return true;
                            }
                        }
                        return false;
                    }
                    if (z) {
                        Log.i("KeyguardDisplayManager", "Do not show KeyguardPresentation on an unlocked display");
                    }
                }
            } else if (z) {
                Log.i("KeyguardDisplayManager", "Do not show KeyguardPresentation on the default display");
            }
        } else if (z) {
            Log.i("KeyguardDisplayManager", "Cannot show Keyguard on null display");
        }
        return false;
    }

    public final void updateDisplays(boolean z) {
        if (!z) {
            this.mPresentations.size();
            for (int size = this.mPresentations.size() - 1; size >= 0; size--) {
                updateNavigationBarVisibility(this.mPresentations.keyAt(size), true);
                ((Presentation) this.mPresentations.valueAt(size)).dismiss();
            }
            this.mPresentations.clear();
            return;
        }
        for (Display display : ((DisplayTrackerImpl) this.mDisplayTracker).displayManager.getDisplays()) {
            updateNavigationBarVisibility(display.getDisplayId(), false);
            showPresentation(display);
        }
    }

    public final void updateNavigationBarVisibility(int i, boolean z) {
        NavigationBarView navigationBarView;
        this.mDisplayTracker.getClass();
        if (i == 0 || (navigationBarView = ((NavigationBarControllerImpl) this.mNavigationBarControllerLazy.get()).getNavigationBarView(i)) == null) {
            return;
        }
        if (z) {
            navigationBarView.getRootView().setVisibility(0);
        } else {
            navigationBarView.getRootView().setVisibility(8);
        }
    }
}

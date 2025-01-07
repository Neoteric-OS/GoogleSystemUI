package com.android.systemui.accessibility;

import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import android.util.Range;
import android.view.WindowManager;
import android.view.accessibility.IMagnificationConnectionCallback;
import com.android.app.viewcapture.ViewCaptureAwareWindowManager;
import com.android.internal.accessibility.common.MagnificationConstants;
import com.android.internal.graphics.SfVsyncFrameCallbackProvider;
import com.android.systemui.accessibility.AccessibilityLogger;
import com.android.systemui.accessibility.MagnificationImpl;
import com.android.systemui.util.settings.SecureSettings;
import com.android.systemui.util.time.SystemClockImpl;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class MagnificationSettingsController implements ComponentCallbacks {
    public static final Range A11Y_ACTION_SCALE_RANGE = new Range(Float.valueOf(1.0f), Float.valueOf(MagnificationConstants.SCALE_MAX_VALUE));
    public final Configuration mConfiguration;
    public final Context mContext;
    public final int mDisplayId;
    public final Callback mSettingsControllerCallback;
    public final WindowMagnificationSettings mWindowMagnificationSettings;
    final WindowMagnificationSettingsCallback mWindowMagnificationSettingsCallback;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.accessibility.MagnificationSettingsController$1, reason: invalid class name */
    public final class AnonymousClass1 implements WindowMagnificationSettingsCallback {
        public AnonymousClass1() {
        }

        public final void onMagnifierScale(float f, boolean z) {
            IMagnificationConnectionCallback iMagnificationConnectionCallback;
            MagnificationSettingsController magnificationSettingsController = MagnificationSettingsController.this;
            Callback callback = magnificationSettingsController.mSettingsControllerCallback;
            int i = magnificationSettingsController.mDisplayId;
            float floatValue = ((Float) MagnificationSettingsController.A11Y_ACTION_SCALE_RANGE.clamp(Float.valueOf(f))).floatValue();
            MagnificationImpl.AnonymousClass3 anonymousClass3 = (MagnificationImpl.AnonymousClass3) callback;
            MagnificationConnectionImpl magnificationConnectionImpl = MagnificationImpl.this.mMagnificationConnectionImpl;
            if (magnificationConnectionImpl != null && (iMagnificationConnectionCallback = magnificationConnectionImpl.mConnectionCallback) != null) {
                try {
                    iMagnificationConnectionCallback.onPerformScaleAction(i, floatValue, z);
                } catch (RemoteException e) {
                    Log.e("WindowMagnificationConnectionImpl", "Failed to inform performing scale action", e);
                }
            }
            AccessibilityLogger accessibilityLogger = MagnificationImpl.this.mA11yLogger;
            AccessibilityLogger.MagnificationSettingsEvent magnificationSettingsEvent = AccessibilityLogger.MagnificationSettingsEvent.MAGNIFICATION_SETTINGS_ZOOM_SLIDER_CHANGED;
            synchronized (accessibilityLogger.clock) {
                try {
                    ((SystemClockImpl) accessibilityLogger.clock).getClass();
                    long elapsedRealtime = SystemClock.elapsedRealtime();
                    boolean z2 = magnificationSettingsEvent.equals(accessibilityLogger.lastEventThrottled) && elapsedRealtime - accessibilityLogger.lastTimeThrottledMs < ((long) 2000);
                    accessibilityLogger.lastEventThrottled = magnificationSettingsEvent;
                    accessibilityLogger.lastTimeThrottledMs = elapsedRealtime;
                    if (z2) {
                        return;
                    }
                    accessibilityLogger.uiEventLogger.log(magnificationSettingsEvent);
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Callback {
    }

    public MagnificationSettingsController(Context context, SfVsyncFrameCallbackProvider sfVsyncFrameCallbackProvider, Callback callback, SecureSettings secureSettings, WindowMagnificationSettings windowMagnificationSettings, ViewCaptureAwareWindowManager viewCaptureAwareWindowManager) {
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        this.mWindowMagnificationSettingsCallback = anonymousClass1;
        Context createWindowContext = context.createWindowContext(context.getDisplay(), 2024, null);
        this.mContext = createWindowContext;
        createWindowContext.setTheme(R.style.Theme_SystemUI);
        this.mDisplayId = createWindowContext.getDisplayId();
        this.mConfiguration = new Configuration(createWindowContext.getResources().getConfiguration());
        this.mSettingsControllerCallback = callback;
        if (windowMagnificationSettings != null) {
            this.mWindowMagnificationSettings = windowMagnificationSettings;
        } else {
            this.mWindowMagnificationSettings = new WindowMagnificationSettings(createWindowContext, anonymousClass1, sfVsyncFrameCallbackProvider, secureSettings, viewCaptureAwareWindowManager);
        }
    }

    @Override // android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        int diff = configuration.diff(this.mConfiguration);
        this.mConfiguration.setTo(configuration);
        onConfigurationChanged(diff);
    }

    public void onConfigurationChanged(int i) {
        WindowMagnificationSettings windowMagnificationSettings = this.mWindowMagnificationSettings;
        windowMagnificationSettings.getClass();
        if ((i & 512) == 0 && (Integer.MIN_VALUE & i) == 0 && (1073741824 & i) == 0 && (i & 4) == 0 && (i & 4096) == 0) {
            if ((i & 128) == 0 && (i & 1024) == 0) {
                return;
            }
            windowMagnificationSettings.mDraggableWindowBounds.set(windowMagnificationSettings.getDraggableWindowBounds$1());
            WindowManager.LayoutParams layoutParams = windowMagnificationSettings.mParams;
            Rect rect = windowMagnificationSettings.mDraggableWindowBounds;
            layoutParams.x = rect.right;
            layoutParams.y = rect.bottom;
            windowMagnificationSettings.updateButtonViewLayoutIfNeeded();
            return;
        }
        windowMagnificationSettings.mParams.width = windowMagnificationSettings.getPanelWidth(windowMagnificationSettings.mContext);
        windowMagnificationSettings.mParams.accessibilityTitle = windowMagnificationSettings.mContext.getString(android.R.string.android_upgrading_complete);
        boolean z = windowMagnificationSettings.mIsVisible;
        windowMagnificationSettings.hideSettingPanel(false);
        windowMagnificationSettings.inflateView();
        if (z) {
            windowMagnificationSettings.showSettingPanel(false);
        }
    }

    @Override // android.content.ComponentCallbacks
    public final void onLowMemory() {
    }
}

package com.android.systemui.keyguard.ui.view.layout.sections;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import com.android.systemui.biometrics.AuthController;
import com.android.systemui.biometrics.shared.model.SensorLocation;
import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.flags.Flags;
import com.android.systemui.flags.UnreleasedFlag;
import com.android.systemui.keyguard.shared.model.KeyguardSection;
import com.android.systemui.keyguard.ui.binder.DeviceEntryIconViewBinder;
import com.android.systemui.keyguard.ui.view.DeviceEntryIconView;
import com.android.systemui.keyguard.ui.viewmodel.DeviceEntryBackgroundViewModel;
import com.android.systemui.keyguard.ui.viewmodel.DeviceEntryForegroundViewModel;
import com.android.systemui.keyguard.ui.viewmodel.DeviceEntryIconViewModel;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LongPressHandlingViewLogger;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.shade.NotificationPanelView;
import com.android.systemui.statusbar.VibratorHelper;
import com.android.systemui.util.kotlin.DisposableHandles;
import com.android.wm.shell.R;
import dagger.Lazy;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DefaultDeviceEntrySection extends KeyguardSection {
    public final CoroutineScope applicationScope;
    public final AuthController authController;
    public final Context context;
    public final Lazy deviceEntryBackgroundViewModel;
    public final Lazy deviceEntryForegroundViewModel;
    public final Lazy deviceEntryIconViewModel;
    public DisposableHandles disposableHandle;
    public final Lazy falsingManager;
    public final FeatureFlags featureFlags;
    public final LogBuffer logBuffer;
    public final NotificationPanelView notificationPanelView;
    public final Lazy vibratorHelper;
    public final WindowManager windowManager;

    public DefaultDeviceEntrySection(CoroutineScope coroutineScope, AuthController authController, WindowManager windowManager, Context context, NotificationPanelView notificationPanelView, FeatureFlags featureFlags, Lazy lazy, Lazy lazy2, Lazy lazy3, Lazy lazy4, Lazy lazy5, Lazy lazy6, LogBuffer logBuffer) {
        this.applicationScope = coroutineScope;
        this.authController = authController;
        this.windowManager = windowManager;
        this.context = context;
        this.notificationPanelView = notificationPanelView;
        this.featureFlags = featureFlags;
        this.deviceEntryIconViewModel = lazy2;
        this.deviceEntryForegroundViewModel = lazy3;
        this.deviceEntryBackgroundViewModel = lazy4;
        this.falsingManager = lazy5;
        this.vibratorHelper = lazy6;
        this.logBuffer = logBuffer;
    }

    @Override // com.android.systemui.keyguard.shared.model.KeyguardSection
    public final void addViews(ConstraintLayout constraintLayout) {
        NotificationPanelView notificationPanelView = this.notificationPanelView;
        notificationPanelView.removeView(notificationPanelView.findViewById(R.id.lock_icon_view));
        DeviceEntryIconView deviceEntryIconView = new DeviceEntryIconView(this.context, new LongPressHandlingViewLogger(this.logBuffer, "DefaultDeviceEntrySection"));
        deviceEntryIconView.setId(R.id.device_entry_icon_view);
        constraintLayout.addView(deviceEntryIconView);
    }

    @Override // com.android.systemui.keyguard.shared.model.KeyguardSection
    public final void applyConstraints(ConstraintSet constraintSet) {
        Lazy lazy = this.deviceEntryIconViewModel;
        Log.d("DefaultDeviceEntrySection", "isUdfpsSupported=" + ((DeviceEntryIconViewModel) lazy.get()).isUdfpsSupported.getValue());
        boolean booleanValue = ((Boolean) ((DeviceEntryIconViewModel) lazy.get()).isUdfpsSupported.getValue()).booleanValue();
        AuthController authController = this.authController;
        float f = authController.mScaleFactor;
        int dimensionPixelSize = this.context.getResources().getDimensionPixelSize(R.dimen.lock_icon_margin_bottom);
        Rect bounds = this.windowManager.getCurrentWindowMetrics().getBounds();
        float f2 = bounds.right;
        UnreleasedFlag unreleasedFlag = Flags.NULL_FLAG;
        this.featureFlags.getClass();
        float f3 = bounds.bottom;
        int i = (int) ((DisplayMetrics.DENSITY_DEVICE_STABLE / 160.0f) * 36);
        if (!booleanValue) {
            centerIcon$frameworks__base__packages__SystemUI__android_common__SystemUI_core(new Point((int) (f2 / 2), (int) (f3 - ((dimensionPixelSize + i) * f))), i * f, constraintSet);
            return;
        }
        SensorLocation sensorLocation = (SensorLocation) ((StateFlowImpl) ((DeviceEntryIconViewModel) lazy.get()).udfpsLocation.$$delegate_0).getValue();
        if (sensorLocation != null) {
            int i2 = sensorLocation.naturalCenterX;
            float f4 = sensorLocation.scale;
            int i3 = sensorLocation.naturalCenterY;
            Log.d("DeviceEntrySection", "udfpsLocation=" + sensorLocation + ", scaledLocation=(" + (i2 * f4) + "," + (i3 * f4) + "), unusedAuthController=" + authController.getUdfpsLocation());
            centerIcon$frameworks__base__packages__SystemUI__android_common__SystemUI_core(new Point((int) (((float) i2) * f4), (int) (((float) i3) * f4)), ((float) sensorLocation.naturalRadius) * f4, constraintSet);
        }
    }

    @Override // com.android.systemui.keyguard.shared.model.KeyguardSection
    public final void bindData(ConstraintLayout constraintLayout) {
        DeviceEntryIconView deviceEntryIconView = (DeviceEntryIconView) constraintLayout.findViewById(R.id.device_entry_icon_view);
        if (deviceEntryIconView != null) {
            DisposableHandles disposableHandles = this.disposableHandle;
            if (disposableHandles != null) {
                disposableHandles.dispose();
            }
            this.disposableHandle = DeviceEntryIconViewBinder.m828bind9Oi015Q(this.applicationScope, deviceEntryIconView, (DeviceEntryIconViewModel) this.deviceEntryIconViewModel.get(), (DeviceEntryForegroundViewModel) this.deviceEntryForegroundViewModel.get(), (DeviceEntryBackgroundViewModel) this.deviceEntryBackgroundViewModel.get(), (FalsingManager) this.falsingManager.get(), (VibratorHelper) this.vibratorHelper.get(), null);
        }
    }

    public final void centerIcon$frameworks__base__packages__SystemUI__android_common__SystemUI_core(Point point, float f, ConstraintSet constraintSet) {
        Rect rect = new Rect();
        int i = point.x;
        int i2 = (int) f;
        int i3 = point.y;
        rect.set(i - i2, i3 - i2, i + i2, i3 + i2);
        constraintSet.constrainWidth(R.id.device_entry_icon_view, rect.right - rect.left);
        constraintSet.constrainHeight(R.id.device_entry_icon_view, rect.bottom - rect.top);
        constraintSet.connect(R.id.device_entry_icon_view, 3, 0, 3, rect.top);
        constraintSet.connect(R.id.device_entry_icon_view, 6, 0, 6, rect.left);
    }

    @Override // com.android.systemui.keyguard.shared.model.KeyguardSection
    public final void removeViews(ConstraintLayout constraintLayout) {
        ExtensionsKt.removeView(constraintLayout, R.id.device_entry_icon_view);
        DisposableHandles disposableHandles = this.disposableHandle;
        if (disposableHandles != null) {
            disposableHandles.dispose();
        }
    }
}

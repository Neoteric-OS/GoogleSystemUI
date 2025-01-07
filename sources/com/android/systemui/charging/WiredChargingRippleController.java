package com.android.systemui.charging;

import android.content.Context;
import android.graphics.Rect;
import android.os.SystemProperties;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import com.android.app.viewcapture.ViewCaptureAwareWindowManager;
import com.android.internal.logging.UiEventLogger;
import com.android.settingslib.Utils;
import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.flags.FeatureFlagsClassicRelease;
import com.android.systemui.flags.Flags;
import com.android.systemui.statusbar.commandline.Command;
import com.android.systemui.statusbar.commandline.CommandRegistry;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.statusbar.policy.BatteryControllerImpl;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.surfaceeffects.ripple.RippleShader;
import com.android.systemui.surfaceeffects.ripple.RippleView;
import com.android.systemui.util.time.SystemClock;
import com.android.wm.shell.R;
import java.io.PrintWriter;
import java.util.List;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class WiredChargingRippleController {
    public final BatteryController batteryController;
    public final ConfigurationController configurationController;
    public final Context context;
    public int debounceLevel;
    public Long lastTriggerTime;
    public float normalizedPortPosX;
    public float normalizedPortPosY;
    public boolean pluggedIn;
    public final RippleView rippleView;
    public final SystemClock systemClock;
    public final UiEventLogger uiEventLogger;
    public final ViewCaptureAwareWindowManager viewCaptureAwareWindowManager;
    public final WindowManager.LayoutParams windowLayoutParams;
    public final WindowManager windowManager;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ChargingRippleCommand implements Command {
        public ChargingRippleCommand() {
        }

        @Override // com.android.systemui.statusbar.commandline.Command
        public final void execute(PrintWriter printWriter, List list) {
            WiredChargingRippleController.this.startRipple();
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class WiredChargingRippleEvent implements UiEventLogger.UiEventEnum {
        public static final /* synthetic */ WiredChargingRippleEvent[] $VALUES;
        public static final WiredChargingRippleEvent CHARGING_RIPPLE_PLAYED;
        private final int _id = 829;

        static {
            WiredChargingRippleEvent wiredChargingRippleEvent = new WiredChargingRippleEvent();
            CHARGING_RIPPLE_PLAYED = wiredChargingRippleEvent;
            WiredChargingRippleEvent[] wiredChargingRippleEventArr = {wiredChargingRippleEvent};
            $VALUES = wiredChargingRippleEventArr;
            EnumEntriesKt.enumEntries(wiredChargingRippleEventArr);
        }

        public static WiredChargingRippleEvent valueOf(String str) {
            return (WiredChargingRippleEvent) Enum.valueOf(WiredChargingRippleEvent.class, str);
        }

        public static WiredChargingRippleEvent[] values() {
            return (WiredChargingRippleEvent[]) $VALUES.clone();
        }

        public final int getId() {
            return this._id;
        }
    }

    public WiredChargingRippleController(CommandRegistry commandRegistry, BatteryController batteryController, ConfigurationController configurationController, FeatureFlags featureFlags, Context context, WindowManager windowManager, ViewCaptureAwareWindowManager viewCaptureAwareWindowManager, SystemClock systemClock, UiEventLogger uiEventLogger) {
        this.batteryController = batteryController;
        this.configurationController = configurationController;
        this.context = context;
        this.windowManager = windowManager;
        this.viewCaptureAwareWindowManager = viewCaptureAwareWindowManager;
        this.systemClock = systemClock;
        this.uiEventLogger = uiEventLogger;
        if (((FeatureFlagsClassicRelease) featureFlags).isEnabled(Flags.CHARGING_RIPPLE)) {
            SystemProperties.getBoolean("persist.debug.suppress-charging-ripple", false);
        }
        this.normalizedPortPosX = context.getResources().getFloat(R.dimen.physical_charger_port_location_normalized_x);
        this.normalizedPortPosY = context.getResources().getFloat(R.dimen.physical_charger_port_location_normalized_y);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.width = -1;
        layoutParams.height = -1;
        layoutParams.layoutInDisplayCutoutMode = 3;
        layoutParams.format = -3;
        layoutParams.type = 2009;
        layoutParams.setFitInsetsTypes(0);
        layoutParams.setTitle("Wired Charging Animation");
        layoutParams.flags = 24;
        layoutParams.setTrustedOverlay();
        this.windowLayoutParams = layoutParams;
        RippleView rippleView = new RippleView(context, null);
        rippleView.setupShader(RippleShader.RippleShape.CIRCLE);
        this.rippleView = rippleView;
        this.pluggedIn = ((BatteryControllerImpl) batteryController).mPluggedIn;
        commandRegistry.registerCommand("charging-ripple", new Function0() { // from class: com.android.systemui.charging.WiredChargingRippleController.1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return WiredChargingRippleController.this.new ChargingRippleCommand();
            }
        });
        updateRippleColor();
    }

    public final void startRipple() {
        RippleView rippleView = this.rippleView;
        if (rippleView.animator.isRunning() || rippleView.getParent() != null) {
            return;
        }
        this.windowLayoutParams.packageName = this.context.getOpPackageName();
        rippleView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.android.systemui.charging.WiredChargingRippleController$startRipple$1
            @Override // android.view.View.OnAttachStateChangeListener
            public final void onViewAttachedToWindow(View view) {
                WiredChargingRippleController wiredChargingRippleController = WiredChargingRippleController.this;
                Rect bounds = wiredChargingRippleController.windowManager.getCurrentWindowMetrics().getBounds();
                int width = bounds.width();
                int height = bounds.height();
                float max = Integer.max(width, height) * 2.0f;
                RippleView rippleView2 = wiredChargingRippleController.rippleView;
                RippleShader rippleShader = rippleView2.rippleShader;
                if (rippleShader == null) {
                    rippleShader = null;
                }
                RippleShader.RippleSize rippleSize = rippleShader.rippleSize;
                rippleSize.getClass();
                rippleSize.setSizeAtProgresses(rippleSize.initialSize, new RippleShader.SizeAtProgress(1.0f, max, max));
                Display display = wiredChargingRippleController.context.getDisplay();
                Integer valueOf = display != null ? Integer.valueOf(display.getRotation()) : null;
                if (valueOf != null && valueOf.intValue() == 0) {
                    rippleView2.setCenter(width * wiredChargingRippleController.normalizedPortPosX, height * wiredChargingRippleController.normalizedPortPosY);
                } else if (valueOf != null && valueOf.intValue() == 1) {
                    rippleView2.setCenter(width * wiredChargingRippleController.normalizedPortPosY, (1 - wiredChargingRippleController.normalizedPortPosX) * height);
                } else if (valueOf != null && valueOf.intValue() == 2) {
                    float f = 1;
                    rippleView2.setCenter((f - wiredChargingRippleController.normalizedPortPosX) * width, (f - wiredChargingRippleController.normalizedPortPosY) * height);
                } else if (valueOf != null && valueOf.intValue() == 3) {
                    rippleView2.setCenter((1 - wiredChargingRippleController.normalizedPortPosY) * width, height * wiredChargingRippleController.normalizedPortPosX);
                }
                WiredChargingRippleController wiredChargingRippleController2 = WiredChargingRippleController.this;
                wiredChargingRippleController2.rippleView.startRipple(new WiredChargingRippleController$startRipple$1$onViewAttachedToWindow$1(wiredChargingRippleController2));
                WiredChargingRippleController.this.rippleView.removeOnAttachStateChangeListener(this);
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public final void onViewDetachedFromWindow(View view) {
            }
        });
        this.viewCaptureAwareWindowManager.addView(rippleView, this.windowLayoutParams);
        this.uiEventLogger.log(WiredChargingRippleEvent.CHARGING_RIPPLE_PLAYED);
    }

    public final void updateRippleColor() {
        int defaultColor = Utils.getColorAttr(android.R.attr.colorAccent, this.context).getDefaultColor();
        int i = RippleView.$r8$clinit;
        this.rippleView.setColor(defaultColor, 115);
    }

    public static /* synthetic */ void getRippleView$annotations() {
    }
}

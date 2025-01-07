package com.android.systemui.keyguard.data.quickaffordance;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.android.systemui.animation.Expandable$Companion$fromView$1;
import com.android.systemui.controls.ControlsServiceInfo;
import com.android.systemui.controls.controller.ControlsController;
import com.android.systemui.controls.controller.Favorites;
import com.android.systemui.controls.dagger.ControlsComponent;
import com.android.systemui.controls.management.ControlsListingController;
import com.android.systemui.controls.management.ControlsListingControllerImpl;
import com.android.systemui.controls.ui.ControlsActivity;
import com.android.systemui.controls.ui.ControlsUiController;
import com.android.systemui.controls.ui.ControlsUiControllerImpl;
import com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig;
import com.android.systemui.plugins.BcSmartspaceDataPlugin;
import com.android.wm.shell.R;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.text.StringsKt;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class HomeControlsKeyguardQuickAffordanceConfig implements KeyguardQuickAffordanceConfig {
    public final Context appContext;
    public final ControlsComponent component;
    public final Context context;
    public final ChannelFlowTransformLatest lockScreenState;
    public final String key = BcSmartspaceDataPlugin.UI_SURFACE_HOME_SCREEN;
    public final Lazy pickerIconResourceId$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.keyguard.data.quickaffordance.HomeControlsKeyguardQuickAffordanceConfig$pickerIconResourceId$2
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return Integer.valueOf(HomeControlsKeyguardQuickAffordanceConfig.this.component.controlsTileResourceConfiguration.getTileImageId());
        }
    });

    public HomeControlsKeyguardQuickAffordanceConfig(Context context, ControlsComponent controlsComponent) {
        this.context = context;
        this.component = controlsComponent;
        this.appContext = context.getApplicationContext();
        this.lockScreenState = FlowKt.transformLatest(controlsComponent.canShowWhileLockedSetting, new HomeControlsKeyguardQuickAffordanceConfig$special$$inlined$flatMapLatest$1(null, this));
    }

    public static KeyguardQuickAffordanceConfig.PickerScreenState.Disabled disabledPickerState(String str, String str2, Intent intent) {
        if (intent == null || str2 != null) {
            return new KeyguardQuickAffordanceConfig.PickerScreenState.Disabled(str, str2, intent);
        }
        throw new IllegalStateException("Check failed.");
    }

    @Override // com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig
    public final String getKey() {
        return this.key;
    }

    @Override // com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig
    public final Flow getLockScreenState() {
        return this.lockScreenState;
    }

    @Override // com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig
    public final int getPickerIconResourceId() {
        return ((Number) this.pickerIconResourceId$delegate.getValue()).intValue();
    }

    @Override // com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig
    public final Object getPickerScreenState(Continuation continuation) {
        ControlsComponent controlsComponent = this.component;
        if (!controlsComponent.featureEnabled) {
            return KeyguardQuickAffordanceConfig.PickerScreenState.UnavailableOnDevice.INSTANCE;
        }
        Intent intent = null;
        ControlsListingController controlsListingController = (ControlsListingController) controlsComponent.controlsListingController.orElse(null);
        List currentServices = controlsListingController != null ? ((ControlsListingControllerImpl) controlsListingController).getCurrentServices() : null;
        boolean z = false;
        boolean z2 = ((ControlsController) controlsComponent.controlsController.orElse(null)) != null && (Favorites.getAllStructures().isEmpty() ^ true);
        if (currentServices != null && !currentServices.isEmpty()) {
            Iterator it = currentServices.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if (((ControlsServiceInfo) it.next()).panelActivity != null) {
                    z = true;
                    break;
                }
            }
        }
        String packageName = controlsComponent.controlsTileResourceConfiguration.getPackageName();
        if ((currentServices != null && !currentServices.isEmpty()) || packageName == null || packageName.length() == 0) {
            if ((currentServices == null || currentServices.isEmpty()) && (packageName == null || packageName.length() == 0)) {
                return disabledPickerState(this.context.getString(R.string.home_quick_affordance_unavailable_install_the_app), null, null);
            }
            if (z2 || z) {
                return new KeyguardQuickAffordanceConfig.PickerScreenState.Default(null);
            }
            Class resolveActivity = ((ControlsUiControllerImpl) ((ControlsUiController) controlsComponent.controlsUiController.get())).resolveActivity();
            String string = this.context.getString(R.string.home_quick_affordance_unavailable_configure_the_app);
            String string2 = this.context.getString(R.string.controls_open_app);
            Intent intent2 = new Intent();
            intent2.setComponent(new ComponentName(this.context, (Class<?>) resolveActivity));
            intent2.putExtra("extra_animate", true);
            return disabledPickerState(string, string2, intent2);
        }
        String string3 = this.context.getString(R.string.home_quick_affordance_unavailable_install_the_app);
        String string4 = this.context.getString(R.string.install_app);
        Context context = this.context;
        if (packageName.length() != 0) {
            String string5 = context.getString(R.string.config_appStorePackageName);
            String string6 = context.getString(R.string.config_appStoreAppLinkTemplate);
            if (string5.length() != 0 && string6.length() != 0) {
                if (!StringsKt.contains$default(string6, "$packageName")) {
                    throw new IllegalStateException("Check failed.");
                }
                Intent intent3 = new Intent("android.intent.action.VIEW");
                intent3.setPackage(string5);
                intent3.setData(Uri.parse(StringsKt__StringsJVMKt.replace$default(string6, "$packageName", packageName)));
                intent = intent3;
            }
        }
        return disabledPickerState(string3, string4, intent);
    }

    @Override // com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig
    public final KeyguardQuickAffordanceConfig.OnTriggeredResult onTriggered(Expandable$Companion$fromView$1 expandable$Companion$fromView$1) {
        return new KeyguardQuickAffordanceConfig.OnTriggeredResult.StartActivity(new Intent(this.appContext, (Class<?>) ControlsActivity.class).addFlags(335544320).putExtra("extra_animate", true), ((Boolean) this.component.canShowWhileLockedSetting.getValue()).booleanValue());
    }

    @Override // com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig
    public final String pickerName() {
        return this.context.getString(this.component.controlsTileResourceConfiguration.getTileTitleId());
    }
}

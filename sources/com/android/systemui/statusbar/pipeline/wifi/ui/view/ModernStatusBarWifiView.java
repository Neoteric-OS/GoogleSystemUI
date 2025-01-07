package com.android.systemui.statusbar.pipeline.wifi.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.android.keyguard.logging.CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0;
import com.android.systemui.statusbar.StatusBarIconView;
import com.android.systemui.statusbar.pipeline.shared.ui.binder.ModernStatusBarViewBinding;
import com.android.systemui.statusbar.pipeline.shared.ui.view.ModernStatusBarView;
import com.android.systemui.statusbar.pipeline.wifi.ui.binder.WifiViewBinder;
import com.android.systemui.statusbar.pipeline.wifi.ui.viewmodel.LocationBasedWifiViewModel;
import com.android.wm.shell.R;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ModernStatusBarWifiView extends ModernStatusBarView {
    public ModernStatusBarWifiView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public static final ModernStatusBarWifiView constructAndBind(Context context, String str, final LocationBasedWifiViewModel locationBasedWifiViewModel) {
        final ModernStatusBarWifiView modernStatusBarWifiView = (ModernStatusBarWifiView) LayoutInflater.from(context).inflate(R.layout.new_status_bar_wifi_group, (ViewGroup) null);
        modernStatusBarWifiView.initView(str, new Function0() { // from class: com.android.systemui.statusbar.pipeline.wifi.ui.view.ModernStatusBarWifiView$Companion$constructAndBind$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return WifiViewBinder.bind(ModernStatusBarWifiView.this, locationBasedWifiViewModel);
            }
        });
        return modernStatusBarWifiView;
    }

    @Override // android.view.View
    public final String toString() {
        String slot = getSlot();
        ModernStatusBarViewBinding modernStatusBarViewBinding = this.binding;
        if (modernStatusBarViewBinding == null) {
            modernStatusBarViewBinding = null;
        }
        boolean isCollecting = modernStatusBarViewBinding.isCollecting();
        String visibleStateString = StatusBarIconView.getVisibleStateString(this.iconVisibleState);
        String frameLayout = super.toString();
        StringBuilder m = CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0.m("ModernStatusBarWifiView(slot='", slot, "', isCollecting=", isCollecting, ", visibleState=");
        m.append(visibleStateString);
        m.append("); viewString=");
        m.append(frameLayout);
        return m.toString();
    }
}

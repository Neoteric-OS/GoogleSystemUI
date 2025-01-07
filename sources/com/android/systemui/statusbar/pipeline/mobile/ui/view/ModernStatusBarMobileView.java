package com.android.systemui.statusbar.pipeline.mobile.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.appsearch.app.GenericDocument$$ExternalSyntheticOutline0;
import com.android.systemui.statusbar.StatusBarIconView;
import com.android.systemui.statusbar.pipeline.mobile.ui.MobileViewLogger;
import com.android.systemui.statusbar.pipeline.mobile.ui.binder.MobileIconBinder;
import com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.LocationBasedMobileViewModel;
import com.android.systemui.statusbar.pipeline.shared.ui.binder.ModernStatusBarViewBinding;
import com.android.systemui.statusbar.pipeline.shared.ui.view.ModernStatusBarView;
import com.android.wm.shell.R;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ModernStatusBarMobileView extends ModernStatusBarView {
    public int subId;

    public ModernStatusBarMobileView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.subId = -1;
    }

    public static final ModernStatusBarMobileView constructAndBind(Context context, final MobileViewLogger mobileViewLogger, String str, final LocationBasedMobileViewModel locationBasedMobileViewModel) {
        final ModernStatusBarMobileView modernStatusBarMobileView = (ModernStatusBarMobileView) LayoutInflater.from(context).inflate(R.layout.status_bar_mobile_signal_group_new, (ViewGroup) null);
        modernStatusBarMobileView.subId = locationBasedMobileViewModel.commonImpl.getSubscriptionId();
        modernStatusBarMobileView.initView(str, new Function0() { // from class: com.android.systemui.statusbar.pipeline.mobile.ui.view.ModernStatusBarMobileView$Companion$constructAndBind$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MobileIconBinder.bind(ModernStatusBarMobileView.this, locationBasedMobileViewModel, 2, mobileViewLogger);
            }
        });
        mobileViewLogger.logNewViewBinding(modernStatusBarMobileView, locationBasedMobileViewModel);
        return modernStatusBarMobileView;
    }

    @Override // android.view.View
    public final String toString() {
        String slot = getSlot();
        int i = this.subId;
        ModernStatusBarViewBinding modernStatusBarViewBinding = this.binding;
        if (modernStatusBarViewBinding == null) {
            modernStatusBarViewBinding = null;
        }
        boolean isCollecting = modernStatusBarViewBinding.isCollecting();
        String visibleStateString = StatusBarIconView.getVisibleStateString(this.iconVisibleState);
        String frameLayout = super.toString();
        StringBuilder m = GenericDocument$$ExternalSyntheticOutline0.m("ModernStatusBarMobileView(slot='", slot, "', subId=", i, ", isCollecting=");
        m.append(isCollecting);
        m.append(", visibleState=");
        m.append(visibleStateString);
        m.append("); viewString=");
        m.append(frameLayout);
        return m.toString();
    }
}

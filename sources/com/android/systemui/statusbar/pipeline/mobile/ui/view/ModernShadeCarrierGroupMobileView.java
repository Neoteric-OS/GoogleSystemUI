package com.android.systemui.statusbar.pipeline.mobile.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.android.systemui.statusbar.pipeline.mobile.ui.MobileViewLogger;
import com.android.systemui.statusbar.pipeline.mobile.ui.binder.MobileIconBinder;
import com.android.systemui.statusbar.pipeline.mobile.ui.binder.ShadeCarrierBinder;
import com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.ShadeCarrierGroupMobileIconViewModel;
import com.android.systemui.util.AutoMarqueeTextView;
import com.android.wm.shell.R;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ModernShadeCarrierGroupMobileView extends LinearLayout {
    public static final /* synthetic */ int $r8$clinit = 0;
    public int subId;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Companion {
        public static ModernShadeCarrierGroupMobileView constructAndBind(Context context, final MobileViewLogger mobileViewLogger, final ShadeCarrierGroupMobileIconViewModel shadeCarrierGroupMobileIconViewModel) {
            ModernShadeCarrierGroupMobileView modernShadeCarrierGroupMobileView = (ModernShadeCarrierGroupMobileView) LayoutInflater.from(context).inflate(R.layout.shade_carrier_new, (ViewGroup) null);
            modernShadeCarrierGroupMobileView.subId = shadeCarrierGroupMobileIconViewModel.commonImpl.getSubscriptionId();
            final ModernStatusBarMobileView modernStatusBarMobileView = (ModernStatusBarMobileView) modernShadeCarrierGroupMobileView.requireViewById(R.id.mobile_combo);
            modernStatusBarMobileView.initView("mobile_carrier_shade_group", new Function0() { // from class: com.android.systemui.statusbar.pipeline.mobile.ui.view.ModernShadeCarrierGroupMobileView$Companion$constructAndBind$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return MobileIconBinder.bind(ModernStatusBarMobileView.this, shadeCarrierGroupMobileIconViewModel, 0, mobileViewLogger);
                }
            });
            mobileViewLogger.logNewViewBinding(modernShadeCarrierGroupMobileView, shadeCarrierGroupMobileIconViewModel);
            ShadeCarrierBinder.bind((AutoMarqueeTextView) modernShadeCarrierGroupMobileView.requireViewById(R.id.mobile_carrier_text), shadeCarrierGroupMobileIconViewModel);
            return modernShadeCarrierGroupMobileView;
        }
    }

    public ModernShadeCarrierGroupMobileView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.subId = -1;
    }

    @Override // android.view.View
    public final String toString() {
        return "ModernShadeCarrierGroupMobileView(subId=" + this.subId + ", viewString=" + super.toString();
    }
}

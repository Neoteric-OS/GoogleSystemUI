package com.android.systemui.qs.footer.ui.viewmodel;

import android.content.Context;
import com.android.systemui.animation.Expandable;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.qs.footer.domain.interactor.FooterActionsInteractor;
import com.android.systemui.qs.footer.domain.interactor.FooterActionsInteractorImpl;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final /* synthetic */ class FooterActionsViewModelKt$FooterActionsViewModel$security$1$1$1 extends FunctionReferenceImpl implements Function2 {
    final /* synthetic */ FalsingManager $falsingManager;
    final /* synthetic */ FooterActionsInteractor $footerActionsInteractor;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FooterActionsViewModelKt$FooterActionsViewModel$security$1$1$1(FalsingManager falsingManager, FooterActionsInteractor footerActionsInteractor) {
        super(2, Intrinsics.Kotlin.class, "onSecurityButtonClicked", "FooterActionsViewModel$onSecurityButtonClicked(Lcom/android/systemui/plugins/FalsingManager;Lcom/android/systemui/qs/footer/domain/interactor/FooterActionsInteractor;Landroid/content/Context;Lcom/android/systemui/animation/Expandable;)V", 0);
        this.$falsingManager = falsingManager;
        this.$footerActionsInteractor = footerActionsInteractor;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        Context context = (Context) obj;
        Expandable expandable = (Expandable) obj2;
        FalsingManager falsingManager = this.$falsingManager;
        FooterActionsInteractor footerActionsInteractor = this.$footerActionsInteractor;
        if (!falsingManager.isFalseTap(1)) {
            ((FooterActionsInteractorImpl) footerActionsInteractor).showDeviceMonitoringDialog(context, expandable);
        }
        return Unit.INSTANCE;
    }
}

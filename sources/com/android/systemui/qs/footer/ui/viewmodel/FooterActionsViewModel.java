package com.android.systemui.qs.footer.ui.viewmodel;

import android.content.Context;
import androidx.lifecycle.LifecycleCoroutineScopeImpl;
import com.android.systemui.globalactions.GlobalActionsDialogLite;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.qs.footer.domain.interactor.FooterActionsInteractor;
import dagger.internal.DelegateFactory;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FooterActionsViewModel {
    public final StateFlowImpl _alpha;
    public final StateFlowImpl _backgroundAlpha;
    public final ReadonlyStateFlow alpha;
    public final ReadonlyStateFlow backgroundAlpha;
    public final Flow foregroundServices;
    public final Function2 observeDeviceMonitoringDialogRequests;
    public final FooterActionsButtonViewModel power;
    public final Flow security;
    public final FooterActionsButtonViewModel settings;
    public final Flow userSwitcher;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Factory {
        public final ActivityStarter activityStarter;
        public final Context context;
        public final FalsingManager falsingManager;
        public final FooterActionsInteractor footerActionsInteractor;
        public final DelegateFactory globalActionsDialogLiteProvider;
        public final boolean showPowerButton;

        public Factory(Context context, FalsingManager falsingManager, FooterActionsInteractor footerActionsInteractor, DelegateFactory delegateFactory, ActivityStarter activityStarter, boolean z) {
            this.context = context;
            this.falsingManager = falsingManager;
            this.footerActionsInteractor = footerActionsInteractor;
            this.globalActionsDialogLiteProvider = delegateFactory;
            this.activityStarter = activityStarter;
            this.showPowerButton = z;
        }

        public final FooterActionsViewModel create(LifecycleCoroutineScopeImpl lifecycleCoroutineScopeImpl) {
            GlobalActionsDialogLite globalActionsDialogLite = (GlobalActionsDialogLite) this.globalActionsDialogLiteProvider.get();
            if (CoroutineScopeKt.isActive(lifecycleCoroutineScopeImpl)) {
                BuildersKt.launch$default(lifecycleCoroutineScopeImpl, null, CoroutineStart.ATOMIC, new FooterActionsViewModel$Factory$create$2(globalActionsDialogLite, null), 1);
            } else {
                globalActionsDialogLite.destroy();
            }
            Context context = this.context;
            Intrinsics.checkNotNull(globalActionsDialogLite);
            return FooterActionsViewModelKt.FooterActionsViewModel(context, this.footerActionsInteractor, this.falsingManager, globalActionsDialogLite, this.activityStarter, this.showPowerButton);
        }
    }

    public FooterActionsViewModel(Flow flow, Flow flow2, Flow flow3, FooterActionsButtonViewModel footerActionsButtonViewModel, FooterActionsButtonViewModel footerActionsButtonViewModel2, Function2 function2) {
        this.security = flow;
        this.foregroundServices = flow2;
        this.userSwitcher = flow3;
        this.settings = footerActionsButtonViewModel;
        this.power = footerActionsButtonViewModel2;
        this.observeDeviceMonitoringDialogRequests = function2;
        Float valueOf = Float.valueOf(1.0f);
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(valueOf);
        this._alpha = MutableStateFlow;
        this.alpha = new ReadonlyStateFlow(MutableStateFlow);
        StateFlowImpl MutableStateFlow2 = StateFlowKt.MutableStateFlow(valueOf);
        this._backgroundAlpha = MutableStateFlow2;
        this.backgroundAlpha = new ReadonlyStateFlow(MutableStateFlow2);
    }
}

package com.android.systemui.statusbar.pipeline.shared.ui.viewmodel;

import android.content.Context;
import com.android.systemui.common.shared.model.ContentDescription;
import com.android.systemui.common.shared.model.Text;
import com.android.systemui.statusbar.pipeline.airplane.data.repository.AirplaneModeRepository;
import com.android.systemui.statusbar.pipeline.airplane.data.repository.AirplaneModeRepositoryImpl;
import com.android.systemui.statusbar.pipeline.ethernet.domain.EthernetInteractor;
import com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconsInteractorImpl;
import com.android.systemui.statusbar.pipeline.shared.data.repository.ConnectivityRepositoryImpl;
import com.android.systemui.statusbar.pipeline.shared.ui.model.InternetTileModel;
import com.android.systemui.statusbar.pipeline.wifi.domain.interactor.WifiInteractorImpl;
import com.android.wm.shell.R;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class InternetTileViewModel {
    public static final InternetTileModel.Inactive NOT_CONNECTED_NETWORKS_UNAVAILABLE = new InternetTileModel.Inactive(null, new Text.Resource(R.string.quick_settings_networks_unavailable), Integer.valueOf(R.drawable.ic_qs_no_internet_unavailable), null, new ContentDescription.Resource(R.string.quick_settings_networks_unavailable), 9);
    public final ChannelFlowTransformLatest activeModelProvider;
    public final Context context;
    public final ChannelFlowTransformLatest ethernetIconFlow;
    public final String internetLabel;
    public final ChannelFlowTransformLatest mobileDataContentName;
    public final ChannelFlowTransformLatest mobileIconFlow;
    public final ReadonlyStateFlow notConnectedFlow;
    public final ReadonlyStateFlow tileModel;
    public final ChannelFlowTransformLatest wifiIconFlow;

    public InternetTileViewModel(AirplaneModeRepository airplaneModeRepository, ConnectivityRepositoryImpl connectivityRepositoryImpl, EthernetInteractor ethernetInteractor, MobileIconsInteractorImpl mobileIconsInteractorImpl, WifiInteractorImpl wifiInteractorImpl, Context context, CoroutineScope coroutineScope) {
        this.context = context;
        this.internetLabel = context.getString(R.string.quick_settings_internet_label);
        this.wifiIconFlow = FlowKt.transformLatest(wifiInteractorImpl.wifiNetwork, new InternetTileViewModel$special$$inlined$flatMapLatest$1(this, null));
        InternetTileViewModel$special$$inlined$flatMapLatest$2 internetTileViewModel$special$$inlined$flatMapLatest$2 = new InternetTileViewModel$special$$inlined$flatMapLatest$2(this, null);
        ReadonlyStateFlow readonlyStateFlow = mobileIconsInteractorImpl.activeDataIconInteractor;
        this.mobileDataContentName = FlowKt.transformLatest(readonlyStateFlow, internetTileViewModel$special$$inlined$flatMapLatest$2);
        this.mobileIconFlow = FlowKt.transformLatest(readonlyStateFlow, new InternetTileViewModel$special$$inlined$flatMapLatest$3(this, null));
        this.ethernetIconFlow = FlowKt.transformLatest(ethernetInteractor.icon, new InternetTileViewModel$special$$inlined$flatMapLatest$4(this, null));
        ReadonlyStateFlow stateIn = FlowKt.stateIn(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(wifiInteractorImpl.areNetworksAvailable, ((AirplaneModeRepositoryImpl) airplaneModeRepository).isAirplaneMode, new InternetTileViewModel$notConnectedFlow$1(this, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), NOT_CONNECTED_NETWORKS_UNAVAILABLE);
        this.notConnectedFlow = stateIn;
        this.tileModel = FlowKt.stateIn(FlowKt.transformLatest(connectivityRepositoryImpl.defaultConnections, new InternetTileViewModel$special$$inlined$flatMapLatest$5(this, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), stateIn.getValue());
    }
}

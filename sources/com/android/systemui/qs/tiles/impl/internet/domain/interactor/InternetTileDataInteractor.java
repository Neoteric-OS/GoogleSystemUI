package com.android.systemui.qs.tiles.impl.internet.domain.interactor;

import android.content.Context;
import android.os.UserHandle;
import com.android.systemui.common.shared.model.ContentDescription;
import com.android.systemui.common.shared.model.Text;
import com.android.systemui.qs.tiles.base.interactor.QSTileDataInteractor;
import com.android.systemui.qs.tiles.impl.internet.domain.model.InternetTileModel;
import com.android.systemui.statusbar.pipeline.airplane.data.repository.AirplaneModeRepository;
import com.android.systemui.statusbar.pipeline.airplane.data.repository.AirplaneModeRepositoryImpl;
import com.android.systemui.statusbar.pipeline.ethernet.domain.EthernetInteractor;
import com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconsInteractorImpl;
import com.android.systemui.statusbar.pipeline.shared.data.repository.ConnectivityRepositoryImpl;
import com.android.systemui.statusbar.pipeline.wifi.domain.interactor.WifiInteractorImpl;
import com.android.wm.shell.R;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class InternetTileDataInteractor implements QSTileDataInteractor {
    public static final InternetTileModel.Inactive NOT_CONNECTED_NETWORKS_UNAVAILABLE = new InternetTileModel.Inactive(null, new Text.Resource(R.string.quick_settings_networks_unavailable), Integer.valueOf(R.drawable.ic_qs_no_internet_unavailable), new ContentDescription.Resource(R.string.quick_settings_networks_unavailable), 9);
    public final ConnectivityRepositoryImpl connectivityRepository;
    public final Context context;
    public final ChannelFlowTransformLatest ethernetIconFlow;
    public final String internetLabel;
    public final CoroutineContext mainCoroutineContext;
    public final ChannelFlowTransformLatest mobileDataContentName;
    public final ChannelFlowTransformLatest mobileIconFlow;
    public final ReadonlyStateFlow notConnectedFlow;
    public final ChannelFlowTransformLatest wifiIconFlow;

    public InternetTileDataInteractor(Context context, CoroutineContext coroutineContext, CoroutineScope coroutineScope, AirplaneModeRepository airplaneModeRepository, ConnectivityRepositoryImpl connectivityRepositoryImpl, EthernetInteractor ethernetInteractor, MobileIconsInteractorImpl mobileIconsInteractorImpl, WifiInteractorImpl wifiInteractorImpl) {
        this.context = context;
        this.mainCoroutineContext = coroutineContext;
        this.connectivityRepository = connectivityRepositoryImpl;
        this.internetLabel = context.getString(R.string.quick_settings_internet_label);
        this.wifiIconFlow = FlowKt.transformLatest(wifiInteractorImpl.wifiNetwork, new InternetTileDataInteractor$special$$inlined$flatMapLatest$1(this, null));
        InternetTileDataInteractor$special$$inlined$flatMapLatest$2 internetTileDataInteractor$special$$inlined$flatMapLatest$2 = new InternetTileDataInteractor$special$$inlined$flatMapLatest$2(this, null);
        ReadonlyStateFlow readonlyStateFlow = mobileIconsInteractorImpl.activeDataIconInteractor;
        this.mobileDataContentName = FlowKt.transformLatest(readonlyStateFlow, internetTileDataInteractor$special$$inlined$flatMapLatest$2);
        this.mobileIconFlow = FlowKt.transformLatest(readonlyStateFlow, new InternetTileDataInteractor$special$$inlined$flatMapLatest$3(this, null));
        this.ethernetIconFlow = FlowKt.transformLatest(ethernetInteractor.icon, new InternetTileDataInteractor$special$$inlined$flatMapLatest$4(this, null));
        this.notConnectedFlow = FlowKt.stateIn(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(wifiInteractorImpl.areNetworksAvailable, ((AirplaneModeRepositoryImpl) airplaneModeRepository).isAirplaneMode, new InternetTileDataInteractor$notConnectedFlow$1(this, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), NOT_CONNECTED_NETWORKS_UNAVAILABLE);
    }

    @Override // com.android.systemui.qs.tiles.base.interactor.QSTileDataInteractor
    public final Flow availability(UserHandle userHandle) {
        return new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(Boolean.TRUE);
    }

    @Override // com.android.systemui.qs.tiles.base.interactor.QSTileDataInteractor
    public final Flow tileData(UserHandle userHandle, ReadonlyStateFlow readonlyStateFlow) {
        return FlowKt.transformLatest(this.connectivityRepository.defaultConnections, new InternetTileDataInteractor$tileData$$inlined$flatMapLatest$1(this, null));
    }
}

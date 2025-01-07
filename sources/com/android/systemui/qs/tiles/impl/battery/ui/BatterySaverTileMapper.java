package com.android.systemui.qs.tiles.impl.battery.ui;

import android.content.res.Resources;
import com.android.systemui.common.shared.model.Icon;
import com.android.systemui.qs.tiles.base.interactor.QSTileDataToStateMapper;
import com.android.systemui.qs.tiles.impl.battery.domain.model.BatterySaverTileModel;
import com.android.systemui.qs.tiles.viewmodel.QSTileConfig;
import com.android.systemui.qs.tiles.viewmodel.QSTileState;
import com.android.wm.shell.R;
import java.util.Collections;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BatterySaverTileMapper implements QSTileDataToStateMapper {
    public final Resources resources;
    public final Resources.Theme theme;

    public BatterySaverTileMapper(Resources resources, Resources.Theme theme) {
        this.resources = resources;
        this.theme = theme;
    }

    @Override // com.android.systemui.qs.tiles.base.interactor.QSTileDataToStateMapper
    public final QSTileState map(QSTileConfig qSTileConfig, Object obj) {
        final BatterySaverTileModel batterySaverTileModel = (BatterySaverTileModel) obj;
        return QSTileState.Companion.build(this.resources, this.theme, qSTileConfig.uiConfig, new Function1() { // from class: com.android.systemui.qs.tiles.impl.battery.ui.BatterySaverTileMapper$map$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX WARN: Type inference failed for: r0v8, types: [com.android.systemui.qs.tiles.impl.battery.ui.BatterySaverTileMapper$map$1$1, kotlin.jvm.internal.Lambda] */
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                final QSTileState.Builder builder = (QSTileState.Builder) obj2;
                String string = BatterySaverTileMapper.this.resources.getString(R.string.battery_detail_switch_title);
                builder.label = string;
                builder.contentDescription = string;
                builder.iconRes = batterySaverTileModel.isPowerSaving() ? Integer.valueOf(R.drawable.qs_battery_saver_icon_on) : Integer.valueOf(R.drawable.qs_battery_saver_icon_off);
                final BatterySaverTileMapper batterySaverTileMapper = BatterySaverTileMapper.this;
                builder.icon = new Function0() { // from class: com.android.systemui.qs.tiles.impl.battery.ui.BatterySaverTileMapper$map$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Resources resources = BatterySaverTileMapper.this.resources;
                        Integer num = builder.iconRes;
                        Intrinsics.checkNotNull(num);
                        return new Icon.Loaded(resources.getDrawable(num.intValue(), BatterySaverTileMapper.this.theme), null);
                    }
                };
                builder.sideViewIcon = QSTileState.SideViewIcon.None.INSTANCE;
                if (batterySaverTileModel.isPluggedIn()) {
                    builder.activationState = QSTileState.ActivationState.UNAVAILABLE;
                    builder.supportedActions = Collections.singleton(QSTileState.UserAction.LONG_CLICK);
                    builder.secondaryLabel = "";
                } else if (batterySaverTileModel.isPowerSaving()) {
                    builder.activationState = QSTileState.ActivationState.ACTIVE;
                    builder.supportedActions = SetsKt.setOf(QSTileState.UserAction.CLICK, QSTileState.UserAction.LONG_CLICK);
                    BatterySaverTileModel batterySaverTileModel2 = batterySaverTileModel;
                    if (batterySaverTileModel2 instanceof BatterySaverTileModel.Extreme) {
                        String string2 = BatterySaverTileMapper.this.resources.getString(((BatterySaverTileModel.Extreme) batterySaverTileModel2).isExtremeSaving ? R.string.extreme_battery_saver_text : R.string.standard_battery_saver_text);
                        builder.secondaryLabel = string2;
                        builder.stateDescription = string2;
                    } else {
                        builder.secondaryLabel = "";
                    }
                } else {
                    builder.activationState = QSTileState.ActivationState.INACTIVE;
                    builder.supportedActions = SetsKt.setOf(QSTileState.UserAction.CLICK, QSTileState.UserAction.LONG_CLICK);
                    builder.secondaryLabel = "";
                }
                return Unit.INSTANCE;
            }
        });
    }
}

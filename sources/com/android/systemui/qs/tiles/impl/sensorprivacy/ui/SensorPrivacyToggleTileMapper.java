package com.android.systemui.qs.tiles.impl.sensorprivacy.ui;

import android.content.res.Resources;
import com.android.systemui.common.shared.model.Icon;
import com.android.systemui.qs.tiles.base.interactor.QSTileDataToStateMapper;
import com.android.systemui.qs.tiles.impl.sensorprivacy.domain.model.SensorPrivacyToggleTileModel;
import com.android.systemui.qs.tiles.viewmodel.QSTileConfig;
import com.android.systemui.qs.tiles.viewmodel.QSTileState;
import com.android.wm.shell.R;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SensorPrivacyToggleTileMapper implements QSTileDataToStateMapper {
    public final Resources resources;
    public final SensorPrivacyTileResources sensorPrivacyTileResources;
    public final Resources.Theme theme;

    public SensorPrivacyToggleTileMapper(Resources resources, Resources.Theme theme, SensorPrivacyTileResources sensorPrivacyTileResources) {
        this.resources = resources;
        this.theme = theme;
        this.sensorPrivacyTileResources = sensorPrivacyTileResources;
    }

    @Override // com.android.systemui.qs.tiles.base.interactor.QSTileDataToStateMapper
    public final QSTileState map(QSTileConfig qSTileConfig, Object obj) {
        final boolean z = ((SensorPrivacyToggleTileModel) obj).isBlocked;
        return QSTileState.Companion.build(this.resources, this.theme, qSTileConfig.uiConfig, new Function1() { // from class: com.android.systemui.qs.tiles.impl.sensorprivacy.ui.SensorPrivacyToggleTileMapper$map$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX WARN: Type inference failed for: r0v11, types: [com.android.systemui.qs.tiles.impl.sensorprivacy.ui.SensorPrivacyToggleTileMapper$map$1$1, kotlin.jvm.internal.Lambda] */
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                final QSTileState.Builder builder = (QSTileState.Builder) obj2;
                SensorPrivacyToggleTileMapper sensorPrivacyToggleTileMapper = SensorPrivacyToggleTileMapper.this;
                String string = sensorPrivacyToggleTileMapper.resources.getString(sensorPrivacyToggleTileMapper.sensorPrivacyTileResources.getTileLabelRes());
                builder.label = string;
                builder.contentDescription = string;
                builder.supportedActions = SetsKt.setOf(QSTileState.UserAction.CLICK, QSTileState.UserAction.LONG_CLICK);
                builder.iconRes = Integer.valueOf(SensorPrivacyToggleTileMapper.this.sensorPrivacyTileResources.getIconRes(z));
                final SensorPrivacyToggleTileMapper sensorPrivacyToggleTileMapper2 = SensorPrivacyToggleTileMapper.this;
                builder.icon = new Function0() { // from class: com.android.systemui.qs.tiles.impl.sensorprivacy.ui.SensorPrivacyToggleTileMapper$map$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Resources resources = SensorPrivacyToggleTileMapper.this.resources;
                        Integer num = builder.iconRes;
                        Intrinsics.checkNotNull(num);
                        return new Icon.Loaded(resources.getDrawable(num.intValue(), SensorPrivacyToggleTileMapper.this.theme), null);
                    }
                };
                builder.sideViewIcon = QSTileState.SideViewIcon.None.INSTANCE;
                if (z) {
                    builder.activationState = QSTileState.ActivationState.INACTIVE;
                    builder.secondaryLabel = sensorPrivacyToggleTileMapper2.resources.getString(R.string.quick_settings_camera_mic_blocked);
                } else {
                    builder.activationState = QSTileState.ActivationState.ACTIVE;
                    builder.secondaryLabel = sensorPrivacyToggleTileMapper2.resources.getString(R.string.quick_settings_camera_mic_available);
                }
                return Unit.INSTANCE;
            }
        });
    }
}

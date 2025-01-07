package com.android.systemui.qs.tiles.impl.saver.domain;

import android.content.res.Resources;
import com.android.systemui.common.shared.model.Icon;
import com.android.systemui.qs.tiles.base.interactor.QSTileDataToStateMapper;
import com.android.systemui.qs.tiles.impl.saver.domain.model.DataSaverTileModel;
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
public final class DataSaverTileMapper implements QSTileDataToStateMapper {
    public final Resources resources;
    public final Resources.Theme theme;

    public DataSaverTileMapper(Resources resources, Resources.Theme theme) {
        this.resources = resources;
        this.theme = theme;
    }

    @Override // com.android.systemui.qs.tiles.base.interactor.QSTileDataToStateMapper
    public final QSTileState map(QSTileConfig qSTileConfig, Object obj) {
        final boolean z = ((DataSaverTileModel) obj).isEnabled;
        return QSTileState.Companion.build(this.resources, this.theme, qSTileConfig.uiConfig, new Function1() { // from class: com.android.systemui.qs.tiles.impl.saver.domain.DataSaverTileMapper$map$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX WARN: Type inference failed for: r3v4, types: [com.android.systemui.qs.tiles.impl.saver.domain.DataSaverTileMapper$map$1$1$1, kotlin.jvm.internal.Lambda] */
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                QSTileState.Builder builder = (QSTileState.Builder) obj2;
                boolean z2 = z;
                DataSaverTileMapper dataSaverTileMapper = this;
                if (z2) {
                    builder.activationState = QSTileState.ActivationState.ACTIVE;
                    builder.iconRes = Integer.valueOf(R.drawable.qs_data_saver_icon_on);
                    builder.secondaryLabel = dataSaverTileMapper.resources.getStringArray(R.array.tile_states_saver)[2];
                } else {
                    builder.activationState = QSTileState.ActivationState.INACTIVE;
                    builder.iconRes = Integer.valueOf(R.drawable.qs_data_saver_icon_off);
                    builder.secondaryLabel = dataSaverTileMapper.resources.getStringArray(R.array.tile_states_saver)[1];
                }
                Resources resources = dataSaverTileMapper.resources;
                Integer num = builder.iconRes;
                Intrinsics.checkNotNull(num);
                final Icon.Loaded loaded = new Icon.Loaded(resources.getDrawable(num.intValue(), dataSaverTileMapper.theme), null);
                builder.icon = new Function0() { // from class: com.android.systemui.qs.tiles.impl.saver.domain.DataSaverTileMapper$map$1$1$1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Icon.Loaded.this;
                    }
                };
                builder.contentDescription = builder.label;
                builder.supportedActions = SetsKt.setOf(QSTileState.UserAction.CLICK, QSTileState.UserAction.LONG_CLICK);
                return Unit.INSTANCE;
            }
        });
    }
}

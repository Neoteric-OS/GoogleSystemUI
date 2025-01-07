package com.android.systemui.qs.tiles.impl.fontscaling.domain;

import android.content.res.Resources;
import com.android.systemui.common.shared.model.Icon;
import com.android.systemui.qs.tiles.base.interactor.QSTileDataToStateMapper;
import com.android.systemui.qs.tiles.viewmodel.QSTileConfig;
import com.android.systemui.qs.tiles.viewmodel.QSTileState;
import com.android.wm.shell.R;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FontScalingTileMapper implements QSTileDataToStateMapper {
    public final Resources resources;
    public final Resources.Theme theme;

    public FontScalingTileMapper(Resources resources, Resources.Theme theme) {
        this.resources = resources;
        this.theme = theme;
    }

    @Override // com.android.systemui.qs.tiles.base.interactor.QSTileDataToStateMapper
    public final QSTileState map(QSTileConfig qSTileConfig, Object obj) {
        return QSTileState.Companion.build(this.resources, this.theme, qSTileConfig.uiConfig, new Function1() { // from class: com.android.systemui.qs.tiles.impl.fontscaling.domain.FontScalingTileMapper$map$1
            {
                super(1);
            }

            /* JADX WARN: Type inference failed for: r3v4, types: [com.android.systemui.qs.tiles.impl.fontscaling.domain.FontScalingTileMapper$map$1$1, kotlin.jvm.internal.Lambda] */
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                QSTileState.Builder builder = (QSTileState.Builder) obj2;
                builder.iconRes = Integer.valueOf(R.drawable.ic_qs_font_scaling);
                FontScalingTileMapper fontScalingTileMapper = FontScalingTileMapper.this;
                final Icon.Loaded loaded = new Icon.Loaded(fontScalingTileMapper.resources.getDrawable(R.drawable.ic_qs_font_scaling, fontScalingTileMapper.theme), null);
                builder.icon = new Function0() { // from class: com.android.systemui.qs.tiles.impl.fontscaling.domain.FontScalingTileMapper$map$1.1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Icon.Loaded.this;
                    }
                };
                builder.contentDescription = builder.label;
                builder.activationState = QSTileState.ActivationState.ACTIVE;
                builder.sideViewIcon = QSTileState.SideViewIcon.Chevron.INSTANCE;
                builder.supportedActions = SetsKt.setOf(QSTileState.UserAction.CLICK, QSTileState.UserAction.LONG_CLICK);
                return Unit.INSTANCE;
            }
        });
    }
}

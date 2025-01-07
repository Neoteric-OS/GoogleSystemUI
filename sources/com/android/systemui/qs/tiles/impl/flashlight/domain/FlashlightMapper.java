package com.android.systemui.qs.tiles.impl.flashlight.domain;

import android.content.res.Resources;
import com.android.systemui.common.shared.model.Icon;
import com.android.systemui.qs.tiles.base.interactor.QSTileDataToStateMapper;
import com.android.systemui.qs.tiles.impl.flashlight.domain.model.FlashlightTileModel;
import com.android.systemui.qs.tiles.viewmodel.QSTileConfig;
import com.android.systemui.qs.tiles.viewmodel.QSTileState;
import com.android.wm.shell.R;
import java.util.Collections;
import kotlin.Unit;
import kotlin.collections.EmptySet;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FlashlightMapper implements QSTileDataToStateMapper {
    public final Resources resources;
    public final Resources.Theme theme;

    public FlashlightMapper(Resources resources, Resources.Theme theme) {
        this.resources = resources;
        this.theme = theme;
    }

    @Override // com.android.systemui.qs.tiles.base.interactor.QSTileDataToStateMapper
    public final QSTileState map(QSTileConfig qSTileConfig, Object obj) {
        final FlashlightTileModel flashlightTileModel = (FlashlightTileModel) obj;
        return QSTileState.Companion.build(this.resources, this.theme, qSTileConfig.uiConfig, new Function1() { // from class: com.android.systemui.qs.tiles.impl.flashlight.domain.FlashlightMapper$map$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX WARN: Type inference failed for: r0v6, types: [com.android.systemui.qs.tiles.impl.flashlight.domain.FlashlightMapper$map$1$1, kotlin.jvm.internal.Lambda] */
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                QSTileState.Builder builder = (QSTileState.Builder) obj2;
                FlashlightTileModel flashlightTileModel2 = FlashlightTileModel.this;
                Integer valueOf = ((flashlightTileModel2 instanceof FlashlightTileModel.FlashlightAvailable) && ((FlashlightTileModel.FlashlightAvailable) flashlightTileModel2).isEnabled) ? Integer.valueOf(R.drawable.qs_flashlight_icon_on) : Integer.valueOf(R.drawable.qs_flashlight_icon_off);
                builder.iconRes = valueOf;
                final Icon.Loaded loaded = new Icon.Loaded(this.resources.getDrawable(valueOf.intValue(), this.theme), null);
                builder.icon = new Function0() { // from class: com.android.systemui.qs.tiles.impl.flashlight.domain.FlashlightMapper$map$1.1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Icon.Loaded.this;
                    }
                };
                builder.contentDescription = builder.label;
                FlashlightTileModel flashlightTileModel3 = FlashlightTileModel.this;
                if (flashlightTileModel3 instanceof FlashlightTileModel.FlashlightTemporarilyUnavailable) {
                    builder.activationState = QSTileState.ActivationState.UNAVAILABLE;
                    String string = this.resources.getString(R.string.quick_settings_flashlight_camera_in_use);
                    builder.secondaryLabel = string;
                    builder.stateDescription = string;
                    builder.supportedActions = EmptySet.INSTANCE;
                } else {
                    if ((flashlightTileModel3 instanceof FlashlightTileModel.FlashlightAvailable) && ((FlashlightTileModel.FlashlightAvailable) flashlightTileModel3).isEnabled) {
                        builder.activationState = QSTileState.ActivationState.ACTIVE;
                        builder.secondaryLabel = this.resources.getStringArray(R.array.tile_states_flashlight)[2];
                    } else {
                        builder.activationState = QSTileState.ActivationState.INACTIVE;
                        builder.secondaryLabel = this.resources.getStringArray(R.array.tile_states_flashlight)[1];
                    }
                    builder.supportedActions = Collections.singleton(QSTileState.UserAction.CLICK);
                }
                return Unit.INSTANCE;
            }
        });
    }
}

package com.android.systemui.qs.tiles.impl.night.ui;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import com.android.systemui.common.shared.model.Icon;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.android.systemui.qs.tiles.base.interactor.QSTileDataToStateMapper;
import com.android.systemui.qs.tiles.base.logging.QSTileLogger;
import com.android.systemui.qs.tiles.impl.night.domain.model.NightDisplayTileModel;
import com.android.systemui.qs.tiles.viewmodel.QSTileConfig;
import com.android.systemui.qs.tiles.viewmodel.QSTileState;
import com.android.wm.shell.R;
import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NightDisplayTileMapper implements QSTileDataToStateMapper {
    public static final DateTimeFormatter formatter12Hour = DateTimeFormatter.ofPattern("hh:mm a");
    public static final DateTimeFormatter formatter24Hour = DateTimeFormatter.ofPattern("HH:mm");
    public static final TileSpec spec = TileSpec.Companion.create("night");
    public final QSTileLogger logger;
    public final Resources resources;
    public final Resources.Theme theme;

    @Override // com.android.systemui.qs.tiles.base.interactor.QSTileDataToStateMapper
    public final QSTileState map(QSTileConfig qSTileConfig, Object obj) {
        final NightDisplayTileModel nightDisplayTileModel = (NightDisplayTileModel) obj;
        return QSTileState.Companion.build(this.resources, this.theme, qSTileConfig.uiConfig, new Function1() { // from class: com.android.systemui.qs.tiles.impl.night.ui.NightDisplayTileMapper$map$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX WARN: Type inference failed for: r1v5, types: [com.android.systemui.qs.tiles.impl.night.ui.NightDisplayTileMapper$map$1$1, kotlin.jvm.internal.Lambda] */
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                LocalTime localTime;
                int i;
                QSTileState.Builder builder = (QSTileState.Builder) obj2;
                builder.label = NightDisplayTileMapper.this.resources.getString(R.string.quick_settings_night_display_label);
                builder.supportedActions = SetsKt.setOf(QSTileState.UserAction.CLICK, QSTileState.UserAction.LONG_CLICK);
                builder.sideViewIcon = QSTileState.SideViewIcon.None.INSTANCE;
                if (nightDisplayTileModel.isActivated()) {
                    builder.activationState = QSTileState.ActivationState.ACTIVE;
                    builder.iconRes = Integer.valueOf(R.drawable.qs_nightlight_icon_on);
                } else {
                    builder.activationState = QSTileState.ActivationState.INACTIVE;
                    builder.iconRes = Integer.valueOf(R.drawable.qs_nightlight_icon_off);
                }
                Resources resources = NightDisplayTileMapper.this.resources;
                Integer num = builder.iconRes;
                Intrinsics.checkNotNull(num);
                Drawable drawable = resources.getDrawable(num.intValue(), NightDisplayTileMapper.this.theme);
                String str = null;
                final Icon.Loaded loaded = new Icon.Loaded(drawable, null);
                builder.icon = new Function0() { // from class: com.android.systemui.qs.tiles.impl.night.ui.NightDisplayTileMapper$map$1.1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Icon.Loaded.this;
                    }
                };
                NightDisplayTileMapper nightDisplayTileMapper = NightDisplayTileMapper.this;
                NightDisplayTileModel nightDisplayTileModel2 = nightDisplayTileModel;
                Resources resources2 = nightDisplayTileMapper.resources;
                if (nightDisplayTileModel2 instanceof NightDisplayTileModel.AutoModeTwilight) {
                    if (((NightDisplayTileModel.AutoModeTwilight) nightDisplayTileModel2).isLocationEnabled) {
                        str = resources2.getString(((NightDisplayTileModel.AutoModeTwilight) nightDisplayTileModel2).isActivated ? R.string.quick_settings_night_secondary_label_until_sunrise : R.string.quick_settings_night_secondary_label_on_at_sunset);
                    }
                } else if (nightDisplayTileModel2 instanceof NightDisplayTileModel.AutoModeOff) {
                    str = resources2.getStringArray(R.array.tile_states_night)[((NightDisplayTileModel.AutoModeOff) nightDisplayTileModel2).isActivated ? (char) 2 : (char) 1];
                } else {
                    if (!(nightDisplayTileModel2 instanceof NightDisplayTileModel.AutoModeCustom)) {
                        throw new NoWhenBranchMatchedException();
                    }
                    if (((NightDisplayTileModel.AutoModeCustom) nightDisplayTileModel2).isActivated) {
                        localTime = ((NightDisplayTileModel.AutoModeCustom) nightDisplayTileModel2).endTime;
                        if (localTime != null) {
                            i = R.string.quick_settings_secondary_label_until;
                        }
                    } else {
                        localTime = ((NightDisplayTileModel.AutoModeCustom) nightDisplayTileModel2).startTime;
                        if (localTime != null) {
                            i = R.string.quick_settings_night_secondary_label_on_at;
                        }
                    }
                    try {
                        str = resources2.getString(i, (((NightDisplayTileModel.AutoModeCustom) nightDisplayTileModel2).is24HourFormat ? NightDisplayTileMapper.formatter24Hour : NightDisplayTileMapper.formatter12Hour).format(localTime));
                    } catch (DateTimeException e) {
                        nightDisplayTileMapper.logger.logWarning(NightDisplayTileMapper.spec, String.valueOf(e.getMessage()));
                    }
                }
                builder.secondaryLabel = str;
                builder.contentDescription = TextUtils.isEmpty(str) ? builder.label : TextUtils.concat(builder.label, ", ", builder.secondaryLabel);
                return Unit.INSTANCE;
            }
        });
    }
}

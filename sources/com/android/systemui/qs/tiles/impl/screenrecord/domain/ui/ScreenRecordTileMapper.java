package com.android.systemui.qs.tiles.impl.screenrecord.domain.ui;

import android.content.res.Resources;
import android.text.TextUtils;
import com.android.systemui.common.shared.model.Icon;
import com.android.systemui.qs.tiles.base.interactor.QSTileDataToStateMapper;
import com.android.systemui.qs.tiles.viewmodel.QSTileConfig;
import com.android.systemui.qs.tiles.viewmodel.QSTileState;
import com.android.systemui.screenrecord.data.model.ScreenRecordModel;
import com.android.wm.shell.R;
import java.util.Arrays;
import java.util.Collections;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ScreenRecordTileMapper implements QSTileDataToStateMapper {
    public final Resources resources;
    public final Resources.Theme theme;

    public ScreenRecordTileMapper(Resources resources, Resources.Theme theme) {
        this.resources = resources;
        this.theme = theme;
    }

    @Override // com.android.systemui.qs.tiles.base.interactor.QSTileDataToStateMapper
    public final QSTileState map(QSTileConfig qSTileConfig, Object obj) {
        final ScreenRecordModel screenRecordModel = (ScreenRecordModel) obj;
        return QSTileState.Companion.build(this.resources, this.theme, qSTileConfig.uiConfig, new Function1() { // from class: com.android.systemui.qs.tiles.impl.screenrecord.domain.ui.ScreenRecordTileMapper$map$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX WARN: Type inference failed for: r0v10, types: [com.android.systemui.qs.tiles.impl.screenrecord.domain.ui.ScreenRecordTileMapper$map$1$3, kotlin.jvm.internal.Lambda] */
            /* JADX WARN: Type inference failed for: r1v14, types: [com.android.systemui.qs.tiles.impl.screenrecord.domain.ui.ScreenRecordTileMapper$map$1$1, kotlin.jvm.internal.Lambda] */
            /* JADX WARN: Type inference failed for: r1v8, types: [com.android.systemui.qs.tiles.impl.screenrecord.domain.ui.ScreenRecordTileMapper$map$1$2, kotlin.jvm.internal.Lambda] */
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                QSTileState.Builder builder = (QSTileState.Builder) obj2;
                builder.label = ScreenRecordTileMapper.this.resources.getString(R.string.quick_settings_screen_record_label);
                builder.supportedActions = Collections.singleton(QSTileState.UserAction.CLICK);
                ScreenRecordModel screenRecordModel2 = screenRecordModel;
                boolean z = screenRecordModel2 instanceof ScreenRecordModel.Recording;
                QSTileState.SideViewIcon.None none = QSTileState.SideViewIcon.None.INSTANCE;
                if (z) {
                    builder.activationState = QSTileState.ActivationState.ACTIVE;
                    builder.iconRes = Integer.valueOf(R.drawable.qs_screen_record_icon_on);
                    ScreenRecordTileMapper screenRecordTileMapper = ScreenRecordTileMapper.this;
                    final Icon.Loaded loaded = new Icon.Loaded(screenRecordTileMapper.resources.getDrawable(R.drawable.qs_screen_record_icon_on, screenRecordTileMapper.theme), null);
                    builder.icon = new Function0() { // from class: com.android.systemui.qs.tiles.impl.screenrecord.domain.ui.ScreenRecordTileMapper$map$1.1
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return Icon.Loaded.this;
                        }
                    };
                    builder.sideViewIcon = none;
                    builder.secondaryLabel = ScreenRecordTileMapper.this.resources.getString(R.string.quick_settings_screen_record_stop);
                } else if (screenRecordModel2 instanceof ScreenRecordModel.Starting) {
                    builder.activationState = QSTileState.ActivationState.ACTIVE;
                    builder.iconRes = Integer.valueOf(R.drawable.qs_screen_record_icon_on);
                    ScreenRecordTileMapper screenRecordTileMapper2 = ScreenRecordTileMapper.this;
                    final Icon.Loaded loaded2 = new Icon.Loaded(screenRecordTileMapper2.resources.getDrawable(R.drawable.qs_screen_record_icon_on, screenRecordTileMapper2.theme), null);
                    builder.icon = new Function0() { // from class: com.android.systemui.qs.tiles.impl.screenrecord.domain.ui.ScreenRecordTileMapper$map$1.2
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return Icon.Loaded.this;
                        }
                    };
                    long j = ((ScreenRecordModel.Starting) screenRecordModel).countdownSeconds;
                    builder.sideViewIcon = none;
                    builder.secondaryLabel = String.format("%d...", Arrays.copyOf(new Object[]{Long.valueOf(j)}, 1));
                } else if (screenRecordModel2 instanceof ScreenRecordModel.DoingNothing) {
                    builder.activationState = QSTileState.ActivationState.INACTIVE;
                    builder.iconRes = Integer.valueOf(R.drawable.qs_screen_record_icon_off);
                    ScreenRecordTileMapper screenRecordTileMapper3 = ScreenRecordTileMapper.this;
                    final Icon.Loaded loaded3 = new Icon.Loaded(screenRecordTileMapper3.resources.getDrawable(R.drawable.qs_screen_record_icon_off, screenRecordTileMapper3.theme), null);
                    builder.icon = new Function0() { // from class: com.android.systemui.qs.tiles.impl.screenrecord.domain.ui.ScreenRecordTileMapper$map$1.3
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return Icon.Loaded.this;
                        }
                    };
                    builder.sideViewIcon = QSTileState.SideViewIcon.Chevron.INSTANCE;
                    builder.secondaryLabel = ScreenRecordTileMapper.this.resources.getString(R.string.quick_settings_screen_record_start);
                }
                builder.contentDescription = TextUtils.isEmpty(builder.secondaryLabel) ? builder.label : TextUtils.concat(builder.label, ", ", builder.secondaryLabel);
                return Unit.INSTANCE;
            }
        });
    }
}

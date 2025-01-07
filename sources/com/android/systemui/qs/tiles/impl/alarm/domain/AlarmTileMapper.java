package com.android.systemui.qs.tiles.impl.alarm.domain;

import android.content.res.Resources;
import com.android.systemui.common.shared.model.Icon;
import com.android.systemui.qs.tiles.base.interactor.QSTileDataToStateMapper;
import com.android.systemui.qs.tiles.impl.alarm.domain.model.AlarmTileModel;
import com.android.systemui.qs.tiles.viewmodel.QSTileConfig;
import com.android.systemui.qs.tiles.viewmodel.QSTileState;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.util.time.SystemClockImpl;
import com.android.wm.shell.R;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.TimeZone;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AlarmTileMapper implements QSTileDataToStateMapper {
    public static final DateTimeFormatter formatter12Hour = DateTimeFormatter.ofPattern("E hh:mm a");
    public static final DateTimeFormatter formatter24Hour = DateTimeFormatter.ofPattern("E HH:mm");
    public static final DateTimeFormatter formatterDateOnly = DateTimeFormatter.ofPattern("E MMM d");
    public final SystemClock clock;
    public final Resources resources;
    public final Resources.Theme theme;

    public AlarmTileMapper(Resources resources, Resources.Theme theme, SystemClock systemClock) {
        this.resources = resources;
        this.theme = theme;
        this.clock = systemClock;
    }

    @Override // com.android.systemui.qs.tiles.base.interactor.QSTileDataToStateMapper
    public final QSTileState map(QSTileConfig qSTileConfig, Object obj) {
        final AlarmTileModel alarmTileModel = (AlarmTileModel) obj;
        return QSTileState.Companion.build(this.resources, this.theme, qSTileConfig.uiConfig, new Function1() { // from class: com.android.systemui.qs.tiles.impl.alarm.domain.AlarmTileMapper$map$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX WARN: Type inference failed for: r0v8, types: [com.android.systemui.qs.tiles.impl.alarm.domain.AlarmTileMapper$map$1$1, kotlin.jvm.internal.Lambda] */
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                final QSTileState.Builder builder = (QSTileState.Builder) obj2;
                AlarmTileModel alarmTileModel2 = AlarmTileModel.this;
                if (alarmTileModel2 instanceof AlarmTileModel.NextAlarmSet) {
                    builder.activationState = QSTileState.ActivationState.ACTIVE;
                    LocalDateTime ofInstant = LocalDateTime.ofInstant(Instant.ofEpochMilli(((AlarmTileModel.NextAlarmSet) alarmTileModel2).alarmClockInfo.getTriggerTime()), TimeZone.getDefault().toZoneId());
                    ((SystemClockImpl) this.clock).getClass();
                    if (ofInstant.compareTo((ChronoLocalDateTime<?>) LocalDateTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis()), TimeZone.getDefault().toZoneId()).plusWeeks(1L).withSecond(0).withNano(0)) >= 0) {
                        builder.secondaryLabel = AlarmTileMapper.formatterDateOnly.format(ofInstant);
                    } else {
                        builder.secondaryLabel = ((AlarmTileModel.NextAlarmSet) AlarmTileModel.this).is24HourFormat ? AlarmTileMapper.formatter24Hour.format(ofInstant) : AlarmTileMapper.formatter12Hour.format(ofInstant);
                    }
                } else if (alarmTileModel2 instanceof AlarmTileModel.NoAlarmSet) {
                    builder.activationState = QSTileState.ActivationState.INACTIVE;
                    builder.secondaryLabel = this.resources.getString(R.string.qs_alarm_tile_no_alarm);
                }
                builder.iconRes = Integer.valueOf(R.drawable.ic_alarm);
                final AlarmTileMapper alarmTileMapper = this;
                builder.icon = new Function0() { // from class: com.android.systemui.qs.tiles.impl.alarm.domain.AlarmTileMapper$map$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Resources resources = AlarmTileMapper.this.resources;
                        Integer num = builder.iconRes;
                        Intrinsics.checkNotNull(num);
                        return new Icon.Loaded(resources.getDrawable(num.intValue(), AlarmTileMapper.this.theme), null);
                    }
                };
                builder.sideViewIcon = QSTileState.SideViewIcon.Chevron.INSTANCE;
                builder.contentDescription = builder.label;
                builder.supportedActions = Collections.singleton(QSTileState.UserAction.CLICK);
                return Unit.INSTANCE;
            }
        });
    }
}

package com.android.systemui.qs.tiles.impl.uimodenight.domain;

import android.content.res.Resources;
import com.android.systemui.qs.tiles.base.interactor.QSTileDataToStateMapper;
import com.android.systemui.qs.tiles.impl.uimodenight.domain.model.UiModeNightTileModel;
import com.android.systemui.qs.tiles.viewmodel.QSTileConfig;
import com.android.systemui.qs.tiles.viewmodel.QSTileState;
import java.time.format.DateTimeFormatter;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UiModeNightTileMapper implements QSTileDataToStateMapper {
    public static final DateTimeFormatter formatter12Hour = DateTimeFormatter.ofPattern("hh:mm a");
    public static final DateTimeFormatter formatter24Hour = DateTimeFormatter.ofPattern("HH:mm");
    public final Resources resources;
    public final Resources.Theme theme;

    public UiModeNightTileMapper(Resources resources, Resources.Theme theme) {
        this.resources = resources;
        this.theme = theme;
    }

    @Override // com.android.systemui.qs.tiles.base.interactor.QSTileDataToStateMapper
    public final QSTileState map(QSTileConfig qSTileConfig, Object obj) {
        final UiModeNightTileModel uiModeNightTileModel = (UiModeNightTileModel) obj;
        return QSTileState.Companion.build(this.resources, this.theme, qSTileConfig.uiConfig, new Function1() { // from class: com.android.systemui.qs.tiles.impl.uimodenight.domain.UiModeNightTileMapper$map$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX WARN: Removed duplicated region for block: B:10:0x00b2  */
            /* JADX WARN: Removed duplicated region for block: B:15:0x00f3  */
            /* JADX WARN: Removed duplicated region for block: B:18:0x0126  */
            /* JADX WARN: Removed duplicated region for block: B:22:0x012d  */
            /* JADX WARN: Removed duplicated region for block: B:23:0x00fb  */
            /* JADX WARN: Removed duplicated region for block: B:24:0x00c5  */
            /* JADX WARN: Removed duplicated region for block: B:35:0x0095  */
            /* JADX WARN: Removed duplicated region for block: B:7:0x0092  */
            /* JADX WARN: Type inference failed for: r8v4, types: [com.android.systemui.qs.tiles.impl.uimodenight.domain.UiModeNightTileMapper$map$1$1$1, kotlin.jvm.internal.Lambda] */
            @Override // kotlin.jvm.functions.Function1
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invoke(java.lang.Object r9) {
                /*
                    Method dump skipped, instructions count: 318
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.tiles.impl.uimodenight.domain.UiModeNightTileMapper$map$1$1.invoke(java.lang.Object):java.lang.Object");
            }
        });
    }
}

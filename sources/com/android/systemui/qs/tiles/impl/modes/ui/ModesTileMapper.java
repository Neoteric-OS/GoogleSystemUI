package com.android.systemui.qs.tiles.impl.modes.ui;

import android.R;
import android.content.res.Resources;
import android.icu.text.MessageFormat;
import android.widget.Button;
import com.android.systemui.qs.tiles.base.interactor.QSTileDataToStateMapper;
import com.android.systemui.qs.tiles.impl.modes.domain.model.ModesTileModel;
import com.android.systemui.qs.tiles.viewmodel.QSTileConfig;
import com.android.systemui.qs.tiles.viewmodel.QSTileState;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Reflection;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ModesTileMapper implements QSTileDataToStateMapper {
    public final Resources resources;
    public final Resources.Theme theme;

    public ModesTileMapper(Resources resources, Resources.Theme theme) {
        this.resources = resources;
        this.theme = theme;
    }

    @Override // com.android.systemui.qs.tiles.base.interactor.QSTileDataToStateMapper
    public final QSTileState map(QSTileConfig qSTileConfig, final ModesTileModel modesTileModel) {
        return QSTileState.Companion.build(this.resources, this.theme, qSTileConfig.uiConfig, new Function1() { // from class: com.android.systemui.qs.tiles.impl.modes.ui.ModesTileMapper$map$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX WARN: Type inference failed for: r0v3, types: [com.android.systemui.qs.tiles.impl.modes.ui.ModesTileMapper$map$1$1, kotlin.jvm.internal.Lambda] */
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                QSTileState.Builder builder = (QSTileState.Builder) obj;
                ModesTileModel.this.getClass();
                builder.iconRes = Integer.valueOf(R.drawable.jog_dial_bg);
                final ModesTileModel modesTileModel2 = ModesTileModel.this;
                builder.icon = new Function0() { // from class: com.android.systemui.qs.tiles.impl.modes.ui.ModesTileMapper$map$1.1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return ModesTileModel.this.icon;
                    }
                };
                builder.activationState = modesTileModel2.isActivated ? QSTileState.ActivationState.ACTIVE : QSTileState.ActivationState.INACTIVE;
                MessageFormat messageFormat = new MessageFormat(this.resources.getString(com.android.wm.shell.R.string.zen_mode_active_modes), Locale.getDefault());
                int size = ((ArrayList) modesTileModel2.activeModes).size();
                HashMap hashMap = new HashMap();
                hashMap.put("count", Integer.valueOf(size));
                if (size >= 1) {
                    hashMap.put("mode", ((ArrayList) modesTileModel2.activeModes).get(0));
                }
                String format = messageFormat.format(hashMap);
                builder.secondaryLabel = format;
                builder.contentDescription = ((Object) builder.label) + ". " + ((Object) format);
                builder.supportedActions = SetsKt.setOf(QSTileState.UserAction.CLICK, QSTileState.UserAction.LONG_CLICK);
                builder.sideViewIcon = QSTileState.SideViewIcon.Chevron.INSTANCE;
                builder.expandedAccessibilityClass = Reflection.getOrCreateKotlinClass(Button.class);
                return Unit.INSTANCE;
            }
        });
    }
}

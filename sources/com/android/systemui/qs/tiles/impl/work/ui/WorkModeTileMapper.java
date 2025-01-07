package com.android.systemui.qs.tiles.impl.work.ui;

import android.app.admin.DevicePolicyManager;
import android.content.res.Resources;
import com.android.systemui.common.shared.model.Icon;
import com.android.systemui.qs.tiles.base.interactor.QSTileDataToStateMapper;
import com.android.systemui.qs.tiles.impl.work.domain.model.WorkModeTileModel;
import com.android.systemui.qs.tiles.viewmodel.QSTileConfig;
import com.android.systemui.qs.tiles.viewmodel.QSTileState;
import com.android.wm.shell.R;
import java.util.function.Supplier;
import kotlin.Unit;
import kotlin.collections.EmptySet;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class WorkModeTileMapper implements QSTileDataToStateMapper {
    public final DevicePolicyManager devicePolicyManager;
    public final Resources resources;
    public final Resources.Theme theme;

    public WorkModeTileMapper(Resources resources, Resources.Theme theme, DevicePolicyManager devicePolicyManager) {
        this.resources = resources;
        this.theme = theme;
        this.devicePolicyManager = devicePolicyManager;
    }

    @Override // com.android.systemui.qs.tiles.base.interactor.QSTileDataToStateMapper
    public final QSTileState map(QSTileConfig qSTileConfig, Object obj) {
        final WorkModeTileModel workModeTileModel = (WorkModeTileModel) obj;
        return QSTileState.Companion.build(this.resources, this.theme, qSTileConfig.uiConfig, new Function1() { // from class: com.android.systemui.qs.tiles.impl.work.ui.WorkModeTileMapper$map$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX WARN: Type inference failed for: r0v5, types: [com.android.systemui.qs.tiles.impl.work.ui.WorkModeTileMapper$map$1$1, kotlin.jvm.internal.Lambda] */
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                final QSTileState.Builder builder = (QSTileState.Builder) obj2;
                final WorkModeTileMapper workModeTileMapper = WorkModeTileMapper.this;
                String string = workModeTileMapper.devicePolicyManager.getResources().getString("SystemUi.QS_WORK_PROFILE_LABEL", new Supplier() { // from class: com.android.systemui.qs.tiles.impl.work.ui.WorkModeTileMapper$getTileLabel$1
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return WorkModeTileMapper.this.resources.getString(R.string.quick_settings_work_mode_label);
                    }
                });
                Intrinsics.checkNotNull(string);
                builder.label = string;
                builder.contentDescription = string;
                builder.iconRes = Integer.valueOf(android.R.drawable.statusbar_background);
                final WorkModeTileMapper workModeTileMapper2 = WorkModeTileMapper.this;
                builder.icon = new Function0() { // from class: com.android.systemui.qs.tiles.impl.work.ui.WorkModeTileMapper$map$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Resources resources = WorkModeTileMapper.this.resources;
                        Integer num = builder.iconRes;
                        Intrinsics.checkNotNull(num);
                        return new Icon.Loaded(resources.getDrawable(num.intValue(), WorkModeTileMapper.this.theme), null);
                    }
                };
                WorkModeTileModel workModeTileModel2 = workModeTileModel;
                if (workModeTileModel2 instanceof WorkModeTileModel.HasActiveProfile) {
                    if (((WorkModeTileModel.HasActiveProfile) workModeTileModel2).isEnabled) {
                        builder.activationState = QSTileState.ActivationState.ACTIVE;
                        builder.secondaryLabel = "";
                    } else {
                        builder.activationState = QSTileState.ActivationState.INACTIVE;
                        builder.secondaryLabel = workModeTileMapper2.resources.getString(R.string.quick_settings_work_mode_paused_state);
                    }
                    builder.supportedActions = SetsKt.setOf(QSTileState.UserAction.CLICK, QSTileState.UserAction.LONG_CLICK);
                } else if (workModeTileModel2 instanceof WorkModeTileModel.NoActiveProfile) {
                    builder.activationState = QSTileState.ActivationState.UNAVAILABLE;
                    builder.secondaryLabel = workModeTileMapper2.resources.getStringArray(R.array.tile_states_work)[0];
                    builder.supportedActions = EmptySet.INSTANCE;
                }
                builder.sideViewIcon = QSTileState.SideViewIcon.None.INSTANCE;
                return Unit.INSTANCE;
            }
        });
    }
}

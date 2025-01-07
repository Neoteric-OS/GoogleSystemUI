package com.android.systemui.qs.tiles.impl.internet.domain;

import android.content.Context;
import android.content.res.Resources;
import android.widget.Switch;
import com.android.systemui.common.shared.model.ContentDescription;
import com.android.systemui.common.shared.model.Icon;
import com.android.systemui.common.shared.model.Text;
import com.android.systemui.qs.tiles.base.interactor.QSTileDataToStateMapper;
import com.android.systemui.qs.tiles.impl.internet.domain.model.InternetTileModel;
import com.android.systemui.qs.tiles.viewmodel.QSTileConfig;
import com.android.systemui.qs.tiles.viewmodel.QSTileState;
import com.android.wm.shell.R;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class InternetTileMapper implements QSTileDataToStateMapper {
    public final Context context;
    public final Resources resources;
    public final Resources.Theme theme;

    public InternetTileMapper(Resources resources, Resources.Theme theme, Context context) {
        this.resources = resources;
        this.theme = theme;
        this.context = context;
    }

    @Override // com.android.systemui.qs.tiles.base.interactor.QSTileDataToStateMapper
    public final QSTileState map(QSTileConfig qSTileConfig, Object obj) {
        final InternetTileModel internetTileModel = (InternetTileModel) obj;
        return QSTileState.Companion.build(this.resources, this.theme, qSTileConfig.uiConfig, new Function1() { // from class: com.android.systemui.qs.tiles.impl.internet.domain.InternetTileMapper$map$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX WARN: Type inference failed for: r0v34, types: [com.android.systemui.qs.tiles.impl.internet.domain.InternetTileMapper$map$1$1, kotlin.jvm.internal.Lambda] */
            /* JADX WARN: Type inference failed for: r1v2, types: [com.android.systemui.qs.tiles.impl.internet.domain.InternetTileMapper$map$1$2, kotlin.jvm.internal.Lambda] */
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                String string;
                String string2;
                String string3;
                QSTileState.Builder builder = (QSTileState.Builder) obj2;
                builder.label = InternetTileMapper.this.resources.getString(R.string.quick_settings_internet_label);
                builder.expandedAccessibilityClass = Reflection.getOrCreateKotlinClass(Switch.class);
                if (internetTileModel.getSecondaryLabel() != null) {
                    Text secondaryLabel = internetTileModel.getSecondaryLabel();
                    Context context = InternetTileMapper.this.context;
                    if (secondaryLabel == null) {
                        string3 = null;
                    } else if (secondaryLabel instanceof Text.Loaded) {
                        string3 = ((Text.Loaded) secondaryLabel).text;
                    } else {
                        if (!(secondaryLabel instanceof Text.Resource)) {
                            throw new NoWhenBranchMatchedException();
                        }
                        string3 = context.getString(((Text.Resource) secondaryLabel).res);
                    }
                    builder.secondaryLabel = string3;
                } else {
                    builder.secondaryLabel = internetTileModel.getSecondaryTitle();
                }
                ContentDescription stateDescription = internetTileModel.getStateDescription();
                Context context2 = InternetTileMapper.this.context;
                if (stateDescription == null) {
                    string = null;
                } else if (stateDescription instanceof ContentDescription.Loaded) {
                    string = ((ContentDescription.Loaded) stateDescription).description;
                } else {
                    if (!(stateDescription instanceof ContentDescription.Resource)) {
                        throw new NoWhenBranchMatchedException();
                    }
                    string = context2.getString(((ContentDescription.Resource) stateDescription).res);
                }
                builder.stateDescription = string;
                ContentDescription contentDescription = internetTileModel.getContentDescription();
                Context context3 = InternetTileMapper.this.context;
                if (contentDescription == null) {
                    string2 = null;
                } else if (contentDescription instanceof ContentDescription.Loaded) {
                    string2 = ((ContentDescription.Loaded) contentDescription).description;
                } else {
                    if (!(contentDescription instanceof ContentDescription.Resource)) {
                        throw new NoWhenBranchMatchedException();
                    }
                    string2 = context3.getString(((ContentDescription.Resource) contentDescription).res);
                }
                builder.contentDescription = string2;
                builder.iconRes = internetTileModel.getIconId();
                if (internetTileModel.getIcon() != null) {
                    final InternetTileModel internetTileModel2 = internetTileModel;
                    builder.icon = new Function0() { // from class: com.android.systemui.qs.tiles.impl.internet.domain.InternetTileMapper$map$1.1
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return InternetTileModel.this.getIcon();
                        }
                    };
                } else if (internetTileModel.getIconId() != null) {
                    Resources resources = InternetTileMapper.this.resources;
                    Integer iconId = internetTileModel.getIconId();
                    Intrinsics.checkNotNull(iconId);
                    final Icon.Loaded loaded = new Icon.Loaded(resources.getDrawable(iconId.intValue(), InternetTileMapper.this.theme), null);
                    builder.icon = new Function0() { // from class: com.android.systemui.qs.tiles.impl.internet.domain.InternetTileMapper$map$1.2
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return Icon.Loaded.this;
                        }
                    };
                }
                builder.sideViewIcon = QSTileState.SideViewIcon.Chevron.INSTANCE;
                builder.activationState = internetTileModel instanceof InternetTileModel.Active ? QSTileState.ActivationState.ACTIVE : QSTileState.ActivationState.INACTIVE;
                builder.supportedActions = SetsKt.setOf(QSTileState.UserAction.CLICK, QSTileState.UserAction.TOGGLE_CLICK, QSTileState.UserAction.LONG_CLICK);
                return Unit.INSTANCE;
            }
        });
    }
}

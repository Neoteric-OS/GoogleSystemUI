package com.android.systemui.qs.tiles.impl.custom.domain;

import android.app.IUriGrantsManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.UserHandle;
import android.widget.Button;
import com.android.systemui.common.shared.model.Icon;
import com.android.systemui.qs.tiles.base.interactor.QSTileDataToStateMapper;
import com.android.systemui.qs.tiles.impl.custom.domain.CustomTileMapper;
import com.android.systemui.qs.tiles.impl.custom.domain.entity.CustomTileDataModel;
import com.android.systemui.qs.tiles.viewmodel.QSTileConfig;
import com.android.systemui.qs.tiles.viewmodel.QSTileState;
import java.util.Collections;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CustomTileMapper implements QSTileDataToStateMapper {
    public final Context context;
    public final IUriGrantsManager uriGrantsManager;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class IconResult {
        public final boolean failedToLoad;
        public final Lambda iconProvider;

        /* JADX WARN: Multi-variable type inference failed */
        public IconResult(Function0 function0, boolean z) {
            this.iconProvider = (Lambda) function0;
            this.failedToLoad = z;
        }
    }

    public CustomTileMapper(Context context, IUriGrantsManager iUriGrantsManager) {
        this.context = context;
        this.uriGrantsManager = iUriGrantsManager;
    }

    /* JADX WARN: Type inference failed for: r7v3, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
    @Override // com.android.systemui.qs.tiles.base.interactor.QSTileDataToStateMapper
    public final QSTileState map(QSTileConfig qSTileConfig, Object obj) {
        Context context;
        final IconResult iconResult;
        Drawable drawable;
        final CustomTileDataModel customTileDataModel = (CustomTileDataModel) obj;
        final Drawable drawable2 = null;
        boolean z = false;
        try {
            context = this.context.createContextAsUser(new UserHandle(customTileDataModel.user.getIdentifier()), 0);
        } catch (IllegalStateException unused) {
            context = null;
        }
        if (context != null) {
            Icon icon = customTileDataModel.tile.getIcon();
            String packageName = customTileDataModel.componentName.getPackageName();
            Icon icon2 = customTileDataModel.defaultTileIcon;
            if (icon != null) {
                try {
                    drawable = icon.loadDrawableCheckingUriGrant(context, this.uriGrantsManager, 0, packageName);
                } catch (Exception unused2) {
                    drawable = null;
                    z = true;
                }
            } else {
                drawable = null;
            }
            if (drawable != null) {
                drawable2 = drawable;
            } else if (icon2 != null) {
                drawable2 = icon2.loadDrawable(context);
            }
            iconResult = new IconResult(new Function0() { // from class: com.android.systemui.qs.tiles.impl.custom.domain.CustomTileMapper$getIconProvider$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Drawable.ConstantState constantState;
                    Drawable newDrawable;
                    Drawable drawable3 = drawable2;
                    if (drawable3 == null || (constantState = drawable3.getConstantState()) == null || (newDrawable = constantState.newDrawable()) == null) {
                        return null;
                    }
                    return new Icon.Loaded(newDrawable, null);
                }
            }, z);
        } else {
            iconResult = new IconResult(new Function0() { // from class: com.android.systemui.qs.tiles.impl.custom.domain.CustomTileMapper$map$iconResult$1
                @Override // kotlin.jvm.functions.Function0
                public final /* bridge */ /* synthetic */ Object invoke() {
                    return null;
                }
            }, true);
        }
        return QSTileState.Companion.build(iconResult.iconProvider, customTileDataModel.tile.getLabel(), new Function1(customTileDataModel, iconResult) { // from class: com.android.systemui.qs.tiles.impl.custom.domain.CustomTileMapper$map$1
            final /* synthetic */ CustomTileDataModel $data;
            final /* synthetic */ CustomTileMapper.IconResult $iconResult;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
                this.$iconResult = iconResult;
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                QSTileState.ActivationState activationState;
                QSTileState.Builder builder = (QSTileState.Builder) obj2;
                int state = this.$data.tile.getState();
                this.$data.getClass();
                CustomTileMapper.IconResult iconResult2 = this.$iconResult;
                builder.icon = iconResult2.iconProvider;
                if (iconResult2.failedToLoad) {
                    activationState = QSTileState.ActivationState.UNAVAILABLE;
                } else {
                    QSTileState.ActivationState.Companion.getClass();
                    activationState = state != 1 ? state != 2 ? QSTileState.ActivationState.UNAVAILABLE : QSTileState.ActivationState.ACTIVE : QSTileState.ActivationState.INACTIVE;
                }
                builder.activationState = activationState;
                CharSequence subtitle = this.$data.tile.getSubtitle();
                if (subtitle != null && subtitle.length() != 0) {
                    builder.secondaryLabel = this.$data.tile.getSubtitle();
                }
                builder.contentDescription = this.$data.tile.getContentDescription();
                builder.stateDescription = this.$data.tile.getStateDescription();
                this.$data.getClass();
                builder.sideViewIcon = QSTileState.SideViewIcon.Chevron.INSTANCE;
                builder.supportedActions = state == 0 ? Collections.singleton(QSTileState.UserAction.LONG_CLICK) : SetsKt.setOf(QSTileState.UserAction.CLICK, QSTileState.UserAction.LONG_CLICK);
                this.$data.getClass();
                builder.expandedAccessibilityClass = Reflection.getOrCreateKotlinClass(Button.class);
                return Unit.INSTANCE;
            }
        });
    }
}

package com.android.systemui.qs.tiles.impl.qr.ui;

import android.content.res.Resources;
import com.android.systemui.common.shared.model.Icon;
import com.android.systemui.qs.tiles.base.interactor.QSTileDataToStateMapper;
import com.android.systemui.qs.tiles.impl.qr.domain.model.QRCodeScannerTileModel;
import com.android.systemui.qs.tiles.viewmodel.QSTileConfig;
import com.android.systemui.qs.tiles.viewmodel.QSTileState;
import com.android.wm.shell.R;
import java.util.Collections;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QRCodeScannerTileMapper implements QSTileDataToStateMapper {
    public final Resources resources;
    public final Resources.Theme theme;

    public QRCodeScannerTileMapper(Resources resources, Resources.Theme theme) {
        this.resources = resources;
        this.theme = theme;
    }

    @Override // com.android.systemui.qs.tiles.base.interactor.QSTileDataToStateMapper
    public final QSTileState map(QSTileConfig qSTileConfig, Object obj) {
        final QRCodeScannerTileModel qRCodeScannerTileModel = (QRCodeScannerTileModel) obj;
        return QSTileState.Companion.build(this.resources, this.theme, qSTileConfig.uiConfig, new Function1() { // from class: com.android.systemui.qs.tiles.impl.qr.ui.QRCodeScannerTileMapper$map$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX WARN: Type inference failed for: r0v5, types: [com.android.systemui.qs.tiles.impl.qr.ui.QRCodeScannerTileMapper$map$1$1, kotlin.jvm.internal.Lambda] */
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                final QSTileState.Builder builder = (QSTileState.Builder) obj2;
                String string = QRCodeScannerTileMapper.this.resources.getString(R.string.qr_code_scanner_title);
                builder.label = string;
                builder.contentDescription = string;
                builder.iconRes = Integer.valueOf(R.drawable.ic_qr_code_scanner);
                final QRCodeScannerTileMapper qRCodeScannerTileMapper = QRCodeScannerTileMapper.this;
                builder.icon = new Function0() { // from class: com.android.systemui.qs.tiles.impl.qr.ui.QRCodeScannerTileMapper$map$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Resources resources = QRCodeScannerTileMapper.this.resources;
                        Integer num = builder.iconRes;
                        Intrinsics.checkNotNull(num);
                        return new Icon.Loaded(resources.getDrawable(num.intValue(), QRCodeScannerTileMapper.this.theme), null);
                    }
                };
                builder.sideViewIcon = QSTileState.SideViewIcon.Chevron.INSTANCE;
                builder.supportedActions = Collections.singleton(QSTileState.UserAction.CLICK);
                QRCodeScannerTileModel qRCodeScannerTileModel2 = qRCodeScannerTileModel;
                if (qRCodeScannerTileModel2 instanceof QRCodeScannerTileModel.Available) {
                    builder.activationState = QSTileState.ActivationState.INACTIVE;
                    builder.secondaryLabel = null;
                } else if (qRCodeScannerTileModel2 instanceof QRCodeScannerTileModel.TemporarilyUnavailable) {
                    builder.activationState = QSTileState.ActivationState.UNAVAILABLE;
                    builder.secondaryLabel = QRCodeScannerTileMapper.this.resources.getString(R.string.qr_code_scanner_updating_secondary_label);
                }
                return Unit.INSTANCE;
            }
        });
    }
}

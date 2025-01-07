package com.android.systemui.qs.tiles.viewmodel;

import androidx.appsearch.platformstorage.converter.GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0;
import com.android.internal.util.Preconditions;
import com.android.systemui.qs.QsEventLoggerImpl;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.android.systemui.qs.shared.model.TileCategory;
import com.android.systemui.qs.tiles.viewmodel.QSTileUIConfig;
import java.util.Map;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QSTileConfigProviderImpl {
    public final Map configs;
    public final QsEventLoggerImpl qsEventLogger;

    public QSTileConfigProviderImpl(Map map, QsEventLoggerImpl qsEventLoggerImpl) {
        this.configs = map;
        this.qsEventLogger = qsEventLoggerImpl;
        for (Map.Entry entry : map.entrySet()) {
            String spec = ((QSTileConfig) entry.getValue()).tileSpec.getSpec();
            String str = (String) entry.getKey();
            Preconditions.checkArgument(Intrinsics.areEqual(spec, str), GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("A wrong config is injected keySpec=", str, " configSpec=", spec), new Object[0]);
        }
    }

    public final QSTileConfig getConfig(String str) {
        TileSpec create = TileSpec.Companion.create(str);
        if (create instanceof TileSpec.PlatformTileSpec) {
            QSTileConfig qSTileConfig = (QSTileConfig) this.configs.get(str);
            if (qSTileConfig != null) {
                return qSTileConfig;
            }
            throw new IllegalArgumentException("There is no config for spec=".concat(str));
        }
        if (create instanceof TileSpec.CustomTileSpec) {
            return new QSTileConfig(create, QSTileUIConfig.Empty.INSTANCE, this.qsEventLogger.sequence.newInstanceId(), TileCategory.PROVIDED_BY_APP, null, null, 112);
        }
        if (create instanceof TileSpec.Invalid) {
            throw new IllegalArgumentException("TileSpec.Invalid doesn't support configs");
        }
        throw new NoWhenBranchMatchedException();
    }
}

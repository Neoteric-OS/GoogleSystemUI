package com.android.systemui.qs.external;

import android.content.ComponentName;
import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import com.android.internal.logging.InstanceId;
import com.android.systemui.qs.QSHostAdapter;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TileServiceRequestController$requestTileAdd$dialogResponse$1 implements Consumer {
    public final /* synthetic */ Consumer $callback;
    public final /* synthetic */ ComponentName $componentName;
    public final /* synthetic */ InstanceId $instanceId;
    public final /* synthetic */ String $packageName;
    public final /* synthetic */ TileServiceRequestController this$0;

    public TileServiceRequestController$requestTileAdd$dialogResponse$1(TileServiceRequestController tileServiceRequestController, ComponentName componentName, String str, InstanceId instanceId, Consumer consumer) {
        this.this$0 = tileServiceRequestController;
        this.$componentName = componentName;
        this.$packageName = str;
        this.$instanceId = instanceId;
        this.$callback = consumer;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        TileRequestDialogEvent tileRequestDialogEvent;
        Integer num = (Integer) obj;
        if (num.intValue() == 2) {
            TileServiceRequestController tileServiceRequestController = this.this$0;
            ((QSHostAdapter) tileServiceRequestController.qsHost).addTile(this.$componentName, true);
        }
        TileServiceRequestController tileServiceRequestController2 = this.this$0;
        tileServiceRequestController2.dialogCanceller = null;
        int intValue = num.intValue();
        String str = this.$packageName;
        InstanceId instanceId = this.$instanceId;
        TileRequestDialogEventLogger tileRequestDialogEventLogger = tileServiceRequestController2.eventLogger;
        tileRequestDialogEventLogger.getClass();
        if (intValue == 0) {
            tileRequestDialogEvent = TileRequestDialogEvent.TILE_REQUEST_DIALOG_TILE_NOT_ADDED;
        } else if (intValue == 2) {
            tileRequestDialogEvent = TileRequestDialogEvent.TILE_REQUEST_DIALOG_TILE_ADDED;
        } else {
            if (intValue != 3) {
                throw new IllegalArgumentException(AnnotationValue$1$$ExternalSyntheticOutline0.m(intValue, "User response not valid: "));
            }
            tileRequestDialogEvent = TileRequestDialogEvent.TILE_REQUEST_DIALOG_DISMISSED;
        }
        tileRequestDialogEventLogger.uiEventLogger.logWithInstanceId(tileRequestDialogEvent, 0, str, instanceId);
        this.$callback.accept(num);
    }
}

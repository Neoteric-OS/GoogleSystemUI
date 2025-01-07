package com.android.systemui.media.taptotransfer.sender;

import android.media.MediaRoute2Info;
import android.util.Log;
import android.view.View;
import com.android.internal.logging.InstanceId;
import com.android.internal.statusbar.IUndoMediaTransferCallback;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaTttSenderCoordinator$getUndoButton$onClickListener$1 implements View.OnClickListener {
    public final /* synthetic */ InstanceId $instanceId;
    public final /* synthetic */ int $newState;
    public final /* synthetic */ MediaRoute2Info $routeInfo;
    public final /* synthetic */ MediaTttSenderUiEvents $uiEvent;
    public final /* synthetic */ IUndoMediaTransferCallback $undoCallback;
    public final /* synthetic */ MediaTttSenderCoordinator this$0;

    public MediaTttSenderCoordinator$getUndoButton$onClickListener$1(MediaTttSenderCoordinator mediaTttSenderCoordinator, MediaTttSenderUiEvents mediaTttSenderUiEvents, InstanceId instanceId, IUndoMediaTransferCallback iUndoMediaTransferCallback, int i, MediaRoute2Info mediaRoute2Info) {
        this.this$0 = mediaTttSenderCoordinator;
        this.$uiEvent = mediaTttSenderUiEvents;
        this.$instanceId = instanceId;
        this.$undoCallback = iUndoMediaTransferCallback;
        this.$newState = i;
        this.$routeInfo = mediaRoute2Info;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        MediaTttSenderUiEventLogger mediaTttSenderUiEventLogger = this.this$0.uiEventLogger;
        MediaTttSenderUiEvents mediaTttSenderUiEvents = this.$uiEvent;
        InstanceId instanceId = this.$instanceId;
        mediaTttSenderUiEventLogger.getClass();
        if (mediaTttSenderUiEvents == MediaTttSenderUiEvents.MEDIA_TTT_SENDER_UNDO_TRANSFER_TO_RECEIVER_CLICKED || mediaTttSenderUiEvents == MediaTttSenderUiEvents.MEDIA_TTT_SENDER_UNDO_TRANSFER_TO_THIS_DEVICE_CLICKED) {
            mediaTttSenderUiEventLogger.logger.log(mediaTttSenderUiEvents, instanceId);
        } else {
            String simpleName = Reflection.getOrCreateKotlinClass(MediaTttSenderUiEventLogger.class).getSimpleName();
            Intrinsics.checkNotNull(simpleName);
            Log.w(simpleName, "Must pass an undo-specific UiEvent.");
        }
        this.$undoCallback.onUndoTriggered();
        MediaTttSenderCoordinator.access$updateMediaTapToTransferSenderDisplay(this.this$0, this.$newState, this.$routeInfo, null);
    }
}

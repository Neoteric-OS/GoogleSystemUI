package com.android.systemui.media.taptotransfer.sender;

import android.content.Context;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.common.shared.model.Text;
import com.android.systemui.media.taptotransfer.sender.SenderEndItem;
import com.android.wm.shell.R;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ChipStateSender {
    public static final /* synthetic */ ChipStateSender[] $VALUES;
    public static final Companion Companion;
    public static final ChipStateSender FAR_FROM_RECEIVER;
    public static final ChipStateSender TRANSFER_TO_RECEIVER_FAILED;
    public static final ChipStateSender TRANSFER_TO_RECEIVER_SUCCEEDED;
    public static final ChipStateSender TRANSFER_TO_RECEIVER_TRIGGERED;
    public static final ChipStateSender TRANSFER_TO_THIS_DEVICE_FAILED;
    public static final ChipStateSender TRANSFER_TO_THIS_DEVICE_SUCCEEDED;
    public static final ChipStateSender TRANSFER_TO_THIS_DEVICE_TRIGGERED;
    private final SenderEndItem endItem;
    private final int stateInt;
    private final Integer stringResId;
    private final TimeoutLength timeoutLength;
    private final TransferStatus transferStatus;
    private final UiEventLogger.UiEventEnum uiEvent;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class ALMOST_CLOSE_TO_END_CAST extends ChipStateSender {
        @Override // com.android.systemui.media.taptotransfer.sender.ChipStateSender
        public final boolean isValidNextState(ChipStateSender chipStateSender) {
            return chipStateSender == ChipStateSender.FAR_FROM_RECEIVER || chipStateSender == ChipStateSender.TRANSFER_TO_THIS_DEVICE_TRIGGERED;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class ALMOST_CLOSE_TO_START_CAST extends ChipStateSender {
        @Override // com.android.systemui.media.taptotransfer.sender.ChipStateSender
        public final boolean isValidNextState(ChipStateSender chipStateSender) {
            return chipStateSender == ChipStateSender.FAR_FROM_RECEIVER || chipStateSender == ChipStateSender.TRANSFER_TO_RECEIVER_TRIGGERED;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
        public static final boolean access$stateIsStartOfSequence(Companion companion, ChipStateSender chipStateSender) {
            companion.getClass();
            return chipStateSender == ChipStateSender.FAR_FROM_RECEIVER || chipStateSender.getTransferStatus() == TransferStatus.NOT_STARTED || chipStateSender.getTransferStatus() == TransferStatus.IN_PROGRESS;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class FAR_FROM_RECEIVER extends ChipStateSender {
        @Override // com.android.systemui.media.taptotransfer.sender.ChipStateSender
        public final Text getChipTextString(Context context, String str) {
            throw new IllegalArgumentException("FAR_FROM_RECEIVER should never be displayed, so its string should never be fetched");
        }

        @Override // com.android.systemui.media.taptotransfer.sender.ChipStateSender
        public final boolean isValidNextState(ChipStateSender chipStateSender) {
            return Companion.access$stateIsStartOfSequence(ChipStateSender.Companion, chipStateSender);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class TRANSFER_TO_RECEIVER_FAILED extends ChipStateSender {
        @Override // com.android.systemui.media.taptotransfer.sender.ChipStateSender
        public final boolean isValidNextState(ChipStateSender chipStateSender) {
            return Companion.access$stateIsStartOfSequence(ChipStateSender.Companion, chipStateSender);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class TRANSFER_TO_RECEIVER_SUCCEEDED extends ChipStateSender {
        @Override // com.android.systemui.media.taptotransfer.sender.ChipStateSender
        public final boolean isValidNextState(ChipStateSender chipStateSender) {
            return Companion.access$stateIsStartOfSequence(ChipStateSender.Companion, chipStateSender);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class TRANSFER_TO_RECEIVER_TRIGGERED extends ChipStateSender {
        @Override // com.android.systemui.media.taptotransfer.sender.ChipStateSender
        public final boolean isValidNextState(ChipStateSender chipStateSender) {
            return chipStateSender == ChipStateSender.FAR_FROM_RECEIVER || chipStateSender == ChipStateSender.TRANSFER_TO_RECEIVER_SUCCEEDED || chipStateSender == ChipStateSender.TRANSFER_TO_RECEIVER_FAILED;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class TRANSFER_TO_THIS_DEVICE_FAILED extends ChipStateSender {
        @Override // com.android.systemui.media.taptotransfer.sender.ChipStateSender
        public final boolean isValidNextState(ChipStateSender chipStateSender) {
            return Companion.access$stateIsStartOfSequence(ChipStateSender.Companion, chipStateSender);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class TRANSFER_TO_THIS_DEVICE_SUCCEEDED extends ChipStateSender {
        @Override // com.android.systemui.media.taptotransfer.sender.ChipStateSender
        public final boolean isValidNextState(ChipStateSender chipStateSender) {
            return Companion.access$stateIsStartOfSequence(ChipStateSender.Companion, chipStateSender);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class TRANSFER_TO_THIS_DEVICE_TRIGGERED extends ChipStateSender {
        @Override // com.android.systemui.media.taptotransfer.sender.ChipStateSender
        public final boolean isValidNextState(ChipStateSender chipStateSender) {
            return chipStateSender == ChipStateSender.FAR_FROM_RECEIVER || chipStateSender == ChipStateSender.TRANSFER_TO_THIS_DEVICE_SUCCEEDED || chipStateSender == ChipStateSender.TRANSFER_TO_THIS_DEVICE_FAILED;
        }
    }

    static {
        MediaTttSenderUiEvents mediaTttSenderUiEvents = MediaTttSenderUiEvents.MEDIA_TTT_SENDER_ALMOST_CLOSE_TO_START_CAST;
        Integer valueOf = Integer.valueOf(R.string.media_move_closer_to_start_cast);
        TransferStatus transferStatus = TransferStatus.NOT_STARTED;
        TimeoutLength timeoutLength = TimeoutLength.LONG;
        ALMOST_CLOSE_TO_START_CAST almost_close_to_start_cast = new ALMOST_CLOSE_TO_START_CAST("ALMOST_CLOSE_TO_START_CAST", 0, 0, mediaTttSenderUiEvents, valueOf, transferStatus, null, timeoutLength);
        ALMOST_CLOSE_TO_END_CAST almost_close_to_end_cast = new ALMOST_CLOSE_TO_END_CAST("ALMOST_CLOSE_TO_END_CAST", 1, 1, MediaTttSenderUiEvents.MEDIA_TTT_SENDER_ALMOST_CLOSE_TO_END_CAST, Integer.valueOf(R.string.media_move_closer_to_end_cast), transferStatus, null, timeoutLength);
        MediaTttSenderUiEvents mediaTttSenderUiEvents2 = MediaTttSenderUiEvents.MEDIA_TTT_SENDER_TRANSFER_TO_RECEIVER_TRIGGERED;
        Integer valueOf2 = Integer.valueOf(R.string.media_transfer_playing_different_device);
        TransferStatus transferStatus2 = TransferStatus.IN_PROGRESS;
        SenderEndItem.Loading loading = SenderEndItem.Loading.INSTANCE;
        TRANSFER_TO_RECEIVER_TRIGGERED transfer_to_receiver_triggered = new TRANSFER_TO_RECEIVER_TRIGGERED("TRANSFER_TO_RECEIVER_TRIGGERED", 2, 2, mediaTttSenderUiEvents2, valueOf2, transferStatus2, loading, timeoutLength);
        TRANSFER_TO_RECEIVER_TRIGGERED = transfer_to_receiver_triggered;
        TRANSFER_TO_THIS_DEVICE_TRIGGERED transfer_to_this_device_triggered = new TRANSFER_TO_THIS_DEVICE_TRIGGERED("TRANSFER_TO_THIS_DEVICE_TRIGGERED", 3, 3, MediaTttSenderUiEvents.MEDIA_TTT_SENDER_TRANSFER_TO_THIS_DEVICE_TRIGGERED, Integer.valueOf(R.string.media_transfer_playing_this_device), transferStatus2, loading, timeoutLength);
        TRANSFER_TO_THIS_DEVICE_TRIGGERED = transfer_to_this_device_triggered;
        MediaTttSenderUiEvents mediaTttSenderUiEvents3 = MediaTttSenderUiEvents.MEDIA_TTT_SENDER_TRANSFER_TO_RECEIVER_SUCCEEDED;
        Integer valueOf3 = Integer.valueOf(R.string.media_transfer_playing_different_device);
        TransferStatus transferStatus3 = TransferStatus.SUCCEEDED;
        TRANSFER_TO_RECEIVER_SUCCEEDED transfer_to_receiver_succeeded = new TRANSFER_TO_RECEIVER_SUCCEEDED("TRANSFER_TO_RECEIVER_SUCCEEDED", 4, 4, mediaTttSenderUiEvents3, valueOf3, transferStatus3, new SenderEndItem.UndoButton(MediaTttSenderUiEvents.MEDIA_TTT_SENDER_UNDO_TRANSFER_TO_RECEIVER_CLICKED, 3));
        TRANSFER_TO_RECEIVER_SUCCEEDED = transfer_to_receiver_succeeded;
        TRANSFER_TO_THIS_DEVICE_SUCCEEDED transfer_to_this_device_succeeded = new TRANSFER_TO_THIS_DEVICE_SUCCEEDED("TRANSFER_TO_THIS_DEVICE_SUCCEEDED", 5, 5, MediaTttSenderUiEvents.MEDIA_TTT_SENDER_TRANSFER_TO_THIS_DEVICE_SUCCEEDED, Integer.valueOf(R.string.media_transfer_playing_this_device), transferStatus3, new SenderEndItem.UndoButton(MediaTttSenderUiEvents.MEDIA_TTT_SENDER_UNDO_TRANSFER_TO_THIS_DEVICE_CLICKED, 2));
        TRANSFER_TO_THIS_DEVICE_SUCCEEDED = transfer_to_this_device_succeeded;
        MediaTttSenderUiEvents mediaTttSenderUiEvents4 = MediaTttSenderUiEvents.MEDIA_TTT_SENDER_TRANSFER_TO_RECEIVER_FAILED;
        Integer valueOf4 = Integer.valueOf(R.string.media_transfer_failed);
        TransferStatus transferStatus4 = TransferStatus.FAILED;
        SenderEndItem.Error error = SenderEndItem.Error.INSTANCE;
        TRANSFER_TO_RECEIVER_FAILED transfer_to_receiver_failed = new TRANSFER_TO_RECEIVER_FAILED("TRANSFER_TO_RECEIVER_FAILED", 6, 6, mediaTttSenderUiEvents4, valueOf4, transferStatus4, error);
        TRANSFER_TO_RECEIVER_FAILED = transfer_to_receiver_failed;
        TRANSFER_TO_THIS_DEVICE_FAILED transfer_to_this_device_failed = new TRANSFER_TO_THIS_DEVICE_FAILED("TRANSFER_TO_THIS_DEVICE_FAILED", 7, 7, MediaTttSenderUiEvents.MEDIA_TTT_SENDER_TRANSFER_TO_THIS_DEVICE_FAILED, Integer.valueOf(R.string.media_transfer_failed), transferStatus4, error);
        TRANSFER_TO_THIS_DEVICE_FAILED = transfer_to_this_device_failed;
        String str = "FAR_FROM_RECEIVER";
        int i = 8;
        FAR_FROM_RECEIVER far_from_receiver = new FAR_FROM_RECEIVER(str, i, 8, MediaTttSenderUiEvents.MEDIA_TTT_SENDER_FAR_FROM_RECEIVER, null, TransferStatus.TOO_FAR, null);
        FAR_FROM_RECEIVER = far_from_receiver;
        ChipStateSender[] chipStateSenderArr = {almost_close_to_start_cast, almost_close_to_end_cast, transfer_to_receiver_triggered, transfer_to_this_device_triggered, transfer_to_receiver_succeeded, transfer_to_this_device_succeeded, transfer_to_receiver_failed, transfer_to_this_device_failed, far_from_receiver};
        $VALUES = chipStateSenderArr;
        EnumEntriesKt.enumEntries(chipStateSenderArr);
        Companion = new Companion();
    }

    public ChipStateSender(String str, int i, int i2, MediaTttSenderUiEvents mediaTttSenderUiEvents, Integer num, TransferStatus transferStatus, SenderEndItem senderEndItem, TimeoutLength timeoutLength) {
        this.stateInt = i2;
        this.uiEvent = mediaTttSenderUiEvents;
        this.stringResId = num;
        this.transferStatus = transferStatus;
        this.endItem = senderEndItem;
        this.timeoutLength = timeoutLength;
    }

    public static ChipStateSender valueOf(String str) {
        return (ChipStateSender) Enum.valueOf(ChipStateSender.class, str);
    }

    public static ChipStateSender[] values() {
        return (ChipStateSender[]) $VALUES.clone();
    }

    public Text getChipTextString(Context context, String str) {
        Integer num = this.stringResId;
        Intrinsics.checkNotNull(num);
        return new Text.Loaded(context.getString(num.intValue(), str));
    }

    public final SenderEndItem getEndItem() {
        return this.endItem;
    }

    public final int getStateInt() {
        return this.stateInt;
    }

    public final TimeoutLength getTimeoutLength() {
        return this.timeoutLength;
    }

    public final TransferStatus getTransferStatus() {
        return this.transferStatus;
    }

    public final UiEventLogger.UiEventEnum getUiEvent() {
        return this.uiEvent;
    }

    public abstract boolean isValidNextState(ChipStateSender chipStateSender);

    public /* synthetic */ ChipStateSender(String str, int i, int i2, MediaTttSenderUiEvents mediaTttSenderUiEvents, Integer num, TransferStatus transferStatus, SenderEndItem senderEndItem) {
        this(str, i, i2, mediaTttSenderUiEvents, num, transferStatus, senderEndItem, TimeoutLength.DEFAULT);
    }
}

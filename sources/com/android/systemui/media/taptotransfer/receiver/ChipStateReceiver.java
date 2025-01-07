package com.android.systemui.media.taptotransfer.receiver;

import com.android.internal.logging.UiEventLogger;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ChipStateReceiver {
    public static final /* synthetic */ ChipStateReceiver[] $VALUES;
    public static final ChipStateReceiver CLOSE_TO_SENDER;
    public static final Companion Companion;
    private final int stateInt;
    private final UiEventLogger.UiEventEnum uiEvent;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
    }

    static {
        ChipStateReceiver chipStateReceiver = new ChipStateReceiver("CLOSE_TO_SENDER", 0, 0, MediaTttReceiverUiEvents.MEDIA_TTT_RECEIVER_CLOSE_TO_SENDER);
        CLOSE_TO_SENDER = chipStateReceiver;
        ChipStateReceiver[] chipStateReceiverArr = {chipStateReceiver, new ChipStateReceiver("FAR_FROM_SENDER", 1, 1, MediaTttReceiverUiEvents.MEDIA_TTT_RECEIVER_FAR_FROM_SENDER), new ChipStateReceiver("TRANSFER_TO_RECEIVER_SUCCEEDED", 2, 2, MediaTttReceiverUiEvents.MEDIA_TTT_RECEIVER_TRANSFER_TO_RECEIVER_SUCCEEDED), new ChipStateReceiver("TRANSFER_TO_RECEIVER_FAILED", 3, 3, MediaTttReceiverUiEvents.MEDIA_TTT_RECEIVER_TRANSFER_TO_RECEIVER_FAILED)};
        $VALUES = chipStateReceiverArr;
        EnumEntriesKt.enumEntries(chipStateReceiverArr);
        Companion = new Companion();
    }

    public ChipStateReceiver(String str, int i, int i2, MediaTttReceiverUiEvents mediaTttReceiverUiEvents) {
        this.stateInt = i2;
        this.uiEvent = mediaTttReceiverUiEvents;
    }

    public static ChipStateReceiver valueOf(String str) {
        return (ChipStateReceiver) Enum.valueOf(ChipStateReceiver.class, str);
    }

    public static ChipStateReceiver[] values() {
        return (ChipStateReceiver[]) $VALUES.clone();
    }

    public final int getStateInt() {
        return this.stateInt;
    }

    public final UiEventLogger.UiEventEnum getUiEvent() {
        return this.uiEvent;
    }
}

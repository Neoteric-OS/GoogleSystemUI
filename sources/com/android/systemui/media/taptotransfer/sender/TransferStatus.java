package com.android.systemui.media.taptotransfer.sender;

import android.os.VibrationEffect;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TransferStatus {
    public static final /* synthetic */ TransferStatus[] $VALUES;
    public static final TransferStatus FAILED;
    public static final TransferStatus IN_PROGRESS;
    public static final TransferStatus NOT_STARTED;
    public static final TransferStatus SUCCEEDED;
    public static final TransferStatus TOO_FAR;
    private final VibrationEffect vibrationEffect;

    static {
        TransferStatus transferStatus = new TransferStatus("NOT_STARTED", 0, VibrationEffect.startComposition().addPrimitive(1, 1.0f, 0).compose());
        NOT_STARTED = transferStatus;
        TransferStatus transferStatus2 = new TransferStatus("IN_PROGRESS", 1, VibrationEffect.startComposition().addPrimitive(4, 1.0f, 0).addPrimitive(1, 0.7f, 70).compose());
        IN_PROGRESS = transferStatus2;
        TransferStatus transferStatus3 = new TransferStatus("SUCCEEDED", 2, null);
        SUCCEEDED = transferStatus3;
        TransferStatus transferStatus4 = new TransferStatus("FAILED", 3, VibrationEffect.get(1));
        FAILED = transferStatus4;
        TransferStatus transferStatus5 = new TransferStatus("TOO_FAR", 4, null);
        TOO_FAR = transferStatus5;
        TransferStatus[] transferStatusArr = {transferStatus, transferStatus2, transferStatus3, transferStatus4, transferStatus5};
        $VALUES = transferStatusArr;
        EnumEntriesKt.enumEntries(transferStatusArr);
    }

    public TransferStatus(String str, int i, VibrationEffect vibrationEffect) {
        this.vibrationEffect = vibrationEffect;
    }

    public static TransferStatus valueOf(String str) {
        return (TransferStatus) Enum.valueOf(TransferStatus.class, str);
    }

    public static TransferStatus[] values() {
        return (TransferStatus[]) $VALUES.clone();
    }

    public final VibrationEffect getVibrationEffect() {
        return this.vibrationEffect;
    }
}

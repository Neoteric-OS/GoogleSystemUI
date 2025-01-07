package com.android.systemui.qs;

import com.android.internal.logging.UiEventLogger;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QSEditEvent implements UiEventLogger.UiEventEnum {
    public static final /* synthetic */ QSEditEvent[] $VALUES;
    public static final QSEditEvent QS_EDIT_ADD;
    public static final QSEditEvent QS_EDIT_CLOSED;
    public static final QSEditEvent QS_EDIT_MOVE;
    public static final QSEditEvent QS_EDIT_OPEN;
    public static final QSEditEvent QS_EDIT_REMOVE;
    public static final QSEditEvent QS_EDIT_RESET;
    private final int _id;

    static {
        QSEditEvent qSEditEvent = new QSEditEvent("QS_EDIT_REMOVE", 0, 210);
        QS_EDIT_REMOVE = qSEditEvent;
        QSEditEvent qSEditEvent2 = new QSEditEvent("QS_EDIT_ADD", 1, 211);
        QS_EDIT_ADD = qSEditEvent2;
        QSEditEvent qSEditEvent3 = new QSEditEvent("QS_EDIT_MOVE", 2, 212);
        QS_EDIT_MOVE = qSEditEvent3;
        QSEditEvent qSEditEvent4 = new QSEditEvent("QS_EDIT_OPEN", 3, 213);
        QS_EDIT_OPEN = qSEditEvent4;
        QSEditEvent qSEditEvent5 = new QSEditEvent("QS_EDIT_CLOSED", 4, 214);
        QS_EDIT_CLOSED = qSEditEvent5;
        QSEditEvent qSEditEvent6 = new QSEditEvent("QS_EDIT_RESET", 5, 215);
        QS_EDIT_RESET = qSEditEvent6;
        QSEditEvent[] qSEditEventArr = {qSEditEvent, qSEditEvent2, qSEditEvent3, qSEditEvent4, qSEditEvent5, qSEditEvent6};
        $VALUES = qSEditEventArr;
        EnumEntriesKt.enumEntries(qSEditEventArr);
    }

    public QSEditEvent(String str, int i, int i2) {
        this._id = i2;
    }

    public static QSEditEvent valueOf(String str) {
        return (QSEditEvent) Enum.valueOf(QSEditEvent.class, str);
    }

    public static QSEditEvent[] values() {
        return (QSEditEvent[]) $VALUES.clone();
    }

    public final int getId() {
        return this._id;
    }
}

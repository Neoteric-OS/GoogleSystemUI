package com.android.systemui.qs;

import com.android.internal.logging.UiEventLogger;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QSUserSwitcherEvent implements UiEventLogger.UiEventEnum {
    public static final /* synthetic */ QSUserSwitcherEvent[] $VALUES;
    public static final QSUserSwitcherEvent QS_USER_DETAIL_CLOSE;
    public static final QSUserSwitcherEvent QS_USER_DETAIL_OPEN;
    public static final QSUserSwitcherEvent QS_USER_GUEST_ADD;
    public static final QSUserSwitcherEvent QS_USER_GUEST_REMOVE;
    public static final QSUserSwitcherEvent QS_USER_MORE_SETTINGS;
    public static final QSUserSwitcherEvent QS_USER_SWITCH;
    private final int _id;

    static {
        QSUserSwitcherEvent qSUserSwitcherEvent = new QSUserSwitcherEvent("QS_USER_SWITCH", 0, 424);
        QS_USER_SWITCH = qSUserSwitcherEvent;
        QSUserSwitcherEvent qSUserSwitcherEvent2 = new QSUserSwitcherEvent("QS_USER_DETAIL_OPEN", 1, 425);
        QS_USER_DETAIL_OPEN = qSUserSwitcherEvent2;
        QSUserSwitcherEvent qSUserSwitcherEvent3 = new QSUserSwitcherEvent("QS_USER_DETAIL_CLOSE", 2, 426);
        QS_USER_DETAIL_CLOSE = qSUserSwitcherEvent3;
        QSUserSwitcherEvent qSUserSwitcherEvent4 = new QSUserSwitcherEvent("QS_USER_MORE_SETTINGS", 3, 427);
        QS_USER_MORE_SETTINGS = qSUserSwitcherEvent4;
        QSUserSwitcherEvent qSUserSwitcherEvent5 = new QSUserSwitcherEvent("QS_USER_GUEST_ADD", 4, 754);
        QS_USER_GUEST_ADD = qSUserSwitcherEvent5;
        QSUserSwitcherEvent qSUserSwitcherEvent6 = new QSUserSwitcherEvent("QS_USER_GUEST_WIPE", 5, 755);
        QSUserSwitcherEvent qSUserSwitcherEvent7 = new QSUserSwitcherEvent("QS_USER_GUEST_CONTINUE", 6, 756);
        QSUserSwitcherEvent qSUserSwitcherEvent8 = new QSUserSwitcherEvent("QS_USER_GUEST_REMOVE", 7, 757);
        QS_USER_GUEST_REMOVE = qSUserSwitcherEvent8;
        QSUserSwitcherEvent[] qSUserSwitcherEventArr = {qSUserSwitcherEvent, qSUserSwitcherEvent2, qSUserSwitcherEvent3, qSUserSwitcherEvent4, qSUserSwitcherEvent5, qSUserSwitcherEvent6, qSUserSwitcherEvent7, qSUserSwitcherEvent8};
        $VALUES = qSUserSwitcherEventArr;
        EnumEntriesKt.enumEntries(qSUserSwitcherEventArr);
    }

    public QSUserSwitcherEvent(String str, int i, int i2) {
        this._id = i2;
    }

    public static QSUserSwitcherEvent valueOf(String str) {
        return (QSUserSwitcherEvent) Enum.valueOf(QSUserSwitcherEvent.class, str);
    }

    public static QSUserSwitcherEvent[] values() {
        return (QSUserSwitcherEvent[]) $VALUES.clone();
    }

    public final int getId() {
        return this._id;
    }
}

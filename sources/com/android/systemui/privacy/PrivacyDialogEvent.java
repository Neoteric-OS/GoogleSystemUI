package com.android.systemui.privacy;

import com.android.internal.logging.UiEventLogger;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PrivacyDialogEvent implements UiEventLogger.UiEventEnum {
    public static final /* synthetic */ PrivacyDialogEvent[] $VALUES;
    public static final PrivacyDialogEvent PRIVACY_DIALOG_CLICK_TO_PRIVACY_DASHBOARD;
    public static final PrivacyDialogEvent PRIVACY_DIALOG_DISMISSED;
    public static final PrivacyDialogEvent PRIVACY_DIALOG_ITEM_CLICKED_TO_APP_SETTINGS;
    public static final PrivacyDialogEvent PRIVACY_DIALOG_ITEM_CLICKED_TO_CLOSE_APP;
    private final int _id;

    static {
        PrivacyDialogEvent privacyDialogEvent = new PrivacyDialogEvent("PRIVACY_DIALOG_ITEM_CLICKED_TO_APP_SETTINGS", 0, 904);
        PRIVACY_DIALOG_ITEM_CLICKED_TO_APP_SETTINGS = privacyDialogEvent;
        PrivacyDialogEvent privacyDialogEvent2 = new PrivacyDialogEvent("PRIVACY_DIALOG_DISMISSED", 1, 905);
        PRIVACY_DIALOG_DISMISSED = privacyDialogEvent2;
        PrivacyDialogEvent privacyDialogEvent3 = new PrivacyDialogEvent("PRIVACY_DIALOG_ITEM_CLICKED_TO_CLOSE_APP", 2, 1396);
        PRIVACY_DIALOG_ITEM_CLICKED_TO_CLOSE_APP = privacyDialogEvent3;
        PrivacyDialogEvent privacyDialogEvent4 = new PrivacyDialogEvent("PRIVACY_DIALOG_CLICK_TO_PRIVACY_DASHBOARD", 3, 1397);
        PRIVACY_DIALOG_CLICK_TO_PRIVACY_DASHBOARD = privacyDialogEvent4;
        PrivacyDialogEvent[] privacyDialogEventArr = {privacyDialogEvent, privacyDialogEvent2, privacyDialogEvent3, privacyDialogEvent4};
        $VALUES = privacyDialogEventArr;
        EnumEntriesKt.enumEntries(privacyDialogEventArr);
    }

    public PrivacyDialogEvent(String str, int i, int i2) {
        this._id = i2;
    }

    public static PrivacyDialogEvent valueOf(String str) {
        return (PrivacyDialogEvent) Enum.valueOf(PrivacyDialogEvent.class, str);
    }

    public static PrivacyDialogEvent[] values() {
        return (PrivacyDialogEvent[]) $VALUES.clone();
    }

    public final int getId() {
        return this._id;
    }
}

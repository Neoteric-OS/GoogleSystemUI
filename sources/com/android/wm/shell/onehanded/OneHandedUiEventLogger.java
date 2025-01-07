package com.android.wm.shell.onehanded;

import com.android.app.viewcapture.data.ViewNode;
import com.android.internal.logging.UiEventLogger;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class OneHandedUiEventLogger {
    public final UiEventLogger mUiEventLogger;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public enum OneHandedSettingsTogglesEvent implements UiEventLogger.UiEventEnum {
        /* JADX INFO: Fake field, exist only in values array */
        INVALID(0),
        ONE_HANDED_SETTINGS_TOGGLES_ENABLED_ON(356),
        ONE_HANDED_SETTINGS_TOGGLES_ENABLED_OFF(357),
        ONE_HANDED_SETTINGS_TOGGLES_APP_TAPS_EXIT_ON(358),
        ONE_HANDED_SETTINGS_TOGGLES_APP_TAPS_EXIT_OFF(359),
        ONE_HANDED_SETTINGS_TOGGLES_TIMEOUT_EXIT_ON(360),
        ONE_HANDED_SETTINGS_TOGGLES_TIMEOUT_EXIT_OFF(361),
        ONE_HANDED_SETTINGS_TOGGLES_TIMEOUT_SECONDS_NEVER(362),
        ONE_HANDED_SETTINGS_TOGGLES_TIMEOUT_SECONDS_4(363),
        ONE_HANDED_SETTINGS_TOGGLES_TIMEOUT_SECONDS_8(364),
        ONE_HANDED_SETTINGS_TOGGLES_TIMEOUT_SECONDS_12(365),
        ONE_HANDED_SETTINGS_TOGGLES_SHOW_NOTIFICATION_ENABLED_ON(847),
        ONE_HANDED_SETTINGS_TOGGLES_SHOW_NOTIFICATION_ENABLED_OFF(848),
        ONE_HANDED_SETTINGS_TOGGLES_SHORTCUT_ENABLED_ON(870),
        ONE_HANDED_SETTINGS_TOGGLES_SHORTCUT_ENABLED_OFF(871);

        private final int mId;

        OneHandedSettingsTogglesEvent(int i) {
            this.mId = i;
        }

        public final int getId() {
            return this.mId;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public enum OneHandedTriggerEvent implements UiEventLogger.UiEventEnum {
        /* JADX INFO: Fake field, exist only in values array */
        INVALID(0),
        ONE_HANDED_TRIGGER_GESTURE_IN(366),
        ONE_HANDED_TRIGGER_GESTURE_OUT(367),
        ONE_HANDED_TRIGGER_OVERSPACE_OUT(368),
        ONE_HANDED_TRIGGER_POP_IME_OUT(369),
        ONE_HANDED_TRIGGER_ROTATION_OUT(370),
        ONE_HANDED_TRIGGER_APP_TAPS_OUT(371),
        ONE_HANDED_TRIGGER_TIMEOUT_OUT(372),
        ONE_HANDED_TRIGGER_SCREEN_OFF_OUT(449);

        private final int mId;

        OneHandedTriggerEvent(int i) {
            this.mId = i;
        }

        public final int getId() {
            return this.mId;
        }
    }

    public OneHandedUiEventLogger(UiEventLogger uiEventLogger) {
        this.mUiEventLogger = uiEventLogger;
    }

    public final void writeEvent(int i) {
        switch (i) {
            case 0:
                this.mUiEventLogger.log(OneHandedTriggerEvent.ONE_HANDED_TRIGGER_GESTURE_IN);
                break;
            case 1:
                this.mUiEventLogger.log(OneHandedTriggerEvent.ONE_HANDED_TRIGGER_GESTURE_OUT);
                break;
            case 2:
                this.mUiEventLogger.log(OneHandedTriggerEvent.ONE_HANDED_TRIGGER_OVERSPACE_OUT);
                break;
            case 3:
                this.mUiEventLogger.log(OneHandedTriggerEvent.ONE_HANDED_TRIGGER_POP_IME_OUT);
                break;
            case 4:
                this.mUiEventLogger.log(OneHandedTriggerEvent.ONE_HANDED_TRIGGER_ROTATION_OUT);
                break;
            case 5:
                this.mUiEventLogger.log(OneHandedTriggerEvent.ONE_HANDED_TRIGGER_APP_TAPS_OUT);
                break;
            case 6:
                this.mUiEventLogger.log(OneHandedTriggerEvent.ONE_HANDED_TRIGGER_TIMEOUT_OUT);
                break;
            case 7:
                this.mUiEventLogger.log(OneHandedTriggerEvent.ONE_HANDED_TRIGGER_SCREEN_OFF_OUT);
                break;
            case 8:
                this.mUiEventLogger.log(OneHandedSettingsTogglesEvent.ONE_HANDED_SETTINGS_TOGGLES_ENABLED_ON);
                break;
            case 9:
                this.mUiEventLogger.log(OneHandedSettingsTogglesEvent.ONE_HANDED_SETTINGS_TOGGLES_ENABLED_OFF);
                break;
            case 10:
                this.mUiEventLogger.log(OneHandedSettingsTogglesEvent.ONE_HANDED_SETTINGS_TOGGLES_APP_TAPS_EXIT_ON);
                break;
            case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                this.mUiEventLogger.log(OneHandedSettingsTogglesEvent.ONE_HANDED_SETTINGS_TOGGLES_APP_TAPS_EXIT_OFF);
                break;
            case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                this.mUiEventLogger.log(OneHandedSettingsTogglesEvent.ONE_HANDED_SETTINGS_TOGGLES_TIMEOUT_EXIT_ON);
                break;
            case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                this.mUiEventLogger.log(OneHandedSettingsTogglesEvent.ONE_HANDED_SETTINGS_TOGGLES_TIMEOUT_EXIT_OFF);
                break;
            case ViewNode.SCALEY_FIELD_NUMBER /* 14 */:
                this.mUiEventLogger.log(OneHandedSettingsTogglesEvent.ONE_HANDED_SETTINGS_TOGGLES_TIMEOUT_SECONDS_NEVER);
                break;
            case 15:
                this.mUiEventLogger.log(OneHandedSettingsTogglesEvent.ONE_HANDED_SETTINGS_TOGGLES_TIMEOUT_SECONDS_4);
                break;
            case 16:
                this.mUiEventLogger.log(OneHandedSettingsTogglesEvent.ONE_HANDED_SETTINGS_TOGGLES_TIMEOUT_SECONDS_8);
                break;
            case ViewNode.CLIPCHILDREN_FIELD_NUMBER /* 17 */:
                this.mUiEventLogger.log(OneHandedSettingsTogglesEvent.ONE_HANDED_SETTINGS_TOGGLES_TIMEOUT_SECONDS_12);
                break;
            case ViewNode.VISIBILITY_FIELD_NUMBER /* 18 */:
                this.mUiEventLogger.log(OneHandedSettingsTogglesEvent.ONE_HANDED_SETTINGS_TOGGLES_SHOW_NOTIFICATION_ENABLED_ON);
                break;
            case ViewNode.ELEVATION_FIELD_NUMBER /* 19 */:
                this.mUiEventLogger.log(OneHandedSettingsTogglesEvent.ONE_HANDED_SETTINGS_TOGGLES_SHOW_NOTIFICATION_ENABLED_OFF);
                break;
            case 20:
                this.mUiEventLogger.log(OneHandedSettingsTogglesEvent.ONE_HANDED_SETTINGS_TOGGLES_SHORTCUT_ENABLED_ON);
                break;
            case 21:
                this.mUiEventLogger.log(OneHandedSettingsTogglesEvent.ONE_HANDED_SETTINGS_TOGGLES_SHORTCUT_ENABLED_OFF);
                break;
        }
    }
}

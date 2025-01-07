package com.android.systemui.deviceentry.shared;

import com.android.internal.logging.UiEventLogger;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class FaceAuthUiEvent implements UiEventLogger.UiEventEnum {
    public static final /* synthetic */ FaceAuthUiEvent[] $VALUES;
    public static final FaceAuthUiEvent FACE_AUTH_ACCESSIBILITY_ACTION;
    public static final FaceAuthUiEvent FACE_AUTH_CAMERA_AVAILABLE_CHANGED;
    public static final FaceAuthUiEvent FACE_AUTH_TRIGGERED_ALTERNATE_BIOMETRIC_BOUNCER_SHOWN;
    public static final FaceAuthUiEvent FACE_AUTH_TRIGGERED_NOTIFICATION_PANEL_CLICKED;
    public static final FaceAuthUiEvent FACE_AUTH_TRIGGERED_OCCLUDING_APP_REQUESTED;
    public static final FaceAuthUiEvent FACE_AUTH_TRIGGERED_PICK_UP_GESTURE_TRIGGERED;
    public static final FaceAuthUiEvent FACE_AUTH_TRIGGERED_QS_EXPANDED;
    public static final FaceAuthUiEvent FACE_AUTH_TRIGGERED_RETRY_AFTER_HW_UNAVAILABLE;
    public static final FaceAuthUiEvent FACE_AUTH_TRIGGERED_SWIPE_UP_ON_BOUNCER;
    public static final FaceAuthUiEvent FACE_AUTH_TRIGGERED_UDFPS_POINTER_DOWN;
    public static final FaceAuthUiEvent FACE_AUTH_UPDATED_ASSISTANT_VISIBILITY_CHANGED;
    public static final FaceAuthUiEvent FACE_AUTH_UPDATED_KEYGUARD_VISIBILITY_CHANGED;
    public static final FaceAuthUiEvent FACE_AUTH_UPDATED_POSTURE_CHANGED;
    public static final FaceAuthUiEvent FACE_AUTH_UPDATED_PRIMARY_BOUNCER_SHOWN;
    public static final FaceAuthUiEvent FACE_AUTH_UPDATED_PRIMARY_BOUNCER_SHOWN_OR_WILL_BE_SHOWN;
    public static final FaceAuthUiEvent FACE_AUTH_UPDATED_STARTED_WAKING_UP;
    public static final FaceAuthUiEvent FACE_AUTH_UPDATED_USER_SWITCHING;
    private int extraInfo = 0;
    private final int id;
    private final String reason;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class FACE_AUTH_UPDATED_STARTED_WAKING_UP extends FaceAuthUiEvent {
    }

    static {
        FaceAuthUiEvent faceAuthUiEvent = new FaceAuthUiEvent("FACE_AUTH_TRIGGERED_OCCLUDING_APP_REQUESTED", 0, 1146, "Face auth due to request from occluding app.");
        FACE_AUTH_TRIGGERED_OCCLUDING_APP_REQUESTED = faceAuthUiEvent;
        FaceAuthUiEvent faceAuthUiEvent2 = new FaceAuthUiEvent("FACE_AUTH_TRIGGERED_UDFPS_POINTER_DOWN", 1, 1147, "Face auth triggered due to finger down on UDFPS");
        FACE_AUTH_TRIGGERED_UDFPS_POINTER_DOWN = faceAuthUiEvent2;
        FaceAuthUiEvent faceAuthUiEvent3 = new FaceAuthUiEvent("FACE_AUTH_TRIGGERED_SWIPE_UP_ON_BOUNCER", 2, 1148, "Face auth due to swipe up on bouncer");
        FACE_AUTH_TRIGGERED_SWIPE_UP_ON_BOUNCER = faceAuthUiEvent3;
        FaceAuthUiEvent faceAuthUiEvent4 = new FaceAuthUiEvent("FACE_AUTH_TRIGGERED_ON_REACH_GESTURE_ON_AOD", 3, 1149, "Face auth requested when user reaches for the device on AoD.");
        FaceAuthUiEvent faceAuthUiEvent5 = new FaceAuthUiEvent("FACE_AUTH_TRIGGERED_FACE_LOCKOUT_RESET", 4, 1150, "Face auth due to face lockout reset.");
        FaceAuthUiEvent faceAuthUiEvent6 = new FaceAuthUiEvent("FACE_AUTH_TRIGGERED_QS_EXPANDED", 5, 1151, "Face auth due to QS expansion.");
        FACE_AUTH_TRIGGERED_QS_EXPANDED = faceAuthUiEvent6;
        FaceAuthUiEvent faceAuthUiEvent7 = new FaceAuthUiEvent("FACE_AUTH_TRIGGERED_NOTIFICATION_PANEL_CLICKED", 6, 1152, "Face auth due to notification panel click.");
        FACE_AUTH_TRIGGERED_NOTIFICATION_PANEL_CLICKED = faceAuthUiEvent7;
        FaceAuthUiEvent faceAuthUiEvent8 = new FaceAuthUiEvent("FACE_AUTH_TRIGGERED_PICK_UP_GESTURE_TRIGGERED", 7, 1153, "Face auth due to pickup gesture triggered when the device is awake and not from AOD.");
        FACE_AUTH_TRIGGERED_PICK_UP_GESTURE_TRIGGERED = faceAuthUiEvent8;
        FaceAuthUiEvent faceAuthUiEvent9 = new FaceAuthUiEvent("FACE_AUTH_TRIGGERED_ALTERNATE_BIOMETRIC_BOUNCER_SHOWN", 8, 1154, "Face auth due to alternate bouncer shown.");
        FACE_AUTH_TRIGGERED_ALTERNATE_BIOMETRIC_BOUNCER_SHOWN = faceAuthUiEvent9;
        FaceAuthUiEvent faceAuthUiEvent10 = new FaceAuthUiEvent("FACE_AUTH_UPDATED_PRIMARY_BOUNCER_SHOWN", 9, 1155, "Face auth started/stopped due to primary bouncer shown.");
        FACE_AUTH_UPDATED_PRIMARY_BOUNCER_SHOWN = faceAuthUiEvent10;
        FaceAuthUiEvent faceAuthUiEvent11 = new FaceAuthUiEvent("FACE_AUTH_UPDATED_PRIMARY_BOUNCER_SHOWN_OR_WILL_BE_SHOWN", 10, 1197, "Face auth started/stopped due to bouncer being shown or will be shown.");
        FACE_AUTH_UPDATED_PRIMARY_BOUNCER_SHOWN_OR_WILL_BE_SHOWN = faceAuthUiEvent11;
        FaceAuthUiEvent faceAuthUiEvent12 = new FaceAuthUiEvent("FACE_AUTH_TRIGGERED_RETRY_AFTER_HW_UNAVAILABLE", 11, 1156, "Face auth due to retry after hardware unavailable.");
        FACE_AUTH_TRIGGERED_RETRY_AFTER_HW_UNAVAILABLE = faceAuthUiEvent12;
        FaceAuthUiEvent faceAuthUiEvent13 = new FaceAuthUiEvent("FACE_AUTH_TRIGGERED_TRUST_DISABLED", 12, 1158, "Face auth started due to trust disabled.");
        FaceAuthUiEvent faceAuthUiEvent14 = new FaceAuthUiEvent("FACE_AUTH_STOPPED_TRUST_ENABLED", 13, 1173, "Face auth stopped due to trust enabled.");
        FaceAuthUiEvent faceAuthUiEvent15 = new FaceAuthUiEvent("FACE_AUTH_UPDATED_KEYGUARD_OCCLUSION_CHANGED", 14, 1159, "Face auth started/stopped due to keyguard occlusion change.");
        FaceAuthUiEvent faceAuthUiEvent16 = new FaceAuthUiEvent("FACE_AUTH_UPDATED_ASSISTANT_VISIBILITY_CHANGED", 15, 1160, "Face auth started/stopped due to assistant visibility change.");
        FACE_AUTH_UPDATED_ASSISTANT_VISIBILITY_CHANGED = faceAuthUiEvent16;
        FACE_AUTH_UPDATED_STARTED_WAKING_UP face_auth_updated_started_waking_up = new FACE_AUTH_UPDATED_STARTED_WAKING_UP("FACE_AUTH_UPDATED_STARTED_WAKING_UP", 16, 1161, "Face auth started/stopped due to device starting to wake up.");
        FACE_AUTH_UPDATED_STARTED_WAKING_UP = face_auth_updated_started_waking_up;
        FaceAuthUiEvent faceAuthUiEvent17 = new FaceAuthUiEvent("FACE_AUTH_UPDATED_POSTURE_CHANGED", 17, 1265, "Face auth started/stopped due to device posture changed.");
        FACE_AUTH_UPDATED_POSTURE_CHANGED = faceAuthUiEvent17;
        FaceAuthUiEvent faceAuthUiEvent18 = new FaceAuthUiEvent("FACE_AUTH_TRIGGERED_DREAM_STOPPED", 18, 1162, "Face auth due to dream stopped.");
        FaceAuthUiEvent faceAuthUiEvent19 = new FaceAuthUiEvent("FACE_AUTH_TRIGGERED_ALL_AUTHENTICATORS_REGISTERED", 19, 1163, "Face auth due to all authenticators registered.");
        FaceAuthUiEvent faceAuthUiEvent20 = new FaceAuthUiEvent("FACE_AUTH_TRIGGERED_ENROLLMENTS_CHANGED", 20, 1164, "Face auth due to enrolments changed.");
        FaceAuthUiEvent faceAuthUiEvent21 = new FaceAuthUiEvent("FACE_AUTH_UPDATED_KEYGUARD_VISIBILITY_CHANGED", 21, 1165, "Face auth stopped or started due to keyguard visibility changed.");
        FACE_AUTH_UPDATED_KEYGUARD_VISIBILITY_CHANGED = faceAuthUiEvent21;
        FaceAuthUiEvent faceAuthUiEvent22 = new FaceAuthUiEvent("FACE_AUTH_STOPPED_FACE_CANCEL_NOT_RECEIVED", 22, 1174, "Face auth stopped due to face cancel signal not received.");
        FaceAuthUiEvent faceAuthUiEvent23 = new FaceAuthUiEvent("FACE_AUTH_TRIGGERED_DURING_CANCELLATION", 23, 1175, "Another request to start face auth received while cancelling face auth");
        FaceAuthUiEvent faceAuthUiEvent24 = new FaceAuthUiEvent("FACE_AUTH_STOPPED_DREAM_STARTED", 24, 1176, "Face auth stopped because dreaming started");
        FaceAuthUiEvent faceAuthUiEvent25 = new FaceAuthUiEvent("FACE_AUTH_STOPPED_FP_LOCKED_OUT", 25, 1177, "Face auth stopped because fp locked out");
        FaceAuthUiEvent faceAuthUiEvent26 = new FaceAuthUiEvent("FACE_AUTH_STOPPED_USER_INPUT_ON_BOUNCER", 26, 1178, "Face auth stopped because user started typing password/pin");
        FaceAuthUiEvent faceAuthUiEvent27 = new FaceAuthUiEvent("FACE_AUTH_STOPPED_KEYGUARD_GOING_AWAY", 27, 1179, "Face auth stopped because keyguard going away");
        FaceAuthUiEvent faceAuthUiEvent28 = new FaceAuthUiEvent("FACE_AUTH_UPDATED_CAMERA_LAUNCHED", 28, 1180, "Face auth started/stopped because camera launched");
        FaceAuthUiEvent faceAuthUiEvent29 = new FaceAuthUiEvent("FACE_AUTH_UPDATED_FP_AUTHENTICATED", 29, 1181, "Face auth started/stopped because fingerprint launched");
        FaceAuthUiEvent faceAuthUiEvent30 = new FaceAuthUiEvent("FACE_AUTH_UPDATED_GOING_TO_SLEEP", 30, 1182, "Face auth started/stopped because going to sleep");
        FaceAuthUiEvent faceAuthUiEvent31 = new FaceAuthUiEvent("FACE_AUTH_STOPPED_FINISHED_GOING_TO_SLEEP", 31, 1183, "Face auth stopped because finished going to sleep");
        FaceAuthUiEvent faceAuthUiEvent32 = new FaceAuthUiEvent("FACE_AUTH_UPDATED_ON_KEYGUARD_INIT", 32, 1189, "Face auth started/stopped because Keyguard is initialized");
        FaceAuthUiEvent faceAuthUiEvent33 = new FaceAuthUiEvent("FACE_AUTH_UPDATED_KEYGUARD_RESET", 33, 1185, "Face auth started/stopped because Keyguard is reset");
        FaceAuthUiEvent faceAuthUiEvent34 = new FaceAuthUiEvent("FACE_AUTH_UPDATED_USER_SWITCHING", 34, 1186, "Face auth started/stopped because user is switching");
        FACE_AUTH_UPDATED_USER_SWITCHING = faceAuthUiEvent34;
        FaceAuthUiEvent faceAuthUiEvent35 = new FaceAuthUiEvent("FACE_AUTH_UPDATED_ON_FACE_AUTHENTICATED", 35, 1187, "Face auth started/stopped because face is authenticated");
        FaceAuthUiEvent faceAuthUiEvent36 = new FaceAuthUiEvent("FACE_AUTH_UPDATED_BIOMETRIC_ENABLED_ON_KEYGUARD", 36, 1188, "Face auth started/stopped because biometric is enabled on keyguard");
        FaceAuthUiEvent faceAuthUiEvent37 = new FaceAuthUiEvent("FACE_AUTH_UPDATED_STRONG_AUTH_CHANGED", 37, 1255, "Face auth stopped because strong auth allowed changed");
        FaceAuthUiEvent faceAuthUiEvent38 = new FaceAuthUiEvent("FACE_AUTH_NON_STRONG_BIOMETRIC_ALLOWED_CHANGED", 38, 1256, "Face auth stopped because non strong biometric allowed changed");
        FaceAuthUiEvent faceAuthUiEvent39 = new FaceAuthUiEvent("FACE_AUTH_ACCESSIBILITY_ACTION", 39, 1454, "Face auth due to an accessibility action.");
        FACE_AUTH_ACCESSIBILITY_ACTION = faceAuthUiEvent39;
        FaceAuthUiEvent faceAuthUiEvent40 = new FaceAuthUiEvent("FACE_AUTH_DISPLAY_OFF", 40, 1461, "Face auth stopped due to display state OFF.");
        FaceAuthUiEvent faceAuthUiEvent41 = new FaceAuthUiEvent("FACE_AUTH_CAMERA_AVAILABLE_CHANGED", 41, 1623, "Face auth started due to the available camera changed");
        FACE_AUTH_CAMERA_AVAILABLE_CHANGED = faceAuthUiEvent41;
        FaceAuthUiEvent[] faceAuthUiEventArr = {faceAuthUiEvent, faceAuthUiEvent2, faceAuthUiEvent3, faceAuthUiEvent4, faceAuthUiEvent5, faceAuthUiEvent6, faceAuthUiEvent7, faceAuthUiEvent8, faceAuthUiEvent9, faceAuthUiEvent10, faceAuthUiEvent11, faceAuthUiEvent12, faceAuthUiEvent13, faceAuthUiEvent14, faceAuthUiEvent15, faceAuthUiEvent16, face_auth_updated_started_waking_up, faceAuthUiEvent17, faceAuthUiEvent18, faceAuthUiEvent19, faceAuthUiEvent20, faceAuthUiEvent21, faceAuthUiEvent22, faceAuthUiEvent23, faceAuthUiEvent24, faceAuthUiEvent25, faceAuthUiEvent26, faceAuthUiEvent27, faceAuthUiEvent28, faceAuthUiEvent29, faceAuthUiEvent30, faceAuthUiEvent31, faceAuthUiEvent32, faceAuthUiEvent33, faceAuthUiEvent34, faceAuthUiEvent35, faceAuthUiEvent36, faceAuthUiEvent37, faceAuthUiEvent38, faceAuthUiEvent39, faceAuthUiEvent40, faceAuthUiEvent41};
        $VALUES = faceAuthUiEventArr;
        EnumEntriesKt.enumEntries(faceAuthUiEventArr);
    }

    public FaceAuthUiEvent(String str, int i, int i2, String str2) {
        this.id = i2;
        this.reason = str2;
    }

    public static FaceAuthUiEvent valueOf(String str) {
        return (FaceAuthUiEvent) Enum.valueOf(FaceAuthUiEvent.class, str);
    }

    public static FaceAuthUiEvent[] values() {
        return (FaceAuthUiEvent[]) $VALUES.clone();
    }

    public final int getExtraInfo() {
        return this.extraInfo;
    }

    public final int getId() {
        return this.id;
    }

    public final String getReason() {
        return this.reason;
    }

    public final void setExtraInfo(int i) {
        this.extraInfo = i;
    }
}

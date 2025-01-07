package com.android.systemui.qs.tiles.impl.sensorprivacy.ui;

import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface SensorPrivacyTileResources {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class CameraPrivacyTileResources implements SensorPrivacyTileResources {
        public static final CameraPrivacyTileResources INSTANCE = new CameraPrivacyTileResources();

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof CameraPrivacyTileResources);
        }

        @Override // com.android.systemui.qs.tiles.impl.sensorprivacy.ui.SensorPrivacyTileResources
        public final int getIconRes(boolean z) {
            return z ? R.drawable.qs_camera_access_icon_off : R.drawable.qs_camera_access_icon_on;
        }

        @Override // com.android.systemui.qs.tiles.impl.sensorprivacy.ui.SensorPrivacyTileResources
        public final int getTileLabelRes() {
            return R.string.quick_settings_camera_label;
        }

        public final int hashCode() {
            return -1523403657;
        }

        public final String toString() {
            return "CameraPrivacyTileResources";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MicrophonePrivacyTileResources implements SensorPrivacyTileResources {
        public static final MicrophonePrivacyTileResources INSTANCE = new MicrophonePrivacyTileResources();

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof MicrophonePrivacyTileResources);
        }

        @Override // com.android.systemui.qs.tiles.impl.sensorprivacy.ui.SensorPrivacyTileResources
        public final int getIconRes(boolean z) {
            return z ? R.drawable.qs_mic_access_off : R.drawable.qs_mic_access_on;
        }

        @Override // com.android.systemui.qs.tiles.impl.sensorprivacy.ui.SensorPrivacyTileResources
        public final int getTileLabelRes() {
            return R.string.quick_settings_mic_label;
        }

        public final int hashCode() {
            return 2116615292;
        }

        public final String toString() {
            return "MicrophonePrivacyTileResources";
        }
    }

    int getIconRes(boolean z);

    int getTileLabelRes();
}

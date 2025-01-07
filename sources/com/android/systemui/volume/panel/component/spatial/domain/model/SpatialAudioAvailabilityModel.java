package com.android.systemui.volume.panel.component.spatial.domain.model;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface SpatialAudioAvailabilityModel {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class HeadTracking implements SpatialAudio {
        public static final HeadTracking INSTANCE = new HeadTracking();

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof HeadTracking);
        }

        public final int hashCode() {
            return 614680126;
        }

        public final String toString() {
            return "HeadTracking";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface SpatialAudio extends SpatialAudioAvailabilityModel {

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class Companion implements SpatialAudio {
            public static final /* synthetic */ Companion $$INSTANCE = new Companion();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Unavailable implements SpatialAudioAvailabilityModel {
        public static final Unavailable INSTANCE = new Unavailable();

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof Unavailable);
        }

        public final int hashCode() {
            return 1618458089;
        }

        public final String toString() {
            return "Unavailable";
        }
    }
}

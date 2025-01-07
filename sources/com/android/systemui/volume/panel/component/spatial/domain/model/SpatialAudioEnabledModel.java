package com.android.systemui.volume.panel.component.spatial.domain.model;

import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface SpatialAudioEnabledModel {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static final List values = CollectionsKt__CollectionsKt.listOf(Disabled.INSTANCE, SpatialAudioEnabled.Companion.$$INSTANCE, HeadTrackingEnabled.INSTANCE);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Disabled implements SpatialAudioEnabledModel {
        public static final Disabled INSTANCE = new Disabled();

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof Disabled);
        }

        public final int hashCode() {
            return 569056123;
        }

        public final String toString() {
            return "Disabled";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class HeadTrackingEnabled implements SpatialAudioEnabled {
        public static final HeadTrackingEnabled INSTANCE = new HeadTrackingEnabled();

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof HeadTrackingEnabled);
        }

        public final int hashCode() {
            return 714776619;
        }

        public final String toString() {
            return "HeadTrackingEnabled";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface SpatialAudioEnabled extends SpatialAudioEnabledModel {

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class Companion implements SpatialAudioEnabled {
            public static final /* synthetic */ Companion $$INSTANCE = new Companion();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Unknown implements SpatialAudioEnabled {
        public static final Unknown INSTANCE = new Unknown();

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof Unknown);
        }

        public final int hashCode() {
            return 1387343723;
        }

        public final String toString() {
            return "Unknown";
        }
    }
}

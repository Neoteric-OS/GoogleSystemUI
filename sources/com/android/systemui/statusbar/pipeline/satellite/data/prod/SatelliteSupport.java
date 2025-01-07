package com.android.systemui.statusbar.pipeline.satellite.data.prod;

import android.telephony.satellite.SatelliteManager;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface SatelliteSupport {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class NotSupported implements SatelliteSupport {
        public static final NotSupported INSTANCE = new NotSupported();

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof NotSupported);
        }

        public final int hashCode() {
            return -1705600401;
        }

        public final String toString() {
            return "NotSupported";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Supported implements SatelliteSupport {
        public final SatelliteManager satelliteManager;

        public Supported(SatelliteManager satelliteManager) {
            this.satelliteManager = satelliteManager;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Supported) && Intrinsics.areEqual(this.satelliteManager, ((Supported) obj).satelliteManager);
        }

        public final int hashCode() {
            return this.satelliteManager.hashCode();
        }

        public final String toString() {
            return "Supported(satelliteManager=" + this.satelliteManager + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Unknown implements SatelliteSupport {
        public static final Unknown INSTANCE = new Unknown();

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof Unknown);
        }

        public final int hashCode() {
            return 1450120310;
        }

        public final String toString() {
            return "Unknown";
        }
    }
}

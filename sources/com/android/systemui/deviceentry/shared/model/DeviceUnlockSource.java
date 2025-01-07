package com.android.systemui.deviceentry.shared.model;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class DeviceUnlockSource {
    public final boolean dismissesLockscreen;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class BouncerInput extends DeviceUnlockSource {
        public static final BouncerInput INSTANCE = new BouncerInput(true);

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof BouncerInput);
        }

        public final int hashCode() {
            return 2089338940;
        }

        public final String toString() {
            return "BouncerInput";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class FaceWithBypass extends DeviceUnlockSource {
        public static final FaceWithBypass INSTANCE = new FaceWithBypass(true);

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof FaceWithBypass);
        }

        public final int hashCode() {
            return -35153465;
        }

        public final String toString() {
            return "FaceWithBypass";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class FaceWithoutBypass extends DeviceUnlockSource {
        public static final FaceWithoutBypass INSTANCE = new FaceWithoutBypass(false);

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof FaceWithoutBypass);
        }

        public final int hashCode() {
            return 975857847;
        }

        public final String toString() {
            return "FaceWithoutBypass";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Fingerprint extends DeviceUnlockSource {
        public static final Fingerprint INSTANCE = new Fingerprint(true);

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof Fingerprint);
        }

        public final int hashCode() {
            return -1818240984;
        }

        public final String toString() {
            return "Fingerprint";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TrustAgent extends DeviceUnlockSource {
        public static final TrustAgent INSTANCE = new TrustAgent(false);

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof TrustAgent);
        }

        public final int hashCode() {
            return -1523751991;
        }

        public final String toString() {
            return "TrustAgent";
        }
    }

    public DeviceUnlockSource(boolean z) {
        this.dismissesLockscreen = z;
    }
}

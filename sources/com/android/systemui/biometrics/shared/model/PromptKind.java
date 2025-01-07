package com.android.systemui.biometrics.shared.model;

import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface PromptKind {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Biometric implements PromptKind {
        public final BiometricModalities activeModalities;
        public final PaneType paneType;

        /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
        /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class PaneType {
            public static final /* synthetic */ PaneType[] $VALUES;
            public static final PaneType ONE_PANE_LARGE_SCREEN_LANDSCAPE;
            public static final PaneType ONE_PANE_NO_SENSOR_LANDSCAPE;
            public static final PaneType ONE_PANE_PORTRAIT;
            public static final PaneType TWO_PANE_LANDSCAPE;

            static {
                PaneType paneType = new PaneType("TWO_PANE_LANDSCAPE", 0);
                TWO_PANE_LANDSCAPE = paneType;
                PaneType paneType2 = new PaneType("ONE_PANE_PORTRAIT", 1);
                ONE_PANE_PORTRAIT = paneType2;
                PaneType paneType3 = new PaneType("ONE_PANE_NO_SENSOR_LANDSCAPE", 2);
                ONE_PANE_NO_SENSOR_LANDSCAPE = paneType3;
                PaneType paneType4 = new PaneType("ONE_PANE_LARGE_SCREEN_LANDSCAPE", 3);
                ONE_PANE_LARGE_SCREEN_LANDSCAPE = paneType4;
                PaneType[] paneTypeArr = {paneType, paneType2, paneType3, paneType4};
                $VALUES = paneTypeArr;
                EnumEntriesKt.enumEntries(paneTypeArr);
            }

            public static PaneType valueOf(String str) {
                return (PaneType) Enum.valueOf(PaneType.class, str);
            }

            public static PaneType[] values() {
                return (PaneType[]) $VALUES.clone();
            }
        }

        public Biometric(BiometricModalities biometricModalities, PaneType paneType) {
            this.activeModalities = biometricModalities;
            this.paneType = paneType;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Biometric)) {
                return false;
            }
            Biometric biometric = (Biometric) obj;
            return Intrinsics.areEqual(this.activeModalities, biometric.activeModalities) && this.paneType == biometric.paneType;
        }

        public final int hashCode() {
            return this.paneType.hashCode() + (this.activeModalities.hashCode() * 31);
        }

        @Override // com.android.systemui.biometrics.shared.model.PromptKind
        public final boolean isBiometric() {
            return true;
        }

        @Override // com.android.systemui.biometrics.shared.model.PromptKind
        public final boolean isCredential() {
            return DefaultImpls.isCredential(this);
        }

        @Override // com.android.systemui.biometrics.shared.model.PromptKind
        public final boolean isOnePaneNoSensorLandscapeBiometric() {
            return DefaultImpls.isOnePaneNoSensorLandscapeBiometric(this);
        }

        @Override // com.android.systemui.biometrics.shared.model.PromptKind
        public final boolean isTwoPaneLandscapeBiometric() {
            return DefaultImpls.isTwoPaneLandscapeBiometric(this);
        }

        public final String toString() {
            return "Biometric(activeModalities=" + this.activeModalities + ", paneType=" + this.paneType + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class DefaultImpls {
        public static boolean isCredential(PromptKind promptKind) {
            return (promptKind instanceof Pin) || (promptKind instanceof Pattern) || (promptKind instanceof Password);
        }

        public static boolean isOnePaneNoSensorLandscapeBiometric(PromptKind promptKind) {
            Biometric biometric = promptKind instanceof Biometric ? (Biometric) promptKind : null;
            return (biometric != null ? biometric.paneType : null) == Biometric.PaneType.ONE_PANE_NO_SENSOR_LANDSCAPE;
        }

        public static boolean isTwoPaneLandscapeBiometric(PromptKind promptKind) {
            Biometric biometric = promptKind instanceof Biometric ? (Biometric) promptKind : null;
            return (biometric != null ? biometric.paneType : null) == Biometric.PaneType.TWO_PANE_LANDSCAPE;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class None implements PromptKind {
        public static final None INSTANCE = new None();

        @Override // com.android.systemui.biometrics.shared.model.PromptKind
        public final boolean isBiometric() {
            return false;
        }

        @Override // com.android.systemui.biometrics.shared.model.PromptKind
        public final boolean isCredential() {
            return DefaultImpls.isCredential(this);
        }

        @Override // com.android.systemui.biometrics.shared.model.PromptKind
        public final boolean isOnePaneNoSensorLandscapeBiometric() {
            return DefaultImpls.isOnePaneNoSensorLandscapeBiometric(this);
        }

        @Override // com.android.systemui.biometrics.shared.model.PromptKind
        public final boolean isTwoPaneLandscapeBiometric() {
            return DefaultImpls.isTwoPaneLandscapeBiometric(this);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Password implements PromptKind {
        public static final Password INSTANCE = new Password();

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof Password);
        }

        public final int hashCode() {
            return 1266244087;
        }

        @Override // com.android.systemui.biometrics.shared.model.PromptKind
        public final boolean isBiometric() {
            return false;
        }

        @Override // com.android.systemui.biometrics.shared.model.PromptKind
        public final boolean isCredential() {
            return DefaultImpls.isCredential(this);
        }

        @Override // com.android.systemui.biometrics.shared.model.PromptKind
        public final boolean isOnePaneNoSensorLandscapeBiometric() {
            return DefaultImpls.isOnePaneNoSensorLandscapeBiometric(this);
        }

        @Override // com.android.systemui.biometrics.shared.model.PromptKind
        public final boolean isTwoPaneLandscapeBiometric() {
            return DefaultImpls.isTwoPaneLandscapeBiometric(this);
        }

        public final String toString() {
            return "Password";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Pattern implements PromptKind {
        public static final Pattern INSTANCE = new Pattern();

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof Pattern);
        }

        public final int hashCode() {
            return 873066676;
        }

        @Override // com.android.systemui.biometrics.shared.model.PromptKind
        public final boolean isBiometric() {
            return false;
        }

        @Override // com.android.systemui.biometrics.shared.model.PromptKind
        public final boolean isCredential() {
            return DefaultImpls.isCredential(this);
        }

        @Override // com.android.systemui.biometrics.shared.model.PromptKind
        public final boolean isOnePaneNoSensorLandscapeBiometric() {
            return DefaultImpls.isOnePaneNoSensorLandscapeBiometric(this);
        }

        @Override // com.android.systemui.biometrics.shared.model.PromptKind
        public final boolean isTwoPaneLandscapeBiometric() {
            return DefaultImpls.isTwoPaneLandscapeBiometric(this);
        }

        public final String toString() {
            return "Pattern";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Pin implements PromptKind {
        public static final Pin INSTANCE = new Pin();

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof Pin);
        }

        public final int hashCode() {
            return -693442375;
        }

        @Override // com.android.systemui.biometrics.shared.model.PromptKind
        public final boolean isBiometric() {
            return false;
        }

        @Override // com.android.systemui.biometrics.shared.model.PromptKind
        public final boolean isCredential() {
            return DefaultImpls.isCredential(this);
        }

        @Override // com.android.systemui.biometrics.shared.model.PromptKind
        public final boolean isOnePaneNoSensorLandscapeBiometric() {
            return DefaultImpls.isOnePaneNoSensorLandscapeBiometric(this);
        }

        @Override // com.android.systemui.biometrics.shared.model.PromptKind
        public final boolean isTwoPaneLandscapeBiometric() {
            return DefaultImpls.isTwoPaneLandscapeBiometric(this);
        }

        public final String toString() {
            return "Pin";
        }
    }

    boolean isBiometric();

    boolean isCredential();

    boolean isOnePaneNoSensorLandscapeBiometric();

    boolean isTwoPaneLandscapeBiometric();
}

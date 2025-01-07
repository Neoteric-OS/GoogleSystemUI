package com.google.android.msdl.data.repository;

import android.os.VibrationEffect;
import com.android.app.viewcapture.data.ViewNode;
import com.google.android.msdl.data.model.HapticComposition;
import com.google.android.msdl.data.model.HapticCompositionPrimitive;
import java.util.Collections;
import kotlin.collections.CollectionsKt__CollectionsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MSDLRepositoryImpl$Companion$HAPTIC_DATA$1 implements MSDLHapticData {
    public final /* synthetic */ int $r8$classId;
    public static final MSDLRepositoryImpl$Companion$HAPTIC_DATA$1 INSTANCE$1 = new MSDLRepositoryImpl$Companion$HAPTIC_DATA$1(1);
    public static final MSDLRepositoryImpl$Companion$HAPTIC_DATA$1 INSTANCE$2 = new MSDLRepositoryImpl$Companion$HAPTIC_DATA$1(2);
    public static final MSDLRepositoryImpl$Companion$HAPTIC_DATA$1 INSTANCE$3 = new MSDLRepositoryImpl$Companion$HAPTIC_DATA$1(3);
    public static final MSDLRepositoryImpl$Companion$HAPTIC_DATA$1 INSTANCE$4 = new MSDLRepositoryImpl$Companion$HAPTIC_DATA$1(4);
    public static final MSDLRepositoryImpl$Companion$HAPTIC_DATA$1 INSTANCE$5 = new MSDLRepositoryImpl$Companion$HAPTIC_DATA$1(5);
    public static final MSDLRepositoryImpl$Companion$HAPTIC_DATA$1 INSTANCE$6 = new MSDLRepositoryImpl$Companion$HAPTIC_DATA$1(6);
    public static final MSDLRepositoryImpl$Companion$HAPTIC_DATA$1 INSTANCE$7 = new MSDLRepositoryImpl$Companion$HAPTIC_DATA$1(7);
    public static final MSDLRepositoryImpl$Companion$HAPTIC_DATA$1 INSTANCE$8 = new MSDLRepositoryImpl$Companion$HAPTIC_DATA$1(8);
    public static final MSDLRepositoryImpl$Companion$HAPTIC_DATA$1 INSTANCE$9 = new MSDLRepositoryImpl$Companion$HAPTIC_DATA$1(9);
    public static final MSDLRepositoryImpl$Companion$HAPTIC_DATA$1 INSTANCE = new MSDLRepositoryImpl$Companion$HAPTIC_DATA$1(0);
    public static final MSDLRepositoryImpl$Companion$HAPTIC_DATA$1 INSTANCE$10 = new MSDLRepositoryImpl$Companion$HAPTIC_DATA$1(10);
    public static final MSDLRepositoryImpl$Companion$HAPTIC_DATA$1 INSTANCE$11 = new MSDLRepositoryImpl$Companion$HAPTIC_DATA$1(11);
    public static final MSDLRepositoryImpl$Companion$HAPTIC_DATA$1 INSTANCE$12 = new MSDLRepositoryImpl$Companion$HAPTIC_DATA$1(12);
    public static final MSDLRepositoryImpl$Companion$HAPTIC_DATA$1 INSTANCE$13 = new MSDLRepositoryImpl$Companion$HAPTIC_DATA$1(13);
    public static final MSDLRepositoryImpl$Companion$HAPTIC_DATA$1 INSTANCE$14 = new MSDLRepositoryImpl$Companion$HAPTIC_DATA$1(14);
    public static final MSDLRepositoryImpl$Companion$HAPTIC_DATA$1 INSTANCE$15 = new MSDLRepositoryImpl$Companion$HAPTIC_DATA$1(15);
    public static final MSDLRepositoryImpl$Companion$HAPTIC_DATA$1 INSTANCE$16 = new MSDLRepositoryImpl$Companion$HAPTIC_DATA$1(16);
    public static final MSDLRepositoryImpl$Companion$HAPTIC_DATA$1 INSTANCE$17 = new MSDLRepositoryImpl$Companion$HAPTIC_DATA$1(17);

    public /* synthetic */ MSDLRepositoryImpl$Companion$HAPTIC_DATA$1(int i) {
        this.$r8$classId = i;
    }

    @Override // com.google.android.msdl.data.repository.MSDLHapticData
    public final HapticComposition get() {
        switch (this.$r8$classId) {
            case 0:
                return new HapticComposition(CollectionsKt__CollectionsKt.listOf(new HapticCompositionPrimitive(3, 1.0f, 0), new HapticCompositionPrimitive(3, 1.0f, 56), new HapticCompositionPrimitive(3, 1.0f, 56)), VibrationEffect.createWaveform(MSDLRepositoryImpl.SPIN_WAVEFORM_TIMINGS, MSDLRepositoryImpl.SPIN_WAVEFORM_AMPLITUDES, -1));
            case 1:
                return new HapticComposition(Collections.singletonList(new HapticCompositionPrimitive(1, 0.7f, 0)), VibrationEffect.createPredefined(0));
            case 2:
                return new HapticComposition(Collections.singletonList(new HapticCompositionPrimitive(1, 0.5f, 0)), VibrationEffect.createPredefined(0));
            case 3:
                return new HapticComposition(Collections.singletonList(new HapticCompositionPrimitive(7, 1.0f, 0)), VibrationEffect.createPredefined(2));
            case 4:
                return new HapticComposition(Collections.singletonList(new HapticCompositionPrimitive(7, 0.5f, 0)), VibrationEffect.createPredefined(2));
            case 5:
                return new HapticComposition(Collections.singletonList(new HapticCompositionPrimitive(1, 0.3f, 0)), VibrationEffect.createPredefined(0));
            case 6:
                return new HapticComposition(Collections.singletonList(new HapticCompositionPrimitive(7, 0.7f, 0)), VibrationEffect.createPredefined(2));
            case 7:
                return new HapticComposition(Collections.singletonList(new HapticCompositionPrimitive(1, 0.7f, 0)), VibrationEffect.createPredefined(0));
            case 8:
                return new HapticComposition(Collections.singletonList(new HapticCompositionPrimitive(1, 0.7f, 0)), VibrationEffect.createPredefined(0));
            case 9:
                return new HapticComposition(Collections.singletonList(new HapticCompositionPrimitive(1, 1.0f, 0)), VibrationEffect.createPredefined(0));
            case 10:
                return new HapticComposition(CollectionsKt__CollectionsKt.listOf(new HapticCompositionPrimitive(1, 1.0f, 0), new HapticCompositionPrimitive(1, 1.0f, 114), new HapticCompositionPrimitive(1, 1.0f, 114)), VibrationEffect.createWaveform(new long[]{10, 10, 10, 114, 10, 10, 10, 114, 10, 10, 10}, new int[]{10, 255, 20, 0, 10, 255, 20, 0, 10, 255, 20}, -1));
            case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                return new HapticComposition(CollectionsKt__CollectionsKt.listOf(new HapticCompositionPrimitive(1, 1.0f, 0), new HapticCompositionPrimitive(1, 1.0f, 114)), VibrationEffect.createWaveform(new long[]{10, 10, 10, 114, 10, 10, 10}, new int[]{10, 255, 20, 0, 10, 255, 20}, -1));
            case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                return new HapticComposition(CollectionsKt__CollectionsKt.listOf(new HapticCompositionPrimitive(1, 1.0f, 0), new HapticCompositionPrimitive(1, 1.0f, 52)), VibrationEffect.createWaveform(new long[]{10, 10, 10, 52, 10, 10, 10}, new int[]{10, 255, 20, 0, 10, 255, 20}, -1));
            case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                return new HapticComposition(CollectionsKt__CollectionsKt.listOf(new HapticCompositionPrimitive(7, 1.0f, 0), new HapticCompositionPrimitive(1, 1.0f, 52)), VibrationEffect.createWaveform(new long[]{5, 52, 10, 10, 10}, new int[]{100, 0, 10, 255, 20}, -1));
            case ViewNode.SCALEY_FIELD_NUMBER /* 14 */:
                return new HapticComposition(Collections.singletonList(new HapticCompositionPrimitive(2, 1.0f, 0)), VibrationEffect.createWaveform(new long[]{50, 100, 100, 50}, new int[]{5, 50, 20, 10}, -1));
            case 15:
                return new HapticComposition(Collections.singletonList(new HapticCompositionPrimitive(1, 1.0f, 0)), VibrationEffect.createPredefined(0));
            case 16:
                return new HapticComposition(Collections.singletonList(new HapticCompositionPrimitive(1, 1.0f, 0)), VibrationEffect.createPredefined(0));
            default:
                return new HapticComposition(Collections.singletonList(new HapticCompositionPrimitive(1, 0.7f, 0)), VibrationEffect.createPredefined(0));
        }
    }
}

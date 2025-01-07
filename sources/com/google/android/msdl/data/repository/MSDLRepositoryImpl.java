package com.google.android.msdl.data.repository;

import com.google.android.msdl.data.model.HapticToken;
import java.util.Arrays;
import java.util.Map;
import kotlin.Pair;
import kotlin.collections.ArraysKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MSDLRepositoryImpl {
    public static final Map HAPTIC_DATA;
    public static final int[] SPIN_WAVEFORM_AMPLITUDES;
    public static final long[] SPIN_WAVEFORM_TIMINGS;

    static {
        long[] jArr = {20, 20, 3, 43, 20, 20, 3};
        int[] iArr = {40, 80, 40, 0, 40, 80, 40};
        long[] copyOf = Arrays.copyOf(jArr, 8);
        copyOf[7] = 56;
        int length = copyOf.length;
        long[] copyOf2 = Arrays.copyOf(copyOf, length + 7);
        System.arraycopy(jArr, 0, copyOf2, length, 7);
        Intrinsics.checkNotNull(copyOf2);
        int length2 = copyOf2.length;
        long[] copyOf3 = Arrays.copyOf(copyOf2, length2 + 1);
        copyOf3[length2] = 56;
        int length3 = copyOf3.length;
        long[] copyOf4 = Arrays.copyOf(copyOf3, length3 + 7);
        System.arraycopy(jArr, 0, copyOf4, length3, 7);
        Intrinsics.checkNotNull(copyOf4);
        SPIN_WAVEFORM_TIMINGS = copyOf4;
        int[] copyOf5 = Arrays.copyOf(iArr, 8);
        copyOf5[7] = 10;
        int[] plus = ArraysKt.plus(copyOf5, iArr);
        int length4 = plus.length;
        int[] copyOf6 = Arrays.copyOf(plus, length4 + 1);
        copyOf6[length4] = 10;
        SPIN_WAVEFORM_AMPLITUDES = ArraysKt.plus(copyOf6, iArr);
        HAPTIC_DATA = MapsKt.mapOf(new Pair(HapticToken.NEGATIVE_CONFIRMATION_HIGH_EMPHASIS, MSDLRepositoryImpl$Companion$HAPTIC_DATA$1.INSTANCE), new Pair(HapticToken.NEGATIVE_CONFIRMATION_MEDIUM_EMPHASIS, MSDLRepositoryImpl$Companion$HAPTIC_DATA$1.INSTANCE$10), new Pair(HapticToken.POSITIVE_CONFIRMATION_HIGH_EMPHASIS, MSDLRepositoryImpl$Companion$HAPTIC_DATA$1.INSTANCE$11), new Pair(HapticToken.POSITIVE_CONFIRMATION_MEDIUM_EMPHASIS, MSDLRepositoryImpl$Companion$HAPTIC_DATA$1.INSTANCE$12), new Pair(HapticToken.POSITIVE_CONFIRMATION_LOW_EMPHASIS, MSDLRepositoryImpl$Companion$HAPTIC_DATA$1.INSTANCE$13), new Pair(HapticToken.NEUTRAL_CONFIRMATION_HIGH_EMPHASIS, MSDLRepositoryImpl$Companion$HAPTIC_DATA$1.INSTANCE$14), new Pair(HapticToken.NEUTRAL_CONFIRMATION_MEDIUM_EMPHASIS, MSDLRepositoryImpl$Companion$HAPTIC_DATA$1.INSTANCE$15), new Pair(HapticToken.LONG_PRESS, MSDLRepositoryImpl$Companion$HAPTIC_DATA$1.INSTANCE$16), new Pair(HapticToken.SWIPE_THRESHOLD_INDICATOR, MSDLRepositoryImpl$Companion$HAPTIC_DATA$1.INSTANCE$17), new Pair(HapticToken.TAP_HIGH_EMPHASIS, MSDLRepositoryImpl$Companion$HAPTIC_DATA$1.INSTANCE$1), new Pair(HapticToken.TAP_MEDIUM_EMPHASIS, MSDLRepositoryImpl$Companion$HAPTIC_DATA$1.INSTANCE$2), new Pair(HapticToken.DRAG_THRESHOLD_INDICATOR, MSDLRepositoryImpl$Companion$HAPTIC_DATA$1.INSTANCE$3), new Pair(HapticToken.DRAG_INDICATOR, MSDLRepositoryImpl$Companion$HAPTIC_DATA$1.INSTANCE$4), new Pair(HapticToken.TAP_LOW_EMPHASIS, MSDLRepositoryImpl$Companion$HAPTIC_DATA$1.INSTANCE$5), new Pair(HapticToken.KEYPRESS_STANDARD, MSDLRepositoryImpl$Companion$HAPTIC_DATA$1.INSTANCE$6), new Pair(HapticToken.KEYPRESS_SPACEBAR, MSDLRepositoryImpl$Companion$HAPTIC_DATA$1.INSTANCE$7), new Pair(HapticToken.KEYPRESS_RETURN, MSDLRepositoryImpl$Companion$HAPTIC_DATA$1.INSTANCE$8), new Pair(HapticToken.KEYPRESS_DELETE, MSDLRepositoryImpl$Companion$HAPTIC_DATA$1.INSTANCE$9));
    }
}

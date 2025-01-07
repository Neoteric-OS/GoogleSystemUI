package com.android.compose.ui.util;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.ui.unit.Velocity;
import androidx.compose.ui.unit.VelocityKt;
import kotlin.NoWhenBranchMatchedException;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class SpaceVectorConverterKt {
    public static final SpaceVectorConverterKt$VerticalConverter$1 HorizontalConverter;
    public static final SpaceVectorConverterKt$VerticalConverter$1 VerticalConverter;

    /* JADX WARN: Type inference failed for: r0v0, types: [com.android.compose.ui.util.SpaceVectorConverterKt$VerticalConverter$1] */
    /* JADX WARN: Type inference failed for: r0v1, types: [com.android.compose.ui.util.SpaceVectorConverterKt$VerticalConverter$1] */
    static {
        final int i = 1;
        HorizontalConverter = new SpaceVectorConverter() { // from class: com.android.compose.ui.util.SpaceVectorConverterKt$VerticalConverter$1
            @Override // com.android.compose.ui.util.SpaceVectorConverter
            /* renamed from: toFloat-TH1AsA0 */
            public final float mo734toFloatTH1AsA0(long j) {
                switch (i) {
                    case 0:
                        return Velocity.m695getYimpl(j);
                    default:
                        return Velocity.m694getXimpl(j);
                }
            }

            @Override // com.android.compose.ui.util.SpaceVectorConverter
            /* renamed from: toFloat-k-4lQ0M */
            public final float mo735toFloatk4lQ0M(long j) {
                switch (i) {
                    case 0:
                        return Float.intBitsToFloat((int) (j & 4294967295L));
                    default:
                        return Float.intBitsToFloat((int) (j >> 32));
                }
            }

            @Override // com.android.compose.ui.util.SpaceVectorConverter
            /* renamed from: toOffset-tuRUvjQ */
            public final long mo736toOffsettuRUvjQ(float f) {
                switch (i) {
                    case 0:
                        return (Float.floatToRawIntBits(f) & 4294967295L) | (Float.floatToRawIntBits(0.0f) << 32);
                    default:
                        return (Float.floatToRawIntBits(f) << 32) | (Float.floatToRawIntBits(0.0f) & 4294967295L);
                }
            }

            @Override // com.android.compose.ui.util.SpaceVectorConverter
            /* renamed from: toVelocity-adjELrA */
            public final long mo737toVelocityadjELrA(float f) {
                switch (i) {
                    case 0:
                        return VelocityKt.Velocity(0.0f, f);
                    default:
                        return VelocityKt.Velocity(f, 0.0f);
                }
            }
        };
        final int i2 = 0;
        VerticalConverter = new SpaceVectorConverter() { // from class: com.android.compose.ui.util.SpaceVectorConverterKt$VerticalConverter$1
            @Override // com.android.compose.ui.util.SpaceVectorConverter
            /* renamed from: toFloat-TH1AsA0 */
            public final float mo734toFloatTH1AsA0(long j) {
                switch (i2) {
                    case 0:
                        return Velocity.m695getYimpl(j);
                    default:
                        return Velocity.m694getXimpl(j);
                }
            }

            @Override // com.android.compose.ui.util.SpaceVectorConverter
            /* renamed from: toFloat-k-4lQ0M */
            public final float mo735toFloatk4lQ0M(long j) {
                switch (i2) {
                    case 0:
                        return Float.intBitsToFloat((int) (j & 4294967295L));
                    default:
                        return Float.intBitsToFloat((int) (j >> 32));
                }
            }

            @Override // com.android.compose.ui.util.SpaceVectorConverter
            /* renamed from: toOffset-tuRUvjQ */
            public final long mo736toOffsettuRUvjQ(float f) {
                switch (i2) {
                    case 0:
                        return (Float.floatToRawIntBits(f) & 4294967295L) | (Float.floatToRawIntBits(0.0f) << 32);
                    default:
                        return (Float.floatToRawIntBits(f) << 32) | (Float.floatToRawIntBits(0.0f) & 4294967295L);
                }
            }

            @Override // com.android.compose.ui.util.SpaceVectorConverter
            /* renamed from: toVelocity-adjELrA */
            public final long mo737toVelocityadjELrA(float f) {
                switch (i2) {
                    case 0:
                        return VelocityKt.Velocity(0.0f, f);
                    default:
                        return VelocityKt.Velocity(f, 0.0f);
                }
            }
        };
    }

    public static final SpaceVectorConverter SpaceVectorConverter(Orientation orientation) {
        int ordinal = orientation.ordinal();
        if (ordinal == 0) {
            return VerticalConverter;
        }
        if (ordinal == 1) {
            return HorizontalConverter;
        }
        throw new NoWhenBranchMatchedException();
    }
}

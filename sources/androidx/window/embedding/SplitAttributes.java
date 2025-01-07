package androidx.window.embedding;

import androidx.window.core.FailedSpecification;
import androidx.window.core.ValidSpecification;
import androidx.window.core.VerificationMode;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SplitAttributes {
    public final EmbeddingAnimationParams animationParams;
    public final DividerAttributes dividerAttributes;
    public final LayoutDirection layoutDirection;
    public final SplitType splitType;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class LayoutDirection {
        public final String description;
        public static final LayoutDirection LOCALE = new LayoutDirection("LOCALE");
        public static final LayoutDirection LEFT_TO_RIGHT = new LayoutDirection("LEFT_TO_RIGHT");
        public static final LayoutDirection RIGHT_TO_LEFT = new LayoutDirection("RIGHT_TO_LEFT");
        public static final LayoutDirection TOP_TO_BOTTOM = new LayoutDirection("TOP_TO_BOTTOM");
        public static final LayoutDirection BOTTOM_TO_TOP = new LayoutDirection("BOTTOM_TO_TOP");

        public LayoutDirection(String str) {
            this.description = str;
        }

        public final String toString() {
            return this.description;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SplitType {
        public static final SplitType SPLIT_TYPE_EXPAND = new SplitType("expandContainers", 0.0f);
        public static final SplitType SPLIT_TYPE_HINGE;
        public final String description;
        public final float value;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public abstract class Companion {
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r0v6, types: [androidx.window.core.FailedSpecification] */
            public static SplitType ratio(final float f) {
                ValidSpecification validSpecification = new ValidSpecification(Float.valueOf(f), "SplitAttributes", VerificationMode.STRICT);
                Function1 function1 = new Function1() { // from class: androidx.window.embedding.SplitAttributes$SplitType$Companion$ratio$checkedRatio$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        ((Number) obj).floatValue();
                        double d = f;
                        return Boolean.valueOf(0.0d <= d && d <= 1.0d && !ArraysKt.contains(new Float[]{Float.valueOf(0.0f), Float.valueOf(1.0f)}, Float.valueOf(f)));
                    }
                };
                Object obj = validSpecification.value;
                if (!((Boolean) function1.invoke(obj)).booleanValue()) {
                    validSpecification = new FailedSpecification(obj, validSpecification.tag, "Ratio must be in range (0.0, 1.0). Use SplitType.expandContainers() instead of 0 or 1.", validSpecification.verificationMode);
                }
                Object compute = validSpecification.compute();
                Intrinsics.checkNotNull(compute);
                float floatValue = ((Number) compute).floatValue();
                return new SplitType("ratio:" + floatValue, floatValue);
            }
        }

        static {
            Companion.ratio(0.5f);
            SPLIT_TYPE_HINGE = new SplitType("hinge", -1.0f);
        }

        public SplitType(String str, float f) {
            this.description = str;
            this.value = f;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof SplitType)) {
                return false;
            }
            SplitType splitType = (SplitType) obj;
            return this.value == splitType.value && Intrinsics.areEqual(this.description, splitType.description);
        }

        public final int hashCode() {
            return (Float.hashCode(this.value) * 31) + this.description.hashCode();
        }

        public final String toString() {
            return this.description;
        }
    }

    public SplitAttributes(SplitType splitType, LayoutDirection layoutDirection, EmbeddingAnimationParams embeddingAnimationParams, DividerAttributes dividerAttributes) {
        this.splitType = splitType;
        this.layoutDirection = layoutDirection;
        this.animationParams = embeddingAnimationParams;
        this.dividerAttributes = dividerAttributes;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SplitAttributes)) {
            return false;
        }
        SplitAttributes splitAttributes = (SplitAttributes) obj;
        return Intrinsics.areEqual(this.splitType, splitAttributes.splitType) && Intrinsics.areEqual(this.layoutDirection, splitAttributes.layoutDirection) && Intrinsics.areEqual(this.animationParams, splitAttributes.animationParams) && Intrinsics.areEqual(this.dividerAttributes, splitAttributes.dividerAttributes);
    }

    public final int hashCode() {
        return this.dividerAttributes.hashCode() + ((this.animationParams.hashCode() + ((this.layoutDirection.hashCode() + (this.splitType.hashCode() * 31)) * 31)) * 31);
    }

    public final String toString() {
        return "SplitAttributes:{splitType=" + this.splitType + ", layoutDir=" + this.layoutDirection + ", animationParams=" + this.animationParams + ", dividerAttributes=" + this.dividerAttributes + " }";
    }
}

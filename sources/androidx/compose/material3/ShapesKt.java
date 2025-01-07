package androidx.compose.material3;

import androidx.compose.foundation.shape.CornerBasedShape;
import androidx.compose.foundation.shape.CornerSize;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.tokens.ShapeKeyTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.StaticProvidableCompositionLocal;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.graphics.Shape;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ShapesKt {
    public static final StaticProvidableCompositionLocal LocalShapes = new StaticProvidableCompositionLocal(new Function0() { // from class: androidx.compose.material3.ShapesKt$LocalShapes$1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return new Shapes(ShapeDefaults.ExtraSmall, ShapeDefaults.Small, ShapeDefaults.Medium, ShapeDefaults.Large, ShapeDefaults.ExtraLarge);
        }
    });

    public static final Shape getValue(ShapeKeyTokens shapeKeyTokens, Composer composer) {
        OpaqueKey opaqueKey = ComposerKt.invocation;
        Shapes shapes = MaterialTheme.getShapes(composer);
        switch (shapeKeyTokens.ordinal()) {
            case 0:
                return shapes.extraLarge;
            case 1:
                return top$default(shapes.extraLarge);
            case 2:
                return shapes.extraSmall;
            case 3:
                return top$default(shapes.extraSmall);
            case 4:
                return RoundedCornerShapeKt.CircleShape;
            case 5:
                return shapes.large;
            case 6:
                CornerBasedShape cornerBasedShape = shapes.large;
                CornerSize cornerSize = ShapeDefaults.CornerNone;
                return CornerBasedShape.copy$default(cornerBasedShape, cornerSize, null, cornerSize, 6);
            case 7:
                return top$default(shapes.large);
            case 8:
                return shapes.medium;
            case 9:
                return RectangleShapeKt.RectangleShape;
            case 10:
                return shapes.small;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public static RoundedCornerShape top$default(CornerBasedShape cornerBasedShape) {
        CornerSize cornerSize = ShapeDefaults.CornerNone;
        return CornerBasedShape.copy$default(cornerBasedShape, null, cornerSize, cornerSize, 3);
    }
}

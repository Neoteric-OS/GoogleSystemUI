package com.android.systemui.qs.panels.ui.compose;

import androidx.compose.material.icons.filled.EditKt;
import androidx.compose.material3.IconKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.internal.ComposableLambdaImpl;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.graphics.vector.PathBuilder;
import androidx.compose.ui.graphics.vector.VectorKt;
import androidx.compose.ui.res.StringResources_androidKt;
import com.android.wm.shell.R;
import kotlin.Unit;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class ComposableSingletons$PaginatedGridLayoutKt {

    /* renamed from: lambda-1, reason: not valid java name */
    public static final ComposableLambdaImpl f40lambda1 = new ComposableLambdaImpl(734215424, false, new Function2() { // from class: com.android.systemui.qs.panels.ui.compose.ComposableSingletons$PaginatedGridLayoutKt$lambda-1$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            Composer composer = (Composer) obj;
            if ((((Number) obj2).intValue() & 11) == 2) {
                ComposerImpl composerImpl = (ComposerImpl) composer;
                if (composerImpl.getSkipping()) {
                    composerImpl.skipToGroupEnd();
                    return Unit.INSTANCE;
                }
            }
            OpaqueKey opaqueKey = ComposerKt.invocation;
            ImageVector imageVector = EditKt._edit;
            if (imageVector == null) {
                ImageVector.Builder builder = new ImageVector.Builder("Filled.Edit", 24.0f, 24.0f, 24.0f, 24.0f, 0L, 0, false, 96);
                EmptyList emptyList = VectorKt.EmptyPath;
                SolidColor solidColor = new SolidColor(Color.Black);
                PathBuilder pathBuilder = new PathBuilder();
                pathBuilder.moveTo(3.0f, 17.25f);
                pathBuilder.verticalLineTo(21.0f);
                pathBuilder.horizontalLineToRelative(3.75f);
                pathBuilder.lineTo(17.81f, 9.94f);
                pathBuilder.lineToRelative(-3.75f, -3.75f);
                pathBuilder.lineTo(3.0f, 17.25f);
                pathBuilder.close();
                pathBuilder.moveTo(20.71f, 7.04f);
                pathBuilder.curveToRelative(0.39f, -0.39f, 0.39f, -1.02f, 0.0f, -1.41f);
                pathBuilder.lineToRelative(-2.34f, -2.34f);
                pathBuilder.curveToRelative(-0.39f, -0.39f, -1.02f, -0.39f, -1.41f, 0.0f);
                pathBuilder.lineToRelative(-1.83f, 1.83f);
                pathBuilder.lineToRelative(3.75f, 3.75f);
                pathBuilder.lineToRelative(1.83f, -1.83f);
                pathBuilder.close();
                builder.m440addPathoIyEayM(1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 0.0f, 0, 0, 2, solidColor, null, "", pathBuilder._nodes);
                imageVector = builder.build();
                EditKt._edit = imageVector;
            }
            IconKt.m214Iconww6aTOc(imageVector, StringResources_androidKt.stringResource(R.string.qs_edit, composer), (Modifier) null, 0L, composer, 0, 12);
            return Unit.INSTANCE;
        }
    });
}

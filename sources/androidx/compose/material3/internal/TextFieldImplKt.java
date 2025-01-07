package androidx.compose.material3.internal;

import androidx.compose.material3.AppBarKt$$ExternalSyntheticOutline0;
import androidx.compose.material3.ContentColorKt;
import androidx.compose.material3.InteractiveComponentSizeKt;
import androidx.compose.material3.tokens.SmallIconButtonTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.CacheDrawScope;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.graphics.ColorProducer;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.OutlineKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.unit.Dp;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class TextFieldImplKt {
    public static final float MinFocusedLabelLineHeight;
    public static final float MinSupportingTextLineHeight;
    public static final float TextFieldPadding;
    public static final float SupportingTopPadding = 4;
    public static final float PrefixSuffixTextPadding = 2;
    public static final float MinTextLineHeight = 24;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[TextFieldType.values().length];
            try {
                iArr[0] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[1] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[InputPhase.values().length];
            try {
                iArr2[0] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr2[1] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr2[2] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    static {
        float f = 16;
        TextFieldPadding = f;
        MinFocusedLabelLineHeight = f;
        MinSupportingTextLineHeight = f;
    }

    /* JADX WARN: Code restructure failed: missing block: B:239:0x0592, code lost:
    
        if (r10 == r9) goto L286;
     */
    /* JADX WARN: Code restructure failed: missing block: B:368:0x03db, code lost:
    
        if (r35 != false) goto L229;
     */
    /* JADX WARN: Code restructure failed: missing block: B:372:0x03ad, code lost:
    
        if (r35 != false) goto L217;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:169:0x02e2  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0339 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0367 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:186:0x039e  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x03cc  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x041d  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x044a  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x04a3 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:223:0x04d0  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x04ea A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:230:0x0511  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x0537  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x0590  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x05f0  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x0603  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x0641  */
    /* JADX WARN: Removed duplicated region for block: B:252:0x0658  */
    /* JADX WARN: Removed duplicated region for block: B:255:0x0677  */
    /* JADX WARN: Removed duplicated region for block: B:262:0x06a3  */
    /* JADX WARN: Removed duplicated region for block: B:266:0x06ba  */
    /* JADX WARN: Removed duplicated region for block: B:269:0x06da  */
    /* JADX WARN: Removed duplicated region for block: B:274:0x0702  */
    /* JADX WARN: Removed duplicated region for block: B:278:0x071b  */
    /* JADX WARN: Removed duplicated region for block: B:283:0x0741  */
    /* JADX WARN: Removed duplicated region for block: B:286:0x075a  */
    /* JADX WARN: Removed duplicated region for block: B:289:0x076e  */
    /* JADX WARN: Removed duplicated region for block: B:292:0x0787  */
    /* JADX WARN: Removed duplicated region for block: B:295:0x079d  */
    /* JADX WARN: Removed duplicated region for block: B:298:0x07b7  */
    /* JADX WARN: Removed duplicated region for block: B:301:0x07d3  */
    /* JADX WARN: Removed duplicated region for block: B:317:0x0887  */
    /* JADX WARN: Removed duplicated region for block: B:318:0x07ba  */
    /* JADX WARN: Removed duplicated region for block: B:319:0x07a0  */
    /* JADX WARN: Removed duplicated region for block: B:324:0x078a  */
    /* JADX WARN: Removed duplicated region for block: B:325:0x0771  */
    /* JADX WARN: Removed duplicated region for block: B:330:0x075c  */
    /* JADX WARN: Removed duplicated region for block: B:331:0x0744  */
    /* JADX WARN: Removed duplicated region for block: B:337:0x0706  */
    /* JADX WARN: Removed duplicated region for block: B:343:0x06cd  */
    /* JADX WARN: Removed duplicated region for block: B:344:0x06a7  */
    /* JADX WARN: Removed duplicated region for block: B:350:0x066b  */
    /* JADX WARN: Removed duplicated region for block: B:351:0x0645  */
    /* JADX WARN: Removed duplicated region for block: B:356:0x0613  */
    /* JADX WARN: Removed duplicated region for block: B:358:0x0595  */
    /* JADX WARN: Removed duplicated region for block: B:359:0x053d  */
    /* JADX WARN: Removed duplicated region for block: B:360:0x0516  */
    /* JADX WARN: Removed duplicated region for block: B:362:0x04d4  */
    /* JADX WARN: Type inference failed for: r0v61, types: [androidx.compose.material3.internal.TextFieldImplKt$CommonDecorationBox$3$containerWithId$1, kotlin.jvm.internal.Lambda] */
    /* JADX WARN: Type inference failed for: r10v25, types: [androidx.compose.material3.internal.TextFieldImplKt$CommonDecorationBox$3$decoratedSuffix$1, kotlin.jvm.internal.Lambda] */
    /* JADX WARN: Type inference failed for: r11v29, types: [androidx.compose.material3.internal.TextFieldImplKt$CommonDecorationBox$3$decoratedLabel$1$1, kotlin.jvm.internal.Lambda] */
    /* JADX WARN: Type inference failed for: r11v34, types: [androidx.compose.material3.internal.TextFieldImplKt$CommonDecorationBox$3$decoratedPrefix$1, kotlin.jvm.internal.Lambda] */
    /* JADX WARN: Type inference failed for: r1v34, types: [androidx.compose.material3.internal.TextFieldImplKt$CommonDecorationBox$3$borderContainerWithId$1, kotlin.jvm.internal.Lambda] */
    /* JADX WARN: Type inference failed for: r2v10, types: [androidx.compose.material3.internal.TextFieldImplKt$CommonDecorationBox$3$decoratedTrailing$1$1, kotlin.jvm.internal.Lambda] */
    /* JADX WARN: Type inference failed for: r2v12, types: [androidx.compose.material3.internal.TextFieldImplKt$CommonDecorationBox$3$decoratedSupporting$1$1, kotlin.jvm.internal.Lambda] */
    /* JADX WARN: Type inference failed for: r2v8, types: [androidx.compose.material3.internal.TextFieldImplKt$CommonDecorationBox$3$decoratedLeading$1$1, kotlin.jvm.internal.Lambda] */
    /* JADX WARN: Type inference failed for: r6v21 */
    /* JADX WARN: Type inference failed for: r6v22, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r6v48 */
    /* JADX WARN: Type inference failed for: r7v17 */
    /* JADX WARN: Type inference failed for: r7v18, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r7v36 */
    /* JADX WARN: Type inference failed for: r7v37, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r7v56, types: [androidx.compose.material3.internal.TextFieldImplKt$CommonDecorationBox$3$decoratedPlaceholder$1, kotlin.jvm.internal.Lambda] */
    /* JADX WARN: Type inference failed for: r7v58 */
    /* JADX WARN: Type inference failed for: r7v61 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void CommonDecorationBox(final androidx.compose.material3.internal.TextFieldType r48, final java.lang.CharSequence r49, final kotlin.jvm.functions.Function2 r50, final androidx.compose.material3.TextFieldLabelPosition r51, final kotlin.jvm.functions.Function3 r52, final kotlin.jvm.functions.Function2 r53, final kotlin.jvm.functions.Function2 r54, final kotlin.jvm.functions.Function2 r55, final kotlin.jvm.functions.Function2 r56, final kotlin.jvm.functions.Function2 r57, final kotlin.jvm.functions.Function2 r58, final boolean r59, final boolean r60, final boolean r61, final androidx.compose.foundation.interaction.InteractionSource r62, final androidx.compose.foundation.layout.PaddingValues r63, final androidx.compose.material3.TextFieldColors r64, final kotlin.jvm.functions.Function2 r65, androidx.compose.runtime.Composer r66, final int r67, final int r68) {
        /*
            Method dump skipped, instructions count: 2334
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.internal.TextFieldImplKt.CommonDecorationBox(androidx.compose.material3.internal.TextFieldType, java.lang.CharSequence, kotlin.jvm.functions.Function2, androidx.compose.material3.TextFieldLabelPosition, kotlin.jvm.functions.Function3, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, boolean, boolean, boolean, androidx.compose.foundation.interaction.InteractionSource, androidx.compose.foundation.layout.PaddingValues, androidx.compose.material3.TextFieldColors, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int, int):void");
    }

    /* renamed from: access$Decoration-3J-VO9M, reason: not valid java name */
    public static final void m246access$Decoration3JVO9M(final long j, final TextStyle textStyle, final Function2 function2, Composer composer, final int i) {
        int i2;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(1208685580);
        if ((i & 6) == 0) {
            i2 = (composerImpl.changed(j) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerImpl.changed(textStyle) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerImpl.changedInstance(function2) ? 256 : 128;
        }
        if ((i2 & 147) == 146 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            ProvideContentColorTextStyleKt.m244ProvideContentColorTextStyle3JVO9M(j, textStyle, function2, composerImpl, i2 & 1022);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: androidx.compose.material3.internal.TextFieldImplKt$Decoration$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    TextFieldImplKt.m246access$Decoration3JVO9M(j, textStyle, function2, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    /* renamed from: access$Decoration-Iv8Zu3U, reason: not valid java name */
    public static final void m247access$DecorationIv8Zu3U(final long j, final Function2 function2, Composer composer, final int i) {
        int i2;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(660142980);
        if ((i & 6) == 0) {
            i2 = (composerImpl.changed(j) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerImpl.changedInstance(function2) ? 32 : 16;
        }
        if ((i2 & 19) == 18 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            CompositionLocalKt.CompositionLocalProvider(AppBarKt$$ExternalSyntheticOutline0.m(j, ContentColorKt.LocalContentColor), function2, composerImpl, (i2 & 112) | 8);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: androidx.compose.material3.internal.TextFieldImplKt$Decoration$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    TextFieldImplKt.m247access$DecorationIv8Zu3U(j, function2, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public static final Modifier textFieldBackground(Modifier modifier, final ColorProducer colorProducer, final Shape shape) {
        return DrawModifierKt.drawWithCache(modifier, new Function1() { // from class: androidx.compose.material3.internal.TextFieldImplKt$textFieldBackground$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                CacheDrawScope cacheDrawScope = (CacheDrawScope) obj;
                final Outline mo37createOutlinePq9zytI = Shape.this.mo37createOutlinePq9zytI(cacheDrawScope.cacheParams.mo279getSizeNHjbRc(), cacheDrawScope.cacheParams.getLayoutDirection(), cacheDrawScope);
                final ColorProducer colorProducer2 = colorProducer;
                return cacheDrawScope.onDrawBehind(new Function1() { // from class: androidx.compose.material3.internal.TextFieldImplKt$textFieldBackground$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        OutlineKt.m387drawOutlinewDX37Ww$default((DrawScope) obj2, Outline.this, colorProducer2.mo206invoke0d7_KjU());
                        return Unit.INSTANCE;
                    }
                });
            }
        });
    }

    public static final float textFieldHorizontalIconPadding(Composer composer) {
        OpaqueKey opaqueKey = ComposerKt.invocation;
        float f = ((Dp) ((ComposerImpl) composer).consume(InteractiveComponentSizeKt.LocalMinimumInteractiveComponentSize)).value;
        if (Float.isNaN(f)) {
            f = 0;
        }
        return RangesKt.coerceAtLeast((f - SmallIconButtonTokens.IconSize) / 2, 0);
    }
}

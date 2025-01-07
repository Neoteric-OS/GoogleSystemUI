package androidx.compose.material3;

import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.State;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.SaverKt;
import androidx.compose.runtime.saveable.SaverKt$Saver$1;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.ReusableGraphicsLayerScope;
import androidx.compose.ui.graphics.TransformOriginKt;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.input.pointer.SuspendPointerInputElement;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt$sam$androidx_compose_ui_input_pointer_PointerInputEventHandler$0;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsProperties;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyKey;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.util.MathHelpersKt;
import com.android.wm.shell.R;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ModalBottomSheetKt {
    public static final float PredictiveBackMaxScaleXDistance = 48;
    public static final float PredictiveBackMaxScaleYDistance = 24;
    public static final long PredictiveBackChildTransformOrigin = TransformOriginKt.TransformOrigin(0.5f, 0.0f);

    /* JADX WARN: Removed duplicated region for block: B:130:0x03b8  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x03e1  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x0412  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0430  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x045b  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x04bb  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x045d  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x0414  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x03e3  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x021d  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x0229  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x0235  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x0243  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x0257  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x0269  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x0274  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x0281  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x02a0  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x02ab  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x02b4  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x02bd  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x02b0  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x02a5  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x029a  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x027a  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x0270  */
    /* JADX WARN: Removed duplicated region for block: B:243:0x023c  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x0231  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x0222  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x015e  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:262:0x0124  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:272:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:280:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:282:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:285:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0159  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0175  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0197  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x01d2  */
    /* JADX WARN: Type inference failed for: r14v33, types: [androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$4, kotlin.jvm.internal.Lambda] */
    /* renamed from: ModalBottomSheet-dYc4hso, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m215ModalBottomSheetdYc4hso(final kotlin.jvm.functions.Function0 r48, androidx.compose.ui.Modifier r49, androidx.compose.material3.SheetState r50, float r51, androidx.compose.ui.graphics.Shape r52, long r53, long r55, float r57, long r58, kotlin.jvm.functions.Function2 r60, kotlin.jvm.functions.Function2 r61, androidx.compose.material3.ModalBottomSheetProperties r62, final kotlin.jvm.functions.Function3 r63, androidx.compose.runtime.Composer r64, final int r65, final int r66, final int r67) {
        /*
            Method dump skipped, instructions count: 1308
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ModalBottomSheetKt.m215ModalBottomSheetdYc4hso(kotlin.jvm.functions.Function0, androidx.compose.ui.Modifier, androidx.compose.material3.SheetState, float, androidx.compose.ui.graphics.Shape, long, long, float, long, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, androidx.compose.material3.ModalBottomSheetProperties, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:112:0x0302  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x031d A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0338  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0351 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0375  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0383  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x038c A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:139:0x03b1 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x03c6  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x03d9  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x03eb A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0385  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0378  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x034a  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x0314  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x024b  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0257  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0266  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0271  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x0287  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x02a8  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x02b3  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x02ba  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x02c1  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x02d5  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x02b6  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x02af  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x029e  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x026b  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x0262  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x0250  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x01be  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x01b2  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x0183  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x0163  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x013c  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:247:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0127  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0142  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x015e  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x017e  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x019a  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x01b9  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x01d6  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0460  */
    /* JADX WARN: Removed duplicated region for block: B:89:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0201  */
    /* JADX WARN: Type inference failed for: r1v25, types: [androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7, kotlin.jvm.internal.Lambda] */
    /* renamed from: ModalBottomSheetContent-IQkwcL4, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m216ModalBottomSheetContentIQkwcL4(final androidx.compose.foundation.layout.BoxScope r46, final androidx.compose.animation.core.Animatable r47, final kotlinx.coroutines.CoroutineScope r48, final kotlin.jvm.functions.Function0 r49, final kotlin.jvm.functions.Function1 r50, androidx.compose.ui.Modifier r51, androidx.compose.material3.SheetState r52, float r53, androidx.compose.ui.graphics.Shape r54, long r55, long r57, float r59, kotlin.jvm.functions.Function2 r60, kotlin.jvm.functions.Function2 r61, final kotlin.jvm.functions.Function3 r62, androidx.compose.runtime.Composer r63, final int r64, final int r65, final int r66) {
        /*
            Method dump skipped, instructions count: 1155
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ModalBottomSheetKt.m216ModalBottomSheetContentIQkwcL4(androidx.compose.foundation.layout.BoxScope, androidx.compose.animation.core.Animatable, kotlinx.coroutines.CoroutineScope, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, androidx.compose.material3.SheetState, float, androidx.compose.ui.graphics.Shape, long, long, float, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* renamed from: access$Scrim-3J-VO9M, reason: not valid java name */
    public static final void m217access$Scrim3JVO9M(final long j, final Function0 function0, final boolean z, Composer composer, final int i) {
        int i2;
        Modifier then;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(951870469);
        if ((i & 6) == 0) {
            i2 = (composerImpl.changed(j) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerImpl.changedInstance(function0) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerImpl.changed(z) ? 256 : 128;
        }
        int i3 = i2;
        if ((i3 & 147) == 146 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            if (j != 16) {
                float f = z ? 1.0f : 0.0f;
                Object motionScheme = MaterialTheme.getMotionScheme(composerImpl);
                composerImpl.startReplaceGroup(-1558791992);
                boolean changed = composerImpl.changed(Reflection.getOrCreateKotlinClass(Float.class)) | composerImpl.changed(motionScheme);
                Object rememberedValue = composerImpl.rememberedValue();
                Object obj = Composer.Companion.Empty;
                if (changed || rememberedValue == obj) {
                    ((MotionSchemeKt$standardMotionScheme$1) motionScheme).getClass();
                    rememberedValue = AnimationSpecKt.spring$default(1.0f, 1600.0f, null, 4);
                    composerImpl.updateRememberedValue(rememberedValue);
                }
                composerImpl.end(false);
                final State animateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, (FiniteAnimationSpec) rememberedValue, null, null, composerImpl, 0, 28);
                final String m245getString2EP1pXo = Strings_androidKt.m245getString2EP1pXo(R.string.close_sheet, composerImpl);
                composerImpl.startReplaceGroup(-1785602766);
                Modifier modifier = Modifier.Companion.$$INSTANCE;
                if (z) {
                    int i4 = i3 & 112;
                    boolean z2 = i4 == 32;
                    Object rememberedValue2 = composerImpl.rememberedValue();
                    if (z2 || rememberedValue2 == obj) {
                        rememberedValue2 = new ModalBottomSheetKt$Scrim$dismissSheet$1$1(function0, null);
                        composerImpl.updateRememberedValue(rememberedValue2);
                    }
                    then = modifier.then(new SuspendPointerInputElement(function0, null, null, new SuspendingPointerInputFilterKt$sam$androidx_compose_ui_input_pointer_PointerInputEventHandler$0((Function2) rememberedValue2), 6));
                    boolean changed2 = (i4 == 32) | composerImpl.changed(m245getString2EP1pXo);
                    Object rememberedValue3 = composerImpl.rememberedValue();
                    if (changed2 || rememberedValue3 == obj) {
                        rememberedValue3 = new Function1() { // from class: androidx.compose.material3.ModalBottomSheetKt$Scrim$dismissSheet$2$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                SemanticsPropertyReceiver semanticsPropertyReceiver = (SemanticsPropertyReceiver) obj2;
                                KProperty[] kPropertyArr = SemanticsPropertiesKt.$$delegatedProperties;
                                SemanticsPropertyKey semanticsPropertyKey = SemanticsProperties.TraversalIndex;
                                KProperty kProperty = SemanticsPropertiesKt.$$delegatedProperties[9];
                                semanticsPropertyKey.setValue(semanticsPropertyReceiver, Float.valueOf(1.0f));
                                SemanticsPropertiesKt.setContentDescription(semanticsPropertyReceiver, m245getString2EP1pXo);
                                final Function0 function02 = function0;
                                SemanticsPropertiesKt.onClick(semanticsPropertyReceiver, null, new Function0() { // from class: androidx.compose.material3.ModalBottomSheetKt$Scrim$dismissSheet$2$1.1
                                    {
                                        super(0);
                                    }

                                    @Override // kotlin.jvm.functions.Function0
                                    public final Object invoke() {
                                        Function0.this.invoke();
                                        return Boolean.TRUE;
                                    }
                                });
                                return Unit.INSTANCE;
                            }
                        };
                        composerImpl.updateRememberedValue(rememberedValue3);
                    }
                    modifier = SemanticsModifierKt.semantics(then, true, (Function1) rememberedValue3);
                }
                composerImpl.end(false);
                Modifier then2 = SizeKt.FillWholeMaxSize.then(modifier);
                boolean changed3 = composerImpl.changed(animateFloatAsState) | ((i3 & 14) == 4);
                Object rememberedValue4 = composerImpl.rememberedValue();
                if (changed3 || rememberedValue4 == obj) {
                    rememberedValue4 = new Function1() { // from class: androidx.compose.material3.ModalBottomSheetKt$Scrim$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            DrawScope drawScope = (DrawScope) obj2;
                            long j2 = j;
                            State state = animateFloatAsState;
                            float f2 = ModalBottomSheetKt.PredictiveBackMaxScaleXDistance;
                            drawScope.mo415drawRectnJ9OG0(j2, 0L, (r19 & 4) != 0 ? DrawScope.m430offsetSizePENXr5M(drawScope.mo432getSizeNHjbRc(), 0L) : 0L, (r19 & 8) != 0 ? 1.0f : RangesKt.coerceIn(((Number) state.getValue()).floatValue(), 0.0f, 1.0f), (r19 & 32) != 0 ? null : null, (r19 & 64) != 0 ? 3 : 0);
                            return Unit.INSTANCE;
                        }
                    };
                    composerImpl.updateRememberedValue(rememberedValue4);
                }
                CanvasKt.Canvas(then2, (Function1) rememberedValue4, composerImpl, 0);
            }
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: androidx.compose.material3.ModalBottomSheetKt$Scrim$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    ((Number) obj3).intValue();
                    ModalBottomSheetKt.m217access$Scrim3JVO9M(j, function0, z, (Composer) obj2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public static final float access$calculatePredictiveBackScaleX(GraphicsLayerScope graphicsLayerScope, float f) {
        ReusableGraphicsLayerScope reusableGraphicsLayerScope = (ReusableGraphicsLayerScope) graphicsLayerScope;
        float m329getWidthimpl = Size.m329getWidthimpl(reusableGraphicsLayerScope.size);
        if (Float.isNaN(m329getWidthimpl) || m329getWidthimpl == 0.0f) {
            return 1.0f;
        }
        return 1.0f - (MathHelpersKt.lerp(0.0f, Math.min(reusableGraphicsLayerScope.graphicsDensity.getDensity() * PredictiveBackMaxScaleXDistance, m329getWidthimpl), f) / m329getWidthimpl);
    }

    public static final float access$calculatePredictiveBackScaleY(GraphicsLayerScope graphicsLayerScope, float f) {
        ReusableGraphicsLayerScope reusableGraphicsLayerScope = (ReusableGraphicsLayerScope) graphicsLayerScope;
        float m327getHeightimpl = Size.m327getHeightimpl(reusableGraphicsLayerScope.size);
        if (Float.isNaN(m327getHeightimpl) || m327getHeightimpl == 0.0f) {
            return 1.0f;
        }
        return 1.0f - (MathHelpersKt.lerp(0.0f, Math.min(reusableGraphicsLayerScope.graphicsDensity.getDensity() * PredictiveBackMaxScaleYDistance, m327getHeightimpl), f) / m327getHeightimpl);
    }

    public static final SheetState rememberModalBottomSheetState(int i, int i2, Composer composer) {
        final SheetValue sheetValue = SheetValue.Hidden;
        boolean z = true;
        final boolean z2 = (i2 & 1) == 0;
        final ModalBottomSheetKt$rememberModalBottomSheetState$1 modalBottomSheetKt$rememberModalBottomSheetState$1 = new Function1() { // from class: androidx.compose.material3.ModalBottomSheetKt$rememberModalBottomSheetState$1
            @Override // kotlin.jvm.functions.Function1
            public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return Boolean.TRUE;
            }
        };
        OpaqueKey opaqueKey = ComposerKt.invocation;
        int i3 = (i & 14) | 384;
        float f = SheetDefaultsKt.DragHandleVerticalPadding;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        final Density density = (Density) composerImpl.consume(CompositionLocalsKt.LocalDensity);
        Object[] objArr = {Boolean.valueOf(z2), modalBottomSheetKt$rememberModalBottomSheetState$1, Boolean.FALSE};
        SheetState$Companion$Saver$1 sheetState$Companion$Saver$1 = new Function2() { // from class: androidx.compose.material3.SheetState$Companion$Saver$1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return (SheetValue) ((SnapshotMutableStateImpl) ((SheetState) obj2).anchoredDraggableState.currentValue$delegate).getValue();
            }
        };
        Function1 function1 = new Function1() { // from class: androidx.compose.material3.SheetState$Companion$Saver$2
            final /* synthetic */ boolean $skipHiddenState = false;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return new SheetState(z2, density, (SheetValue) obj, modalBottomSheetKt$rememberModalBottomSheetState$1, this.$skipHiddenState);
            }
        };
        SaverKt$Saver$1 saverKt$Saver$1 = SaverKt.AutoSaver;
        SaverKt$Saver$1 saverKt$Saver$12 = new SaverKt$Saver$1(sheetState$Companion$Saver$1, function1);
        if ((((i3 & 14) ^ 6) <= 4 || !composerImpl.changed(z2)) && (i3 & 6) != 4) {
            z = false;
        }
        boolean changed = composerImpl.changed(density) | z | composerImpl.changed(modalBottomSheetKt$rememberModalBottomSheetState$1) | composerImpl.changed(false);
        Object rememberedValue = composerImpl.rememberedValue();
        if (changed || rememberedValue == Composer.Companion.Empty) {
            rememberedValue = new Function0() { // from class: androidx.compose.material3.SheetDefaultsKt$rememberSheetState$2$1
                final /* synthetic */ boolean $skipHiddenState = false;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return new SheetState(z2, density, sheetValue, modalBottomSheetKt$rememberModalBottomSheetState$1, this.$skipHiddenState);
                }
            };
            composerImpl.updateRememberedValue(rememberedValue);
        }
        return (SheetState) RememberSaveableKt.rememberSaveable(objArr, saverKt$Saver$12, (Function0) rememberedValue, composerImpl, 0, 4);
    }
}

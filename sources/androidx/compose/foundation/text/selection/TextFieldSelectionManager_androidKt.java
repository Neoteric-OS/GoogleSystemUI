package androidx.compose.foundation.text.selection;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationState;
import androidx.compose.animation.core.AnimationVector2D;
import androidx.compose.foundation.MagnifierElement;
import androidx.compose.foundation.Magnifier_androidKt;
import androidx.compose.foundation.PlatformMagnifierFactoryApi29Impl;
import androidx.compose.foundation.contextmenu.ContextMenuState;
import androidx.compose.foundation.text.Handle;
import androidx.compose.foundation.text.LegacyTextFieldState;
import androidx.compose.foundation.text.TextLayoutResultProxy;
import androidx.compose.foundation.text.selection.TextFieldSelectionManagerKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.Composer$Companion$Empty$1;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.semantics.SemanticsPropertyKey;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpSize;
import androidx.compose.ui.unit.IntSize;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class TextFieldSelectionManager_androidKt {
    public static final Function1 contextMenuBuilder(final ContextMenuState contextMenuState, final TextFieldSelectionManager textFieldSelectionManager) {
        return new Function1() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$contextMenuBuilder$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX WARN: Removed duplicated region for block: B:28:0x0095  */
            /* JADX WARN: Removed duplicated region for block: B:31:0x00c2  */
            /* JADX WARN: Removed duplicated region for block: B:34:0x00c7  */
            /* JADX WARN: Type inference failed for: r0v7, types: [androidx.compose.foundation.text.ContextMenu_androidKt$TextItem$1] */
            /* JADX WARN: Type inference failed for: r2v15, types: [androidx.compose.foundation.text.ContextMenu_androidKt$TextItem$1] */
            /* JADX WARN: Type inference failed for: r2v16, types: [androidx.compose.foundation.text.ContextMenu_androidKt$TextItem$1] */
            /* JADX WARN: Type inference failed for: r6v2, types: [androidx.compose.foundation.text.ContextMenu_androidKt$TextItem$1] */
            @Override // kotlin.jvm.functions.Function1
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invoke(java.lang.Object r9) {
                /*
                    r8 = this;
                    androidx.compose.foundation.contextmenu.ContextMenuScope r9 = (androidx.compose.foundation.contextmenu.ContextMenuScope) r9
                    androidx.compose.foundation.text.selection.TextFieldSelectionManager r0 = r2
                    androidx.compose.ui.text.input.VisualTransformation r1 = r0.visualTransformation
                    boolean r1 = r1 instanceof androidx.compose.ui.text.input.PasswordVisualTransformation
                    androidx.compose.ui.text.input.TextFieldValue r0 = r0.getValue$foundation_release()
                    long r2 = r0.selection
                    boolean r0 = androidx.compose.ui.text.TextRange.m598getCollapsedimpl(r2)
                    androidx.compose.foundation.contextmenu.ContextMenuState r2 = r1
                    androidx.compose.foundation.text.TextContextMenuItems r3 = androidx.compose.foundation.text.TextContextMenuItems.Cut
                    r4 = 0
                    r5 = 1
                    if (r0 != 0) goto L30
                    androidx.compose.foundation.text.selection.TextFieldSelectionManager r6 = r2
                    androidx.compose.runtime.MutableState r6 = r6.editable$delegate
                    androidx.compose.runtime.SnapshotMutableStateImpl r6 = (androidx.compose.runtime.SnapshotMutableStateImpl) r6
                    java.lang.Object r6 = r6.getValue()
                    java.lang.Boolean r6 = (java.lang.Boolean) r6
                    boolean r6 = r6.booleanValue()
                    if (r6 == 0) goto L30
                    if (r1 != 0) goto L30
                    r6 = r5
                    goto L31
                L30:
                    r6 = r4
                L31:
                    androidx.compose.foundation.text.selection.TextFieldSelectionManager r7 = r2
                    if (r6 == 0) goto L42
                    androidx.compose.foundation.text.ContextMenu_androidKt$TextItem$1 r6 = new androidx.compose.foundation.text.ContextMenu_androidKt$TextItem$1
                    r6.<init>()
                    androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$contextMenuBuilder$1$invoke$$inlined$TextItem$1 r3 = new androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$contextMenuBuilder$1$invoke$$inlined$TextItem$1
                    r3.<init>()
                    androidx.compose.foundation.contextmenu.ContextMenuScope.item$default(r9, r6, r3)
                L42:
                    androidx.compose.foundation.contextmenu.ContextMenuState r2 = r1
                    androidx.compose.foundation.text.TextContextMenuItems r3 = androidx.compose.foundation.text.TextContextMenuItems.Copy
                    if (r0 != 0) goto L4c
                    if (r1 != 0) goto L4c
                    r0 = r5
                    goto L4d
                L4c:
                    r0 = r4
                L4d:
                    androidx.compose.foundation.text.selection.TextFieldSelectionManager r1 = r2
                    if (r0 == 0) goto L5e
                    androidx.compose.foundation.text.ContextMenu_androidKt$TextItem$1 r0 = new androidx.compose.foundation.text.ContextMenu_androidKt$TextItem$1
                    r0.<init>()
                    androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$contextMenuBuilder$1$invoke$$inlined$TextItem$2 r3 = new androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$contextMenuBuilder$1$invoke$$inlined$TextItem$2
                    r3.<init>()
                    androidx.compose.foundation.contextmenu.ContextMenuScope.item$default(r9, r0, r3)
                L5e:
                    androidx.compose.foundation.contextmenu.ContextMenuState r0 = r1
                    androidx.compose.foundation.text.TextContextMenuItems r1 = androidx.compose.foundation.text.TextContextMenuItems.Paste
                    androidx.compose.foundation.text.selection.TextFieldSelectionManager r2 = r2
                    androidx.compose.runtime.MutableState r2 = r2.editable$delegate
                    androidx.compose.runtime.SnapshotMutableStateImpl r2 = (androidx.compose.runtime.SnapshotMutableStateImpl) r2
                    java.lang.Object r2 = r2.getValue()
                    java.lang.Boolean r2 = (java.lang.Boolean) r2
                    boolean r2 = r2.booleanValue()
                    if (r2 == 0) goto L90
                    androidx.compose.foundation.text.selection.TextFieldSelectionManager r2 = r2
                    androidx.compose.ui.platform.ClipboardManager r2 = r2.clipboardManager
                    if (r2 == 0) goto L90
                    androidx.compose.ui.platform.AndroidClipboardManager r2 = (androidx.compose.ui.platform.AndroidClipboardManager) r2
                    android.content.ClipboardManager r2 = r2.clipboardManager
                    android.content.ClipDescription r2 = r2.getPrimaryClipDescription()
                    if (r2 == 0) goto L8b
                    java.lang.String r3 = "text/*"
                    boolean r2 = r2.hasMimeType(r3)
                    goto L8c
                L8b:
                    r2 = r4
                L8c:
                    if (r2 != r5) goto L90
                    r2 = r5
                    goto L91
                L90:
                    r2 = r4
                L91:
                    androidx.compose.foundation.text.selection.TextFieldSelectionManager r3 = r2
                    if (r2 == 0) goto La2
                    androidx.compose.foundation.text.ContextMenu_androidKt$TextItem$1 r2 = new androidx.compose.foundation.text.ContextMenu_androidKt$TextItem$1
                    r2.<init>()
                    androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$contextMenuBuilder$1$invoke$$inlined$TextItem$3 r1 = new androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$contextMenuBuilder$1$invoke$$inlined$TextItem$3
                    r1.<init>()
                    androidx.compose.foundation.contextmenu.ContextMenuScope.item$default(r9, r2, r1)
                La2:
                    androidx.compose.foundation.contextmenu.ContextMenuState r0 = r1
                    androidx.compose.foundation.text.TextContextMenuItems r1 = androidx.compose.foundation.text.TextContextMenuItems.SelectAll
                    androidx.compose.foundation.text.selection.TextFieldSelectionManager r2 = r2
                    androidx.compose.ui.text.input.TextFieldValue r2 = r2.getValue$foundation_release()
                    long r2 = r2.selection
                    int r2 = androidx.compose.ui.text.TextRange.m599getLengthimpl(r2)
                    androidx.compose.foundation.text.selection.TextFieldSelectionManager r3 = r2
                    androidx.compose.ui.text.input.TextFieldValue r3 = r3.getValue$foundation_release()
                    androidx.compose.ui.text.AnnotatedString r3 = r3.annotatedString
                    java.lang.String r3 = r3.text
                    int r3 = r3.length()
                    if (r2 == r3) goto Lc3
                    r4 = r5
                Lc3:
                    androidx.compose.foundation.text.selection.TextFieldSelectionManager r8 = r2
                    if (r4 == 0) goto Ld4
                    androidx.compose.foundation.text.ContextMenu_androidKt$TextItem$1 r2 = new androidx.compose.foundation.text.ContextMenu_androidKt$TextItem$1
                    r2.<init>()
                    androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$contextMenuBuilder$1$invoke$$inlined$TextItem$4 r1 = new androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$contextMenuBuilder$1$invoke$$inlined$TextItem$4
                    r1.<init>()
                    androidx.compose.foundation.contextmenu.ContextMenuScope.item$default(r9, r2, r1)
                Ld4:
                    kotlin.Unit r8 = kotlin.Unit.INSTANCE
                    return r8
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$contextMenuBuilder$1.invoke(java.lang.Object):java.lang.Object");
            }
        };
    }

    public static final Modifier textFieldMagnifier(final TextFieldSelectionManager textFieldSelectionManager) {
        Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
        SemanticsPropertyKey semanticsPropertyKey = Magnifier_androidKt.MagnifierPositionInRoot;
        Function3 function3 = new Function3() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$textFieldMagnifier$1
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                Modifier modifier = (Modifier) obj;
                ((Number) obj3).intValue();
                ComposerImpl composerImpl = (ComposerImpl) ((Composer) obj2);
                composerImpl.startReplaceGroup(1980580247);
                OpaqueKey opaqueKey = ComposerKt.invocation;
                final Density density = (Density) composerImpl.consume(CompositionLocalsKt.LocalDensity);
                Object rememberedValue = composerImpl.rememberedValue();
                Composer$Companion$Empty$1 composer$Companion$Empty$1 = Composer.Companion.Empty;
                if (rememberedValue == composer$Companion$Empty$1) {
                    rememberedValue = SnapshotStateKt.mutableStateOf$default(new IntSize(0L));
                    composerImpl.updateRememberedValue(rememberedValue);
                }
                final MutableState mutableState = (MutableState) rememberedValue;
                boolean changedInstance = composerImpl.changedInstance(TextFieldSelectionManager.this);
                final TextFieldSelectionManager textFieldSelectionManager2 = TextFieldSelectionManager.this;
                Object rememberedValue2 = composerImpl.rememberedValue();
                if (changedInstance || rememberedValue2 == composer$Companion$Empty$1) {
                    rememberedValue2 = new Function0() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$textFieldMagnifier$1$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            long j;
                            TextLayoutResultProxy layoutResult;
                            LegacyTextFieldState legacyTextFieldState;
                            AnnotatedString annotatedString;
                            TextFieldSelectionManager textFieldSelectionManager3 = TextFieldSelectionManager.this;
                            long j2 = ((IntSize) mutableState.getValue()).packedValue;
                            Offset m190getCurrentDragPosition_m7T9E = textFieldSelectionManager3.m190getCurrentDragPosition_m7T9E();
                            long j3 = 9205357640488583168L;
                            if (m190getCurrentDragPosition_m7T9E != null) {
                                LegacyTextFieldState legacyTextFieldState2 = textFieldSelectionManager3.state;
                                AnnotatedString annotatedString2 = legacyTextFieldState2 != null ? legacyTextFieldState2.textDelegate.text : null;
                                if (annotatedString2 != null && annotatedString2.text.length() != 0) {
                                    Handle handle = (Handle) ((SnapshotMutableStateImpl) textFieldSelectionManager3.draggingHandle$delegate).getValue();
                                    int i = handle == null ? -1 : TextFieldSelectionManagerKt.WhenMappings.$EnumSwitchMapping$0[handle.ordinal()];
                                    if (i != -1) {
                                        if (i == 1 || i == 2) {
                                            long j4 = textFieldSelectionManager3.getValue$foundation_release().selection;
                                            int i2 = TextRange.$r8$clinit;
                                            j = j4 >> 32;
                                        } else {
                                            if (i != 3) {
                                                throw new NoWhenBranchMatchedException();
                                            }
                                            long j5 = textFieldSelectionManager3.getValue$foundation_release().selection;
                                            int i3 = TextRange.$r8$clinit;
                                            j = j5 & 4294967295L;
                                        }
                                        int i4 = (int) j;
                                        LegacyTextFieldState legacyTextFieldState3 = textFieldSelectionManager3.state;
                                        if (legacyTextFieldState3 != null && (layoutResult = legacyTextFieldState3.getLayoutResult()) != null && (legacyTextFieldState = textFieldSelectionManager3.state) != null && (annotatedString = legacyTextFieldState.textDelegate.text) != null) {
                                            int coerceIn = RangesKt.coerceIn(textFieldSelectionManager3.offsetMapping.originalToTransformed(i4), 0, annotatedString.text.length());
                                            float intBitsToFloat = Float.intBitsToFloat((int) (layoutResult.m168translateDecorationToInnerCoordinatesMKHz9U$foundation_release(m190getCurrentDragPosition_m7T9E.packedValue) >> 32));
                                            TextLayoutResult textLayoutResult = layoutResult.value;
                                            int lineForOffset = textLayoutResult.getLineForOffset(coerceIn);
                                            float lineLeft = textLayoutResult.getLineLeft(lineForOffset);
                                            float lineRight = textLayoutResult.getLineRight(lineForOffset);
                                            float coerceIn2 = RangesKt.coerceIn(intBitsToFloat, Math.min(lineLeft, lineRight), Math.max(lineLeft, lineRight));
                                            if (IntSize.m683equalsimpl0(j2, 0L) || Math.abs(intBitsToFloat - coerceIn2) <= ((int) (j2 >> 32)) / 2) {
                                                float lineTop = textLayoutResult.multiParagraph.getLineTop(lineForOffset);
                                                j3 = (Float.floatToRawIntBits(coerceIn2) << 32) | (Float.floatToRawIntBits(((r13.getLineBottom(lineForOffset) - lineTop) / 2) + lineTop) & 4294967295L);
                                            }
                                        }
                                    }
                                }
                            }
                            return new Offset(j3);
                        }
                    };
                    composerImpl.updateRememberedValue(rememberedValue2);
                }
                final Function0 function0 = (Function0) rememberedValue2;
                boolean changed = composerImpl.changed(density);
                Object rememberedValue3 = composerImpl.rememberedValue();
                if (changed || rememberedValue3 == composer$Companion$Empty$1) {
                    rememberedValue3 = new Function1() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$textFieldMagnifier$1$2$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj4) {
                            final Function0 function02 = (Function0) obj4;
                            Function1 function1 = new Function1() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$textFieldMagnifier$1$2$1.1
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj5) {
                                    return new Offset(((Offset) Function0.this.invoke()).packedValue);
                                }
                            };
                            final Density density2 = Density.this;
                            final MutableState mutableState2 = mutableState;
                            Function1 function12 = new Function1() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$textFieldMagnifier$1$2$1.2
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj5) {
                                    long j = ((DpSize) obj5).packedValue;
                                    MutableState mutableState3 = mutableState2;
                                    Density density3 = Density.this;
                                    mutableState3.setValue(new IntSize((density3.mo45roundToPx0680j_4(DpSize.m672getWidthD9Ej5fM(j)) << 32) | (density3.mo45roundToPx0680j_4(DpSize.m671getHeightD9Ej5fM(j)) & 4294967295L)));
                                    return Unit.INSTANCE;
                                }
                            };
                            SemanticsPropertyKey semanticsPropertyKey2 = Magnifier_androidKt.MagnifierPositionInRoot;
                            return new MagnifierElement(function1, function12, PlatformMagnifierFactoryApi29Impl.INSTANCE);
                        }
                    };
                    composerImpl.updateRememberedValue(rememberedValue3);
                }
                final Function1 function1 = (Function1) rememberedValue3;
                AnimationVector2D animationVector2D = SelectionMagnifierKt.UnspecifiedAnimationVector2D;
                Function3 function32 = new Function3() { // from class: androidx.compose.foundation.text.selection.SelectionMagnifierKt$animatedSelectionMagnifier$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj4, Object obj5, Object obj6) {
                        ((Number) obj6).intValue();
                        ComposerImpl composerImpl2 = (ComposerImpl) ((Composer) obj5);
                        composerImpl2.startReplaceGroup(759876635);
                        OpaqueKey opaqueKey2 = ComposerKt.invocation;
                        Function0 function02 = Function0.this;
                        AnimationVector2D animationVector2D2 = SelectionMagnifierKt.UnspecifiedAnimationVector2D;
                        Object rememberedValue4 = composerImpl2.rememberedValue();
                        Composer$Companion$Empty$1 composer$Companion$Empty$12 = Composer.Companion.Empty;
                        if (rememberedValue4 == composer$Companion$Empty$12) {
                            rememberedValue4 = SnapshotStateKt.derivedStateOf(function02);
                            composerImpl2.updateRememberedValue(rememberedValue4);
                        }
                        State state = (State) rememberedValue4;
                        Object rememberedValue5 = composerImpl2.rememberedValue();
                        if (rememberedValue5 == composer$Companion$Empty$12) {
                            rememberedValue5 = new Animatable(new Offset(((Offset) state.getValue()).packedValue), SelectionMagnifierKt.UnspecifiedSafeOffsetVectorConverter, new Offset(SelectionMagnifierKt.OffsetDisplacementThreshold), null, 8);
                            composerImpl2.updateRememberedValue(rememberedValue5);
                        }
                        Animatable animatable = (Animatable) rememberedValue5;
                        Unit unit = Unit.INSTANCE;
                        boolean changedInstance2 = composerImpl2.changedInstance(animatable);
                        Object rememberedValue6 = composerImpl2.rememberedValue();
                        if (changedInstance2 || rememberedValue6 == composer$Companion$Empty$12) {
                            rememberedValue6 = new SelectionMagnifierKt$rememberAnimatedMagnifierPosition$1$1(state, animatable, null);
                            composerImpl2.updateRememberedValue(rememberedValue6);
                        }
                        EffectsKt.LaunchedEffect(composerImpl2, unit, (Function2) rememberedValue6);
                        final AnimationState animationState = animatable.internalState;
                        Function1 function12 = function1;
                        boolean changed2 = composerImpl2.changed(animationState);
                        Object rememberedValue7 = composerImpl2.rememberedValue();
                        if (changed2 || rememberedValue7 == composer$Companion$Empty$12) {
                            rememberedValue7 = new Function0() { // from class: androidx.compose.foundation.text.selection.SelectionMagnifierKt$animatedSelectionMagnifier$1$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return new Offset(((Offset) animationState.getValue()).packedValue);
                                }
                            };
                            composerImpl2.updateRememberedValue(rememberedValue7);
                        }
                        Modifier modifier2 = (Modifier) function12.invoke((Function0) rememberedValue7);
                        composerImpl2.end(false);
                        return modifier2;
                    }
                };
                Function1 function12 = InspectableValueKt.NoInspectorInfo;
                Modifier composed = ComposedModifierKt.composed(modifier, function32);
                composerImpl.end(false);
                return composed;
            }
        };
        Function1 function1 = InspectableValueKt.NoInspectorInfo;
        return ComposedModifierKt.composed(companion, function3);
    }
}

package androidx.compose.foundation.text;

import androidx.compose.animation.AnimatedContentKt$$ExternalSyntheticOutline0;
import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.text.Handle;
import androidx.compose.foundation.text.LegacyTextFieldState;
import androidx.compose.foundation.text.TextLayoutResultProxy;
import androidx.compose.foundation.text.selection.OffsetProvider;
import androidx.compose.foundation.text.selection.SelectionHandleAnchor;
import androidx.compose.foundation.text.selection.SelectionHandleInfo;
import androidx.compose.foundation.text.selection.SelectionHandlesKt;
import androidx.compose.foundation.text.selection.TextFieldSelectionManager;
import androidx.compose.foundation.text.selection.TextFieldSelectionManagerKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.PersistentCompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.hapticfeedback.HapticFeedback;
import androidx.compose.ui.hapticfeedback.PlatformHapticFeedback;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.compose.ui.text.input.OffsetMapping;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.input.TextInputService;
import androidx.compose.ui.text.input.TextInputSession;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import androidx.compose.ui.unit.Density;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class CoreTextFieldKt {
    /* JADX WARN: Code restructure failed: missing block: B:135:0x04f5, code lost:
    
        if (r6.fontFamilyResolver == r8) goto L298;
     */
    /* JADX WARN: Code restructure failed: missing block: B:165:0x05ea, code lost:
    
        if (r4 > ((r6 != null ? r6.longValue() : r7) + 5000)) goto L338;
     */
    /* JADX WARN: Removed duplicated region for block: B:101:0x03a6 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:104:0x03cf  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x03de  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x03e8 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0468  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x056d  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x058f A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x05ad  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x05c8  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x05da  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x05fb  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x065c  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0670  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x068a  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0698  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x06af  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x06bb  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x06f4 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:197:0x072a A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:201:0x075d  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x0778 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:210:0x07a0 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:213:0x07b8  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x07c4  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x07d8 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:222:0x07f4  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x081a  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x0829 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x084d  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x0865  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x0874 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:241:0x08a5  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x08b0  */
    /* JADX WARN: Removed duplicated region for block: B:247:0x08bd  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x08d6  */
    /* JADX WARN: Removed duplicated region for block: B:255:0x08ea A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:258:0x0913 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x094b  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x096d A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:274:0x0991  */
    /* JADX WARN: Removed duplicated region for block: B:277:0x0999  */
    /* JADX WARN: Removed duplicated region for block: B:282:0x09ad A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:285:0x09c3  */
    /* JADX WARN: Removed duplicated region for block: B:288:0x09f9  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:293:0x0a14 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:296:0x0a70  */
    /* JADX WARN: Removed duplicated region for block: B:304:0x0a97  */
    /* JADX WARN: Removed duplicated region for block: B:306:0x0a9e  */
    /* JADX WARN: Removed duplicated region for block: B:312:0x09c5  */
    /* JADX WARN: Removed duplicated region for block: B:317:0x0993  */
    /* JADX WARN: Removed duplicated region for block: B:319:0x095c  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:325:0x08bf  */
    /* JADX WARN: Removed duplicated region for block: B:326:0x08b2  */
    /* JADX WARN: Removed duplicated region for block: B:327:0x08a7  */
    /* JADX WARN: Removed duplicated region for block: B:329:0x0867  */
    /* JADX WARN: Removed duplicated region for block: B:330:0x084f  */
    /* JADX WARN: Removed duplicated region for block: B:332:0x081c  */
    /* JADX WARN: Removed duplicated region for block: B:334:0x07c6  */
    /* JADX WARN: Removed duplicated region for block: B:335:0x07ba  */
    /* JADX WARN: Removed duplicated region for block: B:341:0x076f  */
    /* JADX WARN: Removed duplicated region for block: B:344:0x06d8  */
    /* JADX WARN: Removed duplicated region for block: B:346:0x06cb  */
    /* JADX WARN: Removed duplicated region for block: B:347:0x06b1  */
    /* JADX WARN: Removed duplicated region for block: B:348:0x069a  */
    /* JADX WARN: Removed duplicated region for block: B:349:0x068c  */
    /* JADX WARN: Removed duplicated region for block: B:353:0x0576  */
    /* JADX WARN: Removed duplicated region for block: B:363:0x0b3d  */
    /* JADX WARN: Removed duplicated region for block: B:367:0x03f8  */
    /* JADX WARN: Removed duplicated region for block: B:369:0x0457  */
    /* JADX WARN: Removed duplicated region for block: B:370:0x03e0  */
    /* JADX WARN: Removed duplicated region for block: B:371:0x03d4  */
    /* JADX WARN: Removed duplicated region for block: B:375:0x0270  */
    /* JADX WARN: Removed duplicated region for block: B:377:0x0277  */
    /* JADX WARN: Removed duplicated region for block: B:379:0x027e  */
    /* JADX WARN: Removed duplicated region for block: B:381:0x0285  */
    /* JADX WARN: Removed duplicated region for block: B:383:0x028c  */
    /* JADX WARN: Removed duplicated region for block: B:385:0x0293  */
    /* JADX WARN: Removed duplicated region for block: B:387:0x02a3  */
    /* JADX WARN: Removed duplicated region for block: B:389:0x02a9  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x014a  */
    /* JADX WARN: Removed duplicated region for block: B:391:0x02b1  */
    /* JADX WARN: Removed duplicated region for block: B:395:0x02bc  */
    /* JADX WARN: Removed duplicated region for block: B:397:0x02c5  */
    /* JADX WARN: Removed duplicated region for block: B:399:0x02cc  */
    /* JADX WARN: Removed duplicated region for block: B:401:0x02d2  */
    /* JADX WARN: Removed duplicated region for block: B:403:0x02d8  */
    /* JADX WARN: Removed duplicated region for block: B:404:0x02f1  */
    /* JADX WARN: Removed duplicated region for block: B:405:0x02d4  */
    /* JADX WARN: Removed duplicated region for block: B:406:0x02ce  */
    /* JADX WARN: Removed duplicated region for block: B:407:0x02c8  */
    /* JADX WARN: Removed duplicated region for block: B:408:0x02c1  */
    /* JADX WARN: Removed duplicated region for block: B:409:0x02b5  */
    /* JADX WARN: Removed duplicated region for block: B:410:0x02ad  */
    /* JADX WARN: Removed duplicated region for block: B:411:0x02a5  */
    /* JADX WARN: Removed duplicated region for block: B:412:0x029d  */
    /* JADX WARN: Removed duplicated region for block: B:413:0x028f  */
    /* JADX WARN: Removed duplicated region for block: B:414:0x0288  */
    /* JADX WARN: Removed duplicated region for block: B:415:0x0281  */
    /* JADX WARN: Removed duplicated region for block: B:416:0x027a  */
    /* JADX WARN: Removed duplicated region for block: B:417:0x0273  */
    /* JADX WARN: Removed duplicated region for block: B:418:0x01e9  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0166  */
    /* JADX WARN: Removed duplicated region for block: B:424:0x01ca  */
    /* JADX WARN: Removed duplicated region for block: B:431:0x01aa  */
    /* JADX WARN: Removed duplicated region for block: B:439:0x0188  */
    /* JADX WARN: Removed duplicated region for block: B:447:0x017b  */
    /* JADX WARN: Removed duplicated region for block: B:448:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:455:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:461:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:468:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:475:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:482:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:489:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:496:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x01a3  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x01c5  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x01e4  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0203  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x023d  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0314  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0326  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x033a  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0389 A[ADDED_TO_REGION] */
    /* JADX WARN: Type inference failed for: r9v33, types: [androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$5, kotlin.jvm.internal.Lambda] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void CoreTextField(final androidx.compose.ui.text.input.TextFieldValue r71, final kotlin.jvm.functions.Function1 r72, androidx.compose.ui.Modifier r73, androidx.compose.ui.text.TextStyle r74, androidx.compose.ui.text.input.VisualTransformation r75, kotlin.jvm.functions.Function1 r76, androidx.compose.foundation.interaction.MutableInteractionSource r77, androidx.compose.ui.graphics.Brush r78, boolean r79, int r80, int r81, androidx.compose.ui.text.input.ImeOptions r82, androidx.compose.foundation.text.KeyboardActions r83, boolean r84, boolean r85, kotlin.jvm.functions.Function3 r86, androidx.compose.runtime.Composer r87, final int r88, final int r89, final int r90) {
        /*
            Method dump skipped, instructions count: 2885
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.CoreTextFieldKt.CoreTextField(androidx.compose.ui.text.input.TextFieldValue, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, androidx.compose.ui.text.TextStyle, androidx.compose.ui.text.input.VisualTransformation, kotlin.jvm.functions.Function1, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.ui.graphics.Brush, boolean, int, int, androidx.compose.ui.text.input.ImeOptions, androidx.compose.foundation.text.KeyboardActions, boolean, boolean, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int, int):void");
    }

    public static final void CoreTextFieldRootBox(final Modifier modifier, final TextFieldSelectionManager textFieldSelectionManager, final Function2 function2, Composer composer, final int i) {
        int i2;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-20551815);
        if ((i & 6) == 0) {
            i2 = (composerImpl.changed(modifier) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerImpl.changedInstance(textFieldSelectionManager) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerImpl.changedInstance(function2) ? 256 : 128;
        }
        if ((i2 & 147) == 146 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.TopStart, true);
            int i3 = composerImpl.compoundKeyHash;
            PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl.currentCompositionLocalScope();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier(composerImpl, modifier);
            ComposeUiNode.Companion.getClass();
            Function0 function0 = ComposeUiNode.Companion.Constructor;
            composerImpl.startReusableNode();
            if (composerImpl.inserting) {
                composerImpl.createNode(function0);
            } else {
                composerImpl.useNode();
            }
            Updater.m259setimpl(composerImpl, maybeCachedBoxMeasurePolicy, ComposeUiNode.Companion.SetMeasurePolicy);
            Updater.m259setimpl(composerImpl, currentCompositionLocalScope, ComposeUiNode.Companion.SetResolvedCompositionLocals);
            Function2 function22 = ComposeUiNode.Companion.SetCompositeKeyHash;
            if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i3))) {
                AnimatedContentKt$$ExternalSyntheticOutline0.m(i3, composerImpl, i3, function22);
            }
            Updater.m259setimpl(composerImpl, materializeModifier, ComposeUiNode.Companion.SetModifier);
            ContextMenu_androidKt.ContextMenuArea(textFieldSelectionManager, function2, composerImpl, (i2 >> 3) & 126);
            composerImpl.end(true);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextFieldRootBox$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    CoreTextFieldKt.CoreTextFieldRootBox(Modifier.this, textFieldSelectionManager, function2, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public static final void TextFieldCursorHandle(final TextFieldSelectionManager textFieldSelectionManager, Composer composer, final int i) {
        int i2;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-1436003720);
        if ((i & 6) == 0) {
            i2 = (composerImpl.changedInstance(textFieldSelectionManager) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i2 & 3) == 2 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            LegacyTextFieldState legacyTextFieldState = textFieldSelectionManager.state;
            if (legacyTextFieldState != null && ((Boolean) ((SnapshotMutableStateImpl) legacyTextFieldState.showCursorHandle$delegate).getValue()).booleanValue()) {
                LegacyTextFieldState legacyTextFieldState2 = textFieldSelectionManager.state;
                AnnotatedString annotatedString = legacyTextFieldState2 != null ? legacyTextFieldState2.textDelegate.text : null;
                if (annotatedString != null && annotatedString.text.length() > 0) {
                    boolean changed = composerImpl.changed(textFieldSelectionManager);
                    Object rememberedValue = composerImpl.rememberedValue();
                    Object obj = Composer.Companion.Empty;
                    if (changed || rememberedValue == obj) {
                        rememberedValue = new TextDragObserver() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager$cursorDragObserver$1
                            /* JADX WARN: Type inference failed for: r0v12, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
                            @Override // androidx.compose.foundation.text.TextDragObserver
                            /* renamed from: onDrag-k-4lQ0M */
                            public final void mo160onDragk4lQ0M(long j) {
                                TextLayoutResultProxy layoutResult;
                                HapticFeedback hapticFeedback;
                                TextFieldSelectionManager textFieldSelectionManager2 = TextFieldSelectionManager.this;
                                textFieldSelectionManager2.dragTotalDistance = Offset.m315plusMKHz9U(textFieldSelectionManager2.dragTotalDistance, j);
                                LegacyTextFieldState legacyTextFieldState3 = textFieldSelectionManager2.state;
                                if (legacyTextFieldState3 == null || (layoutResult = legacyTextFieldState3.getLayoutResult()) == null) {
                                    return;
                                }
                                ((SnapshotMutableStateImpl) textFieldSelectionManager2.currentDragPosition$delegate).setValue(new Offset(Offset.m315plusMKHz9U(textFieldSelectionManager2.dragBeginPosition, textFieldSelectionManager2.dragTotalDistance)));
                                OffsetMapping offsetMapping = textFieldSelectionManager2.offsetMapping;
                                Offset m190getCurrentDragPosition_m7T9E = textFieldSelectionManager2.m190getCurrentDragPosition_m7T9E();
                                Intrinsics.checkNotNull(m190getCurrentDragPosition_m7T9E);
                                int transformedToOriginal = offsetMapping.transformedToOriginal(layoutResult.m166getOffsetForPosition3MmeM6k(m190getCurrentDragPosition_m7T9E.packedValue, true));
                                long TextRange = TextRangeKt.TextRange(transformedToOriginal, transformedToOriginal);
                                if (TextRange.m597equalsimpl0(TextRange, textFieldSelectionManager2.getValue$foundation_release().selection)) {
                                    return;
                                }
                                LegacyTextFieldState legacyTextFieldState4 = textFieldSelectionManager2.state;
                                if ((legacyTextFieldState4 == null || ((Boolean) ((SnapshotMutableStateImpl) legacyTextFieldState4.isInTouchMode$delegate).getValue()).booleanValue()) && (hapticFeedback = textFieldSelectionManager2.hapticFeedBack) != null) {
                                    ((PlatformHapticFeedback) hapticFeedback).m445performHapticFeedbackCdsT49E(9);
                                }
                                textFieldSelectionManager2.onValueChange.invoke(TextFieldSelectionManager.m188createTextFieldValueFDrldGo(textFieldSelectionManager2.getValue$foundation_release().annotatedString, TextRange));
                            }

                            @Override // androidx.compose.foundation.text.TextDragObserver
                            /* renamed from: onStart-k-4lQ0M */
                            public final void mo161onStartk4lQ0M(long j) {
                                TextLayoutResultProxy layoutResult;
                                TextFieldSelectionManager textFieldSelectionManager2 = TextFieldSelectionManager.this;
                                long m185getAdjustedCoordinatesk4lQ0M = SelectionHandlesKt.m185getAdjustedCoordinatesk4lQ0M(textFieldSelectionManager2.m191getHandlePositiontuRUvjQ$foundation_release(true));
                                LegacyTextFieldState legacyTextFieldState3 = textFieldSelectionManager2.state;
                                if (legacyTextFieldState3 == null || (layoutResult = legacyTextFieldState3.getLayoutResult()) == null) {
                                    return;
                                }
                                long m169translateInnerToDecorationCoordinatesMKHz9U$foundation_release = layoutResult.m169translateInnerToDecorationCoordinatesMKHz9U$foundation_release(m185getAdjustedCoordinatesk4lQ0M);
                                textFieldSelectionManager2.dragBeginPosition = m169translateInnerToDecorationCoordinatesMKHz9U$foundation_release;
                                ((SnapshotMutableStateImpl) textFieldSelectionManager2.currentDragPosition$delegate).setValue(new Offset(m169translateInnerToDecorationCoordinatesMKHz9U$foundation_release));
                                textFieldSelectionManager2.dragTotalDistance = 0L;
                                ((SnapshotMutableStateImpl) textFieldSelectionManager2.draggingHandle$delegate).setValue(Handle.Cursor);
                                textFieldSelectionManager2.updateFloatingToolbar(false);
                            }

                            @Override // androidx.compose.foundation.text.TextDragObserver
                            public final void onStop() {
                                TextFieldSelectionManager textFieldSelectionManager2 = TextFieldSelectionManager.this;
                                ((SnapshotMutableStateImpl) textFieldSelectionManager2.draggingHandle$delegate).setValue(null);
                                ((SnapshotMutableStateImpl) textFieldSelectionManager2.currentDragPosition$delegate).setValue(null);
                            }

                            @Override // androidx.compose.foundation.text.TextDragObserver
                            public final void onUp() {
                                TextFieldSelectionManager textFieldSelectionManager2 = TextFieldSelectionManager.this;
                                ((SnapshotMutableStateImpl) textFieldSelectionManager2.draggingHandle$delegate).setValue(null);
                                ((SnapshotMutableStateImpl) textFieldSelectionManager2.currentDragPosition$delegate).setValue(null);
                            }

                            @Override // androidx.compose.foundation.text.TextDragObserver
                            public final void onCancel() {
                            }

                            @Override // androidx.compose.foundation.text.TextDragObserver
                            /* renamed from: onDown-k-4lQ0M */
                            public final void mo159onDownk4lQ0M() {
                            }
                        };
                        composerImpl.updateRememberedValue(rememberedValue);
                    }
                    final TextDragObserver textDragObserver = (TextDragObserver) rememberedValue;
                    Density density = (Density) composerImpl.consume(CompositionLocalsKt.LocalDensity);
                    OffsetMapping offsetMapping = textFieldSelectionManager.offsetMapping;
                    long j = textFieldSelectionManager.getValue$foundation_release().selection;
                    int i3 = TextRange.$r8$clinit;
                    int originalToTransformed = offsetMapping.originalToTransformed((int) (j >> 32));
                    LegacyTextFieldState legacyTextFieldState3 = textFieldSelectionManager.state;
                    TextLayoutResultProxy layoutResult = legacyTextFieldState3 != null ? legacyTextFieldState3.getLayoutResult() : null;
                    Intrinsics.checkNotNull(layoutResult);
                    TextLayoutResult textLayoutResult = layoutResult.value;
                    Rect cursorRect = textLayoutResult.getCursorRect(RangesKt.coerceIn(originalToTransformed, 0, textLayoutResult.layoutInput.text.text.length()));
                    final long floatToRawIntBits = (Float.floatToRawIntBits((density.mo51toPx0680j_4(TextFieldCursorKt.DefaultCursorThickness) / 2) + cursorRect.left) << 32) | (Float.floatToRawIntBits(cursorRect.bottom) & 4294967295L);
                    boolean changed2 = composerImpl.changed(floatToRawIntBits);
                    Object rememberedValue2 = composerImpl.rememberedValue();
                    if (changed2 || rememberedValue2 == obj) {
                        rememberedValue2 = new OffsetProvider() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$TextFieldCursorHandle$1$1
                            @Override // androidx.compose.foundation.text.selection.OffsetProvider
                            /* renamed from: provide-F1C5BW0, reason: not valid java name */
                            public final long mo156provideF1C5BW0() {
                                return floatToRawIntBits;
                            }
                        };
                        composerImpl.updateRememberedValue(rememberedValue2);
                    }
                    OffsetProvider offsetProvider = (OffsetProvider) rememberedValue2;
                    Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
                    boolean changedInstance = composerImpl.changedInstance(textDragObserver) | composerImpl.changedInstance(textFieldSelectionManager);
                    Object rememberedValue3 = composerImpl.rememberedValue();
                    if (changedInstance || rememberedValue3 == obj) {
                        rememberedValue3 = new PointerInputEventHandler() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$TextFieldCursorHandle$2$1

                            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                            /* renamed from: androidx.compose.foundation.text.CoreTextFieldKt$TextFieldCursorHandle$2$1$1, reason: invalid class name */
                            final class AnonymousClass1 extends SuspendLambda implements Function2 {
                                final /* synthetic */ TextFieldSelectionManager $manager;
                                final /* synthetic */ TextDragObserver $observer;
                                final /* synthetic */ PointerInputScope $this_pointerInput;
                                private /* synthetic */ Object L$0;
                                int label;

                                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                                /* renamed from: androidx.compose.foundation.text.CoreTextFieldKt$TextFieldCursorHandle$2$1$1$1, reason: invalid class name and collision with other inner class name */
                                final class C00081 extends SuspendLambda implements Function2 {
                                    final /* synthetic */ TextDragObserver $observer;
                                    final /* synthetic */ PointerInputScope $this_pointerInput;
                                    int label;

                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    public C00081(PointerInputScope pointerInputScope, TextDragObserver textDragObserver, Continuation continuation) {
                                        super(2, continuation);
                                        this.$this_pointerInput = pointerInputScope;
                                        this.$observer = textDragObserver;
                                    }

                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Continuation create(Object obj, Continuation continuation) {
                                        return new C00081(this.$this_pointerInput, this.$observer, continuation);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public final Object invoke(Object obj, Object obj2) {
                                        return ((C00081) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                                    }

                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Object invokeSuspend(Object obj) {
                                        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                                        int i = this.label;
                                        if (i == 0) {
                                            ResultKt.throwOnFailure(obj);
                                            PointerInputScope pointerInputScope = this.$this_pointerInput;
                                            TextDragObserver textDragObserver = this.$observer;
                                            this.label = 1;
                                            if (LongPressTextDragObserverKt.detectDownAndDragGesturesWithObserver(pointerInputScope, textDragObserver, this) == coroutineSingletons) {
                                                return coroutineSingletons;
                                            }
                                        } else {
                                            if (i != 1) {
                                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                            }
                                            ResultKt.throwOnFailure(obj);
                                        }
                                        return Unit.INSTANCE;
                                    }
                                }

                                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                                /* renamed from: androidx.compose.foundation.text.CoreTextFieldKt$TextFieldCursorHandle$2$1$1$2, reason: invalid class name */
                                final class AnonymousClass2 extends SuspendLambda implements Function2 {
                                    final /* synthetic */ TextFieldSelectionManager $manager;
                                    final /* synthetic */ PointerInputScope $this_pointerInput;
                                    int label;

                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    public AnonymousClass2(PointerInputScope pointerInputScope, TextFieldSelectionManager textFieldSelectionManager, Continuation continuation) {
                                        super(2, continuation);
                                        this.$this_pointerInput = pointerInputScope;
                                        this.$manager = textFieldSelectionManager;
                                    }

                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Continuation create(Object obj, Continuation continuation) {
                                        return new AnonymousClass2(this.$this_pointerInput, this.$manager, continuation);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public final Object invoke(Object obj, Object obj2) {
                                        return ((AnonymousClass2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                                    }

                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Object invokeSuspend(Object obj) {
                                        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                                        int i = this.label;
                                        if (i == 0) {
                                            ResultKt.throwOnFailure(obj);
                                            PointerInputScope pointerInputScope = this.$this_pointerInput;
                                            final TextFieldSelectionManager textFieldSelectionManager = this.$manager;
                                            Function1 function1 = new Function1() { // from class: androidx.compose.foundation.text.CoreTextFieldKt.TextFieldCursorHandle.2.1.1.2.1
                                                {
                                                    super(1);
                                                }

                                                @Override // kotlin.jvm.functions.Function1
                                                public final Object invoke(Object obj2) {
                                                    long j = ((Offset) obj2).packedValue;
                                                    TextFieldSelectionManager.this.showSelectionToolbar$foundation_release();
                                                    return Unit.INSTANCE;
                                                }
                                            };
                                            this.label = 1;
                                            if (TapGestureDetectorKt.detectTapGestures$default(pointerInputScope, null, null, function1, this, 7) == coroutineSingletons) {
                                                return coroutineSingletons;
                                            }
                                        } else {
                                            if (i != 1) {
                                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                            }
                                            ResultKt.throwOnFailure(obj);
                                        }
                                        return Unit.INSTANCE;
                                    }
                                }

                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                public AnonymousClass1(PointerInputScope pointerInputScope, TextDragObserver textDragObserver, TextFieldSelectionManager textFieldSelectionManager, Continuation continuation) {
                                    super(2, continuation);
                                    this.$this_pointerInput = pointerInputScope;
                                    this.$observer = textDragObserver;
                                    this.$manager = textFieldSelectionManager;
                                }

                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Continuation create(Object obj, Continuation continuation) {
                                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$this_pointerInput, this.$observer, this.$manager, continuation);
                                    anonymousClass1.L$0 = obj;
                                    return anonymousClass1;
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public final Object invoke(Object obj, Object obj2) {
                                    AnonymousClass1 anonymousClass1 = (AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2);
                                    Unit unit = Unit.INSTANCE;
                                    anonymousClass1.invokeSuspend(unit);
                                    return unit;
                                }

                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Object invokeSuspend(Object obj) {
                                    CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                                    if (this.label != 0) {
                                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                    }
                                    ResultKt.throwOnFailure(obj);
                                    CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                                    CoroutineStart coroutineStart = CoroutineStart.UNDISPATCHED;
                                    BuildersKt.launch$default(coroutineScope, null, coroutineStart, new C00081(this.$this_pointerInput, this.$observer, null), 1);
                                    BuildersKt.launch$default(coroutineScope, null, coroutineStart, new AnonymousClass2(this.$this_pointerInput, this.$manager, null), 1);
                                    return Unit.INSTANCE;
                                }
                            }

                            @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
                            public final Object invoke(PointerInputScope pointerInputScope, Continuation continuation) {
                                Object coroutineScope = CoroutineScopeKt.coroutineScope(continuation, new AnonymousClass1(pointerInputScope, TextDragObserver.this, textFieldSelectionManager, null));
                                return coroutineScope == CoroutineSingletons.COROUTINE_SUSPENDED ? coroutineScope : Unit.INSTANCE;
                            }
                        };
                        composerImpl.updateRememberedValue(rememberedValue3);
                    }
                    Modifier pointerInput = SuspendingPointerInputFilterKt.pointerInput(companion, textDragObserver, (PointerInputEventHandler) rememberedValue3);
                    boolean changed3 = composerImpl.changed(floatToRawIntBits);
                    Object rememberedValue4 = composerImpl.rememberedValue();
                    if (changed3 || rememberedValue4 == obj) {
                        rememberedValue4 = new Function1() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$TextFieldCursorHandle$3$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                ((SemanticsConfiguration) ((SemanticsPropertyReceiver) obj2)).set(SelectionHandlesKt.SelectionHandleInfoKey, new SelectionHandleInfo(Handle.Cursor, floatToRawIntBits, SelectionHandleAnchor.Middle, true));
                                return Unit.INSTANCE;
                            }
                        };
                        composerImpl.updateRememberedValue(rememberedValue4);
                    }
                    AndroidCursorHandle_androidKt.m151CursorHandleUSBMPiE(offsetProvider, SemanticsModifierKt.semantics(pointerInput, false, (Function1) rememberedValue4), 0L, composerImpl, 0, 4);
                }
            }
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$TextFieldCursorHandle$4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    ((Number) obj3).intValue();
                    CoreTextFieldKt.TextFieldCursorHandle(TextFieldSelectionManager.this, (Composer) obj2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public static final void access$SelectionToolbarAndHandles(final TextFieldSelectionManager textFieldSelectionManager, final boolean z, Composer composer, final int i) {
        int i2;
        TextLayoutResultProxy layoutResult;
        TextLayoutResult textLayoutResult;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(626339208);
        if ((i & 6) == 0) {
            i2 = (composerImpl.changedInstance(textFieldSelectionManager) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerImpl.changed(z) ? 32 : 16;
        }
        if ((i2 & 19) == 18 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            composerImpl.startReplaceGroup(1730062411);
            if (z) {
                LegacyTextFieldState legacyTextFieldState = textFieldSelectionManager.state;
                TextLayoutResult textLayoutResult2 = null;
                if (legacyTextFieldState != null && (layoutResult = legacyTextFieldState.getLayoutResult()) != null && (textLayoutResult = layoutResult.value) != null) {
                    LegacyTextFieldState legacyTextFieldState2 = textFieldSelectionManager.state;
                    if (!(legacyTextFieldState2 != null ? legacyTextFieldState2.isLayoutResultStale : true)) {
                        textLayoutResult2 = textLayoutResult;
                    }
                }
                if (textLayoutResult2 != null) {
                    composerImpl.startReplaceGroup(651298255);
                    if (!TextRange.m598getCollapsedimpl(textFieldSelectionManager.getValue$foundation_release().selection)) {
                        int originalToTransformed = textFieldSelectionManager.offsetMapping.originalToTransformed((int) (textFieldSelectionManager.getValue$foundation_release().selection >> 32));
                        int originalToTransformed2 = textFieldSelectionManager.offsetMapping.originalToTransformed((int) (textFieldSelectionManager.getValue$foundation_release().selection & 4294967295L));
                        ResolvedTextDirection bidiRunDirection = textLayoutResult2.getBidiRunDirection(originalToTransformed);
                        ResolvedTextDirection bidiRunDirection2 = textLayoutResult2.getBidiRunDirection(Math.max(originalToTransformed2 - 1, 0));
                        composerImpl.startReplaceGroup(500032624);
                        LegacyTextFieldState legacyTextFieldState3 = textFieldSelectionManager.state;
                        if (legacyTextFieldState3 != null && ((Boolean) ((SnapshotMutableStateImpl) legacyTextFieldState3.showSelectionHandleStart$delegate).getValue()).booleanValue()) {
                            TextFieldSelectionManagerKt.TextFieldSelectionHandle(true, bidiRunDirection, textFieldSelectionManager, composerImpl, ((i2 << 6) & 896) | 6);
                        }
                        composerImpl.end(false);
                        LegacyTextFieldState legacyTextFieldState4 = textFieldSelectionManager.state;
                        if (legacyTextFieldState4 != null && ((Boolean) ((SnapshotMutableStateImpl) legacyTextFieldState4.showSelectionHandleEnd$delegate).getValue()).booleanValue()) {
                            TextFieldSelectionManagerKt.TextFieldSelectionHandle(false, bidiRunDirection2, textFieldSelectionManager, composerImpl, ((i2 << 6) & 896) | 6);
                        }
                    }
                    composerImpl.end(false);
                    LegacyTextFieldState legacyTextFieldState5 = textFieldSelectionManager.state;
                    if (legacyTextFieldState5 != null) {
                        boolean areEqual = Intrinsics.areEqual(textFieldSelectionManager.oldValue.annotatedString.text, textFieldSelectionManager.getValue$foundation_release().annotatedString.text);
                        MutableState mutableState = legacyTextFieldState5.showFloatingToolbar$delegate;
                        if (!areEqual) {
                            ((SnapshotMutableStateImpl) mutableState).setValue(Boolean.FALSE);
                        }
                        if (legacyTextFieldState5.getHasFocus()) {
                            if (((Boolean) ((SnapshotMutableStateImpl) mutableState).getValue()).booleanValue()) {
                                textFieldSelectionManager.showSelectionToolbar$foundation_release();
                            } else {
                                textFieldSelectionManager.hideSelectionToolbar$foundation_release();
                            }
                        }
                    }
                }
            } else {
                textFieldSelectionManager.hideSelectionToolbar$foundation_release();
            }
            composerImpl.end(false);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$SelectionToolbarAndHandles$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    CoreTextFieldKt.access$SelectionToolbarAndHandles(TextFieldSelectionManager.this, z, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public static final void access$endInputSession(LegacyTextFieldState legacyTextFieldState) {
        TextInputSession textInputSession = legacyTextFieldState.inputSession;
        if (textInputSession != null) {
            ((LegacyTextFieldState$onValueChange$1) legacyTextFieldState.onValueChange).invoke(TextFieldValue.m623copy3r_uNRQ$default(legacyTextFieldState.processor.mBufferState, null, 0L, 3));
            TextInputService textInputService = textInputSession.textInputService;
            if (textInputService._currentInputSession.compareAndSet(textInputSession, null)) {
                textInputService.platformTextInputService.stopInput();
            }
        }
        legacyTextFieldState.inputSession = null;
    }

    public static final void notifyFocusedRect(LegacyTextFieldState legacyTextFieldState, TextFieldValue textFieldValue, OffsetMapping offsetMapping) {
        Snapshot currentThreadSnapshot = Snapshot.Companion.getCurrentThreadSnapshot();
        Function1 readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
        Snapshot makeCurrentNonObservable = Snapshot.Companion.makeCurrentNonObservable(currentThreadSnapshot);
        try {
            TextLayoutResultProxy layoutResult = legacyTextFieldState.getLayoutResult();
            if (layoutResult == null) {
                return;
            }
            TextInputSession textInputSession = legacyTextFieldState.inputSession;
            if (textInputSession == null) {
                return;
            }
            LayoutCoordinates layoutCoordinates = legacyTextFieldState.getLayoutCoordinates();
            if (layoutCoordinates == null) {
                return;
            }
            TextFieldDelegate$Companion.notifyFocusedRect$foundation_release(textFieldValue, legacyTextFieldState.textDelegate, layoutResult.value, layoutCoordinates, textInputSession, legacyTextFieldState.getHasFocus(), offsetMapping);
        } finally {
            Snapshot.Companion.restoreNonObservable(currentThreadSnapshot, makeCurrentNonObservable, readObserver);
        }
    }
}

package com.android.compose.animation.scene.content;

import androidx.compose.animation.AnimatedContentKt$$ExternalSyntheticOutline0;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.Composer$Companion$Empty$1;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.PersistentCompositionLocalMap;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.SnapshotMutableFloatStateImpl;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaImpl;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.ZIndexModifierKt;
import androidx.compose.ui.layout.ApproachLayoutElement;
import androidx.compose.ui.layout.ApproachMeasureScope;
import androidx.compose.ui.layout.LookaheadScopeKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.TestTagKt;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.IntSize;
import com.android.compose.animation.scene.ContentKey;
import com.android.compose.animation.scene.SceneTransitionLayoutImpl;
import com.android.compose.animation.scene.SceneTransitionLayoutState;
import java.util.Map;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class Content {
    public final MutableState content$delegate;
    public final SceneTransitionLayoutImpl layoutImpl;
    public final ContentScopeImpl scope;
    public final MutableState targetSize$delegate = SnapshotStateKt.mutableStateOf$default(new IntSize(0));
    public final MutableState userActions$delegate;
    public final MutableFloatState zIndex$delegate;

    public Content(SceneTransitionLayoutImpl sceneTransitionLayoutImpl, ComposableLambdaImpl composableLambdaImpl, Map map, float f) {
        this.layoutImpl = sceneTransitionLayoutImpl;
        this.scope = new ContentScopeImpl(sceneTransitionLayoutImpl, this);
        this.content$delegate = SnapshotStateKt.mutableStateOf$default(composableLambdaImpl);
        this.zIndex$delegate = PrimitiveSnapshotStateKt.mutableFloatStateOf(f);
        this.userActions$delegate = SnapshotStateKt.mutableStateOf$default(map);
    }

    public final void Content(final Modifier modifier, Composer composer, final int i, final int i2) {
        int i3;
        Modifier then;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-305002973);
        int i4 = i2 & 1;
        if (i4 != 0) {
            i3 = i | 6;
        } else if ((i & 14) == 0) {
            i3 = (composerImpl.changed(modifier) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((2 & i2) != 0) {
            i3 |= 48;
        } else if ((i & 112) == 0) {
            i3 |= composerImpl.changed(this) ? 32 : 16;
        }
        if ((i3 & 91) == 18 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            if (i4 != 0) {
                modifier = Modifier.Companion.$$INSTANCE;
            }
            OpaqueKey opaqueKey = ComposerKt.invocation;
            Modifier zIndex = ZIndexModifierKt.zIndex(modifier, getZIndex());
            composerImpl.startReplaceGroup(-1865855015);
            int i5 = i3 & 112;
            boolean z = i5 == 32;
            Object rememberedValue = composerImpl.rememberedValue();
            Composer$Companion$Empty$1 composer$Companion$Empty$1 = Composer.Companion.Empty;
            if (z || rememberedValue == composer$Companion$Empty$1) {
                rememberedValue = new Function1() { // from class: com.android.compose.animation.scene.content.Content$Content$4$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        long j = ((IntSize) obj).packedValue;
                        return Boolean.valueOf(SceneTransitionLayoutState.isTransitioning$default(Content.this.layoutImpl.state, null, 3));
                    }
                };
                composerImpl.updateRememberedValue(rememberedValue);
            }
            Function1 function1 = (Function1) rememberedValue;
            composerImpl.end(false);
            composerImpl.startReplaceGroup(-1865854958);
            boolean z2 = i5 == 32;
            Object rememberedValue2 = composerImpl.rememberedValue();
            if (z2 || rememberedValue2 == composer$Companion$Empty$1) {
                rememberedValue2 = new Function3() { // from class: com.android.compose.animation.scene.content.Content$Content$5$1
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                        MeasureResult layout$1;
                        ApproachMeasureScope approachMeasureScope = (ApproachMeasureScope) obj;
                        long j = ((Constraints) obj3).value;
                        Content content = Content.this;
                        long mo471getLookaheadSizeYbymL2g = approachMeasureScope.mo471getLookaheadSizeYbymL2g();
                        ((SnapshotMutableStateImpl) content.targetSize$delegate).setValue(new IntSize(mo471getLookaheadSizeYbymL2g));
                        final Placeable mo479measureBRTryo0 = ((Measurable) obj2).mo479measureBRTryo0(j);
                        layout$1 = approachMeasureScope.layout$1(mo479measureBRTryo0.width, mo479measureBRTryo0.height, MapsKt.emptyMap(), new Function1() { // from class: com.android.compose.animation.scene.content.Content$Content$5$1.1
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj4) {
                                ((Placeable.PlacementScope) obj4).place(Placeable.this, 0, 0, 0.0f);
                                return Unit.INSTANCE;
                            }
                        });
                        return layout$1;
                    }
                };
                composerImpl.updateRememberedValue(rememberedValue2);
            }
            composerImpl.end(false);
            then = zIndex.then(new ApproachLayoutElement((Function3) rememberedValue2, function1, LookaheadScopeKt.defaultPlacementApproachInProgress));
            Modifier testTag = TestTagKt.testTag(then, getKey().getTestTag());
            MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.TopStart, false);
            int i6 = composerImpl.compoundKeyHash;
            PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl.currentCompositionLocalScope();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier(composerImpl, testTag);
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
            Function2 function2 = ComposeUiNode.Companion.SetCompositeKeyHash;
            if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i6))) {
                AnimatedContentKt$$ExternalSyntheticOutline0.m(i6, composerImpl, i6, function2);
            }
            Updater.m259setimpl(composerImpl, materializeModifier, ComposeUiNode.Companion.SetModifier);
            ((Function3) ((SnapshotMutableStateImpl) this.content$delegate).getValue()).invoke(this.scope, composerImpl, 0);
            composerImpl.end(true);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.compose.animation.scene.content.Content$Content$7
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    Content.this.Content(modifier, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public abstract ContentKey getKey();

    public final float getZIndex() {
        return ((SnapshotMutableFloatStateImpl) this.zIndex$delegate).getFloatValue();
    }
}

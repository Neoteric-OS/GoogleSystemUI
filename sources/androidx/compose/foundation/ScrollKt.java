package androidx.compose.foundation;

import androidx.compose.foundation.gestures.FlingBehavior;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.ScrollableDefaults;
import androidx.compose.foundation.gestures.ScrollableKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.SaverKt$Saver$1;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ScrollKt {
    public static final ScrollState rememberScrollState(Composer composer) {
        OpaqueKey opaqueKey = ComposerKt.invocation;
        Object[] objArr = new Object[0];
        SaverKt$Saver$1 saverKt$Saver$1 = ScrollState.Saver;
        boolean changed = ((ComposerImpl) composer).changed(0);
        ComposerImpl composerImpl = (ComposerImpl) composer;
        Object rememberedValue = composerImpl.rememberedValue();
        if (changed || rememberedValue == Composer.Companion.Empty) {
            rememberedValue = new Function0() { // from class: androidx.compose.foundation.ScrollKt$rememberScrollState$1$1
                final /* synthetic */ int $initial = 0;

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return new ScrollState(this.$initial);
                }
            };
            composerImpl.updateRememberedValue(rememberedValue);
        }
        return (ScrollState) RememberSaveableKt.rememberSaveable(objArr, saverKt$Saver$1, (Function0) rememberedValue, composerImpl, 0, 4);
    }

    public static Modifier verticalScroll$default(Modifier modifier, final ScrollState scrollState, final boolean z, int i) {
        if ((i & 2) != 0) {
            z = true;
        }
        Function1 function1 = InspectableValueKt.NoInspectorInfo;
        return ComposedModifierKt.composed(modifier, new Function3() { // from class: androidx.compose.foundation.ScrollKt$scroll$2
            final /* synthetic */ boolean $isVertical = true;
            final /* synthetic */ boolean $reverseScrolling = false;
            final /* synthetic */ FlingBehavior $flingBehavior = null;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                Modifier scrollable;
                ((Number) obj3).intValue();
                ComposerImpl composerImpl = (ComposerImpl) ((Composer) obj2);
                composerImpl.startReplaceGroup(1478351300);
                OpaqueKey opaqueKey = ComposerKt.invocation;
                Orientation orientation = this.$isVertical ? Orientation.Vertical : Orientation.Horizontal;
                boolean reverseDirection = ScrollableDefaults.reverseDirection((LayoutDirection) composerImpl.consume(CompositionLocalsKt.LocalLayoutDirection), orientation, this.$reverseScrolling);
                Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
                ScrollState scrollState2 = ScrollState.this;
                scrollable = ScrollableKt.scrollable(companion.then(r12 == Orientation.Vertical ? ClipScrollableContainerKt.VerticalScrollableClipModifier : ClipScrollableContainerKt.HorizontalScrollableClipModifier).then(r17 == null ? Modifier.Companion.$$INSTANCE : r7.getEffectModifier()), scrollState2, orientation, ScrollableDefaults.overscrollEffect(composerImpl), z, reverseDirection, this.$flingBehavior, scrollState2.internalInteractionSource, null);
                Modifier then = scrollable.then(new ScrollingLayoutElement(ScrollState.this, this.$reverseScrolling, this.$isVertical));
                composerImpl.end(false);
                return then;
            }
        });
    }
}

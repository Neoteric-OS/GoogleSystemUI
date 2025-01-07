package androidx.compose.foundation.gestures;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.SnapshotStateKt;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ScrollableStateKt {
    public static final ScrollableState ScrollableState(Function1 function1) {
        return new DefaultScrollableState(function1);
    }

    public static final ScrollableState rememberScrollableState(Composer composer, Function1 function1) {
        OpaqueKey opaqueKey = ComposerKt.invocation;
        final MutableState rememberUpdatedState = SnapshotStateKt.rememberUpdatedState(function1, composer);
        ComposerImpl composerImpl = (ComposerImpl) composer;
        Object rememberedValue = composerImpl.rememberedValue();
        if (rememberedValue == Composer.Companion.Empty) {
            DefaultScrollableState defaultScrollableState = new DefaultScrollableState(new Function1() { // from class: androidx.compose.foundation.gestures.ScrollableStateKt$rememberScrollableState$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return (Float) ((Function1) rememberUpdatedState.getValue()).invoke(Float.valueOf(((Number) obj).floatValue()));
                }
            });
            composerImpl.updateRememberedValue(defaultScrollableState);
            rememberedValue = defaultScrollableState;
        }
        return (ScrollableState) rememberedValue;
    }
}

package androidx.compose.runtime.internal;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.RecomposeScope;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeOwner;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ComposableLambdaKt {
    public static final int bitsForSlot(int i, int i2) {
        return i << (((i2 % 10) * 3) + 1);
    }

    public static final ComposableLambdaImpl rememberComposableLambda(int i, Lambda lambda, Composer composer) {
        OpaqueKey opaqueKey = ComposerKt.invocation;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        Object rememberedValue = composerImpl.rememberedValue();
        if (rememberedValue == Composer.Companion.Empty) {
            rememberedValue = new ComposableLambdaImpl(i, true, lambda);
            composerImpl.updateRememberedValue(rememberedValue);
        }
        ComposableLambdaImpl composableLambdaImpl = (ComposableLambdaImpl) rememberedValue;
        if (!Intrinsics.areEqual(composableLambdaImpl._block, lambda)) {
            boolean z = composableLambdaImpl._block == null;
            composableLambdaImpl._block = lambda;
            if (!z && composableLambdaImpl.tracked) {
                RecomposeScopeImpl recomposeScopeImpl = composableLambdaImpl.scope;
                if (recomposeScopeImpl != null) {
                    RecomposeScopeOwner recomposeScopeOwner = recomposeScopeImpl.owner;
                    if (recomposeScopeOwner != null) {
                        recomposeScopeOwner.invalidate(recomposeScopeImpl, null);
                    }
                    composableLambdaImpl.scope = null;
                }
                List list = composableLambdaImpl.scopes;
                if (list != null) {
                    ArrayList arrayList = (ArrayList) list;
                    int size = arrayList.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        RecomposeScopeImpl recomposeScopeImpl2 = (RecomposeScopeImpl) ((RecomposeScope) arrayList.get(i2));
                        RecomposeScopeOwner recomposeScopeOwner2 = recomposeScopeImpl2.owner;
                        if (recomposeScopeOwner2 != null) {
                            recomposeScopeOwner2.invalidate(recomposeScopeImpl2, null);
                        }
                    }
                    list.clear();
                }
            }
        }
        OpaqueKey opaqueKey2 = ComposerKt.invocation;
        return composableLambdaImpl;
    }

    public static final boolean replacableWith(RecomposeScope recomposeScope, RecomposeScopeImpl recomposeScopeImpl) {
        if (recomposeScope != null) {
            if (recomposeScope instanceof RecomposeScopeImpl) {
                RecomposeScopeImpl recomposeScopeImpl2 = (RecomposeScopeImpl) recomposeScope;
                if (!recomposeScopeImpl2.getValid() || recomposeScope.equals(recomposeScopeImpl) || Intrinsics.areEqual(recomposeScopeImpl2.anchor, recomposeScopeImpl.anchor)) {
                }
            }
            return false;
        }
        return true;
    }
}

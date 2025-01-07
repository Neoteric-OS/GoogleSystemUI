package androidx.compose.ui.layout;

import androidx.compose.animation.AnimatedContentKt$$ExternalSyntheticOutline0;
import androidx.compose.runtime.AbstractApplier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.internal.ComposableLambdaImpl;
import androidx.compose.ui.node.ComposeUiNode;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class LayoutKt {
    public static final ComposableLambdaImpl combineAsVirtualLayouts(final List list) {
        return new ComposableLambdaImpl(-1953651383, true, new Function2() { // from class: androidx.compose.ui.layout.LayoutKt$combineAsVirtualLayouts$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                Composer composer = (Composer) obj;
                if ((((Number) obj2).intValue() & 3) == 2) {
                    ComposerImpl composerImpl = (ComposerImpl) composer;
                    if (composerImpl.getSkipping()) {
                        composerImpl.skipToGroupEnd();
                        return Unit.INSTANCE;
                    }
                }
                OpaqueKey opaqueKey = ComposerKt.invocation;
                List list2 = list;
                int size = list2.size();
                for (int i = 0; i < size; i++) {
                    Function2 function2 = (Function2) list2.get(i);
                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer);
                    ComposeUiNode.Companion.getClass();
                    Function0 function0 = ComposeUiNode.Companion.VirtualConstructor;
                    ComposerImpl composerImpl2 = (ComposerImpl) composer;
                    AbstractApplier abstractApplier = composerImpl2.applier;
                    composerImpl2.startReusableNode();
                    if (composerImpl2.inserting) {
                        composerImpl2.createNode(function0);
                    } else {
                        composerImpl2.useNode();
                    }
                    Function2 function22 = ComposeUiNode.Companion.SetCompositeKeyHash;
                    if (composerImpl2.inserting || !Intrinsics.areEqual(composerImpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                        AnimatedContentKt$$ExternalSyntheticOutline0.m(currentCompositeKeyHash, composerImpl2, currentCompositeKeyHash, function22);
                    }
                    function2.invoke(composer, 0);
                    composerImpl2.end(true);
                }
                OpaqueKey opaqueKey2 = ComposerKt.invocation;
                return Unit.INSTANCE;
            }
        });
    }
}

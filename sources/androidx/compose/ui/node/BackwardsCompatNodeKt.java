package androidx.compose.ui.node;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class BackwardsCompatNodeKt {
    public static final BackwardsCompatNodeKt$DetachedModifierLocalReadScope$1 DetachedModifierLocalReadScope = new BackwardsCompatNodeKt$DetachedModifierLocalReadScope$1();
    public static final Function1 updateModifierLocalConsumer = new Function1() { // from class: androidx.compose.ui.node.BackwardsCompatNodeKt$updateModifierLocalConsumer$1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            ((BackwardsCompatNode) obj).updateModifierLocalConsumer();
            return Unit.INSTANCE;
        }
    };
}

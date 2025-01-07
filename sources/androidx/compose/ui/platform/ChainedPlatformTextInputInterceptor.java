package androidx.compose.ui.platform;

import androidx.compose.ui.node.Owner;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
abstract class ChainedPlatformTextInputInterceptor {
    public abstract void textInputSession(Owner owner, Function2 function2, ContinuationImpl continuationImpl);
}

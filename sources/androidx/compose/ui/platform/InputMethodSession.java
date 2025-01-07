package androidx.compose.ui.platform;

import androidx.compose.runtime.collection.MutableVector;
import java.lang.ref.WeakReference;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class InputMethodSession {
    public boolean disposed;
    public final Function0 onAllConnectionsClosed;
    public final PlatformTextInputMethodRequest request;
    public final Object lock = new Object();
    public final MutableVector connections = new MutableVector(new WeakReference[16]);

    public InputMethodSession(PlatformTextInputMethodRequest platformTextInputMethodRequest, Function0 function0) {
        this.request = platformTextInputMethodRequest;
        this.onAllConnectionsClosed = function0;
    }
}

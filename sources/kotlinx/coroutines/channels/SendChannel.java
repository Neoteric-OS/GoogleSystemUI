package kotlinx.coroutines.channels;

import kotlin.coroutines.Continuation;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface SendChannel {
    boolean close(Throwable th);

    Object send(Object obj, Continuation continuation);

    /* renamed from: trySend-JP2dKIU */
    Object mo1790trySendJP2dKIU(Object obj);
}

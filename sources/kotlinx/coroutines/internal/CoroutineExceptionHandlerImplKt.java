package kotlinx.coroutines.internal;

import java.util.Arrays;
import java.util.Collection;
import java.util.ServiceConfigurationError;
import kotlin.sequences.SequencesKt;
import kotlinx.coroutines.android.AndroidExceptionPreHandler;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class CoroutineExceptionHandlerImplKt {
    public static final Collection platformExceptionHandlers;

    static {
        try {
            platformExceptionHandlers = SequencesKt.toList(SequencesKt.asSequence(Arrays.asList(new AndroidExceptionPreHandler()).iterator()));
        } catch (Throwable th) {
            throw new ServiceConfigurationError(th.getMessage(), th);
        }
    }
}

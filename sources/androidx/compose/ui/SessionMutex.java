package androidx.compose.ui;

import java.util.concurrent.atomic.AtomicReference;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Job;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class SessionMutex {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class Session {
        public final Job job;
        public final Object value;

        public Session(Job job, Object obj) {
            this.job = job;
            this.value = obj;
        }
    }

    /* renamed from: getCurrentSession-impl, reason: not valid java name */
    public static final Object m275getCurrentSessionimpl(AtomicReference atomicReference) {
        Session session = (Session) atomicReference.get();
        if (session != null) {
            return session.value;
        }
        return null;
    }

    /* renamed from: withSessionCancellingPrevious-impl, reason: not valid java name */
    public static final Object m276withSessionCancellingPreviousimpl(AtomicReference atomicReference, Function1 function1, Function2 function2, ContinuationImpl continuationImpl) {
        return CoroutineScopeKt.coroutineScope(continuationImpl, new SessionMutex$withSessionCancellingPrevious$2(function1, atomicReference, function2, null));
    }
}

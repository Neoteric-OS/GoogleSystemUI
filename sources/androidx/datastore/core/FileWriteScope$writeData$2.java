package androidx.datastore.core;

import java.io.FileOutputStream;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class FileWriteScope$writeData$2 extends SuspendLambda implements Function1 {
    final /* synthetic */ Object $value;
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ FileWriteScope this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FileWriteScope$writeData$2(FileWriteScope fileWriteScope, Object obj, Continuation continuation) {
        super(1, continuation);
        this.this$0 = fileWriteScope;
        this.$value = obj;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        return new FileWriteScope$writeData$2(this.this$0, this.$value, (Continuation) obj).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r0v6 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Throwable th;
        java.io.Closeable closeable;
        ?? r0;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FileOutputStream fileOutputStream = new FileOutputStream(this.this$0.file);
            FileWriteScope fileWriteScope = this.this$0;
            Object obj2 = this.$value;
            try {
                Serializer serializer = fileWriteScope.serializer;
                UncloseableOutputStream uncloseableOutputStream = new UncloseableOutputStream(fileOutputStream);
                this.L$0 = fileOutputStream;
                this.L$1 = fileOutputStream;
                this.label = 1;
                serializer.writeTo(obj2, uncloseableOutputStream);
                if (unit == coroutineSingletons) {
                    return coroutineSingletons;
                }
                closeable = fileOutputStream;
                r0 = closeable;
            } catch (Throwable th2) {
                th = th2;
                closeable = fileOutputStream;
                throw th;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            FileOutputStream fileOutputStream2 = (FileOutputStream) this.L$1;
            closeable = (java.io.Closeable) this.L$0;
            try {
                ResultKt.throwOnFailure(obj);
                r0 = fileOutputStream2;
            } catch (Throwable th3) {
                th = th3;
                try {
                    throw th;
                } catch (Throwable th4) {
                    CloseableKt.closeFinally(closeable, th);
                    throw th4;
                }
            }
        }
        r0.getFD().sync();
        CloseableKt.closeFinally(closeable, null);
        return unit;
    }
}

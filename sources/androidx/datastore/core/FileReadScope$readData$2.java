package androidx.datastore.core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class FileReadScope$readData$2 extends SuspendLambda implements Function1 {
    Object L$0;
    int label;
    final /* synthetic */ FileReadScope this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FileReadScope$readData$2(FileReadScope fileReadScope, Continuation continuation) {
        super(1, continuation);
        this.this$0 = fileReadScope;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        return new FileReadScope$readData$2(this.this$0, (Continuation) obj).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v8, types: [java.io.Closeable] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        java.io.Closeable closeable;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        ?? r1 = this.label;
        try {
            try {
            } catch (FileNotFoundException unused) {
                if (this.this$0.file.exists()) {
                    FileInputStream fileInputStream = new FileInputStream(this.this$0.file);
                    try {
                        Serializer serializer = this.this$0.serializer;
                        this.L$0 = fileInputStream;
                        this.label = 2;
                        if (serializer.readFrom(fileInputStream) == coroutineSingletons) {
                            return coroutineSingletons;
                        }
                        closeable = fileInputStream;
                    } catch (Throwable th) {
                        th = th;
                        closeable = fileInputStream;
                        try {
                            throw th;
                        } catch (Throwable th2) {
                            CloseableKt.closeFinally(closeable, th);
                            throw th2;
                        }
                    }
                }
            }
            if (r1 == 0) {
                ResultKt.throwOnFailure(obj);
                FileInputStream fileInputStream2 = new FileInputStream(this.this$0.file);
                Serializer serializer2 = this.this$0.serializer;
                this.L$0 = fileInputStream2;
                this.label = 1;
                obj = serializer2.readFrom(fileInputStream2);
                r1 = fileInputStream2;
                if (obj == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else {
                if (r1 != 1) {
                    if (r1 != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    closeable = (java.io.Closeable) this.L$0;
                    try {
                        ResultKt.throwOnFailure(obj);
                        CloseableKt.closeFinally(closeable, null);
                        return this.this$0.serializer.getDefaultValue();
                    } catch (Throwable th3) {
                        th = th3;
                        throw th;
                    }
                }
                java.io.Closeable closeable2 = (java.io.Closeable) this.L$0;
                ResultKt.throwOnFailure(obj);
                r1 = closeable2;
            }
            CloseableKt.closeFinally(r1, null);
            return obj;
        } finally {
        }
    }
}

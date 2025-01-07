package androidx.window.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.ClassReference;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ConsumerAdapter {
    public final ClassLoader loader;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ConsumerHandler implements InvocationHandler {
        public final ClassReference clazz;
        public final Function1 consumer;

        public ConsumerHandler(ClassReference classReference, Function1 function1) {
            this.clazz = classReference;
            this.consumer = function1;
        }

        @Override // java.lang.reflect.InvocationHandler
        public final Object invoke(Object obj, Method method, Object[] objArr) {
            if (Intrinsics.areEqual(method.getName(), "accept") && objArr != null && objArr.length == 1) {
                ClassReference classReference = this.clazz;
                Object obj2 = objArr[0];
                if (classReference.isInstance(obj2)) {
                    this.consumer.invoke(obj2);
                    return Unit.INSTANCE;
                }
                throw new ClassCastException("Value cannot be cast to " + classReference.getQualifiedName());
            }
            if (Intrinsics.areEqual(method.getName(), "equals") && method.getReturnType().equals(Boolean.TYPE) && objArr != null && objArr.length == 1) {
                return Boolean.valueOf(obj == objArr[0]);
            }
            if (Intrinsics.areEqual(method.getName(), "hashCode") && method.getReturnType().equals(Integer.TYPE) && objArr == null) {
                return Integer.valueOf(this.consumer.hashCode());
            }
            if (Intrinsics.areEqual(method.getName(), "toString") && method.getReturnType().equals(String.class) && objArr == null) {
                return this.consumer.toString();
            }
            throw new UnsupportedOperationException("Unexpected method call object:" + obj + ", method: " + method + ", args: " + objArr);
        }
    }

    public ConsumerAdapter(ClassLoader classLoader) {
        this.loader = classLoader;
    }
}

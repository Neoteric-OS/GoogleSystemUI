package kotlin.jvm.internal;

import java.io.Serializable;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class Lambda implements FunctionBase, Serializable {
    private final int arity;

    public Lambda(int i) {
        this.arity = i;
    }

    @Override // kotlin.jvm.internal.FunctionBase
    public int getArity() {
        return this.arity;
    }

    public String toString() {
        Reflection.factory.getClass();
        return ReflectionFactory.renderLambdaToString(this);
    }
}

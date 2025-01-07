package androidx.window.core;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ValidSpecification extends SpecificationComputer {
    public final String tag;
    public final Object value;
    public final VerificationMode verificationMode;

    public ValidSpecification(Object obj, String str, VerificationMode verificationMode) {
        this.value = obj;
        this.tag = str;
        this.verificationMode = verificationMode;
    }

    @Override // androidx.window.core.SpecificationComputer
    public final Object compute() {
        return this.value;
    }
}

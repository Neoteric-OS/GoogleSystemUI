package androidx.window.core;

import android.util.Log;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.ArraysKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FailedSpecification extends SpecificationComputer {
    public final WindowStrictModeException exception;
    public final String message;
    public final String tag;
    public final Object value;
    public final VerificationMode verificationMode;

    public FailedSpecification(Object obj, String str, String str2, VerificationMode verificationMode) {
        this.value = obj;
        this.tag = str;
        this.message = str2;
        this.verificationMode = verificationMode;
        WindowStrictModeException windowStrictModeException = new WindowStrictModeException(str2 + " value: " + obj);
        windowStrictModeException.setStackTrace((StackTraceElement[]) ArraysKt.drop(2, windowStrictModeException.getStackTrace()).toArray(new StackTraceElement[0]));
        this.exception = windowStrictModeException;
    }

    @Override // androidx.window.core.SpecificationComputer
    public final Object compute() {
        int ordinal = this.verificationMode.ordinal();
        if (ordinal == 0) {
            throw this.exception;
        }
        if (ordinal != 1) {
            if (ordinal == 2) {
                return null;
            }
            throw new NoWhenBranchMatchedException();
        }
        Log.d(this.tag, this.message + " value: " + this.value);
        return null;
    }
}

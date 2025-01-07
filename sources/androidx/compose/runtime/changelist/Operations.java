package androidx.compose.runtime.changelist;

import androidx.compose.runtime.Applier;
import androidx.compose.runtime.PreconditionsKt;
import androidx.compose.runtime.SlotWriter;
import androidx.compose.runtime.internal.RememberEventDispatcher;
import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class Operations extends OperationsDebugStringFormattable {
    public int intArgsSize;
    public int objectArgsSize;
    public int opCodesSize;
    public int pushedIntMask;
    public int pushedObjectMask;
    public Operation[] opCodes = new Operation[16];
    public int[] intArgs = new int[16];
    public Object[] objectArgs = new Object[16];

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class OpIterator {
        public int intIdx;
        public int objIdx;
        public int opIdx;

        public OpIterator() {
        }

        /* renamed from: getInt-w8GmfQM, reason: not valid java name */
        public final int m264getIntw8GmfQM(int i) {
            return Operations.this.intArgs[this.intIdx + i];
        }

        /* renamed from: getObject-31yXWZQ, reason: not valid java name */
        public final Object m265getObject31yXWZQ(int i) {
            return Operations.this.objectArgs[this.objIdx + i];
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class WriteScope {
        /* renamed from: setInt-A6tL2VI, reason: not valid java name */
        public static final void m266setIntA6tL2VI(Operations operations, int i, int i2) {
            int i3 = 1 << i;
            if ((operations.pushedIntMask & i3) != 0) {
                PreconditionsKt.throwIllegalStateException("Already pushed argument " + operations.peekOperation().mo260intParamNamew8GmfQM(i));
            }
            operations.pushedIntMask = i3 | operations.pushedIntMask;
            operations.intArgs[(operations.intArgsSize - operations.peekOperation().ints) + i] = i2;
        }

        /* renamed from: setObject-DKhxnng, reason: not valid java name */
        public static final void m267setObjectDKhxnng(Operations operations, int i, Object obj) {
            int i2 = 1 << i;
            if ((operations.pushedObjectMask & i2) != 0) {
                PreconditionsKt.throwIllegalStateException("Already pushed argument " + operations.peekOperation().mo261objectParamName31yXWZQ(i));
            }
            operations.pushedObjectMask = i2 | operations.pushedObjectMask;
            operations.objectArgs[(operations.objectArgsSize - operations.peekOperation().objects) + i] = obj;
        }
    }

    public static final int access$createExpectedArgMask(Operations operations, int i) {
        operations.getClass();
        if (i == 0) {
            return 0;
        }
        return (-1) >>> (32 - i);
    }

    public final void clear() {
        this.opCodesSize = 0;
        this.intArgsSize = 0;
        Arrays.fill(this.objectArgs, 0, this.objectArgsSize, (Object) null);
        this.objectArgsSize = 0;
    }

    public final void executeAndFlushAllPendingOperations(Applier applier, SlotWriter slotWriter, RememberEventDispatcher rememberEventDispatcher) {
        Operations operations;
        int i;
        if (isNotEmpty()) {
            OpIterator opIterator = new OpIterator();
            do {
                operations = Operations.this;
                Operation operation = operations.opCodes[opIterator.opIdx];
                Intrinsics.checkNotNull(operation);
                operation.execute(opIterator, applier, slotWriter, rememberEventDispatcher);
                int i2 = opIterator.opIdx;
                if (i2 >= operations.opCodesSize) {
                    break;
                }
                Operation operation2 = operations.opCodes[i2];
                Intrinsics.checkNotNull(operation2);
                opIterator.intIdx += operation2.ints;
                opIterator.objIdx += operation2.objects;
                i = opIterator.opIdx + 1;
                opIterator.opIdx = i;
            } while (i < operations.opCodesSize);
        }
        clear();
    }

    public final boolean isEmpty() {
        return this.opCodesSize == 0;
    }

    public final boolean isNotEmpty() {
        return this.opCodesSize != 0;
    }

    public final Operation peekOperation() {
        Operation operation = this.opCodes[this.opCodesSize - 1];
        Intrinsics.checkNotNull(operation);
        return operation;
    }

    public final void push(Operation operation) {
        int i = operation.ints;
        int i2 = operation.objects;
        if (i != 0 || i2 != 0) {
            PreconditionsKt.throwIllegalArgumentException("Cannot push " + operation + " without arguments because it expects " + i + " ints and " + i2 + " objects.");
        }
        pushOp(operation);
    }

    public final void pushOp(Operation operation) {
        this.pushedIntMask = 0;
        this.pushedObjectMask = 0;
        int i = this.opCodesSize;
        Operation[] operationArr = this.opCodes;
        if (i == operationArr.length) {
            this.opCodes = (Operation[]) Arrays.copyOf(operationArr, i + (i > 1024 ? 1024 : i));
        }
        int i2 = this.intArgsSize + operation.ints;
        int[] iArr = this.intArgs;
        int length = iArr.length;
        if (i2 > length) {
            int i3 = length + (length > 1024 ? 1024 : length);
            if (i3 >= i2) {
                i2 = i3;
            }
            this.intArgs = Arrays.copyOf(iArr, i2);
        }
        int i4 = this.objectArgsSize;
        int i5 = operation.objects;
        int i6 = i4 + i5;
        Object[] objArr = this.objectArgs;
        int length2 = objArr.length;
        if (i6 > length2) {
            int i7 = length2 + (length2 <= 1024 ? length2 : 1024);
            if (i7 >= i6) {
                i6 = i7;
            }
            this.objectArgs = Arrays.copyOf(objArr, i6);
        }
        Operation[] operationArr2 = this.opCodes;
        int i8 = this.opCodesSize;
        this.opCodesSize = i8 + 1;
        operationArr2[i8] = operation;
        this.intArgsSize += operation.ints;
        this.objectArgsSize += i5;
    }
}

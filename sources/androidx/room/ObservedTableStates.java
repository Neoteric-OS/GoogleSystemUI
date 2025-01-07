package androidx.room;

import java.util.concurrent.locks.ReentrantLock;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ObservedTableStates {
    public final ReentrantLock lock = new ReentrantLock();
    public boolean needsSync;
    public final boolean[] tableObservedState;
    public final long[] tableObserversCount;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ObserveOp {
        public static final /* synthetic */ ObserveOp[] $VALUES;
        public static final ObserveOp ADD;
        public static final ObserveOp NO_OP;
        public static final ObserveOp REMOVE;

        static {
            ObserveOp observeOp = new ObserveOp("NO_OP", 0);
            NO_OP = observeOp;
            ObserveOp observeOp2 = new ObserveOp("ADD", 1);
            ADD = observeOp2;
            ObserveOp observeOp3 = new ObserveOp("REMOVE", 2);
            REMOVE = observeOp3;
            $VALUES = new ObserveOp[]{observeOp, observeOp2, observeOp3};
        }

        public static ObserveOp valueOf(String str) {
            return (ObserveOp) Enum.valueOf(ObserveOp.class, str);
        }

        public static ObserveOp[] values() {
            return (ObserveOp[]) $VALUES.clone();
        }
    }

    public ObservedTableStates(int i) {
        this.tableObserversCount = new long[i];
        this.tableObservedState = new boolean[i];
    }
}

package com.google.common.util.concurrent;

import com.google.common.util.concurrent.AbstractFuture;
import java.util.Set;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.logging.Level;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class AggregateFutureState extends AbstractFuture.TrustedFuture {
    public static final AtomicHelper ATOMIC_HELPER;
    public static final LazyLogger log = new LazyLogger(AggregateFutureState.class);
    public volatile int remaining;
    public volatile Set seenExceptions;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class AtomicHelper {
        public abstract void compareAndSetSeenExceptions(CollectionFuture$ListFuture collectionFuture$ListFuture, Set set);

        public abstract int decrementAndGetRemainingCount(CollectionFuture$ListFuture collectionFuture$ListFuture);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SafeAtomicHelper extends AtomicHelper {
        public final AtomicIntegerFieldUpdater remainingCountUpdater;
        public final AtomicReferenceFieldUpdater seenExceptionsUpdater;

        public SafeAtomicHelper(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, AtomicIntegerFieldUpdater atomicIntegerFieldUpdater) {
            this.seenExceptionsUpdater = atomicReferenceFieldUpdater;
            this.remainingCountUpdater = atomicIntegerFieldUpdater;
        }

        @Override // com.google.common.util.concurrent.AggregateFutureState.AtomicHelper
        public final void compareAndSetSeenExceptions(CollectionFuture$ListFuture collectionFuture$ListFuture, Set set) {
            this.seenExceptionsUpdater.compareAndSet(collectionFuture$ListFuture, null, set);
        }

        @Override // com.google.common.util.concurrent.AggregateFutureState.AtomicHelper
        public final int decrementAndGetRemainingCount(CollectionFuture$ListFuture collectionFuture$ListFuture) {
            return this.remainingCountUpdater.decrementAndGet(collectionFuture$ListFuture);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SynchronizedAtomicHelper extends AtomicHelper {
        @Override // com.google.common.util.concurrent.AggregateFutureState.AtomicHelper
        public final void compareAndSetSeenExceptions(CollectionFuture$ListFuture collectionFuture$ListFuture, Set set) {
            synchronized (collectionFuture$ListFuture) {
                if (collectionFuture$ListFuture.seenExceptions == null) {
                    collectionFuture$ListFuture.seenExceptions = set;
                }
            }
        }

        @Override // com.google.common.util.concurrent.AggregateFutureState.AtomicHelper
        public final int decrementAndGetRemainingCount(CollectionFuture$ListFuture collectionFuture$ListFuture) {
            int i;
            synchronized (collectionFuture$ListFuture) {
                i = collectionFuture$ListFuture.remaining - 1;
                collectionFuture$ListFuture.remaining = i;
            }
            return i;
        }
    }

    static {
        Throwable th;
        AtomicHelper synchronizedAtomicHelper;
        try {
            synchronizedAtomicHelper = new SafeAtomicHelper(AtomicReferenceFieldUpdater.newUpdater(AggregateFutureState.class, Set.class, "seenExceptions"), AtomicIntegerFieldUpdater.newUpdater(AggregateFutureState.class, "remaining"));
            th = null;
        } catch (Throwable th2) {
            th = th2;
            synchronizedAtomicHelper = new SynchronizedAtomicHelper();
        }
        ATOMIC_HELPER = synchronizedAtomicHelper;
        if (th != null) {
            log.get().log(Level.SEVERE, "SafeAtomicHelper is broken!", th);
        }
    }
}

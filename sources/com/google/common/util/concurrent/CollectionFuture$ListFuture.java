package com.google.common.util.concurrent;

import com.google.common.collect.CollectPreconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.util.concurrent.AbstractFuture;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CollectionFuture$ListFuture extends AggregateFutureState {
    public static final LazyLogger logger = new LazyLogger(CollectionFuture$ListFuture.class);
    public final boolean allMustSucceed;
    public final boolean collectsValues;
    public ImmutableList futures;
    public List values;

    public CollectionFuture$ListFuture(ImmutableList immutableList) {
        int size = immutableList.size();
        this.seenExceptions = null;
        this.remaining = size;
        this.futures = immutableList;
        this.allMustSucceed = true;
        this.collectsValues = true;
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    public final void afterDone() {
        ImmutableList immutableList = this.futures;
        this.futures = null;
        this.values = null;
        if ((this.value instanceof AbstractFuture.Cancellation) && (immutableList != null)) {
            boolean wasInterrupted = wasInterrupted();
            ImmutableList.Itr listIterator = immutableList.listIterator(0);
            while (listIterator.hasNext()) {
                ((Future) listIterator.next()).cancel(wasInterrupted);
            }
        }
    }

    public final void collectValueFromNonCancelledFuture(int i, Future future) {
        try {
            Object done = Futures.getDone(future);
            List list = this.values;
            if (list != null) {
                list.set(i, new CollectionFuture$Present(done));
            }
        } catch (ExecutionException e) {
            handleException(e.getCause());
        } catch (Throwable th) {
            handleException(th);
        }
    }

    public final void decrementCountAndMaybeComplete(ImmutableCollection immutableCollection) {
        int decrementAndGetRemainingCount = AggregateFutureState.ATOMIC_HELPER.decrementAndGetRemainingCount(this);
        if (decrementAndGetRemainingCount < 0) {
            throw new IllegalStateException("Less than 0 remaining futures");
        }
        if (decrementAndGetRemainingCount == 0) {
            if (immutableCollection != null) {
                UnmodifiableIterator it = immutableCollection.iterator();
                int i = 0;
                while (it.hasNext()) {
                    Future future = (Future) it.next();
                    if (!future.isCancelled()) {
                        collectValueFromNonCancelledFuture(i, future);
                    }
                    i++;
                }
            }
            this.seenExceptions = null;
            handleAllCompleted();
            this.futures = null;
            this.values = null;
        }
    }

    public final void handleAllCompleted() {
        List<CollectionFuture$Present> list = this.values;
        if (list != null) {
            int size = list.size();
            CollectPreconditions.checkNonnegative(size, "initialArraySize");
            ArrayList arrayList = new ArrayList(size);
            for (CollectionFuture$Present collectionFuture$Present : list) {
                arrayList.add(collectionFuture$Present != null ? collectionFuture$Present.value : null);
            }
            set(Collections.unmodifiableList(arrayList));
        }
    }

    public final void handleException(Throwable th) {
        th.getClass();
        if (this.allMustSucceed && !setException(th)) {
            Set set = this.seenExceptions;
            if (set == null) {
                Set newSetFromMap = Collections.newSetFromMap(new ConcurrentHashMap());
                newSetFromMap.getClass();
                if (!(this.value instanceof AbstractFuture.Cancellation)) {
                    Throwable tryInternalFastPathGetFailure = tryInternalFastPathGetFailure();
                    Objects.requireNonNull(tryInternalFastPathGetFailure);
                    while (tryInternalFastPathGetFailure != null && newSetFromMap.add(tryInternalFastPathGetFailure)) {
                        tryInternalFastPathGetFailure = tryInternalFastPathGetFailure.getCause();
                    }
                }
                AggregateFutureState.ATOMIC_HELPER.compareAndSetSeenExceptions(this, newSetFromMap);
                Set set2 = this.seenExceptions;
                Objects.requireNonNull(set2);
                set = set2;
            }
            for (Throwable th2 = th; th2 != null; th2 = th2.getCause()) {
                if (set.add(th2)) {
                }
            }
            logger.get().log(Level.SEVERE, th instanceof Error ? "Input Future failed with Error" : "Got more than one input Future failure. Logging failures after the first", th);
            return;
        }
        boolean z = th instanceof Error;
        if (z) {
            logger.get().log(Level.SEVERE, z ? "Input Future failed with Error" : "Got more than one input Future failure. Logging failures after the first", th);
        }
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    public final String pendingToString() {
        ImmutableList immutableList = this.futures;
        if (immutableList == null) {
            return super.pendingToString();
        }
        return "futures=" + immutableList;
    }
}

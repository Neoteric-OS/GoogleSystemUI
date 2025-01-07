package com.google.common.util.concurrent;

import com.google.common.base.Function;
import com.google.common.base.MoreObjects$ToStringHelper;
import com.google.common.base.Strings;
import com.google.common.collect.CollectPreconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.TimeoutFuture;
import com.google.common.util.concurrent.internal.InternalFutureFailureAccess;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class Futures {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class CallbackListener implements Runnable {
        public final FutureCallback callback;
        public final ListenableFuture future;

        public CallbackListener(ListenableFuture listenableFuture, FutureCallback futureCallback) {
            this.future = listenableFuture;
            this.callback = futureCallback;
        }

        @Override // java.lang.Runnable
        public final void run() {
            Throwable tryInternalFastPathGetFailure;
            Object obj = this.future;
            if ((obj instanceof InternalFutureFailureAccess) && (tryInternalFastPathGetFailure = ((InternalFutureFailureAccess) obj).tryInternalFastPathGetFailure()) != null) {
                this.callback.onFailure(tryInternalFastPathGetFailure);
                return;
            }
            try {
                this.callback.onSuccess(Futures.getDone(this.future));
            } catch (ExecutionException e) {
                this.callback.onFailure(e.getCause());
            } catch (Throwable th) {
                this.callback.onFailure(th);
            }
        }

        public final String toString() {
            MoreObjects$ToStringHelper moreObjects$ToStringHelper = new MoreObjects$ToStringHelper(CallbackListener.class.getSimpleName());
            FutureCallback futureCallback = this.callback;
            MoreObjects$ToStringHelper.ValueHolder valueHolder = new MoreObjects$ToStringHelper.ValueHolder();
            moreObjects$ToStringHelper.holderTail.next = valueHolder;
            moreObjects$ToStringHelper.holderTail = valueHolder;
            valueHolder.value = futureCallback;
            return moreObjects$ToStringHelper.toString();
        }
    }

    public static CollectionFuture$ListFuture allAsList(Iterable iterable) {
        List arrayList;
        ImmutableList copyOf = ImmutableList.copyOf(iterable);
        final CollectionFuture$ListFuture collectionFuture$ListFuture = new CollectionFuture$ListFuture(copyOf);
        if (copyOf.isEmpty()) {
            arrayList = Collections.emptyList();
        } else {
            int size = copyOf.size();
            CollectPreconditions.checkNonnegative(size, "initialArraySize");
            arrayList = new ArrayList(size);
        }
        final int i = 0;
        int i2 = 0;
        while (true) {
            if (i2 >= copyOf.size()) {
                break;
            }
            arrayList.add(null);
            i2++;
        }
        collectionFuture$ListFuture.values = arrayList;
        Objects.requireNonNull(collectionFuture$ListFuture.futures);
        if (collectionFuture$ListFuture.futures.isEmpty()) {
            collectionFuture$ListFuture.handleAllCompleted();
        } else if (collectionFuture$ListFuture.allMustSucceed) {
            ImmutableList.Itr listIterator = collectionFuture$ListFuture.futures.listIterator(0);
            while (listIterator.hasNext()) {
                final ListenableFuture listenableFuture = (ListenableFuture) listIterator.next();
                listenableFuture.addListener(new Runnable() { // from class: com.google.common.util.concurrent.AggregateFuture$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        CollectionFuture$ListFuture collectionFuture$ListFuture2 = CollectionFuture$ListFuture.this;
                        ListenableFuture listenableFuture2 = listenableFuture;
                        int i3 = i;
                        try {
                            if (listenableFuture2.isCancelled()) {
                                collectionFuture$ListFuture2.futures = null;
                                collectionFuture$ListFuture2.cancel(false);
                            } else {
                                collectionFuture$ListFuture2.collectValueFromNonCancelledFuture(i3, listenableFuture2);
                            }
                            collectionFuture$ListFuture2.decrementCountAndMaybeComplete(null);
                        } catch (Throwable th) {
                            collectionFuture$ListFuture2.decrementCountAndMaybeComplete(null);
                            throw th;
                        }
                    }
                }, DirectExecutor.INSTANCE);
                i++;
            }
        } else {
            final ImmutableList immutableList = collectionFuture$ListFuture.collectsValues ? collectionFuture$ListFuture.futures : null;
            Runnable runnable = new Runnable() { // from class: com.google.common.util.concurrent.AggregateFuture$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    CollectionFuture$ListFuture.this.decrementCountAndMaybeComplete(immutableList);
                }
            };
            ImmutableList.Itr listIterator2 = collectionFuture$ListFuture.futures.listIterator(0);
            while (listIterator2.hasNext()) {
                ((ListenableFuture) listIterator2.next()).addListener(runnable, DirectExecutor.INSTANCE);
            }
        }
        return collectionFuture$ListFuture;
    }

    public static Object getDone(Future future) {
        Object obj;
        if (!future.isDone()) {
            throw new IllegalStateException(Strings.lenientFormat("Future was expected to be done: %s", future));
        }
        boolean z = false;
        while (true) {
            try {
                obj = future.get();
                break;
            } catch (InterruptedException unused) {
                z = true;
            } catch (Throwable th) {
                if (z) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
        return obj;
    }

    public static AbstractTransformFuture.TransformFuture transform(ListenableFuture listenableFuture, Function function, Executor executor) {
        int i = AbstractTransformFuture.$r8$clinit;
        AbstractTransformFuture.TransformFuture transformFuture = new AbstractTransformFuture.TransformFuture();
        transformFuture.inputFuture = listenableFuture;
        transformFuture.function = function;
        listenableFuture.addListener(transformFuture, MoreExecutors.rejectionPropagatingExecutor(executor, transformFuture));
        return transformFuture;
    }

    public static ListenableFuture withTimeout(SettableFuture settableFuture, TimeUnit timeUnit, ScheduledExecutorService scheduledExecutorService) {
        if (settableFuture.isDone()) {
            return settableFuture;
        }
        TimeoutFuture timeoutFuture = new TimeoutFuture();
        timeoutFuture.delegateRef = settableFuture;
        TimeoutFuture.Fire fire = new TimeoutFuture.Fire();
        fire.timeoutFutureRef = timeoutFuture;
        timeoutFuture.timer = scheduledExecutorService.schedule(fire, 5L, timeUnit);
        settableFuture.addListener(fire, DirectExecutor.INSTANCE);
        return timeoutFuture;
    }
}

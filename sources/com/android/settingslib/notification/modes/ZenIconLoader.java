package com.android.settingslib.notification.modes;

import android.content.Context;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.util.LruCache;
import com.android.settingslib.notification.modes.ZenIcon;
import com.google.common.base.Function;
import com.google.common.util.concurrent.AbstractCatchingFuture$CatchingFuture;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ForwardingFluentFuture;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ImmediateFuture;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ZenIconLoader {
    public static final Drawable MISSING = new ColorDrawable();
    public final ListeningExecutorService mBackgroundExecutor;
    public final LruCache mCache = new LruCache(50);

    public ZenIconLoader(ExecutorService executorService) {
        ListeningExecutorService scheduledListeningDecorator;
        if (executorService instanceof ListeningExecutorService) {
            scheduledListeningDecorator = (ListeningExecutorService) executorService;
        } else {
            scheduledListeningDecorator = executorService instanceof ScheduledExecutorService ? new MoreExecutors.ScheduledListeningDecorator((ScheduledExecutorService) executorService) : new MoreExecutors.ListeningDecorator(executorService);
        }
        this.mBackgroundExecutor = scheduledListeningDecorator;
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [com.android.settingslib.notification.modes.ZenIconLoader$$ExternalSyntheticLambda3] */
    public final ListenableFuture loadIcon(final Context context, final ZenIcon.Key key, final boolean z) {
        synchronized (this.mCache) {
            try {
                Drawable drawable = (Drawable) this.mCache.get(key);
                if (drawable != null) {
                    if (drawable == MISSING) {
                        drawable = null;
                    }
                    return drawable == null ? ImmediateFuture.NULL : new ImmediateFuture(drawable);
                }
                ListenableFuture submit = ((MoreExecutors.ListeningDecorator) this.mBackgroundExecutor).submit(new Callable() { // from class: com.android.settingslib.notification.modes.ZenIconLoader$$ExternalSyntheticLambda2
                    @Override // java.util.concurrent.Callable
                    public final Object call() {
                        Context context2 = context;
                        ZenIcon.Key key2 = key;
                        if (TextUtils.isEmpty(key2.resPackage)) {
                            return context2.getDrawable(key2.resId);
                        }
                        Drawable drawable2 = context2.createPackageContext(key2.resPackage, 0).getDrawable(key2.resId);
                        if (z && (drawable2 instanceof AdaptiveIconDrawable)) {
                            AdaptiveIconDrawable adaptiveIconDrawable = (AdaptiveIconDrawable) drawable2;
                            if (adaptiveIconDrawable.getMonochrome() != null) {
                                drawable2 = new InsetDrawable(adaptiveIconDrawable.getMonochrome(), AdaptiveIconDrawable.getExtraInsetFraction() * (-2.0f));
                            }
                        }
                        return drawable2;
                    }
                });
                int i = FluentFuture.$r8$clinit;
                FluentFuture forwardingFluentFuture = submit instanceof FluentFuture ? (FluentFuture) submit : new ForwardingFluentFuture(submit);
                ?? r0 = new Function() { // from class: com.android.settingslib.notification.modes.ZenIconLoader$$ExternalSyntheticLambda3
                    @Override // com.google.common.base.Function
                    public final Object apply(Object obj) {
                        Log.e("ZenIconLoader", "Error while loading mode icon " + ZenIcon.Key.this, (Exception) obj);
                        return null;
                    }
                };
                Executor directExecutor = MoreExecutors.directExecutor();
                forwardingFluentFuture.getClass();
                AbstractCatchingFuture$CatchingFuture abstractCatchingFuture$CatchingFuture = new AbstractCatchingFuture$CatchingFuture();
                abstractCatchingFuture$CatchingFuture.inputFuture = forwardingFluentFuture;
                abstractCatchingFuture$CatchingFuture.exceptionType = Exception.class;
                abstractCatchingFuture$CatchingFuture.fallback = r0;
                forwardingFluentFuture.addListener(abstractCatchingFuture$CatchingFuture, MoreExecutors.rejectionPropagatingExecutor(directExecutor, abstractCatchingFuture$CatchingFuture));
                return Futures.transform(abstractCatchingFuture$CatchingFuture, new ZenIconLoader$$ExternalSyntheticLambda1(this, key, 1), MoreExecutors.directExecutor());
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}

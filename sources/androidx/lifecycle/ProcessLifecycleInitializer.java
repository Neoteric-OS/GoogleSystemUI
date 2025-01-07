package androidx.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleDispatcher;
import androidx.startup.AppInitializer;
import androidx.startup.Initializer;
import java.util.List;
import kotlin.collections.EmptyList;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ProcessLifecycleInitializer implements Initializer {
    @Override // androidx.startup.Initializer
    public final Object create(Context context) {
        if (!AppInitializer.getInstance(context).mDiscovered.contains(ProcessLifecycleInitializer.class)) {
            throw new IllegalStateException("ProcessLifecycleInitializer cannot be initialized lazily.\n               Please ensure that you have:\n               <meta-data\n                   android:name='androidx.lifecycle.ProcessLifecycleInitializer'\n                   android:value='androidx.startup' />\n               under InitializationProvider in your AndroidManifest.xml");
        }
        if (!LifecycleDispatcher.initialized.getAndSet(true)) {
            ((Application) context.getApplicationContext()).registerActivityLifecycleCallbacks(new LifecycleDispatcher.DispatcherActivityCallback());
        }
        final ProcessLifecycleOwner processLifecycleOwner = ProcessLifecycleOwner.newInstance;
        processLifecycleOwner.getClass();
        processLifecycleOwner.handler = new Handler();
        processLifecycleOwner.registry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
        ((Application) context.getApplicationContext()).registerActivityLifecycleCallbacks(new EmptyActivityLifecycleCallbacks() { // from class: androidx.lifecycle.ProcessLifecycleOwner$attach$1
            @Override // androidx.lifecycle.EmptyActivityLifecycleCallbacks, android.app.Application.ActivityLifecycleCallbacks
            public void onActivityPaused(Activity activity) {
                ProcessLifecycleOwner processLifecycleOwner2 = ProcessLifecycleOwner.this;
                int i = processLifecycleOwner2.resumedCounter - 1;
                processLifecycleOwner2.resumedCounter = i;
                if (i == 0) {
                    Handler handler = processLifecycleOwner2.handler;
                    Intrinsics.checkNotNull(handler);
                    handler.postDelayed(processLifecycleOwner2.delayedPauseRunnable, 700L);
                }
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityPreCreated(Activity activity, Bundle bundle) {
                final ProcessLifecycleOwner processLifecycleOwner2 = ProcessLifecycleOwner.this;
                activity.registerActivityLifecycleCallbacks(new EmptyActivityLifecycleCallbacks() { // from class: androidx.lifecycle.ProcessLifecycleOwner$attach$1$onActivityPreCreated$1
                    @Override // android.app.Application.ActivityLifecycleCallbacks
                    public void onActivityPostResumed(Activity activity2) {
                        ProcessLifecycleOwner processLifecycleOwner3 = ProcessLifecycleOwner.this;
                        int i = processLifecycleOwner3.resumedCounter + 1;
                        processLifecycleOwner3.resumedCounter = i;
                        if (i == 1) {
                            if (processLifecycleOwner3.pauseSent) {
                                processLifecycleOwner3.registry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
                                processLifecycleOwner3.pauseSent = false;
                            } else {
                                Handler handler = processLifecycleOwner3.handler;
                                Intrinsics.checkNotNull(handler);
                                handler.removeCallbacks(processLifecycleOwner3.delayedPauseRunnable);
                            }
                        }
                    }

                    @Override // android.app.Application.ActivityLifecycleCallbacks
                    public void onActivityPostStarted(Activity activity2) {
                        ProcessLifecycleOwner processLifecycleOwner3 = ProcessLifecycleOwner.this;
                        int i = processLifecycleOwner3.startedCounter + 1;
                        processLifecycleOwner3.startedCounter = i;
                        if (i == 1 && processLifecycleOwner3.stopSent) {
                            processLifecycleOwner3.registry.handleLifecycleEvent(Lifecycle.Event.ON_START);
                            processLifecycleOwner3.stopSent = false;
                        }
                    }
                });
            }

            @Override // androidx.lifecycle.EmptyActivityLifecycleCallbacks, android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStopped(Activity activity) {
                ProcessLifecycleOwner processLifecycleOwner2 = ProcessLifecycleOwner.this;
                int i = processLifecycleOwner2.startedCounter - 1;
                processLifecycleOwner2.startedCounter = i;
                if (i == 0 && processLifecycleOwner2.pauseSent) {
                    processLifecycleOwner2.registry.handleLifecycleEvent(Lifecycle.Event.ON_STOP);
                    processLifecycleOwner2.stopSent = true;
                }
            }

            @Override // androidx.lifecycle.EmptyActivityLifecycleCallbacks, android.app.Application.ActivityLifecycleCallbacks
            public void onActivityCreated(Activity activity, Bundle bundle) {
            }
        });
        return processLifecycleOwner;
    }

    @Override // androidx.startup.Initializer
    public final List dependencies() {
        return EmptyList.INSTANCE;
    }
}

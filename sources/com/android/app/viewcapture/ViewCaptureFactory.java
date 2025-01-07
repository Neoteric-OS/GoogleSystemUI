package com.android.app.viewcapture;

import android.content.Context;
import android.os.Build;
import android.os.Looper;
import android.util.Log;
import com.android.app.viewcapture.ViewCaptureFactory;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ViewCaptureFactory {
    public static ViewCapture instance;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Companion {
        public static ViewCapture getInstance(final Context context) {
            ViewCapture settingsAwareViewCapture;
            if (!Intrinsics.areEqual(Looper.myLooper(), Looper.getMainLooper())) {
                LooperExecutor looperExecutor = ViewCapture.MAIN_EXECUTOR;
                Callable callable = new Callable() { // from class: com.android.app.viewcapture.ViewCaptureFactory$Companion$getInstance$1
                    @Override // java.util.concurrent.Callable
                    public final Object call() {
                        return ViewCaptureFactory.Companion.getInstance(context);
                    }
                };
                looperExecutor.getClass();
                FutureTask futureTask = new FutureTask(callable);
                looperExecutor.execute(futureTask);
                return (ViewCapture) futureTask.get();
            }
            ViewCapture viewCapture = ViewCaptureFactory.instance;
            if (viewCapture != null) {
                return viewCapture;
            }
            if (Build.IS_DEBUGGABLE) {
                Log.i("ViewCaptureFactory", "instantiating SettingsAwareViewCapture");
                settingsAwareViewCapture = new SettingsAwareViewCapture(context.getApplicationContext(), ViewCapture.createAndStartNewLooperExecutor(-2, "SAViewCapture"));
            } else {
                Log.i("ViewCaptureFactory", "instantiating NoOpViewCapture");
                settingsAwareViewCapture = new NoOpViewCapture(0, 0, ViewCapture.createAndStartNewLooperExecutor(1, "NoOpViewCapture"));
            }
            ViewCaptureFactory.instance = settingsAwareViewCapture;
            return settingsAwareViewCapture;
        }
    }
}

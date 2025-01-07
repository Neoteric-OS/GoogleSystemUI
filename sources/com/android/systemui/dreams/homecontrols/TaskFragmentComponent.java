package com.android.systemui.dreams.homecontrols;

import android.app.Activity;
import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.os.Binder;
import android.window.TaskFragmentInfo;
import android.window.TaskFragmentOperation;
import android.window.TaskFragmentOrganizer;
import android.window.TaskFragmentTransaction;
import android.window.WindowContainerTransaction;
import com.android.systemui.util.concurrency.DelayableExecutor;
import java.lang.ref.WeakReference;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TaskFragmentComponent {
    public final Activity activity;
    public final Binder fragmentToken = new Binder();
    public final Function0 hide;
    public final Function1 onCreateCallback;
    public final Function1 onInfoChangedCallback;
    public final Organizer organizer;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Factory {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Organizer extends TaskFragmentOrganizer {
        public final WeakReference component;

        public Organizer(WeakReference weakReference, DelayableExecutor delayableExecutor) {
            super(delayableExecutor);
            this.component = weakReference;
        }

        public final void onTransactionReady(TaskFragmentTransaction taskFragmentTransaction) {
            TaskFragmentComponent taskFragmentComponent = (TaskFragmentComponent) this.component.get();
            if (taskFragmentComponent != null) {
                WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
                for (TaskFragmentTransaction.Change change : taskFragmentTransaction.getChanges()) {
                    TaskFragmentInfo taskFragmentInfo = change.getTaskFragmentInfo();
                    if (taskFragmentInfo != null && Intrinsics.areEqual(taskFragmentInfo.getFragmentToken(), taskFragmentComponent.fragmentToken)) {
                        int type = change.getType();
                        Function0 function0 = taskFragmentComponent.hide;
                        switch (type) {
                            case 1:
                                windowContainerTransaction.addTaskFragmentOperation(taskFragmentComponent.fragmentToken, new TaskFragmentOperation.Builder(13).build());
                                ((HomeControlsDreamService$onAttachedToWindow$2) taskFragmentComponent.onCreateCallback).invoke(taskFragmentInfo);
                                break;
                            case 2:
                                ((HomeControlsDreamService$onAttachedToWindow$3) taskFragmentComponent.onInfoChangedCallback).invoke(taskFragmentInfo);
                                break;
                            case 3:
                                ((HomeControlsDreamService$onAttachedToWindow$4) function0).invoke();
                                break;
                            case 4:
                            case 6:
                                break;
                            case 5:
                                ((HomeControlsDreamService$onAttachedToWindow$4) function0).invoke();
                                break;
                            default:
                                throw new IllegalArgumentException(AnnotationValue$1$$ExternalSyntheticOutline0.m(change.getType(), "Unknown TaskFragmentEvent="));
                        }
                    }
                }
                taskFragmentComponent.organizer.onTransactionHandled(taskFragmentTransaction.getTransactionToken(), windowContainerTransaction, 6, false);
            }
        }
    }

    public TaskFragmentComponent(Activity activity, Function1 function1, Function1 function12, Function0 function0, DelayableExecutor delayableExecutor) {
        this.activity = activity;
        this.onCreateCallback = function1;
        this.onInfoChangedCallback = function12;
        this.hide = function0;
        Organizer organizer = new Organizer(new WeakReference(this), delayableExecutor);
        organizer.registerOrganizer(true);
        this.organizer = organizer;
    }
}

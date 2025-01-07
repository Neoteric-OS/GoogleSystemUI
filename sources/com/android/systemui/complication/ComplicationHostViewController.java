package com.android.systemui.complication;

import android.os.Debug;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.VelocityTrackerCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.lifecycle.Observer;
import com.android.systemui.dreams.DreamOverlayLifecycleOwner;
import com.android.systemui.dreams.DreamOverlayStateController;
import com.android.systemui.util.ViewController;
import com.android.systemui.util.settings.SecureSettings;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ComplicationHostViewController extends ViewController {
    public static final boolean DEBUG = Log.isLoggable("ComplicationHostVwCtrl", 3);
    public final ComplicationCollectionViewModel mComplicationCollectionViewModel;
    public final HashMap mComplications;
    boolean mIsAnimationEnabled;
    public final ComplicationLayoutEngine mLayoutEngine;
    public final DreamOverlayLifecycleOwner mLifecycleOwner;

    public ComplicationHostViewController(ConstraintLayout constraintLayout, ComplicationLayoutEngine complicationLayoutEngine, DreamOverlayStateController dreamOverlayStateController, DreamOverlayLifecycleOwner dreamOverlayLifecycleOwner, ComplicationCollectionViewModel complicationCollectionViewModel, SecureSettings secureSettings) {
        super(constraintLayout);
        this.mComplications = new HashMap();
        this.mLayoutEngine = complicationLayoutEngine;
        this.mLifecycleOwner = dreamOverlayLifecycleOwner;
        this.mComplicationCollectionViewModel = complicationCollectionViewModel;
        this.mIsAnimationEnabled = secureSettings.getFloatForUser(1.0f, -2, "animator_duration_scale") != 0.0f;
    }

    public final List getViewsAtPosition(final int i) {
        final int i2 = 0;
        Stream flatMap = this.mLayoutEngine.mPositions.entrySet().stream().filter(new Predicate() { // from class: com.android.systemui.complication.ComplicationLayoutEngine$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                int i3 = i;
                return (((Integer) ((Map.Entry) obj).getKey()).intValue() & i3) == i3;
            }
        }).flatMap(new Function() { // from class: com.android.systemui.complication.ComplicationLayoutEngine$$ExternalSyntheticLambda1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                switch (i2) {
                    case 0:
                        VelocityTrackerCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(((Map.Entry) obj).getValue());
                        throw null;
                    default:
                        throw null;
                }
            }
        });
        final int i3 = 1;
        return (List) flatMap.map(new Function() { // from class: com.android.systemui.complication.ComplicationLayoutEngine$$ExternalSyntheticLambda1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                switch (i3) {
                    case 0:
                        VelocityTrackerCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(((Map.Entry) obj).getValue());
                        throw null;
                    default:
                        throw null;
                }
            }
        }).collect(Collectors.toList());
    }

    @Override // com.android.systemui.util.ViewController
    public final void onInit() {
        this.mComplicationCollectionViewModel.mComplications.observe(this.mLifecycleOwner, new Observer() { // from class: com.android.systemui.complication.ComplicationHostViewController$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                Collection collection = (Collection) obj;
                final ComplicationHostViewController complicationHostViewController = ComplicationHostViewController.this;
                complicationHostViewController.getClass();
                if (ComplicationHostViewController.DEBUG) {
                    Log.d("ComplicationHostVwCtrl", "updateComplications called. Callers = " + Debug.getCallers(25));
                    Log.d("ComplicationHostVwCtrl", "    mComplications = " + complicationHostViewController.mComplications.toString());
                    Log.d("ComplicationHostVwCtrl", "    complications = " + collection.toString());
                }
                final Collection collection2 = (Collection) collection.stream().map(new ComplicationHostViewController$$ExternalSyntheticLambda1()).collect(Collectors.toSet());
                final int i = 0;
                final int i2 = 0;
                ((Collection) complicationHostViewController.mComplications.keySet().stream().filter(new Predicate() { // from class: com.android.systemui.complication.ComplicationHostViewController$$ExternalSyntheticLambda2
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj2) {
                        int i3 = i;
                        Object obj3 = collection2;
                        switch (i3) {
                            case 0:
                                return !((Collection) obj3).contains((ComplicationId) obj2);
                            default:
                                ComplicationHostViewController complicationHostViewController2 = (ComplicationHostViewController) obj3;
                                if (obj2 != null) {
                                    throw new ClassCastException();
                                }
                                complicationHostViewController2.getClass();
                                throw null;
                        }
                    }
                }).collect(Collectors.toSet())).forEach(new Consumer() { // from class: com.android.systemui.complication.ComplicationHostViewController$$ExternalSyntheticLambda3
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj2) {
                        int i3 = i2;
                        ComplicationHostViewController complicationHostViewController2 = complicationHostViewController;
                        switch (i3) {
                            case 0:
                                ComplicationId complicationId = (ComplicationId) obj2;
                                Log.e("ComplicationLayoutEng", "could not find id:" + complicationId);
                                complicationHostViewController2.mComplications.remove(complicationId);
                                return;
                            default:
                                if (obj2 != null) {
                                    throw new ClassCastException();
                                }
                                complicationHostViewController2.getClass();
                                throw null;
                        }
                    }
                });
                final int i3 = 1;
                ((Collection) collection.stream().filter(new Predicate() { // from class: com.android.systemui.complication.ComplicationHostViewController$$ExternalSyntheticLambda2
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj2) {
                        int i32 = i3;
                        Object obj3 = complicationHostViewController;
                        switch (i32) {
                            case 0:
                                return !((Collection) obj3).contains((ComplicationId) obj2);
                            default:
                                ComplicationHostViewController complicationHostViewController2 = (ComplicationHostViewController) obj3;
                                if (obj2 != null) {
                                    throw new ClassCastException();
                                }
                                complicationHostViewController2.getClass();
                                throw null;
                        }
                    }
                }).collect(Collectors.toSet())).forEach(new Consumer() { // from class: com.android.systemui.complication.ComplicationHostViewController$$ExternalSyntheticLambda3
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj2) {
                        int i32 = i3;
                        ComplicationHostViewController complicationHostViewController2 = complicationHostViewController;
                        switch (i32) {
                            case 0:
                                ComplicationId complicationId = (ComplicationId) obj2;
                                Log.e("ComplicationLayoutEng", "could not find id:" + complicationId);
                                complicationHostViewController2.mComplications.remove(complicationId);
                                return;
                            default:
                                if (obj2 != null) {
                                    throw new ClassCastException();
                                }
                                complicationHostViewController2.getClass();
                                throw null;
                        }
                    }
                });
            }
        });
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewAttached() {
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewDetached() {
    }
}

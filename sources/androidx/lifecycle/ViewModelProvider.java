package androidx.lifecycle;

import android.app.Application;
import androidx.lifecycle.viewmodel.MutableCreationExtras;
import java.lang.reflect.InvocationTargetException;
import kotlin.jvm.internal.ClassReference;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ViewModelProvider {
    public static final ViewModelProvider$special$$inlined$Key$1 VIEW_MODEL_KEY = new ViewModelProvider$special$$inlined$Key$1();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class OnRequeryFactory {
        public abstract void onRequery(ViewModel viewModel);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Factory {
        default ViewModel create(ClassReference classReference, MutableCreationExtras mutableCreationExtras) {
            return create(classReference.getJClass(), mutableCreationExtras);
        }

        default ViewModel create(Class cls) {
            throw new UnsupportedOperationException("`Factory.create(String, CreationExtras)` is not implemented. You may need to override the method and provide a custom implementation. Note that using `Factory.create(String)` is not supported and considered an error.");
        }

        default ViewModel create(Class cls, MutableCreationExtras mutableCreationExtras) {
            return create(cls);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public class NewInstanceFactory implements Factory {
        public static NewInstanceFactory _instance;

        @Override // androidx.lifecycle.ViewModelProvider.Factory
        public ViewModel create(Class cls) {
            try {
                Class[] clsArr = new Class[0];
                return (ViewModel) cls.getDeclaredConstructor(null).newInstance(null);
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Cannot create an instance of " + cls, e);
            } catch (InstantiationException e2) {
                throw new RuntimeException("Cannot create an instance of " + cls, e2);
            } catch (NoSuchMethodException e3) {
                throw new RuntimeException("Cannot create an instance of " + cls, e3);
            }
        }

        @Override // androidx.lifecycle.ViewModelProvider.Factory
        public final ViewModel create(ClassReference classReference, MutableCreationExtras mutableCreationExtras) {
            return create(classReference.getJClass(), mutableCreationExtras);
        }

        @Override // androidx.lifecycle.ViewModelProvider.Factory
        public ViewModel create(Class cls, MutableCreationExtras mutableCreationExtras) {
            return create(cls);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class AndroidViewModelFactory extends NewInstanceFactory {
        public static final ViewModelProvider$special$$inlined$Key$1 APPLICATION_KEY = new ViewModelProvider$special$$inlined$Key$1();
        public static AndroidViewModelFactory _instance;
        public final Application application;

        public AndroidViewModelFactory(Application application) {
            this.application = application;
        }

        @Override // androidx.lifecycle.ViewModelProvider.NewInstanceFactory, androidx.lifecycle.ViewModelProvider.Factory
        public final ViewModel create(Class cls, MutableCreationExtras mutableCreationExtras) {
            if (this.application != null) {
                return create(cls);
            }
            Application application = (Application) mutableCreationExtras.extras.get(APPLICATION_KEY);
            if (application != null) {
                return create(cls, application);
            }
            if (AndroidViewModel.class.isAssignableFrom(cls)) {
                throw new IllegalArgumentException("CreationExtras must have an application by `APPLICATION_KEY`");
            }
            return super.create(cls);
        }

        @Override // androidx.lifecycle.ViewModelProvider.NewInstanceFactory, androidx.lifecycle.ViewModelProvider.Factory
        public final ViewModel create(Class cls) {
            Application application = this.application;
            if (application != null) {
                return create(cls, application);
            }
            throw new UnsupportedOperationException("AndroidViewModelFactory constructed with empty constructor works only with create(modelClass: Class<T>, extras: CreationExtras).");
        }

        public final ViewModel create(Class cls, Application application) {
            if (AndroidViewModel.class.isAssignableFrom(cls)) {
                try {
                    return (ViewModel) cls.getConstructor(Application.class).newInstance(application);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Cannot create an instance of " + cls, e);
                } catch (InstantiationException e2) {
                    throw new RuntimeException("Cannot create an instance of " + cls, e2);
                } catch (NoSuchMethodException e3) {
                    throw new RuntimeException("Cannot create an instance of " + cls, e3);
                } catch (InvocationTargetException e4) {
                    throw new RuntimeException("Cannot create an instance of " + cls, e4);
                }
            }
            return super.create(cls);
        }
    }
}

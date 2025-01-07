package androidx.window.embedding;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.ArrayMap;
import android.util.Log;
import androidx.core.app.NotificationCompat$Builder$$ExternalSyntheticOutline0;
import androidx.profileinstaller.ProfileInstallReceiver$$ExternalSyntheticLambda0;
import androidx.window.core.ConsumerAdapter;
import androidx.window.core.ExtensionsUtil;
import androidx.window.core.PredicateAdapter;
import androidx.window.embedding.EmbeddingCompat;
import androidx.window.embedding.ExtensionEmbeddingBackend;
import androidx.window.extensions.WindowExtensionsProvider;
import androidx.window.extensions.embedding.ActivityEmbeddingComponent;
import androidx.window.reflection.Consumer2;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.ClassReference;
import kotlin.jvm.internal.Reflection;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ExtensionEmbeddingBackend implements EmbeddingBackend {
    public static volatile ExtensionEmbeddingBackend globalInstance;
    public static final ReentrantLock globalLock = new ReentrantLock();
    public final Context applicationContext;
    public final EmbeddingCompat embeddingExtension;
    public final CopyOnWriteArrayList splitChangeCallbacks;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Companion {
        public static EmbeddingCompat initAndVerifyEmbeddingExtension() {
            ClassLoader classLoader;
            ActivityEmbeddingComponent activityEmbeddingComponent;
            int safeVendorApiLevel = ExtensionsUtil.getSafeVendorApiLevel();
            EmbeddingCompat embeddingCompat = null;
            if (safeVendorApiLevel >= 1) {
                try {
                    if (EmbeddingCompat.Companion.isEmbeddingAvailable() && (classLoader = EmbeddingBackend.class.getClassLoader()) != null) {
                        if (EmbeddingCompat.Companion.isEmbeddingAvailable()) {
                            ClassLoader classLoader2 = EmbeddingCompat.class.getClassLoader();
                            if (classLoader2 == null || (activityEmbeddingComponent = new SafeActivityEmbeddingComponentProvider(classLoader2, new ConsumerAdapter(classLoader2), WindowExtensionsProvider.getWindowExtensions()).getActivityEmbeddingComponent()) == null) {
                                activityEmbeddingComponent = (ActivityEmbeddingComponent) Proxy.newProxyInstance(EmbeddingCompat.class.getClassLoader(), new Class[]{ActivityEmbeddingComponent.class}, new EmbeddingCompat$Companion$$ExternalSyntheticLambda0());
                            }
                        } else {
                            activityEmbeddingComponent = (ActivityEmbeddingComponent) Proxy.newProxyInstance(EmbeddingCompat.class.getClassLoader(), new Class[]{ActivityEmbeddingComponent.class}, new EmbeddingCompat$Companion$$ExternalSyntheticLambda0());
                        }
                        EmbeddingAdapter embeddingAdapter = new EmbeddingAdapter(new PredicateAdapter());
                        ConsumerAdapter consumerAdapter = new ConsumerAdapter(classLoader);
                        if (safeVendorApiLevel >= 8) {
                            new OverlayControllerImpl(activityEmbeddingComponent, embeddingAdapter);
                        }
                        if (safeVendorApiLevel >= 6) {
                            new ReentrantLock();
                            new ArrayMap();
                            int safeVendorApiLevel2 = ExtensionsUtil.getSafeVendorApiLevel();
                            if (safeVendorApiLevel2 < 6) {
                                throw new UnsupportedOperationException("This API requires extension version 6, but the device is on " + safeVendorApiLevel2);
                            }
                        }
                        embeddingCompat = new EmbeddingCompat(activityEmbeddingComponent, embeddingAdapter, consumerAdapter);
                    }
                } catch (Throwable th) {
                    Log.d("EmbeddingBackend", "Failed to load embedding extension: " + th);
                }
            }
            if (embeddingCompat == null) {
                Log.d("EmbeddingBackend", "No supported embedding extension found");
            }
            return embeddingCompat;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class EmbeddingCallbackImpl implements EmbeddingInterfaceCompat$EmbeddingCallbackInterface {
        public EmbeddingCallbackImpl() {
            EmptyList emptyList = EmptyList.INSTANCE;
        }
    }

    public ExtensionEmbeddingBackend(Context context, final EmbeddingCompat embeddingCompat) {
        this.applicationContext = context;
        this.embeddingExtension = embeddingCompat;
        final EmbeddingCallbackImpl embeddingCallbackImpl = new EmbeddingCallbackImpl();
        this.splitChangeCallbacks = new CopyOnWriteArrayList();
        if (embeddingCompat != null) {
            int i = embeddingCompat.windowSdkExtensions.extensionVersion;
            if (i == 1) {
                ActivityEmbeddingComponent activityEmbeddingComponent = embeddingCompat.embeddingExtension;
                ClassReference orCreateKotlinClass = Reflection.getOrCreateKotlinClass(List.class);
                Function1 function1 = new Function1() { // from class: androidx.window.embedding.EmbeddingCompat$setEmbeddingCallback$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        ArrayList arrayList = new ArrayList();
                        for (Object obj2 : (List) obj) {
                            if (obj2 instanceof androidx.window.extensions.embedding.SplitInfo) {
                                arrayList.add(obj2);
                            }
                        }
                        EmbeddingInterfaceCompat$EmbeddingCallbackInterface embeddingInterfaceCompat$EmbeddingCallbackInterface = embeddingCallbackImpl;
                        embeddingCompat.adapter.translate(arrayList);
                        Iterator it = ExtensionEmbeddingBackend.this.splitChangeCallbacks.iterator();
                        if (it.hasNext()) {
                            throw NotificationCompat$Builder$$ExternalSyntheticOutline0.m(it);
                        }
                        return Unit.INSTANCE;
                    }
                };
                ConsumerAdapter consumerAdapter = embeddingCompat.consumerAdapter;
                Method method = activityEmbeddingComponent.getClass().getMethod("setSplitInfoCallback", consumerAdapter.loader.loadClass("java.util.function.Consumer"));
                ConsumerAdapter.ConsumerHandler consumerHandler = new ConsumerAdapter.ConsumerHandler(orCreateKotlinClass, function1);
                ClassLoader classLoader = consumerAdapter.loader;
                method.invoke(activityEmbeddingComponent, Proxy.newProxyInstance(classLoader, new Class[]{classLoader.loadClass("java.util.function.Consumer")}, consumerHandler));
            } else if (2 <= i && i < 5) {
                final int i2 = 1;
                embeddingCompat.embeddingExtension.setSplitInfoCallback(new Consumer2() { // from class: androidx.window.embedding.EmbeddingCompat$$ExternalSyntheticLambda0
                    @Override // androidx.window.reflection.Consumer2
                    public final void accept(Object obj) {
                        switch (i2) {
                            case 0:
                                List list = (List) obj;
                                EmbeddingAdapter embeddingAdapter = embeddingCompat.adapter;
                                embeddingAdapter.getClass();
                                ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
                                Iterator it = list.iterator();
                                while (it.hasNext()) {
                                    arrayList.add(embeddingAdapter.translate$window_release((androidx.window.extensions.embedding.ActivityStack) it.next()));
                                }
                                return;
                            default:
                                ExtensionEmbeddingBackend.EmbeddingCallbackImpl embeddingCallbackImpl2 = embeddingCallbackImpl;
                                embeddingCompat.adapter.translate((List) obj);
                                Iterator it2 = ExtensionEmbeddingBackend.this.splitChangeCallbacks.iterator();
                                if (it2.hasNext()) {
                                    throw NotificationCompat$Builder$$ExternalSyntheticOutline0.m(it2);
                                }
                                return;
                        }
                    }
                });
            } else if (5 <= i && i <= Integer.MAX_VALUE) {
                final int i3 = 1;
                embeddingCompat.embeddingExtension.setSplitInfoCallback(new Consumer2() { // from class: androidx.window.embedding.EmbeddingCompat$$ExternalSyntheticLambda0
                    @Override // androidx.window.reflection.Consumer2
                    public final void accept(Object obj) {
                        switch (i3) {
                            case 0:
                                List list = (List) obj;
                                EmbeddingAdapter embeddingAdapter = embeddingCompat.adapter;
                                embeddingAdapter.getClass();
                                ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
                                Iterator it = list.iterator();
                                while (it.hasNext()) {
                                    arrayList.add(embeddingAdapter.translate$window_release((androidx.window.extensions.embedding.ActivityStack) it.next()));
                                }
                                return;
                            default:
                                ExtensionEmbeddingBackend.EmbeddingCallbackImpl embeddingCallbackImpl2 = embeddingCallbackImpl;
                                embeddingCompat.adapter.translate((List) obj);
                                Iterator it2 = ExtensionEmbeddingBackend.this.splitChangeCallbacks.iterator();
                                if (it2.hasNext()) {
                                    throw NotificationCompat$Builder$$ExternalSyntheticOutline0.m(it2);
                                }
                                return;
                        }
                    }
                });
                final int i4 = 0;
                embeddingCompat.embeddingExtension.registerActivityStackCallback(new ProfileInstallReceiver$$ExternalSyntheticLambda0(), new Consumer2() { // from class: androidx.window.embedding.EmbeddingCompat$$ExternalSyntheticLambda0
                    @Override // androidx.window.reflection.Consumer2
                    public final void accept(Object obj) {
                        switch (i4) {
                            case 0:
                                List list = (List) obj;
                                EmbeddingAdapter embeddingAdapter = embeddingCompat.adapter;
                                embeddingAdapter.getClass();
                                ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
                                Iterator it = list.iterator();
                                while (it.hasNext()) {
                                    arrayList.add(embeddingAdapter.translate$window_release((androidx.window.extensions.embedding.ActivityStack) it.next()));
                                }
                                return;
                            default:
                                ExtensionEmbeddingBackend.EmbeddingCallbackImpl embeddingCallbackImpl2 = embeddingCallbackImpl;
                                embeddingCompat.adapter.translate((List) obj);
                                Iterator it2 = ExtensionEmbeddingBackend.this.splitChangeCallbacks.iterator();
                                if (it2.hasNext()) {
                                    throw NotificationCompat$Builder$$ExternalSyntheticOutline0.m(it2);
                                }
                                return;
                        }
                    }
                });
            }
        }
        new HashMap();
        LazyKt__LazyJVMKt.lazy(new Function0() { // from class: androidx.window.embedding.ExtensionEmbeddingBackend$splitSupportStatus$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                ExtensionEmbeddingBackend extensionEmbeddingBackend = ExtensionEmbeddingBackend.this;
                EmbeddingCompat embeddingCompat2 = extensionEmbeddingBackend.embeddingExtension;
                SplitController$SplitSupportStatus splitController$SplitSupportStatus = SplitController$SplitSupportStatus.SPLIT_UNAVAILABLE;
                if (embeddingCompat2 == null) {
                    return splitController$SplitSupportStatus;
                }
                Context context2 = extensionEmbeddingBackend.applicationContext;
                SplitController$SplitSupportStatus splitController$SplitSupportStatus2 = SplitController$SplitSupportStatus.SPLIT_ERROR_PROPERTY_NOT_DECLARED;
                try {
                    PackageManager.Property property = context2.getPackageManager().getProperty("android.window.PROPERTY_ACTIVITY_EMBEDDING_SPLITS_ENABLED", context2.getPackageName());
                    if (property.isBoolean()) {
                        return property.getBoolean() ? SplitController$SplitSupportStatus.SPLIT_AVAILABLE : splitController$SplitSupportStatus;
                    }
                } catch (PackageManager.NameNotFoundException | Exception unused) {
                }
                return splitController$SplitSupportStatus2;
            }
        });
    }
}

package com.android.systemui;

import android.app.Activity;
import android.app.AppComponentFactory;
import android.app.Application;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentCallbacks2;
import android.content.ContentProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import androidx.core.app.CoreComponentFactory;
import com.android.systemui.dagger.ContextComponentResolver;
import com.google.android.systemui.SystemUIGoogleInitializer;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ExecutionException;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class SystemUIAppComponentFactoryBase extends AppComponentFactory {
    public static SystemUIGoogleInitializer systemUIInitializer;
    public ContextComponentResolver componentHelper;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface ContextAvailableCallback {
        SystemUIGoogleInitializer onContextAvailable(Context context);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface ContextInitializer {
        void setContextAvailableCallback(ContextAvailableCallback contextAvailableCallback);
    }

    public static final SystemUIGoogleInitializer access$createSystemUIInitializerInternal(SystemUIAppComponentFactoryBase systemUIAppComponentFactoryBase, Context context) {
        systemUIAppComponentFactoryBase.getClass();
        SystemUIGoogleInitializer systemUIGoogleInitializer = systemUIInitializer;
        if (systemUIGoogleInitializer == null) {
            systemUIGoogleInitializer = new SystemUIGoogleInitializer(context.getApplicationContext());
            try {
                systemUIGoogleInitializer.init();
                systemUIGoogleInitializer.mSysUIComponent.inject(systemUIAppComponentFactoryBase);
                systemUIInitializer = systemUIGoogleInitializer;
            } catch (InterruptedException e) {
                throw new RuntimeException("Failed to initialize SysUI", e);
            } catch (ExecutionException e2) {
                throw new RuntimeException("Failed to initialize SysUI", e2);
            }
        }
        return systemUIGoogleInitializer;
    }

    @Override // android.app.AppComponentFactory
    public final Activity instantiateActivity(ClassLoader classLoader, String str, Intent intent) {
        SystemUIGoogleInitializer systemUIGoogleInitializer;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
        if (this.componentHelper == null && (systemUIGoogleInitializer = systemUIInitializer) != null && (daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = systemUIGoogleInitializer.mSysUIComponent) != null) {
            daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.inject(this);
        }
        ContextComponentResolver contextComponentResolver = this.componentHelper;
        if (contextComponentResolver == null) {
            contextComponentResolver = null;
        }
        Activity activity = (Activity) ContextComponentResolver.resolve(str, contextComponentResolver.mActivityCreators);
        if (activity == null) {
            try {
                Class[] clsArr = new Class[0];
                activity = (Activity) Class.forName(str, false, classLoader).asSubclass(Activity.class).getDeclaredConstructor(null).newInstance(null);
            } catch (NoSuchMethodException | InvocationTargetException e) {
                throw new RuntimeException("Couldn't call constructor", e);
            }
        }
        return (Activity) CoreComponentFactory.checkCompatWrapper(activity);
    }

    @Override // android.app.AppComponentFactory
    public final Application instantiateApplication(ClassLoader classLoader, String str) {
        try {
            Class<? extends U> asSubclass = Class.forName(str, false, classLoader).asSubclass(Application.class);
            Class[] clsArr = new Class[0];
            ComponentCallbacks2 componentCallbacks2 = (Application) asSubclass.getDeclaredConstructor(null).newInstance(null);
            if (!(componentCallbacks2 instanceof ContextInitializer)) {
                throw new RuntimeException("App must implement ContextInitializer");
            }
            ((ContextInitializer) componentCallbacks2).setContextAvailableCallback(new ContextAvailableCallback() { // from class: com.android.systemui.SystemUIAppComponentFactoryBase$instantiateApplicationCompat$1
                @Override // com.android.systemui.SystemUIAppComponentFactoryBase.ContextAvailableCallback
                public final SystemUIGoogleInitializer onContextAvailable(Context context) {
                    return SystemUIAppComponentFactoryBase.access$createSystemUIInitializerInternal(SystemUIAppComponentFactoryBase.this, context);
                }
            });
            return (Application) CoreComponentFactory.checkCompatWrapper(componentCallbacks2);
        } catch (NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException("Couldn't call constructor", e);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.app.AppComponentFactory
    public final ContentProvider instantiateProvider(ClassLoader classLoader, String str) {
        try {
            Class<? extends U> asSubclass = Class.forName(str, false, classLoader).asSubclass(ContentProvider.class);
            Class[] clsArr = new Class[0];
            final ContentProvider contentProvider = (ContentProvider) asSubclass.getDeclaredConstructor(null).newInstance(null);
            if (contentProvider instanceof ContextInitializer) {
                ((ContextInitializer) contentProvider).setContextAvailableCallback(new ContextAvailableCallback() { // from class: com.android.systemui.SystemUIAppComponentFactoryBase$instantiateProviderCompat$1
                    @Override // com.android.systemui.SystemUIAppComponentFactoryBase.ContextAvailableCallback
                    public final SystemUIGoogleInitializer onContextAvailable(Context context) {
                        SystemUIGoogleInitializer access$createSystemUIInitializerInternal = SystemUIAppComponentFactoryBase.access$createSystemUIInitializerInternal(SystemUIAppComponentFactoryBase.this, context);
                        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = access$createSystemUIInitializerInternal.mSysUIComponent;
                        try {
                            daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass().getMethod("inject", contentProvider.getClass()).invoke(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, contentProvider);
                        } catch (IllegalAccessException e) {
                            Log.w("AppComponentFactory", "No injector for class: " + contentProvider.getClass(), e);
                        } catch (NoSuchMethodException e2) {
                            Log.w("AppComponentFactory", "No injector for class: " + contentProvider.getClass(), e2);
                        } catch (InvocationTargetException e3) {
                            Log.w("AppComponentFactory", "No injector for class: " + contentProvider.getClass(), e3);
                        }
                        return access$createSystemUIInitializerInternal;
                    }
                });
            }
            return (ContentProvider) CoreComponentFactory.checkCompatWrapper(contentProvider);
        } catch (NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException("Couldn't call constructor", e);
        }
    }

    @Override // android.app.AppComponentFactory
    public final BroadcastReceiver instantiateReceiver(ClassLoader classLoader, String str, Intent intent) {
        SystemUIGoogleInitializer systemUIGoogleInitializer;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
        if (this.componentHelper == null && (systemUIGoogleInitializer = systemUIInitializer) != null && (daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = systemUIGoogleInitializer.mSysUIComponent) != null) {
            daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.inject(this);
        }
        ContextComponentResolver contextComponentResolver = this.componentHelper;
        if (contextComponentResolver == null) {
            contextComponentResolver = null;
        }
        BroadcastReceiver broadcastReceiver = (BroadcastReceiver) ContextComponentResolver.resolve(str, contextComponentResolver.mBroadcastReceiverCreators);
        if (broadcastReceiver == null) {
            try {
                Class[] clsArr = new Class[0];
                broadcastReceiver = (BroadcastReceiver) Class.forName(str, false, classLoader).asSubclass(BroadcastReceiver.class).getDeclaredConstructor(null).newInstance(null);
            } catch (NoSuchMethodException | InvocationTargetException e) {
                throw new RuntimeException("Couldn't call constructor", e);
            }
        }
        return (BroadcastReceiver) CoreComponentFactory.checkCompatWrapper(broadcastReceiver);
    }

    @Override // android.app.AppComponentFactory
    public final Service instantiateService(ClassLoader classLoader, String str, Intent intent) {
        SystemUIGoogleInitializer systemUIGoogleInitializer;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
        if (this.componentHelper == null && (systemUIGoogleInitializer = systemUIInitializer) != null && (daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = systemUIGoogleInitializer.mSysUIComponent) != null) {
            daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.inject(this);
        }
        ContextComponentResolver contextComponentResolver = this.componentHelper;
        if (contextComponentResolver == null) {
            contextComponentResolver = null;
        }
        Service service = (Service) ContextComponentResolver.resolve(str, contextComponentResolver.mServiceCreators);
        if (service == null) {
            try {
                Class[] clsArr = new Class[0];
                service = (Service) Class.forName(str, false, classLoader).asSubclass(Service.class).getDeclaredConstructor(null).newInstance(null);
            } catch (NoSuchMethodException | InvocationTargetException e) {
                throw new RuntimeException("Couldn't call constructor", e);
            }
        }
        return (Service) CoreComponentFactory.checkCompatWrapper(service);
    }
}

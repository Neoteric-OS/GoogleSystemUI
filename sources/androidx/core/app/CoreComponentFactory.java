package androidx.core.app;

import android.app.Activity;
import android.app.AppComponentFactory;
import android.app.Application;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentProvider;
import android.content.Intent;
import androidx.slice.SliceProvider;
import androidx.slice.compat.SliceProviderWrapperContainer$SliceProviderWrapper;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class CoreComponentFactory extends AppComponentFactory {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface CompatWrapped {
    }

    public static Object checkCompatWrapper(Object obj) {
        if (!(obj instanceof CompatWrapped)) {
            return obj;
        }
        SliceProvider sliceProvider = (SliceProvider) ((CompatWrapped) obj);
        sliceProvider.getClass();
        String[] strArr = sliceProvider.mAutoGrantPermissions;
        SliceProviderWrapperContainer$SliceProviderWrapper sliceProviderWrapperContainer$SliceProviderWrapper = new SliceProviderWrapperContainer$SliceProviderWrapper(strArr);
        if (strArr == null || strArr.length == 0) {
            strArr = null;
        }
        sliceProviderWrapperContainer$SliceProviderWrapper.mAutoGrantPermissions = strArr;
        sliceProviderWrapperContainer$SliceProviderWrapper.mSliceProvider = sliceProvider;
        return sliceProviderWrapperContainer$SliceProviderWrapper;
    }

    @Override // android.app.AppComponentFactory
    public final Activity instantiateActivity(ClassLoader classLoader, String str, Intent intent) {
        return (Activity) checkCompatWrapper(super.instantiateActivity(classLoader, str, intent));
    }

    @Override // android.app.AppComponentFactory
    public final Application instantiateApplication(ClassLoader classLoader, String str) {
        return (Application) checkCompatWrapper(super.instantiateApplication(classLoader, str));
    }

    @Override // android.app.AppComponentFactory
    public final ContentProvider instantiateProvider(ClassLoader classLoader, String str) {
        return (ContentProvider) checkCompatWrapper(super.instantiateProvider(classLoader, str));
    }

    @Override // android.app.AppComponentFactory
    public final BroadcastReceiver instantiateReceiver(ClassLoader classLoader, String str, Intent intent) {
        return (BroadcastReceiver) checkCompatWrapper(super.instantiateReceiver(classLoader, str, intent));
    }

    @Override // android.app.AppComponentFactory
    public final Service instantiateService(ClassLoader classLoader, String str, Intent intent) {
        return (Service) checkCompatWrapper(super.instantiateService(classLoader, str, intent));
    }
}

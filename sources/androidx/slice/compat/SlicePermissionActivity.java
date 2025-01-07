package androidx.slice.compat;

import android.R;
import android.content.ComponentName;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.LocaleList;
import android.os.RemoteException;
import android.text.Html;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.activity.ComponentActivity;
import androidx.activity.ComponentActivity$activityResultRegistry$1;
import androidx.activity.OnBackPressedDispatcher;
import androidx.activity.contextaware.ContextAwareHelper;
import androidx.activity.contextaware.OnContextAvailableListener;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultRegistry$register$2;
import androidx.activity.result.contract.ActivityResultContracts$StartActivityForResult;
import androidx.appcompat.app.AlertController;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatCallback;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.app.AppCompatDelegateImpl;
import androidx.appcompat.app.WindowDecorActionBar;
import androidx.appcompat.view.SupportMenuInflater;
import androidx.appcompat.view.ViewPropertyAnimatorCompatSet;
import androidx.appcompat.widget.AppCompatDrawableManager;
import androidx.appcompat.widget.ResourceManagerInternal;
import androidx.appcompat.widget.VectorEnabledTintResources;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import androidx.collection.LongSparseArray;
import androidx.collection.SparseArrayCompat;
import androidx.core.app.ActivityCompat$RequestPermissionsRequestCodeValidator;
import androidx.core.app.NavUtils;
import androidx.core.app.NotificationCompat$Builder$$ExternalSyntheticOutline0;
import androidx.core.text.BidiFormatter;
import androidx.core.text.TextDirectionHeuristicsCompat;
import androidx.core.util.Consumer;
import androidx.core.view.MenuHostHelper;
import androidx.core.view.VelocityTrackerCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.fragment.app.BackStackRecord;
import androidx.fragment.app.BackStackState;
import androidx.fragment.app.FragmentActivity$HostCallbacks;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentController;
import androidx.fragment.app.FragmentManager$2;
import androidx.fragment.app.FragmentManager$OpGenerator;
import androidx.fragment.app.FragmentManagerImpl;
import androidx.fragment.app.FragmentStore;
import androidx.fragment.app.FragmentTransaction$Op;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.lifecycle.viewmodel.ViewModelProviderImpl;
import androidx.loader.app.LoaderManagerImpl$LoaderViewModel;
import androidx.savedstate.SavedStateRegistry;
import androidx.slice.compat.SlicePermissionActivity;
import androidx.slice.compat.SliceProviderCompat;
import com.android.systemui.plugins.clocks.WeatherData;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.jvm.internal.ClassReference;
import kotlin.jvm.internal.Reflection;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class SlicePermissionActivity extends ComponentActivity implements DialogInterface.OnClickListener, DialogInterface.OnDismissListener, AppCompatCallback, ActivityCompat$RequestPermissionsRequestCodeValidator {
    public String mCallingPkg;
    public boolean mCreated;
    public AppCompatDelegateImpl mDelegate;
    public AlertDialog mDialog;
    public boolean mResumed;
    public Uri mUri;
    public final FragmentController mFragments = new FragmentController(new FragmentActivity$HostCallbacks(this));
    public final LifecycleRegistry mFragmentLifecycleRegistry = new LifecycleRegistry(this);
    public boolean mStopped = true;

    public SlicePermissionActivity() {
        this.savedStateRegistryController.savedStateRegistry.registerSavedStateProvider("android:support:lifecycle", new SavedStateRegistry.SavedStateProvider() { // from class: androidx.fragment.app.FragmentActivity$$ExternalSyntheticLambda0
            @Override // androidx.savedstate.SavedStateRegistry.SavedStateProvider
            public final Bundle saveState() {
                SlicePermissionActivity slicePermissionActivity = SlicePermissionActivity.this;
                Iterator it = slicePermissionActivity.mFragments.mHost.fragmentManager.mFragmentStore.getFragments().iterator();
                while (it.hasNext()) {
                    if (it.next() != null) {
                        throw new ClassCastException();
                    }
                }
                slicePermissionActivity.mFragmentLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP);
                return new Bundle();
            }
        });
        final int i = 0;
        addOnConfigurationChangedListener(new Consumer(this) { // from class: androidx.fragment.app.FragmentActivity$$ExternalSyntheticLambda1
            public final /* synthetic */ SlicePermissionActivity f$0;

            {
                this.f$0 = this;
            }

            @Override // androidx.core.util.Consumer
            public final void accept(Object obj) {
                switch (i) {
                    case 0:
                        this.f$0.mFragments.noteStateNotSaved();
                        break;
                    default:
                        this.f$0.mFragments.noteStateNotSaved();
                        break;
                }
            }
        });
        final int i2 = 1;
        this.onNewIntentListeners.add(new Consumer(this) { // from class: androidx.fragment.app.FragmentActivity$$ExternalSyntheticLambda1
            public final /* synthetic */ SlicePermissionActivity f$0;

            {
                this.f$0 = this;
            }

            @Override // androidx.core.util.Consumer
            public final void accept(Object obj) {
                switch (i2) {
                    case 0:
                        this.f$0.mFragments.noteStateNotSaved();
                        break;
                    default:
                        this.f$0.mFragments.noteStateNotSaved();
                        break;
                }
            }
        });
        OnContextAvailableListener onContextAvailableListener = new OnContextAvailableListener() { // from class: androidx.fragment.app.FragmentActivity$$ExternalSyntheticLambda3
            @Override // androidx.activity.contextaware.OnContextAvailableListener
            public final void onContextAvailable() {
                Bundle bundle;
                Bundle bundle2;
                FragmentActivity$HostCallbacks fragmentActivity$HostCallbacks = SlicePermissionActivity.this.mFragments.mHost;
                final FragmentManagerImpl fragmentManagerImpl = fragmentActivity$HostCallbacks.fragmentManager;
                if (fragmentManagerImpl.mHost != null) {
                    throw new IllegalStateException("Already attached");
                }
                fragmentManagerImpl.mHost = fragmentActivity$HostCallbacks;
                fragmentManagerImpl.mContainer = fragmentActivity$HostCallbacks;
                fragmentManagerImpl.mOnAttachListeners.add(fragmentActivity$HostCallbacks);
                SlicePermissionActivity slicePermissionActivity = fragmentActivity$HostCallbacks.this$0;
                OnBackPressedDispatcher onBackPressedDispatcher = slicePermissionActivity.getOnBackPressedDispatcher();
                fragmentManagerImpl.mOnBackPressedDispatcher = onBackPressedDispatcher;
                onBackPressedDispatcher.addCallback(fragmentActivity$HostCallbacks, fragmentManagerImpl.mOnBackPressedCallback);
                ViewModelProviderImpl viewModelProviderImpl = new ViewModelProviderImpl(slicePermissionActivity.getViewModelStore(), FragmentManagerViewModel.FACTORY, CreationExtras.Empty.INSTANCE);
                ClassReference orCreateKotlinClass = Reflection.getOrCreateKotlinClass(FragmentManagerViewModel.class);
                String qualifiedName = orCreateKotlinClass.getQualifiedName();
                if (qualifiedName == null) {
                    throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
                }
                FragmentManagerViewModel fragmentManagerViewModel = (FragmentManagerViewModel) viewModelProviderImpl.getViewModel$lifecycle_viewmodel_release(orCreateKotlinClass, "androidx.lifecycle.ViewModelProvider.DefaultKey:".concat(qualifiedName));
                fragmentManagerImpl.mNonConfig = fragmentManagerViewModel;
                FragmentStore fragmentStore = fragmentManagerImpl.mFragmentStore;
                fragmentStore.mNonConfig = fragmentManagerViewModel;
                FragmentActivity$HostCallbacks fragmentActivity$HostCallbacks2 = fragmentManagerImpl.mHost;
                if (fragmentActivity$HostCallbacks2 != null) {
                    SavedStateRegistry savedStateRegistry = fragmentActivity$HostCallbacks2.getSavedStateRegistry();
                    savedStateRegistry.registerSavedStateProvider("android:support:fragments", new SavedStateRegistry.SavedStateProvider() { // from class: androidx.fragment.app.FragmentManager$$ExternalSyntheticLambda4
                        @Override // androidx.savedstate.SavedStateRegistry.SavedStateProvider
                        public final Bundle saveState() {
                            ArrayList arrayList;
                            BackStackRecordState[] backStackRecordStateArr;
                            FragmentManagerImpl fragmentManagerImpl2 = FragmentManagerImpl.this;
                            fragmentManagerImpl2.getClass();
                            Bundle bundle3 = new Bundle();
                            Iterator it = ((HashSet) fragmentManagerImpl2.collectAllSpecialEffectsController()).iterator();
                            while (it.hasNext()) {
                                ((DefaultSpecialEffectsController) it.next()).getClass();
                            }
                            fragmentManagerImpl2.endAnimatingAwayFragments();
                            fragmentManagerImpl2.execPendingActions(true);
                            fragmentManagerImpl2.mStateSaved = true;
                            fragmentManagerImpl2.mNonConfig.getClass();
                            FragmentStore fragmentStore2 = fragmentManagerImpl2.mFragmentStore;
                            fragmentStore2.getClass();
                            ArrayList arrayList2 = new ArrayList(fragmentStore2.mActive.size());
                            Iterator it2 = fragmentStore2.mActive.values().iterator();
                            while (it2.hasNext()) {
                                VelocityTrackerCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(it2.next());
                            }
                            HashMap hashMap = fragmentManagerImpl2.mFragmentStore.mSavedState;
                            if (!hashMap.isEmpty()) {
                                FragmentStore fragmentStore3 = fragmentManagerImpl2.mFragmentStore;
                                synchronized (fragmentStore3.mAdded) {
                                    try {
                                        if (fragmentStore3.mAdded.isEmpty()) {
                                            arrayList = null;
                                        } else {
                                            arrayList = new ArrayList(fragmentStore3.mAdded.size());
                                            Iterator it3 = fragmentStore3.mAdded.iterator();
                                            if (it3.hasNext()) {
                                                if (it3.next() == null) {
                                                    throw null;
                                                }
                                                throw new ClassCastException();
                                            }
                                        }
                                    } finally {
                                    }
                                }
                                int size = fragmentManagerImpl2.mBackStack.size();
                                if (size > 0) {
                                    backStackRecordStateArr = new BackStackRecordState[size];
                                    for (int i3 = 0; i3 < size; i3++) {
                                        backStackRecordStateArr[i3] = new BackStackRecordState((BackStackRecord) fragmentManagerImpl2.mBackStack.get(i3));
                                    }
                                } else {
                                    backStackRecordStateArr = null;
                                }
                                FragmentManagerState fragmentManagerState = new FragmentManagerState();
                                fragmentManagerState.mPrimaryNavActiveWho = null;
                                ArrayList arrayList3 = new ArrayList();
                                fragmentManagerState.mBackStackStateKeys = arrayList3;
                                ArrayList arrayList4 = new ArrayList();
                                fragmentManagerState.mBackStackStates = arrayList4;
                                fragmentManagerState.mActive = arrayList2;
                                fragmentManagerState.mAdded = arrayList;
                                fragmentManagerState.mBackStack = backStackRecordStateArr;
                                fragmentManagerState.mBackStackIndex = fragmentManagerImpl2.mBackStackIndex.get();
                                arrayList3.addAll(fragmentManagerImpl2.mBackStackStates.keySet());
                                arrayList4.addAll(fragmentManagerImpl2.mBackStackStates.values());
                                fragmentManagerState.mLaunchedFragments = new ArrayList(fragmentManagerImpl2.mLaunchedFragments);
                                bundle3.putParcelable(WeatherData.STATE_KEY, fragmentManagerState);
                                for (String str : fragmentManagerImpl2.mResults.keySet()) {
                                    bundle3.putBundle(AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("result_", str), (Bundle) fragmentManagerImpl2.mResults.get(str));
                                }
                                for (String str2 : hashMap.keySet()) {
                                    bundle3.putBundle(AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("fragment_", str2), (Bundle) hashMap.get(str2));
                                }
                            }
                            return bundle3;
                        }
                    });
                    Bundle consumeRestoredStateForKey = savedStateRegistry.consumeRestoredStateForKey("android:support:fragments");
                    if (consumeRestoredStateForKey != null) {
                        for (String str : consumeRestoredStateForKey.keySet()) {
                            if (str.startsWith("result_") && (bundle2 = consumeRestoredStateForKey.getBundle(str)) != null) {
                                bundle2.setClassLoader(fragmentManagerImpl.mHost.context.getClassLoader());
                                fragmentManagerImpl.mResults.put(str.substring(7), bundle2);
                            }
                        }
                        HashMap hashMap = new HashMap();
                        for (String str2 : consumeRestoredStateForKey.keySet()) {
                            if (str2.startsWith("fragment_") && (bundle = consumeRestoredStateForKey.getBundle(str2)) != null) {
                                bundle.setClassLoader(fragmentManagerImpl.mHost.context.getClassLoader());
                                hashMap.put(str2.substring(9), bundle);
                            }
                        }
                        fragmentStore.mSavedState.clear();
                        fragmentStore.mSavedState.putAll(hashMap);
                        FragmentManagerState fragmentManagerState = (FragmentManagerState) consumeRestoredStateForKey.getParcelable(WeatherData.STATE_KEY);
                        if (fragmentManagerState != null) {
                            fragmentStore.mActive.clear();
                            Iterator it = fragmentManagerState.mActive.iterator();
                            while (it.hasNext()) {
                                Bundle bundle3 = (Bundle) fragmentStore.mSavedState.remove((String) it.next());
                                if (bundle3 != null) {
                                    if (fragmentManagerImpl.mNonConfig.mRetainedFragments.get(((FragmentState) bundle3.getParcelable(WeatherData.STATE_KEY)).mWho) != null) {
                                        throw new ClassCastException();
                                    }
                                    fragmentManagerImpl.mHost.context.getClassLoader();
                                    fragmentManagerImpl.mHostFragmentFactory.instantiate(((FragmentState) bundle3.getParcelable(WeatherData.STATE_KEY)).mClassName);
                                    throw null;
                                }
                            }
                            FragmentManagerViewModel fragmentManagerViewModel2 = fragmentManagerImpl.mNonConfig;
                            fragmentManagerViewModel2.getClass();
                            Iterator it2 = new ArrayList(fragmentManagerViewModel2.mRetainedFragments.values()).iterator();
                            if (it2.hasNext()) {
                                throw NotificationCompat$Builder$$ExternalSyntheticOutline0.m(it2);
                            }
                            ArrayList arrayList = fragmentManagerState.mAdded;
                            fragmentStore.mAdded.clear();
                            if (arrayList != null) {
                                Iterator it3 = arrayList.iterator();
                                if (it3.hasNext()) {
                                    String str3 = (String) it3.next();
                                    VelocityTrackerCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(fragmentStore.mActive.get(str3));
                                    throw new IllegalStateException(DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("No instantiated fragment for (", str3, ")"));
                                }
                            }
                            if (fragmentManagerState.mBackStack != null) {
                                fragmentManagerImpl.mBackStack = new ArrayList(fragmentManagerState.mBackStack.length);
                                int i3 = 0;
                                while (true) {
                                    BackStackRecordState[] backStackRecordStateArr = fragmentManagerState.mBackStack;
                                    if (i3 >= backStackRecordStateArr.length) {
                                        break;
                                    }
                                    BackStackRecordState backStackRecordState = backStackRecordStateArr[i3];
                                    backStackRecordState.getClass();
                                    BackStackRecord backStackRecord = new BackStackRecord(fragmentManagerImpl);
                                    int i4 = 0;
                                    int i5 = 0;
                                    while (true) {
                                        int[] iArr = backStackRecordState.mOps;
                                        boolean z = true;
                                        if (i4 >= iArr.length) {
                                            break;
                                        }
                                        FragmentTransaction$Op fragmentTransaction$Op = new FragmentTransaction$Op();
                                        fragmentTransaction$Op.mCmd = iArr[i4];
                                        fragmentTransaction$Op.mOldMaxState = Lifecycle.State.values()[backStackRecordState.mOldMaxLifecycleStates[i5]];
                                        fragmentTransaction$Op.mCurrentMaxState = Lifecycle.State.values()[backStackRecordState.mCurrentMaxLifecycleStates[i5]];
                                        int[] iArr2 = backStackRecordState.mOps;
                                        int i6 = i4 + 2;
                                        if (iArr2[i4 + 1] == 0) {
                                            z = false;
                                        }
                                        fragmentTransaction$Op.mFromExpandedOp = z;
                                        int i7 = iArr2[i6];
                                        fragmentTransaction$Op.mEnterAnim = i7;
                                        int i8 = iArr2[i4 + 3];
                                        fragmentTransaction$Op.mExitAnim = i8;
                                        int i9 = i4 + 5;
                                        int i10 = iArr2[i4 + 4];
                                        fragmentTransaction$Op.mPopEnterAnim = i10;
                                        i4 += 6;
                                        int i11 = iArr2[i9];
                                        fragmentTransaction$Op.mPopExitAnim = i11;
                                        backStackRecord.mEnterAnim = i7;
                                        backStackRecord.mExitAnim = i8;
                                        backStackRecord.mPopEnterAnim = i10;
                                        backStackRecord.mPopExitAnim = i11;
                                        backStackRecord.mOps.add(fragmentTransaction$Op);
                                        fragmentTransaction$Op.mEnterAnim = backStackRecord.mEnterAnim;
                                        fragmentTransaction$Op.mExitAnim = backStackRecord.mExitAnim;
                                        fragmentTransaction$Op.mPopEnterAnim = backStackRecord.mPopEnterAnim;
                                        fragmentTransaction$Op.mPopExitAnim = backStackRecord.mPopExitAnim;
                                        i5++;
                                    }
                                    backStackRecord.mTransition = backStackRecordState.mTransition;
                                    backStackRecord.mName = backStackRecordState.mName;
                                    backStackRecord.mAddToBackStack = true;
                                    backStackRecord.mBreadCrumbTitleRes = backStackRecordState.mBreadCrumbTitleRes;
                                    backStackRecord.mBreadCrumbTitleText = backStackRecordState.mBreadCrumbTitleText;
                                    backStackRecord.mBreadCrumbShortTitleRes = backStackRecordState.mBreadCrumbShortTitleRes;
                                    backStackRecord.mBreadCrumbShortTitleText = backStackRecordState.mBreadCrumbShortTitleText;
                                    backStackRecord.mSharedElementSourceNames = backStackRecordState.mSharedElementSourceNames;
                                    backStackRecord.mSharedElementTargetNames = backStackRecordState.mSharedElementTargetNames;
                                    backStackRecord.mReorderingAllowed = backStackRecordState.mReorderingAllowed;
                                    backStackRecord.mIndex = backStackRecordState.mIndex;
                                    for (int i12 = 0; i12 < backStackRecordState.mFragmentWhos.size(); i12++) {
                                        String str4 = (String) backStackRecordState.mFragmentWhos.get(i12);
                                        if (str4 != null) {
                                            FragmentTransaction$Op fragmentTransaction$Op2 = (FragmentTransaction$Op) backStackRecord.mOps.get(i12);
                                            VelocityTrackerCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(fragmentStore.mActive.get(str4));
                                            fragmentTransaction$Op2.getClass();
                                        }
                                    }
                                    backStackRecord.bumpBackStackNesting();
                                    fragmentManagerImpl.mBackStack.add(backStackRecord);
                                    i3++;
                                }
                            } else {
                                fragmentManagerImpl.mBackStack = new ArrayList();
                            }
                            fragmentManagerImpl.mBackStackIndex.set(fragmentManagerState.mBackStackIndex);
                            String str5 = fragmentManagerState.mPrimaryNavActiveWho;
                            if (str5 != null) {
                                VelocityTrackerCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(fragmentStore.mActive.get(str5));
                            }
                            ArrayList arrayList2 = fragmentManagerState.mBackStackStateKeys;
                            if (arrayList2 != null) {
                                for (int i13 = 0; i13 < arrayList2.size(); i13++) {
                                    fragmentManagerImpl.mBackStackStates.put((String) arrayList2.get(i13), (BackStackState) fragmentManagerState.mBackStackStates.get(i13));
                                }
                            }
                            fragmentManagerImpl.mLaunchedFragments = new ArrayDeque(fragmentManagerState.mLaunchedFragments);
                        }
                    }
                }
                FragmentActivity$HostCallbacks fragmentActivity$HostCallbacks3 = fragmentManagerImpl.mHost;
                if (fragmentActivity$HostCallbacks3 != null) {
                    SlicePermissionActivity slicePermissionActivity2 = fragmentActivity$HostCallbacks3.this$0;
                    ActivityResultContracts$StartActivityForResult activityResultContracts$StartActivityForResult = new ActivityResultContracts$StartActivityForResult(0);
                    final int i14 = 0;
                    ActivityResultCallback activityResultCallback = new ActivityResultCallback() { // from class: androidx.fragment.app.FragmentManager$8
                        @Override // androidx.activity.result.ActivityResultCallback
                        public final void onActivityResult(Object obj) {
                            switch (i14) {
                                case 0:
                                    FragmentManagerImpl fragmentManagerImpl2 = fragmentManagerImpl;
                                    FragmentManager$LaunchedFragmentInfo fragmentManager$LaunchedFragmentInfo = (FragmentManager$LaunchedFragmentInfo) fragmentManagerImpl2.mLaunchedFragments.pollLast();
                                    if (fragmentManager$LaunchedFragmentInfo == null) {
                                        Log.w("FragmentManager", "No Activities were started for result for " + this);
                                        break;
                                    } else {
                                        String str6 = fragmentManager$LaunchedFragmentInfo.mWho;
                                        fragmentManagerImpl2.mFragmentStore.findFragmentByWho();
                                        Log.w("FragmentManager", "Activity result delivered for unknown Fragment " + str6);
                                        break;
                                    }
                                case 1:
                                    Map map = (Map) obj;
                                    ArrayList arrayList3 = new ArrayList(map.values());
                                    int[] iArr3 = new int[arrayList3.size()];
                                    for (int i15 = 0; i15 < arrayList3.size(); i15++) {
                                        iArr3[i15] = ((Boolean) arrayList3.get(i15)).booleanValue() ? 0 : -1;
                                    }
                                    FragmentManagerImpl fragmentManagerImpl3 = fragmentManagerImpl;
                                    FragmentManager$LaunchedFragmentInfo fragmentManager$LaunchedFragmentInfo2 = (FragmentManager$LaunchedFragmentInfo) fragmentManagerImpl3.mLaunchedFragments.pollFirst();
                                    if (fragmentManager$LaunchedFragmentInfo2 == null) {
                                        Log.w("FragmentManager", "No permissions were requested for " + this);
                                        break;
                                    } else {
                                        String str7 = fragmentManager$LaunchedFragmentInfo2.mWho;
                                        fragmentManagerImpl3.mFragmentStore.findFragmentByWho();
                                        Log.w("FragmentManager", "Permission request result delivered for unknown Fragment " + str7);
                                        break;
                                    }
                                default:
                                    FragmentManagerImpl fragmentManagerImpl4 = fragmentManagerImpl;
                                    FragmentManager$LaunchedFragmentInfo fragmentManager$LaunchedFragmentInfo3 = (FragmentManager$LaunchedFragmentInfo) fragmentManagerImpl4.mLaunchedFragments.pollFirst();
                                    if (fragmentManager$LaunchedFragmentInfo3 == null) {
                                        Log.w("FragmentManager", "No IntentSenders were started for " + this);
                                        break;
                                    } else {
                                        String str8 = fragmentManager$LaunchedFragmentInfo3.mWho;
                                        fragmentManagerImpl4.mFragmentStore.findFragmentByWho();
                                        Log.w("FragmentManager", "Intent Sender result delivered for unknown Fragment " + str8);
                                        break;
                                    }
                            }
                        }
                    };
                    ComponentActivity$activityResultRegistry$1 componentActivity$activityResultRegistry$1 = slicePermissionActivity2.activityResultRegistry;
                    fragmentManagerImpl.mStartActivityForResult = componentActivity$activityResultRegistry$1.register("FragmentManager:StartActivityForResult", activityResultContracts$StartActivityForResult, activityResultCallback);
                    final int i15 = 2;
                    fragmentManagerImpl.mStartIntentSenderForResult = componentActivity$activityResultRegistry$1.register("FragmentManager:StartIntentSenderForResult", new FragmentManager$FragmentIntentSenderContract(), new ActivityResultCallback() { // from class: androidx.fragment.app.FragmentManager$8
                        @Override // androidx.activity.result.ActivityResultCallback
                        public final void onActivityResult(Object obj) {
                            switch (i15) {
                                case 0:
                                    FragmentManagerImpl fragmentManagerImpl2 = fragmentManagerImpl;
                                    FragmentManager$LaunchedFragmentInfo fragmentManager$LaunchedFragmentInfo = (FragmentManager$LaunchedFragmentInfo) fragmentManagerImpl2.mLaunchedFragments.pollLast();
                                    if (fragmentManager$LaunchedFragmentInfo == null) {
                                        Log.w("FragmentManager", "No Activities were started for result for " + this);
                                        break;
                                    } else {
                                        String str6 = fragmentManager$LaunchedFragmentInfo.mWho;
                                        fragmentManagerImpl2.mFragmentStore.findFragmentByWho();
                                        Log.w("FragmentManager", "Activity result delivered for unknown Fragment " + str6);
                                        break;
                                    }
                                case 1:
                                    Map map = (Map) obj;
                                    ArrayList arrayList3 = new ArrayList(map.values());
                                    int[] iArr3 = new int[arrayList3.size()];
                                    for (int i152 = 0; i152 < arrayList3.size(); i152++) {
                                        iArr3[i152] = ((Boolean) arrayList3.get(i152)).booleanValue() ? 0 : -1;
                                    }
                                    FragmentManagerImpl fragmentManagerImpl3 = fragmentManagerImpl;
                                    FragmentManager$LaunchedFragmentInfo fragmentManager$LaunchedFragmentInfo2 = (FragmentManager$LaunchedFragmentInfo) fragmentManagerImpl3.mLaunchedFragments.pollFirst();
                                    if (fragmentManager$LaunchedFragmentInfo2 == null) {
                                        Log.w("FragmentManager", "No permissions were requested for " + this);
                                        break;
                                    } else {
                                        String str7 = fragmentManager$LaunchedFragmentInfo2.mWho;
                                        fragmentManagerImpl3.mFragmentStore.findFragmentByWho();
                                        Log.w("FragmentManager", "Permission request result delivered for unknown Fragment " + str7);
                                        break;
                                    }
                                default:
                                    FragmentManagerImpl fragmentManagerImpl4 = fragmentManagerImpl;
                                    FragmentManager$LaunchedFragmentInfo fragmentManager$LaunchedFragmentInfo3 = (FragmentManager$LaunchedFragmentInfo) fragmentManagerImpl4.mLaunchedFragments.pollFirst();
                                    if (fragmentManager$LaunchedFragmentInfo3 == null) {
                                        Log.w("FragmentManager", "No IntentSenders were started for " + this);
                                        break;
                                    } else {
                                        String str8 = fragmentManager$LaunchedFragmentInfo3.mWho;
                                        fragmentManagerImpl4.mFragmentStore.findFragmentByWho();
                                        Log.w("FragmentManager", "Intent Sender result delivered for unknown Fragment " + str8);
                                        break;
                                    }
                            }
                        }
                    });
                    final int i16 = 1;
                    fragmentManagerImpl.mRequestPermissions = componentActivity$activityResultRegistry$1.register("FragmentManager:RequestPermissions", new ActivityResultContracts$StartActivityForResult(1), new ActivityResultCallback() { // from class: androidx.fragment.app.FragmentManager$8
                        @Override // androidx.activity.result.ActivityResultCallback
                        public final void onActivityResult(Object obj) {
                            switch (i16) {
                                case 0:
                                    FragmentManagerImpl fragmentManagerImpl2 = fragmentManagerImpl;
                                    FragmentManager$LaunchedFragmentInfo fragmentManager$LaunchedFragmentInfo = (FragmentManager$LaunchedFragmentInfo) fragmentManagerImpl2.mLaunchedFragments.pollLast();
                                    if (fragmentManager$LaunchedFragmentInfo == null) {
                                        Log.w("FragmentManager", "No Activities were started for result for " + this);
                                        break;
                                    } else {
                                        String str6 = fragmentManager$LaunchedFragmentInfo.mWho;
                                        fragmentManagerImpl2.mFragmentStore.findFragmentByWho();
                                        Log.w("FragmentManager", "Activity result delivered for unknown Fragment " + str6);
                                        break;
                                    }
                                case 1:
                                    Map map = (Map) obj;
                                    ArrayList arrayList3 = new ArrayList(map.values());
                                    int[] iArr3 = new int[arrayList3.size()];
                                    for (int i152 = 0; i152 < arrayList3.size(); i152++) {
                                        iArr3[i152] = ((Boolean) arrayList3.get(i152)).booleanValue() ? 0 : -1;
                                    }
                                    FragmentManagerImpl fragmentManagerImpl3 = fragmentManagerImpl;
                                    FragmentManager$LaunchedFragmentInfo fragmentManager$LaunchedFragmentInfo2 = (FragmentManager$LaunchedFragmentInfo) fragmentManagerImpl3.mLaunchedFragments.pollFirst();
                                    if (fragmentManager$LaunchedFragmentInfo2 == null) {
                                        Log.w("FragmentManager", "No permissions were requested for " + this);
                                        break;
                                    } else {
                                        String str7 = fragmentManager$LaunchedFragmentInfo2.mWho;
                                        fragmentManagerImpl3.mFragmentStore.findFragmentByWho();
                                        Log.w("FragmentManager", "Permission request result delivered for unknown Fragment " + str7);
                                        break;
                                    }
                                default:
                                    FragmentManagerImpl fragmentManagerImpl4 = fragmentManagerImpl;
                                    FragmentManager$LaunchedFragmentInfo fragmentManager$LaunchedFragmentInfo3 = (FragmentManager$LaunchedFragmentInfo) fragmentManagerImpl4.mLaunchedFragments.pollFirst();
                                    if (fragmentManager$LaunchedFragmentInfo3 == null) {
                                        Log.w("FragmentManager", "No IntentSenders were started for " + this);
                                        break;
                                    } else {
                                        String str8 = fragmentManager$LaunchedFragmentInfo3.mWho;
                                        fragmentManagerImpl4.mFragmentStore.findFragmentByWho();
                                        Log.w("FragmentManager", "Intent Sender result delivered for unknown Fragment " + str8);
                                        break;
                                    }
                            }
                        }
                    });
                }
                FragmentActivity$HostCallbacks fragmentActivity$HostCallbacks4 = fragmentManagerImpl.mHost;
                if (fragmentActivity$HostCallbacks4 != null) {
                    fragmentActivity$HostCallbacks4.this$0.addOnConfigurationChangedListener(fragmentManagerImpl.mOnConfigurationChangedListener);
                }
                FragmentActivity$HostCallbacks fragmentActivity$HostCallbacks5 = fragmentManagerImpl.mHost;
                if (fragmentActivity$HostCallbacks5 != null) {
                    fragmentActivity$HostCallbacks5.this$0.onTrimMemoryListeners.add(fragmentManagerImpl.mOnTrimMemoryListener);
                }
                FragmentActivity$HostCallbacks fragmentActivity$HostCallbacks6 = fragmentManagerImpl.mHost;
                if (fragmentActivity$HostCallbacks6 != null) {
                    fragmentActivity$HostCallbacks6.this$0.onMultiWindowModeChangedListeners.add(fragmentManagerImpl.mOnMultiWindowModeChangedListener);
                }
                FragmentActivity$HostCallbacks fragmentActivity$HostCallbacks7 = fragmentManagerImpl.mHost;
                if (fragmentActivity$HostCallbacks7 != null) {
                    fragmentActivity$HostCallbacks7.this$0.onPictureInPictureModeChangedListeners.add(fragmentManagerImpl.mOnPictureInPictureModeChangedListener);
                }
                FragmentActivity$HostCallbacks fragmentActivity$HostCallbacks8 = fragmentManagerImpl.mHost;
                if (fragmentActivity$HostCallbacks8 != null) {
                    MenuHostHelper menuHostHelper = fragmentActivity$HostCallbacks8.this$0.menuHostHelper;
                    menuHostHelper.mMenuProviders.add(fragmentManagerImpl.mMenuProvider);
                    menuHostHelper.mOnInvalidateMenuCallback.run();
                }
            }
        };
        ContextAwareHelper contextAwareHelper = this.contextAwareHelper;
        if (contextAwareHelper.context != null) {
            onContextAvailableListener.onContextAvailable();
        }
        contextAwareHelper.listeners.add(onContextAvailableListener);
        this.savedStateRegistryController.savedStateRegistry.registerSavedStateProvider("androidx:appcompat", new SavedStateRegistry.SavedStateProvider() { // from class: androidx.appcompat.app.AppCompatActivity$1
            @Override // androidx.savedstate.SavedStateRegistry.SavedStateProvider
            public final Bundle saveState() {
                Bundle bundle = new Bundle();
                SlicePermissionActivity.this.getDelegate().getClass();
                return bundle;
            }
        });
        OnContextAvailableListener onContextAvailableListener2 = new OnContextAvailableListener() { // from class: androidx.appcompat.app.AppCompatActivity$2
            @Override // androidx.activity.contextaware.OnContextAvailableListener
            public final void onContextAvailable() {
                SlicePermissionActivity slicePermissionActivity = SlicePermissionActivity.this;
                AppCompatDelegate delegate = slicePermissionActivity.getDelegate();
                delegate.installViewFactory();
                slicePermissionActivity.savedStateRegistryController.savedStateRegistry.consumeRestoredStateForKey("androidx:appcompat");
                delegate.onCreate();
            }
        };
        ContextAwareHelper contextAwareHelper2 = this.contextAwareHelper;
        if (contextAwareHelper2.context != null) {
            onContextAvailableListener2.onContextAvailable();
        }
        contextAwareHelper2.listeners.add(onContextAvailableListener2);
    }

    public static CharSequence loadSafeLabel(PackageManager packageManager, ApplicationInfo applicationInfo) {
        String obj = Html.fromHtml(applicationInfo.loadLabel(packageManager).toString()).toString();
        int length = obj.length();
        int i = 0;
        while (i < length) {
            int codePointAt = obj.codePointAt(i);
            int type = Character.getType(codePointAt);
            if (type == 13 || type == 15 || type == 14) {
                obj = obj.substring(0, i);
                break;
            }
            if (type == 12) {
                obj = obj.substring(0, i) + " " + obj.substring(Character.charCount(codePointAt) + i);
            }
            i += Character.charCount(codePointAt);
        }
        String trim = obj.trim();
        if (trim.isEmpty()) {
            return applicationInfo.packageName;
        }
        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize(42.0f);
        return TextUtils.ellipsize(trim, textPaint, 500.0f, TextUtils.TruncateAt.END);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        initViewTreeOwners$1();
        AppCompatDelegateImpl appCompatDelegateImpl = (AppCompatDelegateImpl) getDelegate();
        appCompatDelegateImpl.ensureSubDecor();
        ((ViewGroup) appCompatDelegateImpl.mSubDecor.findViewById(R.id.content)).addView(view, layoutParams);
        appCompatDelegateImpl.mAppCompatWindowCallback.bypassOnContentChanged(appCompatDelegateImpl.mWindow.getCallback());
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    public final void attachBaseContext(final Context context) {
        AppCompatDelegateImpl appCompatDelegateImpl = (AppCompatDelegateImpl) getDelegate();
        appCompatDelegateImpl.mBaseContextAttached = true;
        int i = appCompatDelegateImpl.mLocalNightMode;
        if (i == -100) {
            i = AppCompatDelegate.sDefaultNightMode;
        }
        int mapNightMode = appCompatDelegateImpl.mapNightMode(i, context);
        if (AppCompatDelegate.isAutoStorageOptedIn(context) && AppCompatDelegate.isAutoStorageOptedIn(context) && !AppCompatDelegate.sIsFrameworkSyncChecked) {
            AppCompatDelegate.sSerialExecutorForLocalesStorage.execute(new Runnable() { // from class: androidx.appcompat.app.AppCompatDelegate$$ExternalSyntheticLambda0
                /* JADX WARN: Code restructure failed: missing block: B:32:0x00a2, code lost:
                
                    if (r6 != null) goto L69;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:42:0x00a4, code lost:
                
                    r6.close();
                 */
                /* JADX WARN: Code restructure failed: missing block: B:48:0x008d, code lost:
                
                    if (r9 != 4) goto L84;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:51:0x009a, code lost:
                
                    if (r7.getName().equals("locales") == false) goto L89;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:53:0x009c, code lost:
                
                    r3 = r7.getAttributeValue(null, "application_locales");
                 */
                /* JADX WARN: Code restructure failed: missing block: B:72:0x00b1, code lost:
                
                    if (r6 == null) goto L47;
                 */
                /* JADX WARN: Removed duplicated region for block: B:39:0x00cf  */
                @Override // java.lang.Runnable
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void run() {
                    /*
                        Method dump skipped, instructions count: 229
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegate$$ExternalSyntheticLambda0.run():void");
                }
            });
        }
        Configuration configuration = null;
        if (context instanceof ContextThemeWrapper) {
            try {
                ((ContextThemeWrapper) context).applyOverrideConfiguration(AppCompatDelegateImpl.createOverrideAppConfiguration(context, mapNightMode, null, false));
            } catch (IllegalStateException unused) {
            }
            super.attachBaseContext(context);
        }
        if (context instanceof androidx.appcompat.view.ContextThemeWrapper) {
            try {
                ((androidx.appcompat.view.ContextThemeWrapper) context).applyOverrideConfiguration(AppCompatDelegateImpl.createOverrideAppConfiguration(context, mapNightMode, null, false));
            } catch (IllegalStateException unused2) {
            }
            super.attachBaseContext(context);
        }
        if (AppCompatDelegateImpl.sCanReturnDifferentContext) {
            Configuration configuration2 = new Configuration();
            configuration2.uiMode = -1;
            configuration2.fontScale = 0.0f;
            Configuration configuration3 = context.createConfigurationContext(configuration2).getResources().getConfiguration();
            Configuration configuration4 = context.getResources().getConfiguration();
            configuration3.uiMode = configuration4.uiMode;
            if (!configuration3.equals(configuration4)) {
                configuration = new Configuration();
                configuration.fontScale = 0.0f;
                if (configuration3.diff(configuration4) != 0) {
                    float f = configuration3.fontScale;
                    float f2 = configuration4.fontScale;
                    if (f != f2) {
                        configuration.fontScale = f2;
                    }
                    int i2 = configuration3.mcc;
                    int i3 = configuration4.mcc;
                    if (i2 != i3) {
                        configuration.mcc = i3;
                    }
                    int i4 = configuration3.mnc;
                    int i5 = configuration4.mnc;
                    if (i4 != i5) {
                        configuration.mnc = i5;
                    }
                    LocaleList locales = configuration3.getLocales();
                    LocaleList locales2 = configuration4.getLocales();
                    if (!locales.equals(locales2)) {
                        configuration.setLocales(locales2);
                        configuration.locale = configuration4.locale;
                    }
                    int i6 = configuration3.touchscreen;
                    int i7 = configuration4.touchscreen;
                    if (i6 != i7) {
                        configuration.touchscreen = i7;
                    }
                    int i8 = configuration3.keyboard;
                    int i9 = configuration4.keyboard;
                    if (i8 != i9) {
                        configuration.keyboard = i9;
                    }
                    int i10 = configuration3.keyboardHidden;
                    int i11 = configuration4.keyboardHidden;
                    if (i10 != i11) {
                        configuration.keyboardHidden = i11;
                    }
                    int i12 = configuration3.navigation;
                    int i13 = configuration4.navigation;
                    if (i12 != i13) {
                        configuration.navigation = i13;
                    }
                    int i14 = configuration3.navigationHidden;
                    int i15 = configuration4.navigationHidden;
                    if (i14 != i15) {
                        configuration.navigationHidden = i15;
                    }
                    int i16 = configuration3.orientation;
                    int i17 = configuration4.orientation;
                    if (i16 != i17) {
                        configuration.orientation = i17;
                    }
                    int i18 = configuration3.screenLayout & 15;
                    int i19 = configuration4.screenLayout & 15;
                    if (i18 != i19) {
                        configuration.screenLayout |= i19;
                    }
                    int i20 = configuration3.screenLayout & 192;
                    int i21 = configuration4.screenLayout & 192;
                    if (i20 != i21) {
                        configuration.screenLayout |= i21;
                    }
                    int i22 = configuration3.screenLayout & 48;
                    int i23 = configuration4.screenLayout & 48;
                    if (i22 != i23) {
                        configuration.screenLayout |= i23;
                    }
                    int i24 = configuration3.screenLayout & 768;
                    int i25 = configuration4.screenLayout & 768;
                    if (i24 != i25) {
                        configuration.screenLayout |= i25;
                    }
                    int i26 = configuration3.colorMode & 3;
                    int i27 = configuration4.colorMode & 3;
                    if (i26 != i27) {
                        configuration.colorMode |= i27;
                    }
                    int i28 = configuration3.colorMode & 12;
                    int i29 = configuration4.colorMode & 12;
                    if (i28 != i29) {
                        configuration.colorMode |= i29;
                    }
                    int i30 = configuration3.uiMode & 15;
                    int i31 = configuration4.uiMode & 15;
                    if (i30 != i31) {
                        configuration.uiMode |= i31;
                    }
                    int i32 = configuration3.uiMode & 48;
                    int i33 = configuration4.uiMode & 48;
                    if (i32 != i33) {
                        configuration.uiMode |= i33;
                    }
                    int i34 = configuration3.screenWidthDp;
                    int i35 = configuration4.screenWidthDp;
                    if (i34 != i35) {
                        configuration.screenWidthDp = i35;
                    }
                    int i36 = configuration3.screenHeightDp;
                    int i37 = configuration4.screenHeightDp;
                    if (i36 != i37) {
                        configuration.screenHeightDp = i37;
                    }
                    int i38 = configuration3.smallestScreenWidthDp;
                    int i39 = configuration4.smallestScreenWidthDp;
                    if (i38 != i39) {
                        configuration.smallestScreenWidthDp = i39;
                    }
                    int i40 = configuration3.densityDpi;
                    int i41 = configuration4.densityDpi;
                    if (i40 != i41) {
                        configuration.densityDpi = i41;
                    }
                }
            }
            Configuration createOverrideAppConfiguration = AppCompatDelegateImpl.createOverrideAppConfiguration(context, mapNightMode, configuration, true);
            androidx.appcompat.view.ContextThemeWrapper contextThemeWrapper = new androidx.appcompat.view.ContextThemeWrapper(com.android.wm.shell.R.style.Theme_AppCompat_Empty, context);
            contextThemeWrapper.applyOverrideConfiguration(createOverrideAppConfiguration);
            try {
                if (context.getTheme() != null) {
                    contextThemeWrapper.getTheme().rebase();
                }
            } catch (NullPointerException unused3) {
            }
            context = contextThemeWrapper;
        }
        super.attachBaseContext(context);
    }

    @Override // android.app.Activity
    public final void closeOptionsMenu() {
        ((AppCompatDelegateImpl) getDelegate()).initWindowDecorActionBar();
        if (getWindow().hasFeature(0)) {
            super.closeOptionsMenu();
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        keyEvent.getKeyCode();
        ((AppCompatDelegateImpl) getDelegate()).initWindowDecorActionBar();
        return super.dispatchKeyEvent(keyEvent);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    @Override // android.app.Activity
    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        String str2;
        super.dump(str, fileDescriptor, printWriter, strArr);
        if (strArr != null && strArr.length != 0) {
            String str3 = strArr[0];
            switch (str3.hashCode()) {
                case -645125871:
                    if (str3.equals("--translation")) {
                        return;
                    }
                    break;
                case 100470631:
                    if (str3.equals("--dump-dumpable")) {
                        return;
                    }
                    break;
                case 472614934:
                    if (str3.equals("--list-dumpables")) {
                        return;
                    }
                    break;
                case 1159329357:
                    if (str3.equals("--contentcapture")) {
                        return;
                    }
                    break;
                case 1455016274:
                    if (str3.equals("--autofill")) {
                        return;
                    }
                    break;
            }
        }
        printWriter.print(str);
        printWriter.print("Local FragmentActivity ");
        printWriter.print(Integer.toHexString(System.identityHashCode(this)));
        printWriter.println(" State:");
        String str4 = str + "  ";
        printWriter.print(str4);
        printWriter.print("mCreated=");
        printWriter.print(this.mCreated);
        printWriter.print(" mResumed=");
        printWriter.print(this.mResumed);
        printWriter.print(" mStopped=");
        printWriter.print(this.mStopped);
        if (getApplication() != null) {
            ViewModelProviderImpl viewModelProviderImpl = new ViewModelProviderImpl(getViewModelStore(), LoaderManagerImpl$LoaderViewModel.FACTORY, CreationExtras.Empty.INSTANCE);
            ClassReference orCreateKotlinClass = Reflection.getOrCreateKotlinClass(LoaderManagerImpl$LoaderViewModel.class);
            String qualifiedName = orCreateKotlinClass.getQualifiedName();
            if (qualifiedName == null) {
                throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
            }
            SparseArrayCompat sparseArrayCompat = ((LoaderManagerImpl$LoaderViewModel) viewModelProviderImpl.getViewModel$lifecycle_viewmodel_release(orCreateKotlinClass, "androidx.lifecycle.ViewModelProvider.DefaultKey:".concat(qualifiedName))).mLoaders;
            if (sparseArrayCompat.size > 0) {
                printWriter.print(str4);
                printWriter.println("Loaders:");
                if (sparseArrayCompat.size > 0) {
                    if (sparseArrayCompat.values[0] != null) {
                        throw new ClassCastException();
                    }
                    printWriter.print(str4);
                    printWriter.print("  #");
                    printWriter.print(sparseArrayCompat.keys[0]);
                    printWriter.print(": ");
                    throw null;
                }
            }
        }
        FragmentManagerImpl fragmentManagerImpl = this.mFragments.mHost.fragmentManager;
        fragmentManagerImpl.getClass();
        String m = DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m(str, "    ");
        FragmentStore fragmentStore = fragmentManagerImpl.mFragmentStore;
        fragmentStore.getClass();
        if (!fragmentStore.mActive.isEmpty()) {
            printWriter.print(str);
            printWriter.println("Active Fragments:");
            Iterator it = fragmentStore.mActive.values().iterator();
            while (it.hasNext()) {
                VelocityTrackerCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(it.next());
                printWriter.print(str);
                printWriter.println("null");
            }
        }
        int size = fragmentStore.mAdded.size();
        if (size > 0) {
            printWriter.print(str);
            printWriter.println("Added Fragments:");
            if (size > 0) {
                if (fragmentStore.mAdded.get(0) != null) {
                    throw new ClassCastException();
                }
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(0);
                printWriter.print(": ");
                throw null;
            }
        }
        int size2 = fragmentManagerImpl.mBackStack.size();
        if (size2 > 0) {
            printWriter.print(str);
            printWriter.println("Back Stack:");
            for (int i = 0; i < size2; i++) {
                BackStackRecord backStackRecord = (BackStackRecord) fragmentManagerImpl.mBackStack.get(i);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i);
                printWriter.print(": ");
                printWriter.println(backStackRecord.toString());
                printWriter.print(m);
                printWriter.print("mName=");
                printWriter.print(backStackRecord.mName);
                printWriter.print(" mIndex=");
                printWriter.print(backStackRecord.mIndex);
                printWriter.print(" mCommitted=");
                printWriter.println(backStackRecord.mCommitted);
                if (backStackRecord.mTransition != 0) {
                    printWriter.print(m);
                    printWriter.print("mTransition=#");
                    printWriter.print(Integer.toHexString(backStackRecord.mTransition));
                }
                if (backStackRecord.mEnterAnim != 0 || backStackRecord.mExitAnim != 0) {
                    printWriter.print(m);
                    printWriter.print("mEnterAnim=#");
                    printWriter.print(Integer.toHexString(backStackRecord.mEnterAnim));
                    printWriter.print(" mExitAnim=#");
                    printWriter.println(Integer.toHexString(backStackRecord.mExitAnim));
                }
                if (backStackRecord.mPopEnterAnim != 0 || backStackRecord.mPopExitAnim != 0) {
                    printWriter.print(m);
                    printWriter.print("mPopEnterAnim=#");
                    printWriter.print(Integer.toHexString(backStackRecord.mPopEnterAnim));
                    printWriter.print(" mPopExitAnim=#");
                    printWriter.println(Integer.toHexString(backStackRecord.mPopExitAnim));
                }
                if (backStackRecord.mBreadCrumbTitleRes != 0 || backStackRecord.mBreadCrumbTitleText != null) {
                    printWriter.print(m);
                    printWriter.print("mBreadCrumbTitleRes=#");
                    printWriter.print(Integer.toHexString(backStackRecord.mBreadCrumbTitleRes));
                    printWriter.print(" mBreadCrumbTitleText=");
                    printWriter.println(backStackRecord.mBreadCrumbTitleText);
                }
                if (backStackRecord.mBreadCrumbShortTitleRes != 0 || backStackRecord.mBreadCrumbShortTitleText != null) {
                    printWriter.print(m);
                    printWriter.print("mBreadCrumbShortTitleRes=#");
                    printWriter.print(Integer.toHexString(backStackRecord.mBreadCrumbShortTitleRes));
                    printWriter.print(" mBreadCrumbShortTitleText=");
                    printWriter.println(backStackRecord.mBreadCrumbShortTitleText);
                }
                if (!backStackRecord.mOps.isEmpty()) {
                    printWriter.print(m);
                    printWriter.println("Operations:");
                    int size3 = backStackRecord.mOps.size();
                    for (int i2 = 0; i2 < size3; i2++) {
                        FragmentTransaction$Op fragmentTransaction$Op = (FragmentTransaction$Op) backStackRecord.mOps.get(i2);
                        switch (fragmentTransaction$Op.mCmd) {
                            case 0:
                                str2 = "NULL";
                                break;
                            case 1:
                                str2 = "ADD";
                                break;
                            case 2:
                                str2 = "REPLACE";
                                break;
                            case 3:
                                str2 = "REMOVE";
                                break;
                            case 4:
                                str2 = "HIDE";
                                break;
                            case 5:
                                str2 = "SHOW";
                                break;
                            case 6:
                                str2 = "DETACH";
                                break;
                            case 7:
                                str2 = "ATTACH";
                                break;
                            case 8:
                                str2 = "SET_PRIMARY_NAV";
                                break;
                            case 9:
                                str2 = "UNSET_PRIMARY_NAV";
                                break;
                            case 10:
                                str2 = "OP_SET_MAX_LIFECYCLE";
                                break;
                            default:
                                str2 = "cmd=" + fragmentTransaction$Op.mCmd;
                                break;
                        }
                        printWriter.print(m);
                        printWriter.print("  Op #");
                        printWriter.print(i2);
                        printWriter.print(": ");
                        printWriter.print(str2);
                        printWriter.print(" ");
                        printWriter.println((Object) null);
                        if (fragmentTransaction$Op.mEnterAnim != 0 || fragmentTransaction$Op.mExitAnim != 0) {
                            printWriter.print(m);
                            printWriter.print("enterAnim=#");
                            printWriter.print(Integer.toHexString(fragmentTransaction$Op.mEnterAnim));
                            printWriter.print(" exitAnim=#");
                            printWriter.println(Integer.toHexString(fragmentTransaction$Op.mExitAnim));
                        }
                        if (fragmentTransaction$Op.mPopEnterAnim != 0 || fragmentTransaction$Op.mPopExitAnim != 0) {
                            printWriter.print(m);
                            printWriter.print("popEnterAnim=#");
                            printWriter.print(Integer.toHexString(fragmentTransaction$Op.mPopEnterAnim));
                            printWriter.print(" popExitAnim=#");
                            printWriter.println(Integer.toHexString(fragmentTransaction$Op.mPopExitAnim));
                        }
                    }
                }
            }
        }
        printWriter.print(str);
        printWriter.println("Back Stack Index: " + fragmentManagerImpl.mBackStackIndex.get());
        synchronized (fragmentManagerImpl.mPendingActions) {
            try {
                int size4 = fragmentManagerImpl.mPendingActions.size();
                if (size4 > 0) {
                    printWriter.print(str);
                    printWriter.println("Pending Actions:");
                    for (int i3 = 0; i3 < size4; i3++) {
                        FragmentManager$OpGenerator fragmentManager$OpGenerator = (FragmentManager$OpGenerator) fragmentManagerImpl.mPendingActions.get(i3);
                        printWriter.print(str);
                        printWriter.print("  #");
                        printWriter.print(i3);
                        printWriter.print(": ");
                        printWriter.println(fragmentManager$OpGenerator);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        printWriter.print(str);
        printWriter.println("FragmentManager misc state:");
        printWriter.print(str);
        printWriter.print("  mHost=");
        printWriter.println(fragmentManagerImpl.mHost);
        printWriter.print(str);
        printWriter.print("  mContainer=");
        printWriter.println(fragmentManagerImpl.mContainer);
        printWriter.print(str);
        printWriter.print("  mCurState=");
        printWriter.print(fragmentManagerImpl.mCurState);
        printWriter.print(" mStateSaved=");
        printWriter.print(fragmentManagerImpl.mStateSaved);
        printWriter.print(" mStopped=");
        printWriter.print(fragmentManagerImpl.mStopped);
        printWriter.print(" mDestroyed=");
        printWriter.println(fragmentManagerImpl.mDestroyed);
    }

    @Override // android.app.Activity
    public final View findViewById(int i) {
        AppCompatDelegateImpl appCompatDelegateImpl = (AppCompatDelegateImpl) getDelegate();
        appCompatDelegateImpl.ensureSubDecor();
        return appCompatDelegateImpl.mWindow.findViewById(i);
    }

    public final AppCompatDelegate getDelegate() {
        if (this.mDelegate == null) {
            AppCompatDelegate.SerialExecutor serialExecutor = AppCompatDelegate.sSerialExecutorForLocalesStorage;
            this.mDelegate = new AppCompatDelegateImpl(this, null, this, this);
        }
        return this.mDelegate;
    }

    @Override // android.app.Activity
    public final MenuInflater getMenuInflater() {
        AppCompatDelegateImpl appCompatDelegateImpl = (AppCompatDelegateImpl) getDelegate();
        if (appCompatDelegateImpl.mMenuInflater == null) {
            appCompatDelegateImpl.initWindowDecorActionBar();
            WindowDecorActionBar windowDecorActionBar = appCompatDelegateImpl.mActionBar;
            appCompatDelegateImpl.mMenuInflater = new SupportMenuInflater(windowDecorActionBar != null ? windowDecorActionBar.getThemedContext() : appCompatDelegateImpl.mContext);
        }
        return appCompatDelegateImpl.mMenuInflater;
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public final Resources getResources() {
        int i = VectorEnabledTintResources.$r8$clinit;
        return super.getResources();
    }

    public final void initViewTreeOwners$1() {
        getWindow().getDecorView().setTag(com.android.wm.shell.R.id.view_tree_lifecycle_owner, this);
        getWindow().getDecorView().setTag(com.android.wm.shell.R.id.view_tree_view_model_store_owner, this);
        getWindow().getDecorView().setTag(com.android.wm.shell.R.id.view_tree_saved_state_registry_owner, this);
        getWindow().getDecorView().setTag(com.android.wm.shell.R.id.view_tree_on_back_pressed_dispatcher_owner, this);
    }

    @Override // android.app.Activity
    public final void invalidateOptionsMenu() {
        AppCompatDelegateImpl appCompatDelegateImpl = (AppCompatDelegateImpl) getDelegate();
        if (appCompatDelegateImpl.mActionBar != null) {
            appCompatDelegateImpl.initWindowDecorActionBar();
            appCompatDelegateImpl.mActionBar.getClass();
            appCompatDelegateImpl.invalidatePanelMenu(0);
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void onActivityResult(int i, int i2, Intent intent) {
        this.mFragments.noteStateNotSaved();
        super.onActivityResult(i, i2, intent);
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        if (i == -1) {
            String packageName = getPackageName();
            String str = this.mCallingPkg;
            Uri build = this.mUri.buildUpon().path("").build();
            try {
                ContentProviderClient acquireUnstableContentProviderClient = getContentResolver().acquireUnstableContentProviderClient(build);
                if (acquireUnstableContentProviderClient == null) {
                    throw new IllegalArgumentException("No provider found for " + build);
                }
                SliceProviderCompat.ProviderHolder providerHolder = new SliceProviderCompat.ProviderHolder(acquireUnstableContentProviderClient);
                try {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("slice_uri", build);
                    bundle.putString("provider_pkg", packageName);
                    bundle.putString("pkg", str);
                    providerHolder.mProvider.call("grant_perms", "supports_versioned_parcelable", bundle);
                    providerHolder.close();
                } finally {
                }
            } catch (RemoteException e) {
                Log.e("SliceProviderCompat", "Unable to get slice descendants", e);
            }
        }
        finish();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        AppCompatDelegateImpl appCompatDelegateImpl = (AppCompatDelegateImpl) getDelegate();
        if (appCompatDelegateImpl.mHasActionBar && appCompatDelegateImpl.mSubDecorInstalled) {
            appCompatDelegateImpl.initWindowDecorActionBar();
            WindowDecorActionBar windowDecorActionBar = appCompatDelegateImpl.mActionBar;
            if (windowDecorActionBar != null) {
                windowDecorActionBar.setHasEmbeddedTabs(windowDecorActionBar.mContext.getResources().getBoolean(com.android.wm.shell.R.bool.abc_action_bar_embed_tabs));
            }
        }
        AppCompatDrawableManager appCompatDrawableManager = AppCompatDrawableManager.get();
        Context context = appCompatDelegateImpl.mContext;
        synchronized (appCompatDrawableManager) {
            ResourceManagerInternal resourceManagerInternal = appCompatDrawableManager.mResourceManager;
            synchronized (resourceManagerInternal) {
                LongSparseArray longSparseArray = (LongSparseArray) resourceManagerInternal.mDrawableCaches.get(context);
                if (longSparseArray != null) {
                    longSparseArray.clear();
                }
            }
        }
        appCompatDelegateImpl.mEffectiveConfiguration = new Configuration(appCompatDelegateImpl.mContext.getResources().getConfiguration());
        appCompatDelegateImpl.applyApplicationSpecificConfig(false);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        onCreate$androidx$fragment$app$FragmentActivity(bundle);
        this.mUri = (Uri) getIntent().getParcelableExtra("slice_uri");
        this.mCallingPkg = getIntent().getStringExtra("pkg");
        String stringExtra = getIntent().getStringExtra("provider_pkg");
        try {
            PackageManager packageManager = getPackageManager();
            TextDirectionHeuristicsCompat.TextDirectionHeuristicInternal textDirectionHeuristicInternal = BidiFormatter.DEFAULT_TEXT_DIRECTION_HEURISTIC;
            String unicodeWrap = (TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == 1 ? BidiFormatter.DEFAULT_RTL_INSTANCE : BidiFormatter.DEFAULT_LTR_INSTANCE).unicodeWrap(loadSafeLabel(packageManager, packageManager.getApplicationInfo(this.mCallingPkg, 0)).toString());
            String unicodeWrap2 = (TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == 1 ? BidiFormatter.DEFAULT_RTL_INSTANCE : BidiFormatter.DEFAULT_LTR_INSTANCE).unicodeWrap(loadSafeLabel(packageManager, packageManager.getApplicationInfo(stringExtra, 0)).toString());
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            AlertController.AlertParams alertParams = builder.P;
            alertParams.mTitle = getString(com.android.wm.shell.R.string.abc_slice_permission_title, new Object[]{unicodeWrap, unicodeWrap2});
            alertParams.mViewLayoutResId = com.android.wm.shell.R.layout.abc_slice_permission_request;
            alertParams.mNegativeButtonText = alertParams.mContext.getText(com.android.wm.shell.R.string.abc_slice_permission_deny);
            alertParams.mNegativeButtonListener = this;
            alertParams.mPositiveButtonText = alertParams.mContext.getText(com.android.wm.shell.R.string.abc_slice_permission_allow);
            alertParams.mPositiveButtonListener = this;
            alertParams.mOnDismissListener = this;
            AlertDialog create = builder.create();
            create.show();
            this.mDialog = create;
            ((TextView) create.getWindow().getDecorView().findViewById(com.android.wm.shell.R.id.text1)).setText(getString(com.android.wm.shell.R.string.abc_slice_permission_text_1, new Object[]{unicodeWrap2}));
            ((TextView) this.mDialog.getWindow().getDecorView().findViewById(com.android.wm.shell.R.id.text2)).setText(getString(com.android.wm.shell.R.string.abc_slice_permission_text_2, new Object[]{unicodeWrap2}));
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("SlicePermissionActivity", "Couldn't find package", e);
            finish();
        }
    }

    public final void onCreate$androidx$fragment$app$FragmentActivity(Bundle bundle) {
        super.onCreate(bundle);
        this.mFragmentLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
        FragmentManagerImpl fragmentManagerImpl = this.mFragments.mHost.fragmentManager;
        fragmentManagerImpl.mStateSaved = false;
        fragmentManagerImpl.mStopped = false;
        fragmentManagerImpl.mNonConfig.getClass();
        fragmentManagerImpl.dispatchStateChange(1);
    }

    @Override // android.app.Activity, android.view.LayoutInflater.Factory2
    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        FragmentContainerView fragmentContainerView = (FragmentContainerView) this.mFragments.mHost.fragmentManager.mLayoutInflaterFactory.onCreateView(view, str, context, attributeSet);
        return fragmentContainerView == null ? super.onCreateView(view, str, context, attributeSet) : fragmentContainerView;
    }

    @Override // android.app.Activity
    public final void onDestroy() {
        onDestroy$androidx$appcompat$app$AppCompatActivity();
        AlertDialog alertDialog = this.mDialog;
        if (alertDialog == null || !alertDialog.isShowing()) {
            return;
        }
        this.mDialog.cancel();
    }

    public final void onDestroy$androidx$appcompat$app$AppCompatActivity() {
        onDestroy$androidx$fragment$app$FragmentActivity();
        getDelegate().onDestroy();
    }

    public final void onDestroy$androidx$fragment$app$FragmentActivity() {
        super.onDestroy();
        FragmentManagerImpl fragmentManagerImpl = this.mFragments.mHost.fragmentManager;
        boolean z = true;
        fragmentManagerImpl.mDestroyed = true;
        fragmentManagerImpl.execPendingActions(true);
        fragmentManagerImpl.endAnimatingAwayFragments();
        FragmentActivity$HostCallbacks fragmentActivity$HostCallbacks = fragmentManagerImpl.mHost;
        FragmentStore fragmentStore = fragmentManagerImpl.mFragmentStore;
        if (fragmentActivity$HostCallbacks != null) {
            z = fragmentStore.mNonConfig.mHasBeenCleared;
        } else {
            SlicePermissionActivity slicePermissionActivity = fragmentActivity$HostCallbacks.context;
            if (slicePermissionActivity != null) {
                z = true ^ slicePermissionActivity.isChangingConfigurations();
            }
        }
        if (z) {
            Iterator it = fragmentManagerImpl.mBackStackStates.values().iterator();
            while (it.hasNext()) {
                Iterator it2 = ((BackStackState) it.next()).mFragments.iterator();
                while (it2.hasNext()) {
                    fragmentStore.mNonConfig.clearNonConfigState((String) it2.next(), false);
                }
            }
        }
        fragmentManagerImpl.dispatchStateChange(-1);
        FragmentActivity$HostCallbacks fragmentActivity$HostCallbacks2 = fragmentManagerImpl.mHost;
        if (fragmentActivity$HostCallbacks2 != null) {
            fragmentActivity$HostCallbacks2.this$0.onTrimMemoryListeners.remove(fragmentManagerImpl.mOnTrimMemoryListener);
        }
        FragmentActivity$HostCallbacks fragmentActivity$HostCallbacks3 = fragmentManagerImpl.mHost;
        if (fragmentActivity$HostCallbacks3 != null) {
            fragmentActivity$HostCallbacks3.this$0.onConfigurationChangedListeners.remove(fragmentManagerImpl.mOnConfigurationChangedListener);
        }
        FragmentActivity$HostCallbacks fragmentActivity$HostCallbacks4 = fragmentManagerImpl.mHost;
        if (fragmentActivity$HostCallbacks4 != null) {
            fragmentActivity$HostCallbacks4.this$0.onMultiWindowModeChangedListeners.remove(fragmentManagerImpl.mOnMultiWindowModeChangedListener);
        }
        FragmentActivity$HostCallbacks fragmentActivity$HostCallbacks5 = fragmentManagerImpl.mHost;
        if (fragmentActivity$HostCallbacks5 != null) {
            fragmentActivity$HostCallbacks5.this$0.onPictureInPictureModeChangedListeners.remove(fragmentManagerImpl.mOnPictureInPictureModeChangedListener);
        }
        FragmentActivity$HostCallbacks fragmentActivity$HostCallbacks6 = fragmentManagerImpl.mHost;
        if (fragmentActivity$HostCallbacks6 != null) {
            MenuHostHelper menuHostHelper = fragmentActivity$HostCallbacks6.this$0.menuHostHelper;
            CopyOnWriteArrayList copyOnWriteArrayList = menuHostHelper.mMenuProviders;
            FragmentManager$2 fragmentManager$2 = fragmentManagerImpl.mMenuProvider;
            copyOnWriteArrayList.remove(fragmentManager$2);
            if (menuHostHelper.mProviderToLifecycleContainers.remove(fragmentManager$2) != null) {
                throw new ClassCastException();
            }
            menuHostHelper.mOnInvalidateMenuCallback.run();
        }
        fragmentManagerImpl.mHost = null;
        fragmentManagerImpl.mContainer = null;
        if (fragmentManagerImpl.mOnBackPressedDispatcher != null) {
            fragmentManagerImpl.mOnBackPressedCallback.remove();
            fragmentManagerImpl.mOnBackPressedDispatcher = null;
        }
        ActivityResultRegistry$register$2 activityResultRegistry$register$2 = fragmentManagerImpl.mStartActivityForResult;
        if (activityResultRegistry$register$2 != null) {
            activityResultRegistry$register$2.this$0.unregister$activity_release(activityResultRegistry$register$2.$key);
            ActivityResultRegistry$register$2 activityResultRegistry$register$22 = fragmentManagerImpl.mStartIntentSenderForResult;
            activityResultRegistry$register$22.this$0.unregister$activity_release(activityResultRegistry$register$22.$key);
            ActivityResultRegistry$register$2 activityResultRegistry$register$23 = fragmentManagerImpl.mRequestPermissions;
            activityResultRegistry$register$23.this$0.unregister$activity_release(activityResultRegistry$register$23.$key);
        }
        this.mFragmentLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public final boolean onMenuItemSelected(int i, MenuItem menuItem) {
        Intent parentActivityIntent;
        if (onMenuItemSelected$androidx$fragment$app$FragmentActivity(i, menuItem)) {
            return true;
        }
        AppCompatDelegateImpl appCompatDelegateImpl = (AppCompatDelegateImpl) getDelegate();
        appCompatDelegateImpl.initWindowDecorActionBar();
        WindowDecorActionBar windowDecorActionBar = appCompatDelegateImpl.mActionBar;
        if (menuItem.getItemId() == 16908332 && windowDecorActionBar != null && (windowDecorActionBar.mDecorToolbar.mDisplayOpts & 4) != 0 && (parentActivityIntent = NavUtils.getParentActivityIntent(this)) != null) {
            if (!shouldUpRecreateTask(parentActivityIntent)) {
                navigateUpTo(parentActivityIntent);
                return true;
            }
            ArrayList arrayList = new ArrayList();
            Intent parentActivityIntent2 = NavUtils.getParentActivityIntent(this);
            if (parentActivityIntent2 == null) {
                parentActivityIntent2 = NavUtils.getParentActivityIntent(this);
            }
            if (parentActivityIntent2 != null) {
                ComponentName component = parentActivityIntent2.getComponent();
                if (component == null) {
                    component = parentActivityIntent2.resolveActivity(getPackageManager());
                }
                int size = arrayList.size();
                try {
                    Intent parentActivityIntent3 = NavUtils.getParentActivityIntent(this, component);
                    while (parentActivityIntent3 != null) {
                        arrayList.add(size, parentActivityIntent3);
                        parentActivityIntent3 = NavUtils.getParentActivityIntent(this, parentActivityIntent3.getComponent());
                    }
                    arrayList.add(parentActivityIntent2);
                } catch (PackageManager.NameNotFoundException e) {
                    Log.e("TaskStackBuilder", "Bad ComponentName while traversing activity parent metadata");
                    throw new IllegalArgumentException(e);
                }
            }
            if (arrayList.isEmpty()) {
                throw new IllegalStateException("No intents added to TaskStackBuilder; cannot startActivities");
            }
            Intent[] intentArr = (Intent[]) arrayList.toArray(new Intent[0]);
            intentArr[0] = new Intent(intentArr[0]).addFlags(268484608);
            startActivities(intentArr, null);
            try {
                finishAffinity();
                return true;
            } catch (IllegalStateException unused) {
                finish();
                return true;
            }
        }
        return false;
    }

    public final boolean onMenuItemSelected$androidx$fragment$app$FragmentActivity(int i, MenuItem menuItem) {
        if (super.onMenuItemSelected(i, menuItem)) {
            return true;
        }
        if (i == 6) {
            FragmentManagerImpl fragmentManagerImpl = this.mFragments.mHost.fragmentManager;
            if (fragmentManagerImpl.mCurState >= 1) {
                Iterator it = fragmentManagerImpl.mFragmentStore.getFragments().iterator();
                while (it.hasNext()) {
                    if (it.next() != null) {
                        throw new ClassCastException();
                    }
                }
            }
        }
        return false;
    }

    @Override // android.app.Activity
    public final void onPause() {
        super.onPause();
        this.mResumed = false;
        this.mFragments.mHost.fragmentManager.dispatchStateChange(5);
        this.mFragmentLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE);
    }

    @Override // android.app.Activity
    public final void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        ((AppCompatDelegateImpl) getDelegate()).ensureSubDecor();
    }

    @Override // android.app.Activity
    public final void onPostResume() {
        onPostResume$androidx$fragment$app$FragmentActivity();
        AppCompatDelegateImpl appCompatDelegateImpl = (AppCompatDelegateImpl) getDelegate();
        appCompatDelegateImpl.initWindowDecorActionBar();
        WindowDecorActionBar windowDecorActionBar = appCompatDelegateImpl.mActionBar;
        if (windowDecorActionBar != null) {
            windowDecorActionBar.mShowHideAnimationEnabled = true;
        }
    }

    public final void onPostResume$androidx$fragment$app$FragmentActivity() {
        super.onPostResume();
        this.mFragmentLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
        FragmentManagerImpl fragmentManagerImpl = this.mFragments.mHost.fragmentManager;
        fragmentManagerImpl.mStateSaved = false;
        fragmentManagerImpl.mStopped = false;
        fragmentManagerImpl.mNonConfig.getClass();
        fragmentManagerImpl.dispatchStateChange(7);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        this.mFragments.noteStateNotSaved();
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    @Override // android.app.Activity
    public final void onResume() {
        FragmentController fragmentController = this.mFragments;
        fragmentController.noteStateNotSaved();
        super.onResume();
        this.mResumed = true;
        fragmentController.mHost.fragmentManager.execPendingActions(true);
    }

    @Override // android.app.Activity
    public final void onStart() {
        onStart$androidx$fragment$app$FragmentActivity();
        ((AppCompatDelegateImpl) getDelegate()).applyApplicationSpecificConfig(true);
    }

    public final void onStart$androidx$fragment$app$FragmentActivity() {
        FragmentController fragmentController = this.mFragments;
        fragmentController.noteStateNotSaved();
        super.onStart();
        this.mStopped = false;
        boolean z = this.mCreated;
        FragmentActivity$HostCallbacks fragmentActivity$HostCallbacks = fragmentController.mHost;
        if (!z) {
            this.mCreated = true;
            FragmentManagerImpl fragmentManagerImpl = fragmentActivity$HostCallbacks.fragmentManager;
            fragmentManagerImpl.mStateSaved = false;
            fragmentManagerImpl.mStopped = false;
            fragmentManagerImpl.mNonConfig.getClass();
            fragmentManagerImpl.dispatchStateChange(4);
        }
        fragmentActivity$HostCallbacks.fragmentManager.execPendingActions(true);
        this.mFragmentLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START);
        FragmentManagerImpl fragmentManagerImpl2 = fragmentActivity$HostCallbacks.fragmentManager;
        fragmentManagerImpl2.mStateSaved = false;
        fragmentManagerImpl2.mStopped = false;
        fragmentManagerImpl2.mNonConfig.getClass();
        fragmentManagerImpl2.dispatchStateChange(5);
    }

    @Override // android.app.Activity
    public final void onStateNotSaved() {
        this.mFragments.noteStateNotSaved();
    }

    @Override // android.app.Activity
    public final void onStop() {
        onStop$androidx$fragment$app$FragmentActivity();
        AppCompatDelegateImpl appCompatDelegateImpl = (AppCompatDelegateImpl) getDelegate();
        appCompatDelegateImpl.initWindowDecorActionBar();
        WindowDecorActionBar windowDecorActionBar = appCompatDelegateImpl.mActionBar;
        if (windowDecorActionBar != null) {
            windowDecorActionBar.mShowHideAnimationEnabled = false;
            ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet = windowDecorActionBar.mCurrentShowAnim;
            if (viewPropertyAnimatorCompatSet != null) {
                viewPropertyAnimatorCompatSet.cancel();
            }
        }
    }

    public final void onStop$androidx$fragment$app$FragmentActivity() {
        super.onStop();
        this.mStopped = true;
        FragmentController fragmentController = this.mFragments;
        Iterator it = fragmentController.mHost.fragmentManager.mFragmentStore.getFragments().iterator();
        while (it.hasNext()) {
            if (it.next() != null) {
                throw new ClassCastException();
            }
        }
        FragmentManagerImpl fragmentManagerImpl = fragmentController.mHost.fragmentManager;
        fragmentManagerImpl.mStopped = true;
        fragmentManagerImpl.mNonConfig.getClass();
        fragmentManagerImpl.dispatchStateChange(4);
        this.mFragmentLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP);
    }

    @Override // android.app.Activity
    public final void onTitleChanged(CharSequence charSequence, int i) {
        super.onTitleChanged(charSequence, i);
        getDelegate().setTitle(charSequence);
    }

    @Override // android.app.Activity
    public final void openOptionsMenu() {
        ((AppCompatDelegateImpl) getDelegate()).initWindowDecorActionBar();
        if (getWindow().hasFeature(0)) {
            super.openOptionsMenu();
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void setContentView(int i) {
        initViewTreeOwners$1();
        getDelegate().setContentView(i);
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public final void setTheme(int i) {
        super.setTheme(i);
        ((AppCompatDelegateImpl) getDelegate()).mThemeResId = i;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void setContentView(View view) {
        initViewTreeOwners$1();
        getDelegate().setContentView(view);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        initViewTreeOwners$1();
        getDelegate().setContentView(view, layoutParams);
    }

    @Override // android.app.Activity, android.view.LayoutInflater.Factory
    public final View onCreateView(String str, Context context, AttributeSet attributeSet) {
        FragmentContainerView fragmentContainerView = (FragmentContainerView) this.mFragments.mHost.fragmentManager.mLayoutInflaterFactory.onCreateView(null, str, context, attributeSet);
        return fragmentContainerView == null ? super.onCreateView(str, context, attributeSet) : fragmentContainerView;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public final void onContentChanged() {
    }
}

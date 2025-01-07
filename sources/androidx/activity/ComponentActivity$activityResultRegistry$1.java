package androidx.activity;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.core.app.ActivityCompat$RequestPermissionsRequestCodeValidator;
import java.util.Arrays;
import java.util.HashSet;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ComponentActivity$activityResultRegistry$1 extends ActivityResultRegistry {
    public final /* synthetic */ ComponentActivity this$0;

    public ComponentActivity$activityResultRegistry$1(ComponentActivity componentActivity) {
        this.this$0 = componentActivity;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.activity.result.ActivityResultRegistry
    public final void onLaunch(final int i, ActivityResultContract activityResultContract, Object obj) {
        Bundle bundle;
        ComponentActivity componentActivity = this.this$0;
        final ActivityResultContract.SynchronousResult synchronousResult = activityResultContract.getSynchronousResult(componentActivity, obj);
        if (synchronousResult != null) {
            final int i2 = 0;
            new Handler(Looper.getMainLooper()).post(new Runnable(this) { // from class: androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda1
                public final /* synthetic */ ComponentActivity$activityResultRegistry$1 f$0;

                {
                    this.f$0 = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    switch (i2) {
                        case 0:
                            ComponentActivity$activityResultRegistry$1 componentActivity$activityResultRegistry$1 = this.f$0;
                            int i3 = i;
                            Object obj2 = ((ActivityResultContract.SynchronousResult) synchronousResult).value;
                            String str = (String) componentActivity$activityResultRegistry$1.rcToKey.get(Integer.valueOf(i3));
                            if (str != null) {
                                ActivityResultRegistry.CallbackAndContract callbackAndContract = (ActivityResultRegistry.CallbackAndContract) componentActivity$activityResultRegistry$1.keyToCallback.get(str);
                                if ((callbackAndContract != null ? callbackAndContract.callback : null) != null) {
                                    ActivityResultCallback activityResultCallback = callbackAndContract.callback;
                                    if (componentActivity$activityResultRegistry$1.launchedKeys.remove(str)) {
                                        activityResultCallback.onActivityResult(obj2);
                                        break;
                                    }
                                } else {
                                    componentActivity$activityResultRegistry$1.pendingResults.remove(str);
                                    componentActivity$activityResultRegistry$1.parsedPendingResults.put(str, obj2);
                                    break;
                                }
                            }
                            break;
                        default:
                            this.f$0.dispatchResult(i, 0, new Intent().setAction("androidx.activity.result.contract.action.INTENT_SENDER_REQUEST").putExtra("androidx.activity.result.contract.extra.SEND_INTENT_EXCEPTION", (IntentSender.SendIntentException) synchronousResult));
                            break;
                    }
                }
            });
            return;
        }
        Intent createIntent = activityResultContract.createIntent(obj);
        if (createIntent.getExtras() != null) {
            Bundle extras = createIntent.getExtras();
            Intrinsics.checkNotNull(extras);
            if (extras.getClassLoader() == null) {
                createIntent.setExtrasClassLoader(componentActivity.getClassLoader());
            }
        }
        if (createIntent.hasExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE")) {
            Bundle bundleExtra = createIntent.getBundleExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE");
            createIntent.removeExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE");
            bundle = bundleExtra;
        } else {
            bundle = null;
        }
        if (!"androidx.activity.result.contract.action.REQUEST_PERMISSIONS".equals(createIntent.getAction())) {
            if (!"androidx.activity.result.contract.action.INTENT_SENDER_REQUEST".equals(createIntent.getAction())) {
                componentActivity.startActivityForResult(createIntent, i, bundle);
                return;
            }
            IntentSenderRequest intentSenderRequest = (IntentSenderRequest) createIntent.getParcelableExtra("androidx.activity.result.contract.extra.INTENT_SENDER_REQUEST");
            try {
                Intrinsics.checkNotNull(intentSenderRequest);
                componentActivity.startIntentSenderForResult(intentSenderRequest.intentSender, i, intentSenderRequest.fillInIntent, intentSenderRequest.flagsMask, intentSenderRequest.flagsValues, 0, bundle);
                return;
            } catch (IntentSender.SendIntentException e) {
                final int i3 = 1;
                new Handler(Looper.getMainLooper()).post(new Runnable(this) { // from class: androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda1
                    public final /* synthetic */ ComponentActivity$activityResultRegistry$1 f$0;

                    {
                        this.f$0 = this;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        switch (i3) {
                            case 0:
                                ComponentActivity$activityResultRegistry$1 componentActivity$activityResultRegistry$1 = this.f$0;
                                int i32 = i;
                                Object obj2 = ((ActivityResultContract.SynchronousResult) e).value;
                                String str = (String) componentActivity$activityResultRegistry$1.rcToKey.get(Integer.valueOf(i32));
                                if (str != null) {
                                    ActivityResultRegistry.CallbackAndContract callbackAndContract = (ActivityResultRegistry.CallbackAndContract) componentActivity$activityResultRegistry$1.keyToCallback.get(str);
                                    if ((callbackAndContract != null ? callbackAndContract.callback : null) != null) {
                                        ActivityResultCallback activityResultCallback = callbackAndContract.callback;
                                        if (componentActivity$activityResultRegistry$1.launchedKeys.remove(str)) {
                                            activityResultCallback.onActivityResult(obj2);
                                            break;
                                        }
                                    } else {
                                        componentActivity$activityResultRegistry$1.pendingResults.remove(str);
                                        componentActivity$activityResultRegistry$1.parsedPendingResults.put(str, obj2);
                                        break;
                                    }
                                }
                                break;
                            default:
                                this.f$0.dispatchResult(i, 0, new Intent().setAction("androidx.activity.result.contract.action.INTENT_SENDER_REQUEST").putExtra("androidx.activity.result.contract.extra.SEND_INTENT_EXCEPTION", (IntentSender.SendIntentException) e));
                                break;
                        }
                    }
                });
                return;
            }
        }
        String[] stringArrayExtra = createIntent.getStringArrayExtra("androidx.activity.result.contract.extra.PERMISSIONS");
        if (stringArrayExtra == null) {
            stringArrayExtra = new String[0];
        }
        HashSet hashSet = new HashSet();
        for (String str : stringArrayExtra) {
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException(ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(new StringBuilder("Permission request for permissions "), Arrays.toString(stringArrayExtra), " must not contain null or empty values"));
            }
        }
        int size = hashSet.size();
        String[] strArr = size > 0 ? new String[stringArrayExtra.length - size] : stringArrayExtra;
        if (size > 0) {
            if (size == stringArrayExtra.length) {
                return;
            }
            int i4 = 0;
            for (int i5 = 0; i5 < stringArrayExtra.length; i5++) {
                if (!hashSet.contains(Integer.valueOf(i5))) {
                    strArr[i4] = stringArrayExtra[i5];
                    i4++;
                }
            }
        }
        if (componentActivity instanceof ActivityCompat$RequestPermissionsRequestCodeValidator) {
        }
        componentActivity.requestPermissions(stringArrayExtra, i);
    }
}

package com.android.systemui.biometrics.ui.binder;

import android.content.Context;
import android.hardware.face.FaceManager;
import android.hardware.face.FaceSensorPropertiesInternal;
import android.view.View;
import com.android.systemui.biometrics.shared.model.BiometricModalities;
import com.android.systemui.biometrics.shared.model.BiometricModality;
import com.android.systemui.biometrics.shared.model.BiometricModalityKt;
import com.android.systemui.biometrics.ui.viewmodel.PromptViewModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class Spaghetti {
    public final Context applicationContext;
    public final CoroutineScope applicationScope;
    public Callback legacyCallback;
    public final List lockoutErrorStrings;
    public BiometricModalities modalities = new BiometricModalities(null, null);
    public final View view;
    public final PromptViewModel viewModel;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Callback {
        void onAuthenticated();

        void onAuthenticatedAndConfirmed();

        void onButtonNegative();

        void onButtonTryAgain();

        void onContentViewMoreOptionsButtonPressed();

        void onError();

        void onStartDelayedFingerprintSensor();

        void onUseDeviceCredential();

        void onUserCanceled();
    }

    public Spaghetti(View view, PromptViewModel promptViewModel, Context context, CoroutineScope coroutineScope) {
        this.view = view;
        this.viewModel = promptViewModel;
        this.applicationContext = context;
        this.applicationScope = coroutineScope;
        List listOf = CollectionsKt__CollectionsKt.listOf(7, 9);
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listOf, 10));
        Iterator it = listOf.iterator();
        while (it.hasNext()) {
            arrayList.add(FaceManager.getErrorString(this.applicationContext, ((Number) it.next()).intValue(), 0));
        }
        this.lockoutErrorStrings = arrayList;
    }

    public final boolean ignoreUnsuccessfulEventsFrom(BiometricModality biometricModality, String str) {
        if (!this.modalities.getHasFaceAndFingerprint() || biometricModality != BiometricModality.Face) {
            return false;
        }
        FaceSensorPropertiesInternal faceSensorPropertiesInternal = this.modalities.faceProperties;
        return (faceSensorPropertiesInternal != null && faceSensorPropertiesInternal.sensorStrength == 2 && this.lockoutErrorStrings.contains(str)) ? false : true;
    }

    public final void onAuthenticationFailed(int i, String str) {
        BiometricModality asBiometricModality = BiometricModalityKt.asBiometricModality(i);
        this.viewModel.ensureFingerprintHasStarted(true);
        BuildersKt.launch$default(this.applicationScope, null, null, new Spaghetti$onAuthenticationFailed$1(this, str, asBiometricModality, null), 3);
    }

    public final void onAuthenticationSucceeded(int i) {
        BuildersKt.launch$default(this.applicationScope, null, null, new Spaghetti$onAuthenticationSucceeded$1(i, this, null), 3);
    }

    public final void onError(int i, String str) {
        if (ignoreUnsuccessfulEventsFrom(BiometricModalityKt.asBiometricModality(i), str)) {
            return;
        }
        BuildersKt.launch$default(this.applicationScope, null, null, new Spaghetti$onError$1(this, str, null), 3);
    }

    public final void onHelp(int i, String str) {
        if (ignoreUnsuccessfulEventsFrom(BiometricModalityKt.asBiometricModality(i), "")) {
            return;
        }
        BuildersKt.launch$default(this.applicationScope, null, null, new Spaghetti$onHelp$1(this, str, null), 3);
    }
}

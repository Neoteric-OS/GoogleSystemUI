package com.google.android.systemui.assist.uihints;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Paint;
import android.os.Handler;
import android.provider.Settings;
import com.android.internal.app.AssistUtils;
import com.android.systemui.assist.AssistManager$UiController;
import com.android.systemui.assist.ui.EdgeLight;
import com.android.systemui.navigationbar.NavigationBarControllerImpl;
import com.android.systemui.navigationbar.views.NavigationBar;
import com.android.systemui.navigationbar.views.NavigationBarTransitions;
import com.google.android.systemui.assist.AssistManagerGoogle;
import com.google.android.systemui.assist.AssistManagerGoogle$$ExternalSyntheticLambda1;
import com.google.android.systemui.assist.AssistManagerGoogle$$ExternalSyntheticLambda2;
import com.google.android.systemui.assist.uihints.NgaMessageHandler;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AssistantPresenceHandler implements NgaMessageHandler.ConfigInfoListener {
    public final AssistUtils mAssistUtils;
    public final Set mAssistantPresenceChangeListeners = new HashSet();
    public final ContentResolver mContentResolver;
    public boolean mGoogleIsAssistant;
    public boolean mNgaIsAssistant;

    public AssistantPresenceHandler(Context context, AssistUtils assistUtils) {
        ContentResolver contentResolver = context.getContentResolver();
        this.mContentResolver = contentResolver;
        this.mAssistUtils = assistUtils;
        this.mNgaIsAssistant = Settings.Secure.getInt(contentResolver, "com.google.android.systemui.assist.uihints.NGA_IS_ASSISTANT", 0) != 0;
    }

    @Override // com.google.android.systemui.assist.uihints.NgaMessageHandler.ConfigInfoListener
    public final void onConfigInfo(NgaMessageHandler.ConfigInfo configInfo) {
        ComponentName assistComponentForUser = this.mAssistUtils.getAssistComponentForUser(-2);
        updateAssistantPresence(assistComponentForUser != null && "com.google.android.googlequicksearchbox/com.google.android.voiceinteraction.GsaVoiceInteractionService".equals(assistComponentForUser.flattenToString()), configInfo.ngaIsAssistant);
    }

    /* JADX WARN: Type inference failed for: r12v1 */
    /* JADX WARN: Type inference failed for: r12v2, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r12v7 */
    public final void updateAssistantPresence(boolean z, boolean z2) {
        NavigationBarControllerImpl navigationBarControllerImpl;
        NavigationBar defaultNavigationBar;
        NavigationBarControllerImpl navigationBarControllerImpl2;
        NavigationBar defaultNavigationBar2;
        ?? r12 = (z && z2) ? 1 : 0;
        if (this.mGoogleIsAssistant == z && this.mNgaIsAssistant == r12) {
            return;
        }
        this.mGoogleIsAssistant = z;
        this.mNgaIsAssistant = r12;
        Settings.Secure.putInt(this.mContentResolver, "com.google.android.systemui.assist.uihints.NGA_IS_ASSISTANT", r12);
        Iterator it = ((HashSet) this.mAssistantPresenceChangeListeners).iterator();
        while (it.hasNext()) {
            AssistManagerGoogle$$ExternalSyntheticLambda1 assistManagerGoogle$$ExternalSyntheticLambda1 = (AssistManagerGoogle$$ExternalSyntheticLambda1) it.next();
            boolean z3 = this.mGoogleIsAssistant;
            boolean z4 = this.mNgaIsAssistant;
            AssistManagerGoogle assistManagerGoogle = assistManagerGoogle$$ExternalSyntheticLambda1.f$0;
            if (assistManagerGoogle.mGoogleIsAssistant != z3 || assistManagerGoogle.mNgaIsAssistant != z4) {
                Handler handler = assistManagerGoogle.mUiHandler;
                if (z4) {
                    AssistManager$UiController assistManager$UiController = assistManagerGoogle.mUiController;
                    NgaUiController ngaUiController = assistManagerGoogle.mNgaUiController;
                    if (!assistManager$UiController.equals(ngaUiController)) {
                        AssistManager$UiController assistManager$UiController2 = assistManagerGoogle.mUiController;
                        assistManagerGoogle.mUiController = ngaUiController;
                        Objects.requireNonNull(assistManager$UiController2);
                        handler.post(new AssistManagerGoogle$$ExternalSyntheticLambda2(1, assistManager$UiController2));
                    }
                } else {
                    AssistManager$UiController assistManager$UiController3 = assistManagerGoogle.mUiController;
                    GoogleDefaultUiController googleDefaultUiController = assistManagerGoogle.mDefaultUiController;
                    if (!assistManager$UiController3.equals(googleDefaultUiController)) {
                        AssistManager$UiController assistManager$UiController4 = assistManagerGoogle.mUiController;
                        assistManagerGoogle.mUiController = googleDefaultUiController;
                        Objects.requireNonNull(assistManager$UiController4);
                        handler.post(new AssistManagerGoogle$$ExternalSyntheticLambda2(1, assistManager$UiController4));
                    }
                    AssistantInvocationLightsView assistantInvocationLightsView = googleDefaultUiController.mInvocationLightsView;
                    if (z3) {
                        int i = assistantInvocationLightsView.mColorBlue;
                        int i2 = assistantInvocationLightsView.mColorRed;
                        int i3 = assistantInvocationLightsView.mColorYellow;
                        int i4 = assistantInvocationLightsView.mColorGreen;
                        assistantInvocationLightsView.mUseNavBarColor = false;
                        if (assistantInvocationLightsView.mRegistered && (navigationBarControllerImpl2 = assistantInvocationLightsView.mNavigationBarController) != null && (defaultNavigationBar2 = navigationBarControllerImpl2.getDefaultNavigationBar()) != null) {
                            defaultNavigationBar2.mNavigationBarTransitions.mDarkIntensityListeners.remove(assistantInvocationLightsView);
                            assistantInvocationLightsView.mRegistered = false;
                        }
                        ((EdgeLight) assistantInvocationLightsView.mAssistInvocationLights.get(0)).setColor(i);
                        ((EdgeLight) assistantInvocationLightsView.mAssistInvocationLights.get(1)).setColor(i2);
                        ((EdgeLight) assistantInvocationLightsView.mAssistInvocationLights.get(2)).setColor(i3);
                        ((EdgeLight) assistantInvocationLightsView.mAssistInvocationLights.get(3)).setColor(i4);
                    } else {
                        assistantInvocationLightsView.mUseNavBarColor = true;
                        assistantInvocationLightsView.mPaint.setStrokeCap(Paint.Cap.BUTT);
                        if (!assistantInvocationLightsView.mRegistered && (navigationBarControllerImpl = assistantInvocationLightsView.mNavigationBarController) != null && (defaultNavigationBar = navigationBarControllerImpl.getDefaultNavigationBar()) != null) {
                            NavigationBarTransitions navigationBarTransitions = defaultNavigationBar.mNavigationBarTransitions;
                            navigationBarTransitions.mDarkIntensityListeners.add(assistantInvocationLightsView);
                            assistantInvocationLightsView.updateDarkness(navigationBarTransitions.mLightTransitionsController.mDarkIntensity);
                            assistantInvocationLightsView.mRegistered = true;
                        }
                    }
                }
                assistManagerGoogle.mGoogleIsAssistant = z3;
                assistManagerGoogle.mNgaIsAssistant = z4;
            }
            assistManagerGoogle.mCheckAssistantStatus = false;
        }
    }
}

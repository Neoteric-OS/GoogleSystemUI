package com.android.systemui.biometrics.ui.binder;

import android.content.res.TypedArray;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.airbnb.lottie.LottieAnimationView;
import com.android.systemui.biometrics.AuthContainerView;
import com.android.systemui.biometrics.ui.binder.Spaghetti;
import com.android.systemui.biometrics.ui.viewmodel.PromptViewModel;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.statusbar.VibratorHelper;
import com.android.wm.shell.R;
import com.google.android.msdl.domain.MSDLPlayer;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$BooleanRef;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class BiometricViewBinder {
    public static final Spaghetti bind(final View view, PromptViewModel promptViewModel, AuthContainerView.AnonymousClass1 anonymousClass1, View view2, Spaghetti.Callback callback, CoroutineScope coroutineScope, VibratorHelper vibratorHelper, MSDLPlayer mSDLPlayer) {
        Object systemService = view.getContext().getSystemService((Class<Object>) AccessibilityManager.class);
        Intrinsics.checkNotNull(systemService);
        AccessibilityManager accessibilityManager = (AccessibilityManager) systemService;
        int color = view.getResources().getColor(R.color.biometric_dialog_error, view.getContext().getTheme());
        TypedArray obtainStyledAttributes = view.getContext().obtainStyledAttributes(R.style.TextAppearance_AuthCredential_Indicator, new int[]{android.R.attr.textColor});
        int color2 = obtainStyledAttributes.getColor(0, 0);
        obtainStyledAttributes.recycle();
        ImageView imageView = (ImageView) view.requireViewById(R.id.logo);
        TextView textView = (TextView) view.requireViewById(R.id.logo_description);
        TextView textView2 = (TextView) view.requireViewById(R.id.title);
        TextView textView3 = (TextView) view.requireViewById(R.id.subtitle);
        TextView textView4 = (TextView) view.requireViewById(R.id.description);
        LinearLayout linearLayout = (LinearLayout) view.requireViewById(R.id.customized_view_container);
        View requireViewById = view.requireViewById(R.id.panel);
        textView2.setSelected((accessibilityManager.isEnabled() && accessibilityManager.isTouchExplorationEnabled()) ? false : true);
        textView3.setSelected((accessibilityManager.isEnabled() && accessibilityManager.isTouchExplorationEnabled()) ? false : true);
        LottieAnimationView lottieAnimationView = (LottieAnimationView) view.requireViewById(R.id.biometric_icon);
        TextView textView5 = (TextView) view.requireViewById(R.id.indicator);
        Button button = (Button) view.requireViewById(R.id.button_negative);
        Button button2 = (Button) view.requireViewById(R.id.button_cancel);
        Button button3 = (Button) view.requireViewById(R.id.button_use_credential);
        Button button4 = (Button) view.requireViewById(R.id.button_confirm);
        Button button5 = (Button) view.requireViewById(R.id.button_try_again);
        AccessibilityDelegateCompat accessibilityDelegateCompat = new AccessibilityDelegateCompat() { // from class: com.android.systemui.biometrics.ui.binder.BiometricViewBinder$bind$cancelDelegate$1
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public final void onInitializeAccessibilityNodeInfo(View view3, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                this.mOriginalDelegate.onInitializeAccessibilityNodeInfo(view3, accessibilityNodeInfoCompat.mInfo);
                accessibilityNodeInfoCompat.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(16, view.getContext().getString(R.string.biometric_dialog_cancel_authentication)));
            }
        };
        ViewCompat.setAccessibilityDelegate(view2, accessibilityDelegateCompat);
        ViewCompat.setAccessibilityDelegate(button2, accessibilityDelegateCompat);
        Spaghetti spaghetti = new Spaghetti(view, promptViewModel, view.getContext().getApplicationContext(), coroutineScope);
        RepeatWhenAttachedKt.repeatWhenAttached(view, EmptyCoroutineContext.INSTANCE, new BiometricViewBinder$bind$1(promptViewModel, view, imageView, textView, textView2, textView3, textView4, linearLayout, callback, button, button2, button3, button4, button5, spaghetti, new Ref$BooleanRef(), anonymousClass1, lottieAnimationView, requireViewById, view2, textView5, accessibilityManager, color, color2, vibratorHelper, mSDLPlayer, null));
        return spaghetti;
    }
}

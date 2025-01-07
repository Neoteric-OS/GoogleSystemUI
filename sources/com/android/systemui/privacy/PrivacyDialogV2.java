package com.android.systemui.privacy;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import androidx.compose.animation.Scale$$ExternalSyntheticOutline0;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;
import com.android.settingslib.Utils;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.systemui.util.DialogKt;
import com.android.wm.shell.R;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Pair;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PrivacyDialogV2 extends SystemUIDialog {
    public final Function2 closeApp;
    public final View.OnLayoutChangeListener decorViewLayoutListener;
    public final List dismissListeners;
    public final AtomicBoolean dismissed;
    public final List list;
    public final Function3 manageApp;
    public final Function0 openPrivacyDashboard;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class PrivacyElement {
        public final CharSequence applicationName;
        public final CharSequence attributionLabel;
        public final CharSequence attributionTag;
        public final StringBuilder builder;
        public final boolean isActive;
        public final boolean isPhoneCall;
        public final boolean isService;
        public final long lastActiveTimestamp;
        public final Intent navigationIntent;
        public final String packageName;
        public final String permGroupName;
        public final CharSequence proxyLabel;
        public final PrivacyType type;
        public final int userId;

        public PrivacyElement(PrivacyType privacyType, String str, int i, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, CharSequence charSequence4, long j, boolean z, boolean z2, boolean z3, String str2, Intent intent) {
            this.type = privacyType;
            this.packageName = str;
            this.userId = i;
            this.applicationName = charSequence;
            this.attributionTag = charSequence2;
            this.attributionLabel = charSequence3;
            this.proxyLabel = charSequence4;
            this.lastActiveTimestamp = j;
            this.isActive = z;
            this.isPhoneCall = z2;
            this.isService = z3;
            this.permGroupName = str2;
            this.navigationIntent = intent;
            StringBuilder sb = new StringBuilder("PrivacyElement(");
            this.builder = sb;
            sb.append("type=" + privacyType.getLogName());
            sb.append(", packageName=".concat(str));
            sb.append(", userId=" + i);
            sb.append(", appName=" + ((Object) charSequence));
            if (charSequence2 != null) {
                sb.append(", attributionTag=" + ((Object) charSequence2));
            }
            if (charSequence3 != null) {
                sb.append(", attributionLabel=" + ((Object) charSequence3));
            }
            if (charSequence4 != null) {
                sb.append(", proxyLabel=" + ((Object) charSequence4));
            }
            sb.append(", lastActive=" + j);
            if (z) {
                sb.append(", active");
            }
            if (z2) {
                sb.append(", phoneCall");
            }
            if (z3) {
                sb.append(", service");
            }
            sb.append(", permGroupName=".concat(str2));
            sb.append(", navigationIntent=" + intent + ")");
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof PrivacyElement)) {
                return false;
            }
            PrivacyElement privacyElement = (PrivacyElement) obj;
            return this.type == privacyElement.type && Intrinsics.areEqual(this.packageName, privacyElement.packageName) && this.userId == privacyElement.userId && Intrinsics.areEqual(this.applicationName, privacyElement.applicationName) && Intrinsics.areEqual(this.attributionTag, privacyElement.attributionTag) && Intrinsics.areEqual(this.attributionLabel, privacyElement.attributionLabel) && Intrinsics.areEqual(this.proxyLabel, privacyElement.proxyLabel) && this.lastActiveTimestamp == privacyElement.lastActiveTimestamp && this.isActive == privacyElement.isActive && this.isPhoneCall == privacyElement.isPhoneCall && this.isService == privacyElement.isService && Intrinsics.areEqual(this.permGroupName, privacyElement.permGroupName) && Intrinsics.areEqual(this.navigationIntent, privacyElement.navigationIntent);
        }

        public final int hashCode() {
            int hashCode = (this.applicationName.hashCode() + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.userId, PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(this.packageName, this.type.hashCode() * 31, 31), 31)) * 31;
            CharSequence charSequence = this.attributionTag;
            int hashCode2 = (hashCode + (charSequence == null ? 0 : charSequence.hashCode())) * 31;
            CharSequence charSequence2 = this.attributionLabel;
            int hashCode3 = (hashCode2 + (charSequence2 == null ? 0 : charSequence2.hashCode())) * 31;
            CharSequence charSequence3 = this.proxyLabel;
            return this.navigationIntent.hashCode() + PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(this.permGroupName, TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(Scale$$ExternalSyntheticOutline0.m((hashCode3 + (charSequence3 != null ? charSequence3.hashCode() : 0)) * 31, 31, this.lastActiveTimestamp), 31, this.isActive), 31, this.isPhoneCall), 31, this.isService), 31);
        }

        public final String toString() {
            return this.builder.toString();
        }
    }

    public PrivacyDialogV2(Context context, List list, Function3 function3, Function2 function2, Function0 function0) {
        super(context, R.style.Theme_PrivacyDialog, true);
        this.list = list;
        this.manageApp = function3;
        this.closeApp = function2;
        this.openPrivacyDashboard = function0;
        this.dismissListeners = new ArrayList();
        this.dismissed = new AtomicBoolean(false);
        Pair maybeForceFullscreen = DialogKt.maybeForceFullscreen(this);
        this.decorViewLayoutListener = maybeForceFullscreen != null ? (View.OnLayoutChangeListener) maybeForceFullscreen.component2() : null;
    }

    public final Drawable getMutableDrawable(int i) {
        Drawable drawable = getContext().getDrawable(i);
        Intrinsics.checkNotNull(drawable);
        return drawable.mutate();
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x00ec, code lost:
    
        if (r0 == null) goto L37;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v15, types: [android.view.LayoutInflater] */
    /* JADX WARN: Type inference failed for: r0v19, types: [android.content.pm.PackageManager] */
    /* JADX WARN: Type inference failed for: r0v22, types: [android.content.pm.PackageManager] */
    /* JADX WARN: Type inference failed for: r0v33, types: [android.widget.TextView] */
    /* JADX WARN: Type inference failed for: r10v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r10v1, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r10v16, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r12v0 */
    /* JADX WARN: Type inference failed for: r12v13 */
    /* JADX WARN: Type inference failed for: r12v14 */
    /* JADX WARN: Type inference failed for: r12v2, types: [android.content.pm.PackageItemInfo] */
    /* JADX WARN: Type inference failed for: r12v4, types: [android.widget.ImageView] */
    /* JADX WARN: Type inference failed for: r16v0, types: [android.app.AlertDialog, com.android.systemui.privacy.PrivacyDialogV2, com.android.systemui.statusbar.phone.SystemUIDialog] */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r4v23 */
    @Override // com.android.systemui.statusbar.phone.SystemUIDialog, android.app.AlertDialog, android.app.Dialog
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onCreate(android.os.Bundle r17) {
        /*
            Method dump skipped, instructions count: 793
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.privacy.PrivacyDialogV2.onCreate(android.os.Bundle):void");
    }

    @Override // com.android.systemui.statusbar.phone.SystemUIDialog
    public final void stop() {
        this.dismissed.set(true);
        Iterator it = this.dismissListeners.iterator();
        while (it.hasNext()) {
            WeakReference weakReference = (WeakReference) it.next();
            it.remove();
            PrivacyDialogControllerV2$onDialogDismissed$1 privacyDialogControllerV2$onDialogDismissed$1 = (PrivacyDialogControllerV2$onDialogDismissed$1) weakReference.get();
            if (privacyDialogControllerV2$onDialogDismissed$1 != null) {
                PrivacyDialogControllerV2 privacyDialogControllerV2 = privacyDialogControllerV2$onDialogDismissed$1.this$0;
                privacyDialogControllerV2.privacyLogger.logPrivacyDialogDismissed();
                privacyDialogControllerV2.uiEventLogger.log(PrivacyDialogEvent.PRIVACY_DIALOG_DISMISSED);
                privacyDialogControllerV2.dialog = null;
            }
        }
        if (this.decorViewLayoutListener != null) {
            Window window = getWindow();
            Intrinsics.checkNotNull(window);
            window.getDecorView().removeOnLayoutChangeListener(this.decorViewLayoutListener);
        }
    }

    public final void updateIconView(ImageView imageView, Drawable drawable, boolean z) {
        drawable.setTint(Utils.getColorAttrDefaultColor(z ? android.R.^attr-private.materialColorOnPrimaryFixed : android.R.^attr-private.materialColorOnSurface, 0, getContext()));
        Drawable mutableDrawable = getMutableDrawable(R.drawable.privacy_dialog_background_circle);
        mutableDrawable.setTint(Utils.getColorAttrDefaultColor(z ? android.R.^attr-private.materialColorPrimaryFixed : android.R.^attr-private.materialColorSurfaceContainerHigh, 0, getContext()));
        int dimension = (int) getContext().getResources().getDimension(R.dimen.ongoing_appops_dialog_circle_size);
        int dimension2 = (int) getContext().getResources().getDimension(R.dimen.ongoing_appops_dialog_icon_size);
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{mutableDrawable, drawable});
        layerDrawable.setLayerSize(0, dimension, dimension);
        layerDrawable.setLayerGravity(0, 17);
        layerDrawable.setLayerSize(1, dimension2, dimension2);
        layerDrawable.setLayerGravity(1, 17);
        imageView.setImageDrawable(layerDrawable);
    }
}

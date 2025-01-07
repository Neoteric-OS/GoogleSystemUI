package com.android.systemui.privacy;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsets;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import androidx.compose.animation.Scale$$ExternalSyntheticOutline0;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;
import com.android.settingslib.Utils;
import com.android.systemui.privacy.PrivacyDialog;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.wm.shell.R;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PrivacyDialog extends SystemUIDialog {
    public final PrivacyDialog$clickListener$1 clickListener;
    public final List dismissListeners;
    public final AtomicBoolean dismissed;
    public final String enterpriseText;
    public final int iconColorSolid;
    public final List list;
    public final String phonecall;
    public ViewGroup rootView;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class PrivacyElement {
        public final boolean active;
        public final CharSequence applicationName;
        public final CharSequence attributionLabel;
        public final CharSequence attributionTag;
        public final StringBuilder builder;
        public final boolean enterprise;
        public final long lastActiveTimestamp;
        public final Intent navigationIntent;
        public final String packageName;
        public final CharSequence permGroupName;
        public final boolean phoneCall;
        public final CharSequence proxyLabel;
        public final PrivacyType type;
        public final int userId;

        public PrivacyElement(PrivacyType privacyType, String str, int i, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, CharSequence charSequence4, long j, boolean z, boolean z2, boolean z3, CharSequence charSequence5, Intent intent) {
            this.type = privacyType;
            this.packageName = str;
            this.userId = i;
            this.applicationName = charSequence;
            this.attributionTag = charSequence2;
            this.attributionLabel = charSequence3;
            this.proxyLabel = charSequence4;
            this.lastActiveTimestamp = j;
            this.active = z;
            this.enterprise = z2;
            this.phoneCall = z3;
            this.permGroupName = charSequence5;
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
                sb.append(", enterprise");
            }
            if (z3) {
                sb.append(", phoneCall");
            }
            sb.append(", permGroupName=" + ((Object) charSequence5) + ")");
            StringBuilder sb2 = new StringBuilder(", navigationIntent=");
            sb2.append(intent);
            sb.append(sb2.toString());
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof PrivacyElement)) {
                return false;
            }
            PrivacyElement privacyElement = (PrivacyElement) obj;
            return this.type == privacyElement.type && Intrinsics.areEqual(this.packageName, privacyElement.packageName) && this.userId == privacyElement.userId && Intrinsics.areEqual(this.applicationName, privacyElement.applicationName) && Intrinsics.areEqual(this.attributionTag, privacyElement.attributionTag) && Intrinsics.areEqual(this.attributionLabel, privacyElement.attributionLabel) && Intrinsics.areEqual(this.proxyLabel, privacyElement.proxyLabel) && this.lastActiveTimestamp == privacyElement.lastActiveTimestamp && this.active == privacyElement.active && this.enterprise == privacyElement.enterprise && this.phoneCall == privacyElement.phoneCall && Intrinsics.areEqual(this.permGroupName, privacyElement.permGroupName) && Intrinsics.areEqual(this.navigationIntent, privacyElement.navigationIntent);
        }

        public final int hashCode() {
            int hashCode = (this.applicationName.hashCode() + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.userId, PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(this.packageName, this.type.hashCode() * 31, 31), 31)) * 31;
            CharSequence charSequence = this.attributionTag;
            int hashCode2 = (hashCode + (charSequence == null ? 0 : charSequence.hashCode())) * 31;
            CharSequence charSequence2 = this.attributionLabel;
            int hashCode3 = (hashCode2 + (charSequence2 == null ? 0 : charSequence2.hashCode())) * 31;
            CharSequence charSequence3 = this.proxyLabel;
            int hashCode4 = (this.permGroupName.hashCode() + TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(Scale$$ExternalSyntheticOutline0.m((hashCode3 + (charSequence3 == null ? 0 : charSequence3.hashCode())) * 31, 31, this.lastActiveTimestamp), 31, this.active), 31, this.enterprise), 31, this.phoneCall)) * 31;
            Intent intent = this.navigationIntent;
            return hashCode4 + (intent != null ? intent.hashCode() : 0);
        }

        public final String toString() {
            return this.builder.toString();
        }
    }

    /* JADX WARN: Type inference failed for: r3v2, types: [com.android.systemui.privacy.PrivacyDialog$clickListener$1] */
    public PrivacyDialog(Context context, List list, final Function4 function4) {
        super(context, R.style.PrivacyDialog, true);
        this.list = list;
        this.dismissListeners = new ArrayList();
        this.dismissed = new AtomicBoolean(false);
        this.iconColorSolid = Utils.getColorAttrDefaultColor(android.R.attr.colorPrimary, 0, getContext());
        this.enterpriseText = AppSearchSchema$Builder$$ExternalSyntheticOutline0.m(" ", context.getString(R.string.ongoing_privacy_dialog_enterprise));
        this.phonecall = context.getString(R.string.ongoing_privacy_dialog_phonecall);
        this.clickListener = new View.OnClickListener() { // from class: com.android.systemui.privacy.PrivacyDialog$clickListener$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Object tag = view.getTag();
                if (tag != null) {
                    PrivacyDialog.PrivacyElement privacyElement = (PrivacyDialog.PrivacyElement) tag;
                    Function4.this.invoke(privacyElement.packageName, Integer.valueOf(privacyElement.userId), privacyElement.attributionTag, privacyElement.navigationIntent);
                }
            }
        };
    }

    @Override // com.android.systemui.statusbar.phone.SystemUIDialog, android.app.AlertDialog, android.app.Dialog
    public final void onCreate(Bundle bundle) {
        int i;
        super.onCreate(bundle);
        Window window = getWindow();
        if (window != null) {
            window.getAttributes().setFitInsetsTypes(window.getAttributes().getFitInsetsTypes() | WindowInsets.Type.statusBars());
            window.getAttributes().receiveInsetsIgnoringZOrder = true;
            window.setGravity(49);
        }
        setTitle(R.string.ongoing_privacy_dialog_a11y_title);
        setContentView(R.layout.privacy_dialog);
        this.rootView = (ViewGroup) requireViewById(R.id.root);
        for (PrivacyElement privacyElement : this.list) {
            ViewGroup viewGroup = this.rootView;
            String str = null;
            if (viewGroup == null) {
                viewGroup = null;
            }
            LayoutInflater from = LayoutInflater.from(getContext());
            ViewGroup viewGroup2 = this.rootView;
            if (viewGroup2 == null) {
                viewGroup2 = null;
            }
            ViewGroup viewGroup3 = (ViewGroup) from.inflate(R.layout.privacy_dialog_item, viewGroup2, false);
            PrivacyType privacyType = privacyElement.type;
            Context context = getContext();
            int ordinal = privacyType.ordinal();
            if (ordinal == 0) {
                i = R.drawable.privacy_item_circle_camera;
            } else if (ordinal == 1) {
                i = R.drawable.privacy_item_circle_microphone;
            } else if (ordinal == 2) {
                i = R.drawable.privacy_item_circle_location;
            } else {
                if (ordinal != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                i = R.drawable.privacy_item_circle_media_projection;
            }
            LayerDrawable layerDrawable = (LayerDrawable) context.getDrawable(i);
            layerDrawable.findDrawableByLayerId(R.id.icon).setTint(this.iconColorSolid);
            ImageView imageView = (ImageView) viewGroup3.requireViewById(R.id.icon);
            imageView.setImageDrawable(layerDrawable);
            imageView.setContentDescription(privacyElement.type.getName(imageView.getContext()));
            int i2 = privacyElement.active ? R.string.ongoing_privacy_dialog_using_op : R.string.ongoing_privacy_dialog_recent_op;
            boolean z = privacyElement.phoneCall;
            CharSequence charSequence = z ? this.phonecall : privacyElement.applicationName;
            if (privacyElement.enterprise) {
                charSequence = TextUtils.concat(charSequence, this.enterpriseText);
            }
            CharSequence string = getContext().getString(i2, charSequence);
            CharSequence charSequence2 = privacyElement.attributionLabel;
            CharSequence charSequence3 = privacyElement.proxyLabel;
            if (charSequence2 != null && charSequence3 != null) {
                str = getContext().getString(R.string.ongoing_privacy_dialog_attribution_proxy_label, charSequence2, charSequence3);
            } else if (charSequence2 != null) {
                str = getContext().getString(R.string.ongoing_privacy_dialog_attribution_label, charSequence2);
            } else if (charSequence3 != null) {
                str = getContext().getString(R.string.ongoing_privacy_dialog_attribution_text, charSequence3);
            }
            if (str != null) {
                string = TextUtils.concat(string, " ", str);
            }
            ((TextView) viewGroup3.requireViewById(R.id.text)).setText(string);
            if (z) {
                viewGroup3.requireViewById(R.id.chevron).setVisibility(8);
            }
            viewGroup3.setTag(privacyElement);
            if (!z) {
                viewGroup3.setOnClickListener(this.clickListener);
            }
            viewGroup.addView(viewGroup3);
        }
    }

    @Override // com.android.systemui.statusbar.phone.SystemUIDialog
    public final void stop() {
        this.dismissed.set(true);
        Iterator it = this.dismissListeners.iterator();
        while (it.hasNext()) {
            WeakReference weakReference = (WeakReference) it.next();
            it.remove();
            PrivacyDialogController$onDialogDismissed$1 privacyDialogController$onDialogDismissed$1 = (PrivacyDialogController$onDialogDismissed$1) weakReference.get();
            if (privacyDialogController$onDialogDismissed$1 != null) {
                PrivacyDialogController privacyDialogController = privacyDialogController$onDialogDismissed$1.this$0;
                privacyDialogController.privacyLogger.logPrivacyDialogDismissed();
                privacyDialogController.uiEventLogger.log(PrivacyDialogEvent.PRIVACY_DIALOG_DISMISSED);
                privacyDialogController.dialog = null;
            }
        }
    }
}

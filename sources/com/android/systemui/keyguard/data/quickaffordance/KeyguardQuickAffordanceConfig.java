package com.android.systemui.keyguard.data.quickaffordance;

import android.app.AlertDialog;
import android.content.Intent;
import com.android.systemui.animation.Expandable$Companion$fromView$1;
import com.android.systemui.common.shared.model.Icon;
import com.android.systemui.keyguard.shared.quickaffordance.ActivationState;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface KeyguardQuickAffordanceConfig {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class OnTriggeredResult {

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class Handled extends OnTriggeredResult {
            public static final Handled INSTANCE = new Handled();
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class ShowDialog extends OnTriggeredResult {
            public final AlertDialog dialog;
            public final Expandable$Companion$fromView$1 expandable;

            public ShowDialog(AlertDialog alertDialog, Expandable$Companion$fromView$1 expandable$Companion$fromView$1) {
                this.dialog = alertDialog;
                this.expandable = expandable$Companion$fromView$1;
            }

            public final boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof ShowDialog)) {
                    return false;
                }
                ShowDialog showDialog = (ShowDialog) obj;
                return this.dialog.equals(showDialog.dialog) && this.expandable.equals(showDialog.expandable);
            }

            public final int hashCode() {
                return this.expandable.hashCode() + (this.dialog.hashCode() * 31);
            }

            public final String toString() {
                return "ShowDialog(dialog=" + this.dialog + ", expandable=" + this.expandable + ")";
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class StartActivity extends OnTriggeredResult {
            public final boolean canShowWhileLocked;
            public final Intent intent;

            public StartActivity(Intent intent, boolean z) {
                this.intent = intent;
                this.canShowWhileLocked = z;
            }

            public final boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof StartActivity)) {
                    return false;
                }
                StartActivity startActivity = (StartActivity) obj;
                return Intrinsics.areEqual(this.intent, startActivity.intent) && this.canShowWhileLocked == startActivity.canShowWhileLocked;
            }

            public final int hashCode() {
                return Boolean.hashCode(this.canShowWhileLocked) + (this.intent.hashCode() * 31);
            }

            public final String toString() {
                return "StartActivity(intent=" + this.intent + ", canShowWhileLocked=" + this.canShowWhileLocked + ")";
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class PickerScreenState {

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class Default extends PickerScreenState {
            public final Intent configureIntent;

            public Default(Intent intent) {
                this.configureIntent = intent;
            }

            public final boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof Default) && Intrinsics.areEqual(this.configureIntent, ((Default) obj).configureIntent);
            }

            public final int hashCode() {
                Intent intent = this.configureIntent;
                if (intent == null) {
                    return 0;
                }
                return intent.hashCode();
            }

            public final String toString() {
                return "Default(configureIntent=" + this.configureIntent + ")";
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class Disabled extends PickerScreenState {
            public final Intent actionIntent;
            public final String actionText;
            public final String explanation;

            public Disabled(String str, String str2, Intent intent) {
                this.explanation = str;
                this.actionText = str2;
                this.actionIntent = intent;
                if (str.length() <= 0) {
                    throw new IllegalStateException("Explanation must not be empty!");
                }
                if ((str2 == null || str2.length() == 0) && intent == null) {
                    return;
                }
                if (str2 == null || str2.length() == 0 || intent == null) {
                    throw new IllegalStateException("actionText and actionIntent must either both be null/empty or both be\nnon-null and non-empty!");
                }
            }

            public final boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Disabled)) {
                    return false;
                }
                Disabled disabled = (Disabled) obj;
                return Intrinsics.areEqual(this.explanation, disabled.explanation) && Intrinsics.areEqual(this.actionText, disabled.actionText) && Intrinsics.areEqual(this.actionIntent, disabled.actionIntent);
            }

            public final int hashCode() {
                int hashCode = this.explanation.hashCode() * 31;
                String str = this.actionText;
                int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
                Intent intent = this.actionIntent;
                return hashCode2 + (intent != null ? intent.hashCode() : 0);
            }

            public final String toString() {
                return "Disabled(explanation=" + this.explanation + ", actionText=" + this.actionText + ", actionIntent=" + this.actionIntent + ")";
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class UnavailableOnDevice extends PickerScreenState {
            public static final UnavailableOnDevice INSTANCE = new UnavailableOnDevice();
        }
    }

    String getKey();

    Flow getLockScreenState();

    int getPickerIconResourceId();

    Object getPickerScreenState(Continuation continuation);

    OnTriggeredResult onTriggered(Expandable$Companion$fromView$1 expandable$Companion$fromView$1);

    String pickerName();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class LockScreenState {

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class Hidden extends LockScreenState {
            public static final Hidden INSTANCE = new Hidden();
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class Visible extends LockScreenState {
            public final ActivationState activationState;
            public final Icon icon;

            public Visible(Icon icon, ActivationState activationState) {
                this.icon = icon;
                this.activationState = activationState;
            }

            public final boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Visible)) {
                    return false;
                }
                Visible visible = (Visible) obj;
                return Intrinsics.areEqual(this.icon, visible.icon) && Intrinsics.areEqual(this.activationState, visible.activationState);
            }

            public final int hashCode() {
                return this.activationState.hashCode() + (this.icon.hashCode() * 31);
            }

            public final String toString() {
                return "Visible(icon=" + this.icon + ", activationState=" + this.activationState + ")";
            }

            public /* synthetic */ Visible(Icon icon) {
                this(icon, ActivationState.Inactive.INSTANCE$1);
            }
        }
    }
}

package com.android.systemui.user.domain.model;

import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import com.android.systemui.animation.Expandable;
import com.android.systemui.qs.user.UserSwitchDialogController;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class ShowDialogRequestModel {
    public final UserSwitchDialogController.DialogShower dialogShower;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ShowExitGuestDialog extends ShowDialogRequestModel {
        public final UserSwitchDialogController.DialogShower dialogShower;
        public final int guestUserId;
        public final boolean isGuestEphemeral;
        public final boolean isKeyguardShowing;
        public final FunctionReferenceImpl onExitGuestUser;
        public final int targetUserId;

        /* JADX WARN: Multi-variable type inference failed */
        public ShowExitGuestDialog(int i, int i2, boolean z, boolean z2, Function3 function3, UserSwitchDialogController.DialogShower dialogShower) {
            super(2, dialogShower);
            this.guestUserId = i;
            this.targetUserId = i2;
            this.isGuestEphemeral = z;
            this.isKeyguardShowing = z2;
            this.onExitGuestUser = (FunctionReferenceImpl) function3;
            this.dialogShower = dialogShower;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ShowExitGuestDialog)) {
                return false;
            }
            ShowExitGuestDialog showExitGuestDialog = (ShowExitGuestDialog) obj;
            return this.guestUserId == showExitGuestDialog.guestUserId && this.targetUserId == showExitGuestDialog.targetUserId && this.isGuestEphemeral == showExitGuestDialog.isGuestEphemeral && this.isKeyguardShowing == showExitGuestDialog.isKeyguardShowing && this.onExitGuestUser.equals(showExitGuestDialog.onExitGuestUser) && Intrinsics.areEqual(this.dialogShower, showExitGuestDialog.dialogShower);
        }

        @Override // com.android.systemui.user.domain.model.ShowDialogRequestModel
        public final UserSwitchDialogController.DialogShower getDialogShower() {
            return this.dialogShower;
        }

        public final int hashCode() {
            int hashCode = (this.onExitGuestUser.hashCode() + TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.targetUserId, Integer.hashCode(this.guestUserId) * 31, 31), 31, this.isGuestEphemeral), 31, this.isKeyguardShowing)) * 31;
            UserSwitchDialogController.DialogShower dialogShower = this.dialogShower;
            return hashCode + (dialogShower == null ? 0 : dialogShower.hashCode());
        }

        public final String toString() {
            return "ShowExitGuestDialog(guestUserId=" + this.guestUserId + ", targetUserId=" + this.targetUserId + ", isGuestEphemeral=" + this.isGuestEphemeral + ", isKeyguardShowing=" + this.isKeyguardShowing + ", onExitGuestUser=" + this.onExitGuestUser + ", dialogShower=" + this.dialogShower + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ShowUserCreationDialog extends ShowDialogRequestModel {
        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ShowUserCreationDialog)) {
                return false;
            }
            ((ShowUserCreationDialog) obj).getClass();
            return true;
        }

        public final int hashCode() {
            return Boolean.hashCode(true);
        }

        public final String toString() {
            return "ShowUserCreationDialog(isGuest=true)";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ShowUserSwitcherDialog extends ShowDialogRequestModel {
        public final Expandable expandable;

        public ShowUserSwitcherDialog(Expandable expandable) {
            super(3, null);
            this.expandable = expandable;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof ShowUserSwitcherDialog) && Intrinsics.areEqual(this.expandable, ((ShowUserSwitcherDialog) obj).expandable);
        }

        @Override // com.android.systemui.user.domain.model.ShowDialogRequestModel
        public final Expandable getExpandable() {
            return this.expandable;
        }

        public final int hashCode() {
            Expandable expandable = this.expandable;
            if (expandable == null) {
                return 0;
            }
            return expandable.hashCode();
        }

        public final String toString() {
            return "ShowUserSwitcherDialog(expandable=" + this.expandable + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ShowUserSwitcherFullscreenDialog extends ShowDialogRequestModel {
        public final Expandable expandable;

        public ShowUserSwitcherFullscreenDialog(Expandable expandable) {
            super(3, null);
            this.expandable = expandable;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof ShowUserSwitcherFullscreenDialog) && Intrinsics.areEqual(this.expandable, ((ShowUserSwitcherFullscreenDialog) obj).expandable);
        }

        @Override // com.android.systemui.user.domain.model.ShowDialogRequestModel
        public final Expandable getExpandable() {
            return this.expandable;
        }

        public final int hashCode() {
            Expandable expandable = this.expandable;
            if (expandable == null) {
                return 0;
            }
            return expandable.hashCode();
        }

        public final String toString() {
            return "ShowUserSwitcherFullscreenDialog(expandable=" + this.expandable + ")";
        }
    }

    public ShowDialogRequestModel(int i, UserSwitchDialogController.DialogShower dialogShower) {
        this.dialogShower = (i & 1) != 0 ? null : dialogShower;
    }

    public UserSwitchDialogController.DialogShower getDialogShower() {
        return this.dialogShower;
    }

    public Expandable getExpandable() {
        return null;
    }
}

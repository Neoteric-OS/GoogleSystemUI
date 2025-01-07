package com.android.systemui.bouncer.domain.interactor;

import com.android.keyguard.KeyguardSecurityModel;
import com.android.systemui.authentication.shared.model.AuthenticationMethodModel;
import com.android.systemui.bouncer.shared.model.BouncerMessageModel;
import com.android.systemui.bouncer.shared.model.BouncerMessageStrings;
import com.android.systemui.bouncer.shared.model.Message;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class BouncerMessageInteractorKt {
    public static final BouncerMessageModel access$defaultMessage(KeyguardSecurityModel.SecurityMode securityMode, String str, boolean z) {
        Pair pair = BouncerMessageStrings.EmptyMessage;
        Message message = toMessage(BouncerMessageStrings.defaultMessage(toAuthModel(securityMode), z)).message;
        return new BouncerMessageModel(new Message(13, message != null ? message.messageResId : null, null), new Message(14, null, str));
    }

    public static final AuthenticationMethodModel toAuthModel(KeyguardSecurityModel.SecurityMode securityMode) {
        int ordinal = securityMode.ordinal();
        AuthenticationMethodModel.None none = AuthenticationMethodModel.None.INSTANCE;
        AuthenticationMethodModel.Sim sim = AuthenticationMethodModel.Sim.INSTANCE;
        switch (ordinal) {
            case 0:
            case 1:
                return none;
            case 2:
                return AuthenticationMethodModel.Pattern.INSTANCE;
            case 3:
                return AuthenticationMethodModel.Password.INSTANCE;
            case 4:
                return AuthenticationMethodModel.Pin.INSTANCE;
            case 5:
            case 6:
                return sim;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public static final BouncerMessageModel toMessage(Pair pair) {
        return new BouncerMessageModel(new Message(13, (Integer) pair.getFirst(), null), new Message(13, (Integer) pair.getSecond(), null));
    }
}

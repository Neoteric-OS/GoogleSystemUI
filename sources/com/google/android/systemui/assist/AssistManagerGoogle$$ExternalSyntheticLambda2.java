package com.google.android.systemui.assist;

import android.content.ComponentName;
import com.android.systemui.assist.AssistManager$UiController;
import com.google.android.systemui.assist.uihints.AssistantPresenceHandler;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class AssistManagerGoogle$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ AssistManagerGoogle$$ExternalSyntheticLambda2(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                AssistManagerGoogle assistManagerGoogle = (AssistManagerGoogle) obj;
                AssistantPresenceHandler assistantPresenceHandler = assistManagerGoogle.mAssistantPresenceHandler;
                ComponentName assistComponentForUser = assistantPresenceHandler.mAssistUtils.getAssistComponentForUser(-2);
                assistantPresenceHandler.updateAssistantPresence(assistComponentForUser != null && "com.google.android.googlequicksearchbox/com.google.android.voiceinteraction.GsaVoiceInteractionService".equals(assistComponentForUser.flattenToString()), assistantPresenceHandler.mNgaIsAssistant);
                assistManagerGoogle.mCheckAssistantStatus = false;
                break;
            default:
                ((AssistManager$UiController) obj).hide();
                break;
        }
    }
}

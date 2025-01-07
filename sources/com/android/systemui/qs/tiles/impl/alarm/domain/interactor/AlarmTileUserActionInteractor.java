package com.android.systemui.qs.tiles.impl.alarm.domain.interactor;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import com.android.systemui.animation.Expandable;
import com.android.systemui.qs.tiles.base.actions.QSTileIntentUserInputHandlerImpl;
import com.android.systemui.qs.tiles.base.interactor.QSTileInput;
import com.android.systemui.qs.tiles.base.interactor.QSTileUserActionInteractor;
import com.android.systemui.qs.tiles.impl.alarm.domain.model.AlarmTileModel;
import com.android.systemui.qs.tiles.viewmodel.QSTileUserAction;
import java.util.Iterator;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AlarmTileUserActionInteractor implements QSTileUserActionInteractor {
    public final QSTileIntentUserInputHandlerImpl inputHandler;

    public AlarmTileUserActionInteractor(QSTileIntentUserInputHandlerImpl qSTileIntentUserInputHandlerImpl) {
        this.inputHandler = qSTileIntentUserInputHandlerImpl;
    }

    @Override // com.android.systemui.qs.tiles.base.interactor.QSTileUserActionInteractor
    public final Object handleInput(QSTileInput qSTileInput, Continuation continuation) {
        Object obj;
        QSTileUserAction qSTileUserAction = qSTileInput.action;
        if (qSTileUserAction instanceof QSTileUserAction.Click) {
            Object obj2 = qSTileInput.data;
            boolean z = obj2 instanceof AlarmTileModel.NextAlarmSet;
            QSTileIntentUserInputHandlerImpl qSTileIntentUserInputHandlerImpl = this.inputHandler;
            if (z) {
                AlarmTileModel.NextAlarmSet nextAlarmSet = (AlarmTileModel.NextAlarmSet) obj2;
                if (nextAlarmSet.alarmClockInfo.getShowIntent() != null) {
                    PendingIntent showIntent = nextAlarmSet.alarmClockInfo.getShowIntent();
                    Expandable expandable = qSTileUserAction.getExpandable();
                    Intrinsics.checkNotNull(showIntent);
                    qSTileIntentUserInputHandlerImpl.getClass();
                    if (showIntent.isActivity()) {
                        qSTileIntentUserInputHandlerImpl.activityStarter.postStartActivityDismissingKeyguard(showIntent, expandable != null ? expandable.activityTransitionController(32) : null);
                    } else {
                        Intent addFlags = new Intent("android.intent.action.MAIN").addCategory("android.intent.category.LAUNCHER").setPackage(showIntent.getCreatorPackage()).addFlags(270532608);
                        Iterator it = qSTileIntentUserInputHandlerImpl.packageManager.queryIntentActivitiesAsUser(addFlags, PackageManager.ResolveInfoFlags.of(0L), qSTileIntentUserInputHandlerImpl.userHandle.getIdentifier()).iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                obj = null;
                                break;
                            }
                            obj = it.next();
                            if (((ResolveInfo) obj).activityInfo.exported) {
                                break;
                            }
                        }
                        ResolveInfo resolveInfo = (ResolveInfo) obj;
                        if (resolveInfo != null) {
                            addFlags.setPackage(null);
                            addFlags.setComponent(resolveInfo.activityInfo.getComponentName());
                            QSTileIntentUserInputHandlerImpl.handle$default(qSTileIntentUserInputHandlerImpl, expandable, addFlags);
                        }
                    }
                }
            }
            QSTileIntentUserInputHandlerImpl.handle$default(qSTileIntentUserInputHandlerImpl, qSTileUserAction.getExpandable(), new Intent("android.intent.action.SHOW_ALARMS"));
        } else if (!(qSTileUserAction instanceof QSTileUserAction.LongClick)) {
            boolean z2 = qSTileUserAction instanceof QSTileUserAction.ToggleClick;
        }
        return Unit.INSTANCE;
    }
}

package androidx.room.coroutines;

import com.android.systemui.communal.data.db.CommunalDatabase_Impl;
import com.android.systemui.communal.data.db.CommunalWidgetDao_Impl$$ExternalSyntheticLambda0;
import kotlinx.coroutines.flow.SafeFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class FlowUtil {
    public static final SafeFlow createFlow(CommunalDatabase_Impl communalDatabase_Impl, String[] strArr, CommunalWidgetDao_Impl$$ExternalSyntheticLambda0 communalWidgetDao_Impl$$ExternalSyntheticLambda0) {
        return new SafeFlow(new FlowUtil$createFlow$1(communalDatabase_Impl, false, strArr, communalWidgetDao_Impl$$ExternalSyntheticLambda0, null));
    }
}

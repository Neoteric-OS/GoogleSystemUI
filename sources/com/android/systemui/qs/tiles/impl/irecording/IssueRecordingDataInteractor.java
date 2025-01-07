package com.android.systemui.qs.tiles.impl.irecording;

import android.os.Build;
import android.os.UserHandle;
import com.android.systemui.qs.tiles.base.interactor.QSTileDataInteractor;
import com.android.systemui.recordissue.IssueRecordingState;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class IssueRecordingDataInteractor implements QSTileDataInteractor {
    public final CoroutineContext bgCoroutineContext;
    public final IssueRecordingState state;

    public IssueRecordingDataInteractor(IssueRecordingState issueRecordingState, CoroutineContext coroutineContext) {
        this.state = issueRecordingState;
        this.bgCoroutineContext = coroutineContext;
    }

    @Override // com.android.systemui.qs.tiles.base.interactor.QSTileDataInteractor
    public final Flow availability(UserHandle userHandle) {
        boolean z = Build.IS_DEBUGGABLE;
        return new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(false);
    }

    @Override // com.android.systemui.qs.tiles.base.interactor.QSTileDataInteractor
    public final Flow tileData(UserHandle userHandle, ReadonlyStateFlow readonlyStateFlow) {
        return FlowKt.flowOn(FlowKt.distinctUntilChanged(new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new IssueRecordingDataInteractor$tileData$2(this, null), FlowConflatedKt.conflatedCallbackFlow(new IssueRecordingDataInteractor$tileData$1(this, null)))), this.bgCoroutineContext);
    }
}

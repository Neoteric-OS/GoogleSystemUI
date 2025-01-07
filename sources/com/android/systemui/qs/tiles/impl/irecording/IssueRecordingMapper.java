package com.android.systemui.qs.tiles.impl.irecording;

import android.content.res.Resources;
import com.android.systemui.common.shared.model.Icon;
import com.android.systemui.qs.tiles.base.interactor.QSTileDataToStateMapper;
import com.android.systemui.qs.tiles.viewmodel.QSTileConfig;
import com.android.systemui.qs.tiles.viewmodel.QSTileState;
import com.android.wm.shell.R;
import java.util.Collections;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class IssueRecordingMapper implements QSTileDataToStateMapper {
    public final Resources resources;
    public final Resources.Theme theme;

    public IssueRecordingMapper(Resources resources, Resources.Theme theme) {
        this.resources = resources;
        this.theme = theme;
    }

    @Override // com.android.systemui.qs.tiles.base.interactor.QSTileDataToStateMapper
    public final QSTileState map(QSTileConfig qSTileConfig, Object obj) {
        final boolean z = ((IssueRecordingModel) obj).isRecording;
        return QSTileState.Companion.build(this.resources, this.theme, qSTileConfig.uiConfig, new Function1() { // from class: com.android.systemui.qs.tiles.impl.irecording.IssueRecordingMapper$map$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                QSTileState.Builder builder = (QSTileState.Builder) obj2;
                if (z) {
                    builder.activationState = QSTileState.ActivationState.ACTIVE;
                    builder.secondaryLabel = this.resources.getString(R.string.qs_record_issue_stop);
                    builder.icon = new Function0() { // from class: com.android.systemui.qs.tiles.impl.irecording.IssueRecordingMapper$map$1.1
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return new Icon.Resource(R.drawable.qs_record_issue_icon_on, null);
                        }
                    };
                } else {
                    builder.icon = new Function0() { // from class: com.android.systemui.qs.tiles.impl.irecording.IssueRecordingMapper$map$1.2
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return new Icon.Resource(R.drawable.qs_record_issue_icon_off, null);
                        }
                    };
                    builder.activationState = QSTileState.ActivationState.INACTIVE;
                    builder.secondaryLabel = this.resources.getString(R.string.qs_record_issue_start);
                }
                builder.supportedActions = Collections.singleton(QSTileState.UserAction.CLICK);
                builder.contentDescription = ((Object) builder.label) + ", " + ((Object) builder.secondaryLabel);
                return Unit.INSTANCE;
            }
        });
    }
}

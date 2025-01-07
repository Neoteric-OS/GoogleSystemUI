package com.android.systemui.education.ui.viewmodel;

import android.content.res.Resources;
import com.android.systemui.contextualeducation.GestureType;
import com.android.systemui.education.domain.interactor.KeyboardTouchpadEduInteractor;
import com.android.systemui.education.shared.model.EducationInfo;
import com.android.systemui.education.shared.model.EducationUiType;
import com.android.wm.shell.R;
import kotlin.NoWhenBranchMatchedException;
import kotlinx.coroutines.flow.FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ContextualEduViewModel {
    public final ContextualEduViewModel$special$$inlined$map$1 eduContent;
    public final Resources resources;

    public ContextualEduViewModel(Resources resources, KeyboardTouchpadEduInteractor keyboardTouchpadEduInteractor) {
        this.resources = resources;
        this.eduContent = new ContextualEduViewModel$special$$inlined$map$1(new FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1(keyboardTouchpadEduInteractor.educationTriggered), this);
    }

    public static final String access$getEduContent(ContextualEduViewModel contextualEduViewModel, EducationInfo educationInfo) {
        int i;
        contextualEduViewModel.getClass();
        EducationUiType educationUiType = educationInfo.educationUiType;
        EducationUiType educationUiType2 = EducationUiType.Notification;
        GestureType gestureType = educationInfo.gestureType;
        if (educationUiType == educationUiType2) {
            int ordinal = gestureType.ordinal();
            if (ordinal == 0) {
                i = R.string.back_edu_notification_content;
            } else if (ordinal == 1) {
                i = R.string.home_edu_notification_content;
            } else if (ordinal == 2) {
                i = R.string.overview_edu_notification_content;
            } else {
                if (ordinal != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                i = R.string.all_apps_edu_notification_content;
            }
        } else {
            int ordinal2 = gestureType.ordinal();
            if (ordinal2 == 0) {
                i = R.string.back_edu_toast_content;
            } else if (ordinal2 == 1) {
                i = R.string.home_edu_toast_content;
            } else if (ordinal2 == 2) {
                i = R.string.overview_edu_toast_content;
            } else {
                if (ordinal2 != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                i = R.string.all_apps_edu_toast_content;
            }
        }
        return contextualEduViewModel.resources.getString(i);
    }
}

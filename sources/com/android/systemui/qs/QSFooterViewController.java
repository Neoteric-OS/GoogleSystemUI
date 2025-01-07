package com.android.systemui.qs;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.qs.customize.QSCustomizerController;
import com.android.systemui.retail.domain.interactor.RetailModeInteractorImpl;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.util.ViewController;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QSFooterViewController extends ViewController {
    public final ActivityStarter mActivityStarter;
    public final TextView mBuildText;
    public final View mEditButton;
    public final FalsingManager mFalsingManager;
    public final PageIndicator mPageIndicator;
    public final QSPanelController mQsPanelController;
    public final RetailModeInteractorImpl mRetailModeInteractor;
    public final UserTracker mUserTracker;

    public QSFooterViewController(QSFooterView qSFooterView, UserTracker userTracker, FalsingManager falsingManager, ActivityStarter activityStarter, QSPanelController qSPanelController, RetailModeInteractorImpl retailModeInteractorImpl) {
        super(qSFooterView);
        this.mUserTracker = userTracker;
        this.mQsPanelController = qSPanelController;
        this.mFalsingManager = falsingManager;
        this.mActivityStarter = activityStarter;
        this.mRetailModeInteractor = retailModeInteractorImpl;
        this.mBuildText = (TextView) qSFooterView.findViewById(R.id.build);
        this.mPageIndicator = (PageIndicator) qSFooterView.findViewById(R.id.footer_page_indicator);
        this.mEditButton = qSFooterView.findViewById(android.R.id.edit);
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewAttached() {
        this.mBuildText.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.android.systemui.qs.QSFooterViewController$$ExternalSyntheticLambda0
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                QSFooterViewController qSFooterViewController = QSFooterViewController.this;
                CharSequence text = qSFooterViewController.mBuildText.getText();
                if (TextUtils.isEmpty(text)) {
                    return false;
                }
                ((ClipboardManager) ((UserTrackerImpl) qSFooterViewController.mUserTracker).getUserContext().getSystemService(ClipboardManager.class)).setPrimaryClip(ClipData.newPlainText(qSFooterViewController.mView.getResources().getString(R.string.build_number_clip_data_label), text));
                Toast.makeText(qSFooterViewController.mView.getContext(), R.string.build_number_copy_toast, 0).show();
                return true;
            }
        });
        this.mEditButton.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.qs.QSFooterViewController$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(final View view) {
                final QSFooterViewController qSFooterViewController = QSFooterViewController.this;
                if (qSFooterViewController.mFalsingManager.isFalseTap(1)) {
                    return;
                }
                qSFooterViewController.mActivityStarter.postQSRunnableDismissingKeyguard(new Runnable() { // from class: com.android.systemui.qs.QSFooterViewController$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        QSFooterViewController qSFooterViewController2 = QSFooterViewController.this;
                        final View view2 = view;
                        final QSPanelController qSPanelController = qSFooterViewController2.mQsPanelController;
                        qSPanelController.getClass();
                        view2.post(new Runnable() { // from class: com.android.systemui.qs.QSPanelController$$ExternalSyntheticLambda0
                            @Override // java.lang.Runnable
                            public final void run() {
                                QSPanelController qSPanelController2 = QSPanelController.this;
                                View view3 = view2;
                                QSCustomizerController qSCustomizerController = qSPanelController2.mQsCustomizerController;
                                if (qSCustomizerController.isCustomizing()) {
                                    return;
                                }
                                int[] locationOnScreen = view3.getLocationOnScreen();
                                qSCustomizerController.show((view3.getWidth() / 2) + locationOnScreen[0], (view3.getHeight() / 2) + locationOnScreen[1], false);
                            }
                        });
                    }
                });
            }
        });
        QSPanel qSPanel = (QSPanel) this.mQsPanelController.mView;
        if (qSPanel.mTileLayout instanceof PagedTileLayout) {
            qSPanel.mFooterPageIndicator = this.mPageIndicator;
            qSPanel.updatePageIndicator();
        }
        QSFooterView qSFooterView = (QSFooterView) this.mView;
        qSFooterView.getClass();
        qSFooterView.post(new QSFooterView$$ExternalSyntheticLambda0(qSFooterView));
    }

    public final void setVisibility(int i) {
        ((QSFooterView) this.mView).setVisibility(i);
        this.mEditButton.setVisibility(this.mRetailModeInteractor.repository.getInRetailMode() ? 8 : 0);
        this.mEditButton.setClickable(i == 0);
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewDetached() {
    }
}

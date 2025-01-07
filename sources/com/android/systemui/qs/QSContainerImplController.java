package com.android.systemui.qs;

import android.content.res.Configuration;
import android.view.MotionEvent;
import android.view.View;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.util.ViewController;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QSContainerImplController extends ViewController {
    public final ConfigurationController mConfigurationController;
    public final AnonymousClass1 mConfigurationListener;
    public final AnonymousClass2 mContainerTouchHandler;
    public final FalsingManager mFalsingManager;
    public final NonInterceptingScrollView mQSPanelContainer;
    public final QSPanelController mQsPanelController;
    public final QuickStatusBarHeaderController mQuickStatusBarHeaderController;

    /* JADX WARN: Type inference failed for: r0v0, types: [com.android.systemui.qs.QSContainerImplController$1] */
    /* JADX WARN: Type inference failed for: r0v1, types: [com.android.systemui.qs.QSContainerImplController$2] */
    public QSContainerImplController(QSContainerImpl qSContainerImpl, QSPanelController qSPanelController, QuickStatusBarHeaderController quickStatusBarHeaderController, ConfigurationController configurationController, FalsingManager falsingManager) {
        super(qSContainerImpl);
        this.mConfigurationListener = new ConfigurationController.ConfigurationListener() { // from class: com.android.systemui.qs.QSContainerImplController.1
            @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
            public final void onConfigChanged(Configuration configuration) {
                QSContainerImplController qSContainerImplController = QSContainerImplController.this;
                ((QSContainerImpl) qSContainerImplController.mView).updateResources(qSContainerImplController.mQsPanelController, qSContainerImplController.mQuickStatusBarHeaderController);
            }
        };
        this.mContainerTouchHandler = new View.OnTouchListener() { // from class: com.android.systemui.qs.QSContainerImplController.2
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getActionMasked() != 1) {
                    return false;
                }
                QSContainerImplController qSContainerImplController = QSContainerImplController.this;
                if (!qSContainerImplController.mQSPanelContainer.mPreventingIntercept) {
                    return false;
                }
                qSContainerImplController.mFalsingManager.isFalseTouch(17);
                return false;
            }
        };
        this.mQsPanelController = qSPanelController;
        this.mQuickStatusBarHeaderController = quickStatusBarHeaderController;
        this.mConfigurationController = configurationController;
        this.mFalsingManager = falsingManager;
        this.mQSPanelContainer = qSContainerImpl.mQSPanelContainer;
    }

    @Override // com.android.systemui.util.ViewController
    public final void onInit() {
        this.mQuickStatusBarHeaderController.init$9();
        ((QSContainerImpl) this.mView).getClass();
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewAttached() {
        ((QSContainerImpl) this.mView).updateResources(this.mQsPanelController, this.mQuickStatusBarHeaderController);
        ((ConfigurationControllerImpl) this.mConfigurationController).addCallback(this.mConfigurationListener);
        NonInterceptingScrollView nonInterceptingScrollView = this.mQSPanelContainer;
        if (nonInterceptingScrollView != null) {
            nonInterceptingScrollView.setOnTouchListener(this.mContainerTouchHandler);
        }
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewDetached() {
        ((ConfigurationControllerImpl) this.mConfigurationController).removeCallback(this.mConfigurationListener);
        NonInterceptingScrollView nonInterceptingScrollView = this.mQSPanelContainer;
        if (nonInterceptingScrollView != null) {
            nonInterceptingScrollView.setOnTouchListener(null);
        }
    }
}

package com.android.wm.shell.pip.phone;

import android.content.res.Resources;
import android.graphics.Rect;
import android.provider.DeviceConfig;
import android.view.ViewConfiguration;
import com.android.wm.shell.R;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.common.pip.PipBoundsState;
import com.android.wm.shell.common.pip.PipUiEventLogger;
import com.android.wm.shell.shared.animation.PhysicsAnimator;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class PipTouchHandler$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ PipTouchHandler f$0;

    public /* synthetic */ PipTouchHandler$$ExternalSyntheticLambda0(PipTouchHandler pipTouchHandler, int i) {
        this.$r8$classId = i;
        this.f$0 = pipTouchHandler;
    }

    @Override // java.lang.Runnable
    public final void run() {
        final int i = 0;
        final int i2 = 1;
        int i3 = this.$r8$classId;
        final PipTouchHandler pipTouchHandler = this.f$0;
        switch (i3) {
            case 0:
                PipBoundsState pipBoundsState = pipTouchHandler.mPipBoundsState;
                if (!pipBoundsState.isStashed()) {
                    Rect bounds = pipBoundsState.getBounds();
                    boolean willResizeMenu = pipTouchHandler.willResizeMenu();
                    PhonePipMenuController phonePipMenuController = pipTouchHandler.mMenuController;
                    if (willResizeMenu && phonePipMenuController.isMenuVisible()) {
                        PipMenuView pipMenuView = phonePipMenuController.mPipMenuView;
                        pipMenuView.mMenuContainer.setAlpha(0.0f);
                        pipMenuView.mSettingsButton.setAlpha(0.0f);
                        pipMenuView.mDismissButton.setAlpha(0.0f);
                    }
                    phonePipMenuController.showMenuInternal(1, bounds, true, willResizeMenu, willResizeMenu);
                    break;
                } else {
                    pipTouchHandler.animateToUnStashedState();
                    pipTouchHandler.mPipUiEventLogger.log(PipUiEventLogger.PipUiEventEnum.PICTURE_IN_PICTURE_STASH_UNSTASHED);
                    pipBoundsState.setStashed(0);
                    break;
                }
                break;
            case 1:
                pipTouchHandler.updateMovementBounds();
                break;
            case 2:
                pipTouchHandler.animateToUnStashedState();
                break;
            default:
                pipTouchHandler.mEnableResize = pipTouchHandler.mContext.getResources().getBoolean(R.bool.config_pipEnableResizeForMenu);
                Resources resources = pipTouchHandler.mContext.getResources();
                pipTouchHandler.mBottomOffsetBufferPx = resources.getDimensionPixelSize(R.dimen.pip_bottom_offset_buffer);
                pipTouchHandler.mImeOffset = resources.getDimensionPixelSize(R.dimen.pip_ime_offset);
                PipDismissTargetHandler pipDismissTargetHandler = pipTouchHandler.mPipDismissTargetHandler;
                pipDismissTargetHandler.updateMagneticTargetSize();
                PipMotionHelper pipMotionHelper = pipTouchHandler.mMotionHelper;
                Rect rect = pipMotionHelper.mPipBoundsState.mMotionBoundsState.mBoundsInMotion;
                Function2 function2 = PhysicsAnimator.onAnimatorCreated;
                pipMotionHelper.mTemporaryBoundsPhysicsAnimator = PhysicsAnimator.Companion.getInstance(rect);
                PipResizeGestureHandler pipResizeGestureHandler = pipTouchHandler.mPipResizeGestureHandler;
                pipResizeGestureHandler.mContext.getDisplay().getRealSize(pipResizeGestureHandler.mMaxSize);
                pipResizeGestureHandler.mTouchSlop = ViewConfiguration.get(pipResizeGestureHandler.mContext).getScaledTouchSlop();
                pipResizeGestureHandler.mEnablePinchResize = pipResizeGestureHandler.mContext.getResources().getBoolean(R.bool.config_pipEnablePinchResize);
                pipDismissTargetHandler.init();
                pipTouchHandler.mEnableStash = DeviceConfig.getBoolean("systemui", "pip_stashing", true);
                DeviceConfig.OnPropertiesChangedListener onPropertiesChangedListener = new DeviceConfig.OnPropertiesChangedListener() { // from class: com.android.wm.shell.pip.phone.PipTouchHandler$$ExternalSyntheticLambda7
                    public final void onPropertiesChanged(DeviceConfig.Properties properties) {
                        int i4 = i;
                        PipTouchHandler pipTouchHandler2 = pipTouchHandler;
                        pipTouchHandler2.getClass();
                        switch (i4) {
                            case 0:
                                if (properties.getKeyset().contains("pip_stashing")) {
                                    pipTouchHandler2.mEnableStash = properties.getBoolean("pip_stashing", true);
                                    break;
                                }
                                break;
                            default:
                                if (properties.getKeyset().contains("pip_velocity_threshold")) {
                                    pipTouchHandler2.mStashVelocityThreshold = properties.getFloat("pip_velocity_threshold", 18000.0f);
                                    break;
                                }
                                break;
                        }
                    }
                };
                ShellExecutor shellExecutor = pipTouchHandler.mMainExecutor;
                DeviceConfig.addOnPropertiesChangedListener("systemui", shellExecutor, onPropertiesChangedListener);
                pipTouchHandler.mStashVelocityThreshold = DeviceConfig.getFloat("systemui", "pip_velocity_threshold", 18000.0f);
                DeviceConfig.addOnPropertiesChangedListener("systemui", shellExecutor, new DeviceConfig.OnPropertiesChangedListener() { // from class: com.android.wm.shell.pip.phone.PipTouchHandler$$ExternalSyntheticLambda7
                    public final void onPropertiesChanged(DeviceConfig.Properties properties) {
                        int i4 = i2;
                        PipTouchHandler pipTouchHandler2 = pipTouchHandler;
                        pipTouchHandler2.getClass();
                        switch (i4) {
                            case 0:
                                if (properties.getKeyset().contains("pip_stashing")) {
                                    pipTouchHandler2.mEnableStash = properties.getBoolean("pip_stashing", true);
                                    break;
                                }
                                break;
                            default:
                                if (properties.getKeyset().contains("pip_velocity_threshold")) {
                                    pipTouchHandler2.mStashVelocityThreshold = properties.getFloat("pip_velocity_threshold", 18000.0f);
                                    break;
                                }
                                break;
                        }
                    }
                });
                break;
        }
    }
}

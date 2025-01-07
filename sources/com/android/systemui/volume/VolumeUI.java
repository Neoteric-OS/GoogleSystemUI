package com.android.systemui.volume;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;
import com.android.settingslib.volume.data.repository.AudioRepository;
import com.android.settingslib.volume.data.repository.AudioRepositoryImpl;
import com.android.systemui.CoreStartable;
import com.android.systemui.Prefs;
import com.android.systemui.qs.tiles.DndTile;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.volume.domain.interactor.AudioSharingInteractor;
import com.android.wm.shell.R;
import java.io.PrintWriter;
import kotlinx.coroutines.BuildersKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class VolumeUI implements CoreStartable, ConfigurationController.ConfigurationListener {
    public static final boolean LOGD = Log.isLoggable("VolumeUI", 3);
    public final AudioRepository mAudioRepository;
    public final Context mContext;
    public boolean mEnabled;
    public final VolumeDialogComponent mVolumeComponent;

    public VolumeUI(Context context, VolumeDialogComponent volumeDialogComponent, AudioRepository audioRepository, AudioSharingInteractor audioSharingInteractor) {
        this.mContext = context;
        this.mVolumeComponent = volumeDialogComponent;
        this.mAudioRepository = audioRepository;
    }

    @Override // com.android.systemui.CoreStartable, com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.print("mEnabled=");
        printWriter.println(this.mEnabled);
        if (this.mEnabled) {
            this.mVolumeComponent.getClass();
        }
    }

    @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
    public final void onConfigChanged(Configuration configuration) {
        if (this.mEnabled) {
            VolumeDialogComponent volumeDialogComponent = this.mVolumeComponent;
            if (volumeDialogComponent.mConfigChanges.applyNewConfig(volumeDialogComponent.mContext.getResources())) {
                volumeDialogComponent.mController.mCallbacks.onConfigurationChanged();
            }
        }
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        AudioRepositoryImpl audioRepositoryImpl = (AudioRepositoryImpl) this.mAudioRepository;
        audioRepositoryImpl.getClass();
        try {
            audioRepositoryImpl.audioManager.setVolumeController(audioRepositoryImpl.volumeController);
        } catch (SecurityException e) {
            Log.wtf("AudioManager", "Unable to set the volume controller", e);
        }
        boolean z = this.mContext.getResources().getBoolean(R.bool.enable_volume_ui);
        boolean z2 = this.mContext.getResources().getBoolean(R.bool.enable_safety_warning);
        boolean z3 = z || z2;
        this.mEnabled = z3;
        if (z3) {
            VolumeDialogComponent volumeDialogComponent = this.mVolumeComponent;
            VolumeDialogControllerImpl volumeDialogControllerImpl = volumeDialogComponent.mController;
            volumeDialogControllerImpl.mShowVolumeDialog = z;
            volumeDialogControllerImpl.mShowSafetyWarning = z2;
            Context context = this.mContext;
            Intent intent = DndTile.ZEN_SETTINGS;
            Prefs.putBoolean(context, "DndTileVisible", true);
            if (LOGD) {
                Log.d("VolumeUI", "Registering default volume controller");
            }
            VolumeDialogControllerImpl volumeDialogControllerImpl2 = volumeDialogComponent.mController;
            VolumeControllerAdapter volumeControllerAdapter = volumeDialogControllerImpl2.mVolumeControllerAdapter;
            volumeControllerAdapter.getClass();
            BuildersKt.launch$default(volumeControllerAdapter.coroutineScope, null, null, new VolumeControllerAdapter$collectToController$1(volumeControllerAdapter, volumeDialogControllerImpl2.mVolumeController, null), 3);
            volumeDialogControllerImpl2.setVolumePolicy(volumeDialogControllerImpl2.mVolumePolicy);
            if (D.BUG) {
                Log.d(VolumeDialogControllerImpl.TAG, "showDndTile");
            }
            Prefs.putBoolean(volumeDialogControllerImpl2.mContext, "DndTileVisible", true);
            try {
                volumeDialogControllerImpl2.mMediaSessions.init();
            } catch (SecurityException e2) {
                Log.w(VolumeDialogControllerImpl.TAG, "No access to media sessions", e2);
            }
            Prefs.putBoolean(volumeDialogComponent.mContext, "DndTileCombinedIcon", true);
        }
    }
}

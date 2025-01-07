package androidx.profileinstaller;

import android.content.res.AssetManager;
import androidx.profileinstaller.ProfileInstaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DeviceProfileWriter {
    public final String mApkName;
    public final AssetManager mAssetManager;
    public final File mCurProfile;
    public boolean mDeviceSupportsAotProfile = false;
    public final ProfileInstaller.DiagnosticsCallback mDiagnostics;
    public final Executor mExecutor;
    public DexProfileData[] mProfile;
    public byte[] mTranscodedProfile;

    public DeviceProfileWriter(AssetManager assetManager, Executor executor, ProfileInstaller.DiagnosticsCallback diagnosticsCallback, String str, File file) {
        this.mAssetManager = assetManager;
        this.mExecutor = executor;
        this.mDiagnostics = diagnosticsCallback;
        this.mApkName = str;
        this.mCurProfile = file;
    }

    public final InputStream openStreamFromAssets(AssetManager assetManager, String str) {
        try {
            return assetManager.openFd(str).createInputStream();
        } catch (FileNotFoundException e) {
            String message = e.getMessage();
            if (message != null && message.contains("compressed")) {
                this.mDiagnostics.onDiagnosticReceived();
            }
            return null;
        }
    }

    public final void result(final int i, final Object obj) {
        this.mExecutor.execute(new Runnable() { // from class: androidx.profileinstaller.DeviceProfileWriter$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                DeviceProfileWriter deviceProfileWriter = DeviceProfileWriter.this;
                deviceProfileWriter.mDiagnostics.onResultReceived(i, obj);
            }
        });
    }
}

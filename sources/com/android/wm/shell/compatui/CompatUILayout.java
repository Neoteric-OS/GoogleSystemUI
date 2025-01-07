package com.android.wm.shell.compatui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.SurfaceControlViewHost;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class CompatUILayout extends LinearLayout {
    public static final /* synthetic */ int $r8$clinit = 0;
    public CompatUIWindowManager mWindowManager;

    public CompatUILayout(Context context) {
        this(context, null);
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        ImageButton imageButton = (ImageButton) findViewById(R.id.size_compat_restart_button);
        final int i = 0;
        imageButton.setOnClickListener(new View.OnClickListener(this) { // from class: com.android.wm.shell.compatui.CompatUILayout$$ExternalSyntheticLambda0
            public final /* synthetic */ CompatUILayout f$0;

            {
                this.f$0 = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i2 = i;
                CompatUILayout compatUILayout = this.f$0;
                switch (i2) {
                    case 0:
                        CompatUIWindowManager compatUIWindowManager = compatUILayout.mWindowManager;
                        compatUIWindowManager.mOnRestartButtonClicked.accept(Pair.create(compatUIWindowManager.mTaskInfo, compatUIWindowManager.mTaskListener));
                        break;
                    default:
                        int i3 = CompatUILayout.$r8$clinit;
                        compatUILayout.setViewVisibility(R.id.size_compat_hint, false);
                        break;
                }
            }
        });
        imageButton.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.android.wm.shell.compatui.CompatUILayout$$ExternalSyntheticLambda1
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                CompatUILayout compatUILayout = CompatUILayout.this.mWindowManager.mLayout;
                if (compatUILayout != null) {
                    compatUILayout.setViewVisibility(R.id.size_compat_hint, true);
                }
                return true;
            }
        });
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.size_compat_hint);
        ((TextView) linearLayout.findViewById(R.id.compat_mode_hint_text)).setText(R.string.restart_button_description);
        final int i2 = 1;
        linearLayout.setOnClickListener(new View.OnClickListener(this) { // from class: com.android.wm.shell.compatui.CompatUILayout$$ExternalSyntheticLambda0
            public final /* synthetic */ CompatUILayout f$0;

            {
                this.f$0 = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i22 = i2;
                CompatUILayout compatUILayout = this.f$0;
                switch (i22) {
                    case 0:
                        CompatUIWindowManager compatUIWindowManager = compatUILayout.mWindowManager;
                        compatUIWindowManager.mOnRestartButtonClicked.accept(Pair.create(compatUIWindowManager.mTaskInfo, compatUIWindowManager.mTaskListener));
                        break;
                    default:
                        int i3 = CompatUILayout.$r8$clinit;
                        compatUILayout.setViewVisibility(R.id.size_compat_hint, false);
                        break;
                }
            }
        });
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        CompatUIWindowManager compatUIWindowManager = this.mWindowManager;
        WindowManager.LayoutParams windowLayoutParams = compatUIWindowManager.getWindowLayoutParams();
        SurfaceControlViewHost surfaceControlViewHost = compatUIWindowManager.mViewHost;
        if (surfaceControlViewHost == null) {
            return;
        }
        surfaceControlViewHost.relayout(windowLayoutParams);
        compatUIWindowManager.updateSurfacePosition();
    }

    public final void setViewVisibility(int i, boolean z) {
        View findViewById = findViewById(i);
        int i2 = z ? 0 : 8;
        if (findViewById.getVisibility() == i2) {
            return;
        }
        findViewById.setVisibility(i2);
    }

    public CompatUILayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CompatUILayout(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public CompatUILayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }
}

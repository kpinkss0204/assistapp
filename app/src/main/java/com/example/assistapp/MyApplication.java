package com.example.assistapp;

import android.app.Application;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.kakao.vectormap.KakaoMapSdk;
import kotlin.Metadata;

/* JADX INFO: compiled from: MyApplication.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Lcom/example/assistapp/MyApplication;", "Landroid/app/Application;", "<init>", "()V", "onCreate", "", "app_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class MyApplication extends Application {
    public static final int $stable = 0;

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        KakaoMapSdk.init(this, "18ce207a640e4a1f61001296ba153fde");
    }
}

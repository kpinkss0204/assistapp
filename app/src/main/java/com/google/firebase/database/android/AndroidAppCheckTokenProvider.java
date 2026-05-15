package com.google.firebase.database.android;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.appcheck.AppCheckTokenResult;
import com.google.firebase.appcheck.interop.AppCheckTokenListener;
import com.google.firebase.appcheck.interop.InteropAppCheckTokenProvider;
import com.google.firebase.database.core.TokenProvider;
import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes3.dex */
public class AndroidAppCheckTokenProvider implements TokenProvider {
    private final Deferred<InteropAppCheckTokenProvider> deferredAppCheckProvider;
    private final AtomicReference<InteropAppCheckTokenProvider> internalAppCheck = new AtomicReference<>();

    @Override // com.google.firebase.database.core.TokenProvider
    public void removeTokenChangeListener(TokenProvider.TokenChangeListener tokenChangeListener) {
    }

    public AndroidAppCheckTokenProvider(Deferred<InteropAppCheckTokenProvider> deferred) {
        this.deferredAppCheckProvider = deferred;
        deferred.whenAvailable(new Deferred.DeferredHandler() { // from class: com.google.firebase.database.android.AndroidAppCheckTokenProvider$$ExternalSyntheticLambda1
            @Override // com.google.firebase.inject.Deferred.DeferredHandler
            public final void handle(Provider provider) {
                this.f$0.m7695x13f0d4b1(provider);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$new$0$com-google-firebase-database-android-AndroidAppCheckTokenProvider, reason: not valid java name */
    /* synthetic */ void m7695x13f0d4b1(Provider provider) {
        this.internalAppCheck.set((InteropAppCheckTokenProvider) provider.get());
    }

    @Override // com.google.firebase.database.core.TokenProvider
    public void getToken(boolean z, final TokenProvider.GetTokenCompletionListener getTokenCompletionListener) {
        InteropAppCheckTokenProvider interopAppCheckTokenProvider = this.internalAppCheck.get();
        if (interopAppCheckTokenProvider != null) {
            interopAppCheckTokenProvider.getToken(z).addOnSuccessListener(new OnSuccessListener() { // from class: com.google.firebase.database.android.AndroidAppCheckTokenProvider$$ExternalSyntheticLambda4
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    getTokenCompletionListener.onSuccess(((AppCheckTokenResult) obj).getToken());
                }
            }).addOnFailureListener(new OnFailureListener() { // from class: com.google.firebase.database.android.AndroidAppCheckTokenProvider$$ExternalSyntheticLambda5
                @Override // com.google.android.gms.tasks.OnFailureListener
                public final void onFailure(Exception exc) {
                    getTokenCompletionListener.onError(exc.getMessage());
                }
            });
        } else {
            getTokenCompletionListener.onSuccess(null);
        }
    }

    @Override // com.google.firebase.database.core.TokenProvider
    public void addTokenChangeListener(final ExecutorService executorService, final TokenProvider.TokenChangeListener tokenChangeListener) {
        this.deferredAppCheckProvider.whenAvailable(new Deferred.DeferredHandler() { // from class: com.google.firebase.database.android.AndroidAppCheckTokenProvider$$ExternalSyntheticLambda3
            @Override // com.google.firebase.inject.Deferred.DeferredHandler
            public final void handle(Provider provider) {
                ((InteropAppCheckTokenProvider) provider.get()).addAppCheckTokenListener(new AppCheckTokenListener() { // from class: com.google.firebase.database.android.AndroidAppCheckTokenProvider$$ExternalSyntheticLambda2
                    @Override // com.google.firebase.appcheck.interop.AppCheckTokenListener
                    public final void onAppCheckTokenChanged(AppCheckTokenResult appCheckTokenResult) {
                        executorService.execute(new Runnable() { // from class: com.google.firebase.database.android.AndroidAppCheckTokenProvider$$ExternalSyntheticLambda0
                            @Override // java.lang.Runnable
                            public final void run() {
                                tokenChangeListener.onTokenChange(appCheckTokenResult.getToken());
                            }
                        });
                    }
                });
            }
        });
    }
}

package com.hn.insave.admob;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import androidx.annotation.NonNull;

import com.google.android.ads.nativetemplates.NativeTemplateStyle;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.nativead.NativeAd;


public class AdMobUtil {

    private Context context;
    private InterstitialAd mInterstitialAd;
    private OnInterstitialAdListener mInterstitialAdListener;
    private OnRewardedAdListener mRewardedAdListener;
    private boolean isInterstitialAdLoaded = true;
    private boolean isRewardedAdLoaded = true;

    public AdMobUtil(Context context, String appId) {
        this.context = context;
        MobileAds.initialize(context, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {
            }
        });
    }

    public void loadBannerAd(ViewGroup container, AdSize adSize, String bannerId) {
        AdView mAdView = new AdView(context);
        mAdView.setAdSize(adSize);
        mAdView.setAdUnitId(bannerId);
        container.addView(mAdView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    public void loadNativeAd(String nativeId, OnNativeAdListener listener) {
        AdLoader loader = new AdLoader.Builder(context, nativeId)
                .forNativeAd( new NativeAd.OnNativeAdLoadedListener() {
            @Override
            public void onNativeAdLoaded(@NonNull NativeAd nativeAd) {
                TemplateView templateView = listener.onTemplateView();
                templateView.setNativeAd(nativeAd);
            }
        }).build();

        loader.loadAd(new AdRequest.Builder().build());
    }

    public void showInterstitialAd(Activity context, OnInterstitialAdListener listener) {
        mInterstitialAdListener = listener;
        if (mInterstitialAd!= null) {
            isInterstitialAdLoaded = true;
            mInterstitialAd.show(context);
        } else {
            isInterstitialAdLoaded = false;
        }
    }

    public void initInterstitialAd(String interstitialId) {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(context, interstitialId, adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                super.onAdLoaded(interstitialAd);
                mInterstitialAd = interstitialAd;
                mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                        super.onAdFailedToShowFullScreenContent(adError);
                        if (mInterstitialAdListener != null)
                            mInterstitialAdListener.onAdListener(isInterstitialAdLoaded, Interstitial.ON_AD_FAILED_TO_LOAD);
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                        super.onAdShowedFullScreenContent();
                        mInterstitialAd = null;
                        if (mInterstitialAdListener != null)
                            mInterstitialAdListener.onAdListener(isInterstitialAdLoaded, Interstitial.ON_AD_LOADED);
                    }

                    @Override
                    public void onAdDismissedFullScreenContent() {
                        super.onAdDismissedFullScreenContent();
                        if (mInterstitialAdListener != null)
                            mInterstitialAdListener.onAdListener(isInterstitialAdLoaded, Interstitial.ON_AD_CLOSED);
                    }

                    @Override
                    public void onAdImpression() {
                        super.onAdImpression();
                        if (mInterstitialAdListener != null)
                            mInterstitialAdListener.onAdListener(isInterstitialAdLoaded, Interstitial.ON_AD_OPENED);
                    }

                    @Override
                    public void onAdClicked() {
                        super.onAdClicked();
                        if (mInterstitialAdListener != null)
                            mInterstitialAdListener.onAdListener(isInterstitialAdLoaded, Interstitial.ON_AD_CLICKED);
                    }
                });
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                mInterstitialAd = null;
                if (mInterstitialAdListener != null)
                    mInterstitialAdListener.onAdListener(isInterstitialAdLoaded, Interstitial.ON_AD_FAILED_TO_LOAD);
            }
        });


    }

}

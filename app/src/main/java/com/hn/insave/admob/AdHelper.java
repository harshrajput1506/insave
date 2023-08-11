package com.hn.insave.admob;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdSize;
import com.hn.insave.utils.Config;
import com.hn.insave.utils.Constants;
import com.hn.insave.utils.PrefUtil;

public class AdHelper {

    private static AdMobUtil admobUtil;
    private static AdUnitHelper adUnitHelper;

    public static AdUnitHelper initAdMob(Context context, String appId) {
        admobUtil = new AdMobUtil(context, appId);
        return new AdUnitHelper();
    }

    public void loadAds(AdUnitHelper adUnits) {
        adUnitHelper = adUnits;
        if (adUnitHelper.getInterstitialId() != null)
            admobUtil.initInterstitialAd(adUnits.getInterstitialId());
    }

    public static void loadBannerAd(ViewGroup container, AdSize adSize) {
        if (adUnitHelper.getBannerId() != null) {
            admobUtil.loadBannerAd(container, adSize, adUnitHelper.getBannerId());
        }
    }

    public static void loadNativeAd(OnNativeAdListener listener) {
        if (adUnitHelper.getNativeId() != null) {
            admobUtil.loadNativeAd(adUnitHelper.getNativeId(), listener);
        }
    }

    public static void showInterstitialAd(Activity context) {
        int totalClicks = PrefUtil.getInt(Constants.TOTAL_NO_CLICKS, Config.DEFAULT_TOTAL_NO_CLICKS);
        int currentClicks = PrefUtil.getInt(Constants.CURRENT_CLICKS, 0);
        if (currentClicks >= totalClicks || getMinutes(System.currentTimeMillis(), PrefUtil.getLong(Constants.LAST_AD_TIMESTAMP)) > 60) {
            admobUtil.showInterstitialAd(context, (isLoaded, interstitial) -> {
                if (interstitial == Interstitial.ON_AD_CLOSED) {
                    PrefUtil.setInt(Constants.CURRENT_CLICKS, 0);
                    PrefUtil.setLong(Constants.LAST_AD_TIMESTAMP, System.currentTimeMillis());
                }
            });
        } else {
            currentClicks++;
            PrefUtil.setInt(Constants.CURRENT_CLICKS, currentClicks);
        }
    }

    public static void showInterstitialAd(OnInterstitialAdListener listener, Activity context) {
        int totalClicks = PrefUtil.getInt(Constants.TOTAL_NO_CLICKS, Config.DEFAULT_TOTAL_NO_CLICKS);
        int currentClicks = PrefUtil.getInt(Constants.CURRENT_CLICKS, 0);
        if (currentClicks >= totalClicks || getMinutes(System.currentTimeMillis(), PrefUtil.getLong(Constants.LAST_AD_TIMESTAMP)) > 60) {
            admobUtil.showInterstitialAd(context, listener);
        } else {
            currentClicks++;
            PrefUtil.setInt(Constants.CURRENT_CLICKS, currentClicks);
        }
    }


    private static int getMinutes(long milliseconds1, long milliseconds2) {
        long diff = milliseconds2 - milliseconds1;
        long diffSeconds = diff / 1000;
        long diffMinutes = diff / (60 * 1000);
        long diffHours = diff / (60 * 60 * 1000);
        long diffDays = diff / (24 * 60 * 60 * 1000);
        return (int) diffMinutes;
    }
}

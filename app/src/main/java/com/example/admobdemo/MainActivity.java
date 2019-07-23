package com.example.admobdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

public class MainActivity extends AppCompatActivity implements RewardedVideoAdListener {
    private AdView mAdView;
    private InterstitialAd interstitialAd;
    Button interstitialAdButton, rewardedAdButton;
    private RewardedAd rewardedAd;
    private RewardedVideoAd rewardedVideoAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        interstitialAdButton =  findViewById(R.id.interstitialbutton);
        rewardedAdButton = findViewById(R.id.rewardedbutton);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        // Banner AdMob  Code :
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        // Interstitial AdMob  Code :
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        interstitialAd.loadAd(new AdRequest.Builder().build());

        interstitialAd.setAdListener(new AdListener() {
                                         public void onAdLoaded() {
                                             // Code to be executed when an ad finishes loading.

                                             interstitialAd.loadAd(new AdRequest.Builder().build());
                                         }
                                     });

        interstitialAdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(interstitialAd.isLoaded())
                {
                    interstitialAd.show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Interstitial Ad isn't loaded.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //// NATIVE ADMOB CODE :
        //
        //        final AdLoader adLoader = new AdLoader.Builder(MainActivity.this, "ca-app-pub-3940256099942544/2247696110")
        //                .forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
        //                    @Override
        //                    public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
        //                                            }
        //                })
        //                .withAdListener(new AdListener() {
        //                    @Override
        //                    public void onAdFailedToLoad(int errorCode) {
        //                        // Handle the failure by logging, altering the UI, and so on.
        //                    }
        //                })
        //                .withNativeAdOptions(new NativeAdOptions.Builder()
        //                        // Methods in the NativeAdOptions.Builder class can be
        //                        // used here to specify individual options settings.
        //                        .build())
        //                .build();


        //Rewarded ADMOB Video CODE
        rewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        rewardedVideoAd.setRewardedVideoAdListener(this);
        loadRewardedVideoAd();

           if (rewardedVideoAd.isLoaded()) {
                    rewardedVideoAd.show();
                }






        //        // Rewarded ADMOB CODE new API:
        //
        //        rewardedAd = new RewardedAd(this,"ca-app-pub-3940256099942544/5224354917");
        //        RewardedAdLoadCallback rewardedAdLoadCallback = new RewardedAdLoadCallback(){
        //            @Override
        //            public void onRewardedAdLoaded() {
        //                // Ad successfully loaded.
        //            }
        //
        //            @Override
        //            public void onRewardedAdFailedToLoad(int errorCode) {
        //                // Ad failed to load.
        //            }
        //        };
        //
        //        rewardedAd.loadAd(new AdRequest.Builder().build(),rewardedAdLoadCallback);
        //
        //        rewardedAdButton.setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View view) {
        //                if(rewardedAd.isLoaded()){
        //                    Activity activityContext = new Activity();
        //                    RewardedAdCallback rewardedAdCallback = new RewardedAdCallback() {
        //
        //                        @Override
        //                        public void onRewardedAdOpened() {
        //                            // Ad opened.
        //                        }
        //
        //                        @Override
        //                        public void onRewardedAdClosed() {
        //                            // Ad closed.
        //                        }
        //                        @Override
        //                        public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
        //
        //                        }
        //                        @Override
        //                        public void onRewardedAdFailedToShow(int errorCode) {
        //                            // Ad failed to display
        //                        }
        //                    };
        //                    rewardedAd.show(activityContext,rewardedAdCallback);
        //                }
        //                else {
        //                    Toast.makeText(MainActivity.this, "The rewarded ad wasn't loaded yet...", Toast.LENGTH_SHORT).show();
        //                }
        //            }
        //        });



    }
    private void loadRewardedVideoAd() {
        rewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917",new AdRequest.Builder().build());
        rewardedVideoAd.show();
    }

    @Override
    public void onRewardedVideoAdLoaded() {
        Toast.makeText(this, "onRewardedVideoAdLoaded", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onRewardedVideoAdOpened() {
        Toast.makeText(this, "onRewardedVideoAdOpened", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onRewardedVideoStarted() {
        Toast.makeText(this, "onRewardedVideoStarted", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onRewardedVideoAdClosed() {
        loadRewardedVideoAd();
        Toast.makeText(this, "onRewardedVideoAdClosed", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onRewarded(RewardItem rewardItem) {

        Toast.makeText(this, "onRewarded! currency: " + rewardItem.getType() + "  amount: " +
                rewardItem.getAmount(), Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
        Toast.makeText(this, "onRewardedVideoAdLeftApplication",
                Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {
        Toast.makeText(this, "onRewardedVideoAdFailedToLoad", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onRewardedVideoCompleted() {
        Toast.makeText(this, "onRewardedVideoCompleted", Toast.LENGTH_SHORT).show();
    }
}

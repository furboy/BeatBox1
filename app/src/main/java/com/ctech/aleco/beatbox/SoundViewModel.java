package com.ctech.aleco.beatbox;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class SoundViewModel extends BaseObservable {
    private Sound mSound;
    private BeatBox mBeatBox;


    public SoundViewModel(BeatBox beatBox){mBeatBox = beatBox;}

    @Bindable
    public String getTitle(){return mSound.getName();}





    public Sound getSound() {
        return mSound;
    }

    public void setSound(Sound mSound) {
        this.mSound = mSound;
        notifyChange();
    }

    public void onButtonClicked(){
        mBeatBox.play(mSound);
    }




}

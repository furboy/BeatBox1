package com.ctech.aleco.beatbox;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ctech.aleco.beatbox.databinding.FragmentBeatBoxBinding;
import com.ctech.aleco.beatbox.databinding.ListItemSoundBinding;

import java.util.List;

import static android.media.CamcorderProfile.get;

public class BeatBoxFragment extends Fragment {
    private BeatBox mBeatBox;
    private ListItemSoundBinding mBinding;


    public static BeatBoxFragment newInstance() {
        return new BeatBoxFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBeatBox = new BeatBox(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        FragmentBeatBoxBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_beat_box, container, false);

        binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        binding.recyclerView.setAdapter(new SoundAdapter(mBeatBox.getSoundList()));

        return binding.getRoot();
    }

    private class SoundHolder extends RecyclerView.ViewHolder {
        private SoundHolder(ListItemSoundBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mBinding.setViewModel(new SoundViewModel(mBeatBox));
        }

        public void bind(Sound sound) {
            mBinding.getViewModel().setSound(sound);
            mBinding.executePendingBindings();
        }
    }

    private class SoundAdapter extends Adapter<SoundHolder> {

        private List<Sound> mSoundList;

        public SoundAdapter(List<Sound> sounds) {
            mSoundList = sounds;
        }

        @NonNull
        @Override
        public SoundHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            ListItemSoundBinding binding = DataBindingUtil.inflate(inflater, R.layout.list_item_sound, viewGroup, false);
            return new SoundHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull SoundHolder soundHolder, int position) {
            Sound sound = mSoundList.get(position);
            soundHolder.bind(sound);
        }

        @Override
        public int getItemCount() {
            return mSoundList.size();
        }
    }

@Override
    public void onDestroy(){
        super.onDestroy();
        mBeatBox.release();
}
}


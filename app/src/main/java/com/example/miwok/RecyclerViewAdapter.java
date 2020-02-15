package com.example.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Integer> circleImages, audioFiles;
    private ArrayList<String> miwokWords, defaultWords;
    private boolean isImageView;
    private int colorBackgroundID;
    private MediaPlayer mediaPlayer;
    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };
    private AudioManager audioManager;
    private AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mediaPlayer.start();
            }
        }
    };

    public RecyclerViewAdapter(Context context, int colorBackgroundID, ArrayList<String> miwokWords, ArrayList<String> defaultWords, ArrayList<Integer> audioFiles) {
        this.context = context;
        this.colorBackgroundID = colorBackgroundID;
        this.miwokWords = miwokWords;
        this.defaultWords = defaultWords;
        this.audioFiles = audioFiles;
        this.isImageView = false;
    }

    public RecyclerViewAdapter(Context context, int colorBackgroundID, ArrayList<String> miwokWords, ArrayList<String> defaultWords, ArrayList<Integer> audioFiles, ArrayList<Integer> circleImages) {
        this(context, colorBackgroundID, miwokWords, defaultWords, audioFiles);
        this.circleImages = circleImages;
        this.isImageView = true;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        if (isImageView)
            holder.getCircleImageView().setImageResource(circleImages.get(position));
        else
            holder.getCircleImageView().setVisibility(View.GONE);
        holder.getMiwokWord().setText(miwokWords.get(position));
        holder.getDefaultWord().setText(defaultWords.get(position));
        holder.getRelativeLayout().setBackgroundResource(colorBackgroundID);
        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        holder.getRelativeLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                releaseMediaPlayer();
                int response = audioManager.requestAudioFocus(onAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
                if (response == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaPlayer = MediaPlayer.create(context, audioFiles.get(position));
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(onCompletionListener);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return miwokWords.size();
    }

    public void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
            audioManager.abandonAudioFocus(onAudioFocusChangeListener);
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout relativeLayout;
        private CircleImageView circleImageView;
        private TextView miwokWord, defaultWord;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.relativeLayout = itemView.findViewById(R.id.list_item_relative_layout);
            this.circleImageView = itemView.findViewById(R.id.list_item_cirle_image_view);
            this.miwokWord = itemView.findViewById(R.id.list_item_text_view_miwok);
            this.defaultWord = itemView.findViewById(R.id.list_item_text_view_default);
        }

        public RelativeLayout getRelativeLayout() {
            return relativeLayout;
        }

        public CircleImageView getCircleImageView() {
            return circleImageView;
        }

        public TextView getMiwokWord() {
            return miwokWord;
        }

        public TextView getDefaultWord() {
            return defaultWord;
        }

    }
}

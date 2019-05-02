package com.shuttlebus.user;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;



/**
 * A custom adapter to use with the RecyclerView widget.
 */
public class BusRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<AbstractModel> modelList;
    private OnItemClickListener mItemClickListener;



    public BusRecyclerViewAdapter(Context context, ArrayList<AbstractModel> modelList) {
        this.mContext = context;
        this.modelList = modelList;
    }

    public void updateList(ArrayList<AbstractModel> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycler_list, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        //Here you can fill your row view
        if (holder instanceof ViewHolder) {
            final AbstractModel model = getItem(position);
            ViewHolder genericViewHolder = (ViewHolder) holder;

            genericViewHolder.itemTxtStart.setText(model.getStart());
            genericViewHolder.itemTxtMessage.setText(model.getMessage());
            genericViewHolder.itemTxtEnd.setText(model.getEnd());
            genericViewHolder.seekBar.setProgress(model.getProgress());


            model.setSeekBar(genericViewHolder.seekBar);

            if(position != 0){
                /*
                 * 버스가 전역에 도착 햇다면.
                 */
                if(getItem(position-1).getIsArrived()) {
                    modelList.get(position-1).getSeekBar().setVisibility(View.INVISIBLE);

                    genericViewHolder.seekBar.setVisibility(View.VISIBLE);
                }
                else{
                    genericViewHolder.seekBar.setVisibility(View.INVISIBLE);
                }
            }

            else{
                if(getItem(0).getIsArrived()){
                    genericViewHolder.seekBar.setVisibility(View.INVISIBLE);
                }
                else{
                    genericViewHolder.seekBar.setVisibility(View.VISIBLE);
                }
            }

            if(model.getIsArrived()){
                genericViewHolder.itemLayout.setBackgroundColor(mContext.getResources().getColor(R.color.backGroundGreen));
                genericViewHolder.seekBar.setProgress(100);
                genericViewHolder.imgUser.setImageResource(R.drawable.ic_check_green_24dp);
            }

            genericViewHolder.itemView.setTranslationX(300);
            genericViewHolder.itemView.setAlpha(0.f);
            genericViewHolder.itemView.setScaleX(0.8f);
            genericViewHolder.itemView.setScaleY(0.6f);
            genericViewHolder.itemView.animate()
                    .translationX(0)
                    .alpha(1.f)
                    .scaleX(1.f)
                    .scaleY(1.f)
                    .setDuration(300)
                    .setStartDelay(100)
                    .start();
        }

    }


    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private AbstractModel getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position, AbstractModel model);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        private SeekBar seekBar;
        private ImageView imgUser;
        private TextView itemTxtStart;
        private TextView itemTxtMessage;
        private TextView itemTxtEnd;
        private LinearLayout itemLayout;


        // @BindView(R.id.img_user)
        // ImageView imgUser;
        // @BindView(R.id.item_txt_title)
        // TextView itemTxtTitle;
        // @BindView(R.id.item_txt_message)
        // TextView itemTxtMessage;
        // @BindView(R.id.radio_list)
        // RadioButton itemTxtMessage;
        // @BindView(R.id.check_list)
        // CheckBox itemCheckList;
        public ViewHolder(final View itemView) {
            super(itemView);

            // ButterKnife.bind(this, itemView);
            this.seekBar = (SeekBar) itemView.findViewById(R.id.seekBar);
            this.imgUser = (ImageView) itemView.findViewById(R.id.img_user);
            this.itemTxtStart = (TextView) itemView.findViewById(R.id.item_txt_start);
            this.itemTxtMessage = (TextView) itemView.findViewById(R.id.item_txt_message);
            this.itemTxtEnd = (TextView) itemView.findViewById(R.id.item_txt_end);
            this.itemLayout = (LinearLayout) itemView.findViewById(R.id.itemBackGround);



            seekBar.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return true;
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()));

                }
            });

        }

    }

}


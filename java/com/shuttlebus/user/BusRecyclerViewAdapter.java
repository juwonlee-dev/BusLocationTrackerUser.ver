package com.shuttlebus.user;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

import android.support.v7.widget.LinearLayoutManager;


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


            if(position != 0){
                genericViewHolder.seekBar.setThumb(null);
            }
            genericViewHolder.seekBar.setProgress(0);


//            if(position == 2)
//                genericViewHolder.seekBar.getThumb().setVisible(false, false);
//            else
//                genericViewHolder.seekBar.getThumb().setVisible(true, true);

//            if(position == 0)
//                genericViewHolder.seekBar.setPadding(0, 100, 0, 0);
//            else if(position == getItemCount()-1)
//                genericViewHolder.seekBar.setPadding(0, 0, 0, 100);
//            else
//                genericViewHolder.seekBar.setPadding(0, 0, 0, 0);


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

    public void setProgress(){
    }
    public class ViewHolder extends RecyclerView.ViewHolder {


        private SeekBar seekBar;
        private ImageView imgUser;
        private TextView itemTxtStart;
        private TextView itemTxtMessage;
        private TextView itemTxtEnd;


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

//            seekBar.setOnTouchListener(new View.OnTouchListener() {
//                @Override
//                public boolean onTouch(View v, MotionEvent event) {
//                    return true;
//                }
//            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()));

                }
            });

        }

    }

}


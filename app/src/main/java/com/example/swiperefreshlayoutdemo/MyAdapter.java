package com.example.swiperefreshlayoutdemo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * 适配器
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {

    private List<String> datas;
    private OnItemClickListener listener;

    public MyAdapter(List<String> datas) {
        this.datas = datas;
    }

    public void setOnItemClickLisener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item, null);
        return new MyHolder(view);
    }


    //绑定数据
    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.textView.setText(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void addData(int position, String city) {

        datas.add(position, city);
        notifyItemInserted(position);//刷新某一条
    }

    public void remove(int position) {
        datas.remove(position);
        notifyItemRemoved(position);
    }

    class MyHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public MyHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onClick(v, getLayoutPosition(),
                                datas.get(getLayoutPosition()));
                    }
                }
            });
        }
    }

    interface OnItemClickListener {
        void onClick(View view, int position, String city);

    }
}

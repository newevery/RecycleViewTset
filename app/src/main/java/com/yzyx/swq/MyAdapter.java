package com.yzyx.swq;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/10.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private List<Item> mItemList;
    private List<Item> data;
    private TextView t4, t5, t6, t7;

    public MyAdapter(Context context, List<Item> mItemList) {
        this.context = context;
        this.mItemList = mItemList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {//此处模拟数据，具体实现可通过Adapter传递
        data = new ArrayList<>();
        data.add(new Item(111, "BBBBBBBBB", "OOOOOOOOOOOOOOO", true));
        data.add(new Item(666, "BBBBBBBBB", "OOOOOOOOOOOOOOO", true));
        data.add(new Item(666, "BBBBBBBBB", "OOOOOOOOOOOOOOO", false));
        data.add(new Item(666, "BBBBBBBBB", "OOOOOOOOOOOOOOO", true));
        data.add(new Item(666, "BBBBBBBBB", "OOOOOOOOOOOOOOO", false));
        data.add(new Item(666, "BBBBBBBBB", "OOOOOOOOOOOOOOO", true));
        data.add(new Item(666, "BBBBBBBBB", "OOOOOOOOOOOOOOO", false));
        data.add(new Item(622266, "BBBBBBBBB", "OOOOOOOOOOOOOOO", true));
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_item, parent, false);
        return new MyViewHolder(inflate);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.t1.setText(mItemList.get(position).getIs() + "");
        holder.t2.setText(mItemList.get(position).getName() + "");
        holder.t3.setText(mItemList.get(position).getPass() + "");
        if (mItemList.get(position).isGender()) {
            holder.radioButton.setText("男");
            holder.radioButton.setBackgroundColor(Color.RED);
        } else {
            holder.radioButton.setText("女");
            holder.radioButton.setBackgroundColor(Color.YELLOW);
        }
        int pos = holder.getLayoutPosition();
        holder.bind(pos);
        holder.goneLayout.removeAllViewsInLayout();//设置recyccleView的每一项

        for (int i = 0; i < data.size(); i++) {
//              要添加的每一项的布局文件，必须在循环内定义，否则报错
            View inflate = LayoutInflater.from(context).inflate(R.layout.item, null);
            inflate.setPadding(0,10,0,5);
            t4 = (TextView) inflate.findViewById(R.id.textView4);
            t5 = (TextView) inflate.findViewById(R.id.textView5);
            t6 = (TextView) inflate.findViewById(R.id.textView6);
            t7 = (TextView) inflate.findViewById(R.id.textView7);
            t4.setText(data.get(i).getIs() + "     " + pos);
            t5.setText(data.get(i).getName());
            t6.setText(data.get(i).getPass() + "");
            if (data.get(i).isGender()) {
                t7.setText("OK");
                t7.setBackgroundColor(Color.CYAN);
            } else {
                t7.setText("NOT");
                t7.setBackgroundColor(Color.YELLOW);
            }
            holder.goneLayout.addView(inflate);//设置每一个item下面的动态添加布局
        }
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    private int opened = -1;

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private TextView t1, t2, t3;
        private TextView radioButton;
        private LinearLayout goneLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            t1 = (TextView) itemView.findViewById(R.id.textView);
            t2 = (TextView) itemView.findViewById(R.id.textView2);
            t3 = (TextView) itemView.findViewById(R.id.textView3);
            radioButton = (TextView) itemView.findViewById(R.id.radioButton);
            goneLayout = (LinearLayout) itemView.findViewById(R.id.goneLayout);//此为item下的布局容器

            itemView.setOnClickListener(this);//设置单击事件
            itemView.setOnLongClickListener(this);//设置长击击事件
        }


        public void bind(int pos) { // 此方法实现列表的展开和关闭

            if (pos == opened)
                goneLayout.setVisibility(View.VISIBLE);
            else
                goneLayout.setVisibility(View.GONE);
        }

        @Override
        public void onClick(View v) {

            if (opened == getAdapterPosition()) {//更改Item不同状态的属性
                opened = -1;
                mItemList.add(getAdapterPosition()+1, new Item(22222, "DDD", "sdsd", true));//具体业务的实现
                notifyItemRangeChanged(getAdapterPosition(), getItemCount());//刷新Position之后的数据
                v.setBackgroundColor(Color.YELLOW);
                notifyItemChanged(getAdapterPosition());
            } else {
                int oldOpened = opened;
                opened = getAdapterPosition();
                notifyItemChanged(oldOpened);
                v.setBackgroundColor(Color.RED);

                notifyItemChanged(opened);//即时更新
            }

        }

        @Override
        public boolean onLongClick(final View v) {
            new AlertDialog.Builder(v.getContext()).setTitle("SSSSS")
                    .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(v.getContext(), "mItemList.get(which):" + mItemList.get(getAdapterPosition()) + "JKL", Toast.LENGTH_SHORT).show();
                            v.setBackgroundColor(Color.MAGENTA);
                        }
            })
                    .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
//                     具体业务实现
                            System.out.println(getAdapterPosition()+"");
                            mItemList.remove(getAdapterPosition());
                            notifyItemRangeChanged(getAdapterPosition(), getItemCount());//刷新Position之后的数据(删除)
                        }
            }).setView(new EditText(v.getContext())).show();//显示dialog
            return false;
        }
    }
}

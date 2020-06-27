package com.example.javachipnavigationbar.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.javachipnavigationbar.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class SliderAdapterExample extends
        SliderViewAdapter<SliderAdapterExample.SliderAdapterVH> {

    private Context context;

    public SliderAdapterExample(Context context) {
        this.context = context;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout_item, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(final SliderAdapterVH viewHolder, final int position) {


        switch (position) {
            case 0:
                viewHolder.textViewDescription.setText("Biển Ba Làng An");
                viewHolder.textViewDescription.setTextSize(16);
                viewHolder.textViewDescription.setTextColor(Color.WHITE);
                viewHolder.imageGifContainer.setVisibility(View.GONE);
                Glide.with(viewHolder.itemView)
                        .load("https://www.taidanang.com/wp-content/uploads/2017/12/M%E1%BB%99t-Ng%C3%A0y-Kh%C3%A1m-Ph%C3%A1-V%C3%B9ng-%C4%90%E1%BA%A5t-Xinh-%C4%90%E1%BA%B9p-Ba-L%C3%A0ng-An-cover.jpg")
                        .into(viewHolder.imageViewBackground);
                break;
            case 1:
                viewHolder.textViewDescription.setText("Biển Lệ  Thủy");
                viewHolder.textViewDescription.setTextSize(16);
                viewHolder.textViewDescription.setTextColor(Color.WHITE);
                viewHolder.imageGifContainer.setVisibility(View.GONE);
                Glide.with(viewHolder.itemView)
                        .load("https://media.foody.vn/res/g22/210903/prof/s/foody-mobile-t1-jpg-590-635913186577918089.jpg")
                        .into(viewHolder.imageViewBackground);
//                Glide.with(viewHolder.itemView)
//                        .asGif()
//                        .load(R.drawable.cc)
//                        .into(viewHolder.imageGifContainer);
                break;
            case 2:
                viewHolder.textViewDescription.setText("Sale sập sàn ngập tràng quà tặng");
                viewHolder.textViewDescription.setTextSize(16);
                viewHolder.textViewDescription.setTextColor(Color.WHITE);
                viewHolder.imageGifContainer.setVisibility(View.GONE);
                Glide.with(viewHolder.itemView)
                        .load("http://phukienacen.com/wp-content/uploads/2018/12/banner-ph%E1%BB%A5-ki%E1%BB%87n-acen.jpg")
                        .into(viewHolder.imageViewBackground);
//                Glide.with(viewHolder.itemView)
//                        .asGif()
//                        .load(R.drawable.ax)
//                        .into(viewHolder.imageGifContainer);
                break;
            case 3:
                viewHolder.textViewDescription.setTextSize(18);
                viewHolder.textViewDescription.setTextColor(Color.WHITE);
                viewHolder.textViewDescription.setText("Bảo sale công nghệ");
                viewHolder.imageGifContainer.setVisibility(View.VISIBLE);
                Glide.with(viewHolder.itemView)
                        .load("https://www.xtmobile.vn/vnt_upload/news/04_2019/24/sale-cuoi-tuan-thang-4-tai-xtmobile-hoang-van-thu.jpg")
                        .into(viewHolder.imageViewBackground);
//                Glide.with(viewHolder.itemView)
//                        .asGif()
//                        .load(R.drawable.oh_look_at_this)
//                        .into(viewHolder.imageGifContainer);
                break;
            case 4:
                viewHolder.textViewDescription.setTextSize(18);
                viewHolder.textViewDescription.setTextColor(Color.WHITE);
                viewHolder.textViewDescription.setText("Giảm giá 30%");
                viewHolder.imageGifContainer.setVisibility(View.VISIBLE);
                Glide.with(viewHolder.itemView)
                        .load("https://didongviet.vn/blog/wp-content/uploads/2018/11/black-friday-phu-kien-780x520-didongviet.jpg")
                        .into(viewHolder.imageViewBackground);
//                Glide.with(viewHolder.itemView)
//                        .asGif()
//                        .load(R.drawable.oh_look_at_this)
//                        .into(viewHolder.imageGifContainer);
                break;
            case 5:
                viewHolder.textViewDescription.setTextSize(18);
                viewHolder.textViewDescription.setTextColor(Color.WHITE);
                viewHolder.textViewDescription.setText("Giảm đến 50%");
                viewHolder.imageGifContainer.setVisibility(View.VISIBLE);
                Glide.with(viewHolder.itemView)
                        .load("http://www.congkhuyenmai.com/image/data/TinTuc/8-2014/tiki-summer-sale-1.jpg")
                        .into(viewHolder.imageViewBackground);
//                Glide.with(viewHolder.itemView)
//                        .asGif()
//                        .load(R.drawable.oh_look_at_this)
//                        .into(viewHolder.imageGifContainer);
                break;


        }
//        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                switch (position){
//                    case 0:
//                        Intent intent=new Intent(context, HangCongNghe.class);
//                        Pair[] pairs=new Pair[1];
//                        pairs[0]=new Pair<View,String>(viewHolder.itemView,"imageTransition");
//                        ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation((Activity) context,pairs);
//                        context.startActivity(intent,options.toBundle());
//                        break;
//                    case 2:
//                        context.startActivity(new Intent(context, SaleUp.class));
//
//                }
//            }
//        });

    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return 6;
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;
        ImageView imageGifContainer;
        TextView textViewDescription;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            imageGifContainer = itemView.findViewById(R.id.iv_gif_container);
            textViewDescription = itemView.findViewById(R.id.tv_auto_image_slider);
            this.itemView = itemView;
        }
    }


}
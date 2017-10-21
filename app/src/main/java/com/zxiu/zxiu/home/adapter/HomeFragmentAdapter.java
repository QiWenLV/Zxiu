package com.zxiu.zxiu.home.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;
import com.zxiu.zxiu.R;
import com.zxiu.zxiu.home.bean.ResultBeanData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 启文 on 2017/10/20.
 */
public class HomeFragmentAdapter extends RecyclerView.Adapter {

    /**
     * 顶部轮播图类型
     */
    public static final int BANNER = 0;
    /**
     * 频道类型
     */
    public static final int CHANNWL = 1;
    /**
     * 折扣类型
     */
    public static final int SECKILL = 2;
    /**
     * 推荐类型
     */
    public static final int RECOMMEBD = 3;
    /**
     * 热卖
     */
    public static final int HOT = 4;
    /**
     * 文章类型
     */
    public static final int ARTZICLE = 5;


    private int currentType = 0;    //记录当前类型
    private Context context;
    private ResultBeanData.ResultBean result;
    private LayoutInflater from;

    public HomeFragmentAdapter(Context context, ResultBeanData.ResultBean result) {
        this.context = context;
        this.result = result;
        from = LayoutInflater.from(context);     //之后直接使用LayoutInflater初始化布局
    }

    /**
     * 相对于getView 创建ViewHolder的部分代码
     * 创建ViewHolder
     *
     * @param parent   //父类
     * @param viewType //当前Item的类型
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == BANNER) {
            return new BannerViewHolder(context, from.inflate(R.layout.banner_viewpager, null));
        }else if (viewType == CHANNWL) {
            return new ChannelViewHolder(context, from.inflate(R.layout.channel_item, null));
        }else if(viewType == SECKILL) {
            return new SeckillViewHolder(context, from.inflate(R.layout.seckill_item, null));
        }else if(viewType == RECOMMEBD){
            return new RecommebdViewHolder(context, from.inflate(R.layout.recommend_item, null));
        }else if(viewType == HOT){
            return new HotViewHolder(context, from.inflate(R.layout.hot_item, null));
        }
        else if(viewType == ARTZICLE){
            return new ArticleViewHolder(context, from.inflate(R.layout.article_item, null));
        }

        return null;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (position) {
            case BANNER:
                BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
                bannerViewHolder.setData(result.getBanner_info());
                break;
            case CHANNWL:
                ChannelViewHolder channelViewHolder = (ChannelViewHolder) holder;
                channelViewHolder.setData(result.getChannel_info());
                break;
            case SECKILL:
                SeckillViewHolder seckillViewHolder = (SeckillViewHolder) holder;
                seckillViewHolder.setData(result.getSeckill_info());
                break;
            case RECOMMEBD:
                RecommebdViewHolder recommebdViewHolder = (RecommebdViewHolder) holder;
                recommebdViewHolder.setData(result.getRecommend_info());
                break;
            case HOT:
                HotViewHolder hotViewHolder = (HotViewHolder) holder;
                hotViewHolder.setData(result.getHot_info());
                break;
            case ARTZICLE:
                ArticleViewHolder articleViewHolder = (ArticleViewHolder) holder;
                articleViewHolder.setData(result.getArticle_info());
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {

        switch (position){
            case BANNER:
                currentType = BANNER;
                break;
            case CHANNWL:
                currentType = CHANNWL;
                break;
            case SECKILL:
                currentType = SECKILL;
                break;
            case RECOMMEBD:
                currentType = RECOMMEBD;
                break;
            case HOT:
                currentType = HOT;
                break;
            case ARTZICLE:
                currentType = ARTZICLE;
                break;
        }
        return currentType;
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    /**
     * 顶部轮播图
     */
    private class BannerViewHolder extends RecyclerView.ViewHolder {

        private Context context;
        private MZBannerView banner;

        public BannerViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            this.banner = itemView.findViewById(R.id.banner);
        }


        MZHolderCreator mzHolderCreator;

        public void setData(List<ResultBeanData.ResultBean.BannerInfoBean> data) {
            //设置Banner的数据
            final List<Bitmap> bitmaps = new ArrayList<>();
            //得到图片地址集合
            for (int i = 0; i < data.size(); i++) {
                String str = data.get(i).getImage();
              //  Log.i("TAG", "Constants=" + Constants.BASE_URL_IMAGE + str);
//                try {
//
//                    Bitmap bitmap = Glide.with(context)
//                            .load(Constants.BASE_URL_IMAGE + str)
//                            .asBitmap() //必须
//                            .centerCrop()
//                            .into(500, 500)
//                            .get();
//                    // 在这里执行图片保存方法
//                    //bitmaps.add(bitmap);
//
//
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                    Log.i("TAG", "bitmaps5=");
//                } catch (ExecutionException e) {
//                    e.printStackTrace();
//                    Log.i("TAG", "bitmaps6=");
//                }


            }

            bitmaps.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.banner_image));
            bitmaps.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.banner_image2));
            bitmaps.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.banner_image));
            bitmaps.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.banner_image));

            mzHolderCreator = new MZHolderCreator() {
                @Override
                public MZViewHolder createViewHolder() {
                    return new BannerHolder();
                }
            };

            Log.i("TAG", "bitmaps=" + bitmaps.size());

            banner.setPages(bitmaps, mzHolderCreator);

        }


    }

    private class BannerHolder implements MZViewHolder<Bitmap> {
        private ImageView mImageView;

        /**
         * 创建View
         *
         * @param context
         * @return
         */
        @Override
        public View createView(Context context) {
            // 返回页面布局
            View view = LayoutInflater.from(context).inflate(R.layout.item_banner, null);
            mImageView = (ImageView) view.findViewById(R.id.banner_image);
            return view;
        }

        /**
         * 绑定数据
         *
         * @param context
         * @param position
         * @param data
         */
        @Override
        public void onBind(Context context, int position, Bitmap data) {
            mImageView.setImageBitmap(data);
        }
    }
    /**
     * 频道
     */
    private class ChannelViewHolder extends RecyclerView.ViewHolder {
        private Context context;
        private GridView gv_channel;
        private ChannelAdapter adapter;

        public ChannelViewHolder(final Context context, View itemView) {
            super(itemView);
            this.context = context;
            gv_channel= (GridView) itemView.findViewById(R.id.gv_channel);

            //设置Item的点击事件
            gv_channel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(context, "点击了第"+i+"频道", Toast.LENGTH_SHORT).show();
                }
            });

        }

        public void setData(List<ResultBeanData.ResultBean.ChannelInfoBean> data) {
            //设置适配器
            adapter = new ChannelAdapter(context, data);
            gv_channel.setAdapter(adapter);
        }
    }

    /**
     * 折扣的ViewHolder
     */
    private class SeckillViewHolder extends RecyclerView.ViewHolder{

        private Context context;
        private TextView tv_more_seckill;
        private RecyclerView rv_seckill;

        private SeckillAdaper adapter;



        public SeckillViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            tv_more_seckill = (TextView) itemView.findViewById(R.id.tv_more_seckill);
            rv_seckill = (RecyclerView) itemView.findViewById(R.id.rv_seckill);
        }


        public void setData(ResultBeanData.ResultBean.SeckillInfoBean data) {

            //设置适配器
            adapter = new SeckillAdaper(context, data.getList());
            rv_seckill.setAdapter(adapter);
            //设置布局管理器
            rv_seckill.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));

            //设置item的点击事件（接口）
            adapter.setOnSeckillRecyclerView(new SeckillAdaper.OnSeckillRecyclerView() {
                @Override
                public void onItemClick(int position) {
                    Toast.makeText(context, "秒杀"+position, Toast.LENGTH_SHORT).show();
                }
            });

        }

    }

    /**
     * 推荐的ViewHolder
     */
    private class RecommebdViewHolder extends RecyclerView.ViewHolder {
        private Context context;
        private TextView tv_more_recommend;
        private GridView gv_recommend;
        private RecommendGridViewAdapter adapter;


        public RecommebdViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            tv_more_recommend = (TextView) itemView.findViewById(R.id.tv_more_recommend);
            gv_recommend = (GridView) itemView.findViewById(R.id.gv_recommend);


        }

        public void setData(List<ResultBeanData.ResultBean.RecommendInfoBean> data) {
            adapter = new RecommendGridViewAdapter(context, data);
            gv_recommend.setAdapter(adapter);

            gv_recommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(context, "po"+ i, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    /**
     * 热卖的ViewHolder
     */
    private class HotViewHolder extends RecyclerView.ViewHolder {
        private Context context;
        private TextView tv_more_hot;
        private GridView gv_hot;
        private HotGirdViewAdaper adaper;

        public HotViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            tv_more_hot = (TextView) itemView.findViewById(R.id.tv_more_hot);
            gv_hot = (GridView) itemView.findViewById(R.id.gv_hot);
        }

        public void setData(List<ResultBeanData.ResultBean.HotInfoBean> data) {

            adaper = new HotGirdViewAdaper(context, data);
            gv_hot.setAdapter(adaper);


            gv_hot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(context, "gv"+ i, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    /**
     * 文章的ViewHolder
     */
    private class ArticleViewHolder extends RecyclerView.ViewHolder {
        private Context context;
        private TextView tv_more_recommend;
        private ListView lv_article;

        private ArticleListViewAdapter adapter;

        public ArticleViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            tv_more_recommend = itemView.findViewById(R.id.tv_more_recommend);
            lv_article = itemView.findViewById(R.id.lv_article);
        }

        public void setData(List<ResultBeanData.ResultBean.ArticleInfoBean> data) {
            adapter = new ArticleListViewAdapter(context, data);
            lv_article.setAdapter(adapter);

            lv_article.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(context, "lv"+ i, Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}

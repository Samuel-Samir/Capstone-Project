package samuel.example.com.soccernow.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import samuel.example.com.soccernow.R;
import samuel.example.com.soccernow.model.articleModel.Article;

/**
 * Created by samuel on 6/16/2017.
 */



public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.RecyclerViewAdapterHolder>{
    private List<Article> articleList ;

    public void setApiResponse (List<Article> articleList )
    {
        this.articleList =articleList;
        notifyDataSetChanged();
    }


    @Override
    public RecyclerViewAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        int layoutPhotoItem = R.layout.news_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutPhotoItem ,parent ,shouldAttachToParentImmediately);
        return new RecyclerViewAdapterHolder(view);    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapterHolder holder, final int position) {

        final Article article = articleList.get(position);
        holder.article_auther.setText(article.getAuthor());
        holder.article_title.setText(article.getTitle());
        String [] dateSplited = article.getPublishedAt().split("T");
        holder.date.setText(dateSplited[0]);
        Picasso.with(holder.context)
                .load(article.getUrlToImage())
                .placeholder(R.drawable.p1)
                .error(R.drawable.p1)
                .into(holder.article_icon);

    }

    @Override
    public int getItemCount() {
        if(articleList!=null)
        {
            return articleList.size();
        }
        return 0;
    }

    public class RecyclerViewAdapterHolder extends RecyclerView.ViewHolder {
        private LinearLayout layoutParent ;
        private ImageView article_icon  ;
        private TextView article_title ;
        private TextView article_auther ;
        private TextView date ;
        private Context context ;


        public RecyclerViewAdapterHolder(View itemView) {
            super(itemView);
            context =  itemView.getContext() ;
            layoutParent =(LinearLayout) itemView.findViewById(R.id.layoutParent);
            article_icon= (ImageView) itemView.findViewById(R.id.icon_news);
            article_auther= (TextView) itemView.findViewById(R.id.author_text_view);
            article_title= (TextView) itemView.findViewById(R.id.title_text_view);
            date = (TextView) itemView.findViewById(R.id.date);

        }

    }


}

package samuel.example.com.soccernow.adapter;

import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.caverock.androidsvg.SVG;

import java.io.InputStream;
import java.util.List;

import samuel.example.com.soccernow.R;
import samuel.example.com.soccernow.model.football.leagueTable.LeagueData;
import samuel.example.com.soccernow.svg.SvgDecoder;
import samuel.example.com.soccernow.svg.SvgDrawableTranscoder;
import samuel.example.com.soccernow.svg.SvgSoftwareLayerSetter;

import static android.os.Looper.getMainLooper;

/**
 * Created by samuel on 7/12/2017.
 */

public class LeagueTableAdapter extends RecyclerView.Adapter<LeagueTableAdapter.RecyclerViewAdapterHolder>{
    private  List<LeagueData> leagueDataList ;
    private LeagueTableAdapter.RecyclerViewTeamCallback recyclerViewTeamCallback;

    public void setApiResponse ( List<LeagueData> leagueDataList  )
    {
        this.leagueDataList =leagueDataList;
        notifyDataSetChanged();
    }


     public void setRecyclerViewCallback(LeagueTableAdapter.RecyclerViewTeamCallback recyclerViewTeamCallback) {
        this.recyclerViewTeamCallback = recyclerViewTeamCallback;
    }

    @Override
    public LeagueTableAdapter.RecyclerViewAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        int layoutPhotoItem = R.layout.league_table_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutPhotoItem ,parent ,shouldAttachToParentImmediately);
        return new LeagueTableAdapter.RecyclerViewAdapterHolder(view);    }

    @Override
    public void onBindViewHolder(final LeagueTableAdapter.RecyclerViewAdapterHolder holder, final int position) {

     final LeagueData leagueData = leagueDataList.get(position);
        holder.position.setText(String.valueOf(leagueData.getPosition()));
        holder.team_name.setText(String.valueOf(leagueData.getTeamName()));
        holder.win.setText(String.valueOf(leagueData.getWins()));
        holder.draws.setText(String.valueOf(leagueData.getDraws()));
        holder.loses.setText(String.valueOf(leagueData.getLosses()));
        holder.pts.setText(String.valueOf(leagueData.getPoints()));
        holder.holderLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerViewTeamCallback.onItemClick(position);
            }
        });


        if (leagueData.getCrestURI()!=null) {
            GenericRequestBuilder requestBuilder;
            Context context = holder.league_icon.getContext();
            String imagUri = leagueData.getCrestURI();
            Uri uri = Uri.parse(imagUri);
            requestBuilder = Glide.with(context)
                    .using(Glide.buildStreamModelLoader(Uri.class, context), InputStream.class)
                    .from(Uri.class)
                    .as(SVG.class)
                    .transcode(new SvgDrawableTranscoder(), PictureDrawable.class)
                    .sourceEncoder(new StreamEncoder())
                    .cacheDecoder(new FileToStreamDecoder<SVG>(new SvgDecoder()))
                    .decoder(new SvgDecoder())
                    .animate(android.R.anim.fade_in)
                    .listener(new SvgSoftwareLayerSetter<Uri>());
            requestBuilder
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .load(uri)
                    .into(holder.league_icon);
        }

    }

    @Override
    public int getItemCount() {
        if(leagueDataList!=null)
        {
            return leagueDataList.size();
        }
        return 0;
    }


    public class RecyclerViewAdapterHolder extends RecyclerView.ViewHolder {
        private LinearLayout holderLinearLayout ;
        private ImageView league_icon  ;
        private TextView position ;
        private TextView team_name ;
        private TextView win ;
        private TextView draws ;
        private TextView loses ;
        private TextView pts ;



        public RecyclerViewAdapterHolder(View itemView) {
            super(itemView);
            league_icon= (ImageView) itemView.findViewById(R.id.team_image);
            position= (TextView) itemView.findViewById(R.id.position_textView);
            team_name= (TextView) itemView.findViewById(R.id.teamName_textView);
            win = (TextView) itemView.findViewById(R.id.win_textView);
            draws= (TextView) itemView.findViewById(R.id.draes_textView);
            loses= (TextView) itemView.findViewById(R.id.loses_textView);
            pts = (TextView) itemView.findViewById(R.id.pts_textView);
            holderLinearLayout = (LinearLayout) itemView.findViewById(R.id.holder_LinearLayout);

        }

    }

    public interface RecyclerViewTeamCallback {
        void onItemClick(int  position);
    }

}

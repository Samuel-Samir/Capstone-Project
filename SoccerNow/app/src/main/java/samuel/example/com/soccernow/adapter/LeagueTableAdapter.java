package samuel.example.com.soccernow.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import samuel.example.com.soccernow.R;
import samuel.example.com.soccernow.model.football.leagueTable.LeagueData;

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
    public void onBindViewHolder(LeagueTableAdapter.RecyclerViewAdapterHolder holder, final int position) {

     LeagueData leagueData = leagueDataList.get(position);
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

package samuel.example.com.soccernow.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import samuel.example.com.soccernow.R;
import samuel.example.com.soccernow.model.football.leagueMatches.MatcheData;
import samuel.example.com.soccernow.model.football.leagueTable.LeagueData;

/**
 * Created by samuel on 7/15/2017.
 */

public class LeagueMatchAdapter   extends RecyclerView.Adapter<LeagueMatchAdapter.RecyclerViewAdapterHolder>{

    private List<MatcheData> matcheDataList;
    public void setApiResponse ( List<MatcheData> matcheDataList  )
    {
        this.matcheDataList =matcheDataList;
        notifyDataSetChanged();
    }



    @Override
    public LeagueMatchAdapter.RecyclerViewAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        int layoutPhotoItem = R.layout.match_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutPhotoItem ,parent ,shouldAttachToParentImmediately);
        return new LeagueMatchAdapter.RecyclerViewAdapterHolder(view);    }

    @Override
    public void onBindViewHolder(LeagueMatchAdapter.RecyclerViewAdapterHolder holder, final int position) {

        MatcheData matcheData = matcheDataList.get(position);
        holder.team1.setText(matcheData.getHomeTeamName());
        holder.team2.setText(matcheData.getAwayTeamName());

        String [] dateArr = matcheData.getDate().split("T");
        holder.date.setText(dateArr[0]);
        if (matcheData.getStatus()==null)
        {
            holder.status.setText(holder.status.getResources().getString(R.string.notYet));
            String result="- : -";
            holder.result.setText(result);


        }
        else if (matcheData.getStatus().equals("FINISHED"))
        {
            holder.status.setTextColor(holder.status.getResources().getColor(R.color.red));
            holder.status.setText(matcheData.getStatus());
            String result="";
            result+=matcheData.getResult().getGoalsHomeTeam() + " : " + matcheData.getResult().getGoalsAwayTeam();
            holder.result.setText(result);


        }
        else {
            holder.status.setTextColor(holder.status.getResources().getColor(R.color.colorPrimary));
            holder.status.setText(matcheData.getStatus());
            String result="- : -";
            holder.result.setText(result);

        }




    }

    @Override
    public int getItemCount() {
        if(matcheDataList!=null)
        {
            return matcheDataList.size();
        }
        return 0;
    }


    public class RecyclerViewAdapterHolder extends RecyclerView.ViewHolder {
        private TextView team1 ;
        private TextView team2 ;
        private TextView result ;
        private ImageView team1Image ;
        private ImageView team2Image ;
        private TextView status ;
        private TextView date ;




        public RecyclerViewAdapterHolder(View itemView) {
            super(itemView);
            team1= (TextView) itemView.findViewById(R.id.firstTeam);
            team2= (TextView) itemView.findViewById(R.id.secondTeam);
            result = (TextView) itemView.findViewById(R.id.result);
            team1Image= (ImageView) itemView.findViewById(R.id.firstTeamImage);
            team2Image= (ImageView) itemView.findViewById(R.id.secondTeamImage);
            status = (TextView) itemView.findViewById(R.id.status);
            date = (TextView) itemView.findViewById(R.id.date);




        }

    }

    public interface RecyclerViewCallback {
        void onItemClick(int  position);
    }

}

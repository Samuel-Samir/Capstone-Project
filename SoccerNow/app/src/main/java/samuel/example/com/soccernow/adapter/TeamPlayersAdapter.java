package samuel.example.com.soccernow.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import samuel.example.com.soccernow.R;
import samuel.example.com.soccernow.model.football.PlayerData;

/**
 * Created by samuel on 7/15/2017.
 */

public class TeamPlayersAdapter extends RecyclerView.Adapter<TeamPlayersAdapter.RecyclerViewAdapterHolder> {

    private List<PlayerData> playerDataList;

    public void setApiResponse(List<PlayerData> playerDataList) {
        this.playerDataList = playerDataList;
        notifyDataSetChanged();
    }


    @Override
    public TeamPlayersAdapter.RecyclerViewAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutPhotoItem = R.layout.player_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutPhotoItem, parent, shouldAttachToParentImmediately);
        return new TeamPlayersAdapter.RecyclerViewAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(TeamPlayersAdapter.RecyclerViewAdapterHolder holder, final int position) {
        PlayerData playerData = playerDataList.get(position);
        holder.playerNum.setText(String.valueOf(playerData.getJerseyNumber()));
        holder.playername.setText(playerData.getName());
        holder.playerPosition.setText(playerData.getPosition());

    }

    @Override
    public int getItemCount() {
        if (playerDataList != null) {
            return playerDataList.size();
        }
        return 0;
    }


    public class RecyclerViewAdapterHolder extends RecyclerView.ViewHolder {
        private TextView playerNum;
        private TextView playername;
        private TextView playerPosition;

        public RecyclerViewAdapterHolder(View itemView) {
            super(itemView);
            playerNum = (TextView) itemView.findViewById(R.id.player_num);
            playername = (TextView) itemView.findViewById(R.id.play_name);
            playerPosition = (TextView) itemView.findViewById(R.id.player_position);
        }

    }

}

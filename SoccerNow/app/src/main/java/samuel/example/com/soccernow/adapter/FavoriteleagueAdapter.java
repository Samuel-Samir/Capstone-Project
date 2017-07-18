package samuel.example.com.soccernow.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import samuel.example.com.soccernow.R;
import samuel.example.com.soccernow.view.ContentActivity;

import static samuel.example.com.soccernow.Utilities.saveFavoritLeagueSharedPreferences;

/**
 * Created by samuel on 7/16/2017.
 */

public class FavoriteleagueAdapter extends RecyclerView.Adapter<FavoriteleagueAdapter.RecyclerViewAdapterHolder> {

    List<String> leagueNameList;
    List<Integer> leagueCodeList;
    Activity context;

    public FavoriteleagueAdapter(Activity context) {
        this.context = context;
        leagueNameList = new ArrayList<>();
        leagueCodeList = new ArrayList<>();
        leagueNameList.add(context.getResources().getString(R.string.primera_division));
        leagueNameList.add(context.getResources().getString(R.string.primera_liga));
        leagueNameList.add(context.getResources().getString(R.string.bundesliga));
        leagueNameList.add(context.getResources().getString(R.string.ligue_1));
        leagueNameList.add(context.getResources().getString(R.string.serie_A));
        leagueNameList.add(context.getResources().getString(R.string.champions_league));

        leagueCodeList.add(436);
        leagueCodeList.add(445);
        leagueCodeList.add(430);
        leagueCodeList.add(450);
        leagueCodeList.add(444);
        leagueCodeList.add(446);

    }

    @Override
    public FavoriteleagueAdapter.RecyclerViewAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutPhotoItem = R.layout.favorite_league_iteam;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutPhotoItem, parent, shouldAttachToParentImmediately);
        return new FavoriteleagueAdapter.RecyclerViewAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(FavoriteleagueAdapter.RecyclerViewAdapterHolder holder, final int position) {

        holder.leagueNme.setText(leagueNameList.get(position));
        holder.leaguePic.setImageDrawable(context.getResources().getDrawable(context.getResources()
                .getIdentifier("p" + leagueCodeList.get(position), "drawable", context.getPackageName())));
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFavoritLeagueSharedPreferences(leagueCodeList.get(position), context);
                context.startActivity(new Intent(context, ContentActivity.class));
                context.finish();

            }
        });
    }

    @Override
    public int getItemCount() {

        return leagueNameList.size();
    }


    public class RecyclerViewAdapterHolder extends RecyclerView.ViewHolder {

        private TextView leagueNme;
        private ImageView leaguePic;
        private LinearLayout linearLayout;

        public RecyclerViewAdapterHolder(View itemView) {
            super(itemView);

            linearLayout = (LinearLayout) itemView.findViewById(R.id.data_LinearLayout);
            leagueNme = (TextView) itemView.findViewById(R.id.fav_name);
            leaguePic = (ImageView) itemView.findViewById(R.id.fav_img);


        }

    }


}

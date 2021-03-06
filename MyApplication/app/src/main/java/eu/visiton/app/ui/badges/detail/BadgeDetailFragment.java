package eu.visiton.app.ui.badges.detail;

import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;

import eu.visiton.app.R;
import eu.visiton.app.data.BadgeViewModel;
import eu.visiton.app.responses.BadgePoiResponse;
import eu.visiton.app.responses.BadgeResponse;
import eu.visiton.app.retrofit.generator.AuthType;
import eu.visiton.app.retrofit.generator.ServiceGenerator;
import eu.visiton.app.retrofit.services.BadgeService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BadgeDetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BadgeDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BadgeDetailFragment extends Fragment {
    private String badgeId, jwt;
    private BadgeResponse badge;
    private List<BadgePoiResponse> pois;
    private TextView name, description, points, title;
    private ImageView earned, icon;
    private BadgeDetailListener mListener;
    private Context ctx;
    private boolean isEarned;
    PoisAdapter adapter;
    RecyclerView recycler;
    private RequestBuilder<PictureDrawable> requestBuilder;

    private BadgeViewModel badgeViewModel;

    public BadgeDetailFragment() {
        // Required empty public constructor
    }

    public BadgeDetailFragment(String badgeId, boolean isEarned) {
        this.badgeId = badgeId; this.isEarned = isEarned;
    }

    private void getBadgeDetails(String badgeId, View layout) {

        badgeViewModel.getBadgeDetails(badgeId).observe(getActivity(), badgeResponse -> {

            badge = badgeResponse;

            pois = badge.getPois();

            adapter = new PoisAdapter(
                    ctx,
                    pois,
                    mListener
            );

            recycler.setAdapter(adapter);

            setData(layout);
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public static BadgeDetailFragment newInstance(String param1, String param2) {
        BadgeDetailFragment fragment = new BadgeDetailFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        badgeViewModel = ViewModelProviders.of(getActivity())
                .get(BadgeViewModel.class);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_badge_detail, container, false);
        ctx = layout.getContext();
        recycler = layout.findViewById(R.id.badge_detail_pois_recycler);
        recycler.setLayoutManager(new LinearLayoutManager(ctx));

        getBadgeDetails(badgeId, layout);

        return layout;
    }

    private void setData(View layout) {
        name = layout.findViewById(R.id.badge_detail_name);
        description = layout.findViewById(R.id.badge_detail_description);
        points = layout.findViewById(R.id.badge_detail_points);
        icon = layout.findViewById(R.id.badge_detail_icon);
        earned = layout.findViewById(R.id.badge_detail_earned);
        title = layout.findViewById(R.id.badge_detail_pois_title);
        name.setText(badge.getName());
        description.setText(badge.getDescription());
        points.setText(String.valueOf(badge.getPoints()) +" "+ ctx.getString(R.string.points));

        if (isEarned) {
            earned.setVisibility(View.VISIBLE);
            title.setText(ctx.getString(R.string.congratulations_bagdge_get));
        }
        Glide.with(ctx).load(badge.getIcon()).into(icon);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
//            mListener.onBadgeClick();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BadgeDetailListener) {
            mListener = (BadgeDetailListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement BadgeDetailListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

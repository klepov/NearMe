package klep.nearme.listExecute;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import clojure.lang.Util;
import klep.nearme.R;
import klep.nearme.common.BaseViewStateFragment;
import klep.nearme.model.Person;
import klep.nearme.profilePerson.ProfileActivity;
import klep.nearme.profilePerson.ProfileFragment;
import klep.nearme.settings.SettingViewState;

/**
 * Created by klep on 18.04.16 with love.
 */
public class ListExecuteFragment extends BaseViewStateFragment<ListExecuteView, ListExecutePresenter>
        implements ListExecuteView, AdapterPersons.CallbackItemClickListener {

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    AdapterPersons adapterPersons;

    List<Person> persons;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_persons;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.wishExecute);

        if (savedInstanceState == null) {
            persons = new ArrayList<>();
        }

        adapterPersons = new AdapterPersons(persons, this, getActivity());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapterPersons);
        recyclerView.addItemDecoration(new DividerItem(getContext()));

    }

    @Override
    public void onNewViewStateInstance() {
        getList();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void getList() {
        presenter.getListExecute();
    }

    @Override
    public void listFromServer(List<Person> persons) {
        this.persons.addAll(persons);
        adapterPersons.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewState createViewState() {
        return new ListExecuteViewState();
    }

    @NonNull
    @Override
    public ListExecutePresenter createPresenter() {
        return new ListExecutePresenter();
    }


    @Override
    public void itemClickedFromViewHolder(Person person) {
        Intent intent = new Intent(getActivity(), ProfileActivity.class);
        intent.putExtra("Person",person);
        startActivity(intent);
    }

    class DividerItem extends RecyclerView.ItemDecoration {
        private Drawable divider;

        public DividerItem(Context context) {
            divider = ContextCompat.getDrawable(context, R.drawable.line_divider);
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();

            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = parent.getChildAt(i);

                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

                int top = child.getBottom() + params.bottomMargin;
                int bottom = top + divider.getIntrinsicHeight();

                divider.setBounds(left, top, right, bottom);
                divider.draw(c);
            }
        }
    }
}
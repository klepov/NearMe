package klep.nearme.listExecute;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import klep.nearme.R;
import klep.nearme.common.BaseViewStateFragment;
import klep.nearme.model.Person;
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
        if (savedInstanceState == null) {
            persons = new ArrayList<>();
        }

        adapterPersons = new AdapterPersons(persons, this, getActivity());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapterPersons);
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
        return new SettingViewState();
    }

    @NonNull
    @Override
    public ListExecutePresenter createPresenter() {
        return new ListExecutePresenter();
    }


    @Override
    public void itemClickedFromViewHolder(Person artist) {

    }
}
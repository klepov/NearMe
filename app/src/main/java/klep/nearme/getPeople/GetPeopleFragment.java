package klep.nearme.getPeople;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import klep.nearme.R;
import klep.nearme.common.BaseViewStateFragment;
import klep.nearme.model.Person;
import klep.nearme.model.Persons;
import link.fls.swipestack.SwipeStack;

/**
 * Created by klep.io on 26.03.16.
 */
public class GetPeopleFragment extends BaseViewStateFragment<GetPeopleView, GetPeoplePresenter>
        implements GetPeopleView {

    private SwipeStack swipeStack;
    private SwipeStackAdapter mAdapter;


    Bundle savedInstanceState;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(getLayoutRes(), container, false);
        this.savedInstanceState = savedInstanceState;
        swipeStack = (SwipeStack) v.findViewById(R.id.swipeStack);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getPeople();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_people;
    }


    @NonNull
    @Override
    public ViewState createViewState() {
        return new GetPeopleViewState();
    }

    @Override
    public void onNewViewStateInstance() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError(int error) {

    }

    @Override
    public void showPeople(Persons person) {
        mAdapter = new SwipeStackAdapter(person);
        swipeStack.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

    }

    @Override
    public GetPeoplePresenter createPresenter() {
        return new GetPeoplePresenter();
    }


    public class SwipeStackAdapter extends BaseAdapter {

        @Bind(R.id.imagePerson)
        ImageView image;

        @Bind(R.id.namePerson)
        TextView namePerson;

        @Bind(R.id.age)
        TextView age;

        @Bind(R.id.wishPerson)
        TextView wish;
        private List<Person> persons;

        public SwipeStackAdapter(Persons persons) {
            this.persons = persons.getItems();
        }

        @Override
        public int getCount() {
            return persons.size();
        }

        @Override
        public Object getItem(int position) {
            return persons.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater(savedInstanceState).inflate(R.layout.card, parent, false);
                ButterKnife.bind(this, convertView);
            }


            Picasso.with(getContext())
                    .load(persons.get(position).getPhotoId())
                    .into(image);

            namePerson.setText(persons.get(position).getUserName());
            age.setText("" + persons.get(position).getAge());
            wish.setText("" + persons.get(position).getWish());


            return convertView;
        }
    }

}

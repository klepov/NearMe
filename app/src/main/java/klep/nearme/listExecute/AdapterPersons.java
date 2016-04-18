package klep.nearme.listExecute;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import klep.nearme.R;
import klep.nearme.model.Person;

/**
 * Created by klep on 18.04.16 with love.
 */
public class AdapterPersons extends RecyclerView.Adapter<AdapterPersons.ViewHolder> {


    Context context;

    private List<Person> persons;
    private CallbackItemClickListener callbackItemClickListener;

    public AdapterPersons(List<Person> persons, CallbackItemClickListener callbackItemClickListener, Context context) {
        this.persons = persons;
        this.callbackItemClickListener = callbackItemClickListener;
        this.context = context;
    }

    public AdapterPersons(CallbackItemClickListener callbackItemClickListener, Context context) {

        this.callbackItemClickListener = callbackItemClickListener;
        this.context = context;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View artistView = inflater.inflate(R.layout.person_in_list, parent, false);

        ViewHolder viewHolder = new ViewHolder(artistView, new ViewHolder.ViewHolderCallback() {
            @Override
            public void itemSelected(int itemNum) {
                callbackItemClickListener
                        .itemClickedFromViewHolder(persons.get(itemNum));
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Person person = persons.get(position);

        holder.namePersonList.setText(person.getUserName());
        holder.agePersonList.setText(person.getAgeFormat());
        Picasso.with(context)
                .load(person.getPhotoId())
                .into(holder.photoPersonList);

    }

    @Override
    public int getItemCount() {
        return persons.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ViewHolderCallback callback;

        @Bind(R.id.photoPersonList)
        CircleImageView photoPersonList;

        @Bind(R.id.namePersonList)
        TextView namePersonList;

        @Bind(R.id.agePersonList)
        TextView agePersonList;

        /**
         * инжектит view.
         * реализует callback для выбранного элемента
         *
         * @param itemView
         * @param callback
         */
        public ViewHolder(View itemView, ViewHolderCallback callback) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            this.callback = callback;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
//            отправляет в адаптер id выбранного артиста
            callback.itemSelected(getLayoutPosition());
        }

        public interface ViewHolderCallback {
            void itemSelected(int itemNum);
        }

    }

    public interface CallbackItemClickListener {
        //        2 callback
        void itemClickedFromViewHolder(Person artist);
    }


}
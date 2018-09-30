package ru.home.vetukov.test.calculator;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ButtonAdapter extends RecyclerView.Adapter<ButtonAdapter.ButtonHolder> {

    private ArrayList<BottonLine> buttons;
    private View buttonsView;

    public ButtonAdapter(ArrayList<BottonLine> buttons) {
        this.buttons = buttons;
    }

    @NonNull
    @Override
    public ButtonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        buttonsView = inflater.inflate(R.layout.button_lines, parent, false);

        ButtonHolder holder = new ButtonHolder(buttonsView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ButtonHolder buttonHolder, int position) {
        BottonLine buttonLine = buttons.get(position);

        TextView one = buttonHolder.btnOne;
        TextView two = buttonHolder.btnSecond;
        TextView three = buttonHolder.btnThrid;
        TextView four = buttonHolder.btnFourth;
        TextView five = buttonHolder.btnFifth;

        setTextView(one,
                    buttonLine.getBottonFirst().getName(),
                    buttonLine.getBottonFirst().getId());
        setTextView(two,
                    buttonLine.getBottonSecond().getName(),
                    buttonLine.getBottonSecond().getId());
        setTextView(three,
                    buttonLine.getBottonThird().getName(),
                    buttonLine.getBottonThird().getId());
        setTextView(four,
                    buttonLine.getBottonFourth().getName(),
                    buttonLine.getBottonFourth().getId());
        setTextView(five,
                    buttonLine.getBottonFifth().getName(),
                    buttonLine.getBottonFifth().getId());


    }

    public static void setTextView(TextView view, String name, int id) {
        view.setText(name);
        view.setId(id);
    }

    @Override
    public int getItemCount() {
        return buttons.size();
    }



    public class ButtonHolder extends RecyclerView.ViewHolder {

        Button btnOne;
        Button btnSecond;
        Button btnThrid;
        Button btnFourth;
        Button btnFifth;


        public ButtonHolder(@NonNull View itemView) {
            super(itemView);

            this.btnOne = (Button) itemView.findViewById(R.id.first_button);
            this.btnSecond = (Button) itemView.findViewById(R.id.second_button);
            this.btnThrid = (Button) itemView.findViewById(R.id.third_button);
            this.btnFourth = (Button) itemView.findViewById(R.id.fourth_button);
            this.btnFifth = (Button) itemView.findViewById(R.id.fifth_button);

        }

    }
}

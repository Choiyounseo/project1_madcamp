package com.example.user.album_tabb;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by user on 2017-12-28.
 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class fragmentC extends Fragment {

    private String pre_cal;
    private Float result_cal;

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab3calculate, container, false);

        pre_cal = new String();

        final TextView waiting = rootView.findViewById(R.id.Cal);
        final TextView result = rootView.findViewById(R.id.result);


        Button num0 = rootView.findViewById(R.id.num0);
        Button num1 = rootView.findViewById(R.id.num1);
        Button num2 = rootView.findViewById(R.id.num2);
        Button num3 = rootView.findViewById(R.id.num3);
        Button num4 = rootView.findViewById(R.id.num4);
        Button num5 = rootView.findViewById(R.id.num5);
        Button num6 = rootView.findViewById(R.id.num6);
        Button num7 = rootView.findViewById(R.id.num7);
        Button num8 = rootView.findViewById(R.id.num8);
        Button num9 = rootView.findViewById(R.id.num9);
        Button dot = rootView.findViewById(R.id.dot);

        Button plus = rootView.findViewById(R.id.plus);
        Button minus = rootView.findViewById(R.id.minus);
        Button multiple = rootView.findViewById(R.id.mul);
        Button divide = rootView.findViewById(R.id.div);
        Button mod = rootView.findViewById(R.id.mod);

        Button leftPare = rootView.findViewById(R.id.pare1);
        Button rightPare = rootView.findViewById(R.id.pare2);

        Button cal = rootView.findViewById(R.id.cal);

        Button clear = rootView.findViewById(R.id.eraseall);
        final Button up = rootView.findViewById(R.id.up);
        ImageButton backspace = rootView.findViewById(R.id.backspace);

        num0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pre_cal += "0";
                waiting.setText(pre_cal);
            }
        });
        num1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pre_cal += "1";
                waiting.setText(pre_cal);
            }
        });
        num2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pre_cal += "2";
                waiting.setText(pre_cal);
            }
        });
        num3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pre_cal += "3";
                waiting.setText(pre_cal);
            }
        });
        num4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pre_cal += "4";
                waiting.setText(pre_cal);
            }
        });
        num5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pre_cal += "5";
                waiting.setText(pre_cal);
            }
        });
        num6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pre_cal += "6";
                waiting.setText(pre_cal);
            }
        });
        num7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pre_cal += "7";
                waiting.setText(pre_cal);
            }
        });
        num8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pre_cal += "8";
                waiting.setText(pre_cal);
            }
        });
        num9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pre_cal += "9";
                waiting.setText(pre_cal);
            }
        });
        dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pre_cal += ".";
                waiting.setText(pre_cal);
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pre_cal += " + ";
                waiting.setText(pre_cal);
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pre_cal += " - ";
                waiting.setText(pre_cal);
            }
        });
        multiple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pre_cal += " X ";
                waiting.setText(pre_cal);
            }
        });
        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pre_cal += " / ";
                waiting.setText(pre_cal);
            }
        });
        mod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pre_cal += " % ";
                waiting.setText(pre_cal);
            }
        });


        leftPare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pre_cal += "(";
                waiting.setText(pre_cal);
            }
        });
        rightPare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pre_cal += ")";
                waiting.setText(pre_cal);
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pre_cal = "";
                result.setText("");
                waiting.setText(pre_cal);
                result_cal= 0.0f;
            }
        });



        backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pre_cal.length() > 0) {
                    pre_cal = pre_cal.substring(0, pre_cal.length() - 1);
                    waiting.setText(pre_cal);
                }
            }
        });

        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String[] expression = pre_cal.split(" ");

                Float a = Float.parseFloat(expression[0]);
                String operator = expression[1];
                Float b = Float.parseFloat(expression[2]);

                if (operator.equals("+"))
                {
                    result_cal = a + b;
                    result.setText("" + result_cal);
                }
                else if (operator.equals("-"))
                {
                    result_cal = a - b;
                    result.setText("" + result_cal);
                }
                else if (operator.equals("X"))
                {
                    result_cal = a * b;
                    result.setText("" + result_cal);
                }

                else if (operator.equals("/"))
                {
                    if (b.equals(0.0f))
                    {
                        Toast.makeText(getActivity().getApplicationContext(), "0으로 나눌 수 없습니다.", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        result_cal = a / b;
                        result.setText("" + result_cal);
                    }
                }
                else if (operator.equals("%"))
                {
                    if (b.equals(0.0f))
                    {
                        Toast.makeText(getActivity().getApplicationContext(), "0으로 나눌 수 없습니다.", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        result_cal = a % b;
                        result.setText("" + result_cal);
                    }

                }

                up.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        pre_cal = ""+result_cal;
                        result.setText("");
                        waiting.setText(pre_cal);
                        result_cal= 0.0f;
                    }
                });
            }
        });
        return rootView;
    }
}
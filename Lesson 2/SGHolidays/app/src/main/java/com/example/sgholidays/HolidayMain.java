package com.example.sgholidays;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class HolidayMain extends AppCompatActivity {

    ListView lv;
    TextView tvType;
    ArrayAdapter aaHoliday;
    ArrayList<Holiday> holiday1;
    ArrayList<Holiday> holiday2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holiday_main);

        lv = (ListView) this.findViewById(R.id.lvHoliday);
        tvType = (TextView) findViewById(R.id.tvType);

        Intent i = getIntent();
        String type = i.getStringExtra(Intent.EXTRA_TEXT);
        tvType.setText(type);
        // Create a few food objects in Food array
        holiday1 = new ArrayList<Holiday>();
        holiday1.add(new Holiday("New Year's Day", "1 Jan 2017",
                "New Year's Day is a public holiday in Singapore. ... Singapore" +
                        " celebrates the day in accordance with the customs and traditions" +
                        " of the Chinese. As a matter of principle, the celebrations feature " +
                        "an extended fifteen days of revels instead of only on the first day" +
                        " of January."));
        holiday1.add(new Holiday("Labour Day", "1 May 2017", "In Singapore," +
                " May Day (or Labour Day) is celebrated on the 1st of May each year as a mark" +
                " of solidarity amongst workers. The celebration of May Day as a public " +
                "holiday began only in 1960 after the People's Action Party came into " +
                "power. ... It also makes it easier for workers to come together for" +
                " celebrations."));

        holiday2 = new ArrayList<Holiday>();
        holiday2.add(new Holiday("Chinese New Year", "28-29 Jan 2017",
                "It is celebrated from the 1st day of the 1st lunar month to the 15th day" +
                        " of the 1st lunar month. The final (15th) day is called Lantern " +
                        "Festival, and the night before the 1st day is called Chinese New " +
                        "Year's Eve (Chuxi in Chinese pinyin)."));
        holiday2.add(new Holiday("Good Friday", "14 April 2017", "Good Friday," +
                " the Friday before Easter, commemorates the day Jesus Christ was crucified." +
                " It is a day of fasting and penance. In Singapore, Good Friday is a public " +
                "holiday. ... It commemorates the final day of Christ's death."));

        // Link this Activity object, the row.xml layout for
        //  each row and the food String array together
        if (type == "Secular") {
            aaHoliday = new HolidayAdapter(this, R.layout.holidayrow, holiday1);
            lv.setAdapter(aaHoliday);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Holiday selectedHoliday = holiday1.get(position);

                    Toast.makeText(HolidayMain.this, selectedHoliday.getName()
                                    + " Date: " + selectedHoliday.getDate() + "\n"
                                    + selectedHoliday.getIntro(),
                            Toast.LENGTH_LONG).show();
                }
            });
        } else {
            aaHoliday = new HolidayAdapter(this, R.layout.holidayrow, holiday2);
            lv.setAdapter(aaHoliday);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Holiday selectedHoliday = holiday2.get(position);

                    Toast.makeText(HolidayMain.this, selectedHoliday.getName()
                                    + " Date: " + selectedHoliday.getDate() + "\n"
                                    + selectedHoliday.getIntro(),
                            Toast.LENGTH_LONG).show();
                }
            });
        }

    }
}

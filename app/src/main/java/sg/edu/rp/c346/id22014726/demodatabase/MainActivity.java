package sg.edu.rp.c346.id22014726.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert;
    Button btnGetTasks;
    TextView tvResults;
    ListView lv;
    EditText editDescription;
    EditText editDate;
    ArrayAdapter<String> aa;
    ArrayList<Task> al;
    ArrayAdapter<Task> adapter;
    boolean asc = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        btnGetTasks = findViewById(R.id.btnGetTasks);
        tvResults = findViewById(R.id.tvResults);
        lv = findViewById(R.id.lv);
        editDescription = findViewById(R.id.editDescription);
        editDate = findViewById(R.id.editDate);

        al = new ArrayList<>();
        ArrayAdapter adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, al);
        lv.setAdapter(adapter);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create the DBHelper object, passing in the activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                //Insert a task
                db.insertTask("Submit RJ", "25 Apr 2021");
                db.insertTask(editDescription.getText().toString(), editDate.getText().toString());
            }
        });

        btnGetTasks.setOnClickListener(new View.OnClickListener() {
            private boolean ascOrder = true;
            @Override
            public void onClick(View v) {
                //Create the DBHelper object, passing in the activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                //Insert a task
                ArrayList<String> data = db.getTaskContent();

                //Retrieve all tasks from database table
                al = db.getTasks();

                if (ascOrder) {
                    data = db.getTasksAsc();
                } else {
                    data = db.getTasksDesc();
                }
                ArrayList<Task> data1 = db.getTasks();
                db.close();

                al.clear();
                adapter.notifyDataSetChanged();

                String txt = "";
                for (int i = 0; i < data.size(); i++) {
                    Log.d("Database Content", i + ". "+ data.get(i));
                    txt += i + ". " + data.get(i) + "\n";
                }
                tvResults.setText(txt);

                ascOrder = !ascOrder;
            }
        });


    }
}
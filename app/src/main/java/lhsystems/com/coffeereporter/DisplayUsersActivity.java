package lhsystems.com.coffeereporter;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;

import lhsystems.com.coffeereporter.db.dao.UserDao;
import lhsystems.com.coffeereporter.db.entity.User;


public class DisplayUsersActivity extends ActionBarActivity {

    private UserDao userDao;
    private List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_users);
        this.refresh();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_users, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void refresh(View view){
        this.refresh();
    }

    private void refresh() {
        userDao = new UserDao(this);
        users = userDao.findAll();
        ListView listView =  (ListView) findViewById(R.id.users);
        String [] arr = new String[users.size()];
        for(int i=0 ; i < users.size() ; i++) {
            arr[i] = users.get(i).toString();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, arr);
        listView.setAdapter(adapter);
    }
}

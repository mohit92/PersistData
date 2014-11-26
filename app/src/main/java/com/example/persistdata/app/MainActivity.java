package com.example.persistdata.app;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends ActionBarActivity {

  private static final String TAG = "HelpshiftDebug";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    //HashMap to store
    HashMap hashMap = new HashMap();
    hashMap.put("foo", "bar");
    hashMap.put("foofoo", "barbar");
    hashMap.put("abc",  "def");

    //ArrayList to store
    ArrayList<String> stringArrayList = new ArrayList<String>();
    stringArrayList.add(0, "foo");
    stringArrayList.add(1, "bar");

    File sdCard = Environment.getExternalStoragePublicDirectory("Helpshift");
    Log.d(TAG, "directory path: " + sdCard.getAbsolutePath());
    Log.d(TAG,"--------------------------------------------");
    File dir = new File (sdCard.getAbsolutePath());
    if(!dir.exists()) {
      dir.mkdirs();
    }

    try {
      //write hashmap to a file named HashMap
      File file = new File(dir, "HashMap");
      FileOutputStream fos = new FileOutputStream(file);
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      oos.writeObject(hashMap);
      oos.close();
      Log.d(TAG, "written hashmap is: " + hashMap.toString());

      //read the hashmap from written file
      FileInputStream fis = new FileInputStream(file);
      ObjectInputStream ois = new ObjectInputStream(fis);
      HashMap readHashMap = (HashMap) ois.readObject();
      ois.close();
      Log.d(TAG, "read hashmap is: " + readHashMap.toString());
      Log.d(TAG, "value for key \"foo\" is: "+readHashMap.get("foo"));
      Log.d(TAG,"--------------------------------------------");

      //write arrayList to a file named ArrayList
      file = new File(dir, "ArrayList");
      fos = new FileOutputStream(file);
      oos = new ObjectOutputStream(fos);
      oos.writeObject(stringArrayList);
      oos.close();
      Log.d(TAG, "written arrayList is: "+stringArrayList.toString());

      //read the arrayList from written file
      fis = new FileInputStream(file);
      ois = new ObjectInputStream(fis);
      ArrayList<String> readArrayList = (ArrayList<String>) ois.readObject();
      ois.close();
      Log.d(TAG, "read arrayList is: "+ readArrayList.toString());
      Log.d(TAG, "value at index 0 is "+ readArrayList.get(0));
      Log.d(TAG,"--------------------------------------------");

    } catch (ClassNotFoundException e1) {
      e1.printStackTrace();
    } catch (OptionalDataException e1) {
        e1.printStackTrace();
    } catch (FileNotFoundException e1) {
      e1.printStackTrace();
    } catch (StreamCorruptedException e1) {
      e1.printStackTrace();
    } catch (IOException e1) {
      e1.printStackTrace();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();
    if (id == R.id.action_settings) {
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
}

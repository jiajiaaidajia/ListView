package com.example.guojiajia.myapplication;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParserException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView showList;
    private static ArrayList<String> showText = new ArrayList<String>();
    private static ArrayList<Boolean> isShow = new ArrayList<Boolean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showList = (ListView) findViewById(R.id.show_list);
        readXMLToDisplay(getApplicationContext());
        //ListAdapter adapter = new ListAdapter(this,showText,isShow);
        ListAdapter adapter = new ListAdapter(this,showText,isShow, new ListAdapter.OnButtenClick() {
            @Override
            public void OnButtenClick(View view) {
                Toast.makeText(getBaseContext(),"ssssssssssssss",Toast.LENGTH_LONG).show();
            }
        });
        if(showText.size()==0){
            readXMLToDisplay(getBaseContext());
        }
        showList.setAdapter(adapter);
    }
    private  void readXMLToDisplay(Context context) {
                 try (XmlResourceParser xrp = context.getResources().getXml(R.xml.runin_test_list)) {
                         while (xrp.next() != XmlResourceParser.START_TAG) {
                                 continue;
                             }
                         xrp.next();
                       while (xrp.getEventType() != XmlResourceParser.END_TAG) {
                            while (xrp.getEventType() != XmlResourceParser.START_TAG) {
                                      xrp.next();
                                  }
                              if (xrp.getName().equals("boolean")) {
                                      String text = xrp.getAttributeValue(0);
                                      showText.add(text);
                                      String isshow = xrp.getAttributeValue(1);
                                      isShow.add(Boolean.parseBoolean(isshow));

                                  xrp.next();
                              }
                           while (xrp.getEventType() != XmlResourceParser.END_TAG) {
                               xrp.next();
                           }
                           xrp.next();

                            }
                    } catch (XmlPullParserException xppe) {
                } catch (java.io.IOException ioe) {
                  }

            }

}

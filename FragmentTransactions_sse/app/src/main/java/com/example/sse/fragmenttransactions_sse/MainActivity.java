package com.example.sse.fragmenttransactions_sse;

//import android.app.Activity;
//import android.app.FragmentManager;
//import android.app.FragmentTransaction;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity{


    //Two basic ways of working with fragments.
    //
    //1. Just include them in the Activity's layout.
    //
    //2. Instantatiate and work with them in code.
    // in code you have much more control.

    //3. create objects to reference the views, including fragments.
private
    Frag_One  f1;
    Frag_Two  f2;
    Frag_Three  f3;

    android.support.v4.app.FragmentManager fm;  // we will need this later.

    private Button btnFrag1;
    private Button btnFrag2;
    private Button btnFrag3;
    private LinearLayout FragLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    //4. get references for our views.

        btnFrag1 = (Button) findViewById(R.id.btnFrag1);
        btnFrag2 = (Button) findViewById(R.id.btnFrag2);
        btnFrag3 = (Button) findViewById(R.id.btnFrag3);
        FragLayout = (LinearLayout) findViewById(R.id.FragLayout);

//        f1 = (Frag_One) findViewById(R.id.frag1);  //Hey, w/hy won't this work for fragments?  Wait does the fragment even exist?

    //5a.  We actually have to create the fragments ourselves.  Where is our friend, R?
        f1 = new Frag_One();
        f2 = new Frag_Two();
        f3 = new Frag_Three();

    //5b. Grab a reference to the Activity's Fragment Manager, Every Activity has one!
       fm = getSupportFragmentManager ();  //that was easy.
//         fm = getSupportFragmentManager();  // **//use this call instead, if your activity extends AppCompatActivity


    //5c. Now we can "plop" fragment(s) into our container.
        fm.beginTransaction ()  //Create a reference to a fragment transaction.
          .add(R.id.FragLayout, f1, "tag1") //now we have added our fragement to our Activity programmatically.  The other fragments exist, but have not been added yet.
         .addToBackStack ("myFrag1")  //why do we do this?
         .commit ();  //don't forget to commit your changes.  It is a transaction after all.

    btnFrag1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showFrag1();
        }
    });

        btnFrag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFrag2();
            }
        });

        btnFrag3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFrag3();
            }
        });

    }

public void showFrag1() {



    f1 = (Frag_One) fm.findFragmentByTag("tag1");   //what should we do if f1 doesn't exist anymore?  How do we check and how do we fix?
    fm.beginTransaction ()  //Create a reference to a fragment transaction
    .replace(R.id.FragLayout, f1)
    .addToBackStack ("myFrag1")//why does this not *always* crash?
    .commit();
}

    public void showFrag2() {

        if (f2 == null)
          f2 = new Frag_Two();

        fm.beginTransaction ()  //Create a reference to a fragment transaction.
          .replace(R.id.FragLayout, f2)
          .addToBackStack ("myFrag2")  //why do we do this?
          .commit();
    }


    public void showFrag3() {

        fm.beginTransaction ()  //Create a reference to a fragment transaction.
          //what would happen if f1, f2, or f3 were null?  how would we check and fix this?
        .replace(R.id.FragLayout,f3)
        .addToBackStack ("myFrag3")
        .commit();
    }
}

package com.integro.bsk.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.integro.bsk.R;
import com.integro.bsk.adapters.WhatWeDoAdapter;
import com.integro.bsk.model.WhatWeDo;

import java.util.ArrayList;

public class WhatWeDoActivity extends AppCompatActivity {

    /* RecyclerView rvWhatWeDo;
     String[] whatwedo = {"Dhisha", "Children’s Parliament", "Drinking Water", "Training & Consultancy",
             "DB Action India", "DB Tech", "Micro-credit", "Young at Risk “YaR”"};
     LinearLayoutManager manager;*/
    // WhatWeDoAdapter whatWeDoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_what_we_do);
        //getSupportActionBar().hide();
       /* if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayShowTitleEnabled(false);*/


        RecyclerView rvWhatWeDo;
        LinearLayoutManager manager;
        WhatWeDoAdapter whatWeDoAdapter;
        ArrayList<WhatWeDo> whatWeDoArrayList;
        WhatWeDo whatWeDo;

        rvWhatWeDo = findViewById(R.id.rv_WhatWeDo);
        manager = new LinearLayoutManager(this);
        rvWhatWeDo.setLayoutManager(manager);

        whatWeDoArrayList = new ArrayList<>();

        whatWeDo = new WhatWeDo();
        whatWeDo.setImageId(R.drawable.navigaton);
        whatWeDo.setTitle("Dhisha");
        whatWeDo.setDesc("Career guidance and job placements for unemployed rural and marginalized youth.");
        whatWeDo.setLink("https://boscosevakendra.org/dhisha");
        whatWeDoArrayList.add(whatWeDo);


        whatWeDo = new WhatWeDo();
        whatWeDo.setImageId(R.drawable.students);
        whatWeDo.setTitle("Children’s Parliament");
        whatWeDo.setDesc("A forum for child participation in governance and supplementary learning in tuition centers.");
        whatWeDo.setLink("https://boscosevakendra.org/children-parliament");
        whatWeDoArrayList.add(whatWeDo);

        whatWeDo = new WhatWeDo();
        whatWeDo.setImageId(R.drawable.waterdrop);
        whatWeDo.setTitle("Drinking Water");
        whatWeDo.setDesc("Donec id elit non mi porta gravida at eget metus. Fusce dapibus.");
        whatWeDo.setLink("https://boscosevakendra.org/drinking-water");
        whatWeDoArrayList.add(whatWeDo);


        whatWeDo = new WhatWeDo();
        whatWeDo.setImageId(R.drawable.bulb);
        whatWeDo.setTitle("Training & Consultancy");
        whatWeDo.setDesc("Providing Training and Job Consultancy services to the needy children");
        whatWeDo.setLink("https://boscosevakendra.org");
        whatWeDoArrayList.add(whatWeDo);

        whatWeDo = new WhatWeDo();
        whatWeDo.setImageId(R.drawable.fire);
        whatWeDo.setTitle("DB Action India");
        whatWeDo.setDesc("DBAI is the national network of the regional networks of Don Bosco human and social development organizations with constituent partners across India.");
        whatWeDo.setLink("https://boscosevakendra.org/db-action-india");
        whatWeDoArrayList.add(whatWeDo);

        whatWeDo = new WhatWeDo();
        whatWeDo.setImageId(R.drawable.setting);
        whatWeDo.setTitle("DB Tech");
        whatWeDo.setDesc("Don Bosco Tech. is a network of 125 Don Bosco skill training centers which provides employment-linked and market-driven short duration vocational courses to the economically and socially marginalized youth");
        whatWeDo.setLink("https://boscosevakendra.org/db-tech");
        whatWeDoArrayList.add(whatWeDo);

        whatWeDo = new WhatWeDo();
        whatWeDo.setImageId(R.drawable.umberilla);
        whatWeDo.setTitle("Micro-credit");
        whatWeDo.setDesc("Micro-credit program is a thrift and credit programme for the marginalized women to generate employment.");
        whatWeDo.setLink("https://boscosevakendra.org/micro-credit");
        whatWeDoArrayList.add(whatWeDo);

        whatWeDo = new WhatWeDo();
        whatWeDo.setImageId(R.drawable.contact);
        whatWeDo.setTitle("Young at Risk “YaR”");
        whatWeDo.setDesc("With a vision to strive for the creation of a child-friendly society where all the young in difficult situation");
        whatWeDo.setLink("https://boscosevakendra.org/yar");
        whatWeDoArrayList.add(whatWeDo);

        whatWeDoAdapter = new WhatWeDoAdapter(this, whatWeDoArrayList);
        rvWhatWeDo.setAdapter(whatWeDoAdapter);
    }


}


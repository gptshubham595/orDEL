package com.shubham.ordel;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.asksira.loopingviewpager.LoopingViewPager;
import com.rd.PageIndicatorView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    LoopingViewPager viewPager;
    String hostel="",room="";
    DemoInfiniteAdapter adapter;
    PageIndicatorView indicatorView;
    String TAG = "MainActivity";
    String payeeAddress = "gptshubham595@oksbi";
    String payeeName = "Shubham Kumar Gupta";
    String transactionNote = "OrDEL";
    String currencyUnit = "INR";
    EditText samosa, dabeli, cake, chicken, babycorn, frimaggi, maggi,vada;
    TextView samosaadd, dabeliadd, cakeadd, chickenadd, babycornadd, frimaggiadd, maggiadd,vadaadd;
    TextView samosaminus, dabeliminus, cakeminus, chickenminus, babycornminus, frimaggiminus, maggiminus,vadaminus;
    private int currentDataSet = 1;
    ImageView cart;
    public static int total=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cart=findViewById(R.id.cart);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                total=Integer.parseInt(samosa.getText().toString().trim())*10
                        +Integer.parseInt(dabeli.getText().toString().trim())*45
                        +Integer.parseInt(cake.getText().toString().trim())*440
                        +Integer.parseInt(chicken.getText().toString().trim())*165
                        +Integer.parseInt(babycorn.getText().toString().trim())*135
                        +Integer.parseInt(frimaggi.getText().toString().trim())*28
                        +Integer.parseInt(maggi.getText().toString().trim())*25
                        +Integer.parseInt(vada.getText().toString().trim())*35;
                Toast.makeText(MainActivity.this,""+ total, Toast.LENGTH_SHORT).show();

                if(total!=0)
                {showDialog(MainActivity.this);}
                else
                    Toast.makeText(MainActivity.this, "SELECT ATEAST 1", Toast.LENGTH_SHORT).show();
            }
        });
        samosa = findViewById(R.id.samosatotal);
        dabeli = findViewById(R.id.dabelitotal);
        cake = findViewById(R.id.caketotal);
        chicken = findViewById(R.id.chickentotal);
        babycorn = findViewById(R.id.babytotal);
        frimaggi = findViewById(R.id.frimaggitotal);
        maggi = findViewById(R.id.maggitotal);
        vada = findViewById(R.id.vadatotal);

        samosa.setEnabled(false);
        dabeli.setEnabled(false);
        cake.setEnabled(false);
        chicken.setEnabled(false);
        babycorn.setEnabled(false);
        frimaggi.setEnabled(false);
        maggi.setEnabled(false);
        vada.setEnabled(false);

        samosaadd = findViewById(R.id.samosaadd);
        dabeliadd = findViewById(R.id.dabeliadd);
        cakeadd = findViewById(R.id.cakeadd);
        chickenadd = findViewById(R.id.chickenadd);
        babycornadd = findViewById(R.id.babyadd);
        frimaggiadd = findViewById(R.id.friadd);
        maggiadd = findViewById(R.id.maggiadd);
        vadaadd = findViewById(R.id.vadaadd);

        samosaminus = findViewById(R.id.samosaminus);
        dabeliminus = findViewById(R.id.dabeliminus);
        cakeminus = findViewById(R.id.cakeminus);
        chickenminus = findViewById(R.id.chickenminus);
        babycornminus = findViewById(R.id.babyminus);
        frimaggiminus = findViewById(R.id.friminus);
        maggiminus = findViewById(R.id.maggiminus);
        vadaminus = findViewById(R.id.vadaminus);

        vadaadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int s = Integer.parseInt(vada.getText().toString().trim());
                s++;
                if (s > 10) {
                    Toast.makeText(MainActivity.this, "Sorry Not More than 10", Toast.LENGTH_SHORT).show();
                } else if (s > 0) {
                    vada.setText("" + s);
                }
            }
        });
        samosaadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int s = Integer.parseInt(samosa.getText().toString().trim());
                s++;
                if (s > 10) {
                    Toast.makeText(MainActivity.this, "Sorry Not More than 10", Toast.LENGTH_SHORT).show();
                } else if (s > 0) {
                    samosa.setText("" + s);
                }
            }
        });
        dabeliadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int s = Integer.parseInt(dabeli.getText().toString().trim());
                s++;
                if (s > 10) {
                    Toast.makeText(MainActivity.this, "Sorry Not More than 10", Toast.LENGTH_SHORT).show();
                } else if (s > 0) {
                    dabeli.setText("" + s);
                }
            }
        });
        cakeadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int s = Integer.parseInt(cake.getText().toString().trim());
                s++;
                if (s > 10) {
                    Toast.makeText(MainActivity.this, "Sorry Not More than 10", Toast.LENGTH_SHORT).show();
                } else if (s > 0) {
                    cake.setText("" + s);
                }
            }
        });
        chickenadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int s = Integer.parseInt(chicken.getText().toString().trim());
                s++;
                if (s > 10) {
                    Toast.makeText(MainActivity.this, "Sorry Not More than 10", Toast.LENGTH_SHORT).show();
                } else if (s > 0) {
                    chicken.setText("" + s);
                }
            }
        });
        babycornadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int s = Integer.parseInt(babycorn.getText().toString().trim());
                s++;
                if (s > 10) {
                    Toast.makeText(MainActivity.this, "Sorry Not More than 10", Toast.LENGTH_SHORT).show();
                } else if (s > 0) {
                    babycorn.setText("" + s);
                }
            }
        });
        frimaggiadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int s = Integer.parseInt(frimaggi.getText().toString().trim());
                s++;
                if (s > 10) {
                    Toast.makeText(MainActivity.this, "Sorry Not More than 10", Toast.LENGTH_SHORT).show();
                } else if (s > 0) {
                    frimaggi.setText("" + s);
                }
            }
        });
        maggiadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int s = Integer.parseInt(maggi.getText().toString().trim());
                s++;
                if (s > 10) {
                    Toast.makeText(MainActivity.this, "Sorry Not More than 10", Toast.LENGTH_SHORT).show();
                } else if (s > 0) {
                    maggi.setText("" + s);
                }
            }
        });


        vadaminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int s=Integer.parseInt(vada.getText().toString().trim());
                s--;
                if(s<0){
                    Toast.makeText(MainActivity.this, "Sorry Not less than 0", Toast.LENGTH_SHORT).show();   vada.setText("0");
                }else{
                    vada.setText(""+s);
                }
            }
        });
        samosaminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int s=Integer.parseInt(samosa.getText().toString().trim());
                s--;
                if(s<0){
                    Toast.makeText(MainActivity.this, "Sorry Not less than 0", Toast.LENGTH_SHORT).show();   samosa.setText("0");
                }else{
                    samosa.setText(""+s);
                }
            }
        });
        dabeliminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int s=Integer.parseInt(dabeli.getText().toString().trim());
                s--;
                if(s<0){
                    Toast.makeText(MainActivity.this, "Sorry Not less than 0", Toast.LENGTH_SHORT).show();   dabeli.setText("0");
                }else{
                    dabeli.setText(""+s);
                }
            }
        });
        cakeminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int s=Integer.parseInt(cake.getText().toString().trim());
                s--;
                if(s<0){
                    Toast.makeText(MainActivity.this, "Sorry Not less than 0", Toast.LENGTH_SHORT).show();   cake.setText("0");
                }else{
                    cake.setText(""+s);
                }
            }
        });
        chickenminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int s=Integer.parseInt(chicken.getText().toString().trim());
                s--;
                if(s<0){
                    Toast.makeText(MainActivity.this, "Sorry Not less than 0", Toast.LENGTH_SHORT).show();   chicken.setText("0");
                }else {
                    chicken.setText(""+s);
                }
            }
        });
        babycornminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int s=Integer.parseInt(babycorn.getText().toString().trim());
                s--;
                if(s<0){
                    Toast.makeText(MainActivity.this, "Sorry Not less than 0", Toast.LENGTH_SHORT).show();   babycorn.setText("0");
                }else {
                    babycorn.setText(""+s);
                }
            }
        });
        frimaggiminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int s=Integer.parseInt(frimaggi.getText().toString().trim());
                s--;
                if(s<0){
                    Toast.makeText(MainActivity.this, "Sorry Not less than 0", Toast.LENGTH_SHORT).show();   frimaggi.setText("0");
                }else {
                    frimaggi.setText(""+s);
                }
            }
        });
        maggiminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int s=Integer.parseInt(maggi.getText().toString().trim());
                s--;
                if(s<0){
                    Toast.makeText(MainActivity.this, "Sorry Not less than 0", Toast.LENGTH_SHORT).show();   maggi.setText("0");
                }else {
                    maggi.setText(""+s);
                }
            }
        });







        viewPager = findViewById(R.id.viewpager);
        indicatorView = findViewById(R.id.indicator);
        adapter = new DemoInfiniteAdapter(this, createDummyItems(), true);
        viewPager.setAdapter(adapter);

        //Custom bind indicator
        indicatorView.setCount(viewPager.getIndicatorCount());
        viewPager.setIndicatorPageChangeListener(new LoopingViewPager.IndicatorPageChangeListener() {
            @Override
            public void onIndicatorProgress(int selectingPosition, float progress) {
                indicatorView.setProgress(selectingPosition, progress);
            }

            @Override
            public void onIndicatorPageChange(int newIndicatorPosition) {
//                indicatorView.setSelection(newIndicatorPosition);
            }
        });

        View.OnClickListener pageSelector = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer number = Integer.valueOf(((Button) v).getText().toString());
                viewPager.setCurrentItem(adapter.isInfinite() ? number : number - 1);
            }
        };

    }

    private void payamt(int total) {
        String amount = ""+total;
        try{
        Uri uri = Uri.parse("upi://pay?pa="+payeeAddress+"&pn="+payeeName+"&tn="+transactionNote+
                "&am="+amount+"&cu="+currencyUnit);
        Log.d(TAG, "onClick: uri: "+uri);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivityForResult(intent,1);}catch (Exception e){
        Toast.makeText(MainActivity.this, "Sorry! Please Install GPAY or BHIM", Toast.LENGTH_SHORT).show();e.printStackTrace();
    }}

    void sendwhatsapp(String number,String text) {
        PackageManager pm=getPackageManager();
        try {
            PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
            Uri uri = Uri.parse("smsto:" + number);
            Intent i = new Intent(Intent.ACTION_SENDTO, uri);
            i.setPackage("com.whatsapp");
            i.putExtra(Intent.EXTRA_TEXT, text);
            i.setType("text/plain");
            startActivity(Intent.createChooser(i, "Share"));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, "WhatsApp not Installed", Toast.LENGTH_SHORT)
                    .show();
            try{
            Uri uri = Uri.parse("market://details?id=com.whatsapp");
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(goToMarket);}catch (Exception e1){e1.printStackTrace();
                Toast.makeText(this, "FAILED wahtsapp", Toast.LENGTH_SHORT).show();}
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: requestCode: " + requestCode);
        Log.d(TAG, "onActivityResult: resultCode: " + resultCode);
        //txnId=UPI20b6226edaef4c139ed7cc38710095a3&responseCode=00&ApprovalRefNo=null&Status=SUCCESS&txnRef=undefined
        //txnId=UPI608f070ee644467aa78d1ccf5c9ce39b&responseCode=ZM&ApprovalRefNo=null&Status=FAILURE&txnRef=undefined
        Date currentTime = Calendar.getInstance().getTime();
        if (data != null) {
            Log.d(TAG, "onActivityResult: data: " + data.getStringExtra("response"));
            String res = data.getStringExtra("response");
            String search = "SUCCESS";
            if (res.toLowerCase().contains(search.toLowerCase())) {
                Toast.makeText(this, "Payment Successful", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "HOSTEL "+hostel+" Room No.: "+room+" DATE: "+currentTime +" Payment DONE txnID: "+ requestCode, Toast.LENGTH_SHORT).show();
                sendwhatsapp("6204553564","HOSTEL "+hostel+" Room No.: "+room+" DATE: "+currentTime +" Payment DONE txnID: "+ requestCode);
            } else {
                Toast.makeText(this, "Payment Failed", Toast.LENGTH_SHORT).show();
            }
        }

    }
    public void showDialog(final Activity activity) {

        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.newcustom_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        final EditText hostelis=dialog.findViewById(R.id.hostel);
        final EditText roomis=dialog.findViewById(R.id.room);
        final TextView price=dialog.findViewById(R.id.price);
        price.setText(""+total);

        Button cancel = dialog.findViewById(R.id.retry);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        Button ok = dialog.findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                room=roomis.getText().toString().trim();
               hostel=hostelis.getText().toString().trim();

             if((!TextUtils.isEmpty(room)) && (hostel.equals("SIANG") || hostel.equals("BARAK") ||hostel.equals("BRAHMAPUTRA") ||hostel.equals("DHANSIRI") ||hostel.equals("DIBANG") ||hostel.equals("DIHING") || hostel.equals("KAMENG")|| hostel.equals("KAPILI") || hostel.equals("LOHIT") || hostel.equals("MANAS") || hostel.equals("SUBHANSIRI") || hostel.equals("UMIAM" )))
             {dialog.cancel();payamt(total);}
             else{
                 hostelis.setError("Write Correct");

                 Toast.makeText(activity, "Wrtie Room No. and Hostel eg. SIANG , BRAHMAPUTRA", Toast.LENGTH_SHORT).show();
             }
            }
        });
            dialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewPager.resumeAutoScroll();
    }

    @Override
    protected void onPause() {
        viewPager.pauseAutoScroll();
        super.onPause();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.action_about:{
                Intent i=new Intent(this, About.class);
                startActivity(i);
            } return true;

        }

        return super.onOptionsItemSelected(item);
    }

    private ArrayList<Integer> createDummyItems() {
        ArrayList<Integer> items = new ArrayList<>();
        items.add(0, 1);
        items.add(1, 2);
        items.add(2, 3);
        items.add(3, 4);
        items.add(4, 5);
        items.add(5, 6);
        items.add(6, 0);
        return items;
    }

    private ArrayList<Integer> createSecondDummyItems() {
        ArrayList<Integer> items = new ArrayList<>();
        items.add(0, 1);
        items.add(1, 2);
        return items;
    }

    private ArrayList<Integer> createThirdDummyItems() {
        ArrayList<Integer> items = new ArrayList<>();
        items.add(0, 1);
        return items;
    }

    private void changeDataset() {
        if (currentDataSet == 1) {
            adapter.setItemList(createSecondDummyItems());
            currentDataSet++;
            toggleSixButtons(false);
        } else if (currentDataSet == 2) {
            adapter.setItemList(createThirdDummyItems());
            currentDataSet++;
            toggleSixButtons(false);
        } else {
            adapter.setItemList(createDummyItems());
            currentDataSet = 1;
            toggleSixButtons(true);
        }
        indicatorView.setCount(viewPager.getIndicatorCount());
        viewPager.reset();
    }

    private void toggleSixButtons(boolean isVisible) {
        int status = isVisible ? View.VISIBLE : View.GONE;
    }
}
package com.shubham.ordel;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.asksira.loopingviewpager.LoopingViewPager;
import com.rd.PageIndicatorView;
import com.shashank.sony.fancytoastlib.FancyToast;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    LoopingViewPager viewPager;
    public static String hostel="",room="",name="",number="",order="";
    DemoInfiniteAdapter adapter;
    PageIndicatorView indicatorView;
    Uri imageUri;
    String TAG = "MainActivity";
    String payeeAddress = "vanshaj.wore@okaxis";
    String payeeName = "Vansaj Wore";
    String transactionNote = "OrDEL: Paid To Vansaj Wore";
    String currencyUnit = "INR";
    EditText samosa, dabeli, cake, chicken, babycorn, frimaggi, maggi,vada;
    TextView samosaadd, dabeliadd, cakeadd, chickenadd, babycornadd, frimaggiadd, maggiadd,vadaadd;
    TextView samosaminus, dabeliminus, cakeminus, chickenminus, babycornminus, frimaggiminus, maggiminus,vadaminus;

    File imagepath;
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
                int sam=Integer.parseInt(samosa.getText().toString().trim());
                int dab=Integer.parseInt(dabeli.getText().toString().trim());
                int ca=Integer.parseInt(cake.getText().toString().trim());
                int chic=Integer.parseInt(chicken.getText().toString().trim());
                int baby=Integer.parseInt(babycorn.getText().toString().trim());
                int fri=Integer.parseInt(frimaggi.getText().toString().trim());
                int mag=Integer.parseInt(maggi.getText().toString().trim());
                int va=Integer.parseInt(vada.getText().toString().trim());

                total=Integer.parseInt(samosa.getText().toString().trim())*10
                        +Integer.parseInt(dabeli.getText().toString().trim())*45
                        +Integer.parseInt(cake.getText().toString().trim())*440
                        +Integer.parseInt(chicken.getText().toString().trim())*165
                        +Integer.parseInt(babycorn.getText().toString().trim())*135
                        +Integer.parseInt(frimaggi.getText().toString().trim())*28
                        +Integer.parseInt(maggi.getText().toString().trim())*25
                        +Integer.parseInt(vada.getText().toString().trim())*35;

                FancyToast.makeText(MainActivity.this,"TOTAL :"+ total,FancyToast.LENGTH_LONG, FancyToast.WARNING,false ).show();


                if(total!=0)
                {order="";
                    if(sam!=0)
                    order+="\n SAMOSA "+" Quantity: "+sam;
                    if(dab!=0)
                        order+="\n DABELI "+" Quantity: "+dab;
                    if(ca!=0)
                        order+="\n CAKE "+" Quantity: "+ca;
                    if(chic!=0)
                        order+="\n CHICKEN "+" Quantity: "+chic;
                    if(baby!=0)
                        order+="\n BABYCORN "+" Quantity: "+baby;
                    if(fri!=0)
                        order+="\n FRIED MAGGI "+" Quantity: "+fri;
                    if(mag!=0)
                        order+="\n MAGGI "+" Quantity: "+mag;
                    if(va!=0)
                        order+="\n VADA PAO "+" Quantity: "+va;

                    showDialog(MainActivity.this);}
                else
                    FancyToast.makeText(MainActivity.this,"SELECT ATEAST 1",FancyToast.LENGTH_LONG, FancyToast.ERROR,false ).show();

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
                    FancyToast.makeText(MainActivity.this, "Sorry Not More than 10", FancyToast.LENGTH_SHORT, FancyToast.ERROR,false ).show();
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
                    FancyToast.makeText(MainActivity.this, "Sorry Not More than 10", FancyToast.LENGTH_SHORT, FancyToast.ERROR,false ).show();
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
                    FancyToast.makeText(MainActivity.this, "Sorry Not More than 10", FancyToast.LENGTH_SHORT, FancyToast.ERROR,false ).show();
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
                    FancyToast.makeText(MainActivity.this, "Sorry Not More than 10", FancyToast.LENGTH_SHORT, FancyToast.ERROR,false ).show();
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
                    FancyToast.makeText(MainActivity.this,"Sorry Not More than 10",FancyToast.LENGTH_LONG, FancyToast.ERROR,false ).show();
                    
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
                    FancyToast.makeText(MainActivity.this, "Sorry Not More than 10", FancyToast.LENGTH_SHORT, FancyToast.ERROR,false ).show();
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
                    FancyToast.makeText(MainActivity.this, "Sorry Not More than 10", FancyToast.LENGTH_SHORT, FancyToast.ERROR,false ).show();
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
                    FancyToast.makeText(MainActivity.this, "Sorry Not More than 10", FancyToast.LENGTH_SHORT, FancyToast.ERROR,false ).show();
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
                    FancyToast.makeText(MainActivity.this, "Sorry Not less than 0", FancyToast.LENGTH_SHORT, FancyToast.ERROR,false ).show();   vada.setText("0");
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
                    FancyToast.makeText(MainActivity.this, "Sorry Not less than 0", FancyToast.LENGTH_SHORT, FancyToast.ERROR,false ).show();   samosa.setText("0");
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
                    FancyToast.makeText(MainActivity.this, "Sorry Not less than 0", FancyToast.LENGTH_SHORT, FancyToast.ERROR,false ).show();   dabeli.setText("0");
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
                    FancyToast.makeText(MainActivity.this, "Sorry Not less than 0", FancyToast.LENGTH_SHORT, FancyToast.ERROR,false ).show();   cake.setText("0");
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
                    FancyToast.makeText(MainActivity.this, "Sorry Not less than 0", FancyToast.LENGTH_SHORT, FancyToast.ERROR,false ).show();   chicken.setText("0");
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
                    FancyToast.makeText(MainActivity.this, "Sorry Not less than 0", FancyToast.LENGTH_SHORT, FancyToast.ERROR,false ).show();   babycorn.setText("0");
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
                    FancyToast.makeText(MainActivity.this, "Sorry Not less than 0", FancyToast.LENGTH_SHORT, FancyToast.ERROR,false ).show();   frimaggi.setText("0");
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
                    FancyToast.makeText(MainActivity.this, "Sorry Not less than 0", FancyToast.LENGTH_SHORT, FancyToast.ERROR,false ).show();   maggi.setText("0");
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
            FancyToast.makeText(this, "PAYMENT", FancyToast.LENGTH_SHORT, FancyToast.WARNING,false ).show();
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivityForResult(intent,1);}
        catch (Exception e){
            FancyToast.makeText(this,"Sorry! Please Install GPAY or BHIM",FancyToast.LENGTH_LONG, FancyToast.ERROR,false ).show();
    }}


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
                    FancyToast.makeText(this,"Payment Successful !",FancyToast.LENGTH_LONG, FancyToast.SUCCESS,false ).show();
                FancyToast.makeText(this,"HOSTEL "+hostel+" Room No.: "+room+" DATE: "+currentTime +" Payment DONE txnID: "+ requestCode,FancyToast.LENGTH_LONG, FancyToast.SUCCESS,false ).show();
                String txt2img=
                        "Name :"+name +
                        "\nHOSTEL :"+hostel+
                        "\n Room No.: "+room+
                        "\n Contact Number:"+number+
                        "\n Total Price. Paid :"+total+
                        "\n \n Ordered :"+order +
                        "\n \n DATE: "+currentTime +
                        "\n txnID: "+ requestCode;
                whatsapp(MainActivity.this,"+919669615449",txt2img);
            } else {
                FancyToast.makeText(this,"Payment Failed",FancyToast.LENGTH_LONG, FancyToast.ERROR,false ).show();

            }
        }

    }
    @SuppressLint("NewApi")
    public void whatsapp(Activity activity, String phone,String txt) {

        PackageManager packageManager = getApplicationContext().getPackageManager();


        try {
            String url = "https://api.whatsapp.com/send?phone="+ "+919669615449" +"&text=" + URLEncoder.encode(txt, "UTF-8");
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setPackage("com.whatsapp");
            i.putExtra(Intent.EXTRA_TEXT," Sharing ");
            i.setType("text/plain");
            i.setData(Uri.parse(url));

            startActivity(i);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void showDialog(final Activity activity) {

        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.newcustom_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        //final EditText hostelis=dialog.findViewById(R.id.hostel);
        final EditText roomis=dialog.findViewById(R.id.room);
        final EditText numberis=dialog.findViewById(R.id.number);
        final EditText nameis=dialog.findViewById(R.id.name);
        final TextView price=dialog.findViewById(R.id.price);
       final Spinner niceSpinner = dialog.findViewById(R.id.hostel);
        String[] arraySpinner = new String[] {
                "BARAK", "BRAHMAPUTRA", "DHANSIRI", "DIBANG", "DIHING","KAMENG","KAPILI","LOHIT", "MANAS", "SIANG", "SUBHANSIRI","UMIAM"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        niceSpinner.setAdapter(adapter);
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
                name=nameis.getText().toString().trim();
                number=numberis.getText().toString().trim();
               hostel=niceSpinner.getSelectedItem().toString().trim();

             if(!TextUtils.isEmpty(room) && !TextUtils.isEmpty(name) && !TextUtils.isEmpty(number) && number.length()==10 )
             {dialog.cancel();
                cnfrmDialog(dialog.getContext());

             }
             else{
                 if(TextUtils.isEmpty(name))
                 nameis.setError("Write Correct");
                 if(TextUtils.isEmpty(number) || number.length()!=10)
                 numberis.setError("Write Correct");
                 if(TextUtils.isEmpty(room))
                 roomis.setError("Write Correct");
                 FancyToast.makeText(activity,"Wrtie Room No. and Select Hostel !",FancyToast.LENGTH_LONG, FancyToast.ERROR,false ).show();

             }
            }
        });
            dialog.show();
    }
    public void cnfrmDialog(Context context) {

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialogcnfrm);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        final TextView orderis=dialog.findViewById(R.id.order);
        final TextView price=dialog.findViewById(R.id.price);
        price.setText(""+total);
        orderis.setText(order);
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
                dialog.cancel();
                payamt(total);

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
            } return false;

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
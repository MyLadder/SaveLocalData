package com.soussidev.kotlin.savelocaldata;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.soussidev.kotlin.savelocaldata.adaptor.ProductAdaptor;
import com.soussidev.kotlin.savelocaldata.model.Marque;
import com.soussidev.kotlin.savelocaldata.model.PriceUnit;
import com.soussidev.kotlin.savelocaldata.model.Product;
import java.util.ArrayList;
import rx.Observable;



public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    public static final int SPAN_COUNT_ONE = 1;
    public static final int SPAN_COUNT_THREE = 3;
    public static final int SPAN_COUNT_TWO = 2;
    private RecyclerView recyclerView;
    private ProductAdaptor itemAdapter;
    private GridLayoutManager gridLayoutManager;
    private ArrayList<Product> itemProduct=new ArrayList<Product>();
    private int getorientation;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private FloatingActionButton fab_filtre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view->{
            Log.d(TAG, ":FloatingActionButton ");
        });
        InitView();

        InitObserve();

        getorientation=getResources().getConfiguration().orientation;
        Log.d(TAG, "onOrientation: "+getorientation);

        itemProduct.addAll(getGoogle());
        itemProduct.addAll(getSamsung());

        itemAdapter.notifyItemRangeChanged(0, itemAdapter.getItemCount());
    }

    private void InitView()
    {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Log.d(TAG, "InitView /SDK VERSION: "+android.os.Build.VERSION.SDK_INT);
        }


        fab_filtre=(FloatingActionButton)findViewById(R.id.fab_filtre);

        fab_filtre.setOnClickListener(view -> {

            Log.d(TAG, "fab_filtre: ");

            PopupMenu menu = new PopupMenu(MainActivity.this, view);

            for(Marque marque : Marque.values())
            {
                menu.getMenu().add(marque.toString());
            }

            menu.getMenu().add("Show All");
            menu.show();

            menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    filter_items(item);


                    return true;
                }
            });

            menu.show();


        });

     recyclerView=(RecyclerView)findViewById(R.id.recycler);
     collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("List Of Products");

        collapsingToolbarLayout.setScrimsShown(true);
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorAccent));
        collapsingToolbarLayout.setStatusBarScrimColor(getResources().getColor(R.color.colorAccent));
        collapsingToolbarLayout.setSoundEffectsEnabled(true);
        collapsingToolbarLayout.setBackgroundResource(R.drawable.samsunggalaxy);

     gridLayoutManager = new GridLayoutManager(MainActivity.this, SPAN_COUNT_ONE);
     recyclerView.setLayoutManager(gridLayoutManager);

        itemAdapter = new ProductAdaptor(itemProduct, gridLayoutManager,this);
        recyclerView.setAdapter(itemAdapter);
    }



    private void InitObserve()
    {
        //Observe by Marque
        Observable.just(itemProduct)
                .flatMap(listOfProducts -> Observable.from(listOfProducts)

                        .groupBy(product -> product.getMarque_prod().toString()))

                .subscribe(ProductGroupedObservable -> {
                    switch (ProductGroupedObservable.getKey()) {
                      //  ProductGroupedObservable.getKey()

                        case "samsung": {
                            ProductGroupedObservable.asObservable()
                                    .subscribe(product -> System.out.println("Gamme Sumsung:" + product.getNom_prod()+"- Price :"+product.getPrice_prod()+ PriceUnit.DT));

                            break;
                        }
                        case "Google": {
                            ProductGroupedObservable.asObservable()
                                    .subscribe(product -> System.out.println("Gamme Google:" + product.getNom_prod()+"- Price :"+product.getPrice_prod()+PriceUnit.DT));
                            break;
                        }
                        default :
                        {
                            Toast.makeText(MainActivity.this,"No Products in the Lists",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void filter_items(MenuItem item)
    {
        //Observe by Marque
        Observable.just(item)
                .flatMap(listOfProducts -> Observable.from(new MenuItem[]{listOfProducts})

                        .groupBy(product -> product.getTitle().toString()))

                .subscribe(ProductGroupedObservable -> {
                    switch (ProductGroupedObservable.getKey()) {
                        //  ProductGroupedObservable.getKey()

                        case "Samsung": {
                            ProductGroupedObservable.asObservable()
                                    .subscribe(product -> System.out.println("title:" + product.getTitle()));
                            itemProduct.clear();
                            itemProduct.addAll(getSamsung());
                            itemAdapter.notifyItemRangeChanged(0, itemAdapter.getItemCount());
                            itemAdapter = new ProductAdaptor(itemProduct, gridLayoutManager,getApplicationContext());
                            recyclerView.setAdapter(itemAdapter);
                            break;
                        }
                        case "Google": {
                            ProductGroupedObservable.asObservable()
                                    .subscribe(product -> System.out.println("title:" + product.getTitle()));
                            itemProduct.clear();
                            itemProduct.addAll(getGoogle());
                            itemAdapter.notifyItemRangeChanged(0, itemAdapter.getItemCount());
                            itemAdapter = new ProductAdaptor(itemProduct, gridLayoutManager,getApplicationContext());
                            recyclerView.setAdapter(itemAdapter);

                            break;
                        }
                        case "Nokia": {
                            ProductGroupedObservable.asObservable()
                                    .subscribe(product -> System.out.println("title:" + product.getTitle()));


                            break;
                        }
                        case "Sony": {
                            ProductGroupedObservable.asObservable()
                                    .subscribe(product -> System.out.println("title:" + product.getTitle()));


                            break;
                        }
                        case "Motorola": {
                            ProductGroupedObservable.asObservable()
                                    .subscribe(product -> System.out.println("title:" + product.getTitle()));

                            break;
                        }
                        case "Lg": {
                            ProductGroupedObservable.asObservable()
                                    .subscribe(product -> System.out.println("title:" + product.getTitle()));


                            break;
                        }
                        case "Htc": {
                            ProductGroupedObservable.asObservable()
                                    .subscribe(product -> System.out.println("title:" + product.getTitle()));


                            break;
                        }
                        case "Huawei": {
                            ProductGroupedObservable.asObservable()
                                    .subscribe(product -> System.out.println("title:" + product.getTitle()));
                            itemProduct.clear();
                            itemAdapter.notifyItemRangeChanged(0, itemAdapter.getItemCount());
                            itemProduct.addAll(getGoogle());
                            itemProduct.addAll(getSamsung());
                            itemAdapter.notifyItemRangeChanged(0, itemAdapter.getItemCount());
                            itemAdapter = new ProductAdaptor(itemProduct, gridLayoutManager,getApplicationContext());
                            recyclerView.setAdapter(itemAdapter);
                            recyclerView.getAdapter().notifyDataSetChanged();

                            break;
                        }

                        case "Show All": {
                            ProductGroupedObservable.asObservable()
                                    .subscribe(product -> System.out.println("title:" + product.getTitle()));
                            itemProduct.clear();
                            itemAdapter.notifyItemRangeChanged(0, itemAdapter.getItemCount());
                            itemProduct.addAll(getGoogle());
                            itemProduct.addAll(getSamsung());
                            itemAdapter.notifyItemRangeChanged(0, itemAdapter.getItemCount());
                            itemAdapter = new ProductAdaptor(itemProduct, gridLayoutManager,getApplicationContext());
                            recyclerView.setAdapter(itemAdapter);
                            recyclerView.getAdapter().notifyDataSetChanged();


                            break;
                        }
                        default :
                        {
                            Toast.makeText(MainActivity.this,"No Products in the Lists",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private ArrayList<Product> getSamsung() {
        ArrayList<Product> Samsung = new ArrayList<>();
        Samsung.add(new Product(0,"samsung Galaxy 1", Marque.Samsung,"S1",250.000,"2011",R.drawable.samsung));
        Samsung.add(new Product(1,"samsung Galaxy 2", Marque.Samsung,"S2",300.000,"2012",R.drawable.samsung));
        Samsung.add(new Product(1,"samsung Galaxy 3", Marque.Samsung,"S3",350.000,"2013",R.drawable.samsung));
        Samsung.add(new Product(1,"samsung Galaxy 4", Marque.Samsung,"S4",1150.000,"2014",R.drawable.samsung));
        Samsung.add(new Product(1,"samsung Galaxy 5", Marque.Samsung,"S5",1450.000,"2015",R.drawable.samsung));
        Samsung.add(new Product(1,"samsung Galaxy 6", Marque.Samsung,"S6",1850.000,"2015",R.drawable.samsung));
        Samsung.add(new Product(1,"samsung Galaxy 7", Marque.Samsung,"S7",2150.000,"2016",R.drawable.samsung));
        Samsung.add(new Product(1,"samsung Galaxy 8", Marque.Samsung,"S8",2450.000,"2017",R.drawable.samsung));

        return Samsung;
    }

    private ArrayList<Product> getGoogle() {
        ArrayList<Product> google = new ArrayList<>();
        google.add(new Product(0,"Google Pixel",Marque.Google,"Pixel",950.000,"2016",R.drawable.pixel_2));
        google.add(new Product(1,"Google Pixel 2",Marque.Google,"Pixel 2",1300.000,"2017",R.drawable.pixel_2));
        google.add(new Product(1,"Google Pixel 3",Marque.Google,"Pixel 3",2350.000,"2017",R.drawable.pixel_2));

        return google;
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d(TAG, "onBackPressed: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    /**
     * @author Soussi
     *
     * Func switchIcon()
     *
     * @param item

     */
    private void switchIcon(MenuItem item) {

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {


            if (gridLayoutManager.getSpanCount() == SPAN_COUNT_THREE) {

                item.setIcon(getDrawable(R.drawable.ic_span_1));

            } else {
                item.setIcon(getDrawable(R.drawable.ic_span_3));

            }

        }else
        {
            //For old api
            if (gridLayoutManager.getSpanCount() == SPAN_COUNT_THREE) {

                item.setIcon(getResources().getDrawable(R.drawable.ic_span_1));
            } else {
                item.setIcon(getResources().getDrawable(R.drawable.ic_span_3));
            }


        }
    }

    private void switchLayout() {
        if (gridLayoutManager.getSpanCount() == SPAN_COUNT_ONE) {

             if(getorientation==1)
             {
                 gridLayoutManager.setSpanCount(SPAN_COUNT_TWO);
             }else{
                 gridLayoutManager.setSpanCount(SPAN_COUNT_THREE);
             }


        } else {
            gridLayoutManager.setSpanCount(SPAN_COUNT_ONE);

        }
        itemAdapter.notifyItemRangeChanged(0, itemAdapter.getItemCount());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

            switchLayout();
            switchIcon(item);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }









}

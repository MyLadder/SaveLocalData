# RecyclerView with Filter
Recyclerview filtre items using observable
<br>
[![Ansible Role](https://img.shields.io/badge/Developer-Soussidev-yellow.svg)]()
[![Hex.pm](https://img.shields.io/hexpm/l/plug.svg)]()

<a href='https://ko-fi.com/A243447K' target='_blank'><img height='36' style='border:0px;height:36px;' src='https://az743702.vo.msecnd.net/cdn/kofi4.png?v=0' border='0' alt='Buy Me a Coffee at ko-fi.com' /></a>

# Code :
```java
PopupMenu menu = new PopupMenu(MainActivity.this, view);

menu.setOnMenuItemClickListener(item -> {

                filter_items(item);
            return true;
            });


private void filter_items(MenuItem item)
    {
        //Observe by Marque
        Observable.just(item)
                .flatMap(listOfProducts -> Observable.from(new MenuItem[]{listOfProducts})

                        .groupBy(product -> product.getTitle().toString()))

                .subscribe(ProductGroupedObservable -> {
                    switch (ProductGroupedObservable.getKey()) {


                        case "Samsung": {
                            ProductGroupedObservable.asObservable()
                                    .subscribe(product -> collapsingToolbarLayout.setTitle(product.getTitle().toString()));
                            itemProduct.clear();
                            itemProduct.addAll(getSamsung());
                            itemAdapter.notifyItemRangeChanged(0, itemAdapter.getItemCount());
                            itemAdapter = new ProductAdaptor(itemProduct, gridLayoutManager,getApplicationContext());
                            recyclerView.setAdapter(itemAdapter);
                            break;
                        }
                        case "Google": {
                            ProductGroupedObservable.asObservable()
                                    .subscribe(product -> collapsingToolbarLayout.setTitle(product.getTitle().toString()));
                            itemProduct.clear();
                            itemProduct.addAll(getGoogle());
                            itemAdapter.notifyItemRangeChanged(0, itemAdapter.getItemCount());
                            itemAdapter = new ProductAdaptor(itemProduct, gridLayoutManager,getApplicationContext());
                            recyclerView.setAdapter(itemAdapter);

                            break;
                        }
                        case "Nokia": {
                            ProductGroupedObservable.asObservable()
                                    .subscribe(product -> collapsingToolbarLayout.setTitle(product.getTitle().toString()));
                            itemProduct.clear();
                            itemAdapter.notifyItemRangeChanged(0, itemAdapter.getItemCount());
                            itemAdapter = new ProductAdaptor(itemProduct, gridLayoutManager,getApplicationContext());
                            recyclerView.setAdapter(itemAdapter);

                            break;
                        }
                        case "Sony": {
                            ProductGroupedObservable.asObservable()
                                    .subscribe(product -> collapsingToolbarLayout.setTitle(product.getTitle().toString()));
                            itemProduct.clear();
                            itemAdapter.notifyItemRangeChanged(0, itemAdapter.getItemCount());
                            itemAdapter = new ProductAdaptor(itemProduct, gridLayoutManager,getApplicationContext());
                            recyclerView.setAdapter(itemAdapter);

                            break;
                        }
                        case "Motorola": {
                            ProductGroupedObservable.asObservable()
                                    .subscribe(product -> collapsingToolbarLayout.setTitle(product.getTitle().toString()));
                            itemProduct.clear();
                            itemAdapter.notifyItemRangeChanged(0, itemAdapter.getItemCount());
                            itemAdapter = new ProductAdaptor(itemProduct, gridLayoutManager,getApplicationContext());
                            recyclerView.setAdapter(itemAdapter);
                            break;
                        }
                        case "Lg": {
                            ProductGroupedObservable.asObservable()
                                    .subscribe(product -> collapsingToolbarLayout.setTitle(product.getTitle().toString()));

                            itemProduct.clear();
                            itemProduct.addAll(getLg());
                            itemAdapter.notifyItemRangeChanged(0, itemAdapter.getItemCount());
                            itemAdapter = new ProductAdaptor(itemProduct, gridLayoutManager,getApplicationContext());
                            recyclerView.setAdapter(itemAdapter);
                            break;
                        }
                        case "Htc": {
                            ProductGroupedObservable.asObservable()
                                    .subscribe(product -> collapsingToolbarLayout.setTitle(product.getTitle().toString()));
                            itemProduct.clear();
                            itemAdapter.notifyItemRangeChanged(0, itemAdapter.getItemCount());
                            itemAdapter = new ProductAdaptor(itemProduct, gridLayoutManager,getApplicationContext());
                            recyclerView.setAdapter(itemAdapter);

                            break;
                        }
                        case "Huawei": {
                            ProductGroupedObservable.asObservable()
                                    .subscribe(product -> collapsingToolbarLayout.setTitle(product.getTitle().toString()));
                            itemProduct.clear();
                            itemAdapter.notifyItemRangeChanged(0, itemAdapter.getItemCount());
                            itemAdapter = new ProductAdaptor(itemProduct, gridLayoutManager,getApplicationContext());
                            recyclerView.setAdapter(itemAdapter);

                            break;
                        }

                        case "Show All": {
                            ProductGroupedObservable.asObservable()
                                    .subscribe(product -> collapsingToolbarLayout.setTitle(product.getTitle().toString()));
                            itemProduct.clear();
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

```

# SDK Required
+ Target sdk:<br>
[![API](https://img.shields.io/badge/API-26%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=26)
+ Min sdk:<br>
[![API](https://img.shields.io/badge/API-16%2B-orange.svg?style=flat)](https://android-arsenal.com/api?level=19)

# Social Media
<table style="border:0px;">
   <tr>
      <td>
<a href="https://www.linkedin.com/in/soussimohamed/">
<img src="picture/linkedin.png" height="100" width="100" alt="Soussi Mohamed">
</a>
      </td>
      <td>
         <a href="https://twitter.com/soussimohamed7/">
<img src="picture/Twitter.png" height="60" width="60" alt="Soussi Mohamed">
</a>
     </td>
        <td>
         <a href="https://plus.google.com/u/0/+SoussiMohamed747">
<img src="picture/googleplus.png" height="60" width="60" alt="Soussi Mohamed">
</a>
     </td>
  </tr> 
</table> 

# Licence
```
Copyright 2017 Soussidev, Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

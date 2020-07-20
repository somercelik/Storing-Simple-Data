package com.somercelik.storingdata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /*SharedPreferences sınıfından oluşturulan nesne
     * belirli ve az sayıda olan ve uygulama kapatılsa
     * dahi bir sonraki açılışta kullanılacak olan veri-
     * lerin saklanması için kullanılabilecek bir yapıdır.
     * created by @somercelik*/

    EditText editText;
    TextView textView;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {                    //Kullanılacak component'ler initialize edilir.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);

        sharedPreferences = this.getSharedPreferences("com.somercelik.storingdata", Context.MODE_PRIVATE);  //SharedPreferences nesnesi initialize edilir
        // ve Private modda bulunduğumuz package içerisinde kullanılmak üzere projeye bağlanır. Bu demektir ki bu veri başka uygulamalarca kullanılamaz.

        int storedAge = sharedPreferences.getInt("storedAge", 0);       //storedAge isimli key'de bulunan değer aynı isimdeki değişkene alınır. Default değer olarak 0 verilir, yani boş ise bu değer 0'dır.
        if (storedAge == 0) {                                                //Eğer key değeri 0'a eşit ise yani boş ise textView'a boş string yazılır.
            textView.setText("Your age: ");
        } else {
            textView.setText("Your age: " + storedAge);                      //Değil ise saklanan değer textView'a yazılır.
        }


    }

    public void save(View view) {                                           //Save butonunun onClick metodudur.
        if (!editText.getText().toString().matches("")) {             //editText'e herhangi bir değer girilmişse
            int userAge = Integer.parseInt(editText.getText().toString());  //yazılan değer userAge şeklinde bir değişkene alınır.
            textView.setText("Your age: " + userAge);                       //Yazılan değer alttaki textView'a yazdırılır.
            sharedPreferences.edit().putInt("storedAge", userAge).apply(); //Yazdırılan değer saklanan SharedPreference'ta da değiştirilerek güncellenir.
        }
    }

    public void delete(View view) {                                         //Altta bulunan "DELETE" butonunun onClick metodudur.
        int storedData = sharedPreferences.getInt("storedAge", 0);     //Saklanan veri bir değikene alınarak kontrol edilir.
        if (storedData != 0) {                                              //Eğer 0 yani boş değil ise
            sharedPreferences.edit().remove("storedAge").apply();           //sharedPreferences silinir.
            textView.setText("Your age: ");                                 //Ardından boş String yazdırılır.
        }

    }
}
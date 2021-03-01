package com.example.t_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.t_1.settings.SettingsActivity;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.jsoup.nodes.Document;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    RecyclerView recyclerView;
    Adapter adapter;
    ArrayList<String> items;

    private Document doc;
    private Thread secThread;
    private Runnable runnable;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        items = new ArrayList<>();
        items.add("Введение в Java");
        items.add("Язык программирования Java");
        items.add("Первая программа на Java");
        items.add("Первая программа в IntelliJ IDEA");
        items.add("Первая программа в NetBeans");
        items.add("Первая программа в Eclipse");
        items.add("Основы программирования на Java");
        items.add("Структура программы");
        items.add("Переменные и константы");
        items.add("Типы данных");
        items.add("Консольный ввод/вывод в Java");
        items.add("Арифметические операции");
        items.add("Поразрядные операции");
        items.add("Условные выражения");
        items.add("Операции присваивания и приоритет операций");
        items.add("Преобразования базовых типов данных");
        items.add("Условные конструкции");
        items.add("Циклы");
        items.add("Массивы");
        items.add("Методы");
        items.add("Параметры методов");
        items.add("Оператор return. Результат метода");
        items.add("Перегрузка методов");
        items.add("Рекурсивные функции");
        items.add("Введение в обработку исключений");
        items.add("Классы и объекты");
        items.add("Пакеты");
        items.add("Модификаторы доступа и инкапсуляция");
        items.add("Статические члены и модификатор static");
        items.add("Объекты как параметры методов");
        items.add("Внутренние и вложенные классы");
        items.add("Наследование");
        items.add("Абстрактные классы");
        items.add("Иерархия наследования и преобразование типов");
        items.add("Интерфейсы");
        items.add("Интерфейсы в механизме обратного вызова");
        items.add("Перечисления enum");
        items.add("Класс Object и его методы");
        items.add("Обобщения (Generics)");
        items.add("Ограничения обобщений");
        items.add("Наследование и обобщения");
        items.add("Ссылочные типы и клонирование объектов");
        items.add("Оператор throws");
        items.add("Классы исключений");
        items.add("Создание своих классов исключений");
        items.add("Типы коллекций. Интерфейс Collection");
        items.add("Класс ArrayList и интерфейс List");
        items.add("Очереди и класс ArrayDeque");
        items.add("Класс LinkedList");
        items.add("Интерфейс Set и класс HashSet");
        items.add("SortedSet, NavigableSet, TreeSet");
        items.add("Интерфейсы Comparable и Comporator. Сортировка");
        items.add("Интерфейс Map и класс HashMap");
        items.add("Интерфейсы SortedMap и NavigableMap. Класс TreeMap");
        items.add("Итераторы");
        items.add("Потоки ввода-вывода");
        items.add("Чтение и запись файлов. FileInputStream и FileOutputStream");
        items.add("Закрытие потоков");
        items.add("Классы ByteArrayInputStream и ByteArrayOutputStream");
        items.add("Буферизованные потоки BufferedInputStream и BufferedOutputStream");
        items.add("Форматируемый вывод. PrintStream и PrintWriter");
        items.add("Классы DataOutputStream и DataInputStream");
        items.add("Чтение и запись текстовых файлов");
        items.add("Буферизация символьных потоков. BufferedReader и BufferedWriter");
        items.add("Сериализация объектов");
        items.add("Класс File. Работа с файлами и каталогами");
        items.add("Работа с ZIP-архивами");
        items.add("Класс Console");
        items.add("Введение в строки. Класс String");
        items.add("Основные операции со строками");
        items.add("StringBuffer и StringBuilder");
        items.add("Регулярные выражения");
        items.add("Введение в лямбда-выражения");
        items.add("Лямбды как параметры и результаты методов");
        items.add("Встроенные функциональные интерфейсы");
        items.add("Класс Thread");
        items.add("Создание и выполнение потоков");
        items.add("Завершение и прерывание потока");
        items.add("Синхронизация потоков. Оператор synchronized");
        items.add("Взаимодействие потоков. Методы wait и notify");
        items.add("Семафоры");
        items.add("Обмен между потоками. Класс Exchanger");
        items.add("Класс Phaser");
        items.add("Блокировки. ReentrantLock");
        items.add("Условия в блокировках");
        items.add("Введение в Stream API");
        items.add("Создание потока данных");
        items.add("Фильтрация, перебор элементов и отображение");
        items.add("Сортировка");
        items.add("Получение подпотока и объединение потоков");
        items.add("Методы skip и limit");
        items.add("Операции сведения");
        items.add("Метод reduce");
        items.add("Тип Optional");
        items.add("Метод collect");
        items.add("Группировка");
        items.add("Параллельные потоки");
        items.add("Параллельные операции над массивами");
        items.add("Создание модуля");
        items.add("Зависимые модули");
        items.add("Взаимодействие между модулями");
        items.add("Математические вычисления и класс Math");
        items.add("Большие числа BigInteger и BigDecimal");
        items.add("Работа с датами. LocalDate");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this,items);
        recyclerView.setAdapter(adapter);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
//        mAppBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
//               .setDrawerLayout(drawer)
//                .build();
//
//
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.activity_data) {
            Intent intent = new Intent(MainActivity.this, activity_database.class);
            startActivity(intent);
        } else if (id == R.id.nav_slideshow) {

        }
        drawer.closeDrawer(GravityCompat.START);
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings)
        {
            Intent i = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}
package katey2658.com.my.toastdemo;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableWrapper;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ActionMenuItem;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.text.method.KeyListener;
import android.text.method.NumberKeyListener;
import android.text.method.SingleLineTransformationMethod;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private String phone[]={"iPhone4","iPhone6","iPhone6s","iPhone5","xiaomi","hauiwei","nexus6p"};
    //单输入自动匹配输入
    private AutoCompleteTextView autoCompleteTextView;
    //多输入自动匹配输入
    private  MultiAutoCompleteTextView multiAutoCompleteTextView;

    //创建适配器
    ArrayAdapter<String> mDatas;

    //获取上下文
    private Context mContext;

    //四个按钮
    private Button btn_showToast1;
    private Button btn_showToast2;
    private Button btn_showToast3;
    private Button btn_showToast4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //做一些初始化的工作
        init();
    }

    private void init() {
        //上下文对象是本身
        mContext=this;
        //实例化4个按钮
        btn_showToast1=new Button(mContext);
        btn_showToast2=new Button(mContext);
        btn_showToast3=new Button(mContext);
        btn_showToast4=new Button(mContext);
        //对4个对象和布局文件里的控件实现关联
        btn_showToast1= (Button) findViewById(R.id.btn_showToast1);
        btn_showToast2= (Button) findViewById(R.id.btn_showToast2);
        btn_showToast3= (Button) findViewById(R.id.btn_showToast3);
        btn_showToast4= (Button) findViewById(R.id.btn_showToast4);
        //最普通的Toast
        btn_showToast1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,"显示第一个Toast",Toast.LENGTH_SHORT).show();
            }
        });

        //自定义显示位置的Toast
        btn_showToast2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast=Toast.makeText(mContext,"显示第二个带不同位置的Toast",Toast.LENGTH_SHORT);
                //设置位置，底下居中
                toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);
                toast.show();

            }
        });

        //放一张图片在Toast
        btn_showToast3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //toast对象
                Toast toast=Toast.makeText(mContext,"显示带图片的Toast",Toast.LENGTH_SHORT);
                //toast对象
                LinearLayout linerLayout= (LinearLayout) toast.getView();
                //定义图片
                ImageView mImageView=new ImageView(mContext);
                //获取图片资源
                mImageView.setImageResource(R.drawable.actionbar_icon);
                //设置Toast的布局摆放方式-水平
                linerLayout.setOrientation(LinearLayout.HORIZONTAL);
                //加图片到toast第一个位置
                linerLayout.addView(mImageView,0);
                //显示
                toast.show();
            }
        });


        //自定义Toast
        btn_showToast4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater=getLayoutInflater();
                View viewToast=inflater.inflate(R.layout.view_toast_custom, (ViewGroup) findViewById(R.id.my_toast));
                ImageView img_logo= (ImageView) viewToast.findViewById(R.id.img_logo);
                TextView tv_msg= (TextView) viewToast.findViewById(R.id.tv_msg);
                tv_msg.setText("自定义Toast");
                Toast toast=new Toast(mContext);
                toast.setView(viewToast);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.show();

            }
        });

        //来自其他线程的toast
        //暂时先不写


        /*
        关于监听的几种方法，相对都比较好实现，这里不一一实现，
        有机会再来详细的写，就这样

        1。匿名内部监听类，重写方法 当当前页面需要监听的控件比较少的时候，
          比较推荐使用这个
        2。写一个继承OnClickListenter的内部类
        3。写一个继承OnClickListenter的外部类
        4. 让当前类继承nClickListenter接口，重写方法onClick()
            然后用switch方法去判断View.getId()
        5。 在XML布局文件中设置onClick属性 然后在java文件中写上相应的方法
        5. 适配器

         */


        /*
        关于EditText相关的注意点，关于如何用代码实现相应的效果之后再给出

        1. hint设置默认提示的文字，当获取焦点时候会自动消失
           设置文字颜色textColorHint
        2. singleLine单行显示,不然会跳到第二行，特不好看
           *代码实现：
           *editText.setTransformationMethod(SingleLineTransformationMethod.getInstance());
        3. cursorVisible设置光标是否可见
        4. imeOptions会在键盘上右下角显示相应的事件,比如actionNext
           跳到下一个actionGo可以设置相关的动作事件方法，可以是跳转界面
           还有很多，但注意需要和singleLine一起使用
           可以在java文件里面给输入框设置OnEditorActionListener做一个事件判断
        5. digists 只允许输入的字符
           *代码实现
           setInputType(InputType.TYPE_CLASS_NUMBER);
           setKeyListener(new DigitsKeyListener());
           setKeyListener(new NumberKeyListener() {
                      @Override
               protected char[] getAcceptedChars() {
                 char s[] = {'1', '2', '1', '5', '#', '*'};
                 return s;
              }
                      @Override
              public int getInputType() {
                 return InputType.TYPE_CLASS_TEXT;
             }
           });
           editText.setKeyListener(new DigitsKeyListener());
        6. drawableTop等等 可以设置在输入框中带相应的图片
           *代码实现：
           Drawable img = getResources().getDrawable(R.mipmap.ic_launcher);
           editText.setCompoundDrawablesWithIntrinsicBounds(img, null, null, null);
        7. gravity 内部控件的对齐方式，通用的一个属性
         */


        /*一下是AutoCompleteTextView和MultiAutoCompleteTextView
        推荐是和上面的实例化放在一起，这里主要是为了方便划分区别
        */
        //实例化
        autoCompleteTextView=new AutoCompleteTextView(mContext);
        multiAutoCompleteTextView=new MultiAutoCompleteTextView(mContext);
        //建立关联
        autoCompleteTextView= (AutoCompleteTextView) findViewById(R.id.auto_complete_textView);
        multiAutoCompleteTextView= (MultiAutoCompleteTextView) findViewById(R.id.multi_auto_complete_textview);
        //给输入框里的文字设置颜色，不然在低版本中失去焦点后可能看不见
        autoCompleteTextView.setTextColor(Color.BLACK);
        multiAutoCompleteTextView.setTextColor(Color.BLACK);
        //实例化适配器
        mDatas=new ArrayAdapter<String>(mContext,R.layout.show,R.id.auto_textView,phone);
        //从第几个输入字符开始匹配，在单输入哪个我已经在xml文件中设置了 这里是代码实现
        multiAutoCompleteTextView.setThreshold(2);
        //设置适配器
        autoCompleteTextView.setAdapter(mDatas);
        multiAutoCompleteTextView.setAdapter(mDatas);
        //multiAutoCompleteTextView需要多一个分隔符，默认是一个逗号
        //分割符号可以继承CommaTokenize里面所继承的接口，然后弄一些set get方法就可以自己设置分隔符
        multiAutoCompleteTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }

    //对菜单添加item,注意低版本菜单是在按键上呼出的，之后版本都是在左上角
    //其实也就是两个方法而已

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1,1,1,"帮助");
        menu.add(1,2,2,"关于");
        menu.add(1,3,3,"退出");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:
                Toast.makeText(mContext,"帮助",Toast.LENGTH_SHORT);
                break;
            case 2:
                Toast.makeText(mContext,"帮助",Toast.LENGTH_SHORT);
                break;
            case 3:
                //退出当前界面 android activity是以按栈的形式陈放
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}

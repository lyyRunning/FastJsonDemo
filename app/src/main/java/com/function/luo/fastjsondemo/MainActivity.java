package com.function.luo.fastjsondemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_1)
    Button btn1;
    @BindView(R.id.btn_2)
    Button btn2;
    @BindView(R.id.btn_3)
    Button btn3;
    @BindView(R.id.btn_4)
    Button btn4;
    @BindView(R.id.btn_5)
    Button btn5;

    private  User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
      /*  //
        public static boolean isValid (String str)；
        public static boolean isValidArray(String str)

        // 把JSON文本parse为JSONObject或者JSONArray
        public static final Object parse (String text);
        // 把JSON文本parse成JSONObject
        public static final JSONObject parseObject (String text)；
        // 把JSON文本parse为JavaBean
        public static final <T> T parseObject (String text, Class < T > clazz);
        // 把JSON文本parse成JSONArray
        public static final JSONArray parseArray (String text);
        //把JSON文本parse成JavaBean集合
        public static final <T> List<T> parseArray (String text, Class < T > clazz);
        // 将JavaBean序列化为JSON文本
        public static final String toJSONString (Object object);
        // 将JavaBean序列化为带格式的JSON文本
        public static final String toJSONString (Object object,boolean prettyFormat);
        //将JavaBean转换为JSONObject或者JSONArray。
        public static final Object toJSON (Object javaObject);*/
        user =  new User("小明","15618273672",27,"伯俊公司");
    }

    @OnClick({R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4, R.id.btn_5,R.id.btn_6})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_1:
                //对象转 json 数据
                String jsonString = JSON.toJSONString(user);
                Log.d("LUO","======"+jsonString);
                //{"age":27,"company":"伯俊公司","name":"小明","phone":"15618273672"}
                break;
            case R.id.btn_2:

                String jsonString2 = JSON.toJSONString(user);
                //json数据转对象
                User user1 =JSON.parseObject(jsonString2,User.class);
                Log.d("LUO","======"+user1);
                //com.function.luo.fastjsondemo.User@100ff36
                break;
            case R.id.btn_3:
                List<User> UserList = new ArrayList<User>();
                UserList.add(new User("小红","15618273672",27,"伯俊公司1"));
                UserList.add(new User("小亮","15618273677",25,"伯俊公司2"));
                UserList.add(new User("小涛","15618273676",24,"伯俊公司3"));


                List<UserGroup> userGroupList = new ArrayList<UserGroup>();


                userGroupList.add(new UserGroup("项目一",UserList));
                userGroupList.add(new UserGroup("项目二",UserList));
                userGroupList.add(new UserGroup("项目三",UserList));
                //集合对象转 Json 数据
                String jsonString3 = JSON.toJSONString(userGroupList);
                Log.d("LUO","======"+jsonString3);
                // [{"name":"项目一","users":[{"age":27,"company":"伯俊公司1","name":"小红","phone":"15618273672"},{"age":25,"company":"伯俊公司2","name":"小亮","phone":"15618273677"},{"age":24,"company":"伯俊公司3","name":"小涛","phone":"15618273676"}]},{"name":"项目二","users":[{"$ref":"$[0].users[0]"},{"$ref":"$[0].users[1]"},{"$ref":"$[0].users[2]"}]},{"name":"项目三","users":[{"$ref":"$[0].users[0]"},{"$ref":"$[0].users[1]"},{"$ref":"$[0].users[2]"}]}]

                break;
            case R.id.btn_4:
                List<User> UserList2 = new ArrayList<User>();
                UserList2.add(new User("小红","15618273672",27,"伯俊公司1"));
                UserList2.add(new User("小亮","15618273677",25,"伯俊公司2"));
                UserList2.add(new User("小涛","15618273676",24,"伯俊公司3"));

                String jsonString4 = JSON.toJSONString(UserList2);

                // json数据转集合对象
                List<User> UserList3 = JSON.parseArray(jsonString4,User.class);
                Log.d("LUO","======"+UserList3);
                break;
            case R.id.btn_5:
                try{
                    String str = JSON.toJSONString(user);
                    //json数据转对象
                    Object parse = JSON.parse(str);
                    Log.d("LUO","======"+parse);
                }catch (Exception e){
                    e.printStackTrace();
                }

                break;
            case R.id.btn_6:
                TwoActivity.launch(MainActivity.this);
                break;
            default:
                break;
        }
    }
}

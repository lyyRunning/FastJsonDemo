package com.function.luo.fastjsondemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.function.luo.data.Animal;
import com.function.luo.data.Data;
import com.function.luo.data.Dog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by luo on 2019/7/15.
 */

public class TwoActivity extends Activity {

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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        ButterKnife.bind(this);
        user =  new User("小明","15618273672",27,"伯俊公司");
    }

    @OnClick({R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4, R.id.btn_5, R.id.btn_6, R.id.btn_7})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_1:
                //对象转 Json 数据
                String str =  JsonUtil.convertObjectToJson(user);
                Log.d("LUO","======"+str);
                //{"age":27,"company":"伯俊公司","name":"小明","phone":"15618273672"}
                break;
            case R.id.btn_2:
                String jsonString2 =  JsonUtil.convertObjectToJson(user);
                //Json数据转对象
                User user1 = JsonUtil.convertJsonToObject(jsonString2,User.class);
                Log.d("LUO","======"+user1);
                //com.function.luo.fastjsondemo.User@555d8c2
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
                String jsonString3 =  JsonUtil.convertObjectToJson(userGroupList);
                Log.d("LUO","======"+jsonString3);
                //[{"name":"项目一","users":[{"age":27,"company":"伯俊公司1","name":"小红","phone":"15618273672"},{"age":25,"company":"伯俊公司2","name":"小亮","phone":"15618273677"},{"age":24,"company":"伯俊公司3","name":"小涛","phone":"15618273676"}]},{"name":"项目二","users":[{"$ref":"$[0].users[0]"},{"$ref":"$[0].users[1]"},{"$ref":"$[0].users[2]"}]},{"name":"项目三","users":[{"$ref":"$[0].users[0]"},{"$ref":"$[0].users[1]"},{"$ref":"$[0].users[2]"}]}]

                break;
            case R.id.btn_4:
                List<User> UserList2 = new ArrayList<User>();
                UserList2.add(new User("小红","15618273672",27,"伯俊公司1"));
                UserList2.add(new User("小亮","15618273677",25,"伯俊公司2"));
                UserList2.add(new User("小涛","15618273676",24,"伯俊公司3"));


                String jsonString4 =  JsonUtil.convertObjectToJson(UserList2);

                //Json 数据转集合对象
                List<User> UserList3 = null;
                try {
                    UserList3 = JsonUtil.convertJsonToObjectObjList(jsonString4,User.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.d("LUO","======"+UserList3);
                //[com.function.luo.fastjsondemo.User@d12877d, com.function.luo.fastjsondemo.User@dd9f672, com.function.luo.fastjsondemo.User@d10dac3]

                break;
            case R.id.btn_5:
                try{
                    String str2 =   JsonUtil.convertObjectToJson(user);
                    //json 数据转对象
                    User user = JsonUtil.convertJsonToObject(str2,User.class);
                    Log.d("LUO","======"+user);
                    //com.function.luo.fastjsondemo.User@8c51e79
                }catch (Exception e){
                    e.printStackTrace();
                }

                break;
            case R.id.btn_6:

                //二层嵌套解析
                parseData();
                break;
            case R.id.btn_7:
                //三层嵌套解析
                parseData7();
                break;
            default:
                break;
        }
    }

    /**
     * 多级嵌套网络请求
     */
    private void parseData7() {

        Animal animal = new Animal();
        Data data = new Data();

        List<Dog> dogList = new ArrayList<>();
        Dog dog1 = new Dog();
        dog1.setAddress("上海市，闵行625 号");
        dog1.setAge(8);
        dog1.setName("小狗 1");

        Dog dog2 = new Dog();
        dog2.setAddress("上海市，闵行626 号");
        dog2.setAge(9);
        dog2.setName("小狗 2");

        Dog dog3 = new Dog();
        dog3.setAddress("上海市，闵行625 号");
        dog3.setAge(6);
        dog3.setName("小狗 3");

        dogList.add(dog1);
        dogList.add(dog2);
        dogList.add(dog3);
        data.setDog(dogList);
        data.setStore("闵行店");
        data.setId(10);

        animal.setData(data);
        animal.setResult("200");
        animal.setMessage("请求成功");



        String jsonStr = JsonUtil.convertObjectToJson(animal);
        Log.d("LUO","======"+jsonStr);
        try {
            //{"data":{"dog":[{"address":"上海市，闵行625 号","age":8,"name":"小狗 1"},{"address":"上海市，闵行626 号","age":9,"name":"小狗 2"},{"address":"上海市，闵行625 号","age":6,"name":"小狗 3"}],"id":1,"store":"闵行店"},"message":"请求成功","result":"200"}

            JSONObject json =  JsonUtil.convertJsonToObject(jsonStr);
            //看清是值还是对象，值用get（）或getIntValue（），对象用getJSONObject（）
            //获取 result 值
            String result = (String) json.get("result");
            //获取 message 值
            String message = (String) json.get("message");
            //获取 data 下面的 id 值
            int id = json.getJSONObject("data").getIntValue("id");
            //获取 data 下面的 store 值
           String store = json.getJSONObject("data").getString("store");
            //获取 data 下面的 dog集合值（看清是集合还是对象，集合用getJSONArray（），对象用getJSONObject（））
            List<Dog> dogList1 = JsonUtil.convertJsonToObjectObjList(json.getJSONObject("data").getJSONArray("dog").toString(), Dog.class);

            Log.d("LUO","result======"+result);
            // result======200
            Log.d("LUO","message======"+message);
            // message======请求成功
            Log.d("LUO","id======"+id);
            //id======10
            Log.d("LUO","store======"+store);
            //store======闵行店
            Log.d("LUO","======"+dogList1);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 解析二层嵌套示例
     * @
     */
    private void parseData() {

        List<User> UserList = new ArrayList<User>();
        UserList.add(new User("小红","15618273672",27,"伯俊公司1"));
        UserList.add(new User("小亮","15618273677",25,"伯俊公司2"));
        UserList.add(new User("小涛","15618273676",24,"伯俊公司3"));


        UserGroup userGroup = new UserGroup("项目一",UserList);
        String jsonString = JsonUtils.ObjectTojson(userGroup);
        Log.d("LUO","======"+jsonString);
        //{"name":"项目一","users":[{"age":27,"company":"伯俊公司1","name":"小红","phone":"15618273672"},{"age":25,"company":"伯俊公司2","name":"小亮","phone":"15618273677"},{"age":24,"company":"伯俊公司3","name":"小涛","phone":"15618273676"}]}


        try {
            //转化为JSONObject对象
            JSONObject json = JsonUtil.convertJsonToObject(jsonString);

            //获取name 值
            String name = String.valueOf(json.get("name"));


            Log.d("LUO","name======"+name);
            //name======项目一
            //获取 users 集合的值
            List<User> userList = JsonUtil.convertJsonToObjectObjList(json.getJSONArray("users").toString(), User.class);
            Log.d("LUO","======"+userList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void launch(Context mContext) {
        Intent intent = new Intent(mContext,TwoActivity.class);
        mContext.startActivity(intent);
    }
}

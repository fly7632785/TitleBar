# TitleBar
a title bar like iOS 


### ScreenShots
<img src="https://github.com/fly7632785/TitleBar/raw/master/screenshots/shot1.png" width="40%"/>

### Support 
- the most left text and img 
- the most right text and two img
- easy to config in xml
- 4 type of the lef img (None、Back、Close、Menu)
- custom center title layout


### Use
| filed                  |meaning                 |
| -------------          | --------------         |
| tb_left_img    |  left imgSrc   |
| tb_left_text    |  left text   |
| tb_left_img_type   |  NONE、BACK、CLOSE、MENU  |
| tb_left_text_left_drawable   |  left text drawable  |
| tb_center_text   |  title  |
| tb_right_img1   |  right img1  |
| tb_right_img2   |  right img2  |
| tb_right_text   |  right text  |

```
<com.jafir.TitleBar
        android:id="@+id/title_6"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="20dp"
        app:tb_center_text="jafir"
        app:tb_left_img="@drawable/ic_home_up_close"
        app:tb_left_text="退出啦"
        app:tb_right_img1="@drawable/ic_home_up_menu"
        app:tb_right_img2="@drawable/ic_launcher"
        app:tb_right_text="菜单" />
        
**Expose each child view, you can operate it directly and flexible**
```
···
 titleBar6.getLeftImg().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(),"left click",Toast.LENGTH_SHORT).show();
            }
        });
···


### Gradle
```
repositories {
	...
	maven { url 'https://jitpack.io' }
}
```
```
compile 'com.github.fly7632785:TitleBar:1.0.0'
```



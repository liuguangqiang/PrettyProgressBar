PrettyProgressBar
==============

PrettyProgressBar is a collection that contains a lot of progress bar.
If you have a new idea or a issue ,you can contribute to me.

----

##CircleProgressBar

The first progressbar is CircleProgressBar.

It is a awesome progress bar.You can change all of the style.Such as background color,progress color and whether is a filled circle.You can even set the gradient color.

###Screenshot

![image](images/screenshot_circle.gif)

###Sample
```xml
    <com.liuguangqiang.progressbar.CircleProgressBar
        android:id="@+id/progressbar1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>
```

```java
CircleProgressBar progressBar1 = (CircleProgressBar) this.findViewById(R.id.progressbar1);
progressBar1.setProgress(10);
```

---

###Dependencies

####Gradle
```groovy
dependencies {
   compile 'com.github.liuguangqiang.prettyprogressbar:library:0.1.0'
}
```

####Maven
```xml
<dependency>
    <groupId>com.github.liuguangqiang.prettyprogressbar</groupId>
    <artifactId>library</artifactId>
    <version>0.1.1</version>
    <type>aar</type>
</dependency>
```
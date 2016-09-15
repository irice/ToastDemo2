# ToastDemo2
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

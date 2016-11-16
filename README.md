Android Doesn't have clickable email address or phonenumber within textview unlike IOS.
This library is a custom implementation of Android's default textview.

EDIT: Library has been further enhanced, now you can make any pattern clickable just provide regex of that pattern.
In the "How to use library" section below I have explained how to use this library by giving example of three patterns email,
phone and weblink.

In your project gradle put

maven { url "https://jitpack.io" }

It will look something like this


        allprojects {
        repositories {
        jcenter()
        maven { url "https://jitpack.io" }
        }
        }


DEMO:

![alt tag](/demo.gif)


In your app gradle put

          dependencies {
	        compile 'com.github.nitin-jaiman:CustomClickableEmailPhoneTextview:3.0'
	                     }

How to use library



           CustomPartialyClickableTextview customPartialyClickableTextview= (CustomPartialyClickableTextview) findViewById(R.id.textViewCustom);

                /**
                 * Create Objects For Click Patterns
                 */
                ClickPattern email=new ClickPattern();
                ClickPattern phone=new ClickPattern();
                ClickPattern weblink=new ClickPattern();

                /**
                 * set Functionality for what will happen on click of that pattern
                 * In this example pattern is email
                 */
                email.setOnClickListener(new ClickPattern.OnClickListener() {
                    @Override
                    public void onClick() {

                        Toast.makeText(MainActivity.this,"email clicked",Toast.LENGTH_LONG).show();


                    }
                });

                /**
                 * set Functionality for what will happen on click of that pattern
                 * In this example pattern is phone
                 */
                phone.setOnClickListener(new ClickPattern.OnClickListener() {
                    @Override
                    public void onClick() {
                        Toast.makeText(MainActivity.this,"phone clicked",Toast.LENGTH_LONG).show();

                    }
                });

                /**
                 * set Functionality for what will happen on click of that pattern
                 * In this example pattern is weblink
                 */
                weblink.setOnClickListener(new ClickPattern.OnClickListener() {
                    @Override
                    public void onClick() {
                        Toast.makeText(MainActivity.this,"website clicked",Toast.LENGTH_LONG).show();

                    }
                });

                /**
                 * set respective regex string to be used to identify patter
                 */
                email.setRegex("\\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b"); // regex for email
                phone.setRegex("[1-9][0-9]{9,14}"); // regex for phone number
                weblink.setRegex("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]"); // regex for weblink

                /**
                 * add click pattern to the custom textview - first parameter is tag for reference second parameter is ClickPattern object
                 */
                customPartialyClickableTextview.addClickPattern("email",email);
                customPartialyClickableTextview.addClickPattern("phone",phone);
                customPartialyClickableTextview.addClickPattern("weblink",weblink);


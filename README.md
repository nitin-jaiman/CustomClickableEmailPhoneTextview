Android Doesn't have clickable emailaddress or phonenumber within textview unlike IOS. 
This library is a custom implementation of Android's default textview.

In your project gradle put

maven { url "https://jitpack.io" }


DEMO:

![alt tag](/demo.gif)


In your app gradle put

          dependencies {
	        compile 'com.github.nitin-jaiman:CustomClickableEmailPhoneTextview:1.0'
	                     }

How to use library



        customPartialyClickableTextview= (CustomPartialyClickableTextview) findViewById(R.id.textViewCustom);

        customPartialyClickableTextview.setOnHandleClicks(new CustomPartialyClickableTextview.OnHandleClicks() {
            @Override
            public void onEmailClick() {
                Toast.makeText(CustomTextView.this,"email clicked",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onPhoneClick() {

                Toast.makeText(CustomTextView.this,"phone clicked",Toast.LENGTH_LONG).show();

            }
        });


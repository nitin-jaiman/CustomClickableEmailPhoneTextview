package jaiman.nitin.com.customclickableemailphonetextview;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by apple on 10/11/16.
 */

public class CustomPartialyClickableTextview extends TextView {

    private OnHandleClicks onHandleClicks;

    public OnHandleClicks getOnHandleClicks() {
        return onHandleClicks;
    }

    public void setOnHandleClicks(OnHandleClicks onHandleClicks) {
        this.onHandleClicks = onHandleClicks;
    }

    public CustomPartialyClickableTextview(Context context) {
        super(context);
        init(context);

    }

    public CustomPartialyClickableTextview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context,attrs);

    }

    public CustomPartialyClickableTextview(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context,attrs);

    }

    /**
     * Initialize fields.
     *
     * @param context
     * @param attrs
     */
    private void init(Context context, AttributeSet attrs) {


      makeTextEmailClickable(this);
        makeTextPhoneClickable(this);
    } /**
     * Initialize fields.
     *
     * @param context
     */
    private void init(Context context) {


      makeTextEmailClickable(this);
        makeTextPhoneClickable(this);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);


    }

    /**
     * If any phone found from getPhoneFromString() method set spannable on it.
     * @param textView
     */
    public void makeTextPhoneClickable(TextView textView){


        ArrayList<String> emailsFromString= getPhonesFromString(textView.getText().toString());

        int i=0;
        for(String s:emailsFromString){

            SpannableString ss = new SpannableString(textView.getText());
            ClickableSpan span1 = new ClickableSpan() {
                @Override
                public void onClick(View textView) {


                    if(onHandleClicks!=null){

                        onHandleClicks.onPhoneClick();

                    }

                }
            };
            ss.setSpan(span1, textView.getText().toString().indexOf(emailsFromString.get(i)) , textView.getText().toString().indexOf(emailsFromString.get(i))+emailsFromString.get(i).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            textView.setText(ss);
            textView.setMovementMethod(LinkMovementMethod.getInstance());



            i++;

        }


    }

    /**
     * If any email found from getEmailsFromString() method set spannable on it.
     * @param textView
     */
    public void makeTextEmailClickable(TextView textView){

        ArrayList<String> emailsFromString= getEmailsFromString(textView.getText().toString());

        int i=0;
        for(String s:emailsFromString){

            SpannableString ss = new SpannableString(textView.getText());
            ClickableSpan span1 = new ClickableSpan() {
                @Override
                public void onClick(View textView) {

                    if(onHandleClicks!=null){

                        onHandleClicks.onEmailClick();

                    }


                }
            };
            ss.setSpan(span1, textView.getText().toString().indexOf(emailsFromString.get(i)) , textView.getText().toString().indexOf(emailsFromString.get(i))+emailsFromString.get(i).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            textView.setText(ss);
            textView.setMovementMethod(LinkMovementMethod.getInstance());



            i++;

        }


    }


    /**
     * parsing emails
     * @param text
     * @return arraylist of emails
     */
    public ArrayList<String> getEmailsFromString(String text){


        Pattern p = Pattern.compile("\\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(text);
        ArrayList<String> emails = new ArrayList<>();

        while(matcher.find()) {
            emails.add(matcher.group());
        }

        System.out.println(emails);

        return emails;

    }

    /**
     * parsing phone-numbers
     * @param text
     * @return arraylist of phonenumbers
     */
    public ArrayList<String> getPhonesFromString(String text){


        Pattern p = Pattern.compile("[1-9][0-9]{9,14}");
        Matcher matcher = p.matcher(text);
        ArrayList<String> phones = new ArrayList<>();

        while(matcher.find()) {
            phones.add(matcher.group());
        }

        System.out.println(phones);

        return phones;

    }


    interface OnHandleClicks{

        public void onEmailClick();
        public void onPhoneClick();


    }


}

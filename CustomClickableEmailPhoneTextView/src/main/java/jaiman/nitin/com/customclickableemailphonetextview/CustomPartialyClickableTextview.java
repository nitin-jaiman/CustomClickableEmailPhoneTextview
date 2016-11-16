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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by apple on 10/11/16.
 */

public class CustomPartialyClickableTextview extends TextView {

    private HashMap<String, ClickPattern> clickPatterns = new HashMap<String, ClickPattern>();

    public HashMap<String, ClickPattern> getClickPatterns() {
        return clickPatterns;
    }

    public void setClickPatterns(HashMap<String, ClickPattern> clickPatterns) {
        this.clickPatterns = clickPatterns;
    }


    public CustomPartialyClickableTextview(Context context) {
        super(context);
        init(context);

    }

    public CustomPartialyClickableTextview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs);

    }

    public CustomPartialyClickableTextview(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context, attrs);

    }

    /**
     * Initialize fields.
     *
     * @param context
     * @param attrs
     */
    private void init(Context context, AttributeSet attrs) {

        //  makeTextEmailClickable(this);
        //  makeTextPhoneClickable(this);


        makePatternsClickable(this);
    }

    /**
     * Initialize fields.
     *
     * @param context
     */
    private void init(Context context) {


        //  makeTextEmailClickable(this);
        //  makeTextPhoneClickable(this);


        makePatternsClickable(this);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);


    }


    private void makePatternsClickable(TextView textView) {


        Iterator it = clickPatterns.entrySet().iterator();


        while (it.hasNext()) {

            Map.Entry pair = (Map.Entry) it.next();
            final ClickPattern clickPattern = (ClickPattern) pair.getValue();

            ArrayList<String> emailsFromString = getPatternFromString(textView.getText().toString(), clickPattern.getRegex());

            int i = 0;
            for (String s : emailsFromString) {

                SpannableString ss = new SpannableString(textView.getText());
                ClickableSpan span1 = new ClickableSpan() {
                    @Override
                    public void onClick(View textView) {


                        clickPattern.getOnClickListener().onClick();

                    }
                };
                ss.setSpan(span1, textView.getText().toString().indexOf(emailsFromString.get(i)), textView.getText().toString().indexOf(emailsFromString.get(i)) + emailsFromString.get(i).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                textView.setText(ss);
                textView.setMovementMethod(LinkMovementMethod.getInstance());


                i++;

            }
        }


    }


    /**
     * parsing pattern
     *
     * @param text
     * @return arraylist of emails
     */
    public ArrayList<String> getPatternFromString(String text, String regex) {


        Pattern p = Pattern.compile(regex,
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(text);
        ArrayList<String> patterns = new ArrayList<>();

        while (matcher.find()) {
            patterns.add(matcher.group());
        }

        System.out.println(patterns);

        return patterns;

    }


    public void addClickPattern(String tag, ClickPattern clickPattern) {


        if (clickPatterns != null) {

            clickPatterns.put(tag, clickPattern);

            makePatternsClickable(this);
        }

    }


}

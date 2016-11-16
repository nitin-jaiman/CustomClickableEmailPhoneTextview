package jaiman.nitin.com.customclickableemailphonetextview;


/**
 * Created by apple on 16/11/16.
 */

public class ClickPattern {

    /**
     * Regex String to find pattern
     */
    private String regex;

    /**
     * Click Listener to give implementation of onClick
     * on the pattern. For ex: to print toast of "Email Clicked "
     * on click of emails you have to give an implementation for this
     */
    private OnClickListener onClickListener;

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }


    /**
     * Interface for click
     */
   public interface OnClickListener{

        public void onClick();

    }


}

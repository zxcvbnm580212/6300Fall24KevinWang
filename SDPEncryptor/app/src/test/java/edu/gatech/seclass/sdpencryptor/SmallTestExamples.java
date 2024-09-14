package edu.gatech.seclass.sdpencryptor;

import static org.junit.Assert.fail;

import android.view.View;
import android.widget.TextView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

/**
 * This is a Georgia Tech provided code example for use in assigned private GT
 * repositories. Students and other users of this template code are advised not
 * to share it with other students or to make it available on publicly viewable
 * websites including repositories such as github and gitlab.  Such sharing may
 * be investigated as a GT honor code violation. Created for CS6300.
 */

@RunWith(RobolectricTestRunner.class)
public class SmallTestExamples {

    private MainActivity activity;
    private RobolectricViewAssertions rva = new RobolectricViewAssertions();

    @Before
    public void setUp() {
        activity = Robolectric.buildActivity(MainActivity.class).create().get();
        rva.setActivity(activity);
    }

    @Test(timeout = 500)
    public void Screenshot1() {
        rva.replaceText(R.id.plainTextID, "Cat & Dog");
        rva.replaceText(R.id.multiplierArgID, "5");
        rva.replaceText(R.id.adderArgID, "8");
        rva.clickWithTimeoutAndDefaultMessage(R.id.encryptButtonID);

        rva.assertTextViewText(R.id.cipherTextID, "Ogi & Tof");
    }

    @Test(timeout = 500)
    public void Screenshot2() {
        rva.replaceText(R.id.plainTextID, "Up with the White And Gold!");
        rva.replaceText(R.id.multiplierArgID, "1");
        rva.replaceText(R.id.adderArgID, "1");
        rva.clickWithTimeoutAndDefaultMessage(R.id.encryptButtonID);

        rva.assertTextViewText(R.id.cipherTextID, "uQ XJUI UIF wIJUF aOE gPME!");
    }

    @Test(timeout = 500)
    public void Screenshot3() {
        rva.replaceText(R.id.plainTextID, "abcdefg");
        rva.replaceText(R.id.multiplierArgID, "5");
        rva.replaceText(R.id.adderArgID, "1");
        rva.clickWithTimeoutAndDefaultMessage(R.id.encryptButtonID);

        rva.assertTextViewText(R.id.cipherTextID, "DINSX4C");
    }

    @Test(timeout = 500)
    public void trigger() {
        rva.replaceText(R.id.plainTextID, "__trigger__");
        rva.replaceText(R.id.multiplierArgID, "5");
        rva.replaceText(R.id.adderArgID, "1");
        rva.clickWithTimeoutAndDefaultMessage(R.id.encryptButtonID);

        rva.assertTextViewText(R.id.cipherTextID, "__F0MCCX0__");
    }

    @Test(timeout = 500)
    public void errorTest1() {
        rva.replaceText(R.id.plainTextID, "");
        rva.replaceText(R.id.multiplierArgID, "0");
        rva.replaceText(R.id.adderArgID, "0");
        rva.clickWithTimeoutAndDefaultMessage(R.id.encryptButtonID);

        rva.assertTextViewError(R.id.plainTextID, "Invalid Plain Text");
        rva.assertTextViewError(R.id.multiplierArgID, "Invalid Multiplier Arg");
        rva.assertTextViewError(R.id.adderArgID, "Invalid Adder Arg");
        rva.assertTextViewText(R.id.cipherTextID, "");
    }

    @Test(timeout = 500)
    public void gradingTest13() {
        rva.replaceText(R.id.plainTextID, "Panda Cat");
        rva.replaceText(R.id.multiplierArgID, "23");
        rva.replaceText(R.id.adderArgID, "1");
        rva.clickWithTimeoutAndDefaultMessage(R.id.encryptButtonID);
        rva.assertTextViewText(R.id.cipherTextID, "eMBTM pMP");
    }
}

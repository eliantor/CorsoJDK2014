package me.aktor.corsoapp.corso;

import me.aktor.corsoapp.corso.managers.PrefManager;

/**
 * Created by Andrea Tortorella on 19/07/14.
 */
public class BeanBean {
    public static final String BORLOTTO = "borlotto";
    public static final String CANNELLINO = "borlotto";
    public static final String BIANCO_DI_SPAGNA = "borlotto";

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {

        this.type = type;
    }

    public static void save(BeanBean bean){
        PrefManager pref = CorsoApp.get().getPref();
        pref.setDontKnowWhatThisMean(bean.type);
    }
}
